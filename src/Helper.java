
import java.util.HashMap;
import java.util.Vector;

public class Helper {

    //Stores the information of all connections present.
    public static HashMap<Long, NodeInfoClass> connectionTypes = new HashMap<Long, NodeInfoClass>();
    //Stores the information of all the images stored.
    public static HashMap<Long, Images> imgTypes = new HashMap<Long, Images>();
    //Stores the PMID and corresponding node information.
    public static HashMap<Long, NodeInfoClass> nodeInfo = new HashMap<Long, NodeInfoClass>();
    //Stores the PMID and correspoding node name.
    public static HashMap<Long, String> nodePID = new HashMap<Long, String>();
    //Stores the values of the pid and the connection corresponding to "PMID"
    public static HashMap<Long, String> pidConnect = new HashMap<Long, String>();
    //Stores "VMID" and "PMID"
    public static HashMap<Long, Long> vmPID = new HashMap<Long, Long>();
    //Stores "VMID" and "type of the vm created"
    public static HashMap<Long, Long> vmType = new HashMap<Long, Long>();
    //Stores "VMID" and "NAME of the vm created"
    public static HashMap<Long, String> vmName = new HashMap<Long, String>();
    //Stores "VMID" and "DOMAIN of the vm created"
    public static HashMap<Long, Integer> vmDomain = new HashMap<Long, Integer>();
    //Stores the values of the "PMID" and corresponding RequestTypes.
    public static HashMap<Long, NodeInfoClass> resourcesAvailable = new HashMap<Long, NodeInfoClass>();
    //Stores the sequence of "VMID"
    public static long vmid = 1;
    //number that we need to multiply
    public static final int mulRam = 20;
    //A global vector that stores all the nodes that are assigned with vms
    public static Vector<Long> vmsAssigned = new Vector<Long>();
}
