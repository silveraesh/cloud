package com.silveraesh;
import java.util.UUID;

import com.bitwig.extension.api.PlatformType;
import com.bitwig.extension.controller.AutoDetectionMidiPortNamesList;
import com.bitwig.extension.controller.ControllerExtensionDefinition;
import com.bitwig.extension.controller.api.ControllerHost;

public class cloudExtensionDefinition extends ControllerExtensionDefinition
{
   private static final UUID DRIVER_ID = UUID.fromString("f2527e7f-2de2-4c69-8bed-d7360a0478e1");
   
   public cloudExtensionDefinition()
   {
   }

   @Override
   public String getName()
   {
      return "cloud";
   }
   
   @Override
   public String getAuthor()
   {
      return "silveraesh";
   }

   @Override
   public String getVersion()
   {
      return "0.1";
   }

   @Override
   public UUID getId()
   {
      return DRIVER_ID;
   }
   
   @Override
   public String getHardwareVendor()
   {
      return "silveraesh";
   }
   
   @Override
   public String getHardwareModel()
   {
      return "cloud";
   }

   @Override
   public int getRequiredAPIVersion()
   {
      return 3;
   }

   @Override
   public int getNumMidiInPorts()
   {
      return 1;
   }

   @Override
   public int getNumMidiOutPorts()
   {
      return 1;
   }

   @Override
   public void listAutoDetectionMidiPortNames(final AutoDetectionMidiPortNamesList list, final PlatformType platformType)
   {

//      list.add(
//              new String[] {
//                      "Seaboard RISE",
//                      "Seaboard RISE MIDI 1",
//                      "Seaboard RISE 49",
//                      "ROLI Seaboard RISE",
//                      "ROLI Seaboard RISE 49"
//              },
//              new String[]{
//                      "Seaboard RISE",
//                      "Seaboard RISE MIDI 1",
//                      "Seaboard RISE 49",
//                      "ROLI Seaboard RISE",
//                      "ROLI Seaboard RISE 49"
//              }
//      );
   }

   @Override
   public cloudExtension createInstance(final ControllerHost host)
   {
      return new cloudExtension(this, host);
   }
}
