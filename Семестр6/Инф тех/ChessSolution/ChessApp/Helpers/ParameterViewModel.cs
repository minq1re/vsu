using System.ComponentModel;
using System.Reflection;
using System.Runtime.CompilerServices;

namespace ChessApp.Helpers
{
    public class ParameterViewModel : INotifyPropertyChanged
    {
        public ParameterInfo ParameterInfo { get; }
        private string _value;

        public string Value
        {
            get => _value;
            set
            {
                _value = value;
                OnPropertyChanged();
            }
        }

        public ParameterViewModel(ParameterInfo pi)
        {
            ParameterInfo = pi;
        }

        public event PropertyChangedEventHandler? PropertyChanged;

        protected virtual void OnPropertyChanged([CallerMemberName] string? propertyName = null)
        {
            PropertyChanged?.Invoke(this, new PropertyChangedEventArgs(propertyName));
        }

        protected bool SetField<T>(ref T field, T value, [CallerMemberName] string? propertyName = null)
        {
            if (EqualityComparer<T>.Default.Equals(field, value)) return false;
            field = value;
            OnPropertyChanged(propertyName);
            return true;
        }
    }
}