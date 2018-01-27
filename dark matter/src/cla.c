#include <stdio.h>

#define BITS 16
#define GROUPS BITS/4

int a[BITS];
int b[BITS];
int g[BITS];
int p[BITS];
int c[BITS];
int sum[BITS];
int correct[BITS];
int gg[GROUPS];
int gp[GROUPS];
int gc[GROUPS];

int bitIndex(int a) {
    return BITS-1-a;
}
int groupIndex(int a) {
    return GROUPS-1-a;
}

//test cases:
//4081+12303
//3057+12303

void input() {
    int i;
    long x = 16138;
    long y = 4503;
    long z = x + y;
    for (i = BITS-1; i>=0; i--) {
        a[BITS-1-i] = (x >> i) & 1;
        b[BITS-1-i] = (y >> i) & 1;  
        correct[BITS-1-i] = (z >> i) & 1;     
    }
}

void myprint(int arr[], int len) {
    int i;
    for (i = 0; i < len; i++) {
        printf("%d", arr[i]);
    }
    printf("%c", '\n');
}

int main()
{
    input();
    
    int i;
    for (i = 0; i < BITS; i++) {
        g[i] = a[i] & b[i];
        p[i] = a[i] | b[i];
    }
    
    int j;
    i = 0;
    for (j = 0; j < GROUPS; j++) {
        gg[groupIndex(j)] = g[bitIndex(i+3)] | (p[bitIndex(i+3)] & g[bitIndex(i+2)]) | (p[bitIndex(i+3)] & p[bitIndex(i+2)] & g[bitIndex(i+1)]) | (p[bitIndex(i+3)] & p[bitIndex(i+2)] & p[bitIndex(i+1)] & g[bitIndex(i)]);
        gp[groupIndex(j)] = p[bitIndex(i)] & p[bitIndex(i+1)] & p[bitIndex(i+2)] & p[bitIndex(i+3)];
        i += 4;
    }
    
    gc[groupIndex(0)] = gg[groupIndex(0)];
    for (j = 1; j < GROUPS; j++) {
        gc[groupIndex(j)] = gg[groupIndex(j)] | (gp[groupIndex(j)] & gc[groupIndex(j-1)]);
    }
    
    for (j = 0; j < GROUPS; j++) {
        c[bitIndex(j*4+3)] = gc[groupIndex(j)];
        for (i = 0; i < 3; i++) {
            if (j*4+i-1 < 0) {
                c[bitIndex(j*4+i)] = g[bitIndex(j*4+i)];
            } else {
                c[bitIndex(j*4+i)] = g[bitIndex(j*4+i)] | (p[bitIndex(j*4+i)] & c[bitIndex(j*4+i-1)]);
            }
        }
    }
    
    sum[bitIndex(0)] = a[bitIndex(0)] ^ b[bitIndex(0)];
    for (i = 1; i < BITS; i++) {
        sum[bitIndex(i)] = a[bitIndex(i)] ^ b[bitIndex(i)] ^ c[bitIndex(i-1)];
    }
    
    myprint(a, BITS);
    myprint(b, BITS);
    myprint(g, BITS);
    myprint(p, BITS);
    myprint(gg, GROUPS);
    myprint(gp, GROUPS);
    myprint(gc, GROUPS);
    myprint(c, BITS);
    myprint(sum, BITS);
    myprint(correct, BITS);    
    return 0;
}

