dojo.require("dojo.string")
	
dojo.mixin(dojo.string,{
	contains: function(/* string */ needle, /* string */ haystack, /* bool */ caseInsensitive) {
		if(caseInsensitive) {
			needle = needle.toLowerCase();
			haystack = haystack.toLowerCase();
		}
		return haystack.indexOf(needle) !== -1;
	},
	
	beginsWith: function(/* string */ needle, /* string */ haystack, /* bool */ trimBefore) {
	    if(trimBefore) {
	        needle = dojo.string.trim(needle)
	    }
	    if(needle.length > haystack.length) {
	        return false;
	    }
	    return haystack.substr(0,needle.length) === needle;
	},
	
	endsWith: function(/* string */ needle, /* string */ haystack, /* bool */ trimBefore) {
		var nl = needle.length,
			hl = haystack.length;
	    if(trimBefore) {
	        needle = dojo.string.trim(needle)
	    }
	    if(nl > hl) {
	        return false;
	    }
	    return haystack.substr(hl-nl,hl) === needle;
	}
});