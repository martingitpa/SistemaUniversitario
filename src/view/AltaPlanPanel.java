package view;

import javax.swing.*;
import java.awt.*;
import java.util.List;
import model.Materia;
import model.PlanEstudio;
import model.Planes.*;
import model.Strategy;

public class AltaPlanPanel extends JPanel {
    private JTextField txtNombre;
    private JComboBox<String> comboEstrategias;
    private JButton btnGuardar;
    private JPanel panelMaterias;
    
    private List<PlanEstudio> planesEstudio;
    private List<Materia> materiasDisponibles;
    
    public AltaPlanPanel(List<PlanEstudio> planesEstudio, List<Materia> materiasDisponibles) {
        this.planesEstudio = planesEstudio;
        this.materiasDisponibles = materiasDisponibles;
        initComponents();
    }

    private void initComponents() {
        setLayout(new BorderLayout());
        setBorder(BorderFactory.createEmptyBorder(40, 40, 40, 40));
        
        //Titulo
        JLabel titulo = new JLabel("Alta de Plan de Estudio");
        titulo.setFont(new Font("Arial", Font.BOLD, 22));
        add(titulo, BorderLayout.NORTH);

        //Panel para los formularios
        JPanel formPanel = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);
        gbc.fill = GridBagConstraints.HORIZONTAL;

        gbc.gridx = 0;
        gbc.gridy = 0;
        formPanel.add(new JLabel("Nombre del plan:"), gbc);
        gbc.gridx = 1;
        txtNombre = new JTextField(20);
        formPanel.add(txtNombre, gbc);

        gbc.gridx = 0;
        gbc.gridy = 1;
        formPanel.add(new JLabel("Tipo de plan:"), gbc);
        gbc.gridx = 1;
        comboEstrategias = new JComboBox<>(new String[] { "PlanA", "PlanB", "PlanC","PlanD","PlanE" });
        formPanel.add(comboEstrategias, gbc);

        gbc.gridx = 0;
        gbc.gridy = 2;
        gbc.gridwidth = 2;
        formPanel.add(new JLabel("Materias disponibles:"), gbc);

        gbc.gridy = 3;
        panelMaterias = new JPanel();
        panelMaterias.setLayout(new BoxLayout(panelMaterias, BoxLayout.Y_AXIS));

        // Cargar checkboxes
        for (Materia materia : materiasDisponibles) {
            JCheckBox checkBox = new JCheckBox(materia.getNombre());
            checkBox.putClientProperty("materia", materia);
            panelMaterias.add(checkBox);
        }

        JScrollPane scroll = new JScrollPane(panelMaterias);
        scroll.setPreferredSize(new Dimension(350, 150));
        gbc.gridy = 4;
        formPanel.add(scroll, gbc);

        // Botón
        gbc.gridy = 5;
        btnGuardar = new JButton("Crear Plan");
        formPanel.add(btnGuardar, gbc);

        add(formPanel, BorderLayout.CENTER);

        btnGuardar.addActionListener(e -> guardarPlan());
    }

    private void guardarPlan() {
        String nombre = txtNombre.getText().trim();
        String seleccion = (String) comboEstrategias.getSelectedItem();

        if (nombre.isEmpty() || seleccion == null) {
            JOptionPane.showMessageDialog(this, "Complete todos los campos.", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        // Crear instancia de estrategia
        Strategy estrategia = switch (seleccion) {
            case "PlanA" -> new PlanA();
            case "PlanB" -> new PlanB();
            case "PlanC" -> new PlanC();
            case "PlanD" -> new PlanD();
            case "PlanE" -> new PlanE();
            default -> null;
        };

        if (estrategia == null) {
            JOptionPane.showMessageDialog(this, "Estrategia no válida.", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        //Crear el plan
        PlanEstudio nuevo = new PlanEstudio(nombre, estrategia);
        
        // Agregar materias seleccionadas
        Component[] components = panelMaterias.getComponents();
        for (Component c : components) {
            if (c instanceof JCheckBox checkBox && checkBox.isSelected()) {
                Materia materia = (Materia) checkBox.getClientProperty("materia");
                if (materia != null) {
                    nuevo.agregarMateria(materia);
                }
            }
        }
        
        // Validar que se haya agregado al menos una materia
        if (nuevo.getMaterias().isEmpty()) {
            JOptionPane.showMessageDialog(this, "Debe seleccionar al menos una materia para el plan.", "Advertencia", JOptionPane.WARNING_MESSAGE);
            return;
        }
        
        //Guardo el plan en la lista
        planesEstudio.add(nuevo);

        JOptionPane.showMessageDialog(this, "Plan de estudio creado.");
        txtNombre.setText("");
         
        for (Component c : panelMaterias.getComponents()) {
            if (c instanceof JCheckBox checkBox) {
                checkBox.setSelected(false);
            }
        }
    }
}