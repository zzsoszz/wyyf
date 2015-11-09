<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@page import="com.lys.utils.pagination.PageBean"%>
<%@page import="java.util.Map"%>
<%@page import="java.util.List"%>
<%@page import="com.lys.utils.*"%>
<%@ include file="../top.jsp"%>

<!--选择框-->
<div class="dsigback" style="">
	<form action="index/pingjiaEnd" method="post" enctype="multipart/form-data" onsubmit="return  validatepinjia();">
    <div class="dsignbox">
        <div class="designshow_boxtop clearfix sppj" style="text-align: left;">
            <div class="spmess" style="">
                商品信息
            </div>
            <div class="designshow_boxtop_left" style="">
                <img src="${projectInfo.bg_st_img1}">
            </div>
            <div class="designshow_boxtop_right" style="">
                <div>
                    商品名称：${projectInfo.bg_st_name}
                </div>
                <div>
                    规格：${projectInfo.bg_st_norms}
                </div>
                <div>
                    颜色：${projectInfo.bg_st_color}
                </div>
                <div>
                    数量:${num}
                </div>
            </div>
            <br class="clear" />
            <div class="mygz_ljpj" style="">
                立即评价
                <span>
                    您的认可是我们最大的动力~
                </span>
                <span class="pjknowns" style="display: inline-block;width: 100px;height: 35px;cursor: pointer;"
                onfocus="pjfocus()" onblur="pjblur()">
                    评价须知
                </span>
            </div>
            <div class="clearfix" style="width: 100%;">
                <div class="f_fl" style="width: 40%;">
                    <p class="yellow">
                        认真写评价最高可获得米币50个！
                    </p>
                    <div class="mypjstar">
                        服务态度：
                        <span onclick="setStart1(1)" class="star glyphicon glyphicon-star">
                        </span>
                        <span onclick="setStart1(2)" class="star glyphicon glyphicon-star">
                        </span>
                        <span onclick="setStart1(3)" class="star glyphicon glyphicon-star">
                        </span>
                        <span onclick="setStart1(4)" class="star glyphicon glyphicon-star">
                        </span>
                        <span onclick="setStart1(5)" class="star glyphicon glyphicon-star">
                        </span>
                    </div>
                    <div class="mypjstar">
                        服务诚信：
                        <span onclick="setStart2(1)" class="star glyphicon glyphicon-star">
                        </span>
                        <span onclick="setStart2(2)" class="star glyphicon glyphicon-star">
                        </span>
                        <span onclick="setStart2(3)" class="star glyphicon glyphicon-star">
                        </span>
                        <span onclick="setStart2(4)" class="star glyphicon glyphicon-star">
                        </span>
                        <span onclick="setStart2(5)" class="star glyphicon glyphicon-star">
                        </span>
                    </div>
                    <div class="mypjstar">
                        服务质量：
                        <span onclick="setStart3(1)" class="star glyphicon glyphicon-star">
                        </span>
                        <span onclick="setStart3(2)" class="star glyphicon glyphicon-star">
                        </span>
                        <span onclick="setStart3(3)" class="star glyphicon glyphicon-star">
                        </span>
                        <span onclick="setStart3(4)" class="star glyphicon glyphicon-star">
                        </span>
                        <span onclick="setStart3(5)" class="star glyphicon glyphicon-star">
                        </span>
                    </div>
                    <div class="pj_pic clearfix">
                        <p>
                            上传图片
                        </p>
                        <div class="dk_hkb_pic pic_unload">
                            <div class="pic_unload_over">
                                <span class="uploadImg">
                                    <input type="file" class="file" name="file" size="1" style=" ">
                                    <a href="#" style="">
                                        <img src="images/q_11.png" width="90" height="90">
                                    </a>
                                </span>
                            </div>
                        </div>
                        <div class="dk_hkb_pic pic_unload">
                            <div class="pic_unload_over">
                                <span class="uploadImg">
                                    <input type="file" class="file" name="file" size="1" style=" ">
                                    <a href="#" style="">
                                        <img src="images/q_11.png" width="90" height="90">
                                    </a>
                                </span>
                            </div>
                        </div>
                        <div class="dk_hkb_pic pic_unload">
                            <div class="pic_unload_over">
                                <span class="uploadImg">
                                    <input type="file" class="file" name="file" size="1" style=" ">
                                    <a href="#" style="">
                                        <img src="images/q_11.png" width="90" height="90">
                                    </a>
                                </span>
                            </div>
                        </div>
                    </div>
                    <p>
                        <span style="vertical-align: top;">
                            评价内容：
                        </span>
                        <textarea style="" name="content" placeholder="说点什么">
                        </textarea>
                    </p>
                    <div class="baoming_bu">
                        <input type="submit" value="提交" />
                    </div>
                </div>
                <div class="f_fl" style="width: 58%;">
                    <div id="mjkomesbox">
                        <h3>
                            评价须知（2015-12-15开始实行）
                        </h3>
                        <span>
                            请您根据本次交易，给予真实，客观，仔细的评价，您的评价将是其他会员的参考，也将影响卖家的信用。
                        </span>
                        <h3>
                            评价须知（2015-12-15开始实行）
                        </h3>
                        <span>
                            请您根据本次交易，给予真实，客观，仔细的评价，您的评价将是其他会员的参考，也将影响卖家的信用。
                        </span>
                    </div>
                </div>
            </div>
        </div>
    </div>
    <input type="hidden" name="bh_id" id="bh_id" value="${bh_id}"/>
    <input type="hidden" name="be_nm_manner" id="be_nm_manner" value="0"/>
    <input type="hidden" name="be_nm_integrity" id="be_nm_integrity" value="0"/>
    <input type="hidden" name="be_nm_quality" id="be_nm_quality" value="0"/>
</form>
</div>
<script type="text/javascript">
    function setStart1(value) {
        $("#be_nm_manner").val(value);
    }
    function setStart2(value) {
        $("#be_nm_integrity").val(value);
    }
    function setStart3(value) {
        $("#be_nm_quality").val(value);
    }
    $("#temp9").Slide({
        effect: "scroolLoop",
        autoPlay: false,
        speed: "normal",
        timer: 3000,
        steps: 2
    });
    function validatepinjia(){
    	var be_nm_manner=$("#be_nm_manner").val();
    	var be_nm_integrity=$("#be_nm_integrity").val();
    	var be_nm_quality=$("#be_nm_quality").val();
    	if(be_nm_manner==0){
    		alert('请对服务态度评价');
    		return false;
    	}
    	if(be_nm_integrity==0){
    		alert('请对服务诚信评价');
    		return false;
    	}
    	if(be_nm_quality==0){
    		alert('请对服务质量评价');
    		return false;
    	}
    	return true;
    }
</script>

<%@ include file="../foot.jsp"%>