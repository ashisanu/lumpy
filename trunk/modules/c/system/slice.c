#include "GC.h"
#include "exception.h"

GCNode* sliceArray(GCNode* node, int start, int end, int dataSize) {
    if (end == 0) {
        end = node -> size;
    }
    if (end < 0) {
        if (start == 0) {
            start = node -> size + end;
            if (start < 0) throwSliceException();
            end = node -> size;
        } else {
            end = node -> size + end;
        }
    }
    if (end < start) throwSliceException();
    int newSize = end - start;
    if (newSize>=0) {
        newSize = newSize*dataSize;
        GCNode* newNode = gc_malloc(newSize, node->trace);
        int i = 0;
        for (i = 0; i < newSize; i++) {
            //printf("%d posi old: %d posi new:%d\n",((int*)node->data)[i],i,i - start);
            //if ((i + start*dataSize) >= node->size) throwSliceException();
            ((char*)newNode->data) [i] = ((char*)node->data)[i + start*dataSize];
        }
        return newNode;
    } else {
        throwSliceException();
    }
    return NULL;
}
