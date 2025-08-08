package fr.example.bridgeesgui;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.command.ConsoleCommandSender;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

public class BridgeAdd extends JavaPlugin {

    @Override
    public void onEnable() {
        getLogger().info("BridgeESGUI est activé !");
    }

    @Override
    public void onDisable() {
        getLogger().info("BridgeESGUI est désactivé !");
    }

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        if (cmd.getName().equalsIgnoreCase("bridgeadd")) {
            if (args.length < 4) {
                sender.sendMessage("§cUsage: /bridgeadd <player> <section> <buy> <sell>");
                return true;
            }

            String playerName = args[0];
            String section = args[1];
            String buy = args[2];
            String sell = args[3];

            Player target = Bukkit.getPlayerExact(playerName);
            if (target == null) {
                sender.sendMessage("§cJoueur introuvable.");
                return true;
            }

            if (target.getInventory().getItemInMainHand() == null || target.getInventory().getItemInMainHand().getType().isAir()) {
                sender.sendMessage("§cLe joueur doit tenir l'item moddé en main.");
                target.sendMessage("§eVous devez tenir l'item moddé en main avant que l'admin utilise /bridgeadd.");
                return true;
            }

            String esCommand = String.format("editshop addhanditem %s %s %s", section, buy, sell);
            ConsoleCommandSender console = Bukkit.getConsoleSender();
            Bukkit.dispatchCommand(console, esCommand);

            sender.sendMessage("§aCommande envoyée à EconomyShopGUI : §e" + esCommand);
            return true;
        }
        return false;
    }
}
