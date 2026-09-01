package com.cafesoluble.catalog.controller;

import com.cafesoluble.catalog.exception.ProductoNoEncontradoException;
import com.cafesoluble.catalog.model.Producto;
import com.cafesoluble.catalog.repository.ProductoRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Controlador REST para gestionar la API de productos de Café Soluble S.A.
 * 
 * Base Path: /api/productos
 */
@RestController
@RequestMapping("/api/productos")
public class ProductoController {

    private final ProductoRepository productoRepository;

    public ProductoController(ProductoRepository productoRepository) {
        this.productoRepository = productoRepository;
    }

    /**
     * Operación: Consultar todos los productos
     * GET /api/productos
     * Código HTTP: 200 OK
     */
    @GetMapping
    public ResponseEntity<List<Producto>> obtenerTodosLosProductos() {
        List<Producto> productos = productoRepository.findAll();
        return ResponseEntity.ok(productos);
    }

    /**
     * Operación: Consultar producto por ID
     * GET /api/productos/{id}
     * Código HTTP: 200 OK (si existe) o 404 NOT FOUND (si no existe)
     */
    @GetMapping("/{id}")
    public ResponseEntity<Producto> obtenerProductoPorId(@PathVariable Long id) {
        Producto producto = productoRepository.findById(id)
                .orElseThrow(() -> new ProductoNoEncontradoException(id));
        return ResponseEntity.ok(producto);
    }

    /**
     * Operación: Registrar un nuevo producto
     * POST /api/productos
     * Entrada: Objeto JSON en el cuerpo de la petición
     * Código HTTP: 201 CREATED
     */
    @PostMapping
    public ResponseEntity<Producto> registrarProducto(@RequestBody Producto nuevoProducto) {
        Producto productoCreado = productoRepository.save(nuevoProducto);
        return ResponseEntity.status(HttpStatus.CREATED).body(productoCreado);
    }
}
