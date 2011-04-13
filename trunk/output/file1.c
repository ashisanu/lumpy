#include "C:\Users\Coolo\Documents\NetBeansProjects\Lumpy/modules/c/gc.h"
#include "C:\Users\Coolo\Documents\NetBeansProjects\Lumpy/modules/c/lang.h"
#include "C:\Users\Coolo\Documents\NetBeansProjects\Lumpy/modules/c/string.h"
#include "file0.h"
#include "file1.h"






GCNode* cast2listvalueiterator(GCNode* node) {
	node -> typid = TYP_LISTVALUEITERATOR;
	return node;
	
}
GCNode* new_listvalueiterator() {
	GCNode* obj = gc_malloc(sizeof(listvalueiterator), &standardTrace);
	((listvalueiterator*)obj -> data) -> typid = TYP_LISTVALUEITERATOR;
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
	((list_templates__int*)obj -> data) -> __first_ = NULL;
	((list_templates__int*)obj -> data) -> __last_ = NULL;
	((list_templates__int*)obj -> data) -> __size_ = ((int)0);
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
	((node_templates__int*)obj -> data) -> __prev_ = NULL;
	((node_templates__int*)obj -> data) -> __next_ = NULL;
	((node_templates__int*)obj -> data) -> __value_ = ((int)0);
	return obj;
	
}
GCNode* cast2listvalueiterator_templates__int(GCNode* node) {
	node -> typid = TYP_LISTVALUEITERATOR_TEMPLATES__INT;
	return node;
	
}
GCNode* new_listvalueiterator_templates__int() {
	GCNode* obj = gc_malloc(sizeof(listvalueiterator_templates__int), &standardTrace);
	((listvalueiterator_templates__int*)obj -> data) -> typid = TYP_LISTVALUEITERATOR_TEMPLATES__INT;
	obj -> typid = TYP_LISTVALUEITERATOR_TEMPLATES__INT;
	((listvalueiterator_templates__int*)obj -> data) -> _l_ = NULL;
	((listvalueiterator_templates__int*)obj -> data) -> _status_ = ((int)-1);
	return obj;
	
}
int node_templates__int_get_value(GCNode* _this_) {
	GCNode* _super_ = NULL;
	int tmpid = _this_ -> typid;
	_this_ -> typid = ((object*)_this_->data) -> typid; //grauslicher hack fuers casten...
	switch (tmpid) {
		case TYP_NODE_TEMPLATES__INT: 
			{
				//Line: 152
				return (((node_templates__int*)_this_ -> data) -> __value_);
				return ((int)0);
				
			}
			break;
		default:
			{
				//Line: 152
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
				//Line: 159
				return (((node_templates__int*)_this_ -> data) -> __prev_);
				return NULL;
				
			}
			break;
		default:
			{
				//Line: 159
				return (((node_templates__int*)_this_ -> data) -> __prev_);
				return NULL;
				
			}
			
	}
	return;
	
}
GCNode* node_templates__int_get_next(GCNode* _this_) {
	GCNode* _super_ = NULL;
	int tmpid = _this_ -> typid;
	_this_ -> typid = ((object*)_this_->data) -> typid; //grauslicher hack fuers casten...
	switch (tmpid) {
		case TYP_NODE_TEMPLATES__INT: 
			{
				//Line: 166
				return (((node_templates__int*)_this_ -> data) -> __next_);
				return NULL;
				
			}
			break;
		default:
			{
				//Line: 166
				return (((node_templates__int*)_this_ -> data) -> __next_);
				return NULL;
				
			}
			
	}
	return;
	
}
void node_templates__int_setnext(GCNode* _this_, GCNode* _param_0_) {
	GCNode* _super_ = NULL;
	int tmpid = _this_ -> typid;
	_this_ -> typid = ((object*)_this_->data) -> typid; //grauslicher hack fuers casten...
	switch (tmpid) {
		case TYP_NODE_TEMPLATES__INT: 
			{
				stack_enter(1);
				
				stack_create(&_param_0_, 0);
				//Line: 179
				(((node_templates__int*)_this_ -> data) -> __next_) = _param_0_;
				stack_leave();
				return;
				stack_leave();
				
			}
			break;
		default:
			{
				stack_enter(1);
				
				stack_create(&_param_0_, 0);
				//Line: 179
				(((node_templates__int*)_this_ -> data) -> __next_) = _param_0_;
				stack_leave();
				return;
				stack_leave();
				
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
				//Line: 189
				(((node_templates__int*)_this_ -> data) -> __prev_) = _param_0_;
				//Line: 190
				(((node_templates__int*)_this_ -> data) -> __value_) = _param_1_;
				//Line: 191
				(((node_templates__int*)_this_ -> data) -> __next_) = NULL;
				stack_leave();
				return _this_;
				stack_leave();
				
			}
			break;
		default:
			{
				stack_enter(1);
				
				stack_create(&_param_0_, 0);
				//Line: 189
				(((node_templates__int*)_this_ -> data) -> __prev_) = _param_0_;
				//Line: 190
				(((node_templates__int*)_this_ -> data) -> __value_) = _param_1_;
				//Line: 191
				(((node_templates__int*)_this_ -> data) -> __next_) = NULL;
				stack_leave();
				return _this_;
				stack_leave();
				
			}
			
	}
	return;
	
}
int list_templates__int_get_last(GCNode* _this_) {
	GCNode* _super_ = NULL;
	int tmpid = _this_ -> typid;
	_this_ -> typid = ((object*)_this_->data) -> typid; //grauslicher hack fuers casten...
	switch (tmpid) {
		case TYP_LIST_TEMPLATES__INT: 
			{
				//Line: 63
				return node_templates__int_get_value((((list_templates__int*)_this_ -> data) -> __last_));
				return ((int)0);
				
			}
			break;
		default:
			{
				//Line: 63
				return node_templates__int_get_value((((list_templates__int*)_this_ -> data) -> __last_));
				return ((int)0);
				
			}
			
	}
	return;
	
}
int list_templates__int_get_first(GCNode* _this_) {
	GCNode* _super_ = NULL;
	int tmpid = _this_ -> typid;
	_this_ -> typid = ((object*)_this_->data) -> typid; //grauslicher hack fuers casten...
	switch (tmpid) {
		case TYP_LIST_TEMPLATES__INT: 
			{
				//Line: 70
				return node_templates__int_get_value((((list_templates__int*)_this_ -> data) -> __first_));
				return ((int)0);
				
			}
			break;
		default:
			{
				//Line: 70
				return node_templates__int_get_value((((list_templates__int*)_this_ -> data) -> __first_));
				return ((int)0);
				
			}
			
	}
	return;
	
}
GCNode* list_templates__int_get_firstelement(GCNode* _this_) {
	GCNode* _super_ = NULL;
	int tmpid = _this_ -> typid;
	_this_ -> typid = ((object*)_this_->data) -> typid; //grauslicher hack fuers casten...
	switch (tmpid) {
		case TYP_LIST_TEMPLATES__INT: 
			{
				//Line: 77
				return (((list_templates__int*)_this_ -> data) -> __first_);
				return NULL;
				
			}
			break;
		default:
			{
				//Line: 77
				return (((list_templates__int*)_this_ -> data) -> __first_);
				return NULL;
				
			}
			
	}
	return;
	
}
GCNode* list_templates__int_get_lastelement(GCNode* _this_) {
	GCNode* _super_ = NULL;
	int tmpid = _this_ -> typid;
	_this_ -> typid = ((object*)_this_->data) -> typid; //grauslicher hack fuers casten...
	switch (tmpid) {
		case TYP_LIST_TEMPLATES__INT: 
			{
				//Line: 84
				return (((list_templates__int*)_this_ -> data) -> __last_);
				return NULL;
				
			}
			break;
		default:
			{
				//Line: 84
				return (((list_templates__int*)_this_ -> data) -> __last_);
				return NULL;
				
			}
			
	}
	return;
	
}
int list_templates__int_get_size(GCNode* _this_) {
	GCNode* _super_ = NULL;
	int tmpid = _this_ -> typid;
	_this_ -> typid = ((object*)_this_->data) -> typid; //grauslicher hack fuers casten...
	switch (tmpid) {
		case TYP_LIST_TEMPLATES__INT: 
			{
				//Line: 90
				return (((list_templates__int*)_this_ -> data) -> __size_);
				return ((int)0);
				
			}
			break;
		default:
			{
				//Line: 90
				return (((list_templates__int*)_this_ -> data) -> __size_);
				return ((int)0);
				
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
				//Line: 96
				(((list_templates__int*)_this_ -> data) -> __size_) = ((int)0);
				return _this_;
				
			}
			break;
		default:
			{
				//Line: 96
				(((list_templates__int*)_this_ -> data) -> __size_) = ((int)0);
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
				stack_enter(1);
				
				//Line: 101
				GCNode* _tmp_ = node_templates__int_new (new_node_templates__int(), (((list_templates__int*)_this_ -> data) -> __last_), _param_0_ );
				stack_create(&_tmp_, 0);
				;
				//Line: 103
				if (((((list_templates__int*)_this_ -> data) -> __last_) != NULL)) {
					//Line: 104
					node_templates__int_setnext ((((list_templates__int*)_this_ -> data) -> __last_), _tmp_ );
					
				};
				//Line: 107
				if (((((list_templates__int*)_this_ -> data) -> __first_) == NULL)) {
					//Line: 108
					(((list_templates__int*)_this_ -> data) -> __first_) = _tmp_;
					
				};
				//Line: 111
				(((list_templates__int*)_this_ -> data) -> __last_) = _tmp_;
				//Line: 113
				(((list_templates__int*)_this_ -> data) -> __size_) = ((((list_templates__int*)_this_ -> data) -> __size_) + ((int)1));
				stack_leave();
				return;
				stack_leave();
				
			}
			break;
		default:
			{
				stack_enter(1);
				
				//Line: 101
				GCNode* _tmp_ = node_templates__int_new (new_node_templates__int(), (((list_templates__int*)_this_ -> data) -> __last_), _param_0_ );
				stack_create(&_tmp_, 0);
				;
				//Line: 103
				if (((((list_templates__int*)_this_ -> data) -> __last_) != NULL)) {
					//Line: 104
					node_templates__int_setnext ((((list_templates__int*)_this_ -> data) -> __last_), _tmp_ );
					
				};
				//Line: 107
				if (((((list_templates__int*)_this_ -> data) -> __first_) == NULL)) {
					//Line: 108
					(((list_templates__int*)_this_ -> data) -> __first_) = _tmp_;
					
				};
				//Line: 111
				(((list_templates__int*)_this_ -> data) -> __last_) = _tmp_;
				//Line: 113
				(((list_templates__int*)_this_ -> data) -> __size_) = ((((list_templates__int*)_this_ -> data) -> __size_) + ((int)1));
				stack_leave();
				return;
				stack_leave();
				
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
					//Line: 137
					((list_templates__int*)_this_ -> data) ->_it_ = listvalueiterator_templates__int_new (new_listvalueiterator_templates__int(), _this_ );
					;
					//Line: 138
					((list_templates__int*)_this_ -> data) ->_i_ = ((int)0);
					;
					listvalueiterator_templates__int_start((((list_templates__int*)_this_ -> data) -> _it_));
					(((list_templates__int*)_this_ -> data) -> _i_) = listvalueiterator_templates__int_invoke((((list_templates__int*)_this_ -> data) -> _it_));
					while (listvalueiterator_templates__int_hasnext((((list_templates__int*)_this_ -> data) -> _it_))) {
						{
							//Line: 139
							((list_templates__int*)_this_ -> data) -> _status_ = 1;
							return (((list_templates__int*)_this_ -> data) -> _i_);
							case 1:
							;
							
						}
					(((list_templates__int*)_this_ -> data) -> _i_) = listvalueiterator_templates__int_invoke((((list_templates__int*)_this_ -> data) -> _it_));
					}
					;
					//Line: 141
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
					//Line: 137
					((list_templates__int*)_this_ -> data) ->_it_ = listvalueiterator_templates__int_new (new_listvalueiterator_templates__int(), _this_ );
					;
					//Line: 138
					((list_templates__int*)_this_ -> data) ->_i_ = ((int)0);
					;
					listvalueiterator_templates__int_start((((list_templates__int*)_this_ -> data) -> _it_));
					(((list_templates__int*)_this_ -> data) -> _i_) = listvalueiterator_templates__int_invoke((((list_templates__int*)_this_ -> data) -> _it_));
					while (listvalueiterator_templates__int_hasnext((((list_templates__int*)_this_ -> data) -> _it_))) {
						{
							//Line: 139
							((list_templates__int*)_this_ -> data) -> _status_ = 1;
							return (((list_templates__int*)_this_ -> data) -> _i_);
							case 1:
							;
							
						}
					(((list_templates__int*)_this_ -> data) -> _i_) = listvalueiterator_templates__int_invoke((((list_templates__int*)_this_ -> data) -> _it_));
					}
					;
					//Line: 141
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
GCNode* listvalueiterator_templates__int_new(GCNode* _this_, GCNode* _param_0_) {
	GCNode* _super_ = NULL;
	int tmpid = _this_ -> typid;
	_this_ -> typid = ((object*)_this_->data) -> typid; //grauslicher hack fuers casten...
	switch (tmpid) {
		case TYP_LISTVALUEITERATOR_TEMPLATES__INT: 
			{
				stack_enter(1);
				
				stack_create(&_param_0_, 0);
				//Line: 24
				(((listvalueiterator_templates__int*)_this_ -> data) -> _l_) = _param_0_;
				stack_leave();
				return _this_;
				stack_leave();
				
			}
			break;
		default:
			{
				stack_enter(1);
				
				stack_create(&_param_0_, 0);
				//Line: 24
				(((listvalueiterator_templates__int*)_this_ -> data) -> _l_) = _param_0_;
				stack_leave();
				return _this_;
				stack_leave();
				
			}
			
	}
	return;
	
}
void listvalueiterator_templates__int_start(GCNode* _this_) {
	GCNode* _super_ = NULL;
	int tmpid = _this_ -> typid;
	_this_ -> typid = ((object*)_this_->data) -> typid; //grauslicher hack fuers casten...
	switch (tmpid) {
		case TYP_LISTVALUEITERATOR_TEMPLATES__INT: 
			{
				(((listvalueiterator_templates__int*)_this_ -> data) -> _status_) = ((int)0);
				
			}
			break;
		default:
			{
				(((listvalueiterator_templates__int*)_this_ -> data) -> _status_) = ((int)0);
				
			}
			
	}
	return;
	
}
boolean listvalueiterator_templates__int_hasnext(GCNode* _this_) {
	GCNode* _super_ = NULL;
	int tmpid = _this_ -> typid;
	_this_ -> typid = ((object*)_this_->data) -> typid; //grauslicher hack fuers casten...
	switch (tmpid) {
		case TYP_LISTVALUEITERATOR_TEMPLATES__INT: 
			{
				return ((((listvalueiterator_templates__int*)_this_ -> data) -> _status_) != ((int)-1));
				
			}
			break;
		default:
			{
				return ((((listvalueiterator_templates__int*)_this_ -> data) -> _status_) != ((int)-1));
				
			}
			
	}
	return;
	
}
int listvalueiterator_templates__int_invoke(GCNode* _this_) {
	GCNode* _super_ = NULL;
	int tmpid = _this_ -> typid;
	_this_ -> typid = ((object*)_this_->data) -> typid; //grauslicher hack fuers casten...
	switch (tmpid) {
		case TYP_LISTVALUEITERATOR_TEMPLATES__INT: 
			{
				switch (((listvalueiterator_templates__int*)_this_ -> data) -> _status_) {
					case 0:
					//Line: 28
					((listvalueiterator_templates__int*)_this_ -> data) ->_tmp_ = list_templates__int_get_firstelement((((listvalueiterator_templates__int*)_this_ -> data) -> _l_));
					;
					//Line: 29
					while (((((listvalueiterator_templates__int*)_this_ -> data) -> _tmp_) != NULL)) {
						//Line: 30
						((listvalueiterator_templates__int*)_this_ -> data) -> _status_ = 1;
						return node_templates__int_get_value((((listvalueiterator_templates__int*)_this_ -> data) -> _tmp_));
						case 1:
						;
						//Line: 31
						(((listvalueiterator_templates__int*)_this_ -> data) -> _tmp_) = node_templates__int_get_next((((listvalueiterator_templates__int*)_this_ -> data) -> _tmp_));
						
					};
					//Line: 33
					((listvalueiterator_templates__int*)_this_ -> data) -> _status_ = -1;
					return 0;
					case 2:
					;
					return ((int)0);
					
				}
				((listvalueiterator_templates__int*)_this_ -> data) -> _status_ = -1;
				
			}
			break;
		default:
			{
				switch (((listvalueiterator_templates__int*)_this_ -> data) -> _status_) {
					case 0:
					//Line: 28
					((listvalueiterator_templates__int*)_this_ -> data) ->_tmp_ = list_templates__int_get_firstelement((((listvalueiterator_templates__int*)_this_ -> data) -> _l_));
					;
					//Line: 29
					while (((((listvalueiterator_templates__int*)_this_ -> data) -> _tmp_) != NULL)) {
						//Line: 30
						((listvalueiterator_templates__int*)_this_ -> data) -> _status_ = 1;
						return node_templates__int_get_value((((listvalueiterator_templates__int*)_this_ -> data) -> _tmp_));
						case 1:
						;
						//Line: 31
						(((listvalueiterator_templates__int*)_this_ -> data) -> _tmp_) = node_templates__int_get_next((((listvalueiterator_templates__int*)_this_ -> data) -> _tmp_));
						
					};
					//Line: 33
					((listvalueiterator_templates__int*)_this_ -> data) -> _status_ = -1;
					return 0;
					case 2:
					;
					return ((int)0);
					
				}
				((listvalueiterator_templates__int*)_this_ -> data) -> _status_ = -1;
				
			}
			
	}
	return;
	
}

