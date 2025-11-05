import javax.swing.*;
import javax.swing.border.TitledBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

public class SistemaContratacion extends JFrame {
    private Empresa empresa;
    private List<Empleado> solicitudes; // Lista de solicitudes pendientes
    private DefaultListModel<Empleado> modeloLista;
    private JList<Empleado> listaSolicitudes;
    private JTextArea areaDetalles;
    
    // Componentes del formulario
    private JTextField campoNombre;
    private JTextField campoPuesto;
    private JTextField campoCiudad;
    private JTextField campoDireccion;
    private JTextField campoNumero;
    
    public SistemaContratacion() {
        // Inicializar empresa y lista de solicitudes
        empresa = new Empresa("Mi Empresa");
        solicitudes = new ArrayList<Empleado>();
        modeloLista = new DefaultListModel<Empleado>();
        
        // Configurar ventana principal
        setTitle("Sistema de Contratación de Personal");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(900, 700);
        setLocationRelativeTo(null); // Centrar en pantalla
        setResizable(true); // Permitir redimensionar
        setMinimumSize(new Dimension(800, 600)); // Tamaño mínimo
        
        // Crear panel principal con pestañas
        JTabbedPane pestañas = new JTabbedPane();
        
        // Panel 1: Formulario para candidatos
        pestañas.addTab("Formulario de Solicitud", crearPanelFormulario());
        
        // Panel 2: Vista de la empresa
        pestañas.addTab("Gestión de Empleados", crearPanelEmpresa());
        
        add(pestañas);
    }
    
    // Crear panel del formulario para candidatos
    private JPanel crearPanelFormulario() {
        JPanel panel = new JPanel(new BorderLayout());
        panel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
        
        // Panel de título
        JLabel titulo = new JLabel("Formulario de Solicitud de Empleo", SwingConstants.CENTER);
        titulo.setFont(new Font("Arial", Font.BOLD, 20));
        titulo.setBorder(BorderFactory.createEmptyBorder(0, 0, 20, 0));
        panel.add(titulo, BorderLayout.NORTH);
        
        // Panel de formulario
        JPanel panelFormulario = new JPanel(new GridBagLayout());
        panelFormulario.setBorder(BorderFactory.createTitledBorder(
            BorderFactory.createEtchedBorder(), 
            "Complete sus datos", 
            TitledBorder.LEFT, 
            TitledBorder.TOP,
            new Font("Arial", Font.BOLD, 14)
        ));
        
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);
        gbc.anchor = GridBagConstraints.WEST;
        
        // Nombre
        gbc.gridx = 0; gbc.gridy = 0;
        panelFormulario.add(new JLabel("Nombre completo:"), gbc);
        gbc.gridx = 1;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.weightx = 1.0;
        campoNombre = new JTextField(20);
        panelFormulario.add(campoNombre, gbc);
        
        // Puesto
        gbc.gridx = 0; gbc.gridy = 1;
        gbc.fill = GridBagConstraints.NONE;
        gbc.weightx = 0;
        panelFormulario.add(new JLabel("Puesto deseado:"), gbc);
        gbc.gridx = 1;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.weightx = 1.0;
        campoPuesto = new JTextField(20);
        panelFormulario.add(campoPuesto, gbc);
        
        // Dirección - Ciudad
        gbc.gridx = 0; gbc.gridy = 2;
        gbc.fill = GridBagConstraints.NONE;
        gbc.weightx = 0;
        panelFormulario.add(new JLabel("Ciudad:"), gbc);
        gbc.gridx = 1;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.weightx = 1.0;
        campoCiudad = new JTextField(20);
        panelFormulario.add(campoCiudad, gbc);
        
        // Dirección - Calle
        gbc.gridx = 0; gbc.gridy = 3;
        gbc.fill = GridBagConstraints.NONE;
        gbc.weightx = 0;
        panelFormulario.add(new JLabel("Calle/Avenida:"), gbc);
        gbc.gridx = 1;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.weightx = 1.0;
        campoDireccion = new JTextField(20);
        panelFormulario.add(campoDireccion, gbc);
        
