import java.io.File;
import java.io.FileOutputStream;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class ClassLoaderTest {
    public static void main(String[] args) throws Exception{
        String b="public class B{public void test(){System.out.println(\"hello world\");}}";
        FileOutputStream os = new FileOutputStream("D:\\java\\javaCode\\gitDemo3\\gitproject1\\demo1\\B.java");
        os.write(b.getBytes());
        Runtime runtime = Runtime.getRuntime();
        runtime.exec("javac B.java", null, new File("D:\\java\\javaCode\\gitDemo3\\gitproject1\\demo1"));
        MyClassLoader classLoader = new MyClassLoader();
        Class<?> aClass = classLoader.loadClass("B");
        Method test = aClass.getMethod("test");
        test.invoke(aClass.newInstance());

    }

    private static void method1() throws ClassNotFoundException, InstantiationException, IllegalAccessException, NoSuchMethodException, InvocationTargetException {
        MyClassLoader loader = new MyClassLoader();
        Class<?> a = loader.findClass("A");
        Object o = a.newInstance();
        Method method = a.getDeclaredMethod("test");
        method.invoke(o );
    }

}
