package med.voll.api.controller.medico;
import jakarta.persistence.*;
import jakarta.persistence.Enumerated;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import med.voll.api.controller.endereco.Endereco;

/* JPA -  Java Persistence API */

@Table(name = "medicos")
@Entity(name = "Medico")
/* alguns imports que evitam a mais escrita no código */
@Getter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")

public class Medico {

    @Id 
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nome;
    private String email;
    private String crm;

    @Enumerated(EnumType.STRING)
    private Especialidade especialidade;
    
    @Embedded
    private Endereco endereco;

    public Medico(DadosCadastroMedico dados){
        this.nome = dados.nome();
        this.email = dados.email();
        this.crm = dados.crm();
        this.especialidade = dados.especialidade();
        this.endereco = new Endereco(dados.endereco());
    }
}