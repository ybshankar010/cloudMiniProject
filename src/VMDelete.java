//Class that destroys connections 

import org.libvirt.Connect;
import org.libvirt.Domain;
import org.libvirt.LibvirtException;

public class VMDelete {

    public static int deleteVM(long vmid) {
        try {

            Integer id = Helper.vmDomain.get(vmid);
            Long pmid = Helper.vmPID.get(new Long(vmid));
            String hypervisor = Helper.pidConnect.get(pmid);  //Get the connection from "pidConnect" hashmap.
            String ip = Helper.nodePID.get(pmid);
            Connect conn = new Connect(hypervisor + "+ssh://" + ip + "/system", false);
            Domain d = conn.domainLookupByID(id);
            d.destroy();


            //update the list of available resources.
            Long type = Helper.vmType.get(vmid);
            System.out.println(type);
            NodeInfoClass temp = Helper.connectionTypes.get(type);
            NodeInfoClass ntemp = Helper.resourcesAvailable.get(pmid);         //updating the resources available in a particular node.
            ntemp.setCpu(ntemp.getCpu() + temp.getCpu());
            ntemp.setRam(ntemp.getRam() + (temp.getRam() * Helper.mulRam));
            Helper.resourcesAvailable.remove(pmid);
            Helper.resourcesAvailable.put(pmid, ntemp);


            System.out.println(Helper.resourcesAvailable.get(pmid).getCpu());
            //Stores the value of "VMID"  and its information in hashmaps.
            Helper.vmType.remove(vmid);
            Helper.vmDomain.remove(vmid);
            Helper.vmName.remove(vmid);
            Helper.vmPID.remove(vmid);
            return 1;

        } catch (LibvirtException ex) {
        }
        return 0;
    }
}
