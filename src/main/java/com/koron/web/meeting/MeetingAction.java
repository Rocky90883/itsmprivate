package com.koron.web.meeting;

import cn.afterturn.easypoi.excel.ExcelImportUtil;
import cn.afterturn.easypoi.excel.entity.ImportParams;
import cn.hutool.core.io.FileUtil;
import com.koron.common.ResourceComponent;
import com.koron.common.authentication.NoAuth;
import com.koron.util.*;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.log4j.Logger;
import org.koron.ebs.mybatis.ADOConnection;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.swan.bean.MessageBean;
import springfox.documentation.annotations.ApiIgnore;

import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;


@ApiIgnore//隐藏接口不显示
@Api(tags = "会议大屏")
@RequestMapping("/meetingAction/")
@RestController
public class MeetingAction {

    private static Logger log = Logger.getLogger(MeetingAction.class);

    @Autowired
    private MeetingService meetingService;

    /**
     * 会议名称列表
     * @param billDate
     * @param title
     * @return
     */
    @NoAuth
    @ApiOperation("---会议名称列表")
    @ResponseBody
    @PostMapping(value = "/mettingList")
    public MessageBean<?> mettingList(@RequestParam(value = "billDate",required = false)String billDate,
                                      @RequestParam(value = "title",required = false)String title){

        @SuppressWarnings("rawtypes")
        MessageBean<List> success = MessageBean.create(Constant.MESSAGE_INT_SUCCESS, "success", List.class);
        try {
            MeetingQueryBean queryBean = new MeetingQueryBean();
            queryBean.setPage(1);
            queryBean.setPageSize(20);
            if(!StringUtils.isEmpty(billDate)){
                queryBean.setBillDate(billDate);
            }
            if(!StringUtils.isEmpty(title)){
                queryBean.setTitle(title);
            }
            @SuppressWarnings("unchecked")
            List<MeetingBean> list = ADOConnection.runTask("_default", meetingService, "mettingList",List.class,queryBean);
            success.setData(list);

        } catch (Exception e) {
            log.error("查询会议名称失败", e);
            success.setCode(Constant.MESSAGE_INT_OPERATION);
            success.setDescription("fail " + e.getMessage());
            return success;
        }
        return success;
    }

    /**
     * 会议名称列表
     * @param billDate
     * @param title
     * @return
     */
    @NoAuth
    @ApiOperation("---会议名称包装列表")
    @ResponseBody
    @PostMapping(value = "/mettingLists")
    public MessageBean<?> mettingLists(@RequestParam(value = "billDate",required = false)String billDate,
                                      @RequestParam(value = "title",required = false)String title){
        @SuppressWarnings("rawtypes")
        MessageBean<List> success = MessageBean.create(Constant.MESSAGE_INT_SUCCESS, "success", List.class);
        try {
            MeetingQueryBean queryBean = new MeetingQueryBean();
            queryBean.setPage(1);
            queryBean.setPageSize(20);
            if(!StringUtils.isEmpty(billDate)){
                queryBean.setBillDate(billDate);
            }
            if(!StringUtils.isEmpty(title)){
                queryBean.setTitle(title);
            }

            @SuppressWarnings("unchecked")
            List<MeetingBean> list = ADOConnection.runTask("_default", meetingService, "mettingList",List.class,queryBean);


            Map<String, List<MeetingBean>> titlegroup = list.stream().collect(Collectors.groupingBy(MeetingBean::getTitle));
            List<MeetingZBean> resultlist = new ArrayList<>();
            for(Map.Entry<String, List<MeetingBean>> en : titlegroup.entrySet()){
                MeetingZBean zbean = new MeetingZBean();
                zbean.setBillDate(billDate);
                zbean.setTitle(en.getKey());
                List<MeetingDBean> dbeanlist = new ArrayList<>();
                for(MeetingBean conen : en.getValue()){
                    MeetingDBean dbean = new MeetingDBean();
                    dbean.setContent(conen.getContent());
                    dbean.setSort(conen.getSort());
                    dbeanlist.add(dbean);
                }
                zbean.setListDet(dbeanlist);
                resultlist.add(zbean);
            }
            success.setData(resultlist);
        } catch (Exception e) {
            log.error("查询会议名称失败", e);
            success.setCode(Constant.MESSAGE_INT_OPERATION);
            success.setDescription("fail " + e.getMessage());
            return success;
        }
        return success;
    }


