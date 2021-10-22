package br.com.exlivraria.data.model;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "clients")
public class Client {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @Column(length = 11, unique = true)
    private String cpf;

    @NotNull
    @Size(min = 5, max = 250)
    private String name;

    @NotNull
    @Size(min = 35, max = 500)
    private String address;

    @NotNull
    @Size(min = 10, max = 12)
    private String phone;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "user_id", referencedColumnName = "id")
    private User user;

    @OneToMany(mappedBy = "client")
    private List<Order> orders;

    public Client() {

    }

    public Client(Long id, String cpf, String name, String address, String phone, User user) {
        this.id = id;
        this.cpf = cpf;
        this.name = name;
        this.address = address;
        this.phone = phone;
        this.user = user;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Client client = (Client) o;
        return Objects.equals(id, client.id) && Objects.equals(cpf, client.cpf) && Objects.equals(name, client.name) && Objects.equals(address, client.address) && Objects.equals(phone, client.phone) && Objects.equals(user, client.user);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, cpf, name, address, phone, user);
    }
}
