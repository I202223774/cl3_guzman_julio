package com.company.springframework;

import com.company.springframework.model.Categoria;
import com.company.springframework.model.Producto;
import com.company.springframework.repository.CategoriaRepository;

import static org.assertj.core.api.Assertions.assertThat;

import com.company.springframework.repository.ProductoRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

@SpringBootTest
class SpringframeworkInitialApplicationTests {

    @Autowired
    private CategoriaRepository categoriaRepository;

    @Autowired
    private ProductoRepository productoRepository;

    @Test
    void insertarCategoria() {

        Categoria categoria = new Categoria();
        categoria.setDescripcion("Detergentes");

        Categoria categoriaRegistrado = categoriaRepository.save(categoria);

        assertThat(categoriaRegistrado).isNotNull();
        assertThat(categoriaRegistrado.getIdCategoria()).isPositive();
        assertThat(categoriaRegistrado.getDescripcion()).isNotEmpty();

    }

    @Test
    void insertarProducto() {

        Producto producto = new Producto();

        // create value for attributes
        producto.setDescripcion("Fideos Don Lucho");
        producto.setPrecio(new BigDecimal("8.00"));
        producto.setStock(100);
        producto.setEstado((byte)1);
        producto.setCategoria(producto.getCategoria());

        // create value for relationship
        Categoria categoria = new Categoria();
        categoria.setDescripcion("Abarrotes");
        Categoria categoriaRegistrado = categoriaRepository.save(categoria);

        producto.setCategoria(categoriaRegistrado);

        Producto productoRegistrado = productoRepository.save(producto);

        assertThat(productoRegistrado).isNotNull();
        assertThat(productoRegistrado.getIdProducto()).isPositive();
        assertThat(productoRegistrado.getDescripcion()).isNotEmpty();
        assertThat(productoRegistrado.getPrecio()).isNotNull();
        assertThat(productoRegistrado.getStock()).isNotNull();
        assertThat(productoRegistrado.getEstado()).isNotNull();
        assertThat(productoRegistrado.getCategoria().getDescripcion()).isNotEmpty();
    }

    @Test
    void actualizarProducto() {

        // editar el producto con id 6
        Producto producto = productoRepository.findById(6L).orElse(null);

        // modificar la descripcion del producto
        producto.setDescripcion("Bolivar");

        // guardar los cambios
        productoRepository.save(producto);

        // verificar que el nombre del producto se actualizo
        Producto productoActualizado = productoRepository.findById(6L).orElse(null);
        assertThat(productoActualizado.getDescripcion()).isEqualTo("Bolivar");
    }

    @Test
    void eliminarProducto() {

        // eliminar el producto con id 5
        productoRepository.deleteById(5L);

        // verificar que el producto con id 5 ya no existe
        Producto productoEliminado = productoRepository.findById(1L).orElse(null);
        assertThat(productoEliminado).isNull();

    }

    @Test
    void listarCategorias(){
        Iterable<Categoria> categorias = categoriaRepository.findAll();
        assertThat(categorias).isNotEmpty();
    }

    @Test
    void jpa_query_methods(){
        //Iterable<Producto> productos = productoRepository.findByDescripcion("Pepsi Cola");
        //assertThat(productos).isNotEmpty();

        Iterable<Producto> productos = productoRepository.findByDescripcionContaining("Cola");
        assertThat(((List<Producto>) productos).size()).isEqualTo(2);

        //Iterable<Producto> productos = productoRepository.findByDescripcionStartingWith("P");
        //assertThat(((List<Producto>) productos).size()).isEqualTo(2);

        //Iterable<Producto> productos = productoRepository.findByPrecio(new BigDecimal("10"));
        //assertThat(((List<Producto>) productos).size()).isEqualTo(2);

        //Iterable<Producto> productos = productoRepository.findByPrecioGreaterThan(new BigDecimal("50"));
        //assertThat(((List<Producto>) productos).size()).isEqualTo(1);

        //Iterable<Producto> productos = productoRepository.findByPrecioGreaterThanEqual(new BigDecimal("30"));
        //assertThat(((List<Producto>) empleados).size()).isEqualTo(3);

        //List<Producto> productos = productoRepository.findByPrecioBetween(new BigDecimal("10"), new BigDecimal("20"));
        //assertThat(productos).hasSize(2);
        //assertThat(productos).size().isEqualTo(3);


        //List<Producto> productos = productoRepository.findFirst2BySalarioBetween(new BigDecimal("50000"), new BigDecimal("55000"));
        //assertThat(productos).hasSize(2);
    }
}
