package com.github.klyser8.eggstra.advancement;

import com.github.klyser8.eggstra.Eggstra;
import com.google.gson.JsonObject;
import net.minecraft.advancements.critereon.*;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.item.ItemStack;

public class CrackGoldenEggTrigger extends SimpleCriterionTrigger<CrackGoldenEggTrigger.Triggers> {

    static final ResourceLocation ID = new ResourceLocation(Eggstra.MOD_ID, "crack_golden_egg");

    @Override
    public ResourceLocation getId() {
        return ID;
    }
    @Override
    protected Triggers createInstance(JsonObject json, EntityPredicate.Composite player, DeserializationContext context) {
        EntityPredicate entityPredicate = EntityPredicate.fromJson(json.get("hit_entity"));
        ItemPredicate itemPredicate = ItemPredicate.fromJson(json.get("found_item"));
        return new Triggers(player, itemPredicate, entityPredicate);
    }

    public void trigger(ServerPlayer player, ItemStack foundItem, Entity hitEntity) {
        this.trigger(player, triggers -> triggers.matches(player, foundItem, hitEntity));
    }

    public static class Triggers extends AbstractCriterionTriggerInstance {

        private final ItemPredicate item;
        private final EntityPredicate hitEntity;

        public Triggers(EntityPredicate.Composite playerComp, ItemPredicate itemPredicate, EntityPredicate hitEntityPredicate) {
            super(CrackGoldenEggTrigger.ID, playerComp);
            this.item = itemPredicate;
            this.hitEntity = hitEntityPredicate;
        }

        public boolean matches(ServerPlayer player, ItemStack stack, Entity entity) {
            return hitEntity.matches(player, entity) && item.matches(stack);
        }

        public JsonObject toJson(SerializationContext context) {
            JsonObject jsonObject = super.serializeToJson(context);
            jsonObject.add("found_item", this.item.serializeToJson());
            jsonObject.add("hit_entity", this.hitEntity.serializeToJson());
            return jsonObject;
        }
    }
}
