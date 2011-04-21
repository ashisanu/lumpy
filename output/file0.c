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
	int jmp_env_0 = setjmp(exc_env_0);
	if (jmp_env_0) {
		if (jmp_env_0 == TYP_STRING) {
			GCNode* _ex_ = exc_holder_obj;
			{
				//Line: 18
				print_string (joinstr(newString("exception: "), _ex_) );
				
			}
			
		} else if (jmp_env_0 == TYP_INT) {
			int _ex_ = exc_holder_int;
			{
				//Line: 21
				print_string (joinstr(newString("eine int exceptioN"), int2string(_ex_)) );
				
			}
			
		} else if (jmp_env_0 == TYP_TEST) {
			GCNode* _ex_ = exc_holder_obj;
			{
				//Line: 24
				print_string (test_tostring (_ex_ ) );
				
			}
			
		} else {
			//Line: 27
			print_string (newString("wtf") );
			
		}
		
	} else {
		stack_enter(0, newException(&exc_env_0, 4, TYP_STRING, TYP_INT, TYP_TEST, -1));
		{
			//Line: 14
			function_throwfunc ( );
			//Line: 15
			finally:
			{
				//Line: 30
				print_string (newString("ich werde immer ausgef�hrt") );
				
			}
			return;
			
		}
		
		stack_leave();
		
	}
	//end try
	//finally:
	{
		//Line: 30
		print_string (newString("ich werde immer ausgef�hrt") );
		
	}
	;
	return;
	
}

void function_throwfunc() {
	//Line: 35
	//throw: test_new (new_test() )
	exc_holder_obj = test_new (new_test() );
	throwException(TYP_TEST);
	;
	return;
	
}


