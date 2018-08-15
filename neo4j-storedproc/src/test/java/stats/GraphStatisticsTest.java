package stats;

import java.io.File;
import java.util.*;
import java.util.stream.*;
import org.neo4j.graphdb.*;
import org.neo4j.graphdb.factory.GraphDatabaseFactory;
import org.neo4j.graphdb.factory.GraphDatabaseSettings;
import org.neo4j.kernel.internal.GraphDatabaseAPI;
import org.neo4j.procedure.*;
import org.junit.*;
import static org.junit.Assert.*;
import org.neo4j.kernel.api.proc.CallableProcedure;
import org.neo4j.kernel.configuration.Config;
import org.neo4j.kernel.impl.proc.ProcedureConfig;
import org.neo4j.kernel.impl.proc.Procedures;
import stats.GraphStatistics;

public class GraphStatisticsTest {
	
    @Test public void testDegree() throws Exception {
 
		String dbLocation = "data/stats";		
		//GraphDatabaseService graphDb = new GraphDatabaseFactory().newEmbeddedDatabaseBuilder(new File(dbLocation)).newGraphDatabase();
		GraphDatabaseService db = new GraphDatabaseFactory().newEmbeddedDatabaseBuilder(new File(dbLocation)).setConfig(GraphDatabaseSettings.pagecache_memory, "30M").newGraphDatabase();
	
	   ((GraphDatabaseAPI)db).getDependencyResolver().resolveDependency(Procedures.class).registerProcedure(GraphStatistics.class, true);
        // given Alice knowing Bob and Charlie and Dan knowing no-one
        db.execute("CREATE (alice:User)-[:KNOWS]->(bob:User),(alice)-[:KNOWS]->(charlie:User),(dan:User)").close();

        // when retrieving the degree of the User label
        Result res = db.execute("CALL stats.degree('User')");
        
        // then we expect one result-row with min-degree 0 and max-degree 2
        assertTrue(res.hasNext());
        Map<String,Object> row = res.next();
        assertEquals("User", row.get("label"));
        // Dan has no friends
        assertEquals(new Long(0), row.get("min"));
        // Alice knows 2 people
        assertEquals(new Long(2), row.get("max"));
        // We have 4 nodes in our graph
        //assertEquals(new Long(4), row.get("count"));
        // only one result record was produced
        assertFalse(res.hasNext());
               
        
        System.out.println("TestDegree() function executed");
    }
}
