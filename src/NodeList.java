
//Stores the node information from "machines" file
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;
import org.libvirt.Connect;
import org.libvirt.LibvirtException;
import org.libvirt.NodeInfo;

public class NodeList {

    /* public static void getNodeInfo() throws IOException {
    Connect conn = null;
    NodeInfo nInfo = null;
    NodeInfoClass temp;
    int count = 0;

    while ((line = seek.readLine()) != null) {
    temp = new NodeInfoClass();
    count++;
    Helper.nodePID.put(new Long(count), line);
    try {
    conn = new Connect("qemu+ssh://" + line + "/system", false);
    nInfo = conn.nodeInfo();
    temp.setTid(new Long(count));
    temp.setCpu(new Long(nInfo.maxCpus()));
    temp.setRam(nInfo.memory);
    Helper.nodeInfo.put(new Long(count), temp);
    } catch (LibvirtException ex) {
    ex.printStackTrace();
    }
    }

    /*seek.seek(0);
    while ((line = seek.readLine()) != null) {
    myNode = Helper.nodeInfo.get(line);
    System.out.println(myNode.getCpu() + " " + myNode.getRam());
    }*/
    //}
    public static void getConnectionInfo(String file) throws FileNotFoundException, IOException, LibvirtException {

        RandomAccessFile seek = new RandomAccessFile(file, "r");
        String[] hypervisorType = {"xen", "qemu"};
        Connect conn = null;
        long pid = 0;
        String ip;
        NodeInfo nInfo = null;
        NodeInfoClass myNode;

        while ((ip = seek.readLine()) != null) {
            myNode = new NodeInfoClass();
            pid++;
            for (int i = 0; i < hypervisorType.length; i++) {
                conn = null;
                try {
                    conn = new Connect(hypervisorType[i] + "+ssh://" + ip + "/system", false);
                    Helper.pidConnect.put(new Long(pid), hypervisorType[i]);
                    break;
                } catch (LibvirtException ex) {
                    continue;
                }
            }
            nInfo = conn.nodeInfo();
            myNode.setTid(new Long(pid));
            myNode.setCpu(new Long(conn.getMaxVcpus(Helper.pidConnect.get(new Long(pid)))));
            myNode.setRam(nInfo.memory);
            Helper.nodePID.put(new Long(pid), ip);
            Helper.nodeInfo.put(new Long(pid), myNode);
        }
    }
}
