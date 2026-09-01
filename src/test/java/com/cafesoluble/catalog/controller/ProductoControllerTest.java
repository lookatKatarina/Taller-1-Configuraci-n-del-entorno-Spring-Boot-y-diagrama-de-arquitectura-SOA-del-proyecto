package com.cafesoluble.catalog.controller;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.hamcrest.Matchers.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

/**
 * Pruebas de integración automatizadas para verificar el comportamiento de los endpoints REST
 * de ProductoController en la API de Café Soluble S.A.
 */
@SpringBootTest
@AutoConfigureMockMvc
public class ProductoControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    @DisplayName("Escenario 1: Consultar la colección completa de productos (Retorna 200 OK y 8 productos)")
    public void testConsultarTodosLosProductos() throws Exception {
        mockMvc.perform(get("/api/productos"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$", hasSize(greaterThanOrEqualTo(8))))
                .andExpect(jsonPath("$[0].nombre", is("Café Preclásico Instantáneo")));
    }

    @Test
    @DisplayName("Escenario 2: Consultar un ID existente (ID 3 - Retorna 200 OK y el recurso)")
    public void testConsultarProductoPorIdExistente() throws Exception {
        mockMvc.perform(get("/api/productos/3"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.id", is(3)))
                .andExpect(jsonPath("$.nombre", is("Café Molido Segovia Selecto")))
                .andExpect(jsonPath("$.presentacion", is("250 g")));
    }

    @Test
    @DisplayName("Escenario 3: Consultar el primer ID existente (ID 1 - Retorna 200 OK)")
    public void testConsultarPrimerProducto() throws Exception {
        mockMvc.perform(get("/api/productos/1"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.id", is(1)))
                .andExpect(jsonPath("$.nombre", is("Café Preclásico Instantáneo")));
    }

    @Test
    @DisplayName("Escenario 4: Consultar un ID inexistente (ID 999 - Retorna 404 NOT FOUND)")
    public void testConsultarProductoInexistente() throws Exception {
        mockMvc.perform(get("/api/productos/999"))
                .andExpect(status().isNotFound())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.status", is(404)))
                .andExpect(jsonPath("$.error", is("Not Found")))
                .andExpect(jsonPath("$.message", containsString("El producto con ID 999 no fue encontrado")));
    }

    @Test
    @DisplayName("Escenario 5: Registrar un producto válido (POST - Retorna 201 CREATED y el nuevo recurso)")
    public void testRegistrarProductoValido() throws Exception {
        String nuevoProductoJson = """
                {
                    "nombre": "Café Orgánico Dipilto Reserve",
                    "presentacion": "340 g",
                    "categoria": "Gourmet",
                    "disponible": true
                }
                """;

        mockMvc.perform(post("/api/productos")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(nuevoProductoJson))
                .andExpect(status().isCreated())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.id", notNullValue()))
                .andExpect(jsonPath("$.nombre", is("Café Orgánico Dipilto Reserve")))
                .andExpect(jsonPath("$.presentacion", is("340 g")));
    }

    @Test
    @DisplayName("Escenario 6: Consultar la colección tras la inserción (Verifica presencia del nuevo recurso)")
    public void testConsultarColeccionDespuesDeRegistrar() throws Exception {
        String nuevoProductoJson = """
                {
                    "nombre": "Café Frío Cold Brew Express",
                    "presentacion": "300 ml",
                    "categoria": "Bebidas Listas",
                    "disponible": true
                }
                """;

        // Registrar nuevo producto
        mockMvc.perform(post("/api/productos")
                .contentType(MediaType.APPLICATION_JSON)
                .content(nuevoProductoJson));

        // Consultar colección completa
        mockMvc.perform(get("/api/productos"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[*].nombre", hasItem("Café Frío Cold Brew Express")));
    }
}
