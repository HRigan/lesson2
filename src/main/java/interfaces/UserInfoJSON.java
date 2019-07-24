package interfaces;

import model.UserManager;

import java.io.IOException;

public interface UserInfoJSON {

    void fromJSON(UserInfoJSON userIndividual);
    UserManager toJSON(String id) throws IOException;
}
