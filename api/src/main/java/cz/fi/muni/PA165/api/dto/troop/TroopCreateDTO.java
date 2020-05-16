package cz.fi.muni.PA165.api.dto.troop;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import java.util.Objects;

/**
 * Encapsulates information needed for troop creation
 *
 * @author Alena Bednarikova
 */
public class TroopCreateDTO {
    @NotEmpty(message = "Name can not be empty")
    private String name;

    private String mission;

    @Min(value = 0, message = "Gold can not be negative number")
    private int gold;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getMission() {
        return mission;
    }

    public void setMission(String mission) {
        this.mission = mission;
    }

    public int getGold() {
        return gold;
    }

    public void setGold(int gold) {
        this.gold = gold;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof TroopCreateDTO)) return false;
        TroopCreateDTO that = (TroopCreateDTO) o;
        return getGold() == that.getGold() &&
                Objects.equals(getName(), that.getName()) &&
                Objects.equals(getMission(), that.getMission());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getName(), getMission(), getGold());
    }

    @Override
    public String toString() {
        return "TroopCreateDTO{" +
                "name='" + name + '\'' +
                ", mission='" + mission + '\'' +
                ", gold=" + gold +
                '}';
    }
}
