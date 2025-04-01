import model.*;
import model.Planes.PlanA;

public class Main {
    public static void main(String[] args) {
        // Crear un Plan de Estudios con una estrategia definida
        PlanEstudio plan = new PlanEstudio("Plan 2025", new PlanA());

        // Crear materias
        Materia programacion = new Materia("Programacion 1", 1, true, true);
        Materia estructuras = new Materia("Estructuras de Datos", 2, true, true);
        Materia basesDeDatos = new Materia("Bases de Datos", 3, true, true);

        // Agregar materias al plan de estudio
        plan.agregarMateria(programacion);
        plan.agregarMateria(estructuras);
        plan.agregarMateria(basesDeDatos);

        // Crear una Carrera
        Carrera carrera = new Carrera("Licenciatura en Sistemas", plan, 2);

        // Crear un Alumno
        Alumno alumno = new Alumno("4501934", "Martin Gutierrez");
        alumno.setCarrera(carrera);

        // Mostrar materias disponibles para cursar
        System.out.println("\n");
        
        for (Materia materia : plan.getMateriasDisponiblesParaAlumno(alumno)) {
            System.out.println("- " + materia.getNombre());
        }
    }
}