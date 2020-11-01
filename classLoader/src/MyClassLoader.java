import java.io.FileInputStream;

public class MyClassLoader extends ClassLoader{
    private String uri="D:\\java\\javaCode\\gitDemo3\\gitproject1\\demo1\\";

    @Override
    protected Class<?> findClass(String name) throws ClassNotFoundException {
        FileInputStream is=null;
        byte[] bytes =null ;
        int len=0;
        try {
          is= new FileInputStream(uri+name+".class");
          bytes=  new byte[1024];
           while ((len=is.read(bytes))!=-1){
               return defineClass(name,bytes,0,len);
           }
           return null;
        } catch (Exception e) {
            return null;
        }



    }
}
