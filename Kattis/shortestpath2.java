//Nathaell Avril Leman 
//Solution to shortestpath2 on kattis
import java.util.*;

public class RP4 {
    public static int oo = 987654321;
    public static void main(String Args[]) throws Exception{
        Scanner sc = new Scanner(System.in);

        //Reads in the first case.
        int n = sc.nextInt(); //Number of nodes
        int m = sc.nextInt(); //Number of graphs
        int q = sc.nextInt(); //Number of edges
        int s = sc.nextInt(); //Index of starting node

        //Loop until we get 0 0 0 0, or just when n is 0.
        while(n != 0){

            //Creates the list of vertexes.
            vertex[] nodes = new vertex[n];
            for(int i = 0; i < n; i++){
                nodes[i] = new vertex(i); //Initializes vertexes.
            }

            //Read in the data for each query.
            for(int i = 0; i < m; i++){
                int u  = sc.nextInt(); //Initial vertex
                int v  = sc.nextInt(); //Destination vertex
                int t0 = sc.nextInt(); //Start time
                int p  = sc.nextInt(); //Increment
                int d  = sc.nextInt(); //Traversal time

                //Applies the data to a vertex in index u.
                nodes[u].adjacencies.add(new edge(nodes[v], d, t0, p)); 
            }
            
            //runs djikstra's algorithm on the graph.
            djikstra(nodes[s]);

            //Prints out the results of djikstra's algorithm.
            output(sc, q, nodes);

            n = sc.nextInt(); //Number of nodes
            m = sc.nextInt(); //Number of graphs
            q = sc.nextInt(); //Number of edges
            s = sc.nextInt(); //Index of starting node    
        }
            sc.close();
    }

    static void djikstra(vertex v){
        v.distance = 0;

        Comparator<vertex> comp = new Comparator<vertex>() {
            @Override
            public int compare(vertex o1, vertex o2){
                return Integer.compare(o1.distance, o2.distance);
            }
        };

        PriorityQueue<vertex> queue = new PriorityQueue<>(comp);
        queue.add(v);

        while(!queue.isEmpty()){
            vertex u = queue.poll();
            for(edge e : u.adjacencies){
                int dist = e.t0 + e.weight;

                if(u.distance > e.t0){
                    if(e.p == 0){
                        dist = oo;
                    }else{
                        int steps = 1 + (((u.distance - e.t0) -1) / e.p);
                        dist = e.t0 +steps * e.p + e.weight;
                    }
                }

                vertex c = e.target;
                if(dist < c.distance){
                    queue.remove(c);
                    c.distance = dist;
                    c.previous = u;
                    queue.add(c);
                }
            }
        }
    }
    static void output(Scanner sc, int q, vertex nodes[]){
        for (int i = 0; i < q; i++) {
            int query = sc.nextInt();
            int distance = nodes[query].distance;
            if(distance == oo){
                System.out.println("Impossible");
            }
            else{
                System.out.println(distance);
            }
        }
        System.out.println();

    }
}

class edge{
    public vertex target;
    public int weight;
    public int t0;
    public int p;

    public edge(vertex target, int weight, int t0, int p){
        this.target = target;
        this.weight = weight;
        this.t0 = t0;
        this.p = p;
    }
}

class vertex{
    public int index;
    public ArrayList<edge> adjacencies;
    public int distance;
    public vertex previous;
    public int oo = 987654321;

    public boolean visited;

    public vertex(int i){
        this.index = i;
        this.adjacencies = new ArrayList<>();
        this.distance = oo;
        this.previous = null;
        this.visited = false;
    }
}
