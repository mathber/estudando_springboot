package med.voll.api.domain.medico;

import jakarta.validation.constraints.NotNull;
import med.voll.api.domain.endereco.DadosEndereco;

// devolvemos apenas as informações necessárias
public record DadosAtualizacaoMedico(
    
    @NotNull
    Long id, 
    String nome, 
    String telefone, 
    DadosEndereco endereco
    
    ) {

}
