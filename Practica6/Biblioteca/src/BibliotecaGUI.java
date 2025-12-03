import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import java.awt.*;
import java.text.SimpleDateFormat;

public class BibliotecaGUI extends JFrame {
    private Biblioteca biblioteca;
    private JTable tablaLibros;
    private JTable tablaPrestamos;
    private DefaultTableModel modelLibros;
    private DefaultTableModel modelPrestamos;

    private static final Color COLOR_PRIMARIO = new Color(52, 73, 94);
    private static final Color COLOR_SECUNDARIO = new Color(46, 125, 50);
    private static final Color COLOR_ACENTO = new Color(25, 118, 210);
    private static final Color COLOR_FONDO = new Color(245, 245, 250);

    public BibliotecaGUI() {
        // PERSISTENCIA: Cargar datos de la biblioteca desde archivo
        biblioteca = PersistenciaBiblioteca.cargarBiblioteca();
        if (biblioteca == null) {
            biblioteca = new Biblioteca("Biblioteca De Informatica", "Av. Arce");
        } else {
            // Actualizar contador de préstamos basado en los préstamos existentes
            int maxId = 0;
            for (Prestamo p : biblioteca.getPrestamos()) {
                String id = p.getIdPrestamo();
                if (id.startsWith("P")) {
                    try {
                        int num = Integer.parseInt(id.substring(1));
                        if (num > maxId) maxId = num;
                    } catch (NumberFormatException e) {
                    }
                }
            }
            biblioteca.setContadorPrestamos(maxId + 1);
        }

        setTitle("Biblioteca");
        setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        setSize(1100, 750);
        setLocationRelativeTo(null);
        getContentPane().setBackground(COLOR_FONDO);

        // PERSISTENCIA: Guardar datos al cerrar la ventana
        addWindowListener(new java.awt.event.WindowAdapter() {
            @Override
            public void windowClosing(java.awt.event.WindowEvent windowEvent) {
                guardarDatos();
                System.exit(0);
            }
        });

        JTabbedPane tabbedPane = new JTabbedPane();
        tabbedPane.setFont(new Font("Segoe UI", Font.BOLD, 13));
        tabbedPane.setBackground(COLOR_FONDO);
        tabbedPane.setForeground(COLOR_PRIMARIO);

        tabbedPane.addTab("Catálogo", crearPanelCatalogo());
        tabbedPane.addTab("Préstamos", crearPanelPrestamos());
        tabbedPane.addTab("Gestión", crearPanelGestion());
        tabbedPane.addTab("Reportes", crearPanelReportes());

        add(tabbedPane);
    }

    // PERSISTENCIA: Método para guardar los datos de la biblioteca
    private void guardarDatos() {
        PersistenciaBiblioteca.guardarBiblioteca(biblioteca);
    }

