package model;

import java.util.ArrayList;
import java.util.List;

public class Alumno {
    private String dni;
    private String nombre;
    private Carrera carrera;
    private List<MateriasAlumno> historialAcademico;

    public Alumno(String dni, String nombre) {
        this.dni = dni;
        this.nombre = nombre;
        this.historialAcademico = new ArrayList<>();
    }


    public MateriasAlumno getAlumnoMateria(Materia materia) {
        for (MateriasAlumno am : historialAcademico) {
            if (am.getMateria().equals(materia)) {
                return am;
            }
        }
        return null; // Si no encuentra la relación
    }

    public List<Materia> getMateriasAprobadas() {
        List<Materia> aprobadas = new ArrayList<>();
        for (MateriasAlumno am : historialAcademico) {
            if (am.isAproboCursada()) {
                aprobadas.add(am.getMateria());
            }
        }
        return aprobadas;
    }

    public List<Materia> getFinalesAprobados() {
        List<Materia> finales = new ArrayList<>();
        for (MateriasAlumno am : historialAcademico) {
            if (am.isAproboFinal()) {
                finales.add(am.getMateria());
            }
        }
        return finales;
    }

    public List<MateriasAlumno> getHistorialAcademico() {
        return historialAcademico;
    }

    public String getNombre() {
        return nombre;
    }

    public String getDni() {
        return dni;
    }

    public Carrera getCarrera() {
        return carrera;
    }

    public void setCarrera(Carrera carrera) {
        this.carrera = carrera;
    }

    @Override
    public String toString() {
        return nombre;
    }
    
    public void inscribirMateria(Materia materia) {
        MateriasAlumno nuevaInscripcion = new MateriasAlumno(materia);
        historialAcademico.add(nuevaInscripcion);
    }

}   