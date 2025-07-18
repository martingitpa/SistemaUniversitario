package view;
import javax.swing.*;
import java.awt.*;
import java.util.List;
import model.Alumno;

public class AltaAlumnoPanel extends JPanel {
    private JTextField txtDni;
    private JTextField txtNombre;
    private JButton btnGuardar;

    private List<Alumno> alumnos; // Lista donde se guarda el alumno creado


    public AltaAlumnoPanel(List<Alumno> alumnos) {
        this.alumnos = alumnos;   
        initComponents();
    }

    private void initComponents() {
        setLayout(new BorderLayout()); 
        setBorder(BorderFactory.createEmptyBorder(40, 40, 40, 40));
        
        JLabel titulo = new JLabel("Alta de Alumnos");
        titulo.setFont(new Font("Arial", Font.BOLD, 22));
        add(titulo, BorderLayout.NORTH);
        
        // Panel interno con GridBagLayout para alinear verticalmente los campos
        JPanel formPanel = new JPanel(new GridBagLayout());
        
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
        btnGuardar = new JButton("Guardar");
        btnGuardar.addActionListener(e -> guardarAlumno());
        formPanel.add(btnGuardar, gbc);
        
        // Agregar el panel al centro (con margen arriba)
        add(formPanel, BorderLayout.CENTER);
    }

    private void guardarAlumno() {
        String dni = txtDni.getText();
        String nombre = txtNombre.getText();

        if (dni.isEmpty() || nombre.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Por favor complete todos los campos.", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        Alumno nuevoAlumno = new Alumno(dni, nombre);
        alumnos.add(nuevoAlumno);

        JOptionPane.showMessageDialog(this, "Alumno creado con éxito.");
        txtDni.setText("");
        txtNombre.setText("");
    }
}
