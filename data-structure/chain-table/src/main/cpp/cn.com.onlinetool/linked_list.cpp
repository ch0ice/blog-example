#include <stdlib.h>
#include<iostream>
#include "linked_list_h.h"


//定义双向链表数据体
struct node{
    void *data;
    struct node *prev,*next;
};
//定义双向链表结构体
struct linked_list{
    struct node head;
    int elm_size;
    int elmnr;
};

//初始化
LINKED_LIST_T *linked_list_new(int elm_size) {
    struct linked_list *new_list;
    new_list = (linked_list *)malloc(sizeof(struct linked_list));
//    new_list = new linked_list();
    if (new_list == NULL)
        return NULL;
    new_list->head.data = NULL;
    new_list->head.next = &new_list->head;
    new_list->head.prev = &new_list->head;
    new_list->elm_size = elm_size;
    return (void *)new_list;
};
//
////销毁
//int linked_list_delete(LINKED_LIST_T *ptr) {
//    struct linked_list *me = ptr;
//    struct node *curr, *save;
//    for (curr = me->head.next ;
//            curr != &me->head ; curr = save) {
//        save = curr->next;
//        free(curr->datap);
//        free(curr);
//    }
//    free(me);
//    return 0;
//}
//
////插入节点
//int linked_list_node_append(LINKED_LIST_T *ptr, const void *data) {
//    struct linked_list *me = ptr;
//    struct node *newnodep;
//    newnodep = malloc(sizeof(struct node_st));
//    if (newnodep == NULL)
//        return -1;
//    newnodep->data = malloc(me->elmsize);
//    if (newnodep->data == NULL) {
//        free(newnodep);
//        return -1;
//    }
//    memcpy(newnodep->data, data, me->elmsize);
//    me->head.prev->next = newnodep;
//    newnodep->prev = me->head.prev;
//    me->head.prev = newnodep;
//    newnodep->next = &me->head;
//    return 0;
//}
//
////插入节点
//int linked_list_node_prepend(LINKED_LIST_T *ptr, const void *data) {
//    struct linked_list *me = ptr;
//    struct node *newnodep;
//    newnodep = malloc(sizeof(struct node_st));
//    if (newnodep == NULL)
//        return -1;
//    newnodep->data = malloc(me->elmsize);
//    if (newnodep->data == NULL) {
//        free(newnodep);
//        return -1;
//    }
//    memcpy(newnodep->data, data, me->elmsize);
//    me->head.next->prev = newnodep;
//    newnodep->next = me->head.next;
//    me->head.next = newnodep;
//    newnodep->prev = &me->head;
//    return 0;
//}
//
////遍历节点
//int linked_list_travel(LINKED_LIST_T *ptr, node_proc_fun_t *proc) {
//    struct llist_st *me = ptr;
//    struct node_st *curr;
//    for (curr = me->head.next; curr != &me->head; curr = curr->next)
//        proc(curr->data); // proc(something you like)
//    return 0;
//}
//
////删除节点
//void linked_list_node_delete(LINKED_LIST_T *ptr,
//                       node_comp_fun_t *comp,
//                       const void *key) {
//    struct llist_st *me = ptr;
//    struct node_st *curr;
//    for (curr = me->head.next;
//            curr != &me->head; curr = curr->next) {
//        if ( (*comp)(curr->data, key) == 0 ) {
//            struct node_st *_next, *_prev;
//            _prev = curr->prev; _next = curr->next;
//            _prev->next = _next; _next->prev = _prev;
//            free(curr->data);
//            free(curr);
//            break;
//        }
//    }
//    return;
//}
//
////查找节点
//void *linked_list_node_find(LINKED_LIST_T *ptr,
//                      node_comp_fun_t *comp, const void *key) {
//    struct llist_st *me = ptr;
//    struct node_st *curr;
//    for (curr = me->head.next;
//            curr != &me->head; curr = curr->next) {
//        if ( (*comp)(curr->data, key) == 0 )
//            return curr->data;
//    }
//    return NULL;
//}
