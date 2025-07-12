package de.fhdortmund.schrottverwaltung.immobilie.service;

import de.fhdortmund.schrottverwaltung.immobilie.editLock.LockEntry;
import org.springframework.stereotype.Service;

import java.util.Map;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.locks.Lock;

@Service
public class LockService {

    private final Map<Long, LockEntry> locks = new ConcurrentHashMap<>();

    public synchronized boolean tryLock(Long immobilienId, String username){
        LockEntry existing = locks.get(immobilienId);
        if(existing == null || existing.isExpired()){
            locks.put(immobilienId, new LockEntry(username));
            return true;
        }
        return existing.getUsername().equals(username);
    }

    public synchronized void unlock(Long immobilienId, String username){
        LockEntry existing = locks.get(immobilienId);
        if(existing != null && existing.getUsername().equals(username)){
            locks.remove(immobilienId);
        }
    }

    public synchronized String getLockHolder(Long immobilienId){
        LockEntry lock = locks.get(immobilienId);
        if(lock != null && !lock.isExpired()){
            return lock.getUsername();
        }
        locks.remove(immobilienId);
        return "";
    }
}