        // Dirección - Número
        gbc.gridx = 0; gbc.gridy = 4;
        gbc.fill = GridBagConstraints.NONE;
        gbc.weightx = 0;
        panelFormulario.add(new JLabel("Número:"), gbc);
        gbc.gridx = 1;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.weightx = 1.0;
        campoNumero = new JTextField(20);
        panelFormulario.add(campoNumero, gbc);
        
        // Botón de envío
        gbc.gridx = 0; gbc.gridy = 5;
        gbc.gridwidth = 2;
        gbc.fill = GridBagConstraints.NONE;
        gbc.anchor = GridBagConstraints.CENTER;
        gbc.insets = new Insets(20, 10, 10, 10);
        JButton btnEnviar = new JButton("Enviar Solicitud");
        btnEnviar.setFont(new Font("Arial", Font.BOLD, 14));
        btnEnviar.setPreferredSize(new Dimension(200, 40));
        btnEnviar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                enviarSolicitud();
            }
        });
        panelFormulario.add(btnEnviar, gbc);
        
        panel.add(panelFormulario, BorderLayout.CENTER);
        
        return panel;
    }
    
    // Crear panel de gestión para la empresa
    private JPanel crearPanelEmpresa() {
        JPanel panel = new JPanel(new BorderLayout());
        panel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
        
        // Panel de título
        JLabel titulo = new JLabel("Gestión de Solicitudes y Empleados", SwingConstants.CENTER);
        titulo.setFont(new Font("Arial", Font.BOLD, 20));
        titulo.setBorder(BorderFactory.createEmptyBorder(0, 0, 20, 0));
        panel.add(titulo, BorderLayout.NORTH);
        
        // Panel central con lista y detalles
        JPanel panelCentral = new JPanel(new BorderLayout(10, 10));
        
        // Panel izquierdo: Lista de solicitudes
        JPanel panelLista = new JPanel(new BorderLayout());
        panelLista.setBorder(BorderFactory.createTitledBorder(
            BorderFactory.createEtchedBorder(),
            "Solicitudes Recibidas (" + solicitudes.size() + ")",
            TitledBorder.LEFT,
            TitledBorder.TOP
        ));
        
        listaSolicitudes = new JList<Empleado>(modeloLista);
        listaSolicitudes.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        listaSolicitudes.setCellRenderer(new DefaultListCellRenderer() {
            @Override
            public Component getListCellRendererComponent(JList<?> list, Object value, int index,
                    boolean isSelected, boolean cellHasFocus) {
                super.getListCellRendererComponent(list, value, index, isSelected, cellHasFocus);
                if (value instanceof Empleado) {
                    Empleado emp = (Empleado) value;
                    setText(emp.getNombre() + " - " + emp.getPuesto());
                }
                return this;
            }
        });
        listaSolicitudes.addListSelectionListener(e -> mostrarDetalles());
        
        JScrollPane scrollLista = new JScrollPane(listaSolicitudes);
        scrollLista.setPreferredSize(new Dimension(300, 400));
        panelLista.add(scrollLista, BorderLayout.CENTER);
        
        // Panel derecho: Detalles y acciones
        JPanel panelDerecho = new JPanel(new BorderLayout());
        panelDerecho.setBorder(BorderFactory.createTitledBorder(
            BorderFactory.createEtchedBorder(),
            "Detalles de la Solicitud",
            TitledBorder.LEFT,
            TitledBorder.TOP
        ));
        
        areaDetalles = new JTextArea(15, 30);
        areaDetalles.setEditable(false);
        areaDetalles.setFont(new Font("Monospaced", Font.PLAIN, 12));
        areaDetalles.setText("Seleccione una solicitud de la lista para ver los detalles.");
        JScrollPane scrollDetalles = new JScrollPane(areaDetalles);
        panelDerecho.add(scrollDetalles, BorderLayout.CENTER);
        
        // Panel de botones
        JPanel panelBotones = new JPanel(new FlowLayout());
        JButton btnContratar = new JButton("✓ Contratar");
        btnContratar.setFont(new Font("Arial", Font.BOLD, 12));
        btnContratar.setForeground(new Color(0, 128, 0));
        btnContratar.addActionListener(e -> contratarSeleccionado());
        
        JButton btnRechazar = new JButton("✗ Rechazar");
        btnRechazar.setFont(new Font("Arial", Font.BOLD, 12));
        btnRechazar.setForeground(new Color(200, 0, 0));
        btnRechazar.addActionListener(e -> rechazarSeleccionado());
        
        panelBotones.add(btnContratar);
        panelBotones.add(btnRechazar);
        panelDerecho.add(panelBotones, BorderLayout.SOUTH);
        
        panelCentral.add(panelLista, BorderLayout.WEST);
        panelCentral.add(panelDerecho, BorderLayout.CENTER);
        
        // Panel inferior: Empleados contratados
        JPanel panelEmpleados = new JPanel(new BorderLayout());
        panelEmpleados.setBorder(BorderFactory.createTitledBorder(
            BorderFactory.createEtchedBorder(),
            "Empleados Contratados (" + empresa.getCantidadEmpleados() + ")",
            TitledBorder.LEFT,
            TitledBorder.TOP
        ));
        
        JTextArea areaEmpleados = new JTextArea(8, 50);
        areaEmpleados.setEditable(false);
        areaEmpleados.setFont(new Font("Monospaced", Font.PLAIN, 11));
        actualizarAreaEmpleados(areaEmpleados);
        JScrollPane scrollEmpleados = new JScrollPane(areaEmpleados);
        panelEmpleados.add(scrollEmpleados, BorderLayout.CENTER);
        
        // Actualizar área de empleados cuando se contraten
        btnContratar.addActionListener(e -> actualizarAreaEmpleados(areaEmpleados));
        
        panel.add(panelCentral, BorderLayout.CENTER);
        panel.add(panelEmpleados, BorderLayout.SOUTH);
        
        return panel;
    }
    
    // Método para enviar solicitud
    private void enviarSolicitud() {
        // Validar campos
        if (campoNombre.getText().trim().isEmpty() ||
            campoPuesto.getText().trim().isEmpty() ||
            campoCiudad.getText().trim().isEmpty() ||
            campoDireccion.getText().trim().isEmpty() ||
            campoNumero.getText().trim().isEmpty()) {
            JOptionPane.showMessageDialog(this,
                "Por favor, complete todos los campos.",
                "Campos Incompletos",
                JOptionPane.WARNING_MESSAGE);
            return;
        }
        
        // Validar número
        int numero;
        try {
            numero = Integer.parseInt(campoNumero.getText().trim());
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this,
                "El número de dirección debe ser un valor numérico.",
                "Error de Validación",
                JOptionPane.ERROR_MESSAGE);
            return;
        }
        
        // Crear empleado
        Empleado nuevoEmpleado = new Empleado(
            campoNombre.getText().trim(),
            campoPuesto.getText().trim(),
            campoCiudad.getText().trim(),
            campoDireccion.getText().trim(),
            numero
        );
        
        // Agregar a la lista de solicitudes
        solicitudes.add(nuevoEmpleado);
        modeloLista.addElement(nuevoEmpleado);
        
        // Limpiar formulario
        campoNombre.setText("");
        campoPuesto.setText("");
        campoCiudad.setText("");
        campoDireccion.setText("");
        campoNumero.setText("");
        
        // Mostrar mensaje de éxito
        JOptionPane.showMessageDialog(this,
            "¡Solicitud enviada exitosamente!\n\n" +
            "Su información ha sido recibida. La empresa revisará su solicitud.",
            "Solicitud Enviada",
            JOptionPane.INFORMATION_MESSAGE);
        
        // Actualizar título de la lista
        actualizarTituloLista();
    }
    
    // Método para mostrar detalles de la solicitud seleccionada
    private void mostrarDetalles() {
        Empleado seleccionado = listaSolicitudes.getSelectedValue();
        if (seleccionado != null) {
            areaDetalles.setText("=== INFORMACIÓN DEL CANDIDATO ===\n\n");
            areaDetalles.append(seleccionado.toString());
        } else {
            areaDetalles.setText("Seleccione una solicitud de la lista para ver los detalles.");
        }
    }
    
    // Método para contratar empleado seleccionado
    private void contratarSeleccionado() {
        Empleado seleccionado = listaSolicitudes.getSelectedValue();
        if (seleccionado == null) {
            JOptionPane.showMessageDialog(this,
                "Por favor, seleccione una solicitud de la lista.",
                "Ninguna Selección",
                JOptionPane.WARNING_MESSAGE);
            return;
        }
        
        int confirmacion = JOptionPane.showConfirmDialog(this,
            "¿Está seguro de contratar a " + seleccionado.getNombre() + "?\n\n" +
            "Puesto: " + seleccionado.getPuesto(),
            "Confirmar Contratación",
            JOptionPane.YES_NO_OPTION,
            JOptionPane.QUESTION_MESSAGE);
        
        if (confirmacion == JOptionPane.YES_OPTION) {
            empresa.contratar(seleccionado);
            solicitudes.remove(seleccionado);
            modeloLista.removeElement(seleccionado);
            
            areaDetalles.setText("Seleccione una solicitud de la lista para ver los detalles.");
            
            JOptionPane.showMessageDialog(this,
                "✓ " + seleccionado.getNombre() + " ha sido contratado(a) exitosamente.",
                "Contratación Exitosa",
                JOptionPane.INFORMATION_MESSAGE);
            
            actualizarTituloLista();
        }
    }
    
    // Método para rechazar solicitud
    private void rechazarSeleccionado() {
        Empleado seleccionado = listaSolicitudes.getSelectedValue();
        if (seleccionado == null) {
            JOptionPane.showMessageDialog(this,
                "Por favor, seleccione una solicitud de la lista.",
                "Ninguna Selección",
                JOptionPane.WARNING_MESSAGE);
            return;
        }
        
        int confirmacion = JOptionPane.showConfirmDialog(this,
            "¿Está seguro de rechazar la solicitud de " + seleccionado.getNombre() + "?",
            "Confirmar Rechazo",
            JOptionPane.YES_NO_OPTION,
            JOptionPane.WARNING_MESSAGE);
        
        if (confirmacion == JOptionPane.YES_OPTION) {
            solicitudes.remove(seleccionado);
            modeloLista.removeElement(seleccionado);
            
            areaDetalles.setText("Seleccione una solicitud de la lista para ver los detalles.");
            
            JOptionPane.showMessageDialog(this,
                "La solicitud de " + seleccionado.getNombre() + " ha sido rechazada.",
                "Solicitud Rechazada",
                JOptionPane.INFORMATION_MESSAGE);
            
            actualizarTituloLista();
        }
    }
    
    // Actualizar título de la lista
    private void actualizarTituloLista() {
        // Este método se llamaría si tuviéramos referencia al componente
        // Por ahora, la actualización se hace en el panel
    }
    
    // Actualizar área de empleados contratados
    private void actualizarAreaEmpleados(JTextArea area) {
        if (area != null) {
            area.setText("");
            if (empresa.getCantidadEmpleados() == 0) {
                area.setText("No hay empleados contratados todavía.");
            } else {
                area.append("=== LISTA DE EMPLEADOS CONTRATADOS ===\n\n");
                List<Empleado> empleados = empresa.getEmpleados();
                for (int i = 0; i < empleados.size(); i++) {
                    area.append("Empleado #" + (i + 1) + ":\n");
                    area.append(empleados.get(i).toString());
                    area.append("\n\n---\n\n");
                }
            }
        }
    }
    
    // Método principal
    public static void main(String[] args) {
        System.out.println("Iniciando Sistema de Contratacion...");
        
        // Configurar look and feel
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (Exception e) {
            System.err.println("Error al configurar el look and feel: " + e.getMessage());
            // Continuar sin el look and feel personalizado
        }
        
        // Crear y mostrar ventana
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                try {
                    SistemaContratacion ventana = new SistemaContratacion();
                    ventana.setVisible(true);
                    ventana.toFront(); // Traer la ventana al frente
                    ventana.requestFocus(); // Solicitar foco
                    System.out.println("✓ Ventana del sistema de contratacion abierta correctamente.");
                } catch (Exception e) {
                    System.err.println("Error al crear la ventana: " + e.getMessage());
                    e.printStackTrace();
                    JOptionPane.showMessageDialog(null, 
                        "Error al iniciar la aplicacion: " + e.getMessage(),
                        "Error",
                        JOptionPane.ERROR_MESSAGE);
                }
            }
        });
    }
}

