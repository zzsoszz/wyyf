<?php
define('IN_SYSTEM',true);
require_once('../../pack/include/init.php');
require_once("config.php");
require_once("lib/alipay_notify.class.php");
//test.php?out_trade_no=20140810270&subject=107-kuaiyun&trade_status=TRADE_FINISHED&total_fee=750.00
	//商户订单号
	$out_trade_no = $_GET['out_trade_no'];

	//支付宝交易号
	$trade_no = $_GET['trade_no'];

	//交易状态
	$trade_status = $_GET['trade_status'];

	//订单名称
	$subject = $_GET['subject'];
	
	//订单金额
	$total_fee = $_GET['total_fee'];
	
	$isUpdate = 0;
	
	$state = "fail";

    if($_GET['trade_status'] == 'TRADE_FINISHED' || $_GET['trade_status'] == 'TRADE_SUCCESS') {
		
		$isUpdate = 1;
		
    } else {
		
      	echo "验证成功<br /> 支付状态:".$_GET['trade_status'];
	  
    }
	
	if ($isUpdate == 1){
		$state = "success";
		list($orderid,$ordertype) = explode('-',$subject);
		if ($ordertype == 'kuaiyun'){
			$sql = "select * from `orders` where id = {$orderid}";
			$rs = $db->getRow($sql);
			if($rs){
				if($rs['status']=='未付款'){
					$sql = "update `orders` set status = '已付款',trade_no = '$trade_no' where id = {$orderid}";
					$db->query($sql);
				}
			}
		}elseif($ordertype == 'peto'){
			$sql = "select * from `petorder` where id = {$orderid}";
			$rs = $db->getRow($sql);
			if($rs){
				if($rs['status']=='未付款'){
					$sql = "update `petorder` set status = '已付款',trade_no = '$trade_no',trade_type = '支付宝' where id = {$orderid}";
					$db->query($sql);
				}
			}
		}elseif($ordertype == 'budan'){
			$sql = "select * from `orders` where id = {$orderid}";
			$rs = $db->getRow($sql);
			if($rs){
				if($rs['budan_price']>0){
					$sql = "update `orders` set budan_price = '0',review = '已补单', where id = {$orderid}";
					$db->query($sql);
				}
			}
		}
		
		header("Location:../../paystate.php?v_oid={$out_trade_no}&ordertype={$ordertype}&state={$state}");
	}
		
	
	echo "支付失败！";

	//——请根据您的业务逻辑来编写程序（以上代码仅作参考）——
	
	/////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

?>