public class Consumidor extends Thread {

    private BufferMonitor buffer;
    private int cantidad;
    public Consumidor(BufferMonitor buffer, int cantidad) {
        this.buffer = buffer;
        this.cantidad = cantidad;

    }

    @Override
    public void run() {
        
        for (int i = 0; i < cantidad; i++) {
            try {
                int dato = buffer.consumir();
                System.out.println(
                    "Consumidor obtuvo: " + dato
                );

            } catch (InterruptedException e) {
                System.out.println(
                    "Consumidor fue interrumpido."
                );
                Thread.currentThread().interrupt();

                break;
            }
        }
    }
}