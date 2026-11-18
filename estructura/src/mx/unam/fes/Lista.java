package mx.unam.fes;

public class Lista<E> {
    private Nodo<E> cabeza, cola;
    private int longitud = 0;

    public Lista() {
        cabeza = cola = null;
    }

    public boolean esVacia() {
        return cabeza == null;
    }

    public void agregarCabeza(E dato) {
        cabeza = new Nodo<E>(dato, cabeza);
        if (cola == null) {
            cola = cabeza;
        }
        longitud++;
    }

    public void agregarCola(E dato) {
        if (!esVacia()) {
            cola.setSiguiente(new Nodo<E>(dato));
            cola = cola.getSiguiente();
        } else {
            cola = cabeza = new Nodo<E>(dato);
        }
        longitud++;
    }

    public Object eliminarDeCabeza() {
        Object dato = null;
        if (!esVacia()) {
            dato = cabeza.getDato();
            if (cabeza == cola) {
                cabeza = cola = null;
            } else {
                cabeza = cabeza.getSiguiente();
            }
            longitud--;
        }
        return dato;
    }

    public int getLongitud() {
        return longitud;
    }

    public Object elimiarDeCola() {
        Object dato = null;
        if (!esVacia()) {
            dato = cola.getDato();
            if (cabeza == cola) {
                cabeza = cola = null;
            } else {
                Nodo<E> temp;
                for (temp = cabeza; temp.getSiguiente() != cola; temp =

                        temp.getSiguiente())
                    ;

                cola = temp;
                cola.setSiguiente(null);
            }
            longitud--;
        }
        return dato;
    }

    public E obtenerNodo(int indice) {
        Nodo<E> temp = cabeza;
        for (int contador = 0; contador < indice && temp != null; contador++, temp = temp.getSiguiente())
            ;
        if (temp != null) {
            return temp.getDato();
        } else {
            return null;
        }
    }

    public void imprimir() {

    }
}