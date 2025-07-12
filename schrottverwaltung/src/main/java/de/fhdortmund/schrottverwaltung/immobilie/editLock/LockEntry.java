package de.fhdortmund.schrottverwaltung.immobilie.editLock;

import lombok.Data;

import java.time.Duration;
import java.time.Instant;
import java.time.LocalDateTime;

@Data
public class LockEntry {
    private static final Duration LOCK_TIMEOUT = Duration.ofMinutes(10);


    private final String username;
    private final Instant timestamp = Instant.now();

    public boolean isExpired(){
        return Instant.now().isAfter(timestamp.plus(LOCK_TIMEOUT));
    }
}
