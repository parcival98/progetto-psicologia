package booking_service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/appointments")
public class AppointmentController {

    @Autowired
    private AppointmentRepository appointmentRepository;

    // 1. Endpoint di Test
    @GetMapping("/status")
    public String getStatus() {
        return "Il Booking Service è attivo e funzionante sulla porta 8082!";
    }

    // 2. Crea un nuovo appuntamento (Richiesta del Paziente)
    @PostMapping
    public Appointment createAppointment(@RequestBody Appointment appointment) {
        // Assicuriamo che duri 60 minuti se non è già specificato
        if(appointment.getDataOraInizio() != null && appointment.getDataOraFine() == null) {
            appointment.setDataOraFine(appointment.getDataOraInizio().plusHours(1));
        }
        // Quando viene creato un nuovo appuntamento, lo stato iniziale è sempre IN_ATTESA
        if(appointment.getStato() == null || appointment.getStato().isEmpty()) {
            appointment.setStato("IN_ATTESA");
        }
        return appointmentRepository.save(appointment);
    }

    // 3. Visualizza tutti gli appuntamenti (Calendario generale per la Psicologa)
    @GetMapping
    public List<Appointment> getAllAppointments() {
        return appointmentRepository.findAll();
    }

    // 6. Metodo per cancellare/sbloccare un appuntamento
    @DeleteMapping("/{id}")
    public void eliminaAppuntamento(@PathVariable Long id) {
        appointmentRepository.deleteById(id);
    }
    // 4. Visualizza gli appuntamenti di un singolo paziente
    @GetMapping("/patient/{pazienteId}")
    public List<Appointment> getAppointmentsByPatient(@PathVariable Long pazienteId) {
        return appointmentRepository.findByPazienteId(pazienteId);
    }

    // 5. Metodo per la Psicologa: Accetta un appuntamento in attesa
    @PutMapping("/{id}/accetta")
    public Appointment accettaAppuntamento(@PathVariable Long id) {
        // Trova l'appuntamento nel database tramite l'ID
        Appointment appuntamento = appointmentRepository.findById(id)
            .orElseThrow(() -> new RuntimeException("Appuntamento non trovato con ID: " + id));
        
        // Cambia lo stato da "IN_ATTESA" a "CONFERMATO"
        appuntamento.setStato("CONFERMATO");
        
        // Salva l'aggiornamento nel database e restituisci l'oggetto aggiornato
        return appointmentRepository.save(appuntamento);
    }
}