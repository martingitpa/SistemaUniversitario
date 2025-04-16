package view;

import javax.swing.*;
import java.awt.*;
import java.util.List;
import model.Alumno;
import model.Carrera;

public class MenuPrincipalFrame extends JFrame {
    private List<Alumno> alumnos;
    private List<Carrera> carreras;
    private JPanel panelContenido;
    

    public MenuPrincipalFrame(List<Alumno> alumnos, List<Carrera> carreras) {
        this.alumnos = alumnos;
        this.carreras = carreras;

        setTitle("Sistema Académico - Menú Principal");
        setSize(1200, 700);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setResizable(false);
        
        initComponents();
    }

    private void initComponents() {
    // Panel principal con BorderLayout
    JPanel panelPrincipal = new JPanel(new BorderLayout());

    // Panel del título
    JPanel panelTitulo = new JPanel(new BorderLayout());
    panelTitulo.setBackground(Color.LIGHT_GRAY);
    panelTitulo.setBorder(BorderFactory.createEmptyBorder(40, 52, 10, 10));

    JLabel titulo = new JLabel("Menú Principal", JLabel.LEFT);
    titulo.setFont(new Font("Arial", Font.BOLD, 24));
    panelTitulo.add(titulo, BorderLayout.WEST);

    panelPrincipal.add(panelTitulo, BorderLayout.NORTH);


    // Panel lateral con botones (columna)
    JPanel panelBotones = new JPanel();
    panelBotones.setLayout(new BoxLayout(panelBotones, BoxLayout.Y_AXIS));
    panelBotones.setBorder(BorderFactory.createEmptyBorder(10, 20, 10, 10));
    panelBotones.setBackground (Color.LIGHT_GRAY); 


    // Tamaño fijo para los botones
    Dimension botonSize = new Dimension(250, 40);

    // Botón 1: Alta de Alumno
    JButton btnAltaAlumno = new JButton("Alta de Alumno");
    configurarBoton(btnAltaAlumno, botonSize);
    btnAltaAlumno.addActionListener(e -> mostrarPanel(new AltaAlumnoPanel(alumnos, carreras)));
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
    btnInscripcionCarrera.addActionListener(e -> mostrarPanel(new InscripcionCarreraPanel(alumnos, carreras)));
    panelBotones.add(btnInscripcionCarrera);
    panelBotones.add(Box.createVerticalStrut(10));

    // Botón 5: Inscripción a Materia
    JButton btnInscripcionMateria = new JButton("Inscripción a Materia");
    configurarBoton(btnInscripcionMateria, botonSize);
    //btnInscripcionMateria.addActionListener(e -> mostrarPanel(new InscripcionMateriaPanel(alumnos,carreras)))
    panelBotones.add(btnInscripcionMateria);
    panelBotones.add(Box.createVerticalStrut(10));

    // Botón 6: Verificar Finalización de Carrera
    JButton btnVerificarFinalizacion = new JButton("Verificar Finalización de Carrera");
    configurarBoton(btnVerificarFinalizacion, botonSize);
    panelBotones.add(btnVerificarFinalizacion);

    try {
        // Cargar y escalar imagen
        ImageIcon icono = new ImageIcon(getClass().getResource("/resources/logo.jpg"));
        Image imagen = icono.getImage().getScaledInstance(150, 150, Image.SCALE_SMOOTH);
        icono = new ImageIcon(imagen);
        JLabel imagenLabel = new JLabel(icono);
        imagenLabel.setBorder(BorderFactory.createEmptyBorder(70, 40, 0, 0));
        panelBotones.add(imagenLabel);
    } catch (Exception ex) {
        System.err.println("No se pudo cargar la imagen: " + ex.getMessage());
    }

    panelPrincipal.add(panelBotones, BorderLayout.WEST);
    
    // Panel central donde se carga el contenido dinámico
    panelContenido = new JPanel();
    panelContenido.setLayout(new BorderLayout());
    panelPrincipal.add(panelContenido, BorderLayout.CENTER);
    
    add(panelPrincipal);
}
private void mostrarPanel(JPanel nuevoPanel) {
        panelContenido.removeAll();
        panelContenido.add(nuevoPanel, BorderLayout.CENTER);
        panelContenido.revalidate();
        panelContenido.repaint();
    }

// Método para configurar estilo común de botones
private void configurarBoton(JButton boton, Dimension size) {
    boton.setAlignmentX(Component.LEFT_ALIGNMENT);
    boton.setMaximumSize(size);
    boton.setPreferredSize(size);
    boton.setMinimumSize(size);
}
}