/*package com.projeto.imoveis.services;

import com.projeto.imoveis.models.Pessoa;
import com.projeto.imoveis.repositories.PessoaRepository;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class CustomUserDetailsService implements UserDetailsService {

    final
    PessoaRepository pessoaRepository;

    public CustomUserDetailsService(PessoaRepository pessoaRepository) {
        this.pessoaRepository = pessoaRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {

        /*Pessoa pessoaModel = pessoaRepository.findByEmail(email)
                .orElseThrow(() -> new UsernameNotFoundException("Email não localizado: " + email));*/

/*        Pessoa pessoaModel = pessoaRepository.findByEmail(email);

        if (pessoaModel == null){
            new UsernameNotFoundException("Email não localizado: " + email);
        }

        return new User(pessoaModel.getUsername(), pessoaModel.getPassword(), true, true,
                true, true, pessoaModel.getAuthorities());
    }
}*/
