#include "C:\Users\Coolo\Documents\NetBeansProjects\Lumpy/modules/c/system/gc.h"
#include "C:\Users\Coolo\Documents\NetBeansProjects\Lumpy/modules/c/system/lang.h"
#include "C:\Users\Coolo\Documents\NetBeansProjects\Lumpy/modules/c/system/string.h"
#include "C:\Users\Coolo\Documents\NetBeansProjects\Lumpy/modules/c/system/exception.h"
#include "C:\Users\Coolo\Documents\NetBeansProjects\Lumpy/modules/c/system/slice.h"
#include "C:\Users\Coolo\Documents\NetBeansProjects\Lumpy/modules/c/gui/gui.h"
#include "file0.h"
#include "file1.h"





#ifndef boolean
#define boolean char
#endif

int main(int argc, char *argv[]) {
	GCInit();
	initStatic();
	
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
	((test*)obj -> data) -> superclass = TYP_OBJECT;
	obj -> typid = TYP_TEST;
	return obj;
	
}
GCNode* cast2duck(GCNode* node) {
	node -> typid = TYP_DUCK;
	return node;
	
}
GCNode* new_duck() {
	GCNode* obj = gc_malloc(sizeof(duck), &standardTrace);
	((duck*)obj -> data) -> typid = TYP_DUCK;
	((duck*)obj -> data) -> superclass = TYP_OBJECT;
	obj -> typid = TYP_DUCK;
	return obj;
	
}
GCNode* cast2dog(GCNode* node) {
	node -> typid = TYP_DOG;
	return node;
	
}
GCNode* new_dog() {
	GCNode* obj = gc_malloc(sizeof(dog), &standardTrace);
	((dog*)obj -> data) -> typid = TYP_DOG;
	((dog*)obj -> data) -> superclass = TYP_OBJECT;
	obj -> typid = TYP_DOG;
	return obj;
	
}
void function_program() {
	stack_enter(1, NULL);
	
	//Line: 5
	print_string (joinstr(newString("lol: "), boolean2string(ducktyping_int_int (((int)10), ((int)20) ))) );
	//Line: 6
	print_string (joinstr(newString("rofl: "), boolean2string(ducktyping_float_float (((float)209.0), ((float)30.5) ))) );
	//Line: 8
	GCNode* _t_ = test_new (new_test(), ((int)100) );
	stack_create(&_t_, 0);
	;
	//Line: 9
	test_lol (_t_, ((float)50.900001525878906) );
	//Line: 11
	schnatter_duck (duck_new (new_duck() ) );
	//Line: 12
	schnatter_dog (dog_new (new_dog() ) );
	stack_leave();
	return;
	stack_leave();
	
}

