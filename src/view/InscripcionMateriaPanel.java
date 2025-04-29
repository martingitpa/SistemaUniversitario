package view;

import model.Alumno;
import model.Carrera;
import model.Materia;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;
import model.MateriasAlumno;

public class InscripcionMateriaPanel extends JPanel {
    private JComboBox<Alumno> comboAlumnos;
    private JComboBox<Materia> comboMaterias;
    private JComboBox<Carrera> comboCarreras;
    private JButton btnInscribir;

    private List<Alumno> alumnos;
    private List<Carrera> carreras;
      
    public InscripcionMateriaPanel(List<Alumno> alumnos, List<Carrera> carreras) {
        this.alumnos = alumnos;
        this.carreras = carreras;
        initComponents();
    }

    private void initComponents() {
        setLayout(new BorderLayout());
        setBorder(BorderFactory.createEmptyBorder(40, 40, 40, 40));

        // Título
        JLabel titulo = new JLabel("Inscripción a Materias");
        titulo.setFont(new Font("Arial", Font.BOLD, 22));
        add(titulo, BorderLayout.NORTH); // Colocamos el título en la parte superior del BorderLayout

        // Panel para los formularios
        JPanel formPanel = new JPanel(new GridBagLayout()); // Aquí usamos GridBagLayout para los formularios
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(15, 10, 15, 10);
        gbc.fill = GridBagConstraints.HORIZONTAL;

        // Seleccionar Alumno
        gbc.gridx = 0;
        gbc.gridy = 0;
        formPanel.add(new JLabel("Seleccionar Alumno:"), gbc);
        gbc.gridx = 1;
        comboAlumnos = new JComboBox<>(alumnos.toArray(new Alumno[0]));
        comboAlumnos.addActionListener(e -> actualizarMaterias());
        formPanel.add(comboAlumnos, gbc);

        // Seleccionar Carrera
        gbc.gridx = 0;
        gbc.gridy = 1;
        formPanel.add(new JLabel("Seleccionar Carrera:"), gbc);
        gbc.gridx = 1;
        comboCarreras = new JComboBox<>(carreras.toArray(new Carrera[0]));
        comboCarreras.addActionListener(e -> actualizarMaterias());
        formPanel.add(comboCarreras, gbc);

        // Materias disponibles
        gbc.gridx = 0;
        gbc.gridy = 2;
        formPanel.add(new JLabel("Materias disponibles:"), gbc);
        gbc.gridx = 1;
        comboMaterias = new JComboBox<>();
        formPanel.add(comboMaterias, gbc);

        // Botón de Inscribir
        gbc.gridx = 0;
        gbc.gridy = 3;
        gbc.gridwidth = 2;
        btnInscribir = new JButton("Inscribir");
        btnInscribir.addActionListener(e -> inscribirMateria());
        formPanel.add(btnInscribir, gbc);

        // Agregar el panel de formulario al centro del BorderLayout
        add(formPanel, BorderLayout.CENTER);

        // Si ya hay alumnos, actualizar las materias
        if (comboAlumnos.getItemCount() > 0) {
            actualizarMaterias();
        }
    }

    private void actualizarMaterias() {
        Carrera carreraSeleccionada = (Carrera) comboCarreras.getSelectedItem();
        comboMaterias.removeAllItems();
        if (carreraSeleccionada != null && carreraSeleccionada.getPlanEstudio() != null) {
            List<Materia> disponibles = carreraSeleccionada.getPlanEstudio().getMaterias();
            for (Materia m : disponibles) {
                comboMaterias.addItem(m);
            }
        }
    }

    private void inscribirMateria() {
    Alumno alumno = (Alumno) comboAlumnos.getSelectedItem();
    Materia materia = (Materia) comboMaterias.getSelectedItem();

    if (alumno == null || materia == null) {
        JOptionPane.showMessageDialog(this, "Seleccione un alumno y una materia.", "Error", JOptionPane.ERROR_MESSAGE);
        return;
    }

    // Verificar si ya estaba inscripto a la materia
    MateriasAlumno yaInscripto = alumno.getAlumnoMateria(materia);
    if (yaInscripto != null) {
        JOptionPane.showMessageDialog(this, "El alumno ya está inscripto en esta materia.", "Advertencia", JOptionPane.WARNING_MESSAGE);
        return;
    }
    
    // Validar correlativas
    List<Materia> correlativas = materia.getCorrelativas();
    List<Materia> correlativasPendientes = new ArrayList<>();

    for (Materia correlativa : correlativas) {
        MateriasAlumno estado = alumno.getAlumnoMateria(correlativa);
        if (estado == null || !estado.isAproboFinal()) {
            correlativasPendientes.add(correlativa);
        }
    }

    if (!correlativasPendientes.isEmpty()) {
        StringBuilder mensaje = new StringBuilder("No se puede inscribir. Debe aprobar el final de:\n");
        for (Materia m : correlativasPendientes) {
            mensaje.append(" - ").append(m.getNombre()).append("\n");
        }
        JOptionPane.showMessageDialog(this, mensaje.toString(), "Correlativas pendientes", JOptionPane.WARNING_MESSAGE);
        return;
    }
    
    //Si pasa las correlativas inscribo
    // Crear un nuevo MateriasAlumno para inscribir
    MateriasAlumno nuevaInscripcion = new MateriasAlumno(materia);
    alumno.getHistorialAcademico().add(nuevaInscripcion);

    JOptionPane.showMessageDialog(this, "Inscripción a " + materia.getNombre() + " realizada con éxito para " + alumno.getNombre());
}

}
