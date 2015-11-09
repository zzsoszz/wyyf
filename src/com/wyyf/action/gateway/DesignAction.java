package com.wyyf.action.gateway;

import java.awt.image.BufferedImage;
import java.util.ArrayList;
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
import com.lys.utils.StringUtils;
import com.lys.utils.UploadFile;
import com.lys.utils.pagination.PageBean;
import com.power.bean.Ae_t_sysuser;
import com.power.bean.Ag_t_file;
import com.wyyf.bean.Bf_t_Apply;
@Scope(value = "prototype")
@Controller("DesignAction")
@RequestMapping(value="/index")
//个性设计
public class DesignAction  extends BaseAjaxAction{
	@RequestMapping(value="toDesign")
	public String icontent(){
		getTopFootInfo();
		String sid=request.getParameter("sid");
		System.out.println(sid);
		Ae_t_sysuser ae_t_sysuser = getTopFootInfo();
		if (null != ae_t_sysuser || null != ae_t_sysuser.getAe_st_id() || "" != ae_t_sysuser.getAe_st_id()) {
			if(sid==null||sid.equals("")){
				sid=ae_t_sysuser.getAe_st_id();
			}
		}
		String rid=request.getParameter("rid");
		System.out.println(rid);
		model.addAttribute("rid", rid);
		model.addAttribute("sid", sid);
		String type=request.getParameter("type");
		model.addAttribute("type", type);
		return "wyyf/index/designstyleoder";
		//return "wyyf/index/designnomal";
		
	}
	
