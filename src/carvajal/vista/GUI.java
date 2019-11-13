package carvajal.vista;

import carvajal.controlador.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.print.*;
import static java.awt.print.Printable.*;
import java.io.File;
import java.sql.*;
import java.util.logging.*;
import javax.swing.*;
import javax.swing.border.*;

public class GUI extends JFrame implements Printable {

    private static final long serialVersionUID = 1L;
    private JPanel contentPane, panelpie;
    JMenuBar menuBar;
    JMenu mnCargarDatos, mnGenerarGraficas;
    JMenuItem mntmClientes, mntmComprasAProveedores, mntmVentasDeEmpaques, mntmVentasDePropal, mntmVentasDeMepal, mntmVentasDeSoluciones,
            mntmClientesGraf, mntmVentasGraf, mntmComprasGraf;
    JPanel panelgraficas;
    JLabel lblFiltros, lblClientes, lblVentas, lblComprasAProveedores, lbllogo, lblCopyrightBy, copy;
    JSeparator separator, separator_1, separator_2, separator_3, separator_4, separator_5;
    JRadioButton rbProducto, rbSucursal, rbCategoria,
            rbClientes, rbFecha, rbTipo, rbGenero, rbMes, rbAo;
    ButtonGroup bgClientes,bgVentas;
    JComboBox cbVentas, cbClientes, cbProducto, cbSucursal, cbCProveedor;
    JButton programer, btnAplicarFiltros, jbPDF;
    JCheckBox jckPrecio,jckCosto;
    JToggleButton tgbClientes, tgbVentas, tgbCompras;
    boolean x1 = true, x2 = true, x3 = true;

    public GUI() {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(25, 25, 1005, 691);
        setResizable(false);
        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);
        contentPane.setLayout(null);
        Color c = UIManager.getLookAndFeel().getDefaults().getColor("Panel.background");

        menuBar = new JMenuBar();
        menuBar.setBorderPainted(false);
        menuBar.setBackground(new Color(95, 158, 160));
        menuBar.setBounds(0, 0, 999, 50);
        contentPane.add(menuBar);

        mnCargarDatos = new JMenu("Cargar Datos");
        mnCargarDatos.setForeground(Color.WHITE);
        mnCargarDatos.setFont(new Font("Segoe UI", Font.PLAIN, 20));
        menuBar.add(mnCargarDatos);
        
        mntmClientes = new JMenuItem("Clientes");
        mntmClientes.setFont(new Font("Segoe UI", Font.PLAIN, 16));
        mnCargarDatos.add(mntmClientes);
        mntmClientes.addActionListener((ActionEvent e) -> {
            JFileChooser jc = new JFileChooser();
            int res = jc.showOpenDialog(null);
            if (res == JFileChooser.APPROVE_OPTION) {
                File archivo = jc.getSelectedFile();                
                DB_Clientes f = new DB_Clientes(archivo.getAbsolutePath());
                f.guardar();
                if (!f.error) {
                    mntmClientes.setEnabled(false);
                    mntmVentasDeEmpaques.setEnabled(true);
                    mntmVentasDePropal.setEnabled(true);
                    mntmVentasDeMepal.setEnabled(true);
                    mntmVentasDeSoluciones.setEnabled(true);
                }
            } else {
                mntmClientes.setEnabled(true);
                mntmVentasDeEmpaques.setEnabled(false);
                mntmVentasDePropal.setEnabled(false);
                mntmVentasDeMepal.setEnabled(false);
                mntmVentasDeSoluciones.setEnabled(false);
            }
            llenarclientes();
        });

        mntmComprasAProveedores = new JMenuItem("Compras a Proveedores");
        mntmComprasAProveedores.setFont(new Font("Segoe UI", Font.PLAIN, 16));
        mnCargarDatos.add(mntmComprasAProveedores);
        mntmComprasAProveedores.addActionListener((ActionEvent e) -> {
            JFileChooser jc = new JFileChooser();
            int res = jc.showOpenDialog(null);
            if (res == JFileChooser.APPROVE_OPTION) {

                File archivo = jc.getSelectedFile();
                DB_Compra_proveedor f = new DB_Compra_proveedor(archivo.getAbsolutePath());
                f.guardar();
            } else {
                JOptionPane.showMessageDialog(null, "Cancelo la acción");
            }
        });

        mntmVentasDeEmpaques = new JMenuItem("Ventas de empaques");
        mntmVentasDeEmpaques.setFont(new Font("Segoe UI", Font.PLAIN, 16));
        mnCargarDatos.add(mntmVentasDeEmpaques);
        mntmVentasDeEmpaques.addActionListener((ActionEvent e) -> {
            JFileChooser jc = new JFileChooser();
            int res = jc.showOpenDialog(null);
            if (res == JFileChooser.APPROVE_OPTION) {

                File archivo = jc.getSelectedFile();
                DB_Ventas_empaques f = new DB_Ventas_empaques(archivo.getAbsolutePath());
                f.guardar();
            } else {
                JOptionPane.showMessageDialog(null, "Cancelo la acción");
            }
        });

        mntmVentasDePropal = new JMenuItem("Ventas de propal");
        mntmVentasDePropal.setFont(new Font("Segoe UI", Font.PLAIN, 16));
        mnCargarDatos.add(mntmVentasDePropal);
        mntmVentasDePropal.addActionListener((ActionEvent e) -> {
            JFileChooser jc = new JFileChooser();
            int res = jc.showOpenDialog(null);
            if (res == JFileChooser.APPROVE_OPTION) {

                File archivo = jc.getSelectedFile();
                DB_Ventas_propal f = new DB_Ventas_propal(archivo.getAbsolutePath());
                f.guardar();
            } else {
                JOptionPane.showMessageDialog(null, "Cancelo la acción");
            }
        });

