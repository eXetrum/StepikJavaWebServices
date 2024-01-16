package accounts;

import java.util.Map;
import java.util.HashMap;
public class AccountService {
    private final Map<String, UserProfile> loginToProfile;
    private final Map<String, UserProfile> sessionIdToProfile;

    public AccountService() {
        loginToProfile = new HashMap<>();
        sessionIdToProfile = new HashMap<>();
    }

    public void addNewUser(UserProfile profile) { loginToProfile.put(profile.getLogin(), profile); }
    public UserProfile getUserByLogin(String login) { return loginToProfile.get(login); }
    public UserProfile getUserBySessionId(String sessionId) { return sessionIdToProfile.get(sessionId); }

    public void addSession(String sessionId, UserProfile profile) { sessionIdToProfile.put(sessionId, profile); }
    public void deleteSession(String sessionId) { sessionIdToProfile.remove(sessionId); }

}