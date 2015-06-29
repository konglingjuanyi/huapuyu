/**
 * Baidu UE JavaScript Library - Web Control
 *
 * calendar.js
 * @author UTer
 */


/**
 * 日历控件
 * <pre>
 * <h3>构造器参数说明：</h3>
 * onselect：用户选中日期时触发事件
 * onviewchange:日期视觉区域切换时触发事件
 * dateStyle：日期样式，可为String或Function
 *
 * <h3>示例：</h3>
 * var myCalendar = new Calendar({
 *     onselect: function (date) {
 *         if (date.getDate() == 1) {alert('不让你选1号');return false;}
 *         G("ShowDate").innerHTML = "您选择的日期是:" + date.getFullYear() + "年" + (date.getMonth()+1) + "月" + date.getDate() + "日";
 *     },
 *     dateStyle: function (date) {
 *         if(date.getDay() == 0 || date.getDay() == 6) return "background:#eee";
 *     }
 * });
 * myCalendar.appendTo(document.body);
 * </pre>
 *
 * @param {Object} params 用于初始化控件的属性集合
 */
function Calendar(params) {
    this.uniqueId = getUniqueId();

    params = params || {};
    this.date = params.date || new Date();
    this.viewDate = new Date(Date.parse(this.date));

    this.onselect = params.onselect || new Function();
    this.onviewchange = params.onviewchange || new Function();
    this.dateStyle = params.dateStyle || "";

    this.wrapId = this.uniqueId + 'Calendar';
    this.headId = this.uniqueId + 'Head';
    this.bodyId = this.uniqueId + 'Body';
    this.titleId = this.uniqueId + 'Title';

    this.wrapClass            = "calendar-wrap";
    this.headClass            = "calendar-head";
    this.bodyClass            = "calendar-body";
    this.titleClass            = "calendar-title";
    this.prevMonthClass        = "calendar-prev-month";
    this.prevYearClass        = "calendar-prev-year";
    this.nextMonthClass        = "calendar-next-month";
    this.nextYearClass        = "calendar-next-year";
}

/**
 * 获得日期当前月份的天数
 *
 * @private
 * @param {Date} 日期
 * @return {Number} 天数
 */
Calendar.getDateCountByMonth = function (date) {
    var d = new Date(date.getFullYear(), date.getMonth() + 1, 0);
    return d.getDate();
};

