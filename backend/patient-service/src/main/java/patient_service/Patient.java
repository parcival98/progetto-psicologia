package patient_service;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "pazienti")
public class Patient {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nome;
    private String cognome;
    private String codiceFiscale;
    private String email;
    private String password; // In un'app reale andrebbe criptata, qui la teniamo in chiaro per semplicità
    private String cellulare;
    private String dataNascita;      
    private boolean accettazionePrivacy;

    // Se non usi Lombok, ricordati di generare i metodi Getter e Setter per questi 4 campi!
    

    // Costruttore vuoto (richiesto da Spring)
    public Patient() {}

    // Costruttore con parametri
    public Patient(String nome, String cognome, String dataNascita, String codiceFiscale, String email,String password, String cellulare, boolean accettazionePrivacy) {
        this.nome = nome;
        this.cognome = cognome;
        this.dataNascita = dataNascita;
        this.codiceFiscale = codiceFiscale;
        this.email = email;
        this.password = password;
        this.cellulare = cellulare;
        this.accettazionePrivacy = accettazionePrivacy;
    }

    // --- GETTER E SETTER ---
    // (In un progetto reale potremmo usare la libreria Lombok per non scriverli a mano, 
    // ma per l'esame va benissimo scriverli per far capire che sai cosa fanno)
    
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getNome() { return nome; }
    public void setNome(String nome) { this.nome = nome; }

    public String getDataNascita() { return dataNascita; }
    public void setDataNascita(String dataNascita) { this.dataNascita = dataNascita; }

    public String getCognome() { return cognome; }
    public void setCognome(String cognome) { this.cognome = cognome; }

    public String getCodiceFiscale() { return codiceFiscale; }
    public void setCodiceFiscale(String codiceFiscale) { this.codiceFiscale = codiceFiscale; }

    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }

    public String getPassword() { return password; }
    public void setPassword(String password) { this.password = password; }

    public String getCellulare() { return cellulare; }
    public void setCellulare(String cellulare) { this.cellulare = cellulare; }

    public boolean getAccettazionePrivacy() { return accettazionePrivacy; }
    public void setAccettazionePrivacy(boolean accettazionePrivacy) { this.accettazionePrivacy = accettazionePrivacy; }
}