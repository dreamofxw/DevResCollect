package com.xwtiger.devrescollect.refresh.pullview;

public class GTConfig {
	public final static int G_SERVICE_THREAD_POOL_SIZE = 5;// 线程池大小
	public final static int G_PAGE_SIZE = 10;// 页面每次加载数据条数
	public final static String HISTORYKEY = "HISTORYKEY";
	// 9ea9b5bcc9170b926470938cfabfe9ba 旧的:64fed70fe03662d1096e675a3b24d880
	public final static String appkey_track = "9ea9b5bcc9170b926470938cfabfe9ba";// 热云appkey
	public final static String packagename_weixin = "com.tencent.mm";
	public final static String packagename_alipay = "com.eg.android.AlipayGphone";
	public final static String packagename_qq = "com.tencent.mobileqq";

	public final static String REDPACKET_USERFUL = "redpacket_userful";
	public final static String REDPACKET_OVERTIME = "redpacket_overtime";
	
	public final static String RANKINGLIST_RICHEST = "richest";
	public final static String RANKINGLIST_HOTTEST = "hottest";
	public final static String RANKINGLIST_WINNEREST = "winnerest";
	public final static String SECRETKEY = "09a9049e74cdf3135e84d8819e6b34e6";//秘钥
	

	public static class PayConstant {
		public static final String PAY_WEIXIN = "wx";
		public static final String PAY_ALIPAY = "alipay";
		public static final String PAY_YIBAO = "yeepay_wap";
		/**
		 * // 1：话费充值卡支付 // 2：游戏点卡支付 // 4：银联卡支付 // 403：微信支付 // 501：支付宝支付 //
		 * 502：财付通支付
		 */
		public static final String channel_weixin = "403";
		public static final String channel_alipay = "501";
		public static final String channel_caifutong = "502";
		public static final String channel_bank = "4";
	}

	public static class DBConstant {
		public static final int DATABASE_VERSION = 1;// 数据库版本号

	}

	public static class ShowOrderType {
		public final static int type_default = 1;
		public final static int type_mine = 2;
		public final static int type_shop = 3;
	}

	public static class RequestCodeConstant {

		public static final int REQUESTCODE_LOGIN = 1;
		public static final int REQUEST_CODE_PAYMENT = 2;
		public static final int REQUEST_CODE_GETADDRESS = 3;
		public static final int REQUEST_CODE_GETEMAIL = 4;
		public static final int REQUEST_CODE_MYORDER_ALL = 5;
		public static final int REQUEST_CODE_MYORDER_PAY = 6;
		public static final int REQUEST_CODE_MYORDER_RECEIVE = 7;
		public static final int REQUEST_CODE_MYORDERDITAIL = 8;
		public static final int REQUEST_CODE_MYORDER_VIEWDITAIL_ALL = 9;
		public static final int REQUEST_CODE_MYORDER_VIEWDITAIL_PAY = 10;
		public static final int REQUEST_CODE_MYORDER_VIEWDITAIL_REC = 11;
		public static final int REQUEST_CODE_MYORDER_VIEWDITAIL_FINISH = 12;
		public static final int REQUEST_CODE_ORDERLIST = 13;
		public static final int REQUEST_CODE_EXITLOGIN = 14;
		public static final int REQUEST_CODE_KEYWORDRESULT = 15;
		public static final int REQUEST_CODE_INFORCONFIRM = 16;
		public static final int REQUEST_CODE_INFORCONFIRM_ALL = 17;
		public static final int REQUEST_CODE_INFORCONFIRM_FINISH = 18;
		public static final int REQUEST_CODE_BINDMOBILE = 19;
		public static final int REQUEST_CODE_GOODTOLOGIN = 20;
		public static final int REQUEST_CODE_TOSHOWORDERDITAIL = 21;
		public static final int REQUEST_CODE_GOLDTOPUBLISH = 22;
		public static final int REQUEST_CODE_GOLDTOPUBLISH_JOINALL = 23;
		public static final int REQUEST_CODE_GOLDTOPUBLISH_JOINFINISH = 24;
		public static final int REQUEST_CODE_SHOWORDERTOLOGIN = 25;
		public static final int REQUEST_CODE_ORDERTOREDPACKET = 26;
		public static final int REQUEST_CODE_SELECTSHOPOFROOM = 27;
		public static final int REQUEST_CODE_CHANGOTHERACCOUNT = 28;

	}

