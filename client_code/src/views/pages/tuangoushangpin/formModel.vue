<template>
  <div class="detail-page" :style="{}">
    <div class="breadcrumb-wrapper" style="width: 100%">
      <div class="bread_view">
        <el-breadcrumb separator="Ξ" class="breadcrumb">
          <el-breadcrumb-item class="first_breadcrumb" :to="{ path: '/' }">首页</el-breadcrumb-item>
          <el-breadcrumb-item
            class="second_breadcrumb"
            v-for="(item, index) in breadList"
            :key="index"
            >{{ item.name }}</el-breadcrumb-item
          >
        </el-breadcrumb>
      </div>
      <div class="back_view">
        <el-button class="back_btn" @click="backClick" type="primary">返回</el-button>
      </div>
    </div>
    <div class="detail_view">
      <div class="swiper_view">
        <mySwiper
          :data="bannerList"
          :type="3"
          :loop="false"
          :navigation="true"
          :pagination="false"
          :paginationType="1"
          :scrollbar="false"
          :slidesPerView="1"
          :spaceBetween="20"
          :autoHeight="false"
          :centeredSlides="false"
          :freeMode="false"
          :effectType="0"
          :direction="horizontal"
          :autoplay="false"
          :slidesPerColumn="1"
        >
          <template #default="scope">
            <img
              :style="{ objectFit: 'cover', width: '100%', height: '480px' }"
              :src="scope.row ? $config.url + scope.row : ''"
            />
          </template>
        </mySwiper>
      </div>

      <div class="info_view">
        <div class="title_view">
          <div class="detail_title">
            <span>{{ detail.shangpinmingcheng }}</span>
          </div>
          <div class="collect_view" v-if="!collectType" @click="collectClick(1)">
            <i class="iconfont icon-likeline1"></i>
            <span>收藏</span>
          </div>
          <div class="collect_view" v-if="collectType" @click="collectClick(-1)">
            <i class="iconfont iconfontActive icon-likefill1"></i>
            <span class="textActive">取消收藏</span>
          </div>
        </div>
        <div class="info_item">
          <div class="info_label">价格</div>
          <div class="info_price"><span>￥</span>{{ detail.price }}</div>
        </div>
        <div class="info_item">
          <div class="info_label">团购价</div>
          <div class="info_price"><span>￥</span>{{ Number(detail.groupprice).toFixed(2) }}</div>
        </div>
        <div class="info_item">
          <div class="info_label">成团人数</div>
          <div class="info_text">{{ detail.grouppeople }}人</div>
        </div>
        <div class="info_item" v-if="detail.jf">
          <div class="info_label">积分</div>
          <div class="info_price">{{ detail.jf }}</div>
        </div>
        <div class="info_item">
          <div class="info_label">单次购买</div>
          <div class="info_text">{{ detail.onelimittimes }}</div>
        </div>
        <div class="info_item">
          <div class="info_label">库存</div>
          <div class="info_text">{{ detail.alllimittimes }}</div>
        </div>
        <div class="info_item">
          <div class="info_label">商品编号</div>
          <div class="info_text">{{ detail.shangpinbianhao }}</div>
        </div>
        <div class="info_item">
          <div class="info_label">规格</div>
          <div class="info_text">{{ detail.guige }}</div>
        </div>
        <div class="info_item">
          <div class="info_label">保质期</div>
          <div class="info_text">{{ detail.baozhiqi }}</div>
        </div>
        <div class="info_item">
          <div class="info_label">商品分类</div>
          <div class="info_text">{{ detail.shangpinfenlei }}</div>
        </div>
        <div class="info_item">
          <div class="info_label">商家</div>
          <div class="info_text">{{ detail.shangjia }}</div>
        </div>
        <div class="info_item">
          <div class="info_label">收藏数</div>
          <div class="info_text">{{ detail.storeupNumber }}</div>
        </div>
        <div class="info_item">
          <div class="info_label">评论数</div>
          <div class="info_text">{{ detail.discussNumber }}</div>
        </div>
        <div class="btn_view">
          <el-input-number
            class="inputNumber"
            v-model="buyNumber"
            :min="1"
            v-if="detail.alllimittimes"
          ></el-input-number>
          <div class="break"></div>
          <el-button class="addCart_btn" v-if="detail.alllimittimes" type="primary" @click="addCart"
            >加入购物车</el-button
          >
          <el-button class="buyNow_btn" v-if="detail.alllimittimes" type="primary" @click="buyNow"
            >立即购买</el-button
          >
          <el-button
            class="openGroup_btn"
            v-if="detail.grouppeople > 1"
            type="primary"
            @click="groupBuyTap"
            >我要开团</el-button
          >
          <el-button
            class="goGroup_btn"
            v-if="detail.grouppeople > 1"
            type="success"
            @click="showGroupListDialog"
            >去参团</el-button
          >
        </div>
        <div class="btn_view">
          <el-button
            class="edit_btn"
            type="primary"
            v-if="btnAuth('tuangoushangpin', '私信')"
            @click="chatClick"
            >联系TA</el-button
          >
          <el-button
            class="cross_btn"
            v-if="btnFrontAuth('tuangoushangpin', '配送')"
            @click="dingdanpeisongonAcross('配送', '', '', '', '')"
            type="warning"
            >配送</el-button
          >
          <el-button
            class="edit_btn"
            v-if="centerType && btnAuth('tuangoushangpin', '修改')"
            type="primary"
            @click="editClick"
            >修改</el-button
          >
          <el-button
            class="del_btn"
            v-if="centerType && btnAuth('tuangoushangpin', '删除')"
            type="danger"
            @click="delClick"
            >删除</el-button
          >
        </div>
      </div>
    </div>
    <el-tabs type="border-card" v-model="activeName" class="tabs_view">
      <el-tab-pane label="商品详情" name="first">
        <div v-html="detail.shangpinxiangqing"></div>
      </el-tab-pane>
      <el-tab-pane label="评论" name="commentActive">
        <div class="my_comment_view">
          <el-form
            ref="commentFormRef"
            :model="commentForm"
            class="my_comment_form"
            :rules="commentRules"
          >
            <el-form-item prop="content">
              <editor
                :value="commentForm.content"
                placeholder="善语结善缘,恶语伤人心"
                class="list_editor"
                @change="contentChange"
              ></editor>
            </el-form-item>
            <el-form-item label="评分" class="rate">
              <el-rate v-model="commentForm.score" prop="score" />
            </el-form-item>
          </el-form>
          <div class="comment_btn">
            <el-button class="add_btn" type="primary" @click="commentSave">立即评论</el-button>
            <el-button class="reset_btn" @click="resetForm">重置</el-button>
          </div>
        </div>
        <div class="comment_list">
          <div class="comment" v-for="(item, index) in commentList" :key="index">
            <div class="comment_top">
              <div class="comment_user">
                <div class="comment_user_img">
                  <img
                    :src="
                      item.avatarurl ? $config.url + item.avatarurl : '../../../assets/avatar.png'
                    "
                    alt=""
                  />
                </div>
                <div class="comment_user_info">
                  {{ item.nickname }}
                </div>
              </div>
              <div class="comment_time">{{ item.addtime }}</div>
            </div>
            <div class="comment_bottom">
              <div class="comment_content" v-html="item.content"></div>
              <div class="comment_score" v-if="item.score">
                <el-rate v-model="item.score" disabled />
              </div>
              <div v-if="item.userid == user.id" class="comment_action">
                <span class="del" @click="commentDel(item)" style="cursor: pointer">删除</span>
              </div>
              <div class="comment_reply" v-if="item.reply">
                回复：<span v-html="item.reply"></span>
              </div>
            </div>
          </div>
        </div>
        <el-pagination
          background
          :layout="layouts.join(',')"
          :total="commentTotal"
          :page-size="commentQuery.limit"
          prev-text="上一页"
          next-text="下一页"
          :hide-on-single-page="false"
          :style="{}"
          @size-change="commentSizeChange"
          @current-change="commentCurrentChange"
        />
      </el-tab-pane>
      <el-tab-pane label="拼团列表" name="groupList">
        <div class="group_list_view">
          <el-table :data="groupList" stripe style="width: 100%">
            <el-table-column prop="groupono" label="拼团编号" width="180"></el-table-column>
            <el-table-column prop="leadername" label="团长" width="100"></el-table-column>
            <el-table-column prop="groupprice" label="团购价" width="100">
              <template #default="scope">
                <span>￥{{ Number(scope.row.groupprice).toFixed(2) }}</span>
              </template>
            </el-table-column>
            <el-table-column prop="grouppeople" label="人数" width="100">
              <template #default="scope">
                {{ scope.row.curpeople }}/{{ scope.row.grouppeople }}
              </template>
            </el-table-column>
            <el-table-column prop="status" label="状态" width="100">
              <template #default="scope">
                <el-tag :type="scope.row.status === '拼团中' ? 'success' : 'info'">{{
                  scope.row.status
                }}</el-tag>
              </template>
            </el-table-column>
            <el-table-column prop="addtime" label="开团时间" width="180"></el-table-column>
            <el-table-column label="操作" fixed="right">
              <template #default="scope">
                <el-button
                  type="primary"
                  size="small"
                  @click="joinGroupClick(scope.row)"
                  v-if="
                    scope.row.status === '拼团中' && scope.row.curpeople < scope.row.grouppeople
                  "
                >
                  立即参团
                </el-button>
                <el-button type="info" size="small" disabled v-else>
                  {{ scope.row.status === '拼团中' ? '已满员' : '已结束' }}
                </el-button>
              </template>
            </el-table-column>
          </el-table>
        </div>
      </el-tab-pane>
    </el-tabs>
    <el-dialog v-model="chatVisible" @close="clearChat" :title="fname">
      <div class="chat-content" id="chat-content">
        <div v-bind:key="item.id" v-for="(item, index) in chatList">
          <div
            v-if="
              (index > 1 &&
                moment(chatList[index - 1].addtime).date() != moment(item.addtime).date()) ||
              index == 0
            "
            style="font-size: 12px; text-align: center; margin: 4px 0"
          >
            {{ moment(item.addtime).format('MM-DD HH:mm') }}
          </div>
          <div v-if="item.uid == user.id" class="right-content">
            <el-alert
              v-if="item.format == 1"
              class="text-content"
              :title="item.content"
              :closable="false"
              type="warning"
            ></el-alert>
            <video
              v-else-if="item.content.endsWith('.mp4')"
              controls
              style="width: 200px; height: 160px"
            >
              <source :src="$config.url + item.content" />
            </video>
            <el-image
              v-else
              fit="cover"
              :src="item.content ? $config.url + item.content : ''"
              style="width: 100px; height: 100px"
              :preview-src-list="[item.content ? $config.url + item.content : '']"
            ></el-image>
            <img
              :src="userAvatar"
              alt=""
              style="
                width: 30px;
                border-radius: 50%;
                height: 30px;
                margin: 0 0 0 10px;
                flex-shrink: 0;
                object-fit: cover;
              "
            />
          </div>
          <div v-else class="left-content">
            <img
              :src="fpic ? $config.url + fpic : require('@/assets/avatar.png')"
              alt=""
              style="
                width: 30px;
                border-radius: 50%;
                height: 30px;
                margin: 0 10px 0 0;
                flex-shrink: 0;
                object-fit: cover;
              "
            />
            <el-alert
              v-if="item.format == 1"
              class="text-content"
              :title="item.content"
              :closable="false"
              type="success"
            ></el-alert>
            <video
              v-else-if="item.content.endsWith('.mp4')"
              controls
              style="width: 200px; height: 160px"
            >
              <source :src="$config.url + item.content" />
            </video>
            <el-image
              v-else
              fit="cover"
              :src="item.content ? $config.url + item.content : ''"
              style="width: 100px; height: 100px"
              :preview-src-list="[item.content ? $config.url + item.content : '']"
            ></el-image>
          </div>
          <div class="clear-float"></div>
        </div>
      </div>
      <div slot="footer" class="dialog-footer">
        <el-input
          size="small"
          @keydown.enter.native="addChat"
          v-model="chatForm.content"
          placeholder="请输入内容"
          style="width: calc(100% - 240px); float: left; border: 1px solid #ccc; margin-right: 10px"
        >
        </el-input>
        <el-button
          size="small"
          :disabled="chatForm.content ? false : true"
          type="primary"
          @click="addChat"
          >发送</el-button
        >
        <el-upload
          style="display: inline-block; margin: 0 6px"
          class="upload-demo"
          :action="uploadUrl"
          :on-success="uploadSuccess"
          :show-file-list="false"
          accept="image/*,.mp4"
        >
          <el-button size="small" type="success">上传文件</el-button>
        </el-upload>
        <el-button size="small" type="primary" @click="sendGoods">详情图发送</el-button>
      </div>
    </el-dialog>
  </div>
