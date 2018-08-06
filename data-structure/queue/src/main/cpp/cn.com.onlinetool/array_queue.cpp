#include <stdio.h>
#include <stdlib.h>
// 佇列資料結構
struct Queue {
    int Array[10]; // 陣列空間大小
    int head; // 前端(front)
    int tail; // 後端(rear)
    int length; // 佇列長度，將其視為當前资料大小，並且使用這個成員判斷滿或空
};

// 資料加入佇列
void EnQueue(Queue *Queue1, int x) {
    Queue1->Array[Queue1->tail] = x;
    if (Queue1->tail + 1 == 10) { // Queue1->length改為空間大小10
        Queue1->tail = 0; // 1改為0
    } else
        Queue1->tail = Queue1->tail + 1;
    Queue1->length = Queue1->length + 1; // 當前個數增1
}

// 資料移出佇列
int DeQueue(Queue *Queue1) {
    int x = Queue1->Array[Queue1->head];
    if (Queue1->head + 1 == 10) // 空間大小10
        Queue1->head = 0;
    else
        Queue1->head = Queue1->head + 1;
    Queue1->length = Queue1->length - 1; // 移出后減少1
    return x;
}

// 佇列操作
int main() {
    Queue *Queue1 = (Queue *)malloc(sizeof(Queue)); // 建立資料結構
    Queue1->length = 0; // 新增長度//10改為0，初始狀態
    Queue1->head = 0; // 必須要先初始化
    Queue1->tail = 0; // 必須要先初始化
    EnQueue(Queue1, 5); // 將5放入佇列
    EnQueue(Queue1, 8); // 將8放入佇列
    EnQueue(Queue1, 3); // 將3放入佇列
    EnQueue(Queue1, 2); // 將2放入佇列
    printf("%d ", DeQueue(Queue1)); // 輸出佇列(5)
    printf("%d ", DeQueue(Queue1)); // 輸出佇列(8)
    printf("%d ", DeQueue(Queue1)); // 輸出佇列(3)
    system("pause");
}
