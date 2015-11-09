
package com.wyyf.action;

import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.imageio.ImageIO;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
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
import com.wyyf.bean.Bf_t_Apply;

/**
 * 申请单管理
 * @author shuang
 */
@Scope(value = "prototype")
@Controller("ApplyAction")
@RequestMapping(value="/wyyf/apply")
public class ApplyAction extends BaseAjaxAction{
	/***
	 * 进入超级管理员申请列表界面
	 * @param req
	 * @return
	 */
	@RequestMapping(value="initapply")
	public String initapply(){
		return "wyyf/apply/applyList";
	}
	/***
	 * 进入技师申请列表界面
	 * @param req
	 * @return
	 */
	@RequestMapping(value="initjsapply")
	public String initjsapply(){
		return "wyyf/apply/applyjsList";
	}
	/***
	 * 管理员申请单
	 */
	@RequestMapping(value="applyList")
	public String applyList(Bf_t_Apply advertisement,String ISSHOWCOLUMS,Integer pageIndex,Integer pageSize){
		return commonList(advertisement,"", ISSHOWCOLUMS, pageIndex, pageSize);
	}
	
	/***
	 * 技师申请列表
	 */
	@RequestMapping(value="jsapplyList")
	public String jsapplyList(Bf_t_Apply advertisement,String ISSHOWCOLUMS,Integer pageIndex,Integer pageSize){
		return commonList(advertisement,getcuttSysuserID(), ISSHOWCOLUMS, pageIndex, pageSize);
	}
	
