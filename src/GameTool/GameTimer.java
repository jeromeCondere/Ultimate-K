package GameTool;

public class GameTimer
{
	public boolean switched=false;
    public double startTime;
    public double endTime;
    private boolean Started=false;
    public  int run(double end)
    {
    	if(Started==false)
    	{
    	startTime=System.currentTimeMillis();
    	Started=true;
    	return 0;//started
    	}
    	else if((System.currentTimeMillis()-startTime)>end)
    	{
    		//Started=false;
    		endTime=System.currentTimeMillis();
    		return 1;//stopped
    		
    	}
    	return  2;//is in duration
    }
    public void restart()
    {
    	Started=false;
    }


    public double getDuration()
    {
        return startTime - endTime;
    }
}
