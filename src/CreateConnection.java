// Used to create the connection.

import java.util.logging.Level;
import java.util.logging.Logger;
import org.libvirt.*;

public class CreateConnection {

    public CreateConnection() {
        System.out.println("Connection establishing...!!");
    }

    //This function should return pid as it should be used by the user.
    public static long createConnection(String name, Long type, Long imgType) {
        long tmp = 0;
        try {
            //You have to get the value of PMID from Scheduling algorithm.
            Long pmid = SchedulingAlgorithm.doSchedule(type); // Create the connection
            if (pmid == 0) {
                return -1;
            }
            String hypervisor = Helper.pidConnect.get(pmid);  //Get the connection from "pidConnect" hashmap.
            String ip = Helper.nodePID.get(pmid), image;
            Images img = Helper.imgTypes.get(imgType);
            image = "/var/lib/libvirt/images/" + img.getName();
            System.out.println(hypervisor + "   " + ip);
            Connect conn = new Connect(hypervisor + "+ssh://" + ip + "/system", false);
            NodeInfoClass temp = Helper.connectionTypes.get(type);
            // Creating a Domain object
            Domain ob = conn.domainCreateLinux("<domain type='" + hypervisor + "'>" + " <name>" + name + "</name>" + "<memory>"
                    + (temp.getRam() * Helper.mulRam) + "</memory>" + " <vcpu>" + (temp.getCpu()) + "</vcpu>" + "<os>"
                    + " <type arch='x86_64' machine='pc'>hvm</type>" + "  <boot dev='hd'/>" + " </os> "
                    + "<features>" + "  <acpi/>" + "   <apic/>" + "  <pae/>" + " </features>"
                    + " <on_poweroff>destroy</on_poweroff>" + " <on_reboot>restart</on_reboot>"
                    + " <on_crash>restart</on_crash>" + " <devices>"
                    + " <emulator>/usr/bin/" + hypervisor + "-system-x86_64</emulator>"
                    + " <disk type='file' device='disk'>" + "<driver name='" + hypervisor + "' type='raw'/>"
                    + " <source file='" + image + "' /> " + "<target dev='hda' bus='ide'/>"
                    + " <address type='drive' controller='0' bus='0' unit='0'/>" + " </disk>"
                    + "  </devices>" + "</domain>", 0);


            //System.out.println(ob.getName());
            tmp = Helper.vmid++;

            //Stores the value of "VMID"  and its information in hashmaps.
            Helper.vmType.put(tmp, type);
            Helper.vmDomain.put(tmp, ob.getID());
            Helper.vmName.put(tmp, name);
            Helper.vmPID.put(tmp, pmid);

            NodeInfoClass ntemp = Helper.resourcesAvailable.get(pmid);         //updating the resources available in a particular node.
            ntemp.setCpu(ntemp.getCpu() - temp.getCpu());
            ntemp.setRam(ntemp.getRam() - (temp.getRam() * Helper.mulRam));
            Helper.resourcesAvailable.remove(pmid);
            Helper.resourcesAvailable.put(pmid, ntemp);

            //System.out.println(Helper.resourcesAvailable.get(pmid).getCpu());
            System.out.println("Fini!");
            return tmp;

        } catch (LibvirtException ex) {
            Logger.getLogger(CreateConnection.class.getName()).log(Level.SEVERE, null, ex);
        }
        return -1;
    }
}
