(window["webpackJsonp"]=window["webpackJsonp"]||[]).push([["chunk-68a9d27a"],{"611b":function(e,t,a){},"9bda":function(e,t,a){"use strict";var o=function(){var e=this,t=e.$createElement,a=e._self._c||t;return a("div",{staticClass:"asset-selete"},[a("dialogPublic",{attrs:{title:"我的资产",show:e.showDialong,fullscreen:"",appendTobody:""},on:{"update:show":function(t){e.showDialong=t},doSubmit:e.doSave,doClose:e.doClose}},[a("seleteTable",{ref:"seleteTable",attrs:{tableSetting:e.tableSetting,hadData:e.hadSeleteData}})],1)],1)},s=[],i=(a("96cf"),a("1da1")),n=a("7b5c"),r=a("a742"),l={props:{hadSeleteData:{type:Array,default:new Array},showSelete:{type:Boolean,default:!0}},components:{seleteTable:n["a"]},data:function(){return{tableSetting:{maxHeight:"600px",column:[{label:"编号",prop:"assetsNumber"},{label:"分类",prop:"assetTypeName"},{label:"品牌",prop:"brand"},{label:"型号",prop:"goodsModel"},{label:"所有人",prop:"staffunName"}],data:[]},showDialong:!0}},created:function(){this.getAssets()},methods:{getAssets:function(){var e=this;return Object(i["a"])(regeneratorRuntime.mark((function t(){var a,o;return regeneratorRuntime.wrap((function(t){while(1)switch(t.prev=t.next){case 0:return t.next=2,Object(r["b"])();case 2:a=t.sent,o=a.data.list,e.$refs.seleteTable.setTableData(o||[]);case 5:case"end":return t.stop()}}),t)})))()},doClose:function(){this.$emit("update:showSelete",!1)},doSave:function(){var e=this.$refs.seleteTable.getSeleteData();1==e.length?(this.$emit("changeSelete",e),this.doClose()):this.$message.warning("请选择一个需要操作的资产！")}}},d=l,c=a("2877"),u=Object(c["a"])(d,o,s,!1,null,null,null);t["a"]=u.exports},"9c52":function(e,t,a){"use strict";a.r(t);var o=function(){var e=this,t=e.$createElement,a=e._self._c||t;return a("div",{staticClass:"page-main-box"},[a("KlTable",e._b({directives:[{name:"show",rawName:"v-show",value:"list"==e.pageType,expression:"pageType == 'list'"}],ref:"klTable"},"KlTable",e.tableConfig,!1)),"list"!==e.pageType?a("editPage",{attrs:{rightBtnArr:e.rightBtnArr},on:{goBackPage:e.goBackPage}},[a("addInfo",{ref:"editForm",attrs:{pageType:e.pageType,formData:e.formData},on:{"update:pageType":function(t){e.pageType=t},"update:page-type":function(t){e.pageType=t}}})],1):e._e()],1)},s=[],i=a("f626"),n=a("b7b0"),r={name:"assetRetirementjs",components:{addInfo:i["a"]},data:function(){var e=this;return{tableConfig:{showFixedHeight:!0,searchObj:{searchMethod:function(t,a){e.tableConfig.tableDataObj&&e.getTableData()},searchArr:[{type:"el-input",label:"单号",model:"billNo",placeholder:"单号"},{type:"date",label:"开始日期",model:"beginDate"},{type:"date",label:"结束日期",model:"endDate"}]},queryObj:{method:function(){e.getTableData()},queryData:{page:1,pageCount:15}},tableDataObj:{tableArr:[],total:null},operateArr:[{name:"报废",icon:"el-icon-plus",method:function(t){e.toAdd()}}],tableObj:{selectionShow:!1,tableMethods:{selection:function(t){e.selectionData=t}},showItems:[{name:"单号",prop:"billNo",width:150},{name:"申请日期",prop:"billDate",width:140},{name:"资产分类",prop:"assetTypeName",width:150},{name:"资产型号",prop:"goodsModel",width:150},{name:"原归属人",prop:"staffunName",width:120},{name:"审批信息",prop:"stage",width:200,scopeType:"formate",align:"left",method:function(e){var t=e.stage,a=e.auditorName;return t?t+" "+a:"-"}},{name:"状态",prop:"status",scopeType:"formate",method:function(t){var a=t.status;return e.$store.getters["getFlowStatus"](a)}},{name:"操作",prop:"operate",width:150,scopeType:"button",fixed:"right",buttonArr:[{name:"详情",method:function(t){e.detailsInfo(t)},isShow:function(e,t){return!0},icon:"false"},{name:"审批",method:function(t){e.detailsInfo(t)},isShow:function(e,t){return e.workflowIsApprover},icon:"false"},{name:"编辑",method:function(t){e.editInfo(t)},isShow:function(e,t){return 2===e.status},icon:"false"},{name:"删除",method:function(t){e.deleteInfo(t)},isShow:function(e,t){return 2===e.status},icon:"false"}]}]},paginationObj:{pageSizes:[15,30,50,100]},paginationIsshow:!0,showklTable:!1},pageType:"list",formData:{}}},computed:{rightBtnArr:function(){var e=this,t=[{text:"保存",icon:"el-icon-check",hide:"details"==this.pageType,methods:function(){e.doSubmit(2)}},{text:"提交",icon:"el-icon-finished",hide:"details"==this.pageType,methods:function(){e.doSubmit(1)}}];return t}},watch:{pageType:function(e){"list"==e&&this.getTableData()}},mounted:function(){this.getTableData()},methods:{toAdd:function(){this.pageType="add",this.formData={}},getTableData:function(){var e=this,t=Object.assign({},this.tableConfig.queryObj.queryData);Object(n["c"])(t).then((function(t){e.$set(e.tableConfig.tableDataObj,"tableArr",t.data.list||[]),e.$set(e.tableConfig.tableDataObj,"total",t.data.total||0)})).catch((function(t){e.$set(e.tableConfig.tableDataObj,"tableArr",[]),e.$set(e.tableConfig.tableDataObj,"total",0)}))},detailsInfo:function(e){this.pageType="details",this.formData=Object.assign({},e)},editInfo:function(e){this.pageType="edit",this.formData=Object.assign({},e)},deleteInfo:function(e){var t=this;this.$confirm("是否确认删除?","提示",{confirmButtonText:"确定",cancelButtonText:"取消",type:"warning"}).then((function(){Object(n["a"])({id:e.id}).then((function(e){t.$message.success("操作成功"),t.getTableData()}))})).catch((function(e){}))},doSubmit:function(e){var t=this;this.$refs.editForm.getFormData().then((function(a){var o=a.billDate,s=a.confirmMan,i=a.employeeId,r=a.remark,l=a.sourceId,d=a.id,c={billDate:o,confirmMan:s,employeeId:i,remark:r,sourceId:l,submitType:e,id:d},u=function(e){return e.id?Object(n["e"])(e):Object(n["d"])(e)};u(c).then((function(a){t.$message.success("操作成功！"),2==e?(t.pageType="edit",a.data&&(t.formData.id=a.data),t.$refs.editForm.getDetails(t.formData.id)):t.pageType="list"}))})).catch((function(e){console.log(e)}))},goBackPage:function(e){this.pageType="list"}}},l=r,d=(a("f687"),a("2877")),c=Object(d["a"])(l,o,s,!1,null,null,null);t["default"]=c.exports},b7b0:function(e,t,a){"use strict";a.d(t,"c",(function(){return s})),a.d(t,"d",(function(){return i})),a.d(t,"e",(function(){return n})),a.d(t,"a",(function(){return r})),a.d(t,"b",(function(){return l}));var o=a("fd03");function s(){var e=arguments.length>0&&void 0!==arguments[0]?arguments[0]:{};return Object(o["a"])({url:"/asset/scrappedAction/queryList.htm",method:"post",data:e})}function i(){var e=arguments.length>0&&void 0!==arguments[0]?arguments[0]:{};return Object(o["a"])({url:"/asset/scrappedAction/addScrapped.htm",method:"post",data:e})}function n(){var e=arguments.length>0&&void 0!==arguments[0]?arguments[0]:{};return Object(o["a"])({url:"/asset/scrappedAction/updateScrapped.htm",method:"post",data:e})}function r(){var e=arguments.length>0&&void 0!==arguments[0]?arguments[0]:{};return Object(o["a"])({url:"/asset/scrappedAction/delete.htm",method:"post",params:e})}function l(){var e=arguments.length>0&&void 0!==arguments[0]?arguments[0]:"";return Object(o["a"])({url:"/asset/scrappedAction/detail.htm",method:"post",params:{id:e}})}},f626:function(e,t,a){"use strict";var o=function(){var e=this,t=e.$createElement,a=e._self._c||t;return a("div",{staticClass:"add-info"},[a("div",{staticClass:"record__baseinfo"},[a("dividerTitle",{attrs:{title:"基本信息"}}),a("editForm",{ref:"editForm",attrs:{formConfig:e.formConfig,formData:e.editFormData},scopedSlots:e._u([{key:"assetTypeName",fn:function(t){var o=t.editFormInfo;return[a("el-input",{staticClass:"input-with-select",attrs:{placeholder:"请选择资产信息",readonly:"",disabled:"details"==e.pageType&&!e.isWorkFlowEdit,size:"mini"},model:{value:o.assetTypeName,callback:function(t){e.$set(o,"assetTypeName",t)},expression:"editFormInfo.assetTypeName"}},[a("el-button",{attrs:{slot:"append",icon:"el-icon-search"},on:{click:function(t){return t.stopPropagation(),e.goSeleteAsset(t)}},slot:"append"})],1)]}}])})],1),e.editFormData.status&&2!==e.editFormData.status?a("div",{staticClass:"record__attach"},[a("dividerTitle",{attrs:{title:"流程附件"}}),a("attachTable",{ref:"attachFile",attrs:{attachSetting:e.attachSetting,hideBtn:!e.formData.workflowIsApprover}})],1):e._e(),"details"==e.pageType&&e.editFormData.status&&2!==e.editFormData.status?a("div",{staticClass:"record__workflow"},[a("dividerTitle",{attrs:{title:"工作流"}}),a("workFlow",{attrs:{currentFlowData:e.editFormData,beforeSubmit:e.beforeSubmit,otherParams:e.workFlowParams},on:{submitSuccess:e.workFlowSuccess}})],1):e._e(),e.showSelete?a("assetSelete",{attrs:{hadSeleteData:e.hadSeleteData,showSelete:e.showSelete},on:{changeSelete:e.changeSeleteAsset,"update:showSelete":function(t){e.showSelete=t},"update:show-selete":function(t){e.showSelete=t}}}):e._e()],1)},s=[],i=(a("d81d"),a("d3b7"),a("3835")),n=(a("96cf"),a("1da1")),r=a("5530"),l=a("9bda"),d=a("b7b0"),c={components:{assetSelete:l["a"]},props:{formData:{type:Object,default:new Object},pageType:{type:String,default:""},defaultSet:{type:Boolean,default:!1}},data:function(){var e=this;return{formConfig:{itemArr:[{label:"申请日期",prop:"billDate",type:"date",required:!0},{label:"资产分类",prop:"assetTypeName",type:"text",required:!0},{label:"型号",prop:"goodsModel",type:"text",placeholder:"选择资产后自动带出",disabled:!0},{label:"原归属人",prop:"staffunName",type:"text",placeholder:"选择资产后自动带出",disabled:!0},{label:"审批人",prop:"confirmManName",idsStr:"confirmMan",type:"user",isHide:"details"==this.pageType&&!this.isWorkFlowEdit,required:!0,trigger:"change"},{label:"报废说明",prop:"remark",type:"textarea",span:24,rows:3}]},beforeSubmit:{methods:function(t){return e.submitBefore(t)}},editFormData:{},showSelete:!1,hadSeleteData:[],attachSetting:{title:"",select:!1,billType:"asset_scrapped",sourceId:"",btnArr:["add","delete","save"]}}},created:function(){this.init()},computed:{workFlowParams:function(){var e=this.editFormData.procInstType;return 4===this.formData.status&&this.formData.workflowIsApprover?{procInstType:e||"",assignercodes:[this.editFormData.confirmMan]}:{procInstType:e||""}},isWorkFlowEdit:function(){return!(4!==this.formData.status||!this.formData.workflowIsApprover)}},methods:{init:function(){if("add"==this.pageType){var e={};this.defaultSet&&(e=Object(r["a"])({},this.formData)),this.editFormData=Object.assign({},e,{billDate:this.$utils.getNowDay()})}else this.getDetails(this.formData.id);"details"==this.pageType&&(this.isWorkFlowEdit||this.formConfig.itemArr.map((function(e){return e.disabled=!0})),this.attachSetting.sourceId=this.formData.id)},getDetails:function(e){var t=this;return Object(n["a"])(regeneratorRuntime.mark((function a(){var o,s;return regeneratorRuntime.wrap((function(a){while(1)switch(a.prev=a.next){case 0:return a.next=2,Object(d["b"])(e);case 2:o=a.sent,s=o.data||{},t.editFormData=Object.assign({},s,{confirmMan:s.empId,confirmManName:s.zystaffunName});case 5:case"end":return a.stop()}}),a)})))()},getFormData:function(){return this.$refs.editForm.getFormData()},goSeleteAsset:function(){if("details"!=this.pageType){var e=this.$refs.editForm.getFormItem("sourceId");this.hadSeleteData=[{id:e}],this.showSelete=!0}},changeSeleteAsset:function(e){var t=Object(i["a"])(e,1),a=t[0],o=a.assetTypeName,s=a.goodsModel,n=a.brand,r=a.id,l=a.empId,d=a.staffunName;this.$refs.editForm.setFormItem("assetTypeName",o),this.$refs.editForm.setFormItem("goodsModel",s),this.$refs.editForm.setFormItem("brand",n),this.$refs.editForm.setFormItem("sourceId",r),this.$refs.editForm.setFormItem("employeeId",l),this.$refs.editForm.setFormItem("staffunName",d)},submitBefore:function(e){var t=this;return!this.isWorkFlowEdit||new Promise((function(e,a){t.$refs.editForm.getFormData().then((function(e){var a=e.billDate,o=e.confirmMan,s=e.employeeId,i=e.remark,n=e.sourceId,r=e.id,l={billDate:a,confirmMan:o,employeeId:s,remark:i,sourceId:n,submitType:1,id:r};return t.editFormData=Object.assign({},e),Object(d["e"])(l)})).catch((function(e){a("表单信息未填写完整")}))}))},workFlowSuccess:function(){this.$emit("update:pageType","list")}}},u=c,p=a("2877"),f=Object(p["a"])(u,o,s,!1,null,null,null);t["a"]=f.exports},f687:function(e,t,a){"use strict";var o=a("611b"),s=a.n(o);s.a}}]);
//# sourceMappingURL=chunk-68a9d27a.314e6e52.js.map