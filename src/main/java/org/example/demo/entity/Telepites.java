package org.example.demo.entity;

import jakarta.persistence.*;
import org.springframework.format.annotation.DateTimeFormat;
import java.time.LocalDate;

@Entity
@Table(name = "telepites")
public class Telepites {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private Integer gepid;
    private Integer szoftverid;
    private String verzio;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate datum;

    @ManyToOne
    @JoinColumn(name = "gepid", insertable = false, updatable = false)
    private Gep gep;

    @ManyToOne
    @JoinColumn(name = "szoftverid", insertable = false, updatable = false)
    private Szoftver szoftver;

    public Telepites() {}

    public Integer getId() { return id; }
    public void setId(Integer id) { this.id = id; }

    public Integer getGepid() { return gepid; }
    public void setGepid(Integer gepid) { this.gepid = gepid; }

    public Integer getSzoftverid() { return szoftverid; }
    public void setSzoftverid(Integer szoftverid) { this.szoftverid = szoftverid; }

    public String getVerzio() { return verzio; }
    public void setVerzio(String verzio) { this.verzio = verzio; }

    public LocalDate getDatum() { return datum; }
    public void setDatum(LocalDate datum) { this.datum = datum; }

    public Gep getGep() { return gep; }
    public Szoftver getSzoftver() { return szoftver; }
}