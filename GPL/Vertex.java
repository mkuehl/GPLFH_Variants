package GPL; 

// dja - trying to fix compile problems
import java.util.Iterator; 

import java.util.LinkedList; 
import java.util.Collections; 
import java.util.Comparator; 

import java.lang.Integer; 

// of Graph
 
// The weighted layer needs to extend Vertex to provide a new 
// LinkedList to hold the  weigths  of the edges
// ************************************************************
 
public   class  Vertex  implements EdgeIfc, NeighborIfc {
	
    public LinkedList adjacentVertices;

	
    public String name;

	
 
    public Vertex() {
        VertexConstructor();
    }

	
  
     private void  VertexConstructor__wrappee__DirectedOnlyVertices  () {
        name      = null;
        adjacentVertices = new LinkedList();
    }

	

     private void  VertexConstructor__wrappee__DFS  ( ) 
    {
        VertexConstructor__wrappee__DirectedOnlyVertices( );
        visited = false;
    }

	
 
    public void VertexConstructor() {
        VertexConstructor__wrappee__DFS();
        weightsList = new LinkedList();
    }

	

    public  Vertex assignName( String name ) {
        this.name = name;
        return ( Vertex ) this;
    }

	

    //dja: fix for compile errors during performance improvements
    public String getName( ) 
    { 
        return name; 
    }

	

 
    public void addAdjacent( Vertex n ) {
        adjacentVertices.add( n );
    }

	

     private void  adjustAdorns__wrappee__DirectedOnlyVertices  ( Vertex the_vertex, int index ) 
      {}

	
    
    public void adjustAdorns( Vertex the_vertex, int index )
    {
        int the_weight = ( ( Integer )the_vertex.weightsList.get( index ) ).intValue();
        weightsList.add( new Integer( the_weight ) );
        adjustAdorns__wrappee__DirectedOnlyVertices( the_vertex, index );
    }

	
      
    // dja - trying to fix compile errors
    public VertexIter getNeighbors( ) 
    {
        return new VertexIter( ) 
        {
            private Iterator iter = adjacentVertices.iterator( );
            public Vertex next( ) 
            { 
               return ( Vertex )iter.next( ); 
            }

            public boolean hasNext( ) 
            {
               return iter.hasNext( ); 
            }
        };
    }

	


     private void  display__wrappee__DirectedOnlyVertices  () {
        int s = adjacentVertices.size();
        int i;

        System.out.print( "Vertex " + name + " connected to: " );

        for ( i=0; i<s; i++ )
            System.out.print( ( ( Vertex )adjacentVertices.get( i ) ).name+", " );
        System.out.println();
    }

	

     private void  display__wrappee__Number  ( ) 
    {
        System.out.print( " # "+ VertexNumber + " " );
        display__wrappee__DirectedOnlyVertices( );
    }

	
      
     private void  display__wrappee__StronglyConnected  () {
        System.out.print( " FinishTime -> " + finishTime + " SCCNo -> " 
                        + strongComponentNumber );
        display__wrappee__Number();
    }

	 // white ->0, gray ->1, black->2
      
     private void  display__wrappee__Cycle  () {
        System.out.print( " VertexCycle# " + VertexCycle + " " );
        display__wrappee__StronglyConnected();
    }

	 // of dftNodeSearch

     private void  display__wrappee__DFS  ( ) {
        if ( visited )
            System.out.print( "  visited" );
        else
            System.out.println( " !visited " );
        display__wrappee__Cycle( );
    }

	
                          
    public void display()
    {
        int s = weightsList.size();
        int i;

        System.out.print( " Weights : " );

        for ( i=0; i<s; i++ ) {
            System.out.print( ( ( Integer )weightsList.get( i ) ).intValue() + ", " );
        }

        display__wrappee__DFS();
    }

	

//--------------------
// from EdgeIfc
//--------------------

    public Vertex getStart( ) { return null; }

	
    public Vertex getEnd( ) { return null; }

	

    public void setWeight( int weight ){}

	
    public int getWeight() { return 0; }

	

    public Vertex getOtherVertex( Vertex vertex )
    {
        return this;
    }

	



    public void adjustAdorns( EdgeIfc the_edge )
    {
    }

	
    public int VertexNumber;

	
    public int finishTime;

	
    public int strongComponentNumber;

	
    public int VertexCycle;

	
    public int VertexColor;

	
    public boolean visited;

	

    public void init_vertex( WorkSpace w ) 
    {
        visited = false;
        w.init_vertex( ( Vertex ) this );
    }

	

    public void nodeSearch( WorkSpace w ) 
    {
        Vertex v;

        // Step 1: Do preVisitAction.
        //            If we've already visited this node return
        w.preVisitAction( ( Vertex ) this );

        if ( visited )
            return;

        // Step 2: else remember that we've visited and
        //         visit all neighbors
        visited = true;

        for ( VertexIter  vxiter = getNeighbors(); vxiter.hasNext(); ) 
        {
            v = vxiter.next( );
            w.checkNeighborAction( ( Vertex ) this, v );
            v.nodeSearch( w );
        }

        // Step 3: do postVisitAction now
        w.postVisitAction( ( Vertex ) this );
    }

	
    public LinkedList weightsList;

	
         
    public void addWeight( int weight )
    {
        weightsList.add( new Integer( weight ) );
    }


}
