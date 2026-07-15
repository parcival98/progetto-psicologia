package patient_service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/patients")
public class PatientController {

    // DEPENDENCY INJECTION: Diciamo a Spring di "iniettare" automaticamente 
    // il repository senza dover fare "new PatientRepository()"
    @Autowired
    private PatientRepository patientRepository;

    // 1. Endpoint per TESTARE se il server è su
    @GetMapping("/status")
    public String getStatus() {
        return "Il Patient Service è attivo e funzionante sulla porta 8081!";
    }

    // 2. Endpoint per CREARE un nuovo paziente (Riceve un JSON e lo salva nel DB)
    @PostMapping
    public Patient createPatient(@RequestBody Patient patient) {
        return patientRepository.save(patient);
    }

    // 3. Endpoint per LEGGERE tutti i pazienti
    @GetMapping
    public List<Patient> getAllPatients() {
        return patientRepository.findAll();
    }

    @GetMapping("/{id}")
    public Patient getPatientById(@PathVariable Long id) {
        return patientRepository.findById(id).orElseThrow();
    }


    @PostMapping("/login")
public ResponseEntity<?> login(@RequestBody Map<String, String> credenziali) {
    // 1. Estraiamo in modo sicuro solo i due dati inviati dal frontend
    String email = credenziali.get("email");
    String password = credenziali.get("password");

    // 2. Controllo di sicurezza su dati mancanti
    if (email == null || password == null) {
        return ResponseEntity
                .status(HttpStatus.BAD_REQUEST)
                .body("Email e password sono campi obbligatori");
    }

    // 3. Cerchiamo nel database
    Patient paziente = patientRepository.findByEmailAndPassword(email, password);
    
    // 4. Se le credenziali sono sbagliate, restituiamo HTTP 401 (Unauthorized) invece del 500!
    if (paziente == null) {
        return ResponseEntity
                .status(HttpStatus.UNAUTHORIZED) // Codice HTTP 401
                .body("Credenziali non valide. Controlla email e password.");
    }
    
    // 5. Se tutto è corretto, restituiamo HTTP 200 OK con i dati del paziente
    return ResponseEntity.ok(paziente);
}
}