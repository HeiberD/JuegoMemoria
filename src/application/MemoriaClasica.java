package application;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class MemoriaClasica extends Application {
    private Tablero tablero; // Instancia del tablero
    private Button primerBotonSeleccionado; // Primer botón seleccionado por el jugador
    private Carta primeraCartaSeleccionada; // Primera carta seleccionada
    private int gridSize = 4; // Tamaño del tablero (inicialmente 4x4)

    @Override
    public void start(Stage primaryStage) {
        // Botones para elegir nivel de dificultad
        Button btnFacil = new Button("Fácil");
        Button btnMedio = new Button("Medio");
        Button btnDificil = new Button("Difícil");

        btnFacil.setOnAction(e -> { gridSize = 4; iniciarJuego(primaryStage); });
        btnMedio.setOnAction(e -> { gridSize = 6; iniciarJuego(primaryStage); });
        btnDificil.setOnAction(e -> { gridSize = 8; iniciarJuego(primaryStage); });

        // Contenedor para los botones de selección de dificultad
        VBox root = new VBox(10, btnFacil, btnMedio, btnDificil);
        Scene scene = new Scene(root, 300, 200);
        primaryStage.setTitle("Juego de Memoria");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    // Método para iniciar el juego con el nivel de dificultad seleccionado
    private void iniciarJuego(Stage primaryStage) {
        // Generar imágenes para el tablero
        String[] imagenes = generarImagenes(gridSize);
        tablero = new Tablero(imagenes);

        // Crear el contenedor principal (un GridPane)
        GridPane gridPane = new GridPane();

        // Crear los botones para las cartas
        for (int i = 0; i < tablero.getNumeroDeCartas(); i++) {
            Button boton = new Button("Carta");
            boton.setPrefSize(100, 100);
            int indice = i; // Necesario para usar en la expresión lambda

            // Evento de clic para cada botón
            boton.setOnAction(e -> manejarClicCarta(boton, indice));
            gridPane.add(boton, i % gridSize, i / gridSize); // Añadir el botón al grid
        }

        // Crear una escena con el GridPane
        Scene scene = new Scene(gridPane, 400, 400);
        primaryStage.setTitle("Juego de Memoria Clásico");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    // Método para generar las imágenes del tablero según el tamaño
    private String[] generarImagenes(int gridSize) {
        // Definir un conjunto de imágenes para las cartas
        String[] imagenes = {"🐶", "🐱", "🐰", "🦊", "🐸", "🐢", "🦉", "🐴"};
        
        // Calcular la cantidad de pares de cartas
        int numPares = gridSize * gridSize / 2;
        
        // Crear un arreglo para las cartas
        String[] cartas = new String[numPares * 2]; // Dos cartas por par

        // Copiar las imágenes y duplicarlas para formar pares
        for (int i = 0; i < numPares; i++) {
            String imagen = imagenes[i % imagenes.length]; 
            cartas[i] = imagen;
            cartas[numPares + i] = imagen; // Crear un par
        }
        
        // Barajar las cartas para que no estén en el mismo orden
        shuffleArray(cartas);
        
        return cartas;
    }

    private void shuffleArray(String[] array) {
        // Barajar las cartas de manera aleatoria
        for (int i = array.length - 1; i > 0; i--) {
            int j = (int) (Math.random() * (i + 1));
            String temp = array[i];
            array[i] = array[j];
            array[j] = temp;
        }
    }

    // Método para manejar el clic en una carta
    private void manejarClicCarta(Button boton, int indice) {
        Carta cartaSeleccionada = tablero.getCarta(indice);

        // Mostrar la imagen de la carta 
        boton.setText(cartaSeleccionada.getImagen());

        // Si no hay una carta previamente seleccionada
        if (primeraCartaSeleccionada == null) {
            primeraCartaSeleccionada = cartaSeleccionada;
            primerBotonSeleccionado = boton;
            boton.setDisable(true); // Deshabilitar el botón para que no se pueda volver a seleccionar
        } else {
            // Si ya hay una carta seleccionada, verificar si es un par
            if (primeraCartaSeleccionada.getImagen().equals(cartaSeleccionada.getImagen())) {
                // Las cartas coinciden
                primeraCartaSeleccionada.setEmparejada(true);
                cartaSeleccionada.setEmparejada(true);
                boton.setDisable(true); // Deshabilitar el botón actual
            } else {
                // Las cartas no coinciden: ocultar las imágenes después de un breve retraso
                boton.setText("Carta");
                primerBotonSeleccionado.setText("Carta");
                primerBotonSeleccionado.setDisable(false); // Volver a habilitar el primer botón
            }
            // Restablecer la selección
            primeraCartaSeleccionada = null;
            primerBotonSeleccionado = null;
        }
    }

    public static void main(String[] args) {
        launch(args); // Iniciar la aplicación JavaFX
    }
}

