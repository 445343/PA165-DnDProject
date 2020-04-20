package cz.fi.muni.PA165.api.dto.hero;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.Objects;

/**
 * Encapsulates information for hero update
 *
 * @author Michal Caniga
 */

public class HeroUpdateDTO {
    @NotNull(message = "Id can not be null")
    private Long id;
    @NotEmpty(message = "Name can not be empty")
    private String name;
    @Min(1)
    private int level;

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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        HeroUpdateDTO that = (HeroUpdateDTO) o;
        return getLevel() == that.getLevel() &&
                Objects.equals(getId(), that.getId()) &&
                Objects.equals(getName(), that.getName());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getName(), getLevel());
    }

    @Override
    public String toString() {
        return "HeroUpdateDTO{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", level=" + level +
                '}';
    }
}
