<?php
define('IN_SYSTEM',true);
require_once('../../pack/include/init.php');
require_once("config.php");
require_once("lib/alipay_notify.class.php");
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
	
	$isUpdate = 0;
	
	$state = "fail";

    if($_GET['trade_status'] == 'TRADE_FINISHED' || $_GET['trade_status'] == 'TRADE_SUCCESS') {
		
		$isUpdate = 1;
		
    } else {
		
      	echo "验证成功<br /> 支付状态:".$_GET['trade_status'];
	  
    }
	
	if ($isUpdate == 1){
	
		$state = "success";
		list($orderid,$ordertype) = explode('-',$body);
		echo $body.'<BR>';
		echo $orderid.'<BR>';
		echo $ordertype.'<BR>';
		
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
					//echo $sql;
					$db->query($sql);
				}
			}
		}
		//foreach($_GET as $k=>$v){
			//$req .= "&{$k}={$v}";	
		//}
		//echo $req.'<BR>';
		
		//echo 'ok';exit;
		header("Location:../../paystate.php?v_oid={$out_trade_no}&ordertype={$ordertype}&state={$state}&price={$total_fee}");
	}
	//http://www.fly-go.com.cn/pay/bank/return_url.php?bank_seq_no=6867618074&body=118-budan&buyer_email=kehuzijinbu020%40alipay.com&buyer_id=2088502970120570&exterface=create_direct_pay_by_user&is_success=T&notify_id=RqPnCoPT3K9%252Fvwbh3InR9urPKjZcjX3IbTfkPENET2kU04CMAn6T7Q%252BbF5Ep7fs0wfz2&notify_time=2014-08-12+17%3A49%3A47&notify_type=trade_status_sync&out_trade_no=20140812174022579&payment_type=1&seller_email=50390345%40qq.com&seller_id=2088002604334978&subject=118-在线付款&total_fee=1.00&trade_no=2014081273714057&trade_status=TRADE_SUCCESS&sign=98a9618a68601bd97d5fd40931bd747b&sign_type=MD5
	
	echo "支付失败！";

	//——请根据您的业务逻辑来编写程序（以上代码仅作参考）——
	
	/////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
}
else {
   echo "验证失败";
}
?>