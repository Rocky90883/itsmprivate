
package com.koron.web.systemmanger.dictionary;

import com.koron.util.Constant;
import com.koron.util.Tools;
import com.koron.web.systemmanger.dictionary.bean.DictionaryTree;
import com.koron.web.systemmanger.dictionary.bean.Parameter;
import com.koron.web.systemmanger.dictionary.bean.Region;
import org.apache.log4j.Logger;
import org.koron.ebs.mybatis.SessionFactory;
import org.koron.ebs.mybatis.TaskAnnotation;
import org.springframework.stereotype.Service;
import org.swan.bean.MessageBean;

import java.util.ArrayList;
import java.util.List;


@Service
public class DictionaryService {
	private static Logger log = Logger.getLogger(DictionaryService.class);
	
	@TaskAnnotation("regionList")
	public List<Region> regionList(SessionFactory sessionFactory, String orgId) {
		List<Region> regionList;
		try {
			DictionaryMapper dictionaryMapper = sessionFactory.getMapper(DictionaryMapper.class);
			
			regionList = dictionaryMapper.regionList(orgId);
			
		} catch (Exception e) {
			
			throw new RuntimeException(e.getMessage());
		}
		return regionList;
	}
	
	@TaskAnnotation("regionAdd")
	public void  regionAdd(SessionFactory sessionFactory,Region region) {
		try {
			DictionaryMapper dictionaryMapper = sessionFactory.getMapper(DictionaryMapper.class);
			dictionaryMapper.regionAdd(region);
			
		}catch(Exception e) {
			log.error("添加组失败", e);
			throw new RuntimeException("添加组失败");
		}
	}
	

	
	@TaskAnnotation("regionUpdate")
	public void regionUpdate(SessionFactory sessionFactory,Region region) {
		
		try {
			DictionaryMapper dictionaryMapper = sessionFactory.getMapper(DictionaryMapper.class);
			dictionaryMapper.regionUpdate(region);
		
	
		} catch (Exception e) {
			
			log.error("修改组信息失败", e);
			throw new RuntimeException("修改组信息失败");
		}

	}
	
	@TaskAnnotation("regionDelete")
	public void regionDelete(SessionFactory sessionFactory,Region region) {
		
		try {
			DictionaryMapper dictionaryMapper = sessionFactory.getMapper(DictionaryMapper.class);
			dictionaryMapper.regionDelete(region);
			dictionaryMapper.paramDeleteByDictId(region.getId());
	
		} catch (Exception e) {
			
			log.error("删除组信息失败", e);
			throw new RuntimeException("删除组信息失败");
		}

	}
	
	@TaskAnnotation("paramList")
	public List<Parameter> paramList(SessionFactory sessionFactory, Region region) {
		List<Parameter> paramList;
		try {
			DictionaryMapper dictionaryMapper = sessionFactory.getMapper(DictionaryMapper.class);
			
			paramList = dictionaryMapper.paramList(region);
			
		} catch (Exception e) {
			
			throw new RuntimeException(e.getMessage());
		}
		return paramList;
	}
	
	@TaskAnnotation("paramAdd")
	public void  paramAdd(SessionFactory sessionFactory,Parameter param) {
		try {
			DictionaryMapper dictionaryMapper = sessionFactory.getMapper(DictionaryMapper.class);
			dictionaryMapper.paramAdd(param);
			
		}catch(Exception e) {
			log.error("添加参数失败", e);
			throw new RuntimeException("添加参数失败");
		}
	}
	

	
	@TaskAnnotation("paramUpdate")
	public void paramUpdate(SessionFactory sessionFactory,Parameter param) {
		
		try {
			DictionaryMapper dictionaryMapper = sessionFactory.getMapper(DictionaryMapper.class);
			dictionaryMapper.paramUpdate(param);
		
	
		} catch (Exception e) {
			
			log.error("修改参数信息失败", e);
			throw new RuntimeException("修改参数信息失败");
		}

	}
	
	@TaskAnnotation("paramDelete")
	public void paramDelete(SessionFactory sessionFactory,Parameter param) {
		try {
			DictionaryMapper dictionaryMapper = sessionFactory.getMapper(DictionaryMapper.class);
			dictionaryMapper.paramDelete(param);
		} catch (Exception e) {
			
			log.error("删除参数信息失败", e);
			throw new RuntimeException("删除参数信息失败");
		}
	}


	@TaskAnnotation("getDictionaryTree")
	public List<DictionaryTree> getDictionaryTree(SessionFactory sessionFactory,String orgId) {
		try {
			DictionaryMapper dictionaryMapper = sessionFactory.getMapper(DictionaryMapper.class);
			List<Region> regions = dictionaryMapper.regionList(orgId);

			List<DictionaryTree> treeList =new ArrayList<>();
			regions.stream().forEach( rg->{
				DictionaryTree tree = new DictionaryTree();
				tree.setId(rg.getId());
				tree.setOrgId(rg.getOrgId());
				tree.setRegionCode(rg.getRegionCode());
				tree.setRegionName(rg.getRegionName());
				List<Parameter> paramList = Tools.getParamList(sessionFactory, rg.getRegionCode());
				tree.setChildren(paramList);
				treeList.add(tree);
			});

			return treeList;
		} catch (Exception e) {

			log.error("获取字典树失败", e);
			throw new RuntimeException("获取字典树失败");
		}
	}

//	@TaskAnnotation("paramListByCode")
//	public void paramListByCode(SessionFactory sessionFactory,Parameter param) {
//		try {
//			Tools.getParamList();
//		} catch (Exception e) {
//
//			log.error("删除参数信息失败", e);
//			throw new RuntimeException("删除参数信息失败");
//		}
//	}
}
