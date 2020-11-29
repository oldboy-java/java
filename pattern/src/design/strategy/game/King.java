package design.strategy.game;

import design.strategy.price.Strategy;

/**
 * Created by Administrator on 2019-2-24.
 * 国王角色
 */
public class King extends  Character {

    public King(WeaponBehavior weapon){
        this.weapon = weapon;
    }

    @Override
    public void display() {
        //这里是国王的外貌特征
    }
}
