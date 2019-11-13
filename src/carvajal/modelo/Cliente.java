/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package carvajal.modelo;

import carvajal.controlador.File_Clientes;

/**
 *
 * @author Santiago Romero
 */
public class Cliente {

    //nit,tipo,nombre,apellido,genero,telefono,correo
    public boolean error;
    String tipo[] = {
        "Natural", "Juridica"};

    String nombres[] = {
        "Santiago", "Sebastian", "Samuel", "Alejandro", "Nicolas", "Carlos", "Daniel",
        "David", "Andres", "Felipe", "Miguel", "Juan", "Jose", "Diego", "Esteban",
        "Camilo", "Jhon", "Adrian", "Harold", "Henry", "Oscar", "Emilio", "Jairo",
        "Orlando", "Mauricio",
        "Valentina", "Mariana", "Isabella", "Daniela", "Valeria", "Sofia", "Natalia",
        "Gabriela", "Juliana", "Maria", "Luisa", "Camila", "Fernanda", "Alejandra",
        "Laura", "Paula", "Andrea", "Ana", "Paola", "Marta", "Lorena", "Manuela",
        "Adriana", "Angela", "Victoria"};

    String apellidos[] = {
        "Rodriguez", "Gomez", "Lopez", "Gonzales", "Garcia", "Martinez", "Ramirez",
        "Sanchez", "Hernandez", "Diaz", "Perez", "Torres", "Rojas", "Vargas", "Moreno",
        "Gutierrez", "Jimenez", "Munoz", "Castro", "Ortiz", "Alvarez", "Ruiz",
        "Suiarez", "Romero", "Herrera", "Valencia", "Quintero", "Restrepo", "Giraldo",
        "Morales", "Mejia", "Arias", "Parra", "Jaramillo", "Cardenas", "Osorio",
        "Castillo", "Salazar", "Cardona", "Florez", "Medina", "Rivera", "Montoya",
        "Cortes", "Correa", "Marin", "Rincon", "Zapata", "Escobar", "Velasquez"};

    String generos[] = {
        "M", "M", "M", "M", "M", "M", "M", "M", "M", "M", "M", "M", "M", "M", "M", "M", "M", "M",
        "M", "M", "M", "M", "M", "M", "M", "F", "F", "F", "F", "F", "F", "F", "F", "F", "F", "F",
        "F", "F", "F", "F", "F", "F", "F", "F", "F", "F", "F", "F", "F", "F",};

    String correos[] = {
        "@hotmail.com", "@hotmail.es", "@outlook.com", "@outlook.es", "@gmail.com",
        "@yahoo.com", "@empresa.com", "@mail.com", "@inbox.com", "@persona.com"};

    public void generar(int cantRegistros) {
        int ale = 0;
        int gen = 0;
        String registro = "";
        String elcorreo = "", nomcorre = "", apecorre = "";
        int nit = 1132344760;
        int telefono = 0;
        int contM = 0, contF = 0;
        File_Clientes fc = new File_Clientes();
        fc.crear();
        fc.error = error;
        if (!fc.error) {

            for (int i = 0; i < cantRegistros; i++) {
                registro = nit + ";";

                ale = (int) (Math.random() * 2);//selecciona un tipo
                registro += tipo[ale] + ";";

                ale = (int) (Math.random() * 50);//selecciona un nombre
                registro += nombres[ale] + ";";
                nomcorre = nombres[ale].substring(0, 3);
                gen = ale;

                ale = (int) (Math.random() * 50);//selecciona un apellido
                registro += apellidos[ale] + ";";
                apecorre = apellidos[ale].substring(0, 3);

                registro += generos[gen] + ";";
                if (generos[gen].equals("M")) {
                    contM++;
                } else {
                    contF++;
                }
                gen = 0;

                telefono = (int) Math.floor(Math.random() * (2399999 - 2200000 + 1) + 2300000);
                registro += telefono + ";";

                ale = (int) (Math.random() * 10);//selecciona un correo
                elcorreo = nomcorre + apecorre + (ale + "") + (ale + 1) + correos[ale];
                registro += elcorreo + ";";

                System.out.println(registro);
                fc.guardar(registro);
                nit++;
            }//fin del for

            fc.cerrar();
            System.out.println("Cant de Hombres = " + contM);
            System.out.println("Cant de Mujeres = " + contF);
        }

        //En otro ladoFile_Clientes_DB lfcdb = new DB_Clientes("Clientes.csv");
        //lfcdb.guardar();
    }

}
