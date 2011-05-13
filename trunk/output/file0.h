#ifndef boolean
#define boolean char
#endif
int array_bytes_int(GCNode* size);
int array_bytes_float(GCNode* size);
void print_string(GCNode* text);
void print_int(int text);
void function_program();
GCNode* genAutoArray_int_dim(int size,...);
GCNode* genAutoArray_int_dim_dim(int size,...);
void initStatic();
GCNode* allocarray_1_(int size, int param0);
GCNode* allocarray_2_(int size, int param0, int param1);
