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
    gebaeude_typ, eigentums_form,eigentuemer_id
)
VALUES
    (1, 'Wohnhaus Invalidenstraße', false, 'gut', 1, 'Berlin Mitte', '12', '345', 120, 'HOCHBUNKER', 'VOLLEIGENTUM', 1),
    (2, 'Bürogebäude Marienplatz', false, 'renovierungsbedürftig', 2, 'München Zentrum', '8', '1234', 300, 'GEWERBE', 'TEILEIGENTUM', 2),
    (3, 'Ladenlokal Hohe Straße', true, 'abgerissen', 3, 'Köln Innenstadt', '3', '876', 85, 'WOHNHAUS', 'VOLLEIGENTUM', 3);

INSERT INTO adresse (plz, strasse, ort, hausnummer, hausnummer_zusatz, stadtbezirk)
VALUES
    (44623, 'Friedrich-Ebert-Platz', 'Herne', 2, '', 'Mitte'),
    (44629, 'Karl-Brandt-Weg', 'Herne', 5, 'a', 'Bakau'),
    (44625, 'Hölkeskampring', 'Herne', 168, '', 'Sodingen'),
    (44787, 'Kortumstraße', 'Bochum', 45, '', 'Innenstadt'),
    (44801, 'Universitätsstraße', 'Bochum', 150, 'b', 'Querenburg'),
    (44137, 'Kampstraße', 'Dortmund', 10, '', 'Innenstadt-West');

INSERT INTO koordinaten (x, y)
VALUES
    (51.537967666561926, 7.219673306013044),  -- Herne Rathaus
    (51.551414, 7.211307),      -- Schloß Strünkede
    (51.53919, 7.24045),         -- Otto-Hahn-Gymnasium
    (51.481844, 7.216236),       -- Bochum Hbf
    (51.443611, 7.2625),         -- Ruhr-Universität Bochum
    (51.51494, 7.46529);         -- Dortmunder U

INSERT INTO eigentuemer (adresse_id, vorname, nachname, anmerkung)
VALUES
    (1, 'Max', 'Mustermann', 'Hauptwohnsitz'),
    (2, 'Erika', 'Musterfrau', 'Besitzerin seit 2010'),
    (3, 'Hans', 'Beispiel', ''),
    (4, 'Anna', 'Schmidt', 'Vermietet'),
    (5, 'Peter', 'Müller', 'Eigennutzung'),
    (6, 'Clara', 'Weber', 'Erworben 2022');

INSERT INTO immobilie (
    adresse_id, bezeichnung, archiviert, zustand, koordinaten_id,
    gemarkung, flur, flurstueck, quadratmeter,
    gebaeude_typ, eigentums_form, eigentuemer_id
)
VALUES
    (1, 'Wohnhaus Invalidenstraße', false, 'gut', 4, 'Berlin Mitte', '12', '345', 120, 'HOCHBUNKER', 'VOLLEIGENTUM', 1),
    (2, 'Bürogebäude Marienplatz', false, 'renovierungsbedürftig', 5, 'München Zentrum', '8', '1234', 300, 'HOCHBUNKER', 'TEILEIGENTUM', 2),
    (3, 'Ladenlokal Hohe Straße', true, 'abgerissen', 6, 'Köln Innenstadt', '3', '876', 85, 'WOHNHAUS', 'VOLLEIGENTUM', 3),
    (4, 'Wohnkomplex Kortumstraße', false, 'gut', 7, 'Bochum Mitte', '5', '678', 500, 'HOCHBUNKER', 'VOLLEIGENTUM', 4),
    (5, 'Campusgebäude Universitätsstraße', false, 'sehr gut', 8, 'Bochum Querenburg', '9', '1011', 1000, 'HOCHBUNKER', 'VOLLEIGENTUM', 5),
    (6, 'Kulturzentrum Kampstraße', false, 'renoviert', 9, 'Dortmund Innenstadt', '2', '222', 250, 'HOCHBUNKER', 'VOLLEIGENTUM', 6);




-- chat
-- Additional addresses
INSERT INTO adresse (plz, strasse, ort, hausnummer, hausnummer_zusatz, stadtbezirk)
VALUES
    (10115, 'Invalidenstraße', 'Berlin', 50, '', 'Mitte'),
    (20095, 'Mönckebergstraße', 'Hamburg', 1, '', 'Altstadt'),
    (04109, 'Petersstraße', 'Leipzig', 20, '', 'Zentrum'),
    (80331, 'Marienplatz', 'München', 8, '', 'Altstadt-Lehel'),
    (90403, 'Hauptmarkt', 'Nürnberg', 14, '', 'Altstadt'),
    (50667, 'Hohe Straße', 'Köln', 5, '', 'Innenstadt'),
    (28195, 'Am Markt', 'Bremen', 7, '', 'Altstadt'),
    (70173, 'Königstraße', 'Stuttgart', 12, '', 'Mitte'),
    (01067, 'Prager Straße', 'Dresden', 3, '', 'Altstadt'),
    (99084, 'Anger', 'Erfurt', 10, '', 'Altstadt'),
    (39104, 'Breiter Weg', 'Magdeburg', 15, '', 'Altstadt'),
    (14467, 'Brandenburger Straße', 'Potsdam', 22, '', 'Innenstadt'),
    (55116, 'Schillerplatz', 'Mainz', 18, '', 'Altstadt'),
    (66111, 'Bahnhofstraße', 'Saarbrücken', 9, '', 'Mitte'),
    (28195, 'Schlachte', 'Bremen', 25, '', 'Altstadt');

