package cz.fi.muni.PA165.persistence.model;

import javax.persistence.*;
import javax.validation.constraints.Min;
import java.util.*;

/**
 * Class representing DnD group of heroes
 * @author Jan Válka
 */
@Entity(name = "Group")
@Table(name = "dnd_group")
public class Group {

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long id;
    @Column(name = "name", nullable = false)
    private String name;
    @Column(name = "mission", nullable = true)
    private String mission;
    @Column(name = "gold")
    @Min(0)
    private int gold;

    @OneToMany(fetch = FetchType.LAZY)
    private Set<Hero> heroes = new HashSet<Hero>();

    public Group(){
    }
    public Long getId() {
        return this.id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public String getName(){
        return this.name;
    }

    public void setName(String name){
        this.name = name;
    }

    public String getMission(){
        return this.mission;
    }

    public void setMission(String missionName){
        this.mission = missionName;
    }

    public int getGold(){
        return this.gold;
    }

    public void setGold(int gold){
        this.gold = gold;
    }

    public Set<Hero> getHeroes(){
        return Collections.unmodifiableSet(heroes);
    }

    public void addHero(Hero hero){
        this.heroes.add(hero);

    }

    public void removeHero(Hero hero){
        this.heroes.remove(hero);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Group)) return false;
        Group group = (Group) o;
        return getGold() == group.getGold() &&
                Objects.equals(getId(), group.getId()) &&
                getName().equals(group.getName()) &&
                Objects.equals(getHeroes(), group.getHeroes()) &&
                Objects.equals(getMission(), group.getMission());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getName(), getMission(), getGold(), getHeroes());
    }

    @Override
    public String toString() {
        return "Group{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", mission='" + mission +'\'' +
                ", gold=" + gold +
                ", heroes=" + heroes +
                '}';
    }
}

