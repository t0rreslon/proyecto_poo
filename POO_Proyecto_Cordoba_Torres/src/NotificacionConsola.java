public class NotificacionConsola implements Notificador {

    @Override
    public void enviar(String mensaje) {
        System.out.println(mensaje);
    }
}