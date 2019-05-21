package edu.bsuirDev.controllers;

import edu.bsuirDev.database.UserSession;
import edu.bsuirDev.database.models.Plan;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

/**
 * the first controller
 * */

@RestController
@RequestMapping("/index")
public class IndexController {

    @GetMapping(produces="application/json")
    Map<String, String> index(@RequestParam(value = "id") long id)
    {
        UserSession userSession = new UserSession(id);
        HashMap<String, String> map = new HashMap<>();
        if(userSession.getUser() == null) {
            map.put("null", "null");
            return map;
        }
        for(Plan plan : userSession.getUser().getPlans()) {
            map.put(plan.getInfo(), Long.toString(plan.getId()));
        }

        return map;
    }
}
