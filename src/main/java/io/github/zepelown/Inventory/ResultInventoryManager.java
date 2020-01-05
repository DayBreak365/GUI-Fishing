package io.github.zepelown.Inventory;

import fr.minuskube.inv.ClickableItem;
import fr.minuskube.inv.content.InventoryContents;
import io.github.zepelown.ItemManager.ItemManager;
import io.github.zepelown.event.FishingEvent;
import org.bukkit.Material;
import org.bukkit.entity.Item;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

//출처 : https://github.com/MinusKube/SmartInvs
public class ResultInventoryManager implements fr.minuskube.inv.content.InventoryProvider {

    FishingEvent fe = new FishingEvent();

    @Override
    public void init(Player player, InventoryContents contents) {
        contents.set(0,0, ClickableItem.empty(new ItemStack(Material.OAK_SIGN)));
        contents.set(0,4, ClickableItem.empty(fe.get_Hooked_fish(player)));

        contents.set(1,3,ClickableItem.of(ItemManager.GreenWool, e->{
            if(e.isLeftClick()) {
                if(e.getInventory().firstEmpty() == -1)
                    player.sendMessage("인벤토리가 꽉찼습니다. 인벤토리를 비워주세요.");
                else {
                    player.getInventory().addItem(fe.get_Hooked_fish(player));
                    player.sendMessage(fe.get_Hooked_fish(player).getType().toString() +"를 잡았습니다!");
                    fe.remove_Hooked_fish(player);
                    player.closeInventory();
                }

            }
        }));
        contents.set(1, 5, ClickableItem.of(ItemManager.RedWool, e -> {
            if(e.isLeftClick()) {
                fe.remove_Hooked_fish(player);
                player.sendMessage("잡은 아이템을 버렸습니다!");
                player.closeInventory();
            }
        }));
        contents.set(0,3,ClickableItem.empty(ItemManager.White_Stained_Glass_Pane));
        contents.set(0,5,ClickableItem.empty(ItemManager.White_Stained_Glass_Pane));
        contents.set(1,4,ClickableItem.empty(ItemManager.White_Stained_Glass_Pane));
    }

    @Override
    public void update(Player player, InventoryContents contents) {
    }
}
