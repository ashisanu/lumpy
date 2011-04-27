#ifndef boolean
#define boolean char
#endif
#define TYP_TEST 1000
typedef struct __test__ {
	//Inherit from: object
	int typid;
	int superclass;
	
} test;
GCNode* cast2test(GCNode* node);
GCNode* new_test();
#define TYP_DUCK 1001
typedef struct __duck__ {
	//Inherit from: object
	int typid;
	int superclass;
	
} duck;
GCNode* cast2duck(GCNode* node);
GCNode* new_duck();
#define TYP_DOG 1002
typedef struct __dog__ {
	//Inherit from: object
	int typid;
	int superclass;
	
} dog;
GCNode* cast2dog(GCNode* node);
GCNode* new_dog();
void print_string(GCNode* text);
void function_program();
GCNode* test_new(GCNode* _this_, int vari);
void duck_laut(GCNode* _this_);
GCNode* duck_new(GCNode* _this_);
void dog_laut(GCNode* _this_);
GCNode* dog_new(GCNode* _this_);
boolean ducktyping_int_int(int test1, int test2);
boolean ducktyping_float_float(float test1, float test2);
void test_lol(GCNode* _this_, float v);
void schnatter_duck(GCNode* obj);
void schnatter_dog(GCNode* obj);
void initStatic();
