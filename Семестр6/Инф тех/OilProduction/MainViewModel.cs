using System.Collections.ObjectModel;
using System.ComponentModel;
using System.Windows.Threading;

namespace OilProduction.ViewModel
{
    public class MainViewModel : INotifyPropertyChanged
    {
        public event PropertyChangedEventHandler PropertyChanged;
        private ObservableCollection<OilRigViewModel> oilRigs;
        private Dispatcher dispatcher;        
        private int numberOilRig = 0;

        public ObservableCollection<OilRigViewModel> OilRigs
        {
            get { return oilRigs; }
            set
            {
                oilRigs = value;
                OnPropertyChanged(nameof(OilRigs));
            }
        }

        public MainViewModel(Dispatcher dispatcher)
        {
            this.dispatcher = dispatcher;
            OilRigs = new ObservableCollection<OilRigViewModel>();            

            
        }

        public async Task InitializeOilRigsAsync()
        {
            await Task.Delay(100);
            OilRigs.Add(new OilRigViewModel(numberOilRig, dispatcher));
            numberOilRig++;

            OnPropertyChanged(nameof(numberOilRig));
        }

        

        protected virtual void OnPropertyChanged(string propertyName)
        {
            PropertyChanged?.Invoke(this, new PropertyChangedEventArgs(propertyName));
        }
    }    
}
