package Task.Repeatability;

import Task.ObjectTask;
import Task.Personalization;

import java.time.LocalDate;
import java.time.LocalDateTime;

public class Daily extends ObjectTask {
    public Daily(String name, LocalDateTime dateTime, String description, Personalization personalization) {
        super(name, dateTime, description, personalization);
    }

    public void getNextDate(ObjectTask task) {
        LocalDate date = task.getDateTime().toLocalDate();
        date = date.plusDays(1);
        System.out.println(date);
    }

    public String getTaskRepeatability() {
        return "Повторяется ежедневно";
    }

    @Override
    public boolean appearsIn(LocalDate localDate) {
        LocalDate taskCreationDate = getDateTime().toLocalDate();
        return taskCreationDate.equals(localDate) || taskCreationDate.isBefore(localDate);
    }
}
