(window["webpackJsonp"]=window["webpackJsonp"]||[]).push([["chunk-2d0c934c"],{"57c7":function(e,t,a){"use strict";a.r(t);var o=function(){var e=this,t=e.$createElement,a=e._self._c||t;return a("div",{staticClass:"spareStorage h100"},[a("div",{staticClass:"page-main-box"},["list"==e.pageType?a("KlTable",e._b({ref:"klTable"},"KlTable",e.tableConfig,!1)):e._e(),"list"!==e.pageType?a("editPage",{attrs:{rightBtnArr:e.rightBtnArr},on:{goBackPage:e.goBackPage}},[a("addInfo",{ref:"editForm",attrs:{pageType:e.pageType,formData:e.formData}})],1):e._e()],1)])},i=[],r=a("3835"),s=function(){var e=this,t=e.$createElement,a=e._self._c||t;return a("div",{staticClass:"record__content"},[a("div",{staticClass:"record__baseinfo"},[a("dividerTitle",{attrs:{title:"基本信息"}}),a("editForm",{ref:"editForm",attrs:{formConfig:e.formConfig,formData:e.editFormData},scopedSlots:e._u([{key:"orderNo",fn:function(t){var o=t.editFormInfo;return[a("el-input",{staticClass:"input-with-select",attrs:{placeholder:"请选择入库单号",readonly:"",disabled:"details"==e.pageType&&!e.isWorkFlowEdit,size:"mini"},model:{value:o.orderNo,callback:function(t){e.$set(o,"orderNo",t)},expression:"editFormInfo.orderNo"}},[a("el-button",{attrs:{slot:"append",icon:"el-icon-search"},on:{click:function(t){return t.stopPropagation(),e.getGoodsModel(t)}},slot:"append"})],1)]}},{key:"serviceOrderNo",fn:function(t){var o=t.editFormInfo;return[a("el-input",{staticClass:"input-with-select",attrs:{placeholder:"请选择服务工单号",readonly:"",disabled:"details"==e.pageType&&!e.isWorkFlowEdit,size:"mini"},model:{value:o.serviceOrderNo,callback:function(t){e.$set(o,"serviceOrderNo",t)},expression:"editFormInfo.serviceOrderNo"}},[a("el-button",{attrs:{slot:"append",icon:"el-icon-search"},on:{click:function(t){return t.stopPropagation(),e.getServiceType(t)}},slot:"append"})],1)]}}])})],1),e.editFormData.status&&1==e.editFormData.status?a("div",{staticClass:"process_score"},[a("dividerTitle",{attrs:{title:"评分"}}),e.showScore?a("processScore",{attrs:{scoreSetting:e.scoreSetting,hideBtn:e.scoreHideBtn,showScore:e.showScore},on:{"update:showScore":function(t){e.showScore=t},"update:show-score":function(t){e.showScore=t}}}):e._e()],1):e._e(),a("div",{staticClass:"record__attach"},[a("dividerTitle",{attrs:{title:"附件"}}),a("attachTable",{ref:"attachFile",attrs:{attachSetting:e.attachSetting,hideBtn:e.hideBtn}})],1),"details"==e.pageType&&e.editFormData.status&&2!==e.editFormData.status?a("div",{staticClass:"record__workflow"},[a("dividerTitle",{attrs:{title:"工作流"}}),a("workFlow",{attrs:{currentFlowData:e.editFormData,beforeSubmit:e.beforeSubmit,otherParams:e.workFlowParams},on:{submitSuccess:e.workFlowSuccess}})],1):e._e(),e.showSelete?a("instockSelect",{attrs:{hadSeleteData:e.hadSeleteData,showSelete:e.showSelete},on:{changeSelete:e.selectInstockId,"update:showSelete":function(t){e.showSelete=t},"update:show-selete":function(t){e.showSelete=t}}}):e._e(),e.serviceNumberSelete?a("serviceNumberSelect",{attrs:{hadSeleteData:e.hadSeleteData,showSelete:e.serviceNumberSelete,userID:e.employeeId},on:{changeSelete:e.changeServiceType,"update:showSelete":function(t){e.serviceNumberSelete=t},"update:show-selete":function(t){e.serviceNumberSelete=t}}}):e._e()],1)},n=[],d=(a("d81d"),a("d3b7"),a("96cf"),a("1da1")),c=a("5530"),l=a("fd03");function p(){var e=arguments.length>0&&void 0!==arguments[0]?arguments[0]:{};return Object(l["a"])({url:"/maintain/SpareOutstockAction/queryList.htm",method:"post",data:e})}function u(){var e=arguments.length>0&&void 0!==arguments[0]?arguments[0]:"";return Object(l["a"])({url:"/maintain/SpareOutstockAction/detail.htm",method:"post",params:{id:e}})}function m(){var e=arguments.length>0&&void 0!==arguments[0]?arguments[0]:"";return Object(l["a"])({url:"/maintain/SpareInstockAction/delete.htm",method:"post",params:{id:e}})}function h(){var e=arguments.length>0&&void 0!==arguments[0]?arguments[0]:{};return Object(l["a"])({url:"/maintain/SpareOutstockAction/addSpareOutstock.htm",method:"post",data:e})}function f(){var e=arguments.length>0&&void 0!==arguments[0]?arguments[0]:{};return Object(l["a"])({url:"/maintain/SpareOutstockAction/updateSpareOutstock.htm",method:"post",data:e})}var g={props:{formData:{type:Object,default:new Object},pageType:{type:String,default:""},defaultSet:{type:Boolean,default:!1}},data:function(){var e=this;return{hadSeleteData:[],employeeId:null,showSelete:!1,serviceNumberSelete:!1,editFormData:{suitableWorkArr:[]},trainingType:{},formConfig:{itemArr:[{label:"领用人",placeholder:"请选择领用人",prop:"staffunName",idsStr:"employeeId",type:"user",trigger:"change",required:!0,methods:function(t){var a=t[0],o=a.orgNodeName,i=a.orgNodeCode,r=(a.mobile,a.id);e.employeeId=r,e.$refs.editForm.setFormItem("depatName",o),e.$refs.editForm.setFormItem("depatCode",i)}},{label:"领用部门",prop:"depatName",id:"depatCode",disabled:!0,placeholder:"选择领用人后自动带出",type:"text"},{label:"入库单号",prop:"orderNo",required:!0,span:8},{type:"input",label:"备件型号",placeholder:"选择入库单号后自动带出",prop:"goodsModel",id:"instockId"},{type:"input",label:"备件类型",placeholder:"选择备件型号后自动带出",prop:"assetTypeName",id:"assetTypeId",disabled:!0},{label:"服务工单号",prop:"serviceOrderNo",idsStr:"serviceId",type:"text"},{label:"服务类型",prop:"serviceType",type:"text",placeholder:"选择服务工单号后自动填入",disabled:!0},{label:"领用数量",prop:"qty",type:"el-number",required:!0,min:0,max:9999,precision:0,step:1},{label:"领用说明",prop:"remark",type:"textarea",span:24,rows:4}]},procInstType:null,attachSetting:{title:"",select:!1,billType:this.procInstType,sourceId:"",btnArr:["add","delete","save"]},scoreSetting:{title:"",billType:this.procInstType,sourceId:"",data:[],btnArr:["save"]},scoreHideBtn:!1,coverList:[],dialogVisible:!1,activeName:"0",hideBtn:!1,showScore:!1,maxNum:0,beforeSubmit:{methods:function(t){return e.submitBefore(t)}}}},computed:{workFlowParams:function(){var e=this.editFormData.procInstType;return 4===this.formData.status&&this.formData.workflowIsApprover?{procInstType:e||"",assignercodes:[this.editFormData.confirmMan]}:{procInstType:e||""}},isWorkFlowEdit:function(){return!(4!==this.formData.status||!this.formData.workflowIsApprover)}},mounted:function(){this.init()},methods:{init:function(){if("add"==this.pageType){var e={};this.defaultSet&&(e=Object(c["a"])({},this.formData)),this.editFormData=Object.assign({},e,{billDate:this.$utils.getNowDay(),qty:1})}else this.getDetails(this.formData.id);"details"==this.pageType&&(this.isWorkFlowEdit||this.formConfig.itemArr.map((function(e){return e.disabled=!0})),this.attachSetting.sourceId=this.formData.id),"score"==this.pageType&&(this.scoreSetting.sourceId=this.formData.id,this.formConfig.itemArr.map((function(e){return e.disabled=!0})),this.showScore=!0)},getDetails:function(e){var t=this;return Object(d["a"])(regeneratorRuntime.mark((function a(){var o,i;return regeneratorRuntime.wrap((function(a){while(1)switch(a.prev=a.next){case 0:return a.next=2,u(e);case 2:o=a.sent,i=o.data||{},t.procInstType=i.procInstType,t.attachSetting.sourceId=i.id,t.editFormData=Object.assign({},i,{confirmMan:i.empId,confirmManName:i.zystaffunName});case 7:case"end":return a.stop()}}),a)})))()},submitBefore:function(e){var t=this;return!this.isWorkFlowEdit||new Promise((function(e,a){t.$refs.editForm.getFormData().then((function(o){t.$set(o,"submitType",1),t.editFormData=Object.assign({},o),updateStorage(o).then((function(t){e()})).catch((function(e){a(e)}))})).catch((function(e){console.log(e),a("表单信息未填写完整")}))}))},workFlowSuccess:function(){this.$emit("update:pageType","list")},getFormData:function(){return this.$refs.editForm.getFormData()},getGoodsModel:function(){if("details"!=this.pageType){var e=this.$refs.editForm.getFormItem("sourceId");this.hadSeleteData=[{id:e}],this.showSelete=!0}},selectInstockId:function(e){var t=Object(r["a"])(e,1),a=t[0];console.log(e);var o=a.orderNo,i=a.assetTypeName,s=a.assetTypeId,n=a.goodsModel,d=a.id,c=a.qty;this.maxNum=c,this.$refs.editForm.setFormItem("goodsModel",n),this.$refs.editForm.setFormItem("instockId",d),this.$refs.editForm.setFormItem("orderNo",o),this.$refs.editForm.setFormItem("assetTypeName",i),this.$refs.editForm.setFormItem("assetTypeId",s)},getServiceType:function(){if("details"!=this.pageType){var e=this.$refs.editForm.getFormItem("sourceId");this.hadSeleteData=[{id:e}],this.serviceNumberSelete=!0}},changeServiceType:function(e){console.log(e);var t=Object(r["a"])(e,1),a=t[0],o=a.id,i=a.orderNo,s=a.procInstType;this.$refs.editForm.setFormItem("serviceOrderNo",i),this.$refs.editForm.setFormItem("serviceId",o),this.$refs.editForm.setFormItem("serviceType",s)}}},b=g,y=a("2877"),S=Object(y["a"])(b,s,n,!1,null,"0d901e8e",null),v=S.exports,w=(a("2934"),{name:"spareRecipients",components:{addInfo:v},data:function(){var e=this;return{label:"",maxNum:0,otherInfo:"",showMember:!1,currentItem:{},orgMemberSelect:{},tableConfig:{showFixedHeight:!0,searchObj:{searchMethod:function(t,a){e.tableConfig.tableDataObj&&e.getTableData()},searchArr:[{type:"el-select",label:"备件类型",model:"assetTypeId",option:this.$store.getters.spareTypeList},{type:"el-input",label:"备件型号",model:"goodsModel",placeholder:"型号"},{type:"daterange",label:"申请日期",model:"billDate"},{type:"el-select",label:"审核状态",model:"status",placeholder:"审核状态",option:this.$store.getters["dirtData"]("flow_type")}]},queryObj:{method:function(){e.getTableData()},queryData:{page:1,pageSize:15}},tableDataObj:{tableArr:[],total:null},operateArr:[{name:"新建",icon:"el-icon-plus",method:function(t){e.toAdd()}}],tableObj:{selectionShow:!1,indexShow:!0,tableMethods:{selection:function(t){e.selectionData=t}},showItems:[{name:"领用单号",prop:"orderNo"},{name:"入库单号",prop:"instockNo"},{name:"备件类型",prop:"assetTypeName"},{name:"备件型号",prop:"goodsModel"},{name:"领用人",prop:"staffunName",id:"employeeId"},{name:"所属部门",prop:"depatName",id:"depatCode"},{name:"领用数量",prop:"qty"},{name:"领用说明",prop:"remark"},{name:"审批人",prop:"auditorName",id:"auditor"},{name:"流程节点",prop:"stage"},{name:"流程状态",prop:"flowStatusName",id:"status"},{name:"操作",prop:"operate",width:150,scopeType:"button",fixed:"right",buttonArr:[{name:"详情",method:function(t){e.detailsInfo(t)},isShow:function(e,t){return!0},icon:"false"},{name:"审批",method:function(t){e.detailsInfo(t)},isShow:function(e,t){return e.workflowIsApprover},icon:"false"},{name:"编辑",method:function(t){e.editInfo(t)},isShow:function(e,t){return 2===e.status},icon:"false"},{name:"评分",method:function(t){e.score(t)},isShow:function(e,t){return 1===e.status},icon:"false"},{name:"删除",method:function(t){e.deleteInfo(t)},isShow:function(e,t){return 2===e.status},icon:"false"}]}]},paginationObj:{pageSizes:[15,30,50,100]},paginationIsshow:!0,showklTable:!1},pageType:"list",formData:{}}},computed:{rightBtnArr:function(){var e=this,t=[{text:"保存",icon:"el-icon-check",hide:"details"==this.pageType,methods:function(){e.doSubmit(2)}},{text:"提交",icon:"el-icon-finished",hide:"details"==this.pageType,methods:function(){e.doSubmit(1)}}];return t}},watch:{"tableConfig.queryObj.queryData.staffunName":function(e){""==e&&(this.tableConfig.queryObj.queryData.empId=null)},pageType:function(e){"list"===e&&this.getTableData()}},mounted:function(){this.getTableData()},methods:{toAdd:function(){this.pageType="add",this.formData={}},getTableData:function(){var e=this,t=Object.assign({},this.tableConfig.queryObj.queryData);t.pageCount=t.pageSize;var a=t.billDate;if(a){var o=Object(r["a"])(a,2),i=o[0],s=o[1];t.beginDate=i,t.endDate=s}this.$delete(t,"billDate"),this.$delete(t,"pageSize"),this.$delete(t,"staffunName"),p(t).then((function(t){e.$set(e.tableConfig.tableDataObj,"tableArr",t.data.list||[]),e.$set(e.tableConfig.tableDataObj,"total",t.data.total||0)})).catch((function(t){e.$set(e.tableConfig.tableDataObj,"tableArr",[]),e.$set(e.tableConfig.tableDataObj,"total",0)}))},detailsInfo:function(e){this.pageType="details",this.formData=Object.assign({},e)},editInfo:function(e){this.pageType="edit",this.formData=Object.assign({},e)},score:function(e){this.pageType="score",this.formData=Object.assign({},e)},deleteInfo:function(e){var t=this;this.$confirm("是否确认删除","提示",{confirmButtonText:"确定",cancelButtonText:"取消",type:"warning"}).then((function(){m(e.id).then((function(e){t.$message.success("操作成功"),t.getTableData()}))})).catch((function(e){}))},doSubmit:function(e){var t=this;this.$refs.editForm.getFormData().then((function(a){t.$set(a,"submitType",e),t.$delete(a,"assetsNumber"),t.$delete(a,"appsysName");var o=function(e){return e.id?f(e):h(e)};o(a).then((function(a){t.$message.success("操作成功！"),2==e?(t.pageType="edit",a.data&&(t.formData.id=a.data),t.$refs.editForm.getDetails(t.formData.id)):t.pageType="list"}))})).catch((function(e){}))},goBackPage:function(e){this.pageType="list"}}}),D=w,T=Object(y["a"])(D,o,i,!1,null,null,null);t["default"]=T.exports}}]);
//# sourceMappingURL=chunk-2d0c934c.369980c6.js.map