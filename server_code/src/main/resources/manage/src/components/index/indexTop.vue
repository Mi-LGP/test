<template>
	<div class="top_view">
		<div class="top_left_view">
			<div class="fold_view" @click="toggleClick" :class="{'is_collapse':collapse}">
				<el-switch :value="collapse" :active-value="false" :inactive-value="true"></el-switch>
			</div>
		</div>

		<div class="projectTitle">基于SpribgBoot的社区团购系统</div>
		<div class="top_right_view">
			<el-dropdown class="avatar-container" trigger="hover">
				<div class="avatar-wrapper">
					<div class="nickname">{{$toolUtil.storageGet("adminName")}}</div>
					<img class="user-avatar" :src="store.getters['user/avatar']">
					<el-icon class="el-icon-arrow-down">
						<arrow-down />
					</el-icon>
				</div>
				<template #dropdown>
					<el-dropdown-menu class="user-dropDown" slot="dropdown">
						<el-dropdown-item class="center" @click="centerClick" >
							个人中心
						</el-dropdown-item>
						<el-dropdown-item class="chatRecord" v-if="changeHasChat()">
							<span style="display:block;" @click="chatRecordClick">聊天记录</span>
						</el-dropdown-item>
						<el-dropdown-item class="password" @click="updatepasswordClick">
							修改密码
						</el-dropdown-item>
						<el-dropdown-item class="front">
							<span style="display:block;" @click="frontClick">系统前台</span>
						</el-dropdown-item>
						<el-dropdown-item class="loginOut">
							<span style="display:block;" @click="onLogout">退出登录</span>
						</el-dropdown-item>
					</el-dropdown-menu>
				</template>
			</el-dropdown>
		</div>
		<el-dialog v-model="recordVisible" :title="'聊天记录'" :append-to-body="true">
			<div class="z-box" :style='{"width":"70%","padding":"20px","margin":"0 auto"}'>
				<div class="section-content" v-for="item in recordList" :key="item.id" @click="chatClick(item)">
					<img :src="item.picture?$config.url + item.picture:require('@/assets/img/avatar.png')" style="width: 60px;border-radius: 50%;"
						 alt="">
					<div style="margin: 0 0 0 10px;display: flex;flex-direction: column;align-items: flex-start;">
						<div :style='{"fontSize":"14px","color":"#000","flex":"1","fontWeight":"bold"}'
							 class="item-style">{{item.name}}</div>
						<div :style='{"color":"#666","flex":"1","fontSize":"14px","lineHeight":"1.5","display":"flex","alignItems":"center"}'
							 class="item-style">
							<div v-if="item.notreadnum" style="padding: 0 5px;height: 16px;border-radius: 50%;text-align: center;line-height: 16px;font-size: 12px;background: #f00;color:#fff;width: auto;margin: 0 2px 0 0">{{item.notreadnum}}</div>
							{{item.content.split('/').length&&item.content.split('/')[0]=='upload'?'[图片]':item.content}}
						</div>
					</div>
				</div>
				<div class="noList" v-if="!recordList.length">
					暂无聊天记录
				</div>
			</div>
		</el-dialog>
		<el-dialog v-model="chatVisible" @close="clearChat" :title="nowname" :append-to-body="true">
			<div class="chat-content" id="chat-content">
				<div v-bind:key="item.id" v-for="(item,index) in chatList">
                    <div v-if="index>1&&moment(chatList[index-1].addtime).date()!=moment(item.addtime).date()||index==0"
                         style="font-size: 12px;text-align: center;margin: 4px 0;">
                        {{moment(item.addtime).format("MM-DD HH:mm")}}
                    </div>
					<div v-if="item.uid==user.id" class="right-content">
						<el-alert v-if="item.format==1" class="text-content" :title="item.content" :closable="false"
								  type="warning"></el-alert>
                        <video v-else-if="item.content.endsWith('.mp4')" controls style="width: 200px;height: 160px">
                            <source  :src="$config.url + item.content">
                        </video>
						<el-image v-else fit="cover" :src="item.content?$config.url + item.content:''" style="width: 100px;height: 100px;" :preview-src-list="[item.content?$config.url + item.content:'']"></el-image>
						<img :src="avatar?$config.url + avatar:require('@/assets/img/avatar.png')" alt="" style="width: 30px;border-radius: 50%;height: 30px;margin: 0 0 0 10px;flex-shrink: 0;object-fit: cover;" />
					</div>
					<div v-else class="left-content">
						<img :src="nowfpic?$config.url + nowfpic:require('@/assets/img/avatar.png')" alt="" style="width: 30px;border-radius: 50%;height: 30px;margin: 0 10px 0 0;flex-shrink: 0;object-fit: cover;" />
						<el-alert v-if="item.format==1" class="text-content" :title="item.content" :closable="false"
								  type="success"></el-alert>
                        <video v-else-if="item.content.endsWith('.mp4')" controls style="width: 200px;height: 160px">
                            <source  :src="$config.url + item.content">
                        </video>
						<el-image v-else fit="cover" :src="item.content?$config.url + item.content:''" style="width: 100px;height: 100px;" :preview-src-list="[item.content?$config.url + item.content:'']"></el-image>
					</div>
					<div class="clear-float"></div>
				</div>
			</div>
			<div slot="footer" class="dialog-footer" style="display: flex;align-items: center;">
				<el-input @keydown.enter.native="addChat" v-model="chatForm.content" placeholder="请输入内容" style="width: calc(100% - 180px);float: left;border: 1px solid #ccc;border-radius: 4px;padding: 0 10px;margin-right: 4px;">
				</el-input>
				<el-button :disabled="chatForm.content?false:true" type="primary" @click="addChat">发送</el-button>
				<el-upload style="display: inline-block;margin: 0 0 0 6px;" class="upload-demo" :action="uploadUrl" :on-success="uploadSuccess"
						   :show-file-list="false" accept="image/*,.mp4">
					<el-button type="success">上传文件</el-button>
				</el-upload>
			</div>
		</el-dialog>
	</div>
