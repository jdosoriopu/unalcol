package unalcol.agents.examples.labyrinth.multeseo.eater.isi2017I.thewise;

public class Node
{
	private Position position;
	private byte movement;

	public Node( Position position, int movement )
	{
		this.position = position;
		this.movement = ( byte ) movement;
	}
	
	public Position getPosition()
	{
		return position;
	}
	
	public byte getMovement()
	{
		return movement;
	}
}