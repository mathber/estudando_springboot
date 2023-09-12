package med.voll.api.controller;

import med.voll.api.controller.medico.Medico;
import med.voll.api.controller.medico.DadosAtualizacaoMedico;
import med.voll.api.controller.medico.DadosCadastroMedico;
import med.voll.api.controller.medico.DadosListagemMedico;
import med.voll.api.controller.medico.MedicoRepository;

import org.springframework.web.bind.annotation.RestController;


import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestBody;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;

import jakarta.transaction.Transactional;

import jakarta.validation.Valid;

/* o Controller é uma classe em que mapeamos as requições que chegam na nossa API */
@RestController
@RequestMapping("medicos")
public class MedicoController {
    
    /* mecanismo de injeção de dependências */
    /* o Spring, então, reconhece que ele será o responsável por instaciar o atributo */
    @Autowired
    private MedicoRepository repository;

    /* mapeia que a requisição é do tipo POST */
    @PostMapping
    @Transactional
    public void cadastrar(@RequestBody @Valid DadosCadastroMedico dados){
        
        /* o método save() é responsável por inserir dentro do banco de dados o objeto */
        /* converção do DTO (dados) para um objeto medico, dentro do parênteses */
        repository.save(new Medico(dados));
    }

    // criamos um DTO que devolverá dados da API
    @GetMapping
    public Page<DadosListagemMedico> listar(Pageable paginacao){
        // convertendo uma lista de Medicos em uma lista de DadosListagemMedico
        return repository.findAllByAtivoTrue(paginacao).map(DadosListagemMedico::new);
    }

    @PutMapping
    @Transactional
    public void atualizar(@RequestBody @Valid DadosAtualizacaoMedico dados){
        var medico = repository.getReferenceById(dados.id());
        medico.atualizarInformacoes(dados);
    }

    @DeleteMapping("/{id}")
    @Transactional
    public void excluir(@PathVariable Long id){
        
        var medico = repository.getReferenceById(id);
        medico.excluir();
        
        
        // exclusão total do banco de dados
        //repository.deleteById(id);
    }

}
