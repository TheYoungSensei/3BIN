using System;
using System.Collections.Generic;
using System.Dynamic;
using System.Linq;
using System.Threading.Tasks;
using WebSocketManager;

namespace YoloChat
{
    public class YoloHandler : WebSocketHandler
    {

        private readonly YoloManager _yoloManager;
        public YoloHandler(WebSocketConnectionManager webSocketConnectionManager, YoloManager yoloManager) : base(webSocketConnectionManager)
        {
            _yoloManager = yoloManager;
        }

        public async Task SendMessage(string socketId, string message)
        {
            dynamic dynamicMessage = new ExpandoObject();
            dynamicMessage.UserId = socketId;
            dynamicMessage.Message = message;
            _yoloManager.Messages.Add(dynamicMessage);
            await InvokeClientMethodToAllAsync("pingMessage", socketId, message);
        }
    }
}