        mntmVentasDeMepal = new JMenuItem("Ventas de mepal");
        mntmVentasDeMepal.setFont(new Font("Segoe UI", Font.PLAIN, 16));
        mnCargarDatos.add(mntmVentasDeMepal);
        mntmVentasDeMepal.addActionListener((ActionEvent e) -> {
            JFileChooser jc = new JFileChooser();
            int res = jc.showOpenDialog(null);
            if (res == JFileChooser.APPROVE_OPTION) {

                File archivo = jc.getSelectedFile();
                DB_Ventas_mepal f = new DB_Ventas_mepal(archivo.getAbsolutePath());
                f.guardar();
            } else {
                JOptionPane.showMessageDialog(null, "Cancelo la acción");
            }
        });

        mntmVentasDeSoluciones = new JMenuItem("Ventas de soluciones y comunicaciones");
        mntmVentasDeSoluciones.setFont(new Font("Segoe UI", Font.PLAIN, 16));
        mnCargarDatos.add(mntmVentasDeSoluciones);
        mntmVentasDeSoluciones.addActionListener((ActionEvent e) -> {
            JFileChooser jc = new JFileChooser();
            int res = jc.showOpenDialog(null);
            if (res == JFileChooser.APPROVE_OPTION) {

                File archivo = jc.getSelectedFile();
                DB_Ventas_sol_com f = new DB_Ventas_sol_com(archivo.getAbsolutePath());
                f.guardar();
            } else {
                JOptionPane.showMessageDialog(null, "Cancelo la acción");
            }
        });

        Conexion con = new Conexion();
        boolean error = con.conectarMySQL("carvajal", "root", "", "localhost");//"127.0.0.1"
        if (!error) {
            ResultSet rs = con.consulta("SELECT * FROM cliente");
            int cant = con.getSizeQuery(rs);
            if (cant > 0) {
                mntmClientes.setEnabled(false);
                mntmVentasDeEmpaques.setEnabled(true);
                mntmVentasDePropal.setEnabled(true);
                mntmVentasDeMepal.setEnabled(true);
                mntmVentasDeSoluciones.setEnabled(true);
            } else {
                mntmClientes.setEnabled(true);
                mntmVentasDeEmpaques.setEnabled(false);
                mntmVentasDePropal.setEnabled(false);
                mntmVentasDeMepal.setEnabled(false);
                mntmVentasDeSoluciones.setEnabled(false);
            }
        }
        con.cerrarConsulta();
        con.desconectar();

        mnGenerarGraficas = new JMenu("Graficas");
        mnGenerarGraficas.setForeground(Color.WHITE);
        mnGenerarGraficas.setFont(new Font("Segoe UI", Font.PLAIN, 20));
        menuBar.add(mnGenerarGraficas);

        mntmClientesGraf = new JMenuItem("Clientes");
        mntmClientesGraf.setFont(new Font("Segoe UI", Font.PLAIN, 16));
        mnGenerarGraficas.add(mntmClientesGraf);
        mntmClientesGraf.addActionListener((ActionEvent e) -> {
            rbTipo.setEnabled(true);
            rbGenero.setEnabled(true);
            cbVentas.setEnabled(false);
            rbClientes.setEnabled(false);
            cbClientes.setEnabled(false);
            cbProducto.setEnabled(false);
            rbProducto.setEnabled(false);
            jckPrecio.setEnabled(false);
            jckCosto.setEnabled(false);
            rbCategoria.setEnabled(false);
            rbFecha.setEnabled(false);
            rbMes.setEnabled(false);
            rbAo.setEnabled(false);
            cbSucursal.setEnabled(false);
            rbSucursal.setEnabled(false);
            cbCProveedor.setEnabled(false);
            filtroclientes("tipo");
        });

        mntmVentasGraf = new JMenuItem("Ventas");
        mntmVentasGraf.setFont(new Font("Segoe UI", Font.PLAIN, 16));
        mnGenerarGraficas.add(mntmVentasGraf);
        mntmVentasGraf.addActionListener((ActionEvent e) -> {
            llenarproductos(0 + "");
            rbTipo.setEnabled(false);
            rbGenero.setEnabled(false);
            cbVentas.setEnabled(true);
            rbClientes.setEnabled(true);
            cbClientes.setEnabled(true);
            cbProducto.setEnabled(true);
            rbProducto.setEnabled(true);
            jckPrecio.setEnabled(true);
            jckCosto.setEnabled(true);
            rbCategoria.setEnabled(true);
            rbMes.setEnabled(true);
            rbAo.setEnabled(true);
            rbFecha.setEnabled(true);
            cbSucursal.setEnabled(true);
            rbSucursal.setEnabled(true);
            cbCProveedor.setEnabled(false);
            rbProducto.setSelected(true);
            Barras_Productos_VentasEmpaques demo0;
            try {
                panelgraficas.removeAll();
                demo0 = new Barras_Productos_VentasEmpaques("",0);
                panelgraficas.add(demo0.panel);
                panelgraficas.repaint();
            } catch (SQLException ex) {
                Logger.getLogger(GUI.class.getName()).log(Level.SEVERE, null, ex);
            }

        });

        mntmComprasGraf = new JMenuItem("Compras");
        mntmComprasGraf.setFont(new Font("Segoe UI", Font.PLAIN, 16));
        mnGenerarGraficas.add(mntmComprasGraf);
        mntmComprasGraf.addActionListener((ActionEvent e) -> {
            rbTipo.setEnabled(false);
            rbGenero.setEnabled(false);
            cbVentas.setEnabled(false);
            rbClientes.setEnabled(false);
            cbClientes.setEnabled(false);
            rbProducto.setEnabled(false);
            jckPrecio.setEnabled(false);
            jckCosto.setEnabled(false);
            rbCategoria.setEnabled(false);
            cbProducto.setEnabled(false);
            rbFecha.setEnabled(false);
            rbMes.setEnabled(false);
            rbAo.setEnabled(false);
            cbSucursal.setEnabled(false);
            rbSucursal.setEnabled(false);
            cbCProveedor.setEnabled(true);
            Barras_ComprasProveedor demo1;
            try {
                panelgraficas.removeAll();
                demo1 = new Barras_ComprasProveedor("");
                panelgraficas.add(demo1.panel);
                panelgraficas.repaint();
            } catch (SQLException ex) {
                Logger.getLogger(GUI.class.getName()).log(Level.SEVERE, null, ex);
            }

        });

