using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;
using System.Windows.Media.Imaging;
using System.IO;
using System.Windows;
using System.Reflection;

namespace TopPlaces
{
    public class PlacesData
    {
        private IList<Place> placesList;

        public PlacesData()
        {

            string pathProject = Environment.CurrentDirectory;
            Place p1 = new Place(pathProject +"/photos/bruxelles.jpg", "Bruxelles");
            Place p2 = new Place(pathProject + "/photos/paris.jpg", "Paris");
            Place p3 = new Place(pathProject + "/photos/moscou.jpg", "Moscou");
            Place p4 = new Place(pathProject + "/photos/amsterdam.jpg", "Amsterdam");
            Place p5 = new Place(pathProject + "/photos/newyork.jpg", "New York");

            placesList = new List<Place>();
            placesList.Add(p1);
            placesList.Add(p2);
            placesList.Add(p3);
            placesList.Add(p4);
            placesList.Add(p5);
            
        }

        public IList<Place> PlacesList
        {
            get { return placesList; }
        }
    }

    public class Place
    {
        private string _description;
        private string _pathImageFile;
        private int _nbVotes;
        private Uri _uri;
        private BitmapFrame _image;

        public Place(string path,string description)
        {
            _description = description;
            _pathImageFile = path;
            _nbVotes = 0;
            _uri = new Uri(_pathImageFile);
            _image = BitmapFrame.Create(_uri);
        }

        public string Description
        {
            get { return _description; }
            set { _description = value; }
        }

        public string Path
        {
            get { return _pathImageFile; }
            set { _pathImageFile = value; }
        }

        public int NbVotes
        {
            get { return _nbVotes; }
        }

        public void Vote()
        {
            _nbVotes++;
        }

        public BitmapFrame Image { get { return _image; } }
    }
}
