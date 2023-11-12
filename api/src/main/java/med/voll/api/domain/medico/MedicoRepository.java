package med.voll.api.domain.medico;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

/* é uma interface/classe que herda de uma outra interface já existente pelo Spring, a qual facilita os processos de persistência */

/* JpaRepository <tipo da entidade, tipo do atributo da chave primária da entidade> */
public interface MedicoRepository extends JpaRepository<Medico, Long> {

    Page<Medico> findAllByAtivoTrue(Pageable paginacao);
    
}
