package com.molly.dao;

import java.util.List;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import com.molly.bean.UserBean;
import com.molly.util.PageUtil;

public interface IUserDao {
	
	
	@Insert("INSERT INTO t_user VALUES (DEFAULT,#{uname},#{upwd},'none',DEFAULT,#{uphone},DEFAULT)")
	public int insertUserBean(UserBean userBean);
	
	@Select("select * from t_user where uname=#{uname} AND uflag=0")
	public UserBean findByUname(String uname);
	
	@Select("SELECT *  FROM t_user  WHERE roles!='superadmin' AND uflag=0  LIMIT #{index},#{pageSize}")
	public List<UserBean> showThisUsers(PageUtil util); 
	
	@Select("SELECT *  FROM t_user  WHERE uid=#{uid} AND uflag=0")
	public UserBean findUserByUid(UserBean userBean);
	
	@Update("UPDATE t_user SET roles=#{roles} WHERE uid=#{uid} AND uflag=0")
	public int updateUser(UserBean userBean);
	
	
	@Update("UPDATE t_user SET uflag=-1 WHERE uid=#{uid}")
	public int deleteUser(UserBean userBean);
	
	@Select("select COUNT(*)  from t_user where roles!='superadmin' AND uflag=0")
	public int findAllCount();
	
	@Select("SELECT * FROM t_user  WHERE roles!='superadmin' AND uname like #{userBean.uname}  AND  roles like #{userBean.roles} AND uflag=0 LIMIT #{pageUtil.index},#{pageUtil.pageSize}")
	public List<UserBean> findUsersByUser(@Param("userBean")UserBean userBean,@Param("pageUtil")PageUtil pageUtil);
	
	
	@Select("SELECT COUNT(*) FROM t_user  WHERE roles!='superadmin' AND uname like #{userBean.uname}  AND  roles like #{userBean.roles} AND uflag=0")
	public int findAllUsersByUser(@Param("userBean")UserBean userBean);
}
