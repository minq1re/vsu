using System.ComponentModel;
using System.Runtime.CompilerServices;

namespace WpfApp1
{
    public class MainViewModel : INotifyPropertyChanged
    {
        private Book Book;
        private RelayCommand changeStateIssuedCommand;
        private RelayCommand changeStateInStockCommand;
        private RelayCommand changeStateUnderRestorationCommand;
        private RelayCommand changeCodeCommand;
        private string inputText;

        public MainViewModel()
        {
            // Инициализация команд
            ChangeCodeCommand = new RelayCommand(ChangeCode, CanChangeCode);
            ChangeStateIssuedCommand = new RelayCommand(ChangeStateIssued, CanChangeState);
            ChangeStateInStockCommand = new RelayCommand(ChangeStateInStock, CanChangeState);
            ChangeStateUnderRestorationCommand = new RelayCommand(ChangeStateUnderRestoration, CanChangeState);
            
            Book = new Book("123456", "Harry Potter",  "Author", 500, "Fantasy", BookState.InStock);
            
        }
        public string LibraryCode
        {
            get { return Book.LibraryCode; }
        }
        public string Title
        {
            get { return Book.Title; }
        }

        public string Authors
        {
            get { return Book.Authors; }
        }

        public int PageCount
        {
            get {return Book.PageCount; }
        }

        public string Theme
        {
            get { return Book.Theme; }
        }
        public BookState State
        {
            get { return Book.State; }
        }
        
        public string InputText
        {
            get { return inputText; }
            set
            {
                inputText = value;
                OnPropertyChanged(nameof(InputText));
                ChangeCodeCommand.RaiseCanExecuteChanged();
            }
        }
       
       public RelayCommand ChangeStateIssuedCommand
       {
           get { return changeStateIssuedCommand; }
           set
           {
               changeStateIssuedCommand = value;
               OnPropertyChanged(nameof(ChangeStateIssuedCommand));
           }
       }

       public void ChangeStateIssued(object parameter)
       {
           Book.ChangeState(BookState.Issued);
           OnPropertyChanged(nameof(State));
       }
       
        private bool CanChangeState(object obj)
        {
            return true; // additional conditions can be added
        }
        
        public RelayCommand ChangeStateInStockCommand
        {
            get { return changeStateInStockCommand; }
            set
            {
                changeStateInStockCommand = value;
                OnPropertyChanged(nameof(ChangeStateInStockCommand));
            }
        }

        public void ChangeStateInStock(object parameter)
        {
            Book.ChangeState(BookState.InStock);
            OnPropertyChanged(nameof(State));
        }
       
        
        public RelayCommand ChangeStateUnderRestorationCommand
        {
            get { return changeStateUnderRestorationCommand; }
            set
            {
                changeStateUnderRestorationCommand = value;
                OnPropertyChanged(nameof(ChangeStateUnderRestorationCommand));
            }
        }

        public void ChangeStateUnderRestoration(object parameter)
        {
            Book.ChangeState(BookState.UnderRestoration);
            OnPropertyChanged(nameof(State));
        }
       
       

        public RelayCommand ChangeCodeCommand
        {
            get { return changeCodeCommand; }
            set
            {
                changeCodeCommand = value;
                OnPropertyChanged(nameof(ChangeCodeCommand));
            }
        }
        
        private bool CanChangeCode(object obj)
        {
            return true; // additional conditions can be added
        }

        private void ChangeCode(object parameter)
        {
            string value = InputText;
            Book.ChangeLibraryCode(value);
            OnPropertyChanged(nameof(LibraryCode));
            
        }

        public event PropertyChangedEventHandler PropertyChanged;

        protected virtual void OnPropertyChanged([CallerMemberName] string propertyName = null)
        {
            PropertyChanged?.Invoke(this, new PropertyChangedEventArgs(propertyName));
        }
    }
}