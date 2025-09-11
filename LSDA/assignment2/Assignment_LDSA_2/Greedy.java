package assignment2;

import java.util.LinkedList;

public class Greedy {

    public static LinkedList<Activity> activitySelection(LinkedList<Activity> activities) {
        // TASK 1.B.a
        // Create result list
        LinkedList<Activity> selectedActivites = new LinkedList<>();

        if (!activities.isEmpty()) { // if empty add first activity to selected activity list
            Activity firstActivity = activities.getFirst();
            selectedActivites.add(firstActivity);

            Activity lastSelected = firstActivity; // set added activity to last selected

            for (int i = 1; i < activities.size(); i++) {  // get an activity from remaining activities
                Activity currentActivity = activities.get(i);
                // if current activity doesn't overlap with the last selected activity, select it
                if (!lastSelected.overlap(currentActivity)) {
                    selectedActivites.add(currentActivity);
                    lastSelected = currentActivity; // update last selected activity
                }
            }
        }

        return selectedActivites;
    }

    public static LinkedList<Integer> makeChange(int amount, int[] denominations) {
        // TASK 1.B.b
        // Create result list
        LinkedList<Integer> selectedDenominations = new LinkedList<>();

        // Going through each denomination
        for (int i = 0; i < denominations.length; i++) {
            // while current denomination can reduce the amount, select it
            while (amount >= denominations[i]) {
                selectedDenominations.add(denominations[i]); // add current denomination to the list
                amount -= denominations[i]; // remove from the amount by the selected denomination
            }
            // once amount is 0 no need to continue
            if (amount == 0) {
                break;
            }
        }

        return selectedDenominations;

    }

    public static void main(String[] args) {
        LinkedList<Activity> activities = new LinkedList<Activity>();
        activities.add(new Activity(1,1, 4));
        activities.add(new Activity(2, 3, 5));
        activities.add(new Activity(3, 0, 6));
        activities.add(new Activity(4, 5, 7));
        activities.add(new Activity(5, 3, 8));
        activities.add(new Activity(6, 5, 9));
        activities.add(new Activity(7, 6, 10));
        activities.add(new Activity(8, 8, 11));
        activities.add(new Activity(9, 8, 12));
        activities.add(new Activity(10, 2, 13));
        activities.add(new Activity(11, 12, 14));
        activitySelection(activities).forEach(a -> a.print());

        System.out.println();
        makeChange(1234, new int[] { 5000, 2000, 1000, 500, 200, 100, 50, 20, 10, 5, 2, 1 }).forEach(i -> System.out.println(i));
    }
}
