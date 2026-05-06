<template>
	<div>
		<div class="home_box">
			<!-- 广告信息 -->
			<div class="newsList_view">
				<div class="ntitle"> <div class="n2">News Information</div> <div class="n1">广告信息</div> </div>
				
				    <div class="nlist">
				         <ul> 
				        <li v-for="(item,index) in newsList" :key="index" @click="newsDetailClick(item)">
				          
				          <div class="tim"><div class="t1">{{moment(item.addtime).format('DD')}}</div><div class="t2">{{moment(item.addtime).format('YYYY-MM-DD')}}</div></div>
				          
				          <div class="infobox">
				            <div class="nam">{{item.title}}</div>
				            <div class="info">{{item.introduction}}</div>
				          </div>
				          
				        </li>
				         </ul> 
				    </div>
				
				<div class="nmore" @click="moreClick('news')" style="cursor:pointer"><span>查看更多+</span></div>
			</div>

			<div class="appendBox1"></div>
		</div>
		<formModel ref="newsFormModelRef"></formModel>
	</div>
</template>

<script setup>
	import {
		ref,
		getCurrentInstance
	} from 'vue';
	import moment from 'moment'
	import {
		useRouter
	} from 'vue-router';
	const context = getCurrentInstance()?.appContext.config.globalProperties;
	const router = useRouter()
	//广告信息弹窗
	import formModel from './news/formModel'
	const newsFormModelRef = ref(null)
	//广告信息
	const newsList = ref([])
	const getNewsList = () => {
		context?.$http({
			url: 'news/list',
			method: 'get',
			params:{
				page:1,
				limit: 6,
                sort:'id',
                order:'desc',
			}
		}).then(res=>{
			newsList.value = res.data.data.list
			newsList.value.forEach(item=>{
				if(!isHttp(item.picture)){
					item.imgUrl=context.$config.url+item.picture.split(',')[0]
				}
			})
		})
	}
	const newsDetailClick = (item) => {
		if (item && item.id){
			newsFormModelRef.value.init(item.id)
		}
	}
	//判断图片链接是否带http
	const isHttp = (str) => {
        return str && str.substr(0,4)=='http';
    }
	//跳转详情
	const detailClick = (table,id) => {
		router.push(`/index/${table}Detail?id=${id}`)
	}
	const moreClick = (table) => {
		router.push(`/index/${table}List`)
	}
	const init = () => {
		//广告信息
		getNewsList()
	}
	init()
</script>

<style lang="scss">
	.home_box {
	}

	// 推荐
	.recomList_view {
		.recomList_title {
		}
		// list
		// list
		// animation
		.animation_box {
			transform: rotate(0deg) scale(1) skew(0deg, 0deg) translate3d(0px, 0px, 0px);
			z-index: initial;
		}
		.animation_box:hover {
			transform: rotate(0deg) scale(1) skew(0deg, 0deg) translate3d(0px, 0px, 0px);
			-webkit-perspective: 1000px;
			perspective: 1000px;
			transition: 0.3s;
		}
		.animation_box img {
			transform: rotate(0deg) scale(1) skew(0deg, 0deg) translate3d(0px, 0px, 0px);
			z-index: initial;
		}
		.animation_box img:hover {
			transform: rotate(0deg) scale(1.05) skew(0deg, 0deg) translate3d(0px, 0px, 0px);
			-webkit-perspective: 1000px;
			perspective: 1000px;
			transition: 0.3s;
		}
		// animation
		// 更多
		.recommend_more_view {
			.recommend_more_text {
			}
		}
	}
	// 推荐
	// 新闻资讯
	.newsList_view {

		.newsList_title {
		}
		// list
		.news_list_one {
			display: flex;
			flex-wrap: wrap;
			.news_item {
				box-shadow: 0 4px 6px rgba(0,0,0,.3);
				margin: 0 10px 10px;
				background: #fff;
				display: flex;
				width: calc(33% - 20px);
				align-items: center;
				.news_img_box {
					width: 40%;
					font-size: 0;
					.news_img {
						object-fit: cover;
						width: 100%;
						height: 200px;
					}
				}
				.news_content {
					margin: 0 0 0 20px;
					width: calc(60% - 20px);
					.news_title {
						font-weight: bold;
						font-size: 20px;
					}
					.news_text {
						font-size: 14px;
						line-height: 1.5;
					}
					.news_time {
						color: #999;
						width: 100%;
						text-align: right;
					}
				}
			}
		}
		// list
		// animation
		.animation_box {
			transform: rotate(0deg) scale(1) skew(0deg, 0deg) translate3d(0px, 0px, 0px);
			z-index: initial;
		}
		.animation_box:hover {
			transform: rotate(0deg) scale(1) skew(0deg, 0deg) translate3d(0px, 0px, 0px);
			-webkit-perspective: 1000px;
			perspective: 1000px;
			transition: 0.3s;
		}
		.animation_box img {
			transform: rotate(0deg) scale(1) skew(0deg, 0deg) translate3d(0px, 0px, 0px);
			z-index: initial;
		}
		.animation_box img:hover {
			transform: rotate(0deg) scale(1) skew(0deg, 0deg) translate3d(0px, 0px, 0px);
			-webkit-perspective: 1000px;
			perspective: 1000px;
			transition: 0.3s;
		}
		// animation
		// 更多
		.news_more_view {
			.news_more_text {
			}
			.el-icon {
			}
		}
	}
	// 新闻资讯
	// 首页展示
	.homeList_view {

		.homeList_title {
		}
		// list
		// list
		// animation
		.animation_box {
			transform: rotate(0deg) scale(1) skew(0deg, 0deg) translate3d(0px, 0px, 0px);
			z-index: initial;
		}
		.animation_box:hover {
			transform: rotate(0deg) scale(1) skew(0deg, 0deg) translate3d(0px, 0px, 0px);
			-webkit-perspective: 1000px;
			perspective: 1000px;
			transition: 0.3s;
		}
		.animation_box img {
			transform: rotate(0deg) scale(1) skew(0deg, 0deg) translate3d(0px, 0px, 0px);
			z-index: initial;
		}
		.animation_box img:hover {
			transform: rotate(0deg) scale(1) skew(0deg, 0deg) translate3d(0px, 0px, 0px);
			-webkit-perspective: 1000px;
			perspective: 1000px;
			transition: 0.3s;
		}
		// animation
		// 更多
		.homeList_more_view {
			cursor: pointer;
			.homeList_more_text {
			}
		}
	}
	// 首页展示
