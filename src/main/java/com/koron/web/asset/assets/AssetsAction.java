package com.koron.web.asset.assets;

import cn.afterturn.easypoi.excel.ExcelImportUtil;
import cn.afterturn.easypoi.excel.entity.ImportParams;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.koron.common.authentication.NoAuth;
import com.koron.util.CommonUtil;
import com.koron.util.Constant;
import com.koron.util.ExportExcelUtil;
import com.koron.web.asset.assets.assetsrepdet.bean.AssetsRepdetBean;
import com.koron.web.asset.assets.assetsrepdet.bean.AssetsRepdetDto;
import com.koron.web.asset.assets.attributes.AttributesBean;
import com.koron.web.asset.assets.bean.AssetsBean;
import com.koron.web.asset.assets.bean.AssetsQueryBean;
import com.koron.web.asset.assets.bean.ImportAssetBean;
import com.koron.web.asset.assets.bean.ImportAssetErrBean;
import com.koron.web.meeting.ImportMeetingErrBean;
import com.koron.web.meeting.MeetingBean;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;
import org.koron.ebs.mybatis.ADOConnection;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.swan.bean.MessageBean;

import javax.servlet.http.HttpServletResponse;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;

@Api(tags = "IT资产-资产台账")
@RequestMapping("/asset/assetsAction")
@RestController
public class AssetsAction {

    private static Logger log = Logger.getLogger(AssetsAction.class);


    @ApiOperation("--资产台账-添加")
    @ResponseBody
    @PostMapping(value = "/addAssets")
    public MessageBean<?> addAssets(@RequestBody AssetsBean bean){

        @SuppressWarnings("rawtypes")
        MessageBean<List> success = MessageBean.create(Constant.MESSAGE_INT_SUCCESS, "success", List.class);
        try {
            if(StringUtils.isBlank(bean.getAssetTypeId())){
                return MessageBean.create(Constant.MESSAGE_INT_PARAMS, "分类id不能为空", void.class);
            }
            if(StringUtils.isBlank(bean.getGoodsModel())){
                return MessageBean.create(Constant.MESSAGE_INT_PARAMS, "资产型号不能为空", void.class);
            }

            success = ADOConnection.runTask(new AssetsService(), "addAssets",MessageBean.class,bean);
        } catch (Exception e) {
            log.error("添加资产台账-异常", e);
            success.setCode(Constant.MESSAGE_INT_OPERATION);
            success.setDescription("添加资产台账-异常");
            return success;
        }
        return success;
    }


    @ApiOperation("--资产台账-列表")
    @ResponseBody
    @PostMapping(value = "/queryList")
    public MessageBean<?> queryList(@RequestBody AssetsQueryBean queryBean){

        @SuppressWarnings("rawtypes")
        MessageBean<PageInfo> info = MessageBean.create(Constant.MESSAGE_INT_SUCCESS, "success", PageInfo.class);
        try {

            PageHelper.startPage(queryBean.getPage(), queryBean.getPageCount());
            List<AssetsBean> list = ADOConnection.runTask(new AssetsService(), "queryList", List.class,queryBean);

            info.setData(new PageInfo<>(list));
        } catch (Exception e) {
            log.error("查询资产台账列表-异常", e);
            info.setCode(Constant.MESSAGE_INT_OPERATION);
            info.setDescription("查询资产台账列表-异常");
            return info;
        }
        return info;
    }
    

    @ApiOperation("--资产台账-修改")
    @ResponseBody
    @PostMapping(value = "/updateAssets")
    public MessageBean<?> updateAssets(@RequestBody AssetsBean bean){

        @SuppressWarnings("rawtypes")
        MessageBean<List> success = MessageBean.create(Constant.MESSAGE_INT_SUCCESS, "success", List.class);
        try {

            success = ADOConnection.runTask(new AssetsService(), "updateAssets",MessageBean.class,bean);
        } catch (Exception e) {
            log.error("更新资产台账-异常", e);
            success.setCode(Constant.MESSAGE_INT_OPERATION);
            success.setDescription("更新资产台账-异常");
            return success;
        }
        return success;
    }

    @ApiOperation("--资产台账-删除")
    @ResponseBody
    @PostMapping(value = "/deleteAssets")
    public MessageBean<?> deleteAssets(@RequestBody AssetsBean bean){

        @SuppressWarnings("rawtypes")
        MessageBean success = MessageBean.create(Constant.MESSAGE_INT_SUCCESS, "success", void.class);
        try {
            success = ADOConnection.runTask(new AssetsService(), "deleteAssets",MessageBean.class,bean.getId());
        } catch (Exception e) {
            log.error("删除资产台账-异常", e);
            success.setCode(Constant.MESSAGE_INT_OPERATION);
            success.setDescription("删除资产台账-异常");
            return success;
        }
        return success;
    }


    @ApiOperation("--资产台账-查询资产属性")
    @ResponseBody
    @PostMapping(value = "/assetAttributes.htm")
    public MessageBean<?> assetAttributes(@RequestParam("id") String id){

        @SuppressWarnings("rawtypes")
        MessageBean<AttributesBean> success = MessageBean.create(Constant.MESSAGE_INT_SUCCESS, "success", AttributesBean.class);
        try {
            AttributesBean rst = ADOConnection.runTask(new AssetsService(), "assetAttributes", AttributesBean.class, id);
            success.setData(rst);

        } catch (Exception e) {
            log.error("查询资产属性-异常", e);
            success.setCode(Constant.MESSAGE_INT_OPERATION);
            success.setDescription("查询资产属性-异常");
            return success;
        }
        return success;
    }


