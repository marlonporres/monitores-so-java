public class PruebasAlli {
    
    public static void main(String[] args) {
        BufferMonitorAlli monitor = new BufferMonitorAlli();

        // Creamos el hilo del Productor
        Thread productor = new Thread(() -> {
            try {
                for (int i = 1; i <= 5; i++) {
                    monitor.producir(i);
                    Thread.sleep(500); // Pausa para ver el flujo lento
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });

        // Creamos el hilo del Consumidor
        Thread consumidor = new Thread(() -> {
            try {
                for (int i = 1; i <= 5; i++) {
                    monitor.consumir();
                    Thread.sleep(800); // Pausa un poco más larga
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });

        // Arrancamos la simulación
        productor.start();
        consumidor.start();
    }
}
