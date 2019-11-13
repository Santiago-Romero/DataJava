/*
 * Codigo por Santiago Romero Andrade
 */
package carvajal.vista;

/**
 *
 * @author Santiago Romero
 */
import carvajal.controlador.Conexion;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.axis.CategoryAxis;
import org.jfree.chart.axis.CategoryLabelPositions;
import org.jfree.chart.axis.NumberAxis;
import org.jfree.chart.plot.CategoryPlot;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.renderer.category.BarRenderer;
import org.jfree.data.category.CategoryDataset;
import org.jfree.data.category.DefaultCategoryDataset;
import org.jfree.ui.ApplicationFrame;
import java.awt.*;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.UIManager;

public class Barras_Productos_VentasMepal extends ApplicationFrame {

    public ChartPanel panel;
    public JFreeChart chart;
    int producto1, producto2, producto3, producto4, producto5, producto6,
            producto7, producto8, producto9, producto10, producto11, producto12,
            producto13, producto14, producto15, producto16, producto17,
            producto18, producto19, producto20, producto21,
            producto22, producto23, producto24, producto25;
    int dinero;
    public Barras_Productos_VentasMepal(String title,int dinero) throws SQLException {
        super(title);
        this.dinero=dinero;
        CategoryDataset dataset = crearDataset();
        chart = crearChart(dataset);
        panel = new ChartPanel(chart, false);
        panel.setPreferredSize(new Dimension(700, 500));
        setContentPane(panel);
        pack();
    }

