using System.Windows.Input;

namespace ChessApp.Helpers
{
    public class RelayCommand : ICommand
    {
        public event EventHandler CanExecuteChanged;
        private readonly Action<object> _execute;
        private readonly Func<object, bool> _canExecute;

        public RelayCommand(Action<object> exec, Func<object, bool> canExec = null)
        {
            _execute = exec;
            _canExecute = canExec;
        }

        public bool CanExecute(object param) => _canExecute?.Invoke(param) ?? true;
        public void Execute(object param) => _execute(param);
        public void RaiseCanExecuteChanged() => CanExecuteChanged?.Invoke(this, EventArgs.Empty);
    }
}