package co.edu.uptc.automata.model.automata;

import lombok.*;

import java.util.List;
import java.util.Map;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Automata {
    private boolean isAFD;
    private List<String> estados;
    private List<String> alfabeto;
    private String estadoInicial;
    private List<String> estadosAceptacion;
    private Map<String, Map<String, List<String>>> transiciones;
}