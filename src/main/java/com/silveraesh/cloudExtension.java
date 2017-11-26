package com.silveraesh;

import com.bitwig.extension.controller.api.*;
import com.bitwig.extension.controller.ControllerExtension;

public class cloudExtension extends ControllerExtension {

   protected cloudExtension(final cloudExtensionDefinition definition, final ControllerHost host) {
      super(definition, host);
   }

   @Override
   public void init()
   {
      final ControllerHost host = getHost();

      CloudInterface.init(host);

      // TODO: Perform your driver initialization here.
      // For now just show a popup notification for verification that it is running.
      host.showPopupNotification("tae Initialized");
   }

   @Override
   public void exit()
   {
      // TODO: Perform any cleanup once the driver exits
      // For now just show a popup notification for verification that it is no longer running.
      getHost().showPopupNotification("tae Exited");
   }

   @Override
   public void flush()
   {
      // TODO Send any updates you need here.
   }
}
      