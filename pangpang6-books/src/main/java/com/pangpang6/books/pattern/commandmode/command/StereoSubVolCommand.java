package com.pangpang6.books.pattern.commandmode.command;


import com.pangpang6.books.pattern.commandmode.device.Stereo;

public class StereoSubVolCommand implements Command {
	private Stereo setreo;
	public StereoSubVolCommand(Stereo setreo)
	{
		this.setreo=setreo;
	}
	
	@Override
	public void execute() {
		// TODO Auto-generated method stub
	int vol=	setreo.GetVol();
	if(vol>0)
	{
		setreo.SetVol(--vol);
	}
		
	}

	@Override
	public void undo() {
		// TODO Auto-generated method stub
	int vol=	setreo.GetVol();
	if(vol<11)
	{
		setreo.SetVol(++vol);
	}
		
	}

}
