package dto.user;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.Objects;

/**
 * Encapsulates information for user update
 * @author Boris Jadus
 */
public class UserUpdateDTO {

    @NotNull(message = "Id can not be null")
    private Long id;
    @NotEmpty(message = "User name cannot be null")
    private String userName;
    private boolean isAdmin;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
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
        if (!(o instanceof UserUpdateDTO)) return false;
        UserUpdateDTO that = (UserUpdateDTO) o;
        return isAdmin() == that.isAdmin() &&
                Objects.equals(getId(), that.getId()) &&
                Objects.equals(getUserName(), that.getUserName());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getUserName(), isAdmin());
    }

    @Override
    public String toString() {
        return "UserUpdateDTO{" +
                "id=" + id +
                ", userName='" + userName + '\'' +
                ", isAdmin=" + isAdmin +
                '}';
    }
}
