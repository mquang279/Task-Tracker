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
            case "list-done":
                taskManager.listDoneTask();
                break;
            case "list-in-progress":
                taskManager.listInProgressTask();
                break;
            case "list-todo":
                taskManager.listToDoTask();
                break;
            case "mark-in-progress":
                taskManager.markInProgress(Integer.valueOf(args[1]));
                break;
            case "mark-done":
                taskManager.markInProgress(Integer.valueOf(args[1]));
                break;
        }
        taskManager.jsonUpdate();
    }
}