    /**
     * 会议详情
     * @param billDate
     * @return
     */
    @NoAuth
    @ApiOperation("---会议明细")
    @ResponseBody
    @PostMapping(value = "/mettingDet")
    public MessageBean<?> mettingDet(@RequestParam(value = "billDate" ,required = true)String billDate){

        @SuppressWarnings("rawtypes")
        MessageBean<List> success = MessageBean.create(Constant.MESSAGE_INT_SUCCESS, "success", List.class);
        try {
            @SuppressWarnings("unchecked")
            List<MeetingZBean> list = ADOConnection.runTask("_default", meetingService, "mettingDet",List.class,billDate);
            success.setData(list);

        } catch (Exception e) {
            log.error("查询会议名称失败", e);
            success.setCode(Constant.MESSAGE_INT_OPERATION);
            success.setDescription("fail " + e.getMessage());
            return success;
        }
        return success;
    }


    @NoAuth
    @ApiOperation("---会议添加")
    @ResponseBody
    @PostMapping(value = "/mettingAdd")
    public MessageBean mettingAdd(@RequestBody MeetingBean bean){

        @SuppressWarnings("rawtypes")
        MessageBean success = MessageBean.create(Constant.MESSAGE_INT_SUCCESS, "success", void.class);
        try {
            success = ADOConnection.runTask("_default", meetingService, "mettingAdd", MessageBean.class, bean);
        } catch (Exception e) {
            log.error("添加会议失败", e);
            success.setCode(Constant.MESSAGE_INT_OPERATION);
            success.setDescription("fail " + e.getMessage());
            return success;
        }
        return success;
    }

    @NoAuth
    @ApiOperation("---会议保存")
    @ResponseBody
    @PostMapping(value = "/mettingSave")
    public MessageBean mettingSave(@RequestBody MeetingBean bean){

        @SuppressWarnings("rawtypes")
        MessageBean success = MessageBean.create(Constant.MESSAGE_INT_SUCCESS, "success", void.class);
        try {
//            System.out.println(bean.toString());
            List<MeetingBean> meetingList = new ArrayList<>();
            if(bean.getContentList()!=null && bean.getContentList().size()>0){
                for(int i=0;i<bean.getContentList().size();i++){
                    MeetingBean b = new MeetingBean();
                    b.setTitle(bean.getTitle());
                    b.setBillDate(bean.getBillDate());
                    b.setContent(bean.getContentList().get(i));
                    b.setSort(i);
                    meetingList.add(b);
                }
            }

            //先删除后插入
            MessageBean messageBean = ADOConnection.runTask("_default", meetingService, "deletebybillDateOnTitle", MessageBean.class, bean.getBillDate(), bean.getTitle());
            if(messageBean.getCode()==0){
                for(MeetingBean mee:meetingList){
                    success = ADOConnection.runTask("_default", meetingService, "mettingAdd", MessageBean.class, mee);
                }
            }else{
                return messageBean;
            }
//            success = ADOConnection.runTask("_default", meetingService, "mettingAdd", MessageBean.class, bean);
        } catch (Exception e) {
            log.error("添加会议失败", e);
            success.setCode(Constant.MESSAGE_INT_OPERATION);
            success.setDescription("fail " + e.getMessage());
            return success;
        }
        return success;
    }

    @NoAuth
    @ApiOperation("---组装会议添加")
    @ResponseBody
    @PostMapping(value = "/mettingsAdd")
    public MessageBean mettingsAdd(@RequestBody MeetingZBean zBean){

        @SuppressWarnings("rawtypes")
        MessageBean success = MessageBean.create(Constant.MESSAGE_INT_SUCCESS, "success", void.class);
        try {
            List<MeetingBean> meetingList = new ArrayList<>();
            List<MeetingDBean> listDet = zBean.getListDet();
            for(int i=1;i<=listDet.size();i++){
                MeetingBean m = new MeetingBean();
                m.setBillDate(zBean.getBillDate());
                m.setTitle(zBean.getTitle());

                m.setSort(i);
                m.setContent(listDet.get(i).getContent());
                meetingList.add(m);
            }

            //先删除后插入
            MessageBean messageBean = ADOConnection.runTask("_default", meetingService, "mettingDelByBillDateOnTitle", MessageBean.class, zBean.getBillDate(), zBean.getTitle());
            if(messageBean.getCode()==0){
                for(MeetingBean bean:meetingList){
                    success = ADOConnection.runTask("_default", meetingService, "mettingAdd", MessageBean.class, bean);
                }
            }else{
                return messageBean;
            }

        } catch (Exception e) {
            log.error("添加会议失败", e);
            success.setCode(Constant.MESSAGE_INT_OPERATION);
            success.setDescription("fail " + e.getMessage());
            return success;
        }
        return success;
    }

