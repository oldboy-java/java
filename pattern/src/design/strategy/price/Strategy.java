package design.strategy.price;

/**
 * Created by Administrator on 2019-2-24.
 * 定义策略
 */
public interface Strategy {

    /**
     * 策略算法
     * @param goodsPrice
     * @return
     */
    public float calPrice(float goodsPrice);
}
