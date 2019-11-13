/*
 * Codigo por Santiago Romero Andrade
 */
package carvajal.vista;

import carvajal.controlador.Conexion;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PiePlot;
import org.jfree.data.general.DefaultPieDataset;
import org.jfree.data.general.PieDataset;
import org.jfree.ui.ApplicationFrame;
import org.jfree.ui.RefineryUtilities;

import java.awt.*;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.JOptionPane;
import javax.swing.UIManager;

public class Pie_Clientes extends ApplicationFrame {

    public ChartPanel chartPanel;
    String momento;

    public Pie_Clientes(String momento) {
        super(momento);
        this.momento = momento;
        JFreeChart chart = crearChart(crearDataSet());

        // add the chart to a panel...
        chartPanel = new ChartPanel(chart);
        chartPanel.setPreferredSize(new java.awt.Dimension(758, 510));
        setContentPane(chartPanel);
        pack();

        RefineryUtilities.centerFrameOnScreen(this);
    }

    private PieDataset crearDataSet() {
        String cosan1 = "";
        String cosan2 = "";
        int cosa1 = 0;
        int cosa2 = 0;
        int total = 0;
        Conexion con = new Conexion();
        boolean error = con.conectarMySQL("carvajal", "root", "", "localhost");//"127.0.0.1"
        if (!error) {
            ResultSet rs = con.consulta("SELECT " + momento + " FROM cliente");
            total = con.getSizeQuery(rs);
            if (total > 0) {
                try {
                    while (rs.next()) {
                        if (momento.equals("tipo")) {
                            cosan1 = "Natural";
                            cosan2 = "Juridica";
                            if (rs.getString(1).equals("Natural")) {
                                cosa1++;
                            }
                            if (rs.getString(1).equals("Juridica")) {
                                cosa2++;
                            }
                        }
                        if (momento.equals("genero")) {
                            cosan1 = "Masculino";
                            cosan2 = "Femenino";
                            if (rs.getString(1).equals("M")) {
                                cosa1++;
                            }
                            if (rs.getString(1).equals("F")) {
                                cosa2++;
                            }
                        }
                    }
                } catch (SQLException sql) {
                    JOptionPane.showMessageDialog(null,
                            "Error al tratar de obtener la informacion ");
                }
            } else {
                JOptionPane.showMessageDialog(null,
                        "No existe");
            }
        }

        con.cerrarConsulta();
        con.desconectar();
        String valor1;
        String valor2;
        if(total!=0){
        valor1 = (cosa1 * 100) / total + "";
        valor2 = (cosa2 * 100) / total + "";}
        else{
            valor1=0+"";
            valor2=0+"";
        }

        DefaultPieDataset data = new DefaultPieDataset();
        data.setValue(cosan1, new Double(valor1));
        data.setValue(cosan2, new Double(valor2));
        return data;
    }

    private JFreeChart crearChart(PieDataset data) {
        JFreeChart chart = ChartFactory.createPieChart3D(
                "Demo de PieChart", //Nombre del grafico
                data, //data
                true, //Leyenda
                true,
                false);
        Color c = UIManager.getLookAndFeel().getDefaults().getColor("Panel.background");
        chart.setBackgroundPaint(new Color(c.getRed(), c.getGreen(), c.getBlue()));//Color de fonde de la ventana
        chart.getTitle().setPaint(new Color(c.getRed(), c.getGreen(), c.getBlue())); //Dar color al titulo

        PiePlot plot = (PiePlot) chart.getPlot();
        plot.setLabelBackgroundPaint(Color.white);//Color de las etiquetas

        plot.setBackgroundPaint(new Color(c.getRed(), c.getGreen(), c.getBlue())); //Color de el fondo del gr�fico
        plot.setNoDataMessage("No hay data");

        //CategoryAxis domainAxis = plot.getDomainAxis();
        //domainAxis.setTickLabelFont(new Font("SanSerif",Font.PLAIN,10));
        return chart;
    }

}
