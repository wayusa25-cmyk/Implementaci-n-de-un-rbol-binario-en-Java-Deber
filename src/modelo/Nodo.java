package modelo;

/**
 * Nodo genérico de un árbol binario.
 * Almacena un dato y referencias a sus hijos izquierdo y derecho.
 *
 * @param <T> tipo de dato almacenado en el nodo
 */
public class Nodo<T> {

    private T dato;
    private Nodo<T> izquierdo;
    private Nodo<T> derecho;

    /**
     * Crea un nodo sin hijos.
     *
     * @param dato información que almacenará el nodo
     */
    public Nodo(T dato) {
        this.dato = dato;
        this.izquierdo = null;
        this.derecho = null;
    }

    /**
     * Determina si el nodo es una hoja (no tiene hijos).
     *
     * @return true si no tiene hijo izquierdo ni derecho
     */
    public boolean esHoja() {
        return izquierdo == null && derecho == null;
    }

    /**
     * Devuelve el grado del nodo, es decir, su número de hijos (0, 1 o 2).
     *
     * @return cantidad de hijos del nodo
     */
    public int grado() {
        int grado = 0;
        if (izquierdo != null) {
            grado++;
        }
        if (derecho != null) {
            grado++;
        }
        return grado;
    }

    // ---------------- Getters y setters ----------------

    public T getDato() {
        return dato;
    }

    public void setDato(T dato) {
        this.dato = dato;
    }

    public Nodo<T> getIzquierdo() {
        return izquierdo;
    }

    public void setIzquierdo(Nodo<T> izquierdo) {
        this.izquierdo = izquierdo;
    }

    public Nodo<T> getDerecho() {
        return derecho;
    }

    public void setDerecho(Nodo<T> derecho) {
        this.derecho = derecho;
    }

    @Override
    public String toString() {
        return String.valueOf(dato);
    }
}
