/**
 * Universidad del Valle de Guatemala
 * Algoritmos y Estructuras de Datos
 * Ing. Douglas Barrios / Aux: Cristian Túnchez
 * @author: Marcela Castillo y Andrés Ismalej
*/
package org.example;

// Clase BST (Árbol Binario de Búsqueda)
class BST<E extends Comparable<E>> {
    private class Nodo {
        E value;
        Nodo izquierdo, derecho;

        Nodo(E value) {
            this.value = value;
            izquierdo = derecho = null;
        }
    }

    private Nodo root;

    public BST() {
        this.root = null;
    }

    // Método para insertar
    public void insertar(E value) {
        root = insertarRec(root, value);
    }

    private Nodo insertarRec(Nodo root, E value) {
        if (root == null) {
            return new Nodo(value);
        }
        //Si es menor se va a la izquierda
        if (value.compareTo(root.value) < 0) {
            root.izquierdo = insertarRec(root.izquierdo, value);
        //Si es mayor se va a la derecha
        } else if (value.compareTo(root.value) > 0) {
            root.derecho = insertarRec(root.derecho, value);
        }
        return root;
    }

    // Método para buscar un producto por SKU
    public E buscar(E value) {
        return buscarRec(root, value);
    }

    private E buscarRec(Nodo root, E value) {
        if (root == null || root.value.equals(value)) {
            return root != null ? root.value : null;
        }
        if (value.compareTo(root.value) < 0) {
            return buscarRec(root.izquierdo, value);
        }
        return buscarRec(root.derecho, value);
    }

    // Método para recorrido en orden
    //Se usa el método In-Orden
    public void inOrden() {
        inOrdenRec(root);
    }

    //Primero izquierda, luego centro y luego derecha
    private void inOrdenRec(Nodo root) {
        if (root != null) {
            inOrdenRec(root.izquierdo);
            System.out.println(root.value);
            inOrdenRec(root.derecho);
        }
    }
}