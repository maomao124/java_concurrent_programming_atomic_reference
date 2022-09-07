package mao.t1;

import java.math.BigDecimal;

/**
 * Project name(项目名称)：java并发编程_原子引用
 * Package(包名): mao.t1
 * Class(类名): Test
 * Author(作者）: mao
 * Author QQ：1296193245
 * GitHub：https://github.com/maomao124/
 * Date(创建日期)： 2022/9/7
 * Time(创建时间)： 11:47
 * Version(版本): 1.0
 * Description(描述)： 无
 */

public class Test
{

    public static void main(String[] args)
    {
        DecimalAccount.start(new DecimalAccountUnsafe(new BigDecimal("10000")));
        DecimalAccount.start(new DecimalAccountSafeLock(new BigDecimal("10000")));
        DecimalAccount.start(new DecimalAccountSafeCas(new BigDecimal("10000")));
    }
}
