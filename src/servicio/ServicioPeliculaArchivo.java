package servicio;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.PrintWriter;

import dominio.Pelicula;

public class ServicioPeliculaArchivo implements IServicioPeliculas {


    private final String NOMBRE_ARCHIVO = "peliculas.txt";

    public ServicioPeliculaArchivo(){
        var archivo = new File(NOMBRE_ARCHIVO);
        try{
            if(archivo.exists()){
                System.out.println("El archivo ya existe!");
            }
            else{
                var salida = new PrintWriter(new FileWriter(archivo));
                salida.close();
                System.out.println("Se creo correctamente el archivo");
            }

            

        } catch (Exception e){
            System.out.println("[ERROR] - No se pudo abrir el archivo : " + e.getMessage());
        }

        
    }


    @Override
    public void agregarPelicula(Pelicula pelicula) {
        boolean hit = false;
        var archivo = new File(NOMBRE_ARCHIVO);
        try{
            hit = archivo.exists();
            var salida = new PrintWriter(new FileWriter(archivo, hit));
            //Agregamos pelicula
            salida.println(pelicula);
            salida.close();
            System.out.println("Se agrego la correctamente la pelicula: " + pelicula);

        } catch(Exception e){
            System.out.println("[ERROR] - No se pudo agregar la pelicula: " + e.getMessage());
        }
    }

    @Override
    public void buscarPelicula(Pelicula pelicula) {
        var archivo = new File(NOMBRE_ARCHIVO);
        try{
            var entrada = new BufferedReader(new FileReader(archivo));
            String lineaTexto;
            lineaTexto = entrada.readLine();
            var indice = 1;
            var encontrada = false;
            var peliculaBuscar = pelicula.getNombre();

            while(lineaTexto != null){
                //Buscamos
                if(peliculaBuscar != null && peliculaBuscar.equalsIgnoreCase(lineaTexto)){
                    encontrada = true;
                    break;
                }
                //Leemos la siguiente linea
                lineaTexto = entrada.readLine();
                indice++;
            }
            if(encontrada){
                System.out.println("Pelicula " + lineaTexto + " encontrada - Linea: "+ indice);
            }
            else{
                System.out.println("No se ha encontrado la pelicula: " + pelicula.getNombre());
            }
            entrada.close();

        } catch(Exception e){
            System.out.println("[ERROR] - No se pudo agregar la pelicula: " + e.getMessage());
        }
    }

    @Override
    public void listarPeliculas() {
        //Trabajo con archivos.

        var archivo = new File(NOMBRE_ARCHIVO);
        try{
            System.out.println("Listado de Peliculas");
            var entrada = new BufferedReader(new FileReader(archivo));
            String linea;
            linea = entrada.readLine();
            //Leer las lineas.
            while(linea != null){
                var pelicula = new Pelicula(linea);
                System.out.println(pelicula);
                //Volvemos a leer la siguiente linea.
                linea = entrada.readLine();
            }

            //cerramos el archivo
            entrada.close();
        } catch(Exception e){
            System.out.println("[ERROR] - No se pudo leer el archivo :" + e.getMessage());
        }
    }
    
}
