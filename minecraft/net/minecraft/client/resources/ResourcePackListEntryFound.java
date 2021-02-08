package net.minecraft.client.resources;

import net.minecraft.client.gui.GuiScreenResourcePacks;

public class ResourcePackListEntryFound extends ResourcePackListEntry
{
    private final ResourcePackRepository.Entry field_148319_c;

    public ResourcePackListEntryFound(GuiScreenResourcePacks p_i45053_1_, ResourcePackRepository.Entry p_i45053_2_)
    {
        super(p_i45053_1_);
        this.field_148319_c = p_i45053_2_;
    }

    protected void BindIcon()
    {
        this.field_148319_c.bindTexturePackIcon(this.mc.getTextureManager());
    }

    protected String TexturePackDescription()
    {
        return this.field_148319_c.getTexturePackDescription();
    }

    protected String TexturePackName()
    {
        return this.field_148319_c.getResourcePackName();
    }

    public ResourcePackRepository.Entry RessourcePackRepo()
    {
        return this.field_148319_c;
    }
}
