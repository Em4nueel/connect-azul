package com.connect.jpa.service;

import java.util.Optional;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.connect.jpa.model.Usuario;
import com.connect.jpa.repository.UserInfoRepository;

@Service
public class UserInfoService implements UserDetailsService {

	private final UserInfoRepository repository;
	private final PasswordEncoder encoder;

	public UserInfoService(UserInfoRepository repository, PasswordEncoder encoder) {
		this.repository = repository;
		this.encoder = encoder;
	}

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		Optional<Usuario> userDetail = repository.findByNome(username);
		// Converting userDetail to UserDetails
		return userDetail.map(UserInfoDetails::new)
				.orElseThrow(() -> new UsernameNotFoundException("User not found " + username));
	}

	public String addUser(Usuario userInfo) {
		// Verifica se já existe um usuário com o mesmo nome no banco de dados
		if (repository.findByNome(userInfo.getNome()).isPresent()) {
			// Se encontrar, lança uma exceção informando que o nome de usuário já existe
			throw new IllegalArgumentException("User with name " + userInfo.getNome() + " already exists.");
		}
		// Codifica a senha do usuário usando o PasswordEncoder antes de salvar no banco
		userInfo.setSenha(encoder.encode(userInfo.getSenha()));
		// Salva o objeto UserInfo no repositório (persistência no banco de dados)
		repository.save(userInfo);
		// Retorna uma mensagem de sucesso indicando que o usuário foi adicionado
		return "User Added Successfully";
	}

}