class Docente extends Persona{
    private String dependencia;

    public Docente(String nombre, String documento, String dependecia){
        super(nombre, documento);
        this.dependencia = dependecia;
    }

    public String getDependencia() {
        return dependencia;
    }

@Override 
public int getmaximoDias(){
    return 10;
}

@Override
public int getmaximoEquipos(){
    return 3;
}

}