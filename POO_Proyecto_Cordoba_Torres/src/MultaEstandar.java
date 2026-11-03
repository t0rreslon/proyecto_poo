import java.util.List;

public class MultaEstandar implements CalculadoraMulta{

    @Override 
    public double calcularMulta(List<Equipo> equipos, int diasRetraso){
   
        double total = 0;

    for(Equipo equipo : equipos){
        total += equipo.getMultaDiaria() * diasRetraso;
    }
    return total;
    }
 
}