</style>
<style>
.home_box{
    width: 100%;
    margin: 0px auto;
    display: flex;
    flex-wrap: wrap;
    justify-content: space-between;
}
.home_box .appendBox1{
    width: 100%;
    height:0px;
    background:#f00;
    order:5;
}
/* 总盒子 */
.newsList_view {
    width: 100%;
    margin:20px 0px 0;
    padding: 0 16% 120px;
    background:url(http://clfile.zggen.cn/20241219/92aae42c463d48bd947f23e977b6ede8.jpg) no-repeat center top / cover;
    overflow: hidden;
    position:relative;
    order: 5;
    display:block;
}
.newsList_view:before {
    content: "";
    display: block;
    width: 1100px;
    height: 92px;
    background: url(http://clfile.zggen.cn/20241219/9cf7afb259b14d2b87108552ea885a27.png) no-repeat center top;
    margin: 0 auto;
}
.newsList_view:after {
    content: "";
    display: block;
    width: 1100px;
    height: 92px;
    background: url(http://clfile.zggen.cn/20241219/9cf7afb259b14d2b87108552ea885a27.png) no-repeat center top;
    position: absolute;
    bottom: -1px;
    left: 50%;
    transform: translateX(-50%) rotate(180deg);
}

/* 自定义 start*/
.newsList_view .ntitle{
    position: absolute;
    top: 0;
    left: 10%;
    width: 80%;
    margin: 20px 0 20px;
    z-index:999;
    text-align:center;
}
.newsList_view .ntitle .n1{
    display:inline-block;
    font-size:32px;
    color:#333;
    font-weight:600;
    padding-bottom:10px;
}
.newsList_view .ntitle .n2{
    display:none;
    font-size: 18px;
    line-height:30px;
    color:#888;
    margin-left:5px;
}

.newsList_view .nlist{
    width: 100%;
    margin:80px auto 0;
}
.newsList_view .nlist ul{
    margin:0;
    padding:0;
    display: flex;
    flex-wrap: wrap;
    align-items: center;
    justify-content: space-between;
}
.newsList_view .nlist ul li{
    width: 48%;
    text-align: left;
    background:#fff;
    border: 0px dashed #aaa;
    cursor:pointer;
    display: flex;
    flex-wrap: wrap;
    align-items: center;
    justify-content: space-between;
    margin:0 0 30px;
    padding:20px;
}
.newsList_view .nlist ul li:hover {
    background:var(--theme);
}
.newsList_view .nlist ul li:last-child{
    border-bottom: none;
}
.newsList_view .nlist ul li .tim {
    display: flex;
    flex-wrap: wrap;
    width: 80px;
}
.newsList_view .nlist ul li .tim .t1 {
    width: 100%;
    text-align: center;
    font-size: 48px;
    line-height: 1;
}
.newsList_view .nlist ul li:hover .tim .t1 {
    color:#fff;
}
.newsList_view .nlist ul li .tim .t2 {
    width: 100%;
    text-align: center;
    line-height: 30px;
    font-size: 14px;
    color:#fff;
    background:var(--theme);
}
.newsList_view .nlist ul li:hover .tim .t2 {
    color:var(--theme);
    background:#fff;
}
.newsList_view .nlist ul li .infobox {
    width: calc(100% - 110px);
    flex: 1;
    padding:0 20px 20px;
}
.newsList_view .nlist ul li .infobox .nam {
    font-size: 16px;
    font-weight: 600;
}
.newsList_view .nlist ul li:hover .infobox .nam {
    color:#fff;
}
.newsList_view .nlist ul li:hover .infobox .nam{
    color:#fff;
}
.newsList_view .nlist ul li .infobox .info {
    font-size: 15px;
    color: #888;
    line-height: 24px;
    height:48px;
    overflow:hidden;
    margin-top: 5px;
}
.newsList_view .nlist ul li:hover .infobox .info {
    color:#ddd;
}

/* 更多 */
.newsList_view .nmore{
    position: absolute;
    bottom: 40px;
    left: 10%;
    width: 80%;
    margin: 0px;
    z-index:999;
    text-align:center;
    cursor:pointer;
}
.newsList_view .nmore span{
    display: inline-block;
    background:var(--theme);
    padding:5px 10px;
    font-size: 16px;
    color:#fff;
}
/* 自定义 end*/

/* 首页新闻详情 */
.news-detail-dialog{
    --el-dialog-width: 60%;
}
.news-detail-dialog .news_detail{
}
.news-detail-dialog .news_detail .news_detail_title{
    font-size: 22px;
    font-weight: 600;
}
.news-detail-dialog .news_detail .news_detail_time{
    width: 100%;
    text-align: right;
    margin: 10px 0;
    font-size: 14px;
    color: #999;
}
.news-detail-dialog .news_detail .news_detail_content{
    width: 100%;
    font-size: 15px;
    line-height: 24px;
}

</style>