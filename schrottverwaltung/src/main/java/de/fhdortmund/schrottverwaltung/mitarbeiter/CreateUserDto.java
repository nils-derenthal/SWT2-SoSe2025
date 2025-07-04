package de.fhdortmund.schrottverwaltung.mitarbeiter;

public record CreateUserDto(String firstname, String lastname, String email, String password) {
}
