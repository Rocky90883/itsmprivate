package com.koron.web.systemmanger.dictionary;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import com.koron.util.Tools;
import com.koron.web.systemmanger.dictionary.bean.DictionaryTree;
import com.koron.web.systemmanger.dictionary.bean.Parameter;
import com.koron.web.systemmanger.dictionary.bean.Region;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.log4j.Logger;
import org.bson.types.ObjectId;
import org.koron.ebs.mybatis.ADOConnection;
import org.koron.ebs.mybatis.ADOSessionImpl;
import org.koron.ebs.mybatis.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.swan.bean.MessageBean;
import com.koron.util.Constant;

@RestController

@Api(tags = "数据字典")
@RequestMapping("/systemmanger/dictionaryAction/")
public class DictionaryAction {

	private static Logger log = Logger.getLogger(DictionaryAction.class);
	@Autowired
	private DictionaryService dictionaryService;

    @ApiOperation("---查询组列表")
	@PostMapping(value = "/regionList.htm")
	public MessageBean<?> regionDownList(@RequestBody Region regionBean) {

		@SuppressWarnings("rawtypes")
		MessageBean<List> info = MessageBean.create(Constant.MESSAGE_INT_SUCCESS, "success", List.class);
		try {
			@SuppressWarnings("unchecked")
			List<Region> cdList = ADOConnection.runTask("_default", dictionaryService, "regionList",
					List.class,regionBean.getOrgId());

			info.setData(cdList);
		} catch (Exception e) {
			log.error("查询组列表失败", e);
			info.setCode(Constant.MESSAGE_INT_OPERATION);
			info.setDescription("fail " + e.getMessage());
			return info;
		}
		return info;
	}

    @ApiOperation("---添加组")
	@PostMapping(value="/regionAdd.htm")
	public MessageBean<?> regionAdd(@RequestBody Region region,HttpServletRequest req) {
		try {
//			Region region = JsonUtils.objectToPojo(requestBean.getData(), Region.class);
			String id = new ObjectId().toHexString();
			region.setId(id);
//			region.setOrgId(requestBean.getOrgId());
			ADOConnection.runTask("_default", dictionaryService, "regionAdd", void.class, region);

		} catch (Exception e) {
			log.error("添加组失败", e);
			return MessageBean.create(Constant.MESSAGE_INT_OPERATION, "添加组失败", void.class);
		}
		return MessageBean.create(Constant.MESSAGE_INT_SUCCESS, "添加组成功", void.class);
	}

    @ApiOperation("---修改组")
	@PostMapping(value="/regionUpdateSysModelBean.htm")
	public MessageBean<?> regionUpdate(@RequestBody Region region,HttpServletRequest req) {
		try {
//			Region region = JsonUtils.objectToPojo(requestBean.getData(), Region.class);
			ADOConnection.runTask("_default", dictionaryService, "regionUpdate", void.class, region);

		}catch (Exception e) {
			log.error("修改组信息失败", e);
			return MessageBean.create(Constant.MESSAGE_INT_OPERATION, "修改组信息失败" + e.getMessage(), void.class);
		}
		return MessageBean.create(Constant.MESSAGE_INT_SUCCESS, "修改成功", void.class);
	}

    @ApiOperation("---删除组")
	@PostMapping(value="/regionDeleteSysModelBean.htm")
	public MessageBean<?> regionDelete(@RequestBody Region region,HttpServletRequest req) {
		try {
//			Region region = JsonUtils.objectToPojo(requestBean.getData(), Region.class);
			ADOConnection.runTask("_default", dictionaryService, "regionDelete", void.class, region);

		}catch (Exception e) {
			log.error("删除组信息失败", e);
			return MessageBean.create(Constant.MESSAGE_INT_OPERATION, "删除组信息失败" + e.getMessage(), void.class);
		}
		return MessageBean.create(Constant.MESSAGE_INT_SUCCESS, "删除成功", void.class);
	}

    @ApiOperation("---查询参数列表")
	@PostMapping(value = "/paramListSysModelBean.htm")
	public MessageBean<?> paramList(@RequestBody Region region,HttpServletRequest req) {

		@SuppressWarnings("rawtypes")
		MessageBean<List> info = MessageBean.create(Constant.MESSAGE_INT_SUCCESS, "success", List.class);
		try {
//			Region region = JsonUtils.objectToPojo(requestBean.getData(), Region.class);
			@SuppressWarnings("unchecked")
			List<Region> cdList = ADOConnection.runTask("_default",new DictionaryService(), "paramList",
					List.class,region);

			info.setData(cdList);
		} catch (Exception e) {
			log.error("查询参数列表失败", e);
			info.setCode(Constant.MESSAGE_INT_OPERATION);
			info.setDescription("fail " + e.getMessage());
			return info;
		}
		return info;
	}

