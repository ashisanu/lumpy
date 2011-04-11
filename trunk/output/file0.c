#include "C:\Users\Coolo\Documents\NetBeansProjects\Lumpy/modules/c/gc.h"
#include "C:\Users\Coolo\Documents\NetBeansProjects\Lumpy/modules/c/lang.h"
#include "C:\Users\Coolo\Documents\NetBeansProjects\Lumpy/modules/c/string.h"
#include "file0.h"
#include "file1.h"





typedef struct _object_ {
	int typid;
	int superclass;
} object;

int main(int argc, char *argv[]) {
	GCInit();
	
	function_program();
	
	GCDeInit();
	
	return 0;
}


GCNode* cast2list(GCNode* node) {
	node -> typid = TYP_LIST;
	return node;
	
}
GCNode* new_list() {
	GCNode* obj = gc_malloc(sizeof(list), &standardTrace);
	((list*)obj -> data) -> typid = TYP_LIST;
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
	obj -> typid = TYP_NODE;
	return obj;
	
}
GCNode* cast2list_templates_int(GCNode* node) {
	node -> typid = TYP_LIST_TEMPLATES_INT;
	return node;
	
}
GCNode* new_list_templates_int() {
	GCNode* obj = gc_malloc(sizeof(list_templates_int), &standardTrace);
	((list_templates_int*)obj -> data) -> typid = TYP_LIST_TEMPLATES_INT;
	obj -> typid = TYP_LIST_TEMPLATES_INT;
	((list_templates_int*)obj -> data) -> _head_ = NULL;
	return obj;
	
}
GCNode* cast2node_templates_int(GCNode* node) {
	node -> typid = TYP_NODE_TEMPLATES_INT;
	return node;
	
}
GCNode* new_node_templates_int() {
	GCNode* obj = gc_malloc(sizeof(node_templates_int), &standardTrace);
	((node_templates_int*)obj -> data) -> typid = TYP_NODE_TEMPLATES_INT;
	obj -> typid = TYP_NODE_TEMPLATES_INT;
	((node_templates_int*)obj -> data) -> _prev_ = NULL;
	((node_templates_int*)obj -> data) -> __value_ = ((int)0);
	return obj;
	
}
void function_program() {
	stack_enter(1);
	
	//Line: 40
	GCNode* _list_ = list_templates_int_new (new_list_templates_int() );
	stack_create(&_list_, 0);
	;
	//Line: 41
	list_templates_int_add (_list_, ((int)10) );
	//Line: 42
	list_templates_int_add (_list_, ((int)23) );
	//Line: 43
	list_templates_int_add (_list_, ((int)900) );
	stack_leave();
	return;
	stack_leave();
	
}
GCNode* list_templates_int_new(GCNode* _this_) {
	GCNode* _super_ = NULL;
	int tmpid = _this_ -> typid;
	_this_ -> typid = ((object*)_this_->data) -> typid; //grauslicher hack fuers casten...
	switch (tmpid) {
		case TYP_LIST_TEMPLATES_INT: 
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
void list_templates_int_add(GCNode* _this_, int _param_0_) {
	GCNode* _super_ = NULL;
	int tmpid = _this_ -> typid;
	_this_ -> typid = ((object*)_this_->data) -> typid; //grauslicher hack fuers casten...
	switch (tmpid) {
		case TYP_LIST_TEMPLATES_INT: 
			{
				//Line: 12
				node_templates_int_new (new_node_templates_int(), (((list_templates_int*)_this_ -> data) -> _head_), _param_0_ );
				return;
				
			}
			break;
		default:
			{
				//Line: 12
				node_templates_int_new (new_node_templates_int(), (((list_templates_int*)_this_ -> data) -> _head_), _param_0_ );
				return;
				
			}
			
	}
	return;
	
}
int node_templates_int_get_value(GCNode* _this_) {
	GCNode* _super_ = NULL;
	int tmpid = _this_ -> typid;
	_this_ -> typid = ((object*)_this_->data) -> typid; //grauslicher hack fuers casten...
	switch (tmpid) {
		case TYP_NODE_TEMPLATES_INT: 
			{
				//Line: 22
				return (((node_templates_int*)_this_ -> data) -> __value_);
				return ((int)0);
				
			}
			break;
		default:
			{
				//Line: 22
				return (((node_templates_int*)_this_ -> data) -> __value_);
				return ((int)0);
				
			}
			
	}
	return;
	
}
GCNode* node_templates_int_get_previous(GCNode* _this_) {
	GCNode* _super_ = NULL;
	int tmpid = _this_ -> typid;
	_this_ -> typid = ((object*)_this_->data) -> typid; //grauslicher hack fuers casten...
	switch (tmpid) {
		case TYP_NODE_TEMPLATES_INT: 
			{
				//Line: 28
				return (((node_templates_int*)_this_ -> data) -> _prev_);
				return NULL;
				
			}
			break;
		default:
			{
				//Line: 28
				return (((node_templates_int*)_this_ -> data) -> _prev_);
				return NULL;
				
			}
			
	}
	return;
	
}
GCNode* node_templates_int_new(GCNode* _this_, GCNode* _param_0_, int _param_1_) {
	GCNode* _super_ = NULL;
	int tmpid = _this_ -> typid;
	_this_ -> typid = ((object*)_this_->data) -> typid; //grauslicher hack fuers casten...
	switch (tmpid) {
		case TYP_NODE_TEMPLATES_INT: 
			{
				stack_enter(1);
				
				stack_create(&_param_0_, 0);
				//Line: 33
				(((node_templates_int*)_this_ -> data) -> _prev_) = _param_0_;
				//Line: 34
				(((node_templates_int*)_this_ -> data) -> __value_) = _param_1_;
				stack_leave();
				return _this_;
				stack_leave();
				
			}
			break;
		default:
			{
				stack_enter(1);
				
				stack_create(&_param_0_, 0);
				//Line: 33
				(((node_templates_int*)_this_ -> data) -> _prev_) = _param_0_;
				//Line: 34
				(((node_templates_int*)_this_ -> data) -> __value_) = _param_1_;
				stack_leave();
				return _this_;
				stack_leave();
				
			}
			
	}
	return;
	
}

