package com.power.action;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.util.LinkedCaseInsensitiveMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import com.lys.mvc.action.BaseAjaxAction;
import com.lys.utils.ExcelUtil;
import com.lys.utils.JsonUtils;
import com.lys.utils.StringUtils;
import com.power.bean.Ab_t_code;
import com.power.biz.CommonBiz;
import com.power.utils.PowerStatic;

@Scope(value = "prototype")
@Controller("CodeAction")
@RequestMapping(value="/system/code")
public class CodeAction extends BaseAjaxAction{
	@Resource(name="CommonBizCache")
	public CommonBiz commonBiz;//操作数据的对象biz
	/***
	 * 进入字典主界面
	 * @param req
	 * @return
	 */
	@RequestMapping(value="initCode")
	public String initCode(){
		StringBuffer sb=new StringBuffer("select a.ab_st_id TREEID,a.ab_nm_openorclose  as \"open\",a.ab_st_parent TREEPID,a.ab_st_name TREENAME,a.ab_nm_jdnum  as \"ab_nm_jdnum\",a.ab_st_sysmark  as \"ab_st_sysmark\" from ab_t_code a order by a.ab_nm_orderno asc");
		String json=JsonUtils.getJsonDataFromCollection(baseBiz.queryForList(sb.toString()));
		model.addAttribute("codeTree", json);
		return "system/code/codeList";
	}
	/**
	 * 批量添加字典数据---通过Excel 导入模式
	 * @param response
	 * @param sysfunction
	 */
	@RequestMapping(value="addCodeByfile")
	public void addCodeByfile(Ab_t_code ab_t_code,MultipartHttpServletRequest multipartRequest){
		String jsonData="";
		try {
			if(ab_t_code!=null&&org.springframework.util.StringUtils.hasText(ab_t_code.getAb_st_parent())){
				MultipartFile mf = multipartRequest.getFile("myfile");
				if(mf!=null){
					//查询父标识
					String parentMark=StringUtils.toStringByObject(baseBiz.queryForObject("select ab_st_mark from ab_t_code where ab_st_id=? ",new Object[]{ab_t_code.getAb_st_parent()}, String.class),true);
					if(!org.springframework.util.StringUtils.hasText(parentMark)){
						throw new RuntimeException("父节点标识不存在，请重新打开页面！");
					}
					//File file=new File(pathname);
					//解析Excel文件流
					Map<Integer,List<Map<String, String>>> maps=ExcelUtil.readXls(mf.getInputStream());
					//循环遍历数据。。。。。
					if(maps!=null && maps.size()>0) {
						List<Ab_t_code> codeList=new ArrayList<Ab_t_code>();
						int m=0;//只取第一个Sheet单的值
						List<Map<String, String>> objs =  maps.get(m);
						if(objs	!= null && objs.size()>0){
							//从i=1开始，因为xls第一行是标题
							for(int i = 1,j=objs.size();i<j;i++){
								try {
									Map<String, String> map=objs.get(i);//一行的数据
									//下面是非空判断
									if(!org.springframework.util.StringUtils.hasText(map.get("0"))){
										throw new RuntimeException("错误在xls中的第"+(m+1)+"个sheet中:"+(i+1)+"行的第1列！原因：码值名不能为空！"); 
									}
									if(!org.springframework.util.StringUtils.hasText(map.get("1"))){
										throw new RuntimeException("错误在xls中的第"+(m+1)+"个sheet中:"+(i+1)+"行的第2列！原因：码值不能为空！"); 
									}
									//将取到的值赋给表中想对应字段
									Ab_t_code cd=new Ab_t_code(true);
									cd.setAb_st_name(StringUtils.toStringByObject(map.get("0"),true));//码名
									cd.setAb_st_mark(parentMark+"_"+StringUtils.toStringByObject(map.get("1"),true));//标识===父标识+“_”+码值
									cd.setAb_st_value(StringUtils.toStringByObject(map.get("1"),true));//码值
									cd.setAb_st_type(PowerStatic.codeType.get(StringUtils.toStringByObject(map.get("2"),true)));//码值类型
									int isopen=0;//是否打开
									//如果是否贷款 不等于 空字符串的话
									if(org.springframework.util.StringUtils.hasText(StringUtils.toStringByObject(map.get("3"),true))){
										isopen=Integer.valueOf(PowerStatic.isyesorno.get(StringUtils.toStringByObject(map.get("3"),true)));
									}
									cd.setAb_nm_openorclose(isopen);//是否打开
									int xuhao=0;//序号
									//如果序号不等于 空字符串的话
									if(org.springframework.util.StringUtils.hasText(StringUtils.toStringByObject(map.get("4"),true))){
										xuhao=Integer.valueOf(StringUtils.toStringByObject(map.get("4"),true));
									}
									cd.setAb_nm_orderno(xuhao);//序号
									cd.setAb_st_describe(StringUtils.toStringByObject(map.get("5"),true));//中文描述
									cd.setAb_st_parent(ab_t_code.getAb_st_parent());//父节点
									cd.setAb_nm_jdnum(ab_t_code.getAb_nm_jdnum());//在所有节点中的级号
									cd.setAb_st_sysmark(ab_t_code.getAb_st_sysmark());// 系统标识
									cd.setAb_st_addUserId(getcuttSysuserID());
									cd.setAb_dt_addDate(getcuttDate());
									codeList.add(cd);
								}catch (Exception e){
									throw new RuntimeException("数据存储失败！错误在xls中的第"+(m+1)+"个sheet" +
											"中:"+(i+1)+"行！原因："+e.getMessage());
								}
							}
						}
						if(codeList!=null&&codeList.size()>0){
							if(baseBiz.addsTRANS(codeList, Ab_t_code.class)){
								jsonData = "{success:true,msg:\"数据保存成功!\"}";
							}else{
								jsonData = "{success:false,msg:\"数据保存失败!\"}";
							}
						}
					}
				}else{
					jsonData = "{success:false,msg:\"导入失败，未获取导入文件!\"}";
				}
			}else{
				jsonData = "{success:false,msg:\"导入失败，未获取父节点信息!\"}";
			}
		} catch (Exception e) {
			jsonData = "{success:false,msg:\"导入异常，"+e.getMessage()+"!\"}";
			e.printStackTrace();
		}
		this.createAjax(response,jsonData);
	}
	/**
	 * 添加或修改字典--通过表单提交保存
	 * @param response
	 * @param sysfunction
	 */
	@RequestMapping(value="codeaddorupd")
	public void codeAddOrUpd(Ab_t_code ab_t_code){
		String jsonData="";
		try {
			//验证
			if(!org.springframework.util.StringUtils.hasText(ab_t_code.getAb_st_parent())){
				throw new RuntimeException("父节点不能为空！");
			}
			//修改
			if(org.springframework.util.StringUtils.hasText(ab_t_code.getAb_st_id())){
				Map<String,Object>  map=new LinkedCaseInsensitiveMap<Object>();
				map.put("ab_st_mark", ab_t_code.getAb_st_mark());
				map.put("ab_st_value", ab_t_code.getAb_st_value());
				map.put("ab_st_name", ab_t_code.getAb_st_name());
				map.put("ab_st_type", ab_t_code.getAb_st_type());
				map.put("ab_nm_openorclose", ab_t_code.getAb_nm_openorclose());
				map.put("ab_nm_orderno", ab_t_code.getAb_nm_orderno());
				map.put("ab_st_describe", ab_t_code.getAb_st_describe());
				map.put("ab_st_sysmark", ab_t_code.getAb_st_sysmark());
				map.put("ab_nm_jdnum", ab_t_code.getAb_nm_jdnum());
				map.put("ab_st_updUserId", getcuttSysuserID());//修改人ID
				map.put("ab_dt_updDate", getcuttDate());//修改时间
				map.put("ab_st_id", ab_t_code.getAb_st_id()); //ID
				map.put("ab_st_isuserset", ab_t_code.getAb_st_isuserset()); //是否允许用户控制
				Integer c=baseBiz.updateTRANS(map, Ab_t_code.class);
				if(c==1){
					jsonData = "{success:true,msg:\"修改成功!\",result:["+JsonUtils.getJsonData(ab_t_code)+"]}";
				}else {
					jsonData = "{success:false,msg:\"修改失败!\"}";
				}
			}
			//新增
			else{
				ab_t_code.setAb_st_id(StringUtils.getUUID32());
				ab_t_code.setAb_st_addUserId(getcuttSysuserID());
				ab_t_code.setAb_dt_addDate(getcuttDate());
				//执行保存
				if(baseBiz.addTRANS(ab_t_code)){
					jsonData = "{success:true,msg:\"添加成功!\",result:["+JsonUtils.getJsonData(ab_t_code)+"]}";
				}else{
					jsonData = "{success:false,msg:\"添加失败!\"}";
				}
			}
			//清除缓存
			commonBiz.delALLcode();
			
		} catch (Exception e) {
			jsonData = "{success:false,msg:\"保存异常，"+e.getMessage()+"!\"}";
			e.printStackTrace();
		}
		this.createAjax(response,jsonData);
	}
	
