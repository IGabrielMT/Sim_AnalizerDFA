package co.edu.uptc.automata.model;

import co.edu.uptc.automata.model.automata.Automata;

import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class EvaluadorDFA {

    public boolean evaluarCadena(Automata automata, String cadena) {
        if (automata == null || cadena == null) return false;

        // 1. Manejamos un SET de estados actuales (fundamental para AFN)
        Set<String> estadosActuales = new HashSet<>();
        estadosActuales.add(automata.getEstadoInicial());

        // Opcional: Si implementas transiciones lambda, aquí deberías hacer el primer Cierre-Epsilon
        // estadosActuales = calcularCierreEpsilon(automata, estadosActuales);

        for (char caracter : cadena.toCharArray()) {
            String simbolo = String.valueOf(caracter);

            // Verificamos alfabeto
            if (!automata.getAlfabeto().contains(simbolo)) return false;

            Set<String> proximosEstados = new HashSet<>();

            // 2. Transicionamos desde TODOS los estados en los que podríamos estar
            for (String estado : estadosActuales) {
                Map<String, List<String>> mapaSimbolos = automata.getTransiciones().get(estado);

                if (mapaSimbolos != null && mapaSimbolos.containsKey(simbolo)) {
                    // Agregamos todos los destinos posibles para ese símbolo
                    proximosEstados.addAll(mapaSimbolos.get(simbolo));
                }
            }

            // Si no hay a dónde ir con este símbolo, la cadena muere
            if (proximosEstados.isEmpty()) return false;

            estadosActuales = proximosEstados;

            // Opcional: Cierre-Epsilon después de cada símbolo si es AFN-Lambda
            // estadosActuales = calcularCierreEpsilon(automata, estadosActuales);
        }

        // 3. Aceptación: ¿Al menos uno de los estados finales es de aceptación?
        for (String estado : estadosActuales) {
            if (automata.getEstadosAceptacion().contains(estado)) {
                return true;
            }
        }

        return false;
    }
}
