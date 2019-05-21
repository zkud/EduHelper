package edu.bsuirDev.controllers;


import edu.bsuirDev.database.UserSession;
import edu.bsuirDev.database.models.Plan;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/delete")
public class DeleteController {
    @RequestMapping("/step")
    void deleteStep(@RequestParam(value = "userid") long userid,
                    @RequestParam(value = "planid") long planid,
                    @RequestParam(value = "stepid") long stepid) {
        UserSession userSession = new UserSession(userid);
        Plan plan = userSession.getPlan(planid);

        userSession.removeStep(planid, stepid);
    }
    @RequestMapping("/plan")
    void deleteStep(@RequestParam(value = "userid") long userid,
                    @RequestParam(value = "planid") long planid) {
        UserSession userSession = new UserSession(userid);
        userSession.removePlan(planid);
    }
    @RequestMapping("/user")
    void deleteStep(@RequestParam(value = "userid") long userid) {
        UserSession userSession = new UserSession(userid);
        userSession.deleteUser();
    }
}
