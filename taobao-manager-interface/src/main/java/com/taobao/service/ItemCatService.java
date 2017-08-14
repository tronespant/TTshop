package com.taobao.service;

import java.util.List;

import com.moc.common.pojo.EasyUITreeNode;

public interface ItemCatService {
	
	List<EasyUITreeNode> getItemCatList(long parent_id);

}
