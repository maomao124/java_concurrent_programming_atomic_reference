package mao.t2;

import java.util.concurrent.atomic.AtomicReference;

/**
 * Project name(项目名称)：java并发编程_原子引用
 * Package(包名): mao.t2
 * Class(类名): Test
 * Author(作者）: mao
 * Author QQ：1296193245
 * GitHub：https://github.com/maomao124/
 * Date(创建日期)： 2022/9/7
 * Time(创建时间)： 12:54
 * Version(版本): 1.0
 * Description(描述)： ABA 问题
 */

public class Test
{
    private static final AtomicReference<String> ref = new AtomicReference<>("A");

    /**
     * 睡眠
     *
     * @param time 时间
     */
    private static void sleep(long time)
    {
        try
        {
            Thread.sleep(time);
        }
        catch (InterruptedException e)
        {
            e.printStackTrace();
        }
    }

    /**
     * 改变
     */
    private static void change()
    {
        new Thread(() ->
        {
            System.out.println("t1线程A改B：" + ref.compareAndSet(ref.get(), "B"));
        }, "t1").start();
        sleep(500);
        new Thread(() ->
        {
            System.out.println("t2线程B改A：" + ref.compareAndSet(ref.get(), "A"));
        }, "t2").start();

    }

    public static void main(String[] args)
    {
        String prev = ref.get();
        change();
        sleep(1000);
        System.out.println("main线程A改为C：" + ref.compareAndSet(prev, "C"));
    }
}
