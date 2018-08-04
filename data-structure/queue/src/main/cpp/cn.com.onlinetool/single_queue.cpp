#include <stdlib.h>
#include <math.h>
#include <iostream>
#include <cstring>
#include <cstdlib>
using namespace std;

// 定义单链队列的存储结构
typedef struct QNode {
    int data;
    QNode *next;
}QNode,*QueuePtr;

typedef struct{
    //队头 队尾 指针
    QueuePtr front,rear;
}LinkQueue;


// 链队列的基本操作(9个)
void InitQueue(LinkQueue *Q) {
    // 构造一个空队列Q
    Q->front = Q->rear = (QueuePtr)malloc(sizeof(QNode));
    if (!Q->front)
        exit(OVERFLOW);
    Q->front->next = NULL;
}

void DestroyQueue(LinkQueue *Q) {
    // 销毁队列Q(无论空否均可)
    while (Q->front) {
        Q->rear = Q->front->next;
        free(Q->front);
        Q->front = Q->rear;
    }
}

void ClearQueue(LinkQueue *Q) {
    // 将Q清为空队列
    QueuePtr p, q;
    Q->rear = Q->front;
    p = Q->front->next;
    Q->front->next = NULL;
    while (p) {
        q = p;
        p = p->next;
        free(q);
    }
}

int QueueEmpty(LinkQueue Q) {
    // 若Q为空队列，则返回1，否则返回-1
    if (Q.front->next == NULL)
        return 1;
    else
        return -1;
}

int QueueLength(LinkQueue Q) {
    // 求队列的长度
    int i = 0;
    QueuePtr p;
    p = Q.front;
    while (Q.rear != p) {
        i++;
        p = p->next;
    }
    return i;
}

int GetHead_Q(LinkQueue Q, int *e) {
    // 若队列不空，则用e返回Q的队头元素，并返回1，否则返回-1
    QueuePtr p;
    if (Q.front == Q.rear)
        return -1;
    p = Q.front->next;
    *e = p->data;
    return 1;
}

void EnQueue(LinkQueue *Q, int e) {
    // 插入元素e为Q的新的队尾元素
    QueuePtr p = (QueuePtr)malloc(sizeof(QNode));
    if (!p) // 存储分配失败
        exit(OVERFLOW);
    p->data = e;
    p->next = NULL;
    Q->rear->next = p;
    Q->rear = p;
}

int DeQueue(LinkQueue *Q, int *e) {
    // 若队列不空，删除Q的队头元素，用e返回其值，并返回1，否则返回-1
    QueuePtr p;
    if (Q->front == Q->rear)
        return -1;
    p = Q->front->next; // 指向头结点
    *e = p->data;
    Q->front = p->next; // 摘下头节点
    if (Q->rear == p)
        Q->rear = Q->front;
    free(p);
    return 1;
}

void QueueTraverse(LinkQueue Q, void(*vi)(int)) {
    // 从队头到队尾依次对队列Q中每个元素调用函数vi()
    QueuePtr p;
    p = Q.front->next;
    while (p) {
        vi(p->data);
        p = p->next;
    }
    printf("\n");
}

int main(){

}