package start;

public class Client1 {
    public static void main(String[] args) {
        /*
        * jvm内存：  堆、方法区、栈 、程序计数器、本地方法区
        *   堆和方法区所有线程共享，栈、本地方法区、程序计数器每个线程分配一份
        *
        *
        *                                           -{ Eden区（占年轻代的8/10）
        *                          -{年轻代（1/3）  -|                               -{ from（1/10）
        *   堆（存放引用数据类型）：-|                  -{ Survivor区（占年轻代的2/10）--|
        *                        |                                                 -{  to（1/10）
        *                         -{老年代（2/3）
        *           新创建的对象会存放在年轻代的Eden区，当Eden存储空间满了的时候，当前无引用的对象会被
        *               MinorGC回收（从GC Roots【线程栈的本地变量、静态变量、本地方法栈的变量】根出发，
        *               可达的对象都是有引用的对象）；有引用的对象挪到Survivor的From区，分代年龄+1；
        *               下一次From区满了，会清空Eden和from区，有引用的对象挪到To区，每经过一次MinorGC
        *               对象的分代年龄都会+1，当经过了15次后，对象会挪到老年代
        *           当老年代的空间满了，会触发Full GC，停止当前其他线程，有FullGC执行垃圾回收，回收无引用的对象
        *       JVM调优：减少FullGC执行的次数或执行的时间
        *
        *   方法区： 静态变量、常量、
        *           类元信息（Client.class）包含类的头信息..堆中同一个类的实例（client1，client2）指向同一个类元信息
        *   栈：为每个方法开辟出一块栈帧区
        *      局部变量表（局部变量，第一个变量存放的是自身类对象this）、操作数栈（当前操作的数）、动态链接（符号引用转化为直接引用）、方法出口
        *   程序计数器：记录程序运行过程中的行号
        *   本地方法栈： native方法（c语言的实现）
        *
        * */
        Client1 client1 = new Client1();
        client1.sum();
        System.out.println(1111);
    }

    public int sum(){
        int a=2;
        int b=3;
        int c=a+b;
        return c;
    }


}
