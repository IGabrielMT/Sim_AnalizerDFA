package co.edu.uptc.model;

import co.edu.uptc.model.automata.Automata;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class AutomataEvaluator {

    public EvaluationResult evaluarCadena(Automata automata, String cadena) {
        List<String> traza = new ArrayList<>();

        // Validación completa del autómata
        if (automata == null) {
            traza.add("Error: El autómata es nulo.");
            return new EvaluationResult(false, traza);
        }

        if (cadena == null) {
            traza.add("Error: La cadena es nula.");
            return new EvaluationResult(false, traza);
        }

        // Validar que el autómata tenga estructura válida
        if (automata.getAlfabeto() == null || automata.getAlfabeto().isEmpty()) {
            traza.add("Error: El autómata no tiene alfabeto definido.");
            return new EvaluationResult(false, traza);
        }

        if (automata.getTransiciones() == null || automata.getTransiciones().isEmpty()) {
            traza.add("Error: El autómata no tiene transiciones definidas.");
            return new EvaluationResult(false, traza);
        }

        // Validación del alfabeto
        for (char c : cadena.toCharArray()) {
            String simbolo = String.valueOf(c);
            if (!automata.getAlfabeto().contains(simbolo)) {
                traza.add("El símbolo '" + simbolo + "' no pertenece al alfabeto " + automata.getAlfabeto());
                return new EvaluationResult(false, traza);
            }
        }

        // Cadena vacía
        if (cadena.isEmpty()) {
            String estadoInicial = automata.getEstadoInicial();
            boolean aceptada = automata.getEstadosAceptacion().contains(estadoInicial);
            if (aceptada) {
                traza.add("Cadena vacía aceptada en estado inicial: " + estadoInicial);
            } else {
                traza.add("Cadena vacía rechazada. Estado inicial '" + estadoInicial + "' no es de aceptación.");
            }
            return new EvaluationResult(aceptada, traza);
        }

        // Evaluación del DFA
        String estadoActual = automata.getEstadoInicial();

        for (char c : cadena.toCharArray()) {
            String simbolo = String.valueOf(c);

            // Obtener transiciones del estado actual
            Map<String, List<String>> mapaSimbolos = automata.getTransiciones().get(estadoActual);
            if (mapaSimbolos == null) {
                traza.add("Error: No existen transiciones para el estado '" + estadoActual + "'.");
                return new EvaluationResult(false, traza);
            }

            // Verificar si existe transición para el símbolo
            if (!mapaSimbolos.containsKey(simbolo)) {
                traza.add("Error: No existe transición S(" + estadoActual + ", " + simbolo + ").");
                return new EvaluationResult(false, traza);
            }

            // Obtener estados destino
            List<String> destinos = mapaSimbolos.get(simbolo);
            if (destinos == null || destinos.isEmpty()) {
                traza.add("Error: La transición S(" + estadoActual + ", " + simbolo + ") está indefinida.");
                return new EvaluationResult(false, traza);
            }

            // Avanzar al siguiente estado
            String siguiente = destinos.getFirst();
            traza.add("S(" + estadoActual + ", " + simbolo + ") -> " + siguiente);
            estadoActual = siguiente;
        }

        // Verificación final
        boolean aceptada = automata.getEstadosAceptacion().contains(estadoActual);
        if (!aceptada) {
            traza.add("Estado final '" + estadoActual + "' no es de aceptación.");
        } else {
            traza.add("Cadena aceptada. Estado final: " + estadoActual);
        }

        return new EvaluationResult(aceptada, traza);
    }
}