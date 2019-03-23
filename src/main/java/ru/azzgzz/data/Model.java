package ru.azzgzz.data;

import javax.persistence.*;

@MappedSuperclass
public abstract class Model {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private Long id;

    public Model(){
    }

    public Model (Long id) {
        this.id=id;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

}
