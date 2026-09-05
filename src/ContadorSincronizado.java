import java.util.ArrayList;
import java.util.List;

/**
 * Ejercicio 1: Contador protegido con synchronized.
 *
 * Idea: varios hilos incrementan el mismo contador muchas veces.
 * Como el metodo incrementar() esta marcado como synchronized,
 * cada hilo debe adquirir el "monitor lock" del objeto Contador
 * antes de poder ejecutar el incremento. Mientras un hilo esta
 * dentro del metodo, ningun otro hilo puede entrar a el (sobre
 * el mismo objeto), asi que no se pierden incrementos.
 */
public class ContadorSincronizado {

    // Clase que representa el contador compartido entre hilos.
    static class Contador {
        private int valor = 0;

        // synchronized -> solo un hilo a la vez puede ejecutar este
        // metodo sobre esta instancia de Contador (exclusion mutua),
        // y ademas garantiza que los cambios queden visibles para
        // el siguiente hilo que entre (visibilidad de memoria).
        public synchronized void incrementar() {
            valor++; // operacion de 3 pasos (leer, sumar, escribir) protegida
        }

        public synchronized int getValor() {
            return valor;
        }
    }

    public static void main(String[] args) throws InterruptedException {
        final int NUM_HILOS = 10;
        final int INCREMENTOS_POR_HILO = 100_000;

        Contador contador = new Contador();
        List<Thread> hilos = new ArrayList<>();

        // Creamos NUM_HILOS hilos, cada uno incrementando el contador
        // INCREMENTOS_POR_HILO veces.
        for (int i = 0; i < NUM_HILOS; i++) {
            Thread hilo = new Thread(() -> {
                for (int j = 0; j < INCREMENTOS_POR_HILO; j++) {
                    contador.incrementar();
                }
            });
            hilos.add(hilo);
            hilo.start();
        }

        // Esperamos a que TODOS los hilos terminen antes de leer el resultado.
        for (Thread hilo : hilos) {
            hilo.join();
        }

        int valorEsperado = NUM_HILOS * INCREMENTOS_POR_HILO;
        int valorReal = contador.getValor();

        System.out.println("=== Contador CON synchronized ===");
        System.out.println("Hilos: " + NUM_HILOS);
        System.out.println("Incrementos por hilo: " + INCREMENTOS_POR_HILO);
        System.out.println("Valor esperado: " + valorEsperado);
        System.out.println("Valor obtenido: " + valorReal);
        System.out.println("Resultado correcto: " + (valorEsperado == valorReal));
    }
}