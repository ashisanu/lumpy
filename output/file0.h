#ifndef boolean
#define boolean char
#endif
#define TYP_TEST 1000
typedef struct __test__ {
	int typid;
	int superclass;
	
} test;
GCNode* cast2test(GCNode* node);
GCNode* new_test();
void print_string(GCNode* text);
GCNode* test_tostring(GCNode* _this_);
GCNode* test_new(GCNode* _this_);
void function_program();
void function_throwfunc();
jmp_buf exc_env_0, exc_env_1;
#define TYP_INT 100
int exc_holder_int;
#define TYP_LONG 101
long exc_holder_long;
#define TYP_BYTE 102
byte exc_holder_byte;
#define TYP_SHORT 103
short exc_holder_short;
#define TYP_FLOAT 104
float exc_holder_float;
#define TYP_DOUBLE 105
double exc_holder_double;
#define TYP_STRING 106
GCNode* exc_holder_obj;
#define TYP_BOOLEAN 108
boolean exc_holder_boolean;
