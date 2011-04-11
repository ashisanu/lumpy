language c
	import "c/gc.o"
	import "c/string.o"
	import "c/lang.o"
	import "c/gc.h"
	import "c/lang.h"
	import "c/string.h"
end

extern
	function joinstr:String(str1:string, str2:string) = "joinstr" force
	function gccollect:void() = "gccollect"
	
	function print:void(text:String) = "print_string"
	function print:void(text:int) = "print_int"
end


