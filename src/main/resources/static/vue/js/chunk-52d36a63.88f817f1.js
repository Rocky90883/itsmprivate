(window["webpackJsonp"]=window["webpackJsonp"]||[]).push([["chunk-52d36a63"],{"09a3":function(t,a,e){},2523:function(t,a,e){"use strict";var s=e("38f9"),n=e.n(s);n.a},"38f9":function(t,a,e){},"3f5a":function(t,a,e){"use strict";var s=e("09a3"),n=e.n(s);n.a},"843c":function(t,a,e){},"962b":function(t,a,e){},9668:function(t,a,e){"use strict";var s=e("962b"),n=e.n(s);n.a},cf87:function(t,a,e){},dabe:function(t,a,e){},ddba:function(t,a,e){"use strict";var s=e("843c"),n=e.n(s);n.a},df50:function(t,a,e){"use strict";e.r(a);var s=function(){var t=this,a=t.$createElement,e=t._self._c||a;return e("div",{staticClass:"index_container w100 h100"},[e("el-row",{staticClass:"h50 index-page-row"},[e("el-col",{staticClass:"h100 index-page-col",attrs:{span:12}},[e("myAssets",{staticClass:"h100"})],1),e("el-col",{staticClass:"h100 index-page-col",attrs:{span:12}},[e("backlog",{staticClass:"h100"})],1)],1),e("el-row",{staticClass:"h50  index-page-row"},[e("el-col",{staticClass:"h100 index-page-col",attrs:{span:12}},[e("serviceRecord",{staticClass:"h100"})],1),e("el-col",{staticClass:"h100 index-page-col",attrs:{span:12}},[e("commonTools",{staticClass:"h100"})],1)],1)],1)},n=[],c=function(){var t=this,a=t.$createElement,e=t._self._c||a;return e("cardBox",{attrs:{cardTitle:t.cardTitle}},[e("div",{attrs:{slot:"card-headr-right"},slot:"card-headr-right"},[e("div",{staticClass:"more",on:{click:t.gotoMenu}},[t._v(" 查看更多 > ")])]),e("div",{staticClass:"backlog-content",attrs:{slot:"card-content"},slot:"card-content"},[e("ul",[t.list.length?t._l(t.list,(function(a,s){return e("li",{key:s,staticClass:"content-list--li",class:{"bg-gray":s%2!==0},attrs:{title:a.title},on:{click:function(e){return t.jumpRouter(a)}}},[e("div",{staticClass:"content-list__title"},[t._v(" "+t._s(s+1)+". "+t._s(a.title)+" ")]),e("div",{staticClass:"content-list__date"},[t._v(t._s(a.date)+" ")])])})):e("li",{staticClass:"no-data"},[t._v("暂无数据")])],2)])])},i=[],o={name:"backlog",data:function(){return{cardTitle:"我的待办",list:[{title:"ITMS服务系统ITMS服务系统ITMS服务系统ITMS服务系统ITMS服务系统",date:"2020-10-16",read:!1},{title:"ITMS服务系统ITMS服务系统ITMS服务系统ITMS服务系统ITMS服务系统",date:"2020-10-16",read:!1},{title:"ITMS服务系统ITMS服务系统ITMS服务系统ITMS服务系统ITMS服务系统",date:"2020-10-16",read:!1},{title:"ITMS服务系统ITMS服务系统ITMS服务系统ITMS服务系统ITMS服务系统",date:"2020-10-16",read:!1},{title:"ITMS服务系统ITMS服务系统ITMS服务系统ITMS服务系统ITMS服务系统",date:"2020-10-16",read:!1},{title:"ITMS服务系统ITMS服务系统ITMS服务系统ITMS服务系统ITMS服务系统",date:"2020-10-16",read:!1},{title:"ITMS服务系统ITMS服务系统ITMS服务系统ITMS服务系统ITMS服务系统",date:"2020-10-16",read:!1},{title:"ITMS服务系统ITMS服务系统ITMS服务系统ITMS服务系统ITMS服务系统",date:"2020-10-16",read:!1},{title:"ITMS服务系统ITMS服务系统ITMS服务系统ITMS服务系统ITMS服务系统",date:"2020-10-16",read:!1},{title:"ITMS服务系统ITMS服务系统ITMS服务系统ITMS服务系统ITMS服务系统",date:"2020-10-16",read:!1}]}},methods:{jumpRouter:function(t){},gotoMenu:function(){}}},l=o,r=(e("e4b5"),e("2877")),d=Object(r["a"])(l,c,i,!1,null,null,null),u=d.exports,m=function(){var t=this,a=t.$createElement,e=t._self._c||a;return e("cardBox",{attrs:{cardTitle:t.cardTitle}},[e("div",{attrs:{slot:"card-headr-right"},slot:"card-headr-right"},[e("div",{staticClass:"more",on:{click:t.gotoMenu}},[t._v(" 查看更多 > ")])]),e("div",{staticClass:"commonTools-content",attrs:{slot:"card-content"},slot:"card-content"},[e("ul",{staticClass:"tools-content"},[t._l(t.toolsData,(function(a,s){return[e("li",{key:s,staticClass:"tools-item",on:{click:function(e){return t.channelJump(a)}}},[a.icon?e("i",{staticClass:"item-img",class:a.icon}):t._e(),t._v(" "+t._s(a.name)+" ")])]}))],2)])])},T=[],v={name:"commonTools",data:function(){return{cardTitle:"常用工具",toolsData:[{icon:"icon iconfont icon-inspection",name:"安全管理系统"},{icon:"icon iconfont icon-cecurity-protection",name:"视频系统"},{icon:"icon iconfont icon-phone",name:"PMIS"},{icon:"icon iconfont icon-calculator",name:"盾构系统"},{icon:"icon iconfont icon-notice",name:"门禁系统"},{icon:"icon iconfont icon-operation",name:"质量管理"},{icon:"icon iconfont icon-connections",name:"进度管理"},{icon:"icon iconfont icon-integral",name:"智慧监管"},{icon:"icon iconfont icon-phone",name:"珠三角门户"}]}},methods:{channelJump:function(t){console.log(t)},gotoMenu:function(){}}},f=v,b=(e("ddba"),Object(r["a"])(f,m,T,!1,null,null,null)),M=b.exports,S=function(){var t=this,a=t.$createElement,e=t._self._c||a;return e("cardBox",{attrs:{cardTitle:t.cardTitle}},[e("div",{staticClass:"myAssets-content h100",attrs:{slot:"card-content"},slot:"card-content"},[e("div",{staticClass:"w50"},[e("div",{staticClass:"left-box"},[e("div",{staticClass:"box-title"},[t._v("硬件资产")]),t._l(t.assetsData.hardware,(function(a,s){return[e("div",{key:s,staticClass:"item"},[e("div",{staticClass:"items-name"},[t._v(t._s(a.name))]),e("div",{staticClass:"items-describe"},[t._v(t._s(a.describe))])])]}))],2)]),e("div",{staticClass:"w50"},[e("div",{staticClass:"right-box"},[e("div",{staticClass:"box-title"},[t._v("软件资产")]),t._l(t.assetsData.software,(function(a,s){return[e("div",{key:s,staticClass:"item"},[e("div",{staticClass:"items-name"},[t._v(t._s(a.name))]),e("div",{staticClass:"items-describe"},[t._v(t._s(a.describe))])])]}))],2)])])])},I=[],h={name:"myAssets",data:function(){return{cardTitle:"我的资产",assetsData:{hardware:[{name:"资产名称",describe:"联想笔记本"},{name:"型号",describe:"E480"},{name:"资产所属部门",describe:"市场部"}],software:[{name:"操作系统",describe:"WIN10"},{name:"办公软件LIST",describe:"WPS WORD OFFICE PS"},{name:"资产所属部门",describe:"市场部"}]}}}},_=h,C=(e("fb09"),Object(r["a"])(_,S,I,!1,null,null,null)),p=C.exports,g=function(){var t=this,a=t.$createElement,e=t._self._c||a;return e("cardBox",{attrs:{cardTitle:t.cardTitle}},[e("div",{attrs:{slot:"card-headr-right"},slot:"card-headr-right"},[e("div",{staticClass:"serviceRecord-right"},[e("div",{staticClass:"serviceRecord-add"},[e("el-button",[t._v("+ 新建")])],1),e("div",{staticClass:"more",on:{click:t.gotoMenu}},[t._v(" 查看更多 > ")])])]),e("div",{attrs:{slot:"card-headr-left"},slot:"card-headr-left"},[e("div",{staticClass:"serviceRecord-describe"},[e("span",{staticClass:"top-describe"},[t._v(" 上月服务次数："),e("span",{staticClass:"totalNum"},[t._v(t._s(t.totalNum))])])])]),e("div",{staticClass:"serviceRecord-content",attrs:{slot:"card-content"},slot:"card-content"},[e("div",{staticClass:"serviceRecord-table"},[e("el-table",{staticStyle:{width:"100%"},attrs:{data:t.tableData,stripe:"",height:"100%"}},[e("el-table-column",{attrs:{align:"center",prop:"abstract",label:"摘要"}}),e("el-table-column",{attrs:{align:"center",prop:"date",label:"报告时间"}}),e("el-table-column",{attrs:{align:"center",prop:"name",label:"处理人"}})],1)],1)])])},x=[],w={name:"serviceRecord",data:function(){return{cardTitle:"服务记录",totalNum:10,tableData:[{date:"2020-05-02",name:"王小虎",abstract:"电脑显示故障"},{date:"2020-05-04",name:"王小虎",abstract:"电脑显示故障"},{date:"2020-05-01",name:"王小虎",abstract:"电脑显示故障"},{date:"2020-05-03",name:"王小虎",abstract:"电脑显示故障"},{date:"2020-05-04",name:"王小虎",abstract:"电脑显示故障"},{date:"2020-05-04",name:"王小虎",abstract:"电脑显示故障"},{date:"2020-05-01",name:"王小虎",abstract:"电脑显示故障"},{date:"2020-05-03",name:"王小虎",abstract:"电脑显示故障"},{date:"2020-05-04",name:"王小虎",abstract:"电脑显示故障"}]}},methods:{gotoMenu:function(){}}},k=w,y=(e("3f5a"),e("9668"),Object(r["a"])(k,g,x,!1,null,null,null)),R=y.exports,D={name:"index",components:{backlog:u,commonTools:M,myAssets:p,serviceRecord:R},data:function(){return{}},created:function(){}},j=D,E=(e("2523"),Object(r["a"])(j,s,n,!1,null,"7679575e",null));a["default"]=E.exports},e4b5:function(t,a,e){"use strict";var s=e("cf87"),n=e.n(s);n.a},fb09:function(t,a,e){"use strict";var s=e("dabe"),n=e.n(s);n.a}}]);
//# sourceMappingURL=chunk-52d36a63.88f817f1.js.map