	/**
	 * 查询字典--根据id
	 * @param response
	 * @param sysfunction
	 */
	@RequestMapping(value="findCodeById")
	public void findCodeById(Ab_t_code ab_t_code){
		if(ab_t_code!=null&&org.springframework.util.StringUtils.hasText(ab_t_code.getAb_st_id())){
			StringBuffer sb=new StringBuffer(" select a.ab_st_isuserset as \"ab_st_isuserset\",a.ab_st_id as \"ab_st_id\", a.ab_st_describe as \"ab_st_describe\", a.ab_st_mark as \"ab_st_mark\", a.ab_st_name as \"ab_st_name\", a.ab_nm_openorclose as \"ab_nm_openorclose\", a.ab_nm_orderno as \"ab_nm_orderno\",a.ab_nm_jdnum as \"ab_nm_jdnum\",a.ab_st_sysmark as \"ab_st_sysmark\", ")
							.append(" a.ab_st_parent as \"ab_st_parent\",a.ab_st_type as \"ab_st_type\",(select ab_st_name from ab_t_code where  ab_st_id=a.ab_st_parent)  as \"ab_st_parentName\",a.ab_st_value as \"ab_st_value\" ")
							.append(" from  ab_t_code a  ");
			sb.append(" where a.ab_st_id=? ");
			Map<String, Object>  map=baseBiz.queryForMap(sb.toString(), new Object[]{ab_t_code.getAb_st_id()});
			String jsonData = "{\"success\":true,result:["+JsonUtils.getJsonData(map)+"]}";
			this.createAjax(response, jsonData);
		}
	}
	
	/**
	 * 删除字典
	 * @param response
	 * @param sysfunction
	 */
	@RequestMapping(value="codeDelete")
	public void codeDelete( String codeid, String parentid) {
		String jsonData = "";
		try {
			if (codeid != null) {
				if ("null".equals(parentid) || "0".equals(parentid) || !org.springframework.util.StringUtils.hasText(parentid)) {
					jsonData = "{success:false,msg:\"删除失败,不能删除根节点!\"}";
				} else {
					if (baseBiz.deleteTRANS(StringUtils.getArrayByArray(codeid.split(",")), Ab_t_code.class)) {
						// 清除缓存
						commonBiz.delALLcode();
						jsonData = "{success:true,msg:\"删除成功!\"}";
					} else {
						jsonData = "{success:false,msg:\"删除失败!\"}";
					}
				}
			} else {
				jsonData = "{success:false,msg:\"删除失败!\"}";
			}
		} catch (Exception e) {
			jsonData = "{success:false,msg:\"删除异常，" + e.getMessage() + "!\"}";
			e.printStackTrace();
		}
		this.createAjax(response, jsonData);
	}

}
