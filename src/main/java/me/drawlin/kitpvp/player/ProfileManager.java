package me.drawlin.kitpvp.player;

import com.google.common.collect.Maps;
import lombok.Getter;

import java.util.Map;
import java.util.UUID;

@Getter
public class ProfileManager {

    @Getter
    private final static Map<UUID, PlayerProfile> profileMap = Maps.newHashMap();

    public PlayerProfile createProfile(UUID uuid) {
        PlayerProfile profile = new PlayerProfile(uuid);
        profileMap.put(uuid, profile);
        return profile;
    }

}
