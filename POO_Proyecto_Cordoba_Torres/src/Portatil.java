public class Portatil extends Equipo {
    private int memoriaRam;
    private final Cargador cargador;

    public Portatil(String codigo, String marca, boolean disponible, int memoriaRam,
                    String serialCargador, int potenciaCargador) {
        super(codigo, marca, disponible);
        this.memoriaRam = memoriaRam;
        this.cargador = new Cargador(serialCargador, potenciaCargador); // composición
    }

    public int getMemoriaRam() {
        return memoriaRam;
    }

    public Cargador getCargador() {
        return cargador;
    }

    @Override
    public double getMultaDiaria() {
        return 15000;
    }
}