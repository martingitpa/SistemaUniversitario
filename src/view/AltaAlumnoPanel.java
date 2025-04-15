package view;
import javax.swing.*;
import java.awt.*;
import java.util.List;
import model.Alumno;
import model.Carrera;

public class AltaAlumnoPanel extends JPanel {
    private JTextField txtDni;
    private JTextField txtNombre;
    private JComboBox<Carrera> comboCarreras;
    private JButton btnGuardar;

    private List<Alumno> alumnos; // Lista donde se guarda el alumno creado
    private List<Carrera> carreras; // Lista de carreras disponibles

    public AltaAlumnoPanel(List<Alumno> alumnos, List<Carrera> carreras) {
        this.alumnos = alumnos;
        this.carreras = carreras;
        
        initComponents();
    }

    private void initComponents() {
        setLayout(new BorderLayout()); // Establece layout centrado
        
        // Panel interno con GridBagLayout para alinear verticalmente los campos
        JPanel formPanel = new JPanel(new GridBagLayout());
        formPanel.setBorder(BorderFactory.createEmptyBorder(40, 0, 0, 0)); // Espacio arriba
        
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10); // Espaciado entre componentes
        gbc.anchor = GridBagConstraints.CENTER;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.gridx = 0;
        gbc.gridy = 0;

        formPanel.add(new JLabel("DNI:"), gbc);
        gbc.gridy++;
        txtDni = new JTextField(15);
        formPanel.add(txtDni, gbc);

        gbc.gridy++;
        formPanel.add(new JLabel("Nombre:"), gbc);
        gbc.gridy++;
        txtNombre = new JTextField(15);
        formPanel.add(txtNombre, gbc);

        gbc.gridy++;
        formPanel.add(new JLabel("Carrera:"), gbc);
        gbc.gridy++;
        comboCarreras = new JComboBox<>();
        for (Carrera carrera : carreras) {
            comboCarreras.addItem(carrera);
        }
        formPanel.add(comboCarreras, gbc);

        gbc.gridy++;
        btnGuardar = new JButton("Guardar");
        btnGuardar.addActionListener(e -> guardarAlumno());
        formPanel.add(btnGuardar, gbc);
        
        // Agregar el panel al centro (con margen arriba)
        add(formPanel, BorderLayout.NORTH);
    }

    private void guardarAlumno() {
        String dni = txtDni.getText();
        String nombre = txtNombre.getText();
        Carrera carreraSeleccionada = (Carrera) comboCarreras.getSelectedItem();

        if (dni.isEmpty() || nombre.isEmpty() || carreraSeleccionada == null) {
            JOptionPane.showMessageDialog(this, "Por favor complete todos los campos.", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        Alumno nuevoAlumno = new Alumno(dni, nombre);
        nuevoAlumno.setCarrera(carreraSeleccionada);
        alumnos.add(nuevoAlumno);

        JOptionPane.showMessageDialog(this, "Alumno creado con éxito.");
        txtDni.setText("");
        txtNombre.setText("");
    }
}
