package com.atstudy;

import com.atstudy.entity.*;
import com.atstudy.entity.bo.*;
import com.atstudy.entity.vo.PermissionWithRoleVo;
import com.atstudy.mapper.*;
import com.atstudy.service.*;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.core.userdetails.UserDetails;

import javax.annotation.Resource;
import java.util.List;

@SpringBootTest
@Slf4j
public class TestApplication {


    @Resource
    private AdminService adminService;

    @Resource
    private RoleMapper roleMapper;

    @Resource
    private MenuMapper menuMapper;

    @Resource
    private AdminMapper adminMapper;

    @Resource
    private RoleService roleService;

    @Resource
    private CategoryMapper categoryMapper;


    @Resource
    private PermissionService permissionService;

    @Resource
    private PermissionMapper permissionMapper;


    @Resource
    private UserService userService;

    @Resource
    private CategoryService categoryService;

    @Resource
    private BrandMapper brandMapper;

    @Resource
    private SpuAttrKeyMapper spuAttrKeyMapper;

    @Resource
    private SpuAttrKeyCategoryMapper spuAttrKeyCategoryMapper;


    @Resource
    private SpuMapper spuMapper;

    @Resource
    private SpuService spuService;


    @Resource
    private SkuMapper skuMapper;


    @Test
    public void testSkuMapper(){
        List<Sku> skus = skuMapper.listBySearchBo(new SkuSearchBo(), new PageBo());
        for (Sku sku : skus) {
            System.out.println(sku);
        }
    }

    @Test
    public void testSpuService(){
        boolean b = spuService.deleteOneById(10025867104543L);
        System.out.println(b);
    }


    @Test
    public void testSpuMapper(){
        List<Spu> spuList = spuMapper.listBySearchBo(new SpuSearchBo(),new PageBo());

        for (Spu spu : spuList) {
            System.out.println(spu);
        }
    }


    @Test
    public void testSpuAttrKeyCategoryMapper(){
        List<Integer> integers = spuAttrKeyCategoryMapper.listCateIdByKeyId("a277ed56-650d-471f-8aa3-380fa399dae5");
        for (Integer integer : integers) {
            System.out.println(integer);
        }
    }


    @Test
    public void testSpuAttrKeyMapper(){
        List<SpuAttrKey> list = spuAttrKeyMapper.getKeyListBySpuId(100009616933L);
        for (SpuAttrKey spuAttrKey : list) {
            System.out.println(spuAttrKey);
        }
    }


    @Test
    public void testBrandMapper(){
        List<Brand> list = brandMapper.listBySearchBo(new BrandSearchBo(), new PageBo());
        for (Brand brand : list) {
            System.out.println(brand);
        }
    }

    @Test
    public void testCategoryMapper(){
        List<Integer> idListBySpuId = categoryMapper.getIdListBySpuId(100012809020L);
        for (Integer integer : idListBySpuId) {
            System.out.println(integer);
        }
    }


    @Test
    public void testUserService(){
        List<User> users = userService.listUserBySearchBo(new UserSearchBo(), new PageBo());
        for (User user : users) {
            System.out.println(user);
        }
    }

    @Test
    public void testPermissionMapper(){
        List<PermissionWithRoleVo> list = permissionMapper.listVoWithRoleId(23);
        for (PermissionWithRoleVo permissionWithRoleVo : list) {
            System.out.println(permissionWithRoleVo);
        }
    }



    @Test
    public void testPermissionService(){
        List<Permission> list = permissionService.list();
        for (Permission permission : list) {
            System.out.println(permission);
        }
    }

    @Test
    public void  testRoleService(){
        boolean b = roleService.isNameExists("系统管理员");
        System.out.println(b);
    }


    @Test
    public void testAdminMapper(){
        Admin adminById = adminMapper.getAdminById(1);

    }


    @Test
    public void testMenuMapper(){
        List<Menu> menus = menuMapper.listByAdminId(2);
        for (Menu menu : menus) {
            log.info(menu.toString());
        }
    }


    @Test
    public void testRoleMapper(){



//        List<Role> list = roleMapper.listByuUrl("/userorder/admin");
//        for (Role role : list) {
//            System.out.println(role);
//        }
    }

    @Test
    public void  testAdminService(){
        UserDetails admin = adminService.loadUserByUsername("admin");
        System.out.println(admin);
    }
}
