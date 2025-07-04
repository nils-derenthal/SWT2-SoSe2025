CREATE TABLE adresse(
                        adresse_id INT GENERATED ALWAYS AS IDENTITY,
                        plz INT,
                        strasse VARCHAR(64),
                        ort VARCHAR(64),
                        hausnummer INT,
                        hausnummerzusatz VARCHAR(64),
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
                            adress_id INT,
                            name VARCHAR(64),
                            nachname VARCHAR(64),
                            anmerkung VARCHAR(256),
                            PRIMARY KEY(eigentuemer_id),
                            CONSTRAINT fk_eigentuemer_adresse
                                FOREIGN KEY(adress_id)
                                    REFERENCES adresse(adresse_id)
);

CREATE TABLE immobilien (
                            immobilien_id INT GENERATED ALWAYS AS IDENTITY,
                            adress_id INT,
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
                            bild TEXT,
                            PRIMARY KEY(immobilien_id),
                            CONSTRAINT fk_immobilien_adresse
                                FOREIGN KEY (adress_id)
                                    REFERENCES adresse(adresse_id),
                            CONSTRAINT fk_immobilien_koordinaten
                                FOREIGN KEY (koordinaten_id)
                                    REFERENCES koordinaten(koordinaten_id)
);

INSERT INTO adresse (plz, strasse, ort, hausnummer, hausnummerzusatz, stadtbezirk)
VALUES
    (10115, 'Invalidenstraße', 'Berlin', 116, '', 'Mitte'),
    (80331, 'Marienplatz', 'München', 1, 'A', 'Altstadt-Lehel'),
    (50667, 'Hohe Straße', 'Köln', 5, '', 'Innenstadt');

INSERT INTO koordinaten (x, y)
VALUES
    (13.3888599, 52.5170365),  -- Berlin
    (11.57549, 48.13743),      -- München
    (7.24045, 51.53919);         -- Herne

INSERT INTO eigentuemer (adress_id, name, nachname, anmerkung)
VALUES
    (1, 'Max', 'Mustermann', 'Hauptwohnsitz'),
    (2, 'Erika', 'Musterfrau', 'Besitzerin seit 2010'),
    (3, 'Hans', 'Beispiel', '');

INSERT INTO immobilien (
    adress_id, bezeichnung, archiviert, zustand, koordinaten_id,
    gemarkung, flur, flurstueck, quadratmeter,
    gebaeude_typ, eigentums_form
)
VALUES
    (1, 'Wohnhaus Invalidenstraße', false, 'gut', 1, 'Berlin Mitte', '12', '345', 120, 'HOCHBUNKER', 'VOLLEIGENTUM'),
    (2, 'Bürogebäude Marienplatz', false, 'renovierungsbedürftig', 2, 'München Zentrum', '8', '1234', 300, 'GEWERBE', 'TEILEIGENTUM'),
    (3, 'Ladenlokal Hohe Straße', true, 'abgerissen', 3, 'Köln Innenstadt', '3', '876', 85, 'WOHNHAUS', 'VOLLEIGENTUM');
