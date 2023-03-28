package accounts;

import java.util.HashMap;
import java.util.Map;

/**
 * @author N.Akimov
 * @version 1.0
 */

public class AccountService {

    private final Map<String, UserProfile> loginToProfile;

    public AccountService() {
        loginToProfile = new HashMap<>();
    }

    /**
     * Method for adding a user profile to the map by his login
     *
     * @param userProfile - user profile with information about his login, password and email
     */
    public void addNewUser(UserProfile userProfile) {
        loginToProfile.put(userProfile.getLogin(), userProfile);
    }

    /**
     * pass the login to the method to search for the user profile associated with the login
     *
     * @param login - user's login
     * @return information about user profile
     */
    public UserProfile getUserByLogin(String login) {
        return loginToProfile.get(login);
    }
}


