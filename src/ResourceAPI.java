
// Class that serves the requests of users
import java.util.Iterator;
import java.util.Vector;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;

@Path("/pm/")
public class ResourceAPI {

    @GET
    @Path("/list")
    @Produces("application/json")
    public MyStringClass list_pms() {

        MyStringClass pmids = new MyStringClass();
        pmids.setPmids(Helper.nodeInfo.keySet().toString());
        return pmids;
    }

    @GET
    @Path("/{pmid}/listvms")
    @Produces("application/json")
    public MyVMinfo list_vms(@PathParam("pmid") long pmid) {
        Vector<Long> vmid = new Vector<Long>();

        for (Iterator i = Helper.vmPID.keySet().iterator(); i.hasNext();) {
            Long key = (Long) i.next();

            if (Helper.vmPID.get(key).equals(new Long(pmid))) {
                vmid.add(key);
            }
        }
        MyVMinfo vmids = new MyVMinfo();
        vmids.setVmids(vmid.toString());
        return vmids;
    }

    @GET
    @Path("/{pmid}")
    @Produces("application/json")
    public PMQueryInfo pm_query(@PathParam("pmid") long pmid) {
        int vmCount = 0;

        for (Iterator i = Helper.vmPID.keySet().iterator(); i.hasNext();) {
            Long key = (Long) i.next();
            if (Helper.vmPID.get(key).equals(new Long(pmid))) {
                vmCount++;
            }
        }

        NodeInfoClass present = Helper.nodeInfo.get(pmid);
        NodeInfoClass free = Helper.resourcesAvailable.get(pmid);

        PMQueryInfo query=new PMQueryInfo();
        query.setPmid(pmid);
        query.setCapacity(present);
        query.setFree(free);
        query.setVms(vmCount);

        return query;
    }
}
