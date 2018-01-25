using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;

namespace LINQDataContext
{
    public class Course
    {
        public string Course_ID { get; set; }
        public string Course_Name { get; set; }
        public float Course_Ects { get; set; }
        public int Professor_ID { get; set; }
    }
}
