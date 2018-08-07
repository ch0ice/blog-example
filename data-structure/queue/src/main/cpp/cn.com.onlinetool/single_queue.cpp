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
}QNode,*QNodePtr;

typedef struct LinkQueue{
    //队头 队尾 指针
    QNodePtr head,tail;
}LinkQueue;


// 构造一个空队列Q
LinkQueue* Q_Init() {
    //申请内存
    LinkQueue* Q = (LinkQueue*)malloc(sizeof(LinkQueue));
    //如果 Q 为 NULL 说明 内存申请失败，结束程序
    if (!Q)
        exit(OVERFLOW);
    //初始化头尾节点 指向相同地址
    Q->head = Q->tail = (QNodePtr)malloc(sizeof(QNode));
    //如果 Q->head 为 NULL 说明 内存申请失败，结束程序
    if (!Q->head)
        exit(OVERFLOW);
    Q->head->next = NULL;
    return Q;
}

// 销毁队列Q(无论空否均可)
void Q_Destroy(LinkQueue *Q) {
    while (Q->head) {
        //将队尾指向队头下一个节点的地址（第1个节点）
        Q->tail = Q->head->next;
        //回收队头
        free(Q->head);
        //将队头指向队尾（相当于第1个节点变成了队头，然后依次第2个第3个、、、
        //直到没有下一个节点，也就是 Q->head == NULL 的时候）
        Q->head = Q->tail;
    }
    free(Q);
}

// 将Q清为空队列
void Q_Clear(LinkQueue *Q) {
    QNodePtr p, q;
    //将队尾指向队头点的地址
    Q->tail = Q->head;
    //取出第1个节点
    p = Q->head->next;
    //回收第1个节点
    Q->head->next = NULL;
    while (p) {
        //将 q 指向 p（第1个节点）
        q = p;
        //将 p 指向 第2个节点
        p = p->next;
        //回收第2个节点
        free(q);
    }
}

// 若Q为空队列，则返回-1，否则返回1
int Q_Empty(LinkQueue Q) {
    if (Q.head->next == NULL)
        return 1;
    else
        return -1;
}

// 求队列的长度
int Q_Length(LinkQueue Q) {
    int i = 0;
    QNodePtr p;
    p = Q.head;
    //遍历队列中的节点，直到队尾等于队头
    while (Q.tail != p) {
        i++;
        p = p->next;
    }
    return i;
}

// 打印队列中的内容
void Q_Print(LinkQueue Q) {
    int i = 0;
    QNodePtr p;
    p = Q.head;
    while (Q.tail != p) {
        i++;
        cout << p->next->data << endl;
        p = p->next;
    }
}

// 若队列不空，则用e返回Q的队头元素，并返回1，否则返回-1
int Q_GetHead(LinkQueue Q, int &e) {
    QNodePtr p;
    if (Q.head == Q.tail)
        return -1;
    p = Q.head->next;
    e = p->data;
    return 1;
}

// 插入元素e为Q的新的队尾元素
void Q_Put(LinkQueue *Q, int e) {
    QNodePtr p = (QNodePtr)malloc(sizeof(QNode));
    if (!p) // 存储分配失败
        exit(OVERFLOW);
    p->data = e;
    p->next = NULL;
    //FIFO，将新节点追加到尾节点后面
    Q->tail->next = p;
    //将新的节点变成尾节点
    Q->tail = p;
}


// 若队列不空，删除Q的队头元素，用e返回其值，并返回1，否则返回-1
int Q_Poll(LinkQueue *Q,int &e) {
    QNodePtr p;
    if (Q->head == Q->tail)
        return -1;
    //取出头节点
    p = Q->head->next;
    //取出头节点的数据
    e = p->data;
    cout << e << endl;
    Q->head->next = p->next;
    if (Q->tail == p)
        Q->tail = Q->head;
    free(p);
    cout << e << endl;
    return 1;
}


int main(){

    cout << "初始化队列" << endl;
    LinkQueue * Q = Q_Init();
    cout << "Q " << Q << endl;
    cout << "head= " << Q->head << endl;
    cout << "tail= " << Q->tail << endl;

    cout << "是否为空" << Q_Empty(*(Q)) << endl;

    cout << "当前队列长度" << Q_Length(*(Q)) << endl;

    cout << "插入数据（put）" << endl;
    Q_Put(Q,111);
    Q_Put(Q,222);
    Q_Put(Q,333);
    Q_Put(Q,444);

    cout << "是否为空" << Q_Empty(*(Q)) << endl;


    int head_data;
    Q_GetHead(*(Q),head_data);
    cout << "获取头元素" << head_data << endl;

    cout << "当前队列长度" << Q_Length(*(Q)) << endl;

    cout << "打印队列中的内容" << endl;
    Q_Print(*(Q));

    Q_Poll(Q,head_data);
    cout << "取出并删除数据（poll）" << head_data << endl;
    Q_Poll(Q,head_data);
    cout << "取出并删除数据（poll）" << head_data << endl;
    Q_Poll(Q,head_data);
    cout << "取出并删除数据（poll）" << head_data << endl;

    cout << "当前队列长度" << Q_Length(*(Q)) << endl;

    cout << "清空队列" << endl;
    Q_Clear(Q);
    cout << Q->head << endl;
    cout << Q->tail << endl;

    cout << "是否为空" << Q_Empty(*(Q)) << endl;

    cout << "销毁队列" << endl;
    Q_Destroy(Q);
    cout << "Q " << Q << endl;
    cout << "head= " << Q->head << endl;
    cout << "tail= " << Q->tail << endl;




}