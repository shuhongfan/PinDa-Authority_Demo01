(window["webpackJsonp"]=window["webpackJsonp"]||[]).push([["chunk-6106d564"],{"05b0":function(e,t,i){"use strict";var r=i("db72"),n=i("9256"),a={allTree:{method:"GET",url:"/authority/menu/tree"},save:{method:"POST",url:"/authority/menu"},update:{method:"PUT",url:"/authority/menu"},delete:{method:"DELETE",url:"/authority/menu"}};t["a"]={allTree:function(e){return Object(n["a"])(Object(r["a"])({},a.allTree,{formData:!0,data:e}))},save:function(e){return Object(n["a"])(Object(r["a"])({},a.save,{data:e}))},update:function(e){return Object(n["a"])(Object(r["a"])({},a.update,{data:e}))},delete:function(e){return Object(n["a"])(Object(r["a"])({},a.delete,{data:e}))}}},"20db":function(e,t,i){"use strict";var r=i("db72"),n=i("9256"),a={page:{method:"GET",url:"/authority/resource/page"},save:{method:"POST",url:"/authority/resource"},update:{method:"PUT",url:"/authority/resource"},delete:{method:"DELETE",url:"/authority/resource"}};t["a"]={page:function(e){return Object(n["a"])(Object(r["a"])({},a.page,{formData:!0,data:e}))},save:function(e){return Object(n["a"])(Object(r["a"])({},a.save,{data:e}))},update:function(e){return Object(n["a"])(Object(r["a"])({},a.update,{data:e}))},delete:function(e){return Object(n["a"])(Object(r["a"])({},a.delete,{data:e}))}}},"61d9":function(e,t,i){"use strict";var r=i("db72"),n=i("9256"),a={page:{url:"/authority/role/page",method:"GET"},save:{url:"/authority/role",method:"POST"},update:{url:"/authority/role",method:"PUT"},delete:{url:"/authority/role",method:"DELETE"},saveUserRole:{url:"/authority/role/user",method:"POST"},saveRoleAuthority:{url:"/authority/role/authority",method:"POST"}};t["a"]={page:function(e){return Object(n["a"])(Object(r["a"])({},a.page,{formData:!0,data:e}))},save:function(e){return Object(n["a"])(Object(r["a"])({},a.save,{data:e}))},update:function(e){return Object(n["a"])(Object(r["a"])({},a.update,{data:e}))},delete:function(e){return Object(n["a"])(Object(r["a"])({},a.delete,{data:e}))},get:function(e){return Object(n["a"])({url:"/authority/role/".concat(e),method:"GET"})},check:function(e){return Object(n["a"])({url:"/authority/role/check/".concat(e),method:"GET"})},saveUserRole:function(e){return Object(n["a"])(Object(r["a"])({},a.saveUserRole,{data:e}))},findUserIdByRoleId:function(e){return Object(n["a"])({url:"/authority/role/user/".concat(e),method:"GET"})},findAuthorityIdByRoleId:function(e){return Object(n["a"])({url:"/authority/role/authority/".concat(e),method:"GET"})},saveRoleAuthority:function(e){return Object(n["a"])(Object(r["a"])({},a.saveRoleAuthority,{data:e}))}}},"9e22":function(e,t,i){"use strict";i.r(t);var r=function(){var e=this,t=e.$createElement,i=e._self._c||t;return i("el-dialog",{attrs:{"close-on-click-modal":!1,title:e.title,visible:e.isVisible,width:e.width,top:"50px"},on:{"update:visible":function(t){e.isVisible=t}}},[i("el-form",{ref:"form",attrs:{model:e.roleAuthority,rules:e.rules,"label-position":"top","label-width":"100px"}},[i("el-scrollbar",{staticStyle:{height:"800px"}},[i("el-row",{attrs:{gutter:12}},[i("el-col",{attrs:{span:8}},[i("el-card",{staticClass:"box-card"},[i("el-form-item",{attrs:{label:"菜单",prop:"menuIdList"}},[i("div",{staticStyle:{"margin-left":"24px"},attrs:{align:"left"}},[i("el-checkbox",{attrs:{indeterminate:e.isIndeterminate},on:{change:e.checkedAll},model:{value:e.checkedMenu,callback:function(t){e.checkedMenu=t},expression:"checkedMenu"}}),e._v("全选/反选\n              ")],1),e._v(" "),i("el-tree",{ref:"menuTree",attrs:{"check-strictly":!0,data:e.menuTree,"default-checked-keys":e.roleAuthority.menuIdList,"default-expanded-keys":e.roleAuthority.menuIdList,disabled:e.disabled,"expand-on-click-node":!1,"default-expand-all":"","highlight-current":"","node-key":"id","show-checkbox":""},on:{check:e.checkMenu,"node-click":e.nodeClick}})],1)],1)],1),e._v(" "),i("el-col",{attrs:{span:16}},[i("el-card",{staticClass:"box-card"},[i("el-form-item",{attrs:{label:"资源",prop:"resourceIdList"}},[i("el-table",{directives:[{name:"loading",rawName:"v-loading",value:e.loading,expression:"loading"}],key:e.tableKey,ref:"table",staticStyle:{width:"100%"},attrs:{data:e.tableData.records,border:"",fit:"","row-key":"id"},on:{select:e.onSelect,"select-all":e.onAllSelect}},[i("el-table-column",{attrs:{"reserve-selection":!0,align:"center",type:"selection",width:"40px"}}),e._v(" "),i("el-table-column",{attrs:{label:e.$t("table.resource.code"),"show-overflow-tooltip":!0,align:"center",prop:"code"},scopedSlots:e._u([{key:"default",fn:function(t){return[i("span",[e._v(e._s(t.row.code))])]}}])}),e._v(" "),i("el-table-column",{attrs:{label:e.$t("table.resource.name"),"show-overflow-tooltip":!0,align:"center",prop:"name"},scopedSlots:e._u([{key:"default",fn:function(t){return[i("span",[e._v(e._s(t.row.name))])]}}])})],1)],1)],1)],1)],1)],1)],1),e._v(" "),i("div",{staticClass:"dialog-footer",attrs:{slot:"footer"},slot:"footer"},[i("el-button",{attrs:{plain:"",type:"warning"},on:{click:function(t){e.isVisible=!1}}},[e._v(e._s(e.$t("common.cancel")))]),e._v(" "),i("el-button",{attrs:{disabled:e.disabled,plain:"",type:"primary"},on:{click:e.submitForm}},[e._v(e._s(e.$t("common.confirm")))])],1)],1)},n=[],a=i("75fc"),o=(i("4f7f"),i("5df3"),i("1c4c"),i("ac6a"),i("61d9")),s=i("05b0"),c=i("20db"),l={name:"RoleAuthorityEdit",components:{},props:{dialogVisible:{type:Boolean,default:!1}},data:function(){return{roleAuthority:this.initRoleAuthority(),screenWidth:0,width:this.initWidth(),menuTree:[],resourceList:[],rules:{},tableKey:0,loading:!1,tableData:{total:0},selection:[],disabled:!1,isIndeterminate:!1,checkedMenu:!1}},computed:{isVisible:{get:function(){return this.dialogVisible},set:function(){this.close(),this.reset()}},title:function(){return"配置菜单资源"}},watch:{},mounted:function(){var e=this;this.initMenuTree(),window.onresize=function(){return function(){e.width=e.initWidth()}()}},methods:{allMenuIdList:function(){var e=[];return this.getMenuIdList(this.menuTree,e),e},getMenuIdList:function(e,t){var i=this;e&&e.forEach((function(e){t.push(e.id),e.children&&e.children.length>0&&i.getMenuIdList(e.children,t)}))},checkedAll:function(){this.checkedMenu?(this.$refs.menuTree.setCheckedKeys(this.allMenuIdList()),this.isIndeterminate=!1):(this.$refs.menuTree.setCheckedKeys([]),this.isIndeterminate=!1)},nodeClick:function(e){var t=this;t.loading=!0,c["a"].page({current:1,size:1e4,menuId:e.id}).then((function(e){var i=e.data;t.tableData=i.data,t.loading=!1,t.displayTable()}))},displayTable:function(){var e=this;e.tableData.records.forEach((function(t){e.roleAuthority.resourceIdList.forEach((function(i){t.id===i&&e.$refs.table.toggleRowSelection(t,!0)}))}))},onAllSelect:function(e){this.onSelect(e)},onSelect:function(e){var t=this;this.roleAuthority.resourceIdList=e.map((function(e){return e.id})),this.selection=e;var i=this.$refs.menuTree.getCheckedKeys(),r=e.map((function(e){return e.menuId})),n=Array.from(new Set([].concat(Object(a["a"])(i),Object(a["a"])(r))));this.$refs.menuTree.setCheckedKeys(n),n.forEach((function(e){t.selectedParent(e)}))},initMenuTree:function(){var e=this;s["a"].allTree().then((function(t){var i=t.data;e.menuTree=i.data}))},initRoleAuthority:function(){return{roleId:"",menuIdList:[],resourceIdList:[]}},initWidth:function(){return this.screenWidth=document.body.clientWidth,this.screenWidth<991?"90%":this.screenWidth<1400?"45%":"1000px"},setRoleAuthority:function(e){var t=this;t.roleAuthority.roleId=e.id,o["a"].findAuthorityIdByRoleId(e.id).then((function(e){var i=e.data;t.roleAuthority.menuIdList=i.data.menuIdList,t.roleAuthority.resourceIdList=i.data.resourceIdList,t.$refs.menuTree.setCheckedKeys(i.data.menuIdList),i.data.menuIdList.forEach((function(e){t.selectedParent(e)}))}))},close:function(){this.$emit("close")},reset:function(){this.$refs.form.clearValidate(),this.$refs.form.resetFields(),this.roleAuthority=this.initRoleAuthority(),this.$refs.menuTree.setCheckedKeys([]),this.$refs.table.clearSelection()},submitForm:function(){var e=this;this.$refs.form.validate((function(t){if(!t)return!1;e.editSubmit()}))},editSubmit:function(){var e=this;this.roleAuthority.menuIdList=e.$refs.menuTree.getHalfCheckedKeys().concat(e.$refs.menuTree.getCheckedKeys()),this.roleAuthority.resourceIdList=e.selection.map((function(e){return e.id})),o["a"].saveRoleAuthority(this.roleAuthority).then((function(t){var i=t.data;i.isSuccess&&(e.isVisible=!1,e.$message({message:e.$t("tips.createSuccess"),type:"success"}),e.$emit("success"))}))},checkMenu:function(e,t){0===t.checkedKeys.length?(this.checkedMenu=!1,this.isIndeterminate=!1):t.checkedKeys.length===this.allMenuIdList().length?(this.checkedMenu=!0,this.isIndeterminate=!1):(this.checkedMenu=!1,this.isIndeterminate=!0);var i=t.checkedKeys.indexOf(e.id);-1!==i?(this.selectedParent(e),this.uniteChildSame(e,!0)):e.children&&0!==e.children.length&&this.uniteChildSame(e,!1)},uniteChildSame:function(e,t){if(this.$refs.menuTree.setChecked(e.id,t),e.children)for(var i=0;i<e.children.length;i++)this.uniteChildSame(e.children[i],t)},selectedParent:function(e){var t=this.$refs.menuTree.getNode(e);void 0!==t.parent.key&&(this.$refs.menuTree.setChecked(t.parent,!0),this.selectedParent(t.parent))}}},u=l,d=i("2877"),h=Object(d["a"])(u,r,n,!1,null,"2c71405e",null);t["default"]=h.exports}}]);