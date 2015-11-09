<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ include file="../top.jsp"%>
<%String path = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()+ request.getContextPath(); %>

<script type="text/javascript">

</script>
<div class="payfor_con">
	 <div class="payfor_box" style="">	
	 	<div class="payfor_item" style="">选择收货地址</div>
		 	 <table class="paufor_addr" border="0" cellpadding="20" cellspacing="30" width="1000">
		 	 	 <tbody id="userAddr">
		 	 	 <c:forEach items="${userAddress }" var="val" varStatus="i">
			 	 	 <tr id="${val.bq_st_id}" class=" <c:if test="${i.index==1}">outline</c:if> ">
			 	 	 	<td id="adrss_${val.bq_st_id}_name">${val.bq_st_name }</td>
			 	 	 	<td id="adrss_${val.bq_st_id}_ads">${val.bq_st_sheng }-${val.bq_st_shi }-${val.bq_st_xian }-${val.bq_st_adress }</td>
			 	 	 	<td id="adrss_${val.bq_st_id}_phone">${val.bq_st_phone }</td>
			 	 	 	<td id="adrss_${val.bq_st_id}_edit"><a onclick="Edit('${val.bq_st_id}','${val.bq_st_phone }','${val.bq_st_name }','${val.bq_st_sheng }','${val.bq_st_shi }','${val.bq_st_xian }','${val.bq_st_adress }')">修改</a></td>
			 	 	 </tr>
			 	 	 </c:forEach>
		 	    </tbody>
		 	 </table>
		 	 <div class="payfor_newaddr" style="">
		 	    <span class="shoporder_newaddr">使用新地址</span>
		 	 </div>
	 	     <div class="payfor_item" style="">确认订单信息</div>
	 	     
	 	   	 <table class="shop_goodstable" style="" border="0" cellpadding="" cellspacing="">                     	
                     		<thead>
                     			<tr>
                     			<th class="table_hmess">商品信息</th>
                     			<th class="table_hprice">单价（元）</th>
                     			<th class="table_hnum">数量</th>
                     			<th class="table_hcount">优惠</th>
                     			<th class="table_hcaozuo">小计（元）</th>
                     			</tr>
                     		</thead>
                     		<c:forEach items="${productRelateList }" var="val" varStatus="i">
                     		<tbody>
                     			<tr>
                     			<td class="table_mess clearfix">
                     				<a href="index/shopmess?id=${val.product.bg_st_id}"><img src="${val.product.bg_st_img1}" width="100px" height="100px"></a>
                     			    <span>${val.product.bg_st_summary}</span>
                     			    <!-- <p>颜色分类：红色</p>
                     			    <p>规格：50ml</p> -->
                     			</td>
                     			<td char="table_price">
                     				 <strong class="table_pricenow">￥<span>${val.product.bg_st_fbtpe==2?val.product.bg_st_pricetj :val.product.bg_st_pricezg }</span></strong>
                     				 <p class="table_priceold">￥${val.product.bg_st_pricezg}</p>
                     				  <span class="table_line" style=""></span>
                     				 <span class="iconfont icon-llpromotingtag"> </span>
                     			</td>
                     			<td class="table_num">
                                           ${val.bi_st_spsl}   
                     			</td>
                     			<td class="table_countprice">
                     				<!--select name="shop_youhui">
                     					<option value="20">20元优惠券</option>
                     					<option value="50">50元优惠券</option>
                     					<option value="100">100元优惠券</option>
                     				</select-->
                     			</td>
                     			<td>
                     				<span class="table_delete"><strong class="redcolor">${val.bi_st_spprice*val.bi_st_spsl}元</strong></span>
                     			</td>
                     		    </tr>
                     		</tbody>
                     		</c:forEach>
               </table>
               
	 	       <div class="shop_ps">备注:<input type="text" id="remark"></div>
			   <div class="payfor_bottom clearfix" style="">
			 		 <!-- div>
			 		 <span>使用米奇币</span>
			 		 <input placeholder="您有120个" type="text">个<span class="redcolor">-xx.xx元</span>
			 		 </div-->
			 		<div>实际支付金额：<strong class="redcolor">￥${order.bh_st_spprice}</strong></div>
			 		<!--  a href="index/payfor?orderID='${orderID} "><div class="qrzf">确认支付 </div></a-->
			 		<a onclick="payfor('${order.bh_st_id}')"><div class="qrzf">确认支付 </div></a>
			   </div>
			   
 </div>
</div>
	 	
<!--<div class="zhezhao"></div>	 	-->
<div class="shoporder_addrnewbox" style="">
	 <div class="shoporder_anbtop clearfix">地址信息<span class="closeshoporder"><i class="glyphicon  glyphicon-remove"></i></span> </div>
     <form action="" method="post" class="shoporder_form">
     <input type="hidden" id="id_add" value="0">
     	 <div class="baoming_in">
     	 	<span>收货人：</span><input id="name" type="text" />
     	 </div>
     	 <div class="baoming_in">
     	 	<span>联系电话：</span><input id="phone" type="text" />
     	 </div>
     	 <div class="baoming_in">
     	 	<span>收货地址：</span>
     	 	<select name="sheng" id="province" class="sheng" onchange="province_change();">
     	 		<option value="">--请选择--</option>
     	 	</select>-
     	 	<select name="shi" id="city" class="shi" onchange="city_change();">
     	 		<option value="">--请选择--</option>
     	 	</select>
     	 	<select name="xian" id="district" class="xian">
     	 		<option value="">--请选择--</option>
     	 	</select>
     	 </div>
     	 <div class="baoming_in">
     	 	<span></span><input type="text" id="addr" placeholder="详细地址" />
     	 </div>
     	 <!-- div class="baoming_in">
     	 	<span>邮编：</span><input type="text" id="_post"/>
     	 </div-->
     	<input type="button" id="submitNewAdd" value="提交数据"/>
     </form>
</div>	 	
<script type="text/javascript" src="js/eshop/buy.js"></script>
<%@ include file="../foot.jsp"%>
<script>
</script>