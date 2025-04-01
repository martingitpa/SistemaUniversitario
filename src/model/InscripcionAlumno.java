package model;

public class InscripcionAlumno {
    private Alumno alumno;
    private Carrera carrera;

    public InscripcionAlumno(Alumno alumno, Carrera carrera) {
        this.alumno = alumno;
        this.carrera = carrera;
    }

    public Alumno getAlumno() {
        return alumno;
    }

    public Carrera getCarrera() {
        return carrera;
    }
}