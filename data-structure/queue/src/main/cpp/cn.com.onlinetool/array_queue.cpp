#include <stdlib.h>
#include <math.h>
#include <iostream>
#include <cstring>
#include <cstdlib>
using namespace std;

#define MAX_QSIZE 10 // 最大队列长度+1
// 阵列队列的存储结构
struct Queue {
    int Array[10]; // 阵列空间大小
    int head; // 队头
    int tail; // 队尾
    int length; // 队列长度
};

// 构造一个空队列Q
Queue* Q_Init() {
    Queue *Q = (Queue*)malloc(sizeof(Queue));
    if (!Q){
        // 存储分配失败
        exit(OVERFLOW);
    }
    //初始化
    Q->head = Q->tail = Q->length = 0;
    return Q;
}

// 将Q清为空队列
void Q_Clear(Queue *Q) {
    //清除头尾下标和长度
    Q->head = Q->tail = Q->length = 0;
}

// 入列
int Q_Put(Queue *Q, int x) {
    //如果当前元素数量等于最大数量 返回 -1
    if (Q->tail + 1 == MAX_QSIZE) {
        return -1;
    }
    Q->Array[Q->tail] = x;
    Q->tail = Q->tail + 1;
    //length + 1
    Q->length = Q->length + 1;
    return 1;
}

// 出列
int Q_Poll(Queue *Q) {
    //如果当前元素数量等于最大数量 返回 -1
    if (Q->head + 1 == MAX_QSIZE)
        return -1;
    int x = Q->Array[Q->head];
    Q->head = Q->head + 1;
    // 移出后減少1
    Q->length = Q->length - 1;
    return x;
}


int main() {
    cout << "初始化队列" << endl;
    Queue *Q = Q_Init();
    cout << "sqQueue= " << Q << endl;
    cout << "head= " << Q->head << endl;
    cout << "tail= " << Q->tail << endl;
    cout << "lenth= " << Q->length << endl;

    cout << "插入数据（put）" << endl;
    Q_Put(Q, 111);
    Q_Put(Q, 222);
    Q_Put(Q, 333);
    Q_Put(Q, 444);

    cout << "sqQueue= " << Q << endl;
    cout << "head= " << Q->head << endl;
    cout << "tail= " << Q->tail << endl;
    cout << "lenth= " << Q->length << endl;

    cout << "取出并删除数据（poll）" << Q_Poll(Q) << endl;
    cout << "取出并删除数据（poll）" << Q_Poll(Q) << endl;
    cout << "取出并删除数据（poll）" << Q_Poll(Q) << endl;

    cout << "sqQueue= " << Q << endl;
    cout << "head= " << Q->head << endl;
    cout << "tail= " << Q->tail << endl;
    cout << "lenth= " << Q->length << endl;
}
