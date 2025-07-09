CREATE TABLE adresse(
                        adresse_id INT GENERATED ALWAYS AS IDENTITY,
                        plz INT,
                        strasse VARCHAR(64),
                        ort VARCHAR(64),
                        hausnummer INT,
                        hausnummer_zusatz VARCHAR(64),
                        stadtbezirk VARCHAR(64),
                        PRIMARY KEY (adresse_id)
);

CREATE TABLE koordinaten(
                            koordinaten_id INT GENERATED ALWAYS AS IDENTITY,
                            x DOUBLE PRECISION,
                            y DOUBLE PRECISION,
                            PRIMARY KEY(koordinaten_id)
);

CREATE TABLE eigentuemer(
                            eigentuemer_id INT GENERATED ALWAYS AS IDENTITY,
                            adresse_id INT,
                            vorname VARCHAR(64),
                            nachname VARCHAR(64),
                            anmerkung VARCHAR(256),
                            PRIMARY KEY(eigentuemer_id),
                            CONSTRAINT fk_eigentuemer_adresse
                                FOREIGN KEY(adresse_id)
                                    REFERENCES adresse(adresse_id)
);

CREATE TABLE immobilie (
                            immobilie_id INT GENERATED ALWAYS AS IDENTITY,
                            adresse_id INT,
                            bezeichnung VARCHAR(256),
                            archiviert BOOLEAN,
                            zustand VARCHAR(32),
                            koordinaten_id INT,
                            gemarkung VARCHAR(32),
                            flur VARCHAR(32),
                            flurstueck VARCHAR(32),
                            quadratmeter INT,
                            gebaeude_typ VARCHAR(32),
                            eigentums_form VARCHAR(32),
                            eigentuemer_id BIGINT,
                            bild BYTEA,
                            PRIMARY KEY(immobilie_id),
                            CONSTRAINT fk_immobilie_adresse
                                FOREIGN KEY (adresse_id)
                                    REFERENCES adresse(adresse_id),
                            CONSTRAINT fk_immobilie_koordinaten
                                FOREIGN KEY (koordinaten_id)
                                    REFERENCES koordinaten(koordinaten_id),
                            CONSTRAINT fk_immobilie_eigentuemer
                                FOREIGN KEY (eigentuemer_id)
                                    REFERENCES eigentuemer(eigentuemer_id)
);

INSERT INTO adresse (plz, strasse, ort, hausnummer, hausnummer_zusatz, stadtbezirk)
VALUES
    (44623, 'Friedrich-Ebert-Platz', 'Herne', 2, '', 'Mitte'),
    (44629, 'Karl-Brandt-Weg', 'Herne', 5, 'a', 'Bakau'),
    (44625, 'Hölkeskampring', 'Herne', 168, '', 'Sodingen');

INSERT INTO koordinaten (x, y)
VALUES
    (51.537967666561926, 7.219673306013044),  -- Herne Rathaus
    (51.551414, 7.211307),      -- Schloß Strünkede
    (51.53919, 7.24045);         -- Otto-Hahn-Gymnasium

INSERT INTO eigentuemer (adresse_id, vorname, nachname, anmerkung)
VALUES
    (1, 'Max', 'Mustermann', 'Hauptwohnsitz'),
    (2, 'Erika', 'Musterfrau', 'Besitzerin seit 2010'),
    (3, 'Hans', 'Beispiel', '');

INSERT INTO immobilie (
    adresse_id, bezeichnung, archiviert, zustand, koordinaten_id,
    gemarkung, flur, flurstueck, quadratmeter,
    gebaeude_typ, eigentums_form,eigentuemer_id, bild
)
VALUES
    (1, 'Wohnhaus Invalidenstraße', false, 'gut', 1, 'Berlin Mitte', '12', '345', 120, 'HOCHBUNKER', 'VOLLEIGENTUM', 1,pg_read_binary_file('D:\SWT2-SoSe2025\dataset\schrottdaten\haus.png')),
    (2, 'Bürogebäude Marienplatz', false, 'renovierungsbedürftig', 2, 'München Zentrum', '8', '1234', 300, 'GEWERBE', 'TEILEIGENTUM', 2,pg_read_binary_file('./nochn_haus.png')),
    (3, 'Ladenlokal Hohe Straße', true, 'abgerissen', 3, 'Köln Innenstadt', '3', '876', 85, 'WOHNHAUS', 'VOLLEIGENTUM', 3,pg_read_binary_file('./weiteres_haus.png'));
