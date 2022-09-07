package mao.t3;


import java.util.concurrent.atomic.AtomicStampedReference;

/**
 * Project name(项目名称)：java并发编程_原子引用
 * Package(包名): mao.t3
 * Class(类名): Test
 * Author(作者）: mao
 * Author QQ：1296193245
 * GitHub：https://github.com/maomao124/
 * Date(创建日期)： 2022/9/7
 * Time(创建时间)： 13:03
 * Version(版本): 1.0
 * Description(描述)： 无
 */

public class Test
{
    private static final AtomicStampedReference<String> ref = new AtomicStampedReference<>("A", 0);

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
            System.out.println("t1线程A改B：" + ref.compareAndSet(ref.getReference(),
                    "B", ref.getStamp(), ref.getStamp() + 1));
        }, "t1").start();
        sleep(500);
        new Thread(() ->
        {
            System.out.println("t2线程B改A：" + ref.compareAndSet(ref.getReference(),
                    "A", ref.getStamp(), ref.getStamp() + 1));
        }, "t2").start();

    }

    public static void main(String[] args)
    {
        String prev = ref.getReference();
        //版本号
        int stamp = ref.getStamp();
        change();
        sleep(1000);
        System.out.println("main线程A改为C：" + ref.compareAndSet(prev, "C", stamp, stamp + 1));
    }
}
