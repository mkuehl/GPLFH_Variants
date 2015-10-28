package GPL; 

import java.util.Iterator; 

import java.util.LinkedList; 

public   class  Vertex {
	
    public LinkedList adjacentNeighbors;

	
    public String name;

	
   
    public Vertex() {
        VertexConstructor();
    }

	
    public String getName( ) 
    { 
        return name; 
    }

	

     private void  VertexConstructor__wrappee__DirectedWithNeighbors  () {
        name      = null;
        adjacentNeighbors = new LinkedList();
    }

	

    public void VertexConstructor( ) 
    {
        VertexConstructor__wrappee__DirectedWithNeighbors();
        visited = false;
    }

	

    public  Vertex assignName( String name ) {
        this.name = name;
        return ( Vertex ) this;
    }

	
   
    public void addEdge( Neighbor n ) {
        adjacentNeighbors.add( n );
    }

	


    public VertexIter getNeighbors( ) 
    {
        return new VertexIter( ) 
        {
            private Iterator iter = adjacentNeighbors.iterator( );
            public Vertex next( ) 
            { 
               return ( ( Neighbor )iter.next( ) ).neighbor; 
            }

            public boolean hasNext( ) 
            {
               return iter.hasNext( ); 
            }
        };
    }

	

     private void  adjustAdorns__wrappee__DirectedWithNeighbors  ( Neighbor sourceNeighbor )
      {}

	
    
    public void adjustAdorns( Neighbor sourceNeighbor )
     {
        Neighbor targetNeighbor = 
                ( Neighbor )adjacentNeighbors.getLast();
        targetNeighbor.weight = sourceNeighbor.weight;
        adjustAdorns__wrappee__DirectedWithNeighbors( sourceNeighbor );
    }

	
      
     private void  display__wrappee__DirectedWithNeighbors  () 
    {
        System.out.print( "Node " + getName( ) + " connected to: " );

        for(VertexIter vxiter = getNeighbors( ); vxiter.hasNext( ); )
         {
            Vertex v = vxiter.next( );
            System.out.print( v.getName( ) + ", " );
        }
        System.out.println( );
    }

	

     private void  display__wrappee__Number  ( ) 
    {
        System.out.print( " # "+ VertexNumber + " " );
        display__wrappee__DirectedWithNeighbors( );
    }

	 // of bfsNodeSearch

     private void  display__wrappee__BFS  ( ) 
    {
        if ( visited )
            System.out.print( "  visited " );
        else
            System.out.println( " !visited " );
        display__wrappee__Number( );
    }

	
    
    public void display()
    {
        display__wrappee__BFS();
    }

	
    public int VertexNumber;

	
    public boolean visited;

	

    public void init_vertex( WorkSpace w ) 
    {
        visited = false;
        w.init_vertex( ( Vertex ) this );
    }

	

    public void nodeSearch( WorkSpace w ) 
    {
        int     s, c;
        Vertex  v;
        Vertex  header;

        // Step 1: if preVisitAction is true or if we've already
        //         visited this node
        w.preVisitAction( ( Vertex ) this );

        if ( visited )
        {
            return;
        }

        // Step 2: Mark as visited, put the unvisited neighbors in the queue
        //     and make the recursive call on the first element of the queue
        //     if there is such if not you are done
        visited = true;

        // Step 3: do postVisitAction now, you are no longer going through the
        // node again, mark it as black
        w.postVisitAction( ( Vertex ) this );

        // enqueues the vertices not visited
        for ( VertexIter vxiter = getNeighbors( ); vxiter.hasNext( ); )
        {
            v = vxiter.next( );

            // if your neighbor has not been visited then enqueue
            if ( !v.visited ) 
            {
                GlobalVarsWrapper.Queue.add( v );
            }

        } // end of for

        // while there is something in the queue
        while( GlobalVarsWrapper.Queue.size( )!= 0 )
        {
            header = ( Vertex ) GlobalVarsWrapper.Queue.get( 0 );
            GlobalVarsWrapper.Queue.remove( 0 );
            header.nodeSearch( w );
        }
    }

	
    public void addWeight( Vertex end, int theWeight ) 
    {
        Neighbor the_neighbor = 
                ( Neighbor ) ( end.adjacentNeighbors ).removeLast();
        the_neighbor.weight = theWeight;
        ( end.adjacentNeighbors ).add( the_neighbor );
    }


}
