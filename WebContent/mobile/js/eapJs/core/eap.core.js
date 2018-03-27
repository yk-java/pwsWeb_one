/**
* jQuery EAP_FRAMWORK
* 
* HGF
* 
*/
(function($) {
	
	Function.prototype.eapExtend = function (parent, overrides)
    {
        if (typeof parent != 'function') return this;
        
        this.base = parent.prototype;
        this.base.constructor = parent;
        
        var f = function () { };
        f.prototype = parent.prototype;
        this.prototype = new f();
        this.prototype.constructor = this;
        
        if (overrides) $.extend(this.prototype, overrides);
    };
	
	Function.prototype.delayInvoke = function(o, delay, args) {
		
		var fn = this;
		return setTimeout(function() {fn.apply(o, args || []);}, delay);
	};
	
	$.setRegional = function(key, value){
		if (!$.regional) $.regional = {};
		$.regional[key] = value;
	};
	
	$.eap = {
		
		version: 'V1.0.0',
		managerCount: 0,
		managerIdPrev: 'eap',
		keyCode: {
			ENTER: 13, ESC: 27, END: 35, HOME: 36,
			SHIFT: 16, TAB: 9,
			LEFT: 37, RIGHT: 39, UP: 38, DOWN: 40,
			DELETE: 46, BACKSPACE:8
		},
		getId: function (prev)
        {
            prev = prev || this.managerIdPrev;
            var id = prev + (1000 + this.managerCount);
            this.managerCount++;
            return id;
        },
		
		core: {},

		widget: {}
	};
	
	$.eap.core.Component = function(options) {
		
		this.options = options;
		this.events = this.events || {};
	};
	
	$.extend($.eap.core.Component.prototype, {
		
		trigger: function (arg, data)
		{
			var name = arg.toLowerCase();
			var event = this.events[name];
			if (!event) return;
			data = data || [];
			if ((data instanceof Array) == false)
			{
				data = [data];
			}
			for (var i = 0; i < event.length; i++)
			{
				var ev = event[i];
				if (ev.handler.apply(ev.context, data) == false)
					return false;
			}
		},

		bind: function (arg, handler, context)
		{
			if (typeof arg == 'object')
			{
				for (var p in arg)
				{
					this.bind(p, arg[p]);
				}
				return;
			}
			if (typeof handler != 'function') return false;
			var name = arg.toLowerCase();
			var event = this.events[name] || [];
			context = context || this;
			event.push({ handler: handler, context: context });
			this.events[name] = event;
		},

		unbind: function (arg, handler)
		{
			if (!arg)
			{
				this.events = {};
				return;
			}
			var name = arg.toLowerCase();
			var event = this.events[name];
			if (!event || !event.length) return;
			if (!handler)
			{
				delete this.events[name];
			}
			else
			{
				for (var i = 0, l = event.length; i < l; i++)
				{
					if (event[i].handler == handler)
					{
						event.splice(i, 1);
						break;
					}
				}
			}
		},
		
		hasBind: function (arg)
        {
            var name = arg.toLowerCase();
            var event = this.events[name];
            if (event && event.length) return true;
            return false;
        }
	});
	
	$.eap.core.UIComponent = function(element, options) {
		
		$.eap.core.UIComponent.base.constructor.call(this, options);
		
		this.element = element;
		this._init();
		this._render();
	};
	
	$.eap.core.UIComponent.eapExtend($.eap.core.Component, {
		
		_init: function() {
			
			
		},
		
		_render: function() {
			
			
		}
	});
	
	$.extend(String.prototype, {
		isPositiveInteger:function(){
			return (new RegExp(/^[1-9]\d*$/).test(this));
		},
		isInteger:function(){
			return (new RegExp(/^\d+$/).test(this));
		},
		isNumber: function(value, element) {
			return (new RegExp(/^-?(?:\d+|\d{1,3}(?:,\d{3})+)(?:\.\d+)?$/).test(this));
		},
		trim:function(){
			return this.replace(/(^\s*)|(\s*$)|\r|\n/g, "");
		},
		startsWith:function (pattern){
			return this.indexOf(pattern) === 0;
		},
		endsWith:function(pattern) {
			var d = this.length - pattern.length;
			return d >= 0 && this.lastIndexOf(pattern) === d;
		},
		replaceSuffix:function(index){
			return this.replace(/\[[0-9]+\]/,'['+index+']').replace('#index#',index);
		},
		trans:function(){
			return this.replace(/&lt;/g, '<').replace(/&gt;/g,'>').replace(/&quot;/g, '"');
		},
		encodeTXT: function(){
			return (this).replaceAll('&', '&amp;').replaceAll("<","&lt;").replaceAll(">", "&gt;").replaceAll(" ", "&nbsp;");
		},
		replaceAll:function(os, ns){
			return this.replace(new RegExp(os,"gm"),ns);
		},
		replaceTm:function($data){
			if (!$data) return this;
			return this.replace(RegExp("({[A-Za-z_]+[A-Za-z0-9_]*})","g"), function($1){
				return $data[$1.replace(/[{}]+/g, "")];
			});
		},
		replaceTmById:function(_box){
			var $parent = _box || $(document);
			return this.replace(RegExp("({[A-Za-z_]+[A-Za-z0-9_]*})","g"), function($1){
				var $input = $parent.find("#"+$1.replace(/[{}]+/g, ""));
				return $input.val() ? $input.val() : $1;
			});
		},
		isFinishedTm:function(){
			return !(new RegExp("{[A-Za-z_]+[A-Za-z0-9_]*}").test(this)); 
		},
		skipChar:function(ch) {
			if (!this || this.length===0) {return '';}
			if (this.charAt(0)===ch) {return this.substring(1).skipChar(ch);}
			return this;
		},
		isValidPwd:function() {
			return (new RegExp(/^([_]|[a-zA-Z0-9]){6,32}$/).test(this)); 
		},
		isValidMail:function(){
			return(new RegExp(/^\w+((-\w+)|(\.\w+))*\@[A-Za-z0-9]+((\.|-)[A-Za-z0-9]+)*\.[A-Za-z0-9]+$/).test(this.trim()));
		},
		isSpaces:function() {
			for(var i=0; i<this.length; i+=1) {
				var ch = this.charAt(i);
				if (ch!=' '&& ch!="\n" && ch!="\t" && ch!="\r") {return false;}
			}
			return true;
		},
		isPhone:function() {
			return (new RegExp(/(^([0-9]{3,4}[-])?\d{3,8}(-\d{1,6})?$)|(^\([0-9]{3,4}\)\d{3,8}(\(\d{1,6}\))?$)|(^\d{3,8}$)/).test(this));
		},
		isUrl:function(){
			return (new RegExp(/^[a-zA-z]+:\/\/([a-zA-Z0-9\-\.]+)([-\w .\/?%&=:]*)$/).test(this));
		},
		isExternalUrl:function(){
			return this.isUrl() && this.indexOf("://"+document.domain) == -1;
		}
	});
	
	ArrayUtil = {
		
		contains: function(_array, item) {
			
			var i = _array.length;
			while (i--) {
				if (_array[i] == item) {
					return true;
				}
			}
			return false;
		},
		
		indexOf: function(_array, item) {
			
			var i = _array.length;
			var index = -1;
			while (i--) {
				if (_array[i] == item) {
					
					index = i;
					
					break;
				}
			}
			
			return index;
		}
	};
	
} (jQuery));