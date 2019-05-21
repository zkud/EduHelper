package edu.bsuirDev.controllers;


import edu.bsuirDev.database.UserSession;
import edu.bsuirDev.database.models.Plan;
import edu.bsuirDev.database.models.Step;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;
import java.util.Map;


@RestController
@RequestMapping("/login")
public class LoginController {
    @GetMapping(produces="application/json")
    Map<String, String> planinfo(@RequestParam(value = "name") String name,
                                 @RequestParam(value = "mail") String mail,
                                 @RequestParam(value = "password") String password,
                                 @RequestParam(value = "flag") boolean flag)
    {
        //true - регистрация
        //false - логин
        HashMap<String, String> map = new HashMap<>();

        if(flag) {
            UserSession userSession = UserSession.createNewUser(name, mail, password);
            if(userSession == null) {
                map.put("id", "null");
            }
            else {
                map.put("id", Long.toString(userSession.getUser().getId()));
            }
        }
        else {
            UserSession userSession = new UserSession(name, mail, password);
            if(userSession.getUser() == null) {
                map.put("id", "null");
            }
            else {
                map.put("id", Long.toString(userSession.getUser().getId()));
            }
        }

        return map;
    }
}
