package design.strategy.game;

/**
 * Created by Administrator on 2019-2-24.
 * 定义角色上下文
 */
public abstract  class Character {
    //持有策略对象
     WeaponBehavior weapon;

    /**
     * 动态切换武器
     * @param weapon
     */
    public void setWeapon(WeaponBehavior weapon){
        this.weapon = weapon;
    }

    /**
     * 定义上下文对外暴露的方法
     */
    public  void fight(){
        weapon.useWeapon();
    }

    public abstract void  display();

}
