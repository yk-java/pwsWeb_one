/**
 *@author hgf
 *
 *simpledatepicker
 *
 *
**/
(function($) {
	
	var setting = {
		box$:"#calendar",
		year$:"#calendar [name=year]", month$:"#calendar [name=month]",
		tmInputs$:"#calendar .time :text", hour$:"#calendar .time .hh", minute$:"#calendar .time .mm", second$:"#calendar .time .ss",
		tmBox$:"#calendar .tm", tmUp$:"#calendar .time .up", tmDown$:"#calendar .time .down",
		close$:"#calendar .close", calIcon$:"a.inputDateButton",
		main$:"#calendar .main", days$:"#calendar .days", dayNames$:"#calendar .dayNames",
		clearBut$:"#calendar .clearBut", okBut$:"#calendar .okBut"
	};
	
	var closeCalendar = function() {
		$(setting.box$).remove();
		$(document).unbind("click", closeCalendar);
	};
	
	var keydownInt = function(e){
		if (!((e.keyCode >= 48 && e.keyCode <= 57) || (e.keyCode == $.eap.keyCode.DELETE || e.keyCode == $.eap.keyCode.BACKSPACE))) { return false; }
	};
	
	var changeTm = function($input, type){
		var ivalue = parseInt($input.val()), istart = parseInt($input.attr("start")) || 0, iend = parseInt($input.attr("end"));
		var istep = parseInt($input.attr('step') || 1);
		if (type == 1) {
			if (ivalue <= iend-istep){$input.val(ivalue + istep);}
		} else if (type == -1){
			if (ivalue >= istart+istep){$input.val(ivalue - istep);}
		} else if (ivalue > iend) {
			$input.val(iend);
		} else if (ivalue < istart) {
			$input.val(istart);
		}
	};
	
	var clickTmMenu = function($input, type){
		$(setting.tmBox$).find("."+type+" li").each(function(){
			var $li = $(this);
			$li.click(function(){
				$input.val($li.text());
			});
		});
	};
	
	$.setRegional("datepicker", {
		dayNames:['Sun', 'Mon', 'Tue', 'Wed', 'Thu', 'Fri', 'Sat'],
		monthNames:['Jan', 'Feb', 'Mar', 'Apr', 'May', 'Jun', 'Jul', 'Aug', 'Sep', 'Oct', 'Nov', 'Dec']
	});
	
	$.extend($.fn, {
		
		simpleDatepicker: function(options) {
			
			if (!this.length) {
				
				return;
			}
			
			var p = $.extend({}, $.eap.widget.SimpleDatepicker.defaults, options);
			var datepicker = new $.eap.widget.SimpleDatepicker(this[0], p);
			return datepicker;	
		}
	});
	
	$.eap.widget.SimpleDatepicker = function(element, options) {
		
		$.eap.widget.SimpleDatepicker.base.constructor.call(this, element, options);
	};
	
	$.eap.widget.SimpleDatepicker.defaults = {
		
		pattern:'yyyy-MM-dd',
		minDate:"1900-01-01",
		maxDate:"2099-12-31",
		mmStep:1,
		ssStep:1
	};
	
	$.eap.widget.SimpleDatepicker.eapExtend($.eap.core.UIComponent, {
		
		_init: function() {
			
			this._initDatepickerBody();
			
			var now = new Date();
			this.options.minDate = now.formatDateTm(this.options.minDate);
			this.options.maxDate = now.formatDateTm(this.options.maxDate);
			
			this.sDate = $(this.element).val().trim();
		},
		
		_initDatepickerBody: function() {
			
			this.datepickerhtml = [];
			
			this.datepickerhtml.push("<div id='calendar'><div class='main'><div class='head'>");
			this.datepickerhtml.push("<table width='100%' border='0' cellpadding='0' cellspacing='2'>");
			this.datepickerhtml.push("<tr><td><select name='year'></select></td><td><select name='month'></select></td>");
			this.datepickerhtml.push("<td width='20'><span class='close'>×</span></td></tr></table></div>");
			this.datepickerhtml.push("<div class='body'>");
			this.datepickerhtml.push("<dl class='dayNames'><dt>日</dt><dt>一</dt><dt>二</dt><dt>三</dt><dt>四</dt><dt>五</dt><dt>六</dt></dl>");
			this.datepickerhtml.push("<dl class='days'>日期列表选项</dl><div style='clear:both;height:0;line-height:0'></div></div>");
			this.datepickerhtml.push("<div class='foot'><table class='time'><tr><td>");
			this.datepickerhtml.push("<input type='text' class='hh' maxlength='2' start='0' end='23'/>:");
			this.datepickerhtml.push("<input type='text' class='mm' maxlength='2' start='0' end='59'/>:");
			this.datepickerhtml.push("<input type='text' class='ss' maxlength='2' start='0' end='59'/>");
			this.datepickerhtml.push("</td><td><ul><li class='up'>&and;</li><li class='down'>&or;</li></ul></td></tr></table>");
			this.datepickerhtml.push("<button type='button' class='clearBut'>清空</button>");
			this.datepickerhtml.push("<button type='button' class='okBut'>确定</button><div>");
			this.datepickerhtml.push("<div class='tm'><ul class='hh'>");
			this.datepickerhtml.push("<li>0</li><li>1</li><li>2</li><li>3</li><li>4</li><li>5</li><li>6</li><li>7</li><li>8</li><li>9</li><li>10</li><li>11</li><li>12</li>");
			this.datepickerhtml.push("<li>13</li><li>14</li><li>15</li><li>16</li><li>17</li><li>18</li><li>19</li><li>20</li><li>21</li><li>22</li><li>23</li></ul>");
			this.datepickerhtml.push("<ul class='mm'><li>0</li><li>5</li><li>10</li><li>15</li><li>20</li><li>25</li><li>30</li><li>35</li><li>40</li><li>45</li><li>50</li><li>55</li></ul>");
			this.datepickerhtml.push("<ul class='ss'><li>0</li><li>10</li><li>20</li><li>30</li><li>40</li><li>50</li></ul></div></div></div>");
		},
		
		_render: function() {
			
			var g = this, p = this.options;
			if (this.element.tagName.toLowerCase() != "input" || this.element.type != "text")
                return;
			
			g.inputText = $(this.element);
			//toggle event
			g.inputText.click(function() {
				
				closeCalendar();
				
				var offset = g.inputText.offset();
				var iTop = offset.top + g.inputText.offsetHeight;
				
				$(g.datepickerhtml.join("")).appendTo("body").css({ left: g.inputText.offset().left, 
				top: g.inputText.offset().top + 1 + g.inputText.outerHeight() }).show().click(function(event){
					event.stopPropagation();
				});
				
				($.fn.bgiframe && $(setting.box$).bgiframe());
				
				var dayNames = "";
				$.each($.regional.datepicker.dayNames, function(i,v){
					dayNames += "<dt>" + v + "</dt>"
				});
				$(setting.dayNames$).html(dayNames);
				
				var dw = g.getDateWrap();
				var $year = $(setting.year$);
				var yearstart = g.getMinDate().getFullYear();
				var yearend = g.getMaxDate().getFullYear();
				for(y=yearstart; y<=yearend; y++){
					$year.append('<option value="'+ y +'"'+ (dw.year==y ? 'selected="selected"' : '') +'>'+ y +'</option>');
				}
				var $month = $(setting.month$);
				$.each($.regional.datepicker.monthNames, function(i,v){
					var m = i+1;
					$month.append('<option value="'+ m +'"'+ (dw.month==m ? 'selected="selected"' : '') +'>'+ v +'</option>');
				});
				
				// generate calendar
				g.generateCalendar();
				$year.add($month).change(function(){
					g.changeDate($year.val(), $month.val());
					g.generateCalendar();
				});
				
				// fix top
				var iBoxH = $(setting.box$).outerHeight(true);
				if (iTop > iBoxH && iTop > $(window).height()-iBoxH) {
					$(setting.box$).css("top", offset.top - iBoxH);
				}
				
				$(setting.close$).click(function(){
					closeCalendar();
				});
				$(setting.clearBut$).click(function(){
					g.inputText.val("");
					closeCalendar();
				});
				$(setting.okBut$).click(function(){
					var $dd = $(setting.days$).find("dd.slt");
					
					if ($dd.hasClass("disabled")) return false;
					
					var date = g.changeDay($dd.attr("day"), $dd.attr("chMonth"));
					
					if (g.hasTime()) {
					 	date.setHours(parseInt($(setting.hour$).val()));
						date.setMinutes(parseInt($(setting.minute$).val()));
						date.setSeconds(parseInt($(setting.second$).val()));
					}
					
					if (g.inputText.val() != g.formatDate(date)) {
						
						g.inputText.val(g.formatDate(date));
						g.inputText.trigger("change");
					}
					
					closeCalendar();
				});
				
				$(document).bind("click", closeCalendar);
				return false;
			});
			
			
		},
		
		generateCalendar: function() {
			
			var g = this;
			
			var dw = g.getDateWrap();
			var minDate = g.getMinDate();
			var maxDate = g.getMaxDate();

			var monthStart = new Date(dw.year,dw.month-1,1);
			var startDay = monthStart.getDay();
			var dayStr="";
			if (startDay > 0){
				monthStart.setMonth(monthStart.getMonth() - 1);
				var prevDateWrap = g.getDateWrap(monthStart);
				for(var t=prevDateWrap.days-startDay+1;t<=prevDateWrap.days;t++) {
					var _date = new Date(dw.year,dw.month-2,t);
					var _ctrClass = (_date >= minDate && _date <= maxDate) ? '' : 'disabled';
					dayStr+='<dd class="other '+_ctrClass+'" chMonth="-1" day="' + t + '">'+t+'</dd>';
				}
			}
			for(var t=1;t<=dw.days;t++){
				var _date = new Date(dw.year,dw.month-1,t);
				var _ctrClass = (_date >= minDate && _date <= maxDate) ? '' : 'disabled';
				if(t==dw.day){
					dayStr+='<dd class="slt '+_ctrClass+'" day="' + t + '">'+t+'</dd>';
				}else{
					dayStr+='<dd class="'+_ctrClass+'" day="' + t + '">'+t+'</dd>';
				}
			}
			for(var t=1;t<=42-startDay-dw.days;t++){
				var _date = new Date(dw.year,dw.month,t);
				var _ctrClass = (_date >= minDate && _date <= maxDate) ? '' : 'disabled';
				dayStr+='<dd class="other '+_ctrClass+'" chMonth="1" day="' + t + '">'+t+'</dd>';
			}
			
			var $days = $(setting.days$).html(dayStr).find("dd");
			$days.not('.disabled').click(function(){
				var $day = $(this);
				
				if (!g.hasTime()) {
					
					var _changeDay = g.formatDate(g.changeDay($day.attr("day"), $day.attr("chMonth")));
					
					if (g.inputText.val() != _changeDay) {
						
						g.inputText.val(_changeDay);
						g.inputText.trigger("change");
					}
					closeCalendar(); 
				} else {
					$days.removeClass("slt");
					$day.addClass("slt");
				}
			});

			if (!g.hasDate()) $(setting.main$).addClass('nodate'); // 仅时间，无日期
			
			if (g.hasTime()) {
				$("#calendar .time").show();
				
				var $hour = $(setting.hour$).val(dw.hour).focus(function(){
					g.changeTmMenu("hh");
				});
				var iMinute = parseInt(dw.minute / g.options.mmStep) * g.options.mmStep;
				var $minute = $(setting.minute$).val(iMinute).attr('step',g.options.mmStep).focus(function(){
					g.changeTmMenu("mm");
				});
				var $second = $(setting.second$).val(g.hasSecond() ? dw.second : 0).attr('step',g.options.ssStep).focus(function(){
					g.changeTmMenu("ss");
				});
				
				$hour.add($minute).add($second).click(function(){return false});
				
				clickTmMenu($hour,"hh");
				clickTmMenu($minute,"mm");
				clickTmMenu($second,"ss");
				$(setting.box$).click(function(){
					g.changeTmMenu();
				});
				
				var $inputs = $(setting.tmInputs$);
				$inputs.keydown(keydownInt).each(function(){
					var $input = $(this);
					$input.keyup(function(){
						g.changeTm($input, 0);
					});
				});
				$(setting.tmUp$).click(function(){
					$inputs.filter(".slt").each(function(){
						g.changeTm($(this), 1);
					});
				});
				$(setting.tmDown$).click(function(){
					$inputs.filter(".slt").each(function(){
						g.changeTm($(this), -1);
					});
				});
				
				if (!g.hasHour()) $hour.attr("disabled",true);
				if (!g.hasMinute()) $minute.attr("disabled",true);
				if (!g.hasSecond()) $second.attr("disabled",true);
			}
		},
		
		changeTm: function($input, type){
			var ivalue = parseInt($input.val()), istart = parseInt($input.attr("start")) || 0, iend = parseInt($input.attr("end"));
			var istep = parseInt($input.attr('step') || 1);
			if (type == 1) {
				if (ivalue <= iend-istep){$input.val(ivalue + istep);}
			} else if (type == -1){
				if (ivalue >= istart+istep){$input.val(ivalue - istep);}
			} else if (ivalue > iend) {
				$input.val(iend);
			} else if (ivalue < istart) {
				$input.val(istart);
			}
		},
		
		changeTmMenu: function(sltClass){
			var $tm = $(setting.tmBox$);
			$tm.removeClass("hh").removeClass("mm").removeClass("ss");
			if (sltClass) {
				$tm.addClass(sltClass);
				$(setting.tmInputs$).removeClass("slt").filter("." + sltClass).addClass("slt");
			}
		},
		
		get: function(name) {
			return this.options[name];
		},
		_getDays: function (y,m){//获取某年某月的天数
			return m==2?(y%4||!(y%100)&&y%400?28:29):(/4|6|9|11/.test(m)?30:31);
		},

		_minMaxDate: function(sDate){
			var _count = sDate.split('-').length -1;
			var _format = 'y-M-d';
			if (_count == 1) _format = 'y-M';
			else if (_count == 0) _format = 'y';
			
			return sDate.parseDate(_format);
		},
		getMinDate: function(){
			return this._minMaxDate(this.options.minDate);
		},
		getMaxDate: function(){
			var _sDate = this.options.maxDate;
			var _count = _sDate.split('-').length -1;
			var _date = this._minMaxDate(_sDate);
			
			if (_count < 2) { //format:y-M、y
				var _day = this._getDays(_date.getFullYear(), _date.getMonth()+1);
				_date.setDate(_day);
				if (_count == 0) {//format:y
					_date.setMonth(11);
				}
			}

			return _date;
		},
		getDateWrap: function(date){ //得到年,月,日
			if (!date) date = this.parseDate(this.sDate) || new Date();
			var y = date.getFullYear();
			var m = date.getMonth()+1;
			var days = this._getDays(y,m);
			return {
				year:y, month:m, day:date.getDate(),
				hour:date.getHours(),minute:date.getMinutes(),second:date.getSeconds(),
				days: days, date:date
			}
		},
		/**
		 * @param {year:2010, month:05, day:24}
		 */
		changeDate: function(y, m, d){
			var date = new Date(y, m - 1, d || 1);
			this.sDate = this.formatDate(date);
			return date;
		},
		changeDay: function(day, chMonth){
			if (!chMonth) chMonth = 0;
			var dw = this.getDateWrap();
			return this.changeDate(dw.year, dw.month+parseInt(chMonth), day);
		},
		parseDate: function(sDate){
			if (!sDate) return null;
			return sDate.parseDate(this.options.pattern);
		},
		formatDate: function(date){
			return date.formatDate(this.options.pattern);
		},
		hasHour: function() {
			return this.options.pattern.indexOf("H") != -1;
		},
		hasMinute: function() {
			return this.options.pattern.indexOf("m") != -1;
		},
		hasSecond: function() {
			return this.options.pattern.indexOf("s") != -1;
		},
		hasTime: function() {
			return this.hasHour() || this.hasMinute() || this.hasSecond();
		},
		hasDate: function() {
			var _dateKeys = ['y','M','d','E'];
			for (var i=0; i<_dateKeys.length; i++){
				if (this.options.pattern.indexOf(_dateKeys[i]) != -1) return true;
			}

			return false;
		}
	});
	
}) (jQuery);