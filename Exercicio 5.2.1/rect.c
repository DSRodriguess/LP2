#include "rect.h"
#include <stdio.h>
#include <stdlib.h>

typedef struct Rect{
  int height, width, x, y;
}Rect;

Rect* novo_rect (int width, int height, int x, int y){

  Rect* this = malloc(sizeof(Rect));
  
  this-> width = width;
  this-> height = height;
  this-> x = x;
  this-> y = y;
}

void print_rect(Rect* this){
  printf("Quadradrado de largura: %d, e altura: %d,\n na posicao: (%d,%d)\n",
  this->width, this->height, this->x,this->y); 
}

void rect_drag(Rect* this, int x, int y){
  
  this->x = x;
  this->y = y;
}