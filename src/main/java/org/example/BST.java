/**
 * Universidad del Valle de Guatemala
 * Algoritmos y Estructuras de Datos
 * Ing. Douglas Barrios / Aux: Cristian Túnchez
 * @author: Marcela Castillo y Andrés Ismalej
 * Esta clase se contruyo a partir del ejemplo del libro JavaStructures y la guia de ChatGPT
 * OpenAI.(2025).ChatGPT (versión del 23 de marzo)[Módelo de Lenguaje de gran tamaño] https://chatgpt.com/share/67e32454-67ac-8007-9c05-8d728de9e299
*/
package org.example;

// Clase BST (Árbol Binario de Búsqueda)
class BST<E extends Comparable<E>> {
    
    // Clase interna estática para los nodos del árbol
    private static class Nodo<E> {
        E value;
        Nodo<E> izquierdo, derecho;

        Nodo(E value) {
            this.value = value;
            izquierdo = derecho = null;
        }
    }

    private Nodo<E> root;

    public BST() {
        this.root = null;
    }

    /** 
     * Inserta un valor(producto) en el árbol.
     * Si el valor ya existe, no se inserta.
     */
    public void insertar(E value) {
        root = insertarPro(root, value);
    }

    private Nodo<E> insertarPro(Nodo<E> root, E value) {
        if (root == null) {
            return new Nodo<>(value);
        }
        if (value.compareTo(root.value) < 0) {
            root.izquierdo = insertarPro(root.izquierdo, value);
        } else if (value.compareTo(root.value) > 0) {
            root.derecho = insertarPro(root.derecho, value);
        }
        return root;
    }

    /** 
     * Busca un valor en el árbol.
     * @return el valor si se encuentra, null si no está presente.
     */
    public E buscar(E value) {
        return buscarPro(root, value);
    }
    
    private E buscarPro(Nodo<E> root, E value) {
        if (root == null) {
            return null;
        }
        
        int comp = value.compareTo(root.value);
    
        if (comp == 0) {
            return root.value;  // Producto encontrado
        } else if (comp < 0) {
            return buscarPro(root.izquierdo, value);
        } else {
            return buscarPro(root.derecho, value);
        }
    }


    
    /** 
     * Encuentra el valor mínimo en el árbol.
     * @return el valor mínimo, o null si el árbol está vacío.
     */
    public E encontrarMinimo() {
        if (root == null) return null;
        return encontrarMinimoRec(root).value;
    }

    private Nodo<E> encontrarMinimoRec(Nodo<E> root) {
        return (root.izquierdo == null) ? root : encontrarMinimoRec(root.izquierdo);
    }

    /** 
     * Método para eliminar un nodo del árbol.
     */
    public void eliminar(E value) {
        root = eliminarRec(root, value);
    }

    private Nodo<E> eliminarRec(Nodo<E> root, E value) {
        if (root == null) {
            return null;
        }
        if (value.compareTo(root.value) < 0) {
            root.izquierdo = eliminarRec(root.izquierdo, value);
        } else if (value.compareTo(root.value) > 0) {
            root.derecho = eliminarRec(root.derecho, value);
        } else {
            // Nodo con un solo hijo o sin hijos
            if (root.izquierdo == null) {
                return root.derecho;
            } else if (root.derecho == null) {
                return root.izquierdo;
            }
            // Nodo con dos hijos: obtener el menor en el subárbol derecho
            root.value = encontrarMinimoRec(root.derecho).value;
            root.derecho = eliminarRec(root.derecho, root.value);
        }
        return root;
    }


    /** 
     * Recorrido inorden (izquierda - raíz - derecha).
     */
    public void inOrden() {
        inOrdenRec(root);
        System.out.println();
    }

    private void inOrdenRec(Nodo<E> root) {
        if (root != null) {
            inOrdenRec(root.izquierdo);
            System.out.print(root.value + " ");
            inOrdenRec(root.derecho);
        }
    }
    
}