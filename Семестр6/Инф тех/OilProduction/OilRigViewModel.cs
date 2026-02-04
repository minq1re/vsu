using System.ComponentModel;
using System.Windows.Threading;

namespace OilProduction
{
    public class OilRigViewModel : INotifyPropertyChanged
    {
        public event PropertyChangedEventHandler PropertyChanged;

        private int id;
        private OilRig oilRig;
        private Dispatcher dispatcher;

        public int Id
        {
            get { return id; }
            set
            {
                id = value;
                OnPropertyChanged(nameof(Id));
            }
        }

        public int OilExtract { get; private set; }
        public string StatusOil { get; private set; }

        public OilRigViewModel(int id, Dispatcher dispatcher)
        {
            this.dispatcher = dispatcher;
            Id = id;
            oilRig = new OilRig();
            oilRig.OilExtracted += (sender, e) =>
            {
                dispatcher.Invoke(() => OilExtract = oilRig.getOilExtract());
                OnPropertyChanged(nameof(OilExtract));
            };

            oilRig.StatusChanged += (sender, newStatus) =>
            {
                dispatcher.Invoke(() => StatusOil = newStatus.ToString());
                OnPropertyChanged(nameof(StatusOil));
            };

            Task.Run(() => oilRig.StartDrilling());
        }

        protected virtual void OnPropertyChanged(string propertyName)
        {
            PropertyChanged?.Invoke(this, new PropertyChangedEventArgs(propertyName));
        }
    }
}
