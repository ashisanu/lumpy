#ifndef boolean
#define boolean char
#endif
#define TYP_OBJECT 1003
typedef struct __object__ {
	int typid;
	int superclass;
	
} object;
GCNode* cast2object(GCNode* node);
GCNode* new_object();
#define TYP_EXCEPTION 1004
typedef struct __exception__ {
	//Inherit from: object
	int typid;
	int superclass;
	GCNode* __name_;
	
} exception;
GCNode* cast2exception(GCNode* node);
GCNode* new_exception();
#define TYP_NULLPOINTEREXCEPTION 1005
typedef struct __nullpointerexception__ {
	//Inherit from: exception
	int typid;
	int superclass;
	GCNode* __name_;
	
} nullpointerexception;
GCNode* __static__nullpointerexception__forcecreate_;
GCNode* cast2nullpointerexception(GCNode* node);
GCNode* new_nullpointerexception();
#define TYP_INVALIDSLICEOPERATIONEXCEPTION 1006
typedef struct __invalidsliceoperationexception__ {
	//Inherit from: exception
	int typid;
	int superclass;
	GCNode* __name_;
	
} invalidsliceoperationexception;
GCNode* __static__invalidsliceoperationexception__forcecreate_;
GCNode* cast2invalidsliceoperationexception(GCNode* node);
GCNode* new_invalidsliceoperationexception();
#define TYP_OUTOFMEMORYEXCEPTION 1007
typedef struct __outofmemoryexception__ {
	//Inherit from: exception
	int typid;
	int superclass;
	GCNode* __name_;
	
} outofmemoryexception;
GCNode* __static__outofmemoryexception__forcecreate_;
GCNode* cast2outofmemoryexception(GCNode* node);
GCNode* new_outofmemoryexception();
#define TYP_LISTVALUEITERATOR 1008
typedef struct __listvalueiterator__ {
	//Inherit from: object
	int typid;
	int superclass;
	
} listvalueiterator;
GCNode* cast2listvalueiterator(GCNode* node);
GCNode* new_listvalueiterator();
#define TYP_LISTNODEITERATOR 1009
typedef struct __listnodeiterator__ {
	//Inherit from: object
	int typid;
	int superclass;
	
} listnodeiterator;
GCNode* cast2listnodeiterator(GCNode* node);
GCNode* new_listnodeiterator();
#define TYP_LIST 1010
typedef struct __list__ {
	//Inherit from: object
	int typid;
	int superclass;
	
} list;
GCNode* cast2list(GCNode* node);
GCNode* new_list();
#define TYP_NODE 1011
typedef struct __node__ {
	//Inherit from: object
	int typid;
	int superclass;
	
} node;
GCNode* cast2node(GCNode* node);
GCNode* new_node();
void print_string(GCNode* text);
GCNode* exception_get_name(GCNode* _this_);
GCNode* exception_new(GCNode* _this_, GCNode* name);
GCNode* nullpointerexception_new(GCNode* _this_);
void throwNullPointer();
GCNode* invalidsliceoperationexception_new(GCNode* _this_);
void throwSliceException();
GCNode* outofmemoryexception_new(GCNode* _this_);
void throwOutOfMemory();
