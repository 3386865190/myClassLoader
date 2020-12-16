public class Client {
    /*
    * 类加载过程：加载---->连接（验证，准备，解析）---->初始化
    * 加载：只要求加载进虚拟机的是一个二进制流，而没有规定流从哪里来
    * 验证：检验二进制流是否合法，字节码验证，语法验证
    * 准备：给类变量分配内存并赋初始值（常量除外，放在常量池），—-->String：“”
    *解析：符号引用改为直接引用
    * 初始化：真正执行java类中的代码给变量赋值
    *
    *
    * */
    static{
        i=2;
        //静态语句只能访问到定义在静态代码之前的变量，
        // 定义在静态语句之后的变量，可以赋值，不可以访问
//        System.out.println(i);
    }
    static int i=1;

    public static void main(String[] args) {
        System.out.println(i);
    }
}
