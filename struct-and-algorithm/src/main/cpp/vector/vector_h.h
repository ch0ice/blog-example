#ifndef _MYVECTOR_H__
#define _MYVECTOR_H__
typedef int Rank;//秩
#define DEFAULT_CAPACITY  3 //默认的初始容量（实际应用中可设置为更大）

 template <typename T> class myVector { //向量模板类
 protected:
   Rank _size; int _capacity;  T* _elem; //规模、容量、数据区
   void copyFrom ( T const* A, Rank lo, Rank hi ); //复制数组区间A[lo, hi)
   void expand(); //空间不足时扩容
   void shrink(); //装填因子过小时压缩
   bool bubble ( Rank lo, Rank hi ); //扫描交换
   void bubbleSort ( Rank lo, Rank hi ); //起泡排序算法
   Rank max ( Rank lo, Rank hi ); //选取区间[lo,hi)间的最大元素
   int  maxoftwo(int one,int two){ if(one>two) return one; else return two; }//选取两者之间的最大值
   Rank binSearch(T*A,T const&e,Rank lo,Rank hi) const;//二分查找
 public:
 // 构造函数
    myVector ( int c = DEFAULT_CAPACITY, int s = 0, T v = 0 ) //容量为c、规模为s、所有元素初始为v
    { _elem = new T[_capacity = c]; for ( _size = 0; _size < s; _elem[_size++] = v ); } //s<=c

    myVector ( T const* A, Rank n ) { copyFrom ( A, 0, n ); } //数组整体复制
    myVector ( T const* A, Rank lo, Rank hi ) { copyFrom ( A, lo, hi ); } //区间
    myVector ( myVector<T> const& V ) { copyFrom ( V._elem, 0, V._size ); } //向量整体复制
    myVector ( myVector<T> const& V, Rank lo, Rank hi ) { copyFrom ( V._elem, lo, hi ); } //区间

 // 析构函数
    ~myVector() { delete [] _elem; } //释放内部空间
 // 只读访问接口
    Rank size() const { return _size; } //规模
    T get(Rank r) const;//获取秩为r的元素
    void put(Rank r,T e);//用e替换秩为r的数值
    Rank insert ( Rank r, T const& e ); //插入元素
    Rank insert ( T const& e ) { return insert ( _size, e ); } //默认作为末元素插入
    int remove ( Rank lo, Rank hi ); //删除秩在区间[lo, hi)之内的元素
    T remove ( Rank r ){ T e=_elem[r];remove(r,r+1); return e;} //删除秩为r的元素
    int disordered() const; //判断向量是否已排序
    void sort ( Rank lo, Rank hi ); //对[lo, hi)排序
    void sort() { sort ( 0, _size ); } //整体排序
    Rank find ( T const& e ) const { return find ( e, 0, _size ); } //无序向量整体查找
    Rank find ( T const& e, Rank lo, Rank hi ) const; //无序向量区间查找
    Rank search ( T const& e ) const //有序向量整体查找
    { return ( 0 >= _size ) ? -1 : search ( e, 0, _size ); }
    Rank search ( T const& e, Rank lo, Rank hi ) const; //有序向量区间查找
    int deduplicate(); //无序去重
    int uniquify(); //有序去重
    bool empty() const { return !_size; } //判空
 // 重载
    T& operator[] ( Rank r ) const{return _elem[r];}; //重载下标操作符，可以类似于数组形式引用各元素
    myVector<T> & operator= ( myVector<T> const& ); //重载赋值操作符，以便直接克隆向量
 // 遍历
    void traverse ( void (*visit ) ( T& ) ); //遍历（使用函数指针，只读或局部性修改）
    template <typename VST> void traverse ( VST& ); //遍历（使用函数对象，可全局性修改）

 }; //Vector
#include"vector.cpp"
#endif