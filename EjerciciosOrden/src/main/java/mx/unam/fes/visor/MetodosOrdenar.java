package mx.unam.fes.visor;

public class MetodosOrdenar {

    // 1. BURBUJA
    public static void ordenarBurbuja(int[] vector, PanelGrafico panel) {
        int n = vector.length;
        for (int i = 0; i < n - 1; i++) {
            boolean huboCambio = false;
            for (int j = 0; j < n - 1 - i; j++) {
                panel.refrescarPaso(j, j + 1);
                if (vector[j] > vector[j + 1]) {
                    int aux = vector[j];
                    vector[j] = vector[j + 1];
                    vector[j + 1] = aux;
                    huboCambio = true;
                    panel.refrescarPaso(j, j + 1);
                }
            }
            if (!huboCambio) break;
        }
        panel.terminarProceso();
    }

    // 2. SELECCIÓN
    public static void ordenarSeleccion(int[] vector, PanelGrafico panel) {
        int n = vector.length;
        for (int i = 0; i < n - 1; i++) {
            int posMenor = i;
            for (int j = i + 1; j < n; j++) {
                panel.refrescarPaso(posMenor, j);
                if (vector[j] < vector[posMenor]) {
                    posMenor = j;
                }
            }
            int aux = vector[posMenor];
            vector[posMenor] = vector[i];
            vector[i] = aux;
            panel.refrescarPaso(i, posMenor);
        }
        panel.terminarProceso();
    }

    // 3. INSERCIÓN
    public static void ordenarInsercion(int[] vector, PanelGrafico panel) {
        int n = vector.length;
        for (int i = 1; i < n; i++) {
            int elementoActual = vector[i];
            int k = i - 1;
            panel.refrescarPaso(i, k);
            while (k >= 0 && vector[k] > elementoActual) {
                vector[k + 1] = vector[k];
                panel.refrescarPaso(k + 1, k);
                k--;
            }
            vector[k + 1] = elementoActual;
            panel.refrescarPaso(k + 1, i);
        }
        panel.terminarProceso();
    }

    // 4. MEZCLA (MERGE SORT)
    public static void ordenarMezcla(int[] vector, PanelGrafico panel) {
        dividirMezclar(vector, 0, vector.length - 1, panel);
        panel.terminarProceso();
    }

    private static void dividirMezclar(int[] vector, int inicio, int fin, PanelGrafico panel) {
        if (inicio < fin) {
            int mitad = inicio + (fin - inicio) / 2;
            dividirMezclar(vector, inicio, mitad, panel);
            dividirMezclar(vector, mitad + 1, fin, panel);
            fusionarSubarreglos(vector, inicio, mitad, fin, panel);
        }
    }

    private static void fusionarSubarreglos(int[] vector, int inicio, int mitad, int fin, PanelGrafico panel) {
        int tamIzq = mitad - inicio + 1;
        int tamDer = fin - mitad;

        int[] subIzq = new int[tamIzq];
        int[] subDer = new int[tamDer];

        for (int i = 0; i < tamIzq; i++) subIzq[i] = vector[inicio + i];
        for (int j = 0; j < tamDer; j++) subDer[j] = vector[mitad + 1 + j];

        int i = 0, j = 0, k = inicio;
        while (i < tamIzq && j < tamDer) {
            panel.refrescarPaso(k, mitad);
            if (subIzq[i] <= subDer[j]) {
                vector[k++] = subIzq[i++];
            } else {
                vector[k++] = subDer[j++];
            }
            panel.refrescarPaso(k - 1, -1);
        }
        while (i < tamIzq) {
            vector[k++] = subIzq[i++];
            panel.refrescarPaso(k - 1, -1);
        }
        while (j < tamDer) {
            vector[k++] = subDer[j++];
            panel.refrescarPaso(k - 1, -1);
        }
    }

    // 5. QUICKSORT
    public static void ordenarQuickSort(int[] vector, PanelGrafico panel) {
        quickSortRecursivo(vector, 0, vector.length - 1, panel);
        panel.terminarProceso();
    }

    private static void quickSortRecursivo(int[] vector, int izq, int der, PanelGrafico panel) {
        if (izq < der) {
            int indicePivote = particionar(vector, izq, der, panel);
            quickSortRecursivo(vector, izq, indicePivote - 1, panel);
            quickSortRecursivo(vector, indicePivote + 1, der, panel);
        }
    }

    private static int particionar(int[] vector, int izq, int der, PanelGrafico panel) {
        int pivote = vector[der];
        int i = izq - 1;
        for (int j = izq; j < der; j++) {
            panel.refrescarPaso(j, der);
            if (vector[j] <= pivote) {
                i++;
                int aux = vector[i];
                vector[i] = vector[j];
                vector[j] = aux;
                panel.refrescarPaso(i, j);
            }
        }
        int aux = vector[i + 1];
        vector[i + 1] = vector[der];
        vector[der] = aux;
        panel.refrescarPaso(i + 1, der);
        return i + 1;
    }
}