        panelgraficas = new JPanel();
        panelgraficas.setBorder(new LineBorder(new Color(0, 0, 0)));
        panelgraficas.setBounds(0, 50, 760, 512);
        contentPane.add(panelgraficas);

        lblFiltros = new JLabel("               Filtros");
        lblFiltros.setForeground(new Color(0, 0, 128));
        lblFiltros.setBackground(new Color(0, 0, 0));
        lblFiltros.setFont(new Font("Tahoma", Font.PLAIN, 20));
        lblFiltros.setBounds(759, 49, 225, 27);
        contentPane.add(lblFiltros);

        lblClientes = new JLabel(" Clientes");
        lblClientes.setForeground(new Color(0, 128, 0));
        lblClientes.setFont(new Font("Tahoma", Font.PLAIN, 20));
        lblClientes.setBounds(759, 76, 235, 27);
        contentPane.add(lblClientes);

        separator = new JSeparator();
        separator.setBounds(759, 76, 240, 2);
        contentPane.add(separator);

        rbTipo = new JRadioButton(" Tipo de cliente");
        rbTipo.setFont(new Font("Tahoma", Font.PLAIN, 14));
        rbTipo.setBounds(760, 106, 225, 20);
        contentPane.add(rbTipo);

        separator_1 = new JSeparator();
        separator_1.setBounds(759, 101, 240, 2);
        contentPane.add(separator_1);

        rbGenero = new JRadioButton(" Genero");
        rbGenero.setFont(new Font("Tahoma", Font.PLAIN, 14));
        rbGenero.setBounds(760, 130, 225, 20);
        contentPane.add(rbGenero);

        lblVentas = new JLabel(" Ventas");
        lblVentas.setForeground(new Color(255, 102, 0));
        lblVentas.setFont(new Font("Tahoma", Font.PLAIN, 20));
        lblVentas.setBounds(759, 160, 235, 27);
        contentPane.add(lblVentas);

        separator_2 = new JSeparator();
        separator_2.setBounds(759, 185, 240, 2);
        contentPane.add(separator_2);

        separator_3 = new JSeparator();
        separator_3.setBounds(759, 160, 240, 2);
        contentPane.add(separator_3);

        cbVentas = new JComboBox();
        cbVentas.addItem("Empaques");
        cbVentas.addItem("Propal");
        cbVentas.addItem("Mepal");
        cbVentas.addItem("Soluciones y Comunicaciones");
        cbVentas.setFont(new Font("Tahoma", Font.PLAIN, 12));
        cbVentas.setBounds(760, 185, 240, 25);
        contentPane.add(cbVentas);
        cbVentas.addActionListener((ActionEvent e) -> {
            cbProducto.removeAllItems();
            cbSucursal.removeAllItems();
            llenarproductos(cbVentas.getSelectedIndex() + "");

        });

        rbClientes = new JRadioButton(" Cliente");
        rbClientes.setFont(new Font("Tahoma", Font.PLAIN, 12));
        rbClientes.setBounds(760, 220, 225, 20);
        contentPane.add(rbClientes);
        rbClientes.addActionListener((ActionEvent e) -> {
            jckPrecio.setEnabled(false);
            jckCosto.setEnabled(false);
        });

        cbClientes = new JComboBox();
        cbClientes.setFont(new Font("Tahoma", Font.PLAIN, 12));
        cbClientes.setBounds(760, 240, 240, 20);
        contentPane.add(cbClientes);

        rbProducto = new JRadioButton("Producto");
        rbProducto.setFont(new Font("Tahoma", Font.PLAIN, 12));
        rbProducto.setBounds(760, 260, 80, 20);
        contentPane.add(rbProducto);
        rbProducto.addActionListener((ActionEvent e) -> {
            jckPrecio.setEnabled(true);
            jckCosto.setEnabled(true);
        });
        
        jckPrecio = new JCheckBox("+$");
        jckPrecio.setFont(new Font("Tahoma", Font.PLAIN, 12));
        jckPrecio.setBounds(840, 260, 40, 20);
        contentPane.add(jckPrecio);
        jckPrecio.addActionListener((ActionEvent e) -> {
            jckCosto.setSelected(false);
        });
        
        jckCosto = new JCheckBox("-$");
        jckCosto.setFont(new Font("Tahoma", Font.PLAIN, 12));
        jckCosto.setBounds(890, 260, 80, 20);
        contentPane.add(jckCosto);
        jckCosto.addActionListener((ActionEvent e) -> {
            jckPrecio.setSelected(false);
        });
                     
        cbProducto = new JComboBox();
        cbProducto.setFont(new Font("Tahoma", Font.PLAIN, 12));
        cbProducto.setBounds(760, 280, 240, 20);
        contentPane.add(cbProducto);
        cbProducto.addActionListener((ActionEvent e) -> {
            /*if(cbProducto.getSelectedIndex()==0){
                jckPrecio.setEnabled(true);
                jckCosto.setEnabled(true);
            }
            else{
                jckPrecio.setEnabled(false);
                jckPrecio.setSelected(false);
                jckCosto.setEnabled(false);
                jckCosto.setSelected(false);
            }*/
        });
        
        rbCategoria = new JRadioButton("Categoria");
        rbCategoria.setFont(new Font("Tahoma", Font.PLAIN, 12));
        rbCategoria.setBounds(760, 300, 80, 20);
        contentPane.add(rbCategoria);
        rbCategoria.addActionListener((ActionEvent e) -> {
            jckPrecio.setEnabled(false);
            jckCosto.setEnabled(false);
        });

