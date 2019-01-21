package com.molly.shiro;

import java.util.HashSet;
import java.util.Set;

import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.util.ByteSource;
import org.springframework.beans.factory.annotation.Autowired;

import com.molly.bean.UserBean;
import com.molly.service.IUserService;

public class CustomRealm extends AuthorizingRealm {
	
	@Autowired
	private IUserService userService;
	@Override
	protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
//		通过用户名查询当前用户的所有角色
		Set<String> roles = new HashSet<String>();
		System.out.println(roles.isEmpty());
		String name = principals.toString();
		UserBean userBean = userService.login(name);
		roles.add(userBean.getRoles());
		SimpleAuthorizationInfo info = new SimpleAuthorizationInfo();
		info.setRoles(roles);
		return info;
	}

	@Override
	protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
		String name = token.getPrincipal().toString();
		System.out.println(name);
		UserBean userBean = userService.login(name);
		ByteSource by = ByteSource.Util.bytes(userBean.getUname());
		AuthenticationInfo info = new SimpleAuthenticationInfo(userBean.getUname(), userBean.getUpwd(), by, getName());
		return info;
	}

}
