
//This class that can store the information of virtual machine.

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class VmQueryInfo {

    private Long vmid;
    private String name;
    private Long pmid;
    private Long instance_type;

    public VmQueryInfo() {
    }

    public VmQueryInfo(Long vmid, String name, Long pmid, Long instance_type) {
        this.vmid = vmid;
        this.name = name;
        this.pmid = pmid;
        this.instance_type = instance_type;
    }

    public Long getInstance_type() {
        return instance_type;
    }

    public void setInstance_type(Long instance_type) {
        this.instance_type = instance_type;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Long getPmid() {
        return pmid;
    }

    public void setPmid(Long pmid) {
        this.pmid = pmid;
    }

    public Long getVmid() {
        return vmid;
    }

    public void setVmid(Long vmid) {
        this.vmid = vmid;
    }
}
