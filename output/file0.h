#ifndef boolean
#define boolean char
#endif
#define TYP_MAP 1000
typedef struct __map__ {
	int typid;
	int superclass;
	
} map;
GCNode* cast2map(GCNode* node);
GCNode* new_map();
#define TYP_MAPENTRY 1001
typedef struct __mapentry__ {
	int typid;
	int superclass;
	
} mapentry;
GCNode* cast2mapentry(GCNode* node);
GCNode* new_mapentry();
GCNode* joinstr(GCNode* str1, GCNode* str2);
void function_program();
