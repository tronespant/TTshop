package com.taobao.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.moc.common.pojo.EasyUITreeNode;
import com.moc.mapper.TbItemCatMapper;
import com.moc.pojo.TbItemCatExample;
import com.moc.pojo.TbItemCatExample.Criteria;
import com.taobao.service.ItemCatService;
import com.moc.pojo.TbItemCat;

@Service
public class ItemCatServiceImpl implements ItemCatService {

	@Autowired
	TbItemCatMapper mapper;
	@Override
	public List<EasyUITreeNode> getItemCatList(long parent_id) {
		
		TbItemCatExample example=new TbItemCatExample();
		Criteria cri=example.createCriteria();
		cri.andParentIdEqualTo(parent_id);
		List<TbItemCat> list=mapper.selectByExample(example);
		 
		List<EasyUITreeNode> tree=new ArrayList<EasyUITreeNode>();
		
		for(TbItemCat cat:list){
			EasyUITreeNode node=new EasyUITreeNode();
			node.setId(cat.getId());
			/*if(cat.getStatus()==1)
			node.setState("正常");
			if(cat.getStatus()==2)
				node.setState("删除");*/
			node.setState(cat.getIsParent()?"closed":"spen");
			node.setText(cat.getName());
			tree.add(node);
		}
		
		return tree;
	}

}
