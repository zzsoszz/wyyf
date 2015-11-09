package com.wyyf.action;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import com.lys.mvc.action.BaseAjaxAction;
import com.lys.utils.BizTransUtil;
import com.lys.utils.CommonUtil;
import com.lys.utils.JsonUtils;
import com.lys.utils.StringUtils;
import com.lys.utils.pagination.PageBean;
import com.power.utils.PageColumn;
import com.wyyf.bean.Bj_t_advertisement;
import com.wyyf.bean.Br_t_video;

/***
 * 视频管理
 * @author Administrator
 *
 */
@Scope(value = "prototype")
@Controller("videoAction")
@RequestMapping(value="/wyyf/video")
public class videoAction extends BaseAjaxAction {
	@RequestMapping(value="initVideo")
	public String initVideo(Model model){
		return "wyyf/video/initVideoList";
	}
	@RequestMapping(value="videoList")
	public String advertisementList(Model model,Bj_t_advertisement advertisement,String ISSHOWCOLUMS,Integer pageIndex,Integer pageSize){
		StringBuffer sb=new StringBuffer("select br_st_id, date_format(br_st_addDate,'%Y-%m-%d %T') br_st_addDate,br_st_url, br_st_name"); 
		StringBuffer sqlCount =new StringBuffer("select count(1) ");
		StringBuffer sbcommon=new StringBuffer(" from br_t_video where 1=1 ");
		List<String> pramsList = new ArrayList<String>();
		/*//条件查询 --广告内容
		if (org.springframework.util.StringUtils.hasText(advertisement.getBj_st_title())) {
			sbcommon.append(" and bj_st_title like ?");
			pramsList.add("%" + advertisement.getBj_st_title() + "%");
		}
		//条件查询 --广告类型
		if (org.springframework.util.StringUtils.hasText(advertisement.getBj_st_type())) {
			sbcommon.append(" and bj_st_type= ?");
			pramsList.add(advertisement.getBj_st_type());
		}*/
		/*sb.append(sbcommon).append(" order by bj_st_type,bj_st_enable desc,bj_nm_orderno asc , bj_dt_updDate desc");*/
		sqlCount.append(sbcommon);
		sb.append(sbcommon);
		//分页数据、分页配置 查询
		PageBean pages=baseBiz.getPages(sb.toString(), sqlCount.toString(),pageIndex, pageSize,pramsList.toArray());
		//列设置
		List<PageColumn> columnList=new ArrayList<PageColumn>();
		columnList.add(new PageColumn("序号", "br_st_id").setIsnum(true).setWidth("30"));//序号
		columnList.add(new PageColumn("视频名称", "br_st_name"));
		columnList.add(new PageColumn("视频地址", "br_st_url"));
		columnList.add(new PageColumn("上传时间", "br_st_addDate"));
		//操作按钮
		StringBuffer btns=new StringBuffer("<button data-toggle='modal' onclick='editAdvertisement(this);' class='btn btn-sm blue' ><i class='fa fa-pencil-square-o'></i>编辑</button> ");
		/*btns.append("<button data-toggle='modal' onclick='delAdvertisement(this);' class='btn btn-sm red' ><i class='fa fa-trash-o'></i>删除</button> ");*/
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
	 * 新增修改视频
	 * @param response
	 * @param oldFile
	 * @param advertisement
	 * @param multipartRequest
	 */
	@RequestMapping(value="sysVideoAdd")
	public void sysVideoAdd(HttpServletResponse response,Br_t_video video){
		String jsonData="";
		try {
			if(video!=null){		
				String delfileurl="";
				//创建批量保存事务对象
				List<BizTransUtil> list=new ArrayList<BizTransUtil>();
				//更新
				if (org.springframework.util.StringUtils.hasText(video.getBr_st_id())) {
					//构建数据--修改
					Map<String,Object>  map=new HashMap<String,Object>();
					map.put("br_st_id",video.getBr_st_id());//ID
					map.put("br_st_name", video.getBr_st_name());//内容
					map.put("br_st_addDate", getcuttDate());//点击链接
					map.put("br_st_url", video.getBr_st_url());//备注
					list.add(new BizTransUtil(map,Br_t_video.class,CommonUtil.UPDATE));
				}
				//新增
				else {
					//构建数据--新增
					video.setBr_st_id(StringUtils.getUUID32());
					video.setBr_st_userid(getcuttSysuserID());
					video.setBr_st_addDate(getcuttDate());
				/*	advertisement.setBj_st_id(StringUtils.getUUID32());//ID 
					advertisement.setBj_dt_addDate(getcuttDate());//创建时间
					advertisement.setBj_st_addUserId(getcuttSysuserID());//创建人员ID
*/					list.add(new BizTransUtil(video));
				}
				//新增图片/删除图片
			/*	MultipartFile mf = multipartRequest.getFile("myfile");
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
				}*/
				//执行操作
				if (baseBiz.executesTRANS(list)) {
					/*if(org.springframework.util.StringUtils.hasText(delfileurl)){
						UploadFile.delpic(delfileurl);//删除原文件
					}*/
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
	
	@RequestMapping(value="findVideoById")
	public void findVideoById(HttpServletResponse response,String brid){
		if(org.springframework.util.StringUtils.hasText(brid)){
			StringBuffer sb=new StringBuffer("select br_st_id, date_format(br_st_addDate,'%Y-%m-%d %T') br_st_addDate,br_st_url, br_st_name from br_t_video where 1=1 and br_st_id=? ");
			Map<String, Object> map=baseBiz.queryForMap(sb.toString(), new Object[]{brid});
			String jsonData = "{\"success\":true,result:["+JsonUtils.getJsonData(map)+"]}";
			this.createAjax(response, jsonData);
		}
	}

}
