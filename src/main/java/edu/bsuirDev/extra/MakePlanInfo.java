package edu.bsuirDev.extra;

/*
 * input info for MakePlanController
 *
 * author: L1ttl3S1st3r
 */

import org.springframework.stereotype.Component;

import javax.validation.constraints.NotBlank;
import java.sql.Date;
import java.time.LocalTime;
import java.util.List;

@Component
public class MakePlanInfo {
    @NotBlank(message = "planName can't be null!")
    private String planName;

    @NotBlank(message = "timeToPerform can't be null!")
    private LocalTime timeToPerform;

    @NotBlank(message = "deadline can't be null!")
    private java.sql.Date deadline;

    private long userID;

    @NotBlank(message = "task info can't be null!")
    private List<Task> tasksInfo;

    public String getPlanName() {
        return planName;
    }

    public void setPlanName(String planName) {
        this.planName = planName;
    }

    public LocalTime getTimeToPerform() {
        return timeToPerform;
    }

    public void setTimeToPerform(LocalTime timeToPerform) {
        this.timeToPerform = timeToPerform;
    }

    public Date getDeadline() {
        return deadline;
    }

    public void setDeadline(Date deadline) {
        this.deadline = deadline;
    }

    public long getUserID() {
        return userID;
    }

    public void setUserID(long userID) {
        this.userID = userID;
    }

    public List<Task> getTasksInfo() {
        return tasksInfo;
    }

    public void setTasksInfo(List<Task> tasksInfo) {
        this.tasksInfo = tasksInfo;
    }
}
