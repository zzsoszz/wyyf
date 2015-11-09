<?php
define('IN_SYSTEM',true);
require_once("alipay.config.php");
require_once("lib/alipay_notify.class.php");
require_once('../../pack/include/init.php');
//计算得出通知验证结果
$alipayNotify = new AlipayNotify($alipay_config);
$verify_result = $alipayNotify->verifyNotify();
if($verify_result) {//验证成功
	$out_trade_no = $_POST['out_trade_no'];
	//支付宝交易号
	$trade_no = $_POST['trade_no'];
	//交易状态
	$trade_status = $_POST['trade_status'];
	//订单名称
	$subject = $_POST['subject'];
	//订单金额
	$total_fee = $_POST['total_fee'];
	//订单描述
	$body = $_POST['body'];
	$isUpdate = 0;
    if($_POST['trade_status'] == 'TRADE_FINISHED') {
    	$isUpdate = 1;
    }
    else if ($_POST['trade_status'] == 'TRADE_SUCCESS') {
    	$isUpdate = 1;
    }

	/////更新数据
	if ( $isUpdate == 1 ){
		list($orderid,$ordertype) = explode('-',$body);
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
					$sql = "update `orders` set budan_price = '0',review = '已补单', budan_paymoney='$total_fee',budan_state=1 where id = {$orderid}";

					$db->query($sql);
				}
			}
		}
		
	}
        
	echo "success";		//请不要修改或删除
	
	/////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
}
else {

    echo "fail";

}
?>