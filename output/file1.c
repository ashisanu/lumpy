#include "C:\Users\Coolo\Documents\NetBeansProjects\Lumpy/modules/c/system/gc.h"
#include "C:\Users\Coolo\Documents\NetBeansProjects\Lumpy/modules/c/system/lang.h"
#include "C:\Users\Coolo\Documents\NetBeansProjects\Lumpy/modules/c/system/string.h"
#include "C:\Users\Coolo\Documents\NetBeansProjects\Lumpy/modules/c/system/exception.h"
#include "C:\Users\Coolo\Documents\NetBeansProjects\Lumpy/modules/c/system/slice.h"
#include "C:\Users\Coolo\Documents\NetBeansProjects\Lumpy/modules/c/gui/gui.h"
#include "file0.h"
#include "file1.h"






GCNode* cast2object(GCNode* node) {
	node -> typid = TYP_OBJECT;
	return node;
	
}
GCNode* new_object() {
	GCNode* obj = gc_malloc(sizeof(object), &standardTrace);
	((object*)obj -> data) -> typid = TYP_OBJECT;
	obj -> typid = TYP_OBJECT;
	return obj;
	
}
GCNode* cast2exception(GCNode* node) {
	node -> typid = TYP_EXCEPTION;
	return node;
	
}
GCNode* new_exception() {
	GCNode* obj = gc_malloc(sizeof(exception), &standardTrace);
	((exception*)obj -> data) -> typid = TYP_EXCEPTION;
	((exception*)obj -> data) -> superclass = TYP_OBJECT;
	obj -> typid = TYP_EXCEPTION;
	((exception*)obj -> data) -> __name_ = newString("");
	return obj;
	
}
GCNode* cast2nullpointerexception(GCNode* node) {
	node -> typid = TYP_NULLPOINTEREXCEPTION;
	return node;
	
}
GCNode* new_nullpointerexception() {
	GCNode* obj = gc_malloc(sizeof(nullpointerexception), &standardTrace);
	((nullpointerexception*)obj -> data) -> typid = TYP_NULLPOINTEREXCEPTION;
	((nullpointerexception*)obj -> data) -> superclass = TYP_EXCEPTION;
	obj -> typid = TYP_NULLPOINTEREXCEPTION;
	((nullpointerexception*)obj -> data) -> __name_ = newString("");
	return obj;
	
}
GCNode* cast2invalidsliceoperationexception(GCNode* node) {
	node -> typid = TYP_INVALIDSLICEOPERATIONEXCEPTION;
	return node;
	
}
GCNode* new_invalidsliceoperationexception() {
	GCNode* obj = gc_malloc(sizeof(invalidsliceoperationexception), &standardTrace);
	((invalidsliceoperationexception*)obj -> data) -> typid = TYP_INVALIDSLICEOPERATIONEXCEPTION;
	((invalidsliceoperationexception*)obj -> data) -> superclass = TYP_EXCEPTION;
	obj -> typid = TYP_INVALIDSLICEOPERATIONEXCEPTION;
	((invalidsliceoperationexception*)obj -> data) -> __name_ = newString("");
	return obj;
	
}
GCNode* cast2outofmemoryexception(GCNode* node) {
	node -> typid = TYP_OUTOFMEMORYEXCEPTION;
	return node;
	
}
GCNode* new_outofmemoryexception() {
	GCNode* obj = gc_malloc(sizeof(outofmemoryexception), &standardTrace);
	((outofmemoryexception*)obj -> data) -> typid = TYP_OUTOFMEMORYEXCEPTION;
	((outofmemoryexception*)obj -> data) -> superclass = TYP_EXCEPTION;
	obj -> typid = TYP_OUTOFMEMORYEXCEPTION;
	((outofmemoryexception*)obj -> data) -> __name_ = newString("");
	return obj;
	
}
GCNode* cast2listvalueiterator(GCNode* node) {
	node -> typid = TYP_LISTVALUEITERATOR;
	return node;
	
}
GCNode* new_listvalueiterator() {
	GCNode* obj = gc_malloc(sizeof(listvalueiterator), &standardTrace);
	((listvalueiterator*)obj -> data) -> typid = TYP_LISTVALUEITERATOR;
	((listvalueiterator*)obj -> data) -> superclass = TYP_OBJECT;
	obj -> typid = TYP_LISTVALUEITERATOR;
	return obj;
	
}
GCNode* cast2listnodeiterator(GCNode* node) {
	node -> typid = TYP_LISTNODEITERATOR;
	return node;
	
}
GCNode* new_listnodeiterator() {
	GCNode* obj = gc_malloc(sizeof(listnodeiterator), &standardTrace);
	((listnodeiterator*)obj -> data) -> typid = TYP_LISTNODEITERATOR;
	((listnodeiterator*)obj -> data) -> superclass = TYP_OBJECT;
	obj -> typid = TYP_LISTNODEITERATOR;
	return obj;
	
}
GCNode* cast2list(GCNode* node) {
	node -> typid = TYP_LIST;
	return node;
	
}
GCNode* new_list() {
	GCNode* obj = gc_malloc(sizeof(list), &standardTrace);
	((list*)obj -> data) -> typid = TYP_LIST;
	((list*)obj -> data) -> superclass = TYP_OBJECT;
	obj -> typid = TYP_LIST;
	return obj;
	
}
GCNode* cast2node(GCNode* node) {
	node -> typid = TYP_NODE;
	return node;
	
}
GCNode* new_node() {
	GCNode* obj = gc_malloc(sizeof(node), &standardTrace);
	((node*)obj -> data) -> typid = TYP_NODE;
	((node*)obj -> data) -> superclass = TYP_OBJECT;
	obj -> typid = TYP_NODE;
	return obj;
	
}
GCNode* exception_get_name(GCNode* _this_) {
	if (_this_ == NULL) {
		exc_holder_obj = nullpointerexception_new (new_nullpointerexception() );
		throwException(TYP_NULLPOINTEREXCEPTION);
	}
	GCNode* _super_ = NULL;
	int tmpid = _this_ -> typid;
	_super_ = _this_;//((exception*)_this_ -> data) -> superclass;
	_this_ -> typid = ((object*)_this_->data) -> typid; //grauslicher hack fuers casten...
	switch (tmpid) {
		case TYP_EXCEPTION: 
			{
				//Line: 76
				return (((exception*)_this_ -> data) -> __name_);
				return newString("");
				
			}
			
			break;
		default:
			{
				//Line: 76
				return (((exception*)_this_ -> data) -> __name_);
				return newString("");
				
			}
			
			
	}
	return;
	
}
GCNode* exception_new(GCNode* _this_, GCNode* _param_0_) {
	if (_this_ == NULL) {
		exc_holder_obj = nullpointerexception_new (new_nullpointerexception() );
		throwException(TYP_NULLPOINTEREXCEPTION);
	}
	GCNode* _super_ = NULL;
	int tmpid = _this_ -> typid;
	_super_ = _this_;//((exception*)_this_ -> data) -> superclass;
	_this_ -> typid = ((object*)_this_->data) -> typid; //grauslicher hack fuers casten...
	switch (tmpid) {
		case TYP_EXCEPTION: 
			{
				stack_enter(1, NULL);
				
				stack_create(&_param_0_, 0);
				//Line: 85
				(((exception*)_this_ -> data) -> __name_) = _param_0_;
				stack_leave();
				return _this_;
				stack_leave();
				
			}
			
			break;
		default:
			{
				stack_enter(1, NULL);
				
				stack_create(&_param_0_, 0);
				//Line: 85
				(((exception*)_this_ -> data) -> __name_) = _param_0_;
				stack_leave();
				return _this_;
				stack_leave();
				
			}
			
			
	}
	return;
	
}
GCNode* nullpointerexception_new(GCNode* _this_) {
	if (_this_ == NULL) {
		exc_holder_obj = nullpointerexception_new (new_nullpointerexception() );
		throwException(TYP_NULLPOINTEREXCEPTION);
	}
	GCNode* _super_ = NULL;
	int tmpid = _this_ -> typid;
	_super_ = _this_;//((nullpointerexception*)_this_ -> data) -> superclass;
	_this_ -> typid = ((object*)_this_->data) -> typid; //grauslicher hack fuers casten...
	switch (tmpid) {
		case TYP_NULLPOINTEREXCEPTION: 
			{
				//Line: 91
				(((nullpointerexception*)_this_ -> data) -> __name_) = newString("Null Pointer");
				return _this_;
				
			}
			
			break;
		default:
			{
				//Line: 91
				(((nullpointerexception*)_this_ -> data) -> __name_) = newString("Null Pointer");
				return _this_;
				
			}
			
			
	}
	return;
	
}
void throwNullPointer() {
	//Line: 99
	//throw: nullpointerexception_new (new_nullpointerexception() )
	exc_holder_obj = nullpointerexception_new (new_nullpointerexception() );
	throwException(TYP_NULLPOINTEREXCEPTION);
	;
	return;
	
}

