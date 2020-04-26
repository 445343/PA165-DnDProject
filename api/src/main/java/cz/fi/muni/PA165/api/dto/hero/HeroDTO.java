package cz.fi.muni.PA165.api.dto.hero;

import cz.fi.muni.PA165.api.dto.role.RoleDTO;

import java.util.Objects;
import java.util.Set;

/**
 * Encapsulates information about hero
 *
 * @author Michal Caniga
 */

public class HeroDTO {
    private Long id;
    private String name;
    private int level;
    private Set<RoleDTO> roles;

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

    public Set<RoleDTO> getRoles() {
        return roles;
    }

    public void setRoles(Set<RoleDTO> roles) {
        this.roles = roles;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        HeroDTO heroDTO = (HeroDTO) o;
        return getLevel() == heroDTO.getLevel() &&
                Objects.equals(getId(), heroDTO.getId()) &&
                Objects.equals(getName(), heroDTO.getName()) &&
                Objects.equals(getRoles(), heroDTO.getRoles());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getName(), getLevel(), getRoles());
    }

    @Override
    public String toString() {
        return "HeroDTO{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", level=" + level +
                ", roles=" + roles +
                '}';
    }
}