Calendar.prototype = {
    /**
     * 设置日期
     *
     * @public
     * @param {Date} date 日期
     */
    setDate: function (date) {
        this.date = date;
        this.viewDate = new Date(Date.parse(date));
        G(this.bodyId).innerHTML = this.getBodyHtml();
        G(this.titleId).innerHTML = this.getTitleHtml();
    },

    /**
     * 将控件附加到页面容器
     *
     * @public
     * @param {HTMLElement} el 页面容器元素
     */
    appendTo: function (el) {
        var wrap = new Element('div');
        wrap.id = this.wrapId;
        wrap.className = this.wrapClass;
        el.appendChild(wrap);

        this.renderHead(wrap);
        this.renderBody(wrap);
        this.appendTo = new Function();
    },

    /**
     * 绘制日历控件头
     *
     * @private
     * @param {HTMLElement} container 控件自身容器元素
     */
    renderHead: function (container) {
        var head = new Element('div');
        head.id = this.headId;
        head.className = this.headClass;
        container.append(head);

        var prevYear = new Element('div');
        prevYear.className = this.prevYearClass;
        head.append(prevYear);

        var prevMonth = new Element('div');
        prevMonth.className = this.prevMonthClass;
        head.append(prevMonth);

        var title = new Element('div');
        title.id = this.titleId;
        title.className = this.titleClass;
        title.innerHTML = this.getTitleHtml();
        head.append(title);

        var nextMonth = new Element('div');
        nextMonth.className = this.nextMonthClass;
        head.append(nextMonth);

        var nextYear = new Element('div');
        nextYear.className = this.nextYearClass;
        head.append(nextYear);

        head.onclick = this.getHeadClickHandler();
    },

    /**
     * 绘制日历控件体
     *
     * @private
     * @param {HTMLElement} container 控件自身容器元素
     */
    renderBody: function (container) {
        var body = new Element('div');
        body.className = this.bodyClass;
        body.id = this.bodyId;
        body.onclick = this.getBodyClickHandler();
        container.append(body);

        body.innerHTML = this.getBodyHtml();
    },

    /**
     * 获取控件头点击的事件句柄
     *
     * @private
     * @return {Function} 控件头点击的事件句柄
     */
    getHeadClickHandler: function () {
        var me = this;
        return function (e) {
            e = e || window.event;
            var tar = e.srcElement || e.target;
            var vDate = new Date(me.viewDate);

            switch (tar.className) {
            case me.prevYearClass:
                vDate.setFullYear(me.viewDate.getFullYear() - 1);
                break;
            case me.prevMonthClass:
                vDate.setMonth(me.viewDate.getMonth() - 1);
                break;
            case me.nextMonthClass:
                vDate.setMonth(me.viewDate.getMonth() + 1);
                break;
            case me.nextYearClass:
                vDate.setFullYear(me.viewDate.getFullYear() + 1);
                break;
            }

            var val = me.onviewchange.call(me, vDate);
            if (val !== false) {
                me.viewDate = vDate;
                G(me.bodyId).innerHTML = me.getBodyHtml();
                G(me.titleId).innerHTML = me.getTitleHtml();
            }
        };
    },

    /**
     * 获取控件主体点击的事件句柄
     *
     * @private
     * @return {Function} 控件头点击的事件句柄
     */
    getBodyClickHandler: function () {
        var me = this;
        return function (e) {
            e = e || window.event;
            var tar = e.srcElement || e.target;
            var sign = tar.getAttribute('sign');
            if (sign == 'date') {
                var selDate = new Date(tar.getAttribute('y'),
                                        tar.getAttribute('m'),
                                        tar.getAttribute('d'));
                var reVal = me.onselect.call(me, selDate);
                if (!(reVal === false)) {
                    me.setDate(selDate);
                }
            }
        };
    },

    /**
     * 获取控件标题的html
     *
     * @private
     * @return {String} 控件标题的html
     */
    getTitleHtml: function () {
        return '{0}年{1}月'.format(this.viewDate.getFullYear(), (this.viewDate.getMonth() + 1));
    },

    /**
     * 获取控件主体的html
     *
     * @private
     * @return {String} 控件主体的html
     */
    getBodyHtml: function () {
        var theadHtml = this.getTHeadHtml();
        var tbodyHtml = this.getTBodyHtml();
        var tableTpl = '<table cellpadding="0" cellspacing="0" border="0"><thead>{0}</thead><tbody>{1}</tbody></table>';
        return tableTpl.format(theadHtml, tbodyHtml);
    },

    /**
     * 获取控件表头周的html
     *
     * @private
     * @return {String} 控件表头周的html
     */
    getTHeadHtml: function () {
        var weekMap = ['日', '一', '二', '三', '四', '五', '六'];
        var headHtml = ['<tr>'];
        var headItemTpl = '<td sign="week">{0}</td>';
        for (var i = 0; i < 7; i++) {
            headHtml.push(headItemTpl.format(weekMap[i]));
        }
        headHtml.push('</tr>');
        return headHtml.join('');
    },

    /**
     * 获取控件日期体的html
     *
     * @private
     * @return {String} 控件日期体的html
     */
    getTBodyHtml: function () {
        //模板变量
        var dateTemplate = '<td sign="date" style="{4}" class="{3}" y="{2}" m="{1}" d="{0}">{0}</td>';
        var todayClass = 'calendar-today';
        var thisMonthClass = 'calendar-thismonth';
        var otherMonthClass = 'calendar-othermonth';

        //日期变量
        var viewDate = this.viewDate;
        var year = viewDate.getFullYear();
        var month = viewDate.getMonth();
        var date = viewDate.getDate();

        //前一个月的日期
        var prevMonth = new Date(viewDate.getFullYear(), viewDate.getMonth() - 1, 1);
        //前一个月的天数
        var beforeMonthDays = Calendar.getDateCountByMonth(prevMonth);
        //本月的天数
        var days = Calendar.getDateCountByMonth(viewDate);
        //构造html的循环初始索引
        var index = 0 - new Date(year, month, 1).getDay();

        //make html
        var currDate, currMonth, currYear, currClass, currStyle;
        if (this.dateStyle.constructor == String) {
            currStyle = this.dateStyle;
        }

        //构造上个月和本月的日期html
        var html = [];
        html.push('<tr>');
        for (var trTag = 0; index < days; index++, trTag++) {
            if (trTag > 0 && trTag % 7 === 0) {
                html.push('</tr><tr>');
            }

            if (index < 0) {
                currDate = beforeMonthDays + index + 1;
                currMonth = prevMonth.getMonth();
                currYear = prevMonth.getFullYear();
                currClass = otherMonthClass;
            } else {
                currDate = index + 1;
                currMonth = month;
                currYear = year;
                currClass = thisMonthClass;
                if (date == currDate &&
                    month == this.date.getMonth() &&
                    year == this.date.getFullYear()) {
                    currClass = todayClass;
                }
            }

            if (typeof this.dateStyle == 'function') {
                currStyle = this.dateStyle(new Date(currYear, currMonth, currDate)) || "";
            }

            html.push(dateTemplate.format(currDate,
                                            currMonth,
                                            currYear,
                                            currClass,
                                            (currClass == todayClass ? "" : currStyle)));
        }

        //构造下个月的日期html
        currMonth = month + 1;
        currYear = year;
        if (currMonth > 11) {
            currMonth = 0;
            currYear++;
        }
        currClass = otherMonthClass;
        for (var i = trTag % 7, oriI = i; i > 0 && i < 7; i++) {
            currDate = i - oriI + 1;
            if (typeof this.dateStyle == 'function') {
                currStyle = this.dateStyle(new Date(currYear, currMonth, currDate)) || "";
            }
            html.push(dateTemplate.format(currDate, currMonth, currYear, currClass, currStyle));
        }
        html.push('</tr>');

        return html.join('');
    }
};

