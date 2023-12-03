package com.company.springframework.service.impl;

import com.company.springframework.model.Categoria;
import com.company.springframework.model.Producto;
import com.company.springframework.repository.ProductoRepository;
import com.company.springframework.service.ProductoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

@Service
public class ProductoServiceImpl implements ProductoService {

    @Autowired
    private ProductoRepository productoRepository;

    @Override
    public Producto guardarProducto(Producto producto){
        validarProducto(producto);
        return productoRepository.save(producto);
    }

    @Override
    public Producto actualizarProducto(Producto producto) {
        validarProducto(producto);
        if (!productoRepository.existsById(producto.getIdProducto())){
            throw new IllegalArgumentException("El ID del producto especificado no existe");
        }
        return productoRepository.save(producto);
    }

    @Override
    public void eliminarProducto(Long idProducto) {
        if (!productoRepository.existsById(idProducto)) {
            throw new IllegalArgumentException("El ID del producto especificado no existe");
        }
        productoRepository.deleteById(idProducto);
    }

    @Override
    public Producto obtenerProducto(Long idProducto) {
        Optional<Producto> producto = productoRepository.findById(idProducto);
        if (producto.isEmpty()) {
            throw new IllegalArgumentException("El ID del producto especificado no existe");
        }
        return producto.get();
    }

    @Override
    public List<Producto> listarProductos() {
        return (List<Producto>) productoRepository.findAll();
    }

    private void validarProducto(Producto producto) {
        if (producto.getDescripcion() == null || producto.getDescripcion().trim().isEmpty()) {
            throw new IllegalArgumentException("La descripcion del producto no puede estar vacia");
        }
        if (producto.getPrecio() == null || producto.getPrecio().compareTo(BigDecimal.ZERO) < 0) {
            throw new IllegalArgumentException("El precio no puede ser negativo");
        }
        if (producto.getStock() == null || producto.getStock().compareTo(Integer.SIZE) < 0) {
            throw new IllegalArgumentException("El stock del producto no puede ser negativo");
        }
        /*if (producto.getEstado() == null || producto.getEstado().trim().isEmpty()) {
            throw new IllegalArgumentException("El estado del producto no puede estar vacio");
        }*/
        if (producto.getCategoria() == null || producto.getCategoria().getIdCategoria() == null) {
            throw new IllegalArgumentException("La categoria asignada no es válida.");
        }
    }
}