	public static class IntentConstant {

		public static final String INTENT_WEBURL = "weburl";
		public static final String INTENT_TITLE = "webtitle";
		public static final String INTENT_SPLASH = "splash";

		public static final String KEY_PERIODID = "periodid";
		public static final String KEY_GOODSSTATE = "goodsstate";
		public static final String KEY_ORDERBEAN = "orderbean";
		public static final String KEY_ORDERNUM = "num";
		public static final String KEY_ORDERPRICE = "totalprice";
		public static final String KEY_PROGESSMAX = "goodsprogessmax";
		public static final String KEY_GOODSPROGESS = "goodsprogess";
		public static final String KEY_BACKHOMEPAGE = "backhomepage";
		public static final String KEY_SHOWORDERTYPE = "showordertype";
		public static final String KEY_SHOWORDERSID = "showordersid";
		public static final String KEY_USERRECORDFORWINNER = "userrecordforwinner";
		public static final String KEY_VIEWBALANCEFORPERSON = "viewbalanceforperson";
		public static final String KEY_RECEIVEGOLD = "receivegold";

	}

	public static class MyJoinRecorderConstant {
		public static final String JOIN_ALL = "join_all";// 全部参与记录
		public static final String JOIN_DOING = "join_doing";// 进行中的夺宝记录
		public static final String JOIN_FINISHED = "join_finished";// 已完成的夺宝记录
	}

	public static class UserRecorderConstant {

		public static final String JOINRECORD = "joinrecord";// 夺宝记录
		public static final String LUCKRECORD = "luckrecord";// 夺宝记录
		public static final String SHOWORDERRECORD = "showorderrecord";// 夺宝记录

		public static final String STATE_DOING = "1";// 进行中
		public static final String STATE_DONE = "2";// 已揭晓
		public static final String STATE_ALL = "3";// 全部

	}

	public static class LoginConstant {

		public static final int TYPE_MOBILE = 0;
		public static final int TYPE_WEIXIN = 1;
		public static final int TYPE_QQ = 2;
		public static final int TYPE_SINA = 3;
		public static final String PWD = "yiyuanmojin";

	}

	public static class MyOrderConstant {

		public static final String MYODER_ALL = "myoder_all";// 全部
		public static final String MYODER_PAYMENTING = "myoder_paymenting";// 待付款
		public static final String MYODER_RECEIVEING = "myoder_receiveing";// 待接收
		public static final String MYODER_EVALUATE = "myoder_evaluate";// 待评价
		public static final String CROWD_BEFORETIME = "预热中";//
		public static final String CROWD_INTTIME = "众筹中";//
		public static final String CROWD_SUCCESS = "众筹成功";//
		public static final String CROWD_FAIL = "失败";//

	}

	public static class UmEventConstant {

