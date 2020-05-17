package cz.fi.muni.PA165.service;

import cz.fi.muni.PA165.api.exceptions.ErrorStatus;
import cz.fi.muni.PA165.persistence.dao.HeroDao;
import cz.fi.muni.PA165.persistence.dao.RoleDao;
import cz.fi.muni.PA165.persistence.model.Hero;
import cz.fi.muni.PA165.persistence.model.Role;
import cz.fi.muni.PA165.api.exceptions.DnDServiceException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Implementation of RoleService interface
 * @author Jan Válka
 */
@Service
public class RoleServiceImpl implements RoleService{

    private RoleDao roleDao;
    private HeroDao heroDao;

    @Autowired
    public RoleServiceImpl(RoleDao roleDao, HeroDao heroDao){
        this.roleDao = roleDao;
        this.heroDao = heroDao;
    }

    @Override
    public Role findById(Long id){
        Role role = roleDao.findById(id);
        if (role == null)
            throw new DnDServiceException("Role with id: " + id + "not found", ErrorStatus.RESOURCE_NOT_FOUND);
        return role;
    }

    @Override
    public Long createRole(Role role){
        return roleDao.create(role);
    }

    @Override
    public void deleteRole(Long id){
        Role role = this.findById(id);
        List<Hero> heroes = heroDao.findAll();
        for (Hero hero : heroes){
            if (hero.getRoles().contains(role))
                hero.removeRole(role);
        }
        roleDao.delete(role);
    }

    @Override
    public void updateRole(Role role){
        roleDao.update(role);
    }

    @Override
    public List<Role> findAllRoles(){
        return roleDao.findAll();
    }
}
