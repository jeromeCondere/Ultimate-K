package GameTool;

public class GameSignal 
{
	public GameSignal(float cx,float cy,float R, TYPE t)
	{
		type=t;
		x=cx;
		y=cy;
		radius=R;
	}
	public GameSignal(TYPE t)
	{
		type=t;
		
	}
	public GameSignal(TYPE t,int index)
	{
		type=t;
		if(index>-1)
		this.index=index;
	}
	
public float x;
public float y;
public float radius;

public TYPE type;
public int index=-1;
public enum TYPE{PINK_TOUCH,RED_TOUCH,GREEN_TOUCH,K_TOUCH,K_HIT,KILL_ORDER};
}
