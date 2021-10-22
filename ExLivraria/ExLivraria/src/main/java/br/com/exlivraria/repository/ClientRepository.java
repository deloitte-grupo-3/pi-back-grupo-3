package br.com.exlivraria.repository;

import br.com.exlivraria.data.model.Client;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ClientRepository extends JpaRepository <Client, Long> {
    public Client findByCpf(String cpf);
    public List<Client> findAllByNameContainingIgnoreCase(String name);
    public List<Client> findAllByAddressContainingIgnoreCase(String address);
}