    private CategoryDataset crearDataset() throws SQLException {
        DefaultCategoryDataset dataset = new DefaultCategoryDataset();
        Conexion con = new Conexion();
        boolean error = con.conectarMySQL("carvajal", "root", "", "localhost");//"127.0.0.1"
        if (!error) {
            ResultSet rs = con.consulta("SELECT * FROM ventas_mepal");
            while(rs.next()){
            if(dinero==0){
                    if(rs.getInt(3)==(1)) producto1+=rs.getInt(4);
                    if(rs.getInt(3)==(2)) producto2+=rs.getInt(4);
                    if(rs.getInt(3)==(3)) producto3+=rs.getInt(4);
                    if(rs.getInt(3)==(4)) producto4+=rs.getInt(4);
                    if(rs.getInt(3)==(5)) producto5+=rs.getInt(4);
                    if(rs.getInt(3)==(6)) producto6+=rs.getInt(4);
                    if(rs.getInt(3)==(7)) producto7+=rs.getInt(4);
                    if(rs.getInt(3)==(8)) producto8+=rs.getInt(4);
                    if(rs.getInt(3)==(9)) producto9+=rs.getInt(4);
                    if(rs.getInt(3)==(10)) producto10+=rs.getInt(4);
                    if(rs.getInt(3)==(11)) producto11+=rs.getInt(4);
                    if(rs.getInt(3)==(12)) producto12+=rs.getInt(4);
                    if(rs.getInt(3)==(13)) producto13+=rs.getInt(4);
                    if(rs.getInt(3)==(14)) producto14+=rs.getInt(4);
                    if(rs.getInt(3)==(15)) producto15+=rs.getInt(4);
                    if(rs.getInt(3)==(16)) producto16+=rs.getInt(4);
                    if(rs.getInt(3)==(17)) producto17+=rs.getInt(4);
                    if(rs.getInt(3)==(18)) producto18+=rs.getInt(4);
                    if(rs.getInt(3)==(19)) producto19+=rs.getInt(4);
                    if(rs.getInt(3)==(20)) producto20+=rs.getInt(4);
                    if(rs.getInt(3)==(21)) producto21+=rs.getInt(4);
                    if(rs.getInt(3)==(22)) producto22+=rs.getInt(4);
                    if(rs.getInt(3)==(23)) producto23+=rs.getInt(4);
                    if(rs.getInt(3)==(24)) producto24+=rs.getInt(4);
                    if(rs.getInt(3)==(25)) producto25+=rs.getInt(4);
                }
                if(dinero==1){
                    if(rs.getInt(3)==(1)) producto1+=(rs.getInt(4)*70000);
                    if(rs.getInt(3)==(2)) producto2+=(rs.getInt(4)*90000);
                    if(rs.getInt(3)==(3)) producto3+=(rs.getInt(4)*75000);
                    if(rs.getInt(3)==(4)) producto4+=(rs.getInt(4)*90000);
                    if(rs.getInt(3)==(5)) producto5+=(rs.getInt(4)*75000);
                    if(rs.getInt(3)==(6)) producto6+=(rs.getInt(4)*75000);
                    if(rs.getInt(3)==(7)) producto7+=(rs.getInt(4)*75000);
                    if(rs.getInt(3)==(8)) producto8+=(rs.getInt(4)*70000);
                    if(rs.getInt(3)==(9)) producto9+=(rs.getInt(4)*80000);
                    if(rs.getInt(3)==(10)) producto10+=(rs.getInt(4)*90000);
                    if(rs.getInt(3)==(11)) producto11+=(rs.getInt(4)*90000);
                    if(rs.getInt(3)==(12)) producto12+=(rs.getInt(4)*90000);
                    if(rs.getInt(3)==(13)) producto13+=(rs.getInt(4)*90000);
                    if(rs.getInt(3)==(14)) producto14+=(rs.getInt(4)*90000);
                    if(rs.getInt(3)==(15)) producto15+=(rs.getInt(4)*95000);
                    if(rs.getInt(3)==(16)) producto16+=(rs.getInt(4)*97000);
                    if(rs.getInt(3)==(17)) producto17+=(rs.getInt(4)*95000);
                    if(rs.getInt(3)==(18)) producto18+=(rs.getInt(4)*120000);
                    if(rs.getInt(3)==(19)) producto19+=(rs.getInt(4)*120000);
                    if(rs.getInt(3)==(20)) producto20+=(rs.getInt(4)*150000);
                    if(rs.getInt(3)==(21)) producto21+=(rs.getInt(4)*220000);
                    if(rs.getInt(3)==(22)) producto22+=(rs.getInt(4)*250000);
                    if(rs.getInt(3)==(23)) producto23+=(rs.getInt(4)*200000);
                    if(rs.getInt(3)==(24)) producto24+=(rs.getInt(4)*500000);
                    if(rs.getInt(3)==(25)) producto25+=(rs.getInt(4)*400000);
                }
                if(dinero==2){
                    if(rs.getInt(3)==(1)) producto1+=(rs.getInt(4)*10000);
                    if(rs.getInt(3)==(2)) producto2+=(rs.getInt(4)*10000);
                    if(rs.getInt(3)==(3)) producto3+=(rs.getInt(4)*10000);
                    if(rs.getInt(3)==(4)) producto4+=(rs.getInt(4)*10000);
                    if(rs.getInt(3)==(5)) producto5+=(rs.getInt(4)*8000);
                    if(rs.getInt(3)==(6)) producto6+=(rs.getInt(4)*8000);
                    if(rs.getInt(3)==(7)) producto7+=(rs.getInt(4)*8000);
                    if(rs.getInt(3)==(8)) producto8+=(rs.getInt(4)*8000);
                    if(rs.getInt(3)==(9)) producto9+=(rs.getInt(4)*9000);
                    if(rs.getInt(3)==(10)) producto10+=(rs.getInt(4)*7000);
                    if(rs.getInt(3)==(11)) producto11+=(rs.getInt(4)*7000);
                    if(rs.getInt(3)==(12)) producto12+=(rs.getInt(4)*7000);
                    if(rs.getInt(3)==(13)) producto13+=(rs.getInt(4)*7000);
                    if(rs.getInt(3)==(14)) producto14+=(rs.getInt(4)*7000);
                    if(rs.getInt(3)==(15)) producto15+=(rs.getInt(4)*8000);
                    if(rs.getInt(3)==(16)) producto16+=(rs.getInt(4)*8000);
                    if(rs.getInt(3)==(17)) producto17+=(rs.getInt(4)*8000);
                    if(rs.getInt(3)==(18)) producto18+=(rs.getInt(4)*10000);
                    if(rs.getInt(3)==(19)) producto19+=(rs.getInt(4)*10000);
                    if(rs.getInt(3)==(20)) producto20+=(rs.getInt(4)*11000);
                    if(rs.getInt(3)==(21)) producto21+=(rs.getInt(4)*20000);
                    if(rs.getInt(3)==(22)) producto22+=(rs.getInt(4)*20000);
                    if(rs.getInt(3)==(23)) producto23+=(rs.getInt(4)*15000);
                    if(rs.getInt(3)==(24)) producto24+=(rs.getInt(4)*20000);
                    if(rs.getInt(3)==(25)) producto25+=(rs.getInt(4)*10000);
                }
            }
        }
        con.cerrarConsulta();
        con.desconectar();
        dataset.setValue(producto1, "Ventas", "Pupitre simple");
        dataset.setValue(producto2, "Ventas", "Pupitre conjunto");
        dataset.setValue(producto3, "Ventas", "Pupitre computador");
        dataset.setValue(producto4, "Ventas", "Silla litte");
        dataset.setValue(producto5, "Ventas", "Silla perillo");
        dataset.setValue(producto6, "Ventas", "Silla drop");
        dataset.setValue(producto7, "Ventas", "Silla Class");
        dataset.setValue(producto8, "Ventas", "Silla Moema");
        dataset.setValue(producto9, "Ventas", "Silla Fogo");
        dataset.setValue(producto10, "Ventas", "Silla oficina");
        dataset.setValue(producto11, "Ventas", "Silla Contessa");
        dataset.setValue(producto12, "Ventas", "Silla Sabrina");
        dataset.setValue(producto13, "Ventas", "Silla Bravo");
        dataset.setValue(producto14, "Ventas", "Silla Equity");
        dataset.setValue(producto15, "Ventas", "Silla Lai");
        dataset.setValue(producto16, "Ventas", "Silla Luxo");
        dataset.setValue(producto17, "Ventas", "Silla Visconte");
        dataset.setValue(producto18, "Ventas", "Division vetro");
        dataset.setValue(producto19, "Ventas", "Division recepcion");
        dataset.setValue(producto20, "Ventas", "Division multiple");
        dataset.setValue(producto21, "Ventas", "Archivador Koncisa");
        dataset.setValue(producto22, "Ventas", "Archivo multiple zen");
        dataset.setValue(producto23, "Ventas", "Escritorio Koncisa");
        dataset.setValue(producto24, "Ventas", "Mesa reunion");
        dataset.setValue(producto25, "Ventas", "Mesa ejecutiva");

        return dataset;
    }

