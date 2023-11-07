package br.com.nullkreativitat.hrtechapirest.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name="tb_usuario")
public class Usuario {
    @Id
    @Column(name="id_usuario")
    @SequenceGenerator(name="usuario", sequenceName="sq_tb_usuario", allocationSize=1)
    @GeneratedValue(strategy=GenerationType.SEQUENCE, generator="usuario")
    private long id;
    @Column(name="nm_usuario")
    private String nome;
    @Column(name="ds_email")
    private String email;
    @Column(name="sn_usuario")
    private String senha;
    @ManyToOne
    @JoinColumn(name="id_cargo")
    @JsonBackReference("cargos")
    private Cargo cargo;

    public Usuario() {
    }

    public Usuario(long id, String nome, String email, String senha, Cargo cargo) {
        this.id = id;
        this.nome = nome;
        this.email = email;
        this.senha = senha;
        this.cargo = cargo;
    }
}
