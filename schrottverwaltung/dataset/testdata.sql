--Die eigentlichen Testdaten sollten über den MQTT-Broker von dem Schrottdaten-Server kommen. Die Daten hier sind nur für schnelle lokale Tests.

INSERT INTO adresse (strasse, hausnummer, hausnummer_zusatz, plz, ort, stadtbezirk)
VALUES ('x', 1, 'a', 22222, 'x', 'x');

INSERT INTO koordinaten (x_koordinate, y_koordinate) VALUES (1, 1);

INSERT INTO eigentuemer (vorname, nachname, adresse_id, anmerkung) VALUES ('x', 'x', 1, 'x');

INSERT INTO immobilie (bezeichnung,
                       adresse_id,
                       archiviert,
                       zustand,
                       koordinaten_id,
                       gemarkung,
                       flur,
                       flurstueck,
                       quadratmeter,
                       gebaeudetyp,
                       eigentumsform,
                       eigentuemer_id,
                       aktueller_status_id)
VALUES (
        'test',
        1,
        false,
        'x',
        1,
        'x',
        'x',
        'x',
        42,
        'HOCHBUNKER',
        'VOLLEIGENTUM',
        1,
        1
       );

INSERT INTO immobilien_status (status, beschreibung, immobilie_id) VALUES ('VERDACHT', 'x', 1);
