// src/app/App.java
import javax.swing.*;
import java.util.*;
import model.*;
import view.MenuPrincipalFrame;
import model.Planes.*;

public class Main {
    public static void main(String[] args) {
        // Crear materias
        Materia programacion = new Materia("Programacion 1", 1, true, true);
        Materia estructuras = new Materia("Estructuras de Datos", 2, true, true);
        Materia basesDeDatos = new Materia("Bases de Datos", 3, true, true);

        // Crear lista de materias disponibles (se reutiliza para plan y para la app)
        List<Materia> materias = new ArrayList<>();
        materias.add(programacion);
        materias.add(estructuras);
        materias.add(basesDeDatos);

        // Crear planes
        PlanEstudio plan = new PlanEstudio("Plan A", new PlanA());
        plan.agregarMateria(programacion);
        plan.agregarMateria(estructuras);
        plan.agregarMateria(basesDeDatos);

        PlanEstudio plan2 = new PlanEstudio("Plan B", new PlanB());

        // Crear carreras
        Carrera carrera = new Carrera("Licenciatura en Sistemas", plan, 2);
        Carrera carrera2 = new Carrera("Analista en Sistemas", plan2, 2);

        // Listas globales
        List<Alumno> alumnos = new ArrayList<>();
        List<Carrera> carreras = new ArrayList<>();
        List<PlanEstudio> planEstudio = new ArrayList<>();

        carreras.add(carrera);
        carreras.add(carrera2);
        planEstudio.add(plan);
        planEstudio.add(plan2);

        // Iniciar interfaz
        SwingUtilities.invokeLater(() -> {
            new MenuPrincipalFrame(alumnos, carreras, planEstudio, materias).setVisible(true);
        });
    }
}
