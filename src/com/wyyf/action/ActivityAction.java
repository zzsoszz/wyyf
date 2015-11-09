package com.wyyf.action;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.LinkedCaseInsensitiveMap;
import org.springframework.web.bind.annotation.RequestMapping;

import com.lys.mvc.action.BaseAjaxAction;
import com.lys.utils.BizTransUtil;
import com.lys.utils.CommonUtil;
import com.lys.utils.JsonUtils;
import com.lys.utils.StringUtils;
import com.lys.utils.myexception.MytwoException;
import com.lys.utils.pagination.PageBean;
import com.power.utils.PageColumn;
import com.wyyf.bean.Bo_t_Activity;
import com.wyyf.bean.Bp_t_ActivityProdRelate;

/**
 * 活动管理管理
 * @author shuang
 *
 */
@Scope(value = "prototype")
@Controller("ActivityAction")
@RequestMapping(value="/wyyf/activity")
public class ActivityAction extends BaseAjaxAction{
	/**
	 * 进入活动添加/当前为主界面
	 * @return 活动添加主页面
	 */
	@RequestMapping(value="intoOrderSave")
	public String intoOrderSave(String id){ 
		model.addAttribute("textTitle", "添加");//页面标题
		model.addAttribute("infojsonData","{\"success\":false}");//表单数据
		List<Map<String, Object>> workExplist=new ArrayList<Map<String,Object>>();
		model.addAttribute("workExps", workExplist);//相关商品信息
		//查询所有商品的下拉搜索事件
		StringBuffer sb=new StringBuffer(" SELECT bg_st_name,bg_st_num,bg_st_bbid,bg_nm_storeNum,(select ab_st_name from ab_t_code where ab_st_mark =concat('PPLX_', bg_st_randid )  ) typename, bg_st_pricezg  price  FROM bg_t_prodinfo where 1=1 and bg_st_isdel='0' and bg_st_enable='1' and bg_nm_storeNum>0 "); 
		List<Map<String,Object>> circlenum = baseBiz.queryForList(sb.toString());
		model.addAttribute("allprodauto",JsonUtils.getJsonDataFromCollection(circlenum));
		return "wyyf/activity/activityAdd";
	}
	/**
	 * 对活动信息操作 添加/修改--保存
	 * @param orderForm 信息对象
	 * @param response
	 * @param request
	 */
	@RequestMapping(value="activitySave")
	public void activitySave(Bo_t_Activity orderLs){
		String jsonData="";//返回的json数据
		try {
			String cuutid=getcuttSysuserID();//当前操作人ID
			Date nowDate=getcuttDate();//当前操作时间
			List<BizTransUtil> listobj=new ArrayList<BizTransUtil>();
			if(orderLs.getBo_dt_startDate()!=null&&orderLs.getBo_dt_endDate()!=null){
				//如果开始时间大于结束时间
				if(orderLs.getBo_dt_startDate().getTime()>orderLs.getBo_dt_endDate().getTime()){
					throw new RuntimeException("开始时间不能大于结束时间!");
				}
			}
			//更新
			if (org.springframework.util.StringUtils.hasText(orderLs.getBo_st_id())) {
				//构建数据--修改
				Map<String,Object>  map=new LinkedCaseInsensitiveMap<Object>();
				map.put("bo_st_id", orderLs.getBo_st_id());//ID
				map.put("bo_st_title", orderLs.getBo_st_title());//活动标题
				map.put("bo_st_ddremark", orderLs.getBo_st_ddremark());//活动主题 (客户建议)
				map.put("bo_st_ddstate", orderLs.getBo_st_ddstate());//活动是否发布 // 1=是 0=否
				map.put("bo_st_remark", orderLs.getBo_st_remark());////备注，备用字段
				map.put("bo_dt_startDate", orderLs.getBo_dt_startDate());////活动生效时间
				map.put("bo_dt_endDate", orderLs.getBo_dt_endDate());////活动结束时间
				map.put("bo_st_updUserId", getcuttSysuserID());//修改人员ID 
				map.put("bo_dt_updDate", getcuttDate());//修改时间
				listobj.add(new BizTransUtil(map,Bo_t_Activity.class,CommonUtil.UPDATE));
			}
			 //新增
			else{ 
				orderLs.setBo_st_id(StringUtils.getUUID32());//活动ID
				orderLs.setBo_st_ddnum("A"+nowDate.getTime()+(int)(Math.random()*90000+10000));//活动编号  D+YYYYMMDDHHSSMM00000
				orderLs.setBo_st_del("0");//是否删除
				orderLs.setBo_st_addUserId(cuutid);//创建人员ID  
				orderLs.setBo_dt_addDate(nowDate);//创建时间
				orderLs.setBo_dt_updDate(nowDate);//更新时间
				//各个活动中的商品算法
				if(orderLs.getProdList()!=null){
					double zjiage=0.0;
					for (int i = 0; i < orderLs.getProdList().size(); i++){
						//活动相关商品信息
						Bp_t_ActivityProdRelate jsonObject=orderLs.getProdList().get(i);
						String spnum= jsonObject.getBp_st_spnum();//订购的商品编号
						String spname= jsonObject.getBp_st_remark();//订购的商品名字
						Integer spsl= jsonObject.getBp_st_spsl();//订购的商品数量
						Double spprice= jsonObject.getBp_st_spprice() ;//活动商品中的价格
						//如果没有商品编号或商品名字，则不处理该商品数据
						if(!org.springframework.util.StringUtils.hasText(spnum)||!org.springframework.util.StringUtils.hasText(spname)){
							throw new MytwoException("商品【"+spname+"】的信息不正确。");
						}
						//如果订购的商品数量小于1
						if(spsl<1){
							throw new MytwoException("商品【"+spname+"】的购买数量不能为0，请重新选择数量。");
						}
						//查询商品信息
						List<Map<String, Object>> splist=baseBiz.queryForList("select bg_st_name,bg_nm_storeNum,bg_st_fbtpe  ,bg_st_enable,bg_st_isdel from bg_t_prodinfo where bg_st_num=?", new Object[]{spnum});
						if(splist!=null&&splist.size()>0){
							//删除验证
							if("1".equals(splist.get(0).get("bg_st_isdel"))){
								throw new MytwoException("商品【"+spname+"】已被删除，请重新选择！");
							}
							//下架验证
							if("0".equals(splist.get(0).get("bg_st_enable"))){
								throw new MytwoException("商品【"+spname+"】已下架，请重新选择！");
							}
							//如果订购的商品数量大于 了库存量
							if(spsl>StringUtils.toIntegerByObject(splist.get(0).get("bg_nm_storeNum"))){
								throw new MytwoException("商品【"+spname+"】库存不足，请重新选择数量。");
							}
							//修改商品库存
							BizTransUtil bizTransUtil=new BizTransUtil("update bg_t_prodinfo set bg_nm_storeNum=bg_nm_storeNum-"+spsl+" where bg_nm_storeNum>=? and bg_st_num=? ", new Object[]{spsl,spnum}, CommonUtil.UPDATE);
							bizTransUtil.setIscase(true);
							bizTransUtil.setWhen(1);
							bizTransUtil.setErorrmsg("商品【"+spname+"】库存不足，请重新选择数量。");
							listobj.add(bizTransUtil);
							//计算商品总价
							zjiage=zjiage+spprice*spsl;
							//构建活动中的商品信息
							Bp_t_ActivityProdRelate orderProdRelate=new Bp_t_ActivityProdRelate(true);
							orderProdRelate.setBp_st_ddnum(orderLs.getBo_st_ddnum());//活动编号
							orderProdRelate.setBp_st_spnum(spnum);//商品编号  
							orderProdRelate.setBp_st_spprice(spprice);//商品价格（设置活动的时候的价格）
							orderProdRelate.setBp_st_spsl(spsl);//商品数量
							orderProdRelate.setBp_st_addUserId(cuutid);//创建人员ID  
							orderProdRelate.setBp_dt_addDate(nowDate);//创建时间
							listobj.add(new BizTransUtil(orderProdRelate));
						}else{
							throw new MytwoException("商品【"+spname+"】不存在！");
						}
					}
					orderLs.setBo_st_spprice(zjiage);
					listobj.add(new BizTransUtil(orderLs));
				}else{
					throw new RuntimeException("没有任何商品，不能发起活动！");
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
	 * 进入活动列表
	 */
	@RequestMapping(value="intoActivityList")
	public String intoActivityList(){
		return "wyyf/activity/activityList";
	}
	/**
	 * 活动列表--查询数据
	 * @return
	 */
	@RequestMapping(value="activityList")
	public String activityList(Model model,String ISSHOWCOLUMS,Integer pageIndex,Integer pageSize,Bo_t_Activity orderForm){
		StringBuffer sb=new StringBuffer("select a.bo_st_id,CONCAT(a.bo_st_del,a.bo_st_ddstate) state,a.bo_st_title,a.bo_st_ddnum,a.bo_st_ddstate,a.bo_st_spprice,a.bo_st_del,date_format(a.bo_dt_updDate ,'%Y-%m-%d') bo_dt_updDate,date_format(a.bo_dt_startDate ,'%Y-%m-%d %T') bo_dt_startDate,date_format(a.bo_dt_endDate,'%Y-%m-%d %T') bo_dt_endDate ");
		StringBuffer sqlCount =new StringBuffer("  SELECT COUNT(a.bo_st_id) "); 
		StringBuffer sbcommon=new StringBuffer(" from bo_t_activity a where 1=1 ");
		List<String> pramsList = new ArrayList<String>();
		//活动状态查询
		if(org.springframework.util.StringUtils.hasText(orderForm.getBo_st_ddstate())){
			sbcommon.append(" and a.bo_st_ddstate = ?");
			pramsList.add(orderForm.getBo_st_ddstate());
		}
		//条件编号
		if(org.springframework.util.StringUtils.hasText(orderForm.getBo_st_ddnum())){
			sbcommon.append(" and a.bo_st_ddnum like ?");
			pramsList.add( orderForm.getBo_st_ddnum());
		}
		sqlCount.append(sbcommon);
		sb.append(sbcommon).append(" order by bo_st_del,bo_dt_addDate desc ");
		//
		//分页数据、分页配置 查询
		PageBean pages=baseBiz.getPages(sb.toString(),sqlCount.toString(),pageIndex, pageSize,pramsList.toArray());
		//显示列列设置
		List<PageColumn> columnList=new ArrayList<PageColumn>();
		columnList.add(new PageColumn("序号", "bo_st_id").setIsnum(true).setWidth("30"));//复选框
		columnList.add(new PageColumn("活动号", "bo_st_ddnum"));
		columnList.add(new PageColumn("商品总价格", "bo_st_spprice"));
		columnList.add(new PageColumn("活动状态", "bo_st_ddstate").setCode("YESNO").setIsshowcode(true));
		columnList.add(new PageColumn("活动开始时间", "bo_dt_startDate"));
		columnList.add(new PageColumn("活动结束时间", "bo_dt_endDate"));
		columnList.add(new PageColumn("活动更新时间", "bo_dt_updDate"));
		//操作按钮
		//StringBuffer btns=new StringBuffer("<button data-toggle='modal' onclick='intoeditBtn(this);' class='btn btn-sm green' >去修改<i class='fa fa-pencil-square-o'></i></button> ");
		StringBuffer btns=new StringBuffer();
		btns.append("<button data-toggle='modal' onclick='editBtn(this);' class='btn btn-sm blue' >去修改<i class='fa fa-pencil-square-o'></i></button> ");
		btns.append("<button data-toggle='modal' onclick='delBtn(this);' class='btn btn-sm red' >删除<i class='fa fa-trash-o'></i></button> ");

		//StringBuffer btns2=new StringBuffer("<button data-toggle='modal' disabled='disabled' class='btn btn-sm gray' >去修改<i class='fa fa-pencil-square-o'></i></button> ");
		StringBuffer btns2=new StringBuffer();
		btns2.append("<button data-toggle='modal' onclick='showBtn(this);' class='btn btn-sm yellow' >去查看<i class='fa fa-pencil-square-o'></i></button> ");
		btns2.append("<button data-toggle='modal' disabled class='btn btn-sm gray' >删除<i class='fa fa-trash-o'></i></button> ");
//		StringBuffer btns3=new StringBuffer();
//		btns3.append("<button data-toggle='modal' onclick='showBtn(this);' class='btn btn-sm yellow' >去查看<i class='fa fa-pencil-square-o'></i></button> ");
//		btns3.append("<button data-toggle='modal' onclick='delBtn(this);' class='btn btn-sm red' >删除<i class='fa fa-trash-o'></i></button> ");
		Map<String,String> when=new LinkedCaseInsensitiveMap<String>();
		when.put("00", btns.toString());//没有删除， 
		when.put("01", btns.toString());//没有删除， 
		when.put("10", btns2.toString());// 删除 
		when.put("11", btns2.toString());// 删除 
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
	 * 查询活动详细信息
	 * @param response
	 * @param roleid
	 */
	@RequestMapping(value="queryInfo")
	public String queryInfo(HttpServletResponse response,String id,Model model){
		String 	jsonData = "{success:false,msg:\"未获取到信息!\"}";
		if(org.springframework.util.StringUtils.hasText(id)){
			//基本信息
			StringBuffer sb=new StringBuffer("select a.bo_st_id,a.bo_st_title,a.bo_st_ddremark,a.bo_st_ddnum,a.bo_st_ddstate,a.bo_st_spprice,date_format(a.bo_dt_startDate ,'%Y-%m-%d %T') bo_dt_startDate,date_format(a.bo_dt_endDate,'%Y-%m-%d %T') bo_dt_endDate,a.bo_st_remark  ") 
			.append(" from bo_t_activity a where a.bo_st_id=? ");
			Map<String,Object> map=baseBiz.queryForMap(sb.toString(), new Object[]{id});
			jsonData = "{\"success\":true,result:["+JsonUtils.getJsonData(map)+"]}";
			//商品信息
			StringBuffer sbsp=new StringBuffer("select b.bg_st_id,b.bg_st_name,b.bg_st_img1,a.bp_st_spprice,a.bp_st_spsl  from bp_t_activityprodrelate a, bg_t_prodinfo b where 1=1 and a.bp_st_spnum=b.bg_st_num  ");
			sbsp.append("  and a.bp_st_ddnum=?  order by b.bg_st_name  ");
			List<Map<String, Object>> splist=baseBiz.queryForList(sbsp.toString(), new Object[]{map.get("bo_st_ddnum")});
			model.addAttribute("splist", splist);//相关商品信息
		}
		model.addAttribute("infojsonData", jsonData);//活动基本信息
		return "wyyf/activity/activityInfo";
	}

	/**
	 * 删除--修改活动状态
	 * @param response
	 * @param sysfunction
	 */
	@RequestMapping(value="activitydelete")
	public void activitydelete(HttpServletResponse response,String id){
		String jsonData="";
		try {
			if(org.springframework.util.StringUtils.hasText(id)){
				//创建批量保存事务对象
				List<BizTransUtil> list=new ArrayList<BizTransUtil>();
				list.add(new BizTransUtil("update bo_t_orderform set bo_st_del = '1',bo_st_updUserId=?,bo_dt_updDate=? where bo_st_id=?",new Object[]{getcuttSysuserID(),getcuttDate(),id},CommonUtil.UPDATE));
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
