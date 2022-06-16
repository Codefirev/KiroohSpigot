package net.minecraft.server;

import java.util.List;
import java.util.Random;

public class WorldGenMineshaftCorridor extends StructurePiece {
    private boolean a;
    private boolean b;
    private boolean c;
    private int d;

    public WorldGenMineshaftCorridor() {
    }

    protected void a(NBTTagCompound var1) {
        var1.setBoolean("hr", this.a);
        var1.setBoolean("sc", this.b);
        var1.setBoolean("hps", this.c);
        var1.setInt("Num", this.d);
    }

    protected void b(NBTTagCompound var1) {
        this.a = var1.getBoolean("hr");
        this.b = var1.getBoolean("sc");
        this.c = var1.getBoolean("hps");
        this.d = var1.getInt("Num");
    }

    public WorldGenMineshaftCorridor(int var1, Random var2, StructureBoundingBox var3, int var4) {
        super(var1);
        this.g = var4;
        this.f = var3;
        this.a = var2.nextInt(3) == 0;
        this.b = !this.a && var2.nextInt(23) == 0;
        if (this.g != 2 && this.g != 0) {
            this.d = var3.b() / 5;
        } else {
            this.d = var3.d() / 5;
        }

    }

    public static StructureBoundingBox a(List var0, Random var1, int var2, int var3, int var4, int var5) {
        StructureBoundingBox var6 = new StructureBoundingBox(var2, var3, var4, var2, var3 + 2, var4);

        int var7;
        for(var7 = var1.nextInt(3) + 2; var7 > 0; --var7) {
            int var8 = var7 * 5;
            switch(var5) {
            case 0:
                var6.d = var2 + 2;
                var6.f = var4 + (var8 - 1);
                break;
            case 1:
                var6.a = var2 - (var8 - 1);
                var6.f = var4 + 2;
                break;
            case 2:
                var6.d = var2 + 2;
                var6.c = var4 - (var8 - 1);
                break;
            case 3:
                var6.d = var2 + (var8 - 1);
                var6.f = var4 + 2;
            }

            if (StructurePiece.a(var0, var6) == null) {
                break;
            }
        }

        return var7 > 0 ? var6 : null;
    }

