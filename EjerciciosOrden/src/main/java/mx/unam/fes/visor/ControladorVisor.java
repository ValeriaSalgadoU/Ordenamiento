package mx.unam.fes.visor;

import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Slider;
import javafx.scene.layout.StackPane;

public class ControladorVisor {

    @FXML private StackPane areaCentral;
    @FXML private Button btnNuevoArreglo;
    @FXML private Button btnMetodoBurbuja;
    @FXML private Button btnMetodoSeleccion;
    @FXML private Button btnMetodoInsercion;
    @FXML private Button btnMetodoMezcla;
    @FXML private Button btnMetodoQuickSort;
    @FXML private Slider controlVelocidad;
    @FXML private Label etiquetaEstado;

    private PanelGrafico panelGrafico;
    private Button[] listaBotones;
    private boolean enEjecucion = false;

    @FXML
    public void initialize() {
        panelGrafico = new PanelGrafico(730, 520, 24);
        areaCentral.getChildren().add(panelGrafico);

        listaBotones = new Button[]{
                btnMetodoBurbuja,
                btnMetodoSeleccion,
                btnMetodoInsercion,
                btnMetodoMezcla,
                btnMetodoQuickSort
        };

        controlVelocidad.valueProperty().addListener((obs, anterior, nuevo) -> {
            panelGrafico.ajustarVelocidad(nuevo.intValue());
        });
    }

    @FXML
    private void accionGenerar() {
        if (!enEjecucion) {
            panelGrafico.generarDatosAleatorios(24);
            etiquetaEstado.setText("Estado: Datos regenerados");
        }
    }

    @FXML private void accionBurbuja() { lanzarAlgoritmo("Burbuja"); }
    @FXML private void accionSeleccion() { lanzarAlgoritmo("Selección"); }
    @FXML private void accionInsercion() { lanzarAlgoritmo("Inserción"); }
    @FXML private void accionMezcla() { lanzarAlgoritmo("Mezcla"); }
    @FXML private void accionQuickSort() { lanzarAlgoritmo("QuickSort"); }

    private void lanzarAlgoritmo(String nombre) {
        if (enEjecucion) return;
        enEjecucion = true;
        bloquearControles(true);
        etiquetaEstado.setText("Ejecutando: " + nombre + "...");

        Thread hilo = new Thread(() -> {
            int[] arreglo = panelGrafico.obtenerDatos();
            switch (nombre) {
                case "Burbuja" -> MetodosOrdenar.ordenarBurbuja(arreglo, panelGrafico);
                case "Selección" -> MetodosOrdenar.ordenarSeleccion(arreglo, panelGrafico);
                case "Inserción" -> MetodosOrdenar.ordenarInsercion(arreglo, panelGrafico);
                case "Mezcla" -> MetodosOrdenar.ordenarMezcla(arreglo, panelGrafico);
                case "QuickSort" -> MetodosOrdenar.ordenarQuickSort(arreglo, panelGrafico);
            }
            Platform.runLater(() -> {
                enEjecucion = false;
                bloquearControles(false);
                etiquetaEstado.setText("Finalizado: " + nombre);
            });
        });

        hilo.setDaemon(true);
        hilo.start();
    }

    private void bloquearControles(boolean bloquear) {
        btnNuevoArreglo.setDisable(bloquear);
        for (Button boton : listaBotones) {
            boton.setDisable(bloquear);
        }
    }
}