GCNode* invalidsliceoperationexception_new(GCNode* _this_) {
	if (_this_ == NULL) {
		exc_holder_obj = nullpointerexception_new (new_nullpointerexception() );
		throwException(TYP_NULLPOINTEREXCEPTION);
	}
	GCNode* _super_ = NULL;
	int tmpid = _this_ -> typid;
	_super_ = _this_;//((invalidsliceoperationexception*)_this_ -> data) -> superclass;
	_this_ -> typid = ((object*)_this_->data) -> typid; //grauslicher hack fuers casten...
	switch (tmpid) {
		case TYP_INVALIDSLICEOPERATIONEXCEPTION: 
			{
				//Line: 104
				(((invalidsliceoperationexception*)_this_ -> data) -> __name_) = newString("Invalid Slice Operation");
				return _this_;
				
			}
			
			break;
		default:
			{
				//Line: 104
				(((invalidsliceoperationexception*)_this_ -> data) -> __name_) = newString("Invalid Slice Operation");
				return _this_;
				
			}
			
			
	}
	return;
	
}
void throwSliceException() {
	//Line: 113
	//throw: invalidsliceoperationexception_new (new_invalidsliceoperationexception() )
	exc_holder_obj = invalidsliceoperationexception_new (new_invalidsliceoperationexception() );
	throwException(TYP_INVALIDSLICEOPERATIONEXCEPTION);
	;
	return;
	
}

