<template>
  <div class="home">
    <div class="index_top">
      <div class="index_top_title"><span>基于SpribgBoot的社区团购系统</span></div>
      <div class="index_top_right">
        <el-button
          v-if="!Token"
          class="login"
          type="success"
          icon="User"
          circle
          @click="loginClick"
        >
          登录
        </el-button>
        <div class="user" v-if="Token">
          <el-dropdown class="avatar-container" trigger="hover">
            <div class="avatar-wrapper">
              <img class="user-avatar" :src="store.getters['user/avatar']" style="width: 50px" />
              <div class="nickname">{{ store.getters['user/username'] }}</div>
              <el-icon class="el-icon-arrow-down">
                <arrow-down />
              </el-icon>
            </div>
            <template #dropdown>
              <el-dropdown-menu class="user-dropDown" slot="dropdown">
                <el-dropdown-item @click="menuHandler('center')" class="center">
                  <span>个人中心</span>
                </el-dropdown-item>
                <el-dropdown-item @click="loginOut" class="loginOut">
                  <span>退出登录</span>
                </el-dropdown-item>
              </el-dropdown-menu>
            </template>
          </el-dropdown>
        </div>
      </div>
    </div>
    <el-scrollbar class="contain_view">
      <div class="menu-wrapper">
        <el-scrollbar wrap-class="scrollbar-wrapper" class="menu_scrollbar">
          <el-menu
            :default-openeds="[]"
            :unique-opened="true"
            :default-active="menuIndex"
            class="menu_view"
            mode="horizontal"
            :ellipsis="false"
            @select="menuChange"
            :key="menuIndex"
          >
            <el-menu-item class="first-item" index="/index/home" @click="menuHandler('/')">
              <template #title>
                <span>首页</span>
              </template>
            </el-menu-item>
            <template v-for="(menu, index) in menuList" :key="menu.menu">
              <el-sub-menu
                v-if="menu.child.length > 1"
                :index="menu.name"
                class="first-item"
                :teleported="true"
              >
                <template #title>
                  <span>{{ menu.name }}</span>
                </template>
                <el-menu-item
                  class="second-item"
                  v-for="(child, index1) in menu.child"
                  :key="index1"
                  :index="child.url"
                  @click="menuHandler(child.url)"
                  >{{ child.name }}
                </el-menu-item>
              </el-sub-menu>
              <el-menu-item
                v-else
                :index="menu.child[0].url"
                class="first-item"
                @click="menuHandler(menu.child[0].url)"
              >
                <template #title>
                  <span>{{ menu.child[0].name }}</span>
                </template>
              </el-menu-item>
            </template>
          </el-menu>
        </el-scrollbar>
      </div>
      <div class="rotation_view">
        <mySwiper
          :type="3"
          :data="rotationList"
          :autoHeight="false"
          :autoplay="true"
          :loop="true"
          :navigation="true"
          :pagination="false"
          :paginationType="1"
          :scrollbar="false"
          :slidesPerView="1"
          :spaceBetween="20"
          :centeredSlides="false"
          :freeMode="false"
          :effectType="0"
          :direction="horizontal"
        >
          <template #default="scope">
            <img
              :style="{}"
              :src="scope.row.value ? $config.url + scope.row.value : ''"
              @click="carouselClick(scope.row.url)"
            />
          </template>
        </mySwiper>
      </div>
      <router-view :key="routerKey" />
      <el-backtop :right="100" :bottom="100" />
      <div class="bottom_view">
        <img
          class="bottom_img"
          src="http://chy2.gdnxeco.com/20230912/0d5ae5e141c14b06a52a7c4c2f8ea4cf.jpg"
          alt=""
        />
        <div class="bottom_company"></div>
        <div class="bottom_record"></div>
        <div class="bottom_desc"></div>
      </div>
    </el-scrollbar>
  </div>
