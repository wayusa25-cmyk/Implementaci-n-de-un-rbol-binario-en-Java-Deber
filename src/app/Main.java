package app;

import java.util.Scanner;
import modelo.Nodo;
import negocio.ArbolBinario;

/**
 * Programa principal: organización jerárquica de departamentos universitarios.
 *
 * Ejecución normal:      java -cp out app.Main
 * Casos de prueba:       java -cp out app.Main --pruebas
 */
public class Main {

    public static void main(String[] args) {
        if (args.length > 0 && args[0].equalsIgnoreCase("--pruebas")) {
            ejecutarPruebas();
            return;
        }
        ejecutarMenu();
    }

    // ------------------------------------------------------------
    // Menú interactivo
    // ------------------------------------------------------------

    private static void ejecutarMenu() {
        Scanner sc = new Scanner(System.in);
        ArbolBinario<String> arbol = new ArbolBinario<>();
        int opcion;

        do {
            mostrarMenu();
            opcion = leerEntero(sc, "Seleccione una opción: ");
            System.out.println();

            switch (opcion) {
                case 1:
                    arbol = construirArbolInicial();
                    System.out.println("Árbol inicial creado correctamente.");
                    break;
                case 2:
                    System.out.println("--- Estructura del árbol ---");
                    arbol.mostrarArbol();
                    break;
                case 3:
                    System.out.println("Número de nodos: " + arbol.contarNodos());
                    break;
                case 4:
                    System.out.println("Número de hojas: " + arbol.contarHojas());
                    break;
                case 5:
                    System.out.println("Altura del árbol (en aristas): " + arbol.altura());
                    break;
                case 6:
                    consultarGrado(sc, arbol);
                    break;
                case 7:
                    verificarHoja(sc, arbol);
                    break;
                case 8:
                    System.out.println("Fin del programa.");
                    break;
                default:
                    System.out.println("Opción no válida. Intente de nuevo.");
            }
        } while (opcion != 8);

        sc.close();
    }

    private static void mostrarMenu() {
        System.out.println();
        System.out.println("========== ÁRBOL BINARIO - DEPARTAMENTOS ==========");
        System.out.println("1. Crear el árbol inicial");
        System.out.println("2. Visualizar el árbol");
        System.out.println("3. Consultar el número de nodos");
        System.out.println("4. Consultar el número de hojas");
        System.out.println("5. Consultar la altura");
        System.out.println("6. Consultar el grado de un nodo");
        System.out.println("7. Verificar si un nodo es hoja");
        System.out.println("8. Salir");
        System.out.println("===================================================");
    }

    private static void consultarGrado(Scanner sc, ArbolBinario<String> arbol) {
        if (arbol.esVacio()) {
            System.out.println("El árbol está vacío. Cree primero el árbol inicial (opción 1).");
            return;
        }
        String nombre = leerTexto(sc, "Nombre del nodo: ");
        Nodo<String> nodo = arbol.buscar(nombre);
        if (nodo == null) {
            System.out.println("El nodo '" + nombre + "' no existe en el árbol.");
        } else {
            System.out.println("Grado de " + nombre + ": " + nodo.grado());
        }
    }

    private static void verificarHoja(Scanner sc, ArbolBinario<String> arbol) {
        if (arbol.esVacio()) {
            System.out.println("El árbol está vacío. Cree primero el árbol inicial (opción 1).");
            return;
        }
        String nombre = leerTexto(sc, "Nombre del nodo: ");
        Nodo<String> nodo = arbol.buscar(nombre);
        if (nodo == null) {
            System.out.println("El nodo '" + nombre + "' no existe en el árbol.");
        } else if (nodo.esHoja()) {
            System.out.println(nombre + " ES una hoja.");
        } else {
            System.out.println(nombre + " NO es una hoja.");
        }
    }

    // ------------------------------------------------------------
    // Utilidades de lectura (Scanner)
    // ------------------------------------------------------------

