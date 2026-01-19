package com.departamentos.departamentos.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;

import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;

@Entity
@Table(name = "departamentos")
public class Departamento {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nombreDepartamento;

    @ManyToOne
    private Departamento departamentoSuperior;

    private boolean activo;

    private Integer rutJefe;

    @Enumerated(EnumType.STRING)
    private NivelDepartamento nivel;

    @OneToMany(mappedBy = "departamentoSuperior")
    private List<Departamento> childrens = new ArrayList<>();

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNombreDepartamento() {
        return nombreDepartamento;
    }

    public void setNombreDepartamento(String nombreDepartamento) {
        this.nombreDepartamento = nombreDepartamento;
    }

    public Departamento getDepartamentoSuperior() {
        return departamentoSuperior;
    }

    public void setDepartamentoSuperior(Departamento departamentoPadre) {
        this.departamentoSuperior = departamentoPadre;
    }

    public boolean isActivo() {
        return activo;
    }

    public void setActivo(boolean activo) {
        this.activo = activo;
    }

    public NivelDepartamento getNivel() {
        return nivel;
    }

    public void setChildrens(List<Departamento> childrens) {
        this.childrens = childrens;
    }

    public void setNivel(NivelDepartamento nivel) {
        this.nivel = nivel;
    }

    public enum NivelDepartamento {
        ALCALDIA,
        ADMINISTRACION,
        DIRECCION,
        SUBDIRECCION,
        DEPARTAMENTO,
        SECCION,
        OFICINA

    }

    public List<Departamento> getChildrens() {
        return childrens;
    }

    public Long idPadre() {
        return departamentoSuperior != null ? departamentoSuperior.getId() : null;
    }

    public NivelDepartamento getNivelDeprtamentoSuperior() {
        return this.departamentoSuperior != null ? this.departamentoSuperior.getNivel() : null;
    }

    public Integer getRutJefe() {
        return rutJefe;
    }

    public void setRutJefe(Integer rutJefe) {
        this.rutJefe = rutJefe;
    }

    public Long getIdDepartamentoSuperior() {
        return departamentoSuperior != null ? departamentoSuperior.getId() : null;
    }

    public Integer getRutJefeSuperior() {
        return departamentoSuperior != null ? departamentoSuperior.getRutJefe() : null;
    }

}
