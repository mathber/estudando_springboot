package med.voll.api.domain.usuario;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class AutenticacaoService implements UserDetailsService{

    @Autowired
    private UsuarioRepository repository;

    // método que o Spring Boot chama sempre que um usuário faz um login
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        // findByLogin não precisa ser implementado, visto que o Spring Boot utiliza um padrão próprio de nomenclatura de métodos, possuindo palavras reservadas como findBy e existsBy.
        return repository.findByLogin(username);
    }
    
}
