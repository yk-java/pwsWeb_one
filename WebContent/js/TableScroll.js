/**
 * 表格滚动锁定表头
 * @author MaDx
 */
function TableScroll(_setting){
	this.ths=null;
	this._ths=null;
	this.init(_setting);
	this.resize();
}

TableScroll.prototype.init=function(_setting){
	var tableDiv = $(_setting.renderTo);
	var table = tableDiv.find("table");
	var offset = this.getAbsPoint(table[0]);
	var tableThead = tableDiv.find("table thead");
	var tableThs = tableThead.find("th");		
	this.ths=tableThs;

	var _table = $("<table></table>");
	_table.css({width:"100%",position:"absolute", left:offset.x, top:offset.y});
	_table.addClass(table.attr("class"));
	_table.attr("cellspacing", table.attr("cellspacing"));
	_table.attr("cellpadding", table.attr("cellpadding"));
	_table.attr("border", table.attr("border"));
	var _tableThead = $("<thead>"+tableThead.html()+"</thead>");
	var _tableThs = _tableThead.find("th");
	this._ths=_tableThs;
	//_tableThs.css({color:"red"});
	_table.append(_tableThead);
	tableDiv.append(_table);
	this.resize();
	var tableScroll = this;
	window.onresize=function(){alert();
		tableScroll.resize();
	};
}
TableScroll.prototype.resize=function(){
	var tableThs = this.ths;
	var _tableThs = this._ths;
	tableThs.each(function(){
		var th=$(this);
		var index = tableThs.index(th);
		var _th = $(_tableThs.get(index));
		_th.css({"width":th.css("width")});
		
	});
}
TableScroll.prototype.getAbsPoint = function (e){
	var x = e.offsetLeft;
	var y = e.offsetTop;
	while(e = e.offsetParent){
		if($(e).css("position") == "absolute" || $(e).css("position") == "relative"){
			return {"x": x, "y": y};
		}else{
			x += e.offsetLeft;
			y += e.offsetTop;
		}
	}
	return {"x": x, "y": y};
}