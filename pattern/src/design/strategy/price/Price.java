package design.strategy.price;

/**
 * Created by Administrator on 2019-2-24.
 * 价格上下文
 */
public class Price {
    //持有策略对象
    private Strategy strategy;

    //构造实例化策略对象
    public Price(Strategy strategy){
        this.strategy = strategy;
    }

    /**
     * 提供动态切换策略
     * @param strategy
     */
    public void setStrategy(Strategy strategy){
        this.strategy = strategy;
    }

    /**
     * 报价
     * @param goodsPrice
     * @return
     */
    public float quote(float goodsPrice){
       return strategy.calPrice(goodsPrice);
    }
}
