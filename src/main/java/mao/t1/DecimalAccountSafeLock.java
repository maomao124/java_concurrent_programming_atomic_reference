package mao.t1;

import java.math.BigDecimal;

/**
 * Project name(项目名称)：java并发编程_原子引用
 * Package(包名): mao.t1
 * Class(类名): DecimalAccountSafeLock
 * Author(作者）: mao
 * Author QQ：1296193245
 * GitHub：https://github.com/maomao124/
 * Date(创建日期)： 2022/9/7
 * Time(创建时间)： 11:53
 * Version(版本): 1.0
 * Description(描述)： 无
 */

public class DecimalAccountSafeLock implements DecimalAccount
{
    /**
     * 锁
     */
    private final Object lock = new Object();
    private BigDecimal balance;

    public DecimalAccountSafeLock(BigDecimal balance)
    {
        this.balance = balance;
    }

    @Override
    public BigDecimal getBalance()
    {
        return balance;
    }

    @Override
    public void withdraw(BigDecimal amount)
    {
        synchronized (lock)
        {
            BigDecimal balance = this.getBalance();
            this.balance = balance.subtract(amount);
        }
    }

}
