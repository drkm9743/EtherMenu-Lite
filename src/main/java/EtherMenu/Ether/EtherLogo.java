package EtherMenu.Ether;

import EtherMenu.states.EtherLogoState;
import EtherMenu.utils.Logger;
import java.util.ArrayList;
import zombie.GameWindow;
import zombie.gameStates.GameState;
import zombie.gameStates.TISLogoState;

public class EtherLogo {
   private static EtherLogo instance;

   private EtherLogo() {
   }

   public void init() {
      ArrayList var1 = GameWindow.states.states;
      GameState var2 = (GameState)var1.get(0);
      if (var2 instanceof TISLogoState) {
         GameWindow.states.states.add(0, new EtherLogoState());
         GameWindow.states.loopToState = 1;
      } else {
         Logger.printLog("Error when initializing the EtherLogo!");
      }

   }

   public static EtherLogo getInstance() {
      if (instance == null) {
         instance = new EtherLogo();
      }

      return instance;
   }
}
