#include <stdlib.h>
#include "rect.h"

void main (void){
    Rect* r1 = novo_rect (20, 15, 22, 33);
    print_rect(r1);


    Rect* r2 = novo_rect(40, 20, 40, 60);
    rect_drag(r2, 213, 522);
    print_rect(r2);


    free(r1);
    free(r2);
}