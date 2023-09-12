package med.voll.api.controller.medico;
import med.voll.api.controller.endereco.DadosEndereco;

// imports do Bean Validation
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Email;
import jakarta.validation.Valid;


/* DTO - Data Transfer Object */
public record DadosCadastroMedico(
    
    // verifica se não é vazio, nem null
    @NotBlank 
    String nome,
    
    @NotBlank
    @Email
    String email,
    
    @NotBlank
    String telefone,
    
    @NotBlank
    // limita o conteúdo do crm a partir de uma expressão regular
    // Expressão regular --> aceita de 4 até 6 ({4,6}) dígitos (\\d).
    @Pattern(regexp = "\\d{4,6}")
    String crm, 
    
    @NotNull
    Especialidade especialidade, 
    
    @NotNull
    // diz para validar dentro do 'endereco' também
    @Valid
    DadosEndereco endereco
    
    ) {
}
