package model;

import interfaces.UserInfo;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Scanner;
@Entity
public class UserDeveloper extends User implements UserInfo {
    @Id
    @GeneratedValue
    @Getter @Setter
    private Integer id=0;
    @Getter @Setter
    @ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinTable(name="language",
            joinColumns=@JoinColumn(name="userId"),
            inverseJoinColumns=@JoinColumn(name="langId"))
    private Collection<LangList> lang;


    public UserDeveloper() {
    }

    public UserDeveloper(String fio, String phone, ArrayList<LangList> lang) {
        super(fio, phone);
        this.lang = lang;
    }

    @Override
    public void fromCVS(String str){
        try {

            FileWriter fw = new FileWriter("userEntity.csv", true);
            fw.write(str);
            fw.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    @Override
    public ArrayList<String> toCVS(){
        try {

            Scanner scanner =new Scanner(new FileReader("userDeveloper.csv"));
            ArrayList<String> line =  new ArrayList<>();
            while (scanner.hasNextLine() ) {
                line.add(scanner.nextLine());
            }
            scanner.close();
            return line;
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public String toString() {
         return super.toString()+"\n";
    }

    public String findById(int num, String id) {
        Scanner scanner = null;
        String line = "";
        String cvsSplitBy = ";";

        try {

            scanner = new Scanner(new FileReader("userDeveloper.csv"));
            while (scanner.hasNextLine()) {

                // use comma as separator
                String[] strings = line.split(cvsSplitBy);
                if(strings[0].equals(id)) {
                    return "First name: " + strings[1] + "/nLast name: " + strings[2];
                }
            }

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return "нет пользователя с таким id";
    }
}
