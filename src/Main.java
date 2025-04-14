// src/app/App.java (o simplemente en src/)
import javax.swing.*;
import java.util.*;
import model.*;
import view.MenuPrincipalFrame;
import model.Planes.PlanA;

public class Main{
    public static void main(String[] args) {
        // Crear materias y plan como hiciste antes
        PlanEstudio plan = new PlanEstudio("Plan 2025", new PlanA());

        Materia programacion = new Materia("Programacion 1", 1, true, true);
        Materia estructuras = new Materia("Estructuras de Datos", 2, true, true);
        Materia basesDeDatos = new Materia("Bases de Datos", 3, true, true);

        plan.agregarMateria(programacion);
        plan.agregarMateria(estructuras);
        plan.agregarMateria(basesDeDatos);

        Carrera carrera = new Carrera("Licenciatura en Sistemas", plan, 2);

        List<Alumno> alumnos = new ArrayList<>();
        List<Carrera> carreras = new ArrayList<>();
        carreras.add(carrera);

        // Iniciar GUI
        SwingUtilities.invokeLater(() -> {
            new MenuPrincipalFrame(alumnos, carreras).setVisible(true);
        });
    }
}
