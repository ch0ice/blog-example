package cn.com.onlinetool;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args )
    {
        System.out.println( "Hello World!" );
        //测试 int 常量池
        Integer aaaaaaa = 127;
        Integer aaaaaaa1 = 127;
        System.out.println(aaaaaaa == aaaaaaa1);

        Integer bbbbbbb = 128;
        Integer bbbbbbb1 = 128;
        System.out.println(bbbbbbb == bbbbbbb1);

        //测试流混淆
        int score = 10;
        if(score >= 60){
            System.out.println("及格");
        }else if(score >= 80){
            if(score < 85){
                System.out.println("良好");
            }else if(score >= 85){
                System.out.println("优秀");
            }
        }


    }
}
