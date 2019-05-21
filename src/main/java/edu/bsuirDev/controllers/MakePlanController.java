package edu.bsuirDev.controllers;

import edu.bsuirDev.extra.MakePlanInfo;
import edu.bsuirDev.math.PlanningStrategy;
import edu.bsuirDev.math.LPPlanningStrategy;

import edu.bsuirDev.database.UserSession;
import edu.bsuirDev.database.models.Plan;
import edu.bsuirDev.database.models.Step;

import edu.bsuirDev.extra.Task;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.time.Duration;
import java.time.LocalTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


/*
*  Controller for plan creation
* */
@RestController
@RequestMapping("/makeplan")
public class MakePlanController {
    // creates plan as we want
    private Plan createPlan(String planName, List<Task> tasks,
                            LocalTime timeToPerform, java.sql.Date deadline)
    {
        PlanningStrategy strategy;
        strategy = new LPPlanningStrategy(
                tasks,
                Duration.between(LocalTime.MIN, timeToPerform),
                deadline
        );

        Plan plan = strategy.makePlan();
        plan.setInfo(planName);

        return plan;
    }

    // creates output
    private Map<String, String> constructOutput(UserSession session, Plan plan)
    {
        // init map of Plan
        HashMap<String, String> mapPlan = new HashMap<>();
        mapPlan.put("name", plan.getInfo());
        mapPlan.put("expected result", Double.toString(plan.getResult()));

        // init steps field
        HashMap<String, String> mapSteps = new HashMap<>();
        HashMap<String, String> mapOneStep = new HashMap<>();

        List<Step> list = session.getSteps(plan.getId());
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

    @RequestMapping(method = RequestMethod.POST,
                    consumes = MediaType.APPLICATION_JSON_VALUE,
                    produces = MediaType.APPLICATION_JSON_VALUE)
    Map<String, String> makeplan(@RequestBody MakePlanInfo planInfo)
    {
        // create plan
        Plan plan = createPlan(planInfo.getPlanName(),
                                planInfo.getTasksInfo(),
                                planInfo.getTimeToPerform(),
                                planInfo.getDeadline());

        // run user session
        UserSession userSession = new UserSession(planInfo.getUserID());

        // add plan to db
        userSession.addPlan(plan);

        return constructOutput(userSession, plan);
    }

}
