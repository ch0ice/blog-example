#include <stdlib.h>
#include <math.h>
#include <iostream>
#include <cstring>
#include <cstdlib>
using namespace std;

// 队列的顺序存储结构(循环队列)
#define MAX_QSIZE 5 // 最大队列长度+1
typedef struct {
    int *base; // 初始化的动态分配存储空间
    int head; // 头指针，若队列不空，指向队列头元素
    int tail; // 尾指针，若队列不空，指向队列尾元素的下一个位置
} SqQueue;


// 构造一个空队列Q
SqQueue* Q_Init() {
    SqQueue *Q = (SqQueue*)malloc(sizeof(SqQueue));
    // 存储分配失败
    if (!Q){
        exit(OVERFLOW);
    }
    Q->base = (int *)malloc(MAX_QSIZE * sizeof(int));
    // 存储分配失败
    if (!Q->base){
        exit(OVERFLOW);
    }
    Q->head = Q->tail = 0;
    return Q;
}

// 销毁队列Q，Q不再存在
void Q_Destroy(SqQueue *Q) {
    if (Q->base)
        free(Q->base);
    Q->base = NULL;
    Q->head = Q->tail = 0;
    free(Q);
}

// 将Q清为空队列
void Q_Clear(SqQueue *Q) {
    Q->head = Q->tail = 0;
}

// 若队列Q为空队列，则返回1；否则返回-1
int Q_Empty(SqQueue Q) {
    if (Q.head == Q.tail) // 队列空的标志
        return 1;
    else
        return -1;
}

// 返回Q的元素个数，即队列的长度
int Q_Length(SqQueue Q) {
    return (Q.tail - Q.head + MAX_QSIZE) % MAX_QSIZE;
}

// 若队列不空，则用e返回Q的队头元素，并返回OK；否则返回ERROR
int Q_GetHead(SqQueue Q, int &e) {
    if (Q.head == Q.tail) // 队列空
        return -1;
    e = Q.base[Q.head];
    return 1;
}

// 打印队列中的内容
void Q_Print(SqQueue Q) {
    int p = Q.head;
    while (Q.tail != p) {
        cout << Q.base[p] << endl;
        p += 1;
    }
}

// 插入元素e为Q的新的队尾元素
int Q_Put(SqQueue *Q, int e) {
    if ((Q->tail + 1) % MAX_QSIZE == Q->head) // 队列满
        return -1;
    Q->base[Q->tail] = e;
    Q->tail = (Q->tail + 1) % MAX_QSIZE;
    return 1;
}

// 若队列不空，则删除Q的队头元素，用e返回其值，并返回1；否则返回-1
int Q_Poll(SqQueue *Q, int &e) {
    if (Q->head == Q->tail) // 队列空
        return -1;
    e = Q->base[Q->head];
    Q->head = (Q->head + 1) % MAX_QSIZE;
    return 1;
}

int main(){
    cout << "初始化队列" << endl;
    SqQueue *Q = Q_Init();
    cout << "Q= " << Q << endl;
    cout << "head= " << Q->head << endl;
    cout << "tail= " << Q->tail << endl;

    cout << "是否为空" << Q_Empty(*(Q)) << endl;

    cout << "当前队列长度为" << Q_Length(*(Q)) << endl;

    cout << "插入数据（put）" << endl;
    Q_Put(Q,111);
    Q_Put(Q,222);
    Q_Put(Q,333);
    Q_Put(Q,444);
    cout << Q_Put(Q,4) << endl;

    cout << "是否为空" << Q_Empty(*(Q)) << endl;

    int head_data;
    Q_GetHead(*(Q),head_data);
    cout << "获取头元素" << head_data << endl;

    cout << "当前队列长度为" << Q_Length(*(Q)) << endl;

    cout << "打印队列内容" << endl;
    Q_Print(*(Q));

    Q_Poll(Q,head_data);
    cout << "取出并删除数据（poll）" << head_data << endl;
    Q_Poll(Q,head_data);
    cout << "取出并删除数据（poll）" << head_data << endl;
    Q_Poll(Q,head_data);
    cout << "取出并删除数据（poll）" << head_data << endl;

    cout << "当前队列长度为" << Q_Length(*(Q)) << endl;

    cout << "清空队列" << endl;
    Q_Clear(Q);
    cout << Q->head << endl;
    cout << Q->tail << endl;

    cout << "是否为空" << Q_Empty(*(Q)) << endl;

    cout << "销毁队列" << endl;
    Q_Destroy(Q);
    cout << "Q= " << Q << endl;
    cout << "head= " << Q->head << endl;
    cout << "tail= " << Q->tail << endl;




}