	/**
	 * 免费设计
	 * @param bf
	 * @param multipartRequest
	 * @throws Exception
	 */
	@RequestMapping(value="saveDesign")
	public void saveDesign(Bf_t_Apply bf,MultipartHttpServletRequest multipartRequest) throws Exception{
			//创建批量保存事务对象
			List<BizTransUtil> list=new ArrayList<BizTransUtil>();
				//构建数据--新增
				bf.setBf_st_id(StringUtils.getUUID32());//ID 
				bf.setBf_dt_addDate(getcuttDate());//创建时间
				//bf.setBf_st_addUserId(getcuttSysuserID());//创建人员ID--这里不需要创建人了。 
				list.add(new BizTransUtil(bf));
				//上传的相关图片
				List<Ag_t_file> fileList=bf.getFileList();
				if(fileList!=null&&fileList.size()>0){
					MultipartFile mf = null;
					for(Ag_t_file file:fileList){
						//如果没有选择，自定义类型，跳过
//						if(!StringUtils.hasText(file.getAg_st_mark())){
//							continue;
//						}
						file.setAg_st_mark("1");
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
//						file.setAg_st_addUserId(getcuttSysuserID());//创建人员ID  
						file.setAg_st_objid(bf.getBf_st_id());//文件归属ID
						file.setAg_st_objtype("bf_t_apply");//文件对应的对象
						file.setAg_st_type("1");//文件类型-图片
						file.setAg_st_url(uploadPath);////文件 的存储地址
						file.setAg_st_id(StringUtils.getUUID32());
						list.add(new BizTransUtil(file));
					}
				}
				if (baseBiz.executesTRANS(list)) {
					this.createAjax(response,"0");
				} else {
					this.createAjax(response,"1");
				}
	}
	/**
	 * 获取设计师列表   个性设计和金牌设计  除了分类不同 其他相同
	 * @return
	 */
	@RequestMapping(value="designstyle")
	public String designstyle(){
		getTopFootInfo();
		String sid = request.getParameter("id");// 登陆者ID
		model.addAttribute("sid", sid);
		//城市代码
		String cityCode=request.getParameter("cityCode");
		//页数
		Integer page=StringUtils.toIntegerByObject(request.getParameter("page"));
		if(page==null||page<=0){
			page=Integer.valueOf(1);
		}
		//类型
		
		String CurrentCityCode = this.session.getAttribute("CurrentCityCode").toString();
		if(cityCode==null||cityCode.trim().equals("")){
			cityCode=CurrentCityCode;
		}
		Map<String, Object> CurrentCityInfo=baseBiz.queryForMap("SELECT * FROM aa26 WHERE d_code='"+CurrentCityCode+"'");
		//区域选择
		String sql2 = "select b.* from aa26 a LEFT JOIN aa26 b ON (a.d_code=b.d_parent)";
		List<Map<String, Object>> list2 = baseBiz.queryForList(sql2+" where a.d_code='"+CurrentCityCode+"'");
		model.addAttribute("district_list", list2);
		Map<String, Object> CurrentArea=baseBiz.queryForMap("SELECT * FROM aa26 WHERE d_code='"+cityCode+"'");
		StringBuilder sql=new StringBuilder("SELECT B.ae_st_name, D.ag_st_url, C.COUNT, A.* FROM ba_t_labour A LEFT JOIN ae_t_sysuser B ON B.ae_st_id = A.ba_st_userid LEFT JOIN ( SELECT B.ae_st_id, COUNT(*) COUNT FROM ae_t_sysuser B LEFT JOIN bf_t_apply C ON C.bf_st_receiveid = B.ae_st_id GROUP BY B.ae_st_id ) C ON C.ae_st_id = A.ba_st_id LEFT JOIN ag_t_file D ON D.ag_st_objid=B.ae_st_id WHERE B.ae_st_type='5' AND B.ae_st_name != '网众设计师'");
		StringBuilder totalSql=new StringBuilder("SELECT COUNT(1) FROM ba_t_labour A LEFT JOIN ae_t_sysuser B ON B.ae_st_id = A.ba_st_userid LEFT JOIN ( SELECT B.ae_st_id, COUNT(*) COUNT FROM ae_t_sysuser B LEFT JOIN bf_t_apply C ON C.bf_st_receiveid = B.ae_st_id GROUP BY B.ae_st_id ) C ON C.ae_st_id = A.ba_st_id LEFT JOIN ag_t_file D ON D.ag_st_objid=B.ae_st_id WHERE B.ae_st_type='5' AND B.ae_st_name != '网众设计师'");
		/*if(CurrentArea.get("d_level").toString().equals("1")){
			sql.append(" AND B.ae_st_shsheng ='"+cityCode +"' ");
			totalSql.append(" AND B.ae_st_shsheng ='"+cityCode +"'");
		}else if(CurrentArea.get("d_level").toString().equals("2")){
			sql.append(" AND B.ae_st_shshi ='"+cityCode +"' ");
			totalSql.append(" AND B.ae_st_shshi ='"+cityCode +"' ");
		}else if(CurrentArea.get("d_level").toString().equals("3")){
			sql.append(" AND B.ae_st_shxian ='"+cityCode +"' ");
			totalSql.append(" AND B.ae_st_shxian ='"+cityCode +"' ");
			
		}*/
		
		String zxType=request.getParameter("zxType");
		if(zxType!=null&&!zxType.equals("")&&!zxType.equals("0")){
			sql.append(" and 	A.ba_st_type='"+zxType+"'");
		}else {
			zxType="0";
		}
		//排序类型
		String OrderByType=request.getParameter("OrderByType");
		if(OrderByType==null||OrderByType.equals("")){
			OrderByType="0";
		}
		
		String Up=request.getParameter("Up");
		if(Up==null||Up.equals("")){
			Up="0";
		}
		if(OrderByType.equals("0")){
			if(Up.equals("0")){
				sql.append(" ORDER BY A.ba_st_grade DESC,C.COUNT DESC ,A.ba_st_price DESC ");
			}else{
				sql.append(" ORDER BY A.ba_st_grade ASC,C.COUNT ASC ,A.ba_st_price ASC ");
			}
		}else if(OrderByType.equals("1")){
			if(Up.equals("0")){
				sql.append(" ORDER BY A.ba_st_grade DESC ");
			}else{
				sql.append(" ORDER BY A.ba_st_grade ASC ");
			}
		}else if(OrderByType.equals("2")){
			if(Up.equals("0")){
				sql.append(" ORDER BY C.COUNT DESC ");
			}else{
				sql.append(" ORDER BY C.COUNT ASC ");
			}
		}else if(OrderByType.equals("3")){
			if(Up.equals("0")){
				sql.append(" ORDER BY A.ba_st_price DESC ");
			}else{
				sql.append(" ORDER BY A.ba_st_price ASC");
			}
		}
		PageBean pages=baseBiz.getPages(sql.toString(),totalSql.toString(),page, 16);
		int LabourTotal = baseBiz.queryForInt(totalSql.toString());
		
		model.addAttribute("zxType", zxType);
		model.addAttribute("CurrentCityCode", CurrentCityCode);
		model.addAttribute("cityCode", cityCode);
		model.addAttribute("OrderByType", OrderByType);
		model.addAttribute("Up",Up);
		model.addAttribute("page", page);
		model.addAttribute("LabourTotal", LabourTotal);
		model.addAttribute("su_supervisionlist", pages);	
		
	   //根据类型查询出设计类下面的子类
		String sqlchild ="SELECT * FROM ab_t_code where ab_st_parent='f4530e12ed5e43dcb7eab715a812665a'";
		List<Map<String, Object>> childTypes=baseBiz.queryForList(sqlchild);
		model.addAttribute("childs",childTypes);
		return "wyyf/index/designstyle";
		
	}
	
