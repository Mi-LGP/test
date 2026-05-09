<template>
  <div class="list-page" :style="{}">
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
      <div class="back_view" v-if="centerType">
        <el-button class="back_btn" @click="backClick" type="primary">返回</el-button>
      </div>
    </div>
    <el-form :inline="true" :model="searchQuery" class="list_search">
      <div class="search_view">
        <div class="search_label">商品编号：</div>
        <div class="search_box">
          <el-input
              class="search_inp"
              v-model="searchQuery.shangpinbianhao"
              placeholder="商品编号"
              clearable
          >
          </el-input>
        </div>
      </div>
      <div class="search_view">
        <div class="search_label">商品名称：</div>
        <div class="search_box">
          <el-input
              class="search_inp"
              v-model="searchQuery.shangpinmingcheng"
              placeholder="商品名称"
              clearable
          >
          </el-input>
        </div>
      </div>
      <div class="search_btn_view">
        <el-button class="search_btn" type="primary" @click="searchClick">搜索</el-button>
        <el-button
            class="add_btn"
            type="success"
            v-if="btnAuth('shangpinxinxi', '新增')"
            @click="addClick"
        >新增</el-button
        >
      </div>
    </el-form>
    <div class="category_view">
      <div
          class="category"
          :class="categoryIndex == -1 ? 'categoryActive' : ''"
          @click="categoryClick(-1)"
      >
        全部
      </div>
      <div
          class="category"
          :class="categoryIndex == index ? 'categoryActive' : ''"
          v-for="(item, index) in categoryList"
          :key="index"
          @click="categoryClick(index)"
      >
        {{ item }}
      </div>
    </div>
    <div class="sort-wrapper">
      <el-button
          class="item price"
          @click="sortClick('price')"
          :class="{ active: sortType == 'price' }"
      >
        <el-icon class="icon" v-if="sortType != 'price'"><DCaret /></el-icon>
        <el-icon class="icon desc" v-else-if="sortOrder == 'desc'"><SortDown /></el-icon>
        <el-icon class="icon asc" v-else><SortUp /></el-icon>
        价格
      </el-button>
      <el-button
          class="item storeup"
          @click="sortClick('storeupNumber')"
          :class="{ active: sortType == 'storeupNumber' }"
      >
        <el-icon class="icon" v-if="sortType != 'storeupNumber'"><DCaret /></el-icon>
        <el-icon class="icon desc" v-else-if="sortOrder == 'desc'"><SortDown /></el-icon>
        <el-icon class="icon asc" v-else><SortUp /></el-icon>
        收藏数
      </el-button>
    </div>
    <div class="page_list">
      <div class="data_box">
        <div class="data_view">
          <div
              class="data_item"
              v-for="(item, index) in list"
              :key="index"
              @click.stop="detailClick(item.id)"
          >
            <div class="img_wrapper">
              <div
                  class="data_img_box"
                  v-if="item.shangpintupian && item.shangpintupian.substr(0, 4) == 'http'"
                  @click.stop="preViewClick(item.shangpintupian)"
              >
                <el-image class="data_img" :src="item.shangpintupian" fit="cover"></el-image>
              </div>
              <div
                  class="data_img_box"
                  v-else
                  @click.stop="preViewClick($config.url + item.shangpintupian.split(',')[0])"
              >
                <el-image
                    class="data_img"
                    :src="item.shangpintupian ? $config.url + item.shangpintupian.split(',')[0] : ''"
                    fit="cover"
                ></el-image>
              </div>
              <div class="stock_badge" v-if="item.alllimittimes !== undefined">
                <span class="stock_icon">📦</span>
                <span class="stock_text">{{ item.alllimittimes > 0 ? item.alllimittimes : '售罄' }}</span>
              </div>
            </div>
            <div class="data_content">
              <div class="data_title1">
                <span>{{ item.shangpinmingcheng }}</span>
              </div>
              <div class="price_action_row">
                <div class="data_price">
                  <span class="price_symbol">¥</span>
                  <span class="price_value">{{ item.price }}</span>
                </div>
                <el-button
                    class="quick_add_btn"
                    type="primary"
                    size="small"
                    circle
                    @click.stop="quickAddToCart(item)"
                    :disabled="!item.alllimittimes || item.alllimittimes <= 0"
                >
                  <el-icon><ShoppingCart /></el-icon>
                  <span>加入购物车</span>
                </el-button>
              </div>
            </div>
          </div>
        </div>
        <el-pagination
            background
            :layout="layouts.join(',')"
            :total="total"
            :page-size="listQuery.limit"
            v-model:current-page="listQuery.page"
            prev-text="上一页"
            next-text="下一页"
            :hide-on-single-page="false"
            :style="{}"
            @size-change="sizeChange"
            @current-change="currentChange"
        />
      </div>
    </div>
    <el-dialog v-model="preViewVisible" :title="'查看大图'" width="40%" destroy-on-close>
      <div style="text-align: center">
        <img :src="preViewUrl" style="max-width: 100%" alt="" />
      </div>
    </el-dialog>
  </div>
