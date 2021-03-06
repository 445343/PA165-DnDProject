package cz.fi.muni.PA165.persistence.model;

import javax.persistence.*;
import java.util.Collections;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

/**
 * Class representing DnD Hero character
 * @author Boris Jadus
 */
@Entity(name = "Hero")
@Table(name = "hero")
public class Hero extends AbstractEntity<Long> {

    @Column(name = "name", nullable = false)
    private String name;
    @Column(name = "level")
    private int level;
    @ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.MERGE)
    @JoinColumn(name = "role_id")
    private Set<Role> roles = new HashSet<Role>();
    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.MERGE)
    @JoinColumn(name = "troop_id")
    private Troop troop;

    public Hero() {
    }

    public Long getId() {
        return super.getId();
    }

    public void setId(Long id) {
        super.setId(id);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
    }

    public Set<Role> getRoles() {
        return Collections.unmodifiableSet(roles);
    }

    public void setRoles(Set<Role> roles) {
        this.roles = roles;
    }

    public void addRole(Role role){
        this.roles.add(role);
    }

    public void removeRole(Role role){
        this.roles.remove(role);
    }

    public Troop getTroop() {
        return troop;
    }

    public void setTroop(Troop troop) {
        this.troop = troop;
    }

    public void joinTroop(Troop troop){
        this.troop = troop;
        troop.addHero(this);
    }

    public void leaveTroop(){
        this.troop.removeHero(this);
        this.troop = null;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Hero)) return false;
        Hero hero = (Hero) o;
        return getLevel() == hero.getLevel() &&
                getName().equals(hero.getName()) &&
                Objects.equals(getRoles(), hero.getRoles());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getName(), getLevel(), getRoles());
    }

    @Override
    public String toString() {
        return "Hero{" +
                "id=" + super.getId() +
                ", name='" + name + '\'' +
                ", level=" + level +
                ", roles=" + roles +
                '}';
    }
}
