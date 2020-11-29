package design.strategy.price;

/**
 * Created by Administrator on 2019-2-24.
 * 银牌会员策略
 */
public class SilverMemberStrategy implements Strategy {
    @Override
    public float calPrice(float goodsPrice) {
        System.out.println("您是银牌会员，享受75折，您的价格是："+goodsPrice*0.75);
        return goodsPrice*0.75f;
    }
}
