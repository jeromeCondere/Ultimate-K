package GameTool;

public class Vecteur
{
	public Vecteur(float cx,float cy)
	{
		x=cx;
		y=cy;
	}
	public Vecteur()
	{
		x=0;
		y=0;
	}
	public float NormeSquare()
	{
		return x*x+y*y;
	}
	public float Norme()
	{
		return (float) Math.sqrt(x*x+y*y);
	}
	
	public float x;
	public float y;
}
