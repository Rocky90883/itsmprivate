(window["webpackJsonp"]=window["webpackJsonp"]||[]).push([["chunk-26ff0ad6"],{"0aee":function(t,e,a){},"4d55":function(t,e,a){"use strict";a.r(e);var i=function(){var t=this,e=t.$createElement,a=t._self._c||e;return a("div",{staticClass:"assetRegister h100"},[a("div",{staticClass:"page-main-box"},[a("KlTable",t._b({directives:[{name:"show",rawName:"v-show",value:"list"==t.pageType,expression:"pageType == 'list'"}],ref:"klTable"},"KlTable",t.tableConfig,!1)),"list"!==t.pageType?a("editPage",{attrs:{rightBtnArr:t.rightBtnArr},on:{goBackPage:t.goBackPage}},[a("addInfo",{ref:"editAddForm",attrs:{pageType:t.pageType,formData:t.formData}})],1):t._e()],1),a("dialogPublic",{attrs:{title:"选择采购人",show:t.showMember,appendTobody:"",width:"800px",height:"400px"},on:{"update:show":function(e){t.showMember=e},doSubmit:t.chooseUser,doClose:function(){return t.showMember=!1}}},[t.showMember?a("orgMemberSelect",{ref:"orgMemberSelect",attrs:{seleteSetting:t.orgMemberSelect,otherInfo:t.otherInfo}}):t._e()],1),a("dialogPublic",{attrs:{title:"资产登记-分配 / 剩余可分配数量："+this.maxNum,show:t.distributionDialog,appendTobody:"",width:"800px",height:"400px"},on:{"update:show":function(e){t.distributionDialog=e},doSubmit:t.distributionSubmit,doClose:function(){return t.distributionDialog=!1}}},[a("distributionDialog",{ref:"distributionFrom",attrs:{distributionData:t.distributionData,maxNum:t.maxNum}})],1)],1)},o=[],n=(a("b0c0"),a("d3b7"),a("ac1f"),a("1276"),a("3835")),r=function(){var t=this,e=t.$createElement,a=t._self._c||e;return a("div",{staticClass:"record__content"},[a("div",{staticClass:"record__baseinfo"},[a("dividerTitle",{attrs:{title:"基本信息"}}),a("editForm",{ref:"editForm",attrs:{formConfig:t.formConfig,formData:t.editFormData}})],1),a("div",{staticClass:"record__attach"},[a("dividerTitle",{attrs:{title:"附件"}}),a("attachTable",{ref:"attachFile",attrs:{attachSetting:t.attachSetting,hideBtn:t.hideBtn}})],1)])},s=[],l=(a("d81d"),{props:{formData:{type:Object,default:new Object},pageType:{type:String,default:""}},data:function(){var t=this;return{editFormData:{suitableWorkArr:[]},trainingType:{},formConfig:{itemArr:[{label:"采购日期",prop:"buyDate",type:"date",required:!0,span:8},{label:"采购合同号",prop:"contractNo",required:!0,type:"text"},{label:"分配数量",prop:"overQty",type:"el-number",disabled:!0,min:0,precision:0,step:1},{label:"品牌",prop:"brand",type:"select",id:"brand_type",seleteOption:this.$store.getters["dirtData"]("brand_type"),formatter:function(e){return t.$store.getters["fineDirtDataName"]("brand_type",e)}},{label:"采购人",prop:"staffunName",idsStr:"empId",type:"user",trigger:"change"},{type:"select",label:"资产分类",prop:"assetTypeId",required:!0,seleteOption:this.$store.getters.assetTypeList},{label:"型号",prop:"goodsModel",required:!0,type:"text"},{label:"登记数量",prop:"qty",type:"el-number",required:!0,min:0,max:99999999,precision:0,step:1},{label:"CPU",prop:"cpu",type:"text"},{label:"内存",prop:"ram",type:"text"},{label:"硬盘",prop:"disk",type:"text"},{label:"版本",prop:"edition",type:"text"},{label:"瓦数",prop:"wattage",type:"text"},{label:"规格",prop:"spec",type:"text"},{label:"带宽",prop:"bandwidth",type:"text"},{label:"保修年限",prop:"warranty",type:"text"}]},attachSetting:{title:"",select:!1,billType:"asset_regist",sourceId:"",btnArr:["add","delete","save"]},coverList:[],dialogVisible:!1,activeName:"0",hideBtn:!1}},computed:{},mounted:function(){if(this.attachSetting.sourceId=this.formData.id,this.init(),void 0===this.formData.id){var t=this.$utils.getNowDay();this.$set(this.editFormData,"buyDate",t)}},methods:{init:function(){"add"!=this.pageType&&(this.editFormData=Object.assign({},this.formData)),"details"==this.pageType&&(this.hideBtn=!0,this.formConfig.itemArr.map((function(t){return t.disabled=!0})))},getFormData:function(){var t=this;return new Promise((function(e,a){t.$refs.editForm.getFormData().then((function(t){t=Object.assign({},t),e(t)})).catch((function(t){a(t)}))}))},getDetails:function(){}}}),d=l,c=(a("ffb7"),a("2877")),u=Object(c["a"])(d,r,s,!1,null,"81350ca8",null),p=u.exports,f=function(){var t=this,e=t.$createElement,a=t._self._c||e;return a("div",{staticClass:"distributionDialog"},[a("editForm",{ref:"editForm",attrs:{formConfig:t.formConfig,formData:t.editFormData}})],1)},b=[],h=(a("a9e3"),{props:{distributionData:{type:Object,default:{}},maxNum:{type:Number,default:0}},name:"distributionDialog",data:function(){return{editFormData:{},formConfig:{itemArr:[{type:"select",label:"资产分类",prop:"assetTypeName",id:"assetTypeId",required:!0,disabled:!0,seleteOption:this.$store.getters.assetTypeList,span:24},{label:"分配型号",prop:"goodsModel",required:!0,disabled:!0,type:"text",span:24},{label:"分配日期",prop:"billDate",type:"date",required:!0,span:24},{label:"分配部门",prop:"depatName",idsStr:"depatCode",type:"dept",trigger:"change",required:!0,span:24},{label:"分配数量",prop:"portionQty",type:"el-number",required:!0,min:0,max:this.maxNum,precision:0,step:1,span:24}]}}},created:function(){this.init()},methods:{init:function(){var t=this.distributionData,e=t.id,a=t.assetTypeName,i=t.goodsModel,o={registerId:e,assetTypeName:a,goodsModel:i};this.editFormData=Object.assign({},o)},getFormData:function(){var t=this;return new Promise((function(e,a){t.$refs.editForm.getFormData().then((function(t){t=Object.assign({},t),e(t)})).catch((function(t){a(t)}))}))}}}),m=h,g=Object(c["a"])(m,f,b,!1,null,null,null),y=g.exports,D=a("fd03");function T(){var t=arguments.length>0&&void 0!==arguments[0]?arguments[0]:{};return Object(D["a"])({url:"/asset/assetRegistAction/queryList",method:"post",data:t})}function v(){var t=arguments.length>0&&void 0!==arguments[0]?arguments[0]:"";return Object(D["a"])({url:"/asset/assetRegistAction/deleteRegist",method:"post",data:{id:t}})}function w(){var t=arguments.length>0&&void 0!==arguments[0]?arguments[0]:{};return Object(D["a"])({url:"/asset/assetRegistAction/addRegist",method:"post",data:t})}function O(){var t=arguments.length>0&&void 0!==arguments[0]?arguments[0]:{};return Object(D["a"])({url:"/asset/assetRegistAction/updateRegist",method:"post",data:t})}function x(){var t=arguments.length>0&&void 0!==arguments[0]?arguments[0]:{};return Object(D["a"])({url:"/asset/assetRegistAction/addPortion",method:"post",data:t})}a("2934");var S={name:"assetRegister",components:{addInfo:p,distributionDialog:y},data:function(){var t=this;return{label:"",maxNum:0,otherInfo:"",showMember:!1,distributionDialog:!1,distributionData:{},currentItem:{},orgMemberSelect:{},tableConfig:{showFixedHeight:!0,searchObj:{searchMethod:function(e,a){t.tableConfig.tableDataObj&&t.getTableData()},searchArr:[{type:"daterange",label:"日期选择",model:"billDate"},{type:"el-select",label:"资产分类",model:"assetTypeName\t",id:"assetTypeId",option:this.$store.getters.assetTypeList},{type:"el-input",label:"采购合同号",model:"contractNo",placeholder:"采购合同号"},{type:"el-input",label:"型号",model:"goodsModel",placeholder:"型号"},{type:"el-input",label:"采购人",model:"staffunName",id:"empId",scopeType:"formate",method:function(e){t.chooseStaff("user")},placeholder:"采购人"}]},queryObj:{method:function(){t.getTableData()},queryData:{page:1,pageSize:15}},tableDataObj:{tableArr:[],total:null},operateArr:[{name:"新建",icon:"el-icon-plus",method:function(e){t.toAdd()}}],tableObj:{selectionShow:!1,indexShow:!0,tableMethods:{selection:function(e){t.selectionData=e}},showItems:[{name:"资产分类",prop:"assetTypeName",width:150},{name:"品牌",prop:"brand",scopeType:"formate",method:function(e){var a=e.brand;return t.$store.getters["fineDirtDataName"]("brand_type",a)}},{name:"型号",prop:"goodsModel"},{name:"登记单号",prop:"billNo"},{name:"采购数量",prop:"qty"},{name:"分配数量",prop:"overQty"},{name:"采购人",prop:"staffunName"},{name:"采购日期",prop:"buyDate",width:140},{name:"采购合同号",prop:"contractNo",width:150},{name:"操作",prop:"operate",width:150,scopeType:"button",fixed:"right",buttonArr:[{name:"详情",method:function(e){t.detailsInfo(e)},isShow:function(t,e){return!0},icon:"false"},{name:"编辑",method:function(e){t.editInfo(e)},isShow:function(t,e){return!0},icon:"false"},{name:"分配",method:function(e){t.maxNum=e.qty-e.overQty,t.distributionData=e,t.distributionDialog=!0},isShow:function(t,e){return!0},icon:"false"},{name:"删除",method:function(e){t.deleteInfo(e)},isShow:function(t,e){return!0},icon:"false"}]}]},paginationObj:{pageSizes:[15,30,50,100]},paginationIsshow:!0,showklTable:!1},pageType:"list",formData:{}}},computed:{rightBtnArr:function(){var t=this,e=[{text:"保存",icon:"el-icon-finished",hide:"details"==this.pageType,methods:function(){t.doSubmit()}}];return e}},watch:{"tableConfig.queryObj.queryData.staffunName":function(t){""==t&&(this.tableConfig.queryObj.queryData.empId=null)},pageType:function(t){"list"===t&&this.getTableData()}},mounted:function(){this.getTableData()},methods:{toAdd:function(){this.pageType="add",this.formData={}},getTableData:function(){var t=this,e=Object.assign({},this.tableConfig.queryObj.queryData);e.pageCount=e.pageSize;var a=e.billDate;if(a){var i=Object(n["a"])(a,2),o=i[0],r=i[1];e.beginDate=o,e.endDate=r}this.$delete(e,"billDate"),this.$delete(e,"pageSize"),this.$delete(e,"staffunName"),T(e).then((function(e){t.$set(t.tableConfig.tableDataObj,"tableArr",e.data.list||[]),t.$set(t.tableConfig.tableDataObj,"total",e.data.total||0)})).catch((function(e){t.$set(t.tableConfig.tableDataObj,"tableArr",[]),t.$set(t.tableConfig.tableDataObj,"total",0)}))},chooseStaff:function(t){var e=t.split("-")[0],a=t.split("-")[1];"dept"==e?this.orgMemberSelect={seleteType:a||"org",radio:"dept"==e,props:{label:"organizationName",children:"children"},column:[{label:"组织",prop:"name"}],chosedData:[]}:"user"==e&&(this.orgMemberSelect={seleteType:a||"orgmember",radio:"user"==e,props:{label:"organizationName",children:"children"},column:[{label:"姓名",prop:"name",width:60}],chosedData:[]}),this.showMember=!0},chooseUser:function(){var t=this.$refs.orgMemberSelect.getSeleteData(),e=t[0];e&&(this.$set(this.tableConfig.queryObj.queryData,"staffunName",e.name),this.$set(this.tableConfig.queryObj.queryData,"empId",e.id)),this.showMember=!1},distributionSubmit:function(){var t=this;this.$refs.distributionFrom.getFormData().then((function(e){t.addDistribution(e).then((function(e){t.$message.success("操作成功！"),t.distributionDialog=!1,t.getTableData()}))})).catch((function(t){console.log(t)}))},addDistribution:function(t){return new Promise((function(e,a){x(t).then((function(t){e(t)})).catch((function(t){console.log(t),a(t)}))}))},addSeleteMember:function(){var t=this.$Vue,e=this.selections;t.$refs["tabs"+this.tabname][0].setTableData(e),this.showMember=!1},detailsInfo:function(t){this.pageType="details",this.formData=Object.assign({},t)},editInfo:function(t){this.pageType="edit",this.formData=Object.assign({},t)},deleteInfo:function(t){var e=this;this.$confirm("是否确认删除","提示",{confirmButtonText:"确定",cancelButtonText:"取消",type:"warning"}).then((function(){v(t.id).then((function(t){e.$message.success("操作成功"),e.getTableData()}))})).catch((function(t){}))},doSubmit:function(){var t=this;this.$refs.editAddForm.getFormData().then((function(e){t.addMethons(e).then((function(a){t.$message.success("保存成功！"),e.id||(t.formData={id:a.data}),t.pageType="list",t.getTableData()}))})).catch((function(t){console.log(t)}))},addMethons:function(t){return this.$delete(t,"staffunName"),this.$delete(t,"overQty"),t.id?new Promise((function(e,a){O(t).then((function(t){e(t)})).catch((function(t){console.log(t),a(t)}))})):new Promise((function(e,a){w(t).then((function(t){e(t)})).catch((function(t){console.log(t),a(t)}))}))},goBackPage:function(t){this.pageType="list"}}},j=S,$=Object(c["a"])(j,i,o,!1,null,null,null);e["default"]=$.exports},ffb7:function(t,e,a){"use strict";var i=a("0aee"),o=a.n(i);o.a}}]);
//# sourceMappingURL=chunk-26ff0ad6.f0410b4c.js.map