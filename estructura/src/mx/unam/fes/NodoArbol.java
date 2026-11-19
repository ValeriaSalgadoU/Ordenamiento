package mx.unam.fes;

public class NodoArbol<E> {

    E dato;

    NodoArbol<E> izquierdo;


    NodoArbol<E> derecho;

    public NodoArbol(E dato) {
        this(dato, null, null);
    }

    public NodoArbol(E dato, NodoArbol<E> izquierdo, NodoArbol<E> derecho) {
        this.dato = dato;
        this.izquierdo = izquierdo;
        this.derecho = derecho;
    }
}