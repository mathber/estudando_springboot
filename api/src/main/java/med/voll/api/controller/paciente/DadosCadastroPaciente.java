package med.voll.api.controller.paciente;
import med.voll.api.controller.endereco.DadosEndereco;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.Valid;

/* DTO - Data Transfer Object */
public record DadosCadastroPaciente(

    @NotBlank
    String nome, 

    @NotBlank
    @Email
    String email, 

    @NotBlank
    String telefone, 

    @NotBlank
    @Pattern(regexp = "\\d{11}")
    String cpf, 

    @NotNull
    @Valid
    DadosEndereco endereco
    
    ) { 
}
