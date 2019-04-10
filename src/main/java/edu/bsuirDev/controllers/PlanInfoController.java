package edu.bsuirDev.controllers;

import edu.bsuirDev.database.UserSession;
import edu.bsuirDev.database.models.Plan;
import edu.bsuirDev.database.models.Step;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/planinfo")
public class PlanInfoController {

    @GetMapping(produces="application/json")
    Map<String, String> planinfo(@RequestParam(value = "userid") long userid,
                                 @RequestParam(value = "planid") long planid)
    {
        //не работает с неверным id
        UserSession userSession = new UserSession(userid);
        Plan plan = userSession.getPlan(planid);

        HashMap<String, String> mapPlan = new HashMap<>();
        mapPlan.put("name", plan.getInfo());
        mapPlan.put("expected result", Double.toString(plan.getResult()));

        HashMap<String, String> mapSteps = new HashMap<>();
        HashMap<String, String> mapOneStep = new HashMap<>();
        List<Step> list = userSession.getSteps(planid);
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
