package co.edu.uptc.automata.model;

import co.edu.uptc.automata.model.automata.Automata;

import java.util.Map;

public class EvaluadorDFA {

    public boolean evaluarCadena(Automata automata, String cadena) {
        if (automata == null || cadena == null) {
            return false;
        }

        if (automata.getAlfabeto() == null
                || automata.getEstadoInicial() == null
                || automata.getEstadosAceptacion() == null
                || automata.getTransiciones() == null) {
            return false;
        }

        String estadoActual = automata.getEstadoInicial();

        for (char caracter : cadena.toCharArray()) {
            String simbolo = String.valueOf(caracter);

            if (!automata.getAlfabeto().contains(simbolo)) {
                return false;
            }

            Map<String, String> transicionesEstado = automata.getTransiciones().get(estadoActual);
            if (transicionesEstado == null || !transicionesEstado.containsKey(simbolo)) {
                return false;
            }

            estadoActual = transicionesEstado.get(simbolo);
        }

        return automata.getEstadosAceptacion().contains(estadoActual);
    }

}