</template>
<script setup>
import axios from 'axios'
import moment from 'moment'
import {
  ref,
  getCurrentInstance,
  watch,
  onUnmounted,
  onMounted,
  nextTick,
  computed,
  inject,
} from 'vue'
import { ElMessageBox } from 'element-plus'
import { useRoute, useRouter } from 'vue-router'
import { useStore } from 'vuex'
const store = useStore()
const user = computed(() => store.getters['user/session'])
const userAvatar = computed(() => store.getters['user/avatar'])
const context = getCurrentInstance()?.appContext.config.globalProperties
const route = useRoute()
const router = useRouter()
//基础信息
const tableName = 'tuangoushangpin'
const formName = '团购商品'
//基础信息
const breadList = ref([
  {
    name: formName,
  },
])
//权限验证
const btnAuth = (e, a) => {
  if (centerType.value) {
    return context?.$toolUtil.isBackAuth(e, a)
  } else {
    return context?.$toolUtil.isAuth(e, a)
  }
}
//查看权限验证
const btnFrontAuth = (e, a) => {
  if (centerType.value) {
    return context?.$toolUtil.isBackAuth(e, a)
  } else {
    return context?.$toolUtil.isFrontAuth(e, a)
  }
}
// 返回
const backClick = () => {
  history.back()
}
// 轮播图
const bannerList = ref([])
// 详情
const title = ref('')
const detail = ref({})
const activeName = ref('first')
const getDetail = () => {
  context
    ?.$http({
      url: `${tableName}/detail/${route.query.id}`,
      method: 'get',
    })
    .then(res => {
      bannerList.value = res.data.data.shangpintupian ? res.data.data.shangpintupian.split(',') : []
      title.value = res.data.data.shangpinmingcheng
      if (res.data.data.touxiang) {
        fpic.value = res.data.data.touxiang.split(',')[0]
      } else if (res.data.data.headportrait) {
        fpic.value = res.data.data.headportrait.split(',')[0]
      }
      detail.value = res.data.data
      getInCartList()
      // 加载拼团列表 (确保 detail.value.id 有值)
      loadGroupList()
    })
}
// 下载文件
const downClick = file => {
  if (!file) {
    context?.$toolUtil.message('文件不存在', 'error')
  }
  let arr = file.replace(new RegExp('file/', 'g'), '')
  axios
    .get(
      (location.href.split(context?.$config.name).length > 1
        ? location.href.split(context?.$config.name)[0]
        : '') +
        context?.$config.name +
        '/file/download?fileName=' +
        arr,
      {
        headers: {
          token: context?.$toolUtil.storageGet('frontToken'),
        },
        responseType: 'blob',
      }
    )
    .then(({ data }) => {
      const binaryData = []
      binaryData.push(data)
      const objectUrl = window.URL.createObjectURL(
        new Blob(binaryData, {
          type: 'application/pdf;chartset=UTF-8',
        })
      )
      const a = document.createElement('a')
      a.href = objectUrl
      a.download = arr
      // a.click()
      // 下面这个写法兼容火狐
      a.dispatchEvent(
        new MouseEvent('click', {
          bubbles: true,
          cancelable: true,
          view: window,
        })
      )
      window.URL.revokeObjectURL(data)
    })
}
// 判断是否从个人中心跳转
const centerType = ref(false)
const init = () => {
  if (route.query.centerType) {
    centerType.value = true
  }
  getDetail()
  // 收藏
  getCollect()
  // 评论
  getCommentList()
}
// 收藏
const collectType = ref(false)
const collectInfo = ref({})
const getCollect = () => {
  if (context?.$toolUtil.storageGet('frontToken')) {
    context
      ?.$http({
        url: 'storeup/list',
        method: 'get',
        params: {
          page: 1,
          limit: 1,
          type: 1,
          refid: route.query.id,
          tablename: tableName,
          userid: context?.$toolUtil.storageGet('userid'),
        },
      })
      .then(res => {
        if (res.data.data.list.length) {
          collectType.value = true
          collectInfo.value = res.data.data.list[0]
        } else {
          collectType.value = false
          collectInfo.value = {}
        }
      })
  }
}
// 收藏按钮
const collectClick = type => {
  if (type == 1 && !collectType.value) {
    let params = {
      name: title.value,
      picture: bannerList.value[0],
      refid: detail.value.id,
      type: type,
      tablename: tableName,
      userid: context?.$toolUtil.storageGet('userid'),
    }
    context
      ?.$http({
        url: 'storeup/add',
        method: 'post',
        data: params,
      })
      .then(res => {
        detail.value.storeupNumber += 1
        context?.$http({
          url: `${tableName}/update`,
          method: 'post',
          data: detail.value,
        })
        collectType.value = true
        getCollect()
        context?.$toolUtil.message('收藏成功', 'success')
      })
  } else if (type == -1 && collectType.value) {
    let ids = []
    ids.push(collectInfo.value.id)
    context
      ?.$http({
        url: 'storeup/delete',
        method: 'post',
        data: ids,
      })
      .then(res => {
        detail.value.storeupNumber -= 1
        context?.$http({
          url: `${tableName}/update`,
          method: 'post',
          data: detail.value,
        })
        collectInfo.value = {}
        collectType.value = false
        context?.$toolUtil.message('取消成功', 'success')
      })
  }
}
const contentChange = e => {
  commentForm.value.content = e
}
//评论
const commentForm = ref({
  content: '',
  refid: route.query.id,
  userid: context?.$toolUtil.storageGet('userid'),
  nickname: context?.$toolUtil.storageGet('frontName'),
  avatarurl: context?.$toolUtil.storageGet('headportrait')
    ? context?.$toolUtil.storageGet('headportrait')
    : '',
  score: 0,
})
const commentRules = ref({
  content: [
    {
      required: true,
      message: '请输入',
      trigger: 'blur',
    },
  ],
})
const commentQuery = ref({
  page: 1,
  limit: 10,
  refid: route.query.id,
})
const layouts = ref(['total', 'prev', 'pager', 'next', 'sizes', 'jumper'])
const commentList = ref([])
const commentTotal = ref(0)
const commentFormRef = ref(null)
const commentSizeChange = size => {
  commentQuery.value.limit = size
  getCommentList()
}
const commentCurrentChange = page => {
  commentQuery.value.page = page
  getCommentList()
}
const getCommentList = () => {
  context
    ?.$http({
      url: `discuss${tableName}/list?sort=id&order=desc`,
      params: commentQuery.value,
      method: 'get',
    })
    .then(res => {
      commentList.value = res.data.data.list
      commentTotal.value = res.data.data.total
    })
}
//提交评论
const commentSave = async () => {
  if (!commentForm.value.content || commentForm.value.content == '<p><br></p>') {
    return context.$message.error('请输入评论内容')
  }
  if (!commentForm.value.score) {
    return context.$message.error('评分不能为空')
  }
  let sensitiveWords = '傻逼,脑残,二货,去死'
  let sensitiveWordsArr = []
  if (sensitiveWords) {
    sensitiveWordsArr = sensitiveWords.split(',')
  }
  for (var i = 0; i < sensitiveWordsArr.length; i++) {
    //全局替换
    var reg = new RegExp(sensitiveWordsArr[i], 'g')
    //判断内容中是否包括敏感词
    if (commentForm.value.content.indexOf(sensitiveWordsArr[i]) > -1) {
      // 将敏感词替换为 **
      commentForm.value.content = commentForm.value.content.replace(reg, '**')
    }
  }
  commentFormRef.value.validate(valid => {
    if (valid) {
      context
        ?.$http({
          url: 'orders/list',
          method: 'get',
          params: {
            page: 1,
            limit: 1,
            status: '已完成',
            goodid: detail.value.id,
            userid: context?.$toolUtil.storageGet('userid'),
          },
        })
        .then(res => {
          if (res.data.data.list.length == 0) {
            context?.$toolUtil.message('请完成订单后再评论！', 'error')
            return false
          }
          context
            ?.$http({
              url: `discuss${tableName}/add`,
              method: 'post',
              data: commentForm.value,
            })
            .then(res => {
              context.$http.get(`${tableName}/info/${detail.value.id}`).then(res => {
                let detail = res.data.data
                detail.discussNumber++
                context.$http.post(`${tableName}/update`, detail).then(() => {
                  getDetail()
                })
              })

              context?.$toolUtil.message('评论成功', 'success')
              resetForm()
              getCommentList()
            })
        })
    }
  })
}
const commentDel = item => {
  context
    .$confirm('确定要删除该评论吗？', '提示')
    .then(() => {
      context.$http.post(`discuss${tableName}/delete`, [item.id]).then(res => {
        if (res.data.code == 0) {
          context.$message.success('删除成功')
          context.$http.get(`${tableName}/info/${detail.value.id}`).then(res => {
            let detail = res.data.data
            detail.discussNumber--
            context.$http.post(`${tableName}/update`, detail).then(() => {
              getDetail()
            })
          })
          getCommentList()
        }
      })
    })
    .catch(() => {})
}
const resetForm = () => {
  commentFormRef.value.resetFields()
}
//查找是否已添加购物车
const isInCart = ref(false)
const getInCartList = () => {
  if (!context?.$toolUtil.storageGet('frontToken')) return
  context
    ?.$http({
      url: 'cart/list',
      method: 'get',
      params: {
        userid: context?.$toolUtil.storageGet('userid'),
        tablename: tableName,
        goodid: detail.value.id,
      },
    })
    .then(res => {
      if (res.data.data.list.length) {
        isInCart.value = true
      } else {
        isInCart.value = false
      }
    })
}
const cartForm = ref({
  userid: context?.$toolUtil.storageGet('userid'),
})
const addCart = () => {
  //单次购买限制
  if (detail.value.onelimittimes > 0 && detail.value.onelimittimes < buyNumber.value) {
    context?.$toolUtil.message(`每人只能购买${detail.value.onelimittimes}件商品`, 'error')
    return false
  }
  //库存不足
  if (detail.value.alllimittimes <= 0) {
    context?.$toolUtil.message('商品售罄', 'error')
    return false
  }
  //库存限制
  if (detail.value.alllimittimes > 0 && detail.value.alllimittimes < buyNumber.value) {
    context?.$toolUtil.message('库存不足', 'error')
    return false
  }
  if (isInCart.value) {
    context?.$toolUtil.message('该商品已在购物车，请前往购买', 'error')
    return false
  }
  cartForm.value.buynumber = buyNumber.value
  cartForm.value.goodid = detail.value.id
  cartForm.value.goodname = title.value
  cartForm.value.tablename = tableName
  cartForm.value.picture = bannerList.value[0]
  cartForm.value.shangjia = detail.value.shangjia
  cartForm.value.price = detail.value.price
  context
    ?.$http({
      url: 'cart/save',
      method: 'post',
      data: cartForm.value,
    })
    .then(res => {
      context?.$toolUtil.message('添加成功', 'success')
      getInCartList()
    })
}
const buyNumber = ref(1)
//立即购买
const buyNow = () => {
  //单次购买限制
  if (detail.value.onelimittimes > 0 && detail.value.onelimittimes < buyNumber.value) {
    context?.$toolUtil.message(`每人只能购买${detail.value.onelimittimes}件商品`, 'error')
    return false
  }
  //库存不足
  if (detail.value.alllimittimes <= 0) {
    context?.$toolUtil.message('商品售罄', 'error')
    return false
  }
  //库存限制
  if (detail.value.alllimittimes > 0 && detail.value.alllimittimes < buyNumber.value) {
    context?.$toolUtil.message('库存不足', 'error')
    return false
  }
  let data = {
    tablename: tableName,
    goodid: detail.value.id,
    goodname: title.value,
    buynumber: buyNumber.value,
    userid: context?.$toolUtil.storageGet('userid'),
    discountprice: detail.value.vipprice,
    shangjia: detail.value.shangjia,
    picture: bannerList.value[0],
    price: detail.value.price,
  }
  context?.$toolUtil.storageSet('orders_good', JSON.stringify([data]))
  let query = {
    type: 1,
  }
  router.push({ path: '/index/order_confirm', query: query })
}
//拼团
const groupBuyTap = () => {
  //单次购买限制
  if (detail.value.onelimittimes > 0 && detail.value.onelimittimes < buyNumber.value) {
    context?.$toolUtil.message(`每人只能购买${detail.value.onelimittimes}件商品`, 'error')
    return false
  }
  //库存不足
  if (detail.value.alllimittimes <= 0) {
    context?.$toolUtil.message('商品售罄', 'error')
    return false
  }
  //库存限制
  if (detail.value.alllimittimes > 0 && detail.value.alllimittimes < buyNumber.value) {
    context?.$toolUtil.message('库存不足', 'error')
    return false
  }
  let data = {
    tablename: tableName,
    goodid: detail.value.id,
    goodname: title.value,
    buynumber: buyNumber.value, // 使用用户输入的数量
    userid: context?.$toolUtil.storageGet('userid'),
    price: detail.value.groupprice,
    shangjia: detail.value.shangjia,
    picture: bannerList.value[0],
    isGroupLeader: true, // 标记为团长
  }
  context?.$toolUtil.storageSet('orders_good', JSON.stringify([data]))
  let query = {
    type: 3,
  }
  router.push({ path: '/index/order_confirm', query: query })
}

