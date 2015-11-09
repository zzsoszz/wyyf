<?php
define('IN_SYSTEM',true);
require_once("config.php");
require_once("lib/alipay_notify.class.php");
require_once('../../pack/include/init.php');
//计算得出通知验证结果
$alipayNotify = new AlipayNotify($alipay_config);
$verify_result = $alipayNotify->verifyReturn();
if($verify_result) {//验证成功
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
	//订单描述
	$body = $_GET['body'];
	if($_GET['trade_status'] == 'TRADE_FINISHED' || $_GET['trade_status'] == 'TRADE_SUCCESS') {
		$state = "success";
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
		header("Location:../../paystate.php?v_oid={$out_trade_no}&ordertype={$ordertype}&state={$state}&price={$total_fee}");
    }
}
else {
    echo "付款失败";
}
?>
    
    </body>
</html>