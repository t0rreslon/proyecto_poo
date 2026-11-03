public  class Proyector extends Equipo{
    private int lumenes;

    public Proyector(String codigo, String marca, boolean disponible, int lumenes){
        super(codigo, marca, disponible);
        this.lumenes = lumenes;
    }

    public int getLumenes() {
        return lumenes;
    }

    @Override
    public double getMultaDiaria(){
        return 20000;
    }
}