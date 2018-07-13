# include<iostream>
using namespace std;

//单向链表
struct ss
{
    //数据域
    int data;
    //指针域
    ss* next;
};

int main()
{

    ss* s1 = new ss();
    s1->data = 1;
    ss* s2 = new ss();
    s2->data = 2;
    ss* s3 = new ss();
    s3->data = 3;

//  建立链接
    s1->next = s2;
    s2->next = s3;

//  插入节点
    ss* s4 = new ss();
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

    delete s1;
    delete s2;
    delete s3;
    delete s4;

    return 0;
}



//双向链表
struct s2
{
    int data;
    s2* next;
};

//循环链表
struct s3
{
    int data;
    s3* next;
};
