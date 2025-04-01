package model;

import java.util.ArrayList;
import java.util.List;

public class Carrera {
    private String nombre;
    private PlanEstudio planEstudio;
    private List<Materia> materiasObligatorias; // Materias obligatorias de la carrera
    private List<Materia> materiasOptativas; // Materias optativas de la carrera
    private int cantOptativas;

    public Carrera(String nombre, PlanEstudio planEstudio, int cantOptativas) {
        this.nombre = nombre;
        this.planEstudio = planEstudio;
        this.materiasObligatorias = new ArrayList<>();
        this.materiasOptativas = new ArrayList<>();
        this.cantOptativas = cantOptativas;
    }

    public String getNombre() {
        return nombre;
    }

    public PlanEstudio getPlanEstudio() {
        return planEstudio;
    }

    public List<Materia> getMateriasObligatorias() {
        return materiasObligatorias;
    }

    public List<Materia> getMateriasOptativas() {
        return materiasOptativas;
    }

    public void cargarObligatoria(Materia materia) {
        materiasObligatorias.add(materia);
    }

    public void cargarOptativa(Materia materia) {
        materiasOptativas.add(materia);
    }

    public int getCantidadOptativasRequeridas() {
        return cantOptativas;
    }

    @Override
    public String toString() {
        return nombre;
    }

}