package edu.bsuirDev.controllers;

import edu.bsuirDev.math.PlanningStrategy;
import edu.bsuirDev.math.LPPlanningStrategy;

import edu.bsuirDev.database.UserSession;
import edu.bsuirDev.database.models.Plan;
import edu.bsuirDev.database.models.Step;

import edu.bsuirDev.extra.Task;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.sql.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.time.LocalTime;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/makeplan")
public class MakePlanController {

    private Plan createPlan(String planName, List<Task> tasks, String timeToPerform, String deadline)
    {
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
        java.sql.Date deadlineDate = null;
        try {
            deadlineDate = new java.sql.Date(dateFormat.parse(deadline).getTime());
        }
        catch (ParseException parseException)
        {

        }

        LocalTime performTime = LocalTime.parse(timeToPerform);

        PlanningStrategy strategy = new LPPlanningStrategy(
                tasks,
                Duration.between(LocalTime.MIN, performTime),
                deadlineDate
        );

        // create new plan
        Plan plan = strategy.makePlan();
        plan.setInfo(planName);

        return plan;
    }

    // TODO: add json string parsing
    private List<Task> parseTasks(String json)
    {
        return new LinkedList<>();
    }

    @GetMapping(produces="application/json")
    Map<String, String> makeplan(@RequestParam(value = "userid") long userId,
                                 @RequestParam(value = "planname") String planName,
                                 @RequestParam(value = "info") String tasksInfo,
                                 @RequestParam(value = "deadline") String deadline,
                                 @RequestParam(value = "performtime") String timeToPerform)
    {
        // create plan
        Plan plan = createPlan(planName, parseTasks(tasksInfo), timeToPerform, deadline);

        // run user session
        UserSession userSession = new UserSession(userId);

        // add plan to db
        userSession.addPlan(plan);

        // output result plan and steps (TODO: refactor this)
        HashMap<String, String> mapPlan = new HashMap<>();
        mapPlan.put("name", plan.getInfo());
        mapPlan.put("expected result", Double.toString(plan.getResult()));

        HashMap<String, String> mapSteps = new HashMap<>();
        HashMap<String, String> mapOneStep = new HashMap<>();
        List<Step> list = userSession.getSteps(plan.getId());
        for (Step step : list) {
            mapOneStep.put("name", step.getName());
            mapOneStep.put("deadline", step.getDeadline().toString());
            mapOneStep.put("cost", Double.toString(step.getCost()));
            mapOneStep.put("complete", Boolean.toString(step.isComplete()));
            mapSteps.put(Long.toString(step.getId()), mapOneStep.toString());
        }
        mapPlan.put("steps", mapSteps.toString());

        return mapPlan;
    }

}