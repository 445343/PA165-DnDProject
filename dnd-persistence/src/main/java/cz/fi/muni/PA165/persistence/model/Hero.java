package cz.fi.muni.PA165.persistence.model;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

@Entity
public class Hero {

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false)
    private String name;
    private int level;
    @ManyToMany
    private Set<Role> roles = new HashSet<Role>();
    @ManyToOne
    private Group group;

    public Hero() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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
        return roles;
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

    public Group getGroup() {
        return group;
    }

    public void setGroup(Group group) {
        this.group = group;
    }

    public void joinGroup(Group group){
        this.group = group;
        //group.addHero(this);
    }

    public void leaveGroup(Group group){
        this.group = null;
        //group.removeHero(this)
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Hero)) return false;
        Hero hero = (Hero) o;
        return getLevel() == hero.getLevel() &&
                Objects.equals(getId(), hero.getId()) &&
                getName().equals(hero.getName()) &&
                Objects.equals(getRoles(), hero.getRoles()) &&
                Objects.equals(getGroup(), hero.getGroup());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getName(), getLevel(), getRoles(), getGroup());
    }

    @Override
    public String toString() {
        return "Hero{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", level=" + level +
                ", roles=" + roles +
                ", group=" + group +
                '}';
    }
}
