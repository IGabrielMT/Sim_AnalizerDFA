# Simulador y Analizador Universal de Autómatas Finitos (DFA)

Este proyecto es una implementación computacional de un motor de evaluación de Autómatas Finitos Deterministas (DFA). El software permite modelar la quíntupla de un autómata, persistir su estado, y ejecutar validaciones de cadenas con trazabilidad paso a paso, aplicando principios de diseño de software y separación de responsabilidades.

## Stack Tecnológico

* **Lenguaje:** Java 21
* **Gestor de dependencias:** Maven
* **Librerías principales:**
  * **Gson (2.10.1):** Utilizada para la serialización y deserialización de las estructuras de datos hacia formato JSON.
  * **Lombok:** Implementada para reducir el código repetitivo (boilerplate) en la generación de constructores, getters y setters en la capa de modelo.
* **Arquitectura:** Patrón Model-View-Presenter (MVP)
* **Interfaz:** Consola / Terminal de comandos

## Arquitectura del Proyecto (MVP)

El proyecto está estrictamente desacoplado utilizando el patrón MVP para separar la lógica del dominio matemático de la entrada/salida de datos del usuario.

* **Model:** Contiene las entidades del dominio (Autómata) y el estado de la aplicación. Actúa como fachada para el motor matematico, delegando la lógica de validación y el cálculo de la trazabilidad a la clase `AutomataEvaluator`.
* **View (IView / View):** Capa pasiva que maneja exclusivamente la entrada y salida por terminal. `IView` define el contrato de comunicación, garantizando que la vista no contenga ninguna regla de negocio.
* **Presenter:** Es el orquestador del flujo. Gestiona la comunicación entre la vista y el modelo. Se encarga de validar entradas, ordenar la persistencia y disparar la evaluación de cadenas, sin acoplarse a los detalles de infraestructura.
* **Infraestructura (Repository / JSONManager):** `AutomataRepository` maneja la abstracción de persistencia, apoyándose en `JSONManager` para interactuar con el sistema de archivos usando Gson.

## Funcionalidades Implementadas

* **Gestión de Autómatas:**
  * Creación interactiva de autómatas DFA desde consola validando su estructura (nombre, descripción, estados, alfabeto, estado inicial, estados de aceptación y mapa de transiciones).
  * Persistencia automática y carga dinámica de autómatas guardados al iniciar la sesión.
* **Motor de Evaluación:**
  * **Evaluación individual y por lotes:** Capacidad de procesar una única cadena o un batch de múltiples cadenas en una sola ejecución.
  * **Trazabilidad:** Generación del recorrido paso a paso por cada transición aplicada durante la evaluación de la cadena.
  * **Validación de Alfabeto:** Rechazo automático (short-circuit) si la cadena introducida contiene símbolos que no pertenecen al alfabeto definido.
* **Experiencia de Usuario (CLI):**
  * Navegación mediante menús robustos con manejo de excepciones para entradas no numéricas o inválidas.

## Estructura de Directorios

```text
src/main/java/co/edu/uptc/
├── Main.java
├── presenter/
│   └── Presenter.java
├── view/
│   ├── IView.java
│   └── View.java
└── model/
    ├── Model.java
    ├── EvaluationResult.java
    ├── AutomataEvaluator.java
    ├── AutomataRepository.java
    ├── automata/
    │   └── Automata.java
    └── jsonmanager/
        └── JSONManager.java