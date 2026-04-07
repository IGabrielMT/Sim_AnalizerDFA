package co.edu.uptc.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
public class EvaluationResult {
    private boolean aceptada;
    private List<String> traza;
}