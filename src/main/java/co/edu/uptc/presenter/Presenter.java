package co.edu.uptc.presenter;

import co.edu.uptc.automata.model.AutomataRepository;
import co.edu.uptc.automata.model.Model;
import co.edu.uptc.automata.model.ResultadoEvaluacion;
import co.edu.uptc.automata.model.automata.Automata;
import co.edu.uptc.automata.view.IView;
import lombok.AllArgsConstructor;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@AllArgsConstructor
public class Presenter {
    private Model model;
    private IView view;
    private AutomataRepository repository;

    public void start(){
        getDataAndSetData();
        showMenu();
    }

    private void showMenu() {
        boolean running = true;
        while (running) {
            int option = view.mostrarMenuInicio();
            switch (String.valueOf(option)) {
                case "1" -> crearAutomata();
                case "2" -> verificarCadenaAutomata();
                case "0" -> running = false;
                default -> view.mostrarError("Opción no válida.");
            }
        }
    }
<<<<<<< HEAD

    private void crearAutomata() {
        String nombre = "";
        while (nombre.isBlank()) {
            nombre = view.solicitarEntrada("Ingrese el nombre del autómata:").trim();
            if (nombre.isBlank()) {
                view.mostrarError("El nombre no puede estar vacío.");
=======
    private void verificarCadenaAutomata() {
        List<Automata> automatas = model.getAutomatas();
    // Creamos el flujo de nombres
        List<String> listaMenu = new ArrayList<>(automatas.stream()
                .map(a -> (automatas.indexOf(a) + 1) + ". " + (a.isAFD() ? "DFA" : "NFA") + " - ID: " + a.hashCode())
                .toList());
        // Añadimos la opción de volver
        listaMenu.add("0. Volver al menú principal");
        String[] menuAutomatas = listaMenu.toArray(new String[0]);
        String option = "";
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
>>>>>>> dcb7f1f506c9b64f9201df938951d0d7ec47f160
            }
        }

        String descripcion = "";
        while (descripcion.isBlank()) {
            descripcion = view.solicitarEntrada("Ingrese una descripción (qué cadenas acepta):").trim();
            if (descripcion.isBlank()) {
                view.mostrarError("La descripción no puede estar vacía.");
            }
        }

        List<String> estados = new ArrayList<>();
        while (estados.isEmpty()) {
            String entradaEstados = view.solicitarEntrada("Ingrese los estados separados por coma (ej: q0,q1,q2):");
            String[] partes = entradaEstados.split(",");
            estados = new ArrayList<>();
            for (String parte : partes) {
                String estado = parte.trim();
                if (!estado.isEmpty()) {
                    estados.add(estado);
                }
            }
            if (estados.isEmpty()) {
                view.mostrarError("Debe ingresar al menos un estado.");
            }
        }

        List<String> alfabeto = new ArrayList<>();
        while (alfabeto.isEmpty()) {
            String entradaAlfabeto = view.solicitarEntrada("Ingrese los símbolos del alfabeto separados por coma (ej: 0,1):");
            String[] partes = entradaAlfabeto.split(",");
            alfabeto = new ArrayList<>();
            for (String parte : partes) {
                String simbolo = parte.trim();
                if (!simbolo.isEmpty()) {
                    alfabeto.add(simbolo);
                }
            }
            if (alfabeto.isEmpty()) {
                view.mostrarError("Debe ingresar al menos un símbolo.");
            }
        }

        String estadoInicial = "";
        while (true) {
            estadoInicial = view.solicitarEntrada("Ingrese el estado inicial:").trim();
            if (estados.contains(estadoInicial)) {
                break;
            }
            view.mostrarError("El estado inicial debe existir en la lista de estados.");
        }

        List<String> estadosAceptacion;
        while (true) {
            String entradaAceptacion = view.solicitarEntrada("Ingrese los estados de aceptación separados por coma:");
            estadosAceptacion = new ArrayList<>();
            if (!entradaAceptacion.trim().isEmpty()) {
                String[] partes = entradaAceptacion.split(",");
                for (String parte : partes) {
                    String estado = parte.trim();
                    if (!estado.isEmpty()) {
                        estadosAceptacion.add(estado);
                    }
                }
            }

            boolean todosValidos = true;
            for (String estado : estadosAceptacion) {
                if (!estados.contains(estado)) {
                    todosValidos = false;
                    break;
                }
            }
            if (todosValidos) {
                break;
            }
            view.mostrarError("Todos los estados de aceptación deben existir en la lista de estados.");
        }

        Map<String, Map<String, List<String>>> transiciones = new HashMap<>();
        for (String estado : estados) {
            Map<String, List<String>> porSimbolo = new HashMap<>();
            for (String simbolo : alfabeto) {
                while (true) {
                    String destino = view.solicitarEntrada("Transición δ(" + estado + ", " + simbolo + ") = ").trim();
                    if (destino.isEmpty()) {
                        porSimbolo.put(simbolo, new ArrayList<>());
                        break;
                    }
                    if (estados.contains(destino)) {
                        List<String> destinoDfa = new ArrayList<>();
                        destinoDfa.add(destino);
                        porSimbolo.put(simbolo, destinoDfa);
                        break;
                    }
                    view.mostrarError("El estado destino debe existir en la lista de estados.");
                }
            }
            transiciones.put(estado, porSimbolo);
        }

        Automata automata = new Automata(
                nombre,
                descripcion,
                true,
                estados,
                alfabeto,
                estadoInicial,
                estadosAceptacion,
                transiciones
        );

        if (model.getAutomatas() == null) {
            model.setAutomatas(new ArrayList<>());
        }
        model.getAutomatas().add(automata);
        saveData();
        view.mostrarMensaje("Autómata creado y guardado correctamente.");
    }

    private void verificarCadenaAutomata() {
        List<Automata> automatas = model.getAutomatas();

        if (automatas == null || automatas.isEmpty()) {
            view.mostrarError("No hay autómatas cargados.");
            return;
        }

        List<String> listaMenu = new ArrayList<>(automatas.stream()
            .map(a -> (automatas.indexOf(a) + 1) + ". " + a.getNombre() + " | " + a.getDescripcion())
                .toList());
        listaMenu.add("0. Volver al menú principal");

        String option = "";
        while (!option.equals("0")) {
            option = view.solicitarEntrada(String.join("\n", listaMenu));
            try {
                int seleccion = Integer.parseInt(option);
                if (seleccion > 0 && seleccion <= automatas.size()) {
                    Automata elegido = automatas.get(seleccion - 1);
                    String cadena = view.solicitarEntrada("Ingrese la cadena a evaluar:");
                    // MVP: la traza y el resultado vienen del Model, no se construyen en el Presenter.
                    ResultadoEvaluacion resultado = model.evaluarCadena(elegido, cadena);
                    view.mostrarResultadoEvaluacion(cadena, resultado.isAceptada(), resultado.getTraza());
                    view.solicitarEntrada("Presione Enter para continuar...");
                } else if (seleccion != 0) {
                    view.mostrarError("Opción inválida. Intente de nuevo.");
                }
            } catch (NumberFormatException e) {
                if (!option.equals("0")) {
                    view.mostrarError("Por favor, inserte un número válido.");
                }
            }
        }
        saveData();

    }
    private void getDataAndSetData() {
        List<Automata> automatas = repository.cargarAutomatas();
        if (automatas.isEmpty()) {
            view.mostrarMensaje("No hay automatas guardados. Se inicializa una lista vacía.");
            model.setAutomatas(new ArrayList<>());
        } else {
            model.setAutomatas(automatas);
            view.mostrarMensaje("Automatas cargados en el modelo.");
        }
    }

    private void saveData() {
        repository.guardarAutomatas(model.getAutomatas());
    }


    }
