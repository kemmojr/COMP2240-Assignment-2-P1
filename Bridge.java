import java.util.concurrent.Semaphore;

public class Bridge {
    private int bridgeLen = 20, stepLen = 5, steps = 0;
    private static Semaphore crossingPass;

    public Bridge(Semaphore s){
        crossingPass = s;
    }

    public void cross(FarmerThread f){//to initiate the thread crossing the bridge


        try {
            crossingPass.acquire();
            f.run();

        } catch (Exception e){
            System.out.println("Cross failed");
        }
    }
}
