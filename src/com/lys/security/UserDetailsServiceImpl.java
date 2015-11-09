package com.lys.security;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.util.LinkedCaseInsensitiveMap;

import com.lys.mvc.biz.BaseBiz;
import com.lys.security.exception.AccessDeniedException;
import com.lys.utils.BeanUtils;
import com.power.bean.Ae_t_sysuser;

/**
 * SPRING SECURITY3用户登录处理
 * @author shuang
 */
public class UserDetailsServiceImpl implements UserDetailsService {
	@Resource(name="BaseBizImpl")
	public BaseBiz baseBiz;
	
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		if(org.springframework.util.StringUtils.hasText(username)){
			// 使用User服务类查询数据用户是否存在,如不存在或密码错误则抛出对应的异常
			List<Map<String,Object>> users=baseBiz.queryForList(" select a.ae_st_id,a.ae_st_objid,a.ae_st_objtype,a.ae_st_userkind,a.ae_st_lockstate,a.ae_st_logoffreason,a.ae_nm_loginedcount,a.username,a.password,a.ae_st_nickName from ae_t_sysuser a where a.username=? and a.ae_st_lockstate!='3' ",new Object[]{username});
			if (null == users || users.isEmpty())
				throw new UsernameNotFoundException("用户/密码错误,请重新输入!");
			//查询当前用户的所有角色
			Ae_t_sysuser user = (Ae_t_sysuser) BeanUtils.turnBean(users.get(0), Ae_t_sysuser.class,false,true)  ;
			//如果帐号停用
			if("2".equals(user.getAe_st_lockstate())){
				throw new AccessDeniedException("用户审核不通过，请联系网站管理员!");
			}
			//查询该用户的权限
			List<Map<String,Object>> funcitons = baseBiz.queryForList("select c.aa_st_mark,c.aa_st_classUrl from af_t_sysuserrole a,ad_t_sysrolefunction b,aa_t_sysfunction c where 1=1 and a.ac_st_id=b.ac_st_id and b.aa_st_id=c.aa_st_id   and c.aa_st_active='1' and a.ae_st_id=?",new Object[]{user.getAe_st_id()});
			if (null == funcitons || funcitons.isEmpty())
				throw new AccessDeniedException("权限不足，没有任何权限!");
			//把权限赋值给当前对象
			Collection<GrantedAuthority> gafuncitons = new ArrayList<GrantedAuthority>();
			Map<String,String> purviews=  new LinkedCaseInsensitiveMap<String>();
			for (Map<String,Object> map : funcitons) {
				gafuncitons.add(new SimpleGrantedAuthority(map.get("aa_st_mark").toString()));//这里的用做权限访问url 时候的判断,可用作页面某些元素的显示控制
				purviews.put(map.get("aa_st_classUrl").toString(), map.get("aa_st_mark").toString());
			}
			user.setAuthorities(gafuncitons);
			user.setFunctions(purviews);
			return user;
		}else{
			throw new UsernameNotFoundException("用户/密码错误,请重新输入!");
		}
		
	}

}