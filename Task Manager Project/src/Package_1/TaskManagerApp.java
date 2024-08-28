package Package_1;

import java.util.Scanner;

public class TaskManagerApp {
    private static TaskManager taskManager = new TaskManager();
    private static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        boolean running = true;

        while (running) {
            System.out.println("------------------------------------");
            System.out.println("Task Manager Menu:");
            System.out.println("1. Add Task");
            System.out.println("2. Display/Edit All Tasks");
            System.out.println("3. Search Task");
            System.out.println("4. Delete Task");
            System.out.println("5. Delete All Tasks");
            System.out.println("6. Exit");
            System.out.print("Choose an option: ");

            // Loop until valid input is provided
            while (!scanner.hasNextInt()) {
                System.out.println("Invalid input. Please enter a number between 1 and 6.");
                System.out.print("Choose an option: ");
                scanner.next(); // consume invalid input
            }
            int choice = scanner.nextInt();
            scanner.nextLine(); // consume newline

            switch (choice) {
                case 1:
                    System.out.print("Enter task title: ");
                    String title = scanner.nextLine();
                    System.out.print("Enter task description/date: ");
                    String description = scanner.nextLine();
                    taskManager.addTask(title, description);
                    break;
                case 2:
                    taskManager.displayAllTasks();
                    break;
                case 3:
                	searchTask();
                    break;
                case 4:
                	System.out.print("Enter task title to delete: ");
                    String taskTitle = scanner.nextLine();
                    taskManager.deleteTask(taskTitle);
                    break;
                case 5:
                	taskManager.deleteAllTasks();
                    break;
                case 6:
                    running = false;
                    break;
                    
                    
                    
                    
                default:
                    System.out.println("Invalid option. Please try again.");
                    break;
            }
        }

        System.out.println("Exiting Task Manager. Goodbye!");
        scanner.close();
    }

    private static void searchTask() {
        System.out.print("Enter the title of the task you want to search: ");
        String searchTitle = scanner.nextLine();
        taskManager.searchTask(searchTitle);
    }
}



