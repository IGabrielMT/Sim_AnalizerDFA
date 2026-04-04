package co.edu.uptc;

import co.edu.uptc.automata.model.EvaluadorAutomatas;
import co.edu.uptc.automata.model.Modelo;
import co.edu.uptc.automata.model.automata.Automata;
import co.edu.uptc.presenter.Presenter;
import co.edu.uptc.view.View;

import java.util.*;

public class Main {
    public static void main(String[] args) {
        Modelo modelo = new Modelo();
        View view = new View();
        Presenter presenter = new Presenter(modelo, view);
        presenter.start();
    }
}