using OilProduction.Models;

namespace OilProduction
{

    public interface ILoader
    {
        event EventHandler OilLoaded;
        OilRigStatus LoadOil();
    }

    public enum OilRigStatus
    {
        Normal,
        Fire,
        LoadingOil
    }

    public class OilRig
    {
        private int maxOil = 50;
        private int oilNow = 0;
        private int oilInSecond = 25;
        private int allOilExtract = 0;
        
        private Mechanic mechanic = new Mechanic();
        private Loader loader = new Loader();
        private OilRigStatus status = OilRigStatus.Normal;
        
        public event EventHandler<int> OilExtracted;
        public event EventHandler<OilRigStatus> StatusChanged;

        private Random random = new Random();        

        public void StartDrilling()
        {
            while (true)
            {                
                if (random.Next(0, 50) < 10)
                {                    
                    status = OilRigStatus.Fire;                    
                    OnStatusChanged(status);
                    Thread.Sleep(3000);                    
                    oilNow = 0;
                    status = mechanic.FixPlatformFire();                    
                    OnStatusChanged(status);
                    continue;
                }

                oilNow += oilInSecond;

                if (oilNow >= maxOil)
                {                    
                    status = loader.LoadOil();
                    OnStatusChanged(status);                    
                    allOilExtract += maxOil;                    
                    oilNow = 0;
                    Thread.Sleep(2000);              
                    status = OilRigStatus.Normal;
                    OnStatusChanged(status);                    
                    OnOilExtracted(allOilExtract);
                }

                Thread.Sleep(2000);
            }
        }

        public int getOilExtract()
        {
            return allOilExtract;
        }                

        protected virtual void OnOilExtracted(int oilVolume)
        {
            OilExtracted?.Invoke(this, oilVolume);
        }

        protected virtual void OnStatusChanged(OilRigStatus newStatus)
        {
            StatusChanged?.Invoke(this, newStatus);
        }
    }
}