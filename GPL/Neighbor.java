package GPL; 

import java.util.LinkedList; 

// end of Vertex class
 
  // *************************************************************************
  
public   class  Neighbor  implements EdgeIfc, NeighborIfc {
	
    public  Vertex neighbor;

	
        
    // This constructor has to be present here so that the default one
    // Called on Weighted can call it, i.e. it is not longer implicit    
    public Neighbor( )
    {
        neighbor = null;
    }

	
    
    public Neighbor( Vertex theNeighbor ) 
    {
        NeighborConstructor( theNeighbor );
    }

	
    
    public void NeighborConstructor( Vertex theNeighbor ) 
    {
        neighbor = theNeighbor;
    }

	
  
     private void  display__wrappee__DirectedWithNeighbors  () 
    {
        System.out.print( neighbor.name + " ," );
    }

	

    public void display()
    {
        System.out.print( " Weight = " + weight + " " );
        display__wrappee__DirectedWithNeighbors();
    }

	

    public Vertex getStart( ) 
    { 
       return null; 
    }

	

    public Vertex getEnd( ) 
    { 
      return neighbor; 
    }

	

    public void setWeight  (int weight)
    {
        this.weight = weight;
    }

	

    public Vertex getOtherVertex( Vertex vertex )
    {
        return neighbor;
    }

	

    public void adjustAdorns( EdgeIfc the_edge )
    {
    }

	
    public int weight;

	

    public Neighbor( Vertex theNeighbor, int theWeight ) {
        NeighborConstructor( theNeighbor, theWeight );
    }

	

    public void NeighborConstructor( Vertex theNeighbor, int theWeight )
    {
        NeighborConstructor( theNeighbor );
        weight = theWeight;
    }

	

    public int getWeight()
    {
        return this.weight;
    }


}
