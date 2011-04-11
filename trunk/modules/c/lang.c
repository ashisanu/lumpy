#include "GC.h"
#include "lang.h"

void print_string(GCNode* str) {
    printf("%s\n",(char*)(str -> data));
}

void print_int(int i) {
    printf("%d\n",i);
}
