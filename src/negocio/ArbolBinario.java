package negocio;

import java.util.Objects;

import modelo.Nodo;

/**
 * Árbol binario genérico implementado con nodos enlazados.
 * Las operaciones de conteo y altura se resuelven con recursividad.
 *
 * @param <T> tipo de dato almacenado en el árbol
 */
public class ArbolBinario<T> {

    private Nodo<T> raiz;

    /** Crea un árbol vacío. */
    public ArbolBinario() {
        this.raiz = null;
    }

    public Nodo<T> getRaiz() {
        return raiz;
    }

    // ------------------------------------------------------------
    // Construcción
    // ------------------------------------------------------------

    /**
     * Crea el nodo raíz del árbol.
     *
     * @param dato información de la raíz
     * @throws IllegalStateException si el árbol ya tiene raíz
     */
    public void crearRaiz(T dato) {
        if (raiz != null) {
            throw new IllegalStateException("El árbol ya tiene una raíz.");
        }
        raiz = new Nodo<>(dato);
    }

    /**
     * Agrega un hijo izquierdo al nodo cuyo dato es {@code padre}.
     *
     * @throws IllegalArgumentException si el padre no existe en el árbol
     * @throws IllegalStateException    si la posición izquierda ya está ocupada
     */
    public void agregarIzquierdo(T padre, T dato) {
        Nodo<T> nodoPadre = obtenerPadre(padre);
        if (nodoPadre.getIzquierdo() != null) {
            throw new IllegalStateException("La posición izquierda de '" + padre
                    + "' ya está ocupada por '" + nodoPadre.getIzquierdo().getDato() + "'.");
        }
        nodoPadre.setIzquierdo(new Nodo<>(dato));
    }

    /**
     * Agrega un hijo derecho al nodo cuyo dato es {@code padre}.
     *
     * @throws IllegalArgumentException si el padre no existe en el árbol
     * @throws IllegalStateException    si la posición derecha ya está ocupada
     */
    public void agregarDerecho(T padre, T dato) {
        Nodo<T> nodoPadre = obtenerPadre(padre);
        if (nodoPadre.getDerecho() != null) {
            throw new IllegalStateException("La posición derecha de '" + padre
                    + "' ya está ocupada por '" + nodoPadre.getDerecho().getDato() + "'.");
        }
        nodoPadre.setDerecho(new Nodo<>(dato));
    }

    /** Busca el nodo padre y valida que exista. */
    private Nodo<T> obtenerPadre(T padre) {
        if (esVacio()) {
            throw new IllegalStateException("El árbol está vacío. Cree primero la raíz.");
        }
        Nodo<T> nodoPadre = buscar(padre);
        if (nodoPadre == null) {
            throw new IllegalArgumentException("El nodo padre '" + padre + "' no existe en el árbol.");
        }
        return nodoPadre;
    }

    // ------------------------------------------------------------
    // Consultas
    // ------------------------------------------------------------

    /**
     * Verifica si el árbol está vacío.
     *
     * @return true si no tiene raíz
     */
    public boolean esVacio() {
        return raiz == null;
    }

    /**
     * Busca un nodo por su dato (recorrido en preorden).
     *
     * @return el nodo encontrado o null si no existe
     */
    public Nodo<T> buscar(T dato) {
        return buscar(raiz, dato);
    }

    private Nodo<T> buscar(Nodo<T> actual, T dato) {
        if (actual == null) {
            return null;
        }
        if (Objects.equals(actual.getDato(), dato)) {
            return actual;
        }
        Nodo<T> encontrado = buscar(actual.getIzquierdo(), dato);
        if (encontrado != null) {
            return encontrado;
        }
        return buscar(actual.getDerecho(), dato);
    }

    // ------------------------------------------------------------
    // Operaciones recursivas
    // ------------------------------------------------------------

    /**
     * Cuenta el total de nodos del árbol.
     *
     * @return número de nodos (0 si está vacío)
     */
    public int contarNodos() {
        return contarNodos(raiz);
    }

    private int contarNodos(Nodo<T> nodo) {
        if (nodo == null) {
            return 0;
        }
        return 1 + contarNodos(nodo.getIzquierdo()) + contarNodos(nodo.getDerecho());
    }

    /**
     * Cuenta las hojas del árbol (nodos sin hijos).
     *
     * @return número de hojas (0 si está vacío)
     */
    public int contarHojas() {
        return contarHojas(raiz);
    }

    private int contarHojas(Nodo<T> nodo) {
        if (nodo == null) {
            return 0;
        }
        if (nodo.esHoja()) {
            return 1;
        }
        return contarHojas(nodo.getIzquierdo()) + contarHojas(nodo.getDerecho());
    }

    /**
     * Calcula la altura del árbol medida en aristas.
     * Árbol vacío = -1; árbol con un solo nodo = 0.
     *
     * @return altura del árbol
     */
    public int altura() {
        return altura(raiz);
    }

    private int altura(Nodo<T> nodo) {
        if (nodo == null) {
            return -1;
        }
        return 1 + Math.max(altura(nodo.getIzquierdo()), altura(nodo.getDerecho()));
    }

    // ------------------------------------------------------------
    // Visualización
    // ------------------------------------------------------------

    /**
     * Muestra la estructura jerárquica del árbol en consola.
     * (I) indica hijo izquierdo y (D) hijo derecho.
     */
    public void mostrarArbol() {
        if (esVacio()) {
            System.out.println("(árbol vacío)");
            return;
        }
        System.out.println(raiz.getDato());
        mostrar(raiz, "");
    }

    private void mostrar(Nodo<T> nodo, String prefijo) {
        Nodo<T> izq = nodo.getIzquierdo();
        Nodo<T> der = nodo.getDerecho();

        if (izq != null) {
            boolean hayDerecho = der != null;
            System.out.println(prefijo + (hayDerecho ? "+-- " : "\\-- ") + "(I) " + izq.getDato());
            mostrar(izq, prefijo + (hayDerecho ? "|   " : "    "));
        }
        if (der != null) {
            System.out.println(prefijo + "\\-- (D) " + der.getDato());
            mostrar(der, prefijo + "    ");
        }
    }
}
