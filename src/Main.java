// src/app/App.java
import javax.swing.*;
import java.util.*;
import model.*;
import view.MenuPrincipalFrame;
import model.Planes.*;

public class Main {
    public static void main(String[] args) {
         // Listas globales
        List<Alumno> alumnos = new ArrayList<>();
        List<Carrera> carreras = new ArrayList<>();
        List<PlanEstudio> planes = new ArrayList<>();
        List<Materia> materias = new ArrayList<>();
        
        //Carga de alumnos
        Alumno alumno1= new Alumno("45018352", "Martín Gutierrez");
        Alumno alumno2= new Alumno("40987650", "Marcos Linux");
        Alumno alumno3= new Alumno("19675111", "Alberto Windows");

        alumnos.add(alumno1);
        alumnos.add(alumno2);
        alumnos.add(alumno3);
        
        //Cargar materias
        Materia elementos= new Materia("Elementos de informatica",1,true,true);
        Materia expresion= new Materia("Expresion de Problemas y Algoritmos",1,true,true);
        Materia alg= new Materia("Algebra",1,true,true);
        Materia eng= new Materia("Ingles",1,false,true);
        
        Materia ayp1= new Materia("Algoritmica y Programacion I",2,true,true);
        Materia elemlm= new Materia("Elementos de Logica y Matematica Discreta",2,true,true);
        Materia analisis= new Materia("Analisis Matematico",2,true,true);
        
        //Cargar correlativas
        ayp1.agregarCorrelativas(expresion);//Algoritmica 1 requiere haber aprobado expresion
        analisis.agregarCorrelativas(alg); //Analisis Matematico requiere Algebra
        
        materias.add(elementos);
        materias.add(expresion);
        materias.add(alg);
        materias.add(eng);
        materias.add(ayp1);
        materias.add(elemlm);
        materias.add(analisis);

        // Cargar planes
        PlanEstudio plan = new PlanEstudio("Plan Sistemas 2025 (Plan A)", new PlanA());
        plan.agregarMateria(elementos);
        plan.agregarMateria(expresion);
        plan.agregarMateria(alg);
        plan.agregarMateria(eng);
        plan.agregarMateria(ayp1);
        plan.agregarMateria(elemlm);
        plan.agregarMateria(analisis);
        planes.add(plan);

        // Cargar carreras
        Carrera carrera = new Carrera("Analista en Sistemas", plan, 1);//Requiere 1 optativa
        carrera.cargarObligatoria(elementos);
        carrera.cargarObligatoria(expresion);
        carrera.cargarObligatoria(alg);
        carrera.cargarOptativa(eng);
        carrera.cargarObligatoria(ayp1);
        carrera.cargarObligatoria(elemlm);
        carrera.cargarObligatoria(analisis);
        carreras.add(carrera);
        
        //Asocio la carrera a Martín
        alumno1.setCarrera(carrera);
        
        //Inscribo y apruebo todas las materias
        for (Materia materia : plan.getMaterias()) {
        alumno1.inscribirMateria(materia);
        MateriasAlumno ma = alumno1.getAlumnoMateria(materia);
        if (ma != null) {
            ma.aprobarCursada(8); // Ejemplo: 8 de nota cursada
            ma.aprobarFinal(8);   // Ejemplo: 8 de nota final
        }
    }

        // Iniciar interfaz
        SwingUtilities.invokeLater(() -> {
            new MenuPrincipalFrame(alumnos, carreras, planes, materias).setVisible(true);
        });
    }
}
