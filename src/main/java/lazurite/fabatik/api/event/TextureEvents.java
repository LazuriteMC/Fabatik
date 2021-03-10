package lazurite.fabatik.api.event;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.fabricmc.fabric.api.event.Event;
import net.fabricmc.fabric.api.event.EventFactory;
import net.minecraft.client.texture.AbstractTexture;
import net.minecraft.util.Identifier;

@Environment(EnvType.CLIENT)
public final class TextureEvents {

    private TextureEvents() { }

    public final static Event<TextureLoadCallback> LOAD = EventFactory.createArrayBacked(
            TextureLoadCallback.class,
            textureLoadCallbacks -> (identifier, abstractTexture) -> {
                for (TextureLoadCallback textureLoadCallback : textureLoadCallbacks) {
                    textureLoadCallback.onTextureLoad(identifier, abstractTexture);
                }
            }
    );

    public final static Event<TextureUnloadCallback> UNLOAD = EventFactory.createArrayBacked(
            TextureUnloadCallback.class,
            textureUnloadCallbacks -> (identifier, abstractTexture) -> {
                for (TextureUnloadCallback textureUnloadCallback : textureUnloadCallbacks) {
                    textureUnloadCallback.onTextureUnload(identifier, abstractTexture);
                }
            }
    );

    @FunctionalInterface
    public interface TextureLoadCallback {
        void onTextureLoad(Identifier identifier, AbstractTexture abstractTexture);
    }

    @FunctionalInterface
    public interface TextureUnloadCallback {
        void onTextureUnload(Identifier identifier, AbstractTexture abstractTexture);
    }

}