		// 首页-签到-点击
		public static String click_index_sign = "click_index_sign";//
		// 首页-晒单-点击
		public static String click_index_post = "click_index_post";//
		// 首页-摸金-点击
		public static String click_index_gold = "click_index_gold";//
		// 首页-最新揭晓-点击
		public static String click_index_announcement = "click_index_announcement";//
		// 首页-我的-点击
		public static String click_index_mine = "click_index_mine";//
		// 首页-轮播图-点击 参数：轮播图标题
		public static String click_index_banner = "click_index_banner";//
		// 首页-参与-点击
		public static String click_index_partake = "click_index_partake";//
		// 首页-商品-点击 参数：商品id
		public static String click_index_goods = "click_index_goods";//
		// 列表页-商品-点击 参数：商品id
		public static String click_list_goods = "click_list_goods";//
		// 首页-PV
		public static String pv_index = "pv_index";//
		// 最新揭晓-PV
		public static String pv_newest = "pv_newest";//
		// 最新揭晓-商品-点击 参数 商品id
		public static String click_newest_goods = "click_newest_goods";//
		// 详情页-PV
		public static String pv_detail = "pv_detail";//
		// 详情页-立即参与-点击
		public static String click_detail_partake = "click_detail_partake";//
		// 确认订单页-结算-点击
		public static String click_confirmOrder_balance = "click_confirmOrder_balance";//
		// 确认订单页-PV
		public static String pv_confirmOrder = "pv_confirmOrder";//
		// 支付页-确认支付-点击
		public static String click_payment_confirm = "click_payment_confirm";//
		// 支付页-PV
		public static String pv_payment = "pv_payment";//
		// 支付成功页-PV
		public static String pv_paySuccess = "pv_paySuccess";//
		// 支付失败页-PV
		public static String pv_payFail = "pv_payFail";//
		// 已揭晓商品详情页-PV
		public static String pv_pastDetail = "pv_pastDetail";//
		// 已揭晓商品详情页-立即参与-点击
		public static String click_pastDetail_partake = "click_pastDetail_partake";//
		// 已揭晓商品详情页-计算详细-点击
		public static String click_pastDetail_count = "click_pastDetail_count";//
		// 计算详情页-PV
		public static String pv_countDetail = "pv_countDetail";//
		// 我的-PV
		public static String pv_mine = "pv_mine";//
		// 我的-积分-点击
		public static String click_mine_integral = "click_mine_integral";//
		// 我的积分页-PV
		public static String pv_mineIntegral = "pv_mineIntegral";//
		// 我的-积分兑换-点击
		public static String click_mine_exchange = "click_mine_exchange";
		// 我的-充值-点击
		public static String click_mine_recharge = "click_mine_recharge";//
		// 积分兑换页-PV
		public static String pv_integral = "pv_integral";
		// 充值页-PV
		public static String pv_recharge = "pv_recharge";//
		// 充值成功页-PV
		public static String pv_rechargeSuccess = "pv_rechargeSuccess";//
		// 充值页-确认充值-点击
		public static String click_recharge_confirm = "click_recharge_confirm";//
		// 充值失败页-PV
		public static String pv_rechargeFail = "pv_rechargeFail";//
		// 登录页-PV
		public static String pv_login = "pv_login";//
		// 登录页-登录-点击
		public static String click_login_confirm = "click_login_confirm";//

	}

	public static class UIDesignConstant {

		/** UI设计的基准宽度. */
		public static int UI_WIDTH = 720;

		/** UI设计的基准高度. */
		public static int UI_HEIGHT = 1280;

		/** UI设计的密度. */
		public static int UI_DENSITY = 2;

	}

	public static class NetConstant {
		public final static String NETSTATE_CONNETED = "NETSTATE_CONNETED";
		public final static String NETSTATE_UNCONNETED = "NETSTATE_UNCONNETED";
		public static String NETSTATE = NETSTATE_CONNETED;
		public final static String PSWFORTHIRDPART = "123456";

	}

	public static class IdentityConstant {
		public final static String IDENTITY_USERNAME = "100";
		public final static String IDENTITY_PHONE = "101";
		public final static String IDENTITY_EMAIL = "102";
		public final static String IDENTITY_WECHAT = "201";
		public final static String IDENTITY_QQ = "202";
		public final static String IDENTITY_WEIBO = "203";
		public final static String IDENTITY_BAIDU = "204";
		public final static String IDENTITY_TAOBAO = "205";
		public final static String IDENTITY_163 = "206";
		public final static String IDENTITY_SOHU = "207";
		public final static String IDENTITY_GOOGLE = "301";
		public final static String IDENTITY_TWITTER = "302";
		public final static String IDENTITY_FACEBOOK = "303";
		public final static String IDENTITY_INSTAGRAM = "304";
	}

	public static class Base64EncodeConstant {
		// 逗号后面的空格不能去掉
		public final static String IMGHEAD = "data:image/png;base64, ";

	}

