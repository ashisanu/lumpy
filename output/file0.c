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
GCNode* cast2list_templates__int(GCNode* node) {
	node -> typid = TYP_LIST_TEMPLATES__INT;
	return node;
	
}
GCNode* new_list_templates__int() {
	GCNode* obj = gc_malloc(sizeof(list_templates__int), &standardTrace);
	((list_templates__int*)obj -> data) -> typid = TYP_LIST_TEMPLATES__INT;
	obj -> typid = TYP_LIST_TEMPLATES__INT;
	((list_templates__int*)obj -> data) -> _head_ = NULL;
	((list_templates__int*)obj -> data) -> _status_ = ((int)-1);
	return obj;
	
}
GCNode* cast2node_templates__int(GCNode* node) {
	node -> typid = TYP_NODE_TEMPLATES__INT;
	return node;
	
}
GCNode* new_node_templates__int() {
	GCNode* obj = gc_malloc(sizeof(node_templates__int), &standardTrace);
	((node_templates__int*)obj -> data) -> typid = TYP_NODE_TEMPLATES__INT;
	obj -> typid = TYP_NODE_TEMPLATES__INT;
	((node_templates__int*)obj -> data) -> _prev_ = NULL;
	((node_templates__int*)obj -> data) -> __value_ = ((int)0);
	return obj;
	
}
void function_program() {
	stack_enter(1);
	
	//Line: 48
	GCNode* _list_ = list_templates__int_new (new_list_templates__int() );
	stack_create(&_list_, 0);
	;
	//Line: 49
	list_templates__int_add (_list_, ((int)10) );
	//Line: 50
	list_templates__int_add (_list_, ((int)23) );
	//Line: 51
	list_templates__int_add (_list_, ((int)900) );
	//Line: 54
	int _i_ = ((int)0);
	;
	list_templates__int_start(_list_);
	_i_ = list_templates__int_invoke(_list_);
	while (list_templates__int_hasnext(_list_)) {
		{
			//Line: 55
			print_int (_i_ );
			
		}
	_i_ = list_templates__int_invoke(_list_);
	}
	;
	stack_leave();
	return;
	stack_leave();
	
}
int node_templates__int_get_value(GCNode* _this_) {
	GCNode* _super_ = NULL;
	int tmpid = _this_ -> typid;
	_this_ -> typid = ((object*)_this_->data) -> typid; //grauslicher hack fuers casten...
	switch (tmpid) {
		case TYP_NODE_TEMPLATES__INT: 
			{
				//Line: 31
				return (((node_templates__int*)_this_ -> data) -> __value_);
				return ((int)0);
				
			}
			break;
		default:
			{
				//Line: 31
				return (((node_templates__int*)_this_ -> data) -> __value_);
				return ((int)0);
				
			}
			
	}
	return;
	
}
GCNode* node_templates__int_get_previous(GCNode* _this_) {
	GCNode* _super_ = NULL;
	int tmpid = _this_ -> typid;
	_this_ -> typid = ((object*)_this_->data) -> typid; //grauslicher hack fuers casten...
	switch (tmpid) {
		case TYP_NODE_TEMPLATES__INT: 
			{
				//Line: 37
				return (((node_templates__int*)_this_ -> data) -> _prev_);
				return NULL;
				
			}
			break;
		default:
			{
				//Line: 37
				return (((node_templates__int*)_this_ -> data) -> _prev_);
				return NULL;
				
			}
			
	}
	return;
	
}
GCNode* node_templates__int_new(GCNode* _this_, GCNode* _param_0_, int _param_1_) {
	GCNode* _super_ = NULL;
	int tmpid = _this_ -> typid;
	_this_ -> typid = ((object*)_this_->data) -> typid; //grauslicher hack fuers casten...
	switch (tmpid) {
		case TYP_NODE_TEMPLATES__INT: 
			{
				stack_enter(1);
				
				stack_create(&_param_0_, 0);
				//Line: 42
				(((node_templates__int*)_this_ -> data) -> _prev_) = _param_0_;
				//Line: 43
				(((node_templates__int*)_this_ -> data) -> __value_) = _param_1_;
				stack_leave();
				return _this_;
				stack_leave();
				
			}
			break;
		default:
			{
				stack_enter(1);
				
				stack_create(&_param_0_, 0);
				//Line: 42
				(((node_templates__int*)_this_ -> data) -> _prev_) = _param_0_;
				//Line: 43
				(((node_templates__int*)_this_ -> data) -> __value_) = _param_1_;
				stack_leave();
				return _this_;
				stack_leave();
				
			}
			
	}
	return;
	
}
GCNode* list_templates__int_new(GCNode* _this_) {
	GCNode* _super_ = NULL;
	int tmpid = _this_ -> typid;
	_this_ -> typid = ((object*)_this_->data) -> typid; //grauslicher hack fuers casten...
	switch (tmpid) {
		case TYP_LIST_TEMPLATES__INT: 
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
void list_templates__int_add(GCNode* _this_, int _param_0_) {
	GCNode* _super_ = NULL;
	int tmpid = _this_ -> typid;
	_this_ -> typid = ((object*)_this_->data) -> typid; //grauslicher hack fuers casten...
	switch (tmpid) {
		case TYP_LIST_TEMPLATES__INT: 
			{
				//Line: 12
				(((list_templates__int*)_this_ -> data) -> _head_) = node_templates__int_new (new_node_templates__int(), (((list_templates__int*)_this_ -> data) -> _head_), _param_0_ );
				return;
				
			}
			break;
		default:
			{
				//Line: 12
				(((list_templates__int*)_this_ -> data) -> _head_) = node_templates__int_new (new_node_templates__int(), (((list_templates__int*)_this_ -> data) -> _head_), _param_0_ );
				return;
				
			}
			
	}
	return;
	
}
void list_templates__int_start(GCNode* _this_) {
	GCNode* _super_ = NULL;
	int tmpid = _this_ -> typid;
	_this_ -> typid = ((object*)_this_->data) -> typid; //grauslicher hack fuers casten...
	switch (tmpid) {
		case TYP_LIST_TEMPLATES__INT: 
			{
				(((list_templates__int*)_this_ -> data) -> _status_) = ((int)0);
				
			}
			break;
		default:
			{
				(((list_templates__int*)_this_ -> data) -> _status_) = ((int)0);
				
			}
			
	}
	return;
	
}
boolean list_templates__int_hasnext(GCNode* _this_) {
	GCNode* _super_ = NULL;
	int tmpid = _this_ -> typid;
	_this_ -> typid = ((object*)_this_->data) -> typid; //grauslicher hack fuers casten...
	switch (tmpid) {
		case TYP_LIST_TEMPLATES__INT: 
			{
				return ((((list_templates__int*)_this_ -> data) -> _status_) != ((int)-1));
				
			}
			break;
		default:
			{
				return ((((list_templates__int*)_this_ -> data) -> _status_) != ((int)-1));
				
			}
			
	}
	return;
	
}
int list_templates__int_invoke(GCNode* _this_) {
	GCNode* _super_ = NULL;
	int tmpid = _this_ -> typid;
	_this_ -> typid = ((object*)_this_->data) -> typid; //grauslicher hack fuers casten...
	switch (tmpid) {
		case TYP_LIST_TEMPLATES__INT: 
			{
				switch (((list_templates__int*)_this_ -> data) -> _status_) {
					case 0:
					//Line: 16
					((list_templates__int*)_this_ -> data) ->_tmp_ = (((list_templates__int*)_this_ -> data) -> _head_);
					;
					//Line: 17
					while (((((list_templates__int*)_this_ -> data) -> _tmp_) != NULL)) {
						//Line: 18
						((list_templates__int*)_this_ -> data) -> _status_ = 1;
						return node_templates__int_get_value((((list_templates__int*)_this_ -> data) -> _tmp_));
						case 1:
						;
						//Line: 19
						(((list_templates__int*)_this_ -> data) -> _tmp_) = node_templates__int_get_previous((((list_templates__int*)_this_ -> data) -> _tmp_));
						
					};
					//Line: 21
					((list_templates__int*)_this_ -> data) -> _status_ = -1;
					return 0;
					case 2:
					;
					return ((int)0);
					
				}
				((list_templates__int*)_this_ -> data) -> _status_ = -1;
				
			}
			break;
		default:
			{
				switch (((list_templates__int*)_this_ -> data) -> _status_) {
					case 0:
					//Line: 16
					((list_templates__int*)_this_ -> data) ->_tmp_ = (((list_templates__int*)_this_ -> data) -> _head_);
					;
					//Line: 17
					while (((((list_templates__int*)_this_ -> data) -> _tmp_) != NULL)) {
						//Line: 18
						((list_templates__int*)_this_ -> data) -> _status_ = 1;
						return node_templates__int_get_value((((list_templates__int*)_this_ -> data) -> _tmp_));
						case 1:
						;
						//Line: 19
						(((list_templates__int*)_this_ -> data) -> _tmp_) = node_templates__int_get_previous((((list_templates__int*)_this_ -> data) -> _tmp_));
						
					};
					//Line: 21
					((list_templates__int*)_this_ -> data) -> _status_ = -1;
					return 0;
					case 2:
					;
					return ((int)0);
					
				}
				((list_templates__int*)_this_ -> data) -> _status_ = -1;
				
			}
			
	}
	return;
	
}

