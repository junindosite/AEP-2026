import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class HistoricoStatus {
    private Status status;
    private String comentario;
    private String data;
    private String responsavel;

    public HistoricoStatus(Status status, String comentario, String responsavel) {
        this.status = status;
        this.comentario = comentario;
        this.responsavel = responsavel;
        this.data = LocalDateTime.now()
                .format(DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm"));
    }

    @Override
    public String toString() {
        return String.format("[%s] STATUS: %-12s | RESP: %-15s | MSG: %s",
                data, status, responsavel, comentario);
    }
}