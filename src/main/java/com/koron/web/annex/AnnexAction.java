package com.koron.web.annex;

import cn.hutool.core.io.FileUtil;
import com.koron.common.ResourceComponent;
import com.koron.common.authentication.NoAuth;
import com.koron.util.CommonUtil;
import com.koron.util.Constant;
import com.koron.util.HttpUtils;
import com.koron.util.SessionUtil;
import com.koron.web.annex.bean.AnnexBean;
import com.koron.web.asset.assets.bean.AssetsBean;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.commons.lang3.StringUtils;
import org.jboss.logging.Logger;
import org.koron.ebs.mybatis.ADOConnection;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.swan.bean.MessageBean;

import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Api(tags = "附件")
@RequestMapping("/AnnexAction")
@RestController
public class AnnexAction {

    Logger log = Logger.getLogger(AnnexAction.class);

    @ApiOperation("--附件上传")
    @ResponseBody
    @PostMapping(value = "/fileUpload")
    public MessageBean fileUpload(@RequestParam("files") MultipartFile[] files,@RequestParam("billType") String billType
    ,@RequestParam("sourceId") String sourceId ){

        @SuppressWarnings("rawtypes")
        MessageBean<List> success = MessageBean.create(Constant.MESSAGE_INT_SUCCESS, "success", List.class);
        List<String> filelist = new ArrayList<>();
        String filePath = "";
        StringBuffer resultFileNames = new StringBuffer();
        // 多文件上传
        String filesName = "";
        for (MultipartFile file : files){
            AnnexBean bean = new AnnexBean();
            bean.setBillType(billType); //设置单据类型
            bean.setSourceId(sourceId); //设置单据id
            // 上传简单文件名
            String originalFilename = file.getOriginalFilename();
            // 存储路径
            originalFilename = new StringBuilder("")
                    .append(System.currentTimeMillis())
                    .append(originalFilename)
                    .toString();
            filePath = ResourceComponent.getLocationPath(billType,originalFilename);
            try {
                // 保存文件
                file.transferTo(new File(filePath));
//                resultFileNames.append(";");
//                resultFileNames.append(originalFilename);
            } catch (IOException e) {
                e.printStackTrace();
            }
            bean.setAnnexName(originalFilename);
            bean.setSuffix(originalFilename.substring(originalFilename.lastIndexOf(".")+1));

            bean.setCreateTime(CommonUtil.getCurrentTime());
            bean.setCreateName(SessionUtil.getUseerInfoName());
            bean.setCreateAccount(SessionUtil.getUseerInfoCode());
            ADOConnection.runTask(new AnnexService(), "addAnnex",MessageBean.class,bean);

            filelist.add(originalFilename);
        }
//        System.out.println(resultFileNames.substring(1));
//        success.setData(resultFileNames.substring(1));

        success.setData(filelist);
        return success;
    }


    /**
     * 下载导入错误信息
     * @return
     */
    @ApiOperation("---下载附件")
    @GetMapping("/download")
    public void downLoad(@RequestParam(value = "id") String id,@RequestParam("typeName") String typeName, HttpServletResponse response) throws Exception {

        String[] names = id.split(",");
        if (names.length == 1) {
            AnnexBean annexBean = ADOConnection.runTask(new AnnexService(), "annexById", AnnexBean.class, id);

            String path = ResourceComponent.getLocationPath(typeName,annexBean.getAnnexName());
            HttpUtils.downLoadCommon(response, FileUtil.file(path), annexBean.getAnnexName().substring(13));
//            String path = ResourceComponent.getLocationPath(typeName,names[0]);
//            HttpUtils.downLoadCommon(response, FileUtil.file(path), filename);
            return;
        }
        return;
    }

    @ApiOperation("-单据附件-列表")
    @ResponseBody
    @PostMapping(value = "/queryList")
    public MessageBean<?> queryList(@RequestBody AnnexBean dto){

        @SuppressWarnings("rawtypes")
        MessageBean<List> info = MessageBean.create(Constant.MESSAGE_INT_SUCCESS, "success", List.class);
        try {
            List<AnnexBean> list = ADOConnection.runTask(new AnnexService(), "queryList", List.class,dto);
            list.stream().forEach(bean->{
                bean.setAnnexName(bean.getAnnexName().substring(13));
            });
            info.setData(list);
        } catch (Exception e) {
            log.error("查询单据附件列表-异常", e);
            info.setCode(Constant.MESSAGE_INT_OPERATION);
            info.setDescription("查询单据附件列表-异常");
            return info;
        }
        return info;
    }

    @ApiOperation("-单据附件-删除")
    @ResponseBody
    @PostMapping(value = "/remove")
    public MessageBean<?> remove(@RequestBody AnnexBean dto){

        @SuppressWarnings("rawtypes")
        MessageBean info = MessageBean.create(Constant.MESSAGE_INT_SUCCESS, "success", void.class);
        try {
            if(StringUtils.isEmpty(dto.getBillType())){
                return MessageBean.create(Constant.MESSAGE_INT_SUCCESS, "单据标识不能为空", void.class);
            }
            if(StringUtils.isEmpty(dto.getAnnexName())){
                return MessageBean.create(Constant.MESSAGE_INT_SUCCESS, "附件名称不能为空", void.class);
            }
            if(StringUtils.isEmpty(dto.getId())){
                return MessageBean.create(Constant.MESSAGE_INT_SUCCESS, "附件标识不能为空", void.class);
            }
            String filePath = ResourceComponent.getLocationPath(dto.getBillType(),dto.getAnnexName());


            File file = new File(filePath);
            info = ADOConnection.runTask(new AnnexService(), "deleteRegist", MessageBean.class,dto.getId());
            if (file.exists()) {
                file.delete();
                return info;
            } else {
                return MessageBean.create(Constant.MESSAGE_INT_SUCCESS, "删除失败", void.class);
            }
        } catch (Exception e) {
            log.error("单据附件删除-异常", e);
            info.setCode(Constant.MESSAGE_INT_OPERATION);
            info.setDescription("单据附件删除-异常");
        }
        return info;
    }


    /**
     * 下载导入错误信息
     * @return
     */
    @ApiOperation("---下载导入错误信息")
    @GetMapping("/errorExport.htm")
    public void errorExport(@RequestParam(value = "filename") String filename, HttpServletResponse response) throws Exception {
        log.info("导入错误信息路径"+ filename);
        String[] names = filename.split(",");
        if (names.length == 1) {
            String path = ResourceComponent.getLocationPath(names[0]);
            HttpUtils.downLoadCommon(response, FileUtil.file(path), filename);
            return;
        }
        return;
    }

}
