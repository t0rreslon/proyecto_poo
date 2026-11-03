public class Portatil extends Equipo{
    private int memoriaRam;
    private Cargador cargador;

    public Portatil(String codigo, String marca, boolean disponible, int memoriaRam, Cargador cargador){
        super(codigo, marca, disponible);
        this.memoriaRam = memoriaRam;
    }
    public int getMemoriaram() {
        return memoriaRam;
    }

    @Override
    public double getMultaDiaria(){
        return 15000;
    }

    
}