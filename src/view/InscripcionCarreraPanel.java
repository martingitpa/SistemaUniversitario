package view;

import javax.swing.*;
import java.awt.*;
import java.util.List;
import model.Alumno;
import model.Carrera;

public class InscripcionCarreraPanel extends JPanel {
    private JComboBox<Alumno> comboAlumnos;
    private JComboBox<Carrera> comboCarreras;
    private JButton btnInscribir;

    public InscripcionCarreraPanel(List<Alumno> alumnos, List<Carrera> carreras) {
        setLayout(new BorderLayout());
        
        JLabel titulo = new JLabel("Inscripción a Carrera", JLabel.CENTER);
        titulo.setFont(new Font("Arial", Font.BOLD, 24));
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

        // Combo carreras
        gbc.gridx = 0;
        gbc.gridy = 1;
        formPanel.add(new JLabel("Seleccione Carrera:"), gbc);

        comboCarreras = new JComboBox<>(carreras.toArray(new Carrera[0]));
        gbc.gridx = 1;
        formPanel.add(comboCarreras, gbc);

        // Botón inscribir
        btnInscribir = new JButton("Inscribir");
        gbc.gridx = 0;
        gbc.gridy = 2;
        gbc.gridwidth = 2;
        gbc.anchor = GridBagConstraints.CENTER;
        formPanel.add(btnInscribir, gbc);

        add(formPanel, BorderLayout.CENTER);

        // Acción del botón
        btnInscribir.addActionListener(e -> {
            Alumno alumnoSeleccionado = (Alumno) comboAlumnos.getSelectedItem();
            Carrera carreraSeleccionada = (Carrera) comboCarreras.getSelectedItem();

            if (alumnoSeleccionado != null && carreraSeleccionada != null) {
                alumnoSeleccionado.setCarrera(carreraSeleccionada);
                JOptionPane.showMessageDialog(this, "Alumno " + alumnoSeleccionado.getNombre() + " inscrito en la carrera " + carreraSeleccionada.getNombre());
            } else {
                JOptionPane.showMessageDialog(this, "Debe seleccionar un alumno y una carrera.");
            }
        });
    }
}
