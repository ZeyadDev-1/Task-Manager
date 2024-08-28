package Package_1;


import java.io.*;
import java.util.LinkedList;
import java.util.Scanner;

public class TaskManager {
    private LinkedList<Task> tasks;
    private TaskNode root;
    private static final String SAVE_FILE_NAME = "task_manager_data";
    
    
    
    Scanner scanner = new Scanner(System.in); 

    public TaskManager() {
        tasks = new LinkedList<>();
        root = null;
        loadTasks();
    }

    public void addTask(String title, String description) {
        // Check if thetitle is empty or consists spaces...
    	
        if (title.trim().isEmpty()) {
            System.out.println("Error: Unable to assign empty task names!");
            return; // return to the menu
        }

        String titleCheck = title.toLowerCase(); // Convert input title to lowercase

        // Check if the task title already exists
        while (isTitleAlreadyExist(titleCheck)) {
            System.out.println("Error: Task with title '" + title + "' already exists. Please enter a different title.");
            System.out.print("Enter task title: ");
            title = scanner.nextLine(); // Get a new title from the user
            titleCheck = title.toLowerCase(); // Convert to lowercase for comparison
        }

        // in case the title not exist >>>>
        Task newTask = new Task(title, description);
        tasks.add(newTask);
        if (root == null) {
            root = new TaskNode(newTask);
        } else {
            TaskNode newNode = new TaskNode(newTask);
            root.addChild(newNode);
        }
        System.out.println("[Task added successfully!]");
        saveTasks(); /*we should save here 
        after create save method..*/ 
    }

    // create boolean method to check existance
    private boolean isTitleAlreadyExist(String titleLowerCase) {
        for (Task task : tasks) {
            if (task.getTitle().toLowerCase().equals(titleLowerCase)) {
                return true; // Title already exists
            }
        }
        return false; 
    }

    
    public void deleteTask(String title) {
        String titleLowerCase = title.toLowerCase(); // Convert input title to lowercase
        Task taskToRemove = null;
        for (Task task : tasks) {
            if (task.getTitle().toLowerCase().equals(titleLowerCase)) { // Compare in lowercase
                taskToRemove = task;
                break;
            }
        }
        if (taskToRemove != null) {
            tasks.remove(taskToRemove);
            System.out.println("[Task '" + taskToRemove.getTitle() + "' deleted successfully!]");
            saveTasks(); // Save tasks after deletion
        } else {
            System.out.println("Task '" + title + "' not found. Unable to delete.");
        }
    }

    public void deleteAllTasks() {
        if (tasks.isEmpty()) {
            System.out.println("No tasks found to delete!");
            return; // rreturn to the menu
        }
        
        tasks.clear(); // Clear the list of tasks
        saveTasks(); // Save tasks after deletion.
        System.out.println("[All tasks deleted successfully!]");
    }
    
    

    public void displayAllTasks() {
        if (tasks.isEmpty()) {
            System.out.println("No tasks found.");
            return; //main menu
        }

        System.out.println("All Tasks:");
        System.out.println("<><><><><><><><><><><><>");
        for (Task task : tasks) {
            System.out.println(task.getTitle() + " - " + task.getDescription());
        }
        System.out.println("<><><><><><><><><><><><>");

        // Prompt for editing task details if the user inputs "e" or "t"
        System.out.print("Enter 'd' to edit a task's description, 't' to edit a task's title,\nor any other key to continue: ");
        String editOption = scanner.nextLine();
        switch (editOption) {
            case "d":
                editTaskDescription();
                break;
            case "t":
                editTaskTitle();
                break;
            default:
                // Do nothing
                break;
        }
    }

    private void editTaskDescription() {
        System.out.print("Enter the title of the task you want to edit: ");
        String titleToEdit = scanner.nextLine();
        Task taskToEdit = null;
        for (Task task : tasks) {
            if (task.getTitle().equalsIgnoreCase(titleToEdit)) {
                taskToEdit = task;
                break;
            }
        }
        if (taskToEdit != null) {
            System.out.print("Enter the new description: ");
            String newDescription = scanner.nextLine();
            taskToEdit.setDescription(newDescription);
            System.out.println("[Task description saved successfully.]");
            saveTasks(); // Save tasks after edit
        } else {
            System.out.println("Task not found.");
        }
    }

    private void editTaskTitle() {
        System.out.print("Enter the current title of the task you want to edit: ");
        String currentTitle = scanner.nextLine();
        Task taskToEdit = null;
        for (Task task : tasks) {
            if (task.getTitle().equalsIgnoreCase(currentTitle)) {
                taskToEdit = task;
                break;
            }
        }
        if (taskToEdit != null) {
            System.out.print("Enter the new title: ");
            String newTitle = scanner.nextLine();
            taskToEdit.setTitle(newTitle);
            System.out.println("[Task title saved successfully.]");
            saveTasks(); // Save task method here after edit
        } else {
            System.out.println("Task not found.");
        }
    }

    private void saveTasks() {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(SAVE_FILE_NAME))) {
            oos.writeObject(tasks);
            if (!tasks.isEmpty()) {
                System.out.println("[Tasks saved successfully!]");
            }
        } catch (IOException e) {
            System.out.println("Error saving tasks: " + e.getMessage());
        }
    }

    private void loadTasks() {
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(SAVE_FILE_NAME))) {
            tasks = (LinkedList<Task>) ois.readObject();
            if (tasks.isEmpty()) {
                System.out.println("Status: No saved tasks found.");
            } else {
                System.out.println("Status: Tasks loaded successfully!");
            }
        } catch (IOException | ClassNotFoundException e) {
            System.out.println("Status: No saved tasks found.");
       }
    }
    
    public void searchTask(String searchTitle) {
        String searchTitleLowerCase = searchTitle.toLowerCase(); // Convert input title to lowercase
        boolean found = false;
        for (Task task : tasks) {
            if (task.getTitle().toLowerCase().equals(searchTitleLowerCase)) {
            	System.out.println("<><><><><><><><><>");
                System.out.println("Task found:");
                System.out.println(task.getTitle() + " - " + task.getDescription());
                found = true;
                System.out.println("<><><><><><><><><>");
                break;
            }
        }
        if (!found) {
            System.out.println("Title does not exist !!");
        }
        
    }
 
}




