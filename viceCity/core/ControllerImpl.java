package viceCity.core.interfaces;

import viceCity.common.ConstantMessages;
import viceCity.models.guns.Gun;
import viceCity.models.guns.Pistol;
import viceCity.models.guns.Rifle;
import viceCity.models.neighbourhood.GangNeighbourhood;
import viceCity.models.neighbourhood.Neighbourhood;
import viceCity.models.players.CivilPlayer;
import viceCity.models.players.MainPlayer;
import viceCity.models.players.Player;
import viceCity.repositories.interfaces.GunRepository;
import viceCity.repositories.interfaces.Repository;

import java.util.ArrayDeque;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class ControllerImpl implements Controller {
    private Player player;
    private Map<String, Player> civil;
    private ArrayDeque<Gun> guns;
    private Neighbourhood neighbourhood;

    public ControllerImpl() {
        this.player = new MainPlayer();
        this.civil = new LinkedHashMap<>();
        this.guns = new ArrayDeque<>();
        this.neighbourhood = new GangNeighbourhood();
    }

    @Override
    public String addPlayer(String name) {
        Player playerCivil = new CivilPlayer(name);
        this.civil.putIfAbsent(name, playerCivil);
        return String.format(ConstantMessages.PLAYER_ADDED, name);
    }

    @Override
    public String addGun(String type, String name) {
        Gun gun;
        switch (type) {
            case "Pistol":
                gun = new Pistol(name);
                break;
            case "Rifle":
                gun = new Rifle(name);
                break;
            default:
                return ConstantMessages.GUN_TYPE_INVALID;
        }
        this.guns.add(gun);
        return String.format(ConstantMessages.GUN_ADDED, name, type);
    }

    @Override
    public String addGunToPlayer(String name) {
        Gun gun = this.guns.poll();
        if (gun==null) {
            return ConstantMessages.GUN_QUEUE_IS_EMPTY;
        }
        if (name.equals("Vercetti")) {
            player.getGunRepository().add(gun);
            return String.format(ConstantMessages.GUN_ADDED_TO_MAIN_PLAYER, gun.getName(), player.getName());
        }
        Player player = this.civil.get(name);
        if (player==null){
            return ConstantMessages.CIVIL_PLAYER_DOES_NOT_EXIST;
        }
        player.getGunRepository().add(gun);
        return String.format(ConstantMessages.GUN_ADDED_TO_CIVIL_PLAYER, gun.getName(), name);
    }

    @Override
    public String fight() {
        this.neighbourhood.action(player,this.civil.values());
        if (this.player.getLifePoints()==100 && this.civil.values().stream().allMatch(player1 -> player1.getLifePoints()==50)){
            return ConstantMessages.FIGHT_HOT_HAPPENED;
        }
        List<Player>deadPlayers = this.civil.values().stream().filter(player1 -> !player1.isAlive()).collect(Collectors.toList());
        StringBuilder builder = new StringBuilder();
        builder.append(ConstantMessages.FIGHT_HAPPENED)
                .append(System.lineSeparator())
                .append(String.format(ConstantMessages.MAIN_PLAYER_LIVE_POINTS_MESSAGE,this.player.getLifePoints()))
                .append(System.lineSeparator())
                .append(String.format(ConstantMessages.MAIN_PLAYER_KILLED_CIVIL_PLAYERS_MESSAGE,deadPlayers.size()))
                .append(System.lineSeparator())
                .append(String.format(ConstantMessages.CIVIL_PLAYERS_LEFT_MESSAGE,this.civil.size()-deadPlayers.size()));
        for (Player playerDead:deadPlayers) {
            civil.remove(playerDead.getName());
        }


        return builder.toString().trim();
    }
}
