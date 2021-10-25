package br.com.exlivraria.repository;

import br.com.exlivraria.data.model.Client;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ClientRepository extends JpaRepository <Client, Long> {
    public Client findByCpf(String cpf);
    public List<Client> findAllByNameContainingIgnoreCase(String name);
    public List<Client> findAllByAddressContainingIgnoreCase(String address);
}
