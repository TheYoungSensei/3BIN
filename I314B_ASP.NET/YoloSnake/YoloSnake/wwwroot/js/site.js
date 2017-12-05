var snake;
var apple;
var map;
var snakes = [];
var connection;

window.onload = function () {
    canv = document.getElementById("gc");
    ctx = canv.getContext("2d");
    document.addEventListener("keydown", keyPush);

    map = new Map();
    snake = new Snake();
    apple = new Apple();

    connection = new WebSocketManager.Connection("ws://localhost:5000/server");

    connection.connectionMethods.onConnected = () => {
        snake.id = connection.connectionId;
        connection.invoke("ConnectedSnake", connection.connectionId, JSON.stringify(snake));
    }

    connection.connectionMethods.onDisconnected = () => {
        connection.invoke("DisconnectedSnake", connection.connectionId, "");
    }

    connection.clientMethods["pingSnakes"] = (sersnakes) => {
        snakes = JSON.parse(sersnakes);
    };

    connection.clientMethods["pingApple"] = (serApple) => {
        apple = JSON.parse(serApple);
    };

    connection.start();

    $(window).unload(function () {
        connection.invoke("DisconnectedSnake", connection.connectionId, "");
    });

    setInterval(game, 1000 / 15);
}

function Snake() {
    this.id = "";
    this.color = getRandomColor();
    this.x = Math.floor(Math.random() * map.tilecount);
    this.y = Math.floor(Math.random() * map.tilecount);
    this.xvel = 0;
    this.yvel = 0;
    this.tail = 5;
    this.trail = [];
}

Snake.prototype.eat = function () {
    this.tail++;

}

Snake.prototype.die = function () {
    this.tail = 5;
} 

function Map() {
    this.gridsize = 20;
    this.tilecount = 20;
}

function Apple(x, y) {
    this.x = x;
    this.y = y;
}

Apple.prototype.eaten = function () {
    this.x = Math.floor(Math.random() * map.tilecount);
    this.y = Math.floor(Math.random() * map.tilecount);
}

function game() {
    snake.x += snake.xvel;
    snake.y += snake.yvel;
    if (snake.x < 0) {
        snake.x = map.tilecount - 1;
    }
    if (snake.x > map.tilecount - 1) {
        snake.x = 0;
    }
    if (snake.y < 0) {
        snake.y = map.tilecount - 1;
    }
    if (snake.y > map.tilecount - 1) {
        snake.y = 0;
    }
    ctx.fillStyle = "black";
    ctx.fillRect(0, 0, canv.width, canv.height);
    var conflit = false;

    snakes.forEach(function (sna) {
        if (sna.id !== snake.id) {
            for (var i = 0; i < sna.trail.length; i++) {
                for (var j = 0; j < snake.trail.length; j++) {
                    if (sna.trail[i].x === snake.trail[j].x && sna.trail[i].y === snake.trail[j].y) {
                        snake.tail = 5;
                        conflit = true;
                    }
                }
                ctx.fillStyle = sna.color;
                ctx.fillRect(sna.trail[i].x * map.gridsize, sna.trail[i].y * map.gridsize, map.gridsize - 2, map.gridsize - 2);
            }
        }
    });
    if (conflit === true) {
        ctx.fillStyle = "white";
    } else {
        ctx.fillStyle = "green";
    }
    for (var i = 0; i < snake.trail.length; i++) {
        if (snake.trail[i].x === snake.x && snake.trail[i].y === snake.y) {
            snake.tail = 5;
            ctx.fillStyle = "white";
        }
        ctx.fillRect(snake.trail[i].x * map.gridsize, snake.trail[i].y * map.gridsize, map.gridsize - 2, map.gridsize - 2);
    }

    snake.trail.push({ x: snake.x, y: snake.y });
    while (snake.trail.length > snake.tail) {
        snake.trail.shift();
    }

    if (connection.socket.readyState == 1) {
        connection.invoke("OnMove", connection.connectionId, JSON.stringify(snake));
    }

    if (apple.x === snake.x && apple.y === snake.y) {
        snake.tail++;
        if (connection.socket.readyState == 1) {
            connection.invoke("OnEatApple");
        }
    }

    ctx.fillStyle = "red";
    ctx.fillRect(apple.x * map.gridsize, apple.y * map.gridsize, map.gridsize - 2, map.gridsize - 2);
}
function keyPush(evt) {
    switch (evt.keyCode) {
        case 37:
            snake.xvel = -1; snake.yvel = 0;
            break;
        case 38:
            snake.xvel = 0; snake.yvel = -1;
            break;
        case 39:
            snake.xvel = 1; snake.yvel = 0;
            break;
        case 40:
            snake.xvel = 0; snake.yvel = 1;
            break;
    }
}

function getRandomColor() {
    var letters = '0123456789ABCDEF';
    var color = '#';
    for (var i = 0; i < 6; i++) {
        color += letters[Math.floor(Math.random() * 16)];
    }
    return color;
}
