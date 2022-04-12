package main.entity;

import com.fasterxml.jackson.annotation.JsonCreator;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "member")
public class Groups {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", nullable = false)
    private Integer id;

    @Column(name = "name", nullable = false)
    private String name;

    @OneToMany(mappedBy = "groups", cascade = CascadeType.ALL)
    private List<People> people;

    public Groups() {

    }

    public Groups(String name) {
        this.name = name;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "Groups{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }
}
