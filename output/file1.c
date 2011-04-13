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

