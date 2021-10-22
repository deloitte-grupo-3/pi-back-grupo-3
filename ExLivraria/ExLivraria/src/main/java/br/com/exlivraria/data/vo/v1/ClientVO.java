package br.com.exlivraria.data.vo.v1;


import br.com.exlivraria.data.model.Order;
import br.com.exlivraria.data.model.User;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.github.dozermapper.core.Mapping;
import com.google.common.base.Objects;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.springframework.hateoas.ResourceSupport;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.util.List;

public class ClientVO extends ResourceSupport implements Serializable{

    @Mapping("id") // annotation para o Dozer reconhecer
    @JsonProperty("id")
    private Long key;
    private String cpf;
    private String name;
    private String address;
    private String phone;
    private User user;
    private List<Order> orders;

    public ClientVO() {

    }

    public Long getKey() {
        return key;
    }

    public void setId(Long key) {
        this.key = key;
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
        if (!(o instanceof ClientVO)) return false;

        ClientVO clientVO = (ClientVO) o;

        if (key != null ? !key.equals(clientVO.key) : clientVO.key != null) return false;
        if (cpf != null ? !cpf.equals(clientVO.cpf) : clientVO.cpf != null) return false;
        if (name != null ? !name.equals(clientVO.name) : clientVO.name != null) return false;
        if (address != null ? !address.equals(clientVO.address) : clientVO.address != null) return false;
        if (phone != null ? !phone.equals(clientVO.phone) : clientVO.phone != null) return false;
        if (user != null ? !user.equals(clientVO.user) : clientVO.user != null) return false;
        return orders != null ? orders.equals(clientVO.orders) : clientVO.orders == null;
    }

    @Override
    public int hashCode() {
        int result = key != null ? key.hashCode() : 0;
        result = 31 * result + (cpf != null ? cpf.hashCode() : 0);
        result = 31 * result + (name != null ? name.hashCode() : 0);
        result = 31 * result + (address != null ? address.hashCode() : 0);
        result = 31 * result + (phone != null ? phone.hashCode() : 0);
        result = 31 * result + (user != null ? user.hashCode() : 0);
        result = 31 * result + (orders != null ? orders.hashCode() : 0);
        return result;
    }
}
