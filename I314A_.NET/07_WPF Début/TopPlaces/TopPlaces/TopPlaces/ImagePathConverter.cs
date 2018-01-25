using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;
using System.Windows.Data;

namespace TopPlaces
{
   
    public class ImagePathConverter : IValueConverter
    {
        public object Convert(object value, Type targetType, object parameter, System.Globalization.CultureInfo culture)
        {
            if (value == null) return "rien";
            // index début 
            int indexDebDirPhotos = value.ToString().IndexOf("/photos");
            return value.ToString().Substring(indexDebDirPhotos);
        }

        public object ConvertBack(object value, Type targetType, object parameter, System.Globalization.CultureInfo culture)
        {
            throw new NotImplementedException();
        }
    }
}
