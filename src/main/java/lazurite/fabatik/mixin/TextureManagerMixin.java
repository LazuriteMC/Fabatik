package lazurite.fabatik.mixin;

import lazurite.fabatik.api.event.TextureEvents;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.texture.AbstractTexture;
import net.minecraft.client.texture.TextureManager;
import net.minecraft.client.texture.TextureTickListener;
import net.minecraft.resource.ResourceReloadListener;
import net.minecraft.util.Identifier;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Environment(EnvType.CLIENT)
@Mixin(TextureManager.class)
public abstract class TextureManagerMixin implements ResourceReloadListener, TextureTickListener, AutoCloseable {

    @Inject(method = "registerTexture", at = @At("TAIL"))
    public void onTextureLoad(Identifier identifier, AbstractTexture abstractTexture, CallbackInfo ci) {
        TextureEvents.LOAD.invoker().onTextureLoad(identifier, abstractTexture);
    }

    @Inject(method = "method_30299", at = @At("TAIL"))
    private void onTextureUnload(Identifier identifier, AbstractTexture abstractTexture, CallbackInfo ci) {
        TextureEvents.UNLOAD.invoker().onTextureUnload(identifier, abstractTexture);
    }

}
