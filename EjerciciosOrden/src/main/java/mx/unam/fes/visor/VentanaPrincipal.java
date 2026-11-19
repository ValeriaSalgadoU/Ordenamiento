package mx.unam.fes.visor;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class VentanaPrincipal extends Application {

    @Override
    public void start(Stage escenario) throws IOException {
        FXMLLoader cargador = new FXMLLoader(
                VentanaPrincipal.class.getResource("/interfaz-visor.fxml")
        );
        Scene escena = new Scene(cargador.load(), 940, 520);
        escenario.setTitle("Simulador Gráfico de Ordenamiento - Estructura de Datos");
        escenario.setScene(escena);
        escenario.setResizable(false);
        escenario.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}