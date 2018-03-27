/**
* tip controls
* 
* HGF
* 
*/

(function($) {
	
$.extend($.fn, {
	
	tip: function(msg) {
		
		if (!this.length) {
		
			return;
		}
		
		if (!msg) {
			
			return;
		}
		
		var p = $.extend({}, $.eap.widget.Tip.defaults, {content:msg, target:this, distanceX: 1, distanceY: 1});
		 p.target = this;
		if (p.target.ligeruitipid) return;
		p.x = $(this).offset().left + $(this).width() + (p.distanceX || 0);
		p.y = $(this).offset().top + (p.distanceY || 0);
		p.x = p.x || 0;
		p.y = p.y || 0;
		
		var tip = new $.eap.widget.Tip(p);
		
		return tip;
	}
});

$.eap.widget.Tip = function(options) {
	
	$.eap.widget.Tip.base.constructor.call(this, null, options);
};

$.eap.widget.Tip.defaults = {
	
	content: null,
	callback: null,
	width: 150,
	height: null,
	x: 0,
	y: 0,
	appendIdTo: null,       //保存ID到那一个对象(jQuery)(待移除)
	target: null,
	distanceX: 1,
	distanceY: -3
};

$.eap.widget.Tip.eapExtend($.eap.core.UIComponent, {
	
	_init: function() {
			
		this.id = $.eap.getId("tip");
	},
	
	_render: function() {
		
		var g = this, p = this.options;
		var tip = $('<div class="l-verify-tip"><div class="l-verify-tip-corner"></div><div class="l-verify-tip-content"></div></div>');
		g.tip = tip;
		g.tip.attr("id", g.id);
		if (p.content)
		{
			$(".l-verify-tip-content", tip).html(p.content);
			tip.appendTo('body');
		}
		else
		{
			return;
		}
		
		tip.css({ left: p.x, top: p.y }).show();
		p.width && $("> .l-verify-tip-content", tip).width(p.width - 8);
		p.height && $("> .l-verify-tip-content", tip).width(p.height);
		eee = p.appendIdTo;
		if (p.appendIdTo)
		{
			p.appendIdTo.attr("ligerTipId", g.id);
		}
		if (p.target)
		{
			$(p.target).attr("ligerTipId", g.id);
			p.target.ligeruitipid = g.id;
		}
		
	},
	
	remove: function ()
	{
		if (this.options.appendIdTo)
		{
			this.options.appendIdTo.removeAttr("ligerTipId");
		}
		if (this.options.target)
		{
			$(this.options.target).removeAttr("ligerTipId");
			this.options.target.ligeruitipid = null;
		}
		this.tip.remove();
	}
});

} (jQuery));