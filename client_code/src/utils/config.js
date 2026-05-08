const config = {
  get() {
    return {
      url: process.env.VUE_APP_BASE_API_URL + process.env.VUE_APP_BASE_API + '/',
      name: process.env.VUE_APP_BASE_API,
      menuList: [
        {
          name: '购物车管理',
          icon: '',
          child: [
            {
              name: '购物车',
              url: '/index/cartList',
            },
          ],
        },
        {
          name: '公告资讯管理',
          icon: '',
          child: [
            {
              name: '广告信息',
              url: '/index/newsList',
            },
          ],
        },
        {
          name: '商品信息管理',
          icon: '',
          child: [
            {
              name: '商品信息',
              url: '/index/shangpinxinxiList',
            },
          ],
        },
        {
          name: '热销排行',
          icon: '',
          child: [
            {
              name: '热销排行',
              url: '/index/shangpinxinxiHotRanking',
            },
          ],
        },
        {
          name: '商品信息管理',
          icon: '',
          child: [
            {
              name: '团购商品',
              url: '/index/tuangoushangpinList',
            },
          ],
        },
      ],
    }
  },
  getProjectName() {
    return {
      projectName: `基于SpringBoot的社区团购系统`,
    }
  },
}
export default config
