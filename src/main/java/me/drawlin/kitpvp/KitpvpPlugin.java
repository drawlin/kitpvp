package me.drawlin.kitpvp;

import io.github.thatkawaiisam.assemble.Assemble;
import io.github.thatkawaiisam.assemble.AssembleStyle;
import lombok.Getter;
import me.drawlin.kitpvp.command.CommandFramework;
import me.drawlin.kitpvp.command.impl.KitCommand;
import me.drawlin.kitpvp.command.impl.RemoveKitCommand;
import me.drawlin.kitpvp.kit.KitManager;
import me.drawlin.kitpvp.listeners.KitListener;
import me.drawlin.kitpvp.listeners.PlayerListener;
import me.drawlin.kitpvp.player.ProfileManager;
import me.drawlin.kitpvp.scoreboard.ScoreboardAdapter;
import me.drawlin.kitpvp.storage.MongoStorage;
import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

@Getter
public final class KitpvpPlugin extends JavaPlugin {

    @Getter
    private static KitpvpPlugin plugin;

    private ProfileManager profileManager;
    private KitManager kitManager;
    private MongoStorage mongoStorage;
    private CommandFramework commandFramework;

    @Override
    public void onEnable() {
        plugin = this;

        this.profileManager = new ProfileManager();
        this.kitManager = new KitManager();
//        this.mongoStorage = new MongoStorage(false, "127.0.0.1", 27017, "kitpvp");
        this.commandFramework = new CommandFramework(this);
        this.registerCommands();
        this.registerListeners();
        this.loadScoreboard();

    }

    @Override
    public void onDisable() {
        plugin = null;
    }

    private void registerCommands() {
        this.commandFramework.registerCommands(new KitCommand());
        this.commandFramework.registerCommands(new RemoveKitCommand());
    }

    private void registerListeners() {
        Bukkit.getServer().getPluginManager().registerEvents(new PlayerListener(), this);
        Bukkit.getServer().getPluginManager().registerEvents(new KitListener(), this);
    }

    private void loadScoreboard() {
        Assemble assemble = new Assemble(this, new ScoreboardAdapter());
        assemble.setTicks(2);
        assemble.setAssembleStyle(AssembleStyle.KOHI);
    }
}
