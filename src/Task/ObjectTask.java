package Task;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Objects;

public abstract class ObjectTask {
    private final int id;
    private static int counter = 0;
    private final String name;
    private final String description;
    private final LocalDateTime dateTime;
    private final Personalization personalization;

    public ObjectTask(String name, LocalDateTime dateTime, String description, Personalization personalization) {
        if (name == null || name.isBlank() || dateTime == null || description == null || description.isBlank() || personalization == null) {
            throw new IllegalArgumentException("Недостаточно данных");
        } else {
            this.name = name;
            this.dateTime = dateTime;
            this.id = counter++;
            this.description = description;
            this.personalization = personalization;
        }
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public LocalDateTime getDateTime() {
        return dateTime;
    }

    public Personalization getPersonalization() {
        return personalization;
    }

    public int getId() {
        return id;
    }

    public abstract void getNextDate(ObjectTask task);

    public abstract String getTaskRepeatability();

    public abstract boolean appearsIn(LocalDate localDate);

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ObjectTask task = (ObjectTask) o;
        return id == task.id && Objects.equals(name, task.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name);
    }

}
