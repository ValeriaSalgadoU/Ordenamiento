package mx.unam.fes;
public class PruebaArbol {

    public static void main(String[] args) {


        ArbolExpresion ejemploPdf = new ArbolExpresion();
        ejemploPdf.construir("+*ACB");
        System.out.println("Árbol de +*ACB:");
        ejemploPdf.imprimirArbol();

        String[] expresiones = {
                "+*243",
                "-+*2345",
                "/+84-62"
        };

        for (String expresion : expresiones) {
            ArbolExpresion arbol = new ArbolExpresion();
            arbol.construir(expresion);

            System.out.println("\nExpresión prefija: " + expresion);
            arbol.imprimirArbol();
            System.out.println("Resultado: " + arbol.evaluar());
        }
    }
}