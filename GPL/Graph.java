package GPL; 

import java.util.LinkedList; 
import java.util.Iterator; 
import java.util.Collections; 
import java.util.Comparator; 

// **********************************************************************

public   class  Graph {
	
    private LinkedList vertices;

	
    private LinkedList edges;

	
    public static final boolean isDirected = true;

	

    public Graph() {
        vertices = new LinkedList();
        edges = new LinkedList();
    }

	

    // Fall back method that stops the execution of programs
     private void  run__wrappee__DirectedWithEdges  ( Vertex s ) {}

	
    // Executes Number Vertices
    public void run( Vertex s )
     {
       	System.out.println("Number");
        NumberVertices( );
        run__wrappee__DirectedWithEdges( s );
    }

	

    public void sortEdges(Comparator c) {
        Collections.sort(edges, c);
    }

	

    public void sortVertices(Comparator c) {
        Collections.sort(vertices, c);
    }

	

    // Adds an edge without weights if Weighted layer is not present
    public EdgeIfc addEdge(Vertex start,  Vertex end) {
        Edge theEdge = new  Edge();
        theEdge.EdgeConstructor( start, end );
        edges.add( theEdge );
        start.addNeighbor( new  Neighbor( end, theEdge ) );
        //end.addNeighbor( new  Neighbor( start, theEdge ) );

        return theEdge;
    }

	

    protected void addVertex( Vertex v ) {
        vertices.add( v );
    }

	

    // Finds a vertex given its name in the vertices list
    public  Vertex findsVertex( String theName )
      {
        Vertex theVertex = null;

        // if we are dealing with the root
        if ( theName==null )
            return null;

        for(VertexIter vxiter = getVertices(); vxiter.hasNext(); )
        {
            theVertex = vxiter.next();
            if ( theName.equals( theVertex.getName() ) )
                return theVertex;
        }

        return theVertex;
    }

	

    public VertexIter getVertices() {
        return new VertexIter() {
                private Iterator iter = vertices.iterator();
                public Vertex next() { return (Vertex)iter.next(); }
                public boolean hasNext() { return iter.hasNext(); }
            };
    }

	


    public EdgeIter getEdges() {
        return new EdgeIter() {
                private Iterator iter = edges.iterator();
                public EdgeIfc next() { return (EdgeIfc)iter.next(); }
                public boolean hasNext() { return iter.hasNext(); }
            };
    }

	

    public void display() {
        int i;

        System.out.println( "******************************************" );
        System.out.println( "Vertices " );
        for ( VertexIter vxiter = getVertices(); vxiter.hasNext() ; )
            vxiter.next().display();

        System.out.println( "******************************************" );
        System.out.println( "Edges " );
        for ( EdgeIter edgeiter = getEdges(); edgeiter.hasNext(); )
            edgeiter.next().display();

        System.out.println( "******************************************" );
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
            return; // if there are no vertices return
        }

        // Initializing the vertices
        while( vxiter.hasNext( ) ) 
        {
            Vertex v = vxiter.next( );
            v.init_vertex( w );
        }

        // Step 2: traverse neighbors of each node
        for( vxiter = getVertices( ); vxiter.hasNext( ); ) 
        {
            Vertex v = vxiter.next( );
            if ( !v.visited ) 
            {
                w.nextRegionAction( v );
                v.nodeSearch( w );
            }
        } 
    }


}
