import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        /* ******************************************************************************* */
        /* 1. Cree dos portátiles, un proyector y una tablet; una estudiante y un docente. */
        /* ******************************************************************************* */
        ArrayList<Estudiante> e = new ArrayList<>(); // <-- lista de estudiantes
        ArrayList<Docente> d = new ArrayList<>(); // <-- lista de docentes
        ArrayList<Portatil> p = new ArrayList<>(); // <-- lista de portatiles
        ArrayList<Proyector> pr = new ArrayList<>(); // <-- lista de proyectores
        ArrayList<Tablet> t = new ArrayList<>(); // <-- lista de tablets
        ArrayList<Cargador> c = new ArrayList<>(); // <-- lista de cargadores
        // cargadores
        c.add(new Cargador("101", 20));
        c.add(new Cargador("102", 30));
        c.add(new Cargador("103", 25));
        // 2 portatiles
        p.add(new Portatil("PO-001", "Dell", true, 16, "101"));
        p.add(new Portatil("PO-002", "Lenovo", true, 24, "102"));
        // 1 proyector
        pr.add(new Proyector("PR-001", "Videobeam", true, 2000));
        // 1 tablet
        t.add(new Tablet("TA-001", "Lenovo", true));
        // 1 estudiante
        e.add(new Estudiante("Isabella Torres", "1033466767", "Ingenieria de Software"));
        // 1 docente
        d.add(new Docente("Ramiro Lopera", "3456781", "Sistemas Digitales"));
        /* ******************************************************************************** */
        /* 2. La estudiante presta un portátil. El docente presta el proyector y la tablet. */
        /* ******************************************************************************** */
        List<Prestamo> prestamos = new ArrayList<>();

        Prestamo prestamoEstudiante =
            new Prestamo(e.get(0), "Préstamo de portátil");

        prestamoEstudiante.agregarEquipo(p.get(0));

        prestamos.add(prestamoEstudiante);


        // Préstamo del docente
        Prestamo prestamoDocente =
            new Prestamo(d.get(0), "Préstamo de proyector y tablet");

        prestamoDocente.agregarEquipo(pr.get(0));
        prestamoDocente.agregarEquipo(t.get(0));

        prestamos.add(prestamoDocente);
        
        /* ******************************************************************************** */
        /* 3. Intente que la estudiante preste otro portátil y 
                que el docente preste el portátil ocupado.
                Ambos deben ser rechazados con un mensaje claro. */
        /* ******************************************************************************** */
        RepositorioMemoria repositorio = new RepositorioPrestamo();

        ServicioPrestamos servicio = new ServicioPrestamos(
            new MultaEstandar(),
            new NotificacionConsola(),
            repositorio
        );

        for (Prestamo prestamo : prestamos) {
            servicio.registrarPrestamo(prestamo);
        }

        Prestamo prestamoEstudiante1 = new Prestamo(
            e.get(0),
            "Segundo préstamo de portátil"
        );

        prestamoEstudiante1.agregarEquipo(p.get(1));

        try {
            servicio.registrarPrestamo(prestamoEstudiante1);
        } catch (IllegalStateException ex) {
            System.out.println("Préstamo rechazado para Isabella: "
                + ex.getMessage());
        }

        Prestamo prestamoDocente1 = new Prestamo(
            d.get(0),
            "Préstamo de portátil ocupado"
        );

        try {
            prestamoDocente1.agregarEquipo(p.get(0));
            servicio.registrarPrestamo(prestamoDocente1);

        } catch (IllegalStateException ex) {
            System.out.println("Préstamo rechazado para Ramiro: "
                + ex.getMessage());
        }
        
        /* ************************* */
        /* 4. Imprima el inventario. */
        /* ************************* */
        System.out.println("======================");
        System.out.println("======INVENTARIO======");
        System.out.println("======================");
        List<Equipo> inventario = new ArrayList<>();
        inventario.addAll(p);
        inventario.addAll(pr);
        inventario.addAll(t);

        ReporteUniversitario reporte = new ReporteUniversitario();
        reporte.generarReporte(inventario);

        /* ***************************************************** */
        /* 5. La estudiante devuelve a los 5 días (2 de retraso).
        El docente devuelve a los 8 días (a tiempo).             */
        /* ***************************************************** */
        double multaEstudiante =
            servicio.devolverPrestamo(prestamoEstudiante, 5);

        double multaDocente =
            servicio.devolverPrestamo(prestamoDocente, 8);

        System.out.printf("Multa de la estudiante: $%.0f%n", multaEstudiante);
        System.out.printf("Multa del docente: $%.0f%n", multaDocente);

        
        /* ***************************************************** */
        /* 6. Cree otro servicio con la multa de semana universitaria
            y repita un préstamo con devolución tardía.          */
        /* ***************************************************** */
        RepositorioMemoria repositorioSemana =
        new RepositorioPrestamo();

        ServicioPrestamos servicioSemana = new ServicioPrestamos(
            new MultaUniversitaria(),
            new NotificacionConsola(),
            repositorioSemana
        );

        Prestamo prestamoSemana = new Prestamo(
        e.get(0),
        "Préstamo durante semana universitaria"
        );

        prestamoSemana.agregarEquipo(p.get(0));

        servicioSemana.registrarPrestamo(prestamoSemana);

        double multaSemana = servicioSemana.devolverPrestamo(
            prestamoSemana,
            5
        );

        System.out.printf(
            "Multa de semana universitaria: $%.0f%n",
            multaSemana
        );
        
        /* ***************************************************** */
        /* 7. Imprima el total de multas y el inventario final.  */
        /* ***************************************************** */
        
        double totalMultas = multaEstudiante + multaDocente + multaSemana;

        System.out.println("\n======================");
        System.out.println("====== TOTAL MULTAS ======");
        System.out.println("======================");

        System.out.printf("Multa estudiante: $%.0f%n", multaEstudiante);
        System.out.printf("Multa docente: $%.0f%n", multaDocente);
        System.out.printf("Multa semana universitaria: $%.0f%n", multaSemana);

        System.out.printf("TOTAL DE MULTAS: $%.0f%n", totalMultas);


        // Imprimir inventario final
        System.out.println("\n======================");
        System.out.println("===== INVENTARIO FINAL =====");
        System.out.println("======================");

        reporte.generarReporte(inventario);

    }
}

