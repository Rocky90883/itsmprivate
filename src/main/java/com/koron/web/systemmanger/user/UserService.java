package com.koron.web.systemmanger.user;

import com.koron.common.bean.StaffBean;
import com.koron.common.web.mapper.StaffMapper;
import com.koron.web.systemmanger.UnifiedUserImport;
import com.koron.web.systemmanger.user.bean.StaffunBean;
import com.koron.web.systemmanger.user.bean.StaffunQueryBean;
import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;
import org.koron.ebs.mybatis.SessionFactory;
import org.koron.ebs.mybatis.TaskAnnotation;
import org.springframework.stereotype.Service;
import org.swan.bean.MessageBean;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Service
public class UserService {

    private static Logger log = Logger.getLogger(UserService.class);

    @TaskAnnotation("staffList")
    public List<StaffunBean> staffList(SessionFactory factory, StaffunQueryBean queryBean) {
        List<StaffunBean> staffList;
        try {
            UserMapper mapper = factory.getMapper(UserMapper.class);
            staffList = mapper.queryStaffun(queryBean);

        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException(e.getMessage());
        }
        return staffList;
    }


    /**
     * 同步远程数据源的人员数据
     * @param sessionFactory
     * @return
     */
    @TaskAnnotation("syncUser")
    public MessageBean<?> syncUser(SessionFactory sessionFactory){
        UserMapper localMapper = sessionFactory.getMapper(UserMapper.class);
        StaffMapper orgReader = sessionFactory.getMapper(StaffMapper.class);

        //查询本地所有数据
        List<StaffunBean> allList = localMapper.getAllStaffun();
        //流程库组织所有数据
        List<StaffBean> commlibList = orgReader.getAllStaff();
        //统一用户平台
        List<StaffunBean> unDataList = UnifiedUserImport.importStaffun();;

        //3.匹配数据
        List<StaffunBean> nlist = new ArrayList<StaffunBean>();//添加数据
        List<StaffunBean> ulist = new ArrayList<StaffunBean>();//修改数据
        //将万户数据和远程数据对比更新万户的userid字段
        for (StaffunBean item : unDataList) {
            item.setName(StringUtils.trim(item.getName()));
            if (StringUtils.isNotBlank(item.getName())) {
                //组装数据
                StaffunBean user = new StaffunBean();
                user.setCode(item.getCode());
                user.setName(item.getName());
                user.setMobile(item.getMobile());
                user.setPhone(item.getPhone());
                user.setEmail(item.getEmail());
                user.setSex(item.getSex());
                user.setStatus(item.getStatus());
                user.setOrgNodeCode(item.getOrgNodeCode());
                user.setOrgNodeName(item.getOrgNodeName());
                user.setTitle(item.getTitle());
                for (int j = 0; j < commlibList.size(); j++) {
                    if (item.getCode().equals(commlibList.get(j).getLoginid())) {
                        user.setWorkflowCode(commlibList.get(j).getCode());//将工作流中组织的code赋值给新创建的数据
                        break;
                    }
                }
                boolean flag = false; //本地库是否存在该人员
                for(StaffunBean  staff : allList){
                    if(item.getCode().equals(staff.getCode())){ //配置库已经存在，则修改
                        ulist.add(user);
                        flag = true;
                        break;
                    }
                }
                if(!flag){ //本地库不存在，则新增
                    nlist.add(user);
                }
            }
        }
        //4.实例化到数据库
        int num = 300;//每次修改的数据量
        if(!nlist.isEmpty()){
            for(int i = 0 ;i < nlist.size(); i = i + num){
                List<StaffunBean> add = new ArrayList<>();
                if( i + num <= nlist.size()){
                    add = nlist.subList(i, i+num);
                }else {
                    add = nlist.subList(i, nlist.size());
                }
                if(!add.isEmpty()){
                    localMapper.addStaffs(add);//批量添加
                }

            }
        }
        if(!ulist.isEmpty()){
            for(int i = 0 ;i < ulist.size(); i = i + num){
                List<StaffunBean> upd = new ArrayList<>();
                if( i + num < ulist.size()){
                    upd = ulist.subList(i, i+num);
                }else {
                    upd = ulist.subList(i, ulist.size());
                }
                if(!upd.isEmpty()){
                    localMapper.updateStaffs(upd);//批量修改
                }

            }
        }
        MessageBean<String> msg = MessageBean.create(0, "同步用户完成", String.class);
        msg.setDescription("同步用户完成,新增"+nlist.size()+"条,修改"+ulist.size()+"条");
        return msg;
    }

}
