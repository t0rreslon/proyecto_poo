public abstract class Equipo {
    private String codigo;
    private String marca;
    private boolean disponible;

    public Equipo(String codigo, String marca, boolean disponible){
        this.codigo = codigo;
        this.marca = marca;
        this.disponible = disponible;
    }

     public String getCodigo() {
         return codigo;
     }

     public String getMarca() {
         return marca;
     }

    public boolean isDisponible(){
    return disponible;
    }

    void marcarPrestado(){
        this.disponible = false;
    }

    void marcarDisponible(){
        this.disponible = true;
    }

     public abstract double getMultaDiaria();

}

