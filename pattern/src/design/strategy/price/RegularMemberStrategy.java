package design.strategy.price;

/**
 * Created by Administrator on 2019-2-24.
 * 普通会员策略
 */
public class RegularMemberStrategy implements  Strategy {

    public float calPrice(float goodsPrice) {
        System.out.println("您是普通会员不享受折扣，实际价格："+goodsPrice);
        return goodsPrice;
    }
}
