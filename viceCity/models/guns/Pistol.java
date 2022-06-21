package viceCity.models.guns;

public class Pistol extends BaseGun{
    private static final int BULLETS_PER_BARREL = 10;
    private static final int TOTAL_BULLETS = 100;

    public Pistol(String name) {
        super(name, BULLETS_PER_BARREL, TOTAL_BULLETS);
    }

    @Override
    public int fire() {

        if (this.canFire()) {
            super.setBulletsPerBarrel(this.getBulletsPerBarrel() - 1);
        }
        if (!this.canFire()){
            this.setTotalBullets(this.getTotalBullets()-(this.getStartBullets()-this.getBulletsPerBarrel()));
            this.setBulletsPerBarrel(this.getStartBullets());
        }
        return 1;
    }
}