	public static class PayConfig {
		public static final String appid = "3006753434";
		public static final String privateKey = "MIICXgIBAAKBgQCOqWFTCsTglunf036QBof9EnKo4g6EaZ+IYa+u3jMf3p4YeHmOZjvE8jTxJ+tLvVfroRJNIRQX81uJKhTbtfkBxQjOiC8y9pyfQXz02WTl6DFIM9Kgjjx51bGnEKP8ploW2ieqKCDBzYCwkVEQfr7ayOPx0WWD0Cw3J6nb3EDUKQIDAQABAoGBAI440BzQdJuN99Q67Ua6LCIgnQw+aMia3/8/m7xSKleQQL4WhOBwjQ93g04TROC5/4eZiTw5SOXjp5Kj0C2FSZp5lmIggAg/KRgFhcdQ28jxmGzBD9+2cshTucYoa7YxtaeuljODT21nh7m5XF9HEngU7Ty8nnW8Byumpxa5bYCBAkEA1d3sSsjR7Y5wV6a9VgZsOKvExoCeUSjSaC6/KAZmJHExZB2Mb4yh+3LSV4Pi9xnz4umk/JFWRMJ7oQs7JsOVUQJBAKrEUf9Dbtm1qzvUy474e83N6+3iUsX8p5giRSahaOSsl7jCnnwqwF8nhh2I40XliH106nC4YV2c3f7cS8BEe1kCQQCY319OPapBgrWvEdL5MPIeuDmaIsoH/YQZUID3nUtZ9Ud25uBBxGbtFDBiujV8qCJ7KsPyffkKgXJZtWt81AVhAkEAnoiHvz0xKfiYMYGKQP66oQOtJjlYsumuBXS7UfPDV5hLeoFjdM6TrUMaJU0yAW/oWOAzzdW+vpOlHLgTszlgcQJAPSHsnZBSlKPJUHt3D7tGedgi6f8ayrbqxlsbvMQEFVzgUr0x1OV4ni+5oZOsP6jfoRLeWbiS8GMZlp2t326fgA==";
		public static final String publicKey = "MIGfMA0GCSqGSIb3DQEBAQUAA4GNADCBiQKBgQCNYGO0/Utz7nK1PsdRlCosJvf4Cb2+PEynr7W88ASf17cjKwRbXJY+UsbdLEJ4Bk5U11XEbYepwbC+6+SyoMBMAJ1mh5f/xjPDjgRA1TbXlaxTpXEmoPyjVLsxigpdER/RZELCZ8QACiPFYsY0Vcnp+kZ78dGXT6P1toXBFDzDQwIDAQAB";
		public static final String notifyurl = "http://192.168.0.140:8094/monizhuang/api?type=100";

	}

	public static class URLConstantForStaic {

		//静态页面的url
		public static String testUrlForPage = "http://test.1.busonline.com/";
		public static String testUrlOnlineForPage = "http://onlinetest.1.busonline.com/";
		public static String realUrlForPage = "https://1.busonline.com/";
		
//		public static String baseStaticUrl_test = "http://1.busonline.com/";
//		public static String baseStaticUrl_real = "http://1.busonline.com/";
		public static String baseStaticUrl = URLConstant.isDebug ? (URLConstant.isDebugOnline ?testUrlOnlineForPage: testUrlForPage): realUrlForPage;
		public static String baseappdir = URLConstant.isDebug ? "testapp" : "app";
		
		
		//恭喜页面分享的url http://1.busonline.com/h5web/v-u6Jrym-zh_CN-/yymj/h5web/winshare.w?
		public static String sharedCongratulationUrl = baseStaticUrl + "h5web/v-u6Jrym-zh_CN-/yymj/h5web/winshare.w?";
		
		//分享url http://1.busonline.com/pid/
		public static String sharedurl = baseStaticUrl +"pid/";
		
		

		// 卡密
		public static String URL_CARTPSW = baseStaticUrl + "Static/" + baseappdir + "/cards.html";// testapp

