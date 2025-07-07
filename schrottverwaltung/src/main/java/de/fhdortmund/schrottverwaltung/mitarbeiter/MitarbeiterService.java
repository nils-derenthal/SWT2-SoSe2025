package de.fhdortmund.schrottverwaltung.mitarbeiter;

import de.fhdortmund.schrottverwaltung.mitarbeiter.repo.MitarbeiterRepo;
import de.fhdortmund.schrottverwaltung.security.Encoder;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
@Slf4j
public class MitarbeiterService implements UserDetailsService {
    private final MitarbeiterRepo repo;
    private final Encoder encoder;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<Mitarbeiter> maybeUser = repo.findByMail(username);
        if (maybeUser.isEmpty())
            return null;
        Mitarbeiter user = maybeUser.get();
        return org.springframework.security.core.userdetails.User
                .builder()
                .username(user.getMail())
                .password(user.getPasswort())
                .roles("USER")
                .build();
    }

    public void register(CreateUserDto createUser, HttpServletResponse response) {
        if (repo.existsByMail(createUser.email()))
        {
            response.setStatus(HttpServletResponse.SC_CONFLICT);
            return;
        }
        Mitarbeiter mitarbeiter = new Mitarbeiter(createUser.firstname(),
                createUser.lastname(),
                encoder.encrypt(createUser.password()),
                createUser.email(),
                null,
                null);
        repo.save(mitarbeiter);
    }
}
