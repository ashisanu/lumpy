#include <setjmp.h>
#include "exception.h"

#ifndef NULL
#define NULL (void*)0
#endif

typedef struct gcnode { // Ein Node (= Objekt)
    void* data; //Das referenzierte Objekt
    char mark; //wurde das Objekt mariert?
    int size; //wie groß das Objekt ist
    int typid; //welcher typ ist das objekt?
    void (*trace)(struct gcnode*); //Die Tracefunktion (führt das markieren aus)
    struct gcnode* next; //Das nächste Node
} GCNode;

typedef struct _object_ {
	int typid;
	int superclass;
} object;

typedef struct gcframe {
    GCNode*** refs; //Alle Referenzen
    int size; // wieviele referenzen
    struct gcframe* prev; //Voriges Frame
    ExceptionHolder* exception; //wohin gesprungen wird, bei einer exception
} GCFrame;

GCNode* currentNode; //Startnode
GCFrame* currentFrame;

void GCInit();
void GCDeInit();
void gccollect();
void stack_enter(int size, ExceptionHolder* jmp);
void stack_leave();
void stack_create(GCNode** node, int pos);
void standardTrace(GCNode* node);
GCNode* gc_malloc(int size, void(*tracefunc)(GCNode*));