		// 积分
		public static String URL_Integral = baseStaticUrl + "Static/" + baseappdir + "/points.html";//// testapp

		// FAQ
		public static String URL_Problem = baseStaticUrl + "Static/" + baseappdir + "/faq.html";// testapp

		// 服务协议
		public static String URL_ServiceProtcel = baseStaticUrl + "Static/app/agreement.html";

		// 客服说明
		public static String URL_ServiceIntro = baseStaticUrl + "Static/" + baseappdir + "/service.html";// testapp

		// 三公 http://1.busonline.com/Static/app/fair/fair.html
		public static String URL_THREEPUBLIC = baseStaticUrl + "Static/app/fair/fair.html";

		// apk下载地址
		public static String URL_UPDATE_URL = "http://ks3-cn-beijing.ksyun.com/androidapk/";

		// http://1.busonline.com/Static/app/explain/index.html
		// pk玩法规则
		public static String URL_PKRULE = baseStaticUrl + "Static/app/explain/index.html";
		
		//全价购买说明
		public static String URL_TWOPULIC = baseStaticUrl+"Static/app/fair/fair_all.html";
		
		//邀请的url
		//http://onlinetest.1.busonline.com/h5web/v-u6Jrym-zh_CN-/yymj/h5web/pkhouse.w?
		//http://1.busonline.com/redirect.php?s=/url/pkshare/houseid/10134/uid/101987
		//http://onlinetest.1.busonline.com/h5/pkshare/index.html?houseid=10058&uid=102309
		//http://onlinetest.1.busonline.com/redirect.php?s=/url/pkshare/houseid/10134/uid/101987
		//http://onlinetest.1.busonline.com/redirect.php?s=/url/pkshare/houseid/10134/uid/101987
		public static String URL_INVITEFRIEND = baseStaticUrl+"redirect.php?s=/url/pkshare/";
		
	}

	public static class URLConstant {
		/**
		 * 发布正式改为false
		 */
		public static boolean isDebug = true;

		/**
		 * true 表示线上测试环境 false表示线下测试环境
		 */
		public static boolean isDebugOnline = true;

		public static String testUrl = "http://test.passport.busonline.com/wapapi.php?s=";
		public static String testUrlOnline = "https://onlinetest.passport.busonline.com/wapapi.php?s=";
		// public static String testUrl = "http://192.168.11.47/wapapi.php?s=";
		public static String realUrl = "https://passport.busonline.com/wapapi.php?s=";
		public static String BaseRequest = isDebug ? (isDebugOnline ? testUrlOnline : testUrl) : realUrl;

		
		public static String downurl = "http://1.busonline.com/Resources/App/com.busap.gameBao-v1.6-guanfang.apk";

		//public static String sharedurl = "http://1.busonline.com/pid/";
		
		//onlinetest.
		//public static String sharedCongratulationUrl = "http://1.busonline.com/h5web/v-u6Jrym-zh_CN-/yymj/h5web/winshare.w?";


		/**
		 * 轮播图 - 首页
		 */
		public static String URL_SLIDER = BaseRequest + "/public/slider/";

		/**
		 * 发送手机验证码
		 */
		public static String URL_SENDVALIDCODE = BaseRequest + "/MyUser/sendcode";
		
		/**
		 * 发送手机验证码
		 */
		public static String URL_SENDVALIDCODE_NEW = BaseRequest + "/User/smsSendCode";
		
		/**
		 * 用户注册
		 */
		public static String URL_REGISTER = BaseRequest + "/User/register";

		/**
		 * 用户登录
		 */
		public static String URL_LOGIN = BaseRequest + "/User/login";

		/**
		 * 重置密码
		 */
		public static String URL_RESTPWD = BaseRequest + "/MyUser/resetPwd";

		/**
		 * 获取用户信息
		 */
		public static String URL_GETUSERINFO = BaseRequest + "/User/userinfo";

		/**
		 * 添加地址
		 */
		public static String URL_ADD_ADDRESS = BaseRequest + "/MyUser/addressEdit";

