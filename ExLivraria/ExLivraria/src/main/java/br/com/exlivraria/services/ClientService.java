package br.com.exlivraria.services;

import br.com.exlivraria.Exception.ResourceNotFoundException;
import br.com.exlivraria.converter.DozerConverter;
import br.com.exlivraria.data.model.Book;
import br.com.exlivraria.data.model.Client;
import br.com.exlivraria.data.vo.v1.BookVO;
import br.com.exlivraria.data.vo.v1.ClientVO;
import br.com.exlivraria.repository.BookRepository;
import br.com.exlivraria.repository.ClientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ClientService {

    @Autowired
    ClientRepository repository;

    public ClientVO create(ClientVO client) {
        var entity = DozerConverter.parseObject(client, Client.class);//recebe o param VO e transforma em entity
        var vo = DozerConverter.parseObject(repository.save(entity), ClientVO.class); //salva a entity no repository e converte em VO novamente
        return vo; //retorna o VO para o client
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