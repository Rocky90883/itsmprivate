(window["webpackJsonp"]=window["webpackJsonp"]||[]).push([["chunk-05eada22"],{"0c10":function(e,t,a){"use strict";a.r(t);var r=function(){var e=this,t=e.$createElement,a=e._self._c||t;return a("div",{staticClass:"assetRegister h100"},[a("div",{staticClass:"page-main-box"},[a("KlTable",e._b({directives:[{name:"show",rawName:"v-show",value:"list"==e.pageType,expression:"pageType == 'list'"}],ref:"klTable"},"KlTable",e.tableConfig,!1)),"list"!==e.pageType?a("editPage",{attrs:{rightBtnArr:e.rightBtnArr},on:{goBackPage:e.goBackPage}},["trans"==e.actionType?a("transPage",{ref:"editForm",attrs:{defaultSet:"",pageType:e.pageType,formData:e.formData},on:{"update:pageType":function(t){e.pageType=t},"update:page-type":function(t){e.pageType=t}}}):e._e(),"retirement"==e.actionType?a("retirementPage",{ref:"editForm",attrs:{defaultSet:"",pageType:e.pageType,formData:e.formData},on:{"update:pageType":function(t){e.pageType=t},"update:page-type":function(t){e.pageType=t}}}):e._e()],1):e._e()],1)])},s=[],o=a("a742"),i=a("4460"),n=a("f626"),d=a("b7b0"),l=a("4b54"),c={name:"myAsset",components:{transPage:i["a"],retirementPage:n["a"]},data:function(){var e=this;return{label:"",showMember:!1,details:!1,currentItem:{},orgMemberSelect:{},tableConfig:{showFixedHeight:!0,searchObj:{searchMethod:function(t,a){e.tableConfig.tableDataObj&&e.getTableData()},searchArr:[{type:"el-input",label:"型号",model:"goodsModel",placeholder:"型号"},{type:"el-select",label:"资产分类",model:"assetTypeId",option:this.$store.getters.assetTypeList}]},queryObj:{method:function(){e.getTableData()},queryData:{page:1,pageSize:15}},tableDataObj:{tableArr:[],total:null},operateArr:[],tableObj:{selectionShow:!1,indexShow:!0,tableMethods:{selection:function(t){e.selectionData=t}},showItems:[{name:"资产分类",prop:"assetTypeName"},{name:"品牌",prop:"brand",id:"brand_type",scopeType:"formate",method:function(t){var a=t.brand;return e.$store.getters["fineDirtDataName"]("brand_type",a)}},{name:"型号",prop:"goodsModel"},{name:"资产编码",prop:"assetsNumber"},{name:"资产状态",prop:"assetsStatus",scopeType:"formate",method:function(t){var a=t.assetsStatus;return e.$store.getters["fineDirtDataName"]("assets_status",a)||a}},{name:"操作",prop:"operate",scopeType:"button",fixed:"right",buttonArr:[{name:"确认",method:function(t){e.confirmMethods(t,1)},isShow:function(e,t){return"0"==e.assetsStatus},icon:"false"},{name:"接收",method:function(t){e.confirmMethods(t,2)},isShow:function(e,t){return"1"==e.assetsStatus},icon:"false"},{name:"驳回",method:function(t){e.confirmMethods(t,3)},isShow:function(e,t){return"1"==e.assetsStatus||"0"==e.assetsStatus},icon:"false"},{name:"转移",method:function(t){e.actionInfo(t,"trans")},isShow:function(e,t){return"2"==e.assetsStatus},icon:"false"},{name:"报废",method:function(t){e.actionInfo(t,"retirement")},isShow:function(e,t){return"2"==e.assetsStatus},icon:"false"}]}]},paginationObj:{pageSizes:[15,30,50,100]},paginationIsshow:!0,showklTable:!1},pageType:"list",actionType:"trans",formData:{}}},computed:{rightBtnArr:function(){var e=this,t=[{text:"保存",icon:"el-icon-check",hide:"details"==this.pageType,methods:function(){e.doSubmit(2)}},{text:"提交",icon:"el-icon-finished",hide:"details"==this.pageType,methods:function(){e.doSubmit(1)}}];return t}},watch:{pageType:function(e){"list"===e&&this.getTableData()}},mounted:function(){this.getTableData()},methods:{getTableData:function(){var e=this,t=Object.assign({},this.tableConfig.queryObj.queryData);for(var a in t.pageCount=t.pageSize,this.$delete(t,"pageSize"),t)""==t[a]&&delete t[a];Object(o["b"])(t).then((function(t){e.$set(e.tableConfig.tableDataObj,"tableArr",t.data.list||[]),e.$set(e.tableConfig.tableDataObj,"total",t.data.total||0)})).catch((function(t){e.$set(e.tableConfig.tableDataObj,"tableArr",[]),e.$set(e.tableConfig.tableDataObj,"total",0)}))},confirmMethods:function(e,t){var a=this,r={assetId:e.id,opt:t};Object(o["a"])(r).then((function(e){a.getTableData(),a.$message.success("操作成功！")})).catch((function(e){a.$message.success("操作出错！")}))},actionInfo:function(e,t){var a=e.assetTypeName,r=e.goodsModel,s=e.brand,o=e.id,i=e.empId,n=e.staffunName;this.formData={assetTypeName:a,goodsModel:r,brand:s,sourceId:o,beforeEmpId:i,staffunName:n,employeeId:i},this.actionType=t,this.pageType="add"},doSubmit:function(e){var t=this;this.$refs.editForm.getFormData().then((function(a){"trans"==t.actionType?t.doTrans(e,a):"retirement"==t.actionType&&t.doRetirement(e,a)})).catch((function(e){console.log(e)}))},doTrans:function(e,t){var a=this;this.$refs.editForm.getFormData().then((function(t){var r=t.billDate,s=t.confirmMan,o=t.depatcode,i=t.beforeEmpId,n=t.empId,d=t.remark,c=t.sourceId,u=t.id,m={billDate:r,confirmMan:s,depatcode:o,beforeEmpId:i,empId:n,remark:d,sourceId:c,submitType:e,id:u},p=function(e){return e.id?Object(l["e"])(e):Object(l["d"])(e)};p(m).then((function(t){a.$message.success("操作成功，请在资产转移菜单中查询"),2==e?(a.pageType="edit",t.data&&(a.formData.id=t.data),a.$refs.editForm.getDetails(a.formData.id)):a.pageType="list"}))})).catch((function(e){console.log(e)}))},doRetirement:function(e,t){var a=this,r=t.billDate,s=t.confirmMan,o=t.employeeId,i=t.remark,n=t.sourceId,l=t.id,c={billDate:r,confirmMan:s,employeeId:o,remark:i,sourceId:n,submitType:e,id:l},u=function(e){return e.id?Object(d["e"])(e):Object(d["d"])(e)};u(c).then((function(t){a.$message.success("操作成功，请在资产转移菜单中查询"),2==e?(a.pageType="edit",t.data&&(a.formData.id=t.data),a.$refs.editForm.getDetails(a.formData.id)):a.pageType="list"}))},goBackPage:function(e){this.pageType="list"}}},u=c,m=(a("d08a"),a("2877")),p=Object(m["a"])(u,r,s,!1,null,null,null);t["default"]=p.exports},"24d1":function(e,t,a){},4460:function(e,t,a){"use strict";var r=function(){var e=this,t=e.$createElement,a=e._self._c||t;return a("div",{staticClass:"add-info"},[a("div",{staticClass:"record__baseinfo"},[a("dividerTitle",{attrs:{title:"基本信息"}}),a("editForm",{ref:"editForm",attrs:{formConfig:e.formConfig,formData:e.editFormData},scopedSlots:e._u([{key:"assetTypeName",fn:function(t){var r=t.editFormInfo;return[a("el-input",{staticClass:"input-with-select",attrs:{placeholder:"请选择资产信息",readonly:"",disabled:"details"==e.pageType&&!e.isWorkFlowEdit,size:"mini"},model:{value:r.assetTypeName,callback:function(t){e.$set(r,"assetTypeName",t)},expression:"editFormInfo.assetTypeName"}},[a("el-button",{attrs:{slot:"append",icon:"el-icon-search"},on:{click:function(t){return t.stopPropagation(),e.goSeleteAsset(t)}},slot:"append"})],1)]}}])})],1),e.editFormData.status&&2!==e.editFormData.status?a("div",{staticClass:"record__attach"},[a("dividerTitle",{attrs:{title:"流程附件"}}),a("attachTable",{ref:"attachFile",attrs:{attachSetting:e.attachSetting,hideBtn:!e.formData.workflowIsApprover}})],1):e._e(),"details"==e.pageType&&e.editFormData.status&&2!==e.editFormData.status?a("div",{staticClass:"record__workflow"},[a("dividerTitle",{attrs:{title:"工作流"}}),a("workFlow",{attrs:{currentFlowData:e.editFormData,beforeSubmit:e.beforeSubmit,otherParams:e.workFlowParams},on:{submitSuccess:e.workFlowSuccess}})],1):e._e(),e.showSelete?a("assetSelete",{attrs:{hadSeleteData:e.hadSeleteData,showSelete:e.showSelete},on:{changeSelete:e.changeSeleteAsset,"update:showSelete":function(t){e.showSelete=t},"update:show-selete":function(t){e.showSelete=t}}}):e._e()],1)},s=[],o=(a("d81d"),a("b0c0"),a("d3b7"),a("96cf"),a("1da1")),i=a("5530"),n=a("3835"),d=a("a8b1"),l=a("9bda"),c=a("4b54"),u={components:{workFlow:d["a"],assetSelete:l["a"]},props:{formData:{type:Object,default:new Object},pageType:{type:String,default:""},defaultSet:{type:Boolean,default:!1}},data:function(){var e=this;return{formConfig:{itemArr:[{label:"申请日期",prop:"billDate",type:"date",required:!0},{label:"资产分类",prop:"assetTypeName",type:"text",required:!0},{label:"型号",prop:"goodsModel",type:"text",placeholder:"选择资产后自动带出",disabled:!0},{label:"原归属人",prop:"staffunName",type:"text",placeholder:"选择资产后自动带出",disabled:!0},{label:"接收人",prop:"zystaffunName",idsStr:"empId",type:"user",required:!0,trigger:"change",methods:function(t){if(t.length){var a=Object(n["a"])(t,1),r=a[0],s=r.orgNodeCode,o=r.orgNodeName,i=r.name,d=r.code;e.$refs.editForm.setFormItem("depatcode",s),e.$refs.editForm.setFormItem("depatName",o),e.$refs.editForm.setFormItem("confirmMan",d),e.$refs.editForm.setFormItem("confirmManName",i)}}},{label:"接收部门",prop:"depatName",type:"text",placeholder:"自动带出",required:!0,disabled:!0,trigger:"change"},{label:"审批人",prop:"confirmManName",type:"text",placeholder:"自动带出",required:!0,disabled:!0,isHide:"details"==this.pageType&&this.isWorkFlowEdit,trigger:"change"},{label:"转移说明",prop:"remark",type:"textarea",span:24,rows:3}]},beforeSubmit:{methods:function(t){return e.submitBefore(t)}},editFormData:{},showSelete:!1,hadSeleteData:[],attachSetting:{title:"",select:!1,billType:"asset_shift",sourceId:"",btnArr:["add","delete","save"]}}},created:function(){this.init()},computed:{workFlowParams:function(){var e=this.editFormData.procInstType;return 4===this.formData.status&&this.formData.workflowIsApprover?{procInstType:e||"",assignercodes:[this.editFormData.confirmMan]}:{procInstType:e||""}},isWorkFlowEdit:function(){return!(4!==this.formData.status||!this.formData.workflowIsApprover)}},methods:{init:function(){if("add"==this.pageType){var e={};this.defaultSet&&(e=Object(i["a"])({},this.formData)),this.editFormData=Object.assign({},e,{billDate:this.$utils.getNowDay()})}else this.getDetails(this.formData.id);"details"==this.pageType&&(this.isWorkFlowEdit||this.formConfig.itemArr.map((function(e){return e.disabled=!0})),this.attachSetting.sourceId=this.formData.id)},getDetails:function(e){var t=this;return Object(o["a"])(regeneratorRuntime.mark((function a(){var r,s;return regeneratorRuntime.wrap((function(a){while(1)switch(a.prev=a.next){case 0:return a.next=2,Object(c["b"])(e);case 2:r=a.sent,s=r.data||{},t.editFormData=Object.assign({},s,{confirmMan:s.empId,confirmManName:s.zystaffunName});case 5:case"end":return a.stop()}}),a)})))()},getFormData:function(){return this.$refs.editForm.getFormData()},goSeleteAsset:function(){if("details"!=this.pageType){var e=this.$refs.editForm.getFormItem("sourceId");this.hadSeleteData=[{id:e}],this.showSelete=!0}},changeSeleteAsset:function(e){var t=Object(n["a"])(e,1),a=t[0],r=a.assetTypeName,s=a.goodsModel,o=a.brand,i=a.id,d=a.empId,l=a.staffunName;this.$refs.editForm.setFormItem("assetTypeName",r),this.$refs.editForm.setFormItem("goodsModel",s),this.$refs.editForm.setFormItem("brand",o),this.$refs.editForm.setFormItem("sourceId",i),this.$refs.editForm.setFormItem("beforeEmpId",d),this.$refs.editForm.setFormItem("staffunName",l)},submitBefore:function(e){var t=this;return!this.isWorkFlowEdit||new Promise((function(e,a){t.$refs.editForm.getFormData().then((function(r){var s=r.billDate,o=r.confirmMan,i=r.depatcode,n=r.beforeEmpId,d=r.empId,l=r.remark,u=r.sourceId,m=r.id,p={billDate:s,confirmMan:o,depatcode:i,beforeEmpId:n,empId:d,remark:l,sourceId:u,submitType:1,id:m};t.editFormData=Object.assign({},r),Object(c["e"])(p).then((function(t){e()})).catch((function(e){a(e)}))})).catch((function(e){a("表单信息未填写完整")}))}))},workFlowSuccess:function(){this.$emit("update:pageType","list")}}},m=u,p=a("2877"),f=Object(p["a"])(m,r,s,!1,null,null,null);t["a"]=f.exports},"4b54":function(e,t,a){"use strict";a.d(t,"c",(function(){return s})),a.d(t,"d",(function(){return o})),a.d(t,"e",(function(){return i})),a.d(t,"a",(function(){return n})),a.d(t,"b",(function(){return d}));var r=a("fd03");function s(){var e=arguments.length>0&&void 0!==arguments[0]?arguments[0]:{};return Object(r["a"])({url:"/asset/ShiftAction/queryList.htm",method:"post",data:e})}function o(){var e=arguments.length>0&&void 0!==arguments[0]?arguments[0]:{};return Object(r["a"])({url:"/asset/ShiftAction/addShift.htm",method:"post",data:e})}function i(){var e=arguments.length>0&&void 0!==arguments[0]?arguments[0]:{};return Object(r["a"])({url:"/asset/ShiftAction/updateShift.htm",method:"post",data:e})}function n(){var e=arguments.length>0&&void 0!==arguments[0]?arguments[0]:{};return Object(r["a"])({url:"/asset/ShiftAction/delete.htm",method:"post",params:e})}function d(){var e=arguments.length>0&&void 0!==arguments[0]?arguments[0]:"";return Object(r["a"])({url:"/asset/ShiftAction/detail.htm",method:"post",params:{id:e}})}},"9bda":function(e,t,a){"use strict";var r=function(){var e=this,t=e.$createElement,a=e._self._c||t;return a("div",{staticClass:"asset-selete"},[a("dialogPublic",{attrs:{title:"我的资产",show:e.showDialong,fullscreen:"",appendTobody:""},on:{"update:show":function(t){e.showDialong=t},doSubmit:e.doSave,doClose:e.doClose}},[a("seleteTable",{ref:"seleteTable",attrs:{tableSetting:e.tableSetting,hadData:e.hadSeleteData}})],1)],1)},s=[],o=(a("96cf"),a("1da1")),i=a("7b5c"),n=a("a742"),d={props:{hadSeleteData:{type:Array,default:new Array},showSelete:{type:Boolean,default:!0}},components:{seleteTable:i["a"]},data:function(){return{tableSetting:{maxHeight:"600px",column:[{label:"编号",prop:"assetsNumber"},{label:"分类",prop:"assetTypeName"},{label:"品牌",prop:"brand"},{label:"型号",prop:"goodsModel"},{label:"所有人",prop:"staffunName"}],data:[]},showDialong:!0}},created:function(){this.getAssets()},methods:{getAssets:function(){var e=this;return Object(o["a"])(regeneratorRuntime.mark((function t(){var a,r;return regeneratorRuntime.wrap((function(t){while(1)switch(t.prev=t.next){case 0:return t.next=2,Object(n["b"])();case 2:a=t.sent,r=a.data.list,e.$refs.seleteTable.setTableData(r||[]);case 5:case"end":return t.stop()}}),t)})))()},doClose:function(){this.$emit("update:showSelete",!1)},doSave:function(){var e=this.$refs.seleteTable.getSeleteData();1==e.length?(this.$emit("changeSelete",e),this.doClose()):this.$message.warning("请选择一个需要操作的资产！")}}},l=d,c=a("2877"),u=Object(c["a"])(l,r,s,!1,null,null,null);t["a"]=u.exports},b7b0:function(e,t,a){"use strict";a.d(t,"c",(function(){return s})),a.d(t,"d",(function(){return o})),a.d(t,"e",(function(){return i})),a.d(t,"a",(function(){return n})),a.d(t,"b",(function(){return d}));var r=a("fd03");function s(){var e=arguments.length>0&&void 0!==arguments[0]?arguments[0]:{};return Object(r["a"])({url:"/asset/scrappedAction/queryList.htm",method:"post",data:e})}function o(){var e=arguments.length>0&&void 0!==arguments[0]?arguments[0]:{};return Object(r["a"])({url:"/asset/scrappedAction/addScrapped.htm",method:"post",data:e})}function i(){var e=arguments.length>0&&void 0!==arguments[0]?arguments[0]:{};return Object(r["a"])({url:"/asset/scrappedAction/updateScrapped.htm",method:"post",data:e})}function n(){var e=arguments.length>0&&void 0!==arguments[0]?arguments[0]:{};return Object(r["a"])({url:"/asset/scrappedAction/delete.htm",method:"post",params:e})}function d(){var e=arguments.length>0&&void 0!==arguments[0]?arguments[0]:"";return Object(r["a"])({url:"/asset/scrappedAction/detail.htm",method:"post",params:{id:e}})}},d08a:function(e,t,a){"use strict";var r=a("24d1"),s=a.n(r);s.a},f626:function(e,t,a){"use strict";var r=function(){var e=this,t=e.$createElement,a=e._self._c||t;return a("div",{staticClass:"add-info"},[a("div",{staticClass:"record__baseinfo"},[a("dividerTitle",{attrs:{title:"基本信息"}}),a("editForm",{ref:"editForm",attrs:{formConfig:e.formConfig,formData:e.editFormData},scopedSlots:e._u([{key:"assetTypeName",fn:function(t){var r=t.editFormInfo;return[a("el-input",{staticClass:"input-with-select",attrs:{placeholder:"请选择资产信息",readonly:"",disabled:"details"==e.pageType&&!e.isWorkFlowEdit,size:"mini"},model:{value:r.assetTypeName,callback:function(t){e.$set(r,"assetTypeName",t)},expression:"editFormInfo.assetTypeName"}},[a("el-button",{attrs:{slot:"append",icon:"el-icon-search"},on:{click:function(t){return t.stopPropagation(),e.goSeleteAsset(t)}},slot:"append"})],1)]}}])})],1),e.editFormData.status&&2!==e.editFormData.status?a("div",{staticClass:"record__attach"},[a("dividerTitle",{attrs:{title:"流程附件"}}),a("attachTable",{ref:"attachFile",attrs:{attachSetting:e.attachSetting,hideBtn:!e.formData.workflowIsApprover}})],1):e._e(),"details"==e.pageType&&e.editFormData.status&&2!==e.editFormData.status?a("div",{staticClass:"record__workflow"},[a("dividerTitle",{attrs:{title:"工作流"}}),a("workFlow",{attrs:{currentFlowData:e.editFormData,beforeSubmit:e.beforeSubmit,otherParams:e.workFlowParams},on:{submitSuccess:e.workFlowSuccess}})],1):e._e(),e.showSelete?a("assetSelete",{attrs:{hadSeleteData:e.hadSeleteData,showSelete:e.showSelete},on:{changeSelete:e.changeSeleteAsset,"update:showSelete":function(t){e.showSelete=t},"update:show-selete":function(t){e.showSelete=t}}}):e._e()],1)},s=[],o=(a("d81d"),a("d3b7"),a("3835")),i=(a("96cf"),a("1da1")),n=a("5530"),d=a("9bda"),l=a("b7b0"),c={components:{assetSelete:d["a"]},props:{formData:{type:Object,default:new Object},pageType:{type:String,default:""},defaultSet:{type:Boolean,default:!1}},data:function(){var e=this;return{formConfig:{itemArr:[{label:"申请日期",prop:"billDate",type:"date",required:!0},{label:"资产分类",prop:"assetTypeName",type:"text",required:!0},{label:"型号",prop:"goodsModel",type:"text",placeholder:"选择资产后自动带出",disabled:!0},{label:"原归属人",prop:"staffunName",type:"text",placeholder:"选择资产后自动带出",disabled:!0},{label:"审批人",prop:"confirmManName",idsStr:"confirmMan",type:"user",isHide:"details"==this.pageType&&!this.isWorkFlowEdit,required:!0,trigger:"change"},{label:"报废说明",prop:"remark",type:"textarea",span:24,rows:3}]},beforeSubmit:{methods:function(t){return e.submitBefore(t)}},editFormData:{},showSelete:!1,hadSeleteData:[],attachSetting:{title:"",select:!1,billType:"asset_scrapped",sourceId:"",btnArr:["add","delete","save"]}}},created:function(){this.init()},computed:{workFlowParams:function(){var e=this.editFormData.procInstType;return 4===this.formData.status&&this.formData.workflowIsApprover?{procInstType:e||"",assignercodes:[this.editFormData.confirmMan]}:{procInstType:e||""}},isWorkFlowEdit:function(){return!(4!==this.formData.status||!this.formData.workflowIsApprover)}},methods:{init:function(){if("add"==this.pageType){var e={};this.defaultSet&&(e=Object(n["a"])({},this.formData)),this.editFormData=Object.assign({},e,{billDate:this.$utils.getNowDay()})}else this.getDetails(this.formData.id);"details"==this.pageType&&(this.isWorkFlowEdit||this.formConfig.itemArr.map((function(e){return e.disabled=!0})),this.attachSetting.sourceId=this.formData.id)},getDetails:function(e){var t=this;return Object(i["a"])(regeneratorRuntime.mark((function a(){var r,s;return regeneratorRuntime.wrap((function(a){while(1)switch(a.prev=a.next){case 0:return a.next=2,Object(l["b"])(e);case 2:r=a.sent,s=r.data||{},t.editFormData=Object.assign({},s,{confirmMan:s.empId,confirmManName:s.zystaffunName});case 5:case"end":return a.stop()}}),a)})))()},getFormData:function(){return this.$refs.editForm.getFormData()},goSeleteAsset:function(){if("details"!=this.pageType){var e=this.$refs.editForm.getFormItem("sourceId");this.hadSeleteData=[{id:e}],this.showSelete=!0}},changeSeleteAsset:function(e){var t=Object(o["a"])(e,1),a=t[0],r=a.assetTypeName,s=a.goodsModel,i=a.brand,n=a.id,d=a.empId,l=a.staffunName;this.$refs.editForm.setFormItem("assetTypeName",r),this.$refs.editForm.setFormItem("goodsModel",s),this.$refs.editForm.setFormItem("brand",i),this.$refs.editForm.setFormItem("sourceId",n),this.$refs.editForm.setFormItem("employeeId",d),this.$refs.editForm.setFormItem("staffunName",l)},submitBefore:function(e){var t=this;return!this.isWorkFlowEdit||new Promise((function(e,a){t.$refs.editForm.getFormData().then((function(e){var a=e.billDate,r=e.confirmMan,s=e.employeeId,o=e.remark,i=e.sourceId,n=e.id,d={billDate:a,confirmMan:r,employeeId:s,remark:o,sourceId:i,submitType:1,id:n};return t.editFormData=Object.assign({},e),Object(l["e"])(d)})).catch((function(e){a("表单信息未填写完整")}))}))},workFlowSuccess:function(){this.$emit("update:pageType","list")}}},u=c,m=a("2877"),p=Object(m["a"])(u,r,s,!1,null,null,null);t["a"]=p.exports}}]);
//# sourceMappingURL=chunk-05eada22.f63e5c2c.js.map