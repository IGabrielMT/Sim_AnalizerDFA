package co.edu.uptc.presenter;

import co.edu.uptc.automata.model.Modelo;
import co.edu.uptc.automata.model.automata.Automata;
import co.edu.uptc.automata.model.jsonmanager.JSONManager;
import lombok.AllArgsConstructor;

import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;

@AllArgsConstructor
public class Presenter {
    private Modelo model;
    private View view;

    public void start(){
        getDataAndSetData();
        showMenu();
    }


    public void getDataAndSetData() {
        if (!Files.exists(Paths.get("src/main/resources/automata.json"))) {
            System.out.println("No existe");
            JSONManager.createJSONFileByCollection("src/main/resources/automata.json",new ArrayList<>() );
        }
        Collection<?> collection = JSONManager.createCollectionByJSONFile("src/main/resources/automata.json", Automata.class);
        System.out.println("Variable collection creada");
        if (collection == null || collection.isEmpty()) {
            System.out.println("Collection vacio, creando arraylist vacío");
            model.setAutomatas(new ArrayList<>());
        }
        else{
            model.setAutomatas(new ArrayList<>(List.of(Arrays.copyOf(collection.toArray(), collection.size(), Automata[].class))));
            System.out.println("Collection no vacio, seteando en el modelo la lista de automatas");
        }
    }

    public void saveData() {
        JSONManager.createJSONFileByCollection("src/main/resources/automata.json", model.getAutomatas());
    }


    }