INSERT INTO koordinaten (x, y)
VALUES
    (51.537967666561926, 7.219673306013044),  -- Herne Rathaus
    (51.551414, 7.211307),                    -- Schloss Strünkede
    (51.53919, 7.24045),                      -- Otto-Hahn-Gymnasium
    (51.5405, 7.2228),                        -- Kulturzentrum Herne
    (51.5443, 7.2265),                        -- Gysenbergpark
    (51.5231, 7.2192),                        -- Bahnhof Herne
    (51.5256, 7.2003),                        -- Akademie Mont-Cenis
    (51.5328, 7.2167),                        -- Wanne-Eickel Hauptbahnhof
    (51.5351, 7.2294),                        -- Flottmann-Hallen
    (51.5482, 7.2231),                        -- LWL-Museum für Archäologie
    (51.5507, 7.2338),                        -- Revierpark Gysenberg
    (51.5302, 7.2101),                        -- Stadtgarten Herne
    (51.5385, 7.2156),                        -- St. Marien Hospital Herne
    (51.5364, 7.2287),                        -- Cranger Kirmesplatz
    (51.5439, 7.2205);

-- Additional owners
INSERT INTO eigentuemer (adresse_id, vorname, nachname, anmerkung)
VALUES
    (7, 'Lukas', 'Fischer', 'Hauptwohnsitz'),
    (8, 'Marie', 'Schneider', 'Vermietet'),
    (9, 'Paul', 'Keller', 'Eigennutzung'),
    (10, 'Sophie', 'Bauer', 'Erworben 2020'),
    (11, 'Jonas', 'Hoffmann', 'Besitzer seit 2015'),
    (12, 'Emma', 'Schulz', 'Renoviert 2021'),
    (13, 'Leon', 'Richter', 'Hauptwohnsitz'),
    (14, 'Mia', 'Koch', 'Vermietet'),
    (15, 'Elias', 'Krause', 'Eigennutzung'),
    (16, 'Hannah', 'Beck', 'Erworben 2018'),
    (17, 'Noah', 'Wagner', 'Besitzer seit 2012'),
    (18, 'Lina', 'Schmidt', 'Renoviert 2022'),
    (19, 'Finn', 'Neumann', 'Hauptwohnsitz'),
    (20, 'Lea', 'Schwarz', 'Vermietet'),
    (21, 'Ben', 'Krüger', 'Eigennutzung');

-- Additional properties
INSERT INTO immobilie (
    adresse_id, bezeichnung, archiviert, zustand, koordinaten_id,
    gemarkung, flur, flurstueck, quadratmeter,
    gebaeude_typ, eigentums_form, eigentuemer_id
)
VALUES
    (7, 'Wohnhaus Invalidenstraße', false, 'gut', 10, 'Berlin Mitte', '12', '345', 120, 'WOHNHAUS', 'VOLLEIGENTUM', 7),
    (8, 'Bürogebäude Mönckebergstraße', false, 'renovierungsbedürftig', 11, 'Hamburg Altstadt', '8', '1234', 300, 'GEWERBE', 'TEILEIGENTUM', 8),
    (9, 'Ladenlokal Petersstraße', true, 'abgerissen', 12, 'Leipzig Zentrum', '3', '876', 85, 'WOHNHAUS', 'VOLLEIGENTUM', 9),
    (10, 'Wohnkomplex Marienplatz', false, 'gut', 13, 'München Altstadt', '5', '678', 500, 'WOHNHAUS', 'VOLLEIGENTUM', 10),
    (11, 'Wohnhaus Hauptmarkt', false, 'sehr gut', 14, 'Nürnberg Altstadt', '9', '1011', 1000, 'WOHNHAUS', 'VOLLEIGENTUM', 11),
    (12, 'Kulturzentrum Hohe Straße', false, 'renoviert', 15, 'Köln Innenstadt', '2', '222', 250, 'WOHNHAUS', 'VOLLEIGENTUM', 12),
    (13, 'Wohnhaus Am Markt', false, 'gut', 16, 'Bremen Altstadt', '4', '567', 150, 'WOHNHAUS', 'VOLLEIGENTUM', 13),
    (14, 'Bürogebäude Königstraße', false, 'renovierungsbedürftig', 17, 'Stuttgart Mitte', '6', '890', 400, 'WOHNHAUS', 'TEILEIGENTUM', 14),
    (15, 'Wohnhaus Prager Straße', false, 'gut', 18, 'Dresden Altstadt', '7', '123', 200, 'WOHNHAUS', 'VOLLEIGENTUM', 15),
    (16, 'Wohnhaus Anger', false, 'sehr gut', 19, 'Erfurt Altstadt', '10', '456', 300, 'WOHNHAUS', 'VOLLEIGENTUM', 16),
    (17, 'Wohnhaus Breiter Weg', false, 'renoviert', 20, 'Magdeburg Altstadt', '11', '789', 250, 'WOHNHAUS', 'VOLLEIGENTUM', 17),
    (18, 'Wohnhaus Brandenburger Straße', false, 'gut', 21, 'Potsdam Innenstadt', '13', '101', 180, 'WOHNHAUS', 'VOLLEIGENTUM', 18),
    (19, 'Wohnhaus Schillerplatz', false, 'renovierungsbedürftig', 22, 'Mainz Altstadt', '14', '202', 220, 'WOHNHAUS', 'TEILEIGENTUM', 19),
    (20, 'Wohnhaus Bahnhofstraße', false, 'gut', 23, 'Saarbrücken Mitte', '15', '303', 280, 'WOHNHAUS', 'VOLLEIGENTUM', 20),
    (21, 'Wohnhaus Schlachte', false, 'sehr gut', 24, 'Bremen Altstadt', '16', '404', 320, 'WOHNHAUS', 'VOLLEIGENTUM', 21);