        rbFecha = new JRadioButton("Todas las fechas");
        rbFecha.setFont(new Font("Tahoma", Font.PLAIN, 12));
        rbFecha.setBounds(760, 320, 150, 20);
        contentPane.add(rbFecha);
        rbFecha.addActionListener((ActionEvent e) -> {
            jckPrecio.setEnabled(false);
            jckCosto.setEnabled(false);
        });
        
        rbMes = new JRadioButton("Mes");
        rbMes.setFont(new Font("Tahoma", Font.PLAIN, 12));
        rbMes.setBounds(760, 340, 50, 20);
        contentPane.add(rbMes);
        rbMes.addActionListener((ActionEvent e) -> {
            jckPrecio.setEnabled(false);
            jckCosto.setEnabled(false);
        });
        
        rbAo = new JRadioButton("A\u00F1o");
        rbAo.setFont(new Font("Tahoma", Font.PLAIN, 12));
        rbAo.setBounds(810, 340, 80, 20);
        contentPane.add(rbAo);
        rbAo.addActionListener((ActionEvent e) -> {
            jckPrecio.setEnabled(false);
            jckCosto.setEnabled(false);
        });
        
        rbSucursal = new JRadioButton("Sucursal");
        rbSucursal.setFont(new Font("Tahoma", Font.PLAIN, 12));
        rbSucursal.setBounds(760, 360, 80, 20);
        contentPane.add(rbSucursal);
        rbSucursal.addActionListener((ActionEvent e) -> {
            jckPrecio.setEnabled(false);
            jckCosto.setEnabled(false);
        });

        cbSucursal = new JComboBox();
        cbSucursal.setFont(new Font("Tahoma", Font.PLAIN, 12));
        cbSucursal.setBounds(760, 380, 240, 20);
        contentPane.add(cbSucursal);

        lblComprasAProveedores = new JLabel(" Compras a proveedores");
        lblComprasAProveedores.setForeground(new Color(153, 51, 153));
        lblComprasAProveedores.setFont(new Font("Tahoma", Font.PLAIN, 20));
        lblComprasAProveedores.setBounds(759, 436, 235, 27);
        contentPane.add(lblComprasAProveedores);

        cbCProveedor = new JComboBox();
        cbCProveedor.setFont(new Font("Tahoma", Font.PLAIN, 12));
        cbCProveedor.setBounds(760, 484, 240, 25);
        contentPane.add(cbCProveedor);
        cbCProveedor.addItem("Proveedores");
        cbCProveedor.addItem("Productos");

        separator_4 = new JSeparator();
        separator_4.setBounds(759, 462, 240, 2);
        contentPane.add(separator_4);

        separator_5 = new JSeparator();
        separator_5.setBounds(759, 437, 253, 2);
        contentPane.add(separator_5);

        panelpie = new JPanel();
        panelpie.setBorder(new LineBorder(new Color(0, 0, 0)));
        panelpie.setBounds(0, 561, 760, 100);
        contentPane.add(panelpie);
        panelpie.setLayout(null);

        lbllogo = new JLabel("");
        lbllogo.setIcon(new ImageIcon(this.getClass().getResource("/carvajal/img/carvajal-logo.png")));
        lbllogo.setBounds(0, 0, 600, 100);
        panelpie.add(lbllogo);

        jbPDF = new JButton("");
        jbPDF.setBackground(new Color(c.getRed(), c.getGreen(), c.getBlue()));
        jbPDF.setBorder(BorderFactory.createBevelBorder(0));
        jbPDF.setBounds(835, 575, 70, 70);
        jbPDF.setIcon(new ImageIcon(this.getClass().getResource("/carvajal/img/pdf.png")));
        contentPane.add(jbPDF);
        jbPDF.addActionListener((ActionEvent e) -> {
            imprimir();
        });

        lblCopyrightBy = new JLabel(" Copyright 2017 by Santiago Romero Andrade");
        lblCopyrightBy.setFont(new Font("Summit Regular", Font.PLAIN, 8));
        lblCopyrightBy.setBounds(10, 86, 319, 14);
        panelpie.add(lblCopyrightBy);

        bgVentas = new ButtonGroup();
        bgVentas.add(rbCategoria);
        bgVentas.add(rbProducto);
        bgVentas.add(rbSucursal);
        bgVentas.add(rbClientes);
        bgVentas.add(rbAo);
        bgVentas.add(rbMes);
        bgVentas.add(rbFecha);
        
        bgClientes = new ButtonGroup();
        bgClientes.add(rbGenero);
        bgClientes.add(rbTipo);

        copy = new JLabel("\u00A9");
        copy.setBounds(0, 85, 23, 14);
        panelpie.add(copy);

        programer = new JButton("");
        programer.setBackground(new Color(c.getRed(), c.getGreen(), c.getBlue()));
        programer.setIcon(new ImageIcon(this.getClass().getResource("/carvajal/img/csv.png")));
        programer.setBorder(null);
        programer.setBounds(277, 79, 20, 20);
        panelpie.add(programer);
        programer.addActionListener((ActionEvent e) -> {
            Programer dialog = new Programer();
            dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
            dialog.setVisible(true);
        });
        btnAplicarFiltros = new JButton("Aplicar Filtros");
        btnAplicarFiltros.setBackground(new Color(102, 205, 170));
        btnAplicarFiltros.setFont(new Font("Tahoma", Font.PLAIN, 14));
        btnAplicarFiltros.setBounds(770, 525, 220, 30);
        btnAplicarFiltros.setBorder(BorderFactory.createBevelBorder(0));
        contentPane.add(btnAplicarFiltros);

        rbTipo.setSelected(true);

