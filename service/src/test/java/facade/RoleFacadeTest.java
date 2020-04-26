package facade;

import cz.fi.muni.PA165.api.dto.role.RoleCreateDTO;
import cz.fi.muni.PA165.api.dto.role.RoleDTO;
import cz.fi.muni.PA165.api.dto.role.RoleUpdateDTO;
import cz.fi.muni.PA165.api.facade.RoleFacade;
import cz.fi.muni.PA165.persistence.model.Role;
import cz.fi.muni.PA165.service.RoleService;
import cz.fi.muni.PA165.service.facade.RoleFacadeImpl;
import cz.fi.muni.PA165.service.mapping.BeanMapper;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.BDDMockito.given;
import static org.mockito.BDDMockito.then;
import static org.testng.Assert.assertEquals;

/**
 * Tests for RoleFacade
 *
 * @author Boris Jadus
 */
public class RoleFacadeTest {

    private RoleFacade roleFacade;

    @Mock
    private RoleService roleService;
    @Mock
    private BeanMapper beanMapper;

    private Role role;
    private RoleDTO roleDTO;

    @BeforeMethod
    public void init(){
        MockitoAnnotations.initMocks(this);
        roleFacade = new RoleFacadeImpl(roleService, beanMapper);
        role = new Role();
        role.setId(1L);
        role.setName("RoleName");
        role.setDescription("RoleDescription");

        roleDTO = new RoleDTO();
        roleDTO.setId(role.getId());
        roleDTO.setName(role.getName());
        roleDTO.setDescription(role.getDescription());
    }

    @Test
    public void findById(){
        given(roleService.findById(role.getId())).willReturn(role);
        given(beanMapper.mapTo(role, RoleDTO.class)).willReturn(roleDTO);
        RoleDTO roleById = roleFacade.findById(role.getId());
        assertEquals(roleDTO, roleById);
        then(roleService).should().findById(role.getId());
    }

    @Test
    public void createRole() {
        given(beanMapper.mapTo(any(RoleCreateDTO.class), eq(Role.class))).willReturn(role);
        roleFacade.createRole(new RoleCreateDTO());
        then(roleService).should().createRole(role);
    }

    @Test
    public void deleteRole(){
        roleFacade.deleteRole(20L);
        then(roleService).should().deleteRole(20L);
    }

    @Test
    public void updateRole() {
        given(beanMapper.mapTo(any(RoleUpdateDTO.class), eq(Role.class))).willReturn(role);
        roleFacade.updateRole(new RoleUpdateDTO());
        then(roleService).should().updateRole(role);
    }

    @Test
    public void findAllRoles(){
        Role role2 = new Role();
        role2.setId(2L);
        role2.setName("Name2");
        role2.setDescription("Description2");

        given(roleService.findAllRoles()).willReturn(List.of(role, role2));
        roleFacade.findAllRoles();
        then(roleService).should().findAllRoles();
        then(beanMapper).should().mapTo(List.of(role, role2), RoleDTO.class);
    }
}
