/*
 * Codigo por Santiago Romero Andrade
 */
package carvajal.vista;

/**
 *
 * @author Santiago Romero
 */
import carvajal.modelo.Cliente;
import carvajal.modelo.Compra_proveedor;
import carvajal.modelo.Ventas_empaques;
import carvajal.modelo.Ventas_mepal;
import carvajal.modelo.Ventas_propal;
import carvajal.modelo.Ventas_sol_com;
import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JCheckBox;
import java.awt.Color;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.io.File;
import javax.swing.JOptionPane;

public class Programer extends JDialog {

    private final JPanel contentPanel = new JPanel();
    
    public Programer() {
        
        setBounds(100, 100, 450, 300);
        getContentPane().setLayout(new BorderLayout());
        contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
        getContentPane().add(contentPanel, BorderLayout.CENTER);
        contentPanel.setLayout(null);
        
        JCheckBox checkClientes = new JCheckBox("");
        checkClientes.setEnabled(false);
        checkClientes.setBackground(Color.ORANGE);
        checkClientes.setBounds(263, 30, 21, 23);
        contentPanel.add(checkClientes);

        JCheckBox checkCompras = new JCheckBox("");
        checkCompras.setEnabled(false);
        checkCompras.setBackground(Color.ORANGE);
        checkCompras.setBounds(263, 55, 21, 23);
        contentPanel.add(checkCompras);

        JCheckBox checkEmpaques = new JCheckBox("");
        checkEmpaques.setEnabled(false);
        checkEmpaques.setBackground(Color.RED);
        checkEmpaques.setBounds(263, 80, 21, 23);
        contentPanel.add(checkEmpaques);

        JCheckBox checkPropal = new JCheckBox("");
        checkPropal.setEnabled(false);
        checkPropal.setBackground(Color.RED);
        checkPropal.setBounds(263, 105, 21, 23);
        contentPanel.add(checkPropal);

        JCheckBox checkMepal = new JCheckBox("");
        checkMepal.setEnabled(false);
        checkMepal.setBackground(Color.RED);
        checkMepal.setBounds(263, 130, 21, 23);
        contentPanel.add(checkMepal);

        JCheckBox checkSolCom = new JCheckBox("");
        checkSolCom.setEnabled(false);
        checkSolCom.setBackground(Color.RED);
        checkSolCom.setBounds(263, 155, 21, 23);
        contentPanel.add(checkSolCom);
        
        String fichero1 = "Clientes.csv";
        String fichero2 = "Compra_proveedor.csv";
        String fichero3 = "Ventas_empaques.csv";
        String fichero4 = "Ventas_propal.csv";
        String fichero5 = "Ventas_mepal.csv";
        String fichero6 = "Ventas_sol_com.csv";
        File ficheroCl = new File(fichero1);
        File ficheroCo = new File(fichero2);
        File ficheroVe = new File(fichero3);
        File ficheroVp = new File(fichero4);
        File ficheroVm = new File(fichero5);
        File ficheroVs = new File(fichero6);
        
        
        if (ficheroCl.exists()) checkClientes.setBackground(Color.GREEN);
        else checkClientes.setBackground(Color.ORANGE);
        if (ficheroCo.exists()) checkCompras.setBackground(Color.GREEN);
        else checkCompras.setBackground(Color.ORANGE);
        if (ficheroVe.exists()) checkEmpaques.setBackground(Color.GREEN);
        else checkEmpaques.setBackground(Color.RED);
        if (ficheroVp.exists()) checkPropal.setBackground(Color.GREEN);
        else checkPropal.setBackground(Color.RED);
        if (ficheroVm.exists()) checkMepal.setBackground(Color.GREEN);
        else checkMepal.setBackground(Color.RED);
        if (ficheroVs.exists()) checkSolCom.setBackground(Color.GREEN);
        else checkSolCom.setBackground(Color.RED);
        
        {
            JButton btnClientes = new JButton("Clientes");
            btnClientes.setBackground(Color.LIGHT_GRAY);
            btnClientes.setBounds(56, 30, 201, 23);
            contentPanel.add(btnClientes);
            btnClientes.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent arg0) {
                    int cant=Integer.parseInt(JOptionPane.showInputDialog("Digite la cantidad de registros"));
                    Cliente cl = new Cliente();
                    cl.generar(cant);
                    if(!cl.error){
                        checkClientes.setBackground(Color.green);
                    }
                    
                }
            });
        }
        {
            JButton btnComprasProveedores = new JButton("Compras proveedores");
            btnComprasProveedores.setBackground(Color.LIGHT_GRAY);
            btnComprasProveedores.setBounds(56, 55, 201, 23);
            contentPanel.add(btnComprasProveedores);
            btnComprasProveedores.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent arg0) {
                    int cant=Integer.parseInt(JOptionPane.showInputDialog("Digite la cantidad de registros"));
                    Compra_proveedor cp = new Compra_proveedor(cant);
                    if(!cp.error){
                    checkCompras.setBackground(Color.green);
                    }
                }
            });
        }
        {
            JButton btnVentasPropal = new JButton("Ventas propal");
            btnVentasPropal.setBackground(Color.LIGHT_GRAY);
            btnVentasPropal.setBounds(56, 105, 201, 23);
            contentPanel.add(btnVentasPropal);
            btnVentasPropal.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent arg0) {
                    int cant=Integer.parseInt(JOptionPane.showInputDialog("Digite la cantidad de registros"));
                    Ventas_propal vp = new Ventas_propal(cant);
                    if(!vp.error){
                    checkPropal.setBackground(Color.green);
                    }
                }
            });
        }
        {
            JButton btnVentasMepal = new JButton("Ventas mepal");
            btnVentasMepal.setBackground(Color.LIGHT_GRAY);
            btnVentasMepal.setBounds(56, 130, 201, 23);
            contentPanel.add(btnVentasMepal);
            btnVentasMepal.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent arg0) {
                    int cant=Integer.parseInt(JOptionPane.showInputDialog("Digite la cantidad de registros"));
                    Ventas_mepal vm = new Ventas_mepal(cant);
                    if(!vm.error){
                    checkMepal.setBackground(Color.green);
                    }
                }
            });
        }
        {
            JButton btnVentasEmpaques = new JButton("Ventas empaques");
            btnVentasEmpaques.setBackground(Color.LIGHT_GRAY);
            btnVentasEmpaques.setBounds(56, 80, 201, 23);
            contentPanel.add(btnVentasEmpaques);
            btnVentasEmpaques.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent arg0) {
                    int cant=Integer.parseInt(JOptionPane.showInputDialog("Digite la cantidad de registros"));
                    Ventas_empaques ve = new Ventas_empaques(cant);
                    if(!ve.error){
                    checkEmpaques.setBackground(Color.green);
                    }
                }
            });
        }
        {
            JButton btnVentasSolucionesY = new JButton("Ventas soluciones y comuncaciones");
            btnVentasSolucionesY.setBackground(Color.LIGHT_GRAY);
            btnVentasSolucionesY.setBounds(56, 155, 201, 23);
            contentPanel.add(btnVentasSolucionesY);
            btnVentasSolucionesY.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent arg0) {
                    int cant=Integer.parseInt(JOptionPane.showInputDialog("Digite la cantidad de registros"));
                    Ventas_sol_com vsc = new Ventas_sol_com(cant);
                    if(!vsc.error){
                    checkSolCom.setBackground(Color.green);
                    }
                }
            });
        }

        JLabel lblGeneradorDeArchivos = new JLabel("Generador de Archivos CSV con datos aleatorios");
        lblGeneradorDeArchivos.setFont(new Font("Tahoma", Font.PLAIN, 14));
        lblGeneradorDeArchivos.setBounds(56, 9, 325, 14);
        contentPanel.add(lblGeneradorDeArchivos);

        {
            JPanel buttonPane = new JPanel();
            buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
            getContentPane().add(buttonPane, BorderLayout.SOUTH);
            {
                JButton okButton = new JButton("OK");
                okButton.setActionCommand("OK");
                buttonPane.add(okButton);
                getRootPane().setDefaultButton(okButton);
                okButton.addActionListener(new ActionListener() {
                    public void actionPerformed(ActionEvent e) {
                        setVisible(false);
                    }
                });
            }
        }
    }
}
