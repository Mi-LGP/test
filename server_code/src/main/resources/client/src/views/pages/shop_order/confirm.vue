<template>
	<div class="list-page" :style='{"border":"0px solid #888","padding":"40px 16% 20px","margin":"20px auto 40px","borderRadius":"6px","background":"#fff","width":"100%","fontSize":"16px","position":"relative"}'>
		<div class="section_title">
            <span>{{formName}}</span>
		</div>
		<el-card style="width: 100%">
            <el-button @click="backClick">返回</el-button>
			<el-divider content-position="center">地址</el-divider>
			<el-table :stripe='true' :data="addressList">
				<el-table-column label="选择"  :resizable='true' align="left" header-align="left">
					<template #default="scope">
						<el-radio :label="scope.$index" v-model="addressIndex">&nbsp;</el-radio>
					</template>
				</el-table-column>
				<el-table-column label="联系人" :resizable='true' align="left" header-align="left">
					<template #default="scope">
						{{scope.row.name}}
					</template>
				</el-table-column>
				<el-table-column label="联系电话" :resizable='true' align="left" header-align="left">
					<template #default="scope">
						{{scope.row.phone}}
					</template>
				</el-table-column>
				<el-table-column label="地址" :resizable='true' align="left" header-align="left">
					<template #default="scope">
						{{scope.row.address}}
					</template>
				</el-table-column>
			</el-table>
			<br>
			<div class="add_view">
				<el-button class="addressAdd_btn" @click="addressAdd" type="success">新增地址</el-button>
			</div>
			
			<el-divider content-position="center">商品清单</el-divider>
			<el-table :data="list" :stripe='true'>
				<el-table-column label="商品名称" :resizable='true' align="left" header-align="left">
					<template #default="scope">
						{{scope.row.goodname}}
					</template>
				</el-table-column>
				<el-table-column label="商品图片" :resizable='true' align="left" header-align="left">
					<template #default="scope">
						<img :src="scope.row.picture?($config.url + scope.row.picture):''" alt=""
							style="width: 150px;height: 150px;">
					</template>
				</el-table-column>
				<el-table-column label="价格" :resizable='true' align="left" header-align="left">
					<template #default="scope">
						<span style="font-size: 12px;" >￥</span>{{scope.row.realPrice}} 
					</template>
				</el-table-column>
				<el-table-column label="数量" :resizable='true' align="left" header-align="left">
					<template #default="scope">{{scope.row.buynumber}}
					</template>
				</el-table-column>
				<el-table-column label="总价" :resizable='true' align="left" header-align="left">
					<template #default="scope">
						<span style="font-size: 12px;" >￥</span>{{(scope.row.realPrice * scope.row.buynumber).toFixed(2)}} 
					</template>
				</el-table-column>
			</el-table>
			<el-divider content-position="center">备注</el-divider>
			
			<el-input v-model="remark" placeholder="请输入备注" type="textarea"></el-input>
			<div class="order_confirm_btn" >
				<div class="order_confirm_price">
					总价：<span >￥</span>{{allPrice}}
				</div>
				<el-button class="pay_btn"  @click="payClick" type="success">余额支付</el-button>
			</div>
		</el-card>
		<br>
		<formModel ref="formModelRef" @formModelChange="formModelChange"></formModel>
	</div>
</template>

