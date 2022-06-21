package viceCity.models.guns;

public class Rifle extends BaseGun {
    private static final int BULLETS_PER_BARREL = 50;
    private static final int TOTAL_BULLETS = 500;

    public Rifle(String name) {
        super(name, BULLETS_PER_BARREL, TOTAL_BULLETS);
    }

    @Override
    public int fire() {
        if (this.canFire()) {
            super.setBulletsPerBarrel(this.getBulletsPerBarrel() - 5);
        }
        if (!this.canFire()){
            this.setTotalBullets(this.getTotalBullets()-(this.getStartBullets()-this.getBulletsPerBarrel()));
            this.setBulletsPerBarrel(this.getStartBullets());
        }
        return 5;
    }
}
