#include "C:\Users\Coolo\Documents\NetBeansProjects\Lumpy/modules/c/gc.h"
#include "C:\Users\Coolo\Documents\NetBeansProjects\Lumpy/modules/c/lang.h"
#include "C:\Users\Coolo\Documents\NetBeansProjects\Lumpy/modules/c/string.h"
#include "C:\Users\Coolo\Documents\NetBeansProjects\Lumpy/modules/c/exception.h"
#include "file0.h"
#include "file1.h"







int main(int argc, char *argv[]) {
	GCInit();
	
	function_program();
	
	GCDeInit();
	
	return 0;
}


GCNode* cast2test(GCNode* node) {
	node -> typid = TYP_TEST;
	return node;
	
}
GCNode* new_test() {
	GCNode* obj = gc_malloc(sizeof(test), &standardTrace);
	((test*)obj -> data) -> typid = TYP_TEST;
	obj -> typid = TYP_TEST;
	return obj;
	
}
GCNode* test_tostring(GCNode* _this_) {
	//Line: 6
	return newString("exception");
	return newString("");
	
}
GCNode* test_new(GCNode* _this_) {
	return _this_;
	
}
void function_program() {
	//Line: 13
	//begin try: ex
	int jmp_env_1 = setjmp(exc_env_1);
	if (jmp_env_1) {
		if (jmp_env_1 == TYP_STRING) {
			GCNode* _ex_ = exc_holder_obj;
			{
				//Line: 22
				print_string (joinstr(newString("exception: "), _ex_) );
				
			}
		}
		if (jmp_env_1 == TYP_INT) {
			int _ex_ = exc_holder_int;
			{
				//Line: 25
				print_string (joinstr(newString("eine int exceptioN"), int2string(_ex_)) );
				
			}
		}
		if (jmp_env_1 == TYP_TEST) {
			GCNode* _ex_ = exc_holder_obj;
			{
				//Line: 28
				print_string (test_tostring (_ex_ ) );
				
			}
		}
		
	} else {
		stack_enter(0, newException(&exc_env_1, 3, TYP_STRING, TYP_INT, TYP_TEST));
		{
			//Line: 14
			//begin try: ex
			int jmp_env_0 = setjmp(exc_env_0);
			if (jmp_env_0) {
				if (jmp_env_0 == TYP_TEST) {
					GCNode* _ex_ = exc_holder_obj;
					{
						//Line: 18
						print_string (test_tostring (_ex_ ) );
						
					}
				}
				
			} else {
				stack_enter(0, newException(&exc_env_0, 1, TYP_TEST));
				{
					//Line: 15
					function_throwfunc ( );
					
				}
				stack_leave();
				
			}
			//end try
			;
			
		}
		stack_leave();
		
	}
	//end try
	;
	return;
	
}
void function_throwfunc() {
	//Line: 36
	//throw: test_new (new_test() )
	exc_holder_obj = test_new (new_test() );
	throwException(TYP_TEST);
	;
	return;
	
}

