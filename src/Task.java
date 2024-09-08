import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeFormatterBuilder;

public class Task {
    private static int id = 0;
    private String description;
    private Status status;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    private int currentID;
    private static final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH-mm-ss");

    public Task(String description){
        id++;
        currentID = id;
        this.description = description;
        status = Status.TODO;
        createdAt = LocalDateTime.now();
        updatedAt = LocalDateTime.now();
    }

    public Task(int currentID, String description, Status status, LocalDateTime createdAt, LocalDateTime updatedAt){
        this.currentID = currentID;
        this.description = description;
        this.status = status;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }

    public static int getId() {
        return id;
    }

    public static void setId(int id) {
        Task.id = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public LocalDateTime getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(LocalDateTime updatedAt) {
        this.updatedAt = updatedAt;
    }

    public int getCurrentID() {
        return currentID;
    }

    public void setCurrentID(int currentID) {
        this.currentID = currentID;
    }

    @Override
    public String toString() {
        return "Task{" +
                "description='" + description + '\'' +
                ", status=" + status +
                ", createdAt=" + createdAt.format(formatter) +
                ", updatedAt=" + updatedAt.format(formatter) +
                ", currentID=" + currentID +
                '}';
    }

    public String toJson(){
        return "{\"id\":\"" + this.currentID + "\", " +
                "\"description\":\"" + this.description + "\", " +
                "\"status\":\"" + this.status + "\", " +
                "\"createdAt\":\"" + this.createdAt.format(formatter) + "\", " +
                "\"updatedAt\":\"" + this.createdAt.format(formatter) + "\"}";
    }
}
