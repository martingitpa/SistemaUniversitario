package view;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.List;
import model.Alumno;
import model.Carrera;

public class AltaAlumnoFrame extends JFrame {
    private JTextField txtDni;
    private JTextField txtNombre;
    private JComboBox<Carrera> comboCarreras;
    private JButton btnGuardar;

    private List<Alumno> alumnos; // Lista donde se guarda el alumno creado
    private List<Carrera> carreras; // Lista de carreras disponibles

    public AltaAlumnoFrame(List<Alumno> alumnos, List<Carrera> carreras) {
        this.alumnos = alumnos;
        this.carreras = carreras;

        setTitle("Alta de Alumno");
        setSize(400, 250);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);

        initComponents();
    }

    private void initComponents() {
        JPanel panel = new JPanel(new GridLayout(4, 2, 10, 10));

        panel.add(new JLabel("DNI:"));
        txtDni = new JTextField();
        panel.add(txtDni);

        panel.add(new JLabel("Nombre:"));
        txtNombre = new JTextField();
        panel.add(txtNombre);

        panel.add(new JLabel("Carrera:"));
        comboCarreras = new JComboBox<>();
        for (Carrera carrera : carreras) {
            comboCarreras.addItem(carrera);
        }
        panel.add(comboCarreras);

        btnGuardar = new JButton("Guardar");
        btnGuardar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                guardarAlumno();
            }
        });

        panel.add(new JLabel()); // espacio vacío
        panel.add(btnGuardar);

        add(panel);
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
        dispose(); // Cierra la ventana
    }
}
