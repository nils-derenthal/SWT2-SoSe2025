package de.fhdortmund.schrottverwaltung.security;

public record CreateUserDto(String firstname, String lastname, String email, String password) {
}