    public void a(StructurePiece var1, List var2, Random var3) {
        int var4 = this.d();
        int var5 = var3.nextInt(4);
        switch(this.g) {
        case 0:
            if (var5 <= 1) {
                WorldGenMineshaftPieces.a(var1, var2, var3, this.f.a, this.f.b - 1 + var3.nextInt(3), this.f.f + 1, this.g, var4);
            } else if (var5 == 2) {
                WorldGenMineshaftPieces.a(var1, var2, var3, this.f.a - 1, this.f.b - 1 + var3.nextInt(3), this.f.f - 3, 1, var4);
            } else {
                WorldGenMineshaftPieces.a(var1, var2, var3, this.f.d + 1, this.f.b - 1 + var3.nextInt(3), this.f.f - 3, 3, var4);
            }
            break;
        case 1:
            if (var5 <= 1) {
                WorldGenMineshaftPieces.a(var1, var2, var3, this.f.a - 1, this.f.b - 1 + var3.nextInt(3), this.f.c, this.g, var4);
            } else if (var5 == 2) {
                WorldGenMineshaftPieces.a(var1, var2, var3, this.f.a, this.f.b - 1 + var3.nextInt(3), this.f.c - 1, 2, var4);
            } else {
                WorldGenMineshaftPieces.a(var1, var2, var3, this.f.a, this.f.b - 1 + var3.nextInt(3), this.f.f + 1, 0, var4);
            }
            break;
        case 2:
            if (var5 <= 1) {
                WorldGenMineshaftPieces.a(var1, var2, var3, this.f.a, this.f.b - 1 + var3.nextInt(3), this.f.c - 1, this.g, var4);
            } else if (var5 == 2) {
                WorldGenMineshaftPieces.a(var1, var2, var3, this.f.a - 1, this.f.b - 1 + var3.nextInt(3), this.f.c, 1, var4);
            } else {
                WorldGenMineshaftPieces.a(var1, var2, var3, this.f.d + 1, this.f.b - 1 + var3.nextInt(3), this.f.c, 3, var4);
            }
            break;
        case 3:
            if (var5 <= 1) {
                WorldGenMineshaftPieces.a(var1, var2, var3, this.f.d + 1, this.f.b - 1 + var3.nextInt(3), this.f.c, this.g, var4);
            } else if (var5 == 2) {
                WorldGenMineshaftPieces.a(var1, var2, var3, this.f.d - 3, this.f.b - 1 + var3.nextInt(3), this.f.c - 1, 2, var4);
            } else {
                WorldGenMineshaftPieces.a(var1, var2, var3, this.f.d - 3, this.f.b - 1 + var3.nextInt(3), this.f.f + 1, 0, var4);
            }
        }

        if (var4 < 8) {
            int var6;
            int var7;
            if (this.g != 2 && this.g != 0) {
                for(var6 = this.f.a + 3; var6 + 3 <= this.f.d; var6 += 5) {
                    var7 = var3.nextInt(5);
                    if (var7 == 0) {
                        WorldGenMineshaftPieces.a(var1, var2, var3, var6, this.f.b, this.f.c - 1, 2, var4 + 1);
                    } else if (var7 == 1) {
                        WorldGenMineshaftPieces.a(var1, var2, var3, var6, this.f.b, this.f.f + 1, 0, var4 + 1);
                    }
                }
            } else {
                for(var6 = this.f.c + 3; var6 + 3 <= this.f.f; var6 += 5) {
                    var7 = var3.nextInt(5);
                    if (var7 == 0) {
                        WorldGenMineshaftPieces.a(var1, var2, var3, this.f.a - 1, this.f.b, var6, 1, var4 + 1);
                    } else if (var7 == 1) {
                        WorldGenMineshaftPieces.a(var1, var2, var3, this.f.d + 1, this.f.b, var6, 3, var4 + 1);
                    }
                }
            }
        }

    }

    protected boolean a(World var1, StructureBoundingBox var2, Random var3, int var4, int var5, int var6, StructurePieceTreasure[] var7, int var8) {
        int var9 = this.a(var4, var6);
        int var10 = this.a(var5);
        int var11 = this.b(var4, var6);
        if (var2.b(var9, var10, var11) && var1.getType(var9, var10, var11).getMaterial() == Material.AIR) {
            /*int var12 = var3.nextBoolean() ? 1 : 0;
            var1.setTypeAndData(var9, var10, var11, Blocks.RAILS, this.a(Blocks.RAILS, var12), 2);
            EntityMinecartChest var13 = new EntityMinecartChest(var1, (double)((float)var9 + 0.5F), (double)((float)var10 + 0.5F), (double)((float)var11 + 0.5F));
            StructurePieceTreasure.a(var3, var7, var13, var8);
            var1.addEntity(var13);*/

            var1.setTypeAndData(var9, var10, var11, Blocks.CHEST, 0, 2);
            TileEntityChest localTileEntityChest = (TileEntityChest) var1.getTileEntity(var9, var10, var11);
            if (localTileEntityChest == null) return false;
            StructurePieceTreasure.a(var3, var7, localTileEntityChest, var8);
            return true;
        } else {
            return false;
        }
    }

