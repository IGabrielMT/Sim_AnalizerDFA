package co.edu.uptc.model;

import co.edu.uptc.model.automata.Automata;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class AutomataEvaluator {

    public EvaluationResult evaluarCadena(Automata automata, String cadena) {
        List<String> traza = new ArrayList<>();

        if (automata == null || cadena == null) {
            return new EvaluationResult(false, traza);
        }

        // Validar que todos los símbolos pertenecen al alfabeto
        for (char c : cadena.toCharArray()) {
            String simbolo = String.valueOf(c);
            if (!automata.getAlfabeto().contains(simbolo)) {
                traza.add("El símbolo '" + simbolo + "' no pertenece al alfabeto " + automata.getAlfabeto());
                return new EvaluationResult(false, traza);
            }
        }

        String estadoActual = automata.getEstadoInicial();

        for (char c : cadena.toCharArray()) {
            String simbolo = String.valueOf(c);

            Map<String, List<String>> mapaSimbolos = automata.getTransiciones().get(estadoActual);
            if (mapaSimbolos == null || !mapaSimbolos.containsKey(simbolo)) {
                return new EvaluationResult(false, traza);
            }

            List<String> destinos = mapaSimbolos.get(simbolo);
            if (destinos == null || destinos.isEmpty()) {
                return new EvaluationResult(false, traza);
            }

            String siguiente = destinos.get(0);
            traza.add("δ(" + estadoActual + ", " + simbolo + ") -> " + siguiente);
            estadoActual = siguiente;
        }

        boolean aceptada = automata.getEstadosAceptacion().contains(estadoActual);
        return new EvaluationResult(aceptada, traza);
    }
}