package org.example.demo.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "gep")
public class Gep {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String hely;
    private String tipus;
    private String ipcim;

    public Gep() {}

    public Integer getId() { return id; }
    public void setId(Integer id) { this.id = id; }

    public String getHely() { return hely; }
    public void setHely(String hely) { this.hely = hely; }

    public String getTipus() { return tipus; }
    public void setTipus(String tipus) { this.tipus = tipus; }

    public String getIpcim() { return ipcim; }
    public void setIpcim(String ipcim) { this.ipcim = ipcim; }
}