		/**
		 * 用户地址列表
		 */
		public static String URL_ADDRESS_LIST = BaseRequest + "/MyUser/addressList";

		/**
		 * 设置默认地址
		 */
		public static String URL_SET_DEFAULT_ADDRESS = BaseRequest + "/MyUser/addressDefault";

		/**
		 * 删除地址
		 */
		public static String URL_DELETE_ADDRESS = BaseRequest + "/MyUser/addressDel";

		/**
		 * 编辑地址
		 */
		public static String URL_Edit_ADDRESS = BaseRequest + "/MyUser/addressEdit";

		/**
		 * 更新签名
		 */
		public static String URL_UPDATE_SIGNATURE = BaseRequest + "/MyUser/updateSignature";

		/**
		 * 绑定邮箱
		 */
		public static String URL_BIND_EMAIL = BaseRequest + "/MyUser/bindEmail";

		/**
		 * 会员上传图片
		 */
		public static String URL_USERUPLOADPICTURE = BaseRequest + "/MyUser/userUploadPicture";

		/**
		 * 修改或添加用户名
		 */
		public static String URL_UPDATE_USERNAME = BaseRequest + "/MyUser/updateUserName";

		/**
		 * 我的夺宝记录
		 */
		public static String URL_RECORDS = BaseRequest + "/MyUser/getRecords";
		/**
		 * 我的夺宝记录合并pk /wapapi.php?s=/Usertest/getRecordsNew
		 */
		public static String URL_RECORDS_NEW = BaseRequest + "/Usertest/getRecordsNew";

		/**
		 * 晒单详情
		 */
		public static String URL_SHAREDETAIL = BaseRequest + "/MyUser/getShareDetail";
		
		/**
		 * 合并pk的晒单详情  /wapapi.php?s=/MyUser/getShareDetailNew
		 */
		public static String URL_SHAREDETAIL_NEW = BaseRequest + "/MyUser/getShareDetailNew";

		/**
		 * 我的中奖记录
		 */
		public static String URL_LOTTERY = BaseRequest + "/MyUser/getLottery";
		
		/**
		 * 中奖记录合并pk/wapapi.php?s=/Usertest/getLotteryNew
		 */
		public static String URL_LOTTERY_NEW = BaseRequest + "/Usertest/getLotteryNew";

		/**
		 * 我的晒单列表
		 */
		public static String URL_SHARED_LIST = BaseRequest + "/MyUser/getShared";
		
		/**
		 * 合并pk的我的晒单列表   /wapapi.php?s=/MyUser/getSharedNew
		 */
		public static String URL_SHARED_LIST_NEW = BaseRequest + "/MyUser/getSharedNew";

		/**
		 * 签到
		 */
		public static String URL_USER_SIGN = BaseRequest + "/User/checkin";

		/**
		 * 用户晒单
		 */
		public static String URL_USER_SHARED = BaseRequest + "/User/shared";

		/**
		 * 用户点赞
		 */
		public static String URL_USER_UP = BaseRequest + "/User/userUp";

		/**
		 * 个人积分
		 */
		public static String URL_USER_POINTS = BaseRequest + "/Point/getPoints";

		/**
		 * 修改密码
		 */
		public static String URL_UPDATE_PWD = BaseRequest + "/MyUser/updatePwd";

		/**
		 * 意见反馈
		 */
		public static String URL_FEEDBACK = BaseRequest + "/public/addfeedback/";

		/**
		 * Ping支付功能
		 */
		public static String URL_PAYORDER = BaseRequest + "/api/order/payOrder";

		/**
		 * 最新、往期揭晓列表
		 */
		public static String URL_LOTTERYSTATE = BaseRequest + "/shop/announced/";

		/**
		 * 推荐、分类商品列表
		 */
		public static String URL_RECOMMEND = BaseRequest + "/shop/recommend/";

		/**
		 * 分类
		 */
		public static String URL_CATEGORY = BaseRequest + "/shop/category/";

		/**
		 * 商品详情 -未揭晓
		 */
		public static String URL_DETAIL_NOLETTRY = BaseRequest + "/shop/detail/";

