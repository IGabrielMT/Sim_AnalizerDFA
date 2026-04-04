package co.edu.uptc.automata.model;

import co.edu.uptc.automata.model.automata.Automata;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
public class Model {
    private List<Automata> automatas;
    private EvaluationResult ea;

    public Model(){
        ea = new EvaluationResult();
    }

    public ResultadoEvaluacion evaluarCadena(Automata automata, String cadena){
        boolean aceptada = ea.evaluarCadena(automata, cadena);
        return new ResultadoEvaluacion(aceptada, new ArrayList<>());
    }

}
