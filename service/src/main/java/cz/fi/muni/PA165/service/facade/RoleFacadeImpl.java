package cz.fi.muni.PA165.service.facade;

import cz.fi.muni.PA165.api.dto.role.RoleCreateDTO;
import cz.fi.muni.PA165.api.dto.role.RoleDTO;
import cz.fi.muni.PA165.api.dto.role.RoleUpdateDTO;
import cz.fi.muni.PA165.api.facade.RoleFacade;
import cz.fi.muni.PA165.persistence.model.Role;
import cz.fi.muni.PA165.service.RoleService;
import cz.fi.muni.PA165.service.mapping.BeanMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

/**
 * Implementation of RoleFacade interface
 *
 * @author Jan Válka
 */
@Service
@Transactional
public class RoleFacadeImpl implements RoleFacade {

    private RoleService roleService;
    private BeanMapper beanMapper;

    @Autowired
    public RoleFacadeImpl(RoleService roleService, BeanMapper beanMapper){
        this.roleService = roleService;
        this.beanMapper = beanMapper;
    }

    @Override
    public RoleDTO findById(Long id){
        Role role = roleService.findById(id);
        return beanMapper.mapTo(role, RoleDTO.class);
    }

    @Override
    public Long createRole(RoleCreateDTO role){
        Role newRole = beanMapper.mapTo(role, Role.class);
        return roleService.createRole(newRole);
    }

    @Override
    public void deleteRole(Long id){
        roleService.deleteRole(id);
    }

    @Override
    public void updateRole(RoleUpdateDTO role){
        Role updateRole = beanMapper.mapTo(role, Role.class);
        roleService.updateRole(updateRole);
    }

    @Override
    public List<RoleDTO> findAllRoles(){
        List<Role> allRoles = roleService.findAllRoles();
        return beanMapper.mapTo(allRoles, RoleDTO.class);
    }
}