/**
 * 区间日历
 * 支持定制参数从而显示单日历
 *
 * @constructor RCalendar
 *
 * @param {Object} config 配置参数
 *
 * 默认参数说明 :
 * beginDate 默认id为 "beginDate" 开始日期input的ID
 * endDate 默认id为 "endDate" 结束日期input的ID  如果设置为非真值, 显示单日历( 今天后的日期不可选 )
 * splitter 默认为 "-" 日期分割符
 * editable 默认为 false 是否可编辑
 * interval 默认为 false 数值型  当设置该参数, 单位为月
 * future   默认 false  当设置为true  可以选择今天以后的日期
 */
function RCalendar(config) {
	this.config = {
		"beginDate" : "beginDate",
		"endDate"   : "endDate",
		"splitter"  : "-",
		"editable"  : false,
		"interval"  : false,
		"future"    : false
	};

	var cfg = config || {};
	for (var prop in cfg) {
		this.config[prop] = cfg[prop];
	}
}
RCalendar.cals = [];
RCalendar.prototype = {
	begin : new Date(),
	end   : new Date(),
	dateFormat : '{0}{3}{1}{3}{2}',
	dateRe : /^\d{4}-\d{1,2}-\d{1,2}$/,

	/**
	 * 返回用于显示日历的容器
	 */
	drawDatePanel  : function () {
		var datePanel = G('datePanel');
		if(!datePanel) {
			var div = document.createElement('div');
			div.id = 'datePanel';
			document.body.appendChild(div);
		}
		return G('datePanel');
	},

	/**
	 * 采用Iframe用来压住select
	 */
	drawIframe : function () {
		var ifr = G('ifr-calendar');
		if(!ifr) {
			var ifr = document.createElement('iframe');
			ifr.id = 'ifr-calendar';
			document.body.appendChild(ifr);
		}
		return G('ifr-calendar');
	},

	/**
	 * 设置日历容器的位置
	 */
	setPos : function (input) {
		var pos = G(input).getPosition();
		pos.height = G(input).offsetHeight;
		var datePanel = this.drawDatePanel();
		var position = {position : 'absolute', left : pos.left + 'px', top : (pos.top + pos.height - 1) + 'px'};
		datePanel.css({zIndex : 9999}).css(position);

		var w = datePanel.offsetWidth;
		var h = datePanel.offsetHeight;
		var ifr = this.drawIframe();
		ifr.css({zIndex : 9998, width : w + 'px', height : h + 'px', border : 'none'}).css(position);
	},

	/**
	 * 日历类的初始化
	 */
	init : function () {
		var config = this.config;

		var beginDateInput = G(config.beginDate);
		var beginDateInputValue = beginDateInput.value;
		RCalendar.cals.push(beginDateInput);

		if (config.endDate) {
			var endDateInput = G(config.endDate);
			var endDateInputValue = endDateInput.value;
			RCalendar.cals.push(endDateInput);
		}

		this.begin = this.parseDate(G(config.beginDate).value);
		if (config.endDate) {
			this.end = this.parseDate(G(config.endDate).value);
		}
		var me = this;

		if(!config.editable) {
			beginDateInput.readOnly = "readOnly";
			beginDateInput.css({"cursor" : "text"});
			if (config.endDate) {
				endDateInput.readOnly = "readOnly";
				endDateInput.css({"cursor" : "text"});
			}
		}else{
			beginDateInput.readOnly = "";
			if (config.endDate) {
				endDateInput.readOnly = "";
			}
		}

		/**
		 * 开始日期的显示
		 */
		function showBeginCal() {
			me.begin = me.parseDate(G(config.beginDate).value);
            if (config.endDate) {
			    me.end = me.parseDate(G(config.endDate).value);
            }
			var cal = new Calendar({
				"onselect" : function (date) {
					// 时间间隔
					if (config.interval !== false && G(config.endDate).value) {
						var months = parseInt(config.interval, 10);
						var st = date;
						var stYear = date.getFullYear();
						var stMonth = date.getMonth() + 1;
						var stDate = date.getDate();
						var en = me.end;
						if (new Date(stYear, stMonth + months - 1, stDate) < en) {
							return false;
						}
					}
					// 如果参数日期大于endDate，置灰，不可点击
					if(date > me.end || date > new Date()) {
						if (config.future && (!config.endDate || !G(config.endDate).value || date <= me.end)) {
							// return true;
						} else {
							return false;
						}
					}
					me.setInputValue(beginDateInput, me.formatDate(date));
					me.begin = date;
				}, "date" : me.begin || new Date(), "dateStyle" : function (date) {
					// 时间间隔
					if (config.interval !== false && G(config.endDate).value) {
						var months = parseInt(config.interval, 10);
						var st = date;
						var stYear = date.getFullYear();
						var stMonth = date.getMonth() + 1;
						var stDate = date.getDate();
						var en = me.end;
						if (new Date(stYear, stMonth + months - 1, stDate) < en) {
							return "color:#999;text-decoration:line-through";
						}
					}
					if(date > me.end || date > new Date()) {
						if (config.future && (!config.endDate || !G(config.endDate).value || date <= me.end)) {
							// return true;
						} else {
							return "color:#999;text-decoration:line-through";
						}
					}
				}
			});
			var datePanel = me.drawDatePanel();
			datePanel.setHTML('');
			cal.appendTo(datePanel);
			me.setPos(beginDateInput);
		}

		/**
		 * 结束日期的显示
		 */
		function showEndCal() {
			me.begin = me.parseDate(G(config.beginDate).value);
			me.end = me.parseDate(G(config.endDate).value);
			var cal = new Calendar({
				"onselect" : function (date) {
					// 时间间隔
					if (config.interval !== false && G(config.beginDate).value) {
						var months = parseInt(config.interval, 10);
						var st = me.begin;
						var enYear = date.getFullYear();
						var enMonth = date.getMonth() + 1;
						var enDate = date.getDate();
						if (new Date(enYear, enMonth - months - 1, enDate) > st) {
							return false;
						}
					}
					// 如果参数日期大于endDate，置灰，不可点击
					if((G(config.beginDate).value && date < me.begin) || date > new Date()) {
						if (config.future && date >= me.begin) {
							// return true;
						} else {
							return false;
						}
					}
					me.setInputValue(endDateInput, me.formatDate(date));
					me.end = date;
				}, "date" : me.end || new Date(), "dateStyle" : function (date) {
					// 时间间隔
					if (config.interval !== false && G(config.beginDate).value) {
						var months = parseInt(config.interval, 10);
						var st = me.begin;
						var enYear = date.getFullYear();
						var enMonth = date.getMonth() + 1;
						var enDate = date.getDate();
						if (new Date(enYear, enMonth - months - 1, enDate) > st) {
							return "color:#999;text-decoration:line-through";
						}
					}
					if((G(config.beginDate).value && date < me.begin) || date > new Date()) {
						if (config.future && date >= me.begin) {
							// return true;
						} else {
							return "color:#999;text-decoration:line-through";
						}
					}
				}
			});
			var datePanel = me.drawDatePanel();
			datePanel.setHTML('');
			cal.appendTo(datePanel);
			me.setPos(endDateInput);
		}

		function doclick(e) {
			var evt = new Event(e);
			var datePanel = me.drawDatePanel();
			var isInPos = false;
			for (var i = 0; i < RCalendar.cals.length; i++) {
				if (evt.isIn(RCalendar.cals[i])) {
					isInPos = true;
					break;
				}
			}
			if(!isInPos  && !evt.isIn(datePanel)) {
				if (config.endDate) {
					if (!evt.isIn(endDateInput)) {
						datePanel.css({top : '-10000px'});
					}
				} else {
					datePanel.css({top : '-10000px'});
				}
				if (G('ifr-calendar')) {
					G('ifr-calendar').css({top : '-10000px'});
				}
			}
		}

		Event.add(beginDateInput, 'click', showBeginCal);
		if (config.endDate) {
			Event.add(endDateInput, 'click', showEndCal);
		}
		Event.add(document, 'click', doclick);

	},

	/**
	 * 设置文本框的值
	 */
	setInputValue : function (input, formatedDate) {
		G(input).value = formatedDate;
	},

	/**
	 * 解析日期对象至字符串
	 *
	 * @param {Date} dt 日期对象
	 * @return {String} 转换后的字符串
	 */
	formatDate : function(dt) {
		var _dt = new Date(dt);
		var _dtYear = _dt.getFullYear() + '';
		var _dtMonth = this.zeroPatch(_dt.getMonth() + 1);
		var _dtDate = this.zeroPatch(_dt.getDate());

		return this.dateFormat.format(_dtYear, _dtMonth, _dtDate, this.config.splitter);
	},

	/**
	 * 解析字符串至日期对象
	 *
	 * @param {String} s 字符串
	 * @return {Date} Date对象
	 */
	parseDate : function (s) {
		var splitter = this.config.splitter;
		var dt = [];
		if (splitter == '') {
			dt[0] = s.substr(0, 4);
			dt[1] = s.substr(4, 2);
			dt[2] = s.substr(6, 2);
		} else {
			dt = s.split(splitter);
		}
		return dt.length == 3 ? new Date(dt[0], parseInt(dt[1], 10) - 1, dt[2]) : new Date();
	},

	/**
	 * 补零处理
	 *
	 * @param {Number} n 数字
	 * @return {Number|String} 补零后的数值或字符串
	 */
	zeroPatch : function (n) {
		return n < 10 ? '0' + n : n;
	}
};
/**
 * 调用函数  (向后兼容, 保留)
 */
var RangeCalendar = {
	init : function (config) {
		new RCalendar(config).init();
	}
}