<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ include file="top.jsp"%>
<div class="dsigback">
	<div class="dsignbox clearfix" style="">
		<div class="sessioncheck sessionhack" style="">
			<div class="tab-content" style="min-height:310px;">
				<div class="" id="sy" style="">
					<!--未支付信息显示-->
					<div class="catbox">
						<table id="cartTable">
							<thead>
								<tr>
									<th><label> <input class="check-all check"
											type="checkbox">&nbsp;&nbsp;全选</label>
									</th>
									<th>商品</th>
									<th>单价(元)</th>
									<th>数量</th>
									<th>金额</th>
									<th>操作</th>
								</tr>
							</thead>
							<tbody>
								<c:forEach items="${BuyCarList}" var="val" varStatus="i">
									<tr class="on" id="tr_${val.bn_st_id}" >
										<td class="td_checkbox">
											<input name="id_pro" class="check-one check" type="checkbox" value='${val.bn_st_id}' /> 
										</td>
										<td class="goods"><img id="${val.bn_st_prodid}" src="${val.bg_st_img1 }" alt="">
											<span class="span1"> ${val.bg_st_name }
												<p class="span2">库存：${val.bg_nm_storeNum }件</p>
												<p class="span3">${val.bg_st_summary }</p> </span></td>
										<td class="price">${val.bg_st_fbtpe==2?val.bg_st_pricetj
											:val.bg_st_pricezg }
											<p class="table_priceold">${val.bg_st_pricezg }</p>
											<div class="table_line"
												style="width: 50px;height: 2px;border-top:2px solid #999999;position: relative;left: 30px;top:-15px;"></div>
											<p class="iconfont icon-llpromotingtag"></p></td>
										<td class="count">
											<div>
												<span class="reduce"></span> <input class="count-input"
													name="count-input_num" value="${val.bn_st_prodnum }"
													type="text"> <span class="add">+</span>
											</div></td>
										<td class="subtotal">${val.bg_st_fbtpe==2?val.bg_st_pricetj*val.bn_st_prodnum
											:val.bg_st_pricezg*val.bn_st_prodnum }</td>
										<td class="operation" onclick="shop_delete('${val.bn_st_id}')"><span
											class="delete">删除</span>
										</td>
									</tr>
								</c:forEach>
							</tbody>
						</table>
						<div class="session_foot" id="session_foot">
							<label class="fl select-all"><input type="checkbox"
								class="check-all check">&nbsp;&nbsp;全选</label> <a
								class="fl delete" id="deleteAll" href="javascript:;">删除</a>
							<div class="fr closing" onclick="javascript:createOrder();">
								<a href="javascript:void(0);">结 算</a>
							</div>
							<input type="hidden" id="cartTotalPrice">
							<div class="fr total">
								合计：￥<span id="priceTotal">19500.00</span>
							</div>
							<div class="fr selected" id="selected">
								已选商品<span id="selectedTotal">3</span>件<span class="arrow up">︽</span><span
									class="arrow down">︾</span>
							</div>
							<div class="selected-view">
								<div id="selectedViewList" class="clearfix">
									<c:forEach items="${BuyCarList}" var="val" varStatus="i">
										<div>
											<a href="CancelProduct(${val.bn_st_id})"> <img
												class="del" src="${val.bg_st_img1 }" index="0">取消选择</span> </a>
										</div>
									</c:forEach>
								</div>
								<span class="arrow">◆<span>◆</span> </span>
							</div>
						</div>
					</div>
					<div class="height"></div>
				</div>
			</div>
		</div>

		
	</div>
</div>

<script type="text/javascript" src="js/eshop/mysession.js"></script>
<jsp:include page="foot.jsp" />