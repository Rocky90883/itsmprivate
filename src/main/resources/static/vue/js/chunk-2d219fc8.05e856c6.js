(window["webpackJsonp"]=window["webpackJsonp"]||[]).push([["chunk-2d219fc8"],{ba1d:function(t,e,a){"use strict";a.r(e);var o=function(){var t=this,e=t.$createElement,a=t._self._c||e;return a("div",{staticClass:"assetRegister h100"},[a("div",{staticClass:"page-main-box"},[a("KlTable",t._b({ref:"klTable"},"KlTable",t.tableConfig,!1))],1),a("dialogPublic",{attrs:{title:"新增服务记录",show:t.recordDialog,appendTobody:"",width:"800px",height:"400px"},on:{"update:show":function(e){t.recordDialog=e},doSubmit:t.recordSubmit,doClose:function(){return t.recordDialog=!1}}},[a("addRecordDialog",{ref:"distributionFrom",attrs:{recordData:t.recordData,pageType:t.pageType}})],1)],1)},n=[],r=(a("d3b7"),a("3835")),i=function(){var t=this,e=t.$createElement,a=t._self._c||e;return a("div",{staticClass:"distributionDialog"},[a("editForm",{ref:"editForm",attrs:{formConfig:t.formConfig,formData:t.editFormData}})],1)},s=[],c=(a("d81d"),{props:{recordData:{type:Object,default:{}},pageType:{type:String,default:""}},name:"distributionDialog",data:function(){var t=this;return{editFormData:{},formConfig:{itemArr:[{label:"记录日期",prop:"billDate",type:"date",span:24},{label:"记录人",prop:"empName",idsStr:"employeeId",type:"user",trigger:"change",span:24},{label:"服务记录类型",prop:"apptype",type:"select",seleteOption:this.$store.getters["dirtData"]("record_type"),formatter:function(e){return t.$store.getters["fineDirtDataName"]("record_type",e)},span:24},{label:"来访电话",prop:"phone",type:"text",span:24,rules:{validator:function(t,e,a){var o=/^[0-9]*$/;if(e){if(!o.test(e))return a(new Error("请填写正确的手机号码！"));a()}else;},trigger:"change"}},{label:"登记描述",prop:"appContent",type:"textarea",span:24,rows:4},{label:"备注",prop:"remark",type:"textarea",span:24,rows:4}]}}},watch:{recordData:function(t){this.editFormData=Object.assign({},t)},pageType:function(t){this.init()}},created:function(){this.init()},methods:{init:function(){"details"==this.pageType?this.formConfig.itemArr.map((function(t){return t.disabled=!0})):this.formConfig.itemArr.map((function(t){return t.disabled=!1})),this.editFormData=Object.assign({},this.recordData)},getFormData:function(){var t=this;return new Promise((function(e,a){t.$refs.editForm.getFormData().then((function(t){t=Object.assign({},t),e(t)})).catch((function(t){a(t)}))}))}}}),l=c,d=a("2877"),p=Object(d["a"])(l,i,s,!1,null,null,null),u=p.exports,f=a("fd03");function h(){var t=arguments.length>0&&void 0!==arguments[0]?arguments[0]:{};return Object(f["a"])({url:"/workorder/RecordappAction/queryList.htm",method:"post",data:t})}function g(){var t=arguments.length>0&&void 0!==arguments[0]?arguments[0]:"";return Object(f["a"])({url:"/workorder/RecordappAction/delete.htm",method:"post",params:{id:t}})}function m(){var t=arguments.length>0&&void 0!==arguments[0]?arguments[0]:{};return Object(f["a"])({url:"/workorder/RecordappAction/addRecordapp.htm",method:"post",data:t})}function b(){var t=arguments.length>0&&void 0!==arguments[0]?arguments[0]:{};return Object(f["a"])({url:"/workorder/RecordappAction/updateRecordapp.htm",method:"post",data:t})}var D={name:"assetRegister",components:{addRecordDialog:u},data:function(){var t=this;return{label:"",currentItem:{},recordDialog:!1,recordData:{},tableConfig:{showFixedHeight:!0,searchObj:{searchMethod:function(e,a){t.tableConfig.tableDataObj&&t.getTableData()},searchArr:[{type:"daterange",label:"日期选择",model:"billDate"},{type:"el-select",label:"记录类型",model:"apptype",option:this.$store.getters["dirtData"]("record_type")},{type:"el-input",label:"记录人名称",model:"empName",placeholder:"记录人名称"}]},queryObj:{method:function(){t.getTableData()},queryData:{page:1,pageSize:15}},tableDataObj:{tableArr:[],total:null},operateArr:[{name:"新建",icon:"el-icon-plus",method:function(e){t.toAdd()}}],tableObj:{selectionShow:!1,indexShow:!0,tableMethods:{selection:function(e){t.selectionData=e}},showItems:[{name:"服务单号",prop:"orderNo"},{name:"来访电话",prop:"phone"},{name:"服务记录类型",prop:"apptype",scopeType:"formate",method:function(e){var a=e.apptype;return t.$store.getters["fineDirtDataName"]("record_type",a)}},{name:"记录人",prop:"empName",id:"employeeId"},{name:"记录日期",prop:"billDate"},{name:"备注",prop:"remark"},{name:"操作",prop:"operate",width:150,scopeType:"button",fixed:"right",buttonArr:[{name:"详情",method:function(e){t.detailsInfo(e)},isShow:function(t,e){return!0},icon:"false"},{name:"编辑",method:function(e){t.editInfo(e)},isShow:function(t,e){return!0},icon:"false"},{name:"删除",method:function(e){t.deleteInfo(e)},isShow:function(t,e){return!0},icon:"false"}]}]},paginationObj:{pageSizes:[15,30,50,100]},paginationIsshow:!0,showklTable:!1},pageType:"list"}},watch:{pageType:function(t){"list"===t&&this.getTableData()}},mounted:function(){this.getTableData()},methods:{toAdd:function(){this.pageType="add",this.recordData={},this.recordDialog=!0},getTableData:function(){var t=this,e=Object.assign({},this.tableConfig.queryObj.queryData);e.pageCount=e.pageSize;var a=e.billDate;if(a){var o=Object(r["a"])(a,2),n=o[0],i=o[1];e.beginDate=n,e.endDate=i}this.$delete(e,"billDate"),this.$delete(e,"pageSize"),this.$delete(e,"staffunName"),h(e).then((function(e){t.$set(t.tableConfig.tableDataObj,"tableArr",e.data.list||[]),t.$set(t.tableConfig.tableDataObj,"total",e.data.total||0)})).catch((function(e){t.$set(t.tableConfig.tableDataObj,"tableArr",[]),t.$set(t.tableConfig.tableDataObj,"total",0)}))},detailsInfo:function(t){this.pageType="details",this.recordData=t||{},this.recordDialog=!0},editInfo:function(t){this.recordData=t||{},this.pageType="edit",this.recordDialog=!0},deleteInfo:function(t){var e=this;this.$confirm("是否确认删除","提示",{confirmButtonText:"确定",cancelButtonText:"取消",type:"warning"}).then((function(){g(t.id).then((function(t){e.$message.success("操作成功"),e.getTableData()}))})).catch((function(t){}))},recordSubmit:function(){var t=this;"details"==this.pageType?this.recordDialog=!1:this.$refs.distributionFrom.getFormData().then((function(e){t.addRecord(e).then((function(e){t.$message.success("操作成功！"),t.recordDialog=!1,t.pageType="list",t.getTableData()}))})).catch((function(t){console.log(t)}))},addRecord:function(t){var e=function(t){return t.orderNo?b(t):m(t)};return new Promise((function(a,o){e(t).then((function(t){a(t)})).catch((function(t){console.log(t),o(t)}))}))},addMethons:function(t){return this.$delete(t,"staffunName"),this.$delete(t,"overQty"),t.id?new Promise((function(e,a){b(t).then((function(t){e(t)})).catch((function(t){console.log(t),a(t)}))})):new Promise((function(e,a){m(t).then((function(t){e(t)})).catch((function(t){console.log(t),a(t)}))}))}}},y=D,w=Object(d["a"])(y,o,n,!1,null,null,null);e["default"]=w.exports}}]);
//# sourceMappingURL=chunk-2d219fc8.05e856c6.js.map