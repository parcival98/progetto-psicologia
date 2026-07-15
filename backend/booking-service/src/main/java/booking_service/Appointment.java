package booking_service;

import java.time.LocalDateTime;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "appuntamenti")
public class Appointment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // Riferimento LOGICO al microservizio Patient (NON è una Foreign Key nel DB!)
    private Long pazienteId;

    private LocalDateTime dataOraInizio;
    private LocalDateTime dataOraFine;
    private String stato; // Es: "CONFERMATO", "CANCELLATO", "COMPLETATO"
    private String motivo;

    // Costruttore vuoto richiesto da Spring
    public Appointment() {}

    public Appointment(Long pazienteId, String motivo, LocalDateTime dataOraInizio, LocalDateTime dataOraFine, String stato) {
        this.pazienteId = pazienteId;
        this.motivo = motivo;
        this.dataOraInizio = dataOraInizio;
        this.dataOraFine = dataOraFine;
        this.stato = stato;
    }

    // --- GETTER E SETTER ---
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getMotivo() { return motivo; }
    public void setMotivo(String motivo) { this.motivo = motivo; }

    public Long getPazienteId() { return pazienteId; }
    public void setPazienteId(Long pazienteId) { this.pazienteId = pazienteId; }

    public LocalDateTime getDataOraInizio() { return dataOraInizio; }
    public void setDataOraInizio(LocalDateTime dataOraInizio) { this.dataOraInizio = dataOraInizio; }

    public LocalDateTime getDataOraFine() { return dataOraFine; }
    public void setDataOraFine(LocalDateTime dataOraFine) { this.dataOraFine = dataOraFine; }

    public String getStato() { return stato; }
    public void setStato(String stato) { this.stato = stato; }
}