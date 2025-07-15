package de.fhdortmund.schrottverwaltung.mitarbeiter;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "mitarbeiter")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Mitarbeiter {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String vorname;

    private String nachname;

    private String passwort;

    private String mail;

    private Long fachbereichNr;


    public static class MitarbeiterBuilder {
        private Long id;
        private String vorname;
        private String nachname;
        private String passwort;
        private String mail;
        private Long fachbereichNr;

        public MitarbeiterBuilder setId(Long id) {
            this.id = id;
            return this;
        }

        public MitarbeiterBuilder setVorname(String vorname) {
            this.vorname = vorname;
            return this;
        }

        public MitarbeiterBuilder setNachname(String nachname) {
            this.nachname = nachname;
            return this;
        }

        public MitarbeiterBuilder setPasswort(String passwort) {
            this.passwort = passwort;
            return this;
        }

        public MitarbeiterBuilder setMail(String mail) {
            this.mail = mail;
            return this;
        }

        public MitarbeiterBuilder setFachbereichNr(Long fachbereichNr) {
            this.fachbereichNr = fachbereichNr;
            return this;
        }

        public Mitarbeiter build() {
            return new Mitarbeiter(id, vorname, nachname, passwort, mail, fachbereichNr);
        }
    }

    public static MitarbeiterBuilder builder() {
        return new MitarbeiterBuilder();
    }
}
