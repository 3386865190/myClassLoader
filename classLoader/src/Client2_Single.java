public class Client2_Single {
    public static void main(String[] args) {
        System.out.println(Singleton.a);
        System.out.println(Singleton.b);
        System.out.println(Singleton1.a);
        System.out.println(Singleton1.b);
    }
}

class Singleton{
    //初始化过程是根据源代码的先后顺序执行的，
    //先给b赋值为0，在创建对象时加1
     static int a;
     static int b=0;
    private static Singleton singleton=new Singleton();

    private Singleton(){
        a++;
        b++;
    }

    public static Singleton getInstance(){
        return singleton;
    }

}

class Singleton1{
    //先创建对象时b加1，然后再将0赋值给b
    private static Singleton1 singleton1=new Singleton1();
     static int a;
     static int b=0;
    private Singleton1(){
        a++;
        b++;
    }

    public static Singleton1 getInstance(){
        return singleton1;
    }

}