	import {
		createRouter,
		createWebHashHistory
	} from 'vue-router'
	import news from '@/views/news/list'
	import discusstuangoushangpin from '@/views/discusstuangoushangpin/list'
	import address from '@/views/address/list'
	import shangpinxinxi from '@/views/shangpinxinxi/list'
    import menu_manage from '@/views/menu_manage/list'
	import storeup from '@/views/storeup/list'
	import users from '@/views/users/list'
	import shangjia from '@/views/shangjia/list'
	import cart from '@/views/cart/list'
	import tuangoushangpin from '@/views/tuangoushangpin/list'
	import yonghu from '@/views/yonghu/list'
	import shangpinfenlei from '@/views/shangpinfenlei/list'
	import discussshangpinxinxi from '@/views/discussshangpinxinxi/list'
	import orders from '@/views/orders/list'
	import config from '@/views/config/list'
	import dingdanpeisong from '@/views/dingdanpeisong/list'
	import peisongyuan from '@/views/peisongyuan/list'
	import usersCenter from '@/views/users/center'
	import shangjiaRegister from '@/views/shangjia/register'
	import shangjiaCenter from '@/views/shangjia/center'

export const routes = [{
		path: '/login',
		name: 'login',
		component: () => import('../views/login.vue')
	},{
		path: '/',
		name: '首页',
		component: () => import('../views/index'),
		children: [{
			path: '/',
			name: '首页',
			component: () => import('../views/HomeView.vue'),
			meta: {
				affix: true
			}
		}, {
			path: '/updatepassword',
			name: '修改密码',
			component: () => import('../views/updatepassword.vue')
		}
		
		,{
			path: '/usersCenter',
			name: '管理员个人中心',
			component: usersCenter
		}
		,{
			path: '/shangjiaCenter',
			name: '商家个人中心',
			component: shangjiaCenter
		}
		,{
			path: '/news',
			name: '广告信息',
			component: news
		}
		,{
			path: '/discusstuangoushangpin',
			name: '团购商品评论-评论',
			component: discusstuangoushangpin
		}
		,{
			path: '/address',
			name: '地址',
			component: address
		}
		,{
			path: '/shangpinxinxi',
			name: '商品信息',
			component: shangpinxinxi
		}
        ,{
            path: '/menu',
            name: '菜单权限管理',
            component: menu_manage
        }
		,{
			path: '/storeup',
			name: '我的收藏',
			component: storeup
		}
		,{
			path: '/users',
			name: '管理员',
			component: users
		}
		,{
			path: '/shangjia',
			name: '商家',
			component: shangjia
		}
		,{
			path: '/cart',
			name: '购物车',
			component: cart
		}
		,{
			path: '/tuangoushangpin',
			name: '团购商品',
			component: tuangoushangpin
		}
		,{
			path: '/yonghu',
			name: '用户',
			component: yonghu
		}
		,{
			path: '/shangpinfenlei',
			name: '商品分类',
			component: shangpinfenlei
		}
		,{
			path: '/discussshangpinxinxi',
			name: '商品信息评论-评论',
			component: discussshangpinxinxi
		}
		,{
			path: '/orders',
			name: '订单管理',
			component: orders
		}
		,{
			path: '/config',
			name: '轮播图',
			component: config
		}
		,{
			path: '/dingdanpeisong',
			name: '订单配送',
			component: dingdanpeisong
		}
		,{
			path: '/peisongyuan',
			name: '配送员',
			component: peisongyuan
		}
		]
	},
	{
		path: '/shangjiaRegister',
		name: '商家注册',
		component: shangjiaRegister
	},
]

const router = createRouter({
	history: createWebHashHistory(process.env.BASE_URL),
	routes
})

export default router
