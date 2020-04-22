package cz.fi.muni.PA165.api.dto.role;

import javax.validation.constraints.NotEmpty;
import java.util.Objects;

/**
 * Encapsulates information needed for role creation
 *
 * @author Jan Válka
 */
public class RoleCreateDTO {

    @NotEmpty(message = "Name can not be empty")
    private String name;

    private String description;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof RoleDTO)) return false;
        RoleCreateDTO roleCreateDTO = (RoleCreateDTO) o;
        return getName().equals(roleCreateDTO.getName()) &&
                Objects.equals(getDescription(), roleCreateDTO.getDescription());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getName(), getDescription());
    }

    @Override
    public String toString() {
        return "RoleCreateDTO{"+
                "name='" + name + '\'' +
                ", description='" + description + '\'' +
                '}';
    }
}
