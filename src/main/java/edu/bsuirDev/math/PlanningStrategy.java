package edu.bsuirDev.math;

import edu.bsuirDev.database.models.Plan;

/*
 * Base class of the all planning strategies
 */
public abstract class PlanningStrategy {
    public abstract Plan makePlan();
}
