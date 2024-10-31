package com.example.lavanderia_spring.servicios;
import com.example.lavanderia_spring.dto.UsuarioDTO;

import com.example.lavanderia_spring.mappers.UsuarioMapper;
import com.example.lavanderia_spring.modelos.Usuario;
import com.example.lavanderia_spring.repositorios.IUsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UsuarioService implements UserDetailsService {

    @Autowired
    private IUsuarioRepository usuarioRepository;


    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    private UsuarioMapper usuarioMapper;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return usuarioRepository.findTopByUsername(username)
                .orElseThrow(()-> new UsernameNotFoundException("Usuario no encontrado"));
    }


    public Usuario buscarPorUsername(String username){
        return usuarioRepository.findTopByUsername(username).orElse(null);
    }

    public Usuario save(UsuarioDTO dto){
        dto.setPassword(passwordEncoder.encode(dto.getPassword()));
        return  usuarioRepository.save(usuarioMapper.toEntity(dto));
    }

    public boolean validarPassword(Usuario usuario, String passwordSinEncriptar){
        return passwordEncoder.matches(passwordSinEncriptar, usuario.getPassword());
    }
}
