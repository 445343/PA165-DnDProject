package cz.fi.muni.PA165.persistence.model;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Collections;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

/**
 * Class representing DnD User
 *
 * @author Michal Caniga
 */
@Entity(name = "User")
@Table(name = "user")
public class User implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "user_name", nullable = false)
    private String userName;

    @Column(name = "password_hash", nullable = false)
    private String passwordHash;

    @Column(name = "is_admin")
    private boolean isAdmin;

    @OneToMany(fetch = FetchType.LAZY)
    @JoinColumn(name = "hero_id")
    private Set<Hero> heroes = new HashSet<>();

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

    public Set<Hero> getHeroes() {
        return Collections.unmodifiableSet(heroes);
    }

    public void setHeroes(Set<Hero> heroes) {
        this.heroes = heroes;
    }

    public void addHero(Hero hero) {
        this.heroes.add(hero);
    }

    public void removeHero(Hero hero) {
        this.heroes.remove(hero);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return isAdmin == user.isAdmin &&
                Objects.equals(id, user.id) &&
                Objects.equals(userName, user.userName) &&
                Objects.equals(passwordHash, user.passwordHash) &&
                Objects.equals(heroes, user.heroes);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, userName, passwordHash, isAdmin, heroes);
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", userName='" + userName + '\'' +
                ", passwordHash='" + passwordHash + '\'' +
                ", isAdmin=" + isAdmin +
                ", heroes=" + heroes +
                '}';
    }
}
