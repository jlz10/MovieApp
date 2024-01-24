package presentacion;

import java.util.Scanner;

import dominio.Pelicula;
import servicio.IServicioPeliculas;
import servicio.ServicioPeliculaArchivo;    

public class CatalogoPeliculasApp {
    public static void main(String[] args)  {
        var salir = false;
        var consola = new Scanner(System.in);
        IServicioPeliculas servicioPeliculas = new ServicioPeliculaArchivo();

        while(!salir){
            try{
                mostrarMenu();
                salir = ejecutarOpciones(consola, servicioPeliculas);
            } catch(Exception e){
                System.out.println("[ERROR] - " + e.getMessage());
            }
            System.out.println();
        }
    }

    private static void mostrarMenu(){
        System.out.print("""
            *** Catalogo de Pelicula ***
            1. Agregar pelicula
            2. Listar peliculas
            3. Buscar pelicula
            4. Salir.
            Elige una opcion:
            """);
    }


    private static boolean ejecutarOpciones(Scanner consola, IServicioPeliculas servicioPeliculas){
        var opcion = Integer.parseInt(consola.nextLine());
        var salir = false;

        switch (opcion){
            case 1 -> {
                System.out.println("Introduce el nombre de la pelicula: ");
                var nombrePelicula = consola.nextLine();
                servicioPeliculas.agregarPelicula(new Pelicula(nombrePelicula));
                
            }

            case 2 -> servicioPeliculas.listarPeliculas();
            case 3 -> {
                System.out.println("Introduce la pelicula a buscar: ");
                var buscar = consola.nextLine();
                servicioPeliculas.buscarPelicula(new Pelicula(buscar));
            }

            case 4 -> {
                System.out.println("Goodbye!!");
                salir = true;
            }

            default -> System.out.println("No existe la opcion " + opcion);
        }
        return salir;
    }
}
