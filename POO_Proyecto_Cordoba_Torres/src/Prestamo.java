import java.util.ArrayList;
import java.util.List;

public class Prestamo {
     private static int contadorId = 0; // contador estatico

     private int prestamoId; //Codigo identificador del prestamo
     private String descripcion; // Descripcion del prestamo
     private Persona persona; //Atributo de tipo Persona (Relaciona a una perosna con un prestamo)
     private List<Equipo> equipos; //Lista de equipos (Un prestamos puede tener una listta de equipos)
      private boolean activo;

      public Prestamo(Persona persona, String descripcion){
        this.prestamoId= ++contadorId; // Inicialisamos el prestamoID (genera el numero consecutivo)
        this.persona = persona; 
        this.descripcion = descripcion;
        this.equipos = new ArrayList<>();
        this.activo = true;
      }

      public static int getContadorId() {
          return contadorId;
      } // Sirve para vizualizar el numero actual del contador
    
      public void agregarEquipo(Equipo equipo){
        if(equipo.isDisponible() == false){
            throw new IllegalStateException("El equipo que desea prestar no esta disponible");      
          }
        this.equipos.add(equipo);
        equipo.marcarPrestado();
      }  

      public void agregarEquipo(List<Equipo> equipos){
        for(Equipo equipo : equipos){
            if(equipo.isDisponible() == false){
                throw new IllegalStateException("Uno de los equipos que desea prestar no esta disponible");        }
            }

        for(Equipo equipo : equipos){
            this.equipos.add(equipo);
            equipo.marcarPrestado();
        }     
        
      }

        public int getCantidadEquipos(){
          return equipos.size();
        }
        
        public Persona getPersona(){
          return persona;
        }

        public boolean isActivo(){
          return activo;
        }

        public void finalizarPrestamo(){
          this.activo = false;
        }

        public List<Equipo> getEquipo(){
          return equipos;
        }

}