// 加载拼团列表
const loadGroupList = () => {
  // 查询该商品所有“拼团中”的拼团活动
  context
    ?.$http({
      url: 'tuangouactivity/list',
      params: {
        goodsid: detail.value.id,
        status: '拼团中',
        page: 1,
        limit: 999,
      },
      method: 'get',
    })
    .then(res => {
      groupList.value = res.data.data.list
    })
}

// 显示拼团列表
const groupList = ref([])
const showGroupListDialog = () => {
  // 切换到拼团列表 tab
  activeName.value = 'groupList'
}

// 参加某个团
const joinGroupClick = group => {
  //单次购买限制
  if (detail.value.onelimittimes > 0 && detail.value.onelimittimes < buyNumber.value) {
    context?.$toolUtil.message(`每人只能购买${detail.value.onelimittimes}件商品`, 'error')
    return false
  }
  //库存不足
  if (detail.value.alllimittimes <= 0) {
    context?.$toolUtil.message('商品售罄', 'error')
    return false
  }
  //库存限制
  if (detail.value.alllimittimes > 0 && detail.value.alllimittimes < buyNumber.value) {
    context?.$toolUtil.message('库存不足', 'error')
    return false
  }
  // 检查用户是否已参加过该团
  let params = {
    tuangouid: group.id,
    userid: context?.$toolUtil.storageGet('userid'),
    page: 1,
    limit: 1,
  }
  context
    ?.$http({
      url: 'orders/list',
      params: params,
      method: 'get',
    })
    .then(res => {
      if (res.data.data.list.length) {
        context?.$toolUtil.message('您已参加过该团', 'error')
        return false
      }
      let data = {
        tablename: tableName,
        goodid: detail.value.id,
        goodname: title.value,
        buynumber: buyNumber.value, // 使用用户输入的数量
        userid: context?.$toolUtil.storageGet('userid'),
        price: detail.value.groupprice,
        shangjia: detail.value.shangjia,
        picture: bannerList.value[0],
        selectedGroupId: group.id, // 选择的拼团 ID
      }
      context?.$toolUtil.storageSet('orders_good', JSON.stringify([data]))
      let query = {
        type: 3,
      }
      router.push({ path: '/index/order_confirm', query: query })
    })
}
//修改
const editClick = () => {
  router.push(`/index/${tableName}Add?id=${detail.value.id}&&type=edit`)
}
//删除
const delClick = () => {
  ElMessageBox.confirm(`是否删除此${formName}？`, '提示', {
    confirmButtonText: '是',
    cancelButtonText: '否',
    type: 'warning',
  })
    .then(() => {
      context
        ?.$http({
          url: `${tableName}/delete`,
          method: 'post',
          data: [detail.value.id],
        })
        .then(res => {
          context?.$toolUtil.message('删除成功', 'success', () => {
            history.back()
          })
        })
    })
    .catch(_ => {})
}
const dingdanpeisongonAcross = async (
  btnType,
  crossOptAudit,
  crossOptPay,
  statusColumnName,
  tips,
  statusColumnValue,
  type = 1
) => {
  if (!context?.$toolUtil.storageGet('frontToken')) {
    context?.$toolUtil.message('请登录后再操作！', 'error')
    return false
  }
  if (!btnAuth('tuangoushangpin', btnType)) {
    context?.$toolUtil.message('暂无权限操作！', 'error')
    return false
  }
  context?.$toolUtil.storageSet('crossObj', JSON.stringify(detail.value))
  context?.$toolUtil.storageSet('crossTable', tableName)
  context?.$toolUtil.storageSet('crossStatusColumnName', statusColumnName)
  context?.$toolUtil.storageSet('crossTips', tips)
  context?.$toolUtil.storageSet('crossStatusColumnValue', statusColumnValue)
  if (statusColumnName != '' && !statusColumnName.startsWith('[')) {
    var obj = detail.value
    for (var o in obj) {
      if (o == statusColumnName && obj[o] == statusColumnValue) {
        context?.$toolUtil.message(tips, 'warning')
        return
      }
    }
  }
  nextTick(() => {
    router.push(`/index/dingdanpeisongAdd?type=cross&&id=${detail.value.id}`)
  })
}
const fid = ref('')
const chatVisible = ref(false)
const chatList = ref([])
const chatForm = ref({
  content: '',
})
const chatTimer = ref(null)
const uploadUrl = ref(context.$config.url + 'file/upload')
const fpic = ref('')
const fname = ref('')

