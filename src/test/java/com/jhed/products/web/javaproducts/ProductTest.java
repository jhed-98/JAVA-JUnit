package com.jhed.products.web.javaproducts;


import com.jhed.products.web.javaproducts.model.Product;
import com.jhed.products.web.javaproducts.repository.ProductRepository;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.annotation.Rollback;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import org.assertj.core.api.Assertions;

//Pruebas unitaria para entidades de insercion a al bd
@DataJpaTest
//Indica que ejecute la operacion en la DB Real
@AutoConfigureTestDatabase(replace = Replace.NONE)
//Ordenar metodos
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class ProductTest {
    @Autowired
    private ProductRepository repository;

    @Test
    @Rollback(false)
    @Order(1)
    public void testGuardarProduct() {
        Product product = new Product("Laptop Lenovo", 3000);
        Product productSave = repository.save(product);
        //confirma la prueba unitaria si no retorna un null
        assertNotNull(productSave);
    }

    @Test
    @Order(2)
    public void testBuscarProductxNombre() {
        String nombre = "Laptop Lenovo";
        //String nombre = "Iphone 13";

        Product product = repository.findByName(nombre);

        //Confirma si el valor esperado = valor buscado
        Assertions.assertThat(product.getName()).isEqualTo(nombre);

    }

    @Test
    @Order(3)
    public void testBuscarProductxNombreNoExicst() {
        String nombre = "Iphone 13";
        //String nombre = "Televisor Samsung HD";

        Product product = repository.findByName(nombre);

        //Confirma si el valor esperado = valor buscado
        assertNull(product);
    }

    @Test
    @Rollback(false)
    @Order(4)
    public void testActuaizrProduct() {
        //Correcto
        String nameProduct = "Televisor Xiaomi HD";
        Product product = new Product(nameProduct, 2100); // valores nuevos
        product.setId(1); // id de product a actualizar

        repository.save(product);

        //Confirma si el valor esperado = valor buscado
        Product productUpdate = repository.findByName(nameProduct);
        Assertions.assertThat(productUpdate.getName()).isEqualTo(nameProduct);

    }

    @Test
    @Order(5)
    public void testListProducts() {
        List<Product> products = repository.findAll();

        for (Product product : products) {
            System.out.println(product);
        }

        Assertions.assertThat(products).size().isGreaterThan(0);
    }

    @Test
    @Rollback(false)
    @Order(6)
    public void testEliminarProduct(){
        Integer id = 8;
        //verifica si existe el id antes de eliminar
        boolean esExicstAntesDelete = repository.findById(id).isPresent();

        repository.deleteById(id);

        boolean noExicstAntesDelete = repository.findById(id).isPresent();

        assertTrue(esExicstAntesDelete);
        assertFalse(noExicstAntesDelete);

    }
}