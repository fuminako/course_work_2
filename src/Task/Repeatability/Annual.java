package Task.Repeatability;

import Task.ObjectTask;
import Task.Personalization;

import java.time.LocalDate;
import java.time.LocalDateTime;

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
        if (getDateTime().toLocalDate().getDayOfYear() == localDate.getDayOfYear() && getDateTime().getMonth().equals(localDate.getMonth())) {
            if (getDateTime().toLocalDate().isBefore(localDate) || getDateTime().toLocalDate().equals(localDate)) {
                available = true;
            }
        }
        return available;
    }
}
