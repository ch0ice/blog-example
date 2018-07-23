#ifndef LINKED_LIST_H
#define LINKED_LIST_H

typedef void node_proc_fun_t(void*);
typedef int node_comp_fun_t(const void*, const void*);

typedef void LINKED_LIST_T;

//初始化
LINKED_LIST_T *linked_list_new(int elmsize);

////销毁
//int linked_list_delete(int *ptr);
//
////插入节点
//int linked_list_node_append(int *ptr, const void *data);
//
////插入节点
//int linked_list_node_prepend(int *ptr, const void *data);
//
////遍历节点
//int linked_list_travel(int *ptr, node_proc_fun_t *proc);
//
////删除节点
//void linked_list_node_delete(int *ptr, node_comp_fun_t *comp, const void *key);
//
////查找节点
//void *linked_list_node_find(int *ptr, node_comp_fun_t *comp, const void *key);

#endif