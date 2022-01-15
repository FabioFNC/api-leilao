package br.com.fabiofnc.apileilao.controller.form;


import java.math.BigDecimal;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ProdutoForm {

    @NotBlank
    private String nome;
    
    @NotNull
    private String descricao;
    
    @NotNull
    private BigDecimal valorInicial;
    private Long idDoAnunciante;
    
}