</template>

<script setup>
import { ref, getCurrentInstance, nextTick, computed, inject } from 'vue'
import { useRoute, useRouter } from 'vue-router'
import { useStore } from 'vuex'
import { ShoppingCart } from '@element-plus/icons-vue'
const store = useStore()
const user = computed(() => store.getters['user/session'])
const context = getCurrentInstance()?.appContext.config.globalProperties
const router = useRouter()
const route = useRoute()
//基础信息
const tableName = 'shangpinxinxi'
const formName = '商品信息'
//基础信息
const breadList = ref([
  {
    name: formName,
  },
])
const list = ref([])
const listQuery = ref({
  page: 1,
  limit: Number(10),
})
const total = ref(0)
const listLoading = ref(false)
//权限验证
const btnAuth = (e, a) => {
  if (centerType.value) {
    return context?.$toolUtil.isBackAuth(e, a)
  } else {
    return context?.$toolUtil.isAuth(e, a)
  }
}
const addClick = () => {
  router.push('/index/shangpinxinxiAdd')
}
//判断是否从个人中心跳转
const centerType = ref(false)
//返回
const backClick = () => {
  router.push(`/index/${context?.$toolUtil.storageGet('frontSessionTable')}Center`)
}
//搜索
const searchQuery = ref({})
//下拉列表
const searchClick = () => {
  listQuery.value.page = 1
  getList()
}
//分页
const layouts = ref(['total', 'prev', 'pager', 'next', 'sizes', 'jumper'])
const sizeChange = size => {
  listQuery.value.limit = size
  getList()
}
const currentChange = page => {
  listQuery.value.page = page
  getList()
}
//分页
const sortType = ref('')
const sortOrder = ref('')
const sortClick = type => {
  if (sortType.value == type && sortOrder.value == 'asc') {
    sortType.value = ''
    sortOrder.value = ''
  } else if (sortType.value == type && sortOrder.value == 'desc') {
    sortOrder.value = 'asc'
  } else {
    sortType.value = type
    sortOrder.value = 'desc'
  }
  getList()
}
//列表
const getList = () => {
  listLoading.value = true
  let params = JSON.parse(JSON.stringify(listQuery.value))
  if (categoryIndex.value != -1) {
    params.shangpinfenlei = categoryList.value[categoryIndex.value]
  }
  if (searchQuery.value.shangpinbianhao && searchQuery.value.shangpinbianhao != '') {
    params.shangpinbianhao = '%' + searchQuery.value.shangpinbianhao + '%'
  }
  if (searchQuery.value.shangpinmingcheng && searchQuery.value.shangpinmingcheng != '') {
    params.shangpinmingcheng = '%' + searchQuery.value.shangpinmingcheng + '%'
  }
  if (sortType.value) {
    params['sort'] = sortType.value
    params['order'] = sortOrder.value
  }
  context
      ?.$http({
        url: `${tableName}/${centerType.value ? 'page' : 'list'}`,
        method: 'get',
        params: params,
      })
      .then(res => {
        listLoading.value = false
        list.value = res.data.data.list
        total.value = Number(res.data.data.total)
      })
}
//分类
const categoryList = ref([])
const categoryIndex = ref(-1)
const getCategoryList = () => {
  context
      ?.$http({
        url: 'option/shangpinfenlei/shangpinfenlei',
        method: 'get',
      })
      .then(res => {
        categoryList.value = res.data.data
      })
}
const categoryClick = index => {
  listQuery.value.page = 1
  categoryIndex.value = index
  getList()
}
const detailClick = id => {
  router.push(`${tableName}Detail?id=` + id + (centerType.value ? '&&centerType=1' : ''))
}
//快速添加到购物车
const quickAddToCart = (item) => {
  if (!item.alllimittimes || item.alllimittimes <= 0) {
    context?.$toolUtil.message('商品已售罄', 'error')
    return
  }

  const cartForm = {
    userid: context?.$toolUtil.storageGet('userid'),
    tablename: tableName,
    goodid: item.id,
    goodname: item.shangpinmingcheng,
    picture: item.shangpintupian ? (item.shangpintupian.substr(0, 4) == 'http' ? item.shangpintupian.split(',')[0] : context?.$config.url + item.shangpintupian.split(',')[0]) : '',
    buynumber: 1,
    price: item.price,
    shangjia: item.shangjia
  }

  context?.$http({
    url: 'cart/save',
    method: 'post',
    data: cartForm
  }).then(res => {
    context?.$toolUtil.message('已添加到购物车', 'success')
  }).catch(err => {
    context?.$toolUtil.message('添加失败', 'error')
  })
}
//下载文件
const download = file => {
  if (!file) {
    context?.$toolUtil.message('文件不存在', 'error')
  }
  const a = document.createElement('a')
  a.style.display = 'none'
  a.setAttribute('target', '_blank')
  file && a.setAttribute('download', file)
  a.href = context?.$config.url + file
  document.body.appendChild(a)
  a.click()
  document.body.removeChild(a)
}
// 查看大图
const preViewUrl = ref('')
const preViewVisible = ref(false)
const preViewClick = url => {
  preViewUrl.value = url
  preViewVisible.value = true
}
const init = async () => {
  if (route.query.centerType) {
    centerType.value = true
  }
  getCategoryList()
  if (context.$toolUtil.storageGet('frontToken') && !user.value.id) {
    await store.dispatch('user/getSession')
  }
  getList()
}
init()
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
// 分类盒子
.category_view {
  // 分类item
  .category {
  }
  // item-悬浮
  .category:hover {
  }
  // item-选中
  .categoryActive {
  }
}

