package org.example.demo.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "szoftver")
public class Szoftver {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String nev;
    private String kategoria;

    public Szoftver() {}

    public Integer getId() { return id; }
    public void setId(Integer id) { this.id = id; }

    public String getNev() { return nev; }
    public void setNev(String nev) { this.nev = nev; }

    public String getKategoria() { return kategoria; }
    public void setKategoria(String kategoria) { this.kategoria = kategoria; }
}