package cz.fi.muni.PA165.api.dto.troop;

import cz.fi.muni.PA165.api.dto.hero.HeroDTO;

import java.util.Collections;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

/**
 * Encapsulates information about troop
 *
 * @author Alena Bednarikova
 */
public class TroopDTO {

    private Long id;
    private String name;
    private String mission;
    private int gold;
    private Set<HeroDTO> heroes;

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

    public Set<HeroDTO> getHeroes(){
        return heroes;
    }

    public void setHeroes(Set<HeroDTO> heroes) {
        this.heroes = heroes;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof TroopDTO)) return false;
        TroopDTO troop = (TroopDTO) o;
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
        return "TroopDTO{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", mission='" + mission +'\'' +
                ", gold=" + gold +
                ", heroes=" + heroes +
                '}';
    }
}
