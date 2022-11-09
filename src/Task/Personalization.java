package Task;

public enum Personalization {
     PERSONAL("личная"),
     WORKING("рабочая");

     private final String type;

     Personalization(String type) {
          this.type = type;
     }

     @Override
     public String toString() {
          return "Тип задачи: " + type;
     }

}
