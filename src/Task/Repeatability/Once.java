package Task.Repeatability;

import Task.ObjectTask;
import Task.Personalization;

import java.time.LocalDate;
import java.time.LocalDateTime;

public class Once extends ObjectTask {

    public Once(String name, LocalDateTime dateTime, String description, Personalization personalization) {
        super(name, dateTime, description, personalization);
    }

    @Override
    public void getNextDate(ObjectTask task) {
        LocalDate date = getDateTime().toLocalDate();
        System.out.println(date);
    }

    public String getTaskRepeatability() {
        return "Не повторяется";
    }


    public boolean appearsIn(LocalDate localDate) {
        LocalDate taskCreationDay = getDateTime().toLocalDate();
        return taskCreationDay.equals(localDate);
    }

}
