<template>
  <div>
    <div class="login_view">
      <div class="outTitle_view">
        <div class="outTilte">基于SpringBoot的社区团购系统登录</div>
      </div>
      <el-form :model="loginForm" class="login_form">
        <div class="list_item" v-if="loginType == 1">
          <div class="list_label">账号：</div>
          <input
            class="list_inp"
            v-model="loginForm.username"
            name="username"
            placeholder="请输入账号"
          />
        </div>
        <div class="list_item" v-if="loginType == 1">
          <div class="list_label">密码：</div>
          <input
            class="list_inp"
            v-model="loginForm.password"
            type="password"
            placeholder="请输入密码"
            @keydown.enter.native="handleLogin"
          />
        </div>
        <div class="list_type" v-if="userList.length > 1">
          <div class="list_label">用户类型：</div>
          <el-select v-model="loginForm.role" placeholder="请选择用户类型">
            <el-option
              v-for="(item, index) in userList"
              :label="item.roleName"
              :value="item.roleName"
            ></el-option>
          </el-select>
        </div>
        <div class="btn_view">
          <el-button class="login" v-if="loginType == 1" type="success" @click="handleLogin"
            >登录</el-button
          >
          <el-button class="register" type="primary" @click="handleRegister('yonghu')"
            >注册用户</el-button
          >
        </div>
      </el-form>
    </div>
  </div>
</template>
<script setup>
import { ref, getCurrentInstance, nextTick, onMounted } from 'vue'
import { useStore } from 'vuex'
const store = useStore()
import menu from '@/utils/menu'
const userList = ref([])
const menus = ref([])
const loginForm = ref({
  role: '',
  username: '',
  password: '',
})
const tableName = ref('')
const loginType = ref(1)
const context = getCurrentInstance()?.appContext.config.globalProperties
//注册
const handleRegister = tableName => {
  context?.$router.push(`/${tableName}Register`)
}
const handleLogin = () => {
  if (!loginForm.value.username) {
    context?.$toolUtil.message('请输入用户名', 'error')
    return
  }
  if (!loginForm.value.password) {
    context?.$toolUtil.message('请输入密码', 'error')
    return
  }
  if (userList.value.length > 1) {
    if (!loginForm.value.role) {
      context?.$toolUtil.message('请选择角色', 'error')
      verifySlider.reset()
      return
    }
    for (let i = 0; i < menus.value.length; i++) {
      if (menus.value[i].roleName == loginForm.value.role) {
        tableName.value = menus.value[i].pathName || menus.value[i].tableName
      }
    }
  } else {
    tableName.value = userList.value[0].pathName || userList.value[0].tableName
    loginForm.value.role = userList.value[0].roleName
  }
  login()
}
const login = () => {
  context
    ?.$http({
      url: `${tableName.value}/login?username=${loginForm.value.username}&password=${loginForm.value.password}`,
      method: 'post',
    })
    .then(
      res => {
        context?.$toolUtil.storageSet('frontToken', res.data.token)
        context?.$toolUtil.storageSet('frontRole', loginForm.value.role)
        context?.$toolUtil.storageSet('frontSessionTable', tableName.value)
        store.dispatch('user/getSession')
        let path = context?.$toolUtil.storageGet('toPath')
        if (path && path != '/login') {
          context?.$router.push(path)
          context?.$toolUtil.storageRemove('toPath')
          return
        }
        context?.$router.push(`/index/${tableName.value}Center`)
      },
      err => {}
    )
}
//获取菜单
const getMenu = async () => {
  let arr = menu.list()
  if (!arr) {
    let res = await context?.$http.get('menu/list')
    context?.$toolUtil.storageSet('menus', res.data.data.list[0].menujson)
    arr = JSON.parse(res.data.data.list[0].menujson)
  }
  menus.value = arr
  for (let i = 0; i < menus.value.length; i++) {
    if (menus.value[i].hasFrontLogin == '是') {
      userList.value.push(menus.value[i])
    }
  }
}
//初始化
const init = async () => {
  await getMenu()
  loginForm.value.role = userList.value[0].roleName
}
onMounted(() => {
  init()
})
</script>

