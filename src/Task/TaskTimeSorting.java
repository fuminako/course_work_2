package Task;

import java.util.Comparator;

public class TaskTimeSorting implements Comparator<ObjectTask> {

    public int compare(ObjectTask task1, ObjectTask task2) {
        return task1.getDateTime().toLocalTime().compareTo(task2.getDateTime().toLocalTime());
    }

}
