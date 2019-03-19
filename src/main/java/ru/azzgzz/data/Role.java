package ru.azzgzz.data;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table
public class Role {
    @Id
    @GeneratedValue
    @Column
    private Long id;
    @Column
    private String title;

    @ManyToMany(mappedBy = "roles")
    private Set<User> users = new HashSet<User>();

    public Role(){}

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}
