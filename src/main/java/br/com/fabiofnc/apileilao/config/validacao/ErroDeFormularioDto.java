package br.com.fabiofnc.apileilao.config.validacao;

public class ErroDeFormularioDto {

    private String erro;
    private String campo;

    public ErroDeFormularioDto(String erro, String campo) {
        this.erro = erro;
        this.campo = campo;
    }

    public String getErro() {
        return erro;
    }

    public String getCampo() {
        return campo;
    }

}
