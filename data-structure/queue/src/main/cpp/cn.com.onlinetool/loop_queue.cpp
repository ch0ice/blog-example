#include <stdlib.h>
#include <math.h>
#include <iostream>
#include <cstring>
#include <cstdlib>
using namespace std;

// 队列的顺序存储结构(循环队列)
#define MAX_QSIZE 6 // 最大队列长度+1
// 队列的数据类型
typedef int Q_Data;

typedef struct {
    Q_Data *data; // 初始化的动态分配存储空间
    int front; // 头指针，若队列不空，指向队列头元素
    int rear; // 尾指针，若队列不空，指向队列尾元素的下一个位置
} SqQueue;


// 构造一个空队列Q
SqQueue* Q_Init() {
    SqQueue *Q = (SqQueue*)malloc(sizeof(SqQueue));
    // 存储分配失败
    if (!Q){
        exit(OVERFLOW);
    }
    Q->data = (Q_Data *)malloc(MAX_QSIZE * sizeof(Q_Data));
    // 存储分配失败
    if (!Q->data){
        exit(OVERFLOW);
    }
    Q->front = Q->rear = 0;
    return Q;
}

// 销毁队列Q，Q不再存在
void Q_Destroy(SqQueue *Q) {
    if (Q->data)
        free(Q->data);
    Q->data = NULL;
    Q->front = Q->rear = 0;
    free(Q);
}

// 将Q清为空队列
void Q_Clear(SqQueue *Q) {
    Q->front = Q->rear = 0;
}

// 若队列Q为空队列，则返回1；否则返回-1
Q_Data Q_Empty(SqQueue Q) {
    if (Q.front == Q.rear) // 队列空的标志
        return 1;
    else
        return -1;
}

// 返回Q的元素个数，即队列的长度
Q_Data Q_Length(SqQueue Q) {
    return (Q.rear - Q.front + MAX_QSIZE) % MAX_QSIZE;
}

// 若队列不空，则用e返回Q的队头元素，并返回OK；否则返回ERROR
Q_Data Q_GetHead(SqQueue Q, Q_Data &e) {
    if (Q.front == Q.rear) // 队列空
        return -1;
    e = Q.data[Q.front];
    return 1;
}

// 打印队列中的内容
void Q_Print(SqQueue Q) {
    Q_Data p = Q.front % MAX_QSIZE;
    while (Q.rear != p) {
        cout << Q.data[p] << endl;
        p = (p + 1) % MAX_QSIZE;
    }
}

// 插入元素e为Q的新的队尾元素
Q_Data Q_Put(SqQueue *Q, Q_Data e) {
    if ((Q->rear + 1) % MAX_QSIZE == Q->front) // 队列满
        return -1;
    Q->data[Q->rear] = e;
    Q->rear = (Q->rear + 1) % MAX_QSIZE;
    return 1;
}

// 若队列不空，则删除Q的队头元素，用e返回其值，并返回1；否则返回-1
Q_Data Q_Poll(SqQueue *Q, Q_Data &e) {
    if (Q->front == Q->rear) // 队列空
        return -1;
    e = Q->data[Q->front];
    Q->front = (Q->front + 1) % MAX_QSIZE;
    return 1;
}

int main(){
    cout << "初始化队列" << endl;
    SqQueue *Q = Q_Init();
    cout << "当前队列是否为空" << Q_Empty(*(Q)) << endl;
    cout << "当前队列长度为" << Q_Length(*(Q)) << endl;
    cout << "当前队头下标" << Q-> front << endl;
    cout << "当前队尾下标" << Q-> rear << endl;
    cout << "插入数据（put）" << endl;
    Q_Data front_data;
    Q_Put(Q,111);
    Q_Put(Q,222);
    Q_Put(Q,333);
    Q_Put(Q,444);
    Q_Put(Q,555);
    cout << "打印队列内容" << endl;
    Q_Print(*(Q));
    Q_GetHead(*(Q),front_data);
    cout << "获取头元素" << front_data << endl;
    cout << "取出并删除数据（poll）" << Q_Poll(Q,front_data) << endl;
    cout << "取出并删除数据（poll）" << Q_Poll(Q,front_data) << endl;
    cout << "取出并删除数据（poll）" << Q_Poll(Q,front_data) << endl;
    cout << "取出并删除数据（poll）" << Q_Poll(Q,front_data) << endl;
    cout << "取出并删除数据（poll）" << Q_Poll(Q,front_data) << endl;
    cout << "当前队列是否为空" << Q_Empty(*(Q)) << endl;
    cout << "当前队头下标" << Q-> front << endl;
    cout << "当前队尾下标" << Q-> rear << endl;
    cout << "插入数据（put）" << endl;
    Q_Put(Q,666);
    Q_Put(Q,777);
    Q_Put(Q,888);
    cout << "当前队列是否为空" << Q_Empty(*(Q)) << endl;
    cout << "当前队头下标" << Q-> front << endl;
    cout << "当前队尾下标" << Q-> rear << endl;
    cout << "当前队列长度为" << Q_Length(*(Q)) << endl;
    cout << "打印队列内容" << endl;
    Q_Print(*(Q));
    cout << "清空队列" << endl;
    Q_Clear(Q);
    cout << "当前队头下标" << Q-> front << endl;
    cout << "当前队尾下标" << Q-> rear << endl;
    cout << "当前队列是否为空" << Q_Empty(*(Q)) << endl;
    cout << "销毁队列" << endl;
    Q_Destroy(Q);




}
