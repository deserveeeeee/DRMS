package com.molly.dao;

import java.util.List;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import com.molly.bean.ClaBean;
import com.molly.util.PageUtil;

public interface IClassDao {
	
	
	@Insert("INSERT INTO t_class VALUES (DEFAULT,#{cname},#{ctime},#{cteacher},DEFAULT)")
	public int insertClass(ClaBean claBean);
	
	@Update("UPDATE t_class SET cflag=-1 WHERE cid=#{cid}")
	public int deleteClass(ClaBean claBean);
	
	
	@Select("SELECT * FROM t_class  WHERE cflag=0  LIMIT #{index},#{pageSize}")
	public List<ClaBean> showThisClasses(PageUtil util); 
	
	@Select("select COUNT(*)  from t_class where cflag=0")
	public int findAllCount();
	
	@Select("SELECT * FROM t_class  WHERE cflag=0")
	public List<ClaBean> showAllClasses(); 
	
	
	@Select("SELECT *  FROM t_class  WHERE cid=#{cid} AND cflag=0")
	public ClaBean findClassByCid(ClaBean claBean);
	
	@Select("SELECT * FROM t_class  WHERE cflag=0")
	public List<ClaBean> findAllClasses();
	
}
