<template>
	<div>
		<el-dialog modal-class="edit_form_modal" class="edit_form" v-model="formVisible" :title="formTitle" width="50%" destroy-on-close :fullscreen='false'>
			<el-form class="formModel_form" ref="formRef" :model="form" :rules="rules">
				<el-row >
					<el-col :span="24">
						<el-form-item label="商品名称" prop="shangpinmingcheng">
							<el-input class="list_inp" v-model="form.shangpinmingcheng" placeholder="商品名称"
                                type="text"
								:readonly="!isAdd||disabledForm.shangpinmingcheng?true:false" />
						</el-form-item>
					</el-col>

					<el-col :span="24">
						<el-form-item label="用户" prop="yonghu">
							<el-select
								class="list_sel"
								:disabled="!isAdd||disabledForm.yonghu?true:false"
								v-model="form.yonghu" 
								placeholder="请选择用户"
								@change="yonghuChange">
								<el-option v-for="(item,index) in yonghuLists" :label="item" :value="item">
								</el-option>
							</el-select>
						</el-form-item>
					</el-col>
					<el-col :span="24">
						<el-form-item label="姓名" prop="xingming">
							<el-input class="list_inp" v-model="form.xingming" placeholder="姓名"
                                type="text"
								:readonly="!isAdd||disabledForm.xingming?true:false" />
						</el-form-item>
					</el-col>

					<el-col :span="24">
						<el-form-item label="配送状态" prop="peisongzhuangtai">
							<el-select
								class="list_sel"
								:disabled="!isAdd||disabledForm.peisongzhuangtai?true:false"
								v-model="form.peisongzhuangtai" 
								placeholder="请选择配送状态"
								>
								<el-option v-for="(item,index) in peisongzhuangtaiLists" :label="item"
									:value="item"
									>
								</el-option>
							</el-select>
						</el-form-item>
					</el-col>
					<el-col :span="24">
						<el-form-item label="更新时间" prop="gengxinshijian">
							<el-date-picker
								class="list_date"
								v-model="form.gengxinshijian"
								format="YYYY-MM-DD HH:mm:ss"
								value-format="YYYY-MM-DD HH:mm:ss"
								type="datetime"
								:readonly="!isAdd||disabledForm.gengxinshijian?true:false"
								placeholder="请选择更新时间" />
						</el-form-item>
					</el-col>
					<el-col :span="24">
						<el-form-item label="配送员" prop="peisongyuan">
							<el-select
								class="list_sel"
								:disabled="!isAdd||disabledForm.peisongyuan?true:false"
								v-model="form.peisongyuan" 
								placeholder="请选择配送员"
								>
								<el-option v-for="(item,index) in peisongyuanLists" :label="item"
									:value="item"
									>
								</el-option>
							</el-select>
						</el-form-item>
					</el-col>
					<el-col :span="24">
						<el-form-item label="商家" prop="shangjia">
							<el-input class="list_inp" v-model="form.shangjia" placeholder="商家"
                                type="text"
								:readonly="!isAdd||disabledForm.shangjia?true:false" />
						</el-form-item>
					</el-col>

				</el-row>
			</el-form>
			<template #footer v-if="isAdd||type=='logistics'||type=='reply'">
				<span class="formModel_btn_box">
					<el-button class="cancel_btn" @click="closeClick">取消</el-button>
					<el-button class="confirm_btn" type="primary" @click="save"
						>
						提交
					</el-button>
				</span>
			</template>
		</el-dialog>
	</div>
