package com.company.springframework.controller;

import com.company.springframework.model.Producto;
import com.company.springframework.service.ProductoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/producto")
public class ProductoController {

    @Autowired
    private ProductoService productoService;

    @GetMapping
    public List<Producto> listarProductos() {
        return productoService.listarProductos();
    }

    @GetMapping("/{idProducto}")
    public Producto obtenerProducto(@PathVariable("idProducto") Long idProducto) {
        return productoService.obtenerProducto(idProducto);
    }

    @PostMapping
    public Producto guardarProducto(@RequestBody Producto producto) {
        return productoService.guardarProducto(producto);
    }

    @PostMapping("/{idProducto}")
    public Producto actualizarProducto(@PathVariable("idProducto") Long idProducto, @RequestBody Producto producto) {
        producto.setIdProducto(idProducto);
        return productoService.actualizarProducto(producto);
    }

    @DeleteMapping("/{idProducto}")
    public void eliminarProducto(@PathVariable("idProducto") Long idProducto) {
        productoService.eliminarProducto(idProducto);
    }

}
