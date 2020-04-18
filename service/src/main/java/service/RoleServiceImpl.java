package service;

import cz.fi.muni.PA165.persistence.dao.RoleDao;
import cz.fi.muni.PA165.persistence.model.Role;
import exceptions.DnDServiceException;
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

    @Autowired
    public RoleServiceImpl(RoleDao roleDao){
        this.roleDao = roleDao;
    }

    @Override
    public Role findById(Long id){
        Role role = roleDao.findById(id);
        if (role == null)
            throw new DnDServiceException("Role with " + id + "not found");
        return role;
    }

    @Override
    public void createRole(Role role){
        roleDao.create(role);
    }

    @Override
    public void deleteRole(Long id){
        Role role = this.findById(id);
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
