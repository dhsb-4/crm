package com.t248.crm.config.shiro;

import com.t248.crm.entity.Right;
import com.t248.crm.entity.Role;
import com.t248.crm.entity.SysRoleRight;
import com.t248.crm.entity.User;
import com.t248.crm.service.user.RightService;
import com.t248.crm.service.user.RoleService;
import com.t248.crm.service.user.SysRoleRightService;
import com.t248.crm.service.user.UserService;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.util.ByteSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.core.ValueOperations;

import javax.annotation.Resource;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class MyShiroRealm extends AuthorizingRealm {

    @Resource
    private UserService userService;

    @Resource
    private RoleService roleService;

    @Resource
    private RightService rightService;

    @Resource
    private SysRoleRightService sysRoleRightService;

    @Autowired
    private StringRedisTemplate stringRedisTemplate;

    //用户登录参数计数  redisKey前缀
    private String SHIRO_LOGIN_COUNT = "shiro_login_count_";

    //用户登录是否锁定      一个小时redisKey
    private String SHIRO_IS_LOCK = "shiro_is_lock_";

    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        System.out.println("权限认证-->MyShiroRealm.doGetAutorizationInfo()");
        //授权信息
        SimpleAuthorizationInfo authenticationInfo = new SimpleAuthorizationInfo();

        //设置权限，如果用户没有相应权限
        //会访问ShirConfig过滤器中shiroFilter方法配置的未授权路径
        //将来这里应该是通过数据库动态授权
        // authenticationInfo.addStringPermission("用户管理");

        //authenticationInfo.addStringPermission("开发中");
        // authenticationInfo.addStringPermission("已归档");

        User user = (User) principalCollection.getPrimaryPrincipal();
        for (SysRoleRight sysRoleRight : user.getRole().getSysRoleRights()) {
            System.out.println("用户授权的权限：" + sysRoleRight.getRight().getRightText());
            authenticationInfo.addStringPermission(sysRoleRight.getRight().getRightText());
        }

        return authenticationInfo;
    }

    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {
        System.out.println("身份证：MyShiroRealm.doGetAuthenticationInfo()");
        //获取用户的输入的账户
        UsernamePasswordToken token = (UsernamePasswordToken) authenticationToken;
        String usrName = token.getUsername();
        String usrPassword = new String(token.getPassword());
        System.out.println("usrName:" + usrName);
        System.out.println("usrPasswrod:" + usrPassword);
        //通过username从数据库中查找User对象,如果找到，没找到。
        //实际项目中，这里可以根据实际情况做缓存，如果不做，Shiro自己也是有时间间隔机制，2分钟内不会重复执行该方法
        ValueOperations<String, String> operations = stringRedisTemplate.opsForValue();
        operations.increment(SHIRO_LOGIN_COUNT + usrName, 1);
        if (Integer.parseInt(operations.get(SHIRO_LOGIN_COUNT + usrName)) > 5) {
            operations.set(SHIRO_IS_LOCK + usrName, "LOCK");
            stringRedisTemplate.expire(SHIRO_IS_LOCK + usrName, 1, TimeUnit.HOURS);
        }
        if ("LOCK".equals(operations.get(SHIRO_IS_LOCK + usrName))) {
            throw new DisabledAccountException("由于输入错误次数大于5次，账号1小时内已经禁止登录！");
        }


        User user = userService.getUser(usrName);
        System.out.println("----->>user" + user);
        if (user == null) {
            throw new UnknownAccountException("账号不存在！");
        }/*else if (!user.getUsrPassword().equals(usrPassword)){
            throw new IncorrectCredentialsException("密码不正确！");
        }*/

        Role role = roleService.getRoleByUser(user);
        List<SysRoleRight> rights = sysRoleRightService.findSysList(role);
        role.getSysRoleRights().addAll(rights);
        user.setRole(role);

        //认证信息
        SimpleAuthenticationInfo authenticationInfo = new SimpleAuthenticationInfo(
                user,
                user.getUsrPassword(),
                ByteSource.Util.bytes("salt"),
                getName()
        );

        //清空登录计数
        operations.set(SHIRO_LOGIN_COUNT + usrName, "0");
        return authenticationInfo;
    }
}