    private static int leerEntero(Scanner sc, String mensaje) {
        System.out.print(mensaje);
        String linea = sc.nextLine().trim();
        try {
            return Integer.parseInt(linea);
        } catch (NumberFormatException e) {
            return -1; // opción inválida, el menú mostrará el aviso
        }
    }

    private static String leerTexto(Scanner sc, String mensaje) {
        System.out.print(mensaje);
        return sc.nextLine().trim().toUpperCase();
    }

    // ------------------------------------------------------------
    // Construcción del árbol inicial
    // ------------------------------------------------------------

    /**
     * Construye el árbol del enunciado:
     *
     *              UNIVERSIDAD
     *              /         \
     *        FISEI             DIDE
     *        /   \             /   \
     *    SOFTWARE  TI     INVEST.  VINCULACIÓN
     *     /   \
     *  NIVEL1 NIVEL3
     */
    private static ArbolBinario<String> construirArbolInicial() {
        ArbolBinario<String> arbol = new ArbolBinario<>();
        arbol.crearRaiz("UNIVERSIDAD");

        arbol.agregarIzquierdo("UNIVERSIDAD", "FISEI");
        arbol.agregarDerecho("UNIVERSIDAD", "DIDE");

        arbol.agregarIzquierdo("FISEI", "SOFTWARE");
        arbol.agregarDerecho("FISEI", "TI");

        arbol.agregarIzquierdo("DIDE", "INVEST.");
        arbol.agregarDerecho("DIDE", "VINCULACIÓN");

        arbol.agregarIzquierdo("SOFTWARE", "NIVEL1");
        arbol.agregarDerecho("SOFTWARE", "NIVEL3");

        return arbol;
    }

    // ------------------------------------------------------------
    // Casos de prueba obligatorios
    // ------------------------------------------------------------

    private static void ejecutarPruebas() {
        System.out.println("===== CASOS DE PRUEBA =====");

        // 1. Árbol vacío
        System.out.println("\nCaso 1: Árbol vacío");
        ArbolBinario<String> vacio = new ArbolBinario<>();
        verificar("Nodos", 0, vacio.contarNodos());
        verificar("Altura", -1, vacio.altura());

        // 2. Árbol con un solo nodo
        System.out.println("\nCaso 2: Árbol con un solo nodo");
        ArbolBinario<String> unico = new ArbolBinario<>();
        unico.crearRaiz("UNIVERSIDAD");
        verificar("Nodos", 1, unico.contarNodos());
        verificar("Hojas", 1, unico.contarHojas());
        verificar("Altura", 0, unico.altura());

        // 3. Árbol inicial
        System.out.println("\nCaso 3: Árbol inicial");
        ArbolBinario<String> inicial = construirArbolInicial();
        inicial.mostrarArbol();
        verificar("Nodos", 9, inicial.contarNodos());
        verificar("Hojas", 5, inicial.contarHojas());
        verificar("Altura", 3, inicial.altura());

        // 4. Insertar un hijo en una posición ocupada
        System.out.println("\nCaso 4: Insertar en una posición ocupada");
        try {
            inicial.agregarIzquierdo("FISEI", "NUEVA DEPENDENCIA");
            System.out.println("  [FALLO] No se lanzó error al insertar en posición ocupada.");
        } catch (IllegalStateException e) {
            System.out.println("  [OK] Inserción rechazada: " + e.getMessage());
        }
        verificar("Nodos (sin cambios)", 9, inicial.contarNodos());

        // 5. Grado de FISEI y SOFTWARE
        System.out.println("\nCaso 5: Grado de FISEI y SOFTWARE");
        verificar("Grado de FISEI", 2, inicial.buscar("FISEI").grado());
        verificar("Grado de SOFTWARE", 2, inicial.buscar("SOFTWARE").grado());

        System.out.println("\n===== FIN DE LAS PRUEBAS =====");
    }

    private static void verificar(String descripcion, int esperado, int obtenido) {
        String estado = (esperado == obtenido) ? "[OK]   " : "[FALLO]";
        System.out.println("  " + estado + " " + descripcion
                + " -> esperado: " + esperado + ", obtenido: " + obtenido);
    }
}
