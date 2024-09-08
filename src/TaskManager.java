import java.io.*;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

public class TaskManager {
    private ArrayList<Task> tasks = new ArrayList<>();
    private final Path path = Path.of("tasks.json");

    public TaskManager(){
        if(!Files.exists(path)) {
            try {
                Files.createFile(path);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public void jsonUpdate(){
        try {
            Files.newBufferedWriter(path, StandardOpenOption.TRUNCATE_EXISTING).close();
        } catch (IOException ioe) {
            ioe.printStackTrace();
        }
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("[\n");
        for (int i = 0; i < tasks.size(); i++){
            if (i != tasks.size() - 1){
                stringBuilder.append(tasks.get(i).toJson() + ",\n");
            }
            else{
                stringBuilder.append(tasks.get(i).toJson() + "\n]");
            }
        }
        try {
            Files.writeString(path, stringBuilder.toString(), StandardOpenOption.APPEND);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void tasksUpdate(){
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
                tasks.add(jsonToTask(line));
            }
        }
    }

    public Task jsonToTask(String json){
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
    }

    public void update(int id, String description){
        for (Task task : tasks){
            if (task.getCurrentID() == id){
                task.setDescription(description);
            }
        }
    }

    public void delete(int id){
        for (int i = 0; i < tasks.size(); i++){
            if (tasks.get(i).getCurrentID() == id){
                tasks.remove(i);
                break;
            }
        }
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
            if (task.getStatus() == Status.DONE){
                System.out.println(task);
            }
        }
    }

    public void markInProgress(int id){
        for (Task task : tasks){
            if (task.getCurrentID() == id){
                task.setStatus(Status.IN_PROGRESS);
            }
        }
    }

    public void markDone(int id){
        for (Task task : tasks){
            if (task.getCurrentID() == id){
                task.setStatus(Status.DONE);
            }
        }
    }
}
