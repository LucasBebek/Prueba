package com.tup.freecomers.models;

import javax.persistence.Entity;
import javax.persistence.GenerationType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class productos
{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    
    private Long id;

    private float precio; 

    private String nombre;

    private String pais;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public float getPrecio(){
         return precio;
    }
    public void setPrecio(float precio){
         this.precio = precio;
    }
    public String getPais(){
          return pais;
    }
    public void setPais(String pais)
    {
         this.pais = pais;
    }
}
