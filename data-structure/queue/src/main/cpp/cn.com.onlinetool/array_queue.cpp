#include <stdlib.h>
#include <math.h>
#include <iostream>
#include <cstring>
#include <cstdlib>
using namespace std;

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
    Q->head = Q->tail = Q->length = 0;
    return Q;
}

// 将Q清为空队列
void Q_Clear(Queue *Q) {
    Q->head = Q->tail = Q->length = 0;
}

// 入列
int Q_Put(Queue *Q, int x) {
    if (Q->tail + 1 == 10) {
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
    int x = Q->Array[Q->head];
    // 空间大小10
    if (Q->head + 1 == 10)
        Q->head = 0;
    else
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
