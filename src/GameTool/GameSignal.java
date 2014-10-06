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
public float x;
public float y;
public float radius;
public TYPE type;
public enum TYPE{PINK_TOUCH,RED_TOUCH,GREEN_TOUCH,K_TOUCH};
}
