#ifndef boolean
#define boolean char
#endif
#define TYP_LIST 1000
typedef struct __list__ {
	int typid;
	int superclass;
	
} list;
GCNode* cast2list(GCNode* node);
GCNode* new_list();
#define TYP_NODE 1001
typedef struct __node__ {
	int typid;
	int superclass;
	
} node;
GCNode* cast2node(GCNode* node);
GCNode* new_node();
#define TYP_LIST_TEMPLATES_INT 1004
typedef struct __list_templates_int__ {
	int typid;
	int superclass;
	GCNode* _head_;
	
} list_templates_int;
GCNode* cast2list_templates_int(GCNode* node);
GCNode* new_list_templates_int();
#define TYP_NODE_TEMPLATES_INT 1005
typedef struct __node_templates_int__ {
	int typid;
	int superclass;
	GCNode* _prev_;
	int __value_;
	
} node_templates_int;
GCNode* cast2node_templates_int(GCNode* node);
GCNode* new_node_templates_int();
GCNode* joinstr(GCNode* str1, GCNode* str2);
void function_program();
GCNode* list_templates_int_new(GCNode* _this_);
void list_templates_int_add(GCNode* _this_, int v);
int node_templates_int_get_value(GCNode* _this_);
GCNode* node_templates_int_get_previous(GCNode* _this_);
GCNode* node_templates_int_new(GCNode* _this_, GCNode* prev, int _value);
