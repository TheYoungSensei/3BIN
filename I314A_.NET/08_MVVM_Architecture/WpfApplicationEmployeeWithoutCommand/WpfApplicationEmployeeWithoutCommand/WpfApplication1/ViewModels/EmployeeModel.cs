using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using WpfApplication1.Models;
using System.Threading.Tasks;

namespace WpfApplication1.ViewModels
{
    class EmployeeModel
    {
        private readonly Employees _employee;

        public Employees MonEmployee
        {
            get
            {
                return _employee;
            }
        }

        public EmployeeModel (Employees employee)
        {
            _employee = employee;
        }

        public string FullName
        {
            get
            {
                return String.Format("{0} {1}", _employee.FirstName, _employee.LastName);
            }
        }

        private string DateToString(DateTime date)
        {
            return String.Format("{0}/{1}/{2}",
                    date.Day, date.Month,
                    date.Year);
        }

        private string AmericanDateToString(DateTime date)
        {
            return String.Format("{0}/{1}/{2}", date.Month, date.Day, date.Year);
        }

        public string DisplayBirthDate
        {
            get
            {
                return DateToString(_employee.BirthDate.Value);
            }
        }

        public string LastName
        {
            get
            {
                return _employee.LastName;
            }
            set
            {
                _employee.LastName = value;
            }
        }

        public string FirstName
        {
            get
            {
                return _employee.FirstName;
            }
            set
            {
                _employee.FirstName = value;
            }
        }

        public string BirthDate
        {
            get
            {
                return AmericanDateToString(_employee.BirthDate.Value);
            }
            set
            {
                _employee.BirthDate = DateTime.Parse(value);
            }
        }

        public string HireDate
        {
            get
            {
                return AmericanDateToString(_employee.HireDate.Value);
            }
            set
            {
                _employee.HireDate = DateTime.Parse(value);
            }
        }

        public string TitleOfCourtesy
        {
            get
            {
                return _employee.TitleOfCourtesy;
            }
            set
            {
                _employee.TitleOfCourtesy = value;
            }
        }


    }
}
