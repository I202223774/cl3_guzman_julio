package com.company.springframework.service.impl;

import com.company.springframework.model.Categoria;
import com.company.springframework.repository.CategoriaRepository;
import com.company.springframework.service.CategoriaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CategoriaServiceImpl implements CategoriaService {

    @Autowired
    private CategoriaRepository categoriaRepository;

    @Override
    public Categoria guardarCategoria(Categoria categoria) {
        validarCategoria(categoria);
        return categoriaRepository.save(categoria);
    }

    @Override
    public Categoria actualizarCategoria(Categoria categoria) {
        validarCategoria(categoria);
        if (!categoriaRepository.existsById(categoria.getIdCategoria())) {
            throw new IllegalArgumentException("La categoria con el ID esoecificado no existe");
        }
        return categoriaRepository.save(categoria);
    }

    @Override
    public void eliminarCategoria(Integer idCategoria) {
        if (!categoriaRepository.existsById(idCategoria)) {
            throw new IllegalArgumentException("La categoria con el ID esoecificado no existe");
        }
    }

    @Override
    public Categoria obtenerCategoria(Integer idCategoria) {
        Optional<Categoria> categoria = categoriaRepository.findById(idCategoria);
        if (categoria.isEmpty()) {
            throw new IllegalArgumentException("La categoria con el ID esoecificado no existe");
        }
        return categoria.get();
    }

    @Override
    public List<Categoria> listarCategorias() {
        return (List<Categoria>) categoriaRepository.findAll();
    }

    private void validarCategoria(Categoria categoria) {
        if (categoria.getDescripcion() == null || categoria.getDescripcion().trim().isEmpty()) {
            throw  new IllegalArgumentException("La categoria no puede estar vacia");
        }
    }
}
