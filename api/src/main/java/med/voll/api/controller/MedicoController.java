package med.voll.api.controller;

import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestBody;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;

import jakarta.transaction.Transactional;

import jakarta.validation.Valid;
import med.voll.api.domain.medico.DadosAtualizacaoMedico;
import med.voll.api.domain.medico.DadosCadastroMedico;
import med.voll.api.domain.medico.DadosDetalhamentoMedico;
import med.voll.api.domain.medico.DadosListagemMedico;
import med.voll.api.domain.medico.Medico;
import med.voll.api.domain.medico.MedicoRepository;

/* o Controller é uma classe em que mapeamos as requições que chegam na nossa API */
@RestController
@RequestMapping("medicos")
public class MedicoController {

    /* mecanismo de injeção de dependências */
    /*
     * o Spring, então, reconhece que ele será o responsável por instaciar o
     * atributo
     */
    @Autowired
    private MedicoRepository repository;

    /* mapeia que a requisição é do tipo POST */
    @PostMapping
    @Transactional
    public ResponseEntity<Object> cadastrar(@RequestBody @Valid DadosCadastroMedico dados, UriComponentsBuilder uriBuilder) {

        Medico medico = new Medico(dados);

        /*
         * o método save() é responsável por inserir dentro do banco de dados o objeto
         */
        /* converção do DTO (dados) para um objeto medico, dentro do parênteses */
        repository.save(medico);

        // endereço da API, por exemplo, http:localhost:8080/medico
        // o Spring possui uma classe que encapsula isso automaticamente
        var uri = uriBuilder.path("/medicos/{id}").buildAndExpand(medico.getId()).toUri();

        return ResponseEntity.created(uri).body(new DadosDetalhamentoMedico(medico));
    }

    // criamos um DTO que devolverá dados da API
    @GetMapping
    public ResponseEntity<Page<DadosListagemMedico>> listar(Pageable paginacao) {
        // convertendo uma lista de Medicos em uma lista de DadosListagemMedico
        var page = repository.findAllByAtivoTrue(paginacao).map(DadosListagemMedico::new);

        return ResponseEntity.ok(page);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Object> detalhar(@PathVariable Long id) {
        var medico = repository.getReferenceById(id);
        return ResponseEntity.ok(new DadosDetalhamentoMedico(medico));
    }

    @PutMapping
    @Transactional
    public ResponseEntity<Object> atualizar(@RequestBody @Valid DadosAtualizacaoMedico dados) {
        var medico = repository.getReferenceById(dados.id());
        medico.atualizarInformacoes(dados);

        // não é recomendado retornar entidades JPA dentro da Controller
        return ResponseEntity.ok(new DadosDetalhamentoMedico(medico));
    }

    @DeleteMapping("/{id}")
    @Transactional
    public ResponseEntity<Object> excluir(@PathVariable Long id) {

        var medico = repository.getReferenceById(id);
        medico.excluir();

        // exclusão total do banco de dados
        // repository.deleteById(id);

        return ResponseEntity.noContent().build();
    }

}