<script setup>
	import formModel from '../shop_address/formModel'
	import {
		ref,
		getCurrentInstance,
		nextTick,
		computed,
	} from 'vue';
	import {
		useRoute,
		useRouter
	} from 'vue-router';
	import moment from 'moment'
    import {
        useStore
    } from 'vuex';
    const store = useStore()
	const context = getCurrentInstance()?.appContext.config.globalProperties;
	const router = useRouter()
	const route = useRoute()
	//基础信息
	const tableName = 'orders'
	const formName = '订单提交'
	//基础信息
    // 返回
    const backClick = () =>{
        history.back()
    }
	const list = ref([])
	const remark = ref('')
    //付款类型
    const payType = ref(1)
	//获取收货地址
	const addressIndex = ref(-1)
	const addressList = ref([])
	const getAddressList = () => {
		context.$http.get('address/page?limit=99').then(res => {
			for (let x in res.data.data.list) {
				if (res.data.data.list[x].isdefault == '是') {
					addressIndex.value = Number(x)
				}
			}
			addressList.value = res.data.data.list
		})
	}
	//新增收货地址
	const formModelRef = ref(null)
	const addressAdd = () => {
		formModelRef.value.init(null, '新增收货地址')
	}
	const formModelChange = () => {
		getAddressList()
	}
	//统计总价
	const allPrice = computed(() => {
		let price = 0
		list.value.forEach(item=>{
			price += item.realPrice * item.buynumber
		})
		return Number(price).toFixed(2)
	})
	//获取订单id
	const createOrder = () => {
		return moment().format('YYYYMMDDHHmmssSSS')+Math.random().toString().slice(2, 5);
	}
	//正常支付
	const payClick = async () => {
		//是否选择收货地址
		if (addressIndex.value == -1) {
			context.$toolUtil.message('请选择收货地址', 'error')
			return false
		}
        let orders = []
        for(let i in list.value){
            let item = list.value[i]
            let res = await context.$http.get(`${item.tablename}/info/${item.goodid}`)
            let data = res.data.data
            let orderid = createOrder()
            orders.push({
                orderid: orderid,
                tablename: item.tablename,
                userid: user.value.id,
                role: context.$toolUtil.storageGet('frontSessionTable'),
                goodid: item.goodid,
                goodname: item.goodname,
                shangjia: item.shangjia,
                shangjia: item.shangjia,
                picture: item.picture,
                buynumber: item.buynumber,
                discountprice: item.realPrice,
                discounttotal: Number(item.realPay.toFixed(2)),
                price: item.realPrice,
                total: Number(item.realPay.toFixed(2)),
                type: payType.value,
                //收货地址
                address: addressList.value[addressIndex.value].address,
                tel: addressList.value[addressIndex.value].phone,
                consignee: addressList.value[addressIndex.value].name,
                remark: remark.value,
                status: '未支付',
                goods:data
            })
        }
        let groupNO = ''
        let peopleNum = 0
        let tuangouId = null  // 拼团 ID
        let isGroupLeader = false  // 是否团长
        let tips = ''  // 提示信息
        if(payType.value==3){
            //团购需先判断余额。余额不足不生成订单
            if(Number(user.value.money) < Number(list.value[0].realPay.toFixed(2))){
                context.$toolUtil.message(`余额不足，请先充值`,'error',()=>{
                    router.push(`/index/${context.$toolUtil.storageGet('frontSessionTable')}Center`)
                })
                return false
            }
            
            // 判断是开团还是参团
            isGroupLeader = list.value[0].isGroupLeader || false
            const selectedGroupId = list.value[0].selectedGroupId || null
            
            if(selectedGroupId) {
                // === 参团逻辑：加入已有拼团 ===
                tuangouId = selectedGroupId
                // 获取拼团信息
                const groupRes = await context.$http.get(`tuangouactivity/detail/${selectedGroupId}`)
                const groupData = groupRes.data.data
                
                // 检查该团是否还能加入
                if(groupData.status !== '拼团中' || groupData.curpeople >= groupData.grouppeople) {
                    context.$toolUtil.message('该团已满员或已结束','error')
                    return false
                }
                
                // 更新拼团人数
                groupData.curpeople += 1
                const isFull = groupData.curpeople >= groupData.grouppeople
                
                if(isFull) {
                    // 成团：更新拼团状态为"已拼成"
                    groupData.status = '已拼成'
                    await context.$http.post('tuangouactivity/update', groupData)
                    
                    // 更新所有该团的订单状态为"已支付"
                    const ordersToUpdate = await context.$http.get('orders/list', {
                        params: {
                            tuangouid: selectedGroupId,
                            page: 1,
                            limit: 999
                        }
                    })
                    
                    const updatePromises = ordersToUpdate.data.data.list.map(order => {
                        order.status = '已支付'
                        return context.$http.post('orders/update', order)
                    })
                    await Promise.all(updatePromises)
                    
                    groupNO = groupData.groupono
                    tips = '拼团成功！'
                } else {
                    // 未成团，只更新人数
                    await context.$http.post('tuangouactivity/update', groupData)
                    groupNO = groupData.groupono
                    tips = '参团成功！'
                }
                
                // 设置订单的团购信息
                orders.forEach(order => {
                    order.tuangouid = selectedGroupId
                    order.groupno = groupData.groupono
                    order.groupprice = order.discountprice
                    order.status = isFull ? '已支付' : '拼团中'
                })
                
            } else if(isGroupLeader) {
                // === 开团逻辑：创建新拼团 ===
                const groupono = createOrder()  // 生成唯一拼团编号
                const firstOrder = orders[0]
                
                // 创建拼团活动记录
                const groupActivityData = {
                    goodsid: firstOrder.goodid,
                    goodsname: firstOrder.goodname,
                    picture: firstOrder.picture,
                    groupono: groupono,
                    groupprice: firstOrder.discountprice,
                    grouppeople: firstOrder.goods.grouppeople,
                    curpeople: 1,  // 团长自己
                    status: '拼团中',
                    leaderid: user.value.id,
                    leadername: user.value.xingming || user.value.yonghu
                }
                
                await context.$http.post('tuangouactivity/add', groupActivityData)
                
                // 查询刚创建的拼团活动获取 ID
                const queryRes = await context.$http.get('tuangouactivity/getByGroupono', {
                    params: {
                        groupono: groupono
                    }
                })
                
                if(queryRes.data.data && queryRes.data.data.id) {
                    tuangouId = queryRes.data.data.id
                } else {
                    context.$toolUtil.message('创建拼团失败','error')
                    return false
                }
                
                groupNO = groupono
                
                // 设置订单的团购信息
                orders.forEach(order => {
                    order.tuangouid = tuangouId
                    order.groupno = groupono
                    order.groupprice = order.discountprice
                    order.status = '拼团中'
                })
                
                tips = '开团成功！'
            }
        }
        //新增订单
        Promise.all(orders.map(order=>{ //批量下单，返回primise数组，等待所有请求都完成
            return context.$http.post('orders/add',order)
        })).then(resArr=>{
            let total = 0
            orders.forEach(order=>{
                total+=Number(order.total)
            })
            //判断用户余额是否充足
            if(Number(user.value.money) < Number(total.toFixed(2))){
                context.$toolUtil.message(`余额不足，请先充值`,'error',()=>{
                    router.push(`/index/${context.$toolUtil.storageGet('frontSessionTable')}Center`)
                })
                return false
            }
            //减去用户余额
            user.value.money = (parseFloat(user.value.money) - parseFloat(total)).toFixed(2)
            context.$http.post(`${context.$toolUtil.storageGet('frontSessionTable')}/update`,user.value)

            orders.forEach(order=>{
                //如果商品有库存 减去商品库存
                if(order.goods.hasOwnProperty('alllimittimes')){
                    order.goods.alllimittimes = order.goods.alllimittimes - order.buynumber
                }
                context.$http.post(`${order.tablename}/update`,order.goods)
                // 如果是普通订单或团购但已支付，直接保存；否则更新订单状态
                if(payType.value !== 3 || order.status === '已支付') {
                    // 普通订单或已成团的团购单，已经是"已支付"状态，无需再更新
                } else {
                    // 拼团中的订单，创建后保存
                    context.$http.get('orders/list',{
                        params:{
                            page:1,
                            limit:1,
                            orderid: order.orderid
                        }
                    }).then(res=>{
                        const orderData = res.data.data.list[0]
                        orderData.status = order.status  // 保持原有的"拼团中"状态
                        orderData.groupno = order.groupno
                        orderData.tuangouid = order.tuangouid
                        orderData.groupprice = order.groupprice
                        context.$http.post('orders/update',orderData)
                    })
                }
            })
            //如果存在 id。说明从购物车跳转，需要删除已下单的商品
            list.value.forEach(item=>{
                if(item.id){
                    context.$http({url:'cart/delete',method:'post',data:[item.id]})
                }
            })
            //如果是团购，分三种情况，提示不同
            let tipsMsg = tips || ''  // 使用上面已经设置的 tips
            if(payType.value == 3 && !tipsMsg){
                // 如果 tips 为空，说明没有成团，显示参团成功
                tipsMsg = '参团成功！'
            } else if(!tipsMsg) {
                //如果不是团购单 下单完成，跳转订单
                tipsMsg = '购买成功!'
            }
            context.$toolUtil.message(tipsMsg,'success',()=>{
                router.push('/index/ordersList')
            })
        })
	}

	//获取个人信息
	const user = ref({})
	//初始化
	const init = () => {
		getAddressList()
		if (route.query.type) {
			payType.value = route.query.type
		}
        store.dispatch('user/getSession').then(res=>{
            user.value = res.data.data
            list.value = JSON.parse(context.$toolUtil.storageGet('orders_good'))
            list.value.forEach(item=>{
                item.realPrice = item.price
                item.realPay=Number(item.realPrice*item.buynumber)
            })
        })
	}
	init()
