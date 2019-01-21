package com.molly.dao;

import java.util.List;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import com.molly.bean.FixBean;
import com.molly.bean.RoomBean;


public interface IFixDao {
	@Insert("INSERT INTO t_fix VALUES (DEFAULT,#{fcontent},#{ftime},'未处理',DEFAULT,#{rid})")
	public int insertFix(FixBean fixBean);
	
	
	@Select("SELECT * FROM t_fix  WHERE fflag=0 and rid=#{rid}")
	public List<FixBean> findFixsByRid(RoomBean roomBean); 
	
	
	@Update("UPDATE t_fix SET fstate='已处理' WHERE fid=#{fid} and fflag=0")
	public int deleteFix(FixBean fixBean);
	
	
	@Select("SELECT COUNT(*) FROM t_fix  WHERE fstate='未处理' AND fflag=0 AND rid=#{rid}")
	public int findFixCountsByRid(int rid); 
	
	@Select("SELECT * FROM t_fix  WHERE fflag=0 and fid=#{fid}")
	public FixBean findFixByFix(FixBean fixBean);
}
