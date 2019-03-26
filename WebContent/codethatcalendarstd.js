// CodeThatCalendar STANDARD
// Version: 1.3.4 (08.05.2004.1)
// THE SCRIPT IS FREE FOR NON-COMMERCIAL AND COMMERCIAL USE.
// Copyright (c) 2003-2004 by CodeThat.Com
// http://www.codethat.com/

function UA() {
	var t = this, nv = navigator, n = nv.userAgent.toLowerCase();
	t.win = n.indexOf('win') >= 0;
	t.mac = n.indexOf('mac') >= 0;
	t.DOM = document.getElementById ? true : false;
	t.dynDOM = document.createElement && document.addEventListener;
	t.khtml = nv.vendor == 'KDE';
	var idx = n.indexOf('opera');
	t.opera = idx != -1;
	if (t.opera) {
		t.vers = parseFloat(n.substr(idx + 6));
		t.major = Math.floor(t.vers);
		t.opera5 = t.major == 5;
		t.opera6 = t.major == 6;
		t.opera7 = t.major == 7;
		t.opera7up = t.vers >= 7;
	}
	t.oldOpera = t.opera5 || t.opera6;
	idx = n.indexOf('msie');
	if (idx >= 0 && !t.opera && !t.khtml) {
		t.vers = parseFloat(n.substr(idx + 5));
		t.ie3down = t.vers < 4;
		t.ie = t.ie4up = document.all && document.all.item && !t.ie3down;
		t.ie5up = t.ie && t.DOM;
		t.ie55up = t.ie && t.vers >= 5.5;
		t.ie6up = t.ie && t.vers >= 6
	}
	t.cm = document.compatMode;
	t.css1cm = t.cm == 'CSS1Compat';
	t.nn4 = nv.appName == "Netscape" && !t.DOM && !t.opera;
	if (t.nn4)
		t.vers = parseFloat(nv.appVersion);
	t.moz = t.nn6up = t.gecko = n.indexOf('gecko') != -1;
	if (t.gecko)
		t.vers = parseFloat(n.substr(n.indexOf('rv:') + 3));
	t.nn7up = t.gecko && t.vers > 1;
	t.hj = n.indexOf('hotjava') != -1;
	t.aol = n.indexOf('aol') != -1;
	t.aol4up = t.aol && t.ie4up;
	t.major = Math.floor(t.vers);
	t.supp = t.supported = t.oldOpera || t.opera7up || t.ie || t.moz || t.nn4
			|| t.DOM
};
function Undef(o) {
	return typeof (o) == 'undefined' || o === '' || o == null
};
function Def(o) {
	return !Undef(o)
};
var ua = new UA();
function CodeThatSetMY(m, y, f) {
	switch (f) {
	case 0: {
		++m;
		if (m == 12) {
			m = 0;
			y++;
		}
		break;
	}
	case 1: {
		--m;
		if (m == -1) {
			m = 11;
			y--;
		}
		break;
	}
	case 2: {
		++y;
		break;
	}
	case 3: {
		--y;
		break;
	}
	case 4: {
		break;
	}
	}
	if (!window.opener)
		window.opener = window.parent;
	var c;
	if (ua.moz && ua.vers >= 1.4) {
		if (this.opener)
			c = this.opener.codethatcalendar;
		else
			c = this.parent.codethatcalendar;
		window.opener = this.parent;
	} else if (window.opener)
		c = window.opener.codethatcalendar;
	if (c) {
		c.date.setFullYear(y);
		c.date.setMonth(m);
	}
	location.reload();
};
function CodeThatFind(d, f) {
	if (d.getElementById && Def(d.getElementById(f))) {
		return d.getElementById(f);
	} else if (ua.ie4up) {
		return d.all[f];
	} else {
		if (d.forms.item)
			for ( var i = 0; d.forms.item(i) != null; ++i) {
				if (d.forms.item(i).namedItem && d.forms.item(i).namedItem(f))
					return d.forms.item(i).namedItem(f);
				if (d.forms.item(i).elements && d.forms.item(i).elements[f])
					return d.forms.item(i).elements[f];
			}
		for ( var form in d.forms)
			if (d.forms[form].elements && d.forms[form].elements[f])
				return d.forms[form].elements[f];
	}
	return null;
};
function CodeThatAlign(n) {
	return n < 10 ? ("0" + n) : ("" + n);
	;
};
function CodeThatDateFormat(f, d, m, y) {
	var s = f;
	d = d < 10 ? ("0" + d) : d;
	s = s.replace("dd", d);
	m++;
	m = m < 10 ? ("0" + m) : m;
	s = s.replace("MM", m);
	s = s.replace("yyyy", y);
	if (CodeThatFind(document, 'time')) {
		var t = CodeThatFind(document, 'time').value;
		var hours = parseInt(t.substring(0, 2) - 0);
		if (hours < 0 || hours > 23) {
			hours = hours % 24;
			hours += (hours < 0 ? 24 : 0);
		}
		s = s.replace("HH", CodeThatAlign(hours));
		var minutes = parseInt(t.substring(3, 5) - 0);
		if (minutes < 0 || minutes > 59) {
			minutes = minutes % 60;
			minutes += (minutes < 0 ? 60 : 0);
		}
		s = s.replace("mm", CodeThatAlign(minutes));
		var seconds = parseInt(t.substring(6, 8) - 0);
		if (seconds < 0 || seconds > 59) {
			seconds = seconds % 60;
			seconds += (seconds < 0 ? 60 : 0);
		}
		s = s.replace("ss", CodeThatAlign(seconds));
	}
	return s;
};
function CodeThatSetDay(c, f, d, m, y, i, ifr) {
	var doc;
	var w = window.opener || this.parent;
	if (w && !i)
		doc = w.document;
	else
		doc = document;
	var e = CodeThatFind(doc, c);
	if (Def(e)) {
		e.value = CodeThatDateFormat(f, d, m, y);
		if (e.onchange)
			e.onchange();
	}
	if (w && !i) {
		if (Def(w) && Def(ifr)) {
			var iframe = CodeThatFind(doc, ifr);
			if (Def(iframe))
				iframe.style.visibility = 'hidden';
			if (ua.opera6) {
				var d = CodeThatFind(doc, "calendar_div");
				if (Def(d))
					d.style.visibility = 'hidden';
			}
		} else {
			window.close();
		}
	}
};
function CodeThatCalendar(def) {
	this.def = def;
	this.links = {};
	this.styles = {};
	this.hideifr = true;
};
{
	var CTc = CodeThatCalendar.prototype;
	CTc.getCss = function(key, d) {
		return "";
	};
	CTc.hide = function() {
		if (window.parent && this.hideifr && this.ifr) {
			var iframe = CodeThatFind(window.parent.document, this.ifr);
			if (iframe)
				iframe.style.visibility = 'hidden';
		}
	};
	CTc.create = function(d, ctl) {
		var i = false;
		this.i = i;
		if (!this.date)
			this.date = window.date || new Date();
		var def = this.def;
		var acts = '';
		if (this.ifr && def.headerstyle.type != "comboboxes") {
			acts = 'onMouseOver="window.parent.codethatcalendar.hideifr=false;"';
			acts += " onMouseOut=\"window.parent.codethatcalendar.hideifr=true;setTimeout('window.parent.codethatcalendar.hide();',1000);\""
		}
		d.write("<table " + (Def(this.ifr) ? "align=\"center\"" : "")
				+ " cellspacing=0 cellpadding=0 " + acts + " width="
				+ def.width + " border=" + (def.border_width || 0)
				+ " bordercolor='" + (def.border_color || '#000000') + "'>");
		d.write("<tr>");
		if (i)
			this.createButtons(d, i);
		else {
			if (def.headerstyle.type == "buttons")
				this.createButtons(d, i);
			else if (def.headerstyle.type == "comboboxes")
				this.createCombos(d);
		}
		d.write("</tr>");
		var day = 0;
		d.write("<tr>");
		this.createWeekdays(d);
		d.write("</tr>");
		var w = this.createMonth(d);
		if (def.showtime) {
			this.createTime(d);
		}
		if (ua.opera && w == 4) {
			d.write("<tr><td colspan=7>&nbsp;<td></tr>");
		}
		d.write("</table>");
	};
	CTc.createTime = function(d) {
		d.write("<tr><td colspan=7 align=center>" + (ua.nn4 ? "<form>" : "")
				+ "<input " + (ua.nn4 ? "name" : "id")
				+ "='time' type=textarea value='"
				+ CodeThatAlign(this.date.getHours()) + ":"
				+ CodeThatAlign(this.date.getMinutes()) + ":"
				+ CodeThatAlign(this.date.getSeconds()) + "'>"
				+ (ua.nn4 ? "</form>" : "") + "</td></tr>");
	};
	CTc.createMonth = function(d) {
		var cd = this.date.getDate();
		var cm = this.date.getMonth();
		var cy = this.date.getYear();
		var cday = this.date.getDay();
		var def = this.def;
		var cur_day = 1;
		this.date.setDate(cur_day);
		var thisweek = false;
		var start = (this.date.getDay() - def.firstday);
		start += (start < 0 ? 7 : 0);
		cur_day -= start;
		var css, thismonth = false, weekend, thisday;
		var w;
		for (w = 0; w < 6; ++w) {
			d.write("<tr>");
			for ( var day = 0; day < 7; ++day) {
				weekend = day + def.firstday;
				weekend -= (weekend < 7 ? 0 : 7);
				weekend = weekend == 0 || weekend == 6;
				this.date.setDate(cur_day);
				if (this.date.getDate() == 1)
					thismonth = !thismonth;
				if (day == 0) {
					if (w != 0) {
						thisweek = cd - this.date.getDate();
						if (thisweek < 7 && thisweek >= 0)
							thisweek = true;
						else
							thisweek = false;
					} else
						thisweek = (start + cd) < 8;
				}
				if (day == 7 && thisweek)
					thisweek = false;
				thisday = this.date.getDate() == cd && thismonth;
				css = thisday ? "cd_css" : (thisweek ? "tw_css"
						: (thismonth ? (weekend ? "we_css" : "wd_css")
								: (weekend ? "weom_css" : "wdom_css")));
				d.write("<td align=center width=14% " + this.getCss(css)
						+ "><A " + this.getCss(css) + this.getRef() + ">");
				d.write(this.date.getDate());
				d.write("</A></td>");
				cur_day = this.date.getDate() + 1;
			}
			d.write("</tr>");
			this.date.setDate(cur_day);
			if (this.date.getDate() < 8 && w > 3)
				break;
		}
		this.date.setDate(cd);
		this.date.setMonth(cm);
		this.date.setYear(cy);
		return w;
	};
	CTc.setLink = function(s, l) {
		this.links[s] = l;
	};
	CTc.setStyle = function(s, l) {
		this.styles[s] = l;
	};
	CTc.getStyle = function() {
		var c;
		if (this.i) {
			var fd = CodeThatDateFormat(this.def.dtype, this.date.getDate(),
					this.date.getMonth(), this.date.getFullYear());
			if (this.styles[fd])
				c = this.styles[fd];
		}
		return c;
	};
	CTc.getRef = function() {
		var ref;
		if (this.i) {
			var fd = CodeThatDateFormat(this.def.dtype, this.date.getDate(),
					this.date.getMonth(), this.date.getFullYear());
			if (this.links[fd])
				ref = " href='" + this.links[fd] + "'";
		}
		ref = ref
				|| (" href='javascript:CodeThatSetDay(\"" + this.ctl + "\",\""
						+ this.def.dtype + "\"," + this.date.getDate() + ","
						+ this.date.getMonth() + "," + this.date.getFullYear()
						+ "," + this.i + ",\""
						+ (Def(this.ifr) ? this.ifr : "") + "\")'");
		return ref;
	};
	CTc.createWeekdays = function(d) {
		var def = this.def;
		var weekend;
		for ( var day = 0; day < 7; ++day) {
			weekend = day + def.firstday;
			weekend -= (weekend < 7 ? 0 : 7);
			d.write("<td align=center " + this.getCss("dn_css") + ">"
					+ def.daynames[weekend] + "</td>");
		}
	};
	CTc.createButtons = function(d, i) {
		var col = 9, dh = this.def.headerstyle;
		for ( var p in dh)
			--col;
		if (dh.imgprevy && !i)
			d
					.write("<td width=14% align=left><a href='javascript:CodeThatSetMY("
							+ this.date.getMonth()
							+ ","
							+ this.date.getFullYear()
							+ ",3)'><IMG border=0 src='"
							+ dh.imgprevy
							+ "'</IMG></A>");
		if (dh.imgprevm && !i)
			d
					.write("<td width=14% align=left><a href='javascript:CodeThatSetMY("
							+ this.date.getMonth()
							+ ","
							+ this.date.getFullYear()
							+ ",1)'><IMG border=0 src='"
							+ dh.imgprevm
							+ "'</IMG></A>");
		d.write("<td colspan=" + col + " align=center "
				+ this.getCss("css", dh) + ">"
				+ this.def.monthnames[this.date.getMonth()] + "&nbsp;"
				+ this.date.getFullYear() + "</td>");
		if (dh.imgnextm && !i)
			d
					.write("<td width=14% align=right><a href='javascript:CodeThatSetMY("
							+ this.date.getMonth()
							+ ","
							+ this.date.getFullYear()
							+ ",0)'><IMG border=0 src='"
							+ dh.imgnextm
							+ "'</IMG></A></td>");
		if (dh.imgnexty && !i)
			d
					.write("<td width=14% align=right><a href='javascript:CodeThatSetMY("
							+ this.date.getMonth()
							+ ","
							+ this.date.getFullYear()
							+ ",2)'><IMG border=0 src='"
							+ dh.imgnexty
							+ "'</IMG></A></td>");
	};
	CTc.createCombos = function(d, l) {
		var dh = this.def.headerstyle;
		d.write("<td colspan=7 " + this.getCss("css", dh) + ">");
		if (ua.nn4)
			d.write("<form>");
		d
				.write("<select align=left "
						+ this.getCss("css", dh)
						+ " "
						+ (ua.nn4 ? "name" : "id")
						+ "='month' onchange='CodeThatSetMY(CodeThatFind(document,\"month\").selectedIndex,"
						+ dh.yearrange[0]
						+ "+CodeThatFind(document,\"year\").selectedIndex,4);'>");
		var i = 0;
		for (; i < 12; ++i)
			d.write("<option "
					+ (i == this.date.getMonth() ? "selected='selected'" : "")
					+ " value='" + i + "'>" + this.def.monthnames[i]
					+ "</option>");
		d.write("</select>");
		d
				.write("<select align=right "
						+ this.getCss("css", dh)
						+ " "
						+ (ua.nn4 ? "name" : "id")
						+ "='year' onchange='CodeThatSetMY(CodeThatFind(document,\"month\").selectedIndex,"
						+ dh.yearrange[0]
						+ "+CodeThatFind(document,\"year\").selectedIndex,4);'>");
		for (i = dh.yearrange[0]; i <= dh.yearrange[1]; ++i)
			d.write("<option "
					+ (i == this.date.getFullYear() ? "selected='selected'"
							: "") + " value='" + i + "'>" + i + "</option>");
		d.write("</select>");
		if (ua.nn4)
			d.write("</form>");
		d.write("</td>");
	};
	CTc.parseValue = function(s, d) {
		var f = this.def.dtype;
		d = d || this.date;
		var t;
		if (f.indexOf("yyyy") != -1) {
			t = parseInt(s.substr(f.indexOf("yyyy"), 4) - 0);
			if (!isNaN(t) && typeof (t) != 'undefined')
				d.setFullYear(t);
		}
		if (f.indexOf("MM") != -1) {
			t = parseInt(s.substr(f.indexOf("MM"), 2) - 1);
			if (!isNaN(t) && typeof (t) != 'undefined')
				d.setMonth(t);
		}
		if (f.indexOf("dd") != -1) {
			t = parseInt(s.substr(f.indexOf("dd"), 2) - 0);
			if (!isNaN(t) && typeof (t) != 'undefined')
				d.setDate(t);
		}
		if (f.indexOf("HH") != -1) {
			t = parseInt(s.substr(f.indexOf("HH"), 2) - 0);
			if (!isNaN(t) && typeof (t) != 'undefined')
				d.setHours(t);
		}
		if (f.indexOf("mm") != -1) {
			t = parseInt(s.substr(f.indexOf("mm"), 2) - 0);
			if (!isNaN(t) && typeof (t) != 'undefined')
				d.setMinutes(t);
		}
		if (f.indexOf("ss") != -1) {
			t = parseInt(s.substr(f.indexOf("ss"), 2) - 0);
			if (!isNaN(t) && typeof (t) != 'undefined')
				d.setSeconds(t);
		}
	};
	CTc.popup = function(ctl) {
		window.codethatcalendar = this;
		this.ctl = ctl;
		var e = CodeThatFind(document, ctl);
		this.date = new Date();
		if (e.value != "")
			this.parseValue(e.value);
		var w = window
				.open(
						"./codethatcalendar.jsp",
						"",
						'width='
								+ this.def.windoww
								+ ',height='
								+ this.def.windowh
								+ ',status=no,resizable=no,top=200,left=200,dependent=yes,alwaysRaised=yes');
		w.opener = window;
		w.focus();
	};
	CTc.innerpopup = function(ctl, ifr) {
		window.codethatcalendar = this;
		this.ctl = ctl;
		this.ifr = ifr;
		this.hideifr = false;
		var e = CodeThatFind(document, ctl);
		this.date = new Date();
		if (e.value != "")
			this.parseValue(e.value);
		var w = CodeThatFind(document, ifr);
		if (ua.oldOpera) {
			w = document.frames[ifr];
			w.location.reload();
			var d = CodeThatFind(document, "calendar_div");
			d.style.visibility = 'visible';
		} else {
			w.src = "/Income/pages/codethatcalendar.jsp";
			w.style.visibility = 'visible';
		}
		w.opener = window;
		w.height = this.def.windowh;
		w.width = this.def.windoww;
		if (ua.moz) {
		} else {
			w.focus();
		}
	};
}