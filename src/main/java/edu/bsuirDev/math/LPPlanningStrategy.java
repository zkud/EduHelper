package edu.bsuirDev.math;

import edu.bsuirDev.database.models.Plan;
import edu.bsuirDev.database.models.Step;
import edu.bsuirDev.exceptions.IncorrectArgsException;
import edu.bsuirDev.exceptions.UnrealOptimizationException;
import edu.bsuirDev.extra.Task;
import org.apache.commons.math3.optim.PointValuePair;
import org.apache.commons.math3.optim.linear.*;
import org.apache.commons.math3.optim.nonlinear.scalar.GoalType;

import java.sql.Date;
import java.time.Duration;
import java.util.Collection;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.List;

/*
 * Uses linear programming to find the best strategy how to do tasks
 * forms result and return it in Plan class form
 * @author L1ttl3S1st3r
 * */
public class LPPlanningStrategy extends PlanningStrategy {
    private Date deadline;
    private List<Task> tasks;
    private Duration timeToPerform;

    public LPPlanningStrategy(List<Task> tasks, Duration timeToPerform, Date deadline)
            throws NullPointerException, IncorrectArgsException {
        if (tasks == null || timeToPerform == null || deadline == null) {
            throw new NullPointerException("one of arg is null");
        }

        if (tasks.size() <= 0) {
            throw new IncorrectArgsException("task list can\'t be empty");
        }

        if (timeToPerform.compareTo(Duration.ZERO) <= 0) {
            throw new IncorrectArgsException("time to perform can\'t be empty");
        }

        if (deadline.before(new Date(System.currentTimeMillis()))) {
            throw new IncorrectArgsException("deadline can\'t be in the past");
        }

        this.tasks = tasks;
        this.timeToPerform = timeToPerform;
        this.deadline = deadline;
    }

    private LinearObjectiveFunction convertTasksToFunction() {
        tasks.sort(
                Comparator.comparing(
                        (Task task) -> task.getCost() / task.getVariantsCount()
                ).reversed()
        ); // sort by coef

        return new LinearObjectiveFunction(
                tasks.stream().mapToDouble((task) -> task.getCost() / task.getVariantsCount()).toArray(),
                0
        );
    }

    private LinearConstraintSet convertTasksToConstraits() {

        double[] maskVector = new double[tasks.size()];
        maskVector[0] = 1;
        int oneIndex = -1;

        Collection<LinearConstraint> constraints = new LinkedList<>();
        for (Task task : tasks) {
            // shift vector to the right
            oneIndex++;
            maskVector[oneIndex] = 1;

            if (oneIndex > 0) {
                maskVector[oneIndex - 1] = 0;
            }

            // form constraint
            //  0 <= count of implemented task variants <= max count
            constraints.add(new LinearConstraint(maskVector, Relationship.LEQ, task.getVariantsCount()));
            constraints.add(new LinearConstraint(maskVector, Relationship.GEQ, 0));
        }

        double[] timeConstraintVector = tasks.stream().mapToDouble(task -> task.getTime().getSeconds()).toArray();

        constraints.add(new LinearConstraint(timeConstraintVector, Relationship.GEQ, 0));
        constraints.add(new LinearConstraint(timeConstraintVector, Relationship.LEQ, timeToPerform.getSeconds()));

        return new LinearConstraintSet(constraints);
    }

    @Override
    public Plan makePlan() {
        // I used a stack overflow example a bit
        // (https://stackoverflow.com/questions/39309486/apache-commons-math3-how-to-use-linear-programming)
        // thanks to these people

        //describe the optimization problem
        LinearObjectiveFunction function = this.convertTasksToFunction();

        //create and run solver
        PointValuePair solution = new SimplexSolver().optimize(function, convertTasksToConstraits(), GoalType.MAXIMIZE);

        // form a plan, that uses solution
        if (solution != null) {
            Plan resultPlan = new Plan(solution.getValue(), "");

            int taskIndex = 0;

            for (Task task : tasks) {
                int count = (int) solution.getPoint()[taskIndex];

                while (count  --> 0) {
                    resultPlan.addStep(new Step(task.getName(), deadline, task.getCost() / task.getVariantsCount()));
                }

                taskIndex++;
            }

            return resultPlan;
        } else {
            throw new UnrealOptimizationException("optimization is impossible");
        }
    }
}
