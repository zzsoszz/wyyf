package com.wyyf.action;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.LinkedCaseInsensitiveMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import com.lys.mvc.action.BaseAjaxAction;
import com.lys.utils.BizTransUtil;
import com.lys.utils.CommonUtil;
import com.lys.utils.JsonUtils;
import com.lys.utils.StringUtils;
import com.lys.utils.UploadFile;
import com.lys.utils.pagination.PageBean;
import com.power.utils.PageColumn;
import com.wyyf.bean.Bg_t_prodinfo;

/**
 * 商品管理
 * @author shuang
 *
 */
@Scope(value = "prototype")
@Controller("ProdInfoAction")
@RequestMapping(value="/wyyf/prodInfo")
public class ProdInfoAction extends BaseAjaxAction{
	
	/**
	 * 进入商品添加/当前为主界面
	 * @return 商品添加主页面
	 */
	@RequestMapping(value="intoProdInfoInto")
	public String intoProdInfoInto(HttpServletResponse response,String id,Model model){
		//判断是否为修改
		String uid=getcuttSysuserID();
		Map<String,Object> map1=baseBiz.queryForMap("SELECT * FROM ae_t_sysuser WHERE ae_st_id='"+uid+"'");
		model.addAttribute("ae_st_nickName", map1.get("ae_st_nickName"));
		
		if(org.springframework.util.StringUtils.hasText(id)){
			model.addAttribute("textTitle", "修改");//页面标题
			//基本信息
			StringBuffer sb=new StringBuffer(" select bg_st_randid, ae_st_nickName , pp, cd, cz, xh , bg_st_id,bg_nm_storeNum,bg_st_summary,bg_st_fbtpe,date_format(bg_dt_endDate,'%Y-%m-%d %T') bg_dt_endDate ,date_format(bg_dt_startDate,'%Y-%m-%d %T') bg_dt_startDate,  ");
			sb.append(" bg_st_enable, bg_st_name, bg_st_num,bg_st_pricetj, ")
			.append(" bg_st_pricezg,bg_st_prodIntro,bg_st_randid,bg_st_img1,bg_st_img2,bg_st_img3,bg_st_img4,bg_st_img5,bg_st_img6 ")
			.append(" from bg_t_prodinfo  ,  ae_t_sysuser where bg_st_bbid=ae_st_id and bg_st_id=? ");
			Map<String,Object> map=baseBiz.queryForMap(sb.toString(), new Object[]{id});
			String jsonData = "{\"success\":true,result:["+JsonUtils.getJsonData(map)+"]}";
			model.addAttribute("updatejsonData",jsonData);//表单数据
			model.addAttribute("type",map.get("bg_st_randid"));
		}else{
			model.addAttribute("textTitle", "添加");//页面标题
			model.addAttribute("updatejsonData","{\"success\":false}");//表单数据
		}
		return "wyyf/prodInfo/intoProdInfoInto";
	}
	/**
	 * 进入商品查询主界面--管理员页面
	 */
	@RequestMapping(value="intoprodInfoList")
	public String intoprodInfoList(){
		return "wyyf/prodInfo/prodInfoList";
	}
	/**
	 * 进入商品查询主界面--商家页面
	 */
	@RequestMapping(value="intoprodInfoSjList")
	public String intoprodInfoSjList(){
		return "wyyf/prodInfo/prodInfoSjList";
	}
	/**
	 * 商品查询--管理员查询列表
	 * @return
	 */
	@RequestMapping(value="prodInfoList")
	public String prodInfoList(Model model,String ISSHOWCOLUMS,Integer pageIndex,Integer pageSize,Bg_t_prodinfo prodInfo){
		return commonList("",model, ISSHOWCOLUMS, pageIndex, pageSize, prodInfo);
	}
	
	/**
	 * 商品查询--商家查询列表
	 */
	@RequestMapping(value="prodInfoSjList")
	public String prodInfoSjList(Model model,String ISSHOWCOLUMS,Integer pageIndex,Integer pageSize,Bg_t_prodinfo prodInfo){
		return commonList(getcuttSysuserID(),model, ISSHOWCOLUMS, pageIndex, pageSize, prodInfo);
	}
	
