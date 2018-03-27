/**
* SimpleDateFormat
* MaDx 2016-6-15
* =============Demo===================
* alert(new SimpleDateFormat({formatString:"yyyy-MM-dd"}).format(new Date()));
* alert(new SimpleDateFormat().parse("1989-02-21 12:50:30").getDay());
* ====================================
*/
SimpleDateFormat=function(setting){
	var _fmt_full_year="yyyy";
	var _fmt_short_year="yy";
	var _fmt_month="MM";
	var _fmt_date="dd";
	var _fmt_24_hour="HH";
	var _fmt_minute="mi";
	var _fmt_second="ss";
	var _default_fmt = "yyyy-MM-dd HH:mi:ss";

	if(setting && setting.formatString){
		_default_fmt=setting.formatString;
	}

	this._setting={};
	this.format=function(d){
		var year=d.getFullYear();
		var month=d.getMonth()+1;
		var date=d.getDate();
		var hours=d.getHours();
		var minutes=d.getMinutes();
		var seconds=d.getSeconds();
		var newDate=_default_fmt;
		newDate = newDate.replace(_fmt_full_year, this.numFormat(year, 4));
		newDate = newDate.replace(_fmt_month, this.numFormat(month, 2));
		newDate = newDate.replace(_fmt_date, this.numFormat(date, 2));
		newDate = newDate.replace(_fmt_24_hour, this.numFormat(hours, 2));
		newDate = newDate.replace(_fmt_minute, this.numFormat(minutes, 2));
		newDate = newDate.replace(_fmt_second, this.numFormat(seconds, 2));
		return newDate;
	};
	this.parse=function(dateStr){
		var year=0,month=0,date=0,hours=0,minutes=0,seconds=0;
		year=this.getField(dateStr, _default_fmt, _fmt_full_year);
		month=this.getField(dateStr, _default_fmt, _fmt_month);
		date=this.getField(dateStr, _default_fmt, _fmt_date);
		hours=this.getField(dateStr, _default_fmt, _fmt_24_hour);
		minutes=this.getField(dateStr, _default_fmt, _fmt_minute);
		seconds=this.getField(dateStr, _default_fmt, _fmt_second);//alert(year+", "+month+", "+date+", "+hours+", "+minutes+", "+seconds);
		return new Date(year, month-1, date, hours, minutes, seconds);
	};
	this.getField=function(dateStr, fmtStr, partOfFmt){
		var partOfDate=0;
		var index = fmtStr.indexOf(partOfFmt);
		var s = dateStr.substr(index, partOfFmt.length);
		partOfDate=parseInt(s);
		return partOfDate;
	}
	/* ָ��λ��������λ��ǰ�油0 */
	this.numFormat=function(num, place){
		var numStr=num.toString();
		if(num.toString().length<place){
			var zeroCnt = place-num.toString().length;
			for(var i=0; i<zeroCnt; i++){
				numStr="0"+numStr;
			}
		}
		return numStr;
	};
};