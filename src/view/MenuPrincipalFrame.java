package view;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.List;
import model.Alumno;
import model.Carrera;

public class MenuPrincipalFrame extends JFrame {
    private List<Alumno> alumnos;
    private List<Carrera> carreras;
    

    public MenuPrincipalFrame(List<Alumno> alumnos, List<Carrera> carreras) {
        this.alumnos = alumnos;
        this.carreras = carreras;

        setTitle("Sistema Académico - Menú Principal");
        setSize(1200, 700);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        initComponents();
    }

    private void initComponents() {
    // Panel principal con BorderLayout
    JPanel panelPrincipal = new JPanel(new BorderLayout());

    // Título
    JLabel titulo = new JLabel("Menú Principal", JLabel.LEFT);
    titulo.setFont(new Font("Arial", Font.BOLD, 20));
    titulo.setBorder(BorderFactory.createEmptyBorder(20, 20, 10, 10));
    panelPrincipal.add(titulo, BorderLayout.NORTH);

    // Panel lateral con botones (columna)
    JPanel panelBotones = new JPanel();
    panelBotones.setLayout(new BoxLayout(panelBotones, BoxLayout.Y_AXIS));
    panelBotones.setBorder(BorderFactory.createEmptyBorder(10, 20, 10, 10));

    // Tamaño fijo para los botones
    Dimension botonSize = new Dimension(250, 40);

    // Botón 1: Alta de Alumno
    JButton btnAltaAlumno = new JButton("Alta de Alumno");
    configurarBoton(btnAltaAlumno, botonSize);
    btnAltaAlumno.addActionListener(e -> {
        new AltaAlumnoFrame(alumnos, carreras).setVisible(true);
    });
    panelBotones.add(btnAltaAlumno);
    panelBotones.add(Box.createVerticalStrut(10));

    // Botón 2: Alta de Carreras
    JButton btnAltaCarrera = new JButton("Alta de Carreras");
    configurarBoton(btnAltaCarrera, botonSize);
    panelBotones.add(btnAltaCarrera);
    panelBotones.add(Box.createVerticalStrut(10));

    // Botón 3: Alta de Planes
    JButton btnAltaPlanes = new JButton("Alta de Planes");
    configurarBoton(btnAltaPlanes, botonSize);
    panelBotones.add(btnAltaPlanes);
    panelBotones.add(Box.createVerticalStrut(10));

    // Botón 4: Inscripción a Carrera
    JButton btnInscripcionCarrera = new JButton("Inscripción a Carrera");
    configurarBoton(btnInscripcionCarrera, botonSize);
    panelBotones.add(btnInscripcionCarrera);
    panelBotones.add(Box.createVerticalStrut(10));

    // Botón 5: Inscripción a Materia
    JButton btnInscripcionMateria = new JButton("Inscripción a Materia");
    configurarBoton(btnInscripcionMateria, botonSize);
    panelBotones.add(btnInscripcionMateria);
    panelBotones.add(Box.createVerticalStrut(10));

    // Botón 6: Verificar Finalización de Carrera
    JButton btnVerificarFinalizacion = new JButton("Verificar Finalización de Carrera");
    configurarBoton(btnVerificarFinalizacion, botonSize);
    panelBotones.add(btnVerificarFinalizacion);

    panelPrincipal.add(panelBotones, BorderLayout.WEST);
    add(panelPrincipal);
}

// Método para configurar estilo común de botones
private void configurarBoton(JButton boton, Dimension size) {
    boton.setAlignmentX(Component.LEFT_ALIGNMENT);
    boton.setMaximumSize(size);
    boton.setPreferredSize(size);
    boton.setMinimumSize(size);
}
}



