class Estudiante extends Persona{
    public String programa;

public Estudiante(String nombre, String documento, String programa){
    super(nombre,documento);
    this.programa = programa;
}

public String getPrograma() {
    return programa;
}

@Override 
public int getmaximoDias(){
    return 3;
}

@Override
public int getmaximoEquipos(){
    return 1;
}
}