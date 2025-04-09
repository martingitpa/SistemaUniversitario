package model.Planes;

import model.Alumno;
import model.Materia;
import model.Strategy;

public class PlanB implements Strategy {
    @Override
    public boolean verificarCondicion(Materia materia, Alumno alumno) {
        System.out.println("\n");
        System.out.println(alumno.getNombre() + " puede cursar |" + materia.getNombre() + "| ???");

        // Si no hay correlativas, y no hay historial académico, puede cursar
        if (materia.getCorrelativas().isEmpty() && alumno.getMateriasAprobadas().isEmpty()) {
            System.out.println("La materia no tiene correlativas y " + alumno.getNombre() + " no tiene materias aprobadas. Puede cursar.");
            return true;
        }

        // Para cada correlativa, se requiere tener aprobado el final
        for (Materia correlativa : materia.getCorrelativas()) {
            boolean finalAprobado = alumno.getMateriasAprobadas().contains(correlativa);
            System.out.println(materia.getNombre() + " requiere final aprobado de: " + correlativa.getNombre() + " | ¿Aprobado? " + finalAprobado);

            if (!finalAprobado) {
                System.out.println("No puede cursar " + materia.getNombre() + " porque no aprobó el final de " + correlativa.getNombre());
                return false;
            }
        }

        System.out.println("Puede cursar " + materia.getNombre());
        return true;
    }
}
