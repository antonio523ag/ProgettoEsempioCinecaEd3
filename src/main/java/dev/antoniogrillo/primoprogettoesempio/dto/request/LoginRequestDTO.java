package dev.antoniogrillo.primoprogettoesempio.dto.request;

import lombok.Data;

@Data // genera getter, setter, costruttore con solo i paramertri final o vuoto se non ce ne sono, equals e toString
public class LoginRequestDTO {
    private String username;
    private String password;


}
