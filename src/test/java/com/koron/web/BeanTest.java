//package com.koron.web;
//
//import com.koron.common.bean.BaseOracleQueryBean;
//import com.koron.web.app.goods.AppGoodsBean;
//import com.koron.web.systemmanger.user.UserBean;
//import org.apache.poi.ss.usermodel.Cell;
//import org.apache.poi.ss.usermodel.Row;
//import org.apache.poi.ss.usermodel.Sheet;
//import org.apache.poi.ss.usermodel.Workbook;
//import org.apache.poi.xssf.usermodel.XSSFWorkbook;
//import org.codehaus.jackson.JsonGenerationException;
//import org.codehaus.jackson.map.JsonMappingException;
//import org.codehaus.jackson.map.ObjectMapper;
//import org.junit.Test;
//import org.springframework.web.multipart.MultipartFile;
//
//import java.io.IOException;
//import java.io.InputStream;
//import java.text.SimpleDateFormat;
//import java.util.Date;
//import java.util.HashMap;
//import java.util.Map;
//
//public class BeanTest {
//
//
//
//    public void beanint(){
//
//        AppGoodsBean bean =  new AppGoodsBean();
//        bean.setGoodsFullName("nsf");
//        System.out.println(bean.toString());
//    }
//
//
//    public void fenye(){
//
//        BaseOracleQueryBean bean = new BaseOracleQueryBean();
//        bean.setPageSize(10);
//        bean.setPage(3);
//        System.out.println(bean.toString());
//
//    }
//
//
//    public void stringcompanto(){
//
//        String a = "01";
//        String b = "02";
//        String c = "03";
//        String d = "04";
//        String f = "05";
//        String g = "06";
//        String h = "10";
//        String i = "11";
//        String j = "12";
//        String c2 = "03";
//
//        if(a.compareTo(b)==-1){
//            System.out.println("a小于b");
//            if(b.compareTo(c)==-1) {
//                System.out.println("b小于c");
//                if(g.compareTo(h)==-1){
//                    System.out.println("g小于h");
//                    if(h.compareTo(i)==-1){
//                        System.out.println("h小于i");
//                        if(i.compareTo(j)==-1){
//                            System.out.println("i小于j");
//                        }
//                    }
//                }
//            }
//        }
//
//        System.out.println(c.compareTo(d));
//    }
//
//
//    /**
//     * 生成规则设备编号:设备类型+五位编号（从1开始，不够前补0）
//     * @param equipmentType 设备类型
//     * @param equipmentNo   最新设备编号
//     * @return
//     */
//    public static String getNewEquipmentNo(String equipmentType, String equipmentNo){
//        String newEquipmentNo = "10001";
//
//        if(equipmentNo != null && !equipmentNo.isEmpty()){
//            int newEquipment = Integer.parseInt(equipmentNo) + 1;
//            newEquipmentNo = String.format(equipmentType + "%04d", newEquipment);
//        }
//
//        return newEquipmentNo;
//    }
//
//
//    public void tesTtree(){
//
//        String a = getNewEquipmentNo("1","00001");
//        System.out.println(a);
//        String str = "019000";
//        System.out.println(str.substring(0, 3));
//        String string ="5";
//        System.out.println(String.format("%02d", 5));
//
//    }
//
//
//    @Test
//    public  void testJson6() {
//        ObjectMapper  mapper = new ObjectMapper ();
//        Map<String,Object> map = new HashMap<>();
//        map.put("id","abdae");
//        map.put("name","我的");
//        map.put("model","nokia");
//        map.put("size","60");
//        PhoneBean phone = null;
//        try {
//            String s = mapper.writeValueAsString(map);
//            phone = mapper.readValue(s, PhoneBean.class);
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//        System.out.println(phone);
//        //Phone{id='abdae', model='nokia', other={size=6.0, name=我的}}
//    }
//
//    /**
//     * 自身类作为自己的内部属性
//     */
//
//    public void testfour(){
//
//        ObjectMapper objectMapper = new ObjectMapper();
//
//        PhoneBean p1 = new PhoneBean();
//        p1.setId("213213");
//        p1.setModel("模块1");
//
//        PhoneBean p2 = new PhoneBean();
//        p2.setId("213213");
//        p2.setModel("模块1");
//
//        p1.setInnerBaseObject(p2);
//
//        try {
//            String json = objectMapper.writeValueAsString(p1);
//            System.out.println(json);
//        } catch (JsonGenerationException e) {
//            e.printStackTrace();
//        } catch (JsonMappingException e) {
//            e.printStackTrace();
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//
//
//    }
//
//    public String saveExcelUserCard(MultipartFile multipartFile) {
//        InputStream inputStream = null;
//        Workbook wb0 = null;
//        try {
//            inputStream = multipartFile.getInputStream();
//            //根据指定的文件输入流导入Excel从而产生Workbook对象
//            wb0 = new XSSFWorkbook(inputStream);
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
//        if (wb0 != null) {
//            //获取Excel文档中的第一个表单
//            Sheet sht0 = wb0.getSheetAt(0);
//            //对Sheet中的每一行进行迭代
////            StringBuilder errorBuilder = new StringBuilder();
//            try {
//                for (Row r : sht0) {
//                    //如果当前行的行号（从0开始）未达到1（第二行）则从新循环
//                    //r就是表格的一行
//                    int rowNum = r.getRowNum();
//                    if (rowNum < 1) {
//                        continue;
//                    }
//                    if (r.getCell(0)!= null){
//                        //只有从数字表格中取出 String类型 才需要设置类型
//                        r.getCell(0).setCellType(Cell.CELL_TYPE_STRING);
//                    }
//                    // coolegeId 是我的字段名
//                    String coolegeId = r.getCell(0).getStringCellValue();
//                    String userName = r.getCell(1).getStringCellValue();
//                    String deptName = r.getCell(2).getStringCellValue();
//                    /*String endTime = r.getCell(3).getStringCellValue();
//                    Date endDate = simpleDateFormat.parse(endTime);*/
//                    UserBean userBean = new UserBean();
//                    userBean.setLoginId(Integer.parseInt(coolegeId));
//                    userBean.setUserName(userName);
//                    userBean.setDeptName(deptName);
//                    userBean.setCreateTime(new Date().toString());
//                    //调用数据库保存
////                    schoolCollegeMajorService.save(major);
//                }
//            } catch (Exception e) {
//                e.printStackTrace();
//            }
//            return "添加成功";
//        }
//        return "文件无内容";
//    }
//
//
//}
