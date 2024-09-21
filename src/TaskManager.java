import java.io.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Optional;

public class TaskManager {
    private ArrayList<Task> tasks;

    public TaskManager(){
        this.tasks = new ArrayList<>();
    }

    public void updateListOfTasks(){
        //Read from json file
        BufferedReader reader = null;
        try {
            reader = new BufferedReader(new FileReader("tasks.json"));
        }
        catch (FileNotFoundException fnfe){
            fnfe.printStackTrace();
        }
        String line = null;
        while (true) {
            try {
                if ((line = reader.readLine()) == null) break;
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
            if (line.length() != 1){
                if (line.endsWith(",")) line = line.substring(0, line.length() - 1);
                line = line.substring(0, line.length() - 1);
                tasks.add(convertJsonToTask(line));
            }
        }
    }

    public Task convertJsonToTask(String json){
        String[] properties = json.split(",");
        //id : 0, description : 1, status : 2, createdAt : 3, updatedAt : 4
        int currentProperty = 0;
        int id = 0;
        String description = "";
        Status status = null;
        LocalDateTime createdAt = null, updatedAt = null;
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH-mm-ss");
        for (String property : properties){
            property = property.split(":")[1];
            property = property.substring(1, property.length() - 1);
            switch (currentProperty){
                case 0:
                    id = Integer.valueOf(property);
                    break;
                case 1:
                    description = property;
                    break;
                case 2:
                    status = Status.valueOf(property);
                    break;
                case 3:
                    createdAt = LocalDateTime.parse(property, formatter);
                    break;
                case 4:
                    updatedAt = LocalDateTime.parse(property, formatter);
                    break;
            }
            currentProperty++;
        }
        return new Task(id, description, status, createdAt, updatedAt);
    }

    public void add(String description){
        Task task = new Task(description);
        tasks.add(task);
        System.out.println("Task added successfully (ID: " + task.getId() + ")");
    }

    public void updateTask(int id, String description){
        Task task = findTask(id).orElseThrow(() -> new IllegalArgumentException("Task with " + id + "not found"));
        task.setDescription(description);
        task.setUpdatedAt(LocalDateTime.now());
        System.out.println("Cannot find task with ID: " + id + ".");
    }

    public void listTasks(){
        for (Task task : tasks){
            System.out.println(task);
        }
    }

    public void listDoneTask(){
        for (Task task : tasks){
            if (task.getStatus() == Status.DONE){
                System.out.println(task);
            }
        }
    }

    public void listToDoTask(){
        for (Task task : tasks){
            if (task.getStatus() == Status.TODO){
                System.out.println(task);
            }
        }
    }

    public void listInProgressTask(){
        for (Task task : tasks){
            if (task.getStatus() == Status.IN_PROGRESS){
                System.out.println(task);
            }
        }
    }

    public void markInProgress(int id){
        Task task = findTask(id).orElseThrow(() -> new IllegalArgumentException("Task with " + id + "not found"));
        task.setStatus(Status.IN_PROGRESS);
    }

    public void markDone(int id){
        Task task = findTask(id).orElseThrow(() -> new IllegalArgumentException("Task with " + id + " not found"));
        task.setStatus(Status.DONE);
    }

    public void deleteTask(int id){
        Task task = findTask(id).orElseThrow(() -> new IllegalArgumentException("Task with " + id + " not found"));
        tasks.remove(task);
    }

    public Optional<Task> findTask(int id) {
        return tasks.stream().filter((task) -> task.getId() == id).findFirst();
    }

    public ArrayList<Task> getTasks(){
        return this.tasks;
    }
}
