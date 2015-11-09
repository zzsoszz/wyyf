<%@ page language="java" contentType="text/html; charset=utf-8"	pageEncoding="utf-8"%>
<%@ include file="top.jsp"%>
<%@page import="java.util.List"%>
<%@page import="com.lys.utils.pagination.PageBean"%>
<%PageBean pages=(PageBean)request.getAttribute("pages");
String pid = (String)request.getAttribute("pid");
Integer num = (Integer)request.getAttribute("num");
%>
<%String path = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()+ request.getContextPath(); %>

<div class="dsigback">
	 <div class="dsignbox clearfix" style="">
        <div class="sessioncheck sessionhack" style="">
                <ul class="nav nav-tabs" id="mytabs">
                    <li class="active"><a href="#sy" data-toggle="tab">未支付<span>${cartNum }</span></a></li>
                    <li><a href="#bk" data-toggle="tab">已支付<span>${PaidNum }</span></a></li>
                </ul>
                <div class="tab-content">
                    
                    <div class="tab-pane fade in active" id="sy" style="">
                                              <!--未支付信息显示-->
						<div class="catbox">
							<table id="cartTable">
								<thead>
								<tr>
									<th><label>
										<input class="check-all check" type="checkbox">&nbsp;&nbsp;全选</label></th>
									<th>商品</th>
									<th>单价(元)</th>
									<th>数量</th>
									<th>金额</th>
									<th>操作</th>
								</tr>
								</thead>
								<tbody>
								<c:forEach items="${buy}" var="val" varStatus="i">
								
								<tr class="on" id="tr_${val.bn_st_id}">
									<td class="td_checkbox">
									<input name="id_pro" class="check-one check" type="checkbox" value='${val.bn_st_id}'></td>
									<td class="goods">
										<img src="${val.bg_st_img1 }" alt="">
										<span class="span1">
											${val.bg_st_name }
										    <p class="span2">库存：${val.bg_nm_storeNum }件</p>
										    <p class="span3">${val.bg_st_summary }</p>
										</span>
									</td>
									<td class="price">
										${val.bg_st_fbtpe==2?val.bg_st_pricetj :val.bg_st_pricezg }
										<p class="table_priceold">${val.bg_st_pricezg }</p>
										<div class="table_line" style="width: 50px;height: 2px;border-top:2px solid #999999;position: relative;left: 30px;top:-15px;"></div>
										<p class="iconfont icon-llpromotingtag"></p>
									</td>
									<td class="count">
										<div>
											<span class="reduce"></span>
											<input class="count-input" name="count-input_num" value="${val.bn_st_prodnum }" type="text">
											<span class="add">+</span>
										</div>
									</td>
									<td class="subtotal">${val.bg_st_fbtpe==2?val.bg_st_pricetj :val.bg_st_pricezg }</td>
									<td class="operation"  onclick="shop_delete('${val.bn_st_id}')"><span class="delete" >删除</span></td>
								</tr>
								</c:forEach>
								</tbody>
							</table>
							<div class="session_foot" id="session_foot">
								<label class="fl select-all"><input class="check-all check" type="checkbox">&nbsp;&nbsp;全选</label>
								<!-- a class="fl delete" id="deleteAll" onclick="">删除</a-->
								<div class="fr closing" onclick="getTotalPro()">结 算</div>
								<input id="cartTotalPrice" type="hidden">
								<div class="fr total">合计：￥<span id="priceTotal"></span></div>
								<div class="fr selected" id="selected">已选商品<span id="selectedTotal">${cartNum }</span>件<span class="arrow up">︽</span><span class="arrow down">︾</span></div>
								<div class="selected-view">
									<div id="selectedViewList" class="clearfix">
									<c:forEach items="${buy}" var="val" varStatus="i">
									<div>
									<img src="${val.bg_st_img1 }">
									<span class="del" index="0">取消选择</span>
									</div>
									</c:forEach>
									</div>
									<span class="arrow">◆<span>◆</span></span> </div>
							</div>
						</div>
                       <div class="height"> </div>
                     </div>
                    <div class="tab-pane fade clearfix" id="bk">
                    	<!--已支付信息显示-->
                    	<c:forEach items="${pages.getData() }" var="val" varStatus="i">
                    	 <table id="no_chexk" border="0" cellpadding="" cellspacing="">  
                     		<thead>
                     			<tr><td colspan="6">${val.bh_st_ddnum }&nbsp;<time>${val.bh_dt_addDate }</time></td>
                     		</tr></thead>
                     		<c:forEach items="${val.get('proList') }" var="valu" varStatus="i">
                     		<tbody>
                     			<tr><td class="table_mess clearfix">
                     				<a href=""><img src=${valu.bg_st_img1 }></a>
                     			    <span>${valu.bg_st_name }</span>
                     			    <span>${valu.bg_st_summary }</span>
                     			</td>
                     			<td char="table_price">
                     				 <strong class="table_pricenow">￥<span>${valu.bg_st_pricetj }</span></strong>
                     				 <p class="table_priceold">￥${valu.bg_st_pricezg }</p>
                     				  <span class="table_line" style=""></span>
                     				 <span class="iconfont icon-llpromotingtag"> </span>
                     			</td>
                     			<td class="table_num">
                     				<p>${valu.bi_st_spsl }</p>
                     			</td>
                     			<td class="table_countprice">
                     				<p>实付款</p>
                     				<strong class="redcolor">${valu.bh_st_spprice }</strong>
                     				<p>（含运费0.00）</p>
                     			</td>
                     			<td>
                     				<p>交易成功</p>
                     				<a href="index/toPingjia?id=${sysuser.ae_st_id }&bh_id=${valu.bg_st_id}&num=${valu.bi_st_spsl }"><span class="table_status" style="">去评价</span></a>
                     				<a href="tmoney.html"><span class="table_status" style="">申请退款</span></a>
                     			</td>
                     		</tr></tbody>
                     		</c:forEach>
                     	</table>
                     	</c:forEach>
                     	<!--分页-->
                      <p class="pagination">
			<span class="total"><%=pages.getCurrPageNumber() %>/<%=pages.getTotalPages() %></span>
			  <% if(pages.isFirst()){%>
			  
				 <%	}else{%>
						<a href="javascript:void(0);" start="<%=pages.getPrevious() %>" class="prev">« 上一页</a>	
				 <% }
				 	for(int i=pages.getBetweenIndex().getStartIndex();i<=pages.getBetweenIndex().getEndIndex();i++){
				 %>
				 		 <a 
				 	<%	 if(pages.getCurrPageNumber() == i){ %>
				 			style="color: red;"
				 	<%	 }  %>
				 		 href="javascript:void(0);" start="<%=i %>"><%=i %></a>
				 <% }  %>
				 <% if(pages.isLast()){ %>
				 <% }else{ %>
						<a  href="javascript:void(0);" start="<%=pages.getNext() %>" class="next">下一页 »</a>	
				 <% }  %>
		</p>
		<script>
			//翻页
			 $("p.pagination a").on('click', function() {
				 var start=$(this).attr("start");
				 location.href="index/buyCart?pid=0&num=0&page="+start;
			 });
		</script>
                    </div>
                </div>
        </div>
        <!-- div class="" style="clear: both;text-align: left;font-size: 18px;font-weight:bold">猜你喜欢</div>
		<div id="slide-box" class="slide-box">
			<div class="slide-content" id="temp9">
				<div class="wrap">
					<ul style="width: 1260px;" class="JQ-slide-content clearfix">
						<li class="tuijie_pepole clearfix">
							 <div class="tuijie_pepole_pic"><img src="images/555.jpg"></div>
    	                     <div class="zhifu_like_desci">路易活动撒淡红阿红isis佛挡杀佛胜多负少范德萨</div>
						     <span class="redcolor">RMB:<strong>150</strong></span>
						     <span class="zhifu_like_sale">已售出<span class="zhifu_ls_num">145</span></span>
						</li>
						<li class="tuijie_pepole clearfix">
							 <div class="tuijie_pepole_pic"><img src="images/3_22.png"></div>
    	                     <div class="zhifu_like_desci">路易活动撒淡红阿红isis佛挡杀佛胜多负少范德萨</div>
						     <span class="redcolor">RMB:<strong>150</strong></span>
						     <span class="zhifu_like_sale">已售出<span class="zhifu_ls_num">145</span></span>
						</li>
						<li class="tuijie_pepole clearfix">
							 <div class="tuijie_pepole_pic"><img src="images/666.jpg"></div>
    	                     <div class="zhifu_like_desci">路易活动撒淡红阿红isis佛挡杀佛胜多负少范德萨</div>
						     <span class="redcolor">RMB:<strong>150</strong></span>
						     <span class="zhifu_like_sale">已售出<span class="zhifu_ls_num">145</span></span>
						</li>
						<li class="tuijie_pepole clearfix">
							 <div class="tuijie_pepole_pic"><img src="images/c_23.png"></div>
    	                     <div class="zhifu_like_desci">路易活动撒淡红阿红isis佛挡杀佛胜多负少范德萨</div>
						     <span class="redcolor">RMB:<strong>150</strong></span>
						     <span class="zhifu_like_sale">已售出<span class="zhifu_ls_num">145</span></span>
						</li>
						<li class="tuijie_pepole clearfix">
							 <div class="tuijie_pepole_pic"><img src="images/3_22.png"></div>
    	                     <div class="zhifu_like_desci">路易活动撒淡红阿红isis佛挡杀佛胜多负少范德萨</div>
						     <span class="redcolor">RMB:<strong>150</strong></span>
						     <span class="zhifu_like_sale">已售出<span class="zhifu_ls_num">145</span></span>
						</li>
						<li class="tuijie_pepole clearfix">
							 <div class="tuijie_pepole_pic"><img src="images/3_22.png"></div>
    	                     <div class="zhifu_like_desci">路易活动撒淡红阿红isis佛挡杀佛胜多负少范德萨</div>
						     <span class="redcolor">RMB:<strong>150</strong></span>
						     <span class="zhifu_like_sale">已售出<span class="zhifu_ls_num">145</span></span>
						</li>
						<li class="tuijie_pepole clearfix">
							 <div class="tuijie_pepole_pic"><img src="images/3_22.png"></div>
    	                     <div class="zhifu_like_desci">路易活动撒淡红阿红isis佛挡杀佛胜多负少范德萨</div>
						     <span class="redcolor">RMB:<strong>150</strong></span>
						     <span class="zhifu_like_sale">已售出<span class="zhifu_ls_num">145</span></span>
						</li>
					</ul>
				</div>
				<div class="JQ-slide-nav">
					<a class="prev" href="#" style="">
						<b class="corner"></b>
						<span>‹</span>
						<b class="corner"></b>
					</a>
					<a class="next" href="#" style="">
						<b class="corner"></b>
						<span>›</span>
						<b class="corner"></b>
					</a>
				</div>
			</div>
		</div>
    </div>
