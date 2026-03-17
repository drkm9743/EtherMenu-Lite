package EtherMenu.Ether;

import EtherMenu.annotations.SubscribeLuaEvent;
import EtherMenu.utils.EventSubscriber;
import EtherMenu.utils.Logger;
import zombie.Lua.LuaManager;
import java.util.ArrayList;

/**
 * Класс для управления Lua-скриптами в EtherMenu.
 */
public class EtherLuaManager {
   public final String pathToLuaMainFile = "EtherMenu/lua/EtherMenu.lua";
   public ArrayList<String> luaFilesList = new ArrayList<>();

   /**
    * Конструктор класса EtherLuaManager.
    * Регистрирует объект в качестве подписчика событий.
    */
   public EtherLuaManager() {
      EventSubscriber.register(this);
   }

   /**
    * Загрузка пользовательских Lua контекст игры
    */
   @SubscribeLuaEvent(
           eventName = "OnResetLua"
   )
   @SubscribeLuaEvent(
           eventName = "OnMainMenuEnter"
   )
   public void loadLua() {
      Logger.printLog("Loading EtherMenu Lua...");

      EtherLuaCompiler.getInstance().addWordToBlacklistLuaCompiler("EtherMain");
      EtherLuaCompiler.getInstance().addPathToWhiteListLuaCompiler(pathToLuaMainFile);

      LuaManager.RunLua(pathToLuaMainFile, false);
   }
}
