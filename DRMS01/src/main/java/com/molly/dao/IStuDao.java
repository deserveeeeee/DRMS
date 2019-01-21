package com.molly.dao;

import java.util.List;

import javax.rmi.CORBA.Stub;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Many;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.One;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import com.molly.bean.ClaBean;
import com.molly.bean.RoomBean;
import com.molly.bean.StuBean;
import com.molly.util.PageUtil;


public interface IStuDao {

	@Select("SELECT count(*)  FROM t_student WHERE cid=#{cid} AND sflag=0")
	public int findStudentsByCid(ClaBean cla);
	
	@Select("SELECT * FROM v_stu WHERE rid=#{rid}")
	@Results({
		@Result(id=true,column="sid",property="sid"),
		@Result(column="sname",property="sname"),
		@Result(column="sgender",property="sgender"),
		@Result(column="sphone",property="sphone"),
		@Result(column="imgs",property="imgs"),
		@Result(column="stime",property="stime"),
		@Result(column="cid",property="cla.cid"),
		@Result(column="cname",property="cla.cname"),
		@Result(column="rid",property="room.rid"),
		@Result(column="raddress",property="room.raddress"),
	})
	public List<StuBean> findStudentsByRid(int rid);
	
	@Select("SELECT * FROM v_stu LIMIT #{index},#{pageSize}")
	@Results({
		@Result(id=true,column="sid",property="sid"),
		@Result(column="sname",property="sname"),
		@Result(column="sgender",property="sgender"),
		@Result(column="sphone",property="sphone"),
		@Result(column="imgs",property="imgs"),
		@Result(column="stime",property="stime"),
		@Result(column="cid",property="cla.cid"),
		@Result(column="cname",property="cla.cname"),
		@Result(column="rid",property="room.rid"),
		@Result(column="raddress",property="room.raddress"),
	})
	public List<StuBean> showThisStus(PageUtil util); 
	
	
	@Select("select COUNT(*)  from t_student where sflag=0")
	public int findAllCount();
	
	@Insert("INSERT INTO t_student VALUES (DEFAULT,#{sname},#{sgender},#{sphone},#{cla.cid},#{room.rid},DEFAULT,#{imgs},#{stime})")
	public int insertStu(StuBean stuBean);
	
	@Update("UPDATE t_student SET sflag=-1 WHERE sid=#{sid}")
	public int deleteStu(StuBean stuBean);
	
	@Update("UPDATE t_student SET rid=#{roomBean.rid} WHERE sid=#{stuBean.sid} AND sflag=0")
	public int updateStuRoom(@Param("stuBean")StuBean stuBean,@Param("roomBean")RoomBean roomBean);
	
	@Select("SELECT *  FROM v_stu  WHERE sid=#{sid}")
	@Results({
		@Result(id=true,column="sid",property="sid"),
		@Result(column="sname",property="sname"),
		@Result(column="sgender",property="sgender"),
		@Result(column="sphone",property="sphone"),
		@Result(column="imgs",property="imgs"),
		@Result(column="stime",property="stime"),
		@Result(column="cid",property="cla.cid"),
		@Result(column="cname",property="cla.cname"),
		@Result(column="rid",property="room.rid"),
		@Result(column="raddress",property="room.raddress"),
	})
	public StuBean findStuBySid(StuBean stuBean);
	
	
	
	
	@Select("SELECT *  FROM v_stu  WHERE raddress like #{stuBean.room.raddress}  AND  sname like #{stuBean.sname}  AND cname like #{stuBean.cla.cname} LIMIT #{pageUtil.index},#{pageUtil.pageSize}")
	@Results({
		@Result(id=true,column="sid",property="sid"),
		@Result(column="sname",property="sname"),
		@Result(column="sgender",property="sgender"),
		@Result(column="sphone",property="sphone"),
		@Result(column="imgs",property="imgs"),
		@Result(column="stime",property="stime"),
		@Result(column="cid",property="cla.cid"),
		@Result(column="cname",property="cla.cname"),
		@Result(column="rid",property="room.rid"),
		@Result(column="raddress",property="room.raddress"),
	})
	public List<StuBean> findStuByStuBean(@Param("stuBean")StuBean stuBean,@Param("pageUtil")PageUtil pageUtil);
	
	
	
	
	@Select("select COUNT(*) FROM v_stu  WHERE raddress like #{stuBean.room.raddress}  AND  sname like #{stuBean.sname}  AND cname like #{stuBean.cla.cname}")
	public int searchCount(@Param("stuBean")StuBean stuBean);
}
