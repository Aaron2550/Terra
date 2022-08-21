package com.dfsek.terra.addons.commands.packs;

import cloud.commandframework.ArgumentDescription;
import cloud.commandframework.CommandManager;

import com.dfsek.terra.addons.manifest.api.MonadAddonInitializer;
import com.dfsek.terra.addons.manifest.api.monad.Do;
import com.dfsek.terra.addons.manifest.api.monad.Get;
import com.dfsek.terra.addons.manifest.api.monad.Init;
import com.dfsek.terra.api.util.function.monad.Monad;

import org.jetbrains.annotations.NotNull;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.dfsek.terra.api.command.CommandSender;
import com.dfsek.terra.api.command.arguments.RegistryArgument;
import com.dfsek.terra.api.event.events.platform.CommandRegistrationEvent;
import com.dfsek.terra.api.event.functional.FunctionalEventHandler;


public class PacksCommandAddon implements MonadAddonInitializer {
    private static final Logger logger = LoggerFactory.getLogger(PacksCommandAddon.class);
    
    @Override
    public @NotNull Monad<?, Init<?>> initialize() {
        return Do.with(
                Get.eventManager().map(eventManager -> eventManager.getHandler(FunctionalEventHandler.class)),
                Get.addon(),
                Get.platform(),
                ((functionalEventHandler, base, platform) -> Init.ofPure(
                        functionalEventHandler.register(base, CommandRegistrationEvent.class)
                                              .then(event -> {
                                                  CommandManager<CommandSender> manager = event.getCommandManager();
    
                                                  manager.command(
                                                                 manager.commandBuilder("packs", ArgumentDescription.of("List installed config packs"))
                                                                        .permission("terra.packs")
                                                                        .handler(context -> {
                                                                            StringBuilder packs = new StringBuilder("Installed packs:\n");
                                                                            platform.getConfigRegistry().forEach(pack -> packs.append(" - ")
                                                                                                                              .append(pack.getID())
                                                                                                                              .append('@')
                                                                                                                              .append(pack.getVersion().getFormatted()));
                                                                            context.getSender().sendMessage(packs.toString());
                                                                        })
                                                                 )
                                                         .command(
                                                                 manager.commandBuilder("packs")
                                                                        .literal("info", ArgumentDescription.of("Get information about a pack"))
                                                                        .permission("terra.packs.info")
                                                                        .argument(RegistryArgument.of("pack", platform.getConfigRegistry()))
                                                                        .handler(context -> {
                                                                            ConfigPack pack = context.get("pack");
                                                                            StringBuilder packInfo = new StringBuilder("Pack ").append(pack.getID()).append('\n');
                
                                                                            packInfo.append("Version: ").append(pack.getVersion().getFormatted()).append('\n');
                                                                            packInfo.append("Author: ").append(pack.getAuthor()).append('\n');
                
                                                                            packInfo.append("Addon Dependencies:\n");
                                                                            pack.addons().forEach((id, versions) -> packInfo
                                                                                    .append(" - ")
                                                                                    .append(id.getID())
                                                                                    .append('@')
                                                                                    .append(versions.getFormatted())
                                                                                    .append('\n'));
                                                                            context.getSender().sendMessage(packInfo.toString());
                                                                        }))
                                                         .command(
                                                                 manager.commandBuilder("packs")
                                                                        .literal("reload", ArgumentDescription.of("Reload config packs"))
                                                                        .permission("terra.packs.reload")
                                                                        .handler(context -> {
                                                                            context.getSender().sendMessage("Reloading Terra...");
                                                                            logger.info("Reloading Terra...");
                                                                            if(platform.reload()) {
                                                                                logger.info("Terra reloaded successfully.");
                                                                                context.getSender().sendMessage("Terra reloaded successfully.");
                                                                            } else {
                                                                                logger.error("Terra failed to reload.");
                                                                                context.getSender().sendMessage(
                                                                                        "Terra failed to reload. See logs for more information.");
                                                                            }
                                                                        }));
                                              })))
                      );
    }
}
