package unalcol.agents.examples.labyrinth.multeseo.eater.isi2017I.thewise;

import java.util.ArrayList;
import java.util.HashMap;

import unalcol.agents.Action;
import unalcol.agents.AgentProgram;
import unalcol.agents.Percept;
import unalcol.agents.examples.labyrinth.multeseo.eater.isi2017I.thewise.Position;
import unalcol.agents.simulate.util.SimpleLanguage;
import unalcol.types.collection.vector.Vector;

public class Agent1 implements AgentProgram
{
	private SimpleLanguage language;
	private Vector<String> cmds = new Vector<String>();
	private HashMap<Position, ArrayList<Byte>> positions = new HashMap<>();
	private Position currentPosition = new Position( 0, 0 );
	
	public Agent1( SimpleLanguage language )
	{
		this.language = language;
	}
	
	public int accion( boolean PF, boolean PD, boolean PA, boolean PI, boolean MT, boolean FAIL )
	{
		if( MT )
			return -1;

		ArrayList<Byte> sides = positions.get( currentPosition );
		int maxOutputs = 0, side;
		if( sides != null )
		{
			maxOutputs = sides.size();
		}
		else
		{
			sides = new ArrayList<>();
			if( !PF )
			{
				sides.add( ( byte ) 0 );
				maxOutputs++;
			}
			if( !PD )
			{
				sides.add( ( byte ) 1 );
				maxOutputs++;
			}
			if( !PA )
			{
				sides.add( ( byte ) 2 );
				maxOutputs++;
			}
			if( !PI )
			{
				sides.add( ( byte ) 3 );
				maxOutputs++;
			}
		}
		
        side = sides.get( ( int ) ( Math.random() * maxOutputs ) );
        switch( side )
        {
            case 0:
            	sides.remove( ( byte ) 0 );
            	positions.put( currentPosition, sides );
            	currentPosition = new Position( currentPosition.getX(), currentPosition.getY() + 1 );
                break;
            case 1:
            	sides.remove( ( byte ) 1 );
            	positions.put( currentPosition, sides );
            	currentPosition = new Position( currentPosition.getX() + 1, currentPosition.getY() );
                break;
            case 2:
            	sides.remove( ( byte ) 2 );
            	positions.put( currentPosition, sides );
            	currentPosition = new Position( currentPosition.getX(), currentPosition.getY() - 1 );
            	break;
            case 3:
            	sides.remove( ( byte ) 3 );
            	positions.put( currentPosition, sides );
            	currentPosition = new Position( currentPosition.getX() - 1, currentPosition.getY() );
                break;                    
        }

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
