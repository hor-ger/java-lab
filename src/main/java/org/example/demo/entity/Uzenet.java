package org.example.demo.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

import java.time.LocalDateTime;

@Entity
@Table(name = "uzenet")
public class Uzenet {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "A név megadása kötelező.")
    @Size(min = 2, max = 100, message = "A név 2–100 karakter hosszú legyen.")
    @Column(nullable = false, length = 100)
    private String nev;

    @NotBlank(message = "Az e-mail megadása kötelező.")
    @Email(message = "Érvényes e-mail címet adjon meg.")
    @Column(nullable = false, length = 150)
    private String email;

    @NotBlank(message = "A tárgy megadása kötelező.")
    @Size(min = 3, max = 200, message = "A tárgy 3–200 karakter hosszú legyen.")
    @Column(nullable = false, length = 200)
    private String targy;

    @NotBlank(message = "Az üzenet megadása kötelező.")
    @Size(min = 10, max = 2000, message = "Az üzenet 10–2000 karakter hosszú legyen.")
    @Column(nullable = false, length = 2000)
    private String szoveg;

    @Column(nullable = false)
    private LocalDateTime kuldesIdeje;

    public Uzenet() {}

    @PrePersist
    public void prePersist() {
        if (kuldesIdeje == null) {
            kuldesIdeje = LocalDateTime.now();
        }
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNev() {
        return nev;
    }

    public void setNev(String nev) {
        this.nev = nev;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getTargy() {
        return targy;
    }

    public void setTargy(String targy) {
        this.targy = targy;
    }

    public String getSzoveg() {
        return szoveg;
    }

    public void setSzoveg(String szoveg) {
        this.szoveg = szoveg;
    }

    public LocalDateTime getKuldesIdeje() {
        return kuldesIdeje;
    }

    public void setKuldesIdeje(LocalDateTime kuldesIdeje) {
        this.kuldesIdeje = kuldesIdeje;
    }
}
