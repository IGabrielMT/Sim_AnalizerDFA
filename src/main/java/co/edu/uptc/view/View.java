package co.edu.uptc.view;

import java.util.List;
import java.util.Scanner;

public class View implements IView {
    private final Scanner sc = new Scanner(System.in);

    @Override
    public int mostrarMenuInicio() {
        System.out.println("=== Simulador de Automatas Finitos DFA ===");
        System.out.println("1. Crear automata");
        System.out.println("2. Cargar y evaluar automata");
        System.out.println("0. Salir");
        System.out.print("> ");
        String entrada = sc.nextLine();
        try {
            return Integer.parseInt(entrada);
        } catch (NumberFormatException e) {
            return -1;
        }
    }

    @Override
    public void mostrarMensaje(String mensaje) {
        System.out.println(mensaje);
    }

    @Override
    public void mostrarError(String error) {
        System.out.println("Error: " + error);
    }

    @Override
    public void mostrarResultadoEvaluacion(String cadena, boolean aceptada, List<String> trazabilidad) {
        System.out.println("Cadena evaluada: " + cadena);
        System.out.println(aceptada ? "Resultado: ACEPTADA" : "Resultado: RECHAZADA");
        if (trazabilidad != null && !trazabilidad.isEmpty()) {
            boolean esErrorAlfabeto = trazabilidad.size() == 1 &&
                    trazabilidad.get(0).startsWith("El simbolo");
            if (esErrorAlfabeto) {
                System.out.println("Motivo de rechazo:");
            } else {
                System.out.println("Trazabilidad:");
            }
            for (String paso : trazabilidad) {
                System.out.println("- " + paso);
            }
        }
    }

    @Override
    public String solicitarEntrada(String mensaje) {
        System.out.println(mensaje);
        System.out.print("> ");
        return sc.nextLine();
    }

    @Override
    public int mostrarMenuPrincipal() {
        System.out.println("Que desea hacer: (Inserte en consola)");
        System.out.println("1. Verificar cadena con automata");
        System.out.println("0. Salir");
        System.out.print("> ");
        String entrada = sc.nextLine();
        try {
            return Integer.parseInt(entrada);
        } catch (NumberFormatException e) {
            mostrarError("Opcion no valida, intente de nuevo.");
            return -1;
        }
    }
}