</template>
<script setup>
	import {
		reactive,
		ref,
		getCurrentInstance,
		nextTick,
		computed,
		defineEmits
	} from 'vue'
    import {
        useStore
    } from 'vuex';
    const store = useStore()
    const user = computed(()=>store.getters['user/session'])
	const context = getCurrentInstance()?.appContext.config.globalProperties;	
	const emit = defineEmits(['formModelChange'])
	//基础信息
	const tableName = 'dingdanpeisong'
	const formName = '订单配送'
	//基础信息
	//form表单
	const form = ref({})
	const disabledForm = ref({
        shangpinmingcheng : false,
        yonghu : false,
        xingming : false,
        peisongzhuangtai : false,
        gengxinshijian : false,
        peisongyuan : false,
        shangjia : false,
	})
	const formVisible = ref(false)
	const isAdd = ref(false)
	const formTitle = ref('')
    
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
	//表单验证
	
	const formRef = ref(null)
	const id = ref(0)
	const type = ref('')
	//用户列表
	const yonghuLists = ref([])
	//配送状态列表
	const peisongzhuangtaiLists = ref([])
	//配送员列表
	const peisongyuanLists = ref([])

	//获取唯一标识
	const getUUID =()=> {
      return new Date().getTime();
    }
	//重置
	const resetForm = () => {
		form.value = {
			shangpinmingcheng: '',
			yonghu: '',
			xingming: '',
			peisongzhuangtai: '未送达',
			gengxinshijian: '',
			peisongyuan: '',
			shangjia: '',
		}
	}
	//获取info
	const getInfo = ()=>{
		context?.$http({
			url: `${tableName}/info/${id.value}`,
			method: 'get'
		}).then(res => {
			let reg=new RegExp('../../../file','g')
			form.value = res.data.data
			formVisible.value = true
		})
	}
	const crossRow = ref('')
	const crossTable = ref('')
	const crossTips = ref('')
	const crossColumnName = ref('')
	const crossColumnValue = ref('')
	//初始化
	const init=(formId=null,formType='add',formNames='',row=null,table=null,statusColumnName=null,tips=null,statusColumnValue=null)=>{
		resetForm()
			form.value.gengxinshijian = context?.$toolUtil.getCurDateTime()
		if(formId){
			id.value = formId
			type.value = formType
		}
		if(formType == 'add'){
			isAdd.value = true
			formTitle.value = '新增' + formName
			formVisible.value = true
		}else if(formType == 'info'){
			isAdd.value = false
			formTitle.value = '查看' + formName
			getInfo()
		}else if(formType == 'edit'){
			isAdd.value = true
			formTitle.value = '修改' + formName
			getInfo()
		}
		else if(formType == 'cross'){
			isAdd.value = true
			formTitle.value = formNames
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
			formVisible.value = true
		}

		context?.$http({
			url: `${context?.$toolUtil.storageGet('sessionTable')}/session`,
			method: 'get'
		}).then(res => {
			var json = res.data.data
			if(json.hasOwnProperty('shangpinmingcheng')&& context?.$toolUtil.storageGet("role")!="管理员"){
				form.value.shangpinmingcheng = json.shangpinmingcheng
				disabledForm.value.shangpinmingcheng = true;
			}
			if(json.hasOwnProperty('yonghu')&& context?.$toolUtil.storageGet("role")!="管理员"){
				form.value.yonghu = json.yonghu
				disabledForm.value.yonghu = true;
			}
			if(json.hasOwnProperty('xingming')&& context?.$toolUtil.storageGet("role")!="管理员"){
				form.value.xingming = json.xingming
				disabledForm.value.xingming = true;
			}
			if(json.hasOwnProperty('shangjia')&& context?.$toolUtil.storageGet("role")!="管理员"){
				form.value.shangjia = json.shangjia
				disabledForm.value.shangjia = true;
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
	//声明父级调用
	defineExpose({
		init
	})
	//关闭
	const closeClick = () => {
		formVisible.value = false
	}
	const yonghuChange=()=>{
		context?.$http({
			url: `follow/yonghu/yonghu?columnValue=` + form.value.yonghu,
			method: 'get'
		}).then(res=>{
			if(res.data.data.xingming){
				form.value.xingming = res.data.data.xingming
			}
		})
	}
	//富文本
	const editorChange = (e,name) =>{
		form.value[name] = e
	}
	//提交
	const save= async ()=>{
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
					crossUserId = user.value.id
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
							}).then(async res=>{
								emit('formModelChange')
								context?.$toolUtil.message(`操作成功`,'success')
                                formVisible.value = false
							})
						}
					})
				}else{
					context?.$http({
						url: `${tableName}/${!form.value.id ? "save" : "update"}`,
						method: 'post', 
						data: form.value 
					}).then(async (res)=>{
						emit('formModelChange')
						context?.$toolUtil.message(`操作成功`,'success')
                        formVisible.value = false
					})
				}
			}else{
                context.$message.error('请完善信息')
            }
		})
	}
	//修改跨表数据
	const changeCrossData = async (row)=>{
        if(type.value == 'cross'){
            await context?.$http({
                url: `${crossTable.value}/update`,
                method: 'post',
                data: row
            }).then(res=>{})
        }
	}
</script>
<style lang="scss" scoped>
	// 表单
	.formModel_form{
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
		.cancel_btn {
		}
		.cancel_btn:hover {
		}
		
		.confirm_btn {
		}
		.confirm_btn:hover {
		}
	}
</style>
