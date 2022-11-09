package Task.Repeatability;

import Task.ObjectTask;
import Task.Personalization;

import java.time.LocalDate;
import java.time.LocalDateTime;

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
        if (getDateTime().getDayOfMonth() == (localDate.getDayOfMonth())) {
            if (getDateTime().toLocalDate().isBefore(localDate) || getDateTime().toLocalDate().equals(localDate)) {
                available = true;
            }
        }
        return available;
    }
}
