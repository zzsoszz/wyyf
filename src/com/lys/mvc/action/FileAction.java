package com.lys.mvc.action;
import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.OutputStream;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.io.FileUtils;
import org.springframework.context.annotation.Scope;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.lys.mvc.action.BaseAjaxAction;
import com.lys.system.dictionary.SysStatic;
import com.lys.system.filter.SafetyFilter;
import com.lys.utils.UploadFile;


/**
 * 图片查看、下载、file文件下的静态文件权限控制等
 * @author Administrator
 *
 */

@Scope(value = "prototype")
@Controller("FileAction")
@RequestMapping
public class FileAction extends BaseAjaxAction{ 
	/**
	 * 支持图片显示
	 * 加载图片
	 * @return
	 */
	@RequestMapping(value="/files/images/**")  
	 public ResponseEntity<byte[]> download(HttpServletRequest req) throws IOException {  
		String filepath = SysStatic.fileUploadPathDefault + req.getServletPath();
		try {
			File file = new File(filepath);
			HttpHeaders headers = new HttpHeaders();
			headers.setContentType(MediaType.APPLICATION_OCTET_STREAM);
			return new ResponseEntity<byte[]>(FileUtils.readFileToByteArray(file), headers, HttpStatus.CREATED);
		} catch (Exception e) {
			SafetyFilter.logger.error("图片加载失败：" + filepath);
			return null;
		}

	 }  
	/**
	 * 删除文件
	 * delurl 文件相对路径
	 * @return
	 */
	@RequestMapping(value = "/files/delfiles")
	public void delFileByUrl(HttpServletResponse response,String delurl){
		String jsonData = "";
		//判断是否为修改
		if(org.springframework.util.StringUtils.hasText(delurl)){
			//删除文件
			if(UploadFile.delpic(delurl)){
				jsonData = "{success:true,msg:\"删除成功!\"}";
			}else{
				jsonData = "{success:false,msg:\"删除失败!\"}";
			}
		}else{
			jsonData = "{success:false,msg:\"未获取文件地址!\"}";
		}
		this.createAjax(response, jsonData);
	}
	 /**
	  * 文件下载
	  * @param req
	  * @return
	  * @throws IOException
	  */
	 @RequestMapping(value="/files/files/**")  
	 public void downloadFiles(HttpServletResponse response,HttpServletRequest request,String name) {
		 BufferedInputStream buff=null;
		 OutputStream myout=null;
		 FileInputStream fis=null;
		 try {
			 	response.setContentType("text/html; charset=UTF-8");
		        response.setContentType("application/x-msdownload");//设置response的编码方式
		        String filepath=request.getServletPath();//new String(request.getServletPath().getBytes("iso-8859-1"),"utf8");
		        File file=new File(SysStatic.fileUploadPathDefault+filepath); //创建file对象
		        response.setContentLength((int)file.length());//写明要下载的文件的大小
		        String agent = request.getHeader("User-Agent");
				boolean isMSIE = (agent != null && agent.indexOf("MSIE") != -1);
				
				String fileName=file.getName();
				if(org.springframework.util.StringUtils.hasText(name)){
					fileName=name;
				}
				//如果是IE
				if(isMSIE){
					response.setHeader("Content-Disposition", "attachment;filename=" + java.net.URLEncoder.encode(fileName, "UTF8") ); 
				}else{
					response.setHeader("Content-Disposition","attachment;filename="+new String(fileName.getBytes("utf-8"),"iso-8859-1"));//解决中文乱码  ,并设置下载模式：为下载文件，不再是直接在浏览器打开的那种
				}
		        fis=new FileInputStream(file); //读出文件到i/o流
		        buff=new BufferedInputStream(fis);
		        byte [] b=new byte[1024];//相当于我们的缓存
		        long k=0;//该值用于计算当前实际下载了多少字节
		        myout=response.getOutputStream();//从response对象中得到输出流,准备下载
		        while(k<file.length()){
		            int j=buff.read(b,0,1024);
		            k+=j;
		            myout.write(b,0,j);//将b中的数据写到客户端的内存
		        }
		        myout.flush();//将写入到客户端的内存的数据,刷新到磁盘
		        
		} catch (FileNotFoundException e) {
			SafetyFilter.logger.error("-------------->要下载的文件没有找到！");
		} catch (IOException e) {
			SafetyFilter.logger.error("-------------->下载异常！");
		}finally{
			try {
				if(myout!=null)
					myout.close();
				if(buff!=null)
					buff.close();
				if(fis!=null)
					fis.close();
			} catch (IOException e) {
				SafetyFilter.logger.error("-------------->关闭流异常！");
			}
		}
	 } 
}
