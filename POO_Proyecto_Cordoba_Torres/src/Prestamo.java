import java.util.ArrayList;
import java.util.List;

public class Prestamo {
    private static int contadorId = 0; // contador estático

    private int prestamoId;          // Código identificador del préstamo
    private String descripcion;      // Descripción del préstamo
    private Persona persona;         // Persona que realiza el préstamo
    private List<Equipo> equipos;    // Un préstamo puede tener varios equipos
    private boolean activo;

    public Prestamo(Persona persona, String descripcion) {
        this.prestamoId = ++contadorId;
        this.persona = persona;
        this.descripcion = descripcion;
        this.equipos = new ArrayList<>();
        this.activo = true;
    }

    public static int getContadorId() {
        return contadorId;
    }

  
    public void agregarEquipo(Equipo equipo) {
        if (!equipo.isDisponible()) {
            throw new IllegalStateException("El equipo que desea prestar no esta disponible");
        }
        if (equipos.contains(equipo)) {
            throw new IllegalStateException("El equipo ya fue agregado a este prestamo");
        }
        this.equipos.add(equipo);
    }

    public void agregarEquipo(List<Equipo> nuevos) {
        // Primero se valida todo, así no queda el préstamo a medias
        for (Equipo equipo : nuevos) {
            if (!equipo.isDisponible()) {
                throw new IllegalStateException("Uno de los equipos que desea prestar no esta disponible");
            }
        }
        for (Equipo equipo : nuevos) {
            agregarEquipo(equipo);
        }
    }

    /* Visibilidad de paquete: lo llama ServicioPrestamos DESPUÉS de validar el límite. */
    void confirmarEquipos() {
        for (Equipo equipo : equipos) {
            equipo.marcarPrestado();
        }
    }

    public int getCantidadEquipos() {
        return equipos.size();
    }

    public Persona getPersona() {
        return persona;
    }

    public boolean isActivo() {
        return activo;
    }

    public void finalizarPrestamo() {
        this.activo = false;
    }

    public List<Equipo> getEquipo() {
        return equipos;
    }
}