/**
 * Universidad del Valle de Guatemala
 * Algoritmos y Estructuras de Datos
 * Ing. Douglas Barrios / Aux: Cristian Túnchez
 * @author: Marcela Castillo y Andrés Ismalej
*/
package org.example;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

//Clase para leer el archivo CSV con los datos del producto.
public class ReadFile {

    private String rutaArchivo;
    
    /**
     * Constructor que usa el nombre de archivo por defecto
    */
    public ReadFile() {
        this.rutaArchivo = "home appliance skus lowes.csv";
    }
    
    /**
     * Constructor
     * @param rutaArchivo Nombre del archivo CSV a leer (ubicado en resources)
    */
    public ReadFile(String rutaArchivo) {
        this.rutaArchivo = rutaArchivo;
    }
    
    /**
     * Lee el archivo CSV y extrae los datos relevantes de cada producto
     * @return Lista de objetos Producto con la información extraída
     * @throws IOException Si hay problemas al leer el archivo
    */
    public List<Producto> readProducts() throws IOException {
        List<Producto> productos = new ArrayList<>();
        
        // Obtener el archivo desde los resources
        InputStream inputStream = getClass().getClassLoader().getResourceAsStream(rutaArchivo);
        
        if (inputStream == null) {
            throw new IOException("No se pudo encontrar el archivo: " + rutaArchivo);
        }
        
        try (BufferedReader br = new BufferedReader(new InputStreamReader(inputStream))) {
            // Leer la primera línea (encabezados) para identificar columnas
            String lineaEncabezado = br.readLine();
            if (lineaEncabezado == null) {
                throw new IOException("El archivo CSV está vacío.");
            }
            
            // Dividir los encabezados por comas
            String[] encabezados = lineaEncabezado.split(",");
            
            // Buscar las posiciones de las columnas que necesitamos
            int skuIndex = encontrarIndex(encabezados, "SKU");
            int priceRetailIndex = encontrarIndex(encabezados, "Price_Retail");
            int priceCurrentIndex = encontrarIndex(encabezados, "Price_Current");
            int productNameIndex = encontrarIndex(encabezados, "Product_Name");
            int categoryIndex = encontrarIndex(encabezados, "Category");
            
            // Verificar que se encontraron todas las columnas
            if (skuIndex == -1 || priceRetailIndex == -1 || priceCurrentIndex == -1 || 
                productNameIndex == -1 || categoryIndex == -1) {
                throw new IOException("No se encontraron todas las columnas requeridas en el CSV");
            }
            
            // Leer el resto del archivo línea por línea
            String line;
            while ((line = br.readLine()) != null) {
                // Usar un método más simple para dividir la línea CSV
                String[] values = dividirLineaCSV(line);
                
                // Verificar que la línea tenga suficientes valores
                if (values.length > Math.max(skuIndex, Math.max(priceRetailIndex, 
                    Math.max(priceCurrentIndex, Math.max(productNameIndex, categoryIndex))))) {
                    
                    // Obtener los valores de cada columna
                    String sku = values[skuIndex].trim();
                    double priceRetail = convertirAPrecio(values[priceRetailIndex]);
                    double priceCurrent = convertirAPrecio(values[priceCurrentIndex]);
                    String name = values[productNameIndex].trim();
                    String category = values[categoryIndex].trim();
                    
                    // Crear y añadir el producto a la lista
                    Producto producto = new Producto(sku, priceRetail, priceCurrent, name, category);
                    productos.add(producto);
                }
            }
        }
        
        return productos;
    }
    
    /**
     * Busca el índice de una columna en los encabezados, probando varios posibles nombres
     * @param encabezados Array con los nombres de las columnas
     * @param nombres nombres para la columna buscada
     * @return El índice de la columna, o -1 si no se encontró
    */
    private int encontrarIndex(String[] encabezados, String... nombres) {
        for (int i = 0; i < encabezados.length; i++) {
            String encabezado = encabezados[i].trim();
            for (String name : nombres) {
                if (encabezado.equalsIgnoreCase(name)) {
                    return i;
                }
            }
        }
        return -1;
    }
    
    /**
     * Método simplificado para dividir una línea CSV
     * Esta versión es más básica pero suficiente para la mayoría de los casos
    */
    private String[] dividirLineaCSV(String line) {
        ArrayList<String> result = new ArrayList<>();
        StringBuilder campo = new StringBuilder();
        boolean dentroDeComillas = false;
        
        // Recorrer cada carácter de la línea
        for (int i = 0; i < line.length(); i++) {
            char c = line.charAt(i);
            
            if (c == '"') {
                // Cambiar el estado de estar dentro o fuera de comillas
                dentroDeComillas = !dentroDeComillas;
            } else if (c == ',' && !dentroDeComillas) {
                
                result.add(campo.toString());
                campo = new StringBuilder();
            } else {
                // Cualquier otro carácter se añade al campo actual
                campo.append(c);
            }
        }
        
        result.add(campo.toString());
        
        // Convertir ArrayList a array
        String[] array = new String[result.size()];
        return result.toArray(array);
    }
    
    /**
     * Convierte una cadena a un precio (número decimal)
     * Versión simplificada para manejar precios en formato de texto
     */
    private double convertirAPrecio(String texto) {
        try {
            // Quitar caracteres que no sean números, puntos o comas
            String limpio = texto.replaceAll("[^0-9.,]", "").trim();
            
            // Si queda vacío, devolver 0
            if (limpio.isEmpty()) {
                return 0.0;
            }
            
            // Convertir a número
            return Double.parseDouble(limpio);
        } catch (Exception e) {
            System.out.println("Error al convertir precio: " + texto);
            return 0.0;
        }
    }  

    // PRUEBA DE LECTURA DEL ARCHIVO
    // public static void main(String[] args) {
    //     try {
    //         ReadFile lector = new ReadFile();
            
    //         List<Producto> productos = lector.readProducts();
            
    //         System.out.println("Total de productos leídos: " + productos.size());
            
    //         System.out.println("\nPrimeros productos:");
    //         for (int i = 0; i < Math.min(5, productos.size()); i++) {
    //             System.out.println(productos.get(i));
    //         }
            
    //     } catch (IOException e) {
    //         System.out.println("Error al leer el archivo: " + e.getMessage());
    //         e.printStackTrace();
    //     }
    // }
}
