package design.strategy.price;

/**
 * Created by Administrator on 2019-2-24.
 */
public class Client {

    public static void main(String[] args) {
        //创建普通会员策略
        Strategy strategyA = new RegularMemberStrategy();

        //创建上下文对象
        Price price = new Price(strategyA);

        //执行上下文的报价功能
        price.quote(100);

        //创建银牌会员策略
        Strategy strategyB = new SilverMemberStrategy();

        //动态设置银牌会员策略
        price.setStrategy(strategyB);

        //执行上下文的报价功能
        price.quote(100);
    }
}
