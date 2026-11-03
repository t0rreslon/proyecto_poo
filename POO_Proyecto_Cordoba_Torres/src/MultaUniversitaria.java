import java.util.List;


public class MultaUniversitaria implements CalculadoraMulta{

    private MultaEstandar multaNormal;

    public MultaUniversitaria(){
        this.multaNormal = new MultaEstandar();
    }

@Override 
public double calcularMulta(List<Equipo> equipos, int diasRetraso){
   return multaNormal.calcularMulta(equipos, diasRetraso) * 0.5;
}

}