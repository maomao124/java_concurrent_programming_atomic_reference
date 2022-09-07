package mao.t4;

import java.util.concurrent.atomic.AtomicMarkableReference;

/**
 * Project name(项目名称)：java并发编程_原子引用
 * Package(包名): mao.t4
 * Class(类名): Test
 * Author(作者）: mao
 * Author QQ：1296193245
 * GitHub：https://github.com/maomao124/
 * Date(创建日期)： 2022/9/7
 * Time(创建时间)： 13:12
 * Version(版本): 1.0
 * Description(描述)： 无
 */

public class Test
{
    private static final AtomicMarkableReference<String> ref = new AtomicMarkableReference<>("A", true);

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
            System.out.println("t1线程A改B：" + ref.compareAndSet(ref.getReference(), "B", ref.isMarked(), false));
        }, "t1").start();
        sleep(500);
        new Thread(() ->
        {
            System.out.println("t2线程B改A：" + ref.compareAndSet(ref.getReference(), "A", ref.isMarked(), false));
        }, "t2").start();

    }

    public static void main(String[] args)
    {
        String prev = ref.getReference();
        boolean marked = ref.isMarked();
        change();
        sleep(1000);
        System.out.println("main线程A改为C：" + ref.compareAndSet(prev, "C", marked, false));
    }
}
