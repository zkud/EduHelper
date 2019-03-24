package edu.bsuirDev.database.models;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "plans")
public class Plan {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "result")
    private double result;
    @Column(name = "info")
    private String info;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "owner_id")
    private User user;

    @OneToMany(mappedBy = "plan", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Step> steps;

    public Plan() {
    }

    public Plan(double result, String info) {
        this.result = result;
        this.info = info;
         steps = new ArrayList<>();
    }

    public int getId() {
        return id;
    }

    public double getResult() {
        return result;
    }

    public void setResult(double result) {
        this.result = result;
    }

    public String getInfo() {
        return info;
    }

    public void setInfo(String info) {
        this.info = info;
    }


    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }


    public void addStep(Step step) {
        step.setPlan(this);
        steps.add(step);
    }

    public void removeStep(Step step) {
        steps.remove(step);
    }

    public List<Step> getSteps() {
        return steps;
    }

    public void setSteps(List<Step> steps) {
        this.steps = steps;
    }


    @Override
    public String toString() {
        return "Step { " +
                "id = " + id +
                ", result = " + result +
                ", info = " + info +
                " }";
    }


}
