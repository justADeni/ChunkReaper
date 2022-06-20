package justadeni.chunkreaper;

import org.bukkit.block.CommandBlock;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;

import java.util.UUID;

public class ChunkCommand implements CommandExecutor {

    //private static FileConfiguration mainConfig = ChunkReaper.instance.getConfig();
    //private static FileConfiguration regionConfig = ChunkReaper.config.getData();
    private ChunkReaper chunkReaper = new ChunkReaper();
    private FileConfiguration mainConfig(){
        return chunkReaper.getConfig();
    }
    private FileConfiguration regionConfig(){
        return chunkReaper.config.getData();
    }
    private ChunkFile regionConfigData(){
        return chunkReaper.config;
    }
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (label.equalsIgnoreCase("chunkreaper")){
            if (sender instanceof Player)
                if (!sender.hasPermission("chunkreaper.admin"))
                    return false;

            if (args.length == 0)
                return false;
            if (args.length == 1)
                if (args[0].equalsIgnoreCase("reload")){
                    ChunkReaper.instance.reloadConfig();
                    regionConfigData().reloadData();
                }
            if (args.length == 2){
                if (args[0].equalsIgnoreCase("setradius") && isNumPos(args[1]) ){
                    mainConfig().set("IgnoreRadius", Integer.parseInt(args[1]));
                } else if (args[0].equalsIgnoreCase("region")){
                    if (args[1].equalsIgnoreCase("toggle")){
                        boolean negate = !mainConfig().getBoolean("Regions");
                        mainConfig().set("Regions", negate);
                    }
                }
            }
            if (args.length == 3)
                return false;
            if (args.length == 4)
                return false;
            if (args.length == 5){
                if (args[0].equalsIgnoreCase("region")) {
                    if (isNum(args[1]) && isNum(args[2]) && isNum(args[3]) && isNum(args[4])){

                        String path = UUID.randomUUID() + ".";
                        regionConfig().set(path + "x1", getNum(args[1]));
                        regionConfig().set(path + "z1", getNum(args[2]));
                        regionConfig().set(path + "x2", getNum(args[3]));
                        regionConfig().set(path + "z2", getNum(args[4]));
                    }
                }
            }
        }
        return false;
    }

    private static boolean isNumPos(String strNum) {
        if (strNum == null) {
            return false;
        }
        try {
            int d = Integer.parseInt(strNum);
            return d > 0;
        } catch (NumberFormatException nfe) {
            return false;
        }
        //return true;
    }

    private static boolean isNum(String strNum) {
        if (strNum == null) {
            return false;
        }
        try {
            int d = Integer.parseInt(strNum);
        } catch (NumberFormatException nfe) {
            return false;
        }
        return true;
    }

    private static int getNum(String strNum) {
        try {
            int d = Integer.parseInt(strNum);
        } catch (NumberFormatException nfe) {
            return 0;
        }
        return 0;
    }
}
