package Task.Repeatability;

import Task.ObjectTask;
import Task.Personalization;

import java.time.LocalDate;
import java.time.LocalDateTime;

import static Task.TaskSchedule.taskList;


public class Annual extends ObjectTask {
    public Annual(String name, LocalDateTime dateTime, String description, Personalization personalization) {
        super(name, dateTime, description, personalization);
    }

    public void getNextDate(ObjectTask task) {
        LocalDate date = task.getDateTime().toLocalDate();
        date = date.plusYears(1);
        System.out.println(date);
    }

    public String getTaskRepeatability() {
        return "Повторяется ежегодно";
    }

    @Override
    public boolean appearsIn(LocalDate localDate) {
        boolean available = false;
        for (ObjectTask task : taskList.values()) {
            if ((task.getDateTime().getDayOfYear() == (localDate.getDayOfYear())) && (task.getDateTime().getMonth() == (localDate.getMonth()))) {
                if (task.getDateTime().toLocalDate().isBefore(localDate)) {
                    available = true;
                }
            }
        }
        return available;
    }

}
