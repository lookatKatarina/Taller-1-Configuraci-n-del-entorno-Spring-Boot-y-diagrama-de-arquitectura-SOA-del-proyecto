package com.cafesoluble.catalog.exception;

/**
 * Excepción lanzada cuando no se encuentra un producto por su identificador único.
 */
public class ProductoNoEncontradoException extends RuntimeException {

    public ProductoNoEncontradoException(Long id) {
        super("El producto con ID " + id + " no fue encontrado en el catálogo de Café Soluble S.A.");
    }
}
