using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;

namespace LINQDataContext
{
    public class Section
    {
        public int Section_ID { get; set; }
        public string Section_Name { get; set; }
        public int Delegate_ID { get; set; } //Is a Student_ID
    }
}
