#ifndef boolean
#define boolean char
#endif
#define TYP_LISTVALUEITERATOR 1001
typedef struct __listvalueiterator__ {
	int typid;
	int superclass;
	
} listvalueiterator;
GCNode* cast2listvalueiterator(GCNode* node);
GCNode* new_listvalueiterator();
#define TYP_LISTNODEITERATOR 1002
typedef struct __listnodeiterator__ {
	int typid;
	int superclass;
	
} listnodeiterator;
GCNode* cast2listnodeiterator(GCNode* node);
GCNode* new_listnodeiterator();
#define TYP_LIST 1003
typedef struct __list__ {
	int typid;
	int superclass;
	
} list;
GCNode* cast2list(GCNode* node);
GCNode* new_list();
#define TYP_NODE 1004
typedef struct __node__ {
	int typid;
	int superclass;
	
} node;
GCNode* cast2node(GCNode* node);
GCNode* new_node();
void print_string(GCNode* text);
