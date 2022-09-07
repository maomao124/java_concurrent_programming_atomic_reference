package mao.t1;

import java.math.BigDecimal;
import java.util.concurrent.atomic.AtomicReference;

/**
 * Project name(项目名称)：java并发编程_原子引用
 * Package(包名): mao.t1
 * Class(类名): DecimalAccountSafeCas
 * Author(作者）: mao
 * Author QQ：1296193245
 * GitHub：https://github.com/maomao124/
 * Date(创建日期)： 2022/9/7
 * Time(创建时间)： 12:40
 * Version(版本): 1.0
 * Description(描述)： 无
 */

public class DecimalAccountSafeCas implements DecimalAccount
{
    AtomicReference<BigDecimal> ref;

    public DecimalAccountSafeCas(BigDecimal balance)
    {
        ref = new AtomicReference<>(balance);
    }

    @Override
    public BigDecimal getBalance()
    {
        return ref.get();
    }

    @Override
    public void withdraw(BigDecimal amount)
    {
        while (true)
        {
            BigDecimal prev = ref.get();
            BigDecimal next = prev.subtract(amount);
            if (ref.compareAndSet(prev, next))
            {
                break;
            }
        }
    }
}
