MessageTip=function(setting){
	messageTip=this;
	$tip={};
	this.show=function(){
		this.clear();
		$tip = $("<span class='message-tip'>");
		$tip.html(setting.msg);
		$tip.css({
			border:"solid 1px #ff9900",
			padding:"4px 8px",
			background:"#FFF3CE",
			color:"#ff0000"
		});
		var $hostEl = $(setting.randerTo);
		if(setting.position){
			if(setting.position=="inner"){
				$hostEl.append($tip);
			}else if(setting.position=="after"){
				$hostEl.after($tip);
			}
		}else{
			$hostEl.after($tip);
		}
		this.lazyClear();
	};
	this.clear=function(){
		$(".message-tip").remove();
//		$tip.remove();
	};
	this.lazyClear=function(){
		if(this.timeoutFlag==0){
			this.lazyTimer=setTimeout(messageTip.lazyClear, 3000);
			this.timeoutFlag=1;
		}else{
			clearTimeout(messageTip.lazyTimer);
			messageTip.clear();
			messageTip.timeoutFlag=0;
		}
	};
	this.lazyTimer={};
	this.timeoutFlag=0;
};