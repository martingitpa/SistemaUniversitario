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

        areaInfoAlumno = new JTextArea(10, 30);
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
            // Imprimir la información del alumno y materias inscriptas
            StringBuilder info = new StringBuilder();
            info.append("Alumno: ").append(alumnoSeleccionado.getNombre()).append("\n");
            info.append("Carrera: ").append(alumnoSeleccionado.getCarrera().getNombre()).append("\n");

            // Mostrar las materias que el alumno está cursando
            info.append("Materias Inscriptas:\n");
            List<MateriasAlumno> historial = alumnoSeleccionado.getHistorialAcademico();
            for (MateriasAlumno materiaAlumno : historial) {
                Materia materia = materiaAlumno.getMateria();
                info.append("- ").append(materia.getNombre())
                     .append(" (Cursada Aprobada: ").append(materiaAlumno.isAproboCursada())
                     .append(", Final Aprobado: ").append(materiaAlumno.isAproboFinal()).append(")\n");
            }

            // Verificar si ha finalizado la carrera
            boolean finalizoCarrera = verificarSiFinalizoCarrera(alumnoSeleccionado);

            if (finalizoCarrera) {
                info.append("\n¡El alumno ha finalizado la carrera!");
            } else {
                info.append("\nEl alumno aún no ha finalizado la carrera.");
            }

            // Mostrar la información en el área de texto
            areaInfoAlumno.setText(info.toString());
        } else {
            JOptionPane.showMessageDialog(this, "Debe seleccionar un alumno.");
        }
    }

    private boolean verificarSiFinalizoCarrera(Alumno alumno) {
        Carrera carrera = alumno.getCarrera();
        List<Materia> materiasObligatorias = carrera.getMateriasObligatorias();
        List<Materia> materiasOptativas = carrera.getMateriasOptativas();
        int cantOptativasRequeridas = carrera.getCantidadOptativasRequeridas();

        int materiasObligatoriasAprobadas = 0;
        int materiasOptativasAprobadas = 0;

        // Verificar materias obligatorias aprobadas
        for (Materia materia : materiasObligatorias) {
            MateriasAlumno materiaAlumno = alumno.getAlumnoMateria(materia);
            if (materiaAlumno != null && materiaAlumno.isAproboFinal()) {
                materiasObligatoriasAprobadas++;
            }
        }

        // Verificar materias optativas aprobadas
        for (Materia materia : materiasOptativas) {
            MateriasAlumno materiaAlumno = alumno.getAlumnoMateria(materia);
            if (materiaAlumno != null && materiaAlumno.isAproboFinal()) {
                materiasOptativasAprobadas++;
            }
        }

        // Verificar si cumplió con las condiciones
        return materiasObligatoriasAprobadas == materiasObligatorias.size() &&
               materiasOptativasAprobadas >= cantOptativasRequeridas;
    }
}
