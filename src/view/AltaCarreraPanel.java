package view;

import javax.swing.*;
import java.awt.*;
import java.util.List;
import model.Carrera;
import model.Materia;
import model.PlanEstudio;

public class AltaCarreraPanel extends JPanel {
    private JTextField txtNombre;
    private JComboBox<PlanEstudio> comboPlanes;
    private JSpinner spinnerCantOptativas;
    private JButton btnGuardar;

    private JLabel lblCantidadMaterias;
    private JTextArea areaMaterias;
    
    private List<Carrera> carreras;
    private List<PlanEstudio> planEstudio;

    public AltaCarreraPanel(List<Carrera> carreras, List<PlanEstudio> planesEstudio) {
        this.carreras = carreras;
        this.planEstudio = planesEstudio;

        initComponents();
    }

    private void initComponents() {
        setLayout(new BorderLayout());
        setBorder(BorderFactory.createEmptyBorder(40, 40, 40, 40));

        JLabel titulo = new JLabel("Alta de Carrera");
        titulo.setFont(new Font("Arial", Font.BOLD, 22));
        add(titulo, BorderLayout.NORTH);

        JPanel formPanel = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(15, 10, 15, 10);
        gbc.fill = GridBagConstraints.HORIZONTAL;

        gbc.gridx = 0;
        gbc.gridy = 0;
        formPanel.add(new JLabel("Nombre de la carrera:"), gbc);
        gbc.gridx = 1;
        txtNombre = new JTextField(20);
        formPanel.add(txtNombre, gbc);

        gbc.gridx = 0;
        gbc.gridy = 1;
        formPanel.add(new JLabel("Plan de estudios:"), gbc);
        gbc.gridx = 1;
        comboPlanes = new JComboBox<>(planEstudio.toArray(new PlanEstudio[0]));
        formPanel.add(comboPlanes, gbc);
        
         // Info dinámica del plan
        gbc.gridx = 0;
        gbc.gridy = 2;
        formPanel.add(new JLabel("Materias obligatorias:"), gbc);
        gbc.gridx = 1;
        lblCantidadMaterias = new JLabel("0");
        formPanel.add(lblCantidadMaterias, gbc);

        gbc.gridx = 0;
        gbc.gridy = 3;
        gbc.gridwidth = 2;
        areaMaterias = new JTextArea(5, 30);
        areaMaterias.setEditable(false);
        JScrollPane scroll = new JScrollPane(areaMaterias);
        formPanel.add(scroll, gbc);
        gbc.gridwidth = 1;

        gbc.gridx = 0;
        gbc.gridy = 4;
        formPanel.add(new JLabel("Cantidad de materias optativas requeridas:"), gbc);
        gbc.gridx = 1;
        spinnerCantOptativas = new JSpinner(new SpinnerNumberModel(0, 0, 20, 1));
        formPanel.add(spinnerCantOptativas, gbc);

        gbc.gridx = 0;
        gbc.gridy = 5;
        gbc.gridwidth = 2;
        gbc.anchor = GridBagConstraints.CENTER;
        btnGuardar = new JButton("Crear Carrera");
        formPanel.add(btnGuardar, gbc);

        add(formPanel, BorderLayout.CENTER);

        btnGuardar.addActionListener(e -> crearCarrera());
        comboPlanes.addActionListener(e -> actualizarVistaPlan());

        if (comboPlanes.getItemCount() > 0) {
            actualizarVistaPlan(); // cargar al iniciar
        }
    }

      private void actualizarVistaPlan() {
        PlanEstudio seleccionado = (PlanEstudio) comboPlanes.getSelectedItem();
        if (seleccionado == null) return;

        List<Materia> materias = seleccionado.getMaterias();

        int obligatorias = 0;
        StringBuilder lista = new StringBuilder();
        for (Materia m : materias) {
            lista.append("- ").append(m.getNombre());
            if (m.esObligatoria()) {
                obligatorias++;
                lista.append(" (Obligatoria)");
            } else {
                lista.append(" (Optativa)");
            }
            lista.append("\n");
        }

        lblCantidadMaterias.setText(String.valueOf(obligatorias));  // Mostramos solo las obligatorias
        areaMaterias.setText(lista.toString());
    }

      
    private void crearCarrera() {
        String nombre = txtNombre.getText().trim();
        PlanEstudio plan = (PlanEstudio) comboPlanes.getSelectedItem();
        int cantOptativas = (int) spinnerCantOptativas.getValue();

        if (nombre.isEmpty() || plan == null) {
            JOptionPane.showMessageDialog(this, "Complete todos los campos.", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        Carrera nuevaCarrera = new Carrera(nombre, plan, cantOptativas);
        carreras.add(nuevaCarrera);

        JOptionPane.showMessageDialog(this, "Carrera creada con éxito.");
        txtNombre.setText("");
        spinnerCantOptativas.setValue(0);
    }
}
