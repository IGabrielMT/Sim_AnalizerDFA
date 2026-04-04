package co.edu.uptc.presenter;

import co.edu.uptc.automata.model.Modelo;
import co.edu.uptc.automata.model.automata.Automata;
import co.edu.uptc.automata.model.jsonmanager.JSONManager;
import co.edu.uptc.view.View;
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

    private void showMenu() {
        String option = "";
        while(!option.equalsIgnoreCase("0")){
            option = view.showMessage(new String[]{"Que desea hacer: (Inserte en consola)", "1. Verificar cadena con autómata", "0. Salir"});
            switch (option) {
                case "1" -> verificarCadenaAutomata();
                case "0" -> System.exit(0);
                default -> view.showMessage("Opción no válida, intente de nuevo.");
            }
        }
    }
    private void verificarCadenaAutomata() {
        List<Automata> automatas = model.getAutomatas();
    // Creamos el flujo de nombres
        List<String> listaMenu = new ArrayList<>(automatas.stream()
                .map(a -> (automatas.indexOf(a) + 1) + ". " + (a.isAFD() ? "DFA" : "NFA") + " - ID: " + a.hashCode())
                .toList());
        // Añadimos la opción de volver
        listaMenu.add("0. Volver al menú principal");
        String[] menuAutomatas = listaMenu.toArray(new String[0]);        String option = "";
        while (!option.equals("0")) {
            option = view.showMessage(menuAutomatas);
            // Intentamos convertir la opción a un número para buscar en la lista
            try {
                int seleccion = Integer.parseInt(option);
                if (seleccion > 0 && seleccion <= automatas.size()) {
                    // --- AQUÍ SE ELIGE EL AUTÓMATA ---
                    Automata elegido = automatas.get(seleccion - 1);
                    // Pedimos la cadena a evaluar
                    String cadena = view.showMessage(new String[]{"Ingrese la cadena a evaluar:"});
                    boolean esValida = model.evaluarCadena(elegido, cadena);
                    String resultado = esValida ? "¡Cadena ACEPTADA!" : "Cadena RECHAZADA.";
                    view.showMessage(new String[]{resultado, "Presione Enter para continuar..."});
                } else if (seleccion != 0) {
                    view.showMessage(new String[]{"Opción inválida. Intente de nuevo."});
                }
            } catch (NumberFormatException e) {
                if (!option.equals("0")) {
                    view.showMessage(new String[]{"Por favor, inserte un número válido."});
                }
            }
        }

    }
    private void getDataAndSetData() {
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

    private void saveData() {
        JSONManager.createJSONFileByCollection("src/main/resources/automata.json", model.getAutomatas());
    }


    }
