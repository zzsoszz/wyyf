var Login = function () {
	//登录验证的JS函数
	var handleLogin = function() {
		$('.login-form').validate({
	            errorElement: 'span', //default input error message container
	            errorClass: 'help-block', // default input error message class
	            focusInvalid: false, // do not focus the last invalid input
	            rules: {
	                username: {
	                    required: true
	                },
	                password: {
	                    required: true
	                },
	                validateCode: {
	                    required: true
	                },
	                rememberMe: {
	                    required: false
	                }
	            },
	            messages: {
	                username: {
	                    required: "帐号必须填写."
	                },
	                password: {
	                    required: "密码必须填写."
	                },
	                validateCode: {
	                    required: "验证码必须填写."
	                }
	            },
	            invalidHandler: function (event, validator) { //display error alert on form submit   
	                $('.alert-danger', $('.login-form')).show();
	            },
	            highlight: function (element) { // hightlight error inputs
	                $(element)
	                    .closest('.form-group').addClass('has-error'); // set error class to the control group
	            },
	            success: function (label) {
	                label.closest('.form-group').removeClass('has-error');
	                label.remove();
	            },
	            errorPlacement: function (error, element) {
	                error.insertAfter(element.closest('.input-icon'));
	            }
	        }); 
	}
	//忘记密码的JS函数
	var handleForgetPassword = function () {
		$('.forget-form').validate({
	            errorElement: 'span', //default input error message container
	            errorClass: 'help-block', // default input error message class
	            focusInvalid: false, // do not focus the last invalid input
	            ignore: "",
	            rules: {
	                email: {
	                    required: true,
	                    email: true
	                }
	            },
	            messages: {
	                email: {
	                    required: "邮箱必须填写."
	                }
	            },
	            invalidHandler: function (event, validator) { //display error alert on form submit   
	            },
	            highlight: function (element) { // hightlight error inputs
	                $(element).closest('.form-group').addClass('has-error'); // set error class to the control group
	            },
	            success: function (label) {
	                label.closest('.form-group').removeClass('has-error');
	                label.remove();
	            },
	            errorPlacement: function (error, element) {
	                error.insertAfter(element.closest('.input-icon'));
	            }
	        });
	        jQuery('#forget-password').click(function () {
	            jQuery('.login-form').hide();
	            jQuery('.forget-form').show();
	        });
	        jQuery('#back-btn').click(function () {
	            jQuery('.login-form').show();
	            jQuery('.forget-form').hide();
	        });
	}
    return {
        init: function () {
            handleLogin();
            handleForgetPassword();
        }
    };
}();