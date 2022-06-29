package com.ibm.project.model;

import javax.persistence.*;

@Entity
@Table
public class Employee {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SEQ_EMPLOYEE_ID")
    @SequenceGenerator(name = "SEQ_EMPLOYEE_ID", sequenceName = "SEQ_EMPLOYEE_ID", allocationSize = 1, initialValue = 2)
    private Integer id;

    @Column
    private String name;

    @Column
    private String job;

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

    public String getJob() {
        return job;
    }

    public void setJob(String job) {
        this.job = job;
    }
}
