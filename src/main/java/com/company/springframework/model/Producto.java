package com.company.springframework.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Table(name = "tbl_productoss")
@Entity
@Getter
@Setter
public class Producto {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idProducto;

    @Column(length = 100)
    private String descripcion;

    @Column(precision = 10, scale = 2)
    private BigDecimal precio;

    private Integer stock;

    private Byte estado;

    @ManyToOne(fetch = FetchType.EAGER)
    private Categoria categoria;


}
