typedef struct Rect Rect;

Rect* novo_rect (int width, int height, int x, int y);

void rect_drag (Rect* this, int x, int y);

void print_rect (Rect* this);