package com.rickandMorty.rickandMorty.entities;
import jakarta.persistence.*;

@Entity
@Table(name = "characters",uniqueConstraints = { @UniqueConstraint(columnNames = { "name" })})
public class Character {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String gender;
    private String status;
    private String image;

    public Character(String name, String gender, String status, String image) {
        this.name = name;
        this.gender = gender;
        this.status = status;
        this.image = image;
    }

    public Character() {

    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }
}
