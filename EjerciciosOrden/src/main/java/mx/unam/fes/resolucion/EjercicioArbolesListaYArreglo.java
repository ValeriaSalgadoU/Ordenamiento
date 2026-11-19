package mx.unam.fes.resolucion;

import mx.unam.fes.dinamico.Cola;

public class EjercicioArbolesListaYArreglo {

    // --- PARTE 1: Árbol Binario con Nodos Ligados (Diapositiva 3) ---
    static class NodoLigado {
        int dato;
        NodoLigado izquierdo, derecho;

        public NodoLigado(int dato) {
            this.dato = dato;
        }
    }

    public static void recorrerAmplitud(NodoLigado raiz) {
        if (raiz == null) return;
        Cola<NodoLigado> cola = new Cola<>();
        cola.agregacola(raiz);
        while (!cola.esVacia()) {
            NodoLigado actual = cola.eliminacola();
            System.out.print(actual.dato + " ");
            if (actual.izquierdo != null) cola.agregacola(actual.izquierdo);
            if (actual.derecho != null) cola.agregacola(actual.derecho);
        }
        System.out.println();
    }

    // --- PARTE 2: Árbol Binario con Arreglo (Tabla de la Diapositiva 3) ---
    static class NodoArreglo {
        int info;
        Integer izquierdo; // Índice del hijo izquierdo (null si no tiene)
        Integer derecho;   // Índice del hijo derecho (null si no tiene)

        public NodoArreglo(int info, Integer izquierdo, Integer derecho) {
            this.info = info;
            this.izquierdo = izquierdo;
            this.derecho = derecho;
        }
    }

    public static void imprimirInordenArreglo(NodoArreglo[] arbol, Integer indice) {
        if (indice == null) return;
        imprimirInordenArreglo(arbol, arbol[indice].izquierdo);
        System.out.print(arbol[indice].info + " ");
        imprimirInordenArreglo(arbol, arbol[indice].derecho);
    }

    public static void main(String[] args) {
        // 1. Construcción manual del árbol de la figura con nodos ligados:
        NodoLigado raiz = new NodoLigado(13);
        raiz.izquierdo = new NodoLigado(11);
        raiz.derecho = new NodoLigado(5);
        raiz.izquierdo.izquierdo = new NodoLigado(27);
        raiz.izquierdo.derecho = new NodoLigado(18);
        raiz.derecho.izquierdo = new NodoLigado(2);
        raiz.derecho.derecho = new NodoLigado(31);
        raiz.derecho.derecho.izquierdo = new NodoLigado(9);

        System.out.print("Árbol con Lista Ligada (recorrido por amplitud): ");
        recorrerAmplitud(raiz);

        // 2. Construcción con arreglo según la tabla exacta del PDF:
        NodoArreglo[] tablaArbol = new NodoArreglo[8];
        tablaArbol[0] = new NodoArreglo(13, 1, 2);
        tablaArbol[1] = new NodoArreglo(11, 3, 4);
        tablaArbol[2] = new NodoArreglo(5, 5, 6);
        tablaArbol[3] = new NodoArreglo(27, null, null);
        tablaArbol[4] = new NodoArreglo(18, null, null);
        tablaArbol[5] = new NodoArreglo(2, null, null);
        tablaArbol[6] = new NodoArreglo(31, 7, null);
        tablaArbol[7] = new NodoArreglo(9, null, null);

        System.out.print("Árbol con Arreglo (recorrido inorden): ");
        imprimirInordenArreglo(tablaArbol, 0);
        System.out.println();
    }
}