#include <stdlib.h>
#include <math.h>
#include <iostream>
#include <cstring>
#include <cstdlib>
using namespace std;

#define MAX_QSIZE 5 // 最大队列长度
// 队列的数据类型
typedef int Q_Data;
// 队列的存储结构（非循环队列）
struct Queue {
    Q_Data Array[MAX_QSIZE]; // 阵列空间大小
    int front; // 队头
    int rear; // 队尾
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
    Q->front = Q->rear = Q->length = 0;
    return Q;
}

// 将Q清为空队列
void Q_Clear(Queue *Q) {
    //清除头尾下标和长度
    Q->front = Q->rear = Q->length = 0;
}

// 入列
Q_Data Q_Put(Queue *Q, Q_Data x) {
    //如果当前元素数量等于最大数量 返回 -1
    if (Q->rear == MAX_QSIZE)
        return -1;
    Q->Array[Q->rear] = x;
    Q->rear++;
    //length + 1
    Q->length++;
    return 1;
}

// 出列
Q_Data Q_Poll(Queue *Q,Q_Data &e) {
    //如果当前元素数量等于最大数量 返回 -1
    if (Q->front == MAX_QSIZE)
        return -1;
    e = Q->Array[Q->front];
    Q->front++;
    // 移出后減少1
    Q->length--;
    return 1;
}

// 出列 解决"假溢出"
Q_Data Q_Poll_New(Queue *Q,Q_Data &e) {
    //如果当前元素数量等于0或者等于最大数量 返回 -1
    if (Q->front == 0 || Q->front == MAX_QSIZE)
        return -1;
    e = Q->Array[Q->front];
    int x = MAX_QSIZE;
    while(x != 0){
        Q->Array[x-1] = Q->Array[x];
        x--;
    }
    Q->rear--;
    // 移出后減少1
    Q->length--;
    return 1;
}


int main() {
    cout << "初始化队列" << endl;
    Queue *Q = Q_Init();
    cout << "front= " << Q->front << endl;
    cout << "rear= " << Q->rear << endl;
    cout << "length= " << Q->length << endl;

    cout << "插入数据（put）" << endl;
    Q_Put(Q, 111);
    Q_Put(Q, 222);
    Q_Put(Q, 333);
    Q_Put(Q, 444);
    Q_Put(Q, 555);
    cout << "length= " << Q->length << endl;

    Q_Data head_data;
    cout << "取出并删除数据（poll）" << Q_Poll_New(Q,head_data) << endl;
    cout << "取出并删除数据（poll）" << Q_Poll_New(Q,head_data) << endl;
    cout << "取出并删除数据（poll）" << Q_Poll_New(Q,head_data) << endl;
    cout << "取出并删除数据（poll）" << Q_Poll_New(Q,head_data) << endl;
    cout << "取出并删除数据（poll）" << Q_Poll_New(Q,head_data) << endl;
    cout << "length= " << Q->length << endl;

    cout << "插入数据（put）" << endl;
    Q_Put(Q, 666);
    cout << "length= " << Q->length << endl;

    cout << "取出并删除数据（poll）" << Q_Poll_New(Q,head_data) << endl;

    cout << "front= " << Q->front << endl;
    cout << "rear= " << Q->rear << endl;
    cout << "length= " << Q->length << endl;

}