	private String commonList(Bf_t_Apply advertisement,String bf_st_receiveid, String ISSHOWCOLUMS,Integer pageIndex, Integer pageSize) {
		StringBuffer sb=new StringBuffer("select bf_st_id, "+StringUtils.trunTypeByString("bf_dt_addDate")+" bf_dt_addDate, date_format(bf_dt_time,'%Y-%m-%d') bf_dt_time, bf_st_receiveid, bf_st_addUserId,bf_st_address,bf_st_area,bf_st_housnum,bf_st_incity,bf_st_isreal,bf_st_name,bf_st_remark,bf_st_tell,bf_st_type,bf_st_updUserId,bf_st_userid,(select (case when count(1)>0 then 1 else 0 end ) from ag_t_file where ag_st_objid=bf_st_id and ag_st_mark='JSRUEST') issctp "); 
		StringBuffer sqlCount =new StringBuffer("select count(bf_st_id) ");
		StringBuffer sbcommon=new StringBuffer(" from bf_t_apply  where 1=1 ");
		List<String> pramsList = new ArrayList<String>();
		//条件查询 --姓名查询
		if (org.springframework.util.StringUtils.hasText(advertisement.getBf_st_name())) {
			sbcommon.append(" and bf_st_name like ?");
			pramsList.add("%" + advertisement.getBf_st_name() + "%");
		}
		//条件查询 --姓名查询
		if (org.springframework.util.StringUtils.hasText(advertisement.getBf_st_type())) {
			sbcommon.append(" and bf_st_type = ?");
			pramsList.add( advertisement.getBf_st_type());
		}
		//判断是否技师申请单
		if (org.springframework.util.StringUtils.hasText(bf_st_receiveid)) {
			sbcommon.append(" and bf_st_receiveid = ?");
			pramsList.add( bf_st_receiveid);
		}
		
		sb.append(sbcommon).append(" order by bf_dt_addDate desc");
		sqlCount.append(sbcommon);
		//分页数据、分页配置 查询
		PageBean pages=baseBiz.getPages(sb.toString(), sqlCount.toString(),pageIndex, pageSize,pramsList.toArray());
		//列设置
		List<PageColumn> columnList=new ArrayList<PageColumn>();
		columnList.add(new PageColumn("序号", "bf_st_id").setIsnum(true).setWidth("30"));//序号
		columnList.add(new PageColumn("类型", "bf_st_type").setCode("SQLX").setIsshowcode(true));
		columnList.add(new PageColumn("姓名", "bf_st_name"));
		columnList.add(new PageColumn("电话", "bf_st_tell"));
		columnList.add(new PageColumn("预约时间", "bf_dt_time"));
		columnList.add(new PageColumn("申请时间", "bf_dt_addDate"));
		columnList.add(new PageColumn("是否上传结果", "issctp").setCode("YESNO").setIsshowcode(true));
		
		//操作按钮
		StringBuffer btns=new StringBuffer("<button data-toggle='modal' onclick='findapply(this);' class='btn btn-sm yellow' ><i class='fa fa-pencil-square-o'></i>去查看</button> ");
		btns.append("<button data-toggle='modal' onclick='delapply(this);' class='btn btn-sm red' ><i class='fa fa-trash-o'></i>删除</button> ");
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
	 * 新增或更新申请
	 */
	@RequestMapping(value="applyAdd")
	public void applyAdd(Bf_t_Apply bf,MultipartHttpServletRequest multipartRequest){
		String jsonData="";
		//删除以前的图片
		List<String> dellist=new ArrayList<String>();
		try {
			//创建批量保存事务对象
			List<BizTransUtil> list=new ArrayList<BizTransUtil>();
			//更新
			if (org.springframework.util.StringUtils.hasText(bf.getBf_st_id())) {
				//构建数据--修改
				Map<String,Object>  map=new HashMap<String,Object>();
				map.put("bf_st_id", bf.getBf_st_id());//ID
				map.put("bf_dt_updDate", getcuttDate());//修改时间
				map.put("bf_st_updUserId", getcuttSysuserID());//修改人员ID
				list.add(new BizTransUtil(map,Bf_t_Apply.class,CommonUtil.UPDATE));
				//上传的相关图片
				List<Ag_t_file> file2List=bf.getFile2List(); //这里少个 2 呢？  没有改？我没有修改 
				if(file2List!=null&&file2List.size()>0){
					MultipartFile mf = null;
					for(Ag_t_file file:file2List){
						mf=multipartRequest.getFile(file.getAg_st_addUserId());
						String uploadPath="";
						//判断是否有修改图片
						if(mf!=null && mf.getSize()>0){
							//如果有ID ，说明是修改
							if(org.springframework.util.StringUtils.hasText(file.getAg_st_id())){
								list.add(new BizTransUtil(file.getAg_st_id(), Ag_t_file.class));//删除以前数据
								dellist.add(StringUtils.toStringByObject(file.getAg_st_url()));//删除以前图片
							}
							uploadPath=UploadFile.uploadInputStream(mf.getInputStream(), mf.getOriginalFilename(), "images/business/ad");
							BufferedImage buff = ImageIO.read(mf.getInputStream());
							file.setAg_nm_height(buff.getHeight());//文件属性高
							file.setAg_nm_width(buff.getWidth());//文件属性宽
							file.setAg_nm_size(mf.getSize());//文件属性大小
							file.setAg_st_format(mf.getContentType());//文件格式
						}else{
							//如果没有文件上传则跳过
							continue;
						}
						file.setAg_st_mark("JSRUEST");//自定义标识
						file.setAg_dt_addDate(getcuttDate());//创建时间
						file.setAg_st_addUserId(getcuttSysuserID());//创建人员ID  
						file.setAg_st_objid(bf.getBf_st_id());//文件归属ID
						file.setAg_st_objtype("bf_t_apply");//文件对应的对象
						file.setAg_st_type("1");//文件类型-图片
						file.setAg_st_url(uploadPath);////文件 的存储地址
						file.setAg_st_id(StringUtils.getUUID32());
						list.add(new BizTransUtil(file));
					}
				}else{
					throw new RuntimeException("至少上传一个结果图");
				}
				
			}
			//新增
			else {
				//构建数据--新增
				bf.setBf_st_id(StringUtils.getUUID32());//ID 
				bf.setBf_dt_addDate(getcuttDate());//创建时间
				bf.setBf_st_addUserId(getcuttSysuserID());//创建人员ID
				list.add(new BizTransUtil(bf));
				//上传的相关图片
				List<Ag_t_file> fileList=bf.getFileList();
				if(fileList!=null&&fileList.size()>0){
					MultipartFile mf = null;
					for(Ag_t_file file:fileList){
						//如果没有选择，自定义类型，跳过
						if(!org.springframework.util.StringUtils.hasText(file.getAg_st_mark())){
							continue;
						}
						mf=multipartRequest.getFile(file.getAg_st_addUserId());
						String uploadPath="";
						if(mf!=null && mf.getSize()>0){
							uploadPath=UploadFile.uploadInputStream(mf.getInputStream(), mf.getOriginalFilename(), "images/business/ad");
							BufferedImage buff = ImageIO.read(mf.getInputStream());
							file.setAg_nm_height(buff.getHeight());//文件属性高
							file.setAg_nm_width(buff.getWidth());//文件属性宽
							file.setAg_nm_size(mf.getSize());//文件属性大小
							file.setAg_st_format(mf.getContentType());//文件格式
						}
						file.setAg_dt_addDate(getcuttDate());//创建时间
						file.setAg_st_addUserId(getcuttSysuserID());//创建人员ID  
						file.setAg_st_objid(bf.getBf_st_id());//文件归属ID
						file.setAg_st_objtype("bf_t_apply");//文件对应的对象
						file.setAg_st_type("1");//文件类型-图片
						file.setAg_st_url(uploadPath);////文件 的存储地址
						file.setAg_st_id(StringUtils.getUUID32());
						list.add(new BizTransUtil(file));
					}
				}
			}
			//执行操作
			if (baseBiz.executesTRANS(list)) {
				if(dellist.size()>0){
					for(String s:dellist){
						if(org.springframework.util.StringUtils.hasText(s)){
							UploadFile.delpic(s);//删除原文件
						}
					}
				}
				jsonData = "{success:true,msg:\"操作成功!\"}";
			} else {
				jsonData = "{success:false,msg:\"操作异常!\"}";
			}
		}  catch (Exception e) {
			jsonData = "{success:false,msg:\"操作失败，"+e.getMessage()+"!\"}";
			e.printStackTrace();
		}
		this.createAjax(response,jsonData);
	}
	/**
	 * 查询单个申请信息
	 */
	@RequestMapping(value="findApplyById")
	public void findApplyById(String id){
		if(org.springframework.util.StringUtils.hasText(id)){
			StringBuffer sb=new StringBuffer("select bf_st_id,date_format(bf_dt_addDate,'%Y-%m-%d %T') bf_dt_addDate, date_format(bf_dt_time,'%Y-%m-%d') bf_dt_time, bf_st_receiveid,(select username from ae_t_sysuser where ae_st_id=bf_st_userid) bf_st_receivename,(select username from ae_t_sysuser where ae_st_id=bf_st_receiveid) bf_st_username, bf_st_addUserId,bf_st_address,bf_st_area,bf_st_housnum,bf_st_incity,bf_st_isreal,bf_st_name,bf_st_remark,bf_st_tell,bf_st_type,bf_st_updUserId,bf_st_userid ")
			.append(" from bf_t_apply  where 1=1 and bf_st_id=?  ");
			Map<String, Object> map=baseBiz.queryForMap(sb.toString(), new Object[]{id});
			String jsonData = "{\"success\":true,result:["+JsonUtils.getJsonData(map)+"]}";
			this.createAjax(response, jsonData);
		}
	}
	/**
	 * 查询单个申请信息(技师申请单)
	 */
	@RequestMapping(value="findjsApplyById")
	public void findjsApplyById(String id){
		if(org.springframework.util.StringUtils.hasText(id)){
			StringBuffer sb=new StringBuffer("select payState , bf_st_id, "+StringUtils.trunTypeByString("bf_dt_addDate")+" bf_dt_addDate, date_format(bf_dt_time,'%Y-%m-%d') bf_dt_time, bf_st_receiveid, bf_st_addUserId,bf_st_address,bf_st_area,bf_st_housnum,bf_st_incity,bf_st_isreal,bf_st_name,bf_st_remark,bf_st_tell,bf_st_type,bf_st_updUserId,bf_st_userid ")
			.append(" from bf_t_apply  where 1=1 and bf_st_id=?  ");
			Map<String, Object> map=baseBiz.queryForMap(sb.toString(), new Object[]{id});
			
			
			if("1".equals(map.get("payState"))){
				map.put("payState", "已支付");
			}else{
				map.put("payState", "未支付");
			}
			
			String jsonData = "{\"success\":true,result:["+JsonUtils.getJsonData(map)+"]}";
			this.createAjax(response, jsonData);
		}
	}

	/**
	 * 单个删除申请
	 */
	@RequestMapping(value="applyDelete")
	public void applyDelete(String id){
		String jsonData="";
		try {
			if(org.springframework.util.StringUtils.hasText(id)){
				List<BizTransUtil> list=new ArrayList<BizTransUtil>();
				List<String> dellist=new ArrayList<String>();
				//查询当前申请是否有 文件
				List<Map<String,Object>> filelist= baseBiz.queryForList("select a.ag_st_url,a.ag_st_id from  ag_t_file a  where a.ag_st_objid=?  ", new Object[]{id});
				if(filelist!=null&&filelist.size()>0){
					for(Map<String,Object> map:filelist){
						list.add(new BizTransUtil(map.get("ag_st_id"), Ag_t_file.class));//删除本条数据
						dellist.add(StringUtils.toStringByObject(map.get("ag_st_url")));
					}
				}
				list.add(new BizTransUtil(id, Bf_t_Apply.class));//删除本条申请
				if(baseBiz.executesTRANS(list)){
					if(dellist.size()>0){
						for(String s:dellist){
							if(org.springframework.util.StringUtils.hasText(s)){
								UploadFile.delpic(s);//删除原文件
							}
						}
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
	/**
	 * 单个删除申请(技师)
	 */
	@RequestMapping(value="jsapplyDelete")
	public void jsapplyDelete(String id){
		String jsonData="";
		try {
			if(org.springframework.util.StringUtils.hasText(id)){
				List<BizTransUtil> list=new ArrayList<BizTransUtil>();
				List<String> dellist=new ArrayList<String>();
				//查询当前申请是否有 文件
				List<Map<String,Object>> filelist= baseBiz.queryForList("select a.ag_st_url,a.ag_st_id from  ag_t_file a  where a.ag_st_objid=?  ", new Object[]{id});
				if(filelist!=null&&filelist.size()>0){
					for(Map<String,Object> map:filelist){
						list.add(new BizTransUtil(map.get("ag_st_id"), Ag_t_file.class));//删除本条数据
						dellist.add(StringUtils.toStringByObject(map.get("ag_st_url")));
					}
				}
				list.add(new BizTransUtil(id, Bf_t_Apply.class));//删除本条申请
				if(baseBiz.executesTRANS(list)){
					if(dellist.size()>0){
						for(String s:dellist){
							if(org.springframework.util.StringUtils.hasText(s)){
								UploadFile.delpic(s);//删除原文件
							}
						}
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
