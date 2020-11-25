# include<iostream>
using namespace std;


int main()
{

    cout << "静态创建一维数组" << endl;
    int a[3] = {1,2,3};
    cout << "查询" << endl;
    cout << a[0] << endl;
    cout << a[1] << endl;
    cout << a[2] << endl;
    cout << "修改" << endl;
    a[0] = 4;
    cout << "查询" << endl;
    cout << a[0] << endl;
    cout << a[1] << endl;
    cout << a[2] << endl;

    cout << "动态创建一维数组" << endl;
    int length = 3;
    int *a1 = (int *)malloc(length * sizeof(int));
    cout << "初始化" << endl;
    a1[0] = 1;
    a1[1] = 2;
    a1[2] = 3;
    cout << "查询" << endl;
    cout << "下标=" << 0 << "值=" << a1[0] << endl;
    cout << "下标=" << 1 << "值=" << a1[1] << endl;
    cout << "下标=" << 2 << "值=" << a1[2] << endl;
    cout << "修改" << endl;
    *(a1+1) = 4;
    cout << "查询" << endl;
    cout << "下标=" << 0 << "值=" << a1[0] << endl;
    cout << "下标=" << 1 << "值=" << a1[1] << endl;
    cout << "下标=" << 2 << "值=" << a1[2] << endl;


    cout << "静态创建二维数组" << endl;
    int b[2][2] =
    {
        {11,22},
        {33,44}
    };
    cout << "查询" << endl;
    cout << b[0][0] << endl;
    cout << b[0][1] << endl;
    cout << b[1][0] << endl;
    cout << b[1][1] << endl;
    cout << "修改" << endl;
    b[0][1] = 55;
    cout << "查询" << endl;
    cout << b[0][0] << endl;
    cout << b[0][1] << endl;
    cout << b[1][0] << endl;
    cout << b[1][1] << endl;

    cout << "动态创建二维数组" << endl;
    //一维长度/行长度
    int x = 2;
    //二维长度/列长度
    int y = 2;
    //数组总长度
    int length1 = x * y;
    int *b1 = (int *)malloc(length1 * sizeof(int));
    cout << "初始化" << endl;
    b1[0 * y + 0] = 11;
    b1[0 * y + 1] = 22;;
    b1[1 * y + 0] = 33;;
    b1[1 * y + 1] = 44;;
    cout << "查询" << endl;
    cout << "下标=" << "值=" << b1[0 * y + 0] << endl;
    cout << "下标=" << "值=" << b1[0 * y + 1] << endl;
    cout << "下标=" << "值=" << b1[1 * y + 0] << endl;
    cout << "下标=" << "值=" << b1[1 * y + 1] << endl;
    cout << "修改" << endl;
    b1[0*2+0] = 55;
    cout << "查询" << endl;
    cout << "下标=" << "值=" << b1[0 * y + 0] << endl;
    cout << "下标=" << "值=" << b1[0 * y + 1] << endl;
    cout << "下标=" << "值=" << b1[1 * y + 0] << endl;
    cout << "下标=" << "值=" << b1[1 * y + 1] << endl;


    cout << "静态创建多维数组" << endl;
    int c[2][2][2] =
    {
        {
            {111,222},
            {333,444}
        },
        {
            {555,666},
            {777,888}
        }
    };
    cout << "查询" << endl;
    cout << c[0][0][0] << endl;
    cout << c[0][0][1] << endl;
    cout << c[0][1][0] << endl;
    cout << c[0][1][1] << endl;
    cout << c[1][0][0] << endl;
    cout << c[1][0][1] << endl;
    cout << c[1][1][0] << endl;
    cout << c[1][1][1] << endl;
    cout << "修改" << endl;
    c[1][0][0] = 666;
    cout << "查询" << endl;
    cout << c[0][0][0] << endl;
    cout << c[0][0][1] << endl;
    cout << c[0][1][0] << endl;
    cout << c[0][1][1] << endl;
    cout << c[1][0][0] << endl;
    cout << c[1][0][1] << endl;
    cout << c[1][1][0] << endl;
    cout << c[1][1][1] << endl;

    cout << "动态创建多维数组" << endl;
    //一维长度/高度
    int h = 2;
    //二维长度/宽度
    int w = 2;
    //三维长度/深度
    int d = 2;
    //数组总长度
    int length2 = h * w * d;
    int *c1 = (int *)malloc(length2 * sizeof(int));
    cout << "初始化" << endl;
    c1[0 * h * w + 0 * d + 0] = 111;
    c1[0 * h * w + 0 * d + 1] = 222;
    c1[0 * h * w + 1 * d + 0] = 333;
    c1[0 * h * w + 1 * d + 1] = 444;
    c1[1 * h * w + 0 * d + 0] = 555;
    c1[1 * h * w + 0 * d + 1] = 666;
    c1[1 * h * w + 1 * d + 0] = 777;
    c1[1 * h * w + 1 * d + 1] = 888;
    cout << "查询" << endl;
    cout << "下标=" << (0 * h * w + 0 * d + 0) << "值=" << c1[0 * h * w + 0 * d + 0] << endl;
    cout << "下标=" << (0 * h * w + 0 * d + 1) << "值=" << c1[0 * h * w + 0 * d + 1] << endl;
    cout << "下标=" << (0 * h * w + 1 * d + 0) << "值=" << c1[0 * h * w + 1 * d + 0] << endl;
    cout << "下标=" << (0 * h * w + 1 * d + 1) << "值=" << c1[0 * h * w + 1 * d + 1] << endl;
    cout << "下标=" << (1 * h * w + 0 * d + 0) << "值=" << c1[1 * h * w + 0 * d + 0] << endl;
    cout << "下标=" << (1 * h * w + 0 * d + 1) << "值=" << c1[1 * h * w + 0 * d + 1] << endl;
    cout << "下标=" << (1 * h * w + 1 * d + 0) << "值=" << c1[1 * h * w + 1 * d + 0] << endl;
    cout << "下标=" << (1 * h * w + 1 * d + 1) << "值=" << c1[1 * h * w + 1 * d + 1] << endl;
    cout << "修改" << endl;
    c1[0 * w * d + 0 * d + 0] = 666;
    cout << "查询" << endl;
    cout << "下标=" << (0 * h * w + 0 * d + 0) << "值=" << c1[0 * h * w + 0 * d + 0] << endl;
    cout << "下标=" << (0 * h * w + 0 * d + 1) << "值=" << c1[0 * h * w + 0 * d + 1] << endl;
    cout << "下标=" << (0 * h * w + 1 * d + 0) << "值=" << c1[0 * h * w + 1 * d + 0] << endl;
    cout << "下标=" << (0 * h * w + 1 * d + 1) << "值=" << c1[0 * h * w + 1 * d + 1] << endl;
    cout << "下标=" << (1 * h * w + 0 * d + 0) << "值=" << c1[1 * h * w + 0 * d + 0] << endl;
    cout << "下标=" << (1 * h * w + 0 * d + 1) << "值=" << c1[1 * h * w + 0 * d + 1] << endl;
    cout << "下标=" << (1 * h * w + 1 * d + 0) << "值=" << c1[1 * h * w + 1 * d + 0] << endl;
    cout << "下标=" << (1 * h * w + 1 * d + 1) << "值=" << c1[1 * h * w + 1 * d + 1] << endl;



    //元素标识符和定址公式
    //数组名 =  数组首元素地址 = 元素基址
    //元素地址 = 元素基址 + 元素下标
    //元素基址 + 元素下标 的值 会被解析成 定址公式
    //定址公式体现出了数组的内存连续性

    //验证一维数组 定址公式 = (a1+0) + i * sizeof(DataType)
    cout << "验证一维数组 定址公式" << endl;
    cout << "打印元素基址" << a1 << endl;
    cout << "打印元素基址" << a1+0 << endl;
    //根据上面我们说的公式 假设 元素基址 = 0x7ffc39400340
    //那么 0x7ffc39400340 + 1 * 4 = 0x7ffc39400344;
    cout << "打印第二个元素的地址" << a1+1 << endl;

    //验证二维数组 定址公式 = (b1+0) + (x * 2 + y) * sizeof(DataType)
    //注意公式里的 x y 和上面打印的不一样，上面的 x y 分别代表每个维度的长度，公式里面是每个维度的下标
    cout << "验证二维数组 定址公式" << endl;
    cout << "打印元素基址" << b1 << endl;
    cout << "打印元素基址" << (b1 + 0 * y + 0) << endl;
    //根据上面我们说的公式 假设 元素基址 = 0x7ffc39400340
    //那么 0x7ffc39400340 + (x * 2 + y) = 0x7ffc39400344;
    cout << "打印第二个元素的地址" << (b1 + 0 * y + 1) << endl;

    //验证三维数组 定址公式 = (c1+0) + (h * 2 * 2 + w * 2 + d) * sizeof(DataType)
    //注意公式里的 h w y 和上面打印的不一样，上面的 h w y 分别代表每个维度的长度，公式里面是每个维度的下标
    cout << "验证三维数组 定址公式" << endl;
    cout << "打印元素基址" << c1 << endl;
    cout << "打印元素基址" << c1+0 << endl;
    //根据上面我们说的公式 假设 元素基址 = 0x7ffc39400340
    //那么 0x7ffc39400340 + 0 * 2 * 2 + 0 * 2 + 1 = 0x7ffc39400344;
    cout << "打印第二个元素的地址" << c1 + 0 * 2 * 2 + 0 * 2 + 1 << endl;


    return 0;
}
