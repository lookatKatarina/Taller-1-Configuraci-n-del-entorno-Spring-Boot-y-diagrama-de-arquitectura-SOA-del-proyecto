package com.cafesoluble.catalog.model;

import java.util.Objects;

/**
 * Modelo de Dominio que representa un Producto en el catálogo de Café Soluble S.A.
 */
public class Producto {

    private Long id;
    private String nombre;
    private String presentacion;
    private String categoria;
    private boolean disponible;

    // Constructor por defecto (requerido por Jackson para deserialización JSON)
    public Producto() {
    }

    // Constructor completo
    public Producto(Long id, String nombre, String presentacion, String categoria, boolean disponible) {
        this.id = id;
        this.nombre = nombre;
        this.presentacion = presentacion;
        this.categoria = categoria;
        this.disponible = disponible;
    }

    // Constructor sin ID (útil para la creación mediante POST antes de asignar ID)
    public Producto(String nombre, String presentacion, String categoria, boolean disponible) {
        this.nombre = nombre;
        this.presentacion = presentacion;
        this.categoria = categoria;
        this.disponible = disponible;
    }

    // Getters y Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getPresentacion() {
        return presentacion;
    }

    public void setPresentacion(String presentacion) {
        this.presentacion = presentacion;
    }

    public String getCategoria() {
        return categoria;
    }

    public void setCategoria(String categoria) {
        this.categoria = categoria;
    }

    public boolean isDisponible() {
        return disponible;
    }

    public void setDisponible(boolean disponible) {
        this.disponible = disponible;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Producto producto = (Producto) o;
        return Objects.equals(id, producto.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    @Override
    public String toString() {
        return "Producto{" +
                "id=" + id +
                ", nombre='" + nombre + '\'' +
                ", presentacion='" + presentacion + '\'' +
                ", categoria='" + categoria + '\'' +
                ", disponible=" + disponible +
                '}';
    }
}
