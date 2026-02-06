package projeto_base_de_telas_e_login.tudo.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import projeto_base_de_telas_e_login.adapter.out.persistence.User.UserRepository;
import projeto_base_de_telas_e_login.domain.repository.UserRepositoryPorta.UserPorta;

@Service
public class AuthorizationService implements UserDetailsService {

    private final UserPorta userPorta;

    public AuthorizationService(UserPorta userPorta) {
        this.userPorta = userPorta;
    }

    @Override
    public UserDetails loadUserByUsername(String username)
            throws UsernameNotFoundException {

        return userPorta.findByLogin(username)
                .orElseThrow(() ->
                        new UsernameNotFoundException("Usuário não encontrado")
                );
    }
}
