package me.drawlin.kitpvp.player;

import lombok.Getter;
import lombok.Setter;
import me.drawlin.kitpvp.kit.Kit;

import java.util.UUID;
import java.util.concurrent.CompletableFuture;

@Getter
@Setter
public class PlayerProfile {

    private UUID uuid;
    private int kills, deaths, killStreak;
    private Kit kit;
    private long attack;

    public PlayerProfile(UUID uuid) {
        this.uuid = uuid;
    }

    public double getKDR() {
        // checks if the kills are zero, because zero cannot be divided. if deaths are zero then the number of kills is the KDR.
        return (kills == 0 ? 0 : deaths == 0 ? kills : Math.round((double) kills / deaths));
    }

    public CompletableFuture<PlayerProfile> loadProfile() {
        return CompletableFuture.supplyAsync(() -> {
            // TODO: set values from document\
           return PlayerProfile.this;
        });
    }

    public CompletableFuture<?> saveProfile() {
        return CompletableFuture.runAsync(() -> {
           // TODO: insert document inside of collection
        });
    }

    public boolean inCombat() {
        return attack > System.currentTimeMillis();
    }

    public void setAttack(long time) {
        this.attack = time + 15000;
    }

}
