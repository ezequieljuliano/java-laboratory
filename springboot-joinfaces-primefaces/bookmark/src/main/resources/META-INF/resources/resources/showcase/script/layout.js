/** 
 * PrimeFaces Showcase
 */
$(document).ready(function() {
    
    var Showcase = {

        init: function() {    
            this.wrapper = $(document.body).children('.layout-wrapper');
            this.topbar = this.wrapper.children('.layout-topbar');
            this.sidebar = this.wrapper.children('.layout-sidebar');
            this.menu = $('#layout-menu');
            this.menulinks = this.menu.find('a');
            this.submenuItems = this.menu.find('> li');
            this.menuButton = $('#menu-button');
            this.expandedMenuitems = this.expandedMenuitems||[];
            this.nano = this.sidebar.children('.nano');
            this.searchInput = this.sidebar.find('.search-input > input');

            this.restoreMenuState();
            
            this.nano.nanoScroller({flash:true});

            this._bindEvents();
        },
    
        _bindEvents: function() {
            var $this = this;

            this.sidebar.on('click', function() {
                $this.menuClick = true;
            });

            this.menuButton.off('click').on('click', function(e) {
                $this.menuClick = true;
                if($this.isMobile()) {
                    $this.wrapper.toggleClass('layout-sidebar-mobile-active');   
                    $(document.body).toggleClass('hidden-overflow-body');   
                }
                
                e.preventDefault();
            });    
    
            this.menulinks.on('click', function (e) {
                var link = $(this),
                item = link.parent('li'),
                submenu = item.children('div');
    
                if (item.hasClass('active-menuitem')) {
                    if (submenu.length) {
                        $this.removeMenuitem(item.attr('id'));
                        item.removeClass('active-menuitem');
    
                        submenu.slideUp();
                    }
                }
                else {
                    $this.addMenuitem(item.attr('id'));
                    $this.deactivateItems(item.siblings(), true);
                    $this.activate(item);
                }

                setTimeout(function() {
                    $(".nano").nanoScroller();
                }, 500);
                
                if (submenu.length) {
                    e.preventDefault();
                }
            });

            $(document.body).on('click', function() {
                if(!$this.menuClick && $this.isMobile()) {
                    $this.wrapper.removeClass('layout-sidebar-mobile-active');
                    $(document.body).removeClass('hidden-overflow-body');  
                }
    
                $this.menuClick = false;
            });
            
            this.searchInput.on('keyup', function(e) {
                var input = $(this), 
                searchedValue = input.val().toLowerCase(),
                matchSub = false;

                for(var i = 0; i < $this.submenuItems.length; i++) {
                    var submenuItem = $this.submenuItems.eq(i),
                    submenuLink = submenuItem.children('a'),
                    submenuLinkVal = $.trim(submenuLink.children('span').text()).toLowerCase();
                    
                    if(submenuLinkVal.search(searchedValue) < 0 || searchedValue.length === 0) {  
                        var menulinksInSubmenu = submenuLink.next().find('a');
                        
                        for(var j = 0; j < menulinksInSubmenu.length; j++) {
                            var menulink = menulinksInSubmenu.eq(j),
                            menuitem = menulink.parent(),
                            itemVal = $.trim(menulink.children('span').text()).toLowerCase();
                            
                            if(itemVal.search(searchedValue) >= 0) {
                                menuitem.show();
                                matchSub = true;
                            }
                            else{
                                menuitem.hide();
                            }
                        }
                        
                        if(matchSub) {
                            submenuItem.show();
                            matchSub = false;
                        }
                        else {
                            submenuItem.hide();
                        }
                    }
                    else {
                        submenuItem.show();
                    }
                }   
            });
            
            this.topbar.find('> .layout-topbar-menu .themes-overlay a').on("click", function(e) {
                var href = $(this).attr('href');
                if(href && href !== '#') {
                    window.location.href = href;
                }
                else {
                    var theme = $(this).data("theme");
                    
                    if(theme.startsWith('luna')) {
                        $('.content-implementation').addClass('dark-content');
                    }
                    else {
                        $('.content-implementation').removeClass('dark-content');
                    }
                    
                    changeTheme([{name:'globaltheme', value:theme}]);
                    PrimeFaces.changeTheme(theme);
                    e.preventDefault();
                }
            });
        },

        removeMenuitem: function (id) {
            this.expandedMenuitems = $.grep(this.expandedMenuitems, function (value) {
                return value !== id;
            });
    
            this.saveMenuState();
        },
    
        addMenuitem: function (id) {
            if ($.inArray(id, this.expandedMenuitems) === -1) {
                this.expandedMenuitems.push(id);
            }
            this.saveMenuState();
        },
    
        saveMenuState: function() {
            $.cookie('showcase_expandeditems', this.expandedMenuitems.join(','), {path: '/'});
        },
    
        clearMenuState: function() {
            $.removeCookie('showcase_expandeditems', {path: '/'});
        },
    
        activate: function(item) {
            var submenu = item.children('div');
            item.addClass('active-menuitem');

            if(submenu.length) {
                submenu.slideDown();
            }
        },
    
        deactivate: function(item) {
            var submenu = item.children('div');
            item.removeClass('active-menuitem');
    
            if(submenu.length) {
                submenu.hide();
            }
        },
    
        deactivateItems: function(items, animate) {
            var $this = this;
    
            for(var i = 0; i < items.length; i++) {
                var item = items.eq(i),
                submenu = item.children('div');
    
                if(submenu.length) {
                    if(item.hasClass('active-menuitem')) {
                        var activeSubItems = item.find('.active-menuitem');
                        item.removeClass('active-menuitem');
    
                        if(animate) {
                            submenu.slideUp('normal', function() {
                                $(this).parent().find('.active-menuitem').each(function() {
                                    $this.deactivate($(this));
                                });
                            });
                        }
                        else {
                            submenu.hide();
                            item.find('.active-menuitem').each(function() {
                                $this.deactivate($(this));
                            });
                        }
    
                        $this.removeMenuitem(item.attr('id'));
                        activeSubItems.each(function() {
                            $this.removeMenuitem($(this).attr('id'));
                        });
                    }
                    else {
                        item.find('.active-menuitem').each(function() {
                            var subItem = $(this);
                            $this.deactivate(subItem);
                            $this.removeMenuitem(subItem.attr('id'));
                        });
                    }
                }
                else if(item.hasClass('active-menuitem')) {
                    $this.deactivate(item);
                    $this.removeMenuitem(item.attr('id'));
                }
            }
        },
    
        restoreMenuState: function() {
            var menucookie = $.cookie('showcase_expandeditems');
            if (menucookie) {
                this.expandedMenuitems = menucookie.split(',');
                for (var i = 0; i < this.expandedMenuitems.length; i++) {
                    var id = this.expandedMenuitems[i];
                    if (id) {
                        var menuitem = $("#" + this.expandedMenuitems[i].replace(/:/g, "\\:"));
                        menuitem.addClass('active-menuitem');
    
                        var submenu = menuitem.children('div');
                        if(submenu.length) {
                            submenu.show();
                        }
                    }
                }
            }
        },
    
        isMobile: function( ){
            return window.innerWidth <= 1024;
        }

    };
    
    Showcase.init();
});
/*!
 * jQuery Cookie Plugin v1.4.1
 * https://github.com/carhartl/jquery-cookie
 *
 * Copyright 2006, 2014 Klaus Hartl
 * Released under the MIT license
 */
