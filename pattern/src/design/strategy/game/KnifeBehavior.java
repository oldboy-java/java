package design.strategy.game;

/**
 * Created by Administrator on 2019-2-24.
 * 定义使用匕首武器的策略
 */
public class KnifeBehavior implements  WeaponBehavior {

    @Override
    public void useWeapon() {
        System.out.println("玩家您好，您正在使用匕首");
    }
}
