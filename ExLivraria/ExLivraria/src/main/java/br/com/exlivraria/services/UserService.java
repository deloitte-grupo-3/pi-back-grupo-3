package br.com.exlivraria.services;

import br.com.exlivraria.converter.DozerConverter;
import br.com.exlivraria.data.model.Book;
import br.com.exlivraria.data.model.Permission;
import br.com.exlivraria.data.model.User;
import br.com.exlivraria.data.vo.v1.BookVO;
import br.com.exlivraria.security.AccountCredentialsVO;
import br.com.exlivraria.security.jwt.JwtTokenProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import br.com.exlivraria.repository.UserRepository;

import java.util.ArrayList;
import java.util.List;

@Service 
public class UserService implements UserDetailsService {
	
	@Autowired
	UserRepository repository;

	@Autowired
	JwtTokenProvider tokenProvider;

	//Constructor
	public UserService(UserRepository repository) {
		this.repository = repository;
	}

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		var user = repository.findByUsername(username);
				if(user != null) {
					return user;
				}
				else {
					throw new UsernameNotFoundException("Username " + username + " not found");
				}
	}

	public String create(AccountCredentialsVO acVO) {

		List<Permission> perm = new ArrayList<Permission>();
		Permission p = new Permission();
		p.setDescription("COMMON_USER");
		perm.add(p);


		User user = new User();
		user.setPassword(acVO.getPassword());
		user.setUserName(acVO.getUsername());
		user.setAccountNonExpired(true);
		user.setAccountNonLocked(true);
		user.setCredentialNonExpired(true);
		user.setEnabled(true);
		user.setPermissions(perm);
		user.getRoles();

		var token = tokenProvider.createToken(user.getUsername(), user.getRoles());
		//var entity = DozerConverter.parseObject(acVO, User.class);//recebe o param VO e transforma em entity
		repository.save(user);
		//var vo = DozerConverter.parseObject(repository.save(user), AccountCredentialsVO.class); //salva a entity no repository e converte em VO novamente
		return token; //retorna o VO para o client
	}

	


	
	
}
