#include "C:\Users\Coolo\Documents\NetBeansProjects\Lumpy/modules/c/system/gc.h"
#include "C:\Users\Coolo\Documents\NetBeansProjects\Lumpy/modules/c/system/lang.h"
#include "C:\Users\Coolo\Documents\NetBeansProjects\Lumpy/modules/c/system/string.h"
#include "C:\Users\Coolo\Documents\NetBeansProjects\Lumpy/modules/c/system/exception.h"
#include "C:\Users\Coolo\Documents\NetBeansProjects\Lumpy/modules/c/system/slice.h"
#include "C:\Users\Coolo\Documents\NetBeansProjects\Lumpy/modules/c/gui/gui.h"
#include "file0.h"
#include "file1.h"
#include "file13.h"
#include "file14.h"
#include "file15.h"
#include "file16.h"





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


void function_program() {
	stack_enter(4, NULL);
	
	//Line: 8
	GCNode* _i_ = allocarray_1_(sizeof(int*), ((int)10));
	stack_create(&_i_, 0);
	;
	//Line: 9
	{
		int _j_ = ((int)0);
		;
		while(1) {
			int tmp_inc__ = ((int)1);
			if (tmp_inc__ < 0) { if (_j_  < ((int)9)) break; }
			if (tmp_inc__ > 0) { if (_j_  > ((int)9)) break; }
			{
				//Line: 10
				((int*)_i_-> data)[_j_] = ((_j_ * _j_) + _j_);
				
			}
			
			_j_  = _j_  + ((int)1);
		}
	};
	//Line: 13
	GCNode* _f_ = int_dim_tofloatarray( _i_ );
	stack_create(&_f_, 1);
	;
	//Line: 14
	print_int( int_dim_length( _i_ ) );
	//Line: 16
	{
		int _j_ = ((int)0);
		;
		while(1) {
			int tmp_inc__ = ((int)1);
			if (tmp_inc__ < 0) { if (_j_  < (float_dim_length( _f_ ) - ((int)1))) break; }
			if (tmp_inc__ > 0) { if (_j_  > (float_dim_length( _f_ ) - ((int)1))) break; }
			{
				//Line: 17
				print_string( joinstr(newString("Array: "), float2string(((float*)_f_-> data)[_j_])) );
				
			}
			
			_j_  = _j_  + ((int)1);
		}
	};
	//Line: 19
	GCNode* _eindim_ = genAutoArray_int_dim(4, ((int)10), ((int)20), ((int)30), ((int)40), NULL);
	stack_create(&_eindim_, 2);
	;
	//Line: 20
	print_int( ((int*)_eindim_-> data)[((int)2)] );
	//Line: 22
	GCNode* _mehrdim_ = allocarray_2_(sizeof(int**), ((int)10), ((int)10));
	stack_create(&_mehrdim_, 3);
	;
	//Line: 23
	_mehrdim_ = genAutoArray_int_dim_dim(3, genAutoArray_int_dim(3, ((int)10), ((int)20), ((int)30), NULL), genAutoArray_int_dim(3, ((int)10), ((int)20), ((int)30), NULL), genAutoArray_int_dim(3, ((int)10), ((int)20), ((int)30), NULL), NULL);
	//Line: 24
	print_int( ((int*)((GCNode**)_mehrdim_-> data)[((int)0)]-> data)[((int)0)] );
	//Line: 26
	{
		int _arr_ = ((int)0);
		;
		GCNode* tmp_it_iterator = int_dim_iterator( _i_ );
		intarrayiterator_start(tmp_it_iterator);
		_arr_ = intarrayiterator_invoke(tmp_it_iterator);
		while (intarrayiterator_hasnext(tmp_it_iterator)) {
			{
				//Line: 27
				print_string( joinstr(newString("In Array: "), int2string(_arr_)) );
				
			}
			
		_arr_ = intarrayiterator_invoke(tmp_it_iterator);
		}
	}
	;
	//Line: 30
	{
		int _arr_ = ((int)0);
		;
		GCNode* tmp_it_iterator = int_dim_iterator( function_range_int_int( ((int)40), ((int)1) ) );
		intarrayiterator_start(tmp_it_iterator);
		_arr_ = intarrayiterator_invoke(tmp_it_iterator);
		while (intarrayiterator_hasnext(tmp_it_iterator)) {
			{
				//Line: 31
				print_string( joinstr(newString("range: "), int2string(_arr_)) );
				
			}
			
		_arr_ = intarrayiterator_invoke(tmp_it_iterator);
		}
	}
	;
	stack_leave();
	return;
	stack_leave();
	
}

GCNode* genAutoArray_int_dim(int size,...) {
	va_list args;
	va_start(args, size);
	GCNode* tmp = allocarray_1_(sizeof(int), size);
	int i;
	for (i = 0; i < size; i++) {
		((int*)tmp -> data)[i] = va_arg(args, int);
		
	}
	va_end (args);
	return tmp;
}
GCNode* genAutoArray_int_dim_dim(int size,...) {
	va_list args;
	va_start(args, size);
	GCNode* tmp = allocarray_1_(sizeof(GCNode*), size);
	int i;
	for (i = 0; i < size; i++) {
		((GCNode**)tmp -> data)[i] = va_arg(args, GCNode*);
		
	}
	va_end (args);
	return tmp;
}
void initStatic() {
	
}
GCNode* allocarray_1_(int size, int param0) {
	void** arr = malloc(size*param0);
	int i;
	for (i = 0;i < param0; i++) arr[i] = NULL;
	GCNode* node = gc_malloc(0,&standardTrace);
	node -> data = arr;
	node -> size = size*param0*1;
	return node;
	
}
GCNode* allocarray_2_(int size, int param0, int param1) {
	GCNode** arr = malloc(sizeof(GCNode)*param0);
	int i;
	for (i = 0;i < param0; i++) arr[i] = allocarray_1_(size, param1);
	GCNode* node = gc_malloc(0,&standardTrace);
	node -> data = arr;
	node -> size = size*param0*param1*1;
	return node;
	
}

