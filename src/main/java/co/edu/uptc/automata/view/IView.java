package co.edu.uptc.automata.view;

import java.util.List;

public interface IView {
    int mostrarMenuInicio();

    void mostrarMensaje(String mensaje);

    void mostrarError(String error);

    void mostrarResultadoEvaluacion(String cadena, boolean aceptada, List<String> trazabilidad);

    String solicitarEntrada(String mensaje);

    int mostrarMenuPrincipal();
}