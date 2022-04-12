package main.entity;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;

@Entity
@Table(name = "marks")
public class Marks {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Integer id;

    @Column(name = "student_id", nullable = false)
    private Integer student_id;
    @Column(name = "subject_id", nullable = false)
    private Integer subject_id;
    @Column(name = "teacher_id", nullable = false)
    private Integer teacher_id;
    @Column(name = "value", nullable = false)
    private Integer value;

    @ManyToOne
    @JoinColumn(name = "student_id", insertable=false, updatable=false)
    private People people1;
    @ManyToOne
    @JoinColumn(name = "teacher_id", insertable=false, updatable=false)
    private People people2;
    @ManyToOne
    @JoinColumn(name = "subject_id", insertable=false, updatable=false)
    private Subjects subjects;

    public Marks() {

    }

    public Marks(Integer student_id, Integer subject_id, Integer teacher_id, Integer value) {
        this.student_id = student_id;
        this.subject_id = subject_id;
        this.teacher_id = teacher_id;
        this.value = value;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getStudent_id() {
        return student_id;
    }

    public void setStudent_id(Integer student_id) {
        this.student_id = student_id;
    }

    public Integer getSubject_id() {
        return subject_id;
    }

    public void setSubject_id(Integer subject_id) {
        this.subject_id = subject_id;
    }

    public Integer getTeacher_id() {
        return teacher_id;
    }

    public void setTeacher_id(Integer teacher_id) {
        this.teacher_id = teacher_id;
    }

    public Integer getValue() {
        return value;
    }

    public void setValue(Integer value) {
        this.value = value;
    }

    @Override
    public String toString() {
        return "Marks{" +
                "id=" + id +
                ", student_id=" + student_id +
                ", subject_id=" + subject_id +
                ", teacher_id=" + teacher_id +
                ", value=" + value +
                '}';
    }

}
