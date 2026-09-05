import java.util.LinkedList;
import java.util.Queue;


public class BufferMonitor {

    private final Queue<Integer> buffer = new LinkedList<>();
    private final int capacidadMaxima;

    public BufferMonitor(int capacidadMaxima) {
        this.capacidadMaxima = capacidadMaxima;
    }


    public synchronized void producir(int dato) throws InterruptedException {

        while (buffer.size() == capacidadMaxima) {
            System.out.println("[Productor] Buffer lleno, esperando...");
            wait(); 
        }

        buffer.add(dato);
        System.out.println("[Productor] Produjo: " + dato + " | Buffer: " + buffer);


        notifyAll();
    }


    public synchronized int consumir() throws InterruptedException {

        while (buffer.isEmpty()) {
            System.out.println("[Consumidor] Buffer vacío, esperando...");
            wait();
        }

        int dato = buffer.poll();
        System.out.println("[Consumidor] Consumió: " + dato + " | Buffer: " + buffer);


        notifyAll();

        return dato;
    }
}