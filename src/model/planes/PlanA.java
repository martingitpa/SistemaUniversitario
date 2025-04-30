package model.Planes;

import model.Alumno;
import model.Materia;
import model.Strategy;

public class PlanA implements Strategy {
    @Override
    public boolean verificarCondicion(Materia materia, Alumno alumno) {
        System.out.println("\n");
        System.out.println(alumno.getNombre() + " puede cursar |" + materia.getNombre()+"| ???");

        // Chequear Correlativas
        if (materia.getCorrelativas().isEmpty() && alumno.getHistorialAcademico().isEmpty()) {
            System.out.println("La materia no tiene correlativas y "+ alumno.getNombre()+ " no tiene historial academico. Puede cursar.");
            return true;
        }

        for (Materia correlativa : materia.getCorrelativas()) {
            boolean aprobada = alumno.getMateriasAprobadas().contains(correlativa);
            System.out.println(materia.getNombre() +" Requiere la siguiente correlativa: " + correlativa.getNombre() + " | ¿Aprobada? " + aprobada);

            if (!aprobada) {
                System.out.println("No puede cursar " + materia.getNombre() + " porque no aprobo " + correlativa.getNombre());
                return false;
            }
        }
        return true;
    }
}