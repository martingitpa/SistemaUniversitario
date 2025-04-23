// src/app/App.java (o simplemente en src/)
import javax.swing.*;
import java.util.*;
import model.*;
import view.MenuPrincipalFrame;
import model.Planes.*;

public class Main{
    public static void main(String[] args) {
        // Crear materias y plan como hiciste antes
        PlanEstudio plan = new PlanEstudio("Plan A", new PlanA());
        PlanEstudio plan2 = new PlanEstudio("Plan B", new PlanB());
        
        Materia programacion = new Materia("Programacion 1", 1, true, true);
        Materia estructuras = new Materia("Estructuras de Datos", 2, true, true);
        Materia basesDeDatos = new Materia("Bases de Datos", 3, true, true);

        plan.agregarMateria(programacion);
        plan.agregarMateria(estructuras);
        plan.agregarMateria(basesDeDatos);

        Carrera carrera = new Carrera("Licenciatura en Sistemas", plan, 2);
        Carrera carrera2 = new Carrera("Analista en Sistemas", plan2,2);
        
        List<Alumno> alumnos = new ArrayList<>();
        List<Carrera> carreras = new ArrayList<>();
        List<PlanEstudio> planEstudio = new ArrayList<>();
        carreras.add(carrera);
        carreras.add(carrera2);
        planEstudio.add(plan);
        planEstudio.add(plan2);

        // Iniciar GUI
        SwingUtilities.invokeLater(() -> {
            new MenuPrincipalFrame(alumnos, carreras, planEstudio).setVisible(true);
        });
    }
}
