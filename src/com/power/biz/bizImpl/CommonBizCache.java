package com.power.biz.bizImpl;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import net.sf.ehcache.Cache;
import net.sf.ehcache.Element;

import org.springframework.stereotype.Component;
import org.springframework.util.LinkedCaseInsensitiveMap;

import com.lys.mvc.dao.BaseDao;
import com.lys.system.dictionary.SysStatic;
import com.lys.system.filter.SafetyFilter;
import com.power.biz.CommonBiz;
import com.power.utils.MyKey;
import com.power.utils.PowerStatic.CacheKey;

/**
 * 数据缓存类
 * @author Administrator
 *
 */
@Component("CommonBizCache")
public class CommonBizCache implements CommonBiz {
	@Resource(name = "BaseDaoImpl")
	public BaseDao baseDao;
	@Resource(name="defaultCache")
	private Cache cache;

	/**
	 * 取得所有权限
	 * @return 权限的map集合
	 */
	@Override
	@MyKey(key=CacheKey.getALLfunction_KEY)
	public Map<String, Object> getALLfunction() {
		Map<String,Object> powermap=new LinkedCaseInsensitiveMap<Object>();//不区分大小写
		List<Map<String,Object>> functions=baseDao.queryForList("select aa_st_classUrl,aa_st_mark,aa_st_islog from aa_t_sysfunction where aa_st_active='1'  ");
		for (Map<String,Object> map : functions) {
			powermap.put(map.get("aa_st_classUrl").toString(), map);
		}
		return powermap;
	}
	/**
	 * 添加一个权限到权限集合中
	 * @return
	 */
	@Override
	public boolean addALLfunction(String classUrl,String mark){
		if(org.springframework.util.StringUtils.hasText(classUrl)&&org.springframework.util.StringUtils.hasText(mark)){
			String cacheKey=CacheKey.getALLfunction_KEY.toString();
			Element element=cache.get(cacheKey);
			if(element!=null){
				@SuppressWarnings("unchecked")
				Map<String, String> map=(Map<String, String>) element.getValue();
				map.put(classUrl, mark);
				element = new Element(cacheKey, (Serializable) map);
				cache.put(element);
				SafetyFilter.logger.info(new StringBuffer("===更新==缓存key：").append(cacheKey).append("，添加了新值：").append(classUrl).append("+++").append(mark).append("，目前value：").append(map).toString());
				return true;
			}else{
				return false;
			}
		}else{
			return false;
		}
	}
	/**
	 * 删除权限缓存
	 * @return
	 */
	@Override
	public boolean delALLfunction(){
		SafetyFilter.logger.info(new StringBuffer("===移除==缓存key：").append(CacheKey.getALLfunction_KEY.toString()));
		return cache.remove(CacheKey.getALLfunction_KEY.toString());
	}
	
	/**
	 * 取得所有字典
	 * @return 字典的map集合
	 */
	@Override
	@MyKey(key=CacheKey.getALLcode_KEY)
	public Map<String,Map<String,Object>> getALLcode() {
		Map<String,Map<String,Object>> powermap=new HashMap<String, Map<String,Object>>();
		//执行的sql
		StringBuffer sbsql=new StringBuffer();
		//MYSQL
		if(SysStatic.MYSQL.equals(SysStatic.dbName)){
			sbsql.append("select a.ab_st_id,a.ab_st_mark,a.ab_st_name,a.ab_st_value,(select group_concat(ab_st_mark order by ab_nm_orderno asc) from ab_t_code where ab_st_parent=a.ab_st_id) child from ab_t_code  a ");
		}
		//ORACLE
		else if(SysStatic.ORACLE.equals(SysStatic.dbName)){
			sbsql.append("select a.ab_st_id,a.ab_st_mark, a.ab_st_name,a.ab_st_value,b.orderno child")
			.append("  from ab_t_code a, ")
			.append(" (select k.orderno, k.ab_st_parent ")
			.append("  from (select orderno, n.ab_st_parent,row_number() over(partition by n.ab_st_parent order by length(orderno) desc) rn ")
			.append(" from (select WMSYS.WM_CONCAT(m.ab_st_mark) over(partition by m.ab_st_parent order by m.ab_nm_orderno asc) orderno, m.ab_st_parent ")
			.append(" from ab_t_code m) n) k where k.rn = 1) b ")
			.append(" where a.ab_st_id = b.ab_st_parent(+) ");
		}
		List<Map<String,Object>> functions=baseDao.queryForList(sbsql.toString());
		for (Map<String,Object> map : functions) {
			powermap.put(map.get("ab_st_mark").toString(),map);
		}
		//为powermap添加子节点
		for(Map.Entry<String, Map<String,Object>> entry : powermap.entrySet()){
			Map<String,Object> map=entry.getValue();
			if(map!=null&&map.get("child")!=null){
				String child=map.get("child").toString();
				String[] childs=child.split(",");
				List<Map<String,Object>> list=new ArrayList<Map<String,Object>>();
				for(String s:childs){
					list.add(powermap.get(s));
				}
				map.put("childs", list);
				entry.setValue(map);
			}
        }
		return powermap;
	}
	/**
	 * 删除字典缓存
	 * @return
	 */
	@Override
	public boolean delALLcode(){
		SafetyFilter.logger.info(new StringBuffer("===移除==缓存key：").append(CacheKey.getALLcode_KEY.toString()));
		return cache.remove(CacheKey.getALLcode_KEY.toString());
	}
	
}
