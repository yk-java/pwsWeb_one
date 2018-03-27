/**
 * @FileName showDlog.js
 * @Author MaDx
 * @Mail makeruike@qq.com
 * @Data 2016-06-15
 * @Version v1.3
 * -----------------------------------------
 *  * 2015-03-10
 * bug1: drag use "px"
 * bug2: frameBorder
 * -----------------------------------------
 *  * 2016-06-15
 * bug1: drag failure
 * build with prototype
 * -----------------------------------------
 *
 * =========================================
 *				<< Explain >>
 * =========================================
 *
 * showDlog.showMsg(sHtml, callbackFn, dlogClass)
 * -----------------------------------------
 * showDlog.showDialog(sHtml, title, callbackFn, dlogClass, titleClass)
 * -----------------------------------------
 * showDlog.showFrame(url, title, callbackFn, dlogClass, titleClass)
 * -----------------------------------------
 * showDlog.close()
 * -----------------------------------------
 * showDlog.lazyClose(t)
 * -----------------------------------------
 * showDlog.callbackFn
 * -----------------------------------------
 * showDlog.data
 * -----------------------------------------
 *
 */
var $dlog;
var ShowDlog=function(){};

ShowDlog.prototype.dlogC=null;

ShowDlog.prototype.showMsg=function(sHtml, fn, dlogClass){
	this.callbackFn=fn;							// callbackFn
	// <<------------create msg lay
	var dialog=document.createElement("div");
	dialog.className="dlog_base";
	if(!dlogClass){
		dialog.className+=" dlog_msg";
	}else{
		dialog.className+=" "+dlogClass;			// dlogClass
	}
	dialog.innerHTML=sHtml;
	// ------------->>
	this.createDlogC(dialog);
};

ShowDlog.prototype.showDialog=function(sHtml, titleName, fn, dlogClass, titleClass){
	this.callbackFn=fn;							// callbackFn
	// <<-------------create dialog lay
	var dialog=document.createElement("div");		// create dialog
	dialog.className="dlog_base dlog_dialog_base";
	if(!dlogClass){
		dialog.className+=" dlog_dialog";
	}else{
		dialog.className+=" "+dlogClass;			// dlogClass
	}
	// ------------->>
	// <<-------------create dialog inner
	var dialog_inner = document.createElement("div");
	dialog_inner.className="dlog_dialog_inner";
	// ------------->>
	// <<-------------create dialog title lay
	var dlogTitle=document.createElement("div");	
	if(!titleClass){
		dlogTitle.className="dlog_dialog_title";
	}else{
		dlogTitle.className=titleClass;				// titleClass
	}
	dlogTitle.innerHTML=titleName;
	dlogTitle.onmousedown=function(){$dlog.$drag.startDrag(dlogTitle, dialog)};
	dlogTitle.onmouseup=function(){$dlog.$drag.stopDrag(dlogTitle)};
	dlogTitle.onmousemove=function(){$dlog.$drag.drag(dialog)};
	// ------------->>
	// <<-------------create close button
	var closeBtn=document.createElement("a");
	closeBtn.className="dlog_dialog_closeBtn";
	closeBtn.setAttribute("href", "javascript:$dlog.close()");
	// ------------->>
	// <<-------------create content lay
	var content=document.createElement("div");
	content.className="dlog_content";
	content.innerHTML=sHtml;
	// ------------->>
	dlogTitle.appendChild(closeBtn);
	dialog_inner.appendChild(dlogTitle);
	dialog_inner.appendChild(content);
	dialog.appendChild(dialog_inner);
	this.createDlogC(dialog);
};
ShowDlog.prototype.showFrame=function(url, titleName, fn, dlogClass, titleClass){
	this.callbackFn=fn;							// callbackFn
	// <<-------------create dialog lay
	var dialog=document.createElement("div");
	dialog.className="dlog_base dlog_dialog_base";
	if(!dlogClass){
		dialog.className+=" dlog_dialog_frame";
	}else{
		dialog.className+=" "+dlogClass;			// dlogClass
	}
	// ------------->>
	// <<-------------create dialog inner
	var dialog_inner = document.createElement("div");
	dialog_inner.className="dlog_dialog_inner";
	// ------------->>
	// <<-------------create dialog title lay
	var dlogTitle=document.createElement("div");	//create dialog title
	if(!titleClass){
		dlogTitle.className="dlog_dialog_title";
	}else{
		dlogTitle.className=titleClass;				// titleClass
	}
	dlogTitle.innerHTML=titleName;
	dlogTitle.onmousedown=function(){$dlog.$drag.startDrag(dlogTitle, dialog)};
	dlogTitle.onmouseup=function(){$dlog.$drag.stopDrag(dlogTitle)};
	dlogTitle.onmousemove=function(){$dlog.$drag.drag(dialog)};
	// ------------->>
	// <<-------------create close button
	var closeBtn=document.createElement("a");
	closeBtn.className="dlog_dialog_closeBtn";
	closeBtn.setAttribute("href", "javascript:$dlog.close()");
	// ------------->>
	// <<-------------create iframe
	var iframe=document.createElement("iframe");
	iframe.frameBorder=0;
	iframe.className="dlog_iframe";
	iframe.src=url;
	// ------------->>
	dlogTitle.appendChild(closeBtn);
	dialog_inner.appendChild(dlogTitle);
	var content=document.createElement("div");
	content.className="dlog_content";
	content.appendChild(iframe);
	// ------------->>
	dialog_inner.appendChild(content);
	dialog.appendChild(dialog_inner);
	this.createDlogC(dialog);
	iframe.style.height=dialog.offsetHeight-32+"px";
};
ShowDlog.prototype.createDlogC=function(dialog){						// createDlogC
	$dlog=this;
	if(this.dlogC){
		document.body.removeChild(this.dlogC);
	}
	// <<-------------create dlogC
	this.dlogC=document.createElement("div");
	this.dlogC.className="dlog_C";
	// ------------->>
	// <<-------------create dlogB
	var dlogB=document.createElement("div");
	dlogB.className="dlog_B";
	// ------------->>
	this.dlogC.appendChild(dlogB);
	this.dlogC.appendChild(dialog);
	document.body.appendChild(this.dlogC);
	dialog.style.cssText+="left:"+(this.dlogC.offsetWidth-dialog.offsetWidth)/2+"px;top:"+((this.dlogC.offsetHeight-dialog.offsetHeight)/2+document.body.scrollTop)+"px;";
	var scrollHeight=document.body.scrollHeight;
	var offsetHeight=document.body.offsetHeight;
	var h=600;
	if(offsetHeight>scrollHeight){
		h=offsetHeight;
	}else{
		h=scrollHeight;
	}
	dlogB.style.height=h;
	dlogB.style.top=(document.body.scrollTop)+"px";
	dlogB.style.bottom=-1*(document.body.scrollTop)+"px";
	window.onscroll=function(){
		dlogB.style.top=(document.body.scrollTop)+"px";
		dlogB.style.bottom=-1*(document.body.scrollTop)+"px";
	};
};
ShowDlog.prototype.close=function(returnData){
	if(this.timer){
		clearTimeout(this.timer);
		this.timer=null;
	}
	if(this.dlogC){
		this.dlogC.style.display="none";
		document.body.removeChild(this.dlogC);		// remove dlogC
		this.dlogC=null;
		if(this.isShow){
			this.isShow=false;
		}
		if(this.callbackFn){
			if(!returnData){
				this.callbackFn(this.data);			// callbackFn
			}else{
				this.callbackFn(returnData);			// callbackFn
			}
		}
	}else{
		//alert("ERROR:showDialog not init");
	}
};
ShowDlog.prototype.timer=null;
ShowDlog.prototype.isShow=false;
ShowDlog.prototype.lazyClose=function(t){
	if(!this.isShow){
		this.isShow=true;
		this.timer=setTimeout("$dlog.lazyClose()", t);
	}else{
		clearTimeout(this.timer);
		this.timer=null;
		this.close();
		this.isShow=false;
	}
};
ShowDlog.prototype.$drag={												// drag
	move:false,_X:null,_Y:null,
	startDrag:function(dlogTitle, dialog){
		//dlogTitle.setCapture();
		$dlog.$drag.move=true;
		$dlog.$drag._X=dialog.offsetLeft-event.clientX;
		$dlog.$drag._Y=dialog.offsetTop-event.clientY;
	},
	drag:function(dialog){
		if($dlog.$drag.move){
			dialog.style.left=event.clientX+$dlog.$drag._X+"px";
			dialog.style.top=event.clientY+$dlog.$drag._Y+"px";
		}
	},
	stopDrag:function(dlogTitle){
		//dlogTitle.releaseCapture();
		$dlog.$drag.move=false;
	}
};
ShowDlog.prototype.callbackFn=null;									// callbackFn
ShowDlog.prototype.data=null;										// data
/**
* ====================== Demo =============================
*	new ShowDlog().confirm({msg:"确定要删除嘛？",
*		ok:function(){
*			alert("确定");
*		}, 
*		cancel:function(){
*			alert("取消");
*		}});
*/
ShowDlog.prototype.confirm=function(setting){
	var icon="<span class='dlog-icon-alert'></span>";
	if(setting.icon && setting.icon=="success"){
		icon="<span class='dlog-icon-success'></span>";
	}else if(setting.icon && setting.icon=="error"){
		icon="<span class='dlog-icon-error'></span>";
	}else if(setting.icon && setting.icon=="alert"){
		icon="<span class='dlog-icon-alert'></span>";
	}else if(setting.icon && setting.icon=="warning"){
		icon="<span class='dlog-icon-warning'></span>";
	}else if(setting.icon && setting.icon=="waitting"){
		icon="<span class='dlog-icon-waitting'></span>";
	}else if(setting.icon && setting.icon=="custom"){
		icon=setting.iconHtml;
	}
	this.showDialog(
		"<div class='dlog-alert-msg'>"+icon+" "+setting.msg+"</div>"+
		"<div class='dlog-alert-btn-bar'>"+
		"<input type='button' class='dlog-btn-ok' value='确定' onclick='$dlog.close(\"ok\")'>&nbsp;"+
		"<input type='button' class='dlog-btn-cancel' value='取消' onclick='$dlog.close(\"cancel\")'>&nbsp;"+
		"</div>", "确认提示", function(cmd){
		if(cmd=="ok"){
			if(setting && setting.ok)
				setting.ok();
		}else if(cmd=="cancel"){
			if(setting && setting.cancel)
				setting.cancel();
		}
	});
};
/**
 * ====================Demo========================
 * new ShowDlog().prompt({msg:"新增成功！",
 * 	done:function(){
 * 		//
 * 	},
 * 	t:1500
 * });
 */
ShowDlog.prototype.prompt=function(setting){
	var icon="<span class='dlog-icon-alert'></span>";
	if(setting.icon && setting.icon=="success"){
		icon="<span class='dlog-icon-success'></span>";
	}else if(setting.icon && setting.icon=="error"){
		icon="<span class='dlog-icon-error'></span>";
	}else if(setting.icon && setting.icon=="alert"){
		icon="<span class='dlog-icon-alert'></span>";
	}else if(setting.icon && setting.icon=="warning"){
		icon="<span class='dlog-icon-warning'></span>";
	}else if(setting.icon && setting.icon=="waitting"){
		icon="<span class='dlog-icon-waitting'></span>";
	}else if(setting.icon && setting.icon=="custom"){
		icon=setting.iconHtml;
	}
	this.showMsg("<div style='margin-top:15px;'>"+icon+" "+setting.msg+"</div>",setting.done);
	this.lazyClose(setting.t);
};