const chatClick = () => {
  if (detail.value.shangjia == user.value.shangjia) {
    context.$toolUtil.message('不能给自己发信息', 'error')
    return false
  }
  context.$http
    .get('shangjia/query', {
      params: {
        shangjia: detail.value.shangjia,
      },
    })
    .then(res => {
      if (res.data && res.data.code == 0) {
        fid.value = res.data.data.id
        fname.value = res.data.data.shangjia
        if (res.data.data.touxiang) {
          fpic.value = res.data.data.touxiang.split(',')[0]
        } else if (res.data.data.headportrait) {
          fpic.value = res.data.data.headportrait.split(',')[0]
        }
        getChatList()
        chatVisible.value = true
      }
    })
}
const sendGoods = () => {
  clearTimeout(chatTimer.value)
  context.$http
    .get('chatfriend/page', {
      params: {
        uid: user.value.id,
        fid: fid.value,
      },
    })
    .then(obj => {
      if (obj.data && obj.data.code == 0) {
        if (!obj.data.data.list.length) {
          context.$http
            .post('chatfriend/add', {
              uid: user.value.id,
              fid: fid.value,
              name: fname.value,
              picture: fpic.value,
              type: 2,
              tablename: 'shangjia',
            })
            .then(res => {
              context.$http
                .post('chatfriend/add', {
                  uid: fid.value,
                  fid: user.value.id,
                  type: 2,
                  tablename: localStorage.getItem('frontSessionTable'),
                  name: localStorage.getItem('frontName'),
                  picture: localStorage.getItem('headportrait'),
                })
                .then(res1 => {})
            })
        }
        context.$http
          .post('chatmessage/add', {
            uid: user.value.id,
            fid: fid.value,
            content: bannerList.value[0],
            format: 2,
          })
          .then(res2 => {
            chatForm.value = {
              content: '',
            }
            scrollFlag.value = true
            getChatList()
          })
      }
    })
}
const scrollFlag = ref(true)
const getChatList = () => {
  context.$http
    .get('chatmessage/mlist', {
      params: {
        page: 1,
        limit: 1000,
        uid: user.value.id,
        fid: fid.value,
      },
    })
    .then(res => {
      if (res.data && res.data.code == 0) {
        chatList.value = res.data.data.list
        let div = document.getElementsByClassName('chat-content')[0]
        setTimeout(() => {
          if (div) {
            if (div.scrollTop + div.clientHeight == div.scrollHeight || scrollFlag.value) {
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
}
const uploadSuccess = res => {
  if (res.code == 0) {
    clearTimeout(chatTimer.value)
    context.$http
      .get('chatfriend/page', {
        params: {
          uid: user.value.id,
          fid: fid.value,
        },
      })
      .then(obj => {
        if (obj.data && obj.data.code == 0) {
          if (!obj.data.data.list.length) {
            context.$http
              .post('chatfriend/add', {
                uid: user.value.id,
                fid: fid.value,
                name: fname.value,
                picture: fpic.value,
                type: 2,
                tablename: 'shangjia',
              })
              .then(res => {
                context.$http
                  .post('chatfriend/add', {
                    uid: fid.value,
                    fid: user.value.id,
                    type: 2,
                    tablename: localStorage.getItem('frontSessionTable'),
                    name: localStorage.getItem('frontName'),
                    picture: localStorage.getItem('headportrait'),
                  })
                  .then(res1 => {})
              })
          }
          context.$http
            .post('chatmessage/add', {
              uid: user.value.id,
              fid: fid.value,
              content: 'file/' + res.file,
              format: 2,
            })
            .then(res2 => {
              chatForm.value = {
                content: '',
              }
              scrollFlag.value = true
              getChatList()
            })
        }
      })
  }
}
const addChat = () => {
  if (!chatForm.value.content.trim()) return context.$message.error('消息内容不能为空')
  clearTimeout(chatTimer.value)
  context.$http
    .get('chatfriend/page', {
      params: {
        uid: user.value.id,
        fid: fid.value,
      },
    })
    .then(obj => {
      if (obj.data && obj.data.code == 0) {
        if (!obj.data.data.list.length) {
          context.$http
            .post('chatfriend/add', {
              uid: user.value.id,
              fid: fid.value,
              name: fname.value,
              picture: fpic.value,
              type: 2,
              tablename: 'shangjia',
            })
            .then(res => {
              context.$http
                .post('chatfriend/add', {
                  uid: fid.value,
                  fid: user.value.id,
                  type: 2,
                  tablename: localStorage.getItem('frontSessionTable'),
                  name: localStorage.getItem('frontName'),
                  picture: localStorage.getItem('headportrait'),
                })
                .then(res1 => {})
            })
        }
        let sensitiveWords = '傻逼,脑残,二货,去死'
        let sensitiveWordsArr = []
        if (sensitiveWords) {
          sensitiveWordsArr = sensitiveWords.split(',')
        }
        for (var i = 0; i < sensitiveWordsArr.length; i++) {
          //全局替换
          var reg = new RegExp(sensitiveWordsArr[i], 'g')
          //判断内容中是否包括敏感词
          if (chatForm.value.content.indexOf(sensitiveWordsArr[i]) > -1) {
            // 将敏感词替换为 **
            chatForm.value.content = chatForm.value.content.replace(reg, '**')
          }
        }
        context.$http
          .post('chatmessage/add', {
            uid: user.value.id,
            fid: fid.value,
            content: chatForm.value.content,
            format: 1,
          })
          .then(res2 => {
            chatForm.value = {
              content: '',
            }
            scrollFlag.value = true
            getChatList()
          })
      }
    })
}
onMounted(() => {
  init()
})
</script>
<style lang="scss" scoped>
// 返回盒子
.back_view {
  border-radius: 4px;
  padding: 10px 0px;
  margin: 10px auto;
  background: none;
  width: 100%;
  text-align: right;
  // 返回按钮
  .back_btn {
    border: 1px solid var(--theme-color);
    cursor: pointer;
    border-radius: 0px;
    padding: 0 24px;
    color: #fff;
    background: var(--theme-color);
    width: auto;
    font-size: 14px;
    height: 34px;
  }
  // 返回按钮-悬浮
  .back_btn:hover {
  }
}
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

.detail_view {
  // 轮播图
  .swiper_view {
  }
  // 文字区
  .info_view {
    .title_view {
      .detail_title {
      }
      // 收藏盒子
      .collect_view {
        border: 0px solid #eee;
        cursor: pointer;
        border-radius: 10px;
        padding: 10px 0px;
        color: #999;
        background: #fff;
        display: flex;
        width: auto;
        line-height: 1;
        justify-content: center;
        align-items: center;
        .iconfont {
          margin: 0 5px 0 0;
          color: #888;
        }
        .iconfontActive {
          margin: 0 5px 0 0;
          color: #feb043;
        }
        span {
          color: #666;
        }
        .textActive {
          color: #feb043;
        }
      }
      .collect_view:hover {
      }
      .collect_view:active {
        transform: scale(0.8);
      }
    }

    .info_item {
      .info_label {
      }
      .info_text {
      }
    }
    .btn_view {
      // 数量输入框
      .inputNumber {
        :deep(.el-input-number__decrease) {
          i {
          }
        }
        :deep(.el-input-number__increase) {
          i {
          }
        }
        :deep(.el-input) {
          .el-input__wrapper {
          }
        }
      }
      // 加入购物车-按钮
      .addCart_btn {
      }
      // 悬浮
      .addCart_btn:hover {
      }
      // 立即购买-按钮
      .buyNow_btn {
      }
      // 悬浮
      .buyNow_btn:hover {
      }
      // 我要开团-按钮
      .openGroup_btn {
      }
      // 悬浮
      .openGroup_btn:hover {
      }
      // 去参团-按钮
      .goGroup_btn {
      }
      // 悬浮
      .goGroup_btn:hover {
      }
      // 跨表-按钮
      .cross_btn {
      }
      // 悬浮
      .cross_btn:hover {
      }
      // 修改-按钮
      .edit_btn {
      }
      // 悬浮
      .edit_btn:hover {
      }
      // 删除-按钮
      .del_btn {
      }
      // 悬浮
      .del_btn:hover {
      }
    }
  }
}

//底部盒子
.tabs_view {
  :deep(.el-tabs__header) {
    background: transparent;
    border: none;
  }
  // 头部
  :deep(.el-tabs__nav-scroll) {
    .el-tabs__nav {
      .el-tabs__item {
      }
      .el-tabs__item:hover {
      }
      .is-active {
      }
    }
  }
  // 内容区
  :deep(.el-tabs__content) {
  }
  //评论
  //我的评论
  .my_comment_view {
    .my_comment_form {
      // 输入框
      :deep(.el-textarea__inner) {
      }
    }
    // 按钮盒子
    .comment_btn {
      // 评论按钮
      .add_btn {
      }
      // 悬浮
      .add_btn:hover {
      }
      // 重置按钮
      .reset_btn {
      }
      // 悬浮
      .reset_btn:hover {
      }
    }
  }

  //评论列表
  .comment_list {
    .comment {
      .comment_top {
        .comment_user {
          .comment_user_img {
            img {
              border-radius: 50%;
              width: 40px;
              height: 40px;
            }
          }
          .comment_user_info {
          }
        }
        .comment_time {
        }
      }

      .comment_bottom {
        .comment_content {
        }
        .comment_reply {
        }
      }
    }
  }
  // 分页器
  .el-pagination {
    // 总页码
    :deep(.el-pagination__total) {
    }
    // 上一页
    :deep(.btn-prev) {
    }
    // 下一页
    :deep(.btn-next) {
    }
    // 上一页禁用
    :deep(.btn-prev:disabled) {
    }
    // 下一页禁用
    :deep(.btn-next:disabled) {
    }
    // 页码
    :deep(.el-pager) {
      // 数字
      .number {
      }
      // 数字悬浮
      .number:hover {
      }
      // 选中
      .number.is-active {
      }
    }
    // sizes
    :deep(.el-pagination__sizes) {
      .el-select {
        //去掉默认样式
        .select-trigger {
          height: 100%;
          .el-input {
            height: 100%;
          }
        }
      }
    }
    // 跳页
    :deep(.el-pagination__jump) {
      // 输入框
      .el-input {
      }
    }
  }
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
</style>
