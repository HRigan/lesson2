package model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.Collection;
import java.util.List;

@Entity
public class LangList {
    @Id
    @GeneratedValue
    @Getter
    @Setter
    private Integer id=0;
    @Getter
    @Setter
    private String name;
    @ManyToMany(fetch = FetchType.LAZY, mappedBy = "lang")
   /* @JoinTable(name = "language",
            joinColumns = @JoinColumn(name = "langId"),
            inverseJoinColumns = @JoinColumn(name = "userId"))*/
    @Getter
    @Setter
    private Collection<UserDeveloper> langName;

    public LangList() {
    }

    public LangList(String name) {
        this.name = name;
    }
}


