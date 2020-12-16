import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

public class Client1 {
  /*
  *  线程模型：
  *     用户线程（ULT）：线程有app自己创建和管理，不需要用户态/内核态切换，速度快，线程阻塞，进程阻塞
  *     内核线程（KLT）：线程由操作系统管理
  *线程池原理：首先任务提交后由核心线程执行，
  *     当核心线程数达到最大时，将任务加入到任务队列中等候，
  *    如果任务队列也满了，就会创建非核心线程执行任务，
  *     如果达到最大线程数，将执行饱和策略，拒绝任务
  *    如果非核心线程达到最大空闲时间，将被销毁，核心线程默认不被销毁
  * CorePoolSize：核心线程数8020原则按照80%的情况设计核心线程数，剩下的最大线程数解决
  * workQueue任务队列核心线程数/单个任务执行时间*2
  * maximumPoolSize:最大线程数
  * keepAliveTime：最大空闲时间
  *
  *
  *
  *
  * */
//自定义线程池
    public static void main(String[] args) {
        MyThreadPool pool = new MyThreadPool(2, 4, 20);
        for (int i = 0; i <100 ; i++) {
            MyTesk myTesk = new MyTesk(i);
            pool.submit(myTesk);
        }

    }
}

//任务类
class MyTesk implements Runnable{
    private int num;//任务编号

    public MyTesk(int num) {
        this.num = num;
    }
    @Override
    public void run() {
        System.out.println(num+"号线程即将执行    "+Thread.currentThread().getName());
        try {
            Thread.sleep(200);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println(num+"号线程执行完成   "+Thread.currentThread().getName());
    }

    @Override
    public String toString() {
        return "MyTesk{" +
                "num=" + num +
                '}';
    }
}

//工作类,保存所有的任务类
class MyWork extends Thread{

    private List<Runnable>tasks;//所有将要执行的任务类
    private String name;//线程名
    public MyWork(List<Runnable> tasks,String name){
        super(name);
        this.tasks=tasks;
        this.name=name;
    }

    @Override
    public void run() {
        while (tasks.size()>0) {
            Runnable task = tasks.remove(0);
            task.run();
        }
    }
}

//线程池类
class MyThreadPool{
    private List<Runnable> tasks= Collections.synchronizedList(new LinkedList<>());//任务队列
    private int coreThreadSize;//最大核心线程数
    private int num;//当前线程数
    private int maxSize;//最大线程数
    private int workSize;//最大任务队列长度

    public MyThreadPool(int coreThreadSize, int maxSize, int workSize) {
        this.coreThreadSize = coreThreadSize;
        this.maxSize = maxSize;
        this.workSize = workSize;
    }

    //1.提交任务
    public void submit(Runnable runnable){
        //判断当前集合中的任务数量是否超过最大任务队列
        if (tasks.size()>=workSize){
            System.out.println("丢弃该线程"+runnable);
        }else {
            tasks.add(runnable);
            //执行任务
            execute(runnable);
        }

    }

    private void execute(Runnable runnable) {
        //判断当前线程是否超过了核心线程数
        if (num<coreThreadSize){
            new MyWork(tasks,"核心线程"+num).start();
            num++;
            //判断当前线程数量是否大于最大线程数
        }else if(num<maxSize){
            new MyWork(tasks,"非核心线程"+num).start();
            num++;
        }else {
            System.out.println("任务缓存"+runnable);
        }
    }
}
