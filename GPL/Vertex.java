package GPL; 

import java.util.LinkedList; 
import java.util.Iterator; 

  // *************************************************************************

public   class  Vertex {
	

    // dja: changed neighbors and name to public
    public LinkedList neighbors;

	

    public String name;

	

    public String getName() { return name; }

	

    public Vertex() {
        VertexConstructor();
    }

	

     private void  VertexConstructor__wrappee__DirectedWithEdges  () {
        name      = null;
        neighbors = new LinkedList();
    }

	

    public void VertexConstructor( ) 
    {
        VertexConstructor__wrappee__DirectedWithEdges( );
        visited = false;
    }

	

    public  Vertex assignName( String name ) {
        this.name = name;
        return ( Vertex ) this;
    }

	

    public void addNeighbor( Neighbor n ) {
        neighbors.add( n );
    }

	

    public VertexIter getNeighbors() {
        return new VertexIter() {
                private Iterator iter = neighbors.iterator();
                public Vertex next() { return ((Neighbor)iter.next()).end; }
                public boolean hasNext() { return iter.hasNext(); }
            };
    }

	

    public EdgeIter getEdges()
    {
        return new EdgeIter()
            {
                private Iterator iter = neighbors.iterator();
                /* dja: changed to fix compile error */
//                public EdgeIfc next() { return ((EdgeIfc)  iter.next()).edge; }
                public EdgeIfc next( ) 
                { 
                  return ( ( EdgeIfc ) ( ( Neighbor ) iter.next( ) ).edge ); 
                }
                public boolean hasNext() { return iter.hasNext(); }
            };
    }

	

     private void  display__wrappee__DirectedWithEdges  () {
        System.out.print( " Node " + getName() + " connected to: " );

        for(VertexIter vxiter = getNeighbors(); vxiter.hasNext(); )
         {
            Vertex v = vxiter.next();
            System.out.print( v.getName() + ", " );
        }

        System.out.println();
    }

	

     private void  display__wrappee__Number  ( ) 
    {
        System.out.print( " # "+ VertexNumber + " " );
        display__wrappee__DirectedWithEdges( );
    }

	 // of dftNodeSearch

    public void display( ) {
        if ( visited )
            System.out.print( "  visited" );
        else
            System.out.println( " !visited " );
        display__wrappee__Number( );
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


}
