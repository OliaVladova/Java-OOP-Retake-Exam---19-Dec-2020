package viceCity.models.guns;

import viceCity.common.ExceptionMessages;

public abstract class BaseGun implements Gun {
    private String name;
    private int bulletsPerBarrel;
    private int totalBullets;
    private boolean canFire;
    private int startBullets;

    public BaseGun(String name, int bulletsPerBarrel, int totalBullets) {
        this.setName(name);
        this.setBulletsPerBarrel(bulletsPerBarrel);
        this.setTotalBullets(totalBullets);

    }

    @Override
    public String getName() {
        return this.name;
    }

    private void setName(String name) {
        if (name == null || name.trim().isEmpty()) {
            throw new NullPointerException(ExceptionMessages.NAME_NULL);
        }
        this.name = name;
    }

    @Override
    public int getBulletsPerBarrel() {
        return this.bulletsPerBarrel;
    }

    protected void setBulletsPerBarrel(int bulletsPerBarrel) {
        if (bulletsPerBarrel < 0) {
            throw new IllegalArgumentException(ExceptionMessages.BULLETS_LESS_THAN_ZERO);
        }
        this.bulletsPerBarrel = bulletsPerBarrel;
        this.startBullets = bulletsPerBarrel;
    }

    @Override
    public int getTotalBullets() {
        return this.totalBullets;
    }

    protected void setTotalBullets(int totalBullets) {
        if (totalBullets < 0) {
            throw new IllegalArgumentException(ExceptionMessages.TOTAL_BULLETS_LESS_THAN_ZERO);
        }
        this.totalBullets = totalBullets;
    }

    public boolean canFire() {
        return this.canFire;
    }

    public void setCanFire() {
        if (this.totalBullets>0) {
            if (this instanceof Pistol) {
                if (this.bulletsPerBarrel == 0) {
                    this.canFire = false;
                }
                this.canFire = true;
            } else if (this instanceof Rifle) {
                if (this.bulletsPerBarrel < 5) {
                    this.canFire = false;
                }
                this.canFire = true;
            }
        }this.canFire = false;
    }

    @Override
    public abstract int fire();

    public int getStartBullets() {
        return this.startBullets;
    }
}
