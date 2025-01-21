package application;

public class Tablero {
    private Carta[] cartas;

    public Tablero(String[] imagenes) {
        cartas = new Carta[imagenes.length];
        for (int i = 0; i < imagenes.length; i++) {
            cartas[i] = new Carta(imagenes[i]);
        }
    }

    public Carta getCarta(int indice) {
        return cartas[indice];
    }

    public int getNumeroDeCartas() {
        return cartas.length;
    }

    public Carta[] getCartas() {
        return cartas;
    }
}
