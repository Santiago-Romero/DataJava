package carvajal.vista;

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

public class Barras_Productos_VentasEmpaques extends ApplicationFrame {

    public ChartPanel panel;
    public JFreeChart chart;
    int producto1, producto2, producto3, producto4, producto5, producto6,
            producto7, producto8, producto9, producto10, producto11, producto12,
            producto13, producto14, producto15, producto16, producto17,
            producto18, producto19, producto20, producto21,
            producto22, producto23, producto24, producto25, producto26,
            producto27, producto28, producto29, producto30;
    int dinero;

    public Barras_Productos_VentasEmpaques(String title,int dinero) throws SQLException {
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
            ResultSet rs = con.consulta("SELECT * FROM ventas_empaques");
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
                    if(rs.getInt(3)==(26)) producto26+=rs.getInt(4);
                    if(rs.getInt(3)==(27)) producto27+=rs.getInt(4);
                    if(rs.getInt(3)==(28)) producto28+=rs.getInt(4);
                    if(rs.getInt(3)==(29)) producto29+=rs.getInt(4);
                    if(rs.getInt(3)==(30)) producto30+=rs.getInt(4);
                }
                if(dinero==1){
                    if(rs.getInt(3)==(1)) producto1+=(rs.getInt(4)*500);
                    if(rs.getInt(3)==(2)) producto2+=(rs.getInt(4)*300);
                    if(rs.getInt(3)==(3)) producto3+=(rs.getInt(4)*10);
                    if(rs.getInt(3)==(4)) producto4+=(rs.getInt(4)*10);
                    if(rs.getInt(3)==(5)) producto5+=(rs.getInt(4)*10);
                    if(rs.getInt(3)==(6)) producto6+=(rs.getInt(4)*20);
                    if(rs.getInt(3)==(7)) producto7+=(rs.getInt(4)*180);
                    if(rs.getInt(3)==(8)) producto8+=(rs.getInt(4)*8);
                    if(rs.getInt(3)==(9)) producto9+=(rs.getInt(4)*8);
                    if(rs.getInt(3)==(10)) producto10+=(rs.getInt(4)*8);
                    if(rs.getInt(3)==(11)) producto11+=(rs.getInt(4)*20);
                    if(rs.getInt(3)==(12)) producto12+=(rs.getInt(4)*30);
                    if(rs.getInt(3)==(13)) producto13+=(rs.getInt(4)*30);
                    if(rs.getInt(3)==(14)) producto14+=(rs.getInt(4)*20);
                    if(rs.getInt(3)==(15)) producto15+=(rs.getInt(4)*20);
                    if(rs.getInt(3)==(16)) producto16+=(rs.getInt(4)*20);
                    if(rs.getInt(3)==(17)) producto17+=(rs.getInt(4)*30);
                    if(rs.getInt(3)==(18)) producto18+=(rs.getInt(4)*40);
                    if(rs.getInt(3)==(19)) producto19+=(rs.getInt(4)*20);
                    if(rs.getInt(3)==(20)) producto20+=(rs.getInt(4)*50);
                    if(rs.getInt(3)==(21)) producto21+=(rs.getInt(4)*50);
                    if(rs.getInt(3)==(22)) producto22+=(rs.getInt(4)*50);
                    if(rs.getInt(3)==(23)) producto23+=(rs.getInt(4)*50);
                    if(rs.getInt(3)==(24)) producto24+=(rs.getInt(4)*100);
                    if(rs.getInt(3)==(25)) producto25+=(rs.getInt(4)*70);
                    if(rs.getInt(3)==(26)) producto26+=(rs.getInt(4)*50);
                    if(rs.getInt(3)==(27)) producto27+=(rs.getInt(4)*25);
                    if(rs.getInt(3)==(28)) producto28+=(rs.getInt(4)*30);
                    if(rs.getInt(3)==(29)) producto29+=(rs.getInt(4)*25);
                    if(rs.getInt(3)==(30)) producto30+=(rs.getInt(4)*500);
                }
                if(dinero==2){
                    if(rs.getInt(3)==(1)) producto1+=(rs.getInt(4)*50);
                    if(rs.getInt(3)==(2)) producto2+=(rs.getInt(4)*30);
                    if(rs.getInt(3)==(3)) producto3+=(rs.getInt(4)*3);
                    if(rs.getInt(3)==(4)) producto4+=(rs.getInt(4)*2);
                    if(rs.getInt(3)==(5)) producto5+=(rs.getInt(4)*2);
                    if(rs.getInt(3)==(6)) producto6+=(rs.getInt(4)*5);
                    if(rs.getInt(3)==(7)) producto7+=(rs.getInt(4)*20);
                    if(rs.getInt(3)==(8)) producto8+=(rs.getInt(4)*1);
                    if(rs.getInt(3)==(9)) producto9+=(rs.getInt(4)*1);
                    if(rs.getInt(3)==(10)) producto10+=(rs.getInt(4)*1);
                    if(rs.getInt(3)==(11)) producto11+=(rs.getInt(4)*5);
                    if(rs.getInt(3)==(12)) producto12+=(rs.getInt(4)*6);
                    if(rs.getInt(3)==(13)) producto13+=(rs.getInt(4)*5);
                    if(rs.getInt(3)==(14)) producto14+=(rs.getInt(4)*4);
                    if(rs.getInt(3)==(15)) producto15+=(rs.getInt(4)*4);
                    if(rs.getInt(3)==(16)) producto16+=(rs.getInt(4)*4);
                    if(rs.getInt(3)==(17)) producto17+=(rs.getInt(4)*5);
                    if(rs.getInt(3)==(18)) producto18+=(rs.getInt(4)*6);
                    if(rs.getInt(3)==(19)) producto19+=(rs.getInt(4)*5);
                    if(rs.getInt(3)==(20)) producto20+=(rs.getInt(4)*10);
                    if(rs.getInt(3)==(21)) producto21+=(rs.getInt(4)*10);
                    if(rs.getInt(3)==(22)) producto22+=(rs.getInt(4)*10);
                    if(rs.getInt(3)==(23)) producto23+=(rs.getInt(4)*10);
                    if(rs.getInt(3)==(24)) producto24+=(rs.getInt(4)*20);
                    if(rs.getInt(3)==(25)) producto25+=(rs.getInt(4)*15);
                    if(rs.getInt(3)==(26)) producto26+=(rs.getInt(4)*10);
                    if(rs.getInt(3)==(27)) producto27+=(rs.getInt(4)*5);
                    if(rs.getInt(3)==(28)) producto28+=(rs.getInt(4)*10);
                    if(rs.getInt(3)==(29)) producto29+=(rs.getInt(4)*5);
                    if(rs.getInt(3)==(30)) producto30+=(rs.getInt(4)*100);
                }
            }
        }
        con.cerrarConsulta();
        con.desconectar();
        dataset.setValue(producto1, "Ventas", "Tetrapack");
        dataset.setValue(producto2, "Ventas", "Tarrina Mantequilla");
        dataset.setValue(producto3, "Ventas", "Tapa botella leche");
        dataset.setValue(producto4, "Ventas", "Tapa botella jugos");
        dataset.setValue(producto5, "Ventas", "Caja pastas");
        dataset.setValue(producto6, "Ventas", "Botella jarabe");
        dataset.setValue(producto7, "Ventas", "Tarrina postre");
        dataset.setValue(producto8, "Ventas", "Cuchara");
        dataset.setValue(producto9, "Ventas", "Cuchillo");
        dataset.setValue(producto10, "Ventas", "Tenedor");
        dataset.setValue(producto11, "Ventas", "Plato pando");
        dataset.setValue(producto12, "Ventas", "Plato hondo");
        dataset.setValue(producto13, "Ventas", "Plato semihondo");
        dataset.setValue(producto14, "Ventas", "Vaso peq icopor");
        dataset.setValue(producto15, "Ventas", "Vaso med icopor");
        dataset.setValue(producto16, "Ventas", "Vasija icopor");
        dataset.setValue(producto17, "Ventas", "Vasija plastico");
        dataset.setValue(producto18, "Ventas", "Copa plastico");
        dataset.setValue(producto19, "Ventas", "Bandeja");
        dataset.setValue(producto20, "Ventas", "Plato icopor");
        dataset.setValue(producto21, "Ventas", "Contenedor tres");
        dataset.setValue(producto22, "Ventas", "Contenedor uno");
        dataset.setValue(producto23, "Ventas", "Contenedor cuatro");
        dataset.setValue(producto24, "Ventas", "Contenedor aluminio g");
        dataset.setValue(producto25, "Ventas", "Contenedor aluminio m");
        dataset.setValue(producto26, "Ventas", "Contenedor aluminio p");
        dataset.setValue(producto27, "Ventas", "Malla para redondos");
        dataset.setValue(producto28, "Ventas", "Panales de huevos");
        dataset.setValue(producto29, "Ventas", "Bandeja baja");
        dataset.setValue(producto30, "Ventas", "Nevera de carnes");

        return dataset;
    }

    private JFreeChart crearChart(CategoryDataset data) {
        String nombre = null;
        if(dinero==2){
            nombre="Costo en Ventas(COP)";
        }
        if(dinero==1){
            nombre="Dinero en Ventas (COP)";
        }
        if(dinero==0){
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
        if(dinero==2){
        GradientPaint gp = new GradientPaint(0.0f, 0.0f, Color.red, 0.0f, 0.0f, new Color(0, 64, 0));
        renderer.setSeriesPaint(0, gp);}

        CategoryAxis domainAxis = plot.getDomainAxis();
        domainAxis.setCategoryLabelPositions(CategoryLabelPositions.createUpRotationLabelPositions(Math.PI / 6.0));

        return chart;
    }
}