</script>

<style lang="scss" scoped>
    .section_title{
        span{
        }
    }
	// 表格样式
	.el-table {
		padding: 0;
		margin: 20px 0 0;
		background: #fff;
		width: 100%;
		font-size: 15px;
		border-color: #eee;
		border-width: 1px 0 0 1px;
		border-style: solid;
		:deep(.el-table__header-wrapper) {
			thead {
				color: #333;
				font-weight: 500;
				width: 100%;
				tr {
					background: #fff;
					th {
						padding: 8px 0;
						background: var(--theme);
						border-color: #eee;
						border-width: 0 1px 1px 0;
						border-style: solid;
						text-align: left;
						.cell {
							padding: 0 10px;
							word-wrap: normal;
							color: #fff;
							white-space: nowrap;
							font-weight: bold;
							display: flex;
							vertical-align: middle;
							line-height: 24px;
							text-overflow: ellipsis;
							word-break: break-all;
							width: 100%;
							align-items: center;
							position: relative;
							min-width: 110px;
						}
					}
				}
			}
		}
		:deep(.el-table__body-wrapper) {
			tbody {
				width: 100%;
				tr {
					background: #fff;
					td {
						padding: 12px 0;
						color: #666;
						background: #fff;
						border-color: #eee;
						border-width: 0 1px 1px 0;
						border-style: solid;
						text-align: left;
						.cell {
							padding: 0 10px;
							overflow: hidden;
							word-break: break-all;
							white-space: normal;
							line-height: 24px;
							text-overflow: ellipsis;
							// 单选框
							// 未选中样式
							.el-radio {
								//单选框
								.el-radio__inner {
									background: #fff;
									border-color: #999;
								}
							}
							//选中样式
							.is-checked {
								//单选框
								.el-radio__inner {
									background: var(--theme);
									border-color: var(--theme);
								}
							}
						}
					}
				}
				tr.el-table__row--striped {
					td {
						background: #FAFAFA !important;
					}
				}
				tr:hover {
					td {
						padding: 12px 0;
						color: #333;
						background: rgba(245, 245, 245, 1);
						border-color: #eee;
						border-width: 0 1px 1px 0;
						border-style: solid;
						text-align: left;
					}
				}
			}
		}
	}
	// 新增地址盒子
	.add_view {
		margin: 20px 0 0;
		width: 100%;
		// 新增地址按钮
		.addressAdd_btn {
			border: 0;
			cursor: pointer;
			border-radius: 0px;
			padding: 0 20px;
			margin: 0 10px 0 0;
			color: #fff;
			background: var(--theme);
			width: auto;
			font-size: 16px;
			height: 36px;
		}
		// 新增地址按钮-悬浮
		.addressAdd_btn:hover {
		}
	}
	// 分割线样式
	:deep(.el-divider) {
		border: none;
		background: #dcdfe6;
		// 分割线文字样式
		.el-divider__text {
			font-weight: 600;
			font-size: 18px;
		}
	}
	// 底部盒子
	.order_confirm_btn {
		padding: 0;
		margin: 20px 0;
		background: #fff;
		display: flex;
		width: 100%;
		justify-content: flex-end;
		align-items: center;
		box-sizing: border-box;
		// 总价
		.order_confirm_price {
			margin: 0 20px 0 0;
			color: #f00;
			font-weight: bold;
			font-size: 18px;
		}
		// 余额支付
		.pay_btn {
			border: 0;
			cursor: pointer;
			border-radius: 0px;
			padding: 0 24px;
			margin: 0 10px 0 0;
			color: #fff;
			background: var(--theme);
			width: auto;
			font-size: 14px;
			height: 36px;
		}
		// 余额支付-悬浮
		.pay_btn:hover {
		}
	}
</style>