	/**
	 * 商品查询公共列表
	 */
	private String commonList(String bb_st_id, Model model, String ISSHOWCOLUMS,Integer pageIndex, Integer pageSize, Bg_t_prodinfo prodInfo) {
		StringBuffer sb=new StringBuffer(" SELECT bg_st_id,bg_st_name,bg_st_num,bg_st_pricezg,bg_st_pricetj,bg_nm_storeNum,bg_st_randid,bg_st_enable,bg_st_isdel  ");
		StringBuffer sqlCount =new StringBuffer("  SELECT COUNT(bg_st_id) "); 
		StringBuffer sbcommon=new StringBuffer(" FROM bg_t_prodinfo where 1=1 ");
		List<String> pramsList = new ArrayList<String>();
		//条件查询名称
		if(org.springframework.util.StringUtils.hasText(prodInfo.getBg_st_name())){
			sbcommon.append(" and bg_st_name like ?");
			pramsList.add("%" + prodInfo.getBg_st_name() +"%");
		}
		//条件编号
		if(org.springframework.util.StringUtils.hasText(prodInfo.getBg_st_num())){
			sbcommon.append(" and bg_st_num = ?");
			pramsList.add( prodInfo.getBg_st_num());
		}
		//=商品类型
		if(org.springframework.util.StringUtils.hasText(prodInfo.getBg_st_randid())){
			sbcommon.append(" and bg_st_randid = ?  ");
			pramsList.add(prodInfo.getBg_st_randid());
		}
		//商家id
		if(org.springframework.util.StringUtils.hasText(bb_st_id)){
			sbcommon.append(" and bg_st_bbid = ?  ");
			pramsList.add(bb_st_id);
		}
		sqlCount.append(sbcommon);
		sb.append(sbcommon).append(" order by bg_st_isdel,bg_st_num desc ");
		//
		//分页数据、分页配置 查询
		PageBean pages=baseBiz.getPages(sb.toString(),sqlCount.toString(),pageIndex, pageSize,pramsList.toArray());
		//显示列列设置
		List<PageColumn> columnList=new ArrayList<PageColumn>();
		columnList.add(new PageColumn("序号", "bg_st_id").setIsnum(true).setWidth("30"));//复选框
		columnList.add(new PageColumn("商品号", "bg_st_num"));
		columnList.add(new PageColumn("名称", "bg_st_name").setLength(12));
		columnList.add(new PageColumn("品牌", "bg_st_randid").setCode("PPLX").setIsshowcode(true));
		columnList.add(new PageColumn("市场价格", "bg_st_pricezg"));
		columnList.add(new PageColumn("库存数量", "bg_nm_storeNum"));
		columnList.add(new PageColumn("是否发布", "bg_st_enable").setCode("YESNO").setIsshowcode(true));//
		columnList.add(new PageColumn("是否删除", "bg_st_isdel").setCode("YESNO").setIsshowcode(true));//
		
		//操作按钮
		StringBuffer btns=new StringBuffer("<button data-toggle='modal' onclick='editBtn(this);' class='btn btn-sm blue' >编辑<i class='fa fa-pencil-square-o'></i></button> ");
		btns.append("<button data-toggle='modal' onclick='delBtn(this);' class='btn btn-sm red' >删除<i class='fa fa-trash-o'></i></button> ");
		Map<String,String> when=new LinkedCaseInsensitiveMap<String>();
		when.put("0", btns.toString());
		when.put("1", "<font color='red'>已删除</font>");
		columnList.add(new PageColumn().setTitle("操作").setIsoperation(true).setIscasewhen(true).setCases("bg_st_isdel").setWhen(when));
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
	 * 对商品信息操作 添加/修改
	 */
	@RequestMapping(value="prodInfoSave")
	public void prodInfoSave(Bg_t_prodinfo prodInfo,HttpServletResponse response,MultipartHttpServletRequest multipartRequest){
		String jsonData="";//返回的json数据
		String bg_st_img1="";//图片1
		String bg_st_img2="";//图片2
		String bg_st_img3="";//图片3
		String bg_st_img4="";//图片4
		String bg_st_img5="";//图片5
		String bg_st_img6="";//图片6
		try {
			if(prodInfo!=null){
				/*多图上传**/
				bg_st_img1=uploadto(multipartRequest.getFile("myfile1"));
				bg_st_img2=uploadto(multipartRequest.getFile("myfile2"));
				bg_st_img3=uploadto(multipartRequest.getFile("myfile3"));
				bg_st_img4=uploadto(multipartRequest.getFile("myfile4"));
				bg_st_img5=uploadto(multipartRequest.getFile("myfile5"));
				bg_st_img6=uploadto(multipartRequest.getFile("myfile6"));
				//创建批量保存事务对象
				List<BizTransUtil> list=new ArrayList<BizTransUtil>();
				//更新
				if (org.springframework.util.StringUtils.hasText(prodInfo.getBg_st_id())) {
					//构建数据--修改
					Map<String,Object>  map=new LinkedCaseInsensitiveMap<Object>();
					map.put("bg_st_id", prodInfo.getBg_st_id());//ID
					map.put("bg_st_name", prodInfo.getBg_st_name());//商品名
					map.put("bg_st_pricezg", prodInfo.getBg_st_pricezg());//市场价格
					map.put("bg_nm_storeNum", prodInfo.getBg_nm_storeNum());//库存数量
					map.put("bg_st_randid", prodInfo.getBg_st_randid());//品牌ID（茶叶、酒品、米、油、土特产）
					map.put("bg_st_pricetj", prodInfo.getBg_st_pricetj());//活动价格
					map.put("bg_st_enable", prodInfo.getBg_st_enable());//是否发布 1、发布2下架
					map.put("bg_st_fbtpe", prodInfo.getBg_st_fbtpe());///发布类型1.商城2.限时抢购 
					map.put("bg_st_summary", prodInfo.getBg_st_summary());//商品摘要
					map.put("bg_st_prodIntro", prodInfo.getBg_st_prodIntro());//商品介绍
					map.put("bg_st_updUserId", getcuttSysuserID());//修改人员ID 
					
					map.put("bg_dt_updDate", getcuttDate());//修改时间
					
					map.put("pp", prodInfo.getPp());
					map.put("xh", prodInfo.getXh());
					map.put("cd", prodInfo.getCd());
					map.put("cz", prodInfo.getCz());
					
					
					map.put("bg_st_img1", prodInfo.getBg_st_img1());//图片url
					map.put("bg_st_img2", prodInfo.getBg_st_img2());//图片url
					map.put("bg_st_img3", prodInfo.getBg_st_img3());//图片url
					map.put("bg_st_img4", prodInfo.getBg_st_img4());//图片url
					map.put("bg_st_img5", prodInfo.getBg_st_img5());//图片url
					map.put("bg_st_img6", prodInfo.getBg_st_img6());//图片url
					/**图片处理*/
					if(org.springframework.util.StringUtils.hasText(bg_st_img1)){
						map.put("bg_st_img1", bg_st_img1);//图片url
						UploadFile.delpic(prodInfo.getBg_st_img1());//修改图片，删除原图片文件
					}
					if(org.springframework.util.StringUtils.hasText(bg_st_img2)){
						map.put("bg_st_img2", bg_st_img2);//图片url
						UploadFile.delpic(prodInfo.getBg_st_img2());//修改图片，删除原图片文件
					}
					if(org.springframework.util.StringUtils.hasText(bg_st_img3)){
						map.put("bg_st_img3", bg_st_img3);//图片url
						UploadFile.delpic(prodInfo.getBg_st_img3());//修改图片，删除原图片文件
					}
					if(org.springframework.util.StringUtils.hasText(bg_st_img4)){
						map.put("bg_st_img4", bg_st_img4);//图片url
						UploadFile.delpic(prodInfo.getBg_st_img4());//修改图片，删除原图片文件
					}
					if(org.springframework.util.StringUtils.hasText(bg_st_img5)){
						map.put("bg_st_img5", bg_st_img5);//图片url
						UploadFile.delpic(prodInfo.getBg_st_img5());//修改图片，删除原图片文件
					}
					if(org.springframework.util.StringUtils.hasText(bg_st_img6)){
						map.put("bg_st_img6", bg_st_img6);//图片url
						UploadFile.delpic(prodInfo.getBg_st_img6());//修改图片，删除原图片文件
					}
					list.add(new BizTransUtil(map,Bg_t_prodinfo.class,CommonUtil.UPDATE));
				}else{  //新增
					prodInfo.setBg_st_id(StringUtils.getUUID32());//ID
					prodInfo.setBg_dt_addDate(getcuttDate());//添加时间
					prodInfo.setBg_st_addUserId(getcuttSysuserID());//添加人
					prodInfo.setBg_st_isdel("0");//是否删除
					prodInfo.setBg_st_bbid(getcuttSysuserID());//商品归属的商家
					prodInfo.setBg_st_num(getcuttDate().getTime()+""+(int)(Math.random()*9000+1000));//商品编号
					/**图片处理*/
					prodInfo.setBg_st_img1(bg_st_img1);
					prodInfo.setBg_st_img2(bg_st_img2);
					prodInfo.setBg_st_img3(bg_st_img3);
					prodInfo.setBg_st_img4(bg_st_img4);
					prodInfo.setBg_st_img5(bg_st_img5);
					prodInfo.setBg_st_img6(bg_st_img6);
					list.add(new BizTransUtil(prodInfo));
				}
				//执行操作
				if (baseBiz.executesTRANS(list)) {
					jsonData = "{success:true,msg:\"保存成功!\"}";
				} else {
					jsonData = "{success:false,msg:\"操作异常!\"}";
				}
			}else{
				jsonData = "{success:false,msg:\"保存失败!\"}";
			}
		}  catch (Exception e) {
			UploadFile.delpic(bg_st_img1);//删除上传图片文件
			UploadFile.delpic(bg_st_img2);//删除上传图片文件
			UploadFile.delpic(bg_st_img3);//删除上传图片文件
			UploadFile.delpic(bg_st_img4);//删除上传图片文件
			UploadFile.delpic(bg_st_img5);//删除上传图片文件
			UploadFile.delpic(bg_st_img6);//删除上传图片文件
			jsonData = "{success:false,msg:\"操作异常，"+e.getMessage()+"!\"}";
			e.printStackTrace();
		}
		this.createAjax(response,jsonData);
		
	}
	/**
	 * 多图上传调用地址
	 */
	public String uploadto(MultipartFile mf) throws Exception{
		if(mf!=null && mf.getSize()>0){
			try {
				return UploadFile.uploadInputStream(mf.getInputStream(), mf.getOriginalFilename(), "images/business/prodimg");
			} catch (IOException e) {
				throw new IOException("图片上传失败！");
			}
		}else {
			return "";
		}
	}
	/**
	 * 设置抢购时间
	 * @param prodInfo 信息对象
	 * @param response
	 * @param request
	 */
	@RequestMapping(value="setqgTime")
	public void setqgTime(Bg_t_prodinfo prodInfo,HttpServletResponse response){
		String jsonData="";//返回的json数据
		try {
			if(prodInfo!=null){
				if(prodInfo.getBg_dt_startDate()==null||prodInfo.getBg_dt_endDate()==null){
					throw new RuntimeException("时间不能为空！");
				}
				//创建批量保存事务对象
				List<BizTransUtil> list=new ArrayList<BizTransUtil>();
				//更新
				list.add(new BizTransUtil("update bg_t_prodinfo set bg_dt_startDate=?,bg_dt_endDate=? where bg_st_fbtpe='2' and bg_st_bbid=?", new Object[]{prodInfo.getBg_dt_startDate(),prodInfo.getBg_dt_endDate(),getcuttSysuserID()}, CommonUtil.UPDATE));
				//执行操作
				if (baseBiz.executesTRANS(list)) {
					jsonData = "{success:true,msg:\"设置成功!\"}";
				} else {
					jsonData = "{success:false,msg:\"操作异常!\"}";
				}
			}else{
				jsonData = "{success:false,msg:\"设置失败!\"}";
			}
		}  catch (Exception e) {
			jsonData = "{success:false,msg:\"操作异常，"+e.getMessage()+"!\"}";
			e.printStackTrace();
		}
		this.createAjax(response,jsonData);
		
	}
	/**无用
	 * 查询商品详细信息--无用
	 * @param response
	 * @param roleid
	 */
	@RequestMapping(value="queryInfo")
	public void queryInfo(HttpServletResponse response,String id,Model model){
		if(org.springframework.util.StringUtils.hasText(id)){
			//基本信息
			StringBuffer sb=new StringBuffer(" select bg_st_id,bg_nm_storeNum,bg_st_summary,bg_st_fbtpe, ");
			sb.append(" bg_st_enable, bg_st_name, bg_st_num,bg_st_pricetj, ")
			.append(" bg_st_pricezg,bg_st_prodIntro,bg_st_randid,bg_st_img1,bg_st_img2,bg_st_img3,bg_st_img4,bg_st_img5,bg_st_img6  ")
			.append(" from bg_t_prodinfo where bg_st_id=? ");
			Map<String,Object> map=baseBiz.queryForMap(sb.toString(), new Object[]{id});
			String jsonData = "{\"success\":true,result:["+JsonUtils.getJsonData(map)+"]}";
			this.createAjax(response, jsonData);
		}
	}
	/**
	 * 删除--修改商品状态
	 * @param response
	 * @param sysfunction
	 */
	@RequestMapping(value="prodInfodelete")
	public void prodInfodelete(HttpServletResponse response,String id){
		String jsonData="";
		try {
			if(org.springframework.util.StringUtils.hasText(id)){
				//创建批量保存事务对象
				List<BizTransUtil> list=new ArrayList<BizTransUtil>();
				list.add(new BizTransUtil("update bg_t_prodinfo set  bg_st_enable = '0',bg_st_isdel = '1',bg_st_updUserId=?,bg_dt_updDate=? where bg_st_id=?"
						,new Object[]{getcuttSysuserID(),getcuttDate(),id},CommonUtil.UPDATE));
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
