#include "string.h"
#include "stdio.h"
#include "stdlib.h"
#include "io.h"
#include "math.h"
#include "time.h"

#define OK 1
#define ERROR 0
#define TRUE 1
#define FALSE 0
#define MAXSIZE 100 /* 存储空间初始分配量 */

typedef int Status;        /* Status是函数的类型,其值是函数结果状态代码，如OK等 */

/**********定义二叉树的存储结构************/
typedef char TElemType;
TElemType Nil=' '; /* 字符型以空格符为空 */

typedef struct BinTreeNode  /* 结点结构 */
{
    TElemType data;        /* 结点数据 */
    struct BinTreeNode *lchild,*rchild; /* 左右孩子指针 */
}BinTreeNode,*BinTree;
/*****************************************/

/***********用于构造二叉树************** */
int index=1;
typedef char charArray[24]; /*声明一个char类型数组,charArray是数组名也是一个指针*/
charArray str;

Status AssignStr(charArray T,char *chars)
{
    int i;
    if(strlen(chars)>MAXSIZE) return ERROR; /*输入字符的长度超过存储空间最大值*/
    else
    {
        T[0]=strlen(chars); /*0号单元存放字符串长度*/
        for(i=1;i<=T[0];i++)
        {
            T[i]=*(chars+i-1); /*???*/
        }
        return OK;
    }
}
/* ************************************* */

/*构造二叉树*/
void CreateBinTree(BinTree *T)
{
    TElemType ch;
    ch=str[index++];

    if(ch=='#')
        *T=NULL;
    else
    {
        *T=(BinTree)malloc(sizeof(BinTreeNode));
        if(!*T)
            exit(OVERFLOW);
        (*T)->data=ch; /* 生成根结点 */
        CreateBinTree(&(*T)->lchild); /* 构造左子树 */
        CreateBinTree(&(*T)->rchild); /* 构造右子树 */
    }
}

/* 构造空二叉树*/
Status InitBinTree(BinTree *T)
{
    *T=NULL;
    return OK;
}

/*清空二叉树*/
void ClearBinTree(BinTree *T)
{
    if(*T)
    {
        if((*T)->lchild) /* 有左孩子 */
            ClearBinTree(&(*T)->lchild); /* 销毁左孩子子树 */
        if((*T)->rchild) /* 有右孩子 */
            ClearBinTree(&(*T)->rchild); /* 销毁右孩子子树 */
        free(*T); /* 释放根结点 */
        *T=NULL; /* 空指针赋0 */
    }
}

/*判断二叉树是否为空*/
Status IsBinTreeEmpty(BinTree T)
{
    if(T)
        return FALSE;
    else
        return TRUE;
}

/*计算二叉树的深度*/
int GetDepth(BinTree T)
{
    int i,j;
    if(!T)
        return 0;
    if(T->lchild)
        i=GetDepth(T->lchild);
    else
        i=0;
    if(T->rchild)
        j=GetDepth(T->rchild);
    else
        j=0;
    return i>j?i+1:j+1;
}

/*获取二叉树的根节点*/
TElemType GetRoot(BinTree T)
{
    if(IsBinTreeEmpty(T))
        return Nil; /*Nil表示空字符*/
    else
        return T->data;
}

/*前序遍历
思路：访问根节点--->前序遍历左子树--->前序遍历右子树
*/
void PreOrderTraverse(BinTree T)
{
    if(T==NULL) return;

    printf("%c ",T->data);/* 显示结点数据，可以更改为其它对结点操作 */
    PreOrderTraverse(T->lchild); /* 再先序遍历左子树 */
    PreOrderTraverse(T->rchild); /* 最后先序遍历右子树 */
}

/*中序遍历
思路：中序遍历左子树--->访问根节点--->中序遍历右子树
*/
void InOrderTraverse(BinTree T)
{
    if(T==NULL) return;

    InOrderTraverse(T->lchild); /* 中序遍历左子树 */
    printf("%c ",T->data);/* 显示结点数据，可以更改为其它对结点操作 */
    InOrderTraverse(T->rchild); /* 最后中序遍历右子树 */
}

/*后序遍历
思路：后序遍历左子树--->后序遍历右子树--->访问根节点
*/
void PostOrderTraverse(BinTree T)
{
    if(T==NULL) return;

    PostOrderTraverse(T->lchild); /* 先后序遍历左子树  */
    PostOrderTraverse(T->rchild); /* 再后序遍历右子树  */
    printf("%c ",T->data);/* 显示结点数据，可以更改为其它对结点操作 */
}


int main()
{
    int i;
    BinTree T;
    TElemType e1;

    /*初始化二叉树为一棵空树*/
    InitBinTree(&T);

    /*设置字符数组用于构造二叉树*/
    AssignStr(str,"ABDH#K###E##CFI###G#J##");

    /*创建二叉树*/
    CreateBinTree(&T);
    printf("创建二叉树后,树空否？%d(1:是 0:否) 树的深度为：%d\n",IsBinTreeEmpty(T),GetDepth(T));

    /*获取二叉树的根节点*/
    e1=GetRoot(T);
    printf("\n二叉树的根为: %c\n",e1);

    /*前序遍历*/
    printf("\n前序遍历二叉树:");
    PreOrderTraverse(T);

    /*中序遍历*/
    printf("\n中序遍历二叉树:");
    InOrderTraverse(T);

    /*后序遍历*/
    printf("\n后序遍历二叉树:");
    PostOrderTraverse(T);

    /*清空二叉树*/
    ClearBinTree(&T);
    printf("\n\n清除二叉树后,树空否？%d(1:是 0:否) 树的深度为：%d\n",IsBinTreeEmpty(T),GetDepth(T));
    i=GetRoot(T);
    if(!i) printf("树空，无根\n");

    getchar();
}