package viceCity.models.players;

import viceCity.common.ExceptionMessages;
import viceCity.models.guns.Gun;
import viceCity.repositories.interfaces.GunRepository;
import viceCity.repositories.interfaces.Repository;

public abstract class BasePlayer implements Player{
    private String name;
    private int lifePoints;
    private Repository<Gun> gunRepository;

    public BasePlayer(String name, int lifePoints) {
        this.setName(name);
        this.setLifePoints(lifePoints);
        this.gunRepository = new GunRepository();
    }

    @Override
    public String getName() {
        return this.name;
    }

    private void setName(String name) {
        if (name==null||name.trim().isEmpty()){
            throw new NullPointerException(ExceptionMessages.PLAYER_NULL_USERNAME);
        }
        this.name = name;
    }

    @Override
    public int getLifePoints() {
        return this.lifePoints;
    }

    private void setLifePoints(int lifePoints) {
        if (lifePoints<0){
            throw new IllegalArgumentException(ExceptionMessages.PLAYER_LIFE_POINTS_LESS_THAN_ZERO);
        }
        this.lifePoints = lifePoints;
    }

    @Override
    public Repository<Gun> getGunRepository() {
        return this.gunRepository;
    }

    @Override
    public boolean isAlive() {
        if (this.lifePoints>0){
            return true;
        }
        return false;
    }

    @Override
    public void takeLifePoints(int points) {
        if (this.lifePoints-points<0){
            this.lifePoints = 0;
        }else if (this.lifePoints-points>=0){
            this.lifePoints = this.lifePoints-points;
        }
    }
}