</template>

<script setup>
	import axios from 'axios'
    import moment from "moment"
	import {
		ElMessageBox
	} from 'element-plus'
	import {
		toRefs,
		defineEmits,
		getCurrentInstance,
		ref,
		onBeforeUnmount,
		computed,
	} from 'vue';

	import {
		useRouter,
		useRoute
	} from 'vue-router';
	const router = useRouter()
	const context = getCurrentInstance()?.appContext.config.globalProperties;
	const emit = defineEmits(['collapseChange'])
	const role = context?.$toolUtil.storageGet('sessionTable')
	const roleName = context?.$toolUtil.storageGet('role')

	const props = defineProps({
		collapse: Boolean
	})
	const {collapse} = toRefs(props)

	//获取用户信息
	import { useStore } from 'vuex'
	const store = useStore()
	const user = computed(()=>store.getters['user/session'])
	const avatar = ref(store.state.user.avatar)
	if(!store.state.user.session.id){
		store.dispatch('user/getSession')
	}
	const toggleClick = () => {
		emit('collapseChange')
	}
	// 退出登录
	const onLogout = () => {
		let toolUtil = context?.$toolUtil
		store.dispatch('delAllCachedViews')
		store.dispatch('delAllVisitedViews')
        store.dispatch('user/loginOut')
		toolUtil.storageClear()
		router.replace({
			name: "login"
		});
	}
	// 跳转前台
	const frontClick = () => {
        window.open(`${context.$config.url}client/index.html#/index/home`,'_blank')
	}
	// 个人中心
	const centerClick = () => {
		router.push(`/${role}Center`)
	}
	// 修改密码
	const updatepasswordClick = () => {
		router.push(`/updatepassword`)
	}
	const recordList = ref([])
	const recordVisible = ref(false)
	const chatVisible = ref(false)
	const nowfid = ref(0)
	const nowfpic = ref('')
	const nowname = ref('')
	const chatList = ref([])
	const chatForm = ref({
		content: ''
	})
	const chatTimer = ref(null)
	const uploadUrl = context.$config.url + 'file/upload'
	const hasChatList = [
		'yonghu',
		'tuangoushangpin',
		'shangpinxinxi',
		'shangjia',
	]
	const chatRecordClick = () => {
		getRecordList()
	}
	const changeHasChat = () => {
		let table = context.$toolUtil.storageGet('sessionTable')
		if(hasChatList.includes(table)){
			return true
		}else{
			false
		}
	}
	const getRecordList = () => {
		context.$http.get('chatfriend/page2', {
			params: {
				uid: user.value.id,
				type: 2
			}
		}).then(res => {
			if (res.data && res.data.code == 0) {
				recordList.value = res.data.data.list
				recordVisible.value = true
			}
		})
	}
	const chatClick = (row) => {
		nowfid.value = row.fid
		nowfpic.value = row.picture
		nowname.value = row.name
        scrollFlag.value = true
		getChatList()
		chatVisible.value = true
	}
    const scrollFlag = ref(true)
	const getChatList = () => {
		context.$http.get('chatmessage/mlist', {
			params: {
				page: 1,
				limit: 1000,
				uid: user.value.id,
				fid: nowfid.value
			}
		}).then(res => {
			if (res.data && res.data.code == 0) {
				chatList.value = res.data.data.list
				let div = document.getElementsByClassName('chat-content')[0]
				setTimeout(() => {
                    if (div){
                        if(div.scrollTop+div.clientHeight==div.scrollHeight || scrollFlag.value){
                            div.scrollTop = div.scrollHeight
                            scrollFlag.value = false
                        }
                    }
				}, 0)
				chatTimer.value = setTimeout(() => {
					getChatList()
				}, 5000)
			}
		})
	}
	const clearChat = () => {
		clearTimeout(chatTimer.value)
		chatList.value = []
		getRecordList()
	}
	const uploadSuccess = (res) => {
		if (res.code == 0) {
			clearTimeout(chatTimer.value)
			context.$http.post('chatmessage/add', {
				uid: user.value.id,
				fid: nowfid.value,
				content: 'file/' + res.file,
				format: 2
			}).then(res2 => {
				chatForm.value = {
					content: ''
				}
                scrollFlag.value = true
				getChatList()
			})
		}
	}
	const addChat = () => {
        if(!chatForm.value.content.trim())return context.$message.error("消息内容不能为空")
		clearTimeout(chatTimer.value)
		let sensitiveWords = "傻逼,脑残,二货,去死";
		let sensitiveWordsArr = [];
		if (sensitiveWords) {
			sensitiveWordsArr = sensitiveWords.split(",");
		}
		for (var i = 0; i < sensitiveWordsArr.length; i++) {
			//全局替换
			var reg = new RegExp(sensitiveWordsArr[i], "g");
			//判断内容中是否包括敏感词
			if (chatForm.value.content.indexOf(sensitiveWordsArr[i]) > -1) {
				// 将敏感词替换为 **
				chatForm.value.content = chatForm.value.content.replace(reg, "**");
			}
		}
		context.$http.post('chatmessage/add', {
			uid: user.value.id,
			fid: nowfid.value,
			content: chatForm.value.content,
			format: 1
		}).then(res2 => {
			chatForm.value = {
				content: ''
			}
            scrollFlag.value = true
			getChatList()
		})
	}
