package cz.fi.muni.PA165.api.dto.user;

import cz.fi.muni.PA165.api.dto.hero.HeroDTO;

import java.util.Objects;
import java.util.Set;

/**
 * Encapsulates information about user
 * @author Boris Jadus
 */
public class UserDTO {

    private Long id;
    private String userName;
    private boolean isAdmin;
    private Set<HeroDTO> heroes;

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

    public Set<HeroDTO> getHeroes() {
        return heroes;
    }

    public void setHeroes(Set<HeroDTO> heroes) {
        this.heroes = heroes;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof UserDTO)) return false;
        UserDTO userDTO = (UserDTO) o;
        return isAdmin() == userDTO.isAdmin() &&
                Objects.equals(getId(), userDTO.getId()) &&
                Objects.equals(getUserName(), userDTO.getUserName()) &&
                Objects.equals(getHeroes(), userDTO.getHeroes());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getUserName(), isAdmin(), getHeroes());
    }

    @Override
    public String toString() {
        return "UserDTO{" +
                "id=" + id +
                ", userName='" + userName + '\'' +
                ", isAdmin=" + isAdmin +
                ", heroes=" + heroes +
                '}';
    }
}