    @ApiOperation("--资产台账-维护记录列表")
    @ResponseBody
    @PostMapping(value = "/queryRepdetList.htm")
    public MessageBean<?> queryRepdetList(@RequestBody AssetsRepdetDto dto){

        @SuppressWarnings("rawtypes")
        MessageBean<PageInfo> info = MessageBean.create(Constant.MESSAGE_INT_SUCCESS, "success", PageInfo.class);
        try {

            PageHelper.startPage(dto.getPage(), dto.getPageCount());
            List<AssetsRepdetBean> list = ADOConnection.runTask(new AssetsService(), "queryRepdetList", List.class,dto);

            info.setData(new PageInfo<>(list));
        } catch (Exception e) {
            log.error("维护记录列表-异常", e);
            info.setCode(Constant.MESSAGE_INT_OPERATION);
            info.setDescription("维护记录列表-异常");
            return info;
        }
        return info;
    }


    /**
     * 下载台账模板
     * @param rsp
     * @throws Exception
     */
    @ApiOperation("---下载台账导入模板")
    @GetMapping(value = "/assetTemplateExport.htm")
    public void assetExport(HttpServletResponse rsp) throws Exception{

        List<ImportAssetBean> list = new LinkedList<ImportAssetBean>();
        ImportAssetBean bean = new ImportAssetBean();
        bean.setDepatName("办公室");
        bean.setStaffunName("张总");
        bean.setBrandName("联想");
        bean.setAssetTypeName("手提电脑");
        bean.setGoodsModel("我是模板电脑");
        bean.setFixedNumber("gd0001");
        bean.setSnNumber("这里是资产序列号");

        bean.setCpu("i7");
        bean.setRam("8G");
        bean.setDisk("500G");
        bean.setWattage("100瓦");
        bean.setPortqty("3个接口");
        bean.setBandwidth("带宽");
        bean.setSpec("5.1寸");
        bean.setEdition("最新版");
        bean.setWarranty("3.5保修");
        list.add(bean);

        try {
            ExportExcelUtil.exportExcel(list,ImportAssetBean.class,"台账数据导入模板",rsp);
//            ExportExcelUtil.exportExcel(list,"abc","sheet1",ImportAssetBean.class,"台账数据导入模板",rsp);
        } catch (Exception e) {
            log.error("台账数据模板导出失败");
            e.printStackTrace();
        }
        return;
    }


    /**
     * 台账数据excel导入
     * @param multipartFile
     * @return
     * @throws Exception
     */
    @NoAuth
    @ApiOperation("---台账数据导入")
    @PostMapping("/assetImport.htm")
    public MessageBean assetImport(@RequestParam("file") MultipartFile multipartFile) throws Exception {
        MessageBean success = MessageBean.create(Constant.MESSAGE_INT_SUCCESS, "success", String.class);

        try {
            ImportParams params = new ImportParams();
            params.setHeadRows(1);
//            params.setHeadRows(2);
            List<ImportAssetBean> assetImpList = null;
            try {
                assetImpList = ExcelImportUtil.importExcel(multipartFile.getInputStream(), ImportAssetBean.class, params);
            } catch (Exception e) {
                e.printStackTrace();
                success.setCode(Constant.MESSAGE_INT_PARAMS);
                success.setDescription("excel字段格式识别异常，请注意特殊字符");
                return success;
            }

            if(CollectionUtils.isEmpty(assetImpList)){
                return MessageBean.create(Constant.MESSAGE_INT_PARAMS, "表格记录为空", void.class);
            }
            List<String> collect = assetImpList.stream().map(ImportAssetBean::getFixedNumber).collect(Collectors.toList());
            long count = collect.stream().distinct().count();
            if(collect.size()!=count){
                return MessageBean.create(Constant.MESSAGE_INT_PARAMS, "表格固定资产编号存在重复", void.class);
            }

            for(ImportAssetBean checkbean : assetImpList){
                if(StringUtils.isEmpty(checkbean.getDepatName())){
                    return MessageBean.create(Constant.MESSAGE_INT_PARAMS, "部门名称不能为空", void.class);
                }
                if(StringUtils.isEmpty(checkbean.getStaffunName())){
                    return MessageBean.create(Constant.MESSAGE_INT_PARAMS, "人员名称不能为空", void.class);
                }
                if(StringUtils.isEmpty(checkbean.getAssetTypeName())){
                    return MessageBean.create(Constant.MESSAGE_INT_PARAMS, "资产类型不能为空", void.class);
                }
                if(StringUtils.isEmpty(checkbean.getGoodsModel())){
                    return MessageBean.create(Constant.MESSAGE_INT_PARAMS, "资产型号不能为空", void.class);
                }
            }


            List<ImportAssetErrBean> list = ADOConnection.runTask(new AssetsService(), "assetImport", List.class, assetImpList);

            //如果存在导入异常会
            if(list.size()>0){
                String time = CommonUtil.getNowTimechuo();
                String fileName = ExportExcelUtil.saveExcel(list, ImportAssetErrBean.class, "错误信息" + time + ".xlsx");
                success.setCode(Constant.MESSAGE_ASSTETS_PARAMS);
                success.setDescription("导入信息存在错误");
                success.setData(fileName);
                return success;
            }

//            System.out.println(JsonUtils.objectToJson(meeting));
        } catch (Exception e) {
            e.printStackTrace();
            success.setCode(Constant.MESSAGE_INT_OPERATION);
            success.setDescription("fail " + e.getMessage());
            return success;
        }
        return success;
    }



}
