package com.cafesoluble.catalog.repository;

import com.cafesoluble.catalog.model.Producto;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.atomic.AtomicLong;

/**
 * Repositorio en memoria para gestionar el catálogo de productos de Café Soluble S.A.
 * Utiliza estructuras thread-safe (CopyOnWriteArrayList y AtomicLong) para simular la persistencia sin base de datos.
 */
@Repository
public class ProductoRepository {

    private final List<Producto> productos = new CopyOnWriteArrayList<>();
    private final AtomicLong idSequence = new AtomicLong(0);

    public ProductoRepository() {
        cargarProductosIniciales();
    }

    /**
     * Carga inicial de 8 productos ficticios de Café Soluble S.A. en memoria.
     */
    private void cargarProductosIniciales() {
        saveInternal(new Producto(null, "Café Preclásico Instantáneo", "100 g", "Instantáneo", true));
        saveInternal(new Producto(null, "Café Preclásico Granulado", "200 g", "Instantáneo", true));
        saveInternal(new Producto(null, "Café Molido Segovia Selecto", "250 g", "Molido", true));
        saveInternal(new Producto(null, "Café Express Liofilizado", "50 g", "Liofilizado", true));
        saveInternal(new Producto(null, "Café Supremo Matagalpa Roast", "500 g", "Gourmet", true));
        saveInternal(new Producto(null, "Café Descafeinado Suave", "100 g", "Descafeinado", false));
        saveInternal(new Producto(null, "Café Preclásico Cappuccino Vainilla", "150 g", "Mezclas Especiales", true));
        saveInternal(new Producto(null, "Café Tinto Jinotega Tradicional", "400 g", "Molido", true));
    }

    private Producto saveInternal(Producto producto) {
        long newId = idSequence.incrementAndGet();
        producto.setId(newId);
        productos.add(producto);
        return producto;
    }

    /**
     * Obtiene la colección completa de productos.
     */
    public List<Producto> findAll() {
        return new ArrayList<>(productos);
    }

    /**
     * Busca un producto por su identificador único.
     */
    public Optional<Producto> findById(Long id) {
        return productos.stream()
                .filter(p -> p.getId().equals(id))
                .findFirst();
    }

    /**
     * Registra un nuevo producto en memoria asignándole un ID único incremental.
     */
    public Producto save(Producto nuevoProducto) {
        return saveInternal(nuevoProducto);
    }
}
