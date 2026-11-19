package mx.unam.fes.visor;

import javafx.application.Platform;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;

import java.util.Random;

public class PanelGrafico extends Canvas {
    private int[] datos;
    private int posComparando1 = -1;
    private int posComparando2 = -1;
    private int tiempoEsperaMs = 40;

    public PanelGrafico(double ancho, double alto, int totalElementos) {
        super(ancho, alto);
        generarDatosAleatorios(totalElementos);
    }

    public void generarDatosAleatorios(int cantidad) {
        datos = new int[cantidad];
        Random generador = new Random();
        for (int i = 0; i < cantidad; i++) {
            datos[i] = generador.nextInt(340) + 25;
        }
        posComparando1 = -1;
        posComparando2 = -1;
        renderizar();
    }

    public int[] obtenerDatos() {
        return datos;
    }

    public void ajustarVelocidad(int milisegundos) {
        this.tiempoEsperaMs = milisegundos;
    }

    public void refrescarPaso(int idx1, int idx2) {
        this.posComparando1 = idx1;
        this.posComparando2 = idx2;
        Platform.runLater(this::renderizar);
        try {
            Thread.sleep(tiempoEsperaMs);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }

    public void terminarProceso() {
        this.posComparando1 = -1;
        this.posComparando2 = -1;
        Platform.runLater(this::renderizar);
    }

    public void renderizar() {
        if (datos == null) return;

        GraphicsContext pincel = getGraphicsContext2D();
        double w = getWidth();
        double h = getHeight();

        // Fondo claro estilo laboratorio
        pincel.setFill(Color.web("#f1f5f9"));
        pincel.fillRect(0, 0, w, h);

        // Línea base inferior
        pincel.setStroke(Color.web("#cbd5e1"));
        pincel.setLineWidth(2);
        pincel.strokeLine(15, h - 25, w - 15, h - 25);

        double anchoColumna = Math.max(6, (w - 40) / datos.length);
        double margen = 20;

        for (int i = 0; i < datos.length; i++) {
            if (i == posComparando1) {
                pincel.setFill(Color.web("#6366f1"));
            } else if (i == posComparando2) {
                pincel.setFill(Color.web("#10b981"));
            } else {
                pincel.setFill(Color.web("#334155"));
            }

            double x = margen + i * anchoColumna;
            double y = h - datos[i] - 25;
            pincel.fillRect(x, y, anchoColumna - 5, datos[i]);

            if (datos.length <= 28) {
                pincel.setFill(Color.web("#0f172a"));
                pincel.setFont(Font.font("System", FontWeight.BOLD, 10));
                pincel.fillText(String.valueOf(datos[i]), x + 1, y - 6);
            }
        }
    }
}
