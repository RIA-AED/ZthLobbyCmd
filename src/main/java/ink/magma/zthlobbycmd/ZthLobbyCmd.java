package ink.magma.zthlobbycmd;

import com.google.inject.Inject;
import com.velocitypowered.api.event.Subscribe;
import com.velocitypowered.api.event.proxy.ProxyInitializeEvent;
import com.velocitypowered.api.plugin.Plugin;
import com.velocitypowered.api.proxy.ProxyServer;
import com.velocitypowered.api.proxy.server.RegisteredServer;
import ink.magma.zthlobbycmd.command.LobbyCommand;
import org.slf4j.Logger;

import java.util.Optional;

@Plugin(
        id = "zthlobbycmd",
        name = "ZthLobbyCmd",
        authors = "MagmaBlock",
        description = "提供 /lobby /hub /cloud /云端 指令.",
        version = BuildConstants.VERSION
)
public class ZthLobbyCmd {

    public final ProxyServer proxyServer;
    public final Logger logger;
    public static ZthLobbyCmd INSTANCE;
    public RegisteredServer lobbyServer;


    @Inject
    public ZthLobbyCmd(ProxyServer proxyServer, Logger logger) {
        this.proxyServer = proxyServer;
        this.logger = logger;
        INSTANCE = this;

        logger.info("plugin loaded.");
    }

    @Subscribe
    public void onProxyInitialization(ProxyInitializeEvent event) {
        Optional<RegisteredServer> gettingLobby = proxyServer.getServer("lobby");
        if (gettingLobby.isPresent()) {
            logger.info("成功获取到 lobby 服务器实例.");
            lobbyServer = gettingLobby.get();
            // register command...
            LobbyCommand lobbyCommand = new LobbyCommand();
            proxyServer.getCommandManager().register("lobby", lobbyCommand);
            proxyServer.getCommandManager().register("hub", lobbyCommand);
            proxyServer.getCommandManager().register("cloud", lobbyCommand);
            proxyServer.getCommandManager().register("云端", lobbyCommand);
        } else {
            logger.error("lobby 服务器实例获取失败! 插件将不会生效.");
        }
    }
}
