package ads.practica3;
import java.util.ArrayList;
import java.io.BufferedReader;
import java.io.FileReader;


/**
 * Esta clase modela los electrodomésticos de tipo televisión
 * 
 * @author Miguel Escribano miguel.escribanoo@estudiante.uam.es
 * @author Paula Samper paula.samper@estudiante.uam.es
 */

public class LecturaElectrodomesticos {

    public static ArrayList<Electrodomestico> leer (String file){
        String buf;
        ArrayList<Electrodomestico> lista = new ArrayList<Electrodomestico>();

        try{
            BufferedReader f = new BufferedReader(new FileReader(file));

            while ((buf = f.readLine()) != null) {
                int flag = 1;
                String[] args = buf.split("=");
                Electrodomestico e;

                if (args.length == 5 ) e = crearTelevision(args);
                else if (args.length == 9 ) e = crearFrigorifico(args);
                else e =  crearLavadora(args);

                for (Electrodomestico u: lista) if (u.equals(e)) flag = 0;

                if (flag == 1) lista.add(e);
                else {
                    System.out.println("Duplicado no añadido:");
                    System.out.println(e);
                } 
            }
            f.close();

        } catch(Exception e){
            System.err.println("Archivo no encontrado");
        }

        
        return lista;
    }

    


    public static Electrodomestico crearTelevision(String[] args){
        Electrodomestico e = new Television(args[0], args[1], Double.parseDouble(args[2]), ClaseEnergetica.valueOf(args[3]), Integer.parseInt(args[4]));
        return e;
    }

    public static Electrodomestico crearLavadora(String[] args) {
        if (args.length == 8){
            Electrodomestico e = new Lavadora(args[0], args[1], Double.parseDouble(args[2]), ClaseEnergetica.valueOf(args[3]), 
            new Dimension(Double.parseDouble(args[4]), Double.parseDouble(args[5]),Double.parseDouble(args[6])), Double.parseDouble(args[7]));
            
            return e;
        }

        Electrodomestico e = new Lavadora(args[0], args[1], Double.parseDouble(args[2]), ClaseEnergetica.valueOf(args[3]), 
        new Dimension(Double.parseDouble(args[4]), Double.parseDouble(args[5]),Double.parseDouble(args[6])), Double.parseDouble(args[7]), 
        Double.parseDouble(args[8]),Integer.parseInt(args[9]));
        
        return e;
    }
    
    public static Electrodomestico crearFrigorifico(String[] args){
        Electrodomestico e = new Frigorifico(args[0], args[1], Double.parseDouble(args[2]), 
        ClaseEnergetica.valueOf(args[3]), new Dimension(Double.parseDouble(args[4]),Double.parseDouble(args[5]),Double.parseDouble(args[6])),
        Double.parseDouble(args[7]), Boolean.parseBoolean(args[8]));
        return e;
    }

}