GCNode* outofmemoryexception_new(GCNode* _this_) {
	if (_this_ == NULL) {
		exc_holder_obj = nullpointerexception_new (new_nullpointerexception() );
		throwException(TYP_NULLPOINTEREXCEPTION);
	}
	GCNode* _super_ = NULL;
	int tmpid = _this_ -> typid;
	_super_ = _this_;//((outofmemoryexception*)_this_ -> data) -> superclass;
	_this_ -> typid = ((object*)_this_->data) -> typid; //grauslicher hack fuers casten...
	switch (tmpid) {
		case TYP_OUTOFMEMORYEXCEPTION: 
			{
				//Line: 119
				(((outofmemoryexception*)_this_ -> data) -> __name_) = newString("Out Of Memory");
				return _this_;
				
			}
			
			break;
		default:
			{
				//Line: 119
				(((outofmemoryexception*)_this_ -> data) -> __name_) = newString("Out Of Memory");
				return _this_;
				
			}
			
			
	}
	return;
	
}
void throwOutOfMemory() {
	//Line: 127
	//throw: outofmemoryexception_new (new_outofmemoryexception() )
	exc_holder_obj = outofmemoryexception_new (new_outofmemoryexception() );
	throwException(TYP_OUTOFMEMORYEXCEPTION);
	;
	return;
	
}


