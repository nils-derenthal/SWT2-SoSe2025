CREATE TABLE adresse (
                         adresse_id BIGINT GENERATED ALWAYS AS IDENTITY PRIMARY KEY,
                         plz INT,
                         strasse VARCHAR(64),
                         ort VARCHAR(64),
                         hausnummer INT,
                         hausnummer_zusatz VARCHAR(64),
                         stadtbezirk VARCHAR(64)
);

CREATE TABLE koordinaten (
                             koordinaten_id BIGINT GENERATED ALWAYS AS IDENTITY PRIMARY KEY,
                             x DOUBLE PRECISION,
                             y DOUBLE PRECISION
);

CREATE TABLE eigentuemer (
                             eigentuemer_id BIGINT GENERATED ALWAYS AS IDENTITY PRIMARY KEY,
                             vorname VARCHAR(64),
                             nachname VARCHAR(64),
                             adresse_id BIGINT,
                             CONSTRAINT fk_eigentuemer_adresse FOREIGN KEY (adresse_id) REFERENCES adresse(adresse_id)
);

CREATE TABLE immobilie (
                           immobilie_id BIGINT GENERATED ALWAYS AS IDENTITY PRIMARY KEY,
                           adresse_id BIGINT,
                           bezeichnung VARCHAR(256),
                           archiviert BOOLEAN,
                           zustand VARCHAR(32),
                           koordinaten_id BIGINT,
                           gemarkung VARCHAR(32),
                           flur VARCHAR(32),
                           flurstueck VARCHAR(32),
                           quadratmeter INT,
                           gebaeude_typ VARCHAR(32),
                           eigentums_form VARCHAR(32),
                           eigentuemer_id BIGINT,
                           CONSTRAINT fk_immobilie_adresse FOREIGN KEY (adresse_id) REFERENCES adresse(adresse_id),
                           CONSTRAINT fk_immobilie_koordinaten FOREIGN KEY (koordinaten_id) REFERENCES koordinaten(koordinaten_id),
                           CONSTRAINT fk_immobilie_eigentuemer FOREIGN KEY (eigentuemer_id) REFERENCES eigentuemer(eigentuemer_id)
);

-- Beispiel-Daten einfügen

INSERT INTO adresse (plz, strasse, ort, hausnummer, hausnummer_zusatz, stadtbezirk) VALUES
                                                                                       (10115, 'Invalidenstraße', 'Berlin', 116, '', 'Mitte'),
                                                                                       (80331, 'Marienplatz', 'München', 1, 'A', 'Altstadt-Lehel'),
                                                                                       (50667, 'Hohe Straße', 'Köln', 5, '', 'Innenstadt');

INSERT INTO koordinaten (x, y) VALUES
                                   (13.3888599, 52.5170365),  -- Berlin
                                   (11.57549, 48.13743),      -- München
                                   (7.24045, 51.53919);       -- Herne

INSERT INTO eigentuemer (adresse_id, vorname, nachname) VALUES
                                                            (1, 'Max', 'Mustermann'),
                                                            (2, 'Erika', 'Musterfrau'),
                                                            (3, 'Hans', 'Beispiel');

INSERT INTO immobilie (adresse_id, bezeichnung, archiviert, zustand, koordinaten_id, gemarkung, flur, flurstueck, quadratmeter, gebaeude_typ, eigentums_form, eigentuemer_id) VALUES
                                                                                                                                                                                  (1, 'Wohnhaus Invalidenstraße', false, 'gut', 1, 'Berlin Mitte', '12', '345', 120, 'HOCHBUNKER', 'VOLLEIGENTUM', 1),
                                                                                                                                                                                  (2, 'Bürogebäude Marienplatz', false, 'renovierungsbedürftig', 2, 'München Zentrum', '8', '1234', 300, 'GEWERBE', 'TEILEIGENTUM', 2),
                                                                                                                                                                                  (3, 'Ladenlokal Hohe Straße', true, 'abgerissen', 3, 'Köln Innenstadt', '3', '876', 85, 'WOHNHAUS', 'VOLLEIGENTUM', 3);
