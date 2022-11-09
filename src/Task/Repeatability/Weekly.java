package Task.Repeatability;

import Task.ObjectTask;
import Task.Personalization;

import java.time.LocalDate;
import java.time.LocalDateTime;

public class Weekly extends ObjectTask {
    public Weekly(String name, LocalDateTime dateTime, String description, Personalization personalization) {
        super(name, dateTime, description, personalization);
    }

    public void getNextDate(ObjectTask task) {
        LocalDate date = task.getDateTime().toLocalDate();
        date = date.plusWeeks(1);
        System.out.println(date);
    }

    public String getTaskRepeatability() {
        return "Повторяется еженедельно";
    }

    public boolean appearsIn(LocalDate localDate) {
        boolean available = false;
        if (getDateTime().toLocalDate().getDayOfWeek().equals(localDate.getDayOfWeek())) {
            if (getDateTime().toLocalDate().isBefore(localDate) || getDateTime().toLocalDate().equals(localDate)) {
                available = true;
            }
        }
        return available;
    }

}
