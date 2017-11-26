package com.silveraesh

import com.bitwig.extension.callback.{EnumValueChangedCallback, ShortMidiDataReceivedCallback, SysexMidiDataReceivedCallback}
import com.bitwig.extension.controller.api.{ControllerHost, MidiIn, MidiOut, NoteInput}


object CloudInterface {

  def init(host: ControllerHost): Unit = {
    implicit val implicit_host: ControllerHost = host
    implicit val input: MidiIn = host.getMidiInPort(0)
    implicit val output: MidiOut = host.getMidiOutPort(0)

    val note = input.createNoteInput("", "8?????", "9?????", "B?40??", "B?4A??", "C?????", "D?????", "E?????")
    note.setShouldConsumeEvents(false)
    note.setUseExpressiveMidi(true, 0, 48)
    sendPitchBendRangeRPN(1, 48)

    val bendRanges = Array("12", "24", "36", "48", "60", "72", "84", "96")
    val bendRange = host.getPreferences.getEnumSetting("Bend Range", "Cloud Settings", bendRanges, "48")

    bendRange.addValueObserver((range: String) => {
        host.println(range)
        val pb = Integer.parseInt(range)
        note.setUseExpressiveMidi(true, 0, pb)
        sendPitchBendRangeRPN(1, pb)
      }
    )


    val modes = Array("admin", "producer", "sampler")
    val mode = host.getPreferences.getEnumSetting("Mode", "Cloud Settings", modes, "sampler")
    mode.addValueObserver((mode: String) => {
      host.println(mode)
    })

    input.setMidiCallback(
      (status: Int, data0: Int, data1: Int) => {
        println("midi recieved")
        println(status)
        println(data0)
        println(data1)
      }
    )

    input.setSysexCallback(
      (str: String) => host.println(str)
    )

  }

  private def println(serializable: Any)(implicit host: ControllerHost): Unit = {
    host.println(serializable.toString)
  }

  private implicit def enumCallback(func: (String) => Unit): EnumValueChangedCallback = new EnumValueChangedCallback {
    override def valueChanged(value: String): Unit = func(value)
  }

  private def sendPitchBendRangeRPN(channel: Int, range: Int)(implicit output: MidiOut) = {
    sendChannelController(channel, 100, 0) // Registered Parameter Number (RPN) - LSB*
    sendChannelController(channel, 101, 0) // Registered Parameter Number (RPN) - MSB*
    sendChannelController(channel, 38, 0)
    sendChannelController(channel, 6, range)
  }

  private def sendChannelController(channel: Int, controller: Int, value: Int)(implicit output: MidiOut): Unit = {
    output.sendMidi(channel, controller, value)
  }


}