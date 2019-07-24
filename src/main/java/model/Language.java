package model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
public class Language {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Getter @Setter
    private Integer id=0;
    @Getter @Setter
    private Integer userId=0;
    @Getter @Setter
    private Integer langId=0;

    public Language() {
    }
}
