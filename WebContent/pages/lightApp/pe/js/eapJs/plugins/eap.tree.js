
(function($) {
	
	$.extend($.fn, {
		
		tree: function(_setting) {
			
			var _tree = new $.eap.widget.Tree(this[0], _setting);
			
			return _tree;
		}
	});
	
	$.eap.widget.Tree = function(_target, _setting) {
		
		this.target = _target;
		this.tree_input = "tree_input";
		this.searchList;
		this.eventObj = {};
		var _self = this;
		this.setting = {
			view : {
				dblClickExpand: false,
				viewType : 1
			},
			callback : {
				onClick : function(e, treeid, treeNode) {
					_self.eventObj["OnNodeClick"](e.screenX,e.screenY,e.clientX,e.clientY,treeNode);
				},
				
				onCheck : function(e, treeid, treeNode) {
					_self.eventObj["OnNodeCheck"](e.clientX,e.clientY,e.clientX,e.clientY,treeNode);
				}
			},
			check : {
				autoCheckTrigger : false,
				chkboxType : {"Y": "s", "N": "ps"},
				chkStyle : "checkbox",
				enable : false,
				nocheckInherit : false,
				chkDisabledInherit : false
			},
			data : {
				
				keep : {
					leaf : false,
					parent : false
				},
				simpleData : {
					enable : true,
					idKey: "id",
					pIdKey: "pId"
				},
				key: {
					
					name: "name",
					children: "children"
				}
			},
			
			url:null,
			items:null
		};
		
		/**
		*
		*@param _tree eap.widget.Tree
		*@param _setting 
		**/
		var _init = function(_tree, _setting) {
			
			var defaultSetting = _tree.setting;
			_tree.setting = $.extend(true,{}, defaultSetting, _setting);
			var p = _tree.setting;

			var z_search=$("<div></div>");
			z_search.addClass("z_search");
			var input=$("<input type='text' id='"+_tree.tree_input+"' />");
			input.css("width","150");
			input.addClass("l-text");
			
			var search_button=$("<div align='center'>search</div>");
			search_button.addClass("search_button");
			search_button.click(function(){
					_tree.search(document.getElementById(_tree.tree_input).value,1,true);					 
			});
			
			input.appendTo(z_search);
			search_button.appendTo(z_search);

			var z_display =$("<div></div>");
			z_display.addClass("z_display");
			
			var z_content =$("<div></div>");
			z_content.addClass("z_content");
			
			_tree.treeUl = $("<ul></ul>");
			_tree.treeUl.attr("id", _tree.setting.id);
			_tree.treeUl.addClass("ztree");
			
			_tree.treeUl.appendTo(z_content);
			z_content.appendTo(z_display);
			
			_tree.target.className = "tree";
			if(_tree.setting.width){
				$(_tree.target).width(_tree.setting.width); 
			}
			if(_tree.setting.height){
				$(_tree.target).height(_tree.setting.height);
			}

			if(_tree.setting.view.viewType==2){
				z_search.appendTo(_tree.target);
				
			}

			z_display.appendTo(_tree.target);
			
			if (p.items) {
				
				_tree.loadData(p.items);
			}
			else if (p.url) {
				
				_tree.loadUrl(p.url, true);
			}
		};
		
		/**
		*
		*@param data json
		**/
		this.loadData = function(data) {
			this.treeObj = $.fn.zTree.init(this.treeUl, this.setting, data);
			
		};
		
		/**
		
		*@param url 
		**/
		this.loadUrl = function(url,type) {
			
			var g = this, p = this.setting;
			
			$.ajax({
				async:type,//
				type:"post",
				url:url,
				//dataType:"text",
				success:function(data){
					g.loadData(data);
				}
			});
			
		};
		
		/**
		*
		*@param key 
		*@param mode 
		*@param openall 
		**/
		this.search = function(key,mode,openall) {
			
			if(mode==1){
				this.searchList = this.treeObj.getNodesByParamFuzzy("name",key, null);
			}else if(mode==2){
				this.searchList = this.treeObj.getNodesByParam("name",key, null);
			}
			if(openall){
				this.treeObj.expandAll(true);	
			}
			for( var i=0, l=this.searchList.length; i<l; i++) {
				this.searchList[i].highlight = true;
				this.treeObj.updateNode(this.searchList[i]);
			}
			return this.searchList;
		};
		
		/**
		*
		*@param filter 
		**/
		this.searchByFilter = function(filter) {

			this.searchList = this.treeObj.getNodesByFilter(filter);
			for( var i=0, l=this.searchList.length; i<l; i++) {
				this.searchList[i].highlight = true;
				this.treeObj.updateNode(this.searchList[i]);
			}
			return this.searchList;
		};
		
		/**
		*
		**/
		this.getSelectedNodes = function(){
			var enable = this.setting.check.enable;
			if(enable){
				if(this.treeObj.getCheckedNodes(true).length>0){
					return this.treeObj.getCheckedNodes(true);
				}else if(this.treeObj.getSelectedNodes().length>0){
					return this.treeObj.getSelectedNodes()
				}else{
					return this.treeObj.getCheckedNodes(true);
				}
			}else{
				return this.treeObj.getSelectedNodes();
			}
			
		};
		
		this.selectNodes = function(key,mode) {
			
			var nodes = this.search(key, mode, false);
			if (nodes) {
				
				for (var i = 0;i < nodes.length;i++) {
					
					if (this.setting.check.enable) {
						
						this.treeObj.checkNode(nodes[i], true, true, true);
					}
					else {
						
						this.treeObj.selectNode(nodes[i], false);
					}
				}
				
				if (this.eventObj["OnNodeSelected"]) {
							
					this.eventObj["OnNodeSelected"](nodes);		
				} 
			}
		};
		
		this.selectNodesByFilter = function(filter) {
			
			var nodes = this.searchByFilter(filter);
			if (nodes) {
				
				for (var i = 0;i < nodes.length;i++) {
					
					if (this.setting.check.enable) {
						
						this.treeObj.checkNode(nodes[i], true, true, true);
					}
					else {
						
						this.treeObj.selectNode(nodes[i], true);
						
					}
				}
				
				if (this.eventObj["OnNodeSelected"]) {
							
					this.eventObj["OnNodeSelected"](nodes);		
				} 
			}
		};
		
		this.unselectedAllNodes = function() {
			
			if (this.setting.check.enable) {
				
				this.treeObj.checkAllNodes(false);
			}
			else {
				
				this.treeObj.cancelSelectedNode();
			}
		};
		
		/**
		*
		*@param eventType 
		*@param cb 
		**/
		this.bind = function(eventType, cb) {
			this.eventObj[eventType] = cb;
		};
		
		_init(this, _setting);
	}

}(jQuery));