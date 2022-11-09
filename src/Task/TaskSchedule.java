package Task;

import java.time.LocalDate;
import java.util.*;

public class TaskSchedule {
    public static Map<Integer, ObjectTask> taskList = new HashMap<>();

    public static void fillTaskList(ObjectTask task) {
        taskList.put(task.getId(), task);
    }

    public static void removeTask(int id) throws TaskNotFoundException {
        if (!taskList.containsKey(id)) {
            throw new TaskNotFoundException();
        }
        taskList.remove(id);
    }

    public static Collection<ObjectTask> getTaskOnDay(LocalDate localDate) {
        Set<ObjectTask> taskOnDay = new TreeSet<>(new TaskTimeSorting());
        for (ObjectTask task : taskList.values()) {
            if (task.appearsIn(localDate)) {
                taskOnDay.add(task);
            }
        }
        return taskOnDay;
    }
}
