public class Productor extends Thread 
{

    private BufferMonitor buffer;

    public Productor(BufferMonitor buffer) 
    {
        this.buffer = buffer;
    }

    @Override
    public void run() 
    {

        for (int i = 1; i <= 10; i++) 
            {
                
            try 
            {
            //String dato = "Dato-" + i;

            buffer.producir(i);
            
            System.out.println("Productor genero: " + i);
            
                Thread.sleep(500);
            } catch (InterruptedException e) 
            {
                System.out.println("El productor fue interrumpido.");
                Thread.currentThread().interrupt();
                break;
            }
        }

        System.out.println("Productor termino de generar datos.");
    }
}