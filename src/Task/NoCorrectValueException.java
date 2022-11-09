package Task;

public class NoCorrectValueException extends RuntimeException{
    public NoCorrectValueException() {
    }

    public NoCorrectValueException(String message) {
        super(message);
    }
}