    private JButton crearBotonEstilizado(String texto, Color color) {
        JButton boton = new JButton(texto);
        boton.setFont(new Font("Segoe UI", Font.BOLD, 12));
        boton.setBackground(color);
        boton.setForeground(Color.WHITE);
        boton.setFocusPainted(false);
        boton.setBorderPainted(false);
        boton.setCursor(new Cursor(Cursor.HAND_CURSOR));
        boton.setPreferredSize(new Dimension(150, 35));

        boton.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                boton.setBackground(color.darker());
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                boton.setBackground(color);
            }
        });

        return boton;
    }

    private void estilizarTabla(JTable tabla) {
        tabla.setFont(new Font("Segoe UI", Font.PLAIN, 11));
        tabla.setRowHeight(25);
        tabla.setSelectionBackground(new Color(220, 235, 255));
        tabla.setSelectionForeground(COLOR_PRIMARIO);
        tabla.setGridColor(new Color(200, 200, 200));
        tabla.setShowGrid(true);

        JTableHeader header = tabla.getTableHeader();
        header.setFont(new Font("Segoe UI", Font.BOLD, 12));
        header.setBackground(COLOR_PRIMARIO);
        header.setForeground(Color.BLACK);
        header.setPreferredSize(new Dimension(header.getWidth(), 30));
    }

    private JPanel crearPanelCatalogo() {
        JPanel panel = new JPanel(new BorderLayout(10, 10));
        panel.setBackground(COLOR_FONDO);
        panel.setBorder(BorderFactory.createEmptyBorder(15, 15, 15, 15));

        JPanel panelBotones = new JPanel(new FlowLayout(FlowLayout.LEFT, 10, 10));
        panelBotones.setBackground(COLOR_FONDO);
        JButton btnAgregarLibro = crearBotonEstilizado("Agregar Libro", COLOR_SECUNDARIO);
        JButton btnBuscarLibro = crearBotonEstilizado("Buscar Libro", COLOR_ACENTO);

        btnAgregarLibro.addActionListener(e -> mostrarDialogoAgregarLibro());
        btnBuscarLibro.addActionListener(e -> mostrarDialogoBuscarLibro());

        panelBotones.add(btnAgregarLibro);
        panelBotones.add(btnBuscarLibro);

        String[] columnas = {"Título", "ISBN", "Autor", "Género", "Año", "Disponible"};
        modelLibros = new DefaultTableModel(columnas, 0) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };
        tablaLibros = new JTable(modelLibros);
        tablaLibros.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        estilizarTabla(tablaLibros);
        JScrollPane scrollPane = new JScrollPane(tablaLibros);
        scrollPane.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createEmptyBorder(5, 0, 0, 0),
                BorderFactory.createLineBorder(new Color(200, 200, 200), 1)
        ));
        scrollPane.setBackground(COLOR_FONDO);

        panel.add(panelBotones, BorderLayout.NORTH);
        panel.add(scrollPane, BorderLayout.CENTER);

        actualizarTablaLibros();
        return panel;
    }

    private JPanel crearPanelPrestamos() {
        JPanel panel = new JPanel(new BorderLayout(10, 10));
        panel.setBackground(COLOR_FONDO);
        panel.setBorder(BorderFactory.createEmptyBorder(15, 15, 15, 15));

        JPanel panelBotones = new JPanel(new FlowLayout(FlowLayout.LEFT, 10, 10));
        panelBotones.setBackground(COLOR_FONDO);
        JButton btnNuevoPrestamo = crearBotonEstilizado("Nuevo Préstamo", COLOR_SECUNDARIO);
        JButton btnDevolver = crearBotonEstilizado("Devolver Libro", new Color(220, 53, 69));

        btnNuevoPrestamo.addActionListener(e -> mostrarDialogoNuevoPrestamo());
        btnDevolver.addActionListener(e -> mostrarDialogoDevolver());

        panelBotones.add(btnNuevoPrestamo);
        panelBotones.add(btnDevolver);

        String[] columnas = {"ID Préstamo", "Estudiante", "Libro", "Fecha Préstamo", "Estado"};
        modelPrestamos = new DefaultTableModel(columnas, 0) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };
        tablaPrestamos = new JTable(modelPrestamos);
        tablaPrestamos.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        estilizarTabla(tablaPrestamos);
        JScrollPane scrollPane = new JScrollPane(tablaPrestamos);
        scrollPane.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createEmptyBorder(5, 0, 0, 0),
                BorderFactory.createLineBorder(new Color(200, 200, 200), 1)
        ));
        scrollPane.setBackground(COLOR_FONDO);

        panel.add(panelBotones, BorderLayout.NORTH);
        panel.add(scrollPane, BorderLayout.CENTER);

        actualizarTablaPrestamos();
        return panel;
    }

    private JPanel crearPanelGestion() {
        JPanel panel = new JPanel(new GridLayout(3, 2, 15, 15));
        panel.setBackground(COLOR_FONDO);
        panel.setBorder(BorderFactory.createEmptyBorder(30, 30, 30, 30));

        JButton btnRegistrarEstudiante = crearBotonEstilizado("Registrar Estudiante", COLOR_ACENTO);
        JButton btnVerAutores = crearBotonEstilizado("Ver Autores", new Color(156, 39, 176));
        JButton btnVerEstudiantes = crearBotonEstilizado("Ver Estudiantes", new Color(156, 39, 176));
        JButton btnAgregarLibro = crearBotonEstilizado("Agregar Libro", COLOR_SECUNDARIO);
        JButton btnInfoBiblioteca = crearBotonEstilizado("Información", new Color(255, 152, 0));

        Dimension botonSize = new Dimension(250, 60);
        btnRegistrarEstudiante.setPreferredSize(botonSize);
        btnVerAutores.setPreferredSize(botonSize);
        btnVerEstudiantes.setPreferredSize(botonSize);
        btnAgregarLibro.setPreferredSize(botonSize);
        btnInfoBiblioteca.setPreferredSize(botonSize);

        btnRegistrarEstudiante.addActionListener(e -> mostrarDialogoRegistrarEstudiante());
        btnVerAutores.addActionListener(e -> mostrarListaAutores());
        btnVerEstudiantes.addActionListener(e -> mostrarListaEstudiantes());
        btnAgregarLibro.addActionListener(e -> mostrarDialogoAgregarLibro());
        btnInfoBiblioteca.addActionListener(e -> mostrarInfoBiblioteca());

        panel.add(btnRegistrarEstudiante);
        panel.add(btnVerAutores);
        panel.add(btnVerEstudiantes);
        panel.add(btnAgregarLibro);
        panel.add(btnInfoBiblioteca);

        return panel;
    }

    private JPanel crearPanelReportes() {
        JPanel panel = new JPanel(new BorderLayout(10, 10));
        panel.setBackground(COLOR_FONDO);
        panel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        JTextArea areaReporte = new JTextArea();
        areaReporte.setEditable(false);
        areaReporte.setFont(new Font("Consolas", Font.PLAIN, 12));
        areaReporte.setBackground(Color.WHITE);
        areaReporte.setForeground(COLOR_PRIMARIO);
        areaReporte.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(new Color(200, 200, 200), 1),
                BorderFactory.createEmptyBorder(10, 10, 10, 10)
        ));
        JScrollPane scrollPane = new JScrollPane(areaReporte);
        scrollPane.setBorder(BorderFactory.createEmptyBorder(5, 0, 0, 0));
        scrollPane.setBackground(COLOR_FONDO);

        JPanel panelBotones = new JPanel(new FlowLayout(FlowLayout.LEFT, 10, 10));
        panelBotones.setBackground(COLOR_FONDO);
        JButton btnGenerarReporte = crearBotonEstilizado("Generar Reporte", COLOR_SECUNDARIO);
        btnGenerarReporte.addActionListener(e -> {
            String reporte = generarReporteCompleto();
            areaReporte.setText(reporte);
        });

        panelBotones.add(btnGenerarReporte);
        panel.add(panelBotones, BorderLayout.NORTH);
        panel.add(scrollPane, BorderLayout.CENTER);

        return panel;
    }

    // AGREGAR: Diálogo para registrar un nuevo estudiante
    private void mostrarDialogoRegistrarEstudiante() {
        JDialog dialog = new JDialog(this, "Registrar Estudiante", true);
        dialog.setSize(450, 330);
        dialog.setLocationRelativeTo(this);
        dialog.getContentPane().setBackground(COLOR_FONDO);

        JPanel panel = new JPanel(new GridBagLayout());
        panel.setBackground(COLOR_FONDO);
        panel.setBorder(BorderFactory.createEmptyBorder(25, 25, 25, 25));
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(8, 8, 8, 8);
        gbc.anchor = GridBagConstraints.WEST;

        JTextField txtNombre = new JTextField(20);
        txtNombre.setFont(new Font("Segoe UI", Font.PLAIN, 12));
        JTextField txtId = new JTextField(20);
        txtId.setFont(new Font("Segoe UI", Font.PLAIN, 12));
        JTextField txtEmail = new JTextField(20);
        txtEmail.setFont(new Font("Segoe UI", Font.PLAIN, 12));
        JTextField txtCarrera = new JTextField(20);
        txtCarrera.setFont(new Font("Segoe UI", Font.PLAIN, 12));

        JLabel lblNombre = new JLabel("Nombre:");
        lblNombre.setFont(new Font("Segoe UI", Font.BOLD, 12));
        lblNombre.setForeground(COLOR_PRIMARIO);
        gbc.gridx = 0; gbc.gridy = 0;
        panel.add(lblNombre, gbc);
        gbc.gridx = 1; gbc.fill = GridBagConstraints.HORIZONTAL;
        panel.add(txtNombre, gbc);

        JLabel lblId = new JLabel("ID Estudiante:");
        lblId.setFont(new Font("Segoe UI", Font.BOLD, 12));
        lblId.setForeground(COLOR_PRIMARIO);
        gbc.gridx = 0; gbc.gridy = 1; gbc.fill = GridBagConstraints.NONE;
        panel.add(lblId, gbc);
        gbc.gridx = 1; gbc.fill = GridBagConstraints.HORIZONTAL;
        panel.add(txtId, gbc);

        JLabel lblEmail = new JLabel("Email:");
        lblEmail.setFont(new Font("Segoe UI", Font.BOLD, 12));
        lblEmail.setForeground(COLOR_PRIMARIO);
        gbc.gridx = 0; gbc.gridy = 2; gbc.fill = GridBagConstraints.NONE;
        panel.add(lblEmail, gbc);
        gbc.gridx = 1; gbc.fill = GridBagConstraints.HORIZONTAL;
        panel.add(txtEmail, gbc);

        JLabel lblCarrera = new JLabel("Carrera:");
        lblCarrera.setFont(new Font("Segoe UI", Font.BOLD, 12));
        lblCarrera.setForeground(COLOR_PRIMARIO);
        gbc.gridx = 0; gbc.gridy = 3; gbc.fill = GridBagConstraints.NONE;
        panel.add(lblCarrera, gbc);
        gbc.gridx = 1; gbc.fill = GridBagConstraints.HORIZONTAL;
        panel.add(txtCarrera, gbc);

        JButton btnGuardar = crearBotonEstilizado("Guardar", COLOR_SECUNDARIO);
        btnGuardar.addActionListener(e -> {
            if (txtNombre.getText().isEmpty() || txtId.getText().isEmpty() ||
                    txtEmail.getText().isEmpty() || txtCarrera.getText().isEmpty()) {
                JOptionPane.showMessageDialog(dialog, "Por favor complete todos los campos",
                        "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }

            Estudiante estudiante = new Estudiante(txtNombre.getText(), txtId.getText(),
                    txtEmail.getText(), txtCarrera.getText());
            estudiante.registrarse();
            biblioteca.agregarEstudiante(estudiante);
            guardarDatos();
            JOptionPane.showMessageDialog(dialog, "Estudiante registrado :\n" +
                    estudiante.obtenerInfoEstudiante(), "Éxito", JOptionPane.INFORMATION_MESSAGE);
            dialog.dispose();
        });

        gbc.gridx = 0; gbc.gridy = 4;
        gbc.gridwidth = 2;
        gbc.fill = GridBagConstraints.NONE;
        gbc.anchor = GridBagConstraints.CENTER;
        gbc.insets = new Insets(15, 8, 8, 8);
        panel.add(btnGuardar, gbc);

        dialog.add(panel);
        dialog.setVisible(true);
    }

    // AGREGAR: Diálogo para agregar un nuevo libro
    private void mostrarDialogoAgregarLibro() {
        JDialog dialog = new JDialog(this, "Agregar Libro", true);
        dialog.setSize(550, 450);
        dialog.setLocationRelativeTo(this);
        dialog.getContentPane().setBackground(COLOR_FONDO);

        JPanel panel = new JPanel(new GridBagLayout());
        panel.setBackground(COLOR_FONDO);
        panel.setBorder(BorderFactory.createEmptyBorder(25, 25, 25, 25));
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(8, 8, 8, 8);
        gbc.anchor = GridBagConstraints.WEST;

        gbc.gridx = 0;
        gbc.gridy = 0;
        JLabel lblTitulo = new JLabel("Título:");
        lblTitulo.setFont(new Font("Segoe UI", Font.BOLD, 12));
        lblTitulo.setForeground(COLOR_PRIMARIO);
        panel.add(lblTitulo, gbc);
        JTextField txtTitulo = new JTextField(20);
        txtTitulo.setFont(new Font("Segoe UI", Font.PLAIN, 12));
        gbc.gridx = 1;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.weightx = 1.0;
        panel.add(txtTitulo, gbc);

        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.fill = GridBagConstraints.NONE;
        gbc.weightx = 0;
        JLabel lblDescripcion = new JLabel("Descripción:");
        lblDescripcion.setFont(new Font("Segoe UI", Font.BOLD, 12));
        lblDescripcion.setForeground(COLOR_PRIMARIO);
        panel.add(lblDescripcion, gbc);
        JTextField txtDescripcion = new JTextField(20);
        txtDescripcion.setFont(new Font("Segoe UI", Font.PLAIN, 12));
        gbc.gridx = 1;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.weightx = 1.0;
        panel.add(txtDescripcion, gbc);

        gbc.gridx = 0;
        gbc.gridy = 2;
        gbc.fill = GridBagConstraints.NONE;
        gbc.weightx = 0;
        JLabel lblGenero = new JLabel("Género:");
        lblGenero.setFont(new Font("Segoe UI", Font.BOLD, 12));
        lblGenero.setForeground(COLOR_PRIMARIO);
        panel.add(lblGenero, gbc);
        JTextField txtGenero = new JTextField(20);
        txtGenero.setFont(new Font("Segoe UI", Font.PLAIN, 12));
        gbc.gridx = 1;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.weightx = 1.0;
        panel.add(txtGenero, gbc);

        gbc.gridx = 0;
        gbc.gridy = 3;
        gbc.fill = GridBagConstraints.NONE;
        gbc.weightx = 0;
        JLabel lblAnio = new JLabel("Año Publicación:");
        lblAnio.setFont(new Font("Segoe UI", Font.BOLD, 12));
        lblAnio.setForeground(COLOR_PRIMARIO);
        panel.add(lblAnio, gbc);
        JTextField txtAnio = new JTextField(20);
        txtAnio.setFont(new Font("Segoe UI", Font.PLAIN, 12));
        gbc.gridx = 1;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.weightx = 1.0;
        panel.add(txtAnio, gbc);

        gbc.gridx = 0;
        gbc.gridy = 4;
        gbc.fill = GridBagConstraints.NONE;
        gbc.weightx = 0;
        JLabel lblNombreAutor = new JLabel("Nombre Autor:");
        lblNombreAutor.setFont(new Font("Segoe UI", Font.BOLD, 12));
        lblNombreAutor.setForeground(COLOR_PRIMARIO);
        panel.add(lblNombreAutor, gbc);
        JTextField txtNombreAutor = new JTextField(20);
        txtNombreAutor.setFont(new Font("Segoe UI", Font.PLAIN, 12));
        gbc.gridx = 1;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.weightx = 1.0;
        panel.add(txtNombreAutor, gbc);

        gbc.gridx = 0;
        gbc.gridy = 5;
        gbc.fill = GridBagConstraints.NONE;
        gbc.weightx = 0;
        JLabel lblNacionalidad = new JLabel("Nacionalidad Autor:");
        lblNacionalidad.setFont(new Font("Segoe UI", Font.BOLD, 12));
        lblNacionalidad.setForeground(COLOR_PRIMARIO);
        panel.add(lblNacionalidad, gbc);
        JTextField txtNacionalidad = new JTextField(20);
        txtNacionalidad.setFont(new Font("Segoe UI", Font.PLAIN, 12));
        gbc.gridx = 1;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.weightx = 1.0;
        panel.add(txtNacionalidad, gbc);

        JButton btnGuardar = crearBotonEstilizado("Guardar", COLOR_SECUNDARIO);
        gbc.gridx = 0;
        gbc.gridy = 6;
        gbc.gridwidth = 2;
        gbc.fill = GridBagConstraints.NONE;
        gbc.weightx = 0;
        gbc.anchor = GridBagConstraints.CENTER;
        gbc.insets = new Insets(15, 8, 8, 8);
        panel.add(btnGuardar, gbc);

        btnGuardar.addActionListener(e -> {
            if (txtTitulo.getText().isEmpty() || txtNombreAutor.getText().isEmpty()) {
                JOptionPane.showMessageDialog(dialog, "Complete los campos obligatorios (Título y Nombre Autor)",
                        "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }

            try {
                int anio = Integer.parseInt(txtAnio.getText());

                String idAutor = "A" + System.currentTimeMillis();
                String isbn = "ISBN" + System.currentTimeMillis();

                Autor autor = new Autor(txtNombreAutor.getText(), idAutor,
                        txtNacionalidad.getText().isEmpty() ? "Desconocida" : txtNacionalidad.getText());
                Libro libro = new Libro(txtTitulo.getText(), isbn,
                        txtDescripcion.getText(), txtGenero.getText(), anio, autor);

                autor.registrarLibro(libro, biblioteca);
                actualizarTablaLibros();
                guardarDatos();
                JOptionPane.showMessageDialog(dialog, "Libro agregado ",
                        "Éxito", JOptionPane.INFORMATION_MESSAGE);
                dialog.dispose();
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(dialog, "El año debe ser un número válido",
                        "Error", JOptionPane.ERROR_MESSAGE);
            }
        });

        dialog.add(panel);
        dialog.setVisible(true);
    }

    private void mostrarDialogoBuscarLibro() {
        String titulo = JOptionPane.showInputDialog(this, "Ingrese el título del libro a buscar:",
                "Buscar Libro", JOptionPane.QUESTION_MESSAGE);

        if (titulo != null && !titulo.isEmpty()) {
            Libro libro = biblioteca.buscarLibro(titulo);
            if (libro != null) {
                JOptionPane.showMessageDialog(this, libro.obtenerInfoLibro(),
                        "Libro Encontrado", JOptionPane.INFORMATION_MESSAGE);
            } else {
                JOptionPane.showMessageDialog(this, "Libro no encontrado",
                        "Error", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    // AGREGAR: Diálogo para crear un nuevo préstamo
    private void mostrarDialogoNuevoPrestamo() {
        JDialog dialog = new JDialog(this, "Nuevo Préstamo", true);
        dialog.setSize(450, 280);
        dialog.setLocationRelativeTo(this);
        dialog.getContentPane().setBackground(COLOR_FONDO);

        JPanel panel = new JPanel(new GridBagLayout());
        panel.setBackground(COLOR_FONDO);
        panel.setBorder(BorderFactory.createEmptyBorder(25, 25, 25, 25));
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(8, 8, 8, 8);
        gbc.anchor = GridBagConstraints.WEST;

        JTextField txtNombreEstudiante = new JTextField(20);
        txtNombreEstudiante.setFont(new Font("Segoe UI", Font.PLAIN, 12));
        JTextField txtIdEstudiante = new JTextField(20);
        txtIdEstudiante.setFont(new Font("Segoe UI", Font.PLAIN, 12));
        JTextField txtTituloLibro = new JTextField(20);
        txtTituloLibro.setFont(new Font("Segoe UI", Font.PLAIN, 12));

        JLabel lblNombreEstudiante = new JLabel("Nombre Estudiante:");
        lblNombreEstudiante.setFont(new Font("Segoe UI", Font.BOLD, 12));
        lblNombreEstudiante.setForeground(COLOR_PRIMARIO);
        gbc.gridx = 0; gbc.gridy = 0;
        panel.add(lblNombreEstudiante, gbc);
        gbc.gridx = 1; gbc.fill = GridBagConstraints.HORIZONTAL;
        panel.add(txtNombreEstudiante, gbc);

        JLabel lblIdEstudiante = new JLabel("ID Estudiante:");
        lblIdEstudiante.setFont(new Font("Segoe UI", Font.BOLD, 12));
        lblIdEstudiante.setForeground(COLOR_PRIMARIO);
        gbc.gridx = 0; gbc.gridy = 1; gbc.fill = GridBagConstraints.NONE;
        panel.add(lblIdEstudiante, gbc);
        gbc.gridx = 1; gbc.fill = GridBagConstraints.HORIZONTAL;
        panel.add(txtIdEstudiante, gbc);

        JLabel lblTituloLibro = new JLabel("Título del Libro:");
        lblTituloLibro.setFont(new Font("Segoe UI", Font.BOLD, 12));
        lblTituloLibro.setForeground(COLOR_PRIMARIO);
        gbc.gridx = 0; gbc.gridy = 2; gbc.fill = GridBagConstraints.NONE;
        panel.add(lblTituloLibro, gbc);
        gbc.gridx = 1; gbc.fill = GridBagConstraints.HORIZONTAL;
        panel.add(txtTituloLibro, gbc);

        JButton btnPrestar = crearBotonEstilizado("Realizar Préstamo", COLOR_SECUNDARIO);
        btnPrestar.addActionListener(e -> {
            if (txtNombreEstudiante.getText().isEmpty() || txtIdEstudiante.getText().isEmpty() ||
                    txtTituloLibro.getText().isEmpty()) {
                JOptionPane.showMessageDialog(dialog, "Por favor complete todos los campos",
                        "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }

            Libro libro = biblioteca.buscarLibro(txtTituloLibro.getText());
            if (libro == null) {
                JOptionPane.showMessageDialog(dialog, "Libro no encontrado",
                        "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }

            if (!libro.isDisponible()) {
                JOptionPane.showMessageDialog(dialog, "El libro no está disponible",
                        "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }

            Estudiante estudiante = new Estudiante(txtNombreEstudiante.getText(),
                    txtIdEstudiante.getText(), "", "");
            Prestamo prestamo = estudiante.solicitarPrestamo(libro, biblioteca);

            if (prestamo != null) {
                SimpleDateFormat sdfFecha = new SimpleDateFormat("dd/MM/yyyy");
                SimpleDateFormat sdfHora = new SimpleDateFormat("HH:mm:ss");
                String fecha = sdfFecha.format(prestamo.getFechaPrestamo());
                String hora = sdfHora.format(prestamo.getFechaPrestamo());

                String mensaje = "Préstamo realizado exitosamente\n\n" +
                        "ID Préstamo: " + prestamo.getIdPrestamo() + "\n" +
                        "Fecha: " + fecha + "\n" +
                        "Hora: " + hora;

                biblioteca.agregarEstudiante(estudiante);
                guardarDatos();
                JOptionPane.showMessageDialog(dialog, mensaje, "Éxito", JOptionPane.INFORMATION_MESSAGE);
                actualizarTablaPrestamos();
                actualizarTablaLibros();
                dialog.dispose();
            }
        });

        gbc.gridx = 0; gbc.gridy = 3;
        gbc.gridwidth = 2;
        gbc.fill = GridBagConstraints.NONE;
        gbc.anchor = GridBagConstraints.CENTER;
        gbc.insets = new Insets(15, 8, 8, 8);
        panel.add(btnPrestar, gbc);

        dialog.add(panel);
        dialog.setVisible(true);
    }

    // ELIMINAR: Diálogo para devolver un libro prestado
    private void mostrarDialogoDevolver() {
        String idPrestamo = JOptionPane.showInputDialog(this,
                "Ingrese el ID del préstamo a devolver:", "Devolver Libro", JOptionPane.QUESTION_MESSAGE);

        if (idPrestamo != null && !idPrestamo.isEmpty()) {
            Prestamo prestamo = buscarPrestamoPorId(idPrestamo);
            if (prestamo != null && prestamo.isActivo()) {
                biblioteca.procesarDevolucion(prestamo);
                prestamo.getLibro().marcarDisponible();
                guardarDatos();
                JOptionPane.showMessageDialog(this, "Libro devuelto exitosamente",
                        "Éxito", JOptionPane.INFORMATION_MESSAGE);
                actualizarTablaPrestamos();
                actualizarTablaLibros();
            } else {
                JOptionPane.showMessageDialog(this, "Préstamo no encontrado o ya devuelto",
                        "Error", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    private Prestamo buscarPrestamoPorId(String id) {
        for (Prestamo p : biblioteca.getPrestamos()) {
            if (p.getIdPrestamo().equals(id)) {
                return p;
            }
        }
        return null;
    }

    private void actualizarTablaLibros() {
        modelLibros.setRowCount(0);
        for (Libro libro : biblioteca.getCatalogo()) {
            modelLibros.addRow(new Object[]{
                    libro.getTitulo(),
                    libro.getIsbn(),
                    libro.getAutor().getNombre(),
                    libro.getGenero(),
                    libro.getAñoPublicacion(),
                    libro.isDisponible() ? "Sí" : "No"
            });
        }
    }

    private void actualizarTablaPrestamos() {
        modelPrestamos.setRowCount(0);
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        for (Prestamo prestamo : biblioteca.getPrestamos()) {
            modelPrestamos.addRow(new Object[]{
                    prestamo.getIdPrestamo(),
                    prestamo.getEstudiante().getNombre(),
                    prestamo.getLibro().getTitulo(),
                    sdf.format(prestamo.getFechaPrestamo()),
                    prestamo.isActivo() ? "Disponible" : "Devuelto"
            });
        }
    }

    private void mostrarListaAutores() {
        JDialog dialog = new JDialog(this, "Lista de Autores", true);
        dialog.setSize(600, 400);
        dialog.setLocationRelativeTo(this);
        dialog.getContentPane().setBackground(COLOR_FONDO);

        JPanel panel = new JPanel(new BorderLayout(10, 10));
        panel.setBackground(COLOR_FONDO);
        panel.setBorder(BorderFactory.createEmptyBorder(15, 15, 15, 15));

        String[] columnas = {"Nombre", "Nacionalidad", "ID Autor"};
        DefaultTableModel modelAutores = new DefaultTableModel(columnas, 0) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };

        JTable tablaAutores = new JTable(modelAutores);
        estilizarTabla(tablaAutores);

        java.util.Set<Autor> autoresUnicos = new java.util.HashSet<>();
        for (Libro libro : biblioteca.getCatalogo()) {
            if (libro.getAutor() != null) {
                autoresUnicos.add(libro.getAutor());
            }
        }
        for (Autor autor : biblioteca.getAutores()) {
            autoresUnicos.add(autor);
        }

        for (Autor autor : autoresUnicos) {
            modelAutores.addRow(new Object[]{
                    autor.getNombre(),
                    autor.getNacionalidad(),
                    autor.getIdAutor() != null ? autor.getIdAutor() : "N/A"
            });
        }

        JScrollPane scrollPane = new JScrollPane(tablaAutores);
        scrollPane.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createEmptyBorder(5, 0, 0, 0),
                BorderFactory.createLineBorder(new Color(200, 200, 200), 1)
        ));

        JLabel lblTitulo = new JLabel("Total de autores: " + autoresUnicos.size());
        lblTitulo.setFont(new Font("Segoe UI", Font.BOLD, 12));
        lblTitulo.setForeground(COLOR_PRIMARIO);

        panel.add(lblTitulo, BorderLayout.NORTH);
        panel.add(scrollPane, BorderLayout.CENTER);

        dialog.add(panel);
        dialog.setVisible(true);
    }

    private void mostrarListaEstudiantes() {
        JDialog dialog = new JDialog(this, "Lista de Estudiantes", true);
        dialog.setSize(700, 450);
        dialog.setLocationRelativeTo(this);
        dialog.getContentPane().setBackground(COLOR_FONDO);

        JPanel panel = new JPanel(new BorderLayout(10, 10));
        panel.setBackground(COLOR_FONDO);
        panel.setBorder(BorderFactory.createEmptyBorder(15, 15, 15, 15));

        String[] columnas = {"Código", "Nombre", "Email", "Carrera"};
        DefaultTableModel modelEstudiantes = new DefaultTableModel(columnas, 0) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };

        JTable tablaEstudiantes = new JTable(modelEstudiantes);
        estilizarTabla(tablaEstudiantes);

        for (Estudiante estudiante : biblioteca.getEstudiantes()) {
            modelEstudiantes.addRow(new Object[]{
                    estudiante.getIdEstudiante(),
                    estudiante.getNombre(),
                    estudiante.getEmail() != null && !estudiante.getEmail().isEmpty() ? estudiante.getEmail() : "N/A",
                    estudiante.getCarrera() != null && !estudiante.getCarrera().isEmpty() ? estudiante.getCarrera() : "N/A"
            });
        }

        JScrollPane scrollPane = new JScrollPane(tablaEstudiantes);
        scrollPane.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createEmptyBorder(5, 0, 0, 0),
                BorderFactory.createLineBorder(new Color(200, 200, 200), 1)
        ));

        JLabel lblTitulo = new JLabel("Total de estudiantes: " + biblioteca.getEstudiantes().size());
        lblTitulo.setFont(new Font("Segoe UI", Font.BOLD, 12));
        lblTitulo.setForeground(COLOR_PRIMARIO);

        panel.add(lblTitulo, BorderLayout.NORTH);
        panel.add(scrollPane, BorderLayout.CENTER);

        dialog.add(panel);
        dialog.setVisible(true);
    }

    private void mostrarInfoBiblioteca() {
        String info = "Nombre: " + biblioteca.getNombre() + "\n" +
                "Dirección: " + biblioteca.getDireccion() + "\n" +
                "Total de libros: " + biblioteca.getCatalogo().size() + "\n" +
                "Total de préstamos: " + biblioteca.getPrestamos().size();
        JOptionPane.showMessageDialog(this, info, "Información de la Biblioteca",
                JOptionPane.INFORMATION_MESSAGE);
    }

    private String generarReporteCompleto() {
        StringBuilder reporte = new StringBuilder();
        reporte.append(" REPORTE \n\n");
        reporte.append("Biblioteca: ").append(biblioteca.getNombre()).append("\n");
        reporte.append("Dirección: ").append(biblioteca.getDireccion()).append("\n\n");

        reporte.append(" CATÁLOGO\n");
        reporte.append("Total de libros: ").append(biblioteca.getCatalogo().size()).append("\n\n");
        for (Libro libro : biblioteca.getCatalogo()) {
            reporte.append(libro.obtenerInfoLibro()).append("\n");
        }

        reporte.append("\n=PRÉSTAMOS ===\n");
        reporte.append("Total de préstamos: ").append(biblioteca.getPrestamos().size()).append("\n");
        int activos = 0;
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        for (Prestamo p : biblioteca.getPrestamos()) {
            if (p.isActivo()) {
                activos++;
                reporte.append("Préstamo activo: ").append(p.getIdPrestamo())
                        .append(" - ").append(p.getLibro().getTitulo())
                        .append(" - ").append(p.getEstudiante().getNombre())
                        .append(" - Fecha: ").append(sdf.format(p.getFechaPrestamo())).append("\n");
            }
        }
        reporte.append("Préstamos activos: ").append(activos).append("\n");

        return reporte.toString();
    }
}