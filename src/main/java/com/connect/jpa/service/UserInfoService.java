package com.connect.jpa.service;

import java.util.Optional;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.connect.jpa.model.UserInfoModel;
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
		Optional<UserInfoModel> userDetail = repository.findByName(username);
		// Converting userDetail to UserDetails
		return userDetail.map(UserInfoDetailsService::new)
				.orElseThrow(() -> new UsernameNotFoundException("User not found " + username));
	}

	// Método para adicionar um novo usuário com senha criptografada
	@Transactional // A anotação @Transactional garante que a operação seja executada dentro de uma transação.
	public String addUser(UserInfoModel userInfo) {
		// Verifica se o usuário já existe no banco de dados com o nome fornecido
		if (repository.findByName(userInfo.getName()).isPresent()) {
			// Se o usuário já existir, lança uma exceção para informar que o nome de
			// usuário já está em uso
			throw new IllegalArgumentException("Username already exists");
		}

		// Criptografa a senha do usuário antes de salvar no banco de dados
		userInfo.setPassword(encoder.encode(userInfo.getPassword())); // Utiliza o encoder configurado para criptografar a senha
		// Salva o usuário no banco de dados
		repository.save(userInfo); // Salva o objeto userInfo na base de dados através do repositório

		// Retorna uma mensagem de sucesso caso o usuário tenha sido adicionado com
		// sucesso
		return "User added successfully"; // Retorna uma mensagem indicando que o usuário foi adicionado com sucesso
	}
}