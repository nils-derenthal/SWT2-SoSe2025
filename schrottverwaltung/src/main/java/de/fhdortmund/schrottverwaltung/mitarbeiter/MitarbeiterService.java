package de.fhdortmund.schrottverwaltung.mitarbeiter;

import de.fhdortmund.schrottverwaltung.mitarbeiter.repo.MitarbeiterRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class MitarbeiterService implements UserDetailsService {
    private final MitarbeiterRepo repo;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Mitarbeiter user = repo.findByMail(username);
        return org.springframework.security.core.userdetails.User
                .builder()
                .username(user.getMail())
                .password(user.getPassword())
                .roles("USER")
                .build();
    }
}