		/**
		 * 商品详情 -已揭晓
		 */
		public static String URL_DETAIL_LETTRY = BaseRequest + "/shop/overdetail/";

		/**
		 * 分类商品获取
		 */
		public static String URL_PERIOD = BaseRequest + "/shop/period/";

		/**
		 * 生成订单
		 */
		public static String URL_GENERATEORDER = BaseRequest + "/Pay/generateorder";/// Ipay/generateorder

		public static String URL_GENERATEORDER_PING = BaseRequest + "/Pay/generateorder";/// Ping/generateorder

		/**
		 * 支付订单
		 */
		public static String URL_PINGPAY = BaseRequest + "/Ipay/pay";
		public static String URL_PINGPAY_PING = BaseRequest + "/Ping/pay";
		public static String URL_PINGPAY_SHENG = BaseRequest + "/Shengpay/pay";
		/**
		 * 充值
		 */
		public static String URL_CHARGE = BaseRequest + "/Ipay/recharge";
		public static String URL_CHARGE_PING = BaseRequest + "/Ping/recharge";
		public static String URL_CHARGE_SHENG = BaseRequest + "/Shengpay/recharge";

		/**
		 * 商品详情页--用户参与记录
		 */
		public static String URL_GOODSRECORDLIST = BaseRequest + "/shop/recordlist/";

		/**
		 * 确认收货地址
		 */
		public static String URL_ORDERADDRESS = BaseRequest + "/Shop/addOrderAddress";

		/**
		 * 更新订单状态
		 */
		public static String URL_UPDATESTATUS = BaseRequest + "/Shop/updateOrderStatus";

		/**
		 * 重置密码获取验证码
		 */
		public static String URL_CHECKUSERPHONE = BaseRequest + "/MyUser/checkUserPhone";

		/**
		 * 重置密码验证码校验
		 */
		public static String URL_CHECKPHONECODE = BaseRequest + "/MyUser/checkPhoneCode";

		/**
		 * 商品晒单列表 /wapapi.php?s=/shop/sharedlist/
		 */
		public static String URL_SHOPSHAREDLIST = BaseRequest + "/shop/sharedlist";
		
		/**
		 * 合并pk的晒单列表 /wapapi.php?s=/shop/sharedlistNew/
		 */
		public static String URL_SHOPSHAREDLIST_NEW = BaseRequest + "/shop/sharedlistNew/";

		/**
		 * 晒单列表
		 */
		public static String URL_GETSHARELIST = BaseRequest + "/MyUser/getShareList";
		/**
		 * 用户多图晒单 /wapapi.php?s=/User/shared
		 */
		public static String URL_SHAREDFORMOREPIC = BaseRequest + "/User/shared";

		/**
		 * 商品往期揭晓列表
		 */
		public static String URL_GETHISTORY = BaseRequest + "/shop/history/";

		/**
		 * 商品最后50条记录
		 */
		public static String URL_LASTPURCHASERECORDS = BaseRequest + "/shop/lastPurchaseRecords/";

		/**
		 * 获取订单信息 /wapapi.php?s=/Ping/orderDetails
		 */
		public static String URL_ORDERDETAILS = BaseRequest + "/Pay/orderDetails";/// Ipay/orderDetails
		public static String URL_ORDERDETAILS_PING = BaseRequest + "/Pay/orderDetails";/// Ping/orderDetails

		/**
		 * 退出登录
		 */
		public static String URL_EXITLOGIN = BaseRequest + "/User/exitLogin";

		/**
		 * 消息列表
		 */
		public static String URL_INFORMATION = BaseRequest + "/Message/getList";

		/**
		 * 消息已读
		 */
		public static String URL_MESSAGEREAD = BaseRequest + "/Message/read";

		/**
		 * 充值规则
		 */
		public static String URL_RECHARGERULE = BaseRequest + "/Recharge/rule/";

		/**
		 * 金币明细
		 */
		public static String URL_GOLDDETAIL = BaseRequest + "/Gold/detail/";

