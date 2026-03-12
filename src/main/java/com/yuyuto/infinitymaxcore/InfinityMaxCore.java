package com.yuyuto.infinitymaxcore;

import com.mojang.logging.LogUtils;
import net.minecraftforge.fml.common.Mod;
import org.slf4j.Logger;

// この値は、META-INF/mods.toml ファイル内のエントリと一致する必要があります。
@Mod(InfinityMaxCore.MODID)
public class InfinityMaxCore {
    // すべての参照元が参照できるように、共通の場所でMODIDを定義する
    public static final String MODID = "infinitymaxcore";
    // SLF4Jロガーを直接参照する
    private static final Logger LOGGER = LogUtils.getLogger();

}
