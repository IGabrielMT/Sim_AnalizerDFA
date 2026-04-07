package co.edu.uptc.model;

import co.edu.uptc.model.automata.Automata;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class Model {
    private List<Automata> automatas;
    private AutomataEvaluator ea;

    public Model(){
        ea = new AutomataEvaluator();
    }

    public EvaluationResult evaluarCadena(Automata automata, String cadena){
        return ea.evaluarCadena(automata, cadena);
    }
}