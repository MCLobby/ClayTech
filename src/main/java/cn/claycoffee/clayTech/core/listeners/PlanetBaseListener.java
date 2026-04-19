package cn.claycoffee.clayTech.core.listeners;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.PlayerInventory;
import org.jetbrains.annotations.NotNull;

import com.xzavier0722.mc.plugin.slimefun4.storage.util.StorageCacheUtils;

import cn.claycoffee.clayTech.ClayTech;
import cn.claycoffee.clayTech.ClayTechData;
import cn.claycoffee.clayTech.ClayTechItems;
import cn.claycoffee.clayTech.api.ClayTechManager;
import cn.claycoffee.clayTech.api.objects.Planet;
import cn.claycoffee.clayTech.core.managers.ConfigManager;
import cn.claycoffee.clayTech.utils.Lang;
import cn.claycoffee.clayTech.utils.PlanetUtil;
import cn.claycoffee.clayTech.utils.RocketUtil;
import io.github.thebusybiscuit.slimefun4.api.items.SlimefunItem;
import io.github.thebusybiscuit.slimefun4.utils.SlimefunUtils;

public class PlanetBaseListener implements Listener {
    @EventHandler(priority = EventPriority.HIGHEST)
    public void BlockPlaceEvent(@NotNull BlockPlaceEvent e) {
        if (SlimefunUtils.isItemSimilar(e.getPlayer().getInventory().getItemInMainHand(),
                ClayTechItems.PLANET_BASE_SIGNER, true)) {
            Planet p = PlanetUtil.getPlanet(e.getBlock().getWorld());
            if (p != null) {
                ConfigManager planetsData = ClayTech.getPlanetDataManager();
                FileConfiguration pd = planetsData.getConfig();
                if (!pd.getBoolean(e.getPlayer().getName() + "." + e.getPlayer().getWorld().getName() + ".base")) {
                    pd.set(e.getPlayer().getName() + "." + e.getPlayer().getWorld().getName() + ".base", true);
                    pd.set(e.getPlayer().getName() + "." + e.getPlayer().getWorld().getName() + ".baseX",
                            e.getBlock().getX());
                    pd.set(e.getPlayer().getName() + "." + e.getPlayer().getWorld().getName() + ".baseY",
                            e.getBlock().getY() + 1);
                    pd.set(e.getPlayer().getName() + "." + e.getPlayer().getWorld().getName() + ".baseZ",
                            e.getBlock().getZ());
                    try {
                        pd.save(planetsData.getFile());
                    } catch (Exception ex) {
                        ex.printStackTrace();
                        return;
                    }
                    e.getPlayer().sendMessage(Lang.readGeneralText("BaseCompleted"));
                    return;
                } else {
                    e.getPlayer().sendMessage(Lang.readGeneralText("BaseExists"));
                    e.setCancelled(true);
                    return;
                }
            } else {
                e.getPlayer().sendMessage(Lang.readGeneralText("NotInPlanet"));
                e.setCancelled(true);
                return;
            }
        }
    }

    @EventHandler(priority = EventPriority.HIGHEST)
    public void BlockBreakEvent(@NotNull BlockBreakEvent e) {
        SlimefunItem item = StorageCacheUtils.getSfItem(e.getBlock().getLocation());
        if (item != null) {
            if (item.getId().equals("PLANET_BASE_SIGNER")) {
                ConfigManager planetsData = ClayTech.getPlanetDataManager();
                FileConfiguration pd = planetsData.getConfig();
                int baseX = pd.getInt(e.getPlayer().getName() + "." + e.getPlayer().getWorld().getName() + ".baseX");
                int baseY = pd.getInt(e.getPlayer().getName() + "." + e.getPlayer().getWorld().getName() + ".baseY");
                int baseZ = pd.getInt(e.getPlayer().getName() + "." + e.getPlayer().getWorld().getName() + ".baseZ");
                boolean hasBase = pd
                        .getBoolean(e.getPlayer().getName() + "." + e.getPlayer().getWorld().getName() + ".base");
                if (hasBase) {
                    if (baseX == e.getBlock().getX() && baseY == e.getBlock().getY() + 1
                            && baseZ == e.getBlock().getZ()) {
                        pd.set(e.getPlayer().getName() + "." + e.getPlayer().getWorld().getName() + ".base", false);
                        pd.set(e.getPlayer().getName() + "." + e.getPlayer().getWorld().getName() + ".baseX", null);
                        pd.set(e.getPlayer().getName() + "." + e.getPlayer().getWorld().getName() + ".baseY", null);
                        pd.set(e.getPlayer().getName() + "." + e.getPlayer().getWorld().getName() + ".baseZ", null);
                        try {
                            pd.save(planetsData.getFile());
                        } catch (Exception ex) {
                            ex.printStackTrace();
                            return;
                        }
                        e.getPlayer().sendMessage(Lang.readGeneralText("BaseDestroyed"));
                    } else {
                        e.getPlayer().sendMessage(Lang.readGeneralText("NotYourBase"));
                    }
                }
            }
        }
    }

