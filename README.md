# Monitores en Sistemas Operativos (Java)

Proyecto universitario para demostrar concurrencia, exclusión mutua y sincronización mediante **Monitores** en Java.

## Objetivo

Desarrollar un programa que permita demostrar:

- Race Condition
- Exclusión Mutua
- Monitores
- synchronized
- wait()
- notifyAll()
- Productor - Consumidor

---

# Estructura del proyecto

```
src/
│
├── ContadorSinMonitor.java
├── ContadorMonitor.java
├── BufferMonitor.java
├── Productor.java
├── Consumidor.java
├── Pruebas.java
└── Main.java
```

---

# Distribución

| Integrante | Archivo |
|------------|---------|
| Integrante 1 | ContadorMonitor.java |
| Integrante 2 | ContadorSinMonitor.java |
| Integrante 3 | BufferMonitor.java |
| Integrante 4 | Productor.java |
| Integrante 5 | Consumidor.java |
| Integrante 6 | Pruebas.java |
| Integrante 7 | Main.java + Integración |

---

# Flujo de trabajo

Cada integrante deberá:

1. Crear una rama propia.
2. Trabajar únicamente en su archivo principal.
3. Realizar pruebas locales.
4. Hacer commit.
5. Subir la rama.
6. Crear un Pull Request.
7. Esperar revisión antes del merge.

---

# Compilación

```bash
javac src/*.java
```

---

# Ejecución

```bash
java -cp src Main
```
