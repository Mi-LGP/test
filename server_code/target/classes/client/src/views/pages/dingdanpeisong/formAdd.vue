<template>
	<div class="edit_view" :style='{}'>
        <div class="breadcrumb-wrapper" style="width: 100%;">
            <div class="bread_view">
                <el-breadcrumb separator="Ξ" class="breadcrumb">
                    <el-breadcrumb-item class="first_breadcrumb" :to="{ path: '/' }">首页</el-breadcrumb-item>
                    <el-breadcrumb-item class="second_breadcrumb" v-for="(item,index) in breadList" :key="index">{{item.name}}</el-breadcrumb-item>
                </el-breadcrumb>
            </div>
        </div>
		<el-form ref="formRef" :model="form" class="add_form" label-width="120px" :rules="rules">
			<el-row>
				<el-col :span="12">
					<el-form-item label="商品名称" prop="shangpinmingcheng">
						<el-input class="list_inp"
                                  v-model="form.shangpinmingcheng"
                                  placeholder="商品名称"
                                  type="text"
							      :readonly="!isAdd||disabledForm.shangpinmingcheng?true:false" />
					</el-form-item>
				</el-col>

				<el-col :span="12">
					<el-form-item label="用户" prop="yonghu">
						<el-select
							class="list_sel"
							:disabled="!isAdd||disabledForm.yonghu?true:false"
							v-model="form.yonghu" 
							placeholder="请选择用户"
							style="width:100%;"
							@change="yonghuChange">
							<el-option v-for="(item,index) in yonghuLists" :label="item" :value="item">
							</el-option>
						</el-select>
					</el-form-item>
				</el-col>
				<el-col :span="12">
					<el-form-item label="姓名" prop="xingming">
						<el-input class="list_inp"
                                  v-model="form.xingming"
                                  placeholder="姓名"
                                  type="text"
							      :readonly="!isAdd||disabledForm.xingming?true:false" />
					</el-form-item>
				</el-col>

				<el-col :span="12">
					<el-form-item label="配送状态" prop="peisongzhuangtai">
						<el-select
							class="list_sel"
							:disabled="!isAdd||disabledForm.peisongzhuangtai?true:false"
							v-model="form.peisongzhuangtai" 
							placeholder="请选择配送状态"
							style="width:100%;"
							>
							<el-option v-for="(item,index) in peisongzhuangtaiLists" :label="item"
								:value="item"
								>
							</el-option>
						</el-select>
					</el-form-item>
				</el-col>
				<el-col :span="12">
					<el-form-item label="更新时间" prop="gengxinshijian">
						<el-date-picker
							class="list_date"
							v-model="form.gengxinshijian"
							format="YYYY-MM-DD HH:mm:ss"
							value-format="YYYY-MM-DD HH:mm:ss"
							type="datetime"
							style="width:100%;"
							:readonly="!isAdd||disabledForm.gengxinshijian?true:false"
							placeholder="请选择更新时间" />
					</el-form-item>
				</el-col>
				<el-col :span="12">
					<el-form-item label="配送员" prop="peisongyuan">
						<el-select
							class="list_sel"
							:disabled="!isAdd||disabledForm.peisongyuan?true:false"
							v-model="form.peisongyuan" 
							placeholder="请选择配送员"
							style="width:100%;"
							>
							<el-option v-for="(item,index) in peisongyuanLists" :label="item"
								:value="item"
								>
							</el-option>
						</el-select>
					</el-form-item>
				</el-col>
				<el-col :span="12">
					<el-form-item label="商家" prop="shangjia">
						<el-input class="list_inp"
                                  v-model="form.shangjia"
                                  placeholder="商家"
                                  type="text"
							      :readonly="!isAdd||disabledForm.shangjia?true:false" />
					</el-form-item>
				</el-col>

			</el-row>
			<div class="formModel_btn_box">
				<el-button class="formModel_cancel" @click="backClick">取消</el-button>
				<el-button class="formModel_confirm"
                           @click="save"
                           type="success"
				>
					保存
				</el-button>
			</div>
		</el-form>
	</div>
