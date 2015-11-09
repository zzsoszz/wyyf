package com.wyyf.action;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import javax.servlet.http.HttpServletResponse;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.LinkedCaseInsensitiveMap;
import org.springframework.web.bind.annotation.RequestMapping;

import com.lys.mvc.action.BaseAjaxAction;
import com.lys.utils.BeanUtils;
import com.lys.utils.BizTransUtil;
import com.lys.utils.CommonUtil;
import com.lys.utils.DateConverUtil;
import com.lys.utils.JsonUtils;
import com.lys.utils.StringUtils;
import com.lys.utils.myexception.MytwoException;
import com.lys.utils.pagination.PageBean;
import com.power.bean.Ae_t_sysuser;
import com.power.utils.PageColumn;
//import com.sjfx.bean.Bm_t_userMoneyLog;
//import com.sjfx.bean.Bh_t_orderform;
//import com.sjfx.bean.Bi_t_OrderProdRelate;
import com.wyyf.bean.Bh_t_orderform;
import com.wyyf.bean.Bi_t_OrderProdRelate;

/**
 * 订单管理
 * @author shuang
 *
 */
@Scope(value = "prototype")
@Controller("OrderFormAction")
@RequestMapping(value="/wyyf/orderForm")
public class OrderFormAction extends BaseAjaxAction{
	/**
	 * 进入订单添加/当前为主界面
	 * @return 订单添加主页面
	 */
	@RequestMapping(value="intoOrderSave")
	public String intoOrderSave(HttpServletResponse response,String id,Model model){
		model.addAttribute("textTitle", "添加");//页面标题
		model.addAttribute("infojsonData","{\"success\":false}");//表单数据
		List<Map<String, Object>> workExplist=new ArrayList<Map<String,Object>>();
		model.addAttribute("workExps", workExplist);//相关商品信息
		//查询所有商品的下拉搜索事件
		StringBuffer sb=new StringBuffer(" SELECT bg_st_name,bg_st_num,bg_st_bbid,bg_nm_storeNum,(select ab_st_name from ab_t_code where ab_st_mark =concat('PPLX_', bg_st_randid )  ) typename, bg_st_pricezg  price  FROM bg_t_prodinfo where 1=1 and bg_st_isdel='0' and bg_st_enable='1' and bg_nm_storeNum>0 "); 
		List<Map<String,Object>> circlenum = baseBiz.queryForList(sb.toString());
		model.addAttribute("allprodauto",JsonUtils.getJsonDataFromCollection(circlenum));
		return "wyyf/orderForm/orderFormAdd";
	}
	/**
	 * 管理员进入主界面 --管理员查看
	 * @return 主页面
	 */
	@RequestMapping(value="intoOrderFormList")
	public String intoOrderFormList(){
		return "wyyf/orderForm/orderFormList";
	}
	/**
	 * 管理员进入主界面 --商家查看
	 * @return 主页面
	 */
	@RequestMapping(value="intoOrderFormSjList")
	public String intoOrderFormSjList(){
		return "wyyf/orderForm/orderFormSjList";
	}
	/**
	 * 管理员查询订单列表
	 * @return
	 */
	@RequestMapping(value="orderFormList")
	public String orderFormList(Model model,String ISSHOWCOLUMS,Integer pageIndex,Integer pageSize,Bh_t_orderform orderForm){
		return commonList("",model, ISSHOWCOLUMS, pageIndex, pageSize, orderForm);
	}
	/**
	 * 商家查询订单列表
	 * @return
	 */
	@RequestMapping(value="orderFormSjList")
	public String orderFormSjList(Model model,String ISSHOWCOLUMS,Integer pageIndex,Integer pageSize,Bh_t_orderform orderForm){
		return commonList(getcuttSysuserID(),model, ISSHOWCOLUMS, pageIndex, pageSize, orderForm);
	}
	private String commonList(String bb_st_id,Model model, String ISSHOWCOLUMS,Integer pageIndex, Integer pageSize, Bh_t_orderform orderForm) {
		StringBuffer sb=new StringBuffer("select a.chargeId , a.bh_st_id,a.bh_st_ddnum,a.bh_st_spprice,a.bh_st_psry,a.bh_st_ddstate,a.bh_st_ddremark,a.bh_st_source,b.ae_st_name,b.ae_st_tell,date_format(a.bh_dt_addDate,'%Y-%m-%d %T') bh_dt_addDate,CONCAT(a.bh_st_del,a.bh_st_ddstate) state  ");
		StringBuffer sqlCount =new StringBuffer("  SELECT COUNT(a.bh_st_id) "); 
		StringBuffer sbcommon=new StringBuffer(" from bh_t_orderform a,ae_t_sysuser b where b.ae_st_id=a.bh_st_memberId");
		List<String> pramsList = new ArrayList<String>();
		//订单状态查询
		if(org.springframework.util.StringUtils.hasText(orderForm.getBh_st_ddstate())){
			sbcommon.append(" and a.bh_st_ddstate = ?");
			pramsList.add(orderForm.getBh_st_ddstate());
		}
		//条件编号
		if(org.springframework.util.StringUtils.hasText(orderForm.getBh_st_ddnum())){
			sbcommon.append(" and a.bh_st_ddnum like ?");
			pramsList.add( orderForm.getBh_st_ddnum());
		}
		//判断是否商家查看列表
		if(org.springframework.util.StringUtils.hasText(bb_st_id)){
			sbcommon.append(" and a.bh_st_bbid = ?");
			pramsList.add( bb_st_id);
		}
		sqlCount.append(sbcommon);
		sb.append(sbcommon).append(" order by bh_st_del,bh_dt_addDate desc ");
		//
		//分页数据、分页配置 查询
		PageBean pages=baseBiz.getPages(sb.toString(),sqlCount.toString(),pageIndex, pageSize,pramsList.toArray());
		//显示列列设置
		List<PageColumn> columnList=new ArrayList<PageColumn>();
		columnList.add(new PageColumn("序号", "bh_st_id").setIsnum(true).setWidth("30"));//复选框
		columnList.add(new PageColumn("订单号", "bh_st_ddnum"));
		columnList.add(new PageColumn("商品总价格", "bh_st_spprice"));
		columnList.add(new PageColumn("订单状态", "bh_st_ddstate").setCode("DDZT").setIsshowcode(true));
		columnList.add(new PageColumn("下单人", "ae_st_name"));
		columnList.add(new PageColumn("下单人手机", "ae_st_phone"));
		columnList.add(new PageColumn("下单时间", "bh_dt_addDate"));
		//columnList.add(new PageColumn("显示的", "chargeId"));  
		columnList.add(new PageColumn("隐藏的", "chargeId").setHiddle2(true));   
		//操作按钮
		//StringBuffer btns=new StringBuffer("<button data-toggle='modal' onclick='intoeditBtn(this);' class='btn btn-sm green' >去修改<i class='fa fa-pencil-square-o'></i></button> ");
		StringBuffer btns=new StringBuffer();
		btns.append("<button data-toggle='modal' onclick='showBtn(this);' class='btn btn-sm blue' >去修改<i class='fa fa-pencil-square-o'></i></button> ");
		btns.append("<button data-toggle='modal' onclick='delBtn(this);' class='btn btn-sm red' >删除<i class='fa fa-trash-o'></i></button> ");

		//StringBuffer btns2=new StringBuffer("<button data-toggle='modal' disabled='disabled' class='btn btn-sm gray' >去修改<i class='fa fa-pencil-square-o'></i></button> ");
		StringBuffer btns2=new StringBuffer();
		btns2.append("<button data-toggle='modal' onclick='showBtn(this);' class='btn btn-sm yellow' >去查看<i class='fa fa-pencil-square-o'></i></button> ");
		btns2.append("<button data-toggle='modal' disabled class='btn btn-sm gray' >删除<i class='fa fa-trash-o'></i></button> ");
		StringBuffer btns3=new StringBuffer();
		btns3.append("<button data-toggle='modal' onclick='tkdo(this);' class='btn btn-sm yellow' >确认退款<i class='fa fa-pencil-square-o'></i></button> ");
		btns3.append("<button data-toggle='modal' disabled class='btn btn-sm gray' >删除<i class='fa fa-trash-o'></i></button> ");

//		StringBuffer btns3=new StringBuffer();
//		btns3.append("<button data-toggle='modal' onclick='showBtn(this);' class='btn btn-sm yellow' >去查看<i class='fa fa-pencil-square-o'></i></button> ");
//		btns3.append("<button data-toggle='modal' onclick='delBtn(this);' class='btn btn-sm red' >删除<i class='fa fa-trash-o'></i></button> ");
		Map<String,String> when=new LinkedCaseInsensitiveMap<String>();
		when.put("01", btns.toString());//没有删除，待付款
		when.put("02", btns.toString());//没有删除，待配送
		when.put("03", btns.toString());//没有删除，配送中
		when.put("04", btns2.toString());//没有删除   交易完成
		//when.put("05", btns.toString());// 这里 自己 取舍
		when.put("06", btns.toString());// 这里 自己 取舍 案后 好
		when.put("07", btns2.toString());//已支付
		when.put("05", btns3.toString());//申请退款
		when.put("11", "<font color='red'>已删除</font>");
		when.put("12", "<font color='red'>已删除</font>");
		when.put("13", "<font color='red'>已删除</font>");
		when.put("14", "<font color='red'>已删除</font>");
		columnList.add(new PageColumn().setTitle("操作").setIsoperation(true).setIscasewhen(true).setCases("state").setWhen(when));
		//列显示/隐藏配置
		if(org.springframework.util.StringUtils.hasText(ISSHOWCOLUMS)){
			String[] ss=ISSHOWCOLUMS.split(",");
			Map<String, String> showcolumnMap=new HashMap<String, String>();
			for(String s:ss){
				showcolumnMap.put(s,"yes");
			}
			model.addAttribute("showcolumnMap", showcolumnMap);
		}
		model.addAttribute("pageBeanObj", pages);//分页对象
		model.addAttribute("columnList", columnList);//控制列设置的集合
		return "system/common/commonList";
	}
	/**
	 * 对订单信息操作 添加/修改--保存
	 * @param orderForm 信息对象
	 * @param response
	 * @param request
	 */
	@RequestMapping(value="orderFormSave")
	public void orderFormSave(Bh_t_orderform orderLs,Ae_t_sysuser member,HttpServletResponse response){
		String jsonData="";//返回的json数据
		try {
			String cuutid=getcuttSysuserID();//当前操作人ID
			Date nowDate=getcuttDate();//当前操作时间
			List<BizTransUtil> listobj=new ArrayList<BizTransUtil>();
			//更新
			if (org.springframework.util.StringUtils.hasText(orderLs.getBh_st_id())) {
				//构建数据--修改
				Map<String,Object>  map=new LinkedCaseInsensitiveMap<Object>();
				map.put("bh_st_id", orderLs.getBh_st_id());//ID
				map.put("bh_st_ddstate", orderLs.getBh_st_ddstate());//订单状态
				map.put("bh_st_source", orderLs.getBh_st_source());//订单来源1.app2.pc端
				map.put("bh_st_shname", orderLs.getBh_st_shname());//收货人姓名
				map.put("bh_st_shphone", orderLs.getBh_st_shphone());//收货人电话
				map.put("bh_st_shaddress", orderLs.getBh_st_shaddress());//收货人地址
				map.put("bh_st_remark", orderLs.getBh_st_remark());////备注，备用字段
				map.put("bh_st_updUserId", getcuttSysuserID());//修改人员ID 
				map.put("bh_dt_updDate", getcuttDate());//修改时间
				listobj.add(new BizTransUtil(map,Bh_t_orderform.class,CommonUtil.UPDATE));
			}
			 //新增
			else{ 
				//当前手机用户登录人ID==会员下单人帐号对应的ID 
				String userId ="";
				//如果下单人帐号为空，则不能下单
				String username=member.getUsername();
				if(!org.springframework.util.StringUtils.hasText(username)){
					throw new RuntimeException("下单人帐号为空，不能下单！");
				}else{
					List<Map<String, Object>> zhlist=baseBiz.queryForList("select ae_st_id  from ae_t_sysuser where username=? and ae_st_lockstate='1' ", new Object[]{username});
					if(zhlist==null||zhlist.size()<1){
						throw new RuntimeException("下单人帐号不正常，请重新输入！");
					}else{
						userId=StringUtils.toStringByObject(zhlist.get(0).get("ae_st_id"));//设置为当前下单人
					}
				}
				orderLs.setBh_st_ddstate("1");//订单状态  //1待付款 
				orderLs.setBh_st_del("0");//是否删除
				orderLs.setBh_st_source("2");//订单来源1.app2.pc端
				orderLs.setBh_st_memberId(userId);//会员ID
				orderLs.setBh_st_addUserId(cuutid);//创建人员ID  
				orderLs.setBh_dt_addDate(nowDate);//创建时间
				//各个订单中的商品算法
				if(orderLs.getProdList()!=null){
					//按照商品归属商家不同，分别创建不同订单。
					Map<String,Bh_t_orderform> map=new LinkedCaseInsensitiveMap<Bh_t_orderform>();
					for (int i = 0; i < orderLs.getProdList().size(); i++){
						//订单相关商品信息
						Bi_t_OrderProdRelate jsonObject=orderLs.getProdList().get(i);
						String spnum=StringUtils.toStringByObject(jsonObject.getBi_st_spnum());//订购的商品编号
						String spname=StringUtils.toStringByObject(jsonObject.getBi_st_remark());//订购的商品名字
						Integer spsl=StringUtils.toIntegerByObject(jsonObject.getBi_st_spsl());//订购的商品数量
						String sbbid=StringUtils.toStringByObject(jsonObject.getBi_st_bbid());//商品归属商家ID  ae
						//当前订单
						Bh_t_orderform bh=null;
						if(map.get(sbbid)==null){
							//订单信息
							bh=new Bh_t_orderform();
							BeanUtils.copyProperties(orderLs, bh, false);
							bh.setBh_st_id(StringUtils.getUUID32());//订单ID
							bh.setBh_st_ddnum("D"+nowDate.getTime()+(int)(Math.random()*90000+10000));//订单编号  D+YYYYMMDDHHSSMM00000
							bh.setBh_st_bbid(sbbid);//订单归属商家ID
						}else{
							bh=map.get(sbbid);
						}
						//如果没有商品编号或商品名字，则不处理该商品数据
						if(!org.springframework.util.StringUtils.hasText(spnum)||!org.springframework.util.StringUtils.hasText(spname)){
							continue;
						}
						//如果订购的商品数量小于1
						if(spsl<1){
							throw new MytwoException("商品【"+spname+"】的购买数量不能为0，请重新选择数量。");
						}
						//查询商品信息
						List<Map<String, Object>> splist=baseBiz.queryForList("select bg_st_name,bg_nm_storeNum,bg_st_fbtpe,date_format(bg_dt_startDate,'%Y-%m-%d %T') bg_dt_startDate,date_format(bg_dt_endDate,'%Y-%m-%d %T')  bg_dt_endDate, bg_st_pricezg,bg_st_pricetj,bg_st_enable,bg_st_isdel from bg_t_prodinfo where bg_st_num=?", new Object[]{spnum});
						if(splist!=null&&splist.size()>0){
							Double spprice=StringUtils.toDoubleByObject(splist.get(0).get("bg_st_pricezg"));//商品价格--默认为会员价格
							//删除验证
							if("1".equals(splist.get(0).get("bg_st_isdel"))){
								throw new MytwoException("商品【"+spname+"】已被删除，请重新选择！");
							}
							//下架验证
							if("0".equals(splist.get(0).get("bg_st_enable"))){
								throw new MytwoException("商品【"+spname+"】已下架，请重新选择！");
							}
							//抢购过期验证
							if("2".equals(splist.get(0).get("bg_st_fbtpe"))){
								Date startDate=DateConverUtil.getDbyST(StringUtils.toStringByObject(splist.get(0).get("bg_dt_startDate")));//商品生效时间
								Date endDate=DateConverUtil.getDbyST(StringUtils.toStringByObject(splist.get(0).get("bg_dt_endDate")));//商品结束时间
								//如果当前时间小于抢购开始时间
								if(startDate!=null&&nowDate.getTime()<startDate.getTime()){
									throw new MytwoException("商品【"+spname+"】抢购时间未到，请重新选择！");
								}
								//如果当前时间大于于抢购结束时间
								if(endDate!=null&&nowDate.getTime()>endDate.getTime()){
									throw new MytwoException("商品【"+spname+"】抢购时间已经结束，请重新选择！");
								}
								spprice=StringUtils.toDoubleByObject(splist.get(0).get("bg_st_pricetj"));//特价
							}
							//如果订购的商品数量大于 了库存量
							if(spsl>StringUtils.toIntegerByObject(splist.get(0).get("bg_nm_storeNum"))){
								throw new MytwoException("商品【"+spname+"】库存不足，请重新选择数量。");
							}
							//如果商品的抢购时间在当时，则按照抢购价格计算。
							
							//修改商品库存
							BizTransUtil bizTransUtil=new BizTransUtil("update bg_t_prodinfo set bg_nm_storeNum=bg_nm_storeNum-"+spsl+" where bg_nm_storeNum>=? and bg_st_num=? ", new Object[]{spsl,spnum}, CommonUtil.UPDATE);
							bizTransUtil.setIscase(true);
							bizTransUtil.setWhen(1);
							bizTransUtil.setErorrmsg("商品【"+spname+"】库存不足，请重新选择数量。");
							listobj.add(bizTransUtil);
							//计算商品总价
							bh.setBh_st_spprice(bh.getBh_st_spprice()+spprice*spsl);
							//构建订单中的商品信息
							Bi_t_OrderProdRelate orderProdRelate=new Bi_t_OrderProdRelate(true);
							orderProdRelate.setBi_st_ddnum(bh.getBh_st_ddnum());//订单编号
							orderProdRelate.setBi_st_spnum(spnum);//商品编号  
							orderProdRelate.setBi_st_bbid(sbbid);//订单商品归属商家ID
							orderProdRelate.setBi_st_spprice(spprice);//商品价格（下单时候的商品价格）
							orderProdRelate.setBi_st_spsl(spsl);//商品数量
							orderProdRelate.setBi_st_addUserId(cuutid);//创建人员ID  
							orderProdRelate.setBi_dt_addDate(nowDate);//创建时间
							listobj.add(new BizTransUtil(orderProdRelate));
						}else{
							throw new MytwoException("商品【"+spname+"】不存在！");
						}
						map.put(sbbid,bh);
					}
					//遍历 添加 多个订单到 保存对象
					for (Entry<String, Bh_t_orderform> entry : map.entrySet()) {
						listobj.add(new BizTransUtil( entry.getValue()));
					}
				}else{
					throw new RuntimeException("没有任何商品，不能下单！");
				}
			}
			//执行保存
			if(baseBiz.executesTRANS(listobj)){
				jsonData = "{\"success\":\"true\",\"msg\":\"下单成功\"}";
			}else{
				jsonData = "{\"success\":\"false\",\"flag\":\"4\",\"msg\":\"网络异常，请重试\"}";
			}
		}  catch (Exception e) {
			jsonData = "{success:false,msg:\"操作异常，"+e.getMessage()+"!\"}";
		}
		this.createAjax(response,jsonData);
		
	}
	/**
	 * 查询订单详细信息
	 * @param response
	 * @param roleid
	 */
	@RequestMapping(value="queryInfo")
	public String queryInfo(HttpServletResponse response,String id,Model model){
		String 	jsonData = "{success:false,msg:\"未获取到信息!\"}";
		if(org.springframework.util.StringUtils.hasText(id)){
			//基本信息
			StringBuffer sb=new StringBuffer("select a.bh_st_id,date_format(a.bh_dt_addDate,'%Y-%m-%d %T') bh_dt_addDate,a.bh_st_ddnum,a.bh_st_ddremark ");
			sb.append(",a.bh_st_ddstate,a.bh_st_memberId,(select username from ae_t_sysuser where ae_st_id=a.bh_st_memberId)  username,a.bh_st_remark,a.bh_st_shaddress ") 
			.append(",a.bh_st_spprice,a.bh_st_updUserId,a.bh_st_del,a.bh_st_source,a.bh_st_shname,a.bh_st_shphone from bh_t_orderform a ")
			.append(" where a.bh_st_id=? ");
			Map<String,Object> map=baseBiz.queryForMap(sb.toString(), new Object[]{id});
			jsonData = "{\"success\":true,result:["+JsonUtils.getJsonData(map)+"]}";
			//商品信息
			StringBuffer sbsp=new StringBuffer("select b.bg_st_id,b.bg_st_name,b.bg_st_img1,a.bi_st_spprice,a.bi_st_spsl "); 
			sbsp.append(" from bi_t_orderprodrelate a, bg_t_prodinfo b where 1=1 and a.bi_st_spnum=b.bg_st_num ");
			sbsp.append(" and a.bi_st_ddnum=? ");//订单ID
			sbsp.append(" order by b.bg_st_name ");
			List<Map<String, Object>> splist=baseBiz.queryForList(sbsp.toString(), new Object[]{map.get("bh_st_ddnum")});
			model.addAttribute("splist", splist);//相关商品信息
		}
		model.addAttribute("infojsonData", jsonData);//订单基本信息
		return "wyyf/orderForm/orderFormInfo";
	}

	/**
	 * 删除--修改订单状态
	 * @param response
	 * @param sysfunction
	 */
	@RequestMapping(value="orderFormdelete")
	public void orderFormdelete(HttpServletResponse response,String id){
		String jsonData="";
		try {
			if(org.springframework.util.StringUtils.hasText(id)){
				//创建批量保存事务对象
				List<BizTransUtil> list=new ArrayList<BizTransUtil>();
				list.add(new BizTransUtil("update bh_t_orderform set bh_st_del = '1',bh_st_updUserId=?,bh_dt_updDate=? where bh_st_id=?",new Object[]{getcuttSysuserID(),getcuttDate(),id},CommonUtil.UPDATE));
				if(baseBiz.executesTRANS(list)){
					jsonData = "{success:true,msg:\"删除成功!\"}";
				}else{
					jsonData = "{success:false,msg:\"删除失败!\"}";
				}
			}else{
				jsonData = "{success:false,msg:\"删除失败!\"}";
			}
		} catch (Exception e) {
			jsonData = "{success:false,msg:\"删除异常，"+e.getMessage()+"!\"}";
			e.printStackTrace();
		}
		this.createAjax(response, jsonData);
	}
	
}
