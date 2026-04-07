package co.edu.uptc.model;

import co.edu.uptc.model.automata.Automata;
import co.edu.uptc.model.jsonmanager.JSONManager;

import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;

public class AutomataRepository {
    private final String filePath;

    public AutomataRepository() {
        String ruta;
        try {
            Path directorioBase = Paths.get(
                AutomataRepository.class
                    .getProtectionDomain()
                    .getCodeSource()
                    .getLocation()
                    .toURI()
            ).getParent();

            if (directorioBase == null) {
                directorioBase = Paths.get(".");
            }

            Path carpetaAutomatas = directorioBase.resolve("automatas");
            Path archivoJSON = carpetaAutomatas.resolve("automata.json");
            ruta = archivoJSON.toString();

        } catch (URISyntaxException e) {
            ruta = Paths.get(".")
                    .resolve("automatas")
                    .resolve("automata.json")
                    .toString();
        }

        this.filePath = ruta;
    }

    public List<Automata> cargarAutomatas() {
        try {
            Path carpetaAutomatas = Paths.get(filePath).getParent();
            if (!Files.exists(carpetaAutomatas)) {
                Files.createDirectories(carpetaAutomatas);
            }
            if (!Files.exists(Paths.get(filePath))) {
                JSONManager.createJSONFileByCollection(filePath, new ArrayList<>());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        Collection<?> collection = JSONManager.createCollectionByJSONFile(filePath, Automata.class);
        if (collection == null || collection.isEmpty()) {
            return new ArrayList<>();
        }

        return new ArrayList<>(List.of(Arrays.copyOf(collection.toArray(), collection.size(), Automata[].class)));
    }

    public void guardarAutomatas(List<Automata> automatas) {
        JSONManager.createJSONFileByCollection(filePath, automatas == null ? new ArrayList<>() : automatas);
    }
}