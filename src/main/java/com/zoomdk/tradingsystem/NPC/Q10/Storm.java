package com.zoomdk.tradingsystem.NPC.Q10;

import com.zoomdk.tradingsystem.files.DataConfig;
import com.zoomdk.tradingsystem.files.NPCConfig;
import org.apache.commons.lang.WordUtils;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.player.PlayerInteractEntityEvent;
import org.bukkit.inventory.EquipmentSlot;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.lang.reflect.Array;
import java.util.*;

import static java.lang.Thread.sleep;

public class Storm implements Listener {

    static public Inventory inv;

    public String npc;
    public String npcKey;
    public Integer npcLevel;
    public Integer npcXP;


    public List<Integer> initializeItems() {
        List<Integer> farve = new ArrayList<>();
        farve = NPCConfig.get().getIntegerList("NPCs."+ npcKey + ".trades." + npcLevel.toString() + ".glasfarve");
        ConfigurationSection test = NPCConfig.get().getConfigurationSection("NPCs");
        String navn = test.getString(npcKey + ".trades" + ".0" + ".navn");
        inv.clear();
        Object[] navn1 = NPCConfig.get().getConfigurationSection("NPCs."+ npcKey + ".trades." + npcLevel.toString() + ".items").getKeys(false).toArray();
        for (Object key : navn1){

            int npcslot = NPCConfig.get().getInt("NPCs."+ npcKey + ".trades." + npcLevel.toString() + ".items." + key + ".slot");
            Material npcmaterial = Material.valueOf(NPCConfig.get().getString("NPCs."+ npcKey + ".trades." + npcLevel.toString() + ".items." + key + ".item"));
            int npcdata = NPCConfig.get().getInt("NPCs."+ npcKey + ".trades." + npcLevel.toString() + ".items." + key + ".data");
            String npcnavnet = NPCConfig.get().getString("NPCs."+ npcKey + ".trades." + npcLevel.toString() + ".items." + key + ".navn");
            npcnavnet = npcnavnet.replaceAll("&", "§");

            List<String> npclore = NPCConfig.get().getStringList("NPCs."+ npcKey + ".trades." + npcLevel.toString() + ".items." + key + ".lore");

            for (int i = 0; i < npclore.size(); ++i) {
                npclore.set(i, npclore.get(i).replaceAll("&", "§"));
            }
            for (int i = 0; i < npclore.size(); ++i) {
                if (npclore.get(i).contains("TradeItem:")) {
                    String npcloreItem =npclore.get(i).replaceAll("TradeItem:", "").replaceAll("[:0123456789]", "");
                    String npcloreAmount =npclore.get(i).replaceAll("[^0-9-]", "");
                    npcloreItem = npcloreItem.replaceAll("_", " ");
                    npcloreItem = WordUtils.capitalizeFully(npcloreItem);
                    if (!(npcloreAmount.equals(1))) {
                        if (npcloreItem.equals("Potato")) {
                            npcloreItem = "Potatoes";
                        }
                        else {
                            npcloreItem = npcloreItem + "s";
                        }

                    }
                    npclore.set(i, "§8[§c§l✘§8] §4" + npcloreAmount + " §7" + npcloreItem);
                }

            }

            String[] stringList = new String[npclore.size()];
            stringList = npclore.toArray(stringList);



            inv.addItem(INVcreateGuiItem(npcslot, npcmaterial , npcdata , 1, npcnavnet , stringList));
        }


        int i_1 = farve.get(0);
        int i_2 = farve.get(1);
        int i_3 = farve.get(2);


        inv.addItem(INVcreateGuiItem(0, Material.STAINED_GLASS_PANE, i_1, 1, "§a", "§a"));
        inv.addItem(INVcreateGuiItem(8, Material.STAINED_GLASS_PANE, i_1, 1, "§a", "§a"));
        inv.addItem(INVcreateGuiItem(18, Material.STAINED_GLASS_PANE, i_1, 1, "§a", "§a"));
        inv.addItem(INVcreateGuiItem(26, Material.STAINED_GLASS_PANE, i_1, 1, "§a", "§a"));

        inv.addItem(INVcreateGuiItem(1, Material.STAINED_GLASS_PANE, i_2, 1, "§a", "§a"));
        inv.addItem(INVcreateGuiItem(7, Material.STAINED_GLASS_PANE, i_2, 1, "§a", "§a"));
        inv.addItem(INVcreateGuiItem(9, Material.STAINED_GLASS_PANE, i_2, 1, "§a", "§a"));
        inv.addItem(INVcreateGuiItem(17, Material.STAINED_GLASS_PANE, i_2, 1, "§a", "§a"));
        inv.addItem(INVcreateGuiItem(19, Material.STAINED_GLASS_PANE, i_2, 1, "§a", "§a"));
        inv.addItem(INVcreateGuiItem(25, Material.STAINED_GLASS_PANE, i_2, 1, "§a", "§a"));

        inv.addItem(INVcreateGuiItem(2, Material.STAINED_GLASS_PANE, i_3, 1, "§a", "§a"));
        inv.addItem(INVcreateGuiItem(6, Material.STAINED_GLASS_PANE, i_3, 1, "§a", "§a"));
        inv.addItem(INVcreateGuiItem(20, Material.STAINED_GLASS_PANE, i_3, 1, "§a", "§a"));
        inv.addItem(INVcreateGuiItem(24, Material.STAINED_GLASS_PANE, i_3, 1, "§a", "§a"));

     /*   if (quest_level == 0) {
            if (npc.equals("§6test npc")) {
                inv.addItem(INVcreateGuiItem(13, Material.CHEST, 0, 1, "§6Storms Toolkit §8x1", "§7Kræver:", "§8[§c§l✘§8] §410 §7Potatoes", "§8[§c§l✘§8] §432 §7Iron Ingots", "§8[§c§l✘§8] §416 §7Sticks", "", "§7Giver:", "§6Storm §bLevel 1", "§6Storms Toolkit", "", "§e§lKLIK §7for at give items"));
                inv.addItem(INVcreateGuiItem(4, Material.BOOK, 0, 1, "§7Level: §8§l0 §7» §a§l1", "§bFremskridt: §aTal med de forskellige personer"));
                ItemStack itemStack = inv.getItem(4);
                ItemMeta testEnchantMeta = itemStack.getItemMeta();
                testEnchantMeta.addEnchant(Enchantment.SILK_TOUCH, 1, true);
                testEnchantMeta.addItemFlags(ItemFlag.HIDE_ENCHANTS);
                itemStack.setItemMeta(testEnchantMeta);
            }
        }
        if (quest_level == 1) {
            inv.addItem(INVcreateGuiItem(4, Material.BOOK, 0, 1, "§atest", String.valueOf(quest_level)));
        }*/
        return null;
    }
    protected ItemStack INVcreateGuiItem(final int slot, final Material material, final int data, final int amount, final String name, final String... lore) {
        final ItemStack item = new ItemStack(material, 1, (short) data);
        final ItemMeta meta = item.getItemMeta();
        meta.setDisplayName(name);
        meta.setLore(Arrays.asList(lore));
        item.setItemMeta(meta);
        inv.setItem(slot, item);
        int realam = amount - 1;
        item.setAmount(realam);
        return item;
    }



