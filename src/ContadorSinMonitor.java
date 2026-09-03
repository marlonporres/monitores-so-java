import java.util.ArrayList;
import java.util.List;

/**
 * Ejercicio 2: Contador SIN sincronizacion (race condition).
 *
 * Idea: mismo escenario que ContadorSincronizado, pero el metodo
 * incrementar() NO tiene proteccion. Como "valor++" en realidad
 * son 3 pasos (leer, sumar, escribir), dos hilos pueden leer el
 * mismo valor antes de que ninguno escriba su resultado, y uno de
 * los dos incrementos se "pierde".
 *
 * El programa compila y corre sin errores ni excepciones: el
 * problema es silencioso, solo se nota comparando el valor final
 * contra el valor esperado.
 */
public class ContadorSinMonitor {

    static class Contador {
        private int valor = 0;

        // Sin synchronized: no hay exclusion mutua ni garantia de
        // visibilidad entre hilos.
        //
        // "valor++" se separa aqui en sus 3 pasos reales (leer, sumar,
        // escribir) y se agrega un Thread.yield() justo entre la lectura
        // y la escritura. Esto es una tecnica didactica estandar para
        // ensanchar la "ventana" de la condicion de carrera: en hardware
        // moderno (varios nucleos, operaciones nanosegundo) la colision
        // puede tardar en aparecer por azar; forzar el cambio de contexto
        // en ese punto exacto hace que el error se vea de forma
        // consistente y reproducible en la demostracion.
        public void incrementar() {
            int temporal = valor;   // 1. leer
            try {
                Thread.sleep(1);         // fuerza a que otro hilo pueda intercalarse aqui
            } catch (InterruptedException e) {

            }
            valor = temporal + 1;   // 2. sumar y 3. escribir
        }

        public int getValor() {
            return valor;
        }
    }

    public static void main(String[] args) throws InterruptedException {
        final int NUM_HILOS = 20;
        final int INCREMENTOS_POR_HILO = 1_000;

        Contador contador = new Contador();
        List<Thread> hilos = new ArrayList<>();

        for (int i = 0; i < NUM_HILOS; i++) {
            Thread hilo = new Thread(() -> {
                for (int j = 0; j < INCREMENTOS_POR_HILO; j++) {
                    contador.incrementar();
                }
            });
            hilos.add(hilo);
            hilo.start();
        }

        for (Thread hilo : hilos) {
            hilo.join();
        }

        int valorEsperado = NUM_HILOS * INCREMENTOS_POR_HILO;
        int valorReal = contador.getValor();

        System.out.println("=== Contador SIN synchronized (race condition) ===");
        System.out.println("Hilos: " + NUM_HILOS);
        System.out.println("Incrementos por hilo: " + INCREMENTOS_POR_HILO);
        System.out.println("Valor esperado: " + valorEsperado);
        System.out.println("Valor obtenido: " + valorReal);
        System.out.println("Incrementos perdidos: " + (valorEsperado - valorReal));
        System.out.println("Resultado correcto: " + (valorEsperado == valorReal));
        System.out.println();
        System.out.println("Nota: ejecuta este programa varias veces y compara los");
        System.out.println("resultados. El valor obtenido cambia entre ejecuciones,");
        System.out.println("lo cual es evidencia de la race condition (no es un bug");
        System.out.println("de logica fijo, depende del entrelazado de los hilos).");
    }
}