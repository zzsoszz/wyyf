package com.wyyf.action;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.URLDecoder;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.annotation.Resource;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.springframework.util.LinkedCaseInsensitiveMap;

import com.lys.mvc.action.BaseAjaxAction;
import com.lys.system.dictionary.SysStatic;
import com.lys.utils.BizTransUtil;
import com.lys.utils.DateConverUtil;
import com.lys.utils.JsonUtils;
import com.lys.utils.StringUtils;
import com.lys.utils.UploadFile;
import com.power.bean.Ag_t_file;
import com.wyyf.action.dx.HttpSender;
import com.wyyf.bean.Bf_t_Apply;

/**
 * 平民设计接口--- 因为多图上传，  需要  采用流的形式。 
 * @author han
 *
 */
public class CivilianDesignServlet extends HttpServlet{

	@Override
	public void doPost(HttpServletRequest request, HttpServletResponse response)throws ServletException, IOException{
		request.setCharacterEncoding("UTF-8"); 
		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
		// 创建文件项目工厂对象
		DiskFileItemFactory factory = new DiskFileItemFactory();
		// 设置文件上传路径
		String jsonData = "";
		//String upload = this.getServletContext().getRealPath("/upload/");
		// 获取系统默认的临时文件保存路径，该路径为Tomcat根目录下的temp文件夹
		String temp = System.getProperty("java.io.tmpdir");
		// 设置缓冲区大小为 5M
		factory.setSizeThreshold(1024 * 1024 * 5);
		// 设置临时文件夹为temp
		factory.setRepository(new File(temp));
		// 用工厂实例化上传组件,ServletFileUpload 用来解析文件上传请求
		ServletFileUpload servletFileUpload = new ServletFileUpload(factory);
//		SysStatic.baseBiz
		//status用于标示Android或ios
		String isIos="";
		String fileurl = ""; 
		StringBuffer fileurls=new StringBuffer();
		// 解析结果放在List中
		try{
			List<BizTransUtil> listSql = new ArrayList<BizTransUtil>();
			//判断是否存在文件
			boolean isMultipart = ServletFileUpload.isMultipartContent(request);
			Map maps = new HashMap<>();
			String loginflag="";
			String bf_st_name = ""; //姓名
			String bf_st_tell =""; //电话
			String bf_dt_time = ""; //时间
			String bf_st_address = ""; //地址
			String bf_st_remark = ""; //备注
			String bf_st_type = "";//类型
			String bf_st_receiveid="";
			String bf_st_userid = "";
			String price="";
			Bf_t_Apply apply = new Bf_t_Apply(true);
			if(isMultipart){
				List<FileItem> list2 =  servletFileUpload.parseRequest(request);
				for (FileItem item : list2){
					String videoUrl=UUID.randomUUID().toString();
					fileurl ="images/business/apply/"+videoUrl+ ".jpg";
					fileurls.append(SysStatic.fileUploadPathDefault+fileurl+",");
					InputStream is = item.getInputStream();
					String filedname = item.getFieldName();	
					if (filedname.contains("file")) {
						inputStream2File(is,SysStatic.fileUploadPathDefault+fileurl);
						if(org.springframework.util.StringUtils.hasText(fileurl)){
							Ag_t_file file = new Ag_t_file();
							file.setAg_st_id(StringUtils.getUUID32());
							file.setAg_st_objid(apply.getBf_st_id());
							file.setAg_st_objtype("bf_t_apply");
							file.setAg_st_type("1");
							file.setAg_st_url(fileurl);
							file.setAg_nm_orderno(0);
							file.setAg_st_mark("SJ");
							listSql.add(new BizTransUtil(file));
						}
					}else{
						//处理表单数据，获取到json数据
						String json = item.getString(); 
						maps=(Map) JsonUtils.getBeanFromJsonData(json,LinkedCaseInsensitiveMap.class);//解析数据
						price = StringUtils.toStringByObject(maps.get("price"),true);//jiage
						bf_st_name = StringUtils.toStringByObject(maps.get("bf_st_name"),true); //姓名
						bf_st_receiveid = StringUtils.toStringByObject(maps.get("bf_st_receiveid"),true); //赴约人
						bf_st_userid=StringUtils.toStringByObject(maps.get("bf_st_userid"),true); //登录标识
						
						bf_st_tell = StringUtils.toStringByObject(maps.get("bf_st_tell"),true); //电话
						bf_dt_time = StringUtils.toStringByObject(maps.get("bf_dt_time"),true); //时间
						bf_st_address = StringUtils.toStringByObject(maps.get("bf_st_address"),true); //地址
						bf_st_remark = StringUtils.toStringByObject(maps.get("bf_st_remark"),true); //备注
						isIos = StringUtils.toStringByObject(maps.get("isIos"),true);//浏览权限设置-对应选择手机用户ID 
						bf_st_type=StringUtils.toStringByObject(maps.get("bf_st_type"),true);
						
						
						//用于处理ios乱码
						if("1".equals(isIos)){
							bf_st_name = new String(bf_st_name.getBytes("ISO-8859-1"), "utf-8");  
							bf_st_address =new String(bf_st_address.getBytes("ISO-8859-1"), "utf-8");  
							bf_st_remark =new String(bf_st_remark.getBytes("ISO-8859-1"), "utf-8");  
						}
					}
				}
			}//只包含文字
			else{
				StringBuffer sb = new StringBuffer();
				InputStream iss = request.getInputStream();
				InputStreamReader isr = new InputStreamReader(iss);
				BufferedReader br = new BufferedReader(isr);
				String s = "";
				while ((s = br.readLine()) != null) {
					sb.append(s + "\n");
				}
				String str = sb.toString();
				String json=URLDecoder.decode(str,"UTF-8");
				String jsons =json.substring(5,json.length());
				maps=(Map) JsonUtils.getBeanFromJsonData(jsons,LinkedCaseInsensitiveMap.class);//解析数据
				bf_st_name = StringUtils.toStringByObject(maps.get("bf_st_name"),true); //姓名
				bf_st_tell = StringUtils.toStringByObject(maps.get("bf_st_tell"),true); //电话
				bf_dt_time = StringUtils.toStringByObject(maps.get("bf_dt_time"),true); //时间
				bf_st_address = StringUtils.toStringByObject(maps.get("bf_st_address"),true); //地址
				bf_st_remark = StringUtils.toStringByObject(maps.get("bf_st_remark"),true); //备注
				isIos = StringUtils.toStringByObject(maps.get("isIos"),true);//浏览权限设置-对应选择手机用户ID 
				//用于处理ios乱码
				if("1".equals(isIos)){
					bf_st_name = new String(bf_st_name.getBytes("ISO-8859-1"), "utf-8");  
					bf_st_address =new String(bf_st_address.getBytes("ISO-8859-1"), "utf-8");  
					bf_st_remark =new String(bf_st_remark.getBytes("ISO-8859-1"), "utf-8");  
				}
			}
			//构建数据类型
			apply.setBf_dt_time(DateConverUtil.getDbyST(bf_dt_time, "yyyy-MM-dd"));
			apply.setBf_st_address(bf_st_address);
			apply.setBf_st_name(bf_st_name);
			apply.setBf_st_type(bf_st_type);
			apply.setBf_st_tell(bf_st_tell);
			apply.setBf_st_remark(bf_st_remark);
			String orderNo="D"+new Date().getTime()+(int)(Math.random()*90000+10000);  //生成订单号
			apply.setOrderNo(orderNo);
			if(apply.getBf_st_type().equals("5"))
			{
			apply.setBf_st_receiveid(bf_st_receiveid);
			}
		    else if(apply.getBf_st_type().equals("4")){
				 apply.setBf_st_receiveid("e58e7927a2c9436788aefb219539bb7e");
			 }
			apply.setBf_st_userid(bf_st_userid);
			apply.setBf_dt_addDate(new Date());
			listSql.add(new BizTransUtil(apply));
			
			
		//执行事务操作
		if(org.springframework.util.StringUtils.hasText(bf_st_type)&&SysStatic.baseBiz.executesTRANS(listSql)){
			String uri = "http://222.73.117.158/msg/";// 应用地址
			String account = "wangzhong";// 账号
			String pswd = "Tch147258";// 密码
			boolean needstatus = true;// 是否需要状态报告，需要true，不需要false
			String product = null;// 产品ID
			String extno = null;// 扩展码
			String returnString =null;
		   if(apply.getBf_st_type().equals("5")) {
				//returnString="恭喜您成功申请了网众科技旗下平台的个性设计,我们会在24内和你联系，请注意接听电话   网众科技客户热线  400-028-5998";
				returnString= HttpSender.batchSend(uri, account, pswd, bf_st_tell, "恭喜您成功申请了网众科技旗下平台的个性设计,我们会在24内和你联系，请注意接听电话   网众科技客户热线  400-028-5998", needstatus, product, extno);
				//jsonData = "{\"success\":\"true\",\"msg\":\"申请成功！\"}";
				jsonData = "{\"success\":\"true\",\"flag\":\"1\",\"orderNo\":\""+orderNo+"\",\"msg\":\"预约成功！\"}";
			}
		   else if(apply.getBf_st_type().equals("4"))
			{
				returnString= HttpSender.batchSend(uri, account, pswd, bf_st_tell, "恭喜您成功申请了平民设计,我们会在24内和你联系，请注意接听电话", needstatus, product, extno);
			jsonData = "{\"success\":\"true\",\"msg\":\"申请成功！\"}";
			//}
		    } 
		   else 
		   {
			jsonData = "{\"success\":\"false\",\"flag\":\"4\",\"msg\":\"申请失败！\"}";
			String[] fileurlarray = fileurls.toString().split(",");
			for(int i=0;i<fileurlarray.length;i++){
				UploadFile.delpic(fileurlarray[i]);
			}
		}
		}
		}
		catch (FileUploadException e){
			jsonData = "{\"success\":\"false\",\"flag\":\"5\",\"msg\":\""+e.getMessage()+"\"}";
			e.printStackTrace();
		}catch (RuntimeException e) {
			jsonData = "{\"success\":\"false\",\"flag\":\"3\",\"msg\":\"登录标识已经失效，请重新登录！\"}";
			e.printStackTrace();
		}  catch (Exception e) {
			jsonData = "{\"success\":\"false\",\"flag\":\"1\",\"msg\":\"解析数据异常！\"}";
			e.printStackTrace();
		}
		//response.setContentType("text/plain");
		out.print(jsonData);
		out.flush();
		out.close();
	}
		
	// 流转化成字符串
	public static String inputStream2String(InputStream is) throws IOException{
		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		int i = -1;
		while ((i = is.read()) != -1){
			baos.write(i);
		}
			return baos.toString();
		}
	
	// 流转化成文件
	public static void inputStream2File(InputStream is, String savePath) throws Exception{
		System.out.println("文件保存路径为:" + savePath);
		File file = new File(savePath);
		InputStream inputSteam = is;
		BufferedInputStream fis = new BufferedInputStream(inputSteam);
		FileOutputStream fos = new FileOutputStream(file);
		int f;
		while ((f = fis.read()) != -1){
			fos.write(f);
		}
		fos.flush();
		fos.close();
		fis.close();
		inputSteam.close();
	}
}