    private JFreeChart crearChart(CategoryDataset data) {
        String nombre = null;
        if(dinero==2){
            nombre="Costo en Ventas (COP)";
        }
        if(dinero==1){
            nombre="Dinero en Ventas (COP)";
        }
        if(dinero==2){
            nombre="Productos vendidos";
        }
        JFreeChart chart = ChartFactory.createBarChart(
                "", //Nombre de la gr�fica
                "Productos", //Nombre del eje Horizontal
                nombre, //Nombre del eje vertical
                data, //Data
                PlotOrientation.VERTICAL, //Orientaci�n HORIZONTAL o VERTICAL
                true, //Incluir leyenda
                true, //Informaci�n al pasar el mouse
                true);                      //URls

        Color c = UIManager.getLookAndFeel().getDefaults().getColor("Panel.background");
        chart.setBackgroundPaint(new Color(c.getRed(), c.getGreen(), c.getBlue()));//Dar color al fondo del panel
        chart.getTitle().setPaint(Color.WHITE);//Dar color al titulo 

        //CategoryPlot plot =(CategoryPlot) chart.getPlot();	    	    	    
        CategoryPlot plot = chart.getCategoryPlot();
        plot.setBackgroundPaint(Color.WHITE);//Color del fondo del gr�fico

        plot.setDomainGridlinesVisible(true);//Lineas divisorias
        plot.setRangeGridlinePaint(Color.BLACK);//Color de lineas divisorias	    

        //Calculo de los valores en el eje x
        NumberAxis rangeAxis = (NumberAxis) plot.getRangeAxis();
        rangeAxis.setStandardTickUnits(NumberAxis.createIntegerTickUnits());

        BarRenderer renderer = (BarRenderer) plot.getRenderer();
        renderer.setDrawBarOutline(true);//Asignar color de linea a las barras

        //Dar color a las barras
        if(dinero==0){
        GradientPaint gp = new GradientPaint(0.0f, 0.0f, Color.orange, 0.0f, 0.0f, new Color(0, 64, 0));
        renderer.setSeriesPaint(0, gp);}
        if(dinero==1){
        GradientPaint gp = new GradientPaint(0.0f, 0.0f, Color.green, 0.0f, 0.0f, new Color(0, 64, 0));
        renderer.setSeriesPaint(0, gp);}
        if(dinero==0){
        GradientPaint gp = new GradientPaint(0.0f, 0.0f, Color.red, 0.0f, 0.0f, new Color(0, 64, 0));
        renderer.setSeriesPaint(0, gp);}

        CategoryAxis domainAxis = plot.getDomainAxis();
        domainAxis.setCategoryLabelPositions(CategoryLabelPositions.createUpRotationLabelPositions(Math.PI / 6.0));

        return chart;
    }
}