//搜索
.list_search {
  .search_view {
    .search_label {
    }
    .search_box {
      // 输入框
      :deep(.search_inp) {
        .is-focus {
          box-shadow: none !important;
        }
      }
    }
  }
  .search_btn_view {
    // 搜索按钮
    .search_btn {
    }
    // 搜索按钮-悬浮
    .search_btn:hover {
    }
    // 新增按钮
    .add_btn {
    }
    // 新增按钮-悬浮
    .add_btn:hover {
    }
  }
}

// 数据盒子
.page_list {
  //列表
  .data_box {
    .data_view {
      .data_item {
        position: relative;
        background: #fff;
        border-radius: 12px;
        overflow: hidden;
        transition: all 0.3s ease;
        box-shadow: 0 2px 8px rgba(0, 0, 0, 0.08);
        cursor: pointer;

        &:hover {
          transform: translateY(-4px);
          box-shadow: 0 8px 24px rgba(0, 0, 0, 0.15);

          .data_img_box {
            .data_img {
              transform: scale(1.05);
            }
          }

          .quick_add_btn {
            opacity: 1;
            transform: translateY(0);
          }
        }

        // 图片包装器
        .img_wrapper {
          position: relative;
          width: 100%;
          overflow: hidden;

          // 图片盒子
          .data_img_box {
            width: 100%;
            aspect-ratio: 1 / 1;
            overflow: hidden;

            // 图片
            .data_img {
              width: 100%;
              height: 100%;
              object-fit: cover;
              transition: transform 0.3s ease;
            }
          }

          // 库存徽章
          .stock_badge {
            position: absolute;
            top: 8px;
            right: 8px;
            background: rgba(0, 0, 0, 0.6);
            backdrop-filter: blur(4px);
            color: #fff;
            padding: 4px 10px;
            border-radius: 12px;
            font-size: 12px;
            display: flex;
            align-items: center;
            gap: 4px;

            .stock_icon {
              font-size: 14px;
            }

            .stock_text {
              font-weight: 500;
            }
          }
        }

        // 内容盒子
        .data_content {
          padding: 12px;

          // 标题1
          .data_title1 {
            margin: 0 0 10px;
            width: 100%;
            font-size: 14px;
            font-weight: 500;
            color: #333;
            line-height: 1.4;
            overflow: hidden;
            text-overflow: ellipsis;
            display: -webkit-box;
            -webkit-line-clamp: 2;
            -webkit-box-orient: vertical;
            min-height: 40px;
          }

          // 价格和操作行
          .price_action_row {
            display: flex;
            justify-content: space-between;
            align-items: center;

            // 价格
            .data_price {
              color: #ff4757;
              display: flex;
              align-items: baseline;

              .price_symbol {
                font-size: 14px;
                font-weight: 600;
              }

              .price_value {
                font-size: 20px;
                font-weight: 700;
                margin-left: 2px;
              }
            }

            // 快速添加按钮
            .quick_add_btn {
              width: 36px;
              height: 36px;
              background: linear-gradient(135deg, #667eea 0%, #764ba2 100%);
              border: none;
              opacity: 0;
              transform: translateY(10px);
              transition: all 0.3s ease;

              &:hover:not(:disabled) {
                background: linear-gradient(135deg, #764ba2 0%, #667eea 100%);
                transform: scale(1.1);
              }

              &:disabled {
                background: #ccc;
                cursor: not-allowed;
              }

              :deep(.el-icon) {
                font-size: 18px;
              }
            }
          }
        }
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
          .is-focus {
            box-shadow: none !important;
          }
        }
      }
    }
  }
  // 跳页
  :deep(.el-pagination__jump) {
    // 输入框
    .el-input {
      .is-focus {
        box-shadow: none !important;
      }
    }
  }
}

// 热门信息盒子
.hot_view {
  // 标题
  .hot_title {
  }

  .hot_list {
    // item
    .hot {
      //图片盒子
      .hot_img_view {
        // 图片
        .hot_img {
        }
      }
      // 内容盒子
      .hot_content {
        // 名称
        .hot_text {
        }
      }
    }
  }
}
</style>

