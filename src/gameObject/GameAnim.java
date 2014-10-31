package gameObject;

import gameObject.Ball.STATE;
import android.graphics.Canvas;

public abstract class GameAnim extends Abstract_Object {

	public GameAnim(int fps )
	{
		if(fps>0)
		this.fps=fps;
		activeState=STATE.ACTIVE;
	}
	public GameAnim(int fps ,float duration)
	{
		if(fps>0)
		this.fps=fps;
		if(duration>0)
			this.duration=duration;
		activeState=STATE.ACTIVE;
	}
	@Override
	public void pause() 
	{
		// TODO Auto-generated method stub

		activeState=STATE.PAUSED;
	
	}

	@Override
	public void start() 
	{
		// TODO Auto-generated method stub

		if(n==0)
			finished=false;
		activeState=STATE.ACTIVE;
	}

	@Override
	public void restart() 
  {
		// TODO Auto-generated method stub

		n=0;
		activeState=STATE.ACTIVE;
	}

	@Override
	public abstract void update();
	@Override
	public abstract void draw(Canvas canvas);
	
	protected int n=0;
	protected float fps=1;//we have at least 1 fps
	protected boolean finished;
	protected STATE activeState;
	protected float duration=0;

}