</template>
<script setup>
import menu from '@/utils/menu'
import axios from 'axios'
import moment from 'moment'
import { ref, onBeforeUnmount, getCurrentInstance, nextTick, computed, watch, provide } from 'vue'
import { useRouter, useRoute } from 'vue-router'
import { useStore } from 'vuex'
const store = useStore()
const router = useRouter()
const route = useRoute()
const context = getCurrentInstance()?.appContext.config.globalProperties
const Token = ref('')
const interval = ref(null)
const nowDate = ref(new Date())
if (localStorage.getItem('frontToken') && !store.getters['user/session'].id) {
  store.dispatch('user/getSession')
}
onBeforeUnmount(() => {
  clearInterval(interval.value)
})
// 获取默认菜单index
const setMenuIndex = path => {
  if (path) {
    menuIndex.value = path.replace('Detail', 'List')
  } else {
    menuIndex.value = ''
  }
}
// 默认菜单index
const menuIndex = ref('')
const routerKey = ref(Math.random())
watch(
  () => router.currentRoute.value,
  (value, oldValue) => {
    Token.value = context?.$toolUtil.storageGet('frontToken')
    if (value.path == oldValue?.path) {
      //改变路由query时重新加载router-view
      routerKey.value = Math.random()
    }
    if (value.query.centerType) {
      setMenuIndex()
    } else {
      setMenuIndex(value.path)
    }
  },
  {
    immediate: true,
  }
)
const init = () => {
  // 获取菜单
  getMenu()
  // 赋值token
  Token.value = context?.$toolUtil.storageGet('frontToken')
  // 时间
  interval.value = setInterval(() => {
    nowDate.value = new Date()
  }, 1000)
  // 轮播图
  getRotationList()
  if (Token.value) {
    getSession()
  }
}
// 切换菜单保存index
const menuChange = e => {
  if (e == 'chat') return
}
// 轮播图
const rotationList = ref([])
const baseUrl = context?.$config.url
const getRotationList = () => {
  context
    ?.$http({
      url: 'config/list',
      method: 'get',
      params: {
        page: 1,
        limit: 3,
      },
    })
    .then(res => {
      rotationList.value = res.data.data.list
    })
}

// 轮播图跳转
const carouselClick = url => {
  if (url) {
    if (url.startsWith('http')) {
      window.open(url)
    } else {
      context.$router.push(url)
    }
  }
}
const menuList = ref([])
const role = ref('')
const getMenu = () => {
  let params = {
    page: 1,
    limit: 1,
    sort: 'id',
  }
  context
    ?.$http({
      url: 'menu/list',
      method: 'get',
      params: params,
    })
    .then(res => {
      context?.$toolUtil.storageSet('menus', res.data.data.list[0].menujson)
    })
  menuList.value = context?.$config.menuList
}
const loginClick = () => {
  context?.$toolUtil.storageSet('toPath', window.history.state.current)
  router.push('/login')
}
const loginOut = () => {
  context?.$toolUtil.message('退出成功', 'success')
  context?.$toolUtil.storageClear()
  router.replace('/index/home')
  Token.value = ''
}
//菜单跳转
const menuHandler = name => {
  if (name == 'center') {
    name = `${context?.$toolUtil.storageGet('frontSessionTable')}Center`
    menuChange('center')
  }
  router.push(name)
}
// 获取用户信息
const getSession = () => {
  context
    ?.$http({
      url: `${context?.$toolUtil.storageGet('frontSessionTable')}/session`,
      method: 'get',
    })
    .then(res => {
      context?.$toolUtil.storageSet('userid', res.data.data.id)
      if (context?.$toolUtil.storageGet('frontSessionTable') == 'yonghu') {
        context?.$toolUtil.storageSet('frontName', res.data.data.yonghu)
      }
      if (context?.$toolUtil.storageGet('frontSessionTable') == 'yonghu') {
        context?.$toolUtil.storageSet('headportrait', res.data.data.touxiang)
      }
      if (context?.$toolUtil.storageGet('frontSessionTable') == 'shangjia') {
        context?.$toolUtil.storageSet('frontName', res.data.data.shangjia)
      }
      if (context?.$toolUtil.storageGet('frontSessionTable') == 'shangjia') {
        context?.$toolUtil.storageSet('headportrait', res.data.data.touxiang)
      }
    })
}
init()
</script>
<style lang="scss" scoped>
/* 头部 */
.index_top {
  .index_top_title {
  }
  .index_top_right {
    line-height: 1;
    .weather_time_view {
      display: flex;
      align-items: center;
      .weather {
        padding: 0 10px;
        flex-direction: row;
        display: none;
        font-size: 16px;
        line-height: 1;
        justify-content: center;
        align-items: center;
        .city {
          padding: 0;
          margin: 0 10px 0 0;
        }
        .wea {
        }
      }
      .time {
        padding: 0 20px;
        flex-direction: row;
        display: flex;
        font-size: 16px;
        line-height: 1;
        justify-content: center;
        align-items: center;
        .date {
          padding: 0;
          margin: 0 10px 0 0;
        }
        .timer {
          padding: 0;
          margin: 0;
        }
      }
    }
  }
  // 登录按钮
  :deep(.el-button--success) {
    border-radius: 4px;
    padding: 10px;
    margin: 0 10px 0 0;
    color: #fff;
    background: var(--theme-color);
    border-color: var(--theme-color);
  }
  :deep(.el-button--success:hover) {
    background: var(--theme3-color);
    border-color: var(--theme3-color);
    filter: brightness(1.02);
  }
  // 登出按钮
  :deep(.el-button--danger) {
    border-radius: 4px;
    padding: 10px;
    color: #fff;
    background: #d59bf6;
    border-color: #d59bf6;
  }
  :deep(.el-button--danger:hover) {
    background: #d59bf699;
    border-color: #d59bf699;
  }
}
// 轮播盒子
.rotation_view {
}
// 底部
.bottom_view {
  // 图片
  .bottom_img {
    border-radius: 100%;
    object-fit: cover;
    display: none;
    width: 44px;
    height: 44px;
  }
  // 公司
  .bottom_company {
    margin: 10px 0 0;
  }
  // 备案号
  .bottom_record {
    margin: 10px 0 0;
  }
  // 联系方式
  .bottom_desc {
    margin: 10px 0 0;
  }
}

