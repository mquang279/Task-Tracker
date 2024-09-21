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
}
