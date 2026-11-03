import java.util.ArrayList;
import java.util.List;

public class ServicioPrestamos {
    private RepositorioMemoria repositorio;
    private CalculadoraMulta cobroMultas;
    private Notificador notificacion;

    public ServicioPrestamos(CalculadoraMulta cobroMultas, Notificador notificacion,RepositorioMemoria repositorio){
        this.cobroMultas = cobroMultas;
        this.notificacion = notificacion;
        this.repositorio = repositorio;
    }

    public void registrarPrestamo(Prestamo prestamo) {
    Persona persona = prestamo.getPersona();

    validarLimiteEquipos(
        persona,
        prestamo.getCantidadEquipos()
    );

    for (Equipo equipo : prestamo.getEquipo()) {
        equipo.marcarPrestado();
    }

    repositorio.guardar(prestamo);
    notificacion.enviar("Prestamo registrado correctamente");
}

    public int contarEquiposPrestados(Persona persona) {
    int total = 0;

    for (Prestamo prestamo : repositorio.obtenerTodos()) {
        if (prestamo.getPersona() == persona && prestamo.isActivo()) {
            total += prestamo.getCantidadEquipos();
        }
    }   

    return total;
}

   public void validarLimiteEquipos(Persona persona, int cantidadNueva) {
    int cantidadActual = contarEquiposPrestados(persona);
    int maximo = persona.getmaximoEquipos();

    if (cantidadActual + cantidadNueva > maximo) {
        throw new IllegalStateException(
            "La persona supera el maximo de equipos permitidos"
        );
    }
}

   public double devolverPrestamo(Prestamo prestamo, int diasUsados) {

    int diasRetraso = Math.max(
        0,
        diasUsados - prestamo.getPersona().getmaximoDias()
    );

    double multa = cobroMultas.calcularMulta(
        prestamo.getEquipo(),
        diasRetraso
    );

    for (Equipo equipo : prestamo.getEquipo()) {
        equipo.marcarDisponible();
    }

    prestamo.finalizarPrestamo();

    notificacion.enviar("Devolucion registrada correctamente");
    return multa;
}



}

