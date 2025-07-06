package Registro_Alex;

import java.util.ArrayList;
import java.util.List;

class NodoZona {
    String nombreZona;
    List<Estacionamiento> espacios; // Lista de espacios disponibles
    NodoZona izquierda;  // Comedor
    NodoZona derecha;    // Odontología

    public NodoZona(String nombreZona) {
        this.nombreZona = nombreZona;
        this.espacios = new ArrayList<>();
    }
}