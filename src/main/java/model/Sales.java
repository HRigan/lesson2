package model;


import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
@Entity
public class Sales {
    @Id
    @GeneratedValue
    private Integer id;
    @Getter @Setter
    private Integer UserId;
    @Getter @Setter
    private String  name;
    @Getter @Setter
    private Integer coast;

}
