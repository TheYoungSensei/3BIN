using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;
using System.Windows.Media.Imaging;

namespace TopPlaces
{
    class PlacesData
    {
        public IList<Place> PlacesList { get; }
       
        public PlacesData()
        {
            string pathProject = Environment.CurrentDirectory;
            Place p1 = new Place(pathProject+"/photos/bruxelles.jpg", "Bruxelles");
            Place p2 = new Place(pathProject + "/photos/amsterdam.jpg", "Amsterdam");
            Place p3 = new Place(pathProject + "/photos/berlin.jpg", "Berlin");
            Place p4 = new Place(pathProject + "/photos/moscou.jpg", "Moscou");
            Place p5 = new Place(pathProject + "/photos/paris.jpg", "Paris");
            Place p6 = new Place(pathProject + "/photos/newyork.jpg", "New York");

            PlacesList = new List<Place>
            {
                p1,
                p2,
                p3,
                p4,
                p5,
                p6
            };
        }
        

    }

    class Place
    {
        public string Description { get; set; }
        public string PathImageFile { get; set; }
        public int NbVotes { get; set; }
        public Uri Uri { get; set; }
        public BitmapFrame Image { get; set; }

        public Place(string path, string description)
        {
            Description = description;
            PathImageFile = path;
            NbVotes = 0;
            Uri = new Uri(PathImageFile);
            Image = BitmapFrame.Create(Uri);
        }

        public void Vote()
        {
            this.NbVotes += 1;
        }


    }
}
