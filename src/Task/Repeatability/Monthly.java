package Task.Repeatability;

import Task.ObjectTask;
import Task.Personalization;

import java.time.LocalDate;
import java.time.LocalDateTime;

import static Task.TaskSchedule.taskList;


public class Monthly extends ObjectTask {
    public Monthly(String name, LocalDateTime dateTime, String description, Personalization personalization) {
        super(name, dateTime, description, personalization);
    }

    public void getNextDate(ObjectTask task) {
        LocalDate date = task.getDateTime().toLocalDate();
        date = date.plusMonths(1);
        System.out.println(date);
    }

    public String getTaskRepeatability() {
        return "Повторяется ежемесячно";
    }

    public boolean appearsIn(LocalDate localDate) {
        boolean available = false;
        for (ObjectTask task : taskList.values()) {
            if (task.getDateTime().getDayOfMonth() == (localDate.getDayOfMonth())) {
                if (task.getDateTime().toLocalDate().isBefore(localDate) || task.getDateTime().toLocalDate().equals(localDate)) {
                    available = true;
                }
            }
        }
        return available;
    }
}
