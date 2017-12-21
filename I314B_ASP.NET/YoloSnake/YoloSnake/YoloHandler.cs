using Newtonsoft.Json;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Net.WebSockets;
using System.Threading.Tasks;
using WebSocketManager;
using WebSocketManager.Common;

namespace YoloSnake
{
    public class YoloHandler : WebSocketHandler
    {
        public YoloHandler(WebSocketConnectionManager webSocketConnectionManager) : base(webSocketConnectionManager)
        {
        }

        public async Task ConnectedSnake(string socketId, string serializedSnake)
        {
            var snake = JsonConvert.DeserializeObject<Snake>(serializedSnake);
            var exists = YoloManager.Instance.Snakes.ContainsKey(snake.Id);
            if(!exists)
            {
                YoloManager.Instance.Snakes.TryAdd(snake.Id, snake);
            }
        }

        public async Task DisconnectedSnake(string socketId, string yolo)
        {
            YoloManager.Instance.Snakes.TryRemove(socketId, out Snake yo);
        }

        public async Task OnMove(string socketId, string snakeData)
        {
            var snake = JsonConvert.DeserializeObject<Snake>(snakeData);
            YoloManager.Instance.Snakes.TryGetValue(snake.Id, out Snake exists);
            if(exists != null)
            {
                exists.X = snake.X;
                exists.Y = snake.Y;
                exists.Tail = snake.Tail;
                exists.Trail = snake.Trail;
            }
        }

        public async Task OnEatApple()
        {
            Random random = new Random();
            YoloManager.Instance.Apple.X = random.Next(0, 20);
            YoloManager.Instance.Apple.Y = random.Next(0, 20);
        }

        public override async Task OnConnected(WebSocket socket)
        {
            await base.OnConnected(socket);

            var socketId = WebSocketConnectionManager.GetId(socket);

            var message = new Message
            {
                MessageType = MessageType.Text,
                Data = $"Snake with socket id: {socketId} is now connected!"
            };

            await SendMessageToAllAsync(message);
        }

        public override async Task OnDisconnected(WebSocket socket)
        {
            await base.OnDisconnected(socket);

            var socketId = WebSocketConnectionManager.GetId(socket);

            var message = new Message
            {
                MessageType = MessageType.Text,
                Data = $"Snake with socket id: {socketId} is now disconnected!"
            };

            await SendMessageToAllAsync(message);
        }
    }
}
