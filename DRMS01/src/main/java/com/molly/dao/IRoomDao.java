package com.molly.dao;

import java.util.List;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Many;
import org.apache.ibatis.annotations.Param;

import com.molly.bean.ClaBean;
import com.molly.bean.RoomBean;
import com.molly.util.PageUtil;

public interface IRoomDao {
	
	
	@Select("SELECT * FROM t_room  WHERE rflag=0  LIMIT #{index},#{pageSize}")
	public List<RoomBean> showThisRooms(PageUtil util); 
	
	
	@Select("SELECT * FROM t_room  WHERE rflag=0 AND rhave>0")
	public List<RoomBean> findAllRooms(); 
	
	
	@Select("select COUNT(*)  from t_room where rflag=0")
	public int findAllCount();
	
	@Insert("INSERT INTO t_room VALUES (DEFAULT,DEFAULT,#{rplan},#{raddress},#{rcap},DEFAULT,#{rlandlord},#{rphone},#{rtype},#{rpayment},#{rtime},#{rstate},#{rmoney},#{rcap})")
	public int insertRoom(RoomBean roomBean);
	
	@Update("UPDATE t_room SET rflag=-1 WHERE rid=#{rid}")
	public int deleteRoom(RoomBean roomBean);
	
	
	@Select("SELECT *  FROM t_room  WHERE rid=#{rid} AND rflag=0")
	@Results({
		@Result(id=true,column="rid",property="rid"),
		@Result(column="rplan",property="rplan"),
		@Result(column="raddress",property="raddress"),
		@Result(column="rcap",property="rcap"),
		@Result(column="rused",property="rused"),
		@Result(column="rhave",property="rhave"),
		@Result(column="rlandlord",property="rlandlord"),
		@Result(column="rphone",property="rphone"),
		@Result(column="rtype",property="rtype"),
		@Result(column="rpayment",property="rpayment"),
		@Result(column="rtime",property="rtime"),
		@Result(column="rstate",property="rstate"),
		@Result(column="rmoney",property="rmoney"),
		@Result(column="rid",property="students",
			many=@Many(
				select="com.molly.dao.IStuDao.findStudentsByRid"
			)
		)
	})
	public RoomBean findRoomByRid(RoomBean roomBean);
	
	
	
	@Select("SELECT *  FROM t_room  WHERE raddress like #{roomBean.raddress}  AND  rstate like #{roomBean.rstate}  AND rtype like #{roomBean.rtype} AND rflag=0 LIMIT #{pageUtil.index},#{pageUtil.pageSize}")
	public List<RoomBean> findRoomByRoomAll(@Param("roomBean")RoomBean roomBean,@Param("pageUtil")PageUtil pageUtil);
	
	@Select("select COUNT(*)  from t_room WHERE raddress like #{roomBean.raddress}  AND  rstate like #{roomBean.rstate}  AND rtype like #{roomBean.rtype} AND rflag=0")
	public int searchCountAll(@Param("roomBean")RoomBean roomBean);
	
	@Select("SELECT *  FROM t_room  WHERE rhave>0 AND raddress like #{roomBean.raddress}  AND  rstate like #{roomBean.rstate}  AND rtype like #{roomBean.rtype} AND rflag=0 LIMIT #{pageUtil.index},#{pageUtil.pageSize}")
	public List<RoomBean> findRoomByRoomAva(@Param("roomBean")RoomBean roomBean,@Param("pageUtil")PageUtil pageUtil);
	
	@Select("select COUNT(*)  from t_room WHERE rhave>0 AND raddress like #{roomBean.raddress}  AND  rstate like #{roomBean.rstate}  AND rtype like #{roomBean.rtype} AND rflag=0")
	public int searchCountAva(@Param("roomBean")RoomBean roomBean);
	
	@Update("UPDATE t_room SET rhave=rhave-1,rused=rused+1  WHERE rid=#{rid} and rflag=0")
	public int addRused(RoomBean roomBean);
	
	
	@Update("UPDATE t_room SET rhave=rhave+1,rused=rused-1  WHERE rid=#{rid} and rflag=0")
	public int reRused(RoomBean roomBean);
	
	
	@Select("SELECT * FROM t_room  WHERE rflag=0")
	public List<RoomBean> showAllRooms(); 
	
	
	@Select("UPDATE t_room SET rstate='正常'  WHERE rid=#{rid} AND rflag=0")
	public void updateRoomByRid(int rid);
	
	
	@Select("UPDATE t_room SET rstate='设施损坏'  WHERE rid=#{rid} AND rflag=0")
	public void badRoomByRid(int rid);
}
