package com.molly.filters;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

import org.apache.shiro.subject.Subject;
import org.apache.shiro.web.filter.authz.AuthorizationFilter;

public class AuthFilter extends AuthorizationFilter{

	@Override
	protected boolean isAccessAllowed(ServletRequest request, ServletResponse response, Object mappingValues) throws Exception {
//		获取subject对象
		Subject subject = getSubject(request, response);
//		获取到用户设置角色信息
		String [] roles = (String[]) mappingValues;
//		设置角色之间的关系为或者
		if (roles == null || roles.length == 0) {
			return true;
		}
		for (String string : roles) {
			if (subject.hasRole(string)) {
				return true;
			}
		}
		return false;
	}
 
}
