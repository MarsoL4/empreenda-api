package br.com.fiap.empreenda_api.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import br.com.fiap.empreenda_api.repository.UserRepository;



@Service
public class AuthService implements UserDetailsService { //"Ensina" ao Spring como autenticar um usuário

    @Autowired
    private UserRepository repository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return (UserDetails) repository.findByEmail(username).orElseThrow(
            () -> new UsernameNotFoundException("Usuário não encontrado")
        );
    }

}
