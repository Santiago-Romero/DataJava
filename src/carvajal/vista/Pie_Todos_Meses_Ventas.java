/*
 * Codigo por Santiago Romero Andrade
 */
package carvajal.vista;

import carvajal.controlador.Conexion;
import java.awt.Color;
import java.sql.ResultSet;
import javax.swing.UIManager;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PiePlot3D;
import org.jfree.data.general.DefaultPieDataset;
import org.jfree.data.general.PieDataset;
import org.jfree.ui.ApplicationFrame;

/**
 *
 * @author Santiago Romero
 */
public class Pie_Todos_Meses_Ventas extends ApplicationFrame{
    
    public ChartPanel chartPanel;
    public String empresa;

    public Pie_Todos_Meses_Ventas(final String empresa) {
        
        super(empresa);
        this.empresa=empresa;
        JFreeChart chart = crearChart(crearDataSet());

        // add the chart to a panel...
        chartPanel = new ChartPanel(chart);
        chartPanel.setPreferredSize(new java.awt.Dimension(758,510));
        setContentPane(chartPanel);
        pack();
    }

    private PieDataset crearDataSet() {
        
        DefaultPieDataset data = new DefaultPieDataset();
        
        Conexion con = new Conexion();
            boolean error = con.conectarMySQL("carvajal", "root", "", "localhost");//"127.0.0.1"
            int cant=0;
            for (int a = 1; a <= 12; a++) {
                    if (!error) {
                            ResultSet rs = con.consulta("SELECT * FROM ventas_" +empresa +" WHERE MONTH(fecha)="+a);
                            cant=con.getSizeQuery(rs);
                        }
                    if(a==1) data.setValue("Enero", new Double(cant));
                    if(a==2) data.setValue("Febrero", new Double(cant));
                    if(a==3) data.setValue("Marzo", new Double(cant));
                    if(a==4) data.setValue("Abril", new Double(cant));
                    if(a==5) data.setValue("Mayo", new Double(cant));
                    if(a==6) data.setValue("Junio", new Double(cant));
                    if(a==7) data.setValue("Julio", new Double(cant));
                    if(a==8) data.setValue("Agosto", new Double(cant));
                    if(a==9) data.setValue("Septiembre", new Double(cant));
                    if(a==10) data.setValue("Octubre", new Double(cant));
                    if(a==11) data.setValue("Noviembre", new Double(cant));
                    if(a==12) data.setValue("Diciembre", new Double(cant));
                    
                        
            }

            con.cerrarConsulta();
            con.desconectar();

        return data;
    }

    private JFreeChart crearChart(PieDataset data) {
        // create the chart...
        JFreeChart chart = ChartFactory.createPieChart3D(
                "", // chart title
                data, // data
                true, // include legend
                true,
                false
        );
        Color c = UIManager.getLookAndFeel().getDefaults().getColor("Panel.background");

        chart.setBackgroundPaint(new Color(c.getRed(), c.getGreen(), c.getBlue()));//Color de fonde de la ventana
        chart.getTitle().setPaint(Color.blue); //Dar color al titulo

        PiePlot3D plot = (PiePlot3D) chart.getPlot();
        plot.setLabelBackgroundPaint(Color.WHITE);//Color de las etiquetas
        plot.setForegroundAlpha(0.60f);
        plot.setNoDataMessage("No hay data");

        Rotator rotator = new Rotator(plot);
        rotator.start();

        return chart;
    }
}

