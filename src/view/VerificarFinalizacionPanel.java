package view;

import model.Alumno;
import model.Materia;
import model.Carrera;
import model.MateriasAlumno;
import javax.swing.*;
import java.awt.*;
import java.util.List;

public class VerificarFinalizacionPanel extends JPanel {

    private JComboBox<Alumno> comboAlumnos;
    private JLabel labelInfoAlumno;
    private JTextArea areaInfoAlumno;
    private JButton botonVerificar;

    public VerificarFinalizacionPanel(List<Alumno> alumnos) {
        setLayout(new BorderLayout());
        setBorder(BorderFactory.createEmptyBorder(40, 40, 40, 40));

        // Titulo
        JLabel titulo = new JLabel("Verificación de Finalización de Carrera");
        titulo.setFont(new Font("Arial", Font.BOLD, 22));
        add(titulo, BorderLayout.NORTH);

        // Panel central con formularios
        JPanel formPanel = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);
        gbc.anchor = GridBagConstraints.WEST;

        // Combo alumnos
        gbc.gridx = 0;
        gbc.gridy = 0;
        formPanel.add(new JLabel("Seleccione Alumno:"), gbc);

        comboAlumnos = new JComboBox<>(alumnos.toArray(new Alumno[0]));
        gbc.gridx = 1;
        formPanel.add(comboAlumnos, gbc);

        // Area de información
        labelInfoAlumno = new JLabel("Información del Alumno:");
        gbc.gridx = 0;
        gbc.gridy = 1;
        formPanel.add(labelInfoAlumno, gbc);

        areaInfoAlumno = new JTextArea(10, 50);
        areaInfoAlumno.setEditable(false);
        areaInfoAlumno.setText("Información del alumno se mostrará aquí.");
        gbc.gridx = 1;
        formPanel.add(new JScrollPane(areaInfoAlumno), gbc);

        // Botón verificar
        botonVerificar = new JButton("Verificar Finalización");
        gbc.gridx = 0;
        gbc.gridy = 2;
        gbc.gridwidth = 2;
        gbc.anchor = GridBagConstraints.CENTER;
        formPanel.add(botonVerificar, gbc);

        add(formPanel, BorderLayout.CENTER);

        // Acción del botón
        botonVerificar.addActionListener(e -> verificarFinalizacion());
    }

    private void verificarFinalizacion() {
    Alumno alumnoSeleccionado = (Alumno) comboAlumnos.getSelectedItem();

    if (alumnoSeleccionado != null) {
        StringBuilder info = new StringBuilder();
        info.append("Alumno: ").append(alumnoSeleccionado.getNombre()).append("\n");

        if (alumnoSeleccionado.getCarrera() != null) {
            Carrera carrera = alumnoSeleccionado.getCarrera();
            info.append("Carrera: ").append(carrera.getNombre()).append("\n\n");

            List<MateriasAlumno> historial = alumnoSeleccionado.getHistorialAcademico();
            if (historial.isEmpty()) {
                info.append("El alumno no está anotado en ninguna materia.\n");
            } else {
                info.append("Materias inscriptas:\n");
                for (MateriasAlumno materiaAlumno : historial) {
                    Materia materia = materiaAlumno.getMateria();
                    info.append("- ").append(materia.getNombre())
                        .append(" (Cursada Aprobada: ").append(materiaAlumno.isAproboCursada())
                        .append(", Final Aprobado: ").append(materiaAlumno.isAproboFinal()).append(")\n");
                }
            }

            // --- Análisis de materias obligatorias ---
            List<Materia> obligatorias = carrera.getMateriasObligatorias();
            List<Materia> optativas = carrera.getMateriasOptativas();
            int cantOptativasRequeridas = carrera.getCantidadOptativasRequeridas();

            List<Materia> obligatoriasAprobadas = new java.util.ArrayList<>();
            List<Materia> obligatoriasFaltantes = new java.util.ArrayList<>();
            List<Materia> optativasAprobadas = new java.util.ArrayList<>();
            List<Materia> optativasFaltantes = new java.util.ArrayList<>();
            
            for (Materia materia : obligatorias) {
                MateriasAlumno materiaAlumno = alumnoSeleccionado.getAlumnoMateria(materia);
                if (materiaAlumno != null && materiaAlumno.isAproboFinal()) {
                    obligatoriasAprobadas.add(materia);
                } else {
                    obligatoriasFaltantes.add(materia);
                }
            }

            // --- Análisis de materias optativas ---
            for (Materia materia : optativas) {
                 MateriasAlumno materiaAlumno = alumnoSeleccionado.getAlumnoMateria(materia);
                 if (materiaAlumno != null && materiaAlumno.isAproboFinal()) {
                     optativasAprobadas.add(materia);
                 } else {
                     optativasFaltantes.add(materia);
                 }
             }

            info.append("\nResumen de Finalización:\n");
            info.append("- Materias obligatorias aprobadas: ").append(obligatoriasAprobadas.size())
                .append(" de ").append(obligatorias.size()).append("\n");

            if (!obligatoriasFaltantes.isEmpty()) {
                info.append("Materias obligatorias pendientes:\n");
                for (Materia m : obligatoriasFaltantes) {
                    info.append("  • ").append(m.getNombre()).append("\n");
                }
            }

            info.append("- Materias optativas aprobadas: ")
                .append(optativasAprobadas.size()).append(" de ").append(optativas.size())
                .append(" (mínimo requerido: ").append(cantOptativasRequeridas).append(")\n");

            if (!optativasAprobadas.isEmpty()) {
                info.append("Materias optativas aprobadas:\n");
                for (Materia m : optativasAprobadas) {
                    info.append("  • ").append(m.getNombre()).append("\n");
                }
            }
            if (!optativasFaltantes.isEmpty()) {
                info.append("Materias optativas pendientes:\n");
                for (Materia m : optativasFaltantes) {
                    info.append("  • ").append(m.getNombre()).append("\n");
                }
            }

            // --- Verificación final ---
             boolean finalizoCarrera = (obligatoriasFaltantes.isEmpty()) && (optativasAprobadas.size() >= cantOptativasRequeridas);

            if (finalizoCarrera) {
                info.append("\n🎓 ¡El alumno ha finalizado la carrera!");
            } else {
                info.append("\n🚧 El alumno aún no ha finalizado la carrera.");
            }

        } else {
            info.append("El alumno no está inscripto en ninguna carrera.\n");
        }

        areaInfoAlumno.setText(info.toString());
    } else {
        JOptionPane.showMessageDialog(this, "Debe seleccionar un alumno.");
    }
}
}
