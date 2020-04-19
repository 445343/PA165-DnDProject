package cz.fi.muni.PA165.api.dto.user;

import javax.validation.constraints.NotEmpty;
import java.util.Objects;

/**
 * Encapsulates information needed for user creation
 * @author Boris Jadus
 */
public class UserCreateDTO {

    @NotEmpty(message = "User name can not be empty")
    private String userName;
    @NotEmpty(message = "Password can not be empty")
    private String password;
    private boolean isAdmin;

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public boolean isAdmin() {
        return isAdmin;
    }

    public void setAdmin(boolean admin) {
        isAdmin = admin;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof UserCreateDTO)) return false;
        UserCreateDTO that = (UserCreateDTO) o;
        return isAdmin() == that.isAdmin() &&
                Objects.equals(getUserName(), that.getUserName()) &&
                Objects.equals(getPassword(), that.getPassword());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getUserName(), getPassword(), isAdmin());
    }

    @Override
    public String toString() {
        return "UserCreateDTO{" +
                "userName='" + userName + '\'' +
                ", password='" + password + '\'' +
                ", isAdmin=" + isAdmin +
                '}';
    }
}
