using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;
using System.Windows;
using System.Windows.Input;

namespace WpfApplication1.ViewModels
{

    public class DelegateCommand : ICommand
    {
        private Action _executeMethod;
        public DelegateCommand(Action executeMethod)
        {
            _executeMethod = executeMethod;
        }
        public bool CanExecute(object parameter)
        {
            return true;
        }
        public event EventHandler CanExecuteChanged;
        public void Execute(object parameter)
        {
            _executeMethod.Invoke();
        }
    }
}