</script>

<style lang="scss" scoped>
	// 总盒子
	.top_view {
		// 左边盒子
		.top_left_view {
			display: flex;
			width: 300px;
			align-items: center;
			height: 100%;
			// 折叠按钮盒子
			.fold_view {
				padding: 0 15px;
				// 图标
				.icons {
				}
			}
		}
		// 标题
		.projectTitle{
		}
		// 右部盒子
		.top_right_view{
			// 头像盒子
			.avatar-container {
				.avatar-wrapper {
					// 昵称
					.nickname {
					}
					// 头像
					.user-avatar {
					}
					// 图标
					.el-icon-arrow-down {
					}
				}
			}
		}
	}
	// 下拉盒子
	.el-dropdown-menu{
		background: #fff;
		min-width: 110px;
		// 下拉盒子itme
		:deep(.el-dropdown-menu__item){
			color: #666;
			background: #fff;
		}
		// item悬浮
		:deep(.el-dropdown-menu__item:hover){
			color: #000;
			background: none;
		}
	}
	.section-content {
		cursor: pointer;
		padding: 20px;
		box-shadow: 0px 4px 10px 0px rgba(0, 0, 0, 0.3);
		margin: 0 0 20px;
		color: #333;
		background: #fff;
		display: flex;
		width: 100%;
		border-color: #efefef;
		border-width: 0;
		align-items: center;
		border-style: solid;
		position: relative;
	}

	.section-content:hover {
		color: #fff;
		background: #DF847F10;
	}
	.chat-content {
		padding-bottom: 20px;
		width: 100%;
		margin-bottom: 10px;
		max-height: 300px;
		height: 300px;
		overflow-y: scroll;
		border: 1px solid #eeeeee;
		background: #fff;

		.left-content {
			float: left;
			margin-bottom: 10px;
			padding: 10px;
			max-width: 80%;
			display: flex;
			align-items: center;
		}

		.right-content {
			float: right;
			margin-bottom: 10px;
			padding: 10px;
			max-width: 80%;
			display: flex;
			align-items: center;
		}
	}

	.clear-float {
		clear: both;
	}
	.noList {
		color: #9e9e9e;
		width: 100%;
		text-align: center;
		padding: 60px 0;
	}