    public boolean a(World var1, Random var2, StructureBoundingBox var3) {
        if (this.a(var1, var3)) {
            return false;
        } else {
            boolean var4 = false;
            boolean var5 = true;
            boolean var6 = false;
            boolean var7 = true;
            int var8 = this.d * 5 - 1;
            this.a(var1, var3, 0, 0, 0, 2, 1, var8, Blocks.AIR, Blocks.AIR, false);
            this.a(var1, var3, var2, 0.8F, 0, 2, 0, 2, 2, var8, Blocks.AIR, Blocks.AIR, false);
            if (this.b) {
                this.a(var1, var3, var2, 0.6F, 0, 0, 0, 2, 1, var8, Blocks.WEB, Blocks.AIR, false);
            }

            int var9;
            int var10;
            for(var9 = 0; var9 < this.d; ++var9) {
                var10 = 2 + var9 * 5;
                this.a(var1, var3, 0, 0, var10, 0, 1, var10, Blocks.FENCE, Blocks.AIR, false);
                this.a(var1, var3, 2, 0, var10, 2, 1, var10, Blocks.FENCE, Blocks.AIR, false);
                if (var2.nextInt(4) == 0) {
                    this.a(var1, var3, 0, 2, var10, 0, 2, var10, Blocks.WOOD, Blocks.AIR, false);
                    this.a(var1, var3, 2, 2, var10, 2, 2, var10, Blocks.WOOD, Blocks.AIR, false);
                } else {
                    this.a(var1, var3, 0, 2, var10, 2, 2, var10, Blocks.WOOD, Blocks.AIR, false);
                }

                this.a(var1, var3, var2, 0.1F, 0, 2, var10 - 1, Blocks.WEB, 0);
                this.a(var1, var3, var2, 0.1F, 2, 2, var10 - 1, Blocks.WEB, 0);
                this.a(var1, var3, var2, 0.1F, 0, 2, var10 + 1, Blocks.WEB, 0);
                this.a(var1, var3, var2, 0.1F, 2, 2, var10 + 1, Blocks.WEB, 0);
                this.a(var1, var3, var2, 0.05F, 0, 2, var10 - 2, Blocks.WEB, 0);
                this.a(var1, var3, var2, 0.05F, 2, 2, var10 - 2, Blocks.WEB, 0);
                this.a(var1, var3, var2, 0.05F, 0, 2, var10 + 2, Blocks.WEB, 0);
                this.a(var1, var3, var2, 0.05F, 2, 2, var10 + 2, Blocks.WEB, 0);
                this.a(var1, var3, var2, 0.05F, 1, 2, var10 - 1, Blocks.TORCH, 0);
                this.a(var1, var3, var2, 0.05F, 1, 2, var10 + 1, Blocks.TORCH, 0);
                if (var2.nextInt(100) == 0) {
                    this.a(var1, var3, var2, 2, 0, var10 - 1, StructurePieceTreasure.a(WorldGenMineshaftPieces.b(), new StructurePieceTreasure[]{Items.ENCHANTED_BOOK.b(var2)}), 3 + var2.nextInt(4));
                }

                if (var2.nextInt(100) == 0) {
                    this.a(var1, var3, var2, 0, 0, var10 + 1, StructurePieceTreasure.a(WorldGenMineshaftPieces.b(), new StructurePieceTreasure[]{Items.ENCHANTED_BOOK.b(var2)}), 3 + var2.nextInt(4));
                }

                if (this.b && !this.c) {
                    int var11 = this.a(0);
                    int var12 = var10 - 1 + var2.nextInt(3);
                    int var13 = this.a(1, var12);
                    var12 = this.b(1, var12);
                    if (var3.b(var13, var11, var12)) {
                        this.c = true;
                        var1.setTypeAndData(var13, var11, var12, Blocks.MOB_SPAWNER, 0, 2);
                        TileEntityMobSpawner var14 = (TileEntityMobSpawner)var1.getTileEntity(var13, var11, var12);
                        if (var14 != null) {
                            var14.getSpawner().setMobName("CaveSpider");
                        }
                    }
                }
            }

            for(var9 = 0; var9 <= 2; ++var9) {
                for(var10 = 0; var10 <= var8; ++var10) {
                    byte var16 = -1;
                    Block var17 = this.a(var1, var9, var16, var10, var3);
                    if (var17.getMaterial() == Material.AIR) {
                        byte var18 = -1;
                        this.a(var1, Blocks.WOOD, 0, var9, var18, var10, var3);
                    }
                }
            }

            if (this.a) {
                for(var9 = 0; var9 <= var8; ++var9) {
                    Block var15 = this.a(var1, 1, -1, var9, var3);
                    if (var15.getMaterial() != Material.AIR && var15.j()) {
                        this.a(var1, var3, var2, 0.7F, 1, 0, var9, Blocks.RAILS, this.a(Blocks.RAILS, 0));
                    }
                }
            }

            return true;
        }
    }
}