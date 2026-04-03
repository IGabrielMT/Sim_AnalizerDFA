package co.edu.uptc.presenter;

import co.edu.uptc.automata.model.Modelo;
import co.edu.uptc.automata.model.automata.Automata;
import co.edu.uptc.automata.model.jsonmanager.JSONManager;

import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;

public class Presenter {
    private Modelo model;


    public void getDataAndSetData() {
        if (!Files.exists(Paths.get("automatas.json"))) {
            JSONManager.createJSONFileByCollection("automatas.json",new ArrayList<>() );
        }
        Collection<?> collection = JSONManager.createCollectionByJSONFile("automatas.json", Automata.class);
        if (collection == null || collection.isEmpty()) {
            model.setAutomatas(new ArrayList<>());
        }
        else{
            model.setAutomatas(new ArrayList<>(List.of(Arrays.copyOf(collection.toArray(), collection.size(), Automata[].class))));
        }
    }

}