    @EventHandler
    public void onInventoryClick(InventoryClickEvent e) {
        Object[] fields = NPCConfig.get().getConfigurationSection("NPCs").getKeys(false).toArray();
        String navn = "";
        List<String> npcname = new ArrayList<>();
        for (Object key : fields) {
            navn = NPCConfig.get().getString("NPCs." + key + ".navn");
            navn = navn.replaceAll("\\[", "").replaceAll("\\]", "").replaceAll("&", "§");
            npcname.add(navn);
        }
        String invname = e.getInventory().getName();
        invname = invname.replace(" §8§l»", "");
        if (npcname.contains(invname)) {

            e.setCancelled(true);
            final Player p = (Player) e.getWhoClicked();
            final ItemStack clickedItem = e.getCurrentItem();
            if (clickedItem == null || clickedItem.getType() == Material.AIR) return;





            if (invname.equals("§6test npc"))
                if (e.getInventory().getItem(13).equals(clickedItem)) {
                    ItemMeta meta = inv.getItem(13).getItemMeta();
                    List<String> lore = meta.hasLore() ? meta.getLore() : new ArrayList<>();
                    int count = 0;
                    int totalcount = 32;
                    for (int i = 0; i < lore.size(); i++) {
                        if (lore.get(i).contains("§8[§a§l✓§8] §232 §7Iron Ingots")) {

                            for (ItemStack item : p.getInventory().getContents()) {
                                if (item == null)
                                    continue;
                                if (item.getType().equals(Material.IRON_INGOT)) {
                                    if (!(totalcount <= 0)) {
                                        if (item.getAmount() <= totalcount) {
                                            p.getInventory().removeItem(item);
                                            totalcount -= item.getAmount();
                                            count += item.getAmount();
                                            Bukkit.broadcastMessage("ccc" + totalcount);

                                        } else {
                                            int remove = item.getAmount() - totalcount;
                                            item.setAmount(remove);
                                            count += totalcount;
                                            totalcount = 0;
                                            Bukkit.broadcastMessage("cc" + totalcount);

                                        }
                                    }


                                }
                            }
                        }

                    }
                    p.getInventory().setContents(p.getInventory().getContents());
                }
        }
    }


