using Microsoft.Extensions.DependencyInjection;
using Newtonsoft.Json;
using System;
using System.Collections.Concurrent;
using System.Collections.Generic;
using System.Linq;
using System.Threading;
using System.Threading.Tasks;

namespace YoloSnake
{
    public class YoloManager
    {
        private static YoloManager instance;
        private static readonly object yoloLock = new object();

        public ConcurrentDictionary<string, Snake> Snakes { get; set; }
        public Apple Apple { get; set; }
        public Timer Timer;

        public static YoloManager Instance
        {
            get
            {
                lock(yoloLock)
                {
                    if(instance == null)
                    {
                        instance = new YoloManager();
                    }
                    return instance;
                }
            }
        }

        public void initialize()
        {
            Snakes = new ConcurrentDictionary<string, Snake>();
            Apple = new Apple
            {
                X = 15,
                Y = 15
            };
            Timer = new Timer(CallBack, null, 0, 1000 / 15);
        }

        private void CallBack(object state)
        {
            Startup.ServiceProvider.GetRequiredService<YoloHandler>().
                InvokeClientMethodToAllAsync("pingSnakes", JsonConvert.SerializeObject(Snakes.Values)).Wait();
            Startup.ServiceProvider.GetRequiredService<YoloHandler>().
                InvokeClientMethodToAllAsync("pingApple", JsonConvert.SerializeObject(Apple)).Wait();
        }
    }


}
