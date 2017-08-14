package com.taobao.service;

import com.moc.common.pojo.EasyUIDataGridResult;
import com.moc.pojo.TbItem;
import com.moc.utils.TaotaoResult;

public interface ItemService {
	TbItem getItem(Long id);
	//page为当前页码 以及 当前页码查询数量
	EasyUIDataGridResult getItemList(int page,int rows);
	
	TaotaoResult addItem(TbItem tbItem,String desc);

}
