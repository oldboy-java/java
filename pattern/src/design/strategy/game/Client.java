package design.strategy.game;

/**
 * Created by Administrator on 2019-2-24.
 */
public class Client {

    public static void main(String[] args) {
        //创建匕首武器
        WeaponBehavior knifeWeapon = new KnifeBehavior();

        //创建角色
        Character king = new King(knifeWeapon);

        //执行战斗方法
        king.fight();
    }


}
