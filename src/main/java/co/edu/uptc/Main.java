package co.edu.uptc;

import co.edu.uptc.automata.model.AutomataRepository;
import co.edu.uptc.automata.model.Model;
import co.edu.uptc.automata.view.View;
import co.edu.uptc.presenter.Presenter;

import java.util.*;

public class Main {
    public static void main(String[] args) {
        Model modelo = new Model();
        AutomataRepository repository = new AutomataRepository();
        View view = new View();
        Presenter presenter = new Presenter(modelo, view, repository);
        presenter.start();
    }
}