<style lang="scss" scoped>
.login_view {
  background-image: url('http://clfile.zggen.cn/20250406/7c3e19862b3d411c90f20ddc5ce5e3b2.jpg') !important;
  // 标题盒子
  .outTitle_view {
    .outTilte {
    }
  }
  // 表单盒子
  .login_form {
  }
  // item盒子
  .list_item {
    // label
    .list_label {
    }
    // 输入框
    .list_inp {
    }
  }
  .list_type {
    .list_label {
    }
    // 下拉框样式
    :deep(.el-select) {
      //去掉默认样式
      .select-trigger {
        height: 100%;
        .el-input {
          height: 100%;
        }
      }
    }
  }
  // 按钮盒子
  .btn_view {
    // 登录
    .login {
    }
    // 注册
    .register {
    }
  }
}
</style>
<style>
.login_view {
  min-height: 100vh;
  position: relative;
  background: url(http://clfile.zggen.cn/20250406/7c3e19862b3d411c90f20ddc5ce5e3b2.jpg) no-repeat
    center center / cover;
  display: flex;
  flex-direction: column;
  align-items: center;
  justify-content: center;
}

.login_view .login_form {
  width: min(560px, 92vw);
  margin: 40px auto;
  box-shadow:
    0 18px 48px rgba(234, 88, 12, 0.12),
    0 4px 14px rgba(67, 20, 6, 0.06);
  padding: 32px 36px 36px;
  display: flex;
  flex-wrap: wrap;
  justify-content: center;
  border-radius: 16px;
  background: rgba(255, 255, 255, 0.96);
  border: 1px solid rgba(253, 230, 138, 0.65);
  backdrop-filter: blur(8px);
  transition:
    box-shadow 0.3s ease,
    transform 0.3s ease;
  margin-left: 50vw;
}

.login_view .outTitle_view {
  display: flex;
  align-items: center;
  justify-content: center;
  padding: 24px 16px 12px;
  margin: 0 auto;
  width: 100%;
  max-width: min(560px, 92vw);
}
.login_view .outTitle_view .outTilte {
  color: rgb(51, 51, 51);
  font-size: 30px;
  font-weight: 600;
  -webkit-box-reflect: below 2px linear-gradient(transparent, rgba(0, 0, 0, 0.1));
  background: linear-gradient(
    90deg,
    var(--theme2-color) 0%,
    var(--theme-color) 50%,
    var(--theme2-color) 100%
  );
  -webkit-background-clip: text;
  -webkit-text-fill-color: transparent;
  margin-left: 35vw;
  white-space: nowrap;
}

.login_view .login_form .list_item {
  display: flex;
  align-items: center;
  width: 100%;
  justify-content: center;
  margin: 0px 10px 20px 0px;
}
.login_view .login_form .list_item .list_label {
  width: 120px;
  text-align: right;
  font-size: 16px;
}
.login_view .login_form .list_item .list_inp {
  height: 36px;
  line-height: 36px;
  border: none;
  border-bottom: 1px solid rgb(221, 221, 221);
  padding: 0px 10px;
  width: calc(100% - 120px);
  font-size: 16px;
}

.login_view .login_form .list_type {
  display: flex;
  align-items: center;
  width: 100%;
  justify-content: center;
  margin: 0px 10px 20px 0px;
}
.login_view .login_form .list_type .list_label {
  width: 120px;
  text-align: right;
  font-size: 16px;
}
.login_view .login_form .list_type .el-select {
  line-height: 36px;
  border: none;
  border-bottom: 1px solid rgb(221, 221, 221);
  box-sizing: border-box;
  width: calc(100% - 120px);
  padding: 0px 10px;
  border-radius: 0px;
  font-size: 16px;
  color: rgb(102, 102, 102);
}
.login_view .login_form .list_type .el-select .el-input__inner {
  font-size: 16px;
}

.login_view .login_form .listCode_view {
  display: flex;
  align-items: center;
  width: 100%;
  justify-content: center;
  margin: 0px 10px 10px 0px;
}
.login_view .login_form .listCode_view .listCode_label {
  width: 120px;
  text-align: right;
  font-size: 16px;
}
.login_view .login_form .listCode_view .listCode_inp {
  height: 36px;
  line-height: 36px;
  border: none;
  border-bottom: 1px solid rgb(221, 221, 221);
  padding: 0px 10px;
  width: calc(100% - 220px);
  font-size: 16px;
}
.login_view .login_form .listCode_view .listCode_btn {
  width: 100px;
  border-style: solid;
  border-color: rgb(221, 221, 221);
  border-image: initial;
  height: 36px;
  line-height: 36px;
  text-align: center;
  border-width: 0px 0px 1px 0px;
}

.login_view .login_form .btn_view {
  display: flex;
  flex-wrap: wrap;
  align-items: center;
  justify-content: center;
  padding: 24px 0 0;
  width: 100%;
  gap: 12px;
}
.login_view .login_form .btn_view .login {
  margin: 0;
  padding: 0 28px;
  width: auto;
  min-height: 42px;
  font-size: 16px;
  font-weight: 600;
  color: rgb(255, 255, 255);
  border-radius: 12px;
  border: 0px;
  background: linear-gradient(135deg, #ea580c 0%, #f97316 100%);
  cursor: pointer;
  min-width: 120px;
  box-shadow: 0 10px 26px rgba(234, 88, 12, 0.28);
  transition:
    transform 0.2s ease,
    box-shadow 0.2s ease,
    filter 0.2s ease;
}
.login_view .login_form .btn_view .login:hover {
  transform: translateY(-1px);
  filter: brightness(1.03);
  box-shadow: 0 14px 32px rgba(234, 88, 12, 0.34);
}
.login_view .login_form .btn_view .register {
  margin: 0;
  padding: 0 20px;
  min-height: 42px;
  font-size: 16px;
  color: #57534e;
  border-radius: 12px;
  border: 1px solid rgba(253, 230, 138, 0.9);
  background: #fffbeb;
  cursor: pointer;
  transition:
    background 0.2s ease,
    border-color 0.2s ease,
    color 0.2s ease;
}
.login_view .login_form .btn_view .register:hover {
  color: var(--theme-color);
}
.login_view .login_form .btn_view .forget {
  margin: 0px 10px 12px 0px;
  padding: 0px;
  width: 100%;
  height: 44px;
  font-size: 16px;
  color: rgb(102, 102, 102);
  border-radius: 4px;
  border: 0px;
  background: none;
  cursor: pointer;
  text-align: right;
}
.login_view .login_form .btn_view .forget:hover {
  text-decoration: underline;
}
.login_view .login_form .face {
  font-size: 16px;
  color: rgb(102, 102, 102);
  cursor: pointer;
  padding: 0px 0px 0px 120px;
  text-align: left;
  width: 100%;
}

@media (max-width: 576px) {
  .login_view .login_form .list_item,
  .login_view .login_form .list_type {
    flex-wrap: wrap;
    margin-right: 0;
  }
  .login_view .login_form .list_item .list_label,
  .login_view .login_form .list_type .list_label {
    width: 100%;
    text-align: left;
    margin-bottom: 6px;
  }
  .login_view .login_form .list_item .list_inp,
  .login_view .login_form .list_type .el-select {
    width: 100% !important;
  }
  .login_view .login_form .face {
    padding-left: 0;
    text-align: center;
  }
}
</style>
