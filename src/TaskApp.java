public class TaskApp {
    public static void main(String[] args){
        TaskManager taskManager = new TaskManager();
        String choice = args[0];
        taskManager.listOfTasksUpdate();
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
                taskManager.markDone(Integer.valueOf(args[1]));
                break;
            case "delete":
                taskManager.deleteTask(Integer.valueOf(args[1]));
                break;
            case "update":
                taskManager.updateTask(Integer.valueOf(args[1]), args[2]);
                break;
        }
        taskManager.jsonUpdate();
    }
}
