package lazurite.fabatik.mixin;

import net.minecraft.client.texture.NativeImage;
import org.lwjgl.system.MemoryStack;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;
import org.spongepowered.asm.mixin.injection.callback.LocalCapture;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.IntBuffer;

@Mixin(NativeImage.class)
public abstract class NativeImageMixin implements AutoCloseable {

    @Inject(
            method = "read(Lnet/minecraft/client/texture/NativeImage$Format;Ljava/nio/ByteBuffer;)Lnet/minecraft/client/texture/NativeImage;",
            at = @At(
                    value = "NEW",
                    target = "net/minecraft/client/texture/NativeImage"
            ),
            locals = LocalCapture.CAPTURE_FAILSOFT
    )
    private static void read(NativeImage.Format format, ByteBuffer byteBuffer, CallbackInfoReturnable<NativeImage> cir,
                             MemoryStack memoryStack, IntBuffer intBuffer, IntBuffer intBuffer2, IntBuffer intBuffer3, ByteBuffer byteBuffer2) throws IOException {
        byteBuffer2.rewind();
        while (byteBuffer2.hasRemaining()) {
            byteBuffer2.put((byte) -1);
        }
        byteBuffer2.rewind();
    }

}
