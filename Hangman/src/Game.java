public abstract class Game{
	
	protected boolean myContinue = true;
	
	public final void run() {
		
		initialize();
		loadContent();
		draw();
		while (myContinue)
		{
			userAction();
			myContinue = evaluateState();
			draw();
		}
		report();
	}
	
	protected void initialize() {}
	protected void loadContent(){}
	protected void userAction() {}
	protected void draw() {}
	protected boolean evaluateState() {return true;}
	protected void report() {};
}