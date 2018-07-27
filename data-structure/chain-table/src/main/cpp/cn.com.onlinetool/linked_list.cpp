#include <stdlib.h>
#include <iostream>
#include <cstring>
#include "linked_list_h.h"
using namespace std;

//定义双向链表数据体
struct node{
    void *data;
    node *prev,*next;
};
//定义双向链表结构体
struct linked_list{
    node head;
    int elm_size;
    int elmnr;
};

//初始化
LINKED_LIST_T *linked_list_new(int elm_size) {
    linked_list *new_list = (linked_list *)malloc(sizeof(linked_list));
//    new_list = new linked_list();
    if (new_list == NULL)
        return NULL;
    new_list->head.data = NULL;
    new_list->head.next = &new_list->head;
    new_list->head.prev = &new_list->head;
    new_list->elm_size = elm_size;
    return (void *)new_list;
};

//销毁
int linked_list_delete(LINKED_LIST_T *ptr) {
    linked_list *me = (linked_list*)ptr;
    node *curr, *save;
    for (curr = me->head.next; curr != &me->head; curr = save) {
        save = curr->next;
        free(curr->data);
        free(curr);
    }
    free(me);
    return 0;
}

//插入节点 在元素末尾追加元素
int linked_list_node_append(LINKED_LIST_T *ptr, const void *data) {
    linked_list *new_list = (linked_list*)ptr;
    node *new_node;
    new_node = (node*)malloc(sizeof(node));
    if (new_node == NULL)
        return -1;
    new_node->data = malloc(new_list->elm_size);
    if (new_node->data == NULL) {
        free(new_node);
        return -1;
    }
//    int *aa = (int*)new_node->data;
//    int *bb = (int*)data;
//    cout << *aa << endl;
//    cout << *bb << endl;
    //复制内存
    memcpy(new_node->data, data, new_list->elm_size);
//    cout << *aa << endl;
//    cout << *bb << endl;
    new_list->head.prev->next = new_node;
    new_node->prev = new_list->head.prev;
    new_list->head.prev = new_node;
    new_node->next = &new_list->head;
    return 0;
}

//插入节点 在元素头追加元素
int linked_list_node_prepend(LINKED_LIST_T *ptr, const void *data) {
    linked_list *new_list = (linked_list*)ptr;
    node *new_node;
    new_node = (node*)malloc(sizeof(node));
    if (new_node == NULL)
        return -1;
    new_node->data = malloc(new_list->elm_size);
    if (new_node->data == NULL) {
        free(new_node);
        return -1;
    }
    //复制内存
    memcpy(new_node->data, data, new_list->elm_size);
    new_list->head.next->prev = new_node;
    new_node->next = new_list->head.next;
    new_list->head.next = new_node;
    new_node->prev = &new_list->head;
    return 0;
}

//遍历节点
int linked_list_travel(LINKED_LIST_T *ptr, node_print_fun_t *proc) {
    linked_list *me = (linked_list*)ptr;
    node *curr;
    for (curr = me->head.next; curr != &me->head; curr = curr->next){
        proc(curr->data); // proc(something you like)
    }
    return 0;
}

//删除节点
void linked_list_node_delete(LINKED_LIST_T *ptr,
                       node_compare_fun_t *comp,
                       const void *key) {
    linked_list *me = (linked_list*)ptr;
    node *curr;
    for (curr = me->head.next;
            curr != &me->head; curr = curr->next) {
        if ( (*comp)(curr->data, key) == 0 ) {
            node *_next, *_prev;
            _prev = curr->prev; _next = curr->next;
            _prev->next = _next; _next->prev = _prev;
            free(curr->data);
            free(curr);
            break;
        }
    }
    return;
}

//查找节点
void *linked_list_node_find(LINKED_LIST_T *ptr,
                      node_compare_fun_t *comp, const void *key) {
    linked_list *me = (linked_list*)ptr;
    node *curr;
    for (curr = me->head.next;
            curr != &me->head; curr = curr->next) {
        if ( (*comp)(curr->data, key) == 0 )
            return curr->data;
    }
    return NULL;
}

//比较函数
int compare(const void *data1, const void *data2){
    if (*((int*)data1) == *((int*)data2)){
        return 0;
    }
    return -1;
}

//打印函数
void proc(void *data)
{
//    int *new_data = (int*)data;
//    printf ("%d\n",*new_data);

    cout << *((int*)data) << endl;
}


int main()
{
    //初始化链表
    linked_list* list = (linked_list*)linked_list_new(3);

    //插入节点 尾插
    const int a1 = 1;
    const void* data1 = &a1;
    linked_list_node_append(list,data1);
    const int a2 = 2;
    const void* data2 = &a2;
    linked_list_node_append(list,data2);
    const int a3 = 3;
    const void* data3 = &a3;
    linked_list_node_append(list,data3);
    const int a4 = 4;
    const void* data4 = &a4;
    linked_list_node_append(list,data4);

    //插入节点 头插
    const int a5 = 5;
    const void* data5 = &a5;
    linked_list_node_prepend(list,data5);
    const int a6 = 6;
    const void* data6 = &a6;
    linked_list_node_prepend(list,data6);
    const int a7 = 7;
    const void* data7 = &a7;
    linked_list_node_prepend(list,data7);
    const int a8 = 8;
    const void* data8 = &a8;
    linked_list_node_prepend(list,data8);

    //遍历节点
    node_print_fun_t* proc_fun = proc;
    linked_list_travel(list,proc_fun);

    //查找节点
    node_compare_fun_t* compare_fun = compare;
    const int key = 8;
    const void* void_key = &key;
    void *result = linked_list_node_find(list,compare_fun,void_key);
    printf("%s\n","查询结果为");
    printf("%d\n",*((int*)result));



    return 0;
}
