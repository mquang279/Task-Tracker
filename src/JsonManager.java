import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

public class JsonManager {
    private final Path path = Path.of("tasks.json");

    public JsonManager(){
        if (!Files.exists(path)){
            try {
                Files.createFile(path);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    }

    public void updateJsonFile(ArrayList<Task> tasks){
        //Clear contents
        try {
            Files.newBufferedWriter(path, StandardOpenOption.TRUNCATE_EXISTING).close();
        } catch (IOException ioe) {
            throw new RuntimeException(ioe);
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

    public static Task convertJsonToTask(String json){
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
}
