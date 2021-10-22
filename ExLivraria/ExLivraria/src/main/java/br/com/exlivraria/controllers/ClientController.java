package br.com.exlivraria.controllers;

import br.com.exlivraria.data.model.Client;
import br.com.exlivraria.repository.ClientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/clients")
@CrossOrigin("*")
public class ClientController {
    @Autowired
    private ClientRepository repository;

    @GetMapping
    public ResponseEntity<List<Client>> GetAll() {
        return ResponseEntity.ok(repository.findAll());
    }

    @GetMapping("/{id}")//get pelo id
    public ResponseEntity<Client> GetById(@PathVariable Long id) {
        return repository.findById(id).
                map(resp -> ResponseEntity.ok(resp)).
                orElse(ResponseEntity.notFound().build());
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

    @PostMapping
    public ResponseEntity<Client> Post(@RequestBody Client client) {
        return ResponseEntity.status(HttpStatus.CREATED).body(repository.save(client));
    }

    @PutMapping
    public ResponseEntity<Client> Put(@RequestBody Client client) {
        return ResponseEntity.status(HttpStatus.OK).body(repository.save(client));
    }

    @DeleteMapping("/{id}")
    public void Delete(@PathVariable Long id) {
        repository.deleteById(id);
    }
}
