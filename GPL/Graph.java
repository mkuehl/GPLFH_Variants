package GPL; 

import java.util.Iterator; 

import java.util.LinkedList; 
//dja: added to fix compile problems when doing the performance improvements
import java.util.Comparator; 
import java.util.Collections; 

// ***********************************************************************
 
public   class  Graph {
	
    public LinkedList vertices;

	
    public static boolean isDirected = true;

	

    public Graph() {
        vertices = new LinkedList();
    }

	
 
    // Fall back method that stops the execution of programs
     private void  run__wrappee__DirectedWithNeighbors  ( Vertex s )
      { }

	
    // Executes Number Vertices
    public void run( Vertex s )
     {
       	System.out.println("Number");
        NumberVertices( );
        run__wrappee__DirectedWithNeighbors( s );
    }

	

    //dja: fix for compile problems during performance improvements
    public void sortVertices(Comparator c) {
        Collections.sort(vertices, c);
    }

	


    // Adds an edge without weights if Weighted layer is not present
//    public void addAnEdge( Vertex start,  Vertex end, int weight )
  //    {
    //    addEdge( start, new  Neighbor( end ) );
//    }

    // Adds an edge without weights if Weighted layer is not present
    public EdgeIfc addEdge( Vertex start,  Vertex end )
    {
	  Neighbor e = new Neighbor( end );
        addEdge( start, e );
        return e;
    }

	

        
    public void addVertex( Vertex v ) {
        vertices.add( v );
    }

	
   
     private void  addEdge__wrappee__DirectedWithNeighbors  ( Vertex start,  Neighbor theNeighbor ) {
        start.addEdge( theNeighbor );
    }

	
      
    public void addEdge( Vertex start,  Neighbor theNeighbor )
    {
        addEdge__wrappee__DirectedWithNeighbors( start,theNeighbor );
          
        // At this point the edges are added.
        // If there is an adorn like weight it has to be added to
        // the neighbor already present there
        if ( isDirected==false ) 
      {
            // It has to add ONLY the weight object to the neighbor
            Vertex end = theNeighbor.neighbor;
            end.addWeight( end,theNeighbor.weight );
        
        } // of else
    }

	
    
    // Finds a vertex given its name in the vertices list
    public  Vertex findsVertex( String theName )
      {
        Vertex theVertex = null;

        // if we are dealing with the root
        if ( theName==null )
        {
            return null;
        }

        for(VertexIter vxiter = getVertices( ); vxiter.hasNext( ); )
        {
            theVertex = vxiter.next( );
            if ( theName.equals( theVertex.getName( ) ) )
            {
                return theVertex;
            }
        }

        return theVertex;
    }

	

    public VertexIter getVertices( ) 
    {
        return new VertexIter( ) 
        {
                private Iterator iter = vertices.iterator( );
                public Vertex next( ) 
                { 
                    return (Vertex)iter.next( ); 
                }
                public boolean hasNext( ) 
                { 
                    return iter.hasNext( ); 
                }
            };
    }

	

    
     private void  display__wrappee__DirectedWithNeighbors  ( ) 
    {
        System.out.println( "******************************************" );
        System.out.println( "Vertices " );
        for ( VertexIter vxiter = getVertices( ); vxiter.hasNext( ) ; )
        {
            vxiter.next( ).display( );
        }
        System.out.println( "******************************************" );

    }

	
    
    public void display() 
    {
        display__wrappee__DirectedWithNeighbors();
    }

	

    public void NumberVertices( ) 
    {
        GraphSearch( new NumberWorkSpace( ) );
    }

	
    public void GraphSearch( WorkSpace w ) 
    {
        // Step 1: initialize visited member of all nodes
        VertexIter vxiter = getVertices( );
        if ( vxiter.hasNext( ) == false )
        {
            return;
        }

        // Showing the initialization process
        while(vxiter.hasNext( ) ) 
        {
            Vertex v = vxiter.next( );
            v.init_vertex( w );
        }

        // Step 2: traverse neighbors of each node
        for (vxiter = getVertices( ); vxiter.hasNext( ); ) 
        {
            Vertex v = vxiter.next( );
            if ( !v.visited ) 
            {
                w.nextRegionAction( v );
                v.nodeSearch( w );
            }
        } //end for
    }

	
    // Adds an edge with weights
    public void addAnEdge( Vertex start,  Vertex end, int weight )
    {
        addEdge( start, new  Neighbor( end, weight ) );
    }


}
