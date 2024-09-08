import java.io.IOException;

public class TaskApp {
    public static void main(String[] args){
        TaskManager taskManager = new TaskManager();
        String choice = args[0];
        taskManager.tasksUpdate();
        switch (choice){
            case "add":
                taskManager.add(args[1]);
                break;
            case "list":
                taskManager.listTasks();
                break;
        }
        taskManager.jsonUpdate();
    }
}
