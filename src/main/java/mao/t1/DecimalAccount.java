package mao.t1;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

/**
 * Project name(项目名称)：java并发编程_原子引用
 * Package(包名): mao.t1
 * Interface(接口名): DecimalAccount
 * Author(作者）: mao
 * Author QQ：1296193245
 * GitHub：https://github.com/maomao124/
 * Date(创建日期)： 2022/9/7
 * Time(创建时间)： 11:50
 * Version(版本): 1.0
 * Description(描述)： 无
 */

public interface DecimalAccount
{
    /**
     * 获取余额
     *
     * @return {@link BigDecimal}
     */
    BigDecimal getBalance();

    /**
     * 取款
     *
     * @param amount BigDecimal
     */
    void withdraw(BigDecimal amount);

    /**
     * 方法内会启动 1000 个线程，每个线程做 -10 元 的操作
     * 如果初始余额为 10000 那么正确的结果应当是 0
     */
    static void start(DecimalAccount account)
    {
        List<Thread> threads = new ArrayList<>();
        long start = System.currentTimeMillis();
        for (int i = 0; i < 1000; i++)
        {
            threads.add(new Thread(() ->
            {
                account.withdraw(BigDecimal.TEN);
            }));
        }
        threads.forEach(Thread::start);
        threads.forEach(t ->
        {
            try
            {
                t.join();
            }
            catch (InterruptedException e)
            {
                e.printStackTrace();
            }
        });
        long end = System.currentTimeMillis();
        System.out.println("剩余余额：" + account.getBalance());
        System.out.println("运行时间：" + (end - start) + "ms");
    }
}