GCNode* test_new(GCNode* _this_, int _param_0_) {
	if (_this_ == NULL) {
		exc_holder_obj = nullpointerexception_new (new_nullpointerexception() );
		throwException(TYP_NULLPOINTEREXCEPTION);
	}
	GCNode* _super_ = NULL;
	int tmpid = _this_ -> typid;
	_super_ = _this_;//((test*)_this_ -> data) -> superclass;
	_this_ -> typid = ((object*)_this_->data) -> typid; //grauslicher hack fuers casten...
	switch (tmpid) {
		case TYP_TEST: 
			{
				//Line: 35
				print_string (int2string(_param_0_) );
				return _this_;
				
			}
			
			break;
		default:
			{
				//Line: 35
				print_string (int2string(_param_0_) );
				return _this_;
				
			}
			
			
	}
	return;
	
}
void duck_laut(GCNode* _this_) {
	if (_this_ == NULL) {
		exc_holder_obj = nullpointerexception_new (new_nullpointerexception() );
		throwException(TYP_NULLPOINTEREXCEPTION);
	}
	GCNode* _super_ = NULL;
	int tmpid = _this_ -> typid;
	_super_ = _this_;//((duck*)_this_ -> data) -> superclass;
	_this_ -> typid = ((object*)_this_->data) -> typid; //grauslicher hack fuers casten...
	switch (tmpid) {
		case TYP_DUCK: 
			{
				//Line: 45
				print_string (newString("schnatter") );
				return;
				
			}
			
			break;
		default:
			{
				//Line: 45
				print_string (newString("schnatter") );
				return;
				
			}
			
			
	}
	return;
	
}
GCNode* duck_new(GCNode* _this_) {
	if (_this_ == NULL) {
		exc_holder_obj = nullpointerexception_new (new_nullpointerexception() );
		throwException(TYP_NULLPOINTEREXCEPTION);
	}
	GCNode* _super_ = NULL;
	int tmpid = _this_ -> typid;
	_super_ = _this_;//((duck*)_this_ -> data) -> superclass;
	_this_ -> typid = ((object*)_this_->data) -> typid; //grauslicher hack fuers casten...
	switch (tmpid) {
		case TYP_DUCK: 
			{
				return _this_;
				
			}
			
			break;
		default:
			{
				return _this_;
				
			}
			
			
	}
	return;
	
}
void dog_laut(GCNode* _this_) {
	if (_this_ == NULL) {
		exc_holder_obj = nullpointerexception_new (new_nullpointerexception() );
		throwException(TYP_NULLPOINTEREXCEPTION);
	}
	GCNode* _super_ = NULL;
	int tmpid = _this_ -> typid;
	_super_ = _this_;//((dog*)_this_ -> data) -> superclass;
	_this_ -> typid = ((object*)_this_->data) -> typid; //grauslicher hack fuers casten...
	switch (tmpid) {
		case TYP_DOG: 
			{
				//Line: 54
				print_string (newString("wuff") );
				return;
				
			}
			
			break;
		default:
			{
				//Line: 54
				print_string (newString("wuff") );
				return;
				
			}
			
			
	}
	return;
	
}
GCNode* dog_new(GCNode* _this_) {
	if (_this_ == NULL) {
		exc_holder_obj = nullpointerexception_new (new_nullpointerexception() );
		throwException(TYP_NULLPOINTEREXCEPTION);
	}
	GCNode* _super_ = NULL;
	int tmpid = _this_ -> typid;
	_super_ = _this_;//((dog*)_this_ -> data) -> superclass;
	_this_ -> typid = ((object*)_this_->data) -> typid; //grauslicher hack fuers casten...
	switch (tmpid) {
		case TYP_DOG: 
			{
				return _this_;
				
			}
			
			break;
		default:
			{
				return _this_;
				
			}
			
			
	}
	return;
	
}
boolean ducktyping_int_int(int _param_0_, int _param_1_) {
	//Line: 20
	{
		//Line: 21
		return (_param_0_ < _param_1_);
		
	}
	;
	return ((byte)false);
	
}

boolean ducktyping_float_float(float _param_0_, float _param_1_) {
	//Line: 20
	{
		//Line: 21
		return (_param_0_ < _param_1_);
		
	}
	;
	return ((byte)false);
	
}

void test_lol(GCNode* _this_, float _param_0_) {
	if (_this_ == NULL) {
		exc_holder_obj = nullpointerexception_new (new_nullpointerexception() );
		throwException(TYP_NULLPOINTEREXCEPTION);
	}
	GCNode* _super_ = NULL;
	int tmpid = _this_ -> typid;
	_super_ = _this_;//((test*)_this_ -> data) -> superclass;
	_this_ -> typid = ((object*)_this_->data) -> typid; //grauslicher hack fuers casten...
	switch (tmpid) {
		case TYP_TEST: 
			{
				//Line: 39
				print_string (joinstr(newString("lol in Methode: "), float2string(_param_0_)) );
				return;
				
			}
			
			break;
		default:
			{
				//Line: 39
				print_string (joinstr(newString("lol in Methode: "), float2string(_param_0_)) );
				return;
				
			}
			
			
	}
	return;
	
}
void schnatter_duck(GCNode* _param_0_) {
	stack_enter(1, NULL);
	
	stack_create(&_param_0_, 0);
	//Line: 16
	duck_laut (_param_0_ );
	stack_leave();
	return;
	stack_leave();
	
}

void schnatter_dog(GCNode* _param_0_) {
	stack_enter(1, NULL);
	
	stack_create(&_param_0_, 0);
	//Line: 16
	dog_laut (_param_0_ );
	stack_leave();
	return;
	stack_leave();
	
}

void initStatic() {
	
}

