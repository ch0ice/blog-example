# include<iostream>
using namespace std;


int main()
{
    //定义数组
    int a[4] = {111,222,333,444};
    a[5] = 555;

    //打印内存地址
    cout << "=============================================" << endl;
    cout << a << endl;
    cout << (a+1) << endl;
    cout << (a+2) << endl;
    cout << (a+3) << endl;
    cout << (a+4) << endl;

//    输出结果
//    0x61ff0c
//    0x61ff10
//    0x61ff14
//    0x61ff18
//    0x61ff1c
//    可以看出 数组的内存地址是有规律的
//    从 0x61ff0 开始，每次加上 int 占用空间的大小，也就是 4字节，就是下一个元素的内存地址。
//    所以 由此可以推理出，数组保存的元素是有序的。并且可以直接通过下标访问的
//    正因为数组可以通过下标直接访问元素，所以查询极快


    //直接使用内存地址 取数据
    cout << "=============================================" << endl;
    cout << (*a) << endl;
    cout << *(a+1) << endl;
    cout << *(a+2) << endl;
    cout << *(a+3) << endl;
    cout << *(a+4) << endl;


    //使用下标 取数据
    cout << "=============================================" << endl;
    cout << a[0] << endl;
    cout << a[1] << endl;
    cout << a[2] << endl;
    cout << a[3] << endl;
    cout << a[4] << endl;

//     int aa[1024 * 1024 * 2] = {};

    return 0;
}