</style>
<style>
/*总盒子*/
.top_view{
    background: var(--theme);
    height: 80px;
    width: 100%;
    display: flex;
    align-items: center;
    justify-content: center;
    position: fixed;
    left: 0px;
    top: 0px;
    z-index: 9;
    padding: 0px;
    border-width: 0px;
    border-style: solid;
    border-color: rgb(221, 245, 206);
    font-size: 16px;
    color:#fff;
}
/*标题*/
.top_view .projectTitle{
    font-size: 20px;
    margin: 0px;
    padding: 0px 0px 0px 40px;
    line-height: 1;
    width: 100%;
    font-weight: 500;
    color:#fff;
    background:none;
    text-align:left;
    text-shadow:0 -2px 0 rgba(0, 0, 0, 0.25);
    letter-spacing: 1px;
}

/*左边盒子*/
.top_left_view{
    width: auto !important;
    color:#fff;
    order:2;
}
/*总盒子*/
.top_left_view .weather_time_view{
    display: flex;
    align-items: center;
}
/*天气盒子*/
.top_left_view .weather_time_view .weather{
    padding: 0px 10px;
    border-image: initial;
    display: flex;
    align-items: center;
    line-height: 1;
    margin-right: 30px;
}
.top_left_view .weather_time_view .weather .city{
    display: inline-block;
    white-space: nowrap;
}
.top_left_view .weather_time_view .weather .wea{
    display: inline-block;
    white-space: nowrap;
}
/*时间盒子*/
.top_left_view .weather_time_view .time{
    display: flex;
    align-items: center;
    padding: 0px 10px;
    margin-right: 30px;
}
.top_left_view .weather_time_view .time .date{
    display: inline-block;
    white-space: nowrap;
    margin: 0px 10px 0px 0px
}
.top_left_view .weather_time_view .time .timer{
    display: inline-block;
    white-space: nowrap;
    line-height: 1;
}

/*折叠按钮*/
.top_left_view .fold_view{
    display: none;
    padding: 0px;
    margin: 0px 0px 0px 40px;
    background: none;
    border-radius: 50%;
    width: 32px;
    height: 32px;
    align-items: center;
    justify-content: center;
 }
.top_left_view .fold_view:hover {
 }
/*图标*/
.top_left_view .fold_view .icons{
    font-size: 24px;
    color: rgb(255, 255, 255);
}

/*系统公告*/
.top_view .notice-btn{
    background: rgba(255, 255, 255,0);
    border-color: rgba(255, 255, 255,0);
    padding: 0 10px;
    margin-right:20px;
    height: 44px;
    line-height: 44px;
    font-size: 16px;
    color:#fff;
    border-radius:30px;
    order:3;
}
.top_view .notice-btn:hover{
    color:#fff;
}
.top_view .notice-btn .iconfont{
    font-size: 20px;
}

/*右边盒子*/
/*无下拉操作栏*/
.top_right_view{
    width: auto;
    height: 100%;
    display: flex;
    align-items: center;
    justify-content: flex-end;
    order: 5;
 }
.top_right_view .actionBar{ }
.top_right_view .actionBar .item { float:left; margin-right:20px; margin-bottom:5px; font-size:16px; color:#fff; font-weight:500;  }
.top_right_view .actionBar .item:hover { color:#fff; cursor:pointer;  }
.top_right_view .actionBar .item i { font-size:18px; }

.top_view .user-info{
    position:absolute;
    left:20px;
    display: flex;
    align-items: center;
}
.top_view .user-info .img-wrapper{
    margin-right:10px;
}
.top_view .user-info .img-wrapper .user-avatar{
    width:50px;
}
.top_view .user-info .nickname{
    font-size:16px;
    color:#fff;
    white-space:nowrap;
}

/*头像下拉 默认*/
/*头像盒子*/
.top_right_view .el-dropdown{
    height: 50px;
    margin: 0px 30px 0px 0px;
    cursor: pointer;
    color: rgb(255, 255, 255);
    display: flex;
    align-items: center;
}
/*item*/
.top_right_view .el-dropdown .avatar-wrapper{
    margin: 5px 0px 0px;
    position: relative;
    display: flex;
    align-items: center;
}
/*昵称*/
.top_right_view .el-dropdown .avatar-wrapper .nickname{
    cursor: pointer;
    margin: 0px 5px;
    line-height: 44px;
    color: rgb(255, 255, 255);
    font-size:16px;
    white-space:nowrap;
    order:2;
}
/*头像*/
.top_right_view .el-dropdown .avatar-wrapper .user-avatar{
    cursor: pointer;
    width: 40px;
    height: 40px;
    border-radius: 30px;
}
/*图标*/
.top_right_view .el-dropdown .avatar-wrapper .el-icon{
    color: rgb(255, 255, 255);
    order:3;
}

</style>
