package edu.bsuirDev.extra;

import edu.bsuirDev.exceptions.IncorrectArgsException;
import org.springframework.stereotype.Component;

import java.time.Duration;

/*
 * A task given by user class
 * @author L1ttl3S1st3r
 * */

@Component
public class Task {
    private String name;
    private Duration time;
    private Double cost;
    private Integer variantsCount;

    @Override
    public String toString()
    {
        return "task: " + name + "(" + time.toMinutes() + "m"
                + "," + cost + ")";
    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Duration getTime() {
        return time;
    }

    public void setTime(Duration time) {
        this.time = time;
    }

    public Double getCost() {
        return cost;
    }

    public void setCost(Double cost) {
        this.cost = cost;
    }

    public Integer getVariantsCount() {
        return variantsCount;
    }

    public void setVariantsCount(Integer variantsCount) {
        this.variantsCount = variantsCount;
    }

    // old version, save for future
/*    public Task(String taskName, Duration taskTimeDuration,
                Double taskCost, Integer variantsCount)
            throws NullPointerException, IncorrectArgsException {
        if (taskName == null || taskTimeDuration == null
                || taskCost == null || variantsCount == null) {
            throw new NullPointerException("a null arg is given");
        }

        if (taskTimeDuration.compareTo(Duration.ZERO) <= 0) {
            throw new IncorrectArgsException("value of duration is incorrect");
        }

        if (taskCost.compareTo(Double.MIN_VALUE) <= 0 || variantsCount.compareTo(0) <= 0) {
            throw new IncorrectArgsException("value of cost and variants count must be bigger than 0");
        }

        name = taskName;
        time = taskTimeDuration;
        this.variantsCount = variantsCount;
        this.cost = taskCost;
    }*/
/*
    public void setName(String name) throws NullPointerException {
        if (name == null) {
            throw new NullPointerException();
        }

        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setTime(Duration time) throws NullPointerException, IncorrectArgsException {
        if (time == null) {
            throw new NullPointerException();
        }

        if (time.compareTo(Duration.ZERO) <= 0) {
            throw new IncorrectArgsException("value of duration must be > 0");
        }

        this.time = time;
    }

    public Duration getTime() {
        return time;
    }

    public void setCost(Double cost) throws NullPointerException, IncorrectArgsException {
        if (cost == null) {
            throw new NullPointerException();
        }

        if (cost.compareTo(Double.MIN_VALUE) <= 0) {
            throw new IncorrectArgsException("value of cost must be > 0");
        }

        this.cost = cost;
    }

    public Double getCost() {
        return cost;
    }

    public void setCount(Integer count) throws NullPointerException, IncorrectArgsException {
        if (count == null) {
            throw new NullPointerException();
        }

        if (count.compareTo(0) <= 0) {
            throw new IncorrectArgsException("value of count must be > 0");
        }

        this.variantsCount = count;
    }

    public Integer getCount() {
        return variantsCount;
    }*/
}
