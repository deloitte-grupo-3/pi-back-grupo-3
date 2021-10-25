package br.com.exlivraria.services;

import br.com.exlivraria.Exception.ResourceNotFoundException;
import br.com.exlivraria.converter.DozerConverter;
import br.com.exlivraria.data.model.Book;
import br.com.exlivraria.data.model.Client;
import br.com.exlivraria.data.model.Permission;
import br.com.exlivraria.data.model.User;
import br.com.exlivraria.data.vo.v1.BookVO;
import br.com.exlivraria.data.vo.v1.ClientVO;
import br.com.exlivraria.repository.BookRepository;
import br.com.exlivraria.repository.ClientRepository;
import br.com.exlivraria.security.AccountCredentialsVO;
import br.com.exlivraria.security.jwt.JwtTokenProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ClientService {

    @Autowired
    ClientRepository repository;

    @Autowired
    UserService serviceUser;

    @Autowired
    JwtTokenProvider tokenProvider;

    public String create(ClientVO client) {

        //Criando Permissoes do usuário
        List<Permission> perm = new ArrayList<Permission>();
        Permission p = new Permission();
        p.setDescription("COMMON_USER");
        perm.add(p);



        AccountCredentialsVO acVO = new AccountCredentialsVO(); //cria um VO
        User user = client.getUser();   //Cria um USER e popula com o recebido por param
        acVO.setUsername(user.getUsername()); //popula o VO com username
        acVO.setPassword(user.getPassword()); //popula o VO com pswd
        //String token = serviceUser.create(acVO); //recebe o token de acesso e cria o User no BD
        user.setPermissions(perm);
        user.getRoles();
        var token = tokenProvider.createToken(user.getUsername(), user.getRoles());

        client.setUser(user);//devolve o usuario para o client com as permissoes

        var entity = DozerConverter.parseObject(client, Client.class);//recebe o param VO e transforma em entity

        repository.save(entity);
        //DozerConverter.parseObject(repository.save(entity), ClientVO.class); //salva a entity no repository e converte em VO novamente
        return token; //retorna o token para o client
    }

    public List<ClientVO> findAll() {
        return DozerConverter.parseListObjects(repository.findAll(), ClientVO.class); //busca a lista de entities e retorna convertendo em VO
    }

    public ClientVO findById(Long id) {
        var entity = repository.findById(id).orElseThrow(() -> new ResourceNotFoundException("No records found for this ID")); //busca no banco a entity
        return DozerConverter.parseObject(repository.save(entity), ClientVO.class); //converte a entity em VO
    }

    public ClientVO update(ClientVO p) {

        var entity = repository.findById(p.getKey()).orElseThrow(() -> new ResourceNotFoundException("No records found for this ID")); //busca entity

        entity.setName(p.getName()); //ajusta a entity
        entity.setCpf(p.getCpf());
        entity.setAddress(p.getAddress());
        entity.setPhone(p.getPhone());

        var vo = DozerConverter.parseObject(repository.save(entity), ClientVO.class); //repos.save salva o obj e o dozer converte em VO novamente para retornar pro client
        return vo;
    }

    public void delete(Long id) {
        Client entity = repository.findById(id).orElseThrow(() -> new ResourceNotFoundException("No records found for this ID")); //busca a entity no banco
        repository.delete(entity); //deleta
    }
}