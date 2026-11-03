import java.util.List;

public class ReporteUniversitario {

    public void generarReporte(List<Equipo> equipos) {

        for (Equipo equipo : equipos) {

            System.out.println("Codigo: " + equipo.getCodigo());
            System.out.println("Marca: " + equipo.getMarca());

            if (equipo.isDisponible()) {
                System.out.println("Estado: Disponible");
            } else {
                System.out.println("Estado: Prestado");
            }

            System.out.println("----------------------");
        }
    }
}