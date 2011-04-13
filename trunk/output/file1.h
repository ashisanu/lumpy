#ifndef boolean
#define boolean char
#endif
#define TYP_LISTVALUEITERATOR 1000
typedef struct __listvalueiterator__ {
	int typid;
	int superclass;
	
} listvalueiterator;
GCNode* cast2listvalueiterator(GCNode* node);
GCNode* new_listvalueiterator();
#define TYP_LISTNODEITERATOR 1001
typedef struct __listnodeiterator__ {
	int typid;
	int superclass;
	
} listnodeiterator;
GCNode* cast2listnodeiterator(GCNode* node);
GCNode* new_listnodeiterator();
#define TYP_LIST 1002
typedef struct __list__ {
	int typid;
	int superclass;
	
} list;
GCNode* cast2list(GCNode* node);
GCNode* new_list();
#define TYP_NODE 1003
typedef struct __node__ {
	int typid;
	int superclass;
	
} node;
GCNode* cast2node(GCNode* node);
GCNode* new_node();
#define TYP_LIST_TEMPLATES__INT 1004
typedef struct __list_templates__int__ {
	int typid;
	int superclass;
	GCNode* __first_;
	GCNode* __last_;
	int __size_;
	int _status_;
	GCNode* _it_;
	int _i_;
	
} list_templates__int;
GCNode* cast2list_templates__int(GCNode* node);
GCNode* new_list_templates__int();
#define TYP_NODE_TEMPLATES__INT 1005
typedef struct __node_templates__int__ {
	int typid;
	int superclass;
	GCNode* __prev_;
	GCNode* __next_;
	int __value_;
	
} node_templates__int;
GCNode* cast2node_templates__int(GCNode* node);
GCNode* new_node_templates__int();
#define TYP_LISTVALUEITERATOR_TEMPLATES__INT 1006
typedef struct __listvalueiterator_templates__int__ {
	int typid;
	int superclass;
	GCNode* _l_;
	int _status_;
	GCNode* _tmp_;
	
} listvalueiterator_templates__int;
GCNode* cast2listvalueiterator_templates__int(GCNode* node);
GCNode* new_listvalueiterator_templates__int();
int node_templates__int_get_value(GCNode* _this_);
GCNode* node_templates__int_get_previous(GCNode* _this_);
GCNode* node_templates__int_get_next(GCNode* _this_);
void node_templates__int_setnext(GCNode* _this_, GCNode* n);
GCNode* node_templates__int_new(GCNode* _this_, GCNode* prev, int _value);
int list_templates__int_get_last(GCNode* _this_);
int list_templates__int_get_first(GCNode* _this_);
GCNode* list_templates__int_get_firstelement(GCNode* _this_);
GCNode* list_templates__int_get_lastelement(GCNode* _this_);
int list_templates__int_get_size(GCNode* _this_);
GCNode* list_templates__int_new(GCNode* _this_);
void list_templates__int_add(GCNode* _this_, int v);
void list_templates__int_start(GCNode* _this_);
boolean list_templates__int_hasnext(GCNode* _this_);
int list_templates__int_invoke(GCNode* _this_);
GCNode* listvalueiterator_templates__int_new(GCNode* _this_, GCNode* l);
void listvalueiterator_templates__int_start(GCNode* _this_);
boolean listvalueiterator_templates__int_hasnext(GCNode* _this_);
int listvalueiterator_templates__int_invoke(GCNode* _this_);
