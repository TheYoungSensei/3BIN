using System;
using System.Collections.Generic;
using System.Collections.ObjectModel;
using System.ComponentModel;
using System.Linq;
using System.Text;
using System.Threading.Tasks;
using System.Windows;
using WpfApplication1.Models;

namespace WpfApplication1.ViewModels
{
    public class EmployeeVM : INotifyPropertyChanged
    {
        private NorthwindEntities dc = new NorthwindEntities();
    


        // Property changed standard handling
        public event PropertyChangedEventHandler PropertyChanged; // La view s'enregistera automatiquement sur cet event
        protected virtual void OnPropertyChanged(string propertyName)
        {
            if (PropertyChanged != null)
            {
                PropertyChanged(this, new PropertyChangedEventArgs(propertyName)); // On notifie que la propriété a changé
            }
        }

        private EmployeeModel _selectedEmployee;
        private ObservableCollection<EmployeeModel> _EmployeesList;
        private ObservableCollection<OrderModel> _OrdersList;

        private List<string> _listTitle; 

        public ObservableCollection<EmployeeModel> EmployeesList
        {
            get { 
                    if(_EmployeesList==null)
                    {
                        _EmployeesList = loadEmployee();
                    }
                    
                    return _EmployeesList;
            
            }
            
        }

        public ObservableCollection<OrderModel> OrdersList
        {
            get
            {
                if (SelectedEmployee != null)
                {
                    _OrdersList = loadOrders();
                }

                return _OrdersList;

            }
            
        }
  
        private ObservableCollection<EmployeeModel> loadEmployee()
        {
            ObservableCollection<EmployeeModel> localCollection = new ObservableCollection<EmployeeModel>();
            foreach (var item in dc.Employees)
            {
                localCollection.Add(new EmployeeModel(item));
                
            }
            
            return localCollection;
            
        }

        private ObservableCollection<OrderModel> loadOrders()
        {
            ObservableCollection<OrderModel> localCollection = new ObservableCollection<OrderModel>();
            var query = from Order o in dc.Orders
                        where (o.EmployeeID == SelectedEmployee.MonEmployee.EmployeeID)
                        orderby o.OrderDate descending
                            select o;
                       
            

            int i = 0;
            foreach (var item in query)
            {
                decimal total = dc.Order_Details.Where(od => od.OrderID == item.OrderID).Sum(od => od.UnitPrice);
                localCollection.Add(new OrderModel(item, total));
                i++;
                if (i==3) break;
            }

            return localCollection;

        }


        public List<string> ListTitle
        {
            get { return _listTitle = _listTitle ?? LoadTitleOfCourtesy(); }

        }

        private List<string> LoadTitleOfCourtesy()
        {
            return dc.Employees.Select(e => e.TitleOfCourtesy).Distinct().ToList();
        }

        public EmployeeModel SelectedEmployee
        {
            get { return _selectedEmployee; }
            set { _selectedEmployee = value; OnPropertyChanged("OrdersList"); } 

        }

        private DelegateCommand _addCommand;
        private DelegateCommand _saveCommand;

        public DelegateCommand SaveCommand
        {
            get { return _saveCommand = _saveCommand ?? new DelegateCommand(SaveEmployee); }
        }

        public DelegateCommand AddCommand
        {
            get
            {
                 return _addCommand = _addCommand ?? new DelegateCommand(AddEmployee); }

        }

        private void AddEmployee()
        {
            Employee EGlobal = new Employee();
            EmployeeModel EModel = new EmployeeModel(EGlobal);
            EmployeesList.Add(EModel);
            SelectedEmployee = EModel;
        }

        private void SaveEmployee()
        {

      
            Employee verif = dc.Employees.Where(e => e.EmployeeID == SelectedEmployee.MonEmployee.EmployeeID).SingleOrDefault();
            if (verif == null)
            {
                dc.Employees.Add(SelectedEmployee.MonEmployee);
            }

            dc.SaveChanges();
            MessageBox.Show("Enregistrement en base de données fait");


        }
    }
}
