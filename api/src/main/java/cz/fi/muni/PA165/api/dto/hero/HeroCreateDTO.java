package cz.fi.muni.PA165.api.dto.hero;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import java.util.Objects;

/**
 * Encapsulates information needed for hero creation
 *
 * @author Michal Caniga
 */

public class HeroCreateDTO {
    @NotEmpty(message = "Name can not be empty")
    private String name;
    @Min(1)
    private int level;

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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        HeroCreateDTO that = (HeroCreateDTO) o;
        return getLevel() == that.getLevel() &&
                Objects.equals(getName(), that.getName());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getName(), getLevel());
    }

    @Override
    public String toString() {
        return "HeroCreateDTO{" +
                "name='" + name + '\'' +
                ", level=" + level +
                '}';
    }
}
