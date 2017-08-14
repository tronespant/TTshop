package com.taobao.service.impl;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.moc.common.pojo.EasyUIDataGridResult;
import com.moc.mapper.TbItemDescMapper;
import com.moc.mapper.TbItemMapper;
import com.moc.pojo.TbItem;
import com.moc.pojo.TbItemDesc;
import com.moc.pojo.TbItemQuery;
import com.moc.utils.IDUtils;
import com.moc.utils.TaotaoResult;
import com.taobao.service.ItemService;
//商品管理服务
@Service
public class ItemServiceImpl implements ItemService {

	@Autowired
	private TbItemMapper itemMpper;
	@Autowired
	private TbItemDescMapper tbItemDeseMapper;
	@Override
	public TbItem getItem(Long id) {
		TbItem item=itemMpper.selectByPrimaryKey(id);
		return item;
	}
	
	@Override
	public EasyUIDataGridResult getItemList(int page, int rows) {
		
		PageHelper.startPage(page,rows);
		
		 ApplicationContext applicationContext=new ClassPathXmlApplicationContext("classpath:spring/applicationContext-dao.xml");
		    
	     TbItemMapper tbitem=applicationContext.getBean(TbItemMapper.class);
	     
		TbItemQuery example=new TbItemQuery();
		
		List<TbItem> list=tbitem.selectByExample(example);
		/*List<TbItem> list=itemMpper.selectByExample(example);*/
		PageInfo<TbItem> pageInfo=new PageInfo<>(list);
		EasyUIDataGridResult result=new EasyUIDataGridResult();
		result.setRows(list);
		result.setTotal(pageInfo.getTotal());
		
		return result;
	}

	@Override
	public TaotaoResult addItem(TbItem tbItem, String desc) {
		
		//添加商品ID
	   long id=IDUtils.genItemId();
	   tbItem.setId(id);
	   System.out.println(id);
	   //1.正常 2.下架3.删除
	   tbItem.setStatus((byte) 1);
	   tbItem.setCreated(new Date());
	   tbItem.setUpdated(new Date());
	   itemMpper.insert(tbItem);
	   TbItemDesc tbDesc=new TbItemDesc();
	   tbDesc.setItemId(id);
	   tbDesc.setItemDesc(desc);
	   tbDesc.setCreated(new Date());
	   tbDesc.setUpdated(new Date());
	   tbItemDeseMapper.insert(tbDesc);
		return TaotaoResult.ok();
	}

}
