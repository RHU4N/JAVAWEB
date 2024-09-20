package br.edu.fateccotia.isw029.tasklist.service;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.time.Instant;
import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.edu.fateccotia.isw029.tasklist.model.Token;
import br.edu.fateccotia.isw029.tasklist.model.User;
import br.edu.fateccotia.isw029.tasklist.repository.TokenRepository;
import br.edu.fateccotia.isw029.tasklist.repository.UserRepository;

@Service
public class AuthService {
	private Integer TOKEN_TTL = 300;//In seconds
	
	@Autowired //Ele faz uma injeção de dependencias na classe vazia
	private UserRepository userRepository;
	@Autowired
	private TokenRepository tokenRepository;
	
	public void singup(String email, String password) throws Exception {
		User user = new User();	
		user.setPassword(generateHash(password));
		user.setEmail(email);
	
		Optional<User> userExisting = userRepository.findByEmail(email);
		
		if (userExisting.isPresent()) {
			throw new Exception("E-mail alredy exists");
		}else {
			userRepository.save(user);
		}	
	}
 
	private String generateHash(String password) {
		try {
			MessageDigest md = MessageDigest.getInstance("MD5");
			md.update(password.getBytes());
			byte[]  digest = md.digest();
			return toHex(digest);
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		}
		return null;
	}
 
	private String toHex(byte[] digest) {
		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < digest.length; i++) {
			sb.append(Integer.toString(digest[i]& 0xff + 0x100, 16).substring(1));
		}
		
		return sb.toString();
	}
	
	public Token login(String email, String password) {
		
		User user = new User();
		user.setEmail(email);
		user.setPassword(generateHash(password));
		
		Optional<User> users = userRepository.findByEmail(email);
		
		if (users.isPresent() && users.get().getPassword().equals(user.getPassword())) {
			Token token = new Token();
			token.setUser(users.get());
			token.setToken(UUID.randomUUID().toString());
			token.setExpirationTime(Instant.now().plusSeconds(TOKEN_TTL).toEpochMilli());
			tokenRepository.save(token);
			return token;
		}

		return null;
	}
	


	public void signout(String token) {
		Optional<Token> found = tokenRepository.findByToken(token);
		found.ifPresent(t ->{
			t.setExpirationTime(Instant.now().toEpochMilli());
			tokenRepository.save(t);
		});
		
		
	}

	public Boolean validate(String token) {
		Optional<Token> found = tokenRepository.findByToken(token);
		return found.isPresent()&&found.get().getExpirationTime()>Instant.now().toEpochMilli();

	}
	
	public User toUser(String token) {
		Optional<Token> found = tokenRepository.findByToken(token);

			return found.isPresent() ? found.get().getUser() : null;
	}
}
