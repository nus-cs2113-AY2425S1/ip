package cristiano.goals;

import java.util.ArrayList;
import java.util.List;

/**
 * Manages a list of goals for Ronaldo.
 * This class helps to add, delete, search and return goals.
 */
public class GoalList {
    private final List<Goal> goalList;

    public GoalList() {
        this.goalList = new ArrayList<>();
    }

    public int getSize() {
        return goalList.size();
    }

    public void printGoalCount() {
        if (goalList.size() == 1) {
            System.out.println("Now you have " + getSize() + " goal in the list.\n");
        } else {
            System.out.println("Now you have " + getSize() + " goals in the list.\n");
        }
    }

    public void addGoal(Goal goal) {
        goalList.add(goal);
        printGoalCount();
    }

    public void deleteGoal(int index) {
        goalList.remove(index);
        printGoalCount();
    }

    public Goal getGoalNumber(int index) {
        return goalList.get(index);
    }

    public void printGoals() {
        System.out.println("Here are the goals to complete in order for you to reach your dreams:");
        for (int i = 0; i < goalList.size(); i++) {
            System.out.println((i + 1) + ". " + goalList.get(i));
        }
    }

    public List<Goal> getAllGoals() {
        return goalList;
    }

    /**
     * Finds goals that matches said keyword.
     * Including spaces affects the keyword search.
     *
     * @param keyword contains keywords that are to be matched.
     * @return list of matching goals.
     */
    public List<Goal> findMatchingGoals(String keyword) {
        List<Goal> matchingGoals = new ArrayList<>();
        for (Goal goal : goalList) {
            if (goal.description.contains(keyword)) {
                matchingGoals.add(goal);
            }
        }
        return matchingGoals;
    }

}
