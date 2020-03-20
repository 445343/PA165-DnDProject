package cz.fi.muni.PA165.persistence.model;

import javax.persistence.*;
import java.util.Objects;

/**
 * Class representing DnD User
 *
 * @author Michal Caniga
 */
@Entity(name = "User")
@Table(name = "user")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "userName", nullable = false)
    private String userName;

    @Column(name = "passwordHash", nullable = false)
    private String passwordHash;

    @Column(name = "isAdmin", nullable = false)
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

    public String getPasswordHash() {
        return passwordHash;
    }

    public void setPasswordHash(String passwordHash) {
        this.passwordHash = passwordHash;
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
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return isAdmin == user.isAdmin &&
                Objects.equals(id, user.id) &&
                Objects.equals(userName, user.userName) &&
                Objects.equals(passwordHash, user.passwordHash);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, userName, passwordHash, isAdmin);
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", userName='" + userName + '\'' +
                ", passwordHash='" + passwordHash + '\'' +
                ", isAdmin=" + isAdmin +
                '}';
    }
}
