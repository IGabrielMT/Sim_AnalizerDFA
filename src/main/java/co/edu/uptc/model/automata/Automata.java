package co.edu.uptc.model.automata;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@AllArgsConstructor
@Getter
@Setter
public class Automata {
    private List<String> states;
    private List<Character> alphabet;
    private String initialState;
    private String acceptedState;


}
