#include "C:\Users\Coolo\Documents\NetBeansProjects\Lumpy/modules/c/gc.h"
#include "C:\Users\Coolo\Documents\NetBeansProjects\Lumpy/modules/c/lang.h"
#include "C:\Users\Coolo\Documents\NetBeansProjects\Lumpy/modules/c/string.h"
#include "file0.h"
#include "file1.h"







int main(int argc, char *argv[]) {
	GCInit();
	
	function_program();
	
	GCDeInit();
	
	return 0;
}


GCNode* cast2map(GCNode* node) {
	node -> typid = TYP_MAP;
	return node;
	
}
GCNode* new_map() {
	GCNode* obj = gc_malloc(sizeof(map), &standardTrace);
	((map*)obj -> data) -> typid = TYP_MAP;
	obj -> typid = TYP_MAP;
	return obj;
	
}
GCNode* cast2mapentry(GCNode* node) {
	node -> typid = TYP_MAPENTRY;
	return node;
	
}
GCNode* new_mapentry() {
	GCNode* obj = gc_malloc(sizeof(mapentry), &standardTrace);
	((mapentry*)obj -> data) -> typid = TYP_MAPENTRY;
	obj -> typid = TYP_MAPENTRY;
	return obj;
	
}
void function_program() {
	return;
	
}