</div-->

<div class="footer" style="background-color: white;padding-top: 40px;">
  	  <!--底部-->
  <div class="footer">
  	 <div class="footer1">
        <span>友情链接：</span>
        <span><a href="">搜房</a></span>
        <span class="index_footWrapper_vertical">|</span>
        <span><a href="">中国建筑新闻网——四川</a></span>
        <span class="index_footWrapper_vertical">|</span>
        <span><a href="">天府房产</a></span>
        <span class="index_footWrapper_vertical">|</span>
        <span><a href="">成都旅行社</a></span>
        <span class="index_footWrapper_vertical">|</span>
        <span><a href="">成都楼盘网201409</a></span>
    </div>
    <div class="footer2">
        <span>联系方式：</span>
        <span><a href="">关于我们</a></span>
        <span class="index_footWrapper_vertical">|</span>
        <span><a href="">联系方式</a></span>
        <span class="index_footWrapper_vertical">|</span>
        <span><a href="">免责申明</a></span>
        <span class="index_footWrapper_vertical">|</span>
        <span><a href="">反馈</a></span>
    </div>
    <div class="footer2" style="">
        <span><a href="">www.wangzhong.com</a></span>&nbsp;&nbsp;
        <span><a href="">成都网众天弘科技有限公司</a></span>&nbsp;&nbsp;
        <span><a href="">Copyrighgt©2015</a></span>&nbsp;&nbsp;
        <span><a href="">蜀ICP备15018106号-1</a></span>
    </div> 
    <div class="fixright">
		<div class="fixbox"><span class="glyphicon glyphicon-remove"></span></div>
		<a href="index/mysession"><div class="fixbox"><img src="images/icons_16.png" alt="购物车" title="购物车"></div></a>
		<div class="fixbox"><img src="images/icons_18.png" alt="分享" title="分享"></div>
		<div class="fixbox"><a id="gotopbtn" href="#top"><img src="images/icons_20.png" alt="回顶部" title="回顶部"></a></div>

	</div>
  </div>
</div>
<script type="text/javascript" src="js/eshop/mysession.js"></script>