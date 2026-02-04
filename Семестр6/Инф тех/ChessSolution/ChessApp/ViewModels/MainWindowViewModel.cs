using ChessApp.Helpers;
using ChessLib.Interfaces;
using System;
using System.Collections.ObjectModel;
using System.ComponentModel;
using System.IO;
using System.Linq;
using System.Reflection;
using System.Runtime.CompilerServices;
using System.Text.RegularExpressions;
using System.Windows;

namespace ChessApp.ViewModels
{
    public class MainWindowViewModel : INotifyPropertyChanged
    {

        private string _assemblyPath = "";
        public string AssemblyPath
        {
            get => _assemblyPath;
            set
            {
                if (_assemblyPath == value) return;
                _assemblyPath = value;
                OnPropertyChanged();
                LoadAssemblyCommand.RaiseCanExecuteChanged();
            }
        }

        public RelayCommand LoadAssemblyCommand { get; }

        public ObservableCollection<Type> FigureTypes { get; } = new();
        public ObservableCollection<ParameterViewModel> ConstructorParameters { get; } = new();
        public ObservableCollection<MethodInfo> Methods { get; } = new();
        public ObservableCollection<ParameterViewModel> MethodParameters { get; } = new();

        private Type? _selectedType;
        public Type? SelectedType
        {
            get => _selectedType;
            set
            {
                if (_selectedType == value) return;
                _selectedType = value;
                OnPropertyChanged();

                ConstructorParameters.Clear();
                MethodParameters.Clear();
                Methods.Clear();

                if (_selectedType != null)
                    PrepareConstructorParameters();

                LoadMethods();
                ExecuteCommand.RaiseCanExecuteChanged();
            }
        }

        private MethodInfo? _selectedMethod;
        public MethodInfo? SelectedMethod
        {
            get => _selectedMethod;
            set
            {
                if (_selectedMethod == value) return;
                _selectedMethod = value;
                OnPropertyChanged();

                MethodParameters.Clear();
                if (_selectedMethod != null)
                    PrepareMethodParameters();

                ExecuteCommand.RaiseCanExecuteChanged();
            }
        }

        public RelayCommand ExecuteCommand { get; }

        public MainWindowViewModel()
        {
            LoadAssemblyCommand = new RelayCommand(
                _ => LoadAssembly(),
                _ => File.Exists(AssemblyPath)
            );

            ExecuteCommand = new RelayCommand(
                _ => Execute(),
                _ => CanExecute()
            );
        }
        
        private void LoadAssembly()
        {
            try
            {
                var asm = Assembly.LoadFrom(AssemblyPath);
                FigureTypes.Clear();

                var iface = typeof(IChessFigure);
                foreach (var t in asm.GetTypes()
                                     .Where(t => iface.IsAssignableFrom(t)
                                                 && !t.IsAbstract))
                {
                    FigureTypes.Add(t);
                }

                SelectedType = null;
                SelectedMethod = null;
            }
            catch (Exception ex)
            {
                MessageBox.Show($"Ошибка загрузки сборки:\n{ex.Message}", "Ошибка");
            }
        }

        private void LoadMethods()
        {
            Methods.Clear();
            if (SelectedType == null) return;

            foreach (var mi in SelectedType
                         .GetMethods(BindingFlags.Public | BindingFlags.Instance)
                         .Where(m => !m.IsSpecialName))
            {
                Methods.Add(mi);
            }
        }

        private void PrepareConstructorParameters()
        {
            ConstructorParameters.Clear();
            if (SelectedType == null) return;

            var ctor = SelectedType.GetConstructors().FirstOrDefault();
            if (ctor == null) return;

            foreach (var p in ctor.GetParameters())
            {
                var vm = new ParameterViewModel(p);
                vm.PropertyChanged += (_, __) => ExecuteCommand.RaiseCanExecuteChanged();
                ConstructorParameters.Add(vm);
            }
        }

        private void PrepareMethodParameters()
        {
            MethodParameters.Clear();
            if (SelectedMethod == null) return;

            foreach (var p in SelectedMethod.GetParameters())
            {
                var vm = new ParameterViewModel(p);
                vm.PropertyChanged += (_, __) => ExecuteCommand.RaiseCanExecuteChanged();
                MethodParameters.Add(vm);
            }
        }
        
        private bool CanExecute()
        {
            if (SelectedType == null || SelectedMethod == null)
                return false;
            
            if (ConstructorParameters.Any(p => string.IsNullOrWhiteSpace(p.Value)))
                return false;
            
            if (MethodParameters.Any(p => string.IsNullOrWhiteSpace(p.Value)))
                return false;

            return true;
        }
        
        private void Execute()
        {
            try
            {
                var ctor = SelectedType!.GetConstructors().First();
                var ctorArgs = ConstructorParameters
                    .Select(p => ConvertParameter(p))
                    .ToArray();
                var instance = ctor.Invoke(ctorArgs);

                var methodArgs = MethodParameters
                    .Select(p => ConvertParameter(p))
                    .ToArray();

                var result = SelectedMethod!.Invoke(instance, methodArgs);

                MessageBox.Show(
                    result?.ToString() ?? "Метод выполнен без возвращаемого значения",
                    "Результат"
                );
            }
            catch (Exception ex)
            {
                MessageBox.Show($"Ошибка при выполнении:\n{ex.Message}", "Ошибка");
            }
        }

        private object? ConvertParameter(ParameterViewModel vm)
        {
            var type = vm.ParameterInfo.ParameterType;
            var text = vm.Value.Trim();
            
            if (type == typeof(ValueTuple<int,int>) ||
                type == typeof((int row, int col)))
            {
                return ParseAlgebraic(text);
            }

            if (type.IsEnum)
            {
                return Enum.Parse(type, text, ignoreCase: true);
            }

            return Convert.ChangeType(text, type);
        }

        private static (int row, int col) ParseAlgebraic(string s)
        {
            if (!Regex.IsMatch(s, "^[a-h][1-8]$", RegexOptions.IgnoreCase))
                throw new FormatException($"Неверный формат «{s}». Ожидается от a1 до h8.");

            int col = char.ToLower(s[0]) - 'a';
            int row = (s[1] - '1');
            return (row, col);
        }

        #region INotifyPropertyChanged
        public event PropertyChangedEventHandler? PropertyChanged;
        protected void OnPropertyChanged([CallerMemberName] string? n = null) =>
            PropertyChanged?.Invoke(this, new PropertyChangedEventArgs(n));
        #endregion
    }
}