    @NoAuth
    @ApiOperation("---会议删除")
    @ResponseBody
    @PostMapping(value = "/mettingDel")
    public MessageBean<?> mettingDel(@RequestParam(value = "billDate",required = true) String billDate,
                                     @RequestParam(value = "title",required = true) String title){

        @SuppressWarnings("rawtypes")
        MessageBean success = MessageBean.create(Constant.MESSAGE_INT_SUCCESS, "success", Map.class);
        try {
            success = ADOConnection.runTask("_default", meetingService, "deletebybillDateOnTitle", MessageBean.class,billDate, title);
        } catch (Exception e) {
            log.error("删除会议失败", e);
            success.setCode(Constant.MESSAGE_INT_OPERATION);
            success.setDescription("fail " + e.getMessage());
            return success;
        }
        return success;
    }


    @NoAuth
    @ApiOperation("---会议修改")
    @ResponseBody
    @PostMapping(value = "/mettingUpdate")
    public MessageBean<?> mettingUpdate(@RequestBody MeetingBean bean){

        @SuppressWarnings("rawtypes")
        MessageBean success = MessageBean.create(Constant.MESSAGE_INT_SUCCESS, "success", void.class);
        try {
            success = ADOConnection.runTask("_default", meetingService, "mettingUpdate", MessageBean.class, bean);
        } catch (Exception e) {
            log.error("修改会议失败", e);
            success.setCode(Constant.MESSAGE_INT_OPERATION);
            success.setDescription("fail " + e.getMessage());
            return success;
        }
        return success;
    }

    /**
     * 下载会议模板
     * @param rsp
     * @throws Exception
     */
    @NoAuth
    @ApiOperation("---下载会议模板")
    @GetMapping(value = "/meetingExport")
    public void meetingExport(HttpServletResponse rsp) throws Exception{

        List<MeetingBean> list = new LinkedList<MeetingBean>();

        list.add(new MeetingBean(CommonUtil.getCurrentDate(),"粤海水务会议",1,"1.关于实行副厅级以上领导到公司调研视察检查工作信息事前报备和事后报告。"));
        list.add(new MeetingBean(CommonUtil.getCurrentDate(),"粤海水务会议",2,"2.2020年管理信息系统维护服务项目招标"));
        list.add(new MeetingBean(CommonUtil.getCurrentDate(),"粤海水务会议",3,"3.关于加入深圳市供水行业协会的请示（孙国胜汇报、肖静列席）"));
        list.add(new MeetingBean(CommonUtil.getCurrentDate(),"粤海水务会议",4,"*****请注意日期格式、议题号只做排序、不做显示*****"));
        try {
            ExportExcelUtil.exportExcel(list,MeetingBean.class,"会议数据导入模板",rsp);
        } catch (Exception e) {
            log.error("模板导出失败");
            e.printStackTrace();
        }
        return;
    }

    /**
     * 下载导入错误信息
     * @return
     */
    @NoAuth
    @ApiOperation("---下载导入错误信息")
    @GetMapping("/meetingErrorExport")
    public void downLoad(@RequestParam(value = "filePath") String filename, HttpServletResponse response) throws Exception {
        log.info("导入错误信息路径"+ filename);
        String[] names = filename.split(",");
        if (names.length == 1) {
            String path = ResourceComponent.getLocationPath(names[0]);
            HttpUtils.downLoadCommon(response, FileUtil.file(path), filename);
            return;
        }
        return;
    }



