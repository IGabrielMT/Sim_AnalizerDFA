package co.edu.uptc;

import co.edu.uptc.model.AutomataRepository;
import co.edu.uptc.model.Model;
import co.edu.uptc.view.View;
import co.edu.uptc.presenter.Presenter;


public class Main {
    public static void main(String[] args) {
        Model modelo = new Model();
        AutomataRepository repository = new AutomataRepository();
        View view = new View();
        Presenter presenter = new Presenter(modelo, view, repository);
        presenter.start();
    }
}