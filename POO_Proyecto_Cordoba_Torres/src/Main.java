import java.util.ArrayList;
import java.util.List;
 
public class Main {
    public static void main(String[] args) {
 
        // 1. Crear dos portatiles, un proyector y una tablet; una estudiante y un docente.
            
        Portatil portatil1 = new Portatil("PO-001", "Asus", true, 16, "101", 20);
        Portatil portatil2 = new Portatil("PO-002", "Lenovo", true, 24, "102", 30);
        Proyector proyector = new Proyector("PR-001", "Videobeam", true, 2000);
        Tablet tablet = new Tablet("TA-001", "Lenovo", true);
        Estudiante isabella = new Estudiante("Isabella Torres", "1033466767", "Ingenieria de Software");
        Docente ramiro = new Docente("Ramiro Lopera", "3456781", "Sistemas Digitales");
 
        List<Equipo> inventario = new ArrayList<>();
        inventario.add(portatil1);
        inventario.add(portatil2);
        inventario.add(proyector);
        inventario.add(tablet);
 
        RepositorioMemoria repositorio = new RepositorioPrestamo();

        ServicioPrestamos servicio = new ServicioPrestamos(
            new MultaEstandar(),
            new NotificacionConsola(),
            repositorio
        );
 
        // 2. La estudiante presta un portátil. El docente presta el proyector y la tablet. 

        Prestamo prestamoEstudiante = new Prestamo(isabella, "Prestamo de portátil");
        prestamoEstudiante.agregarEquipo(portatil1);
        servicio.registrarPrestamo(prestamoEstudiante);
 
        Prestamo prestamoDocente = new Prestamo(ramiro, "Prestamo de proyector y tablet");
        prestamoDocente.agregarEquipo(proyector);
        prestamoDocente.agregarEquipo(tablet);
        servicio.registrarPrestamo(prestamoDocente);
 
        //3. Intente que la estudiante preste otro portatil y que el docente preste el portatil ocupado.
        //Ambos deben ser rechazados con un mensaje claro. 

        try {
            Prestamo segundoPrestamo = new Prestamo(isabella, "Segundo prestamo de portátil");
            segundoPrestamo.agregarEquipo(portatil2);
            servicio.registrarPrestamo(segundoPrestamo);
        } catch (IllegalStateException ex) {
            System.out.println("Prestamo rechazado para " + isabella.getNombre() + ": " + ex.getMessage());
        }
 
        try {
            Prestamo prestamoOcupado = new Prestamo(ramiro, "Prestamo de portátil ocupado");
            prestamoOcupado.agregarEquipo(portatil1);
            servicio.registrarPrestamo(prestamoOcupado);
        } catch (IllegalStateException ex) {
            System.out.println("Prestamo rechazado para " + ramiro.getNombre() + ": " + ex.getMessage());
        }
 
        // 4. Imprimir el inventario
        System.out.println("======================");
        System.out.println("======INVENTARIO======");
        System.out.println("======================");

        ReporteUniversitario reporte = new ReporteUniversitario();
        reporte.generarReporte(inventario);
 
        // 5. La estudiante devuelve a los 5 días (2 de retraso). El docente a los 8 (a tiempo).

        double multaEstudiante = servicio.devolverPrestamo(prestamoEstudiante, 5);
        double multaDocente = servicio.devolverPrestamo(prestamoDocente, 8);
 
        /* 6. Otro servicio con la multa de semana universitaria y devolución tardía. */
        ServicioPrestamos servicioSemana = new ServicioPrestamos(
            new MultaUniversitaria(),
            new NotificacionConsola(),
            new RepositorioPrestamo()
        );
 
        Prestamo prestamoSemana = new Prestamo(isabella, "Préstamo durante semana universitaria");
        prestamoSemana.agregarEquipo(portatil1);
        servicioSemana.registrarPrestamo(prestamoSemana);
        double multaSemana = servicioSemana.devolverPrestamo(prestamoSemana, 5);
 
        /* 7. Total de multas e inventario final. */
        double totalMultas = multaEstudiante + multaDocente + multaSemana;
 
        System.out.println("\n======================");
        System.out.println("====== TOTAL MULTAS ======");
        System.out.println("======================");
        System.out.printf("Multa estudiante: $%.0f%n", multaEstudiante);
        System.out.printf("Multa docente: $%.0f%n", multaDocente);
        System.out.printf("Multa semana universitaria: $%.0f%n", multaSemana);
        System.out.printf("TOTAL DE MULTAS: $%.0f%n", totalMultas);
 
        System.out.println("\n======================");
        System.out.println("===== INVENTARIO FINAL =====");
        System.out.println("======================");
        reporte.generarReporte(inventario);
    }
}
 