</template>
<script setup>
	import {
		ref,
		getCurrentInstance,
		watch,
		onUnmounted,
		onMounted,
		nextTick,
		computed
	} from 'vue';
	import {
		useRoute,
		useRouter
	} from 'vue-router';
    import moment from "moment";
	const context = getCurrentInstance()?.appContext.config.globalProperties;
	const route = useRoute()
	const router = useRouter()
	//基础信息
	const tableName = 'dingdanpeisong'
	const formName = '订单配送'
	//基础信息
	const breadList = ref([{
		name: formName
	}])
	//获取唯一标识
	const getUUID =()=> {
      return new Date().getTime();
    }
	//form表单
	const form = ref({
		shangpinmingcheng: '',
		yonghu: '',
		xingming: '',
		peisongzhuangtai: '未送达',
		gengxinshijian: '',
		peisongyuan: '',
		shangjia: '',
	})
	const formRef = ref(null)
	const id = ref(0)
	const type = ref('')
	const disabledForm = ref({
		shangpinmingcheng : false,
		yonghu : false,
		xingming : false,
		peisongzhuangtai : false,
		gengxinshijian : false,
		peisongyuan : false,
		shangjia : false,
	})
	const isAdd = ref(false)
	//表单验证
	const rules = ref({
		shangpinmingcheng: [
			{required: true,message: '请输入',trigger: 'blur'}, 
		],
		yonghu: [
		],
		xingming: [
		],
		peisongzhuangtai: [
		],
		gengxinshijian: [
		],
		peisongyuan: [
		],
		shangjia: [
		],
	})
	//用户列表
	const yonghuLists = ref([])
	//配送状态列表
	const peisongzhuangtaiLists = ref([])
	//配送员列表
	const peisongyuanLists = ref([])
	//methods

	//methods
	//获取info
	const getInfo = ()=>{
		context?.$http({
			url: `${tableName}/info/${id.value}`,
			method: 'get'
		}).then(res => {
			let reg=new RegExp('../../../file','g')
			form.value = res.data.data
		})
	}
	const crossRow = ref('')
	const crossTable = ref('')
	const crossTips = ref('')
	const crossColumnName = ref('')
	const crossColumnValue = ref('')
	//初始化
	const init = (formId=null,formType='add',formNames='',row=null,table=null,statusColumnName=null,tips=null,statusColumnValue=null) => {
			form.value.gengxinshijian = context?.$toolUtil.getCurDateTime()
		if(formId){
			id.value = formId
			type.value = formType
		}
		if(formType == 'add'){
			isAdd.value = true
		}else if(formType == 'info'){
			isAdd.value = false
			getInfo()
		}else if(formType == 'edit'){
			isAdd.value = true
			getInfo()
		}
		else if(formType == 'cross'){
			isAdd.value = true
			// getInfo()
			for(let x in row){
				if(x=='shangpinmingcheng'){
					form.value.shangpinmingcheng = row[x];
					disabledForm.value.shangpinmingcheng = true;
					continue;
				}
				if(x=='yonghu'){
					form.value.yonghu = row[x];
					disabledForm.value.yonghu = true;
					continue;
				}
				if(x=='xingming'){
					form.value.xingming = row[x];
					disabledForm.value.xingming = true;
					continue;
				}
				if(x=='peisongzhuangtai'){
					form.value.peisongzhuangtai = row[x];
					disabledForm.value.peisongzhuangtai = true;
					continue;
				}
				if(x=='gengxinshijian'){
					form.value.gengxinshijian = row[x];
					disabledForm.value.gengxinshijian = true;
					continue;
				}
				if(x=='peisongyuan'){
					form.value.peisongyuan = row[x];
					disabledForm.value.peisongyuan = true;
					continue;
				}
				if(x=='shangjia'){
					form.value.shangjia = row[x];
					disabledForm.value.shangjia = true;
					continue;
				}
			}
			if(row){
				crossRow.value = row
			}
			if(table){
				crossTable.value = table
			}
			if(tips){
				crossTips.value = tips
			}
			if(statusColumnName){
				crossColumnName.value = statusColumnName
			}
			if(statusColumnValue){
				crossColumnValue.value = statusColumnValue
			}
			form.value.peisongzhuangtai='未送达'
		}
		context?.$http({
			url: `${context?.$toolUtil.storageGet('frontSessionTable')}/session`,
			method: 'get'
		}).then(res => {
			var json = res.data.data
			if(json.hasOwnProperty('shangpinmingcheng') && context?.$toolUtil.storageGet("frontRole")!="管理员"){
				form.value.shangpinmingcheng = json.shangpinmingcheng
				disabledForm.value.shangpinmingcheng = true;
			}
			if(json.hasOwnProperty('yonghu') && context?.$toolUtil.storageGet("frontRole")!="管理员"){
				form.value.yonghu = json.yonghu
				disabledForm.value.yonghu = true;
			}
			if(json.hasOwnProperty('xingming') && context?.$toolUtil.storageGet("frontRole")!="管理员"){
				form.value.xingming = json.xingming
				disabledForm.value.xingming = true;
			}
			if(json.hasOwnProperty('shangjia') && context?.$toolUtil.storageGet("frontRole")!="管理员"){
				form.value.shangjia = json.shangjia
				disabledForm.value.shangjia = true;
			}
            if (localStorage.getItem('autoSave')) {
                localStorage.removeItem('autoSave')
                save()
            }
		})
		context?.$http({
			url: `option/yonghu/yonghu`,
			method: 'get'
		}).then(res=>{
			yonghuLists.value = res.data.data
		})
		//由上级字段带出不可改
		disabledForm.value.xingming = true;
		peisongzhuangtaiLists.value = "已送达,未送达".split(',')
		context?.$http({
			url: `option/peisongyuan/peisongyuan`,
			method: 'get'
		}).then(res=>{
			peisongyuanLists.value = res.data.data
		})
	}
	//初始化
	//取消
	const backClick = () => {
		history.back()
	}
	//用户回调
	const yonghuChange=()=>{
		context?.$http({
			url: `follow/yonghu/yonghu?columnValue=` + form.value.yonghu,
			method: 'get'
		}).then(res=>{
			//带出姓名字段
			if(res.data.data.xingming){
				form.value.xingming = res.data.data.xingming
			}
		})
	}
	//提交
	const save=()=>{
		var table = crossTable.value
		var objcross = JSON.parse(JSON.stringify(crossRow.value))
		let crossUserId = ''
		let crossRefId = ''
		let crossOptNum = ''
		if(type.value == 'cross'){
			if(crossColumnName.value!=''){
				if(!crossColumnName.value.startsWith('[')){
					for(let o in objcross){
						if(o == crossColumnName.value){
							objcross[o] = crossColumnValue.value
						}
					}
					//修改跨表数据
					changeCrossData(objcross)
				}else{
					crossUserId = context?.$toolUtil.storageGet('userid')
					crossRefId = objcross['id']
					crossOptNum = crossColumnName.value.replace(/\[/,"").replace(/\]/,"")
				}
			}
		}
		formRef.value.validate((valid)=>{
			if(valid){
				if(crossUserId&&crossRefId){
					form.value.crossuserid = crossUserId
					form.value.crossrefid = crossRefId
					let params = {
						page: 1,
						limit: 1000, 
						crossuserid:form.value.crossuserid,
						crossrefid:form.value.crossrefid,
					}
					context?.$http({
						url: `${tableName}/page`,
						method: 'get', 
						params: params 
					}).then(res=>{
						if(res.data.data.total>=crossOptNum){
							context?.$toolUtil.message(`${crossTips.value}`,'error')
							return false
						}else{
							context?.$http({
								url: `${tableName}/${!form.value.id ? "save" : "update"}`,
								method: 'post', 
								data: form.value 
							}).then(res=>{
                                context?.$toolUtil.message(`操作成功`,'success')
                                history.back()
							})
						}
					})
				}else{
					context?.$http({
						url: `${tableName}/${!form.value.id ? "save" : "update"}`,
						method: 'post', 
						data: form.value 
					}).then(res=>{
                        context?.$toolUtil.message(`操作成功`,'success')
                        history.back()
					})
				}
			}
		})
	}
	//修改跨表数据
	const changeCrossData=(row)=>{
        if(type.value == 'cross'){
            context?.$http({
                url: `${crossTable.value}/update`,
                method: 'post',
                data: row
            }).then(res=>{})
        }
	}
	onMounted(()=>{
		type.value = route.query.type?route.query.type:'add'
		let row = null
		let table = null
		let statusColumnName = null
		let tips = null
		let statusColumnValue = null
		if(type.value == 'cross'){
			row = context?.$toolUtil.storageGet('crossObj')?JSON.parse(context?.$toolUtil.storageGet('crossObj')):{}
			table = context?.$toolUtil.storageGet('crossTable')
			statusColumnName = context?.$toolUtil.storageGet('crossStatusColumnName')
			tips = context?.$toolUtil.storageGet('crossTips')
			statusColumnValue = context?.$toolUtil.storageGet('crossStatusColumnValue')
		}
		init(route.query.id?route.query.id:null, type.value,'', row, table, statusColumnName, tips, statusColumnValue)
	})
    onUnmounted(()=>{
        Object.keys(localStorage).map(item=>{
            if(item.startsWith('cross')){
                localStorage.removeItem(item)
            }
        })
    })
