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

        private void listBoxPhotos_SelectionChanged(object sender, SelectionChangedEventArgs e)
        {
            if (listBoxPhotos.SelectedItem == null) // Il existe surement une meilleure façon de le faire
                return;
            Place place = (Place)listBoxPhotos.SelectedItem;
            BitmapSource photo = BitmapFrame.Create(new Uri(place.PathImageFile));
            image1.Source = photo;
        }

        private void Button_Click(object sender, RoutedEventArgs e)
        {
            Place place = (Place)listBoxPhotos.SelectedItem; 
            place.Vote();
            listBoxPhotos.SelectedItem = null; // Il existe surement une meilleure façon de le faire
            listBoxPhotos.SelectedItem = place;
        }
    }
}
