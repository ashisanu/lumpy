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
	
	//Line: 6
	GCNode* _list_ = list_templates__int_new (new_list_templates__int() );
	stack_create(&_list_, 0);
	;
	//Line: 7
	list_templates__int_add (_list_, ((int)10) );
	//Line: 8
	list_templates__int_add (_list_, ((int)23) );
	//Line: 9
	list_templates__int_add (_list_, ((int)900) );
	//Line: 12
	int _i_ = ((int)0);
	;
	list_templates__int_start(_list_);
	_i_ = list_templates__int_invoke(_list_);
	while (list_templates__int_hasnext(_list_)) {
		{
			//Line: 13
			print_string (joinstr(newString("data: "), int2string(_i_)) );
			
		}
	_i_ = list_templates__int_invoke(_list_);
	}
	;
	//Line: 16
	print_string (newString("---------- infos: -------------") );
	//Line: 17
	print_string (joinstr(newString("size: "), int2string(list_templates__int_get_size(_list_))) );
	//Line: 18
	print_string (joinstr(newString("last: "), int2string(list_templates__int_get_last(_list_))) );
	//Line: 19
	print_string (joinstr(newString("first: "), int2string(list_templates__int_get_first(_list_))) );
	stack_leave();
	return;
	stack_leave();
	
}

