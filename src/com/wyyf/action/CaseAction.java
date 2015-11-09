package com.wyyf.action;

import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.imageio.ImageIO;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
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
import com.power.bean.Ag_t_file;
import com.power.utils.PageColumn;
import com.wyyf.bean.Bd_t_Case;

/**
 * 案例管理
 * @author shuang
 */
@Scope(value = "prototype")
@Controller("CaseAction")
@RequestMapping(value="/wyyf/case")
public class CaseAction extends BaseAjaxAction{
	/***
	 * 进入超级管理员案例列表界面
	 * @param req
	 * @return
	 */
	@RequestMapping(value="initAdminAD")
	public String initAdminAD(Model model){
		return "wyyf/case/initADList";
	}
	/***
	 * 查询案例列表——分页
	 *@param model
	 *@param ISSHOWCOLUMS
	 *@param pageIndex
	 *@param pageSize
	 *@param advertisement
	 *@return
	 */
	@RequestMapping(value="caseList")
	public String caseList( Bd_t_Case bd,String ISSHOWCOLUMS,Integer pageIndex,Integer pageSize){
		StringBuffer sb=new StringBuffer("select bd_st_id,bd_st_name,date_format(bd_dt_time,'%Y-%m-%d') bd_dt_time ,bd_nm_orderno,bd_st_isfb "); 
		StringBuffer sqlCount =new StringBuffer("select count(1) ");
		StringBuffer sbcommon=new StringBuffer(" from bd_t_case where 1=1 and bd_st_bbid=? ");
		List<String> pramsList = new ArrayList<String>();
		pramsList.add(getcuttSysuserID());
		//条件查询 --案例标题
		if (org.springframework.util.StringUtils.hasText(bd.getBd_st_name())) {
			sbcommon.append(" and bd_st_name like ?");
			pramsList.add("%" + bd.getBd_st_name() + "%");
		}
		//条件查询 --案例是否发布
		if (org.springframework.util.StringUtils.hasText(bd.getBd_st_isfb())) {
			sbcommon.append(" and bd_st_isfb= ?");
			pramsList.add(bd.getBd_st_isfb());
		}
		sb.append(sbcommon).append(" order by bd_st_isfb desc ,bd_nm_orderno asc,bd_dt_time desc");
		sqlCount.append(sbcommon);
		//分页数据、分页配置 查询
		PageBean pages=baseBiz.getPages(sb.toString(), sqlCount.toString(),pageIndex, pageSize,pramsList.toArray());
		//列设置
		List<PageColumn> columnList=new ArrayList<PageColumn>();
		columnList.add(new PageColumn("序号", "bd_st_id").setIsnum(true).setWidth("30"));//序号
		columnList.add(new PageColumn("案例名称", "bd_st_name"));
		columnList.add(new PageColumn("是否发布", "bd_st_isfb").setCode("YESNO").setIsshowcode(true));
		columnList.add(new PageColumn("序号", "bd_nm_orderno"));
		columnList.add(new PageColumn("发生时间", "bd_dt_time"));
		//操作按钮
		StringBuffer btns=new StringBuffer("<button data-toggle='modal' onclick='editAdvertisement(this);' class='btn btn-sm blue' ><i class='fa fa-pencil-square-o'></i>编辑</button> ");
		btns.append("<button data-toggle='modal' onclick='delAdvertisement(this);' class='btn btn-sm red' ><i class='fa fa-trash-o'></i>删除</button> ");
		columnList.add(new PageColumn("操作", btns.toString()).setIsoperation(true));
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
	 * 新增或更新案例
	 * @param response
	 * @param 
	 */
	@RequestMapping(value="caseAdd")
	public void caseAdd( Ag_t_file oldFile,Bd_t_Case advertisement,MultipartHttpServletRequest multipartRequest){
		String jsonData="";
		try {
			if(advertisement!=null){		
				String delfileurl="";
				//创建批量保存事务对象
				List<BizTransUtil> list=new ArrayList<BizTransUtil>();
				//更新
				if (org.springframework.util.StringUtils.hasText(advertisement.getBd_st_id())) {
					//构建数据--修改
					Map<String,Object>  map=new HashMap<String,Object>();
					map.put("bd_st_id", advertisement.getBd_st_id());//ID
					map.put("bd_st_name", advertisement.getBd_st_name());//标题
					map.put("bd_st_area", advertisement.getBd_st_area());//面积
					map.put("bd_dt_time", advertisement.getBd_dt_time());//相关时间
					map.put("bd_nm_orderno", advertisement.getBd_nm_orderno());//序号 
					map.put("bd_st_money", advertisement.getBd_st_money());//装修费用
					map.put("bd_st_isfb", advertisement.getBd_st_isfb());//是否发布
					map.put("bd_st_remark", advertisement.getBd_st_remark());//备注
//					map.put("bd_dt_updDate", getcuttDate());//修改时间
					map.put("bd_st_updUserId", getcuttSysuserID());//修改人员ID
					list.add(new BizTransUtil(map,Bd_t_Case.class,CommonUtil.UPDATE));
				}
				//新增
				else {
					//构建数据--新增
					advertisement.setBd_st_id(StringUtils.getUUID32());//ID 
					advertisement.setBd_dt_addDate(getcuttDate());//创建时间
					advertisement.setBd_st_addUserId(getcuttSysuserID());//创建人员ID
					advertisement.setBd_st_bbid(getcuttSysuserID());//案例归属师傅
					list.add(new BizTransUtil(advertisement));
				}
				//新增图片/删除图片
				MultipartFile mf = multipartRequest.getFile("myfile");
				if(mf!=null && mf.getSize()>0){
					//如果是修改，且文件表不为空，则删除相关文件表数据，和清除文件
					if (org.springframework.util.StringUtils.hasText(advertisement.getBd_st_id())){
						//list.add(new BizTransUtil(oldFile.getAg_st_id(), File.class));//删除本条数据
						list.add(new BizTransUtil("delete from ag_t_file where ag_st_objid=?", new Object[]{advertisement.getBd_st_id()},CommonUtil.DELETE));//删除本条数据
						delfileurl=oldFile.getAg_st_url();
					}
					Ag_t_file file=new Ag_t_file(true);
					//上传文件的相对路径
					String uploadPath=UploadFile.uploadInputStream(mf.getInputStream(), mf.getOriginalFilename(), "images/business/case");
					BufferedImage buff = ImageIO.read(mf.getInputStream());
					file.setAg_nm_height(buff.getHeight());//文件属性高
					file.setAg_nm_width(buff.getWidth());//文件属性宽
					file.setAg_nm_size(mf.getSize());//文件属性大小
					file.setAg_st_format(mf.getContentType());//文件格式
					file.setAg_dt_addDate(getcuttDate());//创建时间
					file.setAg_st_addUserId(getcuttSysuserID());//创建人员ID  
					file.setAg_st_objid(advertisement.getBd_st_id());//文件归属ID
					file.setAg_st_objtype("bd_t_case");//文件对应的对象
					file.setAg_st_type("1");//文件类型-图片
					file.setAg_st_mark("adTile");//自定义文件标识
					file.setAg_st_url(uploadPath);////文件 的存储地址
					list.add(new BizTransUtil(file));
				}
				//执行操作
				if (baseBiz.executesTRANS(list)) {
					if(org.springframework.util.StringUtils.hasText(delfileurl)){
						UploadFile.delpic(delfileurl);//删除原文件
					}
					jsonData = "{success:true,msg:\"操作成功!\"}";
				} else {
					jsonData = "{success:false,msg:\"操作异常!\"}";
				}
			}else{
				jsonData = "{success:false,msg:\"操作失败!\"}";
			}
		}  catch (Exception e) {
			jsonData = "{success:false,msg:\"操作异常，"+e.getMessage()+"!\"}";
			e.printStackTrace();
		}
		this.createAjax(response,jsonData);
	}
	/**
	 * 查询单个案例信息
	 * @param response
	 * @param roleid
	 */
	@RequestMapping(value="findCaseById")
	public void findCaseById(String caseId){
		if(org.springframework.util.StringUtils.hasText(caseId)){
			StringBuffer sb=new StringBuffer("select b.bd_st_id,b.bd_st_remark,b.bd_st_name, b.bd_st_area,date_format(b.bd_dt_time,'%Y-%m-%d') bd_dt_time,b.bd_nm_orderno,b.bd_st_money ,b.bd_st_isfb,b.bd_st_remark,a.ag_st_url,a.ag_st_id ")
			.append(" from bd_t_case b left JOIN  ag_t_file a on ")
			.append(" (b.bd_st_id = a.ag_st_objid and a.ag_st_objtype='bd_t_case' and a.ag_st_mark='adTile') ")
			.append(" where  b.bd_st_id=?  limit 0,1 ");
			Map<String, Object> map=baseBiz.queryForMap(sb.toString(), new Object[]{caseId});
			String jsonData = "{\"success\":true,result:["+JsonUtils.getJsonData(map)+"]}";
			this.createAjax(response, jsonData);
		}
	}

	/**
	 * 单个删除案例
	 * @param response
	 * @param sysfunction
	 */
	@RequestMapping(value="caseDelete")
	public void caseDelete( String caseId){
		String jsonData="";
		try {
			if(org.springframework.util.StringUtils.hasText(caseId)){
				String delfileurl="";
				List<BizTransUtil> list=new ArrayList<BizTransUtil>();
				//查询当前案例是否有 文件
				List<Map<String,Object>> filelist= baseBiz.queryForList("select a.ag_st_url,a.ag_st_id from  ag_t_file a  where a.ag_st_objid=? ", new Object[]{caseId});
				if(filelist!=null&&filelist.size()>0){
					list.add(new BizTransUtil(filelist.get(0).get("ag_st_id"), Ag_t_file.class));//删除本条数据
					delfileurl=StringUtils.toStringByObject(filelist.get(0).get("ag_st_url"));
				}
				list.add(new BizTransUtil(caseId, Bd_t_Case.class));//删除本条案例
				if(baseBiz.executesTRANS(list)){
					if(org.springframework.util.StringUtils.hasText(delfileurl)){
						UploadFile.delpic(delfileurl);//删除原文件
					}
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