    /**
     * 会议数据excel导入
     * @param multipartFile
     * @return
     * @throws Exception
     */
    @NoAuth
    @ApiOperation("---会议数据excel导入")
    @PostMapping("/meetingUpload")
    public MessageBean meetingUpload(@RequestParam("file") MultipartFile multipartFile) throws Exception {
        MessageBean success = MessageBean.create(Constant.MESSAGE_INT_SUCCESS, "success", void.class);

        try {
            ImportParams params = new ImportParams();
            params.setHeadRows(1);
            List<MeetingBean> meeting = null;
            try {
                meeting = ExcelImportUtil.importExcel(multipartFile.getInputStream(),MeetingBean.class, params);
            } catch (Exception e) {
                e.printStackTrace();
                success.setCode(Constant.MESSAGE_INT_PARAMS);
                success.setDescription("excel字段格式识别异常，请注意议题号格式需为数字类型");
                return success;
            }
            meeting = meeting.stream().filter(b->StringUtils.isEmpty(b.getBillDate())==false).collect(Collectors.toList());
            for(MeetingBean bean : meeting){
                if(StringUtils.isEmpty(bean.getBillDate())){
                    return MessageBean.create(Constant.MESSAGE_INT_PARAMS, "会议日期不能为空", void.class);
                }
                if(StringUtils.isEmpty(bean.getTitle())){
                    return MessageBean.create(Constant.MESSAGE_INT_PARAMS, "会议名称不能为空", void.class);
                }
                if(StringUtils.isEmpty(bean.getSort())){
                    return MessageBean.create(Constant.MESSAGE_INT_PARAMS, "议题号不能为空", void.class);
                }
                if(!CommonUtil.isInteger(bean.getSort().toString())){
                    return MessageBean.create(Constant.MESSAGE_INT_PARAMS, "议题号格式异常", void.class);
                }

                if(!CommonUtil.isValidDate(bean.getBillDate())){
                    System.out.println(bean.getBillDate());
                    return MessageBean.create(Constant.MESSAGE_INT_PARAMS, "日期格式异常", void.class);
                }
            }

            List<MeetingZBean> delbeanList = new ArrayList();
            //对导入的数根据会议日期分组
            Map<String, List<MeetingBean>> groupByBillDate = meeting.stream().filter(bean->StringUtils.isEmpty(bean.getBillDate())==false).collect(Collectors.groupingBy(MeetingBean::getBillDate));
            for(Map.Entry<String, List<MeetingBean>> enBillDate : groupByBillDate.entrySet()){
                //根据会议名称分组
                Map<String, List<MeetingBean>> groupByTitle = enBillDate.getValue().stream().collect(Collectors.groupingBy(MeetingBean::getTitle));
                for(Map.Entry<String, List<MeetingBean>> enTitle : groupByTitle.entrySet()){
                    MeetingZBean delbean = new MeetingZBean();
                    delbean.setTitle(enTitle.getKey());
                    delbean.setBillDate(enBillDate.getKey());
                    List<Integer> sort = enTitle.getValue().stream().map(MeetingBean::getSort).collect(Collectors.toList());
                    List<Integer> distinct = sort.stream().distinct().collect(Collectors.toList());
                    if(sort.size()!=distinct.size()){
                        success.setCode(Constant.MESSAGE_INT_PARAMS);
                        success.setDescription("日期为:"+enBillDate.getKey()+"的"+enTitle.getKey()+"会议存在议题号重复");
                        return success;
                    }
                    delbeanList.add(delbean);
                }
            }

            //先删除
            for(MeetingZBean bean:delbeanList){
                mettingDel(bean.getBillDate(),bean.getTitle());
            }
            //后插入
            List<ImportMeetingErrBean> errList = new ArrayList<>();
            for(MeetingBean bean : meeting){
                MessageBean insertMsg = mettingAdd(bean);
                if(insertMsg.getCode()!=0){
                    ImportMeetingErrBean errbean = BeanCovertUtil.beanCovert(bean, ImportMeetingErrBean.class);
//                    BeanUtils.copyProperties(bean,errbean);
                    errbean.setErrMsg(insertMsg.getDescription());
                    errList.add(errbean);
                    continue;
                }
            }
            //如果存在导入异常会
            if(errList.size()>0){
                String time = CommonUtil.getNowTimechuo();
                String fileName = ExportExcelUtil.saveExcel(errList, ImportMeetingErrBean.class, "错误信息" + time + ".xlsx");
                success.setCode(Constant.MESSAGE_INT_PARAMS);
                success.setDescription("导入存在异常");
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
        return MessageBean.create(Constant.MESSAGE_INT_SUCCESS, "success", void.class);
    }

}
