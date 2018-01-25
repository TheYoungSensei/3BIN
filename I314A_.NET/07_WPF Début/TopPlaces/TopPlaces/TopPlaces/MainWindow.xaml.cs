using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;
using System.Windows;
using System.Windows.Controls;
using System.Windows.Data;
using System.Windows.Documents;
using System.Windows.Input;
using System.Windows.Media;
using System.Windows.Media.Animation;
using System.Windows.Media.Imaging;
using System.Windows.Navigation;
using System.Windows.Shapes;

namespace TopPlaces
{
    /// <summary>
    /// Logique d'interaction pour MainWindow.xaml
    /// </summary>
    public partial class MainWindow : Window
    {
        
    

        public MainWindow()
        {
            InitializeComponent();

            PlacesData placesData = new PlacesData();
            this.listBoxPhotos.DataContext = placesData.PlacesList;


            
           
        }



        private void photosList_SelectionChanged(object sender, SelectionChangedEventArgs e)
        {
           
                Place place = (Place)listBoxPhotos.SelectedItem;
                //MessageBox.Show(path);
                BitmapSource photo = BitmapFrame.Create(new Uri(place.Path));
                Image1.Source = photo;

                
                
            
        

        }

        private void Button_Click(object sender, RoutedEventArgs e)
        {
            Place place = (Place)listBoxPhotos.SelectedItem;
            place.Vote();
            //this.listBoxPhotos.Items.Refresh();
        }
    }
}
