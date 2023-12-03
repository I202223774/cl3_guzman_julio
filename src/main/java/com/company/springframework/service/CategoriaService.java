package com.company.springframework.service;

import com.company.springframework.model.Categoria;

import java.util.List;

public interface CategoriaService {

    Categoria guardarCategoria(Categoria categoria);

    Categoria actualizarCategoria(Categoria categoria);

    void eliminarCategoria(Integer idCategoria);

    Categoria obtenerCategoria(Integer idCategoria);

    List<Categoria> listarCategorias();

}