    @ApiOperation("---添加参数")
	@PostMapping(value="/paramAddSysModelBean.htm")
	public MessageBean<?> paramAdd(@RequestBody Parameter parameter, HttpServletRequest req) {
		try {
//			Parameter parameter = JsonUtils.objectToPojo(requestBean.getData(), Parameter.class);
			String id = new ObjectId().toHexString();
			parameter.setId(id);
			ADOConnection.runTask("_default", dictionaryService, "paramAdd", void.class, parameter);

		} catch (Exception e) {
			log.error("添加参数失败", e);
			return MessageBean.create(Constant.MESSAGE_INT_OPERATION, "添加参数失败", void.class);
		}
		return MessageBean.create(Constant.MESSAGE_INT_SUCCESS, "添加参数成功", void.class);
	}

    @ApiOperation("---修改参数信息")
	@PostMapping(value="/paramUpdateSysModelBean.htm")
	public MessageBean<?> paramUpdate(@RequestBody Parameter param,HttpServletRequest req) {
		try {
//			Parameter param = JsonUtils.objectToPojo(requestBean.getData(), Parameter.class);
			ADOConnection.runTask("_default", dictionaryService, "paramUpdate", void.class, param);

		}catch (Exception e) {
			log.error("修改参数信息失败", e);
			return MessageBean.create(Constant.MESSAGE_INT_OPERATION, "修改参数信息失败" + e.getMessage(), void.class);
		}
		return MessageBean.create(Constant.MESSAGE_INT_SUCCESS, "修改成功", void.class);
	}

    @ApiOperation("---删除参数信息")
	@PostMapping(value="/paramDeleteSysModelBean.htm")
	public MessageBean<?> paramDelete(@RequestBody Parameter param,HttpServletRequest req) {
		try {
//			Parameter param = JsonUtils.objectToPojo(requestBean.getData(), Parameter.class);
			ADOConnection.runTask("_default", dictionaryService, "paramDelete", void.class, param);

		}catch (Exception e) {
			log.error("删除参数信息失败", e);
			return MessageBean.create(Constant.MESSAGE_INT_OPERATION, "删除参数信息失败" + e.getMessage(), void.class);
		}
		return MessageBean.create(Constant.MESSAGE_INT_SUCCESS, "删除成功", void.class);
	}



	@ApiOperation("---根据code查询参数列表")
	@PostMapping(value = "/paramListByCodeSysModelBean.htm")
	public MessageBean<?> paramListByCode(@RequestBody Region region,HttpServletRequest req) {

		@SuppressWarnings("rawtypes")
		MessageBean<List> info = MessageBean.create(Constant.MESSAGE_INT_SUCCESS, "success", List.class);
		try {
//			Region region = JsonUtils.objectToPojo(requestBean.getData(), Region.class);
            SessionFactory factory = new ADOSessionImpl().getSessionFactory();
            List<Parameter> paramList = Tools.getParamList(factory, region.getRegionCode());
            factory.close();
            info.setData(paramList);
		} catch (Exception e) {
			log.error("更具code查询参数列表失败", e);
			info.setCode(Constant.MESSAGE_INT_OPERATION);
			info.setDescription("fail " + e.getMessage());
			return info;
		}
		return info;
	}


	@ApiOperation("---字典树")
	@PostMapping(value = "/getDictionaryTree.htm")
	public MessageBean<?> getDictionaryTree(@RequestBody Region regionBean) {

		@SuppressWarnings("rawtypes")
		MessageBean<List> info = MessageBean.create(Constant.MESSAGE_INT_SUCCESS, "success", List.class);
		try {

			List<DictionaryTree> list = ADOConnection.runTask(new DictionaryService(), "getDictionaryTree", List.class,regionBean.getOrgId());
			info.setData(list);
		} catch (Exception e) {
			log.error("更具code查询参数列表失败", e);
			info.setCode(Constant.MESSAGE_INT_OPERATION);
			info.setDescription("fail " + e.getMessage());
			return info;
		}
		return info;
	}
}
