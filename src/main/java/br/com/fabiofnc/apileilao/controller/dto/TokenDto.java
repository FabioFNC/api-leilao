package br.com.fabiofnc.apileilao.controller.dto;

public class TokenDto {

    private String token;
    private String tipoDeAutenticacao;

    public TokenDto(String token, String tipoDeAutenticacao) {
        this.token = token;
        this.tipoDeAutenticacao = tipoDeAutenticacao;
    }

    public String getToken() {
        return token;
    }

    public String getTipoDeAutenticacao() {
        return tipoDeAutenticacao;
    }
<<<<<<< Updated upstream

=======
>>>>>>> Stashed changes
}
