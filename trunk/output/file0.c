#include "file0.h"





#ifndef boolean
#define boolean char
#endif

int main(int argc, char *argv[]) {
	GCInit();
	initStatic();
	
	function_program();
	
	GCDeInit();
	
	return 0;
}


void initStatic() {
	
}

