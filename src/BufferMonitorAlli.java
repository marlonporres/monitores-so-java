public class BufferMonitorAlli {
    private int dato;
    private boolean lleno = false;

    public synchronized void producir(int valor) throws InterruptedException {
        while (lleno) {
            System.out.println("[Productor] Buffer LLENO. Esperando...");
            wait(); 
        }
        dato = valor;
        lleno = true;
        System.out.println("[Productor] Colocó el número: " + dato);
        notifyAll(); 
    }

    public synchronized int consumir() throws InterruptedException {
        while (!lleno) {
            System.out.println("[Consumidor] Buffer VACÍO. Esperando...");
            wait(); 
        }
        lleno = false;
        System.out.println("[Consumidor] Retiró el número: " + dato);
        notifyAll(); 
        return dato;
    }
}
