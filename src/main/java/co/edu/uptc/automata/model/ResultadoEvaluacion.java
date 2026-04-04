package co.edu.uptc.automata.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
public class ResultadoEvaluacion {
    private boolean aceptada;
    private List<String> traza;
}