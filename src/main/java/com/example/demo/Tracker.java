package com.example.demo;

import javax.persistence.*;

@Entity
@Table(name = "tracker")
public class Tracker {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", nullable = false)
    private Long id;

    public Tracker(String title, String desc) {
        this.title = title;
        this.desc = desc;
    }

    protected Tracker() {

    }

    @Override
    public String toString() {
        return String.format(
                "Task[id=%d, firstName='%s', lastName='%s']",
                id, title, desc);
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Basic
    private String title;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    @Basic
    private String desc;

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }
}