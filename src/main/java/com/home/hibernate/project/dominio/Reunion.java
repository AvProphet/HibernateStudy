package com.home.hibernate.project.dominio;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "reunion")
public class Reunion {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idReunion")
    private long idReunion;

    @Column(name = "fecha")
    private LocalDateTime fecha;

    @Column(name = "asunto")
    private String asunto;

    @ManyToOne(fetch = FetchType.LAZY)
    private Sala sala;

    @OneToOne(mappedBy = "reunion")
    private Acta acta;

    @ManyToMany(mappedBy = "reuniones", cascade = CascadeType.ALL)
    private Set<Persona> participantes;

    public Reunion(LocalDateTime fecha, String asunto) {
        this();
        this.fecha = fecha;
        this.asunto = asunto;
    }

    public Reunion(Reunion r) {
        this.fecha = fecha;
        this.asunto = asunto;
    }

    public Reunion() {
        participantes = new HashSet<>();
    }

    public long getIdReunion() {
        return idReunion;
    }

    public void setIdReunion(long id) {
        this.idReunion = id;
    }

    public LocalDateTime getFecha() {
        return fecha;
    }

    public void setFecha(LocalDateTime fecha) {
        this.fecha = fecha;
    }

    public String getAsunto() {
        return asunto;
    }

    public void setAsunto(String asunto) {
        this.asunto = asunto;
    }

    public Sala getSala() {
        return sala;
    }

    public void setSala(Sala sala) {
        this.sala = sala;
    }

    public Acta getActa() {
        return acta;
    }

    public void setActa(Acta acta) {
        this.acta = acta;
    }

    public Set<Persona> getParticipantes() {
        return participantes;
    }

    public void addParticipantes(Persona participante) {
        participantes.add(participante);
        if (!participante.getReuniones().contains(this)) {
            participante.addReunion(this);
        }
    }

    @Override
    public String toString() {
        return "Reunion{" +
                "idReunion=" + idReunion +
                ", fecha=" + fecha +
                ", asunto='" + asunto + '\'' +
                ", sala=" + sala +
                '}';
    }
}