(function (factory) {
	if (typeof define === 'function' && define.amd) {
		// AMD (Register as an anonymous module)
		define(['jquery'], factory);
	} else if (typeof exports === 'object') {
		// Node/CommonJS
		module.exports = factory(require('jquery'));
	} else {
		// Browser globals
		factory(jQuery);
	}
}(function ($) {

	var pluses = /\+/g;

	function encode(s) {
		return config.raw ? s : encodeURIComponent(s);
	}

	function decode(s) {
		return config.raw ? s : decodeURIComponent(s);
	}

	function stringifyCookieValue(value) {
		return encode(config.json ? JSON.stringify(value) : String(value));
	}

	function parseCookieValue(s) {
		if (s.indexOf('"') === 0) {
			// This is a quoted cookie as according to RFC2068, unescape...
			s = s.slice(1, -1).replace(/\\"/g, '"').replace(/\\\\/g, '\\');
		}

		try {
			// Replace server-side written pluses with spaces.
			// If we can't decode the cookie, ignore it, it's unusable.
			// If we can't parse the cookie, ignore it, it's unusable.
			s = decodeURIComponent(s.replace(pluses, ' '));
			return config.json ? JSON.parse(s) : s;
		} catch(e) {}
	}

	function read(s, converter) {
		var value = config.raw ? s : parseCookieValue(s);
		return $.isFunction(converter) ? converter(value) : value;
	}

	var config = $.cookie = function (key, value, options) {

		// Write

		if (arguments.length > 1 && !$.isFunction(value)) {
			options = $.extend({}, config.defaults, options);

			if (typeof options.expires === 'number') {
				var days = options.expires, t = options.expires = new Date();
				t.setMilliseconds(t.getMilliseconds() + days * 864e+5);
			}

			return (document.cookie = [
				encode(key), '=', stringifyCookieValue(value),
				options.expires ? '; expires=' + options.expires.toUTCString() : '', // use expires attribute, max-age is not supported by IE
				options.path    ? '; path=' + options.path : '',
				options.domain  ? '; domain=' + options.domain : '',
				options.secure  ? '; secure' : ''
			].join(''));
		}

		// Read

		var result = key ? undefined : {},
			// To prevent the for loop in the first place assign an empty array
			// in case there are no cookies at all. Also prevents odd result when
			// calling $.cookie().
			cookies = document.cookie ? document.cookie.split('; ') : [],
			i = 0,
			l = cookies.length;

		for (; i < l; i++) {
			var parts = cookies[i].split('='),
				name = decode(parts.shift()),
				cookie = parts.join('=');

			if (key === name) {
				// If second argument (value) is a function it's a converter...
				result = read(cookie, value);
				break;
			}

			// Prevent storing a cookie that we couldn't decode.
			if (!key && (cookie = read(cookie)) !== undefined) {
				result[name] = cookie;
			}
		}

		return result;
	};

	config.defaults = {};

	$.removeCookie = function (key, options) {
		// Must not alter options, thus extending a fresh object...
		$.cookie(key, '', $.extend({}, options, { expires: -1 }));
		return !$.cookie(key);
	};

}));
