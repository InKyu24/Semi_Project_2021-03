$(document).ready(function(){
	$("#about").click(function(){
		$("#list").hide();
		$(".mainContent").load("/introduction.camp");
	});
	
	$("#comm").click(function(){
		var logined=$.cookie("logined");
		if (logined) {
			$("#list").hide();	
			$(".mainContent").load("/board/board.camp");
		} else {
			alert ("로그인 후 커뮤니티 사용이 가능합니다.")
		}
			
	});

});

$(document).on("click", "#cart", function(event) { // 장바구니
	$(".mainContent").load("/prod/cart.camp");
});