package mx.unam.fes;


public class ArbolExpresion {


    private NodoArbol<Character> raiz;

    public NodoArbol<Character> getRaiz() {
        return raiz;
    }


    private boolean esOperador(char c) {
        return c == '+' || c == '-' || c == '*' || c == '/';
    }


    public void construir(String prefija) {
        prefija = prefija.replace(" ", "");

        if (prefija.isEmpty()) {
            throw new IllegalArgumentException("La expresión está vacía");
        }


        Pila<NodoArbol<Character>> pila = new Pila<NodoArbol<Character>>();


        NodoArbol<Character> nuevoNodo = new NodoArbol<Character>(prefija.charAt(0));
        raiz = nuevoNodo;


        boolean siguienteEsIzquierdo = true;


        for (int i = 1; i < prefija.length(); i++) {
            char simbolo = prefija.charAt(i);


            NodoArbol<Character> ultimoNodo = nuevoNodo;


            nuevoNodo = new NodoArbol<Character>(simbolo);

            if (siguienteEsIzquierdo) {

                ultimoNodo.izquierdo = nuevoNodo;
                pila.push(ultimoNodo);
            } else {

                if (pila.esVacia()) {
                    throw new IllegalArgumentException(
                            "Expresión prefija inválida: sobran símbolos");
                }
                NodoArbol<Character> padre = pila.pop();
                padre.derecho = nuevoNodo;
            }

            siguienteEsIzquierdo = esOperador(simbolo);
        }
    }

    public double evaluar() {
        return evaluar(raiz);
    }

    private double evaluar(NodoArbol<Character> nodo) {
        if (nodo == null) {
            throw new IllegalArgumentException("Expresión incompleta");
        }

        char simbolo = nodo.dato;


        if (nodo.izquierdo == null && nodo.derecho == null) {
            if (!Character.isDigit(simbolo)) {
                throw new IllegalArgumentException(
                        "No se puede evaluar: '" + simbolo + "' no es un dígito");
            }
            return Character.getNumericValue(simbolo);
        }


        double izq = evaluar(nodo.izquierdo);
        double der = evaluar(nodo.derecho);


        switch (simbolo) {
            case '+':
                return izq + der;
            case '-':
                return izq - der;
            case '*':
                return izq * der;
            case '/':
                return izq / der;
            default:
                throw new IllegalArgumentException("Operador desconocido: " + simbolo);
        }
    }


    public void imprimirArbol() {
        imprimirArbol(raiz, 0);
    }

    private void imprimirArbol(NodoArbol<Character> nodo, int nivel) {
        if (nodo != null) {
            imprimirArbol(nodo.derecho, nivel + 1);
            System.out.println("    ".repeat(nivel) + nodo.dato);
            imprimirArbol(nodo.izquierdo, nivel + 1);
        }
    }
}