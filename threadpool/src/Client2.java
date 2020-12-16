import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Client2 {
    public static void main(String[] args) {
        ExecutorService executorService = Executors.newFixedThreadPool(5);
        for (int i = 0; i <10 ; i++) {
            int id = i;
            executorService.submit(()-> {
                System.out.println(id +Thread.currentThread().getName());
            });

        }
        //shutdownNow()取消当前任务关闭连接池
        //shutdown()执行当前任务提交后的任务关闭连接池
        List<Runnable> runnables = executorService.shutdownNow();
        System.out.println(runnables);
        executorService.shutdown();
    }
}
