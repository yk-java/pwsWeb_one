/**
* DropDownList V0.1
* MaDx 2016-06-02
* ==============DEMO=============
*	new DropDownList().init({
*		renderTo:".dropDownTree",
*		url:"tree.html",
*		height:"200px",
*		onSelected:function(result){
*			alert("result:"+result.name);
*		}
*	});
* ==============DEMO=============
*/
function DropDownList(){
	var dropDownList = this;
	var _setting;
	var $hostEl;
	var $iframe;
	this.init=function(setting){
		_setting = setting;
		$hostEl=$(setting.renderTo);
		$hostEl.click(function(){
			dropDownList.draw();
		});
	};
	this.draw=function(){
		var w = $hostEl.outerWidth()-2;
		var h = $hostEl.outerHeight();
		var offset = this.getAbsPoint($hostEl[0]);//.offset();//alert(w+","+h+","+offset.left+", "+offset.top);
		$iframe = $("<iframe>");
		
		$iframe.attr("src", _setting.url);
		$iframe.attr("frameBorder", 0);
		if(_setting.width)
			w=_setting.width;
		$iframe.css({position:"absolute", "z-index":9999, left:offset.x, top:offset.y+h, width:w, height:_setting.height, border:"solid 1px #ccc"});
		if(_setting.css){
			$iframe.css(_setting.css);
		}
		$hostEl.after($iframe);
		$iframe[0].callback=_setting.onSelected;
		$iframe[0].close=function(){
			dropDownList.clear();
		};
		$iframe.focus();
		$iframe.focusout(function(){
			dropDownList.clear();
		});
	};
	this.getAbsPoint = function (e){
		var x = e.offsetLeft;
		var y = e.offsetTop;
		while(e = e.offsetParent){
			if($(e).css("position") == "absolute"){
				return {"x": x, "y": y};
			}else{
				x += e.offsetLeft;
				y += e.offsetTop;
			}
		}
		return {"x": x, "y": y};
	};
	this.clear=function(){
		$iframe.remove();
	};
}