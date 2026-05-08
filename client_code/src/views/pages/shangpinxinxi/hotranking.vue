<template>
  <div class="list-page">
    <div class="breadcrumb-wrapper" style="width: 100%">
      <div class="bread_view">
        <el-breadcrumb separator="Ξ" class="breadcrumb">
          <el-breadcrumb-item class="first_breadcrumb" :to="{ path: '/' }">首页</el-breadcrumb-item>
          <el-breadcrumb-item class="second_breadcrumb">热销排行</el-breadcrumb-item>
        </el-breadcrumb>
      </div>
    </div>
    
    <div class="ranking-container">
      <div class="ranking-header">
        <h2 class="title">热销排行 TOP 10</h2>
        <p class="subtitle">根据销量、收藏、评论综合评分统计</p>
      </div>
      
      <div class="ranking-list">
        <div 
          v-for="(item, index) in list" 
          :key="index" 
          class="ranking-item"
          @click="detailClick(item.id)"
        >
          <div class="rank-number" :class="'rank-' + (index + 1)">
            {{ index + 1 }}
          </div>
          
          <div class="product-img">
            <el-image 
              :src="item.shangpintupian ? $config.url + item.shangpintupian.split(',')[0] : ''" 
              fit="cover"
            ></el-image>
          </div>
          
          <div class="product-info">
            <div class="product-name">{{ item.shangpinmingcheng }}</div>
            <div class="product-category">分类：{{ item.shangpinfenlei }}</div>
            <div class="product-stats">
              <span class="stat-item"><el-icon><Star /></el-icon> 收藏: {{ item.storeupNumber || 0 }}</span>
              <span class="stat-item"><el-icon><ChatDotRound /></el-icon> 评论: {{ item.discussNumber || 0 }}</span>
            </div>
          </div>
          
          <div class="product-price">
            <span class="price-label">价格</span>
            <span class="price-value">￥{{ item.price }}</span>
          </div>
          
          <div class="product-score">
            <div class="score-label">综合热度</div>
            <div class="score-value">{{ item.score }}</div>
          </div>
        </div>
      </div>
    </div>
  </div>
</template>

<script setup>
import { ref, getCurrentInstance } from 'vue'
import { useRouter } from 'vue-router'

const context = getCurrentInstance()?.appContext.config.globalProperties
const router = useRouter()
const list = ref([])

const getList = () => {
  context?.$http({
    url: 'shangpinxinxi/hotRanking',
    method: 'get'
  }).then(res => {
    list.value = res.data.data
  })
}

const detailClick = (id) => {
  router.push(`shangpinxinxiDetail?id=${id}`)
}

getList()
</script>

<style lang="scss" scoped>
.ranking-container {
  width: 80%;
  margin: 30px auto;
  background: #fff;
  padding: 30px;
  border-radius: 12px;
  box-shadow: 0 4px 20px rgba(0,0,0,0.05);
}

.ranking-header {
  text-align: center;
  margin-bottom: 40px;
  .title {
    font-size: 28px;
    color: #ea580c;
    margin-bottom: 10px;
  }
  .subtitle {
    color: #666;
    font-size: 14px;
  }
}

.ranking-list {
  display: flex;
  flex-direction: column;
  gap: 15px;
}

.ranking-item {
  display: flex;
  align-items: center;
  padding: 15px;
  border: 1px solid #f3f4f6;
  border-radius: 8px;
  transition: all 0.3s;
  cursor: pointer;
  
  &:hover {
    transform: translateY(-2px);
    box-shadow: 0 4px 12px rgba(0,0,0,0.1);
    border-color: #ea580c33;
  }
}

.rank-number {
  width: 40px;
  height: 40px;
  display: flex;
  align-items: center;
  justify-content: center;
  font-size: 20px;
  font-weight: bold;
  margin-right: 20px;
  border-radius: 50%;
  background: #f3f4f6;
  color: #6b7280;
  
  &.rank-1 { background: #fef3c7; color: #d97706; }
  &.rank-2 { background: #e5e7eb; color: #4b5563; }
  &.rank-3 { background: #ffedd5; color: #c2410c; }
}

.product-img {
  width: 80px;
  height: 80px;
  margin-right: 20px;
  .el-image {
    width: 100%;
    height: 100%;
    border-radius: 6px;
  }
}

.product-info {
  flex: 1;
  .product-name {
    font-size: 18px;
    font-weight: bold;
    margin-bottom: 5px;
    color: #1f2937;
  }
  .product-category {
    font-size: 14px;
    color: #6b7280;
    margin-bottom: 5px;
  }
  .product-stats {
    display: flex;
    gap: 15px;
    font-size: 13px;
    color: #9ca3af;
    .stat-item {
      display: flex;
      align-items: center;
      gap: 3px;
    }
  }
}

.product-price {
  text-align: right;
  margin-right: 40px;
  .price-label {
    display: block;
    font-size: 12px;
    color: #9ca3af;
  }
  .price-value {
    font-size: 18px;
    font-weight: bold;
    color: #ef4444;
  }
}

.product-score {
  text-align: center;
  padding: 0 20px;
  border-left: 1px dashed #e5e7eb;
  .score-label {
    font-size: 12px;
    color: #9ca3af;
  }
  .score-value {
    font-size: 22px;
    font-weight: bold;
    color: #ea580c;
  }
}

.bread_view {
  width: 100%;
  padding: 15px 20px 10px 16%;
  background: var(--theme-color);
  .el-breadcrumb__separator {
    color: #fff;
  }
  .el-breadcrumb__inner {
    color: #fff !important;
  }
}
</style>
