package mx.unam.fes.resolucion;

import mx.unam.fes.grafos.Dijkstra;

public class EjercicioDijkstra {
    public static void main(String[] args) {
        String[] vertices = {"a", "b", "c", "d", "e", "f", "g", "z"};
        int n = vertices.length;
        int[][] peso = new int[n][n];

        Dijkstra.unir(peso, 0, 1, 2);
        Dijkstra.unir(peso, 0, 5, 1);
        Dijkstra.unir(peso, 1, 2, 2);
        Dijkstra.unir(peso, 1, 3, 2);
        Dijkstra.unir(peso, 1, 4, 4);
        Dijkstra.unir(peso, 2, 4, 3);
        Dijkstra.unir(peso, 2, 7, 1);
        Dijkstra.unir(peso, 3, 4, 4);
        Dijkstra.unir(peso, 3, 5, 3);
        Dijkstra.unir(peso, 4, 6, 7);
        Dijkstra.unir(peso, 5, 6, 5);
        Dijkstra.unir(peso, 6, 7, 6);

        int origen = 0;  // a
        int destino = 7; // z

        Dijkstra.calcularRuta(vertices, peso, origen, destino);
    }
}