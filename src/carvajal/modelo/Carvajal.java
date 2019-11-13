package carvajal.modelo;

import carvajal.vista.GUI;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;
import javax.swing.JOptionPane;

/**
 *
 * @author Santiago Romero
 */
public class Carvajal {

    boolean creado = false;
    boolean ya = false;
    
    public static void main(String[] args) {
        Carvajal carvajal = new Carvajal();
    }
    
    public Carvajal() {
        try {
            Class.forName("com.mysql.jdbc.Driver");
            Connection conexion2 = DriverManager.getConnection("jdbc:mysql://localhost/", "root", "");
            String Query = "USE `carvajal`;";
            Statement s1 = conexion2.createStatement();
            s1.executeUpdate(Query);
            conexion2.close();
            ya = true;
        } catch (Exception x) {
            ya = false;
            System.out.println(x);
        }
        if (ya == false) {
            crearDB();
            if (creado == true) {
                GUI frame = new GUI();
                frame.setVisible(true);
            }
            if (creado == false) {
                System.exit(0);
            }
        }
        if (ya == true) {
            GUI frame = new GUI();
            frame.setVisible(true);
        }

    }

    public void crearDB() {
        try {
            Class.forName("com.mysql.jdbc.Driver");
            Connection conexion = DriverManager.getConnection("jdbc:mysql://localhost/", "root", "");

            String Query1 = "CREATE DATABASE IF NOT EXISTS `carvajal` /*!40100 DEFAULT CHARACTER SET utf8 COLLATE utf8_spanish_ci */;\n";
            String Query2 = "USE `carvajal`;";
            String Query3 = "CREATE TABLE IF NOT EXISTS `cliente` (\n"
                    + "`nit` int(11) NOT NULL,\n"
                    + "`tipo` varchar(50) COLLATE utf8_spanish_ci DEFAULT NULL,\n"
                    + "`nombre` varchar(50) COLLATE utf8_spanish_ci DEFAULT NULL,\n"
                    + "`apellido` varchar(50) COLLATE utf8_spanish_ci DEFAULT NULL,\n"
                    + "`genero` varchar(1) COLLATE utf8_spanish_ci DEFAULT NULL,\n"
                    + "`telefono` bigint(20) DEFAULT NULL,\n"
                    + "`correo` varchar(50) COLLATE utf8_spanish_ci DEFAULT NULL,\n"
                    + "PRIMARY KEY (`nit`)\n"
                    + ") ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_spanish_ci;\n"
                    + "\n";
            String Query4 = "/*!40000 ALTER TABLE `cliente` DISABLE KEYS */;\n";
            String Query5 = "/*!40000 ALTER TABLE `cliente` ENABLE KEYS */;\n";

            String Query6 = "CREATE TABLE IF NOT EXISTS `compra_proveedor` (\n"
                    + "`id` int(11) NOT NULL,\n"
                    + "`nit_proveedor` int(11) DEFAULT NULL,\n"
                    + "`ref_producto` int(11) DEFAULT NULL,\n"
                    + "`descripcion` varchar(50) COLLATE utf8_spanish_ci DEFAULT NULL,\n"
                    + "`cantidad` int(11) DEFAULT NULL,\n"
                    + "`precio_unidad` int(11) DEFAULT NULL,\n"
                    + "PRIMARY KEY (`id`),\n"
                    + "KEY `FK__proveedores` (`nit_proveedor`),\n"
                    + "CONSTRAINT `FK__proveedores` FOREIGN KEY (`nit_proveedor`) REFERENCES `proveedor` (`nit`) ON DELETE CASCADE ON UPDATE CASCADE\n"
                    + ") ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_spanish_ci;\n";

            String Query7 = "/*!40000 ALTER TABLE `compra_proveedor` DISABLE KEYS */;\n";

            String Query8 = "/*!40000 ALTER TABLE `compra_proveedor` ENABLE KEYS */;\n";

            String Query9 = "CREATE TABLE IF NOT EXISTS `productos_empaques` (\n"
                    + "`ref` int(11) NOT NULL,\n"
                    + "`descripcion` varchar(50) COLLATE utf8_spanish_ci DEFAULT NULL,\n"
                    + "`categoria` varchar(50) COLLATE utf8_spanish_ci DEFAULT NULL,\n"
                    + "`costo` bigint(20) DEFAULT NULL,\n"
                    + "`precio` bigint(20) DEFAULT NULL,\n"
                    + "PRIMARY KEY (`ref`)\n"
                    + ") ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_spanish_ci;\n";

            String Query10 = "/*!40000 ALTER TABLE `productos_empaques` DISABLE KEYS */;\n";

            String Query11 = "INSERT INTO `productos_empaques` (`ref`, `descripcion`, `categoria`, `costo`, `precio`) VALUES\n"
                    + "(1, 'Tetrapack', 'Lacteos', 50, 500),\n"
                    + "(2, 'Tarrina Mantequilla', 'Lacteos', 30, 300),\n"
                    + "(3, 'Tapa botella leche', 'Lacteos', 3, 10),\n"
                    + "(4, 'Tapa botella jugos', 'Frutas', 2, 10),\n"
                    + "(5, 'Caja pastas', 'Farmacos', 2, 10),\n"
                    + "(6, 'Botella jarabe', 'Farmacos', 5, 20),\n"
                    + "(7, 'Tarrina postre', 'Frutas', 20, 180),\n"
                    + "(8, 'Cuchara', 'Utensilios', 1, 8),\n"
                    + "(9, 'Cuchillo', 'Utensilios', 1, 8),\n"
                    + "(10, 'Tenedor', 'Utensilios', 1, 8),\n"
                    + "(11, 'Plato pando', 'Utensilios', 5, 20),\n"
                    + "(12, 'Plato hondo', 'Utensilios', 6, 30),\n"
                    + "(13, 'Plato semihondo', 'Utensilios', 5, 30),\n"
                    + "(14, 'Vaso peq icopor', 'Utensilios', 4, 20),\n"
                    + "(15, 'Vaso med icopor', 'Utensilios', 4, 20),\n"
                    + "(16, 'Vasija icopor', 'Utensilios', 4, 20),\n"
                    + "(17, 'Vasija plastico', 'Utensilios', 5, 30),\n"
                    + "(18, 'Copa plastico', 'Utensilios', 6, 40),\n"
                    + "(19, 'Bandeja', 'Utensilios', 5, 20),\n"
                    + "(20, 'Plato icopor', 'Utensilios', 10, 50),\n"
                    + "(21, 'Contenedor de tres', 'Contenedor', 10, 50),\n"
                    + "(22, 'Contenedor de uno', 'Contenedor', 10, 50),\n"
                    + "(23, 'Contenedor de cuatro', 'Contenedor', 10, 50),\n"
                    + "(24, 'Contenedor aluminio grande', 'Contenedor', 20, 100),\n"
                    + "(25, 'Contenedor aluminio mediano', 'Contenedor', 15, 70),\n"
                    + "(26, 'Contenedor aluminio peque', 'Contenedor ', 10, 50),\n"
                    + "(27, 'Malla para redondos', 'Frutas', 5, 25),\n"
                    + "(28, 'Panales de huevos', 'Contenedor', 10, 30),\n"
                    + "(29, 'Bandeja baja', 'Carnes', 5, 25),\n"
                    + "(30, 'Nevera de carnes', 'Carnes', 100, 500);\n";

            String Query12 = "/*!40000 ALTER TABLE `productos_empaques` ENABLE KEYS */;\n";

            String Query13 = "CREATE TABLE IF NOT EXISTS `productos_mepal` (\n"
                    + "  `ref` int(11) NOT NULL,\n"
                    + "  `descripcion` varchar(50) COLLATE utf8_spanish_ci DEFAULT NULL,\n"
                    + "  `categoria` varchar(50) COLLATE utf8_spanish_ci DEFAULT NULL,\n"
                    + "  `costo` bigint(20) DEFAULT NULL,\n"
                    + "  `precio` bigint(20) DEFAULT NULL,\n"
                    + "  PRIMARY KEY (`ref`)\n"
                    + ") ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_spanish_ci;\n";

            String Query14 = "/*!40000 ALTER TABLE `productos_mepal` DISABLE KEYS */;\n";

            String Query15 = "INSERT INTO `productos_mepal` (`ref`, `descripcion`, `categoria`, `costo`, `precio`) VALUES\n"
                    + "(1, 'Pupitre Academico simple', 'Colegio', 10000, 70000),\n"
                    + "(2, 'Pupitre Academico conjunto', 'Colegio', 10000, 90000),\n"
                    + "(3, 'Pupitre Academico computador', 'Colegio', 10000, 75000),\n"
                    + "(4, 'Silla litte', 'Hotel/Restaurante', 10000, 90000),\n"
                    + "(5, 'Silla perillo', 'Hotel/Restaurante', 8000, 75000),\n"
                    + "(6, 'Silla drop', 'Hotel/Restaurante', 8000, 75000),\n"
                    + "(7, 'Silla Class', 'Hotel/Restaurante', 8000, 75000),\n"
                    + "(8, 'Silla Moema', 'Hotel/Restaurante', 8000, 70000),\n"
                    + "(9, 'Silla Fogo', 'Hotel/Restaurante', 9000, 80000),\n"
                    + "(10, 'Silla oficina', 'Oficina', 7000, 90000),\n"
                    + "(11, 'Silla Contessa', 'Oficina', 7000, 90000),\n"
                    + "(12, 'Silla Sabrina', 'Oficina', 7000, 90000),\n"
                    + "(13, 'Silla Bravo', 'Oficina', 7000, 90000),\n"
                    + "(14, 'Silla Equity', 'Oficina', 7000, 90000),\n"
                    + "(15, 'Silla Lai', 'Oficina', 8000, 95000),\n"
                    + "(16, 'Silla Luxo', 'Oficina', 8000, 97000),\n"
                    + "(17, 'Silla Visconte', 'Oficina', 8000, 95000),\n"
                    + "(18, 'Division vetro', 'Oficina', 10000, 120000),\n"
                    + "(19, 'Division recepcion', 'Oficina', 10000, 120000),\n"
                    + "(20, 'Division multiple', 'Oficina', 11000, 150000),\n"
                    + "(21, 'Archivador Koncisa', 'Oficina', 20000, 220000),\n"
                    + "(22, 'Archivo multiple zen', 'Oficina', 20000, 250000),\n"
                    + "(23, 'Escritorio Koncisa', 'Oficina', 15000, 200000),\n"
                    + "(24, 'Mesa reunion', 'Oficina', 20000, 500000),\n"
                    + "(25, 'Mesa ejecutiva', 'Oficina', 10000, 400000);\n";

            String Query16 = "/*!40000 ALTER TABLE `productos_mepal` ENABLE KEYS */\n";

            String Query17 = "CREATE TABLE IF NOT EXISTS `productos_propal` (\n"
                    + "`ref` int(11) NOT NULL,\n"
                    + "`descripcion` varchar(50) COLLATE utf8_spanish_ci DEFAULT NULL,\n"
                    + "`costo` bigint(20) DEFAULT NULL,\n"
                    + "`precio` bigint(20) DEFAULT NULL,\n"
                    + "PRIMARY KEY (`ref`)\n"
                    + ") ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_spanish_ci;\n";

            String Query18 = "/*!40000 ALTER TABLE `productos_propal` DISABLE KEYS */;\n";

            String Query19 = "INSERT INTO `productos_propal` (`ref`, `descripcion`, `costo`, `precio`) VALUES\n"
                    + "	(1, 'Papel fotografia', 1, 10),\n"
                    + "	(2, 'Papel esmaltado', 2, 15),\n"
                    + "	(3, 'Papel no esmaltado', 1, 13),\n"
                    + "	(4, 'Propalcopia', 2, 20),\n"
                    + "	(5, 'Propalpac', 2, 20);\n";

            String Query20 = "/*!40000 ALTER TABLE `productos_propal` ENABLE KEYS */;\n";

            String Query21 = "CREATE TABLE IF NOT EXISTS `productos_sol_com` (\n"
                    + "  `ref` int(11) NOT NULL,\n"
                    + "  `descripcion` varchar(50) COLLATE utf8_spanish_ci DEFAULT NULL,\n"
                    + "  `categoria` varchar(50) COLLATE utf8_spanish_ci DEFAULT NULL,\n"
                    + "  `costo` bigint(20) DEFAULT NULL,\n"
                    + "  `precio` bigint(20) DEFAULT NULL,\n"
                    + "  PRIMARY KEY (`ref`)\n"
                    + ") ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_spanish_ci;\n";

            String Query22 = "/*!40000 ALTER TABLE `productos_sol_com` DISABLE KEYS */;\n";

            String Query23 = "INSERT INTO `productos_sol_com` (`ref`, `descripcion`, `categoria`, `costo`, `precio`) VALUES\n"
                    + "	(1, 'Campaña e-mailing', 'Mercadeo', 200000, 1200000),\n"
                    + "	(2, 'Canales digitales', 'Mercadeo', 150000, 1000000),\n"
                    + "	(3, 'Construccion big data', 'Mercadeo', 300000, 5000000),\n"
                    + "	(4, 'Consolidacion productos', 'Datos', 100000, 800000),\n"
                    + "	(5, 'Aplicacion reglas negocio', 'Datos', 100000, 800000),\n"
                    + "	(6, 'Segmentaciones', 'Datos', 50000, 400000),\n"
                    + "	(7, 'Transpromo', 'Datos', 100000, 800000),\n"
                    + "	(8, 'Impresion digital', 'Impresion', 20000, 100000),\n"
                    + "	(9, 'Impresion inkjet', 'Impresion', 40000, 200000),\n"
                    + "	(10, 'Publicacion electronica', 'Electronicos', 10000, 90000),\n"
                    + "	(11, 'Envio de documentos', 'Electronicos', 100000, 800000),\n"
                    + "	(12, 'Apps Android', 'Electronicos', 5000000, 18000000),\n"
                    + "	(13, 'Apps iOS', 'Electronicos', 5000000, 18000000),\n"
                    + "	(14, 'Apps Windows', 'Electronicos', 5000000, 18000000),\n"
                    + "	(15, 'Factura electronica', 'Electronicos', 40000, 700000),\n"
                    + "	(16, 'Pagos en linea', 'Electronicos', 40000, 700000),\n"
                    + "	(17, 'Servicio postal', 'Electronicos', 40000, 700000);\n";

            String Query24 = "/*!40000 ALTER TABLE `productos_sol_com` ENABLE KEYS */;\n";

            String Query25 = "CREATE TABLE IF NOT EXISTS `proveedor` (\n"
                    + "  `nit` int(11) NOT NULL,\n"
                    + "  `nombre` varchar(50) COLLATE utf8_spanish_ci DEFAULT NULL,\n"
                    + "  PRIMARY KEY (`nit`)\n"
                    + ") ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_spanish_ci;\n";

            String Query26 = "/*!40000 ALTER TABLE `proveedor` DISABLE KEYS */;\n";

            String Query27 = "INSERT INTO `proveedor` (`nit`, `nombre`) VALUES\n"
                    + "	(123001, 'Pichichi'),\n"
                    + "	(123002, 'Aceroscol'),\n"
                    + "	(123003, 'Agricola Himalaya'),\n"
                    + "	(123004, 'RH SAS'),\n"
                    + "	(123005, 'Riopaila Castilla'),\n"
                    + "	(123006, 'SIESA'),\n"
                    + "	(123007, 'Manuelita'),\n"
                    + "	(123008, 'Gases de occidente'),\n"
                    + "	(123009, 'Electrojaponesa'),\n"
                    + "	(123010, 'Cementos San Marcos');";

            String Query28 = "/*!40000 ALTER TABLE `proveedor` ENABLE KEYS */;\n";

            String Query29 = "CREATE TABLE IF NOT EXISTS `ventas_empaques` (\n"
                    + "  `id` int(11) NOT NULL,\n"
                    + "  `nit_cliente` int(11) DEFAULT NULL,\n"
                    + "  `ref_producto` int(11) DEFAULT NULL,\n"
                    + "  `cantidad` int(11) DEFAULT NULL,\n"
                    + "  `fecha` date DEFAULT NULL,\n"
                    + "  `hora` time DEFAULT NULL,\n"
                    + "  `sucursal` varchar(50) COLLATE utf8_spanish_ci DEFAULT NULL,\n"
                    + "  PRIMARY KEY (`id`),\n"
                    + "  KEY `FK__productos_empaques` (`ref_producto`),\n"
                    + "  KEY `FK_ventas_empaques_cliente` (`nit_cliente`),\n"
                    + "  CONSTRAINT `FK__productos_empaques` FOREIGN KEY (`ref_producto`) REFERENCES `productos_empaques` (`ref`) ON DELETE CASCADE ON UPDATE CASCADE,\n"
                    + "  CONSTRAINT `FK_ventas_empaques_cliente` FOREIGN KEY (`nit_cliente`) REFERENCES `cliente` (`nit`) ON DELETE CASCADE ON UPDATE CASCADE\n"
                    + ") ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_spanish_ci;\n";

            String Query30 = "/*!40000 ALTER TABLE `ventas_empaques` DISABLE KEYS */;\n";
            String Query31 = "/*!40000 ALTER TABLE `ventas_empaques` ENABLE KEYS */;\n";

            String Query32 = "CREATE TABLE IF NOT EXISTS `ventas_mepal` (\n"
                    + "  `id` int(11) NOT NULL,\n"
                    + "  `nit_cliente` int(11),\n"
                    + "  `ref_producto` int(11),\n"
                    + "  `cantidad` int(11) DEFAULT NULL,\n"
                    + "  `fecha` date DEFAULT NULL,\n"
                    + "  `hora` time DEFAULT NULL,\n"
                    + "  `sucursal` varchar(50) COLLATE utf8_spanish_ci DEFAULT NULL,\n"
                    + "  PRIMARY KEY (`id`),\n"
                    + "  KEY `FK__productos_mepal` (`ref_producto`),\n"
                    + "  KEY `FK_ventas_mepal_cliente` (`nit_cliente`),\n"
                    + "  CONSTRAINT `FK__productos_mepal` FOREIGN KEY (`ref_producto`) REFERENCES `productos_mepal` (`ref`) ON DELETE CASCADE ON UPDATE CASCADE,\n"
                    + "  CONSTRAINT `FK_ventas_mepal_cliente` FOREIGN KEY (`nit_cliente`) REFERENCES `cliente` (`nit`) ON DELETE CASCADE ON UPDATE CASCADE\n"
                    + ") ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_spanish_ci;";

            String Query33 = "/*!40000 ALTER TABLE `ventas_mepal` DISABLE KEYS */;\n";
            String Query34 = "/*!40000 ALTER TABLE `ventas_mepal` ENABLE KEYS */;\n";
            String Query35 = "CREATE TABLE IF NOT EXISTS `ventas_propal` (\n"
                    + "  `id` int(11) NOT NULL,\n"
                    + "  `nit_cliente` int(11) DEFAULT NULL,\n"
                    + "  `ref_producto` int(11) DEFAULT NULL,\n"
                    + "  `cantidad` int(11) DEFAULT NULL,\n"
                    + "  `fecha` date DEFAULT NULL,\n"
                    + "  `hora` time DEFAULT NULL,\n"
                    + "  `sucursal` varchar(50) COLLATE utf8_spanish_ci DEFAULT NULL,\n"
                    + "  PRIMARY KEY (`id`),\n"
                    + "  KEY `FK_ventas_propal_cliente` (`nit_cliente`),\n"
                    + "  KEY `FK_ventas_propal_productos_propal` (`ref_producto`),\n"
                    + "  CONSTRAINT `FK_ventas_propal_cliente` FOREIGN KEY (`nit_cliente`) REFERENCES `cliente` (`nit`) ON DELETE CASCADE ON UPDATE CASCADE,\n"
                    + "  CONSTRAINT `FK_ventas_propal_productos_propal` FOREIGN KEY (`ref_producto`) REFERENCES `productos_propal` (`ref`) ON DELETE CASCADE ON UPDATE CASCADE\n"
                    + ") ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_spanish_ci;";

            String Query36 = "/*!40000 ALTER TABLE `ventas_propal` DISABLE KEYS */;\n";
            String Query37 = "/*!40000 ALTER TABLE `ventas_propal` ENABLE KEYS */;\n";

            String Query38 = "CREATE TABLE IF NOT EXISTS `ventas_sol_com` (\n"
                    + "  `id` int(11) NOT NULL,\n"
                    + "  `nit_cliente` int(11) DEFAULT NULL,\n"
                    + "  `ref_producto` int(11) DEFAULT NULL,\n"
                    + "  `cantidad` int(11) DEFAULT NULL,\n"
                    + "  `fecha` date DEFAULT NULL,\n"
                    + "  `hora` time DEFAULT NULL,\n"
                    + "  `sucursal` varchar(50) COLLATE utf8_spanish_ci DEFAULT NULL,\n"
                    + "  PRIMARY KEY (`id`),\n"
                    + "  KEY `FK__productos_sol_com` (`ref_producto`),\n"
                    + "  KEY `FK_ventas_sol_com_cliente` (`nit_cliente`),\n"
                    + "  CONSTRAINT `FK__productos_sol_com` FOREIGN KEY (`ref_producto`) REFERENCES `productos_sol_com` (`ref`) ON DELETE CASCADE ON UPDATE CASCADE,\n"
                    + "  CONSTRAINT `FK_ventas_sol_com_cliente` FOREIGN KEY (`nit_cliente`) REFERENCES `cliente` (`nit`) ON DELETE CASCADE ON UPDATE CASCADE\n"
                    + ") ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_spanish_ci;";

            String Query39 = "/*!40000 ALTER TABLE `ventas_sol_com` DISABLE KEYS */;\n";
            String Query40 = "/*!40000 ALTER TABLE `ventas_sol_com` ENABLE KEYS */;\n";
            String Query41 = "/*!40101 SET SQL_MODE=IFNULL(@OLD_SQL_MODE, '') */;\n";

            Statement s1 = conexion.createStatement();
            s1.executeUpdate(Query1);
            s1.executeUpdate(Query2);
            s1.executeUpdate(Query3);
            s1.executeUpdate(Query4);
            s1.executeUpdate(Query5);
            s1.executeUpdate(Query9);
            s1.executeUpdate(Query10);

            s1.executeUpdate(Query12);
            s1.executeUpdate(Query13);
            s1.executeUpdate(Query14);

            s1.executeUpdate(Query16);
            s1.executeUpdate(Query17);
            s1.executeUpdate(Query18);

            s1.executeUpdate(Query20);
            s1.executeUpdate(Query21);
            s1.executeUpdate(Query22);

            s1.executeUpdate(Query24);
            s1.executeUpdate(Query25);
            s1.executeUpdate(Query6);
            s1.executeUpdate(Query7);
            s1.executeUpdate(Query8);
            s1.executeUpdate(Query26);

            s1.executeUpdate(Query28);
            s1.executeUpdate(Query29);
            s1.executeUpdate(Query30);
            s1.executeUpdate(Query31);
            s1.executeUpdate(Query32);
            s1.executeUpdate(Query33);
            s1.executeUpdate(Query34);
            s1.executeUpdate(Query35);
            s1.executeUpdate(Query36);
            s1.executeUpdate(Query37);
            s1.executeUpdate(Query38);
            s1.executeUpdate(Query39);
            s1.executeUpdate(Query40);
            s1.executeUpdate(Query41);
            s1.executeUpdate(Query11);
            s1.executeUpdate(Query15);
            s1.executeUpdate(Query19);
            s1.executeUpdate(Query23);
            s1.executeUpdate(Query27);
            conexion.close();
            creado = true;
        } catch (Exception x) {
            System.out.println(x);
            creado = false;
        }
        if (creado == false) {
            JOptionPane.showMessageDialog(null, "Ha ocurrido un, con la base de datos");
        }
        if (creado == true) {
            JOptionPane.showMessageDialog(null, "Base de datos correcta");
        }
    }
}
