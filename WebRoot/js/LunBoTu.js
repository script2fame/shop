
// JavaScript Document


	var index=0;
	var playCount=null;

	$(".but li").hover(function(){
		clearInterval(playCount);//当我滑动时，要停止之前动画
		//给按扭添加样式，改变背景图片,并且除了本身之外的，同级的无素li 去掉样式 current
		$(this).addClass("current").siblings().removeClass('current');
		index=$(this).index();
		//显示对应的序列号的内容，并且隐藏其它的
		$(".con li").eq(index).fadeIn().siblings().fadeOut();
		//alert(index);
		
	}).mouseout(function(){
		auto_play();
	});

	//利用函数方法，实现自动播放
	function auto_play(){
	//设置自动播放函数
	playCount=setInterval(function(){
		index++;
		if(index>3){index=0;}//判断，当序列号超出我们的序列号时，设置为0，此处设置的是不超过3代表有4幅图进行轮播
		//alert("自动播放的序列号"+index);
		$(".but li").eq(index).addClass("current").siblings().removeClass("current");
		$(".con li").eq(index).fadeIn().siblings().fadeOut();
	},3000);
		

	};
	
	auto_play();//调用方法






