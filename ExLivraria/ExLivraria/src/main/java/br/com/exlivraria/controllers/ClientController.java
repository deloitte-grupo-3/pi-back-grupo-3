package br.com.exlivraria.controllers;

import br.com.exlivraria.data.model.Client;
import br.com.exlivraria.data.vo.v1.ClientVO;
import br.com.exlivraria.repository.ClientRepository;
import br.com.exlivraria.services.ClientService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.methodOn;

@Api(value = "Client Endpoint", tags = {"ClientEndpoint"})
@RestController
@RequestMapping("/clients")
@CrossOrigin("*")
public class ClientController {
    @Autowired
    private ClientRepository repository;

    @Autowired
    private ClientService services;

    @ApiOperation(value = "Find all the clients recorded")
    @GetMapping(produces = {"application/json", "application/xml" ,"application/x-yaml" })
    public List<ClientVO> findAll(){

        List<ClientVO> clients = services.findAll();

        clients
                .stream() //vai pegar a lista vai percorrer um a um com o foreach de acordo com o que especificarmos abaixo:
                .forEach(p -> p.add(
                                linkTo(methodOn(ClientController.class).findById(p.getKey())).withSelfRel()
                        )
                ); //no caso irá adicionar a todos os itens da lista um link apontando pra ele mesmo.
        return clients;
    }

    @ApiOperation(value = "Find specific client recorded")
    @GetMapping(value = "/{id}",produces = {"application/json", "application/xml" ,"application/x-yaml"})
    public ClientVO findById(@PathVariable("id")Long id){

        ClientVO clientVO = services.findById(id); //busca o Person no banco e armazena em personVO.

        clientVO.add(linkTo(methodOn(ClientController.class) //irá adicionar os links em ClientController (neste caso ele linka com ele mesmo)
                .findById(id)) //o método que será linkado é o find by id
                .withSelfRel()); //serve para dizer que relaciona a si mesmo.
        return clientVO;
    }

    @GetMapping("/cpf/{cpf}")
    public ResponseEntity<Client> GetByCpf(@PathVariable String cpf) {
        return ResponseEntity.ok(repository.findByCpf(cpf));
    }

    @GetMapping("/name/{name}")
    public ResponseEntity<List<Client>> GetByName(@PathVariable String name) {
        return ResponseEntity.ok(repository.findAllByNameContainingIgnoreCase(name));
    }

    @GetMapping("/address/{address}")
    public ResponseEntity<List<Client>> GetByAddress(@PathVariable String address) {
        return  ResponseEntity.ok(repository.findAllByAddressContainingIgnoreCase(address));
    }

    @ApiOperation(value = "Creates client on the database")
    @PostMapping(produces = {"application/json", "application/xml","application/x-yaml" },
            consumes = {"application/json", "application/xml","application/x-yaml" })
    public String create(@RequestBody ClientVO client){
        var u =client.getUser();

        u.getPassword();
        u.setEnabled(true);
        u.setCredentialNonExpired(true);
        u.setAccountNonExpired(true);
        u.setAccountNonLocked(true);
        u.setAccountNonExpired(true);

        BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder(16); //encrypta a senha
        u.setPassword(bCryptPasswordEncoder.encode(u.getPassword())); // encoda a password e devolve pro proprio user.

        client.setUser(u);
        String token = services.create(client);
        return token;
    }

    @ApiOperation(value = "Update specific Client data")
    @PutMapping(produces = {"application/json", "application/xml" ,"application/x-yaml"},
            consumes = {"application/json", "application/xml","application/x-yaml" })
    public ClientVO update(@RequestBody ClientVO client){

        ClientVO  clientVO = services.update(client);

        clientVO.add(linkTo(methodOn(ClientController.class) //irá adicionar os links em ClientController(neste caso ele linka com ele mesmo)
                .findById(clientVO.getKey())) //o método que será linkado é o find by id
                .withSelfRel()); //serve para dizer que relaciona a si mesmo.
        return clientVO;
    }

    @ApiOperation(value = "Delete Client recorded")
    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable("id")Long id){
        services.delete(id);
        return ResponseEntity.ok().build();  //Adicionamos um retorno para o delete tb
    }
}