    @EventHandler(priority = EventPriority.HIGHEST)
    public void PlayerInteractEvent(@NotNull PlayerInteractEvent e) {
        if (e.getAction() == Action.RIGHT_CLICK_AIR) {
            if (e.hasItem()) {
                if (ClayTechManager.isOxygenDistributer(e.getItem())) {
                    PlayerInventory piv = e.getPlayer().getInventory();
                    if (ClayTechManager.isSpaceSuit(piv.getHelmet()) && ClayTechManager.isSpaceSuit(piv.getChestplate())
                            && ClayTechManager.isSpaceSuit(piv.getLeggings())
                            && ClayTechManager.isSpaceSuit(piv.getBoots())) {
                        if (RocketUtil.getOxygen(e.getItem()) >= 4) {
                            List<String> okPart = new ArrayList<>();
                            if (RocketUtil.getMaxOxygen(piv.getHelmet()) > RocketUtil.getOxygen(piv.getHelmet())) {
                                okPart.add("HELMET");
                            }
                            if (RocketUtil.getMaxOxygen(piv.getChestplate()) > RocketUtil
                                    .getOxygen(piv.getChestplate())) {
                                okPart.add("CHESTPLATE");
                            }
                            if (RocketUtil.getMaxOxygen(piv.getLeggings()) > RocketUtil
                                    .getOxygen(piv.getLeggings())) {
                                okPart.add("LEGGINGS");
                            }
                            if (RocketUtil.getMaxOxygen(piv.getBoots()) > RocketUtil.getOxygen(piv.getBoots())) {
                                okPart.add("BOOTS");
                            }
                            int totalOxygen = RocketUtil.getOxygen(e.getItem());
                            if (okPart.isEmpty()) {
                                e.getPlayer().sendMessage(Lang.readGeneralText("SPACESUIT_DISTRIBUTER_FULL"));
                                return;
                            }
                            int takeOxygen = totalOxygen / okPart.size();
                            for (String eachPart : okPart) {
                                totalOxygen -= takeOxygen;
                                int curOxg = 0;
                                if (eachPart.equalsIgnoreCase("HELMET")) {
                                    curOxg = RocketUtil.getOxygen(piv.getHelmet());
                                    if (curOxg + takeOxygen > RocketUtil.getMaxOxygen(piv.getHelmet())) {
                                        totalOxygen += curOxg + takeOxygen - RocketUtil.getMaxOxygen(piv.getHelmet());
                                        RocketUtil.setOxygen(piv.getHelmet(),
                                                RocketUtil.getMaxOxygen(piv.getHelmet()));

                                    } else {
                                        RocketUtil.setOxygen(piv.getHelmet(),
                                                RocketUtil.getOxygen(piv.getHelmet()) + takeOxygen);
                                    }
                                }
                                if (eachPart.equalsIgnoreCase("CHESTPLATE")) {
                                    curOxg = RocketUtil.getOxygen(piv.getChestplate());
                                    if (curOxg + takeOxygen > RocketUtil.getMaxOxygen(piv.getChestplate())) {
                                        totalOxygen += curOxg + takeOxygen
                                                - RocketUtil.getMaxOxygen(piv.getChestplate());
                                        RocketUtil.setOxygen(piv.getChestplate(),
                                                RocketUtil.getMaxOxygen(piv.getChestplate()));

                                    } else {
                                        RocketUtil.setOxygen(piv.getChestplate(),
                                                RocketUtil.getOxygen(piv.getChestplate()) + takeOxygen);
                                    }
                                }
                                if (eachPart.equalsIgnoreCase("LEGGINGS")) {
                                    curOxg = RocketUtil.getOxygen(piv.getLeggings());
                                    if (curOxg + takeOxygen > RocketUtil.getMaxOxygen(piv.getLeggings())) {
                                        totalOxygen += curOxg + takeOxygen
                                                - RocketUtil.getMaxOxygen(piv.getLeggings());
                                        RocketUtil.setOxygen(piv.getLeggings(),
                                                RocketUtil.getMaxOxygen(piv.getLeggings()));

                                    } else {
                                        RocketUtil.setOxygen(piv.getLeggings(),
                                                RocketUtil.getOxygen(piv.getLeggings()) + takeOxygen);
                                    }
                                }
                                if (eachPart.equalsIgnoreCase("BOOTS")) {
                                    curOxg = RocketUtil.getOxygen(piv.getBoots());
                                    if (curOxg + takeOxygen > RocketUtil.getMaxOxygen(piv.getBoots())) {
                                        totalOxygen += curOxg + takeOxygen - RocketUtil.getMaxOxygen(piv.getBoots());
                                        RocketUtil.setOxygen(piv.getBoots(), RocketUtil.getMaxOxygen(piv.getBoots()));

                                    } else {
                                        RocketUtil.setOxygen(piv.getBoots(),
                                                RocketUtil.getOxygen(piv.getBoots()) + takeOxygen);
                                    }
                                }
                            }
                            RocketUtil.setOxygen(e.getItem(), totalOxygen);
                            e.getPlayer().sendMessage(Lang.readGeneralText("SPACESUIT_DISTRIBUTER_OK"));

                        } else {
                            e.getPlayer().sendMessage(Lang.readGeneralText("SPACESUIT_4"));
                        }
                    } else {
                        e.getPlayer().sendMessage(Lang.readGeneralText("SPACESUIT_NOT_OK"));
                    }
                }
            }
        }
    }

    @EventHandler
    public void InventoryClickEvent(@NotNull InventoryClickEvent e) {
        if (e.getView().getTitle().equalsIgnoreCase(Lang.readMachinesText("CLAY_ROCKET_FUEL_INJECTOR"))
                && e.getSlot() == 20) {
            if (ClayTechData.RunningInjectors.get(e.getInventory()) != null) {
                e.setCancelled(true);
            }
        }
        if (e.getView().getTitle().equalsIgnoreCase(Lang.readMachinesText("CLAY_SPACESUIT_OXYGEN_INJECTOR"))
                && e.getSlot() == 22) {
            if (ClayTechData.RunningInjectorsOxygen.get(e.getInventory()) != null) {
                e.setCancelled(true);
            }
        }
    }
}
