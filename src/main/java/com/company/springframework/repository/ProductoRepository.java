package com.company.springframework.repository;

import com.company.springframework.model.Producto;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.util.List;

@Repository
public interface ProductoRepository extends CrudRepository<Producto, Long> {
    List<Producto> findByPrecio(BigDecimal precio);

    List<Producto> findByDescripcion(String descripcion);

    List<Producto> findByDescripcionContaining(String cadena);

    List<Producto> findByDescripcionStartingWith(String cadena);



    List<Producto> findByPrecioGreaterThan(BigDecimal precio);

    List<Producto> findByPrecioGreaterThanEqual(BigDecimal precio);

    List<Producto> findByPrecioBetween(BigDecimal minPrecio, BigDecimal maxPrecio);

    List<Producto> findFirst2ByPrecioBetween(BigDecimal minPrecio, BigDecimal maxPrecio);

}
