public class Client1_Thread {
    /*
     * 虚拟机会保证线程安全，当有多个线程同时去初始化一个类时，，
     * 只有一个线程去执行，区域线程会阻塞
     * */

    public static void main(String[] args) {
        new Thread(() -> {
            System.out.println(Thread.currentThread().getName()+"开始创建对象");
            new A();
            System.out.println("创建完成");
        }).start();
        new Thread(() -> {
            System.out.println(Thread.currentThread().getName()+"开始创建对象");
            new A();
            System.out.println("创建完成");
        }).start();
    }
}

class A {
    static {
        System.out.println("初始化开始");
        if (true) {
            while (true) {

            }
        }
    }
}
