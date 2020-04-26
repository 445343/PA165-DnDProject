package cz.fi.muni.PA165.api.dto.troop;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.Objects;

/**
 * Encapsulates information for troop update
 *
 * @author Alena Bednarikova
 */

public class TroopUpdateDTO {
    @NotNull(message = "Id can not be null")
    private Long id;
    @NotEmpty(message = "Name can not be empty")
    private String name;
    @Min(value = 0, message = "Gold can not be negative number")
    private int gold;

    private String mission;

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

    public int getGold() {
        return gold;
    }

    public void setGold(int gold) {
        this.gold = gold;
    }

    public String getMission() {
        return mission;
    }

    public void setMission(String mission) {
        this.mission = mission;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof TroopUpdateDTO)) return false;
        TroopUpdateDTO that = (TroopUpdateDTO) o;
        return getGold() == that.getGold() &&
                Objects.equals(getId(), that.getId()) &&
                Objects.equals(getName(), that.getName()) &&
                Objects.equals(getMission(), that.getMission());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getName(), getGold(), getMission());
    }

    @Override
    public String toString() {
        return "TroopUpdateDTO{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", gold=" + gold +
                ", mission='" + mission + '\'' +
                '}';
    }
}
