package com.koron.common.web.mapper;


import com.koron.web.systemmanger.dictionary.bean.Parameter;
import com.koron.web.systemmanger.dictionary.bean.Region;

import java.util.List;

public interface DictionaryMapper {
	public List<Region> regionList(String orgId);
	
	public void regionUpdate(Region region);
	
	public void regionDelete(Region region);
	
	public void regionAdd(Region region);
	
	public List<Parameter> paramList(Region region);
	
	public List<Parameter> paramListByCode(String regionCode);
	
	public void paramUpdate(Parameter param);
	
	public void paramDelete(Parameter param);
	
	public void paramDeleteByDictId(String dictId);
	
	public void paramAdd(Parameter param);
}