        btnAplicarFiltros.addActionListener((ActionEvent e) -> {

            if (rbFecha.isSelected() && rbFecha.isEnabled()) {
                filtroTodasFechas();
            }
            if (rbMes.isSelected() && rbMes.isEnabled()) {
                filtroTodosMeses();
            }
            if (rbAo.isSelected() && rbAo.isEnabled()) {
                filtroTodosAos();
            }
            if (rbTipo.isSelected() && rbTipo.isEnabled()) {
                filtroclientes("tipo");
            }
            if (rbGenero.isSelected() && rbGenero.isEnabled()) {
                filtroclientes("genero");
            }
            
            
            if (cbProducto.getSelectedIndex() == 0 && rbProducto.isSelected() && rbProducto.isEnabled() && !jckPrecio.isSelected() && !jckCosto.isSelected()) {
                int dinero=0;
                filtroproductos(dinero);
            }
            if (cbProducto.getSelectedIndex() == 0 && rbProducto.isSelected() && rbProducto.isEnabled() && jckPrecio.isSelected()) {
                int dinero=1;
                filtroproductos(dinero);
            }
            if (cbProducto.getSelectedIndex() == 0 && rbProducto.isSelected() && rbProducto.isEnabled() && jckCosto.isSelected()) {
                int dinero=2;
                filtroproductos(dinero);
            }
            
            
            if (rbCategoria.isSelected() && rbCategoria.isEnabled()) {
                filtrocategorias();
            }
            if (cbSucursal.getSelectedIndex() == 0 && rbSucursal.isSelected() && cbProducto.isEnabled()) {
                filtrosucursal();
            }
            if (cbCProveedor.getSelectedItem().equals("Proveedores") && cbCProveedor.isEnabled()) {
                Barras_ComprasProveedor demo1;
                try {
                    panelgraficas.removeAll();
                    demo1 = new Barras_ComprasProveedor("");
                    panelgraficas.add(demo1.panel);
                    panelgraficas.repaint();
                } catch (SQLException ex) {
                    Logger.getLogger(GUI.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            if (cbCProveedor.getSelectedItem().equals("Productos") && cbCProveedor.isEnabled()) {
                Barras_ComprasProductos demo1;
                try {
                    panelgraficas.removeAll();
                    demo1 = new Barras_ComprasProductos("");
                    panelgraficas.add(demo1.panel);
                    panelgraficas.repaint();
                } catch (SQLException ex) {
                    Logger.getLogger(GUI.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            
            if (rbProducto.isSelected() && rbProducto.isEnabled() && cbProducto.getSelectedIndex() != 0 && !jckPrecio.isSelected() && !jckCosto.isSelected()) {
                int dinero=0;
                try {
                    filtrarProductoPorFecha(dinero);
                } catch (SQLException ex) {
                    Logger.getLogger(GUI.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            if (rbProducto.isSelected() && rbProducto.isEnabled() && cbProducto.getSelectedIndex() != 0 && jckPrecio.isSelected()) {
                int dinero=1;
                try {
                    filtrarProductoPorFecha(dinero);
                } catch (SQLException ex) {
                    Logger.getLogger(GUI.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            if (rbProducto.isSelected() && rbProducto.isEnabled() && cbProducto.getSelectedIndex() != 0 && jckCosto.isSelected()) {
                int dinero=2;
                try {
                    filtrarProductoPorFecha(dinero);
                } catch (SQLException ex) {
                    Logger.getLogger(GUI.class.getName()).log(Level.SEVERE, null, ex);
                }
            }

            if (rbClientes.isEnabled() && rbClientes.isSelected()) {
                try {
                    filtrarPorCadaClientes();
                } catch (SQLException ex) {
                    Logger.getLogger(GUI.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            if (rbSucursal.isEnabled() && rbSucursal.isSelected() && cbSucursal.getSelectedIndex() != 0) {
                try {
                    filtrarSucursalPorFecha();
                } catch (SQLException ex) {
                    Logger.getLogger(GUI.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });

        tgbClientes = new JToggleButton("");
        tgbClientes.setIcon(new ImageIcon(this.getClass().getResource("/carvajal/img/flechita.png")));
        tgbClientes.setBackground(new Color(c.getRed(), c.getGreen(), c.getBlue()));
        tgbClientes.setBorder(null);
        tgbClientes.setBounds(979, 79, 20, 20);
        contentPane.add(tgbClientes);
        tgbClientes.addActionListener((ActionEvent e) -> {
            x1 = x1 == false;
            rbTipo.setVisible(x1);
            rbGenero.setVisible(x1);
            rbTipo.setVisible(x1);
            rbGenero.setVisible(x1);
        });

        tgbVentas = new JToggleButton("");
        tgbVentas.setIcon(new ImageIcon(this.getClass().getResource("/carvajal/img/flechita.png")));
        tgbVentas.setBounds(979, 165, 20, 20);
        tgbVentas.setBackground(new Color(c.getRed(), c.getGreen(), c.getBlue()));
        tgbVentas.setBorder(null);
        contentPane.add(tgbVentas);
        tgbVentas.addActionListener((ActionEvent e) -> {
            x2 = x2 == false;
            cbVentas.setVisible(x2);
            rbClientes.setVisible(x2);
            cbClientes.setVisible(x2);
            rbProducto.setVisible(x2);
            jckPrecio.setVisible(x2);
            jckCosto.setVisible(x2);
            cbProducto.setVisible(x2);
            rbCategoria.setVisible(x2);
            rbFecha.setVisible(x2);
            rbMes.setVisible(x2);
            rbSucursal.setVisible(x2);
            cbSucursal.setVisible(x2);
            rbAo.setVisible(x2);
        });

        tgbCompras = new JToggleButton("");
        tgbCompras.setIcon(new ImageIcon(this.getClass().getResource("/carvajal/img/flechita.png")));
        tgbCompras.setBounds(979, 440, 20, 20);
        tgbCompras.setBackground(new Color(c.getRed(), c.getGreen(), c.getBlue()));
        tgbCompras.setBorder(null);
        contentPane.add(tgbCompras);
        tgbCompras.addActionListener((ActionEvent e) -> {
            x3 = x3 == false;
            cbCProveedor.setVisible(x3);
        });

        rbTipo.setEnabled(false);
        rbGenero.setEnabled(false);
        cbVentas.setEnabled(false);
        rbClientes.setEnabled(false);
        cbClientes.setEnabled(false);
        rbProducto.setEnabled(false);
        jckPrecio.setEnabled(false);
        jckCosto.setEnabled(false);
        cbProducto.setEnabled(false);
        rbCategoria.setEnabled(false);
        rbFecha.setEnabled(false);
        rbMes.setEnabled(false);
        rbAo.setEnabled(false);
        rbSucursal.setEnabled(false);
        cbSucursal.setEnabled(false);
        cbCProveedor.setEnabled(false);

        llenarclientes();
    }

    public void llenarclientes() {
        cbClientes.removeAllItems();
        Conexion con = new Conexion();
        boolean error = con.conectarMySQL("carvajal", "root", "", "localhost");//"127.0.0.1"
        if (!error) {
            ResultSet rs = con.consulta("SELECT * FROM cliente");
            int cant = 0;
            try {
                while (rs.next()) {
                    cant++;
                    cbClientes.addItem(rs.getString(1) + " - " + rs.getString(3) + " " + rs.getString(4));
                }
            } catch (SQLException sql) {
                JOptionPane.showMessageDialog(null,
                        "Error al tratar de obtener la informacion ");
            }
            if (cant == 0) {
                cbClientes.addItem("No hay clientes");
            }
        }
        con.cerrarConsulta();
        con.desconectar();
    }

    public void llenarproductos(String empresa) {
        cbProducto.removeAllItems();
        cbProducto.addItem("Todos");
        cbSucursal.addItem("Todas");
        if (empresa.equals("0")) {
            rbCategoria.setEnabled(true);
            empresa = "empaques";
            cbSucursal.addItem("Bogota");
            cbSucursal.addItem("Tocancipa");
            cbSucursal.addItem("Cali");
            cbSucursal.addItem("Ginebra");
            cbSucursal.addItem("Medellin");
        }
        if (empresa.equals("1")) {
            rbCategoria.setEnabled(false);
            empresa = "propal";
            cbSucursal.addItem("Yumbo");
        }
        if (empresa.equals("2")) {
            rbCategoria.setEnabled(true);
            empresa = "mepal";
            cbSucursal.addItem("Bogota");
            cbSucursal.addItem("Medellin");
            cbSucursal.addItem("Barranquilla");
            cbSucursal.addItem("Cali");
            cbSucursal.addItem("Cartagena");
            cbSucursal.addItem("Eje Cafetero");
        }
        if (empresa.equals("3")) {
            rbCategoria.setEnabled(true);
            empresa = "sol_com";
            cbSucursal.addItem("Bogota");
            cbSucursal.addItem("Medellin");
            cbSucursal.addItem("Bucaramanga");
            cbSucursal.addItem("Manizales");
            cbSucursal.addItem("Cali");
            cbSucursal.addItem("Barranquilla");
            cbSucursal.addItem("Pereira");
        }
        Conexion con = new Conexion();
        boolean error = con.conectarMySQL("carvajal", "root", "", "localhost");//"127.0.0.1"
        if (!error) {
            ResultSet rs = con.consulta("SELECT * FROM productos_" + empresa);
            int cant = 0;
            try {
                while (rs.next()) {
                    cant++;
                    cbProducto.addItem(rs.getString(1) + " - " + rs.getString(2));
                }
            } catch (SQLException sql) {
                JOptionPane.showMessageDialog(null,
                        "Error al tratar de obtener la informacion ");
            }
            if (cant == 0) {
                cbProducto.addItem("No hay productos");
            }
        }

        con.cerrarConsulta();
        con.desconectar();
    }

    public void filtroTodasFechas() {
        panelgraficas.repaint();
        if (cbVentas.getSelectedItem().equals("Empaques")) {
            Barras_Todas_Fechas_Ventas demo;
            panelgraficas.removeAll();
            demo = new Barras_Todas_Fechas_Ventas("empaques");
            panelgraficas.add(demo.panel);
            panelgraficas.repaint();
        }
        if (cbVentas.getSelectedItem().equals("Propal")) {
            Barras_Todas_Fechas_Ventas demo;
            panelgraficas.removeAll();
            demo = new Barras_Todas_Fechas_Ventas("propal");
            panelgraficas.add(demo.panel);
            panelgraficas.repaint();
        }
        if (cbVentas.getSelectedItem().equals("Mepal")) {
            Barras_Todas_Fechas_Ventas demo;
            panelgraficas.removeAll();
            demo = new Barras_Todas_Fechas_Ventas("mepal");
            panelgraficas.add(demo.panel);
            panelgraficas.repaint();
        }
        if (cbVentas.getSelectedItem().equals("Soluciones y Comunicaciones")) {
            Barras_Todas_Fechas_Ventas demo;
            panelgraficas.removeAll();
            demo = new Barras_Todas_Fechas_Ventas("sol_com");
            panelgraficas.add(demo.panel);
            panelgraficas.repaint();
        }
    }

    public void filtroTodosMeses() {
        if (cbVentas.getSelectedItem().equals("Empaques")) {
            Pie_Todos_Meses_Ventas demop;
            panelgraficas.removeAll();
            demop = new Pie_Todos_Meses_Ventas("empaques");
            panelgraficas.add(demop.chartPanel);
            panelgraficas.repaint();
        }
        if (cbVentas.getSelectedItem().equals("Propal")) {
            Pie_Todos_Meses_Ventas demop;
            panelgraficas.removeAll();
            demop = new Pie_Todos_Meses_Ventas("propal");
            panelgraficas.add(demop.chartPanel);
            panelgraficas.repaint();
        }
        if (cbVentas.getSelectedItem().equals("Mepal")) {
            Pie_Todos_Meses_Ventas demop;
            panelgraficas.removeAll();
            demop = new Pie_Todos_Meses_Ventas("mepal");
            panelgraficas.add(demop.chartPanel);
            panelgraficas.repaint();
        }
        if (cbVentas.getSelectedItem().equals("Soluciones y Comunicaciones")) {
            Pie_Todos_Meses_Ventas demop;
            panelgraficas.removeAll();
            demop = new Pie_Todos_Meses_Ventas("sol_com");
            panelgraficas.add(demop.chartPanel);
            panelgraficas.repaint();
        }
    }

    public void filtroTodosAos() {
        if (cbVentas.getSelectedItem().equals("Empaques")) {
            Pie_Todos_Aos_Ventas demop;
            panelgraficas.removeAll();
            demop = new Pie_Todos_Aos_Ventas("empaques");
            panelgraficas.add(demop.chartPanel);
            panelgraficas.repaint();
        }
        if (cbVentas.getSelectedItem().equals("Propal")) {
            Pie_Todos_Aos_Ventas demop;
            panelgraficas.removeAll();
            demop = new Pie_Todos_Aos_Ventas("propal");
            panelgraficas.add(demop.chartPanel);
            panelgraficas.repaint();
        }
        if (cbVentas.getSelectedItem().equals("Mepal")) {
            Pie_Todos_Aos_Ventas demop;
            panelgraficas.removeAll();
            demop = new Pie_Todos_Aos_Ventas("mepal");
            panelgraficas.add(demop.chartPanel);
            panelgraficas.repaint();
        }
        if (cbVentas.getSelectedItem().equals("Soluciones y Comunicaciones")) {
            Pie_Todos_Aos_Ventas demop;
            panelgraficas.removeAll();
            demop = new Pie_Todos_Aos_Ventas("sol_com");
            panelgraficas.add(demop.chartPanel);
            panelgraficas.repaint();
        }

    }

    public void filtrarProductoPorFecha(int dinero) throws SQLException {
        if (cbVentas.getSelectedItem().equals("Empaques")) {
            Barras_Producto_Fechas_Ventas demo;
            panelgraficas.removeAll();
            demo = new Barras_Producto_Fechas_Ventas("empaques", cbProducto.getSelectedIndex() + "",dinero);
            panelgraficas.add(demo.panel);
            panelgraficas.repaint();
        }
        if (cbVentas.getSelectedItem().equals("Propal")) {
            Barras_Producto_Fechas_Ventas demo;
            panelgraficas.removeAll();
            demo = new Barras_Producto_Fechas_Ventas("propal", cbProducto.getSelectedIndex() + "",dinero);
            panelgraficas.add(demo.panel);
            panelgraficas.repaint();
        }
        if (cbVentas.getSelectedItem().equals("Mepal")) {
            Barras_Producto_Fechas_Ventas demo;
            panelgraficas.removeAll();
            demo = new Barras_Producto_Fechas_Ventas("mepal", cbProducto.getSelectedIndex() + "",dinero);
            panelgraficas.add(demo.panel);
            panelgraficas.repaint();
        }
        if (cbVentas.getSelectedItem().equals("Soluciones y Comunicaciones")) {
            Barras_Producto_Fechas_Ventas demo;
            panelgraficas.removeAll();
            demo = new Barras_Producto_Fechas_Ventas("sol_com", cbProducto.getSelectedIndex() + "",dinero);
            panelgraficas.add(demo.panel);
            panelgraficas.repaint();
        }
    }

    public void filtroclientes(String momento) {
        Pie_Clientes demop;
        panelgraficas.removeAll();
        demop = new Pie_Clientes(momento);
        panelgraficas.add(demop.chartPanel);
        panelgraficas.repaint();
    }

    public void filtroproductos(int dinero) {
        panelgraficas.repaint();
        if (cbVentas.getSelectedItem().equals("Empaques")) {
            Barras_Productos_VentasEmpaques demo1;
            try {
                panelgraficas.removeAll();
                demo1 = new Barras_Productos_VentasEmpaques("",dinero);
                panelgraficas.add(demo1.panel);
                panelgraficas.repaint();
            } catch (SQLException ex) {
                Logger.getLogger(GUI.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        if (cbVentas.getSelectedItem().equals("Propal")) {
            Barras_Productos_VentasPropal demo2;
            try {
                panelgraficas.removeAll();
                demo2 = new Barras_Productos_VentasPropal("",dinero);
                panelgraficas.add(demo2.panel);
                panelgraficas.repaint();
            } catch (SQLException ex) {
                Logger.getLogger(GUI.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        if (cbVentas.getSelectedItem().equals("Mepal")) {
            Barras_Productos_VentasMepal demo3;
            try {
                panelgraficas.removeAll();
                demo3 = new Barras_Productos_VentasMepal("",dinero);
                panelgraficas.add(demo3.panel);
                panelgraficas.repaint();
            } catch (SQLException ex) {
                Logger.getLogger(GUI.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        if (cbVentas.getSelectedItem().equals("Soluciones y Comunicaciones")) {
            Barras_Productos_VentasSolCom demo4;
            try {
                panelgraficas.removeAll();
                demo4 = new Barras_Productos_VentasSolCom("",dinero);
                panelgraficas.add(demo4.panel);
                panelgraficas.repaint();
            } catch (SQLException ex) {
                Logger.getLogger(GUI.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
    
    public void filtrocategorias() {
        panelgraficas.repaint();
        if (cbVentas.getSelectedItem().equals("Empaques")) {
            Barras_Categorias_VentasEmpaques demo1;
            try {
                panelgraficas.removeAll();
                demo1 = new Barras_Categorias_VentasEmpaques("");
                panelgraficas.add(demo1.panel);
                panelgraficas.repaint();
            } catch (SQLException ex) {
                Logger.getLogger(GUI.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        if (cbVentas.getSelectedItem().equals("Mepal")) {
            Barras_Categorias_VentasMepal demo3;
            try {
                panelgraficas.removeAll();
                demo3 = new Barras_Categorias_VentasMepal("");
                panelgraficas.add(demo3.panel);
                panelgraficas.repaint();
            } catch (SQLException ex) {
                Logger.getLogger(GUI.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        if (cbVentas.getSelectedItem().equals("Soluciones y Comunicaciones")) {
            Barras_Categorias_VentasSolCom demo4;
            try {
                panelgraficas.removeAll();
                demo4 = new Barras_Categorias_VentasSolCom("");
                panelgraficas.add(demo4.panel);
                panelgraficas.repaint();
            } catch (SQLException ex) {
                Logger.getLogger(GUI.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    public void filtrosucursal() {
        panelgraficas.repaint();
        if (cbVentas.getSelectedItem().equals("Empaques")) {
            Barras_Sucursal_VentasEmpaques demo1;
            try {
                panelgraficas.removeAll();
                demo1 = new Barras_Sucursal_VentasEmpaques("");
                panelgraficas.add(demo1.panel);
                panelgraficas.repaint();
            } catch (SQLException ex) {
                Logger.getLogger(GUI.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        if (cbVentas.getSelectedItem().equals("Propal")) {
            Barras_Sucursal_VentasPropal demo2;
            try {
                panelgraficas.removeAll();
                demo2 = new Barras_Sucursal_VentasPropal("");
                panelgraficas.add(demo2.panel);
                panelgraficas.repaint();
            } catch (SQLException ex) {
                Logger.getLogger(GUI.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        if (cbVentas.getSelectedItem().equals("Mepal")) {
            Barras_Sucursal_VentasMepal demo3;
            try {
                panelgraficas.removeAll();
                demo3 = new Barras_Sucursal_VentasMepal("");
                panelgraficas.add(demo3.panel);
                panelgraficas.repaint();
            } catch (SQLException ex) {
                Logger.getLogger(GUI.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        if (cbVentas.getSelectedItem().equals("Soluciones y Comunicaciones")) {
            Barras_Sucursal_VentasSolCom demo4;
            try {
                panelgraficas.removeAll();
                demo4 = new Barras_Sucursal_VentasSolCom("");
                panelgraficas.add(demo4.panel);
                panelgraficas.repaint();
            } catch (SQLException ex) {
                Logger.getLogger(GUI.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    public void filtrarPorCadaClientes() throws SQLException {
        if (cbVentas.getSelectedItem().equals("Empaques")) {
            panelgraficas.removeAll();
            Lineas_Clientes_Ventas lc = new Lineas_Clientes_Ventas("empaques", cbClientes.getSelectedItem().toString().substring(0, 10));
            panelgraficas.add(lc.frame);
            panelgraficas.repaint();
        }
        if (cbVentas.getSelectedItem().equals("Propal")) {
            panelgraficas.removeAll();
            Lineas_Clientes_Ventas lc = new Lineas_Clientes_Ventas("propal", cbClientes.getSelectedItem().toString().substring(0, 10));
            panelgraficas.add(lc.frame);
            panelgraficas.repaint();
        }
        if (cbVentas.getSelectedItem().equals("Mepal")) {
            panelgraficas.removeAll();
            Lineas_Clientes_Ventas lc = new Lineas_Clientes_Ventas("mepal", cbClientes.getSelectedItem().toString().substring(0, 10));
            panelgraficas.add(lc.frame);
            panelgraficas.repaint();
        }
        if (cbVentas.getSelectedItem().equals("Soluciones y Comunicaciones")) {
            panelgraficas.removeAll();
            Lineas_Clientes_Ventas lc = new Lineas_Clientes_Ventas("sol_com", cbClientes.getSelectedItem().toString().substring(0, 10));
            panelgraficas.add(lc.frame);
            panelgraficas.repaint();
        }
    }

    public void filtrarSucursalPorFecha() throws SQLException {
        if (cbVentas.getSelectedItem().equals("Empaques")) {
            panelgraficas.removeAll();
            Lineas_Sucursal_Fecha lc = new Lineas_Sucursal_Fecha("empaques", cbSucursal.getSelectedItem().toString());
            panelgraficas.add(lc.frame);
            panelgraficas.repaint();
        }
        if (cbVentas.getSelectedItem().equals("Propal")) {
            panelgraficas.removeAll();
            Lineas_Sucursal_Fecha lc = new Lineas_Sucursal_Fecha("propal", cbSucursal.getSelectedItem().toString());
            panelgraficas.add(lc.frame);
            panelgraficas.repaint();
        }
        if (cbVentas.getSelectedItem().equals("Mepal")) {
            panelgraficas.removeAll();
            Lineas_Sucursal_Fecha lc = new Lineas_Sucursal_Fecha("mepal", cbSucursal.getSelectedItem().toString());
            panelgraficas.add(lc.frame);
            panelgraficas.repaint();
        }
        if (cbVentas.getSelectedItem().equals("Soluciones y Comunicaciones")) {
            panelgraficas.removeAll();
            Lineas_Sucursal_Fecha lc = new Lineas_Sucursal_Fecha("sol_com", cbSucursal.getSelectedItem().toString());
            panelgraficas.add(lc.frame);
            panelgraficas.repaint();
        }
    }

    public void imprimir() {
        try {
            PrinterJob job = PrinterJob.getPrinterJob();
            job.setPrintable(this);
            job.printDialog();
            job.print();
        } catch (PrinterException ex) {
            System.out.println(ex);
        }
        JOptionPane.showMessageDialog(null, "Informe Creado");
    }

    public int print(Graphics graphics, PageFormat pageFormat, int pageIndex) throws PrinterException {
        if (pageIndex > 0) {
            return NO_SUCH_PAGE;
        }
        Graphics2D g2d = (Graphics2D) graphics;
        g2d.translate(pageFormat.getImageableX(),
                pageFormat.getImageableY());
        panelgraficas.printAll(graphics);
        return PAGE_EXISTS;

    }
}
