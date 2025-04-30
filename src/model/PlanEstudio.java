   package model;

    import java.util.ArrayList;
    import java.util.List;

    public class PlanEstudio {
        private String nombre;
        private Strategy estrategia;
        private List<Materia> materias;

        public PlanEstudio(String nombre, Strategy estrategia){
            this.nombre = nombre;
            this.estrategia = estrategia;
            this.materias = new ArrayList<>();
        }

        public void agregarMateria(Materia materia){
            materias.add(materia);
        }

        public List<Materia> getMateriasDisponiblesParaAlumno(Alumno alumno) {
            System.out.println("Buscando materias disponibles para: " + alumno.getNombre());

            if (materias.isEmpty()) {
                System.err.println("El plan de estudio no tiene materias.");
            }

            List<Materia> disponibles = new ArrayList<>();

            for (Materia materia : materias) {
                boolean puedeCursar = estrategia.verificarCondicion(materia, alumno);
                System.out.println("Evaluando materia: " + materia.getNombre() + " | Puede cursar: " + puedeCursar);

                if (puedeCursar) {
                    disponibles.add(materia);
                }
            }

            System.out.println("\n Materias disponibles para " + alumno.getNombre() + ": ");

            return disponibles;
        }

        public String getNombre() {
            return nombre;
        }

        public List<Materia> getMaterias() {
            return materias;
        }

        @Override
        public String toString() {
            return nombre;
        }
        
        public Strategy getEstrategia() {
            return estrategia;
        }

    }