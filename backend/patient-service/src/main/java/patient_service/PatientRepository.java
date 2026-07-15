package patient_service;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PatientRepository extends JpaRepository<Patient, Long> {
    // Spring Boot capisce da solo come fare le query base (save, findAll, findById, delete).
    // Se volessimo, potremmo aggiungere metodi personalizzati qui, ad esempio:
    // List<Patient> findByCognome(String cognome);
    Patient findByEmailAndPassword(String email, String password);
}