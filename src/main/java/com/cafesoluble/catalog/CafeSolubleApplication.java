package com.cafesoluble.catalog;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * Clase principal de inicio de la aplicación Spring Boot para Café Soluble S.A.
 * 
 * La anotación @SpringBootApplication combina:
 * - @Configuration: Define la clase como fuente de definiciones de beans.
 * - @EnableAutoConfiguration: Habilita la autoconfiguración de Spring Boot según las dependencias del pom.xml.
 * - @ComponentScan: Escanea los paquetes derivados com.cafesoluble.catalog en busca de componentes (@RestController, @Repository, etc.).
 */
@SpringBootApplication
public class CafeSolubleApplication {

    public static void main(String[] args) {
        SpringApplication.run(CafeSolubleApplication.class, args);
    }
}
