// CargarNotaPanel.java
package view;

import model.Alumno;
import model.Materia;
import model.MateriasAlumno;

import javax.swing.*;
import java.awt.*;
import java.util.List;

public class CargaNotasPanel extends JPanel {
    public CargaNotasPanel(List<Alumno> alumnos) {
        setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);
        gbc.fill = GridBagConstraints.HORIZONTAL;

        JComboBox<Alumno> cbAlumnos = new JComboBox<>(alumnos.toArray(new Alumno[0]));
        JComboBox<Materia> cbMaterias = new JComboBox<>();
        JTextField tfNota = new JTextField(5);
        JButton btnCargarNotaCursada = new JButton("Cargar Nota Cursada");
        JButton btnCargarNotaFinal = new JButton("Cargar Nota Final");

        // Evento: actualizar materias del historial al cambiar alumno
        cbAlumnos.addActionListener(e -> {
            Alumno seleccionado = (Alumno) cbAlumnos.getSelectedItem();
            cbMaterias.removeAllItems();
            if (seleccionado != null) {
                for (MateriasAlumno ma : seleccionado.getHistorialAcademico()) {
                    cbMaterias.addItem(ma.getMateria());
                }
            }
        });

        // Componentes al panel
        gbc.gridx = 0; gbc.gridy = 0; add(new JLabel("Seleccionar Alumno:"), gbc);
        gbc.gridx = 1; add(cbAlumnos, gbc);
        gbc.gridx = 0; gbc.gridy = 1; add(new JLabel("Seleccionar Materia:"), gbc);
        gbc.gridx = 1; add(cbMaterias, gbc);
        gbc.gridx = 0; gbc.gridy = 2; add(new JLabel("Nota (1-10):"), gbc);
        gbc.gridx = 1; add(tfNota, gbc);
        gbc.gridx = 0; gbc.gridy = 3; add(btnCargarNotaCursada, gbc);
        gbc.gridx = 1; add(btnCargarNotaFinal, gbc);

        // Evento: cargar nota de cursada
        btnCargarNotaCursada.addActionListener(e -> {
            Alumno alumno = (Alumno) cbAlumnos.getSelectedItem();
            Materia materia = (Materia) cbMaterias.getSelectedItem();
            try {
                int nota = Integer.parseInt(tfNota.getText());
                if (alumno != null && materia != null) {
                    MateriasAlumno ma = alumno.getAlumnoMateria(materia);
                    if (ma != null) {
                        ma.aprobarCursada(nota);
                        JOptionPane.showMessageDialog(this, "Nota de cursada cargada correctamente.");
                    } else {
                        JOptionPane.showMessageDialog(this, "La materia no está en el historial del alumno.");
                    }
                }
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(this, "Ingrese una nota válida.");
            }
        });

        // Evento: cargar nota de final
        btnCargarNotaFinal.addActionListener(e -> {
            Alumno alumno = (Alumno) cbAlumnos.getSelectedItem();
            Materia materia = (Materia) cbMaterias.getSelectedItem();
            try {
                int nota = Integer.parseInt(tfNota.getText());
                if (alumno != null && materia != null) {
                    MateriasAlumno ma = alumno.getAlumnoMateria(materia);
                    if (ma != null) {
                        ma.aprobarFinal(nota);
                        JOptionPane.showMessageDialog(this, "Nota de final cargada correctamente.");
                    } else {
                        JOptionPane.showMessageDialog(this, "La materia no está en el historial del alumno.");
                    }
                }
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(this, "Ingrese una nota válida.");
            }
        });

        // Trigger inicial para llenar materias
        cbAlumnos.setSelectedIndex(0);
    }
}
