package model.Planes;

import model.Alumno;
import model.Materia;
import model.Strategy;

public class PlanB implements Strategy {
    @Override
    public boolean verificarCondicion(Materia materia, Alumno alumno) {
        System.out.println("\n");
        System.out.println(alumno.getNombre() + " puede cursar |" + materia.getNombre() + "| ???");

        // Si la materia no tiene correlativas, puede cursarla sin restricciones
        if (materia.getCorrelativas().isEmpty()) {
            System.out.println("La materia no tiene correlativas. " + alumno.getNombre() + " puede cursar.");
            return true;
        }

        // Verificar que el alumno haya aprobado los finales de todas las correlativas
        for (Materia correlativa : materia.getCorrelativas()) {
            boolean finalAprobado = alumno.getFinalesAprobados().contains(correlativa);
            System.out.println(materia.getNombre() + " requiere haber aprobado el final de: " + correlativa.getNombre() + " | ¿Final aprobado? " + finalAprobado);

            if (!finalAprobado) {
                System.out.println("No puede cursar " + materia.getNombre() + " porque no aprobó el final de " + correlativa.getNombre());
                return false;
            }
        }

        System.out.println(alumno.getNombre() + " cumple con los requisitos. Puede cursar " + materia.getNombre());
        return true;
    }
}