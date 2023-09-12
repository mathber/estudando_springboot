package med.voll.api.controller.medico;

import jakarta.validation.constraints.NotNull;
import med.voll.api.controller.endereco.DadosEndereco;

// devolvemos apenas as informações necessárias
public record DadosAtualizacaoMedico(
    
    @NotNull
    Long id, 
    String nome, 
    String telefone, 
    DadosEndereco endereco
    
    ) {

}