    @EventHandler(priority = EventPriority.HIGH)
    public void onPlayerInteractEntityEvent(PlayerInteractEntityEvent event) {
        if (event.getHand() == EquipmentSlot.HAND) return;
        Object[] fields = NPCConfig.get().getConfigurationSection("NPCs").getKeys(false).toArray();
        String navn = "";
        List<String> npcname = new ArrayList<>();
        for (Object key : fields){
            navn = NPCConfig.get().getString("NPCs."+key+".navn");
            navn = navn.replaceAll("\\[", "").replaceAll("\\]","").replaceAll("&", "§");
            npcname.add(navn);
        }
        if (npcname.contains(event.getRightClicked().getName())) {
            inv = Bukkit.createInventory(null, 27, event.getRightClicked().getName() + " §8§l»");
            if (event.getHand() == EquipmentSlot.HAND) return;
            Player player = event.getPlayer();

            npc = event.getRightClicked().getName();


            Object[] test = NPCConfig.get().getConfigurationSection("NPCs").getKeys(false).toArray();
            String keyNavn = "";
            for (Object key : test){
                navn = NPCConfig.get().getString("NPCs."+key+".navn");
                navn = navn.replaceAll("\\[", "").replaceAll("\\]","").replaceAll("&", "§");
                if (navn.equals(npc)) {
                    keyNavn = key.toString();
                }
            }
            npcKey = keyNavn;
            ConfigurationSection npcData = DataConfig.get().getConfigurationSection(String.valueOf(player.getUniqueId()));
            if (npcData == null) {
                npcData = DataConfig.get().createSection(String.valueOf(player.getUniqueId()));
                Bukkit.broadcastMessage("test");
            }
                ConfigurationSection npcData1 = npcData.getConfigurationSection(npcKey);
                if (npcData1 != null) {
                    npcLevel = npcData.getInt("LEVEL");
                    npcXP = npcData.getInt("XP");
                }
                else {
                    npcData1 = npcData.createSection(npcKey);
                    npcData1.set("LEVEL", 0);
                    npcData1.set("XP", 0);
                    DataConfig.save();
                }


            player.openInventory(inv);
            initializeItems();

            if (!(npcLevel == -1)) {
                player.openInventory(inv);
                initializeItems();
                for (ItemStack item : inv.getContents()) {
                    if (item == null)
                        continue;
                    ItemMeta itemMeta = item.getItemMeta();
                    List<String> lore = itemMeta.hasLore() ? itemMeta.getLore() : new ArrayList<>();
                    for (int i = 0; i < lore.size(); i++) {
                        if (lore.get(i).contains("§8[§c§l✘§8]")) {
                            String loreMaterial = lore.get(i).replace("§8[§c§l✘§8] ", "").replaceAll(" §7", "").replaceAll("§4", "").replaceAll(" ", "_").replaceAll(" ", "_").replaceAll("[:0123456789]", "").replaceAll(".$", "");
                            String loreAntal = lore.get(i).replace("§8[§c§l✘§8] ", "").replaceAll(" §7", "").replaceAll("§4", "").replaceAll(" ", "_").replaceAll(" ", "_").replaceAll("[^0-9-]", "");
                            loreMaterial = loreMaterial.toUpperCase(Locale.ROOT);
                            if (loreMaterial.equals("POTATOE")) {
                                loreMaterial = "POTATO_ITEM";
                            }
                            for (ItemStack checkItem : player.getInventory().getContents()) {
                                int count = 0;
                                if (checkItem == null)
                                    continue;
                                if (checkItem.getType().equals(Material.valueOf(loreMaterial))) {
                                    count += checkItem.getAmount();
                                    if (count >= Integer.parseInt(loreAntal)) {
                                        lore.set(i, lore.get(i).replace("§8[§c§l✘§8] ", "§8[§a§l✓§8] ").replaceAll("§4", "§2"));

                                        itemMeta.setLore(lore);
                                        item.setItemMeta(itemMeta);
                                        player.updateInventory();
                                }
                                }
                            }


                        }
                    }
                    }





                }
        /*        if (!(quest_level == -1)) {
                    quest_level = -1;
                    if (event.getRightClicked().getName().equals("§6test npc")) {

                        player.sendMessage ("§6Storm §e> §7Dav mester!");
                        Bukkit.getScheduler ().runTaskLater (Bukkit.getPluginManager().getPlugin("TradingSystem"), () -> player.sendMessage ("§6Storm §e> §7Velkommen til Q10, her sker der ikke det store"), 40);
                        Bukkit.getScheduler ().runTaskLater (Bukkit.getPluginManager().getPlugin("TradingSystem"), () -> player.sendMessage ("§6Storm §e> §7Men jeg skal nok hjælpe dig op, tro på det mester"), 120);
                        Bukkit.getScheduler ().runTaskLater (Bukkit.getPluginManager().getPlugin("TradingSystem"), () -> player.sendMessage ("§6Storm §e> §7Der er en mine her over til §ahøjre"), 180);
                        Bukkit.getScheduler ().runTaskLater (Bukkit.getPluginManager().getPlugin("TradingSystem"), () -> player.sendMessage ("§6Storm §e> §7Skaf mig lidt §bKartofler, Iron Ingots og Sticks§7!"), 220);
                        Bukkit.getScheduler ().runTaskLater (Bukkit.getPluginManager().getPlugin("TradingSystem"), () -> player.sendMessage ("§6Storm §e> §7Hvis du er helt lost, mester, så brug §a/info"), 260);
                    }
                    Bukkit.getScheduler().runTaskLater(Bukkit.getPluginManager().getPlugin("TradingSystem"), () -> {
                        ConfigurationSection storm1 = DataConfig.get().createSection(String.valueOf(player.getUniqueId()));
                        storm1.set("Q10_Storm", 0);
                        quest_level = 0;
                        DataConfig.save();

                    }, 300);



                }
                else {
                    return;
                }

            if (!(quest_level == -1)) {

                player.openInventory(inv);
                initializeItems();
                ItemMeta itemMeta = inv.getItem(13).getItemMeta();
                int count1 = 0;
                int count2 = 0;
                int count3 = 0;
                for (ItemStack item : player.getInventory().getContents()) {
                    if (item == null)
                        continue;
                    if (item.getType().equals(Material.IRON_INGOT)) {
                        count1 += item.getAmount();
                        if (count1 >= 32) {
                            ItemMeta meta = inv.getItem(13).getItemMeta();
                            List<String> lore = meta.hasLore() ? meta.getLore() : new ArrayList<>();
                            for (int i = 0; i < lore.size(); i++) {
                                if (lore.get(i).contains("§8[§c§l✘§8] §432 §7Iron Ingots")){
                                    lore.set(i, "§8[§a§l✓§8] §232 §7Iron Ingots");
                                    itemMeta.setLore(lore);
                                    inv.getItem(13).setItemMeta(itemMeta);
                                }
                            }

                        }
                    }
                    if (item.getType().equals(Material.POTATO_ITEM)) {
                        count2 += item.getAmount();
                        if (count2 >= 10) {
                            ItemMeta meta = inv.getItem(13).getItemMeta();
                            List<String> lore = meta.hasLore() ? meta.getLore() : new ArrayList<>();
                            for (int i = 0; i < lore.size(); i++) {
                                if (lore.get(i).contains("§8[§c§l✘§8] §410 §7Potatoes")){
                                    lore.set(i, "§8[§a§l✓§8] §210 §7Potatoes");
                                    itemMeta.setLore(lore);
                                    inv.getItem(13).setItemMeta(itemMeta);
                                }
                            }

                        }
                    }
                    if (item.getType().equals(Material.STICK)) {
                        count3 += item.getAmount();
                        if (count3 >= 16) {
                            ItemMeta meta = inv.getItem(13).getItemMeta();
                            List<String> lore = meta.hasLore() ? meta.getLore() : new ArrayList<>();
                            for (int i = 0; i < lore.size(); i++) {
                                if (lore.get(i).contains("§8[§c§l✘§8] §416 §7Sticks")){
                                    lore.set(i, "§8[§a§l✓§8] §216 §7Sticks");
                                    itemMeta.setLore(lore);
                                    inv.getItem(13).setItemMeta(itemMeta);
                                }
                            }

                        }
                    }

                }
            }*/
        }


        }

}
