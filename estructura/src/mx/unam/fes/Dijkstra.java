package mx.unam.fes;

import java.util.Arrays;

public class Dijkstra {

    static final int INFINITO = Integer.MAX_VALUE;

    static void unir(int[][] peso, int i, int j, int valor) {
        peso[i][j] = valor;
        peso[j][i] = valor;
    }

    public static void main(String[] args) {


        String[] vertices = {"a", "b", "c", "d", "e", "f", "g", "z"};
        int n = vertices.length;
        int[][] peso = new int[n][n];

        unir(peso, 0, 1, 2);
        unir(peso, 0, 5, 1);
        unir(peso, 1, 2, 2);
        unir(peso, 1, 3, 2);
        unir(peso, 1, 4, 4);
        unir(peso, 2, 4, 3);
        unir(peso, 2, 7, 1);
        unir(peso, 3, 4, 4);
        unir(peso, 3, 5, 3);
        unir(peso, 4, 6, 7);
        unir(peso, 5, 6, 5);
        unir(peso, 6, 7, 6);

        int origen = 0;
        int destino = 7;
        int[] distancia = new int[n];

        boolean[] visitado = new boolean[n];

        int[] previo = new int[n];

        Arrays.fill(distancia, INFINITO);
        Arrays.fill(previo, -1);
        distancia[origen] = 0;


        while (!visitado[destino]) {


            int v = -1;
            for (int i = 0; i < n; i++) {
                if (!visitado[i] && (v == -1 || distancia[i] < distancia[v])) {
                    v = i;
                }
            }


            if (distancia[v] == INFINITO) {
                break;
            }

            visitado[v] = true; // T = T - {v}
            System.out.println("Se elige " + vertices[v] + " (distancia " + distancia[v] + ")");


            for (int x = 0; x < n; x++) {
                if (!visitado[x] && peso[v][x] > 0) {


                    int nueva = distancia[v] + peso[v][x];

                    if (nueva < distancia[x]) {
                        distancia[x] = nueva;
                        previo[x] = v;
                        System.out.println("   L(" + vertices[x] + ") = " + nueva
                                + "  (pasando por " + vertices[v] + ")");
                    }
                }
            }
        }


        System.out.println("\nDistancia más corta de a a z: " + distancia[destino]);


        String camino = vertices[destino];
        int actual = destino;
        while (previo[actual] != -1) {
            actual = previo[actual];
            camino = vertices[actual] + " -> " + camino;
        }
        System.out.println("Camino: " + camino);
    }
}