package model;

import com.fasterxml.jackson.databind.ObjectMapper;
import interfaces.UserInfo;
import interfaces.UserInfoJSON;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;
@Entity
public class UserManager extends  User implements UserInfo, UserInfoJSON {
    @Id
    @GeneratedValue
    @Getter @Setter
    private Integer id;
    @Getter @Setter
    private ArrayList<Sales> sales;
    public UserManager() {
    }

    public UserManager(String fio, String phone, Integer id, ArrayList<Sales> sales) {
        super(fio, phone);
        this.id = id;
        this.sales = sales;
    }

    public void fromJSON(UserInfoJSON userIndividual){
        ObjectMapper mapper = new ObjectMapper();
        try {
            mapper.writeValue(new File("UserManager"+id+".json"),userIndividual);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public UserManager toJSON(String id) throws IOException {
        ObjectMapper mapper = new ObjectMapper();

        return mapper.readValue(new File("UserManager"+id+".json"), UserManager.class);
    }



    @Override
    public void fromCVS(String str) {
        try {

            FileWriter fw = new FileWriter("userIndividual.csv", true);
            fw.write(str);
            fw.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public ArrayList<String> toCVS() {
        try {

            Scanner scanner = new Scanner(new FileReader("userIndividual.csv"));
            ArrayList<String> line = new ArrayList<>();
            while (scanner.hasNextLine()) {
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
        return super.toString() + sales + "\n";
    }

    public String findById(int num, String id) {
        Scanner scanner = null;
        String line = "";
        String cvsSplitBy = ",";

        try {

            scanner = new Scanner(new FileReader("userIndividual.csv"));
            while (scanner.hasNextLine()) {

                // use comma as separator
                String[] strings = line.split(cvsSplitBy);
                if(strings[0].equals(id)) {
                    scanner.close();
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
