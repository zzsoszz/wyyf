package com.lys.mvc.action;

import java.io.IOException;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.session.SessionInformation;
import org.springframework.security.core.session.SessionRegistry;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;

import com.lys.mvc.biz.BaseBiz;
import com.lys.utils.MessageSourceHelper;
import com.power.bean.Ae_t_sysuser;
import com.wyyf.bean.Aa26;

/***
 * 公用action方法
 * @author lys
 *
 */
public class BaseAjaxAction {
	
	/***
	 * 在需要获取登录后 的公共数据 时-调用
	 * 查询头部或者底部的数据
	 */
	 public Ae_t_sysuser getTopFootInfo(){
		//登录对象
		Ae_t_sysuser ae_t_sysuser=new Ae_t_sysuser();
		try {
			ae_t_sysuser=getcuttSysuser();
		} catch (Exception e) {
		}
		model.addAttribute("sysuser", ae_t_sysuser);
		String CurrentCityCode = (String)this.session.getAttribute("CurrentCityCode");
		if(CurrentCityCode==null){
			CurrentCityCode="510100";
			this.session.putValue("CurrentCityCode", CurrentCityCode);
		}
		//得到当前城市
		Map<String, Object> CurrentCityInfo=baseBiz.queryForMap("SELECT * FROM aa26 WHERE d_code='"+CurrentCityCode+"'");
		model.addAttribute("CurrentCityInfo", CurrentCityInfo);

//    	//logo地址
//		String logoUrl =(String)baseBiz.queryForObject("select a.ag_st_url from ag_t_file a,bj_t_advertisement b where a.ag_st_objid = b.bj_st_id and b.bj_st_type='1' limit 0,1 ", String.class);
//		//商城名称
//		String title =(String)baseBiz.queryForObject("select bj_st_title from bj_t_advertisement where bj_st_type='2'  limit 0,1 ", String.class);
//		//商品类型
//		List<Map<String, Object>> list = baseBiz.queryForList("select ab_st_id,ab_st_value,ab_st_name from ab_t_code  where ab_st_type='2' and ab_st_mark like 'PPLX_%' ");
//		model.addAttribute("top_logoUrl", logoUrl);
//		model.addAttribute("top_title", title);
//		model.addAttribute("top_list", list);
		return ae_t_sysuser;
    }
	
	
	@Resource(name="BaseBizImpl")
	protected BaseBiz baseBiz;
	@Resource(name="MessageSourceHelper")
	protected MessageSourceHelper message;
	@Resource(name="sessionRegistry")
	protected SessionRegistry sessionRegistry;
	/**
	 * request、response、session对象 action必须是非单例
	 */
	protected HttpServletRequest request; //action必须是非单例
    protected HttpServletResponse response; //action必须是非单例 
    protected HttpSession session;  //action必须是非单例
    protected Model model;  //action必须是非单例
      
    @ModelAttribute  
    public void setReqAndRes(HttpServletRequest request, HttpServletResponse response,Model model){  
        this.request = request;  
        this.response = response;  
        this.session = request.getSession(); 
        this.model=model;
    } 
	/**
	 * 获取当前登录人对象
	 * @return
	 */
	public Ae_t_sysuser getcuttSysuser(){
		return  (Ae_t_sysuser)SecurityContextHolder.getContext().getAuthentication().getPrincipal();
	}
	/**
	 * 获取当前登录人ID
	 * @return
	 */
	public String getcuttSysuserID(){
		return  getcuttSysuser().getAe_st_id();
	}
	/**
	 * 获取当前时间
	 * @return
	 */
	public Date getcuttDate(){
		return new Date();
	}
	/**
	 * 把数据print 到前台
	 * @param response 
	 * @param ajax
	 */
	public void createAjax(HttpServletResponse response,Object ajax){
		createAjax(response, ajax, null);
	}
	/**
	 * 把数据print 到前台
	 * @param response
	 * @param ajax
	 * @param contentType 上下文类型
	 */
	public void createAjax(HttpServletResponse response,Object ajax,String contentType){
		if(response!=null){
			if (null == contentType || "".equals(contentType))
				contentType = "text/plain";
			response.setContentType(contentType);
			try {
				response.getWriter().print(ajax);
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
	/**
	 * 踢出某些用户下线
	 * @param sysuserids 传入的用户ID Map集合 ,map的value值为 1
	 */
	public void outSessionsbyId(Map<String,String> map) {
		if(map!=null){
			List<Object> loginIds= sessionRegistry.getAllPrincipals();
			for(int b = 0; b < loginIds.size(); b++ ){
				Ae_t_sysuser ae_t_sysuser=(Ae_t_sysuser) loginIds.get(b);
				if(org.springframework.util.StringUtils.hasText(map.get(ae_t_sysuser.getAe_st_id()))){
					for(SessionInformation sessions:sessionRegistry.getAllSessions(ae_t_sysuser, false)){
						sessions.expireNow();//这句强制T出了用户， (设置为过期）
					}
				}
			}
		}
	}
	/***
	 * 踢出所有用户下线,不包括自己
	 */
	public void outSessionsAll() {
		List<Object> loginIds= sessionRegistry.getAllPrincipals();
		for(int b = 0; b < loginIds.size(); b++ ){
			Ae_t_sysuser ae_t_sysuser=(Ae_t_sysuser) loginIds.get(b);
			//不包括自己
			if(!ae_t_sysuser.equals(getcuttSysuser())){
				List<SessionInformation> sessions_arrs = sessionRegistry.getAllSessions(ae_t_sysuser, false);
				for(SessionInformation sessions:sessions_arrs){
					sessions.expireNow();//这句强制T出了用户， (设置为过期）
				}
			}
			
		}
	}
 
	
}
