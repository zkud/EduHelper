package edu.bsuirDev.database.models;

import javax.persistence.*;
import java.sql.Date;

@Entity
@Table(name = "steps")
public class Step {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "name")
    private String name;
    @Column(name = "deadline")
    private Date deadline;
    @Column(name = "complete")
    private boolean complete;
    @Column(name = "cost")
    private double cost;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "plan_id")
    private Plan plan;

    public Step() {
    }

    public Step(String name, Date deadline, double cost) {
        this.name = name;
        this.deadline = deadline;
        this.cost = cost;
        this.complete = false;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Date getDeadline() {
        return deadline;
    }

    public void setDeadline(Date deadline) {
        this.deadline = deadline;
    }

    public boolean isComplete() {
        return complete;
    }

    public void setComplete(boolean complete) {
        this.complete = complete;
    }

    public double getCost() {
        return cost;
    }

    public void setCost(double cost) {
        this.cost = cost;
    }


    public Plan getPlan() {
        return plan;
    }

    public void setPlan(Plan plan) {
        this.plan = plan;
    }

    @Override
    public String toString() {
        return "Step { " +
                "id = " + id +
                ", name = " + name +
                ", deadline = " + deadline +
                ", complete = " + complete +
                ", cost = " + cost +
                " }";
    }

}
