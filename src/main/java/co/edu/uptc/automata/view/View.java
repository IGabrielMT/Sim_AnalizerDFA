package co.edu.uptc.automata.view;

import java.util.List;
import java.util.Scanner;

public class View implements IView {
    private final Scanner sc = new Scanner(System.in);

    @Override
    public int mostrarMenuInicio() {
        System.out.println("=== Simulador de Autómatas Finitos ===");
        System.out.println("1. Crear autómata");
        System.out.println("2. Cargar y evaluar autómata");
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
    public void mostrarMensaje(String mensaje){
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
            System.out.println("Trazabilidad:");
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
        System.out.println("1. Verificar cadena con autómata");
        System.out.println("0. Salir");
        System.out.print("> ");
        String entrada = sc.nextLine();
        try {
            return Integer.parseInt(entrada);
        } catch (NumberFormatException e) {
            mostrarError("Opción no válida, intente de nuevo.");
            return -1;
        }
    }
}