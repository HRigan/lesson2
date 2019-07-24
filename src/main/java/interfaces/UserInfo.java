package interfaces;

import java.util.ArrayList;

public interface UserInfo {

     void fromCVS(String str);

    ArrayList<String> toCVS();

    String findById(int num,String id);
}
