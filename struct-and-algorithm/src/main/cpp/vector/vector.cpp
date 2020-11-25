/***************protected函数实现****************/ 
//复制数组区间A[lo, hi)--"copyFrom"函数
template<typename T>
void myVector<T>::copyFrom(T const*A,Rank lo,Rank hi)
{
	_size=hi-lo;//获取向量规模
	_capacity=hi-lo;//获取向量容量
	_elem=new T[_capacity];//生成向量数据区(此时向量处于饱和状态） 
	for(int i=lo;i<hi;i++)
	{
		_elem[i]=A[i];
	}
} 
//向量空间不足时扩容--"expand"函数 
template<typename T>
void myVector<T>::expand()
{
 	if(_size<_capacity)
	{
	 	return;	//尚未满员时，不必扩容
	} 
	_capacity=maxoftwo(_capacity,DEFAULT_CAPACITY);//不低于最小容量
	T*oldElem=_elem;//原向量指针保存 
	_elem=new T[_capacity<<=1];//容量加倍
	for(int i=0;i<_size;i++)//复制原向量内容 
	{
		_elem[i]=oldElem[i];//T为基本类型 ，或已重载赋值操作符"=" 
	} 
	delete [] oldElem;//释放原空间 
}
//装填因子过小时压缩空间--"shrink"函数
template<typename T>
void myVector<T>::shrink()
{
	if(_size<_capacity/2)
	{
		T*oldElem=_elem;//原向量指针保存
		_elem=new T[_capacity>>=1];//容量缩减一半 
		for(int i=0;i<_size;i++)//复制原向量内容
		{
			_elem[i]=oldElem[i];//T为基本类型 ，或已重载赋值操作符"="
		} 
	}
}
//扫描交换--"bubble"函数 
template<typename T> 
bool myVector<T>::bubble(Rank lo,Rank hi)
{
	bool sorted=true;//整体有序标志
	while(++lo<hi)
	{
		if(_elem[lo-1]>_elem[lo])//自做向右，逐一检查各对相邻元素 
		{
			sorted=false; //逆序 
			T temp;//交换 
			temp=_elem[lo-1];
			_elem[lo-1]=_elem[lo];
			_elem[lo]=temp;
		}
	} 
	return sorted; 
}
//起泡排序算法
template<typename T>
void myVector<T>::bubbleSort(Rank lo,Rank hi)
{
	while(!bubble(lo,hi--));//逐趟做扫描交换，直至全部有序 
}
//选取区间[lo,hi)间的最大元素
template<typename T>
Rank myVector<T>::max(Rank lo,Rank hi)
{
	T maxT;
	Rank rank;
	maxT=_elem[lo];
	for(int i=lo;i<hi;i++)
	{
		if(maxT<_elem[i])
		{
			rank=i;
			maxT=_elem[i];
		}
	} 
	return rank;
}
/****************public函数实现******************/ 
//获取秩为r的元素
template<typename T>
T myVector<T>::get(Rank r) const
{
	return _elem[r];
}
//向向量写入数值--"put"函数
template<typename T>
void myVector<T>::put(Rank r,T e)
{
	_elem[r]=e;//用e替换秩为r的数值 
} 
//插入元素--"insert"函数
template<typename T>
Rank myVector<T>::insert(Rank r,T const& e)
{
	expand();//如果有必要，扩容
	for(int i=_size;i>r;i--)//自后向前 
	{
	   _elem[i]=_elem[i-1];//后继元素顺次后移一个单元 	
	} 
	_elem[r]=e;//置入新元素 
	_size++;// 更新容量 
	return r;//返回秩  
}
//删除秩在区间[lo, hi)之内的元素--"remove"函数 
template<typename T>
int myVector<T>::remove(Rank lo,Rank hi)
{
  	if(lo==hi)
	{
	  return 0;
	}
    while(hi<_size)
    {
	 _elem[lo++]=_elem[hi++];//[hi,_size)顺次前移hi-lo位 
    }
    _size=lo;//更新规模 
    shrink();// 如有必要，缩容 
    return hi-lo;//返回被删除元素的数目
}
// 判断向量是否已排序--"disordered"函数
template<typename T>
int myVector<T>::disordered() const
{
	int n=0;//计数器
	for(int i=1;i<_size;i++)//逐一检查各对相邻元素 
	{
	  n+=(_elem[i-1]>_elem[i]);//逆序则计数 
	} 
	return n;//向量有序当且仅当n=0 
}
//对区间[lo,hi)排序(排序接口）--"sort"函数 
template<typename T>
void myVector<T>::sort(Rank lo,Rank hi)
{
	bubbleSort(lo,hi);
}
//无序向量[lo,hi)区间查找--"find"函数
template<typename T>
Rank myVector<T>::find( T const& e, Rank lo, Rank hi ) const//在向量中查找元素，并返回秩最大者 
{
	while((lo<hi--)&&e!=_elem[hi]) ;//逆向查找 
 	return hi;//hi<lo意味着失败；否则hi即命中元素的秩 
}
//有序向量[lo,hi)区间查找--"search"函数
template<typename T>
Rank myVector<T>::search(T const &e,Rank lo,Rank hi) const
{
	return binSearch(_elem,e,lo,hi);//二分查找 
}
//无序去重--"deduplicate"函数
template<typename T>
int myVector<T>::deduplicate()
{
	int oldSize=_size;//记录原规模
	Rank i=1;//从_elem[1]开始
	while(i<_size)//自前向后逐一考查各元素_elem[i] 
	{
	  if(find(_elem[i],0,i)<0)//在前缀中寻找雷同者 
	  {
	      i++;//若无雷同则继续考查其后继
	  } 
	  else
	  {
	   remove(i);//否则删除雷同者  
      }
	}
    return oldSize-_size;//向量规模变化量。即删除元素总数	
} 
//有序去重--"uniquify"函数
template<typename T>
int myVector<T>::uniquify()
{
	int oldSize=_size;//记录原规模 
    int i=0;//从首元素开始 
    while(i<_size-1)//从前向后逐一比对各队相邻元素 
    {
      (_elem[i]==_elem[i+1])?remove(i+1):i++;//若雷同，则删除后者；否则转至后后一元素 
	}
	return oldSize-_size;//向量规模变化量。即删除元素总数
}
//遍历--"traverse"函数 
//遍历1--利用函数指针进行只读或局部性修改
template<typename T>
void myVector<T>::traverse(void (*visit ) ( T& )) 
{
	for(int i=0;i<_size;i++)
	{
		visit(_elem[i]);
	}
}
//遍历2--利用对象机制可进行全局性修改
template<typename T>
template<typename VST>
void myVector<T>::traverse(VST &visit)
{
	for(int i=0;i<_size;i++)
	{
		visit(_elem[i]);
	}
}
/************其他函数**************/
//二分查找(有序向量可用） 
template<typename T>
Rank myVector<T>::binSearch(T*A,T const&e,Rank lo,Rank hi) const 
{
	Rank mi;
    while(lo<hi)//每步迭代可能要做两次比较判断，有3个分支 
    {
	    mi=(lo+hi)>>1;//以中点为轴点 
    	if(e<A[mi]) hi=mi;//深入前半段[lo,mi)继续查找 
    	else if(A[mi]<e) lo=mi+1;//深入后半段(mi,hi) 
    	else return mi;//在mi处待命 
	}
	if(e<A[mi])
	{
		return mi-1;//查找失败
	}
	else
	{
		return mi;//查找失败
	}
		 
<pre>}