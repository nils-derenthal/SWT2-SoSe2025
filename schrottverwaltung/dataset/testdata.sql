--Die eigentlichen Testdaten sollten über den MQTT-Broker von dem Schrottdaten-Server kommen. Die Daten hier sind nur für schnelle lokale Tests.

INSERT INTO adresse (adresse_id, strasse, hausnummer, hausnummer_zusatz, plz, ort, stadtbezirk)
VALUES (1,'x', 1, 'a', 22222, 'x', 'x');

INSERT INTO koordinaten (koordinaten_id, x_koordinate, y_koordinate) VALUES (1,1, 1);

INSERT INTO eigentuemer (eigentuemer_id, vorname, nachname, adresse_id, anmerkung) VALUES (1,'x', 'x', 1, 'x');

INSERT INTO immobilien_status (immobilien_status_id, status, beschreibung) VALUES (1, 'VERDACHT', 'x');

INSERT INTO immobilie (immobilie_id,
                       bezeichnung,
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
        1,
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
