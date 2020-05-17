package com.zx.dao;

import java.util.Vector;

import com.zx.vo.ProListVo;
/**
 * 
 * @author 彭文大帅比
 *
 */
public interface ProListDao {
//	获取项目清单
	public Vector getProList() ;

//	通过id或者name模糊查询
	public Vector findProLikeProIdOrName(String pro_name);
	
	//	通过id获取项目
	public void addByProId(String id,int acount);
	
	//根据id获取库存
	public Object getStockById(String id);
	
	//根据id修改项目库存
	int updateStockById(String id, int stock);
	
	//添加订单表记录
	int insertOrderTable(ProListVo proListVo) ;
	
	//向项目清单记录表添加记录
	int insertOddproject(ProListVo proListVo);
	
}
