#include "C:\Users\Coolo\Documents\NetBeansProjects\Lumpy/modules/c/gc.h"
#include "C:\Users\Coolo\Documents\NetBeansProjects\Lumpy/modules/c/lang.h"
#include "C:\Users\Coolo\Documents\NetBeansProjects\Lumpy/modules/c/string.h"
#include "file0.h"
#include "file1.h"







int main(int argc, char *argv[]) {
	GCInit();
	
	function_program();
	
	GCDeInit();
	
	return 0;
}


void function_program() {
	stack_enter(1);
	
	//Line: 7
	print_string (joinstr(int2string(string_length (newString("lol") )), newString("lol")) );
	//Line: 8
	GCNode* _i_ = newString("lll");
	stack_create(&_i_, 0);
	;
	//Line: 9
	print_int (string_length (_i_ ) );
	stack_leave();
	return;
	stack_leave();
	
}

