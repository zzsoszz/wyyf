package com.wyyf.action;

import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletResponse;

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
import com.wyyf.bean.Bj_t_advertisement;

/**
 * 门户设置管理
 * @author shuang
 */
@Scope(value = "prototype")
@Controller("AdvertisementAction")
@RequestMapping(value="/wyyf/advertisement")
public class AdvertisementAction extends BaseAjaxAction{
	/***
	 * 进入超级管理员广告列表界面
	 * @param req
	 * @return
	 */
	@RequestMapping(value="initAdminAD")
	public String initAdminAD(Model model){
		return "wyyf/advertisement/initADList";
	}
	/***
	 * 查询广告列表——分页
	 *@param model
	 *@param ISSHOWCOLUMS
	 *@param pageIndex
	 *@param pageSize
	 *@param advertisement
	 *@return
	 */
	@RequestMapping(value="advertisementList")
	public String advertisementList(Model model,Bj_t_advertisement advertisement,String ISSHOWCOLUMS,Integer pageIndex,Integer pageSize){
		StringBuffer sb=new StringBuffer("select bj_st_id, date_format(bj_dt_addDate,'%Y-%m-%d %T') bj_dt_addDate,  date_format(bj_dt_updDate,'%Y-%m-%d %T') bj_dt_updDate,bj_st_enable, bj_st_title,bj_st_type, bj_nm_orderno "); 
		StringBuffer sqlCount =new StringBuffer("select count(1) ");
		StringBuffer sbcommon=new StringBuffer(" from bj_t_advertisement where 1=1 ");
		List<String> pramsList = new ArrayList<String>();
		//条件查询 --广告内容
		if (org.springframework.util.StringUtils.hasText(advertisement.getBj_st_title())) {
			sbcommon.append(" and bj_st_title like ?");
			pramsList.add("%" + advertisement.getBj_st_title() + "%");
		}
		//条件查询 --广告类型
		if (org.springframework.util.StringUtils.hasText(advertisement.getBj_st_type())) {
			sbcommon.append(" and bj_st_type= ?");
			pramsList.add(advertisement.getBj_st_type());
		}
		sb.append(sbcommon).append(" order by bj_st_type,bj_st_enable desc,bj_nm_orderno asc , bj_dt_updDate desc");
		sqlCount.append(sbcommon);
		//分页数据、分页配置 查询
		PageBean pages=baseBiz.getPages(sb.toString(), sqlCount.toString(),pageIndex, pageSize,pramsList.toArray());
		//列设置
		List<PageColumn> columnList=new ArrayList<PageColumn>();
		columnList.add(new PageColumn("序号", "bj_st_id").setIsnum(true).setWidth("30"));//序号
		columnList.add(new PageColumn("类型", "bj_st_type").setCode("ADVERTADTYPE").setIsshowcode(true));
		columnList.add(new PageColumn("是否启用", "bj_st_enable").setCode("YESNO").setIsshowcode(true));
		columnList.add(new PageColumn("顺序", "bj_nm_orderno"));
		columnList.add(new PageColumn("内容", "bj_st_title").setLength(15));
		columnList.add(new PageColumn("修改时间", "bj_dt_updDate"));
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
	 * 新增或更新广告
	 * @param response
	 * @param 
	 */
	@RequestMapping(value="sysAdvertisementAdd")
	public void sysroleAdd(HttpServletResponse response,Ag_t_file oldFile,Bj_t_advertisement advertisement,MultipartHttpServletRequest multipartRequest){
		String jsonData="";
		try {
			if(advertisement!=null){		
				String delfileurl="";
				//创建批量保存事务对象
				List<BizTransUtil> list=new ArrayList<BizTransUtil>();
				//更新
				if (org.springframework.util.StringUtils.hasText(advertisement.getBj_st_id())) {
					//构建数据--修改
					Map<String,Object>  map=new HashMap<String,Object>();
					map.put("bj_st_id", advertisement.getBj_st_id());//ID
					map.put("bj_st_title", advertisement.getBj_st_title());//内容
					map.put("bj_st_clickurl", advertisement.getBj_st_clickurl());//点击链接
					map.put("bj_st_remark", advertisement.getBj_st_remark());//备注
					map.put("bj_nm_orderno", advertisement.getBj_nm_orderno());//序号 
					map.put("bj_st_enable", advertisement.getBj_st_enable());//是否启用 
					map.put("bj_st_type", advertisement.getBj_st_type());//广告类型
					map.put("bj_dt_updDate", getcuttDate());//修改时间
					map.put("bj_st_updUserId", getcuttSysuserID());//修改人员ID
					list.add(new BizTransUtil(map,Bj_t_advertisement.class,CommonUtil.UPDATE));
				}
				//新增
				else {
					//构建数据--新增
					advertisement.setBj_st_id(StringUtils.getUUID32());//ID 
					advertisement.setBj_dt_addDate(getcuttDate());//创建时间
					advertisement.setBj_st_addUserId(getcuttSysuserID());//创建人员ID
					list.add(new BizTransUtil(advertisement));
				}
				//新增图片/删除图片
				MultipartFile mf = multipartRequest.getFile("myfile");
				if(mf!=null && mf.getSize()>0){
					//如果是修改，且文件表不为空，则删除相关文件表数据，和清除文件
					if (org.springframework.util.StringUtils.hasText(advertisement.getBj_st_id())){
						//list.add(new BizTransUtil(oldFile.getAg_st_id(), File.class));//删除本条数据
						list.add(new BizTransUtil("delete from ag_t_file where ag_st_objid=?", new Object[]{advertisement.getBj_st_id()},CommonUtil.DELETE));//删除本条数据
						delfileurl=oldFile.getAg_st_url();
					}
					Ag_t_file file=new Ag_t_file(true);
					//上传文件的相对路径
					String uploadPath=UploadFile.uploadInputStream(mf.getInputStream(), mf.getOriginalFilename(), "images/business/ad");
					BufferedImage buff = ImageIO.read(mf.getInputStream());
					file.setAg_nm_height(buff.getHeight());//文件属性高
					file.setAg_nm_width(buff.getWidth());//文件属性宽
					file.setAg_nm_size(mf.getSize());//文件属性大小
					file.setAg_st_format(mf.getContentType());//文件格式
					file.setAg_dt_addDate(getcuttDate());//创建时间
					file.setAg_st_addUserId(getcuttSysuserID());//创建人员ID  
					file.setAg_st_objid(advertisement.getBj_st_id());//文件归属ID
					file.setAg_st_objtype("bj_t_advertisement");//文件对应的对象
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
	 * 查询单个广告信息
	 * @param response
	 * @param roleid
	 */
	@RequestMapping(value="findAdvertisementById")
	public void findAdvertisementById(HttpServletResponse response,String advertisementId){
		if(org.springframework.util.StringUtils.hasText(advertisementId)){
			StringBuffer sb=new StringBuffer("select b.bj_st_id,b.bj_st_clickurl, b.bj_st_enable,b.bj_st_remark,b.bj_st_title,b.bj_st_type ,b.bj_nm_orderno,a.ag_st_url,a.ag_st_id ")
			.append(" from bj_t_advertisement b left JOIN  ag_t_file a on ")
			.append(" (b.bj_st_id = a.ag_st_objid and a.ag_st_objtype='bj_t_advertisement' and a.ag_st_mark='adTile') ")
			.append(" where  b.bj_st_id=?  limit 0,1 ");
			Map<String, Object> map=baseBiz.queryForMap(sb.toString(), new Object[]{advertisementId});
			String jsonData = "{\"success\":true,result:["+JsonUtils.getJsonData(map)+"]}";
			this.createAjax(response, jsonData);
		}
	}

	/**
	 * 单个删除广告
	 * @param response
	 * @param sysfunction
	 */
	@RequestMapping(value="advertisementDelete")
	public void advertisementDelete(HttpServletResponse response,String advertisementId){
		String jsonData="";
		try {
			if(org.springframework.util.StringUtils.hasText(advertisementId)){
				String delfileurl="";
				List<BizTransUtil> list=new ArrayList<BizTransUtil>();
				//查询当前广告是否有 文件
				List<Map<String,Object>> filelist= baseBiz.queryForList("select a.ag_st_url,a.ag_st_id from  ag_t_file a  where a.ag_st_objid=? and a.ag_st_objtype='bj_t_advertisement'", new Object[]{advertisementId});
				if(filelist!=null&&filelist.size()>0){
					list.add(new BizTransUtil(filelist.get(0).get("ag_st_id"), Ag_t_file.class));//删除本条数据
					delfileurl=StringUtils.toStringByObject(filelist.get(0).get("ag_st_url"));
				}
				list.add(new BizTransUtil(advertisementId, Bj_t_advertisement.class));//删除本条广告
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
