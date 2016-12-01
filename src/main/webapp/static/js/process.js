
    //公共的回调函数
    function commonSuccessCallback(target, data,textStatus){
        $("#" + target).html(data);
    }
    

    /**
     * 刷新指定DIV区域，前提条件DIV含有URL属性值
     * 一般在用于分页显示的DIV，分页标签已经预设好URL属性值
     * @param targets 分页显示的DIV ID
     * @return
     */
    function refreshDiv(targets){
        //alert("pageNo="+pageNo+";targets="+targets);
        if(targets==''||targets=='null'){
            alert('TODO');
        }else{
            var url=$("#"+targets).attr('url');
            //alert("org url="+url);
            ajaxGet(url,targets);
        }
    } 
    
    /**
     * AJAX Get调用，主要用于查询/刷新页面等请求
     *
     * @params url 请求的URL地址
     * @params target 响应信息输出显示的DIV ID名称
     * @params callback 可选参数，自定义的回调函数
     * @returns 
     */
    function ajaxGet(url, target, callback) {
        url=getRandedURL(url);
        debug("ajaxGet url="+url);
        $("#" + target).html("loading...");
        $.get(url,null,function(data, textStatus) {
                //alert("success");
                if(callback){
                    callCommon=callback(data);
                    if(callCommon){
                        commonSuccessCallback(target,data);
                    }
                }else{
                    commonSuccessCallback(target,data);
                }
        });
    }

    function ajaxGetJSON(url, callback){
        url=getRandedURL(url);
    	$.getJSON(url,callback);
    }

    /**
     * AJAX Post调用，主要用于Form表单提交请求
     *
     * @params formId 提交Form表单对象的id
     * @returns 
     */
    function ajaxPost(formId,successCallback,failureCallback) {
        var optionsPost = {
            success: function(response){
            	if(!response.type){
            		publishErrorContentPage(response);
            		return;
            	}
            	if(response.type=="success"){
            		publishMessage(response.message);
            		if(successCallback){
            			successCallback(response);
            		}
            	}else{
            		publishError(response.message);
            		if(failureCallback){
            			failureCallback(response);
            		}
            	} 
            }
        };
        $("#"+formId).ajaxSubmit(optionsPost);
    }
    
    /**
     * AJAX Post调用，主要用于URL提交请求
     *
     * @params url 提交URL
     * @returns 
     */
    function ajaxPostURL(url,data,successCallback,failureCallback) {
    	url=getRandedURL(url);
        $.post(encodeURI(url),data,function(response, textStatus) {
        	if(!response.type){
        		publishErrorContentPage(response);
        		return;
        	}
        	if(response.type=="success"){
        		publishMessage(response.message);
        		if(successCallback){
        			successCallback(response);
        		}
        	}else{
        		publishError(response.message);
        		if(failureCallback){
        			failureCallback(response);
        		}
        	} 
        });
    }
    
    /**
     * 通用的表单提交及回调处理
     * @param form
     * @param successCallback
     */
    function globalSubmitHandler(form,successCallback,failureCallback) {   
        var optionsPost = {
                success: function(response){
                	$(form).attr("disabled",false);
                	if(!response.type){
                		publishErrorContentPage(response);
                		return;
                	}
                	if(response.type=="success"){
                		publishMessage(response.message);
                		if(successCallback){
                			successCallback(response);
                		}
                	}else{
                		publishError(response.message);
                		if(failureCallback){
                			failureCallback(response);
                		}
                	} 
                }
            };
        $(form).ajaxSubmit(optionsPost);        
        $(form).attr("disabled",true);
    }


    

    //由于浏览器对于相同URL请求会自动缓存页面，导致请求相同URL地址无法刷新页面。
    //因此为URL地址追加一个随机数，使每次请求的实际URL地址不一样，触发浏览器刷新页面
    function getRandedURL(url){
        return urlreplace(url,"_rand",new Date().getTime());
    }

    /**
     * 专门用于Search查询操作调用方法，主要用于Form表单提交请求
     *
     * @params formId 提交查询Form表单对象的id
     * @params target 响应信息输出显示的DIV ID名称
     * @returns 
     */
    function submitSearchRequest(formId,target){
        debug("submitSearchRequest form="+formId);
        $("#"+formId+" input[type='text']").each(function(){
        	$(this).val($.trim($(this).val()).replace( new RegExp("'","gm"),""));
        });        
        var formSerialize=$("#"+formId).serialize();
        var url=$("#"+formId).attr("action");
        url=getRandedURL(url);
        debug("submitSearchRequest:url="+url+";params="+formSerialize);
        $.get(url,formSerialize,function(data, textStatus) {
            commonSuccessCallback(target,data);
        });
    }
    
    /**
     * 弹出窗口函数
     *
     * @params dialogId 指定弹出窗口的唯一ID，如果dialogId对应窗口不存在，则创建；如果已存在，则直接替换该对话框内容
     * @params url 弹出窗口请求的URL地址
     * @params ititle 弹出窗口显示的Title标题
     * @params iwidth 弹出窗口的Width
     * @params iheight 弹出窗口的Height
     * @returns 
     */
    function popupDialog(dialogId,url,ititle,iwidth,iheight,callback,imodal){
        if($("#"+dialogId).length <= 0){
            $("body").append("<div id='"+dialogId+"' style='width:90%;overflow:auto;'></div>")
        }
        if(imodal==undefined){
        	imodal=true;
        }
        var option={width:iwidth,height:iheight,modal:imodal};
        $("#"+dialogId).html("loading...");
        url=getRandedURL(url);
        $("#"+dialogId).dialog(option).load(url, function() { 
        	if(callback){
        		callback();
        	}
        });
        $("#"+dialogId).dialog("option","title",ititle);
        $("#"+dialogId).dialog("option","width",iwidth);
        $("#"+dialogId).dialog("option","height",iheight);    
    }
    
    /**
     * 询问确认关闭弹出窗口函数
     *
     * @params dialogId 指定弹出窗口的唯一ID
     * @params msg 可选参数，关闭Confirm确认框提示的信息；如果没有提供则使用默认提示语句
     * @returns 
     */
    function closePopupDialog(dialogId, msg){
    	if(msg==undefined){
	        if(confirm('你确认取消吗?')){
	            closePopupDialogDirect(dialogId);
	        }  		
    	}else{
	        if(confirm(msg)){
	            closePopupDialogDirect(dialogId);
	        }   	
    	}

    }

    /**
     * 直接关闭弹出窗口函数
     *
     * @params dialogId 指定弹出窗口的唯一ID
     * @returns 
     */
    function closePopupDialogDirect(dialogId){
        $("#"+dialogId).dialog('close');
    }


    /**
     * 批量删除选择项目
     *
     * @params msg 显示的错误信息
     * @returns 
     */
    function deleteSelectItem(formId,url,successCallback){
        if($("#"+formId+" input:checked").length==0){
            alert("请选择删除项目！");
            return false;
        }
        if(confirm("确认删除所选项目?")){
        	var action=$("#"+formId).attr("action");
        	if(url!=undefined){
            	$("#"+formId).attr("action",url);
        	}
            ajaxPost(formId,function(data){
            	if(!data.type){
            		publishErrorContentPage(data);
            		return;
            	}
            	if(data.type=="success"){
            		publishMessage(data.message);
            		successCallback(data);
            	}else{
            		publishError(data.message);
            	} 
            });
            $("#"+formId).attr("action",action);
        }
    }
    
    function validJSONResponse(data){
    	if(!data.type){
    		publishErrorContentPage(data);
    		return false;
    	}
    	return true;
    }
    
    /**
     * 按照指定URL作为表单的Action提交表单及回调处理
     * @param formId
     * @param url
     * @param successCallback
     */
    function submitFormByURL(formId,url,successCallback){
    	var action=$("#"+formId).attr("action");
    	if(url!=undefined){
        	$("#"+formId).attr("action",url);
    	}
        ajaxPost(formId,function(data){
        	if(!data.type){
        		publishErrorContentPage(data);
        		return;
        	}
        	if(data.type=="success"){
        		publishMessage(data.message);
        		successCallback(data);
        	}else{
        		publishError(data.message);
        	} 
        });
        $("#"+formId).attr("action",action);
    }
    
    /**
     * Tabs标签初始化构造
     *
     * @params tabsId 标签DIV的ID
     * @returns 
     */
    function tabsInit(tabsId,options){
    	var settings = jQuery.extend({
			cache : true
		}, options);
    	$("#"+tabsId).tabs(settings);
    }
    
    function tabsSetOption(tabsId,option,idx,value){
    	$('#'+tabsId).tabs( option , idx , value );
    }
    
    /**
     * 将Tabs标签指定Tab重置，点击之后会触发重新刷新Tab显示信息
     *
     * @params tabsId 标签DIV的ID
     * @params idx 控制的标签项的索引值，以0开头往后排
     * @returns 
     */
    function tabsResetURL(tabsId,idx){
    	var url = $('#'+tabsId).tabs("getURL",idx);
    	url=getRandedURL(url);
    	$('#'+tabsId).tabs( 'url' , idx , url );
    }

    /**
     * Disable一组Tab标签某个Tab
     *
     * @params tabsId 标签DIV的ID
     * @params idx 控制的标签项的索引值，以0开头往后排
     * @returns 
     */
    function tabsDisableItem(tabsId,idx){
        $('#'+tabsId).tabs('disable', idx);
    }

    /**
     * 刷新Tab标签当前选中Tab
     *
     * @params tabsId 标签DIV的ID
     * @returns 
     */
    function tabsRefreshCurrent(tabsId){
        var selected = $('#'+tabsId).tabs('option', 'selected');

    	$('#'+tabsId).tabs('option', 'ajaxOptions', { cache: false });
        $('#'+tabsId).tabs("load",selected);
        $('#'+tabsId).tabs('option', 'ajaxOptions', { cache: true });
    }
    
    function tabsRefreshCurrentNew(tabsId){
        var selected = $('#'+tabsId).tabs('option', 'selected');

    	$('#'+tabsId).tabs('option', 'ajaxOptions', { cache: false });
        $('#'+tabsId).tabs('option', 'ajaxOptions', { cache: true });
    }
    
	function js_calendar(field,button,showTime){
		var params = [];
		params["inputField"]=field;
		params["trigger"]=button;
		if (showTime) {
			params["dateFormat"]="%Y-%m-%d %H:%M:%S";
			params["showTime"]="true";
  		} else {
  			params["dateFormat"]="%Y-%m-%d";
  		}
		params["minuteStep"]=1;
		params["onTimeChange"]=function(cal) {
			var date = cal.selection.get();
	        if (date) {
	            date = Calendar.intToDate(date);
	            var h=cal.getHours();
	            if(h<10){
	            	h="0"+h;
	            }
	            var m=cal.getMinutes();
	            if(m<10){
	            	m="0"+m;
	            }
	            var tm=Calendar.printDate(date, "%Y-%m-%d"+" "+h+":"+m+":00");
	            //alert(tm);
	            $("#"+field).val(tm);
	        }
		};
		params["onSelect"]=function() {
			this.hide();
		};		

		RANGE_CAL_1 = new Calendar(params);
		
		return RANGE_CAL_1;
	}      