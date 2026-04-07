package co.edu.uptc.model.automata;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;
import java.util.Map;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Automata {
    private String nombre;
    private String descripcion;
    private List<String> estados;
    private List<String> alfabeto;
    private String estadoInicial;
    private List<String> estadosAceptacion;
    private Map<String, Map<String, List<String>>> transiciones;

    @Override
    public String toString() {
        return nombre + " - " + descripcion;
    }
}