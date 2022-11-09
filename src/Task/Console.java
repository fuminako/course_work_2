package Task;

import Task.Repeatability.*;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Map;
import java.util.Scanner;

import static Task.Personalization.PERSONAL;
import static Task.Personalization.WORKING;


public class Console {
    public static void main(String[] args) {
        try (Scanner scanner = new Scanner(System.in)) {
            label:
            while (true) {
                printMenu();
                System.out.print("Выберите пункт меню: ");
                if (scanner.hasNextInt()) {
                    int menu = scanner.nextInt();
                    switch (menu) {
                        case 1:
                            inputTask(scanner);
                            break;
                        case 2:
                            removeTask(scanner, TaskSchedule.taskList);
                            break;
                        case 3:
                            printTaskOnThisDay(scanner, TaskSchedule.taskList);
                            break;
                        case 0:
                            break label;
                    }
                } else {
                    scanner.next();
                    System.out.println("Выберите пункт меню из списка!");
                }
            }
        }

    }

    private static void inputTask(Scanner scanner) {
        try {
            System.out.print("Введите название задачи: ");
            String taskName = scanner.useDelimiter("\n").next();
            System.out.print("Введите дату задачи в формате yyyy/MM/dd HH:mm: ");
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm");
            LocalDateTime taskDate = LocalDateTime.parse(scanner.next(), formatter);
            System.out.print("Введите описание задачи: ");
            String taskDescription = scanner.useDelimiter("\n").next();


            System.out.print("Введите тип задачи:  " + "\n" +
                    "1. Рабочая" + "\n" +
                    "2. Личная" + "\n");
            Personalization taskPersonalization = null;
            int personalization = scanner.nextInt();
            try {
                if (personalization == 1) {
                    taskPersonalization = PERSONAL;
                } else if (personalization == 2) {
                    taskPersonalization = WORKING;
                } else {
                    throw new NoCorrectValueException();
                }
            } catch (NoCorrectValueException e) {
                System.out.println("Необходимо выбрать верный тип задачи");
            }


            System.out.println("Какова переодичность задачи? " + "\n" +
                    "1. Однократная" + "\n" +
                    "2. Ежедневная" + "\n" +
                    "3. Еженедельная" + "\n" +
                    "4. Ежемесячная" + "\n" +
                    "5. Ежегодная");
            int taskRepeatability = scanner.nextInt();
            try {
                if (taskRepeatability == 1) {
                    Once task = new Once(taskName, taskDate, taskDescription, taskPersonalization);
                    TaskSchedule.fillTaskList(task);
                    System.out.println("Задача добавлена");
                } else if (taskRepeatability == 2) {
                    Daily task = new Daily(taskName, taskDate, taskDescription, taskPersonalization);
                    TaskSchedule.fillTaskList(task);
                    System.out.println("Задача добавлена");
                } else if (taskRepeatability == 3) {
                    Weekly task = new Weekly(taskName, taskDate, taskDescription, taskPersonalization);
                    TaskSchedule.fillTaskList(task);
                    System.out.println("Задача добавлена");
                } else if (taskRepeatability == 4) {
                    Monthly task = new Monthly(taskName, taskDate, taskDescription, taskPersonalization);
                    TaskSchedule.fillTaskList(task);
                    System.out.println("Задача добавлена");
                } else if (taskRepeatability == 5) {
                    Annual task = new Annual(taskName, taskDate, taskDescription, taskPersonalization);
                    TaskSchedule.fillTaskList(task);
                    System.out.println("Задача добавлена");
                } else {
                    throw new NoCorrectValueException();
                }
            } catch (IllegalArgumentException e) {
                System.out.println("Данных недостаточно");
            } catch (NoCorrectValueException e) {
                System.out.println("Вы указали неверный тип или переодичность задачи");
            }
        } catch (DateTimeParseException e) {
            System.out.println("Неверный формат даты и времени");
        }

    }

    private static void removeTask(Scanner scanner, Map<Integer, ObjectTask> list) {
        System.out.print("Введите id задачи: ");
        int taskId = scanner.nextInt();
        try {
            TaskSchedule.removeTask(taskId);
            System.out.println("Задача удалена");
            for (ObjectTask task : list.values()) {
                System.out.printf("Список задач: %n %s, описание: %s; %s %n ", task.getName(), task.getDescription(), task.getTaskRepeatability());
            }
        } catch (TaskNotFoundException e) {
            System.out.println("Такой задачи не существует");
        }

    }

    private static void printMenu() {
        System.out.println("1. Добавить задачу" + "\n" +
                "2. Удалить задачу" + "\n" +
                "3. Получить задачу на указанный день" + "\n" +
                "0. Выход"
        );
    }

    private static void printTaskOnThisDay(Scanner scanner, Map<Integer, ObjectTask> list) {
        try {
            System.out.println("Введите дату в формате yyyy/MM/dd для получения списка задач ");
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy/MM/dd");
            LocalDate date = LocalDate.parse(scanner.next(), formatter);
            if (TaskSchedule.getTaskOnDay(date).isEmpty()) {
                System.out.println("Нет задач в этот день");
            } else {
                for (ObjectTask task : list.values()) {
                    System.out.printf("%s, дата задачи; %s, %s %n", task.getName(), task.getDateTime().toLocalDate(), task.getTaskRepeatability());
                }
            }
        } catch (DateTimeParseException e) {
            System.out.println("Неверный формат даты");
        }
    }

}
