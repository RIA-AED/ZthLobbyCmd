package ink.magma.zthlobbycmd.command;

import com.velocitypowered.api.command.CommandSource;
import com.velocitypowered.api.command.SimpleCommand;
import com.velocitypowered.api.proxy.ConnectionRequestBuilder;
import com.velocitypowered.api.proxy.Player;
import ink.magma.zthlobbycmd.ZthLobbyCmd;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.format.NamedTextColor;

public class LobbyCommand implements SimpleCommand {
    @Override
    public void execute(Invocation invocation) {
        CommandSource source = invocation.source();

        if (source instanceof Player player) {
            if (source.hasPermission("zth.lobbycmd.use")) {
                ConnectionRequestBuilder connectionRequest = player.createConnectionRequest(ZthLobbyCmd.INSTANCE.lobbyServer);
                connectionRequest.connectWithIndication();
            } else {
                player.sendMessage(Component.text("缺少使用此指令的权限.").color(NamedTextColor.GRAY));
            }
        } else {
            source.sendMessage(Component.text("您必须是一个玩家!"));
        }
    }
}
