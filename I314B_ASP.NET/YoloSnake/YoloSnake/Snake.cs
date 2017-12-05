using Newtonsoft.Json;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Threading.Tasks;

namespace YoloSnake
{
    public class Snake
    {
        [JsonProperty("id")]
        public string Id { get; set; }
        [JsonProperty("x")]
        public int X { get; set; }
        [JsonProperty("y")]
        public int Y { get; set; }
        [JsonProperty("tail")]
        public int Tail { get; set; }
        [JsonProperty("trail")]
        public Case[] Trail { get; set; }
        [JsonProperty("color")]
        public string Color { get; set; }
    }

    public class Case
    {
        [JsonProperty("x")]
        public int X { get; set; }
        [JsonProperty("y")]
        public int Y { get; set; }
    }
}
