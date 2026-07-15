package booking_service;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AppointmentRepository extends JpaRepository<Appointment, Long> {
    
    // Metodo magico di Spring: cerca in automatico nel DB gli appuntamenti con questo pazienteId
    List<Appointment> findByPazienteId(Long pazienteId);
}