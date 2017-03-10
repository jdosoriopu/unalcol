package unalcol.agents.examples.labyrinth.multeseo.eater.isi2017I.thewise;

import java.util.ArrayList;
import java.util.HashMap;

import unalcol.agents.Action;
import unalcol.agents.AgentProgram;
import unalcol.agents.Percept;
import unalcol.agents.simulate.util.SimpleLanguage;
import unalcol.types.collection.vector.Vector;

public class Agent1 implements AgentProgram
{
	private SimpleLanguage language;
	private Vector<String> cmds = new Vector<String>();
	private Position currentPosition = new Position( 0, 0 );
	private HashMap<Node, HashMap<Node, Byte>> tree = new HashMap<>();
	private ArrayList<Node> queue = new ArrayList<>();
	
	public Agent1( SimpleLanguage language )
	{
		this.language = language;
		queue.add( new Node( currentPosition, 0 ) );
	}
	
	public int accion( boolean PF, boolean PD, boolean PA, boolean PI, boolean MT, boolean FAIL )
	{
		if( MT )
			return -1;
		int side = 1;

        return side;
	}

	@Override
	public Action compute( Percept p )
	{
		if( cmds.size() == 0 )
		{
			boolean PF = ( ( Boolean ) p.getAttribute( language.getPercept( 0 ) ) ).
				booleanValue();
		    boolean PD = ( ( Boolean ) p.getAttribute( language.getPercept( 1 ) ) ).
				booleanValue();
		    boolean PA = ( ( Boolean ) p.getAttribute( language.getPercept( 2 ) ) ).
				booleanValue();
		    boolean PI = ( ( Boolean ) p.getAttribute( language.getPercept( 3 ) ) ).
				booleanValue();
		    boolean MT = ( ( Boolean ) p.getAttribute( language.getPercept( 4 ) ) ).
				booleanValue();
		    boolean FAIL = ( ( Boolean ) p.getAttribute( language.getPercept( 5 ) ) ).
				booleanValue();

		    int d = accion( PF, PD, PA, PI, MT, FAIL );
		    if( 0 <= d && d < 4 )
		    {
		    	for( int i = 1; i <= d; i++ )
		    		cmds.add( language.getAction( 3 ) ); //rotate
		        cmds.add( language.getAction( 2 ) ); // advance
		    }
		    else
		    	cmds.add( language.getAction( 0 ) ); // die
		}
		String x = cmds.get( 0 );
		cmds.remove( 0 );
		return new Action( x );
	}

	@Override
	public void init()
	{
		cmds.clear();
	}
}
