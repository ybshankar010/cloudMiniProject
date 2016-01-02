
import javax.xml.bind.annotation.XmlRootElement;

// Class that stores the infor that we obtained from parsing Vm_types(JSON)
@XmlRootElement
public class NodeInfoClass {

    private Long tid;
    private Long cpu;
    private Long ram;
    private Long disk;

    public void setCpu(Long cpu) {
        this.cpu = cpu;
    }

    public void setDisk(Long disk) {
        this.disk = disk;
    }

    public void setRam(Long ram) {
        this.ram = ram;
    }

    public void setTid(Long tid) {
        this.tid = tid;
    }

    public Long getCpu() {
        return cpu;
    }

    public Long getDisk() {
        return disk;
    }

    public Long getRam() {
        return ram;
    }

    public Long getTid() {
        return tid;
    }

    public NodeInfoClass() {
        tid = new Long(-1);
        cpu = new Long(-1);
        ram = new Long(-1);
        disk = new Long(-1);
    }

    public NodeInfoClass(Long tid, Long cpu, Long ram, Long disk) {
        this.tid = tid;
        this.cpu = cpu;
        this.ram = ram;
        this.disk = disk;
    }
}
