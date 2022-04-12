package main.entity;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "people")
public class People {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Integer id;

    @Column(name = "first_name", nullable = false)
    private String first_name;
    @Column(name = "last_name", nullable = false)
    private String last_name;
    @Column(name = "pather_name", nullable = false)
    private String pather_name;

    @Column(name = "group_id")
    private Integer group_id;

    @Column(name = "type")
    private String type;


    @ManyToOne
    @JoinColumn(name = "group_id", insertable=false, updatable=false)
    private Groups groups;

    @OneToMany(mappedBy = "people1", cascade = CascadeType.ALL)
    private List<Marks> students;
    @OneToMany(mappedBy = "people2", cascade = CascadeType.ALL)
    private List<Marks> teachers;

    public People() {
    }

    public People(String first_name, String last_name, String pather_name, Integer group_id, String type) {
        this.first_name = first_name;
        this.last_name = last_name;
        this.pather_name = pather_name;
        this.group_id = group_id;
        this.type = type;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getFirst_name() {
        return first_name;
    }

    public void setFirst_name(String first_name) {
        this.first_name = first_name;
    }

    public String getLast_name() {
        return last_name;
    }

    public void setSecond_name(String last_name) {
        this.last_name = last_name;
    }

    public String getPather_name() {
        return pather_name;
    }

    public void setPather_name(String pather_name) {
        this.pather_name = pather_name;
    }

    public Integer getGroup_id() {
        return group_id;
    }

    public void setGroup_id(Integer group_id) {
        this.group_id = group_id;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    @Override
    public String toString() {
        return "People{" +
                "id=" + id +
                ", first_name='" + first_name + '\'' +
                ", last_name='" + last_name + '\'' +
                ", pather_name='" + pather_name + '\'' +
                ", group_id=" + group_id +
                ", type='" + type + '\'' +
                '}';
    }
}
