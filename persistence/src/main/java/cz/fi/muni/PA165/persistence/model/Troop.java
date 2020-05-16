package cz.fi.muni.PA165.persistence.model;

import javax.persistence.*;
import java.util.*;

/**
 * Class representing DnD troop of heroes
 * @author Jan Válka
 */
@Entity(name = "Troop")
@Table(name = "troop")
public class Troop extends AbstractEntity<Long>  {

    @Column(name = "name", nullable = false)
    private String name;
    @Column(name = "mission", nullable = true)
    private String mission;
    @Column(name = "gold")
    private int gold;

    @OneToMany(fetch = FetchType.LAZY)
    @JoinColumn(name = "troop_id")
    private Set<Hero> heroes = new HashSet<Hero>();

    public Troop(){
    }

    public Long getId() {
        return super.getId();
    }

    public void setId(Long id) {
        super.setId(id);
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

    public void setHeroes(Set<Hero> heroes) {
        this.heroes = heroes;
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
        if (!(o instanceof Troop)) return false;
        Troop troop = (Troop) o;
        return getGold() == troop.getGold() &&
                Objects.equals(getId(), troop.getId()) &&
                getName().equals(troop.getName()) &&
                Objects.equals(getHeroes(), troop.getHeroes()) &&
                Objects.equals(getMission(), troop.getMission());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getName(), getMission(), getGold(), getHeroes());
    }

    @Override
    public String toString() {
        return "Troop{" +
                "id=" + super.getId() +
                ", name='" + name + '\'' +
                ", mission='" + mission +'\'' +
                ", gold=" + gold +
                ", heroes=" + heroes +
                '}';
    }
}