</script>
<style lang="scss" scoped>
	// 面包屑盒子
	.bread_view {
		:deep(.breadcrumb) {
			.el-breadcrumb__separator {
			}
			.first_breadcrumb {
				.el-breadcrumb__inner {
				}
			}
			.second_breadcrumb {
				.el-breadcrumb__inner {
				}
			}
		}
	}
	// 表单
	.add_form{
		// form item
		:deep(.el-form-item) {
			//label
			.el-form-item__label {
			}
			// 内容盒子
			.el-form-item__content {
				// 输入框
				.list_inp {

				}
				//日期选择器
				.list_date {
				}
				// 下拉框
				.list_sel {
					//去掉默认样式
					.select-trigger{
						height: 100%;
						.el-input{
							height: 100%;
						}
					}
				}
			}
		}
	}
	// 按钮盒子
	.formModel_btn_box {
		.formModel_cancel {
		}
		.formModel_cancel:hover {
		}
		
		.formModel_confirm {
		}
		.formModel_confirm:hover {
		}
	}
</style>
<style>
.edit_view {
    width: 1200px;
    margin: 20px auto;
    padding: 40px 20px 20px ;
    background: #fff;
    overflow: hidden;
    border: 0px solid #eee;
    font-size: 16px;
    color:#666;
}
.edit_view .add_form{
    width: 100%;
    padding: 30px;
    border:0px solid #eee
}
.edit_view .add_form .el-form-item{
    margin: 0px 0px 20px;
    display: flex;
}
.edit_view .add_form .el-form-item .el-form-item__label{
    width: 150px;
    background: none;
    text-align: right;
    display: block;
    font-size: 16px;
    color: rgb(51, 51, 51);
    font-weight: 500;
}
.edit_view .add_form .el-form-item .el-form-item__content{
    display: flex;
    justify-content: flex-start;
    align-items: center;
    flex-wrap: wrap;
    width: calc(100% - 120px);
}
.edit_view .add_form .el-form-item .el-form-item__content .list_inp{
    height: 36px;
    line-height: 36px;
    border: 1px solid rgb(221, 221, 221);
    padding: 0px 10px;
    width: 100%;
    box-sizing: border-box;
    background: rgb(255, 255, 255);
    font-size: 16px;
}

.edit_view .add_form .el-form-item .el-form-item__content .list_sel{
    line-height: 36px;
    border: 1px solid rgb(221, 221, 221);
    box-sizing: border-box;
    width: calc(100% - 0px);
    padding: 0px 10px;
    border-radius: 0px;
    background: rgb(255, 255, 255);
    font-size: 16px;
}

.edit_view .add_form .el-form-item .el-form-item__content .list_date{
    line-height: 36px;
    border: 1px solid rgb(221, 221, 221);
    box-sizing: border-box;
    width: 100%;
    border-radius: 0px;
    background: rgb(255, 255, 255);
    font-size: 16px;
}








</style>