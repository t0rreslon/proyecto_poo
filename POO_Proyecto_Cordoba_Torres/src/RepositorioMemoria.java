import java.util.List;

public interface RepositorioMemoria {

    void guardar(Prestamo prestamo);

    List<Prestamo> obtenerTodos();
}