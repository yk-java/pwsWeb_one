/**
*	PageBar V1.1
* ================================
*	MaDx create by 2016-06-03 V0.1
*	MaDx update by 2016-07-02 V1.1
* =============DEMO===============
*	new PageBar().init({
*		renderTo:"#page_bar",
*		totalPage:10,
*		onPage:function(pageNum){
*			alert(pageNum);
*		}
*	});
* ============================
*/
function PageBar(){
	var _pageBar=this;
	var _setting;
	var $container;
	var curPage=1;
	var group=0;
	var maxGroup=0;
	var groupBtnCnt=15;
	var $curPageBtn;
	var $pageBtns=[];
	var prevPageText="Prev Page";
	var nextPageText="Next Page";
	this.init=function(setting){
		_setting = setting;
		if(_setting.btnCount){
			groupBtnCnt=_setting.btnCount;
		}
		if(_setting.curPage){
			curPage=_setting.curPage;
			group=Math.floor(curPage/groupBtnCnt);
		}
		if(_setting.prevPageText){
			prevPageText=_setting.prevPageText;
		}
		if(_setting.nextPageText){
			nextPageText=_setting.nextPageText;
		}
		$container = $(_setting.renderTo);
		if(!_setting.totalPage){
			_setting.totalPage=1;
		}
		maxGroup=Math.floor(_setting.totalPage/groupBtnCnt);
		this.create();
		
	};

	this.create=function(){
		$container.empty();
		$pageBtns={};
		/* Prev Page Button */
		var $prevPageBtn = this.createBtn(prevPageText, function(){
			curPage--;
			if(curPage<1){
				curPage=1;
			}
			group=Math.floor(curPage/groupBtnCnt);
			_setting.onPage(curPage);
			_pageBar.create();
			$curPageBtn.removeClass("selected");
			$curPageBtn=$pageBtns[curPage];
			$curPageBtn.addClass("selected");
		},"page_btn_prev");
		$container.append($prevPageBtn);
		/* dots */
		if(group>0){
			var $dots = $("<label>...</label>");//this.createBtn("...", function(){});
			$container.append($dots);
		}
		/* Page Number Buttons */
		var beginPage=1,endPage=1;
		beginPage=group*groupBtnCnt-1;
		if(beginPage<1){
			beginPage=1;
		}
		endPage=group*groupBtnCnt+groupBtnCnt;
		if(endPage>_setting.totalPage){
			endPage = _setting.totalPage;
		}
		for(var i=beginPage;i<=endPage;i++){
			var pageNum=i;
			var $pageNumBtn = this.createBtn(pageNum, function(){
				var num = $(this).attr("data-pageNum");
				curPage=num;
				group=Math.floor(curPage/groupBtnCnt);
				_setting.onPage(curPage);
				_pageBar.create();
				$curPageBtn.removeClass("selected");
				$curPageBtn=$pageBtns[curPage];
				$curPageBtn.addClass("selected");
			});
			if(pageNum==curPage){
				$curPageBtn=$pageNumBtn;
				$curPageBtn.addClass("selected");//alert(pageNum+", "+$curPageBtn.html());
			}
			$pageNumBtn.attr("data-pageNum",pageNum);
			$container.append($pageNumBtn);
			$pageBtns[pageNum]=$pageNumBtn;
		}
		/* dots */
		if(group<maxGroup){
			var $dots = $("<label>...</label>");//this.createBtn("...", function(){});
			$container.append($dots);
		}
		/* Next Page Button */
		var $nextPageBtn = this.createBtn(nextPageText, function(){
			curPage++;
			if(curPage>_setting.totalPage){
				curPage=_setting.totalPage;
			}
			group=Math.floor(curPage/groupBtnCnt);
			_setting.onPage(curPage);
			_pageBar.create();
			$curPageBtn.removeClass("selected");
			$curPageBtn=$pageBtns[curPage];
			$curPageBtn.addClass("selected");
		},"page_btn_next");
		$container.append($nextPageBtn);
		
		/**/
		if(_setting.showPageInfo && _setting.totalPage && _setting.totalRecord){
			var $pageInfo = $("<label>共"+_setting.totalPage+"页/共"+_setting.totalRecord+"条记录</label>");
			$container.append($pageInfo);
		}

	};
	/* Create a button */
	this.createBtn=function(text, clickFn, className, href){
		var $btn = $("<a>");
		$btn.html(text);
		$btn.click(clickFn);
		$btn.addClass("page_btn");
		if(className){
			$btn.addClass(className);
		}
		if(href){
			$btn.attr("href", href);
		}else{
			$btn.attr("href", "javascript:");
		}
		
		return $btn;
	}
}