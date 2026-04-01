package EtherMenu.Ether;

import EtherMenu.utils.Logger;

public class EtherMain {
   private static EtherMain instance;
   public EtherTranslator etherTranslator;
   public EtherLuaManager etherLuaManager;
   public EtherAPI etherAPI;
   public LicenseManager licenseManager;

   private EtherMain() {
   }

   public void init() {
      Logger.printLog("Initializing EtherMenu...");
      try {
         this.licenseManager = LicenseManager.getInstance();
         this.licenseManager.init();
      } catch (NoClassDefFoundError e) {
         Logger.printLog("License module not available");
      }
      this.etherTranslator = new EtherTranslator();
      this.etherTranslator.loadTranslations();
      this.etherAPI = new EtherAPI();
      this.etherAPI.loadAPI();
      this.etherLuaManager = new EtherLuaManager();
      this.etherLuaManager.loadLua();
      Logger.printLog("Initialization EtherMenu was completed!");
      Logger.printLog("Edition: " + (this.licenseManager != null && this.licenseManager.isLicensed() ? "LICENSED" : "COMMUNITY"));
   }

   public static EtherMain getInstance() {
      if (instance == null) {
         instance = new EtherMain();
      }

      return instance;
   }
}
