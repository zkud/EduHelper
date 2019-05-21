package math;


import edu.bsuirDev.database.models.Plan;
import edu.bsuirDev.database.models.Step;
import edu.bsuirDev.extra.Task;
import edu.bsuirDev.math.LPPlanningStrategy;

import java.time.Duration;
import java.time.Instant;
import java.util.ArrayList;
import java.util.Calendar;

/*
public class LPPlanningStrategyTest {
    public static void test() {
        // form next day
        java.util.Date uDate = new java.util.Date();
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(uDate);
        calendar.add(Calendar.DAY_OF_YEAR, 1);

        java.sql.Date sDate = new java.sql.Date(calendar.getTimeInMillis());

        // form tasks
        Instant instant1 = Instant.now();
        Instant instant2 = Instant.now();

        Duration duration = Duration.between(instant2, instant1);

        Task task1 = new Task("1", duration.plusHours(1), 5.5, 16);
        Task task2 = new Task("2", duration.plusHours(1), 6.3, 2);

        ArrayList<Task> tasks = new ArrayList<>();
        tasks.add(task1);
        tasks.add(task2);

        // make strategy
        LPPlanningStrategy strategy = new LPPlanningStrategy(tasks, duration.plusHours(10), sDate);

        // use it
        Plan plan = strategy.makePlan();

        System.out.println(plan.getResult());

        for (Step step : plan.getSteps()) {
            System.out.println(step.getName() + " " + step.getCost());
        }
    }
} */
