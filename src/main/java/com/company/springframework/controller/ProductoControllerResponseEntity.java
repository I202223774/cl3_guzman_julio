package com.company.springframework.controller;

import com.company.springframework.model.Producto;
import com.company.springframework.service.ProductoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/producto-response-entity")
public class ProductoControllerResponseEntity {

    @Autowired
    private ProductoService productoService;

    @GetMapping
    public ResponseEntity<List<Producto>> listarProductos() {
        return ResponseEntity.ok(productoService.listarProductos());
    }

    @GetMapping("/{idProducto}")
    public ResponseEntity<Producto> obtenerproducto(@PathVariable("idProducto") Long idProducto) {
        Producto producto = productoService.obtenerProducto(idProducto);
        return producto !=null ? ResponseEntity.ok(producto) : ResponseEntity.notFound().build();
    }

    @PostMapping
    public ResponseEntity<Producto> guardarProducto(@RequestBody Producto producto) {
        Producto nuevoProducto = productoService.guardarProducto(producto);
        return new ResponseEntity<>(nuevoProducto, HttpStatus.CREATED);
    }

    @PutMapping("/{idProducto}")
    public ResponseEntity<Producto> actualizarproducto(@PathVariable("idProducto") Long idProducto, @RequestBody Producto producto) {
        producto.setIdProducto(idProducto);
        Producto productoActualizado = productoService.actualizarProducto(producto);
        return productoActualizado != null ? ResponseEntity.ok(productoActualizado) : ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{idProducto}")
    public ResponseEntity<Void> eliminarProducto(@PathVariable("idProducto") Long idProducto) {
        productoService.eliminarProducto(idProducto);
        return ResponseEntity.noContent().build();
    }

}
