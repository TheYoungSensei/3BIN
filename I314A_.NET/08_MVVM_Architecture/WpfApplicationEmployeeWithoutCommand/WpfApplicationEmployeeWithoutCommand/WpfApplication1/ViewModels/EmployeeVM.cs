using System;
using System.Collections.Generic;
using System.Collections.ObjectModel;
using System.Linq;
using System.Text;
using System.Threading.Tasks;
using WpfApplication1.Models;

namespace WpfApplication1.ViewModels
{
    class EmployeeVM
    {

        private Northwind northwind = new Northwind();
        private ObservableCollection<EmployeeModel> _EmployeesList;

        public ObservableCollection<EmployeeModel> EmployeesList
        {
            get
            {
                if(_EmployeesList == null)
                {
                    _EmployeesList = loadEmployees();
                }
                return _EmployeesList;
            }
        }

        private ObservableCollection<EmployeeModel> loadEmployees()
        {
            ObservableCollection<EmployeeModel> collection = new ObservableCollection<EmployeeModel>();
            foreach (Employees empl in northwind.Employees)
            {
                collection.Add(new EmployeeModel(empl));
            }
            return collection;
        }

        private IList<String> _ListTitles;

        public IList<String> ListTitle
        {
            get
            {
                if(_ListTitles == null)
                {
                    _ListTitles = loadTitlesOfCourtesy();
                }
                return _ListTitles;
            }
        }

        private IList<String> loadTitlesOfCourtesy()
        {
            return northwind.Employees.Select(e => e.TitleOfCourtesy).Distinct().ToList();
        }

    }
}
