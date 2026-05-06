import { createRouter, createWebHashHistory } from 'vue-router'
import index from '../views'
import home from '../views/pages/home.vue'
import login from '../views/pages/login.vue'
import newsList from '@/views/pages/news/list'
import yonghuList from '@/views/pages/yonghu/list'
import yonghuDetail from '@/views/pages/yonghu/formModel'
import yonghuAdd from '@/views/pages/yonghu/formAdd'
import yonghuRegister from '@/views/pages/yonghu/register'
import yonghuCenter from '@/views/pages/yonghu/center'
import tuangoushangpinList from '@/views/pages/tuangoushangpin/list'
import tuangoushangpinDetail from '@/views/pages/tuangoushangpin/formModel'
import tuangoushangpinAdd from '@/views/pages/tuangoushangpin/formAdd'
import storeupList from '@/views/pages/storeup/list'
import addressList from '@/views/pages/shop_address/list'
import shangpinfenleiList from '@/views/pages/shangpinfenlei/list'
import shangpinfenleiDetail from '@/views/pages/shangpinfenlei/formModel'
import shangpinfenleiAdd from '@/views/pages/shangpinfenlei/formAdd'
import cartList from '@/views/pages/shop_order/cart'
import shangpinxinxiList from '@/views/pages/shangpinxinxi/list'
import shangpinxinxiDetail from '@/views/pages/shangpinxinxi/formModel'
import shangpinxinxiAdd from '@/views/pages/shangpinxinxi/formAdd'
import shangjiaList from '@/views/pages/shangjia/list'
import shangjiaDetail from '@/views/pages/shangjia/formModel'
import shangjiaAdd from '@/views/pages/shangjia/formAdd'
import dingdanpeisongList from '@/views/pages/dingdanpeisong/list'
import dingdanpeisongDetail from '@/views/pages/dingdanpeisong/formModel'
import dingdanpeisongAdd from '@/views/pages/dingdanpeisong/formAdd'
import peisongyuanList from '@/views/pages/peisongyuan/list'
import peisongyuanDetail from '@/views/pages/peisongyuan/formModel'
import peisongyuanAdd from '@/views/pages/peisongyuan/formAdd'
import chatMessageList from '@/views/pages/chatMessage/list'
import chatMessageDetail from '@/views/pages/chatMessage/formModel'
import chatMessageAdd from '@/views/pages/chatMessage/formAdd'
import chatFriendList from '@/views/pages/chatFriend/list'
import chatFriendDetail from '@/views/pages/chatFriend/formModel'
import chatFriendAdd from '@/views/pages/chatFriend/formAdd'
import order_confirm from '@/views/pages/shop_order/confirm'
import ordersList from '@/views/pages/shop_order/list'

const routes = [{
		path: '/',
		redirect: '/index/home'
	},
	{
		path: '/index',
		component: index,
		children: [{
			path: 'home',
			component: home
		}
		, {
			path: 'newsList',
			component: newsList
		}
		, {
			path: 'yonghuList',
			component: yonghuList
		}, {
			path: 'yonghuDetail',
			component: yonghuDetail
		}, {
			path: 'yonghuAdd',
			component: yonghuAdd
		}
		, {
			path: 'yonghuCenter',
			component: yonghuCenter
		}
		, {
			path: 'tuangoushangpinList',
			component: tuangoushangpinList
		}, {
			path: 'tuangoushangpinDetail',
			component: tuangoushangpinDetail
		}, {
			path: 'tuangoushangpinAdd',
			component: tuangoushangpinAdd
		}
        , {
            path: 'storeupList',
            component: storeupList
        }
		, {
			path: 'addressList',
			component: addressList
		}
		, {
			path: 'shangpinfenleiList',
			component: shangpinfenleiList
		}, {
			path: 'shangpinfenleiDetail',
			component: shangpinfenleiDetail
		}, {
			path: 'shangpinfenleiAdd',
			component: shangpinfenleiAdd
		}
		, {
			path: 'cartList',
			component: cartList
		}
		, {
			path: 'shangpinxinxiList',
			component: shangpinxinxiList
		}, {
			path: 'shangpinxinxiDetail',
			component: shangpinxinxiDetail
		}, {
			path: 'shangpinxinxiAdd',
			component: shangpinxinxiAdd
		}
		, {
			path: 'shangjiaList',
			component: shangjiaList
		}, {
			path: 'shangjiaDetail',
			component: shangjiaDetail
		}, {
			path: 'shangjiaAdd',
			component: shangjiaAdd
		}
		, {
			path: 'dingdanpeisongList',
			component: dingdanpeisongList
		}, {
			path: 'dingdanpeisongDetail',
			component: dingdanpeisongDetail
		}, {
			path: 'dingdanpeisongAdd',
			component: dingdanpeisongAdd
		}
		, {
			path: 'peisongyuanList',
			component: peisongyuanList
		}, {
			path: 'peisongyuanDetail',
			component: peisongyuanDetail
		}, {
			path: 'peisongyuanAdd',
			component: peisongyuanAdd
		}
		, {
			path: 'chatMessageList',
			component: chatMessageList
		}, {
			path: 'chatMessageDetail',
			component: chatMessageDetail
		}, {
			path: 'chatMessageAdd',
			component: chatMessageAdd
		}
		, {
			path: 'chatFriendList',
			component: chatFriendList
		}, {
			path: 'chatFriendDetail',
			component: chatFriendDetail
		}, {
			path: 'chatFriendAdd',
			component: chatFriendAdd
		}
		, {
			path: 'order_confirm',
			component: order_confirm
		}
		, {
			path: 'ordersList',
			component: ordersList
		}
		]
	},
	{
		path: '/login',
		component: login
	}
	,{
		path: '/yonghuRegister',
		component: yonghuRegister
	}
]

const router = createRouter({
  history: createWebHashHistory(process.env.BASE_URL),
  routes
})

export default router
