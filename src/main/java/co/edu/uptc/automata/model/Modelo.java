package co.edu.uptc.automata.model;

import co.edu.uptc.automata.model.automata.Automata;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class Modelo {
    private List<Automata> automatas;
    private EvaluadorAutomatas ea;

    public Modelo(){
        ea = new EvaluadorAutomatas();
    }

    public boolean evaluarCadena(Automata automata, String cadena){
        return ea.evaluarCadena(automata, cadena);
    }

}
