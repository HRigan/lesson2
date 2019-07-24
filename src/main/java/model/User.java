package model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.MappedSuperclass;

@MappedSuperclass
public class User {

    @Getter @Setter
    private String fio;
    @Getter @Setter
    private String phone;
    public User() {

    }

    public User(String fio, String phone) {
        this.fio = fio;
        this.phone = phone;
    }

    @Override
    public String toString() {
        return ";"+fio+";"+phone+";";
    }
}
