import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeFormatterBuilder;

public class Task {
    private static int maxID = 0;
    private int id = 0;
    private String description;
    private Status status;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    private static final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH-mm-ss");

    public Task(String description){
        maxID++;
        this.id = maxID;
        this.description = description;
        status = Status.TODO;
        createdAt = LocalDateTime.now();
        updatedAt = LocalDateTime.now();
    }

    public Task(int id, String description, Status status, LocalDateTime createdAt, LocalDateTime updatedAt){
        maxID = Math.max(maxID, id);
        this.id = id;
        this.description = description;
        this.status = status;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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

    @Override
    public String toString() {
        return "Task{" +
                "taskName='" + description + '\'' +
                ", status=" + status +
                ", createdAt=" + createdAt.format(formatter) +
                ", updatedAt=" + updatedAt.format(formatter) +
                ", id=" + id +
                '}';
    }

    public String toJson(){
        return "{\"id\":\"" + this.id + "\", " +
                "\"description\":\"" + this.description + "\", " +
                "\"status\":\"" + this.status + "\", " +
                "\"createdAt\":\"" + this.createdAt.format(formatter) + "\", " +
                "\"updatedAt\":\"" + this.createdAt.format(formatter) + "\"}";
    }
}