		/**
		 * 金币兑换
		 */
		public static String URL_GOLDEXCHANGE = BaseRequest + "/Gold/addGoldInfo/";

		/**
		 * 发送卡密
		 */
		public static String URL_SENDCARDPWS = BaseRequest + "/MyUser/sendCardSN";

		/**
		 * 上传图片
		 */
		public static String URL_PUTIMAGES = BaseRequest + "/User/luckyshow";

		/**
		 * 删除消息
		 */
		public static String URL_DELETEMES = BaseRequest + "/Message/deleteUserMessage";

		/**
		 * 签到list
		 */
		public static String URL_QIANDAOLIST = BaseRequest + "/User/checkinInfo";

		/**
		 * 游客快速登录 /wapapi.php?s=/User/newlogin
		 */
		public static String URL_QULICKLOGIN = BaseRequest + "/User/newlogin";

		/**
		 * 关联手机号 /wapapi.php?s=/User/bindingPhone
		 */
		public static String URL_BINDMOBILE = BaseRequest + "/User/bindingPhone";
		/**
		 * /wapapi.php?s=/shop/verificationcode/ 输入验证码接口(分类商品用到)
		 */
		public static String URL_VERIFICATIONCODE = BaseRequest + "/shop/verificationcode/";

		/**
		 * /wapapi.php?s=/RedEnvelope/getRedEnvelopeByUid/ 红包列表
		 */
		public static String URL_GETREDENVELOPEBYUID = BaseRequest + "/RedEnvelope/getRedEnvelopeByUid/";

		/**
		 * /wapapi.php?s=/Usertest/pk/ pk首页列表
		 */
		public static String URL_PKMAINPAGE = BaseRequest + "/Usertest/pk/";

		/**
		 * 获取pk场次 /wapapi.php?s=/Usertest/numberList/
		 */
		public static String URL_PKNUMBERLIST = BaseRequest + "/Usertest/numberList/";

		/**
		 * 进入pk房间详情 /wapapi.php?s=/Usertest/pkinfo/
		 */
		public static String URL_PKINFO = BaseRequest + "/Usertest/pkinfo/";

		/**
		 * 选择pk商品 /wapapi.php?s=/Usertest/selectPk/
		 */
		public static String URL_SELECTPK = BaseRequest + "/Usertest/selectPk/";

		/**
		 * 创建私人房间 并且开启新的一期 /wapapi.php?s=/Usertest/createPrivacyPk
		 */
		public static String URL_CREATEPRIVACYPK = BaseRequest + "/Usertest/createPrivacyPk";

		/**
		 * 公开房间点击再次参与 /wapapi.php?s=/Usertest/pkAgain
		 */
		public static String URL_PKAGAIN = BaseRequest + "/Usertest/pkAgain";

		/**
		 * 私密房间再次参与 /wapapi.php?s=/Usertest/privacyPkAgain
		 */
		public static String URL_PRIVACYPKAGAIN = BaseRequest + "/Usertest/privacyPkAgain";

		/**
		 * 设置regid /User/setRegId
		 */
		public static String URL_SETREGID = BaseRequest + "/User/setRegId";
		
		/**
		 * 启动页广告
		 */
		public static String URL_GETDEFULTDIAGRAM = BaseRequest + "/public/getDefaultDiagram/";
		
		/**
		 * 达人榜 
		 */
		public static String URL_RANKINGLIST = BaseRequest + "/top/toplist/";
		
		/**
		 * 进入pk房间 /wapapi.php?s=/Usertest/entryPKRoom/
		 */
		public static String URL_ENTRYPKROOM = BaseRequest + "/Usertest/entryPKRoom/";
		
		/**
		 * 获取配置信息 /wapapi.php?s=/public/getConfig/   
		 */
		public static String URL_GETCONFIG = BaseRequest + "/public/getConfig/";
		
		/**
		 * 绑定渠道号   /wapapi.php?s=/Channel/bindUser4spread
		 */
		public static String URL_BINDCHANNEL = BaseRequest + "/Channel/bindUser4spread";
		
	}

}
