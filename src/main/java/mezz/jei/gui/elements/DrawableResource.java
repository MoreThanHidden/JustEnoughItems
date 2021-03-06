package mezz.jei.gui.elements;

import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.BufferBuilder;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.vertex.DefaultVertexFormats;
import net.minecraft.util.ResourceLocation;

import mezz.jei.api.gui.drawable.IDrawableStatic;

public class DrawableResource implements IDrawableStatic {

	private final ResourceLocation resourceLocation;
	private final int textureWidth;
	private final int textureHeight;

	private final int u;
	private final int v;
	private final int width;
	private final int height;
	private final int paddingTop;
	private final int paddingBottom;
	private final int paddingLeft;
	private final int paddingRight;

	public DrawableResource(ResourceLocation resourceLocation, int u, int v, int width, int height, int paddingTop, int paddingBottom, int paddingLeft, int paddingRight, int textureWidth, int textureHeight) {
		this.resourceLocation = resourceLocation;
		this.textureWidth = textureWidth;
		this.textureHeight = textureHeight;

		this.u = u;
		this.v = v;
		this.width = width;
		this.height = height;

		this.paddingTop = paddingTop;
		this.paddingBottom = paddingBottom;
		this.paddingLeft = paddingLeft;
		this.paddingRight = paddingRight;
	}

	@Override
	public int getWidth() {
		return width + paddingLeft + paddingRight;
	}

	@Override
	public int getHeight() {
		return height + paddingTop + paddingBottom;
	}

	@Override
	public void draw(int xOffset, int yOffset) {
		draw(xOffset, yOffset, 0, 0, 0, 0);
	}

	@Override
	public void draw(int xOffset, int yOffset, int maskTop, int maskBottom, int maskLeft, int maskRight) {
		Minecraft minecraft = Minecraft.getInstance();
		minecraft.getTextureManager().bindTexture(this.resourceLocation);

		int x = xOffset + this.paddingLeft + maskLeft;
		int y = yOffset + this.paddingTop + maskTop;
		int u = this.u + maskLeft;
		int v = this.v + maskTop;
		int width = this.width - maskRight - maskLeft;
		int height = this.height - maskBottom - maskTop;
		float f = 1.0F / this.textureWidth;
		float f1 = 1.0F / this.textureHeight;
		Tessellator tessellator = Tessellator.getInstance();
		BufferBuilder bufferbuilder = tessellator.getBuffer();
		bufferbuilder.begin(7, DefaultVertexFormats.POSITION_TEX);
		bufferbuilder.func_225582_a_(x, y + height, 0.0D).func_225583_a_(u * f, (v + (float)height) * f1).endVertex();
		bufferbuilder.func_225582_a_(x + width, y + height, 0.0D).func_225583_a_((u + (float)width) * f, (v + (float)height) * f1).endVertex();
		bufferbuilder.func_225582_a_(x + width, y, 0.0D).func_225583_a_((u + (float)width) * f, v * f1).endVertex();
		bufferbuilder.func_225582_a_(x, y, 0.0D).func_225583_a_(u * f, v * f1).endVertex();
		tessellator.draw();
	}
}
