import java.util.ArrayList;
import java.util.List;

public class RepositorioPrestamosMemoria implements RepositorioMemoria {

    private List<Prestamo> prestamos = new ArrayList<>();

    @Override
    public void guardar(Prestamo prestamo) {
        prestamos.add(prestamo);
    }

    @Override
    public List<Prestamo> obtenerTodos() {
        return prestamos;
    }
}