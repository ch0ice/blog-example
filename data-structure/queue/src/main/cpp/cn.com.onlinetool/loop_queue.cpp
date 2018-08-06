#include <stdlib.h>
#include <math.h>
#include <iostream>
#include <cstring>
#include <cstdlib>
// 队列的顺序存储结构(循环队列)
#define MAX_QSIZE 5 // 最大队列长度+1
typedef struct {
    int *base; // 初始化的动态分配存储空间
    int front; // 头指针，若队列不空，指向队列头元素
    int rear; // 尾指针，若队列不空，指向队列尾元素的下一个位置
} SqQueue;

// 循环队列的基本操作(9个)
void InitQueue(SqQueue *Q) {
    // 构造一个空队列Q
    Q->base = (int *)malloc(MAX_QSIZE * sizeof(int));
    if (!Q->base) // 存储分配失败
        exit(OVERFLOW);
    Q->front = Q->rear = 0;
}

void DestroyQueue(SqQueue *Q) {
    // 销毁队列Q，Q不再存在
    if (Q->base)
        free(Q->base);
    Q->base = NULL;
    Q->front = Q->rear = 0;
}

void ClearQueue(SqQueue *Q) {
    // 将Q清为空队列
    Q->front = Q->rear = 0;
}

int QueueEmpty(SqQueue Q) {
    // 若队列Q为空队列，则返回TRUE；否则返回FALSE
    if (Q.front == Q.rear) // 队列空的标志
        return 1;
    else
        return -1;
}

int QueueLength(SqQueue Q) {
    // 返回Q的元素个数，即队列的长度
    return (Q.rear - Q.front + MAX_QSIZE) % MAX_QSIZE;
}

int GetHead(SqQueue Q, int *e) {
    // 若队列不空，则用e返回Q的队头元素，并返回OK；否则返回ERROR
    if (Q.front == Q.rear) // 队列空
        return -1;
    *e = Q.base[Q.front];
    return 1;
}

int EnQueue(SqQueue *Q, int e) {
    // 插入元素e为Q的新的队尾元素
    if ((Q->rear + 1) % MAX_QSIZE == Q->front) // 队列满
        return -1;
    Q->base[Q->rear] = e;
    Q->rear = (Q->rear + 1) % MAX_QSIZE;
    return 1;
}

int DeQueue(SqQueue *Q, int *e) {
    // 若队列不空，则删除Q的队头元素，用e返回其值，并返回OK；否则返回ERROR
    if (Q->front == Q->rear) // 队列空
        return -1;
    *e = Q->base[Q->front];
    Q->front = (Q->front + 1) % MAX_QSIZE;
    return 1;
}

int main(){

}
