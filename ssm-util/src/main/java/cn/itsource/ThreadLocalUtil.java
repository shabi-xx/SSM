package cn.itsource;

//线程本地存储
public class ThreadLocalUtil {
    //如果需要存储多个 就多创建几个 ThreadLocal对象
    private static ThreadLocal<String> threadLocal = new ThreadLocal<>();

    //把东西放入当前线程
    public static void set(String s){
        threadLocal.set(s);
    }
    //取出当前线程的内容
    public static String get(){
        return threadLocal.get();
    }
    //删除
    public static void remove(){
        threadLocal.remove();
    }
}
