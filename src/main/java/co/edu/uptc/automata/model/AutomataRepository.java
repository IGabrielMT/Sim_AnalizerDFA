package co.edu.uptc.automata.model;

import co.edu.uptc.automata.model.automata.Automata;
import co.edu.uptc.automata.model.jsonmanager.JSONManager;

import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;

public class AutomataRepository {
    private static final String FILE_PATH = "src/main/resources/automata.json";

    public List<Automata> cargarAutomatas() {
        if (!Files.exists(Paths.get(FILE_PATH))) {
            JSONManager.createJSONFileByCollection(FILE_PATH, new ArrayList<>());
        }

        Collection<?> collection = JSONManager.createCollectionByJSONFile(FILE_PATH, Automata.class);
        if (collection == null || collection.isEmpty()) {
            return new ArrayList<>();
        }

        return new ArrayList<>(List.of(Arrays.copyOf(collection.toArray(), collection.size(), Automata[].class)));
    }

    public void guardarAutomatas(List<Automata> automatas) {
        JSONManager.createJSONFileByCollection(FILE_PATH, automatas == null ? new ArrayList<>() : automatas);
    }
}