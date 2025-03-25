/**
 * Universidad del Valle de Guatemala
 * Algoritmos y Estructuras de Datos
 * Ing. Douglas Barrios / Aux: Cristian Túnchez
 * @author: Marcela Castillo y Andrés Ismalej
*/
package org.example;

import java.util.List;
import java.util.Scanner;
import java.io.IOException;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        try {
            // Crear un objeto ReadFile para leer el archivo CSV
            ReadFile readFile = new ReadFile("home appliance skus lowes.csv");

            // Leer los productos desde el archivo
            List<Producto> productos = readFile.readProducts();

            // Crear un árbol binario de búsqueda para almacenar los productos
            BST<Producto> bst = new BST<>();

            // Insertar productos en el árbol
            for (Producto producto : productos) {
                bst.insertar(producto);
            }

            //Mostrar los productos en orden (inOrden)
            System.out.println("Productos en orden (inOrden):");
            bst.inOrden();

            int op;
            do {
                // Mostrar menú al usuario
                System.out.println("Seleccione una opción:");
                System.out.println("1. Buscar producto por SKU");
                System.out.println("2. Salir");
                op = scanner.nextInt();
                scanner.nextLine(); // Limpiar el buffer del scanner

                if (op == 1) {
                    // Solicitar el SKU a buscar
                    System.out.print("Ingrese el SKU del producto a buscar: ");
                    String skuABuscar = scanner.nextLine();

                    // Buscar el producto por SKU
                    Producto productoEncontrado = bst.buscar(new Producto(skuABuscar, 0.0, 0.0, "", ""));
                    if (productoEncontrado != null) {
                        System.out.println("Producto encontrado: " + productoEncontrado);
                    } else {
                        System.out.println("Producto no encontrado.");
                    }
                } else if (op != 2) {
                    System.out.println("Opción no válida. Intente nuevamente.");
                }

            } while (op != 2); // Repetir mientras la opción no sea 2 (salir)

            System.out.println("Gracias por usar el programa.");
        } catch (IOException e) {
            System.out.println("Error al leer el archivo: " + e.getMessage());
        } finally {
            scanner.close();
        }
    }
}


