/**
* lookup controls
* 
* HGF
* 
*/

(function($) {

$.extend($.fn, {
	
	simpleLookup: function(options) {
		
		if (!this.length) {
		
			return;
		}
		
		var p = $.extend({}, $.eap.widget.SimpleLookup.defaults, options);
		var lookup = new $.eap.widget.SimpleLookup(this[0], p);
		
		return lookup;
	}
});

$.eap.widget.SimpleLookup = function(element, options) {
	
	$.eap.widget.SimpleLookup.base.constructor.call(this, element, options);
};

$.eap.widget.SimpleLookup.defaults = {
	
	resize: true,          
	isMultiSelect: false,   
	isShowCheckBox: false,  
	columns: false,       
	selectBoxWidth: false, 
	selectBoxHeight: false, 
	onBeforeSelect: false, 
	onSelected: null, 
	initValue: null,
	initText: null,
	valueField: 'id',
	textField: 'text',
	valueFieldID: null,
	slide: true,           
	split: ";",
	data: null,
	tree: null,           
	treeLeafOnly: true,   
	grid: null,              
	onStartResize: null,
	onEndResize: null,
	hideOnLoseFocus: true,
	url: null,              
	onSuccess: null,
	onError: null,
	onBeforeOpen: null,      
	render: null,            
	absolute: true        
};

$.eap.widget.SimpleLookup.eapExtend($.eap.core.UIComponent, {
	
		
	_init: function() {
		
		var p = this.options;
		if (p.columns)
		{
			p.isShowCheckBox = true;
		}
		if (p.isMultiSelect)
		{
			p.isShowCheckBox = true;
		}
	},
	
	_render: function() {
		
		var g = this, p = this.options;
		g.data = p.data;
		g.inputText = null;
		g.select = null;
		g.textFieldID = "";
		g.valueFieldID = "";
		g.valueField = null; 
		g.inputText = $(this.element);
		g.textFieldID = this.element.id;
		if (g.inputText[0].name == undefined) g.inputText[0].name = g.textFieldID;
		
		g.valueField = null;
		if (p.valueFieldID)
		{
			g.valueField = $("#" + p.valueFieldID + ":input");
			if (g.valueField.length == 0) g.valueField = $('<input type="hidden"/>');
			g.valueField[0].id = g.valueField[0].name = p.valueFieldID;
		}
		else
		{
			g.valueField = $('<input type="hidden"/>');
			g.valueField[0].id = g.valueField[0].name = g.textFieldID + "_val";
		}
		if (g.valueField[0].name == undefined) g.valueField[0].name = g.valueField[0].id;

		g.selectBox = $('<div class="l-box-select"><div class="l-box-select-inner"><table cellpadding="0" cellspacing="0" border="0" class="l-box-select-table"></table></div></div>');
		g.selectBox.table = $("table:first", g.selectBox);
		
	
		g.textwrapper = g.inputText.parent();

		if (p.absolute)
			g.selectBox.appendTo('body').addClass("l-box-select-absolute");
		else
			g.textwrapper.append(g.selectBox);

		g.textwrapper.append(g.valueField);
		if (p.isShowCheckBox && !g.select)
		{
			$("table", g.selectBox).addClass("l-table-checkbox");
		} else
		{
			p.isShowCheckBox = false;
			$("table", g.selectBox).addClass("l-table-nocheckbox");
		}
		
		g.inputText.click(function ()
		{
			if (p.disabled) return;
			if (g.trigger('beforeOpen') == false) return false;
			
			if (g.selectBox.width() == 0) {
				
				g.selectBox.css('width', g.inputText.css('width'));
			}
			
			g._toggleSelectBox(g.selectBox.is(":visible"));
		});
		
		g.resizing = false;
		
		g.selectBox.hover(null, function (e)
		{
			if (p.hideOnLoseFocus && g.selectBox.is(":visible") && !g.boxToggling && !g.resizing)
			{
				g._toggleSelectBox(true);
			}
		});
		
		if (p.selectBoxWidth)
		{
			g.selectBox.width(p.selectBoxWidth);
		}
		else
		{
			g.selectBox.css('width', g.inputText.css('width'));
		}
		var itemsleng = $("tr", g.selectBox.table).length;
		if (!p.selectBoxHeight && itemsleng < 8) p.selectBoxHeight = itemsleng * 30;
		if (p.selectBoxHeight)
		{
			g.selectBox.height(p.selectBoxHeight);
		}

		g.bulidContent();
	},
	
	destroy: function ()
	{
		if (this.wrapper) this.wrapper.remove();
		if (this.selectBox) this.selectBox.remove();
		this.options = null;
	},
	
	setDisabled: function (value)
	{
		if (value)
		{
			this.wrapper.addClass('l-text-disabled');
		} else
		{
			this.wrapper.removeClass('l-text-disabled');
		}
	},
	
	setLable: function (label)
	{
		var g = this, p = this.options;
		if (label)
		{
			if (g.labelwrapper)
			{
				g.labelwrapper.find(".l-text-label:first").html(label + ':&nbsp');
			}
			else
			{
				g.labelwrapper = g.textwrapper.wrap('<div class="l-labeltext"></div>').parent();
				g.labelwrapper.prepend('<div class="l-text-label" style="float:left;display:inline;">' + label + ':&nbsp</div>');
				g.textwrapper.css('float', 'left');
			}
			if (!p.labelWidth)
			{
				p.labelWidth = $('.l-text-label', g.labelwrapper).outerWidth();
			}
			else
			{
				$('.l-text-label', g.labelwrapper).outerWidth(p.labelWidth);
			}
			$('.l-text-label', g.labelwrapper).width(p.labelWidth);
			$('.l-text-label', g.labelwrapper).height(g.wrapper.height());
			g.labelwrapper.append('<br style="clear:both;" />');
			if (p.labelAlign)
			{
				$('.l-text-label', g.labelwrapper).css('text-align', p.labelAlign);
			}
			g.textwrapper.css({ display: 'inline' });
			g.labelwrapper.width(g.wrapper.outerWidth() + p.labelWidth + 2);
		}
	},
	
	_setWidth: function (value)
	{
		var g = this;
		if (value > 20)
		{
			//g.wrapper.css({ width: value });
			g.inputText.css({ width: value - 20 });
			g.textwrapper.css({ width: value });
		}
	},
	
	_setHeight: function (value)
	{
		var g = this;
		if (value > 10)
		{
			//g.wrapper.height(value);
			g.inputText.height(value - 2);
			//g.link.height(value - 4);
			g.textwrapper.css({ width: value });
		}
	},
	
	_setResize: function (resize)
	{
		if (resize && $.fn.ligerResizable)
		{
			var g = this;
			g.selectBox.ligerResizable({ handles: 'se,s,e', onStartResize: function ()
			{
				g.resizing = true;
				g.trigger('startResize');
			}
			, onEndResize: function ()
			{
				g.resizing = false;
				if (g.trigger('endResize') == false)
					return false;
			}
			});
			g.selectBox.append("<div class='l-btn-nw-drop'></div>");
		}
	},
	
	findTextByValue: function (value)
	{
		var g = this, p = this.options;
		if (value == undefined) return "";
		var texts = "";
		var contain = function (checkvalue)
		{
			var targetdata = value.toString().split(p.split);
			for (var i = 0; i < targetdata.length; i++)
			{
				if (targetdata[i] == checkvalue) return true;
			}
			return false;
		};
		$(g.data).each(function (i, item)
		{
			var val = item[p.valueField];
			var txt = item[p.textField];
			if (contain(val))
			{
				texts += txt + p.split;
			}
		});
		if (texts.length > 0) texts = texts.substr(0, texts.length - 1);
		return texts;
	},
   
	findValueByText: function (text)
	{
		var g = this, p = this.options;
		if (!text && text == "") return "";
		var contain = function (checkvalue)
		{
			var targetdata = text.toString().split(p.split);
			for (var i = 0; i < targetdata.length; i++)
			{
				if (targetdata[i] == checkvalue) return true;
			}
			return false;
		};
		var values = "";
		$(g.data).each(function (i, item)
		{
			var val = item[p.valueField];
			var txt = item[p.textField];
			if (contain(txt))
			{
				values += val + p.split;
			}
		});
		if (values.length > 0) values = values.substr(0, values.length - 1);
		return values;
	},
	
	findValueByFilter: function(filter)
	{
		var g = this, p = this.options;
		if (!filter && typeof filter != 'function') {
			
			return;
		}
		
		var values = "";
		$(g.data).each(function(i, item)
		{
			var val = item[p.valueField];
			var txt = item[p.textField];
			if (filter(txt)) {
				
				values += val + p.split;
			}
		});
		if (values.length > 0) values = values.substr(0, values.length - 1);
		
		return values;
	},
	
	removeItem: function ()
	{
	},
	
	insertItem: function ()
	{
	},
	
	addItem: function ()
	{

	},
	
	_setValue: function (value)
	{
		var g = this, p = this.options;
		var text = g.findTextByValue(value);
		if (p.tree)
		{
			g.selectValueByTree(value);
		}
		else if (p.grid) {
			
			g.selectValueByGrid(value);
		}
		else if (g.select) {
			
			g.selectValueBySelect(value);
		}
		else if (!p.isMultiSelect)
		{
			g._changeValue(value, text);
			$("tr[value=" + value + "] td", g.selectBox).addClass("l-selected");
			$("tr[value!=" + value + "] td", g.selectBox).removeClass("l-selected");
		}
		else
		{
			g._changeValue(value, text);
			var targetdata = value.toString().split(p.split);
			$("table.l-table-checkbox :checkbox", g.selectBox).each(function () { this.checked = false; });
			for (var i = 0; i < targetdata.length; i++)
			{
				$("table.l-table-checkbox tr[value=" + targetdata[i] + "] :checkbox", g.selectBox).each(function () { this.checked = true; });
			}
		}
	},
	
	selectValue: function (value)
	{
		this._setValue(value);
	},
	
	selectValueBySelect: function(value) {
		
		var g = this, p = this.options;
		
		$('option', g.select).each(function (i) {
				
			if ($(this).val() == value) {
				
				g.select[0].selectedIndex = i;
				
				$('td:eq(' + g.select[0].selectedIndex + ')', g.selectBox).each(function() {
					
					$(".l-selected", g.selectBox).removeClass("l-selected");
					$(this).addClass("l-selected");
					
					g.select.trigger("change");
					var value = $(this).attr("value");
					var text = $(this).html();
					if (p.render)
					{
						g.inputText.val(p.render(value, text));
					}
					else
					{
						g.inputText.val(text);
					}
				});
			}
		});
	},
	
	selectValueByTree: function(value) {
		
		var g = this, p = this.options;
		var _vs = value.split(",");
		if (_vs != null && _vs.length > 0) {
			var searchFilter = function(node) {
				
				var nodeId = node[p.valueField];
				
				return ArrayUtil.contains(_vs, nodeId);
			};
			
			g.treeManager.selectNodesByFilter(searchFilter);
		}
	},
	
	selectValueByGrid: function(value) {
		
		var g = this, p = this.options;
		var _vs = value.split(p.split);
		
		var texts = [];
		
		if (_vs != null && _vs.length > 0) {
			
			for (var i = 0;i < _vs.length;i++) {
				
				var row = g.getGridRowByValue(_vs[i]);
				if (row) {
					g.gridManager.select(row);
					
					texts.push(row[p.textField]);
				}
			}
			
			g._changeValue(value, texts.join(p.split));
		}
	},
	
	getGridRowByValue: function(value) {
		
		var row = null;
		
		var records = this.gridManager.records;
		if (records) {
			
			for (var rowId in records) {
				
				if (records[rowId][this.options.valueField] == value) {
					
					row = records[rowId];
					
					break;
				}
			}
		}
		
		return row;
	},
	
	bulidContent: function() {
		
		var g = this, p = this.options;
		this.clearContent();
		if (g.select)
		{
			g.setSelect();
		}
		else if (g.data)
		{
			g.setData(g.data);
		}
		else if (p.tree)
		{
			g.setTree(p.tree);
		}
		else if (p.grid)
		{
			g.setGrid(p.grid);
		}
		else if (p.url)
		{
			$.ajax({
				type: 'post',
				url: p.url,
				cache: false,
				dataType: 'json',
				success: function (data)
				{
					g.data = data;
					g.setData(g.data);
					g.trigger('success', [g.data]);
				},
				error: function (XMLHttpRequest, textStatus)
				{
					g.trigger('error', [XMLHttpRequest, textStatus]);
				}
			});
		}
	},
	
	clearContent: function ()
	{
		var g = this, p = this.options;
		$("table", g.selectBox).html("");
	},
	
	setSelect: function ()
	{
		var g = this, p = this.options;
		this.clearContent();
		$('option', g.select).each(function (i)
		{
			var val = $(this).val();
			var txt = $(this).html();
			var tr = $("<tr><td index='" + i + "' value='" + val + "'>" + txt + "</td>");
			$("table.l-table-nocheckbox", g.selectBox).append(tr);
			$("td", tr).hover(function ()
			{
				$(this).addClass("l-over");
			}, function ()
			{
				$(this).removeClass("l-over");
			});
		});
		$('td:eq(' + g.select[0].selectedIndex + ')', g.selectBox).each(function ()
		{
			if ($(this).hasClass("l-selected"))
			{
				g.selectBox.hide();
				return;
			}
			$(".l-selected", g.selectBox).removeClass("l-selected");
			$(this).addClass("l-selected");
			if (g.select[0].selectedIndex != $(this).attr('index') && g.select[0].onchange)
			{
				g.select[0].selectedIndex = $(this).attr('index'); g.select[0].onchange();
			}
			var newIndex = parseInt($(this).attr('index'));
			g.select[0].selectedIndex = newIndex;
			g.select.trigger("change");
			g.selectBox.hide();
			var value = $(this).attr("value");
			var text = $(this).html();
			if (p.render)
			{
				g.inputText.val(p.render(value, text));
			}
			else
			{
				g.inputText.val(text);
			}
		});
		g._addClickEven();
	},
	
	setData: function (data)
	{
		var g = this, p = this.options;
		this.clearContent();
		if (!data || !data.length) return;
		if (g.data != data) g.data = data;
		if (p.columns)
		{
			g.selectBox.table.headrow = $("<tr class='l-table-headerow'><td width='18px'></td></tr>");
			g.selectBox.table.append(g.selectBox.table.headrow);
			g.selectBox.table.addClass("l-box-select-grid");
			for (var j = 0; j < p.columns.length; j++)
			{
				var headrow = $("<td columnindex='" + j + "' columnname='" + p.columns[j].name + "'>" + p.columns[j].header + "</td>");
				if (p.columns[j].width)
				{
					headrow.width(p.columns[j].width);
				}
				g.selectBox.table.headrow.append(headrow);

			}
		}
		for (var i = 0; i < data.length; i++)
		{
			var val = data[i][p.valueField];
			var txt = data[i][p.textField];
			if (!p.columns)
			{
				$("table.l-table-checkbox", g.selectBox).append("<tr value='" + val + "'><td style='width:18px;'  index='" + i + "' value='" + val + "' text='" + txt + "' ><input type='checkbox' /></td><td index='" + i + "' value='" + val + "' align='left'>" + txt + "</td>");
				$("table.l-table-nocheckbox", g.selectBox).append("<tr value='" + val + "'><td index='" + i + "' value='" + val + "' align='left'>" + txt + "</td>");
			} else
			{
				var tr = $("<tr value='" + val + "'><td style='width:18px;'  index='" + i + "' value='" + val + "' text='" + txt + "' ><input type='checkbox' /></td></tr>");
				$("td", g.selectBox.table.headrow).each(function ()
				{
					var columnname = $(this).attr("columnname");
					if (columnname)
					{
						var td = $("<td>" + data[i][columnname] + "</td>");
						tr.append(td);
					}
				});
				g.selectBox.table.append(tr);
			}
		}

		if (p.isShowCheckBox && $.fn.ligerCheckBox)
		{
			$("table input:checkbox", g.selectBox).ligerCheckBox();
		}
		$(".l-table-checkbox input:checkbox", g.selectBox).change(function ()
		{
			if (this.checked && g.hasBind('beforeSelect'))
			{
				var parentTD = null;
				if ($(this).parent().get(0).tagName.toLowerCase() == "div")
				{
					parentTD = $(this).parent().parent();
				} else
				{
					parentTD = $(this).parent();
				}
				if (parentTD != null && g.trigger('beforeSelect', [parentTD.attr("value"), parentTD.attr("text")]) == false)
				{
					g.selectBox.slideToggle("fast");
					return false;
				}
			}
			if (!p.isMultiSelect)
			{
				if (this.checked)
				{
					$("input:checked", g.selectBox).not(this).each(function ()
					{
						this.checked = false;
						$(".l-checkbox-checked", $(this).parent()).removeClass("l-checkbox-checked");
					});
					g.selectBox.slideToggle("fast");
				}
			}
			g._checkboxUpdateValue();
		});
		$("table.l-table-nocheckbox td", g.selectBox).hover(function ()
		{
			$(this).addClass("l-over");
		}, function ()
		{
			$(this).removeClass("l-over");
		});
		g._addClickEven();
		g._dataInit();
	},
	
	setTree:function(setting) {
		
		var g = this, p = this.options;
		this.clearContent();
		g.selectBox.table.remove();
		
		g.tree = $("<div></div>");
		$("div:first", g.selectBox).append(g.tree);
		g.treeManager = g.tree.tree(setting);
		if (g.selectBoxWidth) {
			g.tree.width(g.selectBoxWidth - 20);
		}
		
		if (g.selectBoxHeight) {
			
			g.tree.height(g.selectBoxHeight - 4);
		}
		
		if (setting.check.enable) {
			
			g.treeManager.bind("OnNodeCheck", function(sx, sy, clientX, clientY, node) {
				
				var nodes = g.treeManager.getSelectedNodes();
				var value = [];
				var text = [];
				
				$(nodes).each(function(i, node) {
					
					value.push(node[p.valueField]);
					text.push(node[p.textField]);
				});
				g._changeValue(value.join(p.split), text.join(p.split));
			});
		}
		else {
			
			g.treeManager.bind("OnNodeClick", function(sx, sy, cx, cy, node) {
				
				var value = node[p.valueField];
				var text = node[p.textField];
				g._changeValue(value, text);
			});
			
			g.treeManager.bind("OnNodeSelected", function(nodes) {
				
				if (nodes && nodes.length) {
					
					var _n = nodes[0];
					var value = _n[p.valueField];
					var text = _n[p.textField];
					g._changeValue(value, text);
				}
			});
		}
	},
	
	setGrid: function (grid)
	{
	
		var g = this, p = this.options;
		this.clearContent();
		g.selectBox.table.remove();
		g.grid = $("div:first", g.selectBox);
		grid.columnWidth = grid.columnWidth || 120;
		grid.width = "100%";
		grid.height = "100%";
		grid.heightDiff = -2;
		grid.InWindow = false;
		g.gridManager = g.grid.ligerGrid(grid);
		p.hideOnLoseFocus = false;
		if (grid.checkbox != false)
		{
			var onCheckRow = function ()
			{
				var rowsdata = g.gridManager.getCheckedRows();
				var value = [];
				var text = [];
				$(rowsdata).each(function (i, rowdata)
				{
					value.push(rowdata[p.valueField]);
					text.push(rowdata[p.textField]);
				});
				g._changeValue(value.join(p.split), text.join(p.split));
			};
			g.gridManager.bind('CheckAllRow', onCheckRow);
			g.gridManager.bind('CheckRow', onCheckRow);
		}
		else
		{
			g.gridManager.bind('SelectRow', function (rowdata, rowobj, index)
			{
				var value = rowdata[p.valueField];
				var text = rowdata[p.textField];
				g._changeValue(value, text);
			});
			g.gridManager.bind('UnSelectRow', function (rowdata, rowobj, index)
			{
				g._changeValue("", "");
			});
		}
		g.bind('show', function ()
		{
			if (g.gridManager)
			{
				g.gridManager._updateFrozenWidth();
			}
		});
		g.bind('endResize', function ()
		{
			if (g.gridManager)
			{
				g.gridManager._updateFrozenWidth();
				g.gridManager.setHeight(g.selectBox.height() - 2);
			}
		});
		
	},
	
	_getValue: function ()
	{
		return $(this.valueField).val();
	},
	
	getValue: function ()
	{
		return this._getValue();
	},
	
	_dataInit: function ()
	{
		var g = this, p = this.options;
		var value = null;
		if (p.initValue != undefined && p.initValue != null
				&& p.initText != undefined && p.initText != null
				)
		{
			g._changeValue(p.initValue, p.initText);
		}

		if (p.initValue != undefined && p.initValue != null)
		{
			value = p.initValue;
			var text = g.findTextByValue(value);
			g._changeValue(value, text);
		}
		else if (p.initText != undefined && p.initText != null)
		{
			value = g.findValueByText(p.initText);
			g._changeValue(value, p.initText);
		}
		else if (g.valueField.val() != "")
		{
			value = g.valueField.val();
			var text = g.findTextByValue(value);
			g._changeValue(value, text);
		}
		if (!p.isShowCheckBox && value != null)
		{
			$("table tr", g.selectBox).find("td:first").each(function ()
			{
				if (value == $(this).attr("value"))
				{
					$(this).addClass("l-selected");
				}
			});
		}
		if (p.isShowCheckBox && value != null)
		{
			$(":checkbox", g.selectBox).each(function ()
			{
				var parentTD = null;
				var checkbox = $(this);
				if (checkbox.parent().get(0).tagName.toLowerCase() == "div")
				{
					parentTD = checkbox.parent().parent();
				} else
				{
					parentTD = checkbox.parent();
				}
				if (parentTD == null) return;
				var valuearr = value.toString().split(p.split);
				$(valuearr).each(function (i, item)
				{
					if (item == parentTD.attr("value"))
					{
						$(".l-checkbox", parentTD).addClass("l-checkbox-checked");
						checkbox[0].checked = true;
					}
				});
			});
		}
	},
	
	_changeValue: function (newValue, newText)
	{
		var g = this, p = this.options;
		g.valueField.val(newValue);
		if (p.render)
		{
			g.inputText.val(p.render(newValue, newText));
		}
		else
		{
			g.inputText.val(newText);
		}
		g.selectedValue = newValue;
		g.selectedText = newText;
		g.inputText.trigger("change").focus();
		g.trigger('selected', [newValue, newText]);
	},
	
	_checkboxUpdateValue: function ()
	{
		var g = this, p = this.options;
		var valueStr = "";
		var textStr = "";
		$("input:checked", g.selectBox).each(function ()
		{
			var parentTD = null;
			if ($(this).parent().get(0).tagName.toLowerCase() == "div")
			{
				parentTD = $(this).parent().parent();
			} else
			{
				parentTD = $(this).parent();
			}
			if (!parentTD) return;
			valueStr += parentTD.attr("value") + p.split;
			textStr += parentTD.attr("text") + p.split;
		});
		if (valueStr.length > 0) valueStr = valueStr.substr(0, valueStr.length - 1);
		if (textStr.length > 0) textStr = textStr.substr(0, textStr.length - 1);
		g._changeValue(valueStr, textStr);
	},
	
	_addClickEven: function ()
	{
		var g = this, p = this.options;

		$(".l-table-nocheckbox td", g.selectBox).click(function ()
		{
			var value = $(this).attr("value");
			var index = parseInt($(this).attr('index'));
			var text = $(this).html();
			if (g.hasBind('beforeSelect') && g.trigger('beforeSelect', [value, text]) == false)
			{
				if (p.slide) g.selectBox.slideToggle("fast");
				else g.selectBox.hide();
				return false;
			}
			if ($(this).hasClass("l-selected"))
			{
				if (p.slide) g.selectBox.slideToggle("fast");
				else g.selectBox.hide();
				return;
			}
			$(".l-selected", g.selectBox).removeClass("l-selected");
			$(this).addClass("l-selected");
			if (g.select)
			{
				if (g.select[0].selectedIndex != index)
				{
					g.select[0].selectedIndex = index;
					g.select.trigger("change");
				}
			}
			if (p.slide)
			{
				g.boxToggling = true;
				g.selectBox.hide("fast", function ()
				{
					g.boxToggling = false;
				})
			} else g.selectBox.hide();
			g._changeValue(value, text);
		});
	},
	
	_toggleSelectBox: function (isHide)
	{
		var g = this, p = this.options;
		var textHeight = g.inputText.height();
		g.boxToggling = true;
		if (isHide)
		{
			if (p.slide)
			{
				g.selectBox.slideToggle('fast', function ()
				{
					g.boxToggling = false;
				});
			}
			else
			{
				g.selectBox.hide();
				g.boxToggling = false;
			}
		}
		else
		{
			if (p.absolute)
			{
				g.selectBox.css({ left: g.inputText.offset().left, top: g.inputText.offset().top + 1 + g.inputText.outerHeight() });
			}
			else
			{
				var topheight = g.inputText.offset().top - $(window).scrollTop();
				var selfheight = g.selectBox.height() + textHeight + 4;
				if (topheight + selfheight > $(window).height() && topheight > selfheight)
				{
					g.selectBox.css("marginTop", -1 * (g.selectBox.height() + textHeight + 5));
				}
			}
			if (p.slide)
			{
				g.selectBox.slideToggle('fast', function ()
				{
					g.boxToggling = false;
					if (!p.isShowCheckBox && $('td.l-selected', g.selectBox).length > 0)
					{
						var offSet = ($('td.l-selected', g.selectBox).offset().top - g.selectBox.offset().top);
						$(".l-box-select-inner", g.selectBox).animate({ scrollTop: offSet });
					}
				});
			}
			else
			{
				g.selectBox.show();
				g.boxToggling = false;
				if (!g.tree && !g.grid && !p.isShowCheckBox && $('td.l-selected', g.selectBox).length > 0)
				{
					var offSet = ($('td.l-selected', g.selectBox).offset().top - g.selectBox.offset().top);
					$(".l-box-select-inner", g.selectBox).animate({ scrollTop: offSet });
				}
			}
		}
		g.isShowed = g.selectBox.is(":visible");
		g.trigger('toggle', [isHide]);
		g.trigger(isHide ? 'hide' : 'show');
	},
	
	reset: function() {
		
		var g = this, p = this.options;
		
		if (p.tree) {
			
			g._changeValue("", "");
			
			g.treeManager.unselectedAllNodes();
		}
		else if (p.grid) {
			
			
			var _value = g.getValue();
			if (_value) {
				
				var _vs = _value.split(p.split);
				if (_vs) {
					
					for (var i = 0;i < _vs.length;i++) {
						var row = g.getGridRowByValue(_vs[i]);
						if (row) {
							g.gridManager.unselect(row);
						}
					}
				}
			}
			
			g._changeValue("", "");
			
			
		}
		else if (p.isShowCheckBox) {
			
			$("input:checked", g.selectBox).each(function ()
			{
				this.checked = false;
				$(".l-checkbox-checked", $(this).parent()).removeClass("l-checkbox-checked");
			});
			
			g._checkboxUpdateValue();
		}
		else {
			
			g._changeValue("", "");
		}
	}
});

} (jQuery));