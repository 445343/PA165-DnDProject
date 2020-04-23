import cz.fi.muni.PA165.api.exceptions.DnDServiceException;
import cz.fi.muni.PA165.persistence.dao.RoleDao;
import cz.fi.muni.PA165.persistence.model.Role;
import cz.fi.muni.PA165.service.RoleService;
import cz.fi.muni.PA165.service.RoleServiceImpl;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.List;

import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.BDDMockito.given;
import static org.mockito.BDDMockito.then;
import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

public class RoleServiceTest {

    private RoleService roleService;

    @Mock
    private RoleDao roleDao;

    private Role role1;

    @BeforeMethod
    public void init(){
        MockitoAnnotations.initMocks(this);
        roleService = new RoleServiceImpl(roleDao);

        role1 = new Role();
        role1.setId(1L);
        role1.setName("RoleName1");
        role1.setDescription("Description1");
    }

    @Test
    public void findById(){
        given(roleDao.findById(role1.getId())).willReturn(role1);
        Role role = roleService.findById(role1.getId());
        assertEquals(role, role1);
        then(roleDao).should().findById(role1.getId());
    }

    @Test(expectedExceptions = DnDServiceException.class)
    public void findByIdWithException(){
        given(roleDao.findById(anyLong())).willReturn(null);
        roleService.findById(100L);
    }

    @Test
    public void createRole(){
        roleService.createRole(role1);
        then(roleDao).should().create(role1);
    }

    @Test
    public void deleteRole(){
        given(roleDao.findById(role1.getId())).willReturn(role1);
        roleService.deleteRole(role1.getId());
        then(roleDao).should().delete(role1);
    }

    @Test
    public void updateRole(){
        roleService.updateRole(role1);
        then(roleDao).should().update(role1);
    }

    @Test
    public void findAllRoles(){
        Role role2 = new Role();
        role2.setId(2L);
        role2.setName("RoleName2");
        role2.setDescription("Description2");

        given(roleDao.findAll()).willReturn(List.of(role1, role2));
        List<Role> roles = roleService.findAllRoles();
        assertEquals(roles.size(), 2);
        assertTrue(roles.contains(role1));
        assertTrue(roles.contains(role2));
        then(roleDao).should().findAll();
    }
}
