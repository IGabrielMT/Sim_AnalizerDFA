package co.edu.uptc.automata.model.automata;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Automata {

    private List<String> estados;
    private List<String> alfabeto;
    private String estadoInicial;
    private List<String> estadosAceptacion;
    private Map<String, Map<String, String>> transiciones;

    public Automata() {
        this.estados = new ArrayList<>();
        this.alfabeto = new ArrayList<>();
        this.estadoInicial = null;
        this.estadosAceptacion = new ArrayList<>();
        this.transiciones = new HashMap<>();
    }

    public Automata(List<String> estados, List<String> alfabeto, String estadoInicial,
                    List<String> estadosAceptacion, Map<String, Map<String, String>> transiciones) {
        this.estados = estados;
        this.alfabeto = alfabeto;
        this.estadoInicial = estadoInicial;
        this.estadosAceptacion = estadosAceptacion;
        this.transiciones = transiciones;
    }

    public List<String> getEstados() {
        return estados;
    }

    public void setEstados(List<String> estados) {
        this.estados = estados;
    }

    public List<String> getAlfabeto() {
        return alfabeto;
    }

    public void setAlfabeto(List<String> alfabeto) {
        this.alfabeto = alfabeto;
    }

    public String getEstadoInicial() {
        return estadoInicial;
    }

    public void setEstadoInicial(String estadoInicial) {
        this.estadoInicial = estadoInicial;
    }

    public List<String> getEstadosAceptacion() {
        return estadosAceptacion;
    }

    public void setEstadosAceptacion(List<String> estadosAceptacion) {
        this.estadosAceptacion = estadosAceptacion;
    }

    public Map<String, Map<String, String>> getTransiciones() {
        return transiciones;
    }

    public void setTransiciones(Map<String, Map<String, String>> transiciones) {
        this.transiciones = transiciones;
    }

    public void agregarTransicion(String estadoActual, String simbolo, String estadoDestino) {
        if (this.transiciones == null) {
            this.transiciones = new HashMap<>();
        }

        this.transiciones
                .computeIfAbsent(estadoActual, key -> new HashMap<>())
                .put(simbolo, estadoDestino);
    }
}