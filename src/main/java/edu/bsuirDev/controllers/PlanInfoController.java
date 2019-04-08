package edu.bsuirDev.controllers;

import edu.bsuirDev.database.UserSession;
import edu.bsuirDev.database.models.Plan;
import edu.bsuirDev.database.models.Step;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
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

        HashMap<String, String> mapStep = new HashMap<>();
        for(Step step : userSession.getSteps(planid)) {
            mapStep.put("name", step.getName());
        }
       // mapPlan.put("steps", mapStep.toString());
        return mapPlan;
    }

}