	@RequestMapping(value="toDesignshow")
	public String toDesignshow(){
		getTopFootInfo();
		String pa=request.getParameter("id");
		String type=request.getParameter("type");
		String sql = "select u.ae_st_name , u.ae_nm_age ,u.ae_st_intro ,date_format(l.ba_dt_addDate ,'%Y-%m-%d') bo_dt_updDate  , l.ba_st_grade ,l.ba_st_grade  ,(select ag_st_url from ag_t_file where ag_st_objid = u.ae_st_id) ag_st_url ,(select count(1) from bf_t_apply where bf_st_receiveid ='"+pa+"' ) num from ae_t_sysuser u,ba_t_labour l where u.ae_st_id = ae_st_id and  u.ae_st_type =5 and l.ba_st_userid=u.ae_st_id and u.ae_st_id='"+pa+"' limit 1";
		String caseSql="select f.ag_st_url  , c.bd_st_name  from bd_t_case c, ag_t_file f ,ae_t_sysuser u where c.bd_st_id=f.ag_st_objid and u.ae_st_id=c.bd_st_bbid  and u.ae_st_id = '"+pa+"'";
		List<Map<String, Object>> data1 = baseBiz.queryForList(caseSql);
		Map<String, Object> data = baseBiz.queryForMap(sql);
		model.addAttribute("id", pa);
		model.addAttribute("type", type);
		model.addAttribute("data", data);
		model.addAttribute("data1", data1);
		return "wyyf/index/designshow";
	}
	//免费设计
	@RequestMapping(value="workmessjsp")
	public String workmessjsp(){
		getTopFootInfo();
		String id=request.getParameter("id");
		String type=request.getParameter("type");
		model.addAttribute("data", id);
		model.addAttribute("type", type);
		return "wyyf/index/designnomal";
	}
	/*
	 * 用户注销
     */
	@RequestMapping(value="exit")
	public String exit(){
		getTopFootInfo();
		request.getSession().invalidate();
		return "redirect:/index";
	}
	
	
	@RequestMapping(value = "designDetail")
	public String fixdetail() {
		getTopFootInfo();
		String id = request.getParameter("id");// 师傅ID(赴约人)
		String sid = request.getParameter("sid");// 申请人ID
		Ae_t_sysuser ae_t_sysuser = getTopFootInfo();
		if (null != ae_t_sysuser || null != ae_t_sysuser.getAe_st_id() || "" != ae_t_sysuser.getAe_st_id()) {
			if (sid == null || sid.equals("")) {
				sid = ae_t_sysuser.getAe_st_id();
			}
		}
		String type = request.getParameter("type");// 类型
		model.addAttribute("type", type);
		model.addAttribute("sid", sid);
		model.addAttribute("rid", id);
		// 师傅信息
		String sql1 = "select ae_st_name,ae_st_id,a.ba_st_price,(select COUNT(1) from  bf_t_apply where bf_st_receiveid= ae_st_id) jdnum,(case when ba_st_grade<2 then 0 when 2<=ba_st_grade<4 then 1 when 4<=ba_st_grade<6 then 2 when 6<=ba_st_grade<8 then 3 else 4 end) ba_st_grade,(select ag_st_url from ag_t_file where ag_st_objid = ae_st_id) ag_st_url,ba_st_teamnum,ba_dt_addDate,ba_st_team_intro from ae_t_sysuser ,ba_t_labour a";
		List<Map<String, Object>> list1 = baseBiz.queryForList(sql1 + " where ae_st_id=ba_st_userid and ae_st_type='5' AND ae_st_id='" + id + "'");
		model.addAttribute("ma_masterDetaillist1", list1);
		// 评价信息
		String sql2 = "SELECT b.ae_st_name,be_st_content,be_dt_addDate FROM ae_t_sysuser a,be_t_assess,ae_t_sysuser b WHERE be_st_jbgid=a.ae_st_id AND be_st_fbgid=b.ae_st_id";
		List<Map<String, Object>> list2 = baseBiz.queryForList(sql2 + " AND a.ae_st_id='" + id + "'");
		model.addAttribute("ma_masterDetaillist2", list2);
		// 案例信息
		String sql3 = "SELECT ag_st_url,bd_st_name,bd_st_money,bd_st_area,bd_st_remark FROM bd_t_case,ae_t_sysuser,ag_t_file WHERE bd_st_bbid=ae_st_id AND ag_st_objid=bd_st_id";
		List<Map<String, Object>> list3 = baseBiz.queryForList(sql3 + "  AND ae_st_id='" + id + "'");
		model.addAttribute("ma_masterDetaillist3", list3);
		return "wyyf/index/masterDetail";
	}
	 
		

}
