package model.Planes;

import java.util.List;
import model.Alumno;
import model.Materia;
import model.MateriasAlumno;
import model.Strategy;

public class PlanD implements Strategy {

    @Override
    public boolean verificarCondicion(Materia materia, Alumno alumno) {
        // 1. Verificar cursadas de las correlativas
        for (Materia correlativa : materia.getCorrelativas()) {
            MateriasAlumno ma = alumno.getAlumnoMateria(correlativa);
            if (ma == null || !ma.isAproboCursada()) {
                return false;
            }
        }

        // 2. Verificar finales aprobados de materias de los últimos 3 cuatrimestres
        int cuatrimestreActual = materia.getCuatrimestre();
        List<MateriasAlumno> historial = alumno.getHistorialAcademico();

        for (MateriasAlumno ma : historial) {
            Materia m = ma.getMateria();
            int cuat = m.getCuatrimestre();

            if (cuat >= cuatrimestreActual - 3 && cuat < cuatrimestreActual) {
                if (!ma.isAproboFinal()) {
                    return false;
                }
            }
        }

        return true;
    }
}
