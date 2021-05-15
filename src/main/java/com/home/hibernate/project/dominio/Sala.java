package com.home.hibernate.project.dominio;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "sala")
public class Sala {

    @OneToMany(mappedBy = "sala")
    private List<Reunion> reunions;

    @Id
    @Column(length = 20)
    private String id;

    private String descripcion;

    private int capacidad;

    public Sala(String id, String descripcion, int capacidad) {
        this.id = id;
        this.descripcion = descripcion;
        this.capacidad = capacidad;
    }

    public Sala() {

    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public int getCapacidad() {
        return capacidad;
    }

    public void setCapacidad(int capacidad) {
        this.capacidad = capacidad;
    }

    public List<Reunion> getReunions() {
        return reunions;
    }

    public void setReunions(List<Reunion> reunions) {
        this.reunions = reunions;
    }

    @Override
    public String toString() {
        return "Sala{" +
                "id='" + id + '\'' +
                ", descripcion='" + descripcion + '\'' +
                ", capacidad=" + capacidad +
                '}';
    }
}
