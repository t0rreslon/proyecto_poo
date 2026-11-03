public class Tablet extends Equipo{

    public Tablet(String codigo, String marca, boolean disponible){
            super(codigo, marca, disponible);
    }

    @Override
    public double getMultaDiaria(){
        return 8000;
    }

}