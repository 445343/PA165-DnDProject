package cz.fi.muni.PA165.api.dto.role;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.Objects;

/**
 * Encapsulates information for role update
 *
 * @author Jan Válka
 */
public class RoleUpdateDTO {
    @NotNull(message = "Id can not be null")
    private Long id;
    @NotEmpty(message = "Name can not be empty")
    private String name;

    private String description;

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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof RoleUpdateDTO)) return false;
        RoleUpdateDTO roleUpdateDTO = (RoleUpdateDTO) o;
        return getId().equals(roleUpdateDTO.getId()) &&
                getName().equals(roleUpdateDTO.getName()) &&
                Objects.equals(getDescription(), roleUpdateDTO.getDescription());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getName(), getDescription());
    }

    @Override
    public String toString() {
        return "RoleUpdateDTO{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                '}';
    }
}
