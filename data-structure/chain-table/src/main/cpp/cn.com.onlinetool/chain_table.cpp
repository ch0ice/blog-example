#include<iostream>
using namespace std;

//单向链表
struct node
{
    //数据域
    int data;
    //指针域
    node* next;
};

int main()
{

    node* s1 = new node();
    s1->data = 1;
    node* s2 = new node();
    s2->data = 2;
    node* s3 = new node();
    s3->data = 3;

//  建立链接
    s1->next = s2;
    s2->next = s3;

//  打印数据
    cout << s1->data << endl;
    cout << s1->next->data << endl;
    cout << s1->next->next->data << endl;

//  插入节点
    node* s4 = new node();
    s4->data = 40;
    s1->next = s4;
    s4->next = s2;


//  打印内存地址
    cout << s1 << endl;
    cout << s2 << endl;
    cout << s3 << endl;

//  由于链表内存不连续，所以没有下标的概念，取值只能循环取
    cout << s1->data << endl;
    cout << s1->next->data << endl;
    cout << s1->next->next->data << endl;
    cout << s1->next->next->next->data << endl;

    delete s1;
    delete s2;
    delete s3;
    delete s4;

    return 0;
}
