package com.clinicaOdontologica.entity;

import jakarta.persistence.*;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;
import java.util.Date;
import java.util.Set;

/*
@Builder
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "pacientes")
@ToString
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})*/



@Builder
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "pacientes")
@ToString
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class Paciente {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column
    private String nombre;
    @Column
    private String apellido;
    @Column
    private String cedula;
    @Column
    private Date fechaIngreso;
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "domicilio_id", referencedColumnName = "id")
    @JsonIgnore
    @JsonProperty("domicilio")
    private Domicilio domicilio;
    @Column(unique = true,nullable = false)
    private String email;

    @OneToMany(mappedBy = "paciente", fetch = FetchType.LAZY)
    @JsonIgnore
    private Set<Turno> turnos;
    public Paciente(String nombre, String apellido, String cedula, Date fechaIngreso, Domicilio domicilio, String email) {
        this.nombre = nombre;
        this.apellido = apellido;
        this.cedula = cedula;
        this.fechaIngreso = fechaIngreso;
        this.domicilio = domicilio;
        this.email= email;
    }
/*
    public Paciente() {
    }
    , Turno turno

*/
    public Paciente(Long id, String nombre, String apellido, String cedula, Date fechaIngreso, Domicilio domicilio, String email) {
        this.id = id;
        this.nombre = nombre;
        this.apellido = apellido;
        this.cedula = cedula;
        this.fechaIngreso = fechaIngreso;
        this.domicilio = domicilio;
        this.email= email;
   //     Set<Turno> turnos1 = this.turnos;


    }

    public Long getId() {
        return id;
    }

    public String getNombre() {
        return nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public String getCedula() {
        return cedula;
    }

    public Date getFechaIngreso() {
        return fechaIngreso;
    }

    public Domicilio getDomicilio() {
        return domicilio;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    @Override
    public String toString() {
        return "Paciente{" +
                "id=" + id +
                ", nombre='" + nombre + '\'' +
                ", apellido='" + apellido + '\'' +
                ", cedula='" + cedula + '\'' +
                ", fechaIngreso=" + fechaIngreso +
                ", domicilio=" + domicilio +
                ", email='" + email + '\'' +
                '}';
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public void setCedula(String cedula) {
        this.cedula = cedula;
    }

    public void setFechaIngreso(Date fechaIngreso) {
        this.fechaIngreso = fechaIngreso;
    }

    public void setDomicilio(Domicilio domicilio) {
        this.domicilio = domicilio;
    }

    public void setTurnos(Set<Turno> turnos) {
        this.turnos = turnos;
    }
}
