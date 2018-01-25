using System;
using WpfApplication1.Models;

namespace WpfApplication1.ViewModels
{
    public class OrderModel
    {
        private readonly Order _monOrder;
        private decimal total = 0;

         public OrderModel(Order current, decimal total)
            {
                this._monOrder = current;
                this.total = total;
            }

        public String OrderDate
        {
            get
            {
                if (_monOrder.OrderDate.HasValue)
                {
                    return _monOrder.OrderDate.Value.ToShortDateString();
                }

                return "";
            }
        }

     

        public String OrderID
        {
            get
            {
                return _monOrder.OrderID.ToString();

            }
        }

        public String OrderTotal
        {
            get
            {
                return total.ToString();

            }
        }
    }
}
