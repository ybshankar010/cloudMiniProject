
// Redirects the request from the Request.
import java.util.Iterator;
import java.util.Vector;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Response;

@Path("/vm/")
public class Redirect {

    @GET
    @Path("/create")			//Function to create VM
    //i/p -> name,vm_type,img_type
    public Response vm_create(@QueryParam("name") String name,
            @QueryParam("vm_type") Long vm_type,
            @QueryParam("image_type") Long img_type) {
        //System.out.println("----invoking getCustomer, Customer name is: "+ name);

        //Create an object with the and establish the connection with the parameters.
        long vmid = CreateConnection.createConnection(name, vm_type, img_type);
        if (vmid == -1) {
            return Response.status(200).entity("Cannot create a VM..Sorry..!!for inconvinience").build();
        }
        return Response.status(200).entity(
                "VM is created with name..." + name + " VM_TYPE: " + vm_type + " IMAGE TYPE: "
                + img_type + " Corresponding vmid is :: " + vmid).build();
    }

    @GET
    @Path("/query")
    @Produces("application/json")
    public VmQueryInfo vm_query(@QueryParam("vmid") long vmid) {
        String name = Helper.vmName.get(vmid);
        Long type = Helper.vmType.get(vmid);
        Long pmid = Helper.vmPID.get(vmid);
        VmQueryInfo myquery = new VmQueryInfo(vmid, name, pmid, type);
        return myquery;
    }

    @GET
    @Path("/destroy")
    @Produces("application/json")
    public Status vm_destroy(@QueryParam("vmid") long vmid) {
        int status = VMDelete.deleteVM(vmid);
        Status s=new Status();
        s.setStatus(status);
        return s;
    }

    @GET
    @Path("/types")
    @Produces("application/json")
    public Vector<NodeInfoClass> vm_types() {

        Iterator iter = Helper.connectionTypes.keySet().iterator();
        Vector<NodeInfoClass> vec = new Vector<NodeInfoClass>();

        while (iter.hasNext()) {
            vec.add(Helper.connectionTypes.get((Long) iter.next()));
        }
        return vec;
    }
}