.contain_view {
  margin: 0;
  background: linear-gradient(180deg, #fffbeb 0%, #ffffff 28%, #fff7ed 100%);
  position: relative;
  height: calc(100% - 60px);
}
.el-aside {
  transition: width 0.15s;
  -webkit-transition: width 0.15s;
  -moz-transition: width 0.15s;
  -webkit-transition: width 0.15s;
  -o-transition: width 0.15s;
}
.el-sub-menu__hide-arrow {
  display: block !important;
}
.menu_scrollbar {
}
// 总盒子
.menu_view {
  // 一级菜单
  :deep(.first-item) {
    // 图标

    // 标题
    .el-sub-menu__title,
    span {
    }

    //箭头
    .el-sub-menu__icon-arrow {
    }
  }
  // 选中
  :deep(.is-active) {
  }

  // 悬浮
  :deep(.first-item:hover) {
  }
}
</style>
<style lang="scss">
// 二级
.menu_popper {
  border: none !important;
  background: none !important;

  // 二级总盒子
  .el-menu--popup {
  }

  // 二级菜单
  .second-item {
  }

  // 选中
  .is-active {
  }

  // 悬浮
  .second-item:hover {
  }
}
</style>
<style>
.home {
  min-height: 100vh;
  background: linear-gradient(180deg, #fffbeb 0%, #ffffff 40%, #fff7ed 100%);
}

.index_top {
  width: 100%;
  display: flex;
  align-items: center;
  height: 110px;
  background: rgba(255, 255, 255, 0.92);
  backdrop-filter: blur(10px);
  justify-content: space-between;
  padding: 0px 16%;
  box-sizing: border-box;
  z-index: 1002;
  position: relative;
  border-bottom: 1px solid rgba(253, 230, 138, 0.65);
  box-shadow: 0 10px 40px rgba(234, 88, 12, 0.06);
}

.index_top .index_top_title {
  font-size: 22px;
  color: #9a3412;
  font-weight: 700;
  letter-spacing: 0.5px;
}

.index_top .index_top_right {
  display: flex;
  align-items: center;
  margin-right: 20px;
}

.index_top .notice-btn {
  background: none;
  border: 0px solid #dcdfe6;
  color: #333;
  margin: 0;
  padding: 10px;
  font-size: 16px;
  border-radius: 0px;
}
.index_top .notice-btn:hover {
  color: var(--theme);
}

.index_top .notice-btn .iconfont {
  font-size: 16px;
}

.index_top .index_top_right .chat {
  margin-right: 10px;
}
.index_top .index_top_right .chat:hover {
  color: var(--theme);
}
.index_top .index_top_right .chat .iconfont {
  font-size: 16px;
}
.index_top .index_top_right .chat span {
  font-size: 16px;
}

.index_top .index_top_right .cart {
  margin-right: 10px;
  font-size: 16px;
}
.index_top .index_top_right .cart:hover {
  color: var(--theme);
}
.index_top .index_top_right .cart .iconfont {
  font-size: 16px;
  margin-right: 5px;
}

.index_top .index_top_right .toBack {
  margin-right: 15px;
}
.index_top .index_top_right .toBack .el-button {
  border: 0;
  padding: 0;
  font-size: 16px;
  color: #333;
}
.index_top .index_top_right .toBack .el-button:hover {
  color: var(--theme);
  background: none;
}

.index_top .user .avatar-wrapper {
  display: flex;
  align-items: center;
  cursor: pointer;
}

.index_top .user .avatar-wrapper .user-avatar {
  width: 36px !important;
  height: 36px;
  border-radius: 100%;
  margin-right: 5px;
}

.index_top .user .avatar-wrapper .nickname {
  font-size: 16px;
  margin-right: 5px;
}

.index_top .user .avatar-wrapper .el-icon-arrow-down {
}
.index_top .el-icon svg {
  color: #fff;
}

.user-dropDown {
  padding: 10px 0;
  margin: 5px 0;
  background: #fff;
  border: 1px solid #e6ebf5;
  border-radius: 4px;
  -webkit-box-shadow: 0 2px 12px 0 rgba(0, 0, 0, 0.1);
  box-shadow: 0 2px 12px 0 rgba(0, 0, 0, 0.1);
}
.user-dropDown li {
  padding: 0 20px !important;
  line-height: 36px !important;
  font-size: 14px !important;
  color: #606266 !important;
}
.user-dropDown li:hover {
  color: #fff !important;
  background: var(--theme2-color) !important;
}

.user-dropDown li.loginOut {
  background: none !important;
  border-color: none !important;
  color: #666 !important;
}
.user-dropDown li.loginOut:hover {
  border-radius: 0px !important;
  background: var(--theme2-color) !important;
  color: #fff !important;
}
.bottom_view {
  width: 100%;
  background: linear-gradient(180deg, #14532d 0%, #166534 45%, #15803d 100%);
  display: flex;
  justify-content: center;
  align-items: center;
  flex-direction: column;
  padding: 28px 0px;
  min-height: 130px;
  border-width: 0px;
  border-style: solid;
  border-color: #d6dee0;
  font-size: 16px;
  color: #ecfdf5;
  line-height: 1.8;
  margin-top: 0px;
  box-shadow: 0 -12px 40px rgba(22, 101, 52, 0.15);
}

.menu_scrollbar {
  width: 100%;
  margin: 0 auto 8px;
  background: linear-gradient(90deg, #ea580c 0%, #f97316 52%, #fb923c 100%);
  border-width: 0px;
  border-style: solid;
  border-color: rgb(239, 239, 239);
  border-radius: 0;
  box-shadow: 0 10px 28px rgba(234, 88, 12, 0.18);
}
.menu_scrollbar .el-scrollbar__view {
  padding-bottom: 0px;
}

.menu_scrollbar .menu_view {
  background: transparent;
  display: flex;
  flex-wrap: wrap;
  justify-content: center;
  border-bottom: none;
  height: 56px;
  padding-top: 8px;
}

.menu_scrollbar .menu_view .el-menu-item,
.menu_scrollbar .menu_view .el-sub-menu,
.menu_scrollbar .menu_view .el-sub-menu .el-sub-menu__title {
  height: 48px;
  line-height: 48px;
  border: 0px;
  color: #fff;
  font-size: 16px;
  padding: 0px 10px;
  cursor: pointer;
  white-space: nowrap;
  list-style: none;
  background: transparent;
  min-width: 80px;
  text-align: center;
}
.menu_scrollbar .menu_view .el-menu-item:hover,
.menu_scrollbar .menu_view .el-sub-menu:hover {
  background: var(--theme-color);
  color: #fff !important;
  clip-path: polygon(0 0, 100% 0, 95% 100%, 0% 100%);
  border-radius: 10px 10px 0 0;
}
.menu_scrollbar .menu_view .el-menu-item.is-active,
.menu_scrollbar .menu_view .el-sub-menu.is-active {
  height: 48px;
  line-height: 48px;
  font-size: 16px;
  border-bottom: none;
  background: var(--theme-color);
  color: #fff !important;
  clip-path: polygon(0 0, 100% 0, 95% 100%, 0% 100%);
  border-radius: 10px 10px 0 0;
}

.menu_scrollbar .menu_view .el-sub-menu .el-sub-menu__title .el-icon-menu,
.menu_scrollbar .menu_view .el-sub-menu .el-sub-menu__title .iconfont {
  vertical-align: middle;
  margin: 0px 3px;
  width: 34px;
  text-align: center;
  font-size: 18px;
  color: inherit;
}

.menu_scrollbar .menu_view .el-sub-menu .el-sub-menu__title span {
  font-size: 16px;
  vertical-align: middle;
  color: inherit;
}

.menu_scrollbar .menu_view .el-sub-menu .el-sub-menu__title .el-sub-menu__icon-arrow {
  position: static;
  margin: -3px 0px 0px 8px;
  font-size: 12px;
  vertical-align: middle;
  color: inherit;
}

.el-menu--horizontal .el-menu {
  border: none;
  padding: 10px;
}
.el-menu--horizontal .el-menu .el-menu-item {
  color: rgb(102, 102, 102);
  height: 40px;
  line-height: 40px;
  padding: 0px 20px;
  background: rgb(255, 255, 255);
  transition: all 0s;
}
.el-menu--horizontal .el-menu .el-menu-item:hover {
  color: #fff !important;
  background: var(--theme-color) !important;
  border-radius: 4px;
}
.el-menu--horizontal .el-menu .el-menu-item.is-active {
  color: #fff !important;
  background: var(--theme-color) !important;
  border-radius: 4px;
}

.rotation_view {
  width: 68%;
  margin: 10px auto;
}

.rotation_view .swiper {
  width: 100% !important;
  height: 450px;
  border: 0px solid #fff;
  border-radius: 10px;
}
.rotation_view .swiper .swiper-slide {
}
.rotation_view .swiper .swiper-slide img {
  width: 100%;
  height: 100%;
  object-fit: cover;
  border-radius: 10px;
}

.rotation_view .swiper .swiper-button-prev {
  margin-left: 5%;
  color: var(--theme);
}

.rotation_view .swiper .swiper-button-next {
  margin-right: 5%;
  color: var(--theme);
}

.bread_view {
  width: 100%;
  background: none;
  color: rgb(51, 51, 51);
  text-align: center;
  font-size: 20px;
  font-weight: 600;
  border: 0px solid #eee;
  padding: 15px 20px 10px 50px;
  background: var(--theme);
}

.bread_view .el-breadcrumb {
  font-size: 16px;
  line-height: 1;
}

.bread_view .el-breadcrumb .el-breadcrumb__separator {
  margin: 0px 9px;
  font-weight: 500;
  color: #fff;
}

.bread_view .el-breadcrumb .first_breadcrumb span a {
  color: #fff;
  display: inline-block;
}

.bread_view .el-breadcrumb .second_breadcrumb .el-breadcrumb__inner {
  color: #fff;
  display: inline-block;
}
.bread_view .el-breadcrumb .second_breadcrumb .el-breadcrumb__inner.is-link {
  color: #fff !important;
}

@media (max-width: 992px) {
  .index_top {
    padding: 12px 5% !important;
    height: auto !important;
    min-height: 72px;
    flex-wrap: wrap;
  }
  .index_top .index_top_title {
    font-size: 18px !important;
    width: 100%;
    text-align: center;
    margin-bottom: 8px;
  }
  .index_top .index_top_right {
    width: 100%;
    justify-content: center;
    margin-right: 0 !important;
  }
  .rotation_view {
    width: 92% !important;
    margin: 12px auto !important;
  }
  .rotation_view .swiper {
    height: 260px !important;
  }
}

@media (max-width: 576px) {
  .menu_scrollbar .menu_view {
    height: auto !important;
    min-height: 52px;
    flex-wrap: nowrap;
    justify-content: flex-start !important;
    overflow-x: auto;
    -webkit-overflow-scrolling: touch;
    padding: 6px 10px 10px !important;
  }
  .menu_scrollbar .menu_view::-webkit-scrollbar {
    height: 4px;
  }
  .menu_scrollbar .menu_view .el-menu-item,
  .menu_scrollbar .menu_view .el-sub-menu,
  .menu_scrollbar .menu_view .el-sub-menu .el-sub-menu__title {
    flex: 0 0 auto;
    font-size: 14px !important;
    min-width: auto !important;
    padding: 0 12px !important;
  }
}
</style>
