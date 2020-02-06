function checkForm(){
	alert("test");
	return false;
}

//获取验证码按钮绑定事件
$('#verication_button').on('click', function() {
	alert('on直接绑定事件了');
});
var countdown = 5;//倒计时总时间，为了演示效果，设为5秒，一般都是60s
function settime() {
		if (countdown == 0) {
			btn.attr("disabled", false);
			btn.html("获取验证码");
			btn.removeClass("disabled");
			countdown = 5;
			return;
		} else {
			btn.addClass("disabled");
			btn.attr("disabled", true);
			btn.html("重新发送(" + countdown + ")");
			countdown--;
		}
		setTimeout(settime, 1000);
}
