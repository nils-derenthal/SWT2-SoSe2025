CREATE TABLE adresse (
                         adresse_id INT GENERATED ALWAYS AS IDENTITY PRIMARY KEY,
                         strasse VARCHAR(256),
                         hausnummer INT,
                         hausnummer_zusatz VARCHAR(256),
                         plz INT,
                         ort VARCHAR(256),
                         stadtbezirk VARCHAR(256)
);

CREATE TABLE eigentuemer (
                             eigentuemer_id INT GENERATED ALWAYS AS IDENTITY PRIMARY KEY,
                             vorname VARCHAR(256),
                             nachname VARCHAR(256),
                             adresse_id INT,
                             anmerkung VARCHAR(256),
                             CONSTRAINT fk_adresse_id FOREIGN KEY (adresse_id) REFERENCES adresse(adresse_id)
);

CREATE TABLE koordinaten (
                             koordinaten_id INT GENERATED ALWAYS AS IDENTITY PRIMARY KEY,
                             x_koordinate DOUBLE PRECISION,
                             y_koordinate DOUBLE PRECISION
);

CREATE TABLE immobilien_status (
                                   immobilien_status_id INT GENERATED ALWAYS AS IDENTITY PRIMARY KEY,
                                   status VARCHAR(256),
                                   beschreibung VARCHAR(256)
);

CREATE TABLE immobilie (
                           immobilie_id INT GENERATED ALWAYS AS IDENTITY PRIMARY KEY,
                           bezeichnung VARCHAR(256),
                           adresse_id INT,
                           archiviert BOOLEAN,
                           zustand VARCHAR(256),
                           koordinaten_id INT,
                           gemarkung VARCHAR(256),
                           flur VARCHAR(256),
                           flurstueck VARCHAR(256),
                           quadratmeter INT,
                           gebaeudetyp VARCHAR(256),
                           eigentumsform VARCHAR(256),
                           eigentuemer_id INT,
                           status_id INT,
                           bild TEXT,
                           CONSTRAINT fk_adresse_id FOREIGN KEY (adresse_id) REFERENCES adresse(adresse_id),
                           CONSTRAINT fk_koordinaten_id FOREIGN KEY (koordinaten_id) REFERENCES koordinaten(koordinaten_id),
                           CONSTRAINT fk_eigentuemer_id FOREIGN KEY (eigentuemer_id) REFERENCES eigentuemer(eigentuemer_id),
                           CONSTRAINT fk_immobilien_status_id FOREIGN KEY (status_id) REFERENCES immobilien_status(immobilien_status_id)
);

CREATE TABLE kriterium (
                           kriterium_id INT GENERATED ALWAYS AS IDENTITY PRIMARY KEY,
                           max_gewichtung INT,
                           bezeichnung VARCHAR(256)
);

CREATE TABLE bewertung (
                           bewertung_id INT GENERATED ALWAYS AS IDENTITY PRIMARY KEY,
                           gewichtung INT,
                           kriterium_id INT,
                           immobilie_id INT,
                           CONSTRAINT fk_kriterium_id FOREIGN KEY (kriterium_id) REFERENCES kriterium(kriterium_id),
                           CONSTRAINT fk_immobilie_id FOREIGN KEY (immobilie_id) REFERENCES immobilie(immobilie_id)
);

CREATE TABLE fachbereich (
                             fachbereich_id INT GENERATED ALWAYS AS IDENTITY PRIMARY KEY,
                             fachbereich_nr INT,
                             bezeichnung VARCHAR(256)
);

CREATE TABLE mitarbeiter (
                             mitarbeiter_id INT GENERATED ALWAYS AS IDENTITY PRIMARY KEY,
                             vorname VARCHAR(256),
                             nachname VARCHAR(256),
                             mail VARCHAR(256),
                             fachbereich_id INT,
                             CONSTRAINT fk_fachbereich_id FOREIGN KEY (fachbereich_id) REFERENCES fachbereich(fachbereich_id)
);
