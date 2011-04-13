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
#define TYP_LIST_TEMPLATES__INT 1002
typedef struct __list_templates__int__ {
	int typid;
	int superclass;
	GCNode* _head_;
	int _status_;
	GCNode* _tmp_;
	
} list_templates__int;
GCNode* cast2list_templates__int(GCNode* node);
GCNode* new_list_templates__int();
#define TYP_NODE_TEMPLATES__INT 1003
typedef struct __node_templates__int__ {
	int typid;
	int superclass;
	GCNode* _prev_;
	int __value_;
	
} node_templates__int;
GCNode* cast2node_templates__int(GCNode* node);
GCNode* new_node_templates__int();
GCNode* joinstr(GCNode* str1, GCNode* str2);
void print_int(int text);
void function_program();
int node_templates__int_get_value(GCNode* _this_);
GCNode* node_templates__int_get_previous(GCNode* _this_);
GCNode* node_templates__int_new(GCNode* _this_, GCNode* prev, int _value);
GCNode* list_templates__int_new(GCNode* _this_);
void list_templates__int_add(GCNode* _this_, int v);
void list_templates__int_start(GCNode* _this_);
boolean list_templates__int_hasnext(GCNode* _this_);
int list_templates__int_invoke(GCNode* _this_);
