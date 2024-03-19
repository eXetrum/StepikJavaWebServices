package models;

public interface IAccountService {
    void singUp(String login, String password);
    boolean singIn(String login, String password);
}
