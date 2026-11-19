package mx.unam.fes.resolucion;

import mx.unam.fes.dinamico.Pila;

public class EjercicioArbolPrefija {

    static class NodoExpr {
        char simbolo;
        NodoExpr izquierdo, derecho;

        public NodoExpr(char simbolo) {
            this.simbolo = simbolo;
        }
    }

    private static boolean esOperador(char c) {
        return c == '+' || c == '-' || c == '*' || c == '/';
    }

    public static NodoExpr construirArbolPrefijo(String prefija) {
        Pila<NodoExpr> pila = new Pila<>();
        char simbolo = prefija.charAt(0);
        NodoExpr raiz = new NodoExpr(simbolo);
        NodoExpr ultimoNodo = raiz;
        String sigMovimiento = "izquierdo";

        for (int i = 1; i < prefija.length(); i++) {
            simbolo = prefija.charAt(i);
            if (Character.isWhitespace(simbolo)) continue;

            NodoExpr nuevoNodo = new NodoExpr(simbolo);

            if (sigMovimiento.equals("izquierdo")) {
                ultimoNodo.izquierdo = nuevoNodo;
                pila.push(ultimoNodo);
            } else {
                ultimoNodo = pila.pop();
                ultimoNodo.derecho = nuevoNodo;
            }

            ultimoNodo = nuevoNodo;
            sigMovimiento = esOperador(simbolo) ? "izquierdo" : "derecha";
        }
        return raiz;
    }

    public static double evaluarArbol(NodoExpr nodo) {
        if (nodo == null) return 0;
        if (!esOperador(nodo.simbolo)) {
            return Character.getNumericValue(nodo.simbolo);
        }
        double izq = evaluarArbol(nodo.izquierdo);
        double der = evaluarArbol(nodo.derecho);

        return switch (nodo.simbolo) {
            case '+' -> izq + der;
            case '-' -> izq - der;
            case '*' -> izq * der;
            case '/' -> izq / der;
            default -> 0;
        };
    }

    public static void imprimirInorden(NodoExpr nodo) {
        if (nodo != null) {
            if (esOperador(nodo.simbolo)) System.out.print("(");
            imprimirInorden(nodo.izquierdo);
            System.out.print(nodo.simbolo);
            imprimirInorden(nodo.derecho);
            if (esOperador(nodo.simbolo)) System.out.print(")");
        }
    }

    public static void main(String[] args) {
        String expr = "+*342"; // Equivalente numérico de +*ACB del PDF
        NodoExpr raiz = construirArbolPrefijo(expr);

        System.out.println("Expresión prefija: " + expr);
        System.out.print("Expresión infija: ");
        imprimirInorden(raiz);
        System.out.println("\nResultado evaluado: " + evaluarArbol(raiz));
    }
}