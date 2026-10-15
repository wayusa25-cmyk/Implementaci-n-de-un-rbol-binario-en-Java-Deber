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
                    arbol = crearArbolManual(sc);
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
                    arbol = construirArbolInicial();
                    System.out.println("Árbol predeterminado creado correctamente.");
                    break;
                case 9:
                    System.out.println("Fin del programa.");
                    break;
                default:
                    System.out.println("Opción no válida. Intente de nuevo.");
            }
        } while (opcion != 9);

        sc.close();
    }

    private static void mostrarMenu() {
        System.out.println();
        System.out.println("========== ÁRBOL BINARIO - DEPARTAMENTOS ==========");
        System.out.println("1. Crear el árbol (ingresar nodos manualmente)");
        System.out.println("2. Visualizar el árbol");
        System.out.println("3. Consultar el número de nodos");
        System.out.println("4. Consultar el número de hojas");
        System.out.println("5. Consultar la altura");
        System.out.println("6. Consultar el grado de un nodo");
        System.out.println("7. Verificar si un nodo es hoja");
        System.out.println("8. Crear árbol predeterminado");
        System.out.println("9. Salir");
        System.out.println("===================================================");
    }

    /**
     * Crea un árbol nuevo pidiendo al usuario cada nodo: primero la raíz y luego,
     * por cada nodo, el padre, el lado (izquierda/derecha) y el nombre del hijo.
     */
    private static ArbolBinario<String> crearArbolManual(Scanner sc) {
        ArbolBinario<String> arbol = new ArbolBinario<>();

        System.out.println("--- Creación manual del árbol ---");
        String raiz = leerTextoNoVacio(sc, "Nombre del nodo raíz: ");
        arbol.crearRaiz(raiz);
        System.out.println("Raíz '" + raiz + "' creada.");

        boolean continuar = true;
        while (continuar) {
            System.out.println();
            String padre = leerTextoNoVacio(sc, "Nombre del nodo padre: ");

            if (arbol.buscar(padre) == null) {
                System.out.println("El nodo '" + padre + "' no existe en el árbol.");
            } else {
                String lado = leerLado(sc);
                String hijo = leerTextoNoVacio(sc, "Nombre del nuevo nodo: ");
                try {
                    if (lado.equals("I")) {
                        arbol.agregarIzquierdo(padre, hijo);
                    } else {
                        arbol.agregarDerecho(padre, hijo);
                    }
                    System.out.println("Nodo '" + hijo + "' agregado como hijo "
                            + (lado.equals("I") ? "izquierdo" : "derecho") + " de '" + padre + "'.");
                } catch (IllegalStateException | IllegalArgumentException e) {
                    System.out.println("No se pudo agregar el nodo: " + e.getMessage());
                }
            }

            System.out.println();
            arbol.mostrarArbol();
            System.out.print("\n¿Desea agregar otro nodo? (S/N): ");
            continuar = sc.nextLine().trim().equalsIgnoreCase("S");
        }

        System.out.println("Árbol creado con " + arbol.contarNodos() + " nodo(s).");
        return arbol;
    }

    /** Pide el lado hasta recibir I (izquierdo) o D (derecho). */
    private static String leerLado(Scanner sc) {
        while (true) {
            System.out.print("¿En qué lado? (I = izquierdo, D = derecho): ");
            String lado = sc.nextLine().trim().toUpperCase();
            if (lado.equals("I") || lado.equals("D")) {
                return lado;
            }
            System.out.println("Opción no válida. Escriba I o D.");
        }
    }

    /** Pide un texto hasta que no esté vacío. */
    private static String leerTextoNoVacio(Scanner sc, String mensaje) {
        String texto = leerTexto(sc, mensaje);
        while (texto.isEmpty()) {
            System.out.println("El nombre no puede estar vacío.");
            texto = leerTexto(sc, mensaje);
        }
        return texto;
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
