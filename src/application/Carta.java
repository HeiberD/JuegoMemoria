package application;

public class Carta {
 // Atributo que almacena la ruta o el identificador de la imagen asociada a la carta.
    private String imagen;
 // Atributo booleano que indica si la carta ya ha sido emparejada con otra (true) o no (false).
    private boolean emparejada;


    public Carta(String imagen) {
        this.imagen = imagen; // Asigna la imagen recibida al atributo.
        this.emparejada = false; // Inicialmente, la carta no está emparejada.
    }

    public String getImagen() {
        return imagen;
    }

    public void setImagen(String imagen) {
        this.imagen = imagen;
    }

    public boolean isEmparejada() {
        return emparejada;
    }

  //Método que permite establecer el estado de la carta como emparejada o no.
    public void setEmparejada(boolean emparejada) {
        this.emparejada = emparejada;
    }
}
