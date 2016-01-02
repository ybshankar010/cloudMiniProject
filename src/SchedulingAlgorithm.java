
import java.util.Iterator;

//Applying best fit algorithm for scheduling,over round robin scheduling
public class SchedulingAlgorithm {

    //returns the best PMID which is best to serve the request.
    public static Long doSchedule(long requested_type) {

        NodeInfoClass requested = Helper.connectionTypes.get(requested_type);
        long pmid = 0, l_cpus = Long.MAX_VALUE, l_ram = Long.MAX_VALUE;
        Iterator iter = Helper.resourcesAvailable.keySet().iterator();
        Long key = new Long(0);
        NodeInfoClass temp = new NodeInfoClass();

        //initial Iteration for getting the PMID using best fit.
        while (iter.hasNext()) {
            key = (Long) iter.next();
            if (!(Helper.vmsAssigned.contains(key))) {
                temp = Helper.resourcesAvailable.get(key);
                if ((temp.getRam().longValue() >= (requested.getRam().longValue() * Helper.mulRam)) && (temp.getCpu().longValue() >= requested.getCpu().longValue())) {
                    System.out.println("-> -> In scheduling..." + pmid);
                    if ((l_cpus > temp.getCpu().longValue()) && (l_ram > temp.getRam().longValue())) {
                        l_cpus = temp.getCpu();
                        l_ram = temp.getRam();
                        pmid = key.longValue();
                    }
                }
            }
        }

        //If we donot get the correct ip in the inital loop we will go to this one.
        if (pmid == 0) {
            for (int i = 0; i < Helper.vmsAssigned.size(); i++) {
                key = Helper.vmsAssigned.get(i);
                temp = Helper.resourcesAvailable.get(key);
                if ((temp.getRam().longValue() >= (requested.getRam().longValue() * Helper.mulRam)) && (temp.getCpu().longValue() >= requested.getCpu().longValue())) {
                    System.out.println("-> In scheduling vector..." + pmid);
                    if ((l_cpus > temp.getCpu().longValue()) && (l_ram > temp.getRam().longValue())) {
                        l_cpus = temp.getCpu();
                        l_ram = temp.getRam();
                        pmid = key.longValue();
                    }
                }
            }
            if(Helper.vmsAssigned.size()==Helper.nodePID.size())
                Helper.vmsAssigned.clear();
        } else {
            Helper.vmsAssigned.add(pmid);
        }
        System.out.println("After Scheduling..." + pmid);
        return